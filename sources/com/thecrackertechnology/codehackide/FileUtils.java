package com.thecrackertechnology.codehackide;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import java.net.URISyntaxException;

public class FileUtils {
    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            try {
                Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, (String) null, (String[]) null, (String) null);
                int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
                if (query.moveToFirst()) {
                    return query.getString(columnIndexOrThrow);
                }
                return null;
            } catch (Exception unused) {
                return null;
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        } else {
            return null;
        }
    }
}
