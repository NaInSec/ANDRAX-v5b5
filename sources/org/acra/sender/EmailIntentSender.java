package org.acra.sender;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.acra.ACRA;
import org.acra.attachment.AcraContentProvider;
import org.acra.attachment.AttachmentUriProvider;
import org.acra.config.ConfigUtils;
import org.acra.config.CoreConfiguration;
import org.acra.config.MailSenderConfiguration;
import org.acra.data.CrashReportData;
import org.acra.util.IOUtils;
import org.acra.util.InstanceCreator;
import org.apache.commons.lang3.StringUtils;

public class EmailIntentSender implements ReportSender {
    public static final String DEFAULT_REPORT_FILENAME = "ACRA-report.stacktrace";
    private final CoreConfiguration config;
    private final MailSenderConfiguration mailConfig;

    public EmailIntentSender(CoreConfiguration coreConfiguration) {
        this.config = coreConfiguration;
        this.mailConfig = (MailSenderConfiguration) ConfigUtils.getPluginConfiguration(coreConfiguration, MailSenderConfiguration.class);
    }

    public void send(Context context, CrashReportData crashReportData) throws ReportSenderException {
        PackageManager packageManager = context.getPackageManager();
        String buildSubject = buildSubject(context);
        try {
            String formattedString = this.config.reportFormat().toFormattedString(crashReportData, this.config.reportContent(), StringUtils.LF, "\n\t", false);
            ArrayList arrayList = new ArrayList();
            boolean fillAttachmentList = fillAttachmentList(context, formattedString, arrayList);
            Intent buildResolveIntent = buildResolveIntent(buildSubject, formattedString);
            ComponentName resolveActivity = buildResolveIntent.resolveActivity(packageManager);
            if (resolveActivity == null) {
                throw new ReportSenderException("No email client found");
            } else if (arrayList.size() == 0) {
                context.startActivity(buildResolveIntent);
            } else {
                Intent buildAttachmentIntent = buildAttachmentIntent(buildSubject, formattedString, arrayList, fillAttachmentList);
                List<Intent> buildInitialIntents = buildInitialIntents(packageManager, buildResolveIntent, buildAttachmentIntent);
                String packageName = getPackageName(resolveActivity, buildInitialIntents);
                buildAttachmentIntent.setPackage(packageName);
                if (packageName == null) {
                    for (Intent next : buildInitialIntents) {
                        grantPermission(context, next, next.getPackage(), arrayList);
                    }
                    showChooser(context, buildInitialIntents);
                } else if (buildAttachmentIntent.resolveActivity(packageManager) != null) {
                    grantPermission(context, buildAttachmentIntent, packageName, arrayList);
                    context.startActivity(buildAttachmentIntent);
                } else {
                    ACRA.log.w(ACRA.LOG_TAG, "No email client supporting attachments found. Attachments will be ignored");
                    context.startActivity(buildResolveIntent);
                }
            }
        } catch (Exception e) {
            throw new ReportSenderException("Failed to convert Report to text", e);
        }
    }

    private String getPackageName(ComponentName componentName, List<Intent> list) {
        String packageName = componentName.getPackageName();
        if (!packageName.equals("android")) {
            return packageName;
        }
        if (list.size() > 1) {
            return null;
        }
        return list.size() == 1 ? list.get(0).getPackage() : packageName;
    }

    /* access modifiers changed from: protected */
    public Intent buildAttachmentIntent(String str, String str2, ArrayList<Uri> arrayList, boolean z) {
        Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{((MailSenderConfiguration) ConfigUtils.getPluginConfiguration(this.config, MailSenderConfiguration.class)).mailTo()});
        intent.addFlags(268435456);
        intent.putExtra("android.intent.extra.SUBJECT", str);
        intent.setType("message/rfc822");
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
        if (!z) {
            intent.putExtra("android.intent.extra.TEXT", str2);
        }
        return intent;
    }

    /* access modifiers changed from: protected */
    public Intent buildResolveIntent(String str, String str2) {
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.fromParts("mailto", ((MailSenderConfiguration) ConfigUtils.getPluginConfiguration(this.config, MailSenderConfiguration.class)).mailTo(), (String) null));
        intent.addFlags(268435456);
        intent.putExtra("android.intent.extra.SUBJECT", str);
        intent.putExtra("android.intent.extra.TEXT", str2);
        return intent;
    }

    private List<Intent> buildInitialIntents(PackageManager packageManager, Intent intent, Intent intent2) {
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            Intent intent3 = new Intent(intent2);
            intent3.setPackage(resolveInfo.activityInfo.packageName);
            if (intent3.resolveActivity(packageManager) != null) {
                arrayList.add(intent3);
            }
        }
        return arrayList;
    }

    private void showChooser(Context context, List<Intent> list) {
        Intent intent = new Intent("android.intent.action.CHOOSER");
        intent.putExtra("android.intent.extra.INTENT", list.remove(0));
        intent.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) list.toArray(new Intent[list.size()]));
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    private void grantPermission(Context context, Intent intent, String str, List<Uri> list) {
        if (Build.VERSION.SDK_INT >= 21) {
            intent.addFlags(1);
            return;
        }
        for (Uri grantUriPermission : list) {
            context.grantUriPermission(str, grantUriPermission, 1);
        }
    }

    /* access modifiers changed from: protected */
    public String buildSubject(Context context) {
        String subject = this.mailConfig.subject();
        if (subject != null) {
            return subject;
        }
        return context.getPackageName() + " Crash Report";
    }

    /* access modifiers changed from: protected */
    public boolean fillAttachmentList(Context context, String str, List<Uri> list) {
        Uri createAttachmentFromString;
        list.addAll(((AttachmentUriProvider) new InstanceCreator().create(this.config.attachmentUriProvider(), $$Lambda$SRgNHF0fTRPu4DS24hMYEAdAspw.INSTANCE)).getAttachments(context, this.config));
        if (!this.mailConfig.reportAsFile() || (createAttachmentFromString = createAttachmentFromString(context, this.mailConfig.reportFileName(), str)) == null) {
            return false;
        }
        list.add(createAttachmentFromString);
        return true;
    }

    /* access modifiers changed from: protected */
    public Uri createAttachmentFromString(Context context, String str, String str2) {
        File file = new File(context.getCacheDir(), str);
        try {
            IOUtils.writeStringToFile(file, str2);
            return AcraContentProvider.getUriForFile(context, file);
        } catch (IOException unused) {
            return null;
        }
    }
}
