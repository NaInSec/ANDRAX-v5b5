package org.acra.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.acra.dialog.BaseCrashReportDialog;
import org.acra.dialog.CrashReportDialog;

@Inherited
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AcraDialog {
    Class<? extends BaseCrashReportDialog> reportDialogClass() default CrashReportDialog.class;

    int resCommentPrompt() default 0;

    int resEmailPrompt() default 0;

    int resIcon() default 17301543;

    int resNegativeButtonText() default 17039360;

    int resPositiveButtonText() default 17039370;

    int resText() default 0;

    int resTheme() default 0;

    int resTitle() default 0;
}
