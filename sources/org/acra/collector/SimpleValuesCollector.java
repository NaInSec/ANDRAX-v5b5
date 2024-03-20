package org.acra.collector;

import android.content.Context;
import android.os.Build;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.UUID;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.util.Installation;

public final class SimpleValuesCollector extends BaseReportFieldCollector {
    public SimpleValuesCollector() {
        super(ReportField.IS_SILENT, ReportField.REPORT_ID, ReportField.INSTALLATION_ID, ReportField.PACKAGE_NAME, ReportField.PHONE_MODEL, ReportField.ANDROID_VERSION, ReportField.BRAND, ReportField.PRODUCT, ReportField.FILE_PATH, ReportField.USER_IP);
    }

    /* access modifiers changed from: package-private */
    public void collect(ReportField reportField, Context context, CoreConfiguration coreConfiguration, ReportBuilder reportBuilder, CrashReportData crashReportData) throws Exception {
        switch (reportField) {
            case IS_SILENT:
                crashReportData.put(ReportField.IS_SILENT, reportBuilder.isSendSilently());
                return;
            case REPORT_ID:
                crashReportData.put(ReportField.REPORT_ID, UUID.randomUUID().toString());
                return;
            case INSTALLATION_ID:
                crashReportData.put(ReportField.INSTALLATION_ID, Installation.id(context));
                return;
            case PACKAGE_NAME:
                crashReportData.put(ReportField.PACKAGE_NAME, context.getPackageName());
                return;
            case PHONE_MODEL:
                crashReportData.put(ReportField.PHONE_MODEL, Build.MODEL);
                return;
            case ANDROID_VERSION:
                crashReportData.put(ReportField.ANDROID_VERSION, Build.VERSION.RELEASE);
                return;
            case BRAND:
                crashReportData.put(ReportField.BRAND, Build.BRAND);
                return;
            case PRODUCT:
                crashReportData.put(ReportField.PRODUCT, Build.PRODUCT);
                return;
            case FILE_PATH:
                crashReportData.put(ReportField.FILE_PATH, getApplicationFilePath(context));
                return;
            case USER_IP:
                crashReportData.put(ReportField.USER_IP, getLocalIpAddress());
                return;
            default:
                throw new IllegalArgumentException();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldCollect(Context context, CoreConfiguration coreConfiguration, ReportField reportField, ReportBuilder reportBuilder) {
        return reportField == ReportField.IS_SILENT || reportField == ReportField.REPORT_ID || super.shouldCollect(context, coreConfiguration, reportField, reportBuilder);
    }

    private String getApplicationFilePath(Context context) {
        return context.getFilesDir().getAbsolutePath();
    }

    private static String getLocalIpAddress() throws SocketException {
        StringBuilder sb = new StringBuilder();
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        boolean z = true;
        while (networkInterfaces.hasMoreElements()) {
            Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress nextElement = inetAddresses.nextElement();
                if (!nextElement.isLoopbackAddress()) {
                    if (!z) {
                        sb.append(10);
                    }
                    sb.append(nextElement.getHostAddress());
                    z = false;
                }
            }
        }
        return sb.toString();
    }
}
