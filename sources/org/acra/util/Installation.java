package org.acra.util;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.acra.ACRA;
import org.acra.log.ACRALog;

public final class Installation {
    private static final String INSTALLATION = "ACRA-INSTALLATION";

    private Installation() {
    }

    public static synchronized String id(Context context) {
        String read;
        synchronized (Installation.class) {
            File file = new File(context.getFilesDir(), INSTALLATION);
            try {
                if (!file.exists()) {
                    IOUtils.writeStringToFile(file, UUID.randomUUID().toString());
                }
                read = new StreamReader(file).read();
            } catch (IOException | RuntimeException e) {
                ACRALog aCRALog = ACRA.log;
                String str = ACRA.LOG_TAG;
                aCRALog.w(str, "Couldn't retrieve InstallationId for " + context.getPackageName(), e);
                return "Couldn't retrieve InstallationId";
            }
        }
        return read;
    }
}
