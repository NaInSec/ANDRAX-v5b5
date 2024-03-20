package org.acra.collector;

import android.content.Context;
import org.acra.config.CoreConfiguration;

public interface ApplicationStartupCollector extends Collector {
    void collectApplicationStartUp(Context context, CoreConfiguration coreConfiguration);
}
