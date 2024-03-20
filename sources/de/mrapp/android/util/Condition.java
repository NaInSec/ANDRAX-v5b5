package de.mrapp.android.util;

import android.text.TextUtils;
import java.io.File;

public final class Condition {
    private Condition() {
    }

    private static void throwException(String str, Class<? extends RuntimeException> cls) {
        RuntimeException runtimeException;
        try {
            runtimeException = (RuntimeException) cls.getConstructor(new Class[]{String.class}).newInstance(new Object[]{str});
        } catch (Exception unused) {
            runtimeException = new RuntimeException(str);
        }
        throw runtimeException;
    }

    public static void ensureTrue(boolean z, String str) {
        ensureTrue(z, str, IllegalArgumentException.class);
    }

    public static void ensureTrue(boolean z, String str, Class<? extends RuntimeException> cls) {
        if (!z) {
            throwException(str, cls);
        }
    }

    public static void ensureFalse(boolean z, String str) {
        ensureFalse(z, str, IllegalArgumentException.class);
    }

    public static void ensureFalse(boolean z, String str, Class<? extends RuntimeException> cls) {
        if (z) {
            throwException(str, cls);
        }
    }

    public static void ensureEqual(Object obj, Object obj2, String str) {
        ensureEqual(obj, obj2, str, IllegalArgumentException.class);
    }

    public static void ensureEqual(Object obj, Object obj2, String str, Class<? extends RuntimeException> cls) {
        if ((obj == null && obj2 != null) || (obj != null && !obj.equals(obj2))) {
            throwException(str, cls);
        }
    }

    public static void ensureNotEqual(Object obj, Object obj2, String str) {
        ensureNotEqual(obj, obj2, str, IllegalArgumentException.class);
    }

    public static void ensureNotEqual(Object obj, Object obj2, String str, Class<? extends RuntimeException> cls) {
        if ((obj == null && obj2 == null) || (obj != null && obj.equals(obj2))) {
            throwException(str, cls);
        }
    }

    public static void ensureNotNull(Object obj, String str) {
        ensureNotNull(obj, str, NullPointerException.class);
    }

    public static void ensureNotNull(Object obj, String str, Class<? extends RuntimeException> cls) {
        if (obj == null) {
            throwException(str, cls);
        }
    }

    public static void ensureNotEmpty(CharSequence charSequence, String str) {
        ensureNotEmpty(charSequence, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureNotEmpty(CharSequence charSequence, String str, Class<? extends RuntimeException> cls) {
        if (TextUtils.isEmpty(charSequence)) {
            throwException(str, cls);
        }
    }

    public static void ensureAtLeast(short s, short s2, String str) {
        ensureAtLeast(s, s2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureAtLeast(short s, short s2, String str, Class<? extends RuntimeException> cls) {
        if (s < s2) {
            throwException(str, cls);
        }
    }

    public static void ensureAtLeast(int i, int i2, String str) {
        ensureAtLeast(i, i2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureAtLeast(int i, int i2, String str, Class<? extends RuntimeException> cls) {
        if (i < i2) {
            throwException(str, cls);
        }
    }

    public static void ensureAtLeast(long j, long j2, String str) {
        ensureAtLeast(j, j2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureAtLeast(long j, long j2, String str, Class<? extends RuntimeException> cls) {
        if (j < j2) {
            throwException(str, cls);
        }
    }

    public static void ensureAtLeast(float f, float f2, String str) {
        ensureAtLeast(f, f2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureAtLeast(float f, float f2, String str, Class<? extends RuntimeException> cls) {
        if (f < f2) {
            throwException(str, cls);
        }
    }

    public static void ensureAtLeast(double d, double d2, String str) {
        ensureAtLeast(d, d2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureAtLeast(double d, double d2, String str, Class<? extends RuntimeException> cls) {
        if (d < d2) {
            throwException(str, cls);
        }
    }

    public static void ensureAtMaximum(short s, short s2, String str) {
        ensureAtMaximum(s, s2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureAtMaximum(short s, short s2, String str, Class<? extends RuntimeException> cls) {
        if (s > s2) {
            throwException(str, cls);
        }
    }

    public static void ensureAtMaximum(int i, int i2, String str) {
        ensureAtMaximum(i, i2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureAtMaximum(int i, int i2, String str, Class<? extends RuntimeException> cls) {
        if (i > i2) {
            throwException(str, cls);
        }
    }

    public static void ensureAtMaximum(long j, long j2, String str) {
        ensureAtMaximum(j, j2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureAtMaximum(long j, long j2, String str, Class<? extends RuntimeException> cls) {
        if (j > j2) {
            throwException(str, cls);
        }
    }

    public static void ensureAtMaximum(float f, float f2, String str) {
        ensureAtMaximum(f, f2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureAtMaximum(float f, float f2, String str, Class<? extends RuntimeException> cls) {
        if (f > f2) {
            throwException(str, cls);
        }
    }

    public static void ensureAtMaximum(double d, double d2, String str) {
        ensureAtMaximum(d, d2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureAtMaximum(double d, double d2, String str, Class<? extends RuntimeException> cls) {
        if (d > d2) {
            throwException(str, cls);
        }
    }

    public static void ensureGreater(short s, short s2, String str) {
        ensureGreater(s, s2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureGreater(short s, short s2, String str, Class<? extends RuntimeException> cls) {
        if (s <= s2) {
            throwException(str, cls);
        }
    }

    public static void ensureGreater(int i, int i2, String str) {
        ensureGreater(i, i2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureGreater(int i, int i2, String str, Class<? extends RuntimeException> cls) {
        if (i <= i2) {
            throwException(str, cls);
        }
    }

    public static void ensureGreater(long j, long j2, String str) {
        ensureGreater(j, j2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureGreater(long j, long j2, String str, Class<? extends RuntimeException> cls) {
        if (j <= j2) {
            throwException(str, cls);
        }
    }

    public static void ensureGreater(float f, float f2, String str) {
        ensureGreater(f, f2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureGreater(float f, float f2, String str, Class<? extends RuntimeException> cls) {
        if (f <= f2) {
            throwException(str, cls);
        }
    }

    public static void ensureGreater(double d, double d2, String str) {
        ensureGreater(d, d2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureGreater(double d, double d2, String str, Class<? extends RuntimeException> cls) {
        if (d <= d2) {
            throwException(str, cls);
        }
    }

    public static void ensureSmaller(short s, short s2, String str) {
        ensureSmaller(s, s2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureSmaller(short s, short s2, String str, Class<? extends RuntimeException> cls) {
        if (s >= s2) {
            throwException(str, cls);
        }
    }

    public static void ensureSmaller(int i, int i2, String str) {
        ensureSmaller(i, i2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureSmaller(int i, int i2, String str, Class<? extends RuntimeException> cls) {
        if (i >= i2) {
            throwException(str, cls);
        }
    }

    public static void ensureSmaller(long j, long j2, String str) {
        ensureSmaller(j, j2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureSmaller(long j, long j2, String str, Class<? extends RuntimeException> cls) {
        if (j >= j2) {
            throwException(str, cls);
        }
    }

    public static void ensureSmaller(float f, float f2, String str) {
        ensureSmaller(f, f2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureSmaller(float f, float f2, String str, Class<? extends RuntimeException> cls) {
        if (f >= f2) {
            throwException(str, cls);
        }
    }

    public static void ensureSmaller(double d, double d2, String str) {
        ensureSmaller(d, d2, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureSmaller(double d, double d2, String str, Class<? extends RuntimeException> cls) {
        if (d >= d2) {
            throwException(str, cls);
        }
    }

    public static void ensureNotEmpty(Iterable<?> iterable, String str) {
        ensureNotEmpty(iterable, str, (Class<? extends RuntimeException>) IllegalArgumentException.class);
    }

    public static void ensureNotEmpty(Iterable<?> iterable, String str, Class<? extends RuntimeException> cls) {
        if (!iterable.iterator().hasNext()) {
            throwException(str, cls);
        }
    }

    public static void ensureFileExists(File file, String str) {
        ensureFileExists(file, str, IllegalArgumentException.class);
    }

    public static void ensureFileExists(File file, String str, Class<? extends RuntimeException> cls) {
        if (!file.exists()) {
            throwException(str, cls);
        }
    }

    public static void ensureFileIsDirectory(File file, String str) {
        ensureFileIsDirectory(file, str, IllegalArgumentException.class);
    }

    public static void ensureFileIsDirectory(File file, String str, Class<? extends RuntimeException> cls) {
        if (!file.isDirectory()) {
            throwException(str, cls);
        }
    }

    public static void ensureFileIsNoDirectory(File file, String str) {
        ensureFileIsNoDirectory(file, str, IllegalArgumentException.class);
    }

    public static void ensureFileIsNoDirectory(File file, String str, Class<? extends RuntimeException> cls) {
        if (!file.isFile()) {
            throwException(str, cls);
        }
    }
}
