package org.acra.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.acra.ACRA;
import org.acra.log.ACRALog;

public final class IOUtils {
    private IOUtils() {
    }

    public static void safeClose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void deleteFile(File file) {
        if (!file.delete()) {
            ACRALog aCRALog = ACRA.log;
            String str = ACRA.LOG_TAG;
            aCRALog.w(str, "Could not delete file: " + file);
        }
    }

    public static void writeStringToFile(File file, String str) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        try {
            outputStreamWriter.write(str);
            outputStreamWriter.flush();
        } finally {
            safeClose(outputStreamWriter);
        }
    }
}
