package com.bumptech.glide.load.model;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.data.DataFetcher;

public abstract class UriLoader<T> implements ModelLoader<Uri, T> {
    private final Context context;
    private final ModelLoader<GlideUrl, T> urlLoader;

    /* access modifiers changed from: protected */
    public abstract DataFetcher<T> getAssetPathFetcher(Context context2, String str);

    /* access modifiers changed from: protected */
    public abstract DataFetcher<T> getLocalUriFetcher(Context context2, Uri uri);

    public UriLoader(Context context2, ModelLoader<GlideUrl, T> modelLoader) {
        this.context = context2;
        this.urlLoader = modelLoader;
    }

    public final DataFetcher<T> getResourceFetcher(Uri uri, int i, int i2) {
        String scheme = uri.getScheme();
        if (isLocalUri(scheme)) {
            if (!AssetUriParser.isAssetUri(uri)) {
                return getLocalUriFetcher(this.context, uri);
            }
            return getAssetPathFetcher(this.context, AssetUriParser.toAssetPath(uri));
        } else if (this.urlLoader == null || (!"http".equals(scheme) && !"https".equals(scheme))) {
            return null;
        } else {
            return this.urlLoader.getResourceFetcher(new GlideUrl(uri.toString()), i, i2);
        }
    }

    private static boolean isLocalUri(String str) {
        return "file".equals(str) || "content".equals(str) || "android.resource".equals(str);
    }
}
