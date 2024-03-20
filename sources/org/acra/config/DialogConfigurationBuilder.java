package org.acra.config;

import org.acra.dialog.BaseCrashReportDialog;

public interface DialogConfigurationBuilder extends ConfigurationBuilder {
    DialogConfigurationBuilder setCommentPrompt(String str);

    DialogConfigurationBuilder setEmailPrompt(String str);

    DialogConfigurationBuilder setEnabled(boolean z);

    DialogConfigurationBuilder setNegativeButtonText(String str);

    DialogConfigurationBuilder setPositiveButtonText(String str);

    DialogConfigurationBuilder setReportDialogClass(Class<? extends BaseCrashReportDialog> cls);

    DialogConfigurationBuilder setResCommentPrompt(int i);

    DialogConfigurationBuilder setResEmailPrompt(int i);

    DialogConfigurationBuilder setResIcon(int i);

    DialogConfigurationBuilder setResNegativeButtonText(int i);

    DialogConfigurationBuilder setResPositiveButtonText(int i);

    DialogConfigurationBuilder setResText(int i);

    DialogConfigurationBuilder setResTheme(int i);

    DialogConfigurationBuilder setResTitle(int i);

    DialogConfigurationBuilder setText(String str);

    DialogConfigurationBuilder setTitle(String str);
}
