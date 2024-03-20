package org.acra.file;

import android.content.Context;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import java.io.File;
import java.util.regex.Pattern;

public enum Directory {
    FILES_LEGACY {
        public File getFile(Context context, String str) {
            return (str.startsWith("/") ? Directory.ROOT : Directory.FILES).getFile(context, str);
        }
    },
    FILES {
        public File getFile(Context context, String str) {
            return new File(context.getFilesDir(), str);
        }
    },
    EXTERNAL_FILES {
        public File getFile(Context context, String str) {
            return new File(context.getExternalFilesDir((String) null), str);
        }
    },
    CACHE {
        public File getFile(Context context, String str) {
            return new File(context.getCacheDir(), str);
        }
    },
    EXTERNAL_CACHE {
        public File getFile(Context context, String str) {
            return new File(context.getExternalCacheDir(), str);
        }
    },
    NO_BACKUP_FILES {
        public File getFile(Context context, String str) {
            return new File(ContextCompat.getNoBackupFilesDir(context), str);
        }
    },
    EXTERNAL_STORAGE {
        public File getFile(Context context, String str) {
            return new File(Environment.getExternalStorageDirectory(), str);
        }
    },
    ROOT {
        public File getFile(Context context, String str) {
            String[] split = str.split(Pattern.quote(File.separator), 2);
            if (split.length == 1) {
                return new File(str);
            }
            File[] listRoots = File.listRoots();
            for (File file : listRoots) {
                if (split[0].equals(file.getPath().replace(File.separator, ""))) {
                    return new File(file, split[1]);
                }
            }
            return new File(listRoots[0], str);
        }
    };

    public abstract File getFile(Context context, String str);
}
