package de.psdev.licensesdialog.licenses;

import android.content.Context;
import de.psdev.licensesdialog.R;

public class BSD3ClauseLicense extends License {
    private static final long serialVersionUID = -5205394619884057474L;

    public String getName() {
        return "BSD 3-Clause License";
    }

    public String getUrl() {
        return "https://opensource.org/licenses/BSD-3-Clause";
    }

    public String getVersion() {
        return "";
    }

    public String readSummaryTextFromResources(Context context) {
        return getContent(context, R.raw.bsd3_summary);
    }

    public String readFullTextFromResources(Context context) {
        return getContent(context, R.raw.bsd3_full);
    }
}
