package org.acra.config;

import android.content.Context;
import java.util.List;
import java.util.Set;
import org.acra.ReportField;
import org.acra.attachment.AttachmentUriProvider;
import org.acra.data.StringFormat;
import org.acra.file.Directory;
import org.acra.sender.ReportSenderFactory;

public final class CoreConfigurationBuilder implements ConfigurationBuilder {
    private String[] additionalDropBoxTags;
    private String[] additionalSharedPreferences;
    private boolean alsoReportToAndroidFramework;
    private String applicationLogFile;
    private Directory applicationLogFileDir;
    private int applicationLogFileLines;
    private Class<? extends AttachmentUriProvider> attachmentUriProvider;
    private String[] attachmentUris;
    private Class buildConfigClass;
    private final Context context;
    private final BaseCoreConfigurationBuilder delegate;
    private boolean deleteOldUnsentReportsOnApplicationStart;
    private boolean deleteUnapprovedReportsOnApplicationStart;
    private int dropboxCollectionMinutes;
    private boolean enabled;
    private String[] excludeMatchingSettingsKeys;
    private String[] excludeMatchingSharedPreferencesKeys;
    private boolean includeDropBoxSystemTags;
    private String[] logcatArguments;
    private boolean logcatFilterByPid;
    private boolean logcatReadNonBlocking;
    private boolean parallel;
    private ReportField[] reportContent;
    private StringFormat reportFormat;
    private String reportSendFailureToast;
    private String reportSendSuccessToast;
    private Class<? extends ReportSenderFactory>[] reportSenderFactoryClasses;
    private Class<? extends RetryPolicy> retryPolicyClass;
    private boolean sendReportsInDevMode;
    private String sharedPreferencesName;
    private boolean stopServicesOnCrash;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: java.lang.Class<? extends org.acra.sender.ReportSenderFactory>[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CoreConfigurationBuilder(android.content.Context r7) {
        /*
            r6 = this;
            r6.<init>()
            java.lang.Class r0 = r7.getClass()
            java.lang.Class<org.acra.annotation.AcraCore> r1 = org.acra.annotation.AcraCore.class
            java.lang.annotation.Annotation r0 = r0.getAnnotation(r1)
            org.acra.annotation.AcraCore r0 = (org.acra.annotation.AcraCore) r0
            r6.context = r7
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0017
            r3 = 1
            goto L_0x0018
        L_0x0017:
            r3 = 0
        L_0x0018:
            r6.enabled = r3
            org.acra.config.BaseCoreConfigurationBuilder r3 = new org.acra.config.BaseCoreConfigurationBuilder
            r3.<init>(r7)
            r6.delegate = r3
            boolean r7 = r6.enabled
            if (r7 == 0) goto L_0x00e6
            java.lang.String r7 = r0.sharedPreferencesName()
            r6.sharedPreferencesName = r7
            boolean r7 = r0.includeDropBoxSystemTags()
            r6.includeDropBoxSystemTags = r7
            java.lang.String[] r7 = r0.additionalDropBoxTags()
            r6.additionalDropBoxTags = r7
            int r7 = r0.dropboxCollectionMinutes()
            r6.dropboxCollectionMinutes = r7
            java.lang.String[] r7 = r0.logcatArguments()
            r6.logcatArguments = r7
            org.acra.ReportField[] r7 = r0.reportContent()
            r6.reportContent = r7
            boolean r7 = r0.deleteUnapprovedReportsOnApplicationStart()
            r6.deleteUnapprovedReportsOnApplicationStart = r7
            boolean r7 = r0.deleteOldUnsentReportsOnApplicationStart()
            r6.deleteOldUnsentReportsOnApplicationStart = r7
            boolean r7 = r0.alsoReportToAndroidFramework()
            r6.alsoReportToAndroidFramework = r7
            java.lang.String[] r7 = r0.additionalSharedPreferences()
            r6.additionalSharedPreferences = r7
            boolean r7 = r0.logcatFilterByPid()
            r6.logcatFilterByPid = r7
            boolean r7 = r0.logcatReadNonBlocking()
            r6.logcatReadNonBlocking = r7
            boolean r7 = r0.sendReportsInDevMode()
            r6.sendReportsInDevMode = r7
            java.lang.String[] r7 = r0.excludeMatchingSharedPreferencesKeys()
            r6.excludeMatchingSharedPreferencesKeys = r7
            java.lang.String[] r7 = r0.excludeMatchingSettingsKeys()
            r6.excludeMatchingSettingsKeys = r7
            java.lang.Class r7 = r0.buildConfigClass()
            r6.buildConfigClass = r7
            java.lang.Class[] r7 = r0.reportSenderFactoryClasses()
            r6.reportSenderFactoryClasses = r7
            java.lang.String r7 = r0.applicationLogFile()
            r6.applicationLogFile = r7
            int r7 = r0.applicationLogFileLines()
            r6.applicationLogFileLines = r7
            org.acra.file.Directory r7 = r0.applicationLogFileDir()
            r6.applicationLogFileDir = r7
            java.lang.Class r7 = r0.retryPolicyClass()
            r6.retryPolicyClass = r7
            boolean r7 = r0.stopServicesOnCrash()
            r6.stopServicesOnCrash = r7
            java.lang.String[] r7 = r0.attachmentUris()
            r6.attachmentUris = r7
            java.lang.Class r7 = r0.attachmentUriProvider()
            r6.attachmentUriProvider = r7
            int r7 = r0.resReportSendSuccessToast()
            if (r7 == 0) goto L_0x00c7
            android.content.Context r7 = r6.context
            int r1 = r0.resReportSendSuccessToast()
            java.lang.String r7 = r7.getString(r1)
            r6.reportSendSuccessToast = r7
        L_0x00c7:
            int r7 = r0.resReportSendFailureToast()
            if (r7 == 0) goto L_0x00d9
            android.content.Context r7 = r6.context
            int r1 = r0.resReportSendFailureToast()
            java.lang.String r7 = r7.getString(r1)
            r6.reportSendFailureToast = r7
        L_0x00d9:
            org.acra.data.StringFormat r7 = r0.reportFormat()
            r6.reportFormat = r7
            boolean r7 = r0.parallel()
            r6.parallel = r7
            goto L_0x0147
        L_0x00e6:
            java.lang.String r7 = ""
            r6.sharedPreferencesName = r7
            r6.includeDropBoxSystemTags = r2
            java.lang.String[] r0 = new java.lang.String[r2]
            r6.additionalDropBoxTags = r0
            r0 = 5
            r6.dropboxCollectionMinutes = r0
            java.lang.String r0 = "-t"
            java.lang.String r3 = "100"
            java.lang.String r4 = "-v"
            java.lang.String r5 = "time"
            java.lang.String[] r0 = new java.lang.String[]{r0, r3, r4, r5}
            r6.logcatArguments = r0
            org.acra.ReportField[] r0 = new org.acra.ReportField[r2]
            r6.reportContent = r0
            r6.deleteUnapprovedReportsOnApplicationStart = r1
            r6.deleteOldUnsentReportsOnApplicationStart = r1
            r6.alsoReportToAndroidFramework = r2
            java.lang.String[] r0 = new java.lang.String[r2]
            r6.additionalSharedPreferences = r0
            r6.logcatFilterByPid = r1
            r6.logcatReadNonBlocking = r2
            r6.sendReportsInDevMode = r1
            java.lang.String[] r0 = new java.lang.String[r2]
            r6.excludeMatchingSharedPreferencesKeys = r0
            java.lang.String[] r0 = new java.lang.String[r2]
            r6.excludeMatchingSettingsKeys = r0
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            r6.buildConfigClass = r0
            java.lang.Class[] r0 = new java.lang.Class[r1]
            java.lang.Class<org.acra.sender.DefaultReportSenderFactory> r3 = org.acra.sender.DefaultReportSenderFactory.class
            r0[r2] = r3
            r6.reportSenderFactoryClasses = r0
            r6.applicationLogFile = r7
            r7 = 100
            r6.applicationLogFileLines = r7
            org.acra.file.Directory r7 = org.acra.file.Directory.FILES_LEGACY
            r6.applicationLogFileDir = r7
            java.lang.Class<org.acra.config.DefaultRetryPolicy> r7 = org.acra.config.DefaultRetryPolicy.class
            r6.retryPolicyClass = r7
            r6.stopServicesOnCrash = r2
            java.lang.String[] r7 = new java.lang.String[r2]
            r6.attachmentUris = r7
            java.lang.Class<org.acra.attachment.DefaultAttachmentProvider> r7 = org.acra.attachment.DefaultAttachmentProvider.class
            r6.attachmentUriProvider = r7
            org.acra.data.StringFormat r7 = org.acra.data.StringFormat.JSON
            r6.reportFormat = r7
            r6.parallel = r1
        L_0x0147:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.config.CoreConfigurationBuilder.<init>(android.content.Context):void");
    }

    public CoreConfigurationBuilder setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean enabled() {
        return this.enabled;
    }

    public CoreConfigurationBuilder setSharedPreferencesName(String str) {
        this.sharedPreferencesName = str;
        return this;
    }

    /* access modifiers changed from: package-private */
    public String sharedPreferencesName() {
        return this.sharedPreferencesName;
    }

    public CoreConfigurationBuilder setIncludeDropBoxSystemTags(boolean z) {
        this.includeDropBoxSystemTags = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean includeDropBoxSystemTags() {
        return this.includeDropBoxSystemTags;
    }

    public CoreConfigurationBuilder setAdditionalDropBoxTags(String... strArr) {
        this.additionalDropBoxTags = strArr;
        return this;
    }

    /* access modifiers changed from: package-private */
    public String[] additionalDropBoxTags() {
        return this.additionalDropBoxTags;
    }

    public CoreConfigurationBuilder setDropboxCollectionMinutes(int i) {
        this.dropboxCollectionMinutes = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public int dropboxCollectionMinutes() {
        return this.dropboxCollectionMinutes;
    }

    public CoreConfigurationBuilder setLogcatArguments(String... strArr) {
        this.logcatArguments = strArr;
        return this;
    }

    /* access modifiers changed from: package-private */
    public String[] logcatArguments() {
        return this.logcatArguments;
    }

    public CoreConfigurationBuilder setReportContent(ReportField... reportFieldArr) {
        this.reportContent = reportFieldArr;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Set<ReportField> reportContent() {
        return this.delegate.transformReportContent(this.reportContent);
    }

    public CoreConfigurationBuilder setDeleteUnapprovedReportsOnApplicationStart(boolean z) {
        this.deleteUnapprovedReportsOnApplicationStart = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean deleteUnapprovedReportsOnApplicationStart() {
        return this.deleteUnapprovedReportsOnApplicationStart;
    }

    public CoreConfigurationBuilder setDeleteOldUnsentReportsOnApplicationStart(boolean z) {
        this.deleteOldUnsentReportsOnApplicationStart = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean deleteOldUnsentReportsOnApplicationStart() {
        return this.deleteOldUnsentReportsOnApplicationStart;
    }

    public CoreConfigurationBuilder setAlsoReportToAndroidFramework(boolean z) {
        this.alsoReportToAndroidFramework = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean alsoReportToAndroidFramework() {
        return this.alsoReportToAndroidFramework;
    }

    public CoreConfigurationBuilder setAdditionalSharedPreferences(String... strArr) {
        this.additionalSharedPreferences = strArr;
        return this;
    }

    /* access modifiers changed from: package-private */
    public String[] additionalSharedPreferences() {
        return this.additionalSharedPreferences;
    }

    public CoreConfigurationBuilder setLogcatFilterByPid(boolean z) {
        this.logcatFilterByPid = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean logcatFilterByPid() {
        return this.logcatFilterByPid;
    }

    public CoreConfigurationBuilder setLogcatReadNonBlocking(boolean z) {
        this.logcatReadNonBlocking = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean logcatReadNonBlocking() {
        return this.logcatReadNonBlocking;
    }

    public CoreConfigurationBuilder setSendReportsInDevMode(boolean z) {
        this.sendReportsInDevMode = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean sendReportsInDevMode() {
        return this.sendReportsInDevMode;
    }

    public CoreConfigurationBuilder setExcludeMatchingSharedPreferencesKeys(String... strArr) {
        this.excludeMatchingSharedPreferencesKeys = strArr;
        return this;
    }

    /* access modifiers changed from: package-private */
    public String[] excludeMatchingSharedPreferencesKeys() {
        return this.excludeMatchingSharedPreferencesKeys;
    }

    public CoreConfigurationBuilder setExcludeMatchingSettingsKeys(String... strArr) {
        this.excludeMatchingSettingsKeys = strArr;
        return this;
    }

    /* access modifiers changed from: package-private */
    public String[] excludeMatchingSettingsKeys() {
        return this.excludeMatchingSettingsKeys;
    }

    public CoreConfigurationBuilder setBuildConfigClass(Class cls) {
        this.buildConfigClass = cls;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Class buildConfigClass() {
        return this.buildConfigClass;
    }

    public CoreConfigurationBuilder setReportSenderFactoryClasses(Class<? extends ReportSenderFactory>... clsArr) {
        this.reportSenderFactoryClasses = clsArr;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Class<? extends ReportSenderFactory>[] reportSenderFactoryClasses() {
        return this.reportSenderFactoryClasses;
    }

    public CoreConfigurationBuilder setApplicationLogFile(String str) {
        this.applicationLogFile = str;
        return this;
    }

    /* access modifiers changed from: package-private */
    public String applicationLogFile() {
        return this.applicationLogFile;
    }

    public CoreConfigurationBuilder setApplicationLogFileLines(int i) {
        this.applicationLogFileLines = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public int applicationLogFileLines() {
        return this.applicationLogFileLines;
    }

    public CoreConfigurationBuilder setApplicationLogFileDir(Directory directory) {
        this.applicationLogFileDir = directory;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Directory applicationLogFileDir() {
        return this.applicationLogFileDir;
    }

    public CoreConfigurationBuilder setRetryPolicyClass(Class<? extends RetryPolicy> cls) {
        this.retryPolicyClass = cls;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Class<? extends RetryPolicy> retryPolicyClass() {
        return this.retryPolicyClass;
    }

    public CoreConfigurationBuilder setStopServicesOnCrash(boolean z) {
        this.stopServicesOnCrash = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean stopServicesOnCrash() {
        return this.stopServicesOnCrash;
    }

    public CoreConfigurationBuilder setAttachmentUris(String... strArr) {
        this.attachmentUris = strArr;
        return this;
    }

    /* access modifiers changed from: package-private */
    public String[] attachmentUris() {
        return this.attachmentUris;
    }

    public CoreConfigurationBuilder setAttachmentUriProvider(Class<? extends AttachmentUriProvider> cls) {
        this.attachmentUriProvider = cls;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Class<? extends AttachmentUriProvider> attachmentUriProvider() {
        return this.attachmentUriProvider;
    }

    public CoreConfigurationBuilder setReportSendSuccessToast(String str) {
        this.reportSendSuccessToast = str;
        return this;
    }

    public CoreConfigurationBuilder setResReportSendSuccessToast(int i) {
        this.reportSendSuccessToast = this.context.getString(i);
        return this;
    }

    /* access modifiers changed from: package-private */
    public String reportSendSuccessToast() {
        return this.reportSendSuccessToast;
    }

    public CoreConfigurationBuilder setReportSendFailureToast(String str) {
        this.reportSendFailureToast = str;
        return this;
    }

    public CoreConfigurationBuilder setResReportSendFailureToast(int i) {
        this.reportSendFailureToast = this.context.getString(i);
        return this;
    }

    /* access modifiers changed from: package-private */
    public String reportSendFailureToast() {
        return this.reportSendFailureToast;
    }

    public CoreConfigurationBuilder setReportFormat(StringFormat stringFormat) {
        this.reportFormat = stringFormat;
        return this;
    }

    /* access modifiers changed from: package-private */
    public StringFormat reportFormat() {
        return this.reportFormat;
    }

    public CoreConfigurationBuilder setParallel(boolean z) {
        this.parallel = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean parallel() {
        return this.parallel;
    }

    public CoreConfigurationBuilder setReportField(ReportField reportField, boolean z) {
        this.delegate.setReportField(reportField, z);
        return this;
    }

    /* access modifiers changed from: package-private */
    public List<Configuration> pluginConfigurations() {
        return this.delegate.pluginConfigurations();
    }

    public <R extends ConfigurationBuilder> R getPluginConfigurationBuilder(Class<R> cls) {
        return this.delegate.getPluginConfigurationBuilder(cls);
    }

    public CoreConfiguration build() throws ACRAConfigurationException {
        if (this.enabled) {
            Class<? extends ReportSenderFactory>[] clsArr = this.reportSenderFactoryClasses;
            if (clsArr.length != 0) {
                ClassValidator.check(clsArr);
                ClassValidator.check(this.retryPolicyClass);
                ClassValidator.check(this.attachmentUriProvider);
            } else {
                throw new ACRAConfigurationException("reportSenderFactoryClasses cannot be empty");
            }
        }
        this.delegate.preBuild();
        return new CoreConfiguration(this);
    }
}
