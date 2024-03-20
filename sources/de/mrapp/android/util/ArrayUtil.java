package de.mrapp.android.util;

public final class ArrayUtil {
    private ArrayUtil() {
    }

    public static int indexOf(boolean[] zArr, boolean z) {
        Condition.ensureNotNull(zArr, "The array may not be null");
        for (int i = 0; i < zArr.length; i++) {
            if (zArr[i] == z) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(boolean[] zArr, boolean z) {
        Condition.ensureNotNull(zArr, "The array may not be null");
        for (int length = zArr.length - 1; length >= 0; length--) {
            if (zArr[length] == z) {
                return length;
            }
        }
        return -1;
    }

    public static boolean contains(boolean[] zArr, boolean z) {
        return indexOf(zArr, z) != -1;
    }
}
