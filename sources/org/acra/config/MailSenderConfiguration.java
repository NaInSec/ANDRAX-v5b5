package org.acra.config;

import java.io.Serializable;

public final class MailSenderConfiguration implements Serializable, Configuration {
    private final boolean enabled;
    private final String mailTo;
    private final boolean reportAsFile;
    private final String reportFileName;
    private final String subject;

    public MailSenderConfiguration(MailSenderConfigurationBuilderImpl mailSenderConfigurationBuilderImpl) {
        this.enabled = mailSenderConfigurationBuilderImpl.enabled();
        this.mailTo = mailSenderConfigurationBuilderImpl.mailTo();
        this.reportAsFile = mailSenderConfigurationBuilderImpl.reportAsFile();
        this.reportFileName = mailSenderConfigurationBuilderImpl.reportFileName();
        this.subject = mailSenderConfigurationBuilderImpl.subject();
    }

    public boolean enabled() {
        return this.enabled;
    }

    public String mailTo() {
        return this.mailTo;
    }

    public boolean reportAsFile() {
        return this.reportAsFile;
    }

    public String reportFileName() {
        return this.reportFileName;
    }

    public String subject() {
        return this.subject;
    }
}
