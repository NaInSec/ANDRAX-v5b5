package de.psdev.licensesdialog.licenses;

import android.content.Context;
import de.psdev.licensesdialog.R;

public class MozillaPublicLicense11 extends License {
    private static final long serialVersionUID = -5912500033007492703L;

    public String getName() {
        return "Mozilla Public License 1.1";
    }

    public String getUrl() {
        return "https://mozilla.org/MPL/1.1/";
    }

    public String getVersion() {
        return "1.1";
    }

    public String readSummaryTextFromResources(Context context) {
        return getContent(context, R.raw.mpl_11_summary);
    }

    public String readFullTextFromResources(Context context) {
        return getContent(context, R.raw.mpl_11_full);
    }
}
