package de.psdev.licensesdialog.licenses;

import android.content.Context;
import de.psdev.licensesdialog.R;

public class BSD2ClauseLicense extends License {
    private static final long serialVersionUID = -5205394619884027401L;

    public String getName() {
        return "BSD 2-Clause License";
    }

    public String getUrl() {
        return "https://opensource.org/licenses/BSD-2-Clause";
    }

    public String getVersion() {
        return "";
    }

    public String readSummaryTextFromResources(Context context) {
        return getContent(context, R.raw.bsd2_summary);
    }

    public String readFullTextFromResources(Context context) {
        return getContent(context, R.raw.bsd2_full);
    }
}
