package org.acra.file;

import android.content.Context;
import java.io.File;
import java.util.Arrays;

public final class ReportLocator {
    private static final String APPROVED_FOLDER_NAME = "ACRA-approved";
    private static final String UNAPPROVED_FOLDER_NAME = "ACRA-unapproved";
    private final Context context;

    public ReportLocator(Context context2) {
        this.context = context2;
    }

    public File getUnapprovedFolder() {
        return this.context.getDir(UNAPPROVED_FOLDER_NAME, 0);
    }

    public File[] getUnapprovedReports() {
        File[] listFiles = getUnapprovedFolder().listFiles();
        return listFiles == null ? new File[0] : listFiles;
    }

    public File getApprovedFolder() {
        return this.context.getDir(APPROVED_FOLDER_NAME, 0);
    }

    public File[] getApprovedReports() {
        File[] listFiles = getApprovedFolder().listFiles();
        if (listFiles == null) {
            return new File[0];
        }
        Arrays.sort(listFiles, new LastModifiedComparator());
        return listFiles;
    }
}
