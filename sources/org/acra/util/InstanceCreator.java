package org.acra.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.acra.ACRA;
import org.acra.log.ACRALog;

public final class InstanceCreator {

    @FunctionalInterface
    public interface Fallback<T> {
        T get();
    }

    public <T> T create(Class<? extends T> cls, Fallback<T> fallback) {
        T create = create(cls);
        return create != null ? create : fallback.get();
    }

    /* access modifiers changed from: package-private */
    public <T> T create(Class<? extends T> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            ACRALog aCRALog = ACRA.log;
            String str = ACRA.LOG_TAG;
            aCRALog.e(str, "Failed to create instance of class " + cls.getName(), e);
            return null;
        } catch (IllegalAccessException e2) {
            ACRALog aCRALog2 = ACRA.log;
            String str2 = ACRA.LOG_TAG;
            aCRALog2.e(str2, "Failed to create instance of class " + cls.getName(), e2);
            return null;
        }
    }

    public <T> List<T> create(Collection<Class<? extends T>> collection) {
        ArrayList arrayList = new ArrayList();
        for (Class<? extends T> create : collection) {
            Object create2 = create(create);
            if (create2 != null) {
                arrayList.add(create2);
            }
        }
        return arrayList;
    }
}
