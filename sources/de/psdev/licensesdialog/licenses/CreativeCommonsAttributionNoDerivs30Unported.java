package de.psdev.licensesdialog.licenses;

import android.content.Context;
import de.psdev.licensesdialog.R;

public class CreativeCommonsAttributionNoDerivs30Unported extends License {
    public String getName() {
        return "Creative Commons Attribution-NoDerivs 3.0 Unported";
    }

    public String getUrl() {
        return "https://creativecommons.org/licenses/by-nd/3.0/";
    }

    public String getVersion() {
        return "3.0";
    }

    public String readSummaryTextFromResources(Context context) {
        return getContent(context, R.raw.ccand_30_summary);
    }

    public String readFullTextFromResources(Context context) {
        return getContent(context, R.raw.ccand_30_full);
    }
}
