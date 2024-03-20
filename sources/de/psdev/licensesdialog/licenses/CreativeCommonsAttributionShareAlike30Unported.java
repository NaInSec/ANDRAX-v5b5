package de.psdev.licensesdialog.licenses;

import android.content.Context;
import de.psdev.licensesdialog.R;

public class CreativeCommonsAttributionShareAlike30Unported extends License {
    private static final long serialVersionUID = -1221518691431383957L;

    public String getName() {
        return "Creative Commons Attribution-Share Alike 3.0 Unported";
    }

    public String getUrl() {
        return "https://creativecommons.org/licenses/by-sa/3.0/";
    }

    public String getVersion() {
        return "3.0";
    }

    public String readSummaryTextFromResources(Context context) {
        return getContent(context, R.raw.ccbysa_30_summary);
    }

    public String readFullTextFromResources(Context context) {
        return getContent(context, R.raw.ccbysa_30_full);
    }
}
