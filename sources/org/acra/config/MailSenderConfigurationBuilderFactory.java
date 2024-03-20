package org.acra.config;

import android.content.Context;

public class MailSenderConfigurationBuilderFactory implements ConfigurationBuilderFactory {
    public ConfigurationBuilder create(Context context) {
        return new MailSenderConfigurationBuilderImpl(context);
    }
}
