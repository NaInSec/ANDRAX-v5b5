package org.acra.sender;

import android.content.Context;
import android.content.pm.PackageManager;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.acra.ACRA;
import org.acra.config.CoreConfiguration;
import org.acra.config.RetryPolicy;
import org.acra.data.CrashReportData;
import org.acra.file.CrashReportPersister;
import org.acra.log.ACRALog;
import org.acra.util.IOUtils;
import org.acra.util.InstanceCreator;
import org.json.JSONException;

final class ReportDistributor {
    private final CoreConfiguration config;
    private final Context context;
    private final List<ReportSender> reportSenders;

    ReportDistributor(Context context2, CoreConfiguration coreConfiguration, List<ReportSender> list) {
        this.context = context2;
        this.config = coreConfiguration;
        this.reportSenders = list;
    }

    public boolean distribute(File file) {
        ACRALog aCRALog = ACRA.log;
        String str = ACRA.LOG_TAG;
        aCRALog.i(str, "Sending report " + file);
        try {
            sendCrashReport(new CrashReportPersister().load(file));
            IOUtils.deleteFile(file);
            return true;
        } catch (RuntimeException e) {
            ACRALog aCRALog2 = ACRA.log;
            String str2 = ACRA.LOG_TAG;
            aCRALog2.e(str2, "Failed to send crash reports for " + file, e);
            IOUtils.deleteFile(file);
            return false;
        } catch (IOException e2) {
            ACRALog aCRALog3 = ACRA.log;
            String str3 = ACRA.LOG_TAG;
            aCRALog3.e(str3, "Failed to load crash report for " + file, e2);
            IOUtils.deleteFile(file);
            return false;
        } catch (JSONException e3) {
            ACRALog aCRALog4 = ACRA.log;
            String str4 = ACRA.LOG_TAG;
            aCRALog4.e(str4, "Failed to load crash report for " + file, e3);
            IOUtils.deleteFile(file);
            return false;
        } catch (ReportSenderException e4) {
            ACRALog aCRALog5 = ACRA.log;
            String str5 = ACRA.LOG_TAG;
            aCRALog5.e(str5, "Failed to send crash report for " + file, e4);
            return false;
        }
    }

    private void sendCrashReport(CrashReportData crashReportData) throws ReportSenderException {
        if (!isDebuggable() || this.config.sendReportsInDevMode()) {
            LinkedList<RetryPolicy.FailedSender> linkedList = new LinkedList<>();
            for (ReportSender next : this.reportSenders) {
                try {
                    if (ACRA.DEV_LOGGING) {
                        ACRALog aCRALog = ACRA.log;
                        String str = ACRA.LOG_TAG;
                        aCRALog.d(str, "Sending report using " + next.getClass().getName());
                    }
                    next.send(this.context, crashReportData);
                    if (ACRA.DEV_LOGGING) {
                        ACRALog aCRALog2 = ACRA.log;
                        String str2 = ACRA.LOG_TAG;
                        aCRALog2.d(str2, "Sent report using " + next.getClass().getName());
                    }
                } catch (ReportSenderException e) {
                    linkedList.add(new RetryPolicy.FailedSender(next, e));
                }
            }
            InstanceCreator instanceCreator = new InstanceCreator();
            if (linkedList.isEmpty()) {
                if (ACRA.DEV_LOGGING) {
                    ACRA.log.d(ACRA.LOG_TAG, "Report was sent by all senders");
                }
            } else if (!((RetryPolicy) instanceCreator.create(this.config.retryPolicyClass(), $$Lambda$b56CD9vHz9YzUv87HD1wOykEnVg.INSTANCE)).shouldRetrySend(this.reportSenders, linkedList)) {
                StringBuilder sb = new StringBuilder("ReportSenders of classes [");
                for (RetryPolicy.FailedSender sender : linkedList) {
                    sb.append(sender.getSender().getClass().getName());
                    sb.append(", ");
                }
                sb.append("] failed, but Policy marked this task as complete. ACRA will not send this report again.");
                ACRA.log.w(ACRA.LOG_TAG, sb.toString());
            } else {
                throw new ReportSenderException("Policy marked this task as incomplete. ACRA will try to send this report again.", ((RetryPolicy.FailedSender) linkedList.get(0)).getException());
            }
        }
    }

    private boolean isDebuggable() {
        try {
            if ((this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 0).flags & 2) > 0) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
