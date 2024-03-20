package com.bumptech.glide.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MarkEnforcingInputStream extends FilterInputStream {
    private static final int END_OF_STREAM = -1;
    private static final int UNSET = Integer.MIN_VALUE;
    private int availableBytes = Integer.MIN_VALUE;

    public MarkEnforcingInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public void mark(int i) {
        super.mark(i);
        this.availableBytes = i;
    }

    public int read() throws IOException {
        if (getBytesToRead(1) == -1) {
            return -1;
        }
        int read = super.read();
        updateAvailableBytesAfterRead(1);
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int bytesToRead = (int) getBytesToRead((long) i2);
        if (bytesToRead == -1) {
            return -1;
        }
        int read = super.read(bArr, i, bytesToRead);
        updateAvailableBytesAfterRead((long) read);
        return read;
    }

    public void reset() throws IOException {
        super.reset();
        this.availableBytes = Integer.MIN_VALUE;
    }

    public long skip(long j) throws IOException {
        long bytesToRead = getBytesToRead(j);
        if (bytesToRead == -1) {
            return -1;
        }
        long skip = super.skip(bytesToRead);
        updateAvailableBytesAfterRead(skip);
        return skip;
    }

    public int available() throws IOException {
        int i = this.availableBytes;
        return i == Integer.MIN_VALUE ? super.available() : Math.min(i, super.available());
    }

    private long getBytesToRead(long j) {
        int i = this.availableBytes;
        if (i == 0) {
            return -1;
        }
        return (i == Integer.MIN_VALUE || j <= ((long) i)) ? j : (long) i;
    }

    private void updateAvailableBytesAfterRead(long j) {
        int i = this.availableBytes;
        if (i != Integer.MIN_VALUE && j != -1) {
            this.availableBytes = (int) (((long) i) - j);
        }
    }
}
