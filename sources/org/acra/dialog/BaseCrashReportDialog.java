package org.acra.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.file.BulkReportDeleter;
import org.acra.file.CrashReportPersister;
import org.acra.interaction.DialogInteraction;
import org.acra.log.ACRALog;
import org.acra.sender.SenderServiceStarter;
import org.json.JSONException;

public abstract class BaseCrashReportDialog extends FragmentActivity {
    private CoreConfiguration config;
    private File reportFile;

    /* access modifiers changed from: protected */
    public void init(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void preInit(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public final void onCreate(Bundle bundle) {
        preInit(bundle);
        super.onCreate(bundle);
        if (ACRA.DEV_LOGGING) {
            ACRALog aCRALog = ACRA.log;
            String str = ACRA.LOG_TAG;
            aCRALog.d(str, "CrashReportDialog extras=" + getIntent().getExtras());
        }
        Serializable serializableExtra = getIntent().getSerializableExtra(DialogInteraction.EXTRA_REPORT_CONFIG);
        Serializable serializableExtra2 = getIntent().getSerializableExtra(DialogInteraction.EXTRA_REPORT_FILE);
        if (!(serializableExtra instanceof CoreConfiguration) || !(serializableExtra2 instanceof File)) {
            ACRA.log.w(ACRA.LOG_TAG, "Illegal or incomplete call of BaseCrashReportDialog.");
            finish();
            return;
        }
        this.config = (CoreConfiguration) serializableExtra;
        this.reportFile = (File) serializableExtra2;
        init(bundle);
    }

    /* access modifiers changed from: protected */
    public final void cancelReports() {
        new Thread(new Runnable() {
            public final void run() {
                BaseCrashReportDialog.this.lambda$cancelReports$0$BaseCrashReportDialog();
            }
        }).start();
    }

    public /* synthetic */ void lambda$cancelReports$0$BaseCrashReportDialog() {
        new BulkReportDeleter(this).deleteReports(false, 0);
    }

    /* access modifiers changed from: protected */
    public final void sendCrash(String str, String str2) {
        new Thread(new Runnable(str, str2) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                BaseCrashReportDialog.this.lambda$sendCrash$1$BaseCrashReportDialog(this.f$1, this.f$2);
            }
        }).start();
    }

    public /* synthetic */ void lambda$sendCrash$1$BaseCrashReportDialog(String str, String str2) {
        CrashReportPersister crashReportPersister = new CrashReportPersister();
        try {
            if (ACRA.DEV_LOGGING) {
                ACRALog aCRALog = ACRA.log;
                String str3 = ACRA.LOG_TAG;
                aCRALog.d(str3, "Add user comment to " + this.reportFile);
            }
            CrashReportData load = crashReportPersister.load(this.reportFile);
            ReportField reportField = ReportField.USER_COMMENT;
            if (str == null) {
                str = "";
            }
            load.put(reportField, str);
            ReportField reportField2 = ReportField.USER_EMAIL;
            if (str2 == null) {
                str2 = "";
            }
            load.put(reportField2, str2);
            crashReportPersister.store(load, this.reportFile);
        } catch (IOException | JSONException e) {
            ACRA.log.w(ACRA.LOG_TAG, "User comment not added: ", e);
        }
        new SenderServiceStarter(this, this.config).startService(false, true);
    }

    /* access modifiers changed from: protected */
    public final CoreConfiguration getConfig() {
        return this.config;
    }
}
