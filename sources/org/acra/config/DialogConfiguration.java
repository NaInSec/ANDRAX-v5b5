package org.acra.config;

import java.io.Serializable;
import org.acra.dialog.BaseCrashReportDialog;

public final class DialogConfiguration implements Serializable, Configuration {
    private final String commentPrompt;
    private final String emailPrompt;
    private final boolean enabled;
    private final String negativeButtonText;
    private final String positiveButtonText;
    private final Class<? extends BaseCrashReportDialog> reportDialogClass;
    private final int resIcon;
    private final int resTheme;
    private final String text;
    private final String title;

    public DialogConfiguration(DialogConfigurationBuilderImpl dialogConfigurationBuilderImpl) {
        this.enabled = dialogConfigurationBuilderImpl.enabled();
        this.reportDialogClass = dialogConfigurationBuilderImpl.reportDialogClass();
        this.positiveButtonText = dialogConfigurationBuilderImpl.positiveButtonText();
        this.negativeButtonText = dialogConfigurationBuilderImpl.negativeButtonText();
        this.commentPrompt = dialogConfigurationBuilderImpl.commentPrompt();
        this.emailPrompt = dialogConfigurationBuilderImpl.emailPrompt();
        this.resIcon = dialogConfigurationBuilderImpl.resIcon();
        this.text = dialogConfigurationBuilderImpl.text();
        this.title = dialogConfigurationBuilderImpl.title();
        this.resTheme = dialogConfigurationBuilderImpl.resTheme();
    }

    public boolean enabled() {
        return this.enabled;
    }

    public Class<? extends BaseCrashReportDialog> reportDialogClass() {
        return this.reportDialogClass;
    }

    public String positiveButtonText() {
        return this.positiveButtonText;
    }

    public String negativeButtonText() {
        return this.negativeButtonText;
    }

    public String commentPrompt() {
        return this.commentPrompt;
    }

    public String emailPrompt() {
        return this.emailPrompt;
    }

    public int resIcon() {
        return this.resIcon;
    }

    public String text() {
        return this.text;
    }

    public String title() {
        return this.title;
    }

    public int resTheme() {
        return this.resTheme;
    }
}
