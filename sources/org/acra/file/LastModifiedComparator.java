package org.acra.file;

import java.io.File;
import java.util.Comparator;

final class LastModifiedComparator implements Comparator<File> {
    LastModifiedComparator() {
    }

    public int compare(File file, File file2) {
        int i = (file.lastModified() > file2.lastModified() ? 1 : (file.lastModified() == file2.lastModified() ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i == 0 ? 0 : 1;
    }
}
