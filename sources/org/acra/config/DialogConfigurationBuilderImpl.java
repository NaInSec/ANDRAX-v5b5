package org.acra.config;

import android.content.Context;
import org.acra.annotation.AcraDialog;
import org.acra.dialog.BaseCrashReportDialog;
import org.acra.dialog.CrashReportDialog;

final class DialogConfigurationBuilderImpl implements DialogConfigurationBuilder {
    private String commentPrompt;
    private final Context context;
    private String emailPrompt;
    private boolean enabled;
    private String negativeButtonText;
    private String positiveButtonText;
    private Class<? extends BaseCrashReportDialog> reportDialogClass;
    private int resIcon;
    private int resTheme;
    private String text;
    private String title;

    DialogConfigurationBuilderImpl(Context context2) {
        AcraDialog acraDialog = (AcraDialog) context2.getClass().getAnnotation(AcraDialog.class);
        this.context = context2;
        this.enabled = acraDialog != null;
        if (this.enabled) {
            this.reportDialogClass = acraDialog.reportDialogClass();
            if (acraDialog.resPositiveButtonText() != 0) {
                this.positiveButtonText = this.context.getString(acraDialog.resPositiveButtonText());
            }
            if (acraDialog.resNegativeButtonText() != 0) {
                this.negativeButtonText = this.context.getString(acraDialog.resNegativeButtonText());
            }
            if (acraDialog.resCommentPrompt() != 0) {
                this.commentPrompt = this.context.getString(acraDialog.resCommentPrompt());
            }
            if (acraDialog.resEmailPrompt() != 0) {
                this.emailPrompt = this.context.getString(acraDialog.resEmailPrompt());
            }
            this.resIcon = acraDialog.resIcon();
            if (acraDialog.resText() != 0) {
                this.text = this.context.getString(acraDialog.resText());
            }
            if (acraDialog.resTitle() != 0) {
                this.title = this.context.getString(acraDialog.resTitle());
            }
            this.resTheme = acraDialog.resTheme();
            return;
        }
        this.reportDialogClass = CrashReportDialog.class;
        this.positiveButtonText = this.context.getString(17039370);
        this.negativeButtonText = this.context.getString(17039360);
        this.resIcon = 17301543;
        this.resTheme = 0;
    }

    public DialogConfigurationBuilderImpl setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean enabled() {
        return this.enabled;
    }

    public DialogConfigurationBuilderImpl setReportDialogClass(Class<? extends BaseCrashReportDialog> cls) {
        this.reportDialogClass = cls;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Class<? extends BaseCrashReportDialog> reportDialogClass() {
        return this.reportDialogClass;
    }

    public DialogConfigurationBuilderImpl setPositiveButtonText(String str) {
        this.positiveButtonText = str;
        return this;
    }

    public DialogConfigurationBuilderImpl setResPositiveButtonText(int i) {
        this.positiveButtonText = this.context.getString(i);
        return this;
    }

    /* access modifiers changed from: package-private */
    public String positiveButtonText() {
        return this.positiveButtonText;
    }

    public DialogConfigurationBuilderImpl setNegativeButtonText(String str) {
        this.negativeButtonText = str;
        return this;
    }

    public DialogConfigurationBuilderImpl setResNegativeButtonText(int i) {
        this.negativeButtonText = this.context.getString(i);
        return this;
    }

    /* access modifiers changed from: package-private */
    public String negativeButtonText() {
        return this.negativeButtonText;
    }

    public DialogConfigurationBuilderImpl setCommentPrompt(String str) {
        this.commentPrompt = str;
        return this;
    }

    public DialogConfigurationBuilderImpl setResCommentPrompt(int i) {
        this.commentPrompt = this.context.getString(i);
        return this;
    }

    /* access modifiers changed from: package-private */
    public String commentPrompt() {
        return this.commentPrompt;
    }

    public DialogConfigurationBuilderImpl setEmailPrompt(String str) {
        this.emailPrompt = str;
        return this;
    }

    public DialogConfigurationBuilderImpl setResEmailPrompt(int i) {
        this.emailPrompt = this.context.getString(i);
        return this;
    }

    /* access modifiers changed from: package-private */
    public String emailPrompt() {
        return this.emailPrompt;
    }

    public DialogConfigurationBuilderImpl setResIcon(int i) {
        this.resIcon = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public int resIcon() {
        return this.resIcon;
    }

    public DialogConfigurationBuilderImpl setText(String str) {
        this.text = str;
        return this;
    }

    public DialogConfigurationBuilderImpl setResText(int i) {
        this.text = this.context.getString(i);
        return this;
    }

    /* access modifiers changed from: package-private */
    public String text() {
        return this.text;
    }

    public DialogConfigurationBuilderImpl setTitle(String str) {
        this.title = str;
        return this;
    }

    public DialogConfigurationBuilderImpl setResTitle(int i) {
        this.title = this.context.getString(i);
        return this;
    }

    /* access modifiers changed from: package-private */
    public String title() {
        return this.title;
    }

    public DialogConfigurationBuilderImpl setResTheme(int i) {
        this.resTheme = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public int resTheme() {
        return this.resTheme;
    }

    public DialogConfiguration build() throws ACRAConfigurationException {
        if (this.enabled) {
            ClassValidator.check(this.reportDialogClass);
            if (this.reportDialogClass == CrashReportDialog.class && this.text == null) {
                throw new ACRAConfigurationException("One of reportDialogClass, text must not be default");
            }
        }
        return new DialogConfiguration(this);
    }
}
