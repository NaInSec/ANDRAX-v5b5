package org.acra.config;

import android.content.Context;
import org.acra.annotation.AcraMailSender;
import org.acra.sender.EmailIntentSender;

final class MailSenderConfigurationBuilderImpl implements MailSenderConfigurationBuilder {
    private final Context context;
    private boolean enabled;
    private String mailTo;
    private boolean reportAsFile;
    private String reportFileName;
    private String subject;

    MailSenderConfigurationBuilderImpl(Context context2) {
        AcraMailSender acraMailSender = (AcraMailSender) context2.getClass().getAnnotation(AcraMailSender.class);
        this.context = context2;
        this.enabled = acraMailSender != null;
        if (this.enabled) {
            this.mailTo = acraMailSender.mailTo();
            this.reportAsFile = acraMailSender.reportAsFile();
            this.reportFileName = acraMailSender.reportFileName();
            if (acraMailSender.resSubject() != 0) {
                this.subject = this.context.getString(acraMailSender.resSubject());
                return;
            }
            return;
        }
        this.reportAsFile = true;
        this.reportFileName = EmailIntentSender.DEFAULT_REPORT_FILENAME;
    }

    public MailSenderConfigurationBuilderImpl setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean enabled() {
        return this.enabled;
    }

    public MailSenderConfigurationBuilderImpl setMailTo(String str) {
        this.mailTo = str;
        return this;
    }

    /* access modifiers changed from: package-private */
    public String mailTo() {
        return this.mailTo;
    }

    public MailSenderConfigurationBuilderImpl setReportAsFile(boolean z) {
        this.reportAsFile = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean reportAsFile() {
        return this.reportAsFile;
    }

    public MailSenderConfigurationBuilderImpl setReportFileName(String str) {
        this.reportFileName = str;
        return this;
    }

    /* access modifiers changed from: package-private */
    public String reportFileName() {
        return this.reportFileName;
    }

    public MailSenderConfigurationBuilderImpl setSubject(String str) {
        this.subject = str;
        return this;
    }

    public MailSenderConfigurationBuilderImpl setResSubject(int i) {
        this.subject = this.context.getString(i);
        return this;
    }

    /* access modifiers changed from: package-private */
    public String subject() {
        return this.subject;
    }

    public MailSenderConfiguration build() throws ACRAConfigurationException {
        if (!this.enabled || this.mailTo != null) {
            return new MailSenderConfiguration(this);
        }
        throw new ACRAConfigurationException("mailTo has to be set");
    }
}
