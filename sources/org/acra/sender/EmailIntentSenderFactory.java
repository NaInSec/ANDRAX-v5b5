package org.acra.sender;

import android.content.Context;
import org.acra.config.CoreConfiguration;
import org.acra.config.MailSenderConfiguration;

public final class EmailIntentSenderFactory extends BaseReportSenderFactory {
    public EmailIntentSenderFactory() {
        super(MailSenderConfiguration.class);
    }

    public ReportSender create(Context context, CoreConfiguration coreConfiguration) {
        return new EmailIntentSender(coreConfiguration);
    }
}
