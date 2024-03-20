package org.acra.config;

import java.util.Iterator;

public final class ConfigUtils {
    public static <T extends Configuration> T getPluginConfiguration(CoreConfiguration coreConfiguration, Class<T> cls) {
        Iterator<Configuration> it = coreConfiguration.pluginConfigurations().iterator();
        while (it.hasNext()) {
            T t = (Configuration) it.next();
            if (cls.isAssignableFrom(t.getClass())) {
                return t;
            }
        }
        throw new IllegalArgumentException(cls.getName() + " is no registered configuration");
    }
}
