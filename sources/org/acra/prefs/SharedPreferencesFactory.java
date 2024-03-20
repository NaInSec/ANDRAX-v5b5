package org.acra.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import org.acra.ACRA;
import org.acra.config.CoreConfiguration;

public class SharedPreferencesFactory {
    private final CoreConfiguration config;
    private final Context context;

    public SharedPreferencesFactory(Context context2, CoreConfiguration coreConfiguration) {
        this.context = context2;
        this.config = coreConfiguration;
    }

    public static boolean shouldEnableACRA(SharedPreferences sharedPreferences) {
        boolean z = false;
        try {
            if (!sharedPreferences.getBoolean(ACRA.PREF_DISABLE_ACRA, false)) {
                z = true;
            }
            return sharedPreferences.getBoolean(ACRA.PREF_ENABLE_ACRA, z);
        } catch (Exception unused) {
            return true;
        }
    }

    public SharedPreferences create() {
        if (this.context == null) {
            throw new IllegalStateException("Cannot call ACRA.getACRASharedPreferences() before ACRA.init().");
        } else if (!"".equals(this.config.sharedPreferencesName())) {
            return this.context.getSharedPreferences(this.config.sharedPreferencesName(), 0);
        } else {
            return PreferenceManager.getDefaultSharedPreferences(this.context);
        }
    }
}
