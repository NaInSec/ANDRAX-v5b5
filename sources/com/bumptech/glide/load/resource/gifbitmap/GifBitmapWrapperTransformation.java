package com.bumptech.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableTransformation;

public class GifBitmapWrapperTransformation implements Transformation<GifBitmapWrapper> {
    private final Transformation<Bitmap> bitmapTransformation;
    private final Transformation<GifDrawable> gifDataTransformation;

    public GifBitmapWrapperTransformation(BitmapPool bitmapPool, Transformation<Bitmap> transformation) {
        this(transformation, (Transformation<GifDrawable>) new GifDrawableTransformation(transformation, bitmapPool));
    }

    GifBitmapWrapperTransformation(Transformation<Bitmap> transformation, Transformation<GifDrawable> transformation2) {
        this.bitmapTransformation = transformation;
        this.gifDataTransformation = transformation2;
    }

    public Resource<GifBitmapWrapper> transform(Resource<GifBitmapWrapper> resource, int i, int i2) {
        Transformation<GifDrawable> transformation;
        Transformation<Bitmap> transformation2;
        Resource<Bitmap> bitmapResource = resource.get().getBitmapResource();
        Resource<GifDrawable> gifResource = resource.get().getGifResource();
        if (bitmapResource != null && (transformation2 = this.bitmapTransformation) != null) {
            Resource<Bitmap> transform = transformation2.transform(bitmapResource, i, i2);
            if (!bitmapResource.equals(transform)) {
                return new GifBitmapWrapperResource(new GifBitmapWrapper(transform, resource.get().getGifResource()));
            }
            return resource;
        } else if (gifResource == null || (transformation = this.gifDataTransformation) == null) {
            return resource;
        } else {
            Resource<GifDrawable> transform2 = transformation.transform(gifResource, i, i2);
            return !gifResource.equals(transform2) ? new GifBitmapWrapperResource(new GifBitmapWrapper(resource.get().getBitmapResource(), transform2)) : resource;
        }
    }

    public String getId() {
        return this.bitmapTransformation.getId();
    }
}
