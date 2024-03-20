package com.bumptech.glide;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.transcode.BitmapBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.InputStream;

public class BitmapTypeRequest<ModelType> extends BitmapRequestBuilder<ModelType, Bitmap> {
    private final ModelLoader<ModelType, ParcelFileDescriptor> fileDescriptorModelLoader;
    private final Glide glide;
    private final RequestManager.OptionsApplier optionsApplier;
    private final ModelLoader<ModelType, InputStream> streamModelLoader;

    /* JADX WARNING: type inference failed for: r4v0, types: [java.lang.Class, java.lang.Class<R>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <A, R> com.bumptech.glide.provider.FixedLoadProvider<A, com.bumptech.glide.load.model.ImageVideoWrapper, android.graphics.Bitmap, R> buildProvider(com.bumptech.glide.Glide r1, com.bumptech.glide.load.model.ModelLoader<A, java.io.InputStream> r2, com.bumptech.glide.load.model.ModelLoader<A, android.os.ParcelFileDescriptor> r3, java.lang.Class<R> r4, com.bumptech.glide.load.resource.transcode.ResourceTranscoder<android.graphics.Bitmap, R> r5) {
        /*
            if (r2 != 0) goto L_0x0006
            if (r3 != 0) goto L_0x0006
            r1 = 0
            return r1
        L_0x0006:
            if (r5 != 0) goto L_0x000e
            java.lang.Class<android.graphics.Bitmap> r5 = android.graphics.Bitmap.class
            com.bumptech.glide.load.resource.transcode.ResourceTranscoder r5 = r1.buildTranscoder(r5, r4)
        L_0x000e:
            java.lang.Class<com.bumptech.glide.load.model.ImageVideoWrapper> r4 = com.bumptech.glide.load.model.ImageVideoWrapper.class
            java.lang.Class<android.graphics.Bitmap> r0 = android.graphics.Bitmap.class
            com.bumptech.glide.provider.DataLoadProvider r1 = r1.buildDataProvider(r4, r0)
            com.bumptech.glide.load.model.ImageVideoModelLoader r4 = new com.bumptech.glide.load.model.ImageVideoModelLoader
            r4.<init>(r2, r3)
            com.bumptech.glide.provider.FixedLoadProvider r2 = new com.bumptech.glide.provider.FixedLoadProvider
            r2.<init>(r4, r5, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.BitmapTypeRequest.buildProvider(com.bumptech.glide.Glide, com.bumptech.glide.load.model.ModelLoader, com.bumptech.glide.load.model.ModelLoader, java.lang.Class, com.bumptech.glide.load.resource.transcode.ResourceTranscoder):com.bumptech.glide.provider.FixedLoadProvider");
    }

    BitmapTypeRequest(GenericRequestBuilder<ModelType, ?, ?, ?> genericRequestBuilder, ModelLoader<ModelType, InputStream> modelLoader, ModelLoader<ModelType, ParcelFileDescriptor> modelLoader2, RequestManager.OptionsApplier optionsApplier2) {
        super(buildProvider(genericRequestBuilder.glide, modelLoader, modelLoader2, Bitmap.class, (ResourceTranscoder) null), Bitmap.class, genericRequestBuilder);
        this.streamModelLoader = modelLoader;
        this.fileDescriptorModelLoader = modelLoader2;
        this.glide = genericRequestBuilder.glide;
        this.optionsApplier = optionsApplier2;
    }

    public <R> BitmapRequestBuilder<ModelType, R> transcode(ResourceTranscoder<Bitmap, R> resourceTranscoder, Class<R> cls) {
        return (BitmapRequestBuilder) this.optionsApplier.apply(new BitmapRequestBuilder(buildProvider(this.glide, this.streamModelLoader, this.fileDescriptorModelLoader, cls, resourceTranscoder), cls, this));
    }

    public BitmapRequestBuilder<ModelType, byte[]> toBytes() {
        return transcode(new BitmapBytesTranscoder(), byte[].class);
    }

    public BitmapRequestBuilder<ModelType, byte[]> toBytes(Bitmap.CompressFormat compressFormat, int i) {
        return transcode(new BitmapBytesTranscoder(compressFormat, i), byte[].class);
    }
}
