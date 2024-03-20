package com.thecrackertechnology.dragonterminal;

import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;

public class XZInputStream extends InputStream {
    private InputStream in = null;
    private int inAvailable = 0;
    private final byte[] inBuf = new byte[8192];
    private int inOffset = 0;
    private long nativeData = 0;
    private int[] offsets = new int[2];
    private boolean outBufEof = false;
    private final byte[] tempBuf = new byte[1];

    private native void nativeClose(long j);

    private native long nativeInit();

    private native int nativeRead(long j, byte[] bArr, int i, byte[] bArr2, int i2, int[] iArr);

    public int available() throws IOException {
        return 0;
    }

    public XZInputStream(InputStream inputStream) throws IOException {
        this.in = inputStream;
        if (inputStream != null) {
            this.nativeData = nativeInit();
            if (this.nativeData == 0) {
                throw new OutOfMemoryError("Cannot initialize JNI liblzma object");
            }
            return;
        }
        throw new NullPointerException("InputStream == null");
    }

    /* JADX INFO: finally extract failed */
    public void close() throws IOException {
        synchronized (this) {
            if (this.nativeData != 0) {
                nativeClose(this.nativeData);
            }
            this.nativeData = 0;
            if (this.in != null) {
                try {
                    this.in.close();
                    this.in = null;
                } catch (Throwable th) {
                    this.in = null;
                    throw th;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws IOException {
        try {
            close();
            try {
                super.finalize();
            } catch (Throwable th) {
                throw new AssertionError(th);
            }
        } catch (Throwable th2) {
            throw new AssertionError(th2);
        }
    }

    public int read() throws IOException {
        if (read(this.tempBuf, 0, 1) == -1) {
            return -1;
        }
        return this.tempBuf[0] & UByte.MAX_VALUE;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.outBufEof) {
            return -1;
        }
        if (i2 <= 0) {
            return 0;
        }
        int i3 = this.inOffset;
        int i4 = this.inAvailable;
        if (i3 >= i4 && i4 != -1) {
            InputStream inputStream = this.in;
            byte[] bArr2 = this.inBuf;
            this.inAvailable = inputStream.read(bArr2, 0, bArr2.length);
            this.inOffset = 0;
        }
        int[] iArr = this.offsets;
        iArr[0] = this.inOffset;
        iArr[1] = i;
        int nativeRead = nativeRead(this.nativeData, this.inBuf, this.inAvailable, bArr, i2, iArr);
        int[] iArr2 = this.offsets;
        this.inOffset = iArr2[0];
        int i5 = iArr2[1];
        if (nativeRead != 0) {
            if (nativeRead == 1) {
                int i6 = this.inOffset;
                int i7 = this.inAvailable;
                if (i6 >= i7) {
                    if (i7 != -1) {
                        InputStream inputStream2 = this.in;
                        byte[] bArr3 = this.inBuf;
                        this.inAvailable = inputStream2.read(bArr3, 0, bArr3.length);
                    }
                    if (this.inAvailable == -1) {
                        this.outBufEof = true;
                    } else {
                        throw new IOException("Garbage at the end of LZMA stream");
                    }
                } else {
                    throw new IOException("Garbage at the end of LZMA stream");
                }
            } else {
                throw new IOException("LZMA error " + nativeRead);
            }
        }
        return i5 - i;
    }
}
