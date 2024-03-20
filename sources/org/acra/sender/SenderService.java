package org.acra.sender;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.JobIntentService;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.acra.ACRA;
import org.acra.collections.ImmutableList;
import org.acra.config.CoreConfiguration;
import org.acra.file.CrashReportFileNameParser;
import org.acra.file.ReportLocator;
import org.acra.util.InstanceCreator;
import org.acra.util.ToastSender;

public class SenderService extends JobIntentService {
    public static final String EXTRA_ACRA_CONFIG = "acraConfig";
    public static final String EXTRA_APPROVE_REPORTS_FIRST = "approveReportsFirst";
    public static final String EXTRA_ONLY_SEND_SILENT_REPORTS = "onlySendSilentReports";
    private final ReportLocator locator = new ReportLocator(this);

    /* access modifiers changed from: protected */
    public void onHandleWork(Intent intent) {
        if (intent.hasExtra(EXTRA_ACRA_CONFIG)) {
            int i = 0;
            boolean booleanExtra = intent.getBooleanExtra(EXTRA_ONLY_SEND_SILENT_REPORTS, false);
            boolean booleanExtra2 = intent.getBooleanExtra(EXTRA_APPROVE_REPORTS_FIRST, false);
            CoreConfiguration coreConfiguration = (CoreConfiguration) intent.getSerializableExtra(EXTRA_ACRA_CONFIG);
            ImmutableList<Class<? extends ReportSenderFactory>> reportSenderFactoryClasses = coreConfiguration.reportSenderFactoryClasses();
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "About to start sending reports from SenderService");
            }
            try {
                List<ReportSender> senderInstances = getSenderInstances(coreConfiguration, reportSenderFactoryClasses);
                if (booleanExtra2) {
                    markReportsAsApproved();
                }
                File[] approvedReports = this.locator.getApprovedReports();
                ReportDistributor reportDistributor = new ReportDistributor(this, coreConfiguration, senderInstances);
                CrashReportFileNameParser crashReportFileNameParser = new CrashReportFileNameParser();
                int length = approvedReports.length;
                int i2 = 0;
                boolean z = false;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    File file = approvedReports[i];
                    boolean z2 = !crashReportFileNameParser.isSilent(file.getName());
                    if (!booleanExtra || !z2) {
                        z |= z2;
                        if (i2 >= 5) {
                            break;
                        } else if (reportDistributor.distribute(file)) {
                            i2++;
                        }
                    }
                    i++;
                }
                if (z) {
                    String reportSendSuccessToast = i2 > 0 ? coreConfiguration.reportSendSuccessToast() : coreConfiguration.reportSendFailureToast();
                    if (reportSendSuccessToast != null) {
                        new Handler(Looper.getMainLooper()).post(new Runnable(reportSendSuccessToast) {
                            private final /* synthetic */ String f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void run() {
                                SenderService.this.lambda$onHandleWork$0$SenderService(this.f$1);
                            }
                        });
                    }
                }
            } catch (Exception e) {
                ACRA.log.e(ACRA.LOG_TAG, "", e);
            }
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Finished sending reports from SenderService");
            }
        } else if (ACRA.DEV_LOGGING) {
            ACRA.log.d(ACRA.LOG_TAG, "SenderService was started but no valid intent was delivered, will now quit");
        }
    }

    public /* synthetic */ void lambda$onHandleWork$0$SenderService(String str) {
        ToastSender.sendToast(this, str, 1);
    }

    private List<ReportSender> getSenderInstances(CoreConfiguration coreConfiguration, Collection<Class<? extends ReportSenderFactory>> collection) {
        ArrayList arrayList = new ArrayList();
        for (T create : new InstanceCreator().create(collection)) {
            arrayList.add(create.create(getApplication(), coreConfiguration));
        }
        return arrayList;
    }

    private void markReportsAsApproved() {
        if (ACRA.DEV_LOGGING) {
            ACRA.log.d(ACRA.LOG_TAG, "Mark all pending reports as approved.");
        }
        for (File file : this.locator.getUnapprovedReports()) {
            File file2 = new File(this.locator.getApprovedFolder(), file.getName());
            if (!file.renameTo(file2)) {
                ACRA.log.w(ACRA.LOG_TAG, "Could not rename approved report from " + file + " to " + file2);
            }
        }
    }
}
