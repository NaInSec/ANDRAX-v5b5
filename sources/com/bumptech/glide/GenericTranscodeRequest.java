package com.bumptech.glide;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.load.resource.transcode.UnitTranscoder;
import com.bumptech.glide.provider.FixedLoadProvider;
import com.bumptech.glide.provider.LoadProvider;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;
import java.io.File;

public class GenericTranscodeRequest<ModelType, DataType, ResourceType> extends GenericRequestBuilder<ModelType, DataType, ResourceType, ResourceType> implements DownloadOptions {
    private final Class<DataType> dataClass;
    private final ModelLoader<ModelType, DataType> modelLoader;
    private final RequestManager.OptionsApplier optionsApplier;
    private final Class<ResourceType> resourceClass;

    private static <A, T, Z, R> LoadProvider<A, T, Z, R> build(Glide glide, ModelLoader<A, T> modelLoader2, Class<T> cls, Class<Z> cls2, ResourceTranscoder<Z, R> resourceTranscoder) {
        return new FixedLoadProvider(modelLoader2, resourceTranscoder, glide.buildDataProvider(cls, cls2));
    }

    GenericTranscodeRequest(Class<ResourceType> cls, GenericRequestBuilder<ModelType, ?, ?, ?> genericRequestBuilder, ModelLoader<ModelType, DataType> modelLoader2, Class<DataType> cls2, Class<ResourceType> cls3, RequestManager.OptionsApplier optionsApplier2) {
        super(build(genericRequestBuilder.glide, modelLoader2, cls2, cls3, UnitTranscoder.get()), cls, genericRequestBuilder);
        this.modelLoader = modelLoader2;
        this.dataClass = cls2;
        this.resourceClass = cls3;
        this.optionsApplier = optionsApplier2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    GenericTranscodeRequest(android.content.Context r13, com.bumptech.glide.Glide r14, java.lang.Class<ModelType> r15, com.bumptech.glide.load.model.ModelLoader<ModelType, DataType> r16, java.lang.Class<DataType> r17, java.lang.Class<ResourceType> r18, com.bumptech.glide.manager.RequestTracker r19, com.bumptech.glide.manager.Lifecycle r20, com.bumptech.glide.RequestManager.OptionsApplier r21) {
        /*
            r12 = this;
            r8 = r12
            r9 = r16
            r10 = r17
            r11 = r18
            com.bumptech.glide.load.resource.transcode.ResourceTranscoder r0 = com.bumptech.glide.load.resource.transcode.UnitTranscoder.get()
            r5 = r14
            com.bumptech.glide.provider.LoadProvider r3 = build(r14, r9, r10, r11, r0)
            r0 = r12
            r1 = r13
            r2 = r15
            r4 = r18
            r6 = r19
            r7 = r20
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            r8.modelLoader = r9
            r8.dataClass = r10
            r8.resourceClass = r11
            r0 = r21
            r8.optionsApplier = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.GenericTranscodeRequest.<init>(android.content.Context, com.bumptech.glide.Glide, java.lang.Class, com.bumptech.glide.load.model.ModelLoader, java.lang.Class, java.lang.Class, com.bumptech.glide.manager.RequestTracker, com.bumptech.glide.manager.Lifecycle, com.bumptech.glide.RequestManager$OptionsApplier):void");
    }

    public <TranscodeType> GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> transcode(ResourceTranscoder<ResourceType, TranscodeType> resourceTranscoder, Class<TranscodeType> cls) {
        return this.optionsApplier.apply(new GenericRequestBuilder(build(this.glide, this.modelLoader, this.dataClass, this.resourceClass, resourceTranscoder), cls, this));
    }

    public <Y extends Target<File>> Y downloadOnly(Y y) {
        return getDownloadOnlyRequest().into(y);
    }

    public FutureTarget<File> downloadOnly(int i, int i2) {
        return getDownloadOnlyRequest().into(i, i2);
    }

    private GenericRequestBuilder<ModelType, DataType, File, File> getDownloadOnlyRequest() {
        return this.optionsApplier.apply(new GenericRequestBuilder(new FixedLoadProvider(this.modelLoader, UnitTranscoder.get(), this.glide.buildDataProvider(this.dataClass, File.class)), File.class, this)).priority(Priority.LOW).diskCacheStrategy(DiskCacheStrategy.SOURCE).skipMemoryCache(true);
    }
}
