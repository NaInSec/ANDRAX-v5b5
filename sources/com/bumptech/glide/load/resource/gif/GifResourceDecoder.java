package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.gifdecoder.GifHeader;
import com.bumptech.glide.gifdecoder.GifHeaderParser;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.UnitTransformation;
import com.bumptech.glide.util.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

public class GifResourceDecoder implements ResourceDecoder<InputStream, GifDrawable> {
    private static final GifDecoderPool DECODER_POOL = new GifDecoderPool();
    private static final GifHeaderParserPool PARSER_POOL = new GifHeaderParserPool();
    private static final String TAG = "GifResourceDecoder";
    private final BitmapPool bitmapPool;
    private final Context context;
    private final GifDecoderPool decoderPool;
    private final GifHeaderParserPool parserPool;
    private final GifBitmapProvider provider;

    public String getId() {
        return "";
    }

    public GifResourceDecoder(Context context2) {
        this(context2, Glide.get(context2).getBitmapPool());
    }

    public GifResourceDecoder(Context context2, BitmapPool bitmapPool2) {
        this(context2, bitmapPool2, PARSER_POOL, DECODER_POOL);
    }

    GifResourceDecoder(Context context2, BitmapPool bitmapPool2, GifHeaderParserPool gifHeaderParserPool, GifDecoderPool gifDecoderPool) {
        this.context = context2;
        this.bitmapPool = bitmapPool2;
        this.decoderPool = gifDecoderPool;
        this.provider = new GifBitmapProvider(bitmapPool2);
        this.parserPool = gifHeaderParserPool;
    }

    public GifDrawableResource decode(InputStream inputStream, int i, int i2) {
        byte[] inputStreamToBytes = inputStreamToBytes(inputStream);
        GifHeaderParser obtain = this.parserPool.obtain(inputStreamToBytes);
        GifDecoder obtain2 = this.decoderPool.obtain(this.provider);
        try {
            return decode(inputStreamToBytes, i, i2, obtain, obtain2);
        } finally {
            this.parserPool.release(obtain);
            this.decoderPool.release(obtain2);
        }
    }

    private GifDrawableResource decode(byte[] bArr, int i, int i2, GifHeaderParser gifHeaderParser, GifDecoder gifDecoder) {
        Bitmap decodeFirstFrame;
        GifHeader parseHeader = gifHeaderParser.parseHeader();
        if (parseHeader.getNumFrames() <= 0 || parseHeader.getStatus() != 0 || (decodeFirstFrame = decodeFirstFrame(gifDecoder, parseHeader, bArr)) == null) {
            return null;
        }
        return new GifDrawableResource(new GifDrawable(this.context, this.provider, this.bitmapPool, UnitTransformation.get(), i, i2, parseHeader, bArr, decodeFirstFrame));
    }

    private Bitmap decodeFirstFrame(GifDecoder gifDecoder, GifHeader gifHeader, byte[] bArr) {
        gifDecoder.setData(gifHeader, bArr);
        gifDecoder.advance();
        return gifDecoder.getNextFrame();
    }

    private static byte[] inputStreamToBytes(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16384);
        try {
            byte[] bArr = new byte[16384];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            Log.w(TAG, "Error reading data from stream", e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    static class GifDecoderPool {
        private final Queue<GifDecoder> pool = Util.createQueue(0);

        GifDecoderPool() {
        }

        public synchronized GifDecoder obtain(GifDecoder.BitmapProvider bitmapProvider) {
            GifDecoder poll;
            poll = this.pool.poll();
            if (poll == null) {
                poll = new GifDecoder(bitmapProvider);
            }
            return poll;
        }

        public synchronized void release(GifDecoder gifDecoder) {
            gifDecoder.clear();
            this.pool.offer(gifDecoder);
        }
    }

    static class GifHeaderParserPool {
        private final Queue<GifHeaderParser> pool = Util.createQueue(0);

        GifHeaderParserPool() {
        }

        public synchronized GifHeaderParser obtain(byte[] bArr) {
            GifHeaderParser poll;
            poll = this.pool.poll();
            if (poll == null) {
                poll = new GifHeaderParser();
            }
            return poll.setData(bArr);
        }

        public synchronized void release(GifHeaderParser gifHeaderParser) {
            gifHeaderParser.clear();
            this.pool.offer(gifHeaderParser);
        }
    }
}
