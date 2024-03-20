package org.acra;

public final class ACRAConstants {
    public static final String APPROVED_SUFFIX = "-approved";
    public static final String DATE_TIME_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ";
    public static final int DEFAULT_BUFFER_SIZE_IN_BYTES = 8192;
    public static final String DEFAULT_CERTIFICATE_TYPE = "X.509";
    public static final int DEFAULT_LOG_LINES = 100;
    public static final ReportField[] DEFAULT_REPORT_FIELDS = {ReportField.REPORT_ID, ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME, ReportField.PACKAGE_NAME, ReportField.FILE_PATH, ReportField.PHONE_MODEL, ReportField.BRAND, ReportField.PRODUCT, ReportField.ANDROID_VERSION, ReportField.BUILD, ReportField.TOTAL_MEM_SIZE, ReportField.AVAILABLE_MEM_SIZE, ReportField.BUILD_CONFIG, ReportField.CUSTOM_DATA, ReportField.IS_SILENT, ReportField.STACK_TRACE, ReportField.INITIAL_CONFIGURATION, ReportField.CRASH_CONFIGURATION, ReportField.DISPLAY, ReportField.USER_COMMENT, ReportField.USER_EMAIL, ReportField.USER_APP_START_DATE, ReportField.USER_CRASH_DATE, ReportField.DUMPSYS_MEMINFO, ReportField.LOGCAT, ReportField.INSTALLATION_ID, ReportField.DEVICE_FEATURES, ReportField.ENVIRONMENT, ReportField.SHARED_PREFERENCES};
    public static final int DEFAULT_RES_VALUE = 0;
    public static final String DEFAULT_STRING_VALUE = "";
    public static final int MAX_SEND_REPORTS = 5;
    public static final String NOT_AVAILABLE = "N/A";
    public static final String NULL_VALUE = "ACRA-NULL-STRING";
    public static final String REPORTFILE_EXTENSION = ".stacktrace";
    public static final String SILENT_SUFFIX = ("-" + ReportField.IS_SILENT);
    public static final String UTF8 = "UTF-8";

    private ACRAConstants() {
    }
}
