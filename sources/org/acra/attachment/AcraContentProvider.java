package org.acra.attachment;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;
import org.acra.ACRA;
import org.acra.file.Directory;
import org.acra.log.ACRALog;

public class AcraContentProvider extends ContentProvider {
    private static final String[] COLUMNS = {"_display_name", "_size"};
    private static final String MIME_TYPE_OCTET_STREAM = "application/octet-stream";
    private String authority;

    public boolean onCreate() {
        this.authority = getAuthority(getContext());
        if (!ACRA.DEV_LOGGING) {
            return true;
        }
        ACRALog aCRALog = ACRA.log;
        String str = ACRA.LOG_TAG;
        aCRALog.d(str, "Registered content provider for authority " + this.authority);
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (ACRA.DEV_LOGGING) {
            ACRA.log.d(ACRA.LOG_TAG, "Query: " + uri);
        }
        File fileForUri = getFileForUri(uri);
        if (fileForUri == null) {
            return null;
        }
        if (strArr == null) {
            strArr = COLUMNS;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str3 : strArr) {
            if (str3.equals("_display_name")) {
                linkedHashMap.put("_display_name", fileForUri.getName());
            } else if (str3.equals("_size")) {
                linkedHashMap.put("_size", Long.valueOf(fileForUri.length()));
            }
        }
        MatrixCursor matrixCursor = new MatrixCursor((String[]) linkedHashMap.keySet().toArray(new String[linkedHashMap.size()]), 1);
        matrixCursor.addRow(linkedHashMap.values());
        return matrixCursor;
    }

    private File getFileForUri(Uri uri) {
        if ("content".equals(uri.getScheme()) && this.authority.equals(uri.getAuthority())) {
            ArrayList arrayList = new ArrayList(uri.getPathSegments());
            if (arrayList.size() < 2) {
                return null;
            }
            try {
                return Directory.valueOf(((String) arrayList.remove(0)).toUpperCase()).getFile(getContext(), TextUtils.join(File.separator, arrayList));
            } catch (IllegalArgumentException unused) {
            }
        }
        return null;
    }

    public String getType(Uri uri) {
        return guessMimeType(uri);
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No insert supported");
    }

    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("No delete supported");
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No update supported");
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        File fileForUri = getFileForUri(uri);
        if (fileForUri == null || !fileForUri.exists()) {
            throw new FileNotFoundException("File represented by uri " + uri + " could not be found");
        }
        if (ACRA.DEV_LOGGING) {
            if (Build.VERSION.SDK_INT >= 19) {
                ACRALog aCRALog = ACRA.log;
                String str2 = ACRA.LOG_TAG;
                aCRALog.d(str2, getCallingPackage() + " opened " + fileForUri.getPath());
            } else {
                ACRALog aCRALog2 = ACRA.log;
                String str3 = ACRA.LOG_TAG;
                aCRALog2.d(str3, fileForUri.getPath() + " was opened by an application");
            }
        }
        return ParcelFileDescriptor.open(fileForUri, 268435456);
    }

    private static String getAuthority(Context context) {
        return context.getPackageName() + ".acra";
    }

    public static Uri getUriForFile(Context context, File file) {
        return getUriForFile(context, Directory.ROOT, file.getPath());
    }

    public static Uri getUriForFile(Context context, Directory directory, String str) {
        Uri.Builder appendPath = new Uri.Builder().scheme("content").authority(getAuthority(context)).appendPath(directory.name().toLowerCase());
        for (String str2 : str.split(Pattern.quote(File.separator))) {
            if (str2.length() > 0) {
                appendPath.appendPath(str2);
            }
        }
        return appendPath.build();
    }

    public static String guessMimeType(Uri uri) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
        String mimeTypeFromExtension = fileExtensionFromUrl != null ? MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl.toLowerCase()) : null;
        return mimeTypeFromExtension == null ? MIME_TYPE_OCTET_STREAM : mimeTypeFromExtension;
    }
}
