package org.acra.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.acra.ReportField;
import org.acra.attachment.AttachmentUriProvider;
import org.acra.attachment.DefaultAttachmentProvider;
import org.acra.config.DefaultRetryPolicy;
import org.acra.config.RetryPolicy;
import org.acra.data.StringFormat;
import org.acra.file.Directory;
import org.acra.sender.DefaultReportSenderFactory;
import org.acra.sender.ReportSenderFactory;

@Inherited
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AcraCore {
    String[] additionalDropBoxTags() default {};

    String[] additionalSharedPreferences() default {};

    boolean alsoReportToAndroidFramework() default false;

    String applicationLogFile() default "";

    Directory applicationLogFileDir() default Directory.FILES_LEGACY;

    int applicationLogFileLines() default 100;

    Class<? extends AttachmentUriProvider> attachmentUriProvider() default DefaultAttachmentProvider.class;

    String[] attachmentUris() default {};

    Class buildConfigClass() default Object.class;

    boolean deleteOldUnsentReportsOnApplicationStart() default true;

    boolean deleteUnapprovedReportsOnApplicationStart() default true;

    int dropboxCollectionMinutes() default 5;

    String[] excludeMatchingSettingsKeys() default {};

    String[] excludeMatchingSharedPreferencesKeys() default {};

    boolean includeDropBoxSystemTags() default false;

    String[] logcatArguments() default {"-t", "100", "-v", "time"};

    boolean logcatFilterByPid() default true;

    boolean logcatReadNonBlocking() default false;

    boolean parallel() default true;

    ReportField[] reportContent() default {};

    StringFormat reportFormat() default StringFormat.JSON;

    Class<? extends ReportSenderFactory>[] reportSenderFactoryClasses() default {DefaultReportSenderFactory.class};

    int resReportSendFailureToast() default 0;

    int resReportSendSuccessToast() default 0;

    Class<? extends RetryPolicy> retryPolicyClass() default DefaultRetryPolicy.class;

    boolean sendReportsInDevMode() default true;

    String sharedPreferencesName() default "";

    boolean stopServicesOnCrash() default false;
}
