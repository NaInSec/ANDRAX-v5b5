package de.psdev.licensesdialog.licenses;

import android.content.Context;
import de.psdev.licensesdialog.R;

public class EclipsePublicLicense10 extends License {
    public String getName() {
        return "Eclipse Public License 1.0";
    }

    public String getUrl() {
        return "https://www.eclipse.org/legal/epl-v10.html";
    }

    public String getVersion() {
        return "1.0";
    }

    public String readSummaryTextFromResources(Context context) {
        return getContent(context, R.raw.epl_v10_summary);
    }

    public String readFullTextFromResources(Context context) {
        return getContent(context, R.raw.epl_v10_full);
    }
}
