package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import java.io.File;
import java.io.IOException;

class CacheLoader {
    private static final String TAG = "CacheLoader";
    private final DiskCache diskCache;

    public CacheLoader(DiskCache diskCache2) {
        this.diskCache = diskCache2;
    }

    public <Z> Resource<Z> load(Key key, ResourceDecoder<File, Z> resourceDecoder, int i, int i2) {
        File file = this.diskCache.get(key);
        Resource<Z> resource = null;
        if (file == null) {
            return null;
        }
        try {
            resource = resourceDecoder.decode(file, i, i2);
        } catch (IOException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Exception decoding image from cache", e);
            }
        }
        if (resource == null) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to decode image from cache or not present in cache");
            }
            this.diskCache.delete(key);
        }
        return resource;
    }
}
