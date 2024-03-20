package com.bumptech.glide;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.transcode.GifDrawableBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.InputStream;

public class GifTypeRequest<ModelType> extends GifRequestBuilder<ModelType> {
    private final RequestManager.OptionsApplier optionsApplier;
    private final ModelLoader<ModelType, InputStream> streamModelLoader;

    /* JADX WARNING: type inference failed for: r3v0, types: [java.lang.Class, java.lang.Class<R>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <A, R> com.bumptech.glide.provider.FixedLoadProvider<A, java.io.InputStream, com.bumptech.glide.load.resource.gif.GifDrawable, R> buildProvider(com.bumptech.glide.Glide r1, com.bumptech.glide.load.model.ModelLoader<A, java.io.InputStream> r2, java.lang.Class<R> r3, com.bumptech.glide.load.resource.transcode.ResourceTranscoder<com.bumptech.glide.load.resource.gif.GifDrawable, R> r4) {
        /*
            if (r2 != 0) goto L_0x0004
            r1 = 0
            return r1
        L_0x0004:
            if (r4 != 0) goto L_0x000c
            java.lang.Class<com.bumptech.glide.load.resource.gif.GifDrawable> r4 = com.bumptech.glide.load.resource.gif.GifDrawable.class
            com.bumptech.glide.load.resource.transcode.ResourceTranscoder r4 = r1.buildTranscoder(r4, r3)
        L_0x000c:
            java.lang.Class<java.io.InputStream> r3 = java.io.InputStream.class
            java.lang.Class<com.bumptech.glide.load.resource.gif.GifDrawable> r0 = com.bumptech.glide.load.resource.gif.GifDrawable.class
            com.bumptech.glide.provider.DataLoadProvider r1 = r1.buildDataProvider(r3, r0)
            com.bumptech.glide.provider.FixedLoadProvider r3 = new com.bumptech.glide.provider.FixedLoadProvider
            r3.<init>(r2, r4, r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.GifTypeRequest.buildProvider(com.bumptech.glide.Glide, com.bumptech.glide.load.model.ModelLoader, java.lang.Class, com.bumptech.glide.load.resource.transcode.ResourceTranscoder):com.bumptech.glide.provider.FixedLoadProvider");
    }

    GifTypeRequest(GenericRequestBuilder<ModelType, ?, ?, ?> genericRequestBuilder, ModelLoader<ModelType, InputStream> modelLoader, RequestManager.OptionsApplier optionsApplier2) {
        super(buildProvider(genericRequestBuilder.glide, modelLoader, GifDrawable.class, (ResourceTranscoder) null), GifDrawable.class, genericRequestBuilder);
        this.streamModelLoader = modelLoader;
        this.optionsApplier = optionsApplier2;
        crossFade();
    }

    public <R> GenericRequestBuilder<ModelType, InputStream, GifDrawable, R> transcode(ResourceTranscoder<GifDrawable, R> resourceTranscoder, Class<R> cls) {
        return this.optionsApplier.apply(new GenericRequestBuilder(buildProvider(this.glide, this.streamModelLoader, cls, resourceTranscoder), cls, this));
    }

    public GenericRequestBuilder<ModelType, InputStream, GifDrawable, byte[]> toBytes() {
        return transcode(new GifDrawableBytesTranscoder(), byte[].class);
    }
}
