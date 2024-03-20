package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.gifdecoder.GifHeader;
import com.bumptech.glide.gifdecoder.GifHeaderParser;
import com.bumptech.glide.gifencoder.AnimatedGifEncoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.UnitTransformation;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.util.LogTime;
import java.io.IOException;
import java.io.OutputStream;

public class GifResourceEncoder implements ResourceEncoder<GifDrawable> {
    private static final Factory FACTORY = new Factory();
    private static final String TAG = "GifEncoder";
    private final BitmapPool bitmapPool;
    private final Factory factory;
    private final GifDecoder.BitmapProvider provider;

    public String getId() {
        return "";
    }

    public GifResourceEncoder(BitmapPool bitmapPool2) {
        this(bitmapPool2, FACTORY);
    }

    GifResourceEncoder(BitmapPool bitmapPool2, Factory factory2) {
        this.bitmapPool = bitmapPool2;
        this.provider = new GifBitmapProvider(bitmapPool2);
        this.factory = factory2;
    }

    /* JADX INFO: finally extract failed */
    public boolean encode(Resource<GifDrawable> resource, OutputStream outputStream) {
        long logTime = LogTime.getLogTime();
        GifDrawable gifDrawable = resource.get();
        Transformation<Bitmap> frameTransformation = gifDrawable.getFrameTransformation();
        if (frameTransformation instanceof UnitTransformation) {
            return writeDataDirect(gifDrawable.getData(), outputStream);
        }
        GifDecoder decodeHeaders = decodeHeaders(gifDrawable.getData());
        AnimatedGifEncoder buildEncoder = this.factory.buildEncoder();
        if (!buildEncoder.start(outputStream)) {
            return false;
        }
        int i = 0;
        while (i < decodeHeaders.getFrameCount()) {
            Resource<Bitmap> transformedFrame = getTransformedFrame(decodeHeaders.getNextFrame(), frameTransformation, gifDrawable);
            try {
                if (!buildEncoder.addFrame(transformedFrame.get())) {
                    transformedFrame.recycle();
                    return false;
                }
                buildEncoder.setDelay(decodeHeaders.getDelay(decodeHeaders.getCurrentFrameIndex()));
                decodeHeaders.advance();
                transformedFrame.recycle();
                i++;
            } catch (Throwable th) {
                transformedFrame.recycle();
                throw th;
            }
        }
        boolean finish = buildEncoder.finish();
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "Encoded gif with " + decodeHeaders.getFrameCount() + " frames and " + gifDrawable.getData().length + " bytes in " + LogTime.getElapsedMillis(logTime) + " ms");
        }
        return finish;
    }

    private boolean writeDataDirect(byte[] bArr, OutputStream outputStream) {
        try {
            outputStream.write(bArr);
            return true;
        } catch (IOException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to write data to output stream in GifResourceEncoder", e);
            }
            return false;
        }
    }

    private GifDecoder decodeHeaders(byte[] bArr) {
        GifHeaderParser buildParser = this.factory.buildParser();
        buildParser.setData(bArr);
        GifHeader parseHeader = buildParser.parseHeader();
        GifDecoder buildDecoder = this.factory.buildDecoder(this.provider);
        buildDecoder.setData(parseHeader, bArr);
        buildDecoder.advance();
        return buildDecoder;
    }

    private Resource<Bitmap> getTransformedFrame(Bitmap bitmap, Transformation<Bitmap> transformation, GifDrawable gifDrawable) {
        Resource buildFrameResource = this.factory.buildFrameResource(bitmap, this.bitmapPool);
        Resource<Bitmap> transform = transformation.transform(buildFrameResource, gifDrawable.getIntrinsicWidth(), gifDrawable.getIntrinsicHeight());
        if (!buildFrameResource.equals(transform)) {
            buildFrameResource.recycle();
        }
        return transform;
    }

    static class Factory {
        Factory() {
        }

        public GifDecoder buildDecoder(GifDecoder.BitmapProvider bitmapProvider) {
            return new GifDecoder(bitmapProvider);
        }

        public GifHeaderParser buildParser() {
            return new GifHeaderParser();
        }

        public AnimatedGifEncoder buildEncoder() {
            return new AnimatedGifEncoder();
        }

        public Resource<Bitmap> buildFrameResource(Bitmap bitmap, BitmapPool bitmapPool) {
            return new BitmapResource(bitmap, bitmapPool);
        }
    }
}
