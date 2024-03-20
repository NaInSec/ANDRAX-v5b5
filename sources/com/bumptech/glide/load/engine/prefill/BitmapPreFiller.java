package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.prefill.PreFillType;
import com.bumptech.glide.util.Util;
import java.util.HashMap;

public final class BitmapPreFiller {
    private final BitmapPool bitmapPool;
    private BitmapPreFillRunner current;
    private final DecodeFormat defaultFormat;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final MemoryCache memoryCache;

    public BitmapPreFiller(MemoryCache memoryCache2, BitmapPool bitmapPool2, DecodeFormat decodeFormat) {
        this.memoryCache = memoryCache2;
        this.bitmapPool = bitmapPool2;
        this.defaultFormat = decodeFormat;
    }

    public void preFill(PreFillType.Builder... builderArr) {
        BitmapPreFillRunner bitmapPreFillRunner = this.current;
        if (bitmapPreFillRunner != null) {
            bitmapPreFillRunner.cancel();
        }
        PreFillType[] preFillTypeArr = new PreFillType[builderArr.length];
        for (int i = 0; i < builderArr.length; i++) {
            PreFillType.Builder builder = builderArr[i];
            if (builder.getConfig() == null) {
                builder.setConfig((this.defaultFormat == DecodeFormat.ALWAYS_ARGB_8888 || this.defaultFormat == DecodeFormat.PREFER_ARGB_8888) ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            }
            preFillTypeArr[i] = builder.build();
        }
        this.current = new BitmapPreFillRunner(this.bitmapPool, this.memoryCache, generateAllocationOrder(preFillTypeArr));
        this.handler.post(this.current);
    }

    /* access modifiers changed from: package-private */
    public PreFillQueue generateAllocationOrder(PreFillType[] preFillTypeArr) {
        int maxSize = (this.memoryCache.getMaxSize() - this.memoryCache.getCurrentSize()) + this.bitmapPool.getMaxSize();
        int i = 0;
        for (PreFillType weight : preFillTypeArr) {
            i += weight.getWeight();
        }
        float f = ((float) maxSize) / ((float) i);
        HashMap hashMap = new HashMap();
        for (PreFillType preFillType : preFillTypeArr) {
            hashMap.put(preFillType, Integer.valueOf(Math.round(((float) preFillType.getWeight()) * f) / getSizeInBytes(preFillType)));
        }
        return new PreFillQueue(hashMap);
    }

    private static int getSizeInBytes(PreFillType preFillType) {
        return Util.getBitmapByteSize(preFillType.getWidth(), preFillType.getHeight(), preFillType.getConfig());
    }
}
