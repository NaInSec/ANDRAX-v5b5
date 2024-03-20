package com.bumptech.glide.provider;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.File;

public class ChildLoadProvider<A, T, Z, R> implements LoadProvider<A, T, Z, R>, Cloneable {
    private ResourceDecoder<File, Z> cacheDecoder;
    private ResourceEncoder<Z> encoder;
    private final LoadProvider<A, T, Z, R> parent;
    private ResourceDecoder<T, Z> sourceDecoder;
    private Encoder<T> sourceEncoder;
    private ResourceTranscoder<Z, R> transcoder;

    public ChildLoadProvider(LoadProvider<A, T, Z, R> loadProvider) {
        this.parent = loadProvider;
    }

    public ModelLoader<A, T> getModelLoader() {
        return this.parent.getModelLoader();
    }

    public void setCacheDecoder(ResourceDecoder<File, Z> resourceDecoder) {
        this.cacheDecoder = resourceDecoder;
    }

    public void setSourceDecoder(ResourceDecoder<T, Z> resourceDecoder) {
        this.sourceDecoder = resourceDecoder;
    }

    public void setEncoder(ResourceEncoder<Z> resourceEncoder) {
        this.encoder = resourceEncoder;
    }

    public void setTranscoder(ResourceTranscoder<Z, R> resourceTranscoder) {
        this.transcoder = resourceTranscoder;
    }

    public void setSourceEncoder(Encoder<T> encoder2) {
        this.sourceEncoder = encoder2;
    }

    public ResourceDecoder<File, Z> getCacheDecoder() {
        ResourceDecoder<File, Z> resourceDecoder = this.cacheDecoder;
        if (resourceDecoder != null) {
            return resourceDecoder;
        }
        return this.parent.getCacheDecoder();
    }

    public ResourceDecoder<T, Z> getSourceDecoder() {
        ResourceDecoder<T, Z> resourceDecoder = this.sourceDecoder;
        if (resourceDecoder != null) {
            return resourceDecoder;
        }
        return this.parent.getSourceDecoder();
    }

    public Encoder<T> getSourceEncoder() {
        Encoder<T> encoder2 = this.sourceEncoder;
        if (encoder2 != null) {
            return encoder2;
        }
        return this.parent.getSourceEncoder();
    }

    public ResourceEncoder<Z> getEncoder() {
        ResourceEncoder<Z> resourceEncoder = this.encoder;
        if (resourceEncoder != null) {
            return resourceEncoder;
        }
        return this.parent.getEncoder();
    }

    public ResourceTranscoder<Z, R> getTranscoder() {
        ResourceTranscoder<Z, R> resourceTranscoder = this.transcoder;
        if (resourceTranscoder != null) {
            return resourceTranscoder;
        }
        return this.parent.getTranscoder();
    }

    public ChildLoadProvider<A, T, Z, R> clone() {
        try {
            return (ChildLoadProvider) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
