package org.acra.config;

import android.content.Context;

public class DialogConfigurationBuilderFactory implements ConfigurationBuilderFactory {
    public ConfigurationBuilder create(Context context) {
        return new DialogConfigurationBuilderImpl(context);
    }
}
