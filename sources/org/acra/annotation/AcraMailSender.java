package org.acra.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AcraMailSender {
    String mailTo();

    boolean reportAsFile() default true;

    String reportFileName() default "ACRA-report.stacktrace";

    int resSubject() default 0;
}
