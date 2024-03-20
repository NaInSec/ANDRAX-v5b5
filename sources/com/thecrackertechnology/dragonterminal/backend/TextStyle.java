package com.thecrackertechnology.dragonterminal.backend;

import android.support.v4.app.FrameMetricsAggregator;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.view.ViewCompat;

public final class TextStyle {
    public static final int CHARACTER_ATTRIBUTE_BLINK = 8;
    public static final int CHARACTER_ATTRIBUTE_BOLD = 1;
    public static final int CHARACTER_ATTRIBUTE_DIM = 256;
    public static final int CHARACTER_ATTRIBUTE_INVERSE = 16;
    public static final int CHARACTER_ATTRIBUTE_INVISIBLE = 32;
    public static final int CHARACTER_ATTRIBUTE_ITALIC = 2;
    public static final int CHARACTER_ATTRIBUTE_PROTECTED = 128;
    public static final int CHARACTER_ATTRIBUTE_STRIKETHROUGH = 64;
    private static final int CHARACTER_ATTRIBUTE_TRUECOLOR_BACKGROUND = 1024;
    private static final int CHARACTER_ATTRIBUTE_TRUECOLOR_FOREGROUND = 512;
    public static final int CHARACTER_ATTRIBUTE_UNDERLINE = 4;
    public static final int COLOR_INDEX_BACKGROUND = 257;
    public static final int COLOR_INDEX_CURSOR = 258;
    public static final int COLOR_INDEX_FOREGROUND = 256;
    static final long NORMAL = encode(256, 257, 0);
    public static final int NUM_INDEXED_COLORS = 259;

    public static int decodeBackColor(long j) {
        return (PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID & j) == 0 ? (int) ((j >>> 16) & 511) : ((int) ((j >>> 16) & 16777215)) | ViewCompat.MEASURED_STATE_MASK;
    }

    public static int decodeEffect(long j) {
        return (int) (j & 2047);
    }

    public static int decodeForeColor(long j) {
        return (512 & j) == 0 ? (int) ((j >>> 40) & 511) : ((int) ((j >>> 40) & 16777215)) | ViewCompat.MEASURED_STATE_MASK;
    }

    static long encode(int i, int i2, int i3) {
        return ((i2 & ViewCompat.MEASURED_STATE_MASK) == -16777216 ? ((((long) i2) & 16777215) << 16) | PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID : (((long) i2) & 511) << 16) | ((long) (i3 & FrameMetricsAggregator.EVERY_DURATION)) | ((i & ViewCompat.MEASURED_STATE_MASK) == -16777216 ? 512 | ((((long) i) & 16777215) << 40) : (((long) i) & 511) << 40);
    }
}
