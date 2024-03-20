package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.IOException;

public class VideoBitmapDecoder implements BitmapDecoder<ParcelFileDescriptor> {
    private static final MediaMetadataRetrieverFactory DEFAULT_FACTORY = new MediaMetadataRetrieverFactory();
    private static final int NO_FRAME = -1;
    private MediaMetadataRetrieverFactory factory;
    private int frame;

    public String getId() {
        return "VideoBitmapDecoder.com.bumptech.glide.load.resource.bitmap";
    }

    public VideoBitmapDecoder() {
        this(DEFAULT_FACTORY, -1);
    }

    public VideoBitmapDecoder(int i) {
        this(DEFAULT_FACTORY, checkValidFrame(i));
    }

    VideoBitmapDecoder(MediaMetadataRetrieverFactory mediaMetadataRetrieverFactory) {
        this(mediaMetadataRetrieverFactory, -1);
    }

    VideoBitmapDecoder(MediaMetadataRetrieverFactory mediaMetadataRetrieverFactory, int i) {
        this.factory = mediaMetadataRetrieverFactory;
        this.frame = i;
    }

    public Bitmap decode(ParcelFileDescriptor parcelFileDescriptor, BitmapPool bitmapPool, int i, int i2, DecodeFormat decodeFormat) throws IOException {
        Bitmap bitmap;
        MediaMetadataRetriever build = this.factory.build();
        build.setDataSource(parcelFileDescriptor.getFileDescriptor());
        int i3 = this.frame;
        if (i3 >= 0) {
            bitmap = build.getFrameAtTime((long) i3);
        } else {
            bitmap = build.getFrameAtTime();
        }
        build.release();
        parcelFileDescriptor.close();
        return bitmap;
    }

    static class MediaMetadataRetrieverFactory {
        MediaMetadataRetrieverFactory() {
        }

        public MediaMetadataRetriever build() {
            return new MediaMetadataRetriever();
        }
    }

    private static int checkValidFrame(int i) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException("Requested frame must be non-negative");
    }
}
