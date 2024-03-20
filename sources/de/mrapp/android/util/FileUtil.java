package de.mrapp.android.util;

import java.io.File;
import java.io.IOException;

public final class FileUtil {
    private static void mkdir(File file, boolean z) throws IOException {
        Condition.ensureNotNull(file, "The directory may not be null");
        if (!(z ? file.mkdirs() : file.mkdir()) && !file.exists()) {
            throw new IOException("Failed to create directory \"" + file + "\"");
        }
    }

    private FileUtil() {
    }

    public static void mkdir(File file) throws IOException {
        mkdir(file, false);
    }

    public static void mkdirs(File file) throws IOException {
        mkdir(file, true);
    }

    public static void delete(File file) throws IOException {
        Condition.ensureNotNull(file, "The file may not be null");
        if (!file.delete() && file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("Directory \"" + file + "\" must be empty before being deleted");
            }
            throw new IOException("Failed to deleted file \"" + file + "\"");
        }
    }

    public static void deleteRecursively(File file) throws IOException {
        Condition.ensureNotNull(file, "The file or directory may not be null");
        if (file.isDirectory()) {
            for (File deleteRecursively : file.listFiles()) {
                deleteRecursively(deleteRecursively);
            }
        }
        delete(file);
    }

    public static void createNewFile(File file) throws IOException {
        createNewFile(file, false);
    }

    public static void createNewFile(File file, boolean z) throws IOException {
        Condition.ensureNotNull(file, "The file may not be null");
        if (file.createNewFile()) {
            return;
        }
        if (z) {
            try {
                delete(file);
                createNewFile(file, false);
            } catch (IOException unused) {
                throw new IOException("Failed to overwrite file \"" + file + "\"");
            }
        } else if (file.exists()) {
            throw new IOException("File \"" + file + "\" does already exist");
        } else {
            throw new IllegalArgumentException("The file must not be a directory");
        }
    }
}
