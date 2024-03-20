package org.acra.config;

public interface MailSenderConfigurationBuilder extends ConfigurationBuilder {
    MailSenderConfigurationBuilder setEnabled(boolean z);

    MailSenderConfigurationBuilder setMailTo(String str);

    MailSenderConfigurationBuilder setReportAsFile(boolean z);

    MailSenderConfigurationBuilder setReportFileName(String str);

    MailSenderConfigurationBuilder setResSubject(int i);

    MailSenderConfigurationBuilder setSubject(String str);
}
