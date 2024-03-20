package com.bumptech.glide.request.target;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;

public class ImageViewTargetFactory {
    public <Z> Target<Z> buildTarget(ImageView imageView, Class<Z> cls) {
        if (GlideDrawable.class.isAssignableFrom(cls)) {
            return new GlideDrawableImageViewTarget(imageView);
        }
        if (Bitmap.class.equals(cls)) {
            return new BitmapImageViewTarget(imageView);
        }
        if (Drawable.class.isAssignableFrom(cls)) {
            return new DrawableImageViewTarget(imageView);
        }
        throw new IllegalArgumentException("Unhandled class: " + cls + ", try .as*(Class).transcode(ResourceTranscoder)");
    }
}
