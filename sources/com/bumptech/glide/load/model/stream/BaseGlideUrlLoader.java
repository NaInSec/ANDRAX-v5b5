package com.bumptech.glide.load.model.stream;

import android.content.Context;
import android.text.TextUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.InputStream;

public abstract class BaseGlideUrlLoader<T> implements StreamModelLoader<T> {
    private final ModelLoader<GlideUrl, InputStream> concreteLoader;
    private final ModelCache<T, GlideUrl> modelCache;

    /* access modifiers changed from: protected */
    public abstract String getUrl(T t, int i, int i2);

    public BaseGlideUrlLoader(Context context) {
        this(context, (ModelCache) null);
    }

    public BaseGlideUrlLoader(Context context, ModelCache<T, GlideUrl> modelCache2) {
        this((ModelLoader<GlideUrl, InputStream>) Glide.buildModelLoader(GlideUrl.class, InputStream.class, context), modelCache2);
    }

    public BaseGlideUrlLoader(ModelLoader<GlideUrl, InputStream> modelLoader) {
        this(modelLoader, (ModelCache) null);
    }

    public BaseGlideUrlLoader(ModelLoader<GlideUrl, InputStream> modelLoader, ModelCache<T, GlideUrl> modelCache2) {
        this.concreteLoader = modelLoader;
        this.modelCache = modelCache2;
    }

    public DataFetcher<InputStream> getResourceFetcher(T t, int i, int i2) {
        ModelCache<T, GlideUrl> modelCache2 = this.modelCache;
        GlideUrl glideUrl = modelCache2 != null ? modelCache2.get(t, i, i2) : null;
        if (glideUrl == null) {
            String url = getUrl(t, i, i2);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            GlideUrl glideUrl2 = new GlideUrl(url, getHeaders(t, i, i2));
            ModelCache<T, GlideUrl> modelCache3 = this.modelCache;
            if (modelCache3 != null) {
                modelCache3.put(t, i, i2, glideUrl2);
            }
            glideUrl = glideUrl2;
        }
        return this.concreteLoader.getResourceFetcher(glideUrl, i, i2);
    }

    /* access modifiers changed from: protected */
    public Headers getHeaders(T t, int i, int i2) {
        return Headers.DEFAULT;
    }
}
