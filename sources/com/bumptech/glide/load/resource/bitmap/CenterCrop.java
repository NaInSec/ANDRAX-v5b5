package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

public class CenterCrop extends BitmapTransformation {
    public String getId() {
        return "CenterCrop.com.bumptech.glide.load.resource.bitmap";
    }

    public CenterCrop(Context context) {
        super(context);
    }

    public CenterCrop(BitmapPool bitmapPool) {
        super(bitmapPool);
    }

    /* access modifiers changed from: protected */
    public Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        Bitmap bitmap2 = bitmapPool.get(i, i2, bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888);
        Bitmap centerCrop = TransformationUtils.centerCrop(bitmap2, bitmap, i, i2);
        if (!(bitmap2 == null || bitmap2 == centerCrop || bitmapPool.put(bitmap2))) {
            bitmap2.recycle();
        }
        return centerCrop;
    }
}
