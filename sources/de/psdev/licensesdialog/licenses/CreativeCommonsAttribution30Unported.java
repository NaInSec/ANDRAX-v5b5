package de.psdev.licensesdialog.licenses;

import android.content.Context;
import de.psdev.licensesdialog.R;

public class CreativeCommonsAttribution30Unported extends License {
    private static final long serialVersionUID = -8244155573094118433L;

    public String getName() {
        return "Creative Commons Attribution 3.0 Unported";
    }

    public String getUrl() {
        return "https://creativecommons.org/licenses/by/3.0/";
    }

    public String getVersion() {
        return "3.0";
    }

    public String readSummaryTextFromResources(Context context) {
        return getContent(context, R.raw.ccby_30_summary);
    }

    public String readFullTextFromResources(Context context) {
        return getContent(context, R.raw.ccby_30_full);
    }
}
