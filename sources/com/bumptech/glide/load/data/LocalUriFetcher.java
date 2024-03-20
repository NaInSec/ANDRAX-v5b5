package com.bumptech.glide.load.data;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.bumptech.glide.Priority;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class LocalUriFetcher<T> implements DataFetcher<T> {
    private static final String TAG = "LocalUriFetcher";
    private final Context context;
    private T data;
    private final Uri uri;

    public void cancel() {
    }

    /* access modifiers changed from: protected */
    public abstract void close(T t) throws IOException;

    /* access modifiers changed from: protected */
    public abstract T loadResource(Uri uri2, ContentResolver contentResolver) throws FileNotFoundException;

    public LocalUriFetcher(Context context2, Uri uri2) {
        this.context = context2.getApplicationContext();
        this.uri = uri2;
    }

    public final T loadData(Priority priority) throws Exception {
        this.data = loadResource(this.uri, this.context.getContentResolver());
        return this.data;
    }

    public void cleanup() {
        T t = this.data;
        if (t != null) {
            try {
                close(t);
            } catch (IOException e) {
                if (Log.isLoggable(TAG, 2)) {
                    Log.v(TAG, "failed to close data", e);
                }
            }
        }
    }

    public String getId() {
        return this.uri.toString();
    }
}
