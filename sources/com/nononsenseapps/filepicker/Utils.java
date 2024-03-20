package com.nononsenseapps.filepicker;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static final String SEP = "/";

    public static boolean isValidFileName(String str) {
        return !TextUtils.isEmpty(str) && !str.contains(SEP) && !str.equals(".") && !str.equals("..");
    }

    public static String appendPath(String str, String str2) {
        String str3 = str + SEP + str2;
        while (str3.contains("//")) {
            str3 = str3.replaceAll("//", SEP);
        }
        return (str3.length() <= 1 || !str3.endsWith(SEP)) ? str3 : str3.substring(0, str3.length() - 1);
    }

    public static File getFileForUri(Uri uri) {
        String encodedPath = uri.getEncodedPath();
        int indexOf = encodedPath.indexOf(47, 1);
        String decode = Uri.decode(encodedPath.substring(1, indexOf));
        String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
        if ("root".equalsIgnoreCase(decode)) {
            File file = new File(SEP);
            File file2 = new File(file, decode2);
            try {
                File canonicalFile = file2.getCanonicalFile();
                if (canonicalFile.getPath().startsWith(file.getPath())) {
                    return canonicalFile;
                }
                throw new SecurityException("Resolved path jumped beyond configured root");
            } catch (IOException unused) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file2);
            }
        } else {
            throw new IllegalArgumentException(String.format("Can't decode paths to '%s', only for 'root' paths.", new Object[]{decode}));
        }
    }

    public static List<Uri> getSelectedFilesFromResult(Intent intent) {
        ArrayList arrayList = new ArrayList();
        if (intent.getBooleanExtra(AbstractFilePickerActivity.EXTRA_ALLOW_MULTIPLE, false)) {
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra(AbstractFilePickerActivity.EXTRA_PATHS);
            if (stringArrayListExtra != null) {
                for (String parse : stringArrayListExtra) {
                    arrayList.add(Uri.parse(parse));
                }
            }
        } else {
            arrayList.add(intent.getData());
        }
        return arrayList;
    }
}
