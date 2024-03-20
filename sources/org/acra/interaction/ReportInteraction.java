package org.acra.interaction;

import android.content.Context;
import java.io.File;
import org.acra.config.CoreConfiguration;

public interface ReportInteraction {

    /* renamed from: org.acra.interaction.ReportInteraction$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static boolean $default$enabled(ReportInteraction reportInteraction, CoreConfiguration coreConfiguration) {
            return true;
        }
    }

    boolean enabled(CoreConfiguration coreConfiguration);

    boolean performInteraction(Context context, CoreConfiguration coreConfiguration, File file);
}
