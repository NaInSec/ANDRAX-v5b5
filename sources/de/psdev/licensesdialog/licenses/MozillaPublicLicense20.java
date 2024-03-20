package de.psdev.licensesdialog.licenses;

import android.content.Context;
import de.psdev.licensesdialog.R;

public class MozillaPublicLicense20 extends License {
    public String getName() {
        return "Mozilla Public License 2.0";
    }

    public String getUrl() {
        return "https://mozilla.org/MPL/2.0/";
    }

    public String getVersion() {
        return "2.0";
    }

    public String readSummaryTextFromResources(Context context) {
        return getContent(context, R.raw.mpl_20_summary);
    }

    public String readFullTextFromResources(Context context) {
        return getContent(context, R.raw.mpl_20_full);
    }
}
