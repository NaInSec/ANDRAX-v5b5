package com.bumptech.glide.signature;

import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class MediaStoreSignature implements Key {
    private final long dateModified;
    private final String mimeType;
    private final int orientation;

    public MediaStoreSignature(String str, long j, int i) {
        this.mimeType = str;
        this.dateModified = j;
        this.orientation = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaStoreSignature mediaStoreSignature = (MediaStoreSignature) obj;
        if (this.dateModified != mediaStoreSignature.dateModified || this.orientation != mediaStoreSignature.orientation) {
            return false;
        }
        String str = this.mimeType;
        String str2 = mediaStoreSignature.mimeType;
        return str == null ? str2 == null : str.equals(str2);
    }

    public int hashCode() {
        String str = this.mimeType;
        int hashCode = str != null ? str.hashCode() : 0;
        long j = this.dateModified;
        return (((hashCode * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.orientation;
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) throws UnsupportedEncodingException {
        messageDigest.update(ByteBuffer.allocate(12).putLong(this.dateModified).putInt(this.orientation).array());
        messageDigest.update(this.mimeType.getBytes("UTF-8"));
    }
}
