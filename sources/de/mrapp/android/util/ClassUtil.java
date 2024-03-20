package de.mrapp.android.util;

public final class ClassUtil {
    private ClassUtil() {
    }

    public static String getTruncatedName(Class<?> cls) {
        Condition.ensureNotNull(cls, "The class may not be null");
        String[] split = cls.getName().split("\\.");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (i != split.length - 1) {
                sb.append(split[i].substring(0, 1));
                sb.append(".");
            } else {
                sb.append(split[i]);
            }
        }
        return sb.toString();
    }
}
