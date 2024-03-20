package com.bumptech.glide;

import android.content.Context;
import android.os.Build;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor;
import java.util.concurrent.ExecutorService;

public class GlideBuilder {
    private BitmapPool bitmapPool;
    private final Context context;
    private DecodeFormat decodeFormat;
    private DiskCache.Factory diskCacheFactory;
    private ExecutorService diskCacheService;
    private Engine engine;
    private MemoryCache memoryCache;
    private ExecutorService sourceService;

    public GlideBuilder(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public GlideBuilder setBitmapPool(BitmapPool bitmapPool2) {
        this.bitmapPool = bitmapPool2;
        return this;
    }

    public GlideBuilder setMemoryCache(MemoryCache memoryCache2) {
        this.memoryCache = memoryCache2;
        return this;
    }

    @Deprecated
    public GlideBuilder setDiskCache(final DiskCache diskCache) {
        return setDiskCache((DiskCache.Factory) new DiskCache.Factory() {
            public DiskCache build() {
                return diskCache;
            }
        });
    }

    public GlideBuilder setDiskCache(DiskCache.Factory factory) {
        this.diskCacheFactory = factory;
        return this;
    }

    public GlideBuilder setResizeService(ExecutorService executorService) {
        this.sourceService = executorService;
        return this;
    }

    public GlideBuilder setDiskCacheService(ExecutorService executorService) {
        this.diskCacheService = executorService;
        return this;
    }

    public GlideBuilder setDecodeFormat(DecodeFormat decodeFormat2) {
        this.decodeFormat = decodeFormat2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public GlideBuilder setEngine(Engine engine2) {
        this.engine = engine2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Glide createGlide() {
        if (this.sourceService == null) {
            this.sourceService = new FifoPriorityThreadPoolExecutor(Math.max(1, Runtime.getRuntime().availableProcessors()));
        }
        if (this.diskCacheService == null) {
            this.diskCacheService = new FifoPriorityThreadPoolExecutor(1);
        }
        MemorySizeCalculator memorySizeCalculator = new MemorySizeCalculator(this.context);
        if (this.bitmapPool == null) {
            if (Build.VERSION.SDK_INT >= 11) {
                this.bitmapPool = new LruBitmapPool(memorySizeCalculator.getBitmapPoolSize());
            } else {
                this.bitmapPool = new BitmapPoolAdapter();
            }
        }
        if (this.memoryCache == null) {
            this.memoryCache = new LruResourceCache(memorySizeCalculator.getMemoryCacheSize());
        }
        if (this.diskCacheFactory == null) {
            this.diskCacheFactory = new InternalCacheDiskCacheFactory(this.context);
        }
        if (this.engine == null) {
            this.engine = new Engine(this.memoryCache, this.diskCacheFactory, this.diskCacheService, this.sourceService);
        }
        if (this.decodeFormat == null) {
            this.decodeFormat = DecodeFormat.DEFAULT;
        }
        return new Glide(this.engine, this.memoryCache, this.bitmapPool, this.context, this.decodeFormat);
    }
}
