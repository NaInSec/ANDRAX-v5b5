package org.acra.collector;

import android.content.Context;
import android.os.Build;
import android.os.DropBoxManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.collections.ImmutableList;
import org.acra.collector.Collector;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.log.ACRALog;
import org.acra.prefs.SharedPreferencesFactory;
import org.acra.util.PackageManagerWrapper;
import org.acra.util.SystemServices;
import org.json.JSONException;
import org.json.JSONObject;

public final class DropBoxCollector extends BaseReportFieldCollector {
    private static final String[] SYSTEM_TAGS = {"system_app_anr", "system_app_wtf", "system_app_crash", "system_server_anr", "system_server_wtf", "system_server_crash", "BATTERY_DISCHARGE_INFO", "SYSTEM_RECOVERY_LOG", "SYSTEM_BOOT", "SYSTEM_LAST_KMSG", "APANIC_CONSOLE", "APANIC_THREADS", "SYSTEM_RESTART", "SYSTEM_TOMBSTONE", "data_app_strictmode"};
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.getDefault());

    public DropBoxCollector() {
        super(ReportField.DROPBOX, new ReportField[0]);
    }

    public Collector.Order getOrder() {
        return Collector.Order.FIRST;
    }

    /* access modifiers changed from: package-private */
    public void collect(ReportField reportField, Context context, CoreConfiguration coreConfiguration, ReportBuilder reportBuilder, CrashReportData crashReportData) throws Exception {
        DropBoxManager dropBoxManager = SystemServices.getDropBoxManager(context);
        Calendar instance = Calendar.getInstance();
        instance.roll(12, -coreConfiguration.dropboxCollectionMinutes());
        long timeInMillis = instance.getTimeInMillis();
        this.dateFormat.format(instance.getTime());
        ArrayList<String> arrayList = new ArrayList<>();
        if (coreConfiguration.includeDropBoxSystemTags()) {
            arrayList.addAll(Arrays.asList(SYSTEM_TAGS));
        }
        ImmutableList<String> additionalDropBoxTags = coreConfiguration.additionalDropBoxTags();
        if (!additionalDropBoxTags.isEmpty()) {
            arrayList.addAll(additionalDropBoxTags);
        }
        if (!arrayList.isEmpty()) {
            JSONObject jSONObject = new JSONObject();
            for (String str : arrayList) {
                StringBuilder sb = new StringBuilder();
                DropBoxManager.Entry nextEntry = dropBoxManager.getNextEntry(str, timeInMillis);
                if (nextEntry == null) {
                    sb.append("Nothing.");
                    sb.append(10);
                } else {
                    while (nextEntry != null) {
                        long timeMillis = nextEntry.getTimeMillis();
                        instance.setTimeInMillis(timeMillis);
                        sb.append('@');
                        sb.append(this.dateFormat.format(instance.getTime()));
                        sb.append(10);
                        String text = nextEntry.getText(500);
                        if (text != null) {
                            sb.append("Text: ");
                            sb.append(text);
                            sb.append(10);
                        } else {
                            sb.append("Not Text!");
                            sb.append(10);
                        }
                        nextEntry.close();
                        nextEntry = dropBoxManager.getNextEntry(str, timeMillis);
                    }
                    try {
                        jSONObject.put(str, sb.toString());
                    } catch (JSONException e) {
                        ACRALog aCRALog = ACRA.log;
                        String str2 = ACRA.LOG_TAG;
                        aCRALog.w(str2, "Failed to collect data for tag " + str, e);
                    }
                }
            }
            crashReportData.put(ReportField.DROPBOX, jSONObject);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldCollect(Context context, CoreConfiguration coreConfiguration, ReportField reportField, ReportBuilder reportBuilder) {
        if (!super.shouldCollect(context, coreConfiguration, reportField, reportBuilder) || ((Build.VERSION.SDK_INT < 16 && !new PackageManagerWrapper(context).hasPermission("android.permission.READ_LOGS")) || !new SharedPreferencesFactory(context, coreConfiguration).create().getBoolean(ACRA.PREF_ENABLE_SYSTEM_LOGS, true))) {
            return false;
        }
        return true;
    }
}
