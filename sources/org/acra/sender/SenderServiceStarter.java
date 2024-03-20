package org.acra.sender;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.JobIntentService;
import org.acra.ACRA;
import org.acra.config.CoreConfiguration;

public class SenderServiceStarter {
    private final CoreConfiguration config;
    private final Context context;

    public SenderServiceStarter(Context context2, CoreConfiguration coreConfiguration) {
        this.context = context2;
        this.config = coreConfiguration;
    }

    public void startService(boolean z, boolean z2) {
        if (ACRA.DEV_LOGGING) {
            ACRA.log.d(ACRA.LOG_TAG, "About to start SenderService");
        }
        Intent intent = new Intent();
        intent.putExtra(SenderService.EXTRA_ONLY_SEND_SILENT_REPORTS, z);
        intent.putExtra(SenderService.EXTRA_APPROVE_REPORTS_FIRST, z2);
        intent.putExtra(SenderService.EXTRA_ACRA_CONFIG, this.config);
        JobIntentService.enqueueWork(this.context, SenderService.class, 0, intent);
    }
}
