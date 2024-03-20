package org.acra.interaction;

import org.acra.config.ConfigUtils;
import org.acra.config.Configuration;
import org.acra.config.CoreConfiguration;

public abstract class BaseReportInteraction implements ReportInteraction {
    private final Class<? extends Configuration> configClass;

    public BaseReportInteraction(Class<? extends Configuration> cls) {
        this.configClass = cls;
    }

    public final boolean enabled(CoreConfiguration coreConfiguration) {
        return ConfigUtils.getPluginConfiguration(coreConfiguration, this.configClass).enabled();
    }
}
