package de.mrapp.android.util;

import java.io.Closeable;
import java.io.IOException;

public final class StreamUtil {
    private StreamUtil() {
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
