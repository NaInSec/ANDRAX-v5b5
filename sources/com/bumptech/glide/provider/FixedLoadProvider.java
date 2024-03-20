package com.bumptech.glide.provider;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.File;

public class FixedLoadProvider<A, T, Z, R> implements LoadProvider<A, T, Z, R> {
    private final DataLoadProvider<T, Z> dataLoadProvider;
    private final ModelLoader<A, T> modelLoader;
    private final ResourceTranscoder<Z, R> transcoder;

    public FixedLoadProvider(ModelLoader<A, T> modelLoader2, ResourceTranscoder<Z, R> resourceTranscoder, DataLoadProvider<T, Z> dataLoadProvider2) {
        if (modelLoader2 != null) {
            this.modelLoader = modelLoader2;
            if (resourceTranscoder != null) {
                this.transcoder = resourceTranscoder;
                if (dataLoadProvider2 != null) {
                    this.dataLoadProvider = dataLoadProvider2;
                    return;
                }
                throw new NullPointerException("DataLoadProvider must not be null");
            }
            throw new NullPointerException("Transcoder must not be null");
        }
        throw new NullPointerException("ModelLoader must not be null");
    }

    public ModelLoader<A, T> getModelLoader() {
        return this.modelLoader;
    }

    public ResourceTranscoder<Z, R> getTranscoder() {
        return this.transcoder;
    }

    public ResourceDecoder<File, Z> getCacheDecoder() {
        return this.dataLoadProvider.getCacheDecoder();
    }

    public ResourceDecoder<T, Z> getSourceDecoder() {
        return this.dataLoadProvider.getSourceDecoder();
    }

    public Encoder<T> getSourceEncoder() {
        return this.dataLoadProvider.getSourceEncoder();
    }

    public ResourceEncoder<Z> getEncoder() {
        return this.dataLoadProvider.getEncoder();
    }
}
