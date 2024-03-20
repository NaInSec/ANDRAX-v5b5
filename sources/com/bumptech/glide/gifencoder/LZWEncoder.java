package com.bumptech.glide.gifencoder;

import android.support.v4.app.FrameMetricsAggregator;
import android.support.v4.internal.view.SupportMenu;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.UByte;

class LZWEncoder {
    static final int BITS = 12;
    private static final int EOF = -1;
    static final int HSIZE = 5003;
    int ClearCode;
    int EOFCode;
    int a_count;
    byte[] accum = new byte[256];
    boolean clear_flg = false;
    int[] codetab = new int[HSIZE];
    private int curPixel;
    int cur_accum = 0;
    int cur_bits = 0;
    int free_ent = 0;
    int g_init_bits;
    int hsize = HSIZE;
    int[] htab = new int[HSIZE];
    private int imgH;
    private int imgW;
    private int initCodeSize;
    int[] masks = {0, 1, 3, 7, 15, 31, 63, 127, 255, FrameMetricsAggregator.EVERY_DURATION, 1023, 2047, 4095, 8191, 16383, 32767, SupportMenu.USER_MASK};
    int maxbits = 12;
    int maxcode;
    int maxmaxcode = 4096;
    int n_bits;
    private byte[] pixAry;
    private int remaining;

    /* access modifiers changed from: package-private */
    public final int MAXCODE(int i) {
        return (1 << i) - 1;
    }

    LZWEncoder(int i, int i2, byte[] bArr, int i3) {
        this.imgW = i;
        this.imgH = i2;
        this.pixAry = bArr;
        this.initCodeSize = Math.max(2, i3);
    }

    /* access modifiers changed from: package-private */
    public void char_out(byte b, OutputStream outputStream) throws IOException {
        byte[] bArr = this.accum;
        int i = this.a_count;
        this.a_count = i + 1;
        bArr[i] = b;
        if (this.a_count >= 254) {
            flush_char(outputStream);
        }
    }

    /* access modifiers changed from: package-private */
    public void cl_block(OutputStream outputStream) throws IOException {
        cl_hash(this.hsize);
        int i = this.ClearCode;
        this.free_ent = i + 2;
        this.clear_flg = true;
        output(i, outputStream);
    }

    /* access modifiers changed from: package-private */
    public void cl_hash(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.htab[i2] = -1;
        }
    }

    /* access modifiers changed from: package-private */
    public void compress(int i, OutputStream outputStream) throws IOException {
        this.g_init_bits = i;
        int i2 = 0;
        this.clear_flg = false;
        this.n_bits = this.g_init_bits;
        this.maxcode = MAXCODE(this.n_bits);
        this.ClearCode = 1 << (i - 1);
        int i3 = this.ClearCode;
        this.EOFCode = i3 + 1;
        this.free_ent = i3 + 2;
        this.a_count = 0;
        int nextPixel = nextPixel();
        for (int i4 = this.hsize; i4 < 65536; i4 *= 2) {
            i2++;
        }
        int i5 = 8 - i2;
        int i6 = this.hsize;
        cl_hash(i6);
        output(this.ClearCode, outputStream);
        while (true) {
            int nextPixel2 = nextPixel();
            if (nextPixel2 != -1) {
                int i7 = (nextPixel2 << this.maxbits) + nextPixel;
                int i8 = (nextPixel2 << i5) ^ nextPixel;
                int[] iArr = this.htab;
                if (iArr[i8] == i7) {
                    nextPixel = this.codetab[i8];
                } else if (iArr[i8] >= 0) {
                    int i9 = i6 - i8;
                    if (i8 == 0) {
                        i9 = 1;
                    }
                    while (true) {
                        i8 -= i9;
                        if (i8 < 0) {
                            i8 += i6;
                        }
                        int[] iArr2 = this.htab;
                        if (iArr2[i8] != i7) {
                            if (iArr2[i8] < 0) {
                                break;
                            }
                        } else {
                            nextPixel = this.codetab[i8];
                            break;
                        }
                    }
                } else {
                    output(nextPixel, outputStream);
                    int i10 = this.free_ent;
                    if (i10 < this.maxmaxcode) {
                        int[] iArr3 = this.codetab;
                        this.free_ent = i10 + 1;
                        iArr3[i8] = i10;
                        this.htab[i8] = i7;
                    } else {
                        cl_block(outputStream);
                    }
                    nextPixel = nextPixel2;
                }
            } else {
                output(nextPixel, outputStream);
                output(this.EOFCode, outputStream);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void encode(OutputStream outputStream) throws IOException {
        outputStream.write(this.initCodeSize);
        this.remaining = this.imgW * this.imgH;
        this.curPixel = 0;
        compress(this.initCodeSize + 1, outputStream);
        outputStream.write(0);
    }

    /* access modifiers changed from: package-private */
    public void flush_char(OutputStream outputStream) throws IOException {
        int i = this.a_count;
        if (i > 0) {
            outputStream.write(i);
            outputStream.write(this.accum, 0, this.a_count);
            this.a_count = 0;
        }
    }

    private int nextPixel() {
        int i = this.remaining;
        if (i == 0) {
            return -1;
        }
        this.remaining = i - 1;
        byte[] bArr = this.pixAry;
        int i2 = this.curPixel;
        this.curPixel = i2 + 1;
        return bArr[i2] & UByte.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public void output(int i, OutputStream outputStream) throws IOException {
        int i2 = this.cur_accum;
        int[] iArr = this.masks;
        int i3 = this.cur_bits;
        this.cur_accum = i2 & iArr[i3];
        if (i3 > 0) {
            this.cur_accum |= i << i3;
        } else {
            this.cur_accum = i;
        }
        this.cur_bits += this.n_bits;
        while (this.cur_bits >= 8) {
            char_out((byte) (this.cur_accum & 255), outputStream);
            this.cur_accum >>= 8;
            this.cur_bits -= 8;
        }
        if (this.free_ent > this.maxcode || this.clear_flg) {
            if (this.clear_flg) {
                int i4 = this.g_init_bits;
                this.n_bits = i4;
                this.maxcode = MAXCODE(i4);
                this.clear_flg = false;
            } else {
                this.n_bits++;
                int i5 = this.n_bits;
                if (i5 == this.maxbits) {
                    this.maxcode = this.maxmaxcode;
                } else {
                    this.maxcode = MAXCODE(i5);
                }
            }
        }
        if (i == this.EOFCode) {
            while (this.cur_bits > 0) {
                char_out((byte) (this.cur_accum & 255), outputStream);
                this.cur_accum >>= 8;
                this.cur_bits -= 8;
            }
            flush_char(outputStream);
        }
    }
}
