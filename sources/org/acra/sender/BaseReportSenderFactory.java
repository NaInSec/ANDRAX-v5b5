package org.acra.sender;

import org.acra.config.ConfigUtils;
import org.acra.config.Configuration;
import org.acra.config.CoreConfiguration;

public abstract class BaseReportSenderFactory implements ReportSenderFactory {
    private final Class<? extends Configuration> configClass;

    public BaseReportSenderFactory(Class<? extends Configuration> cls) {
        this.configClass = cls;
    }

    public final boolean enabled(CoreConfiguration coreConfiguration) {
        return ConfigUtils.getPluginConfiguration(coreConfiguration, this.configClass).enabled();
    }
}
