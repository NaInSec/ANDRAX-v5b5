package de.psdev.licensesdialog.licenses;

import android.content.Context;
import de.psdev.licensesdialog.R;

public class MITLicense extends License {
    private static final long serialVersionUID = 5673599951781482594L;

    public String getName() {
        return "MIT License";
    }

    public String getUrl() {
        return "https://opensource.org/licenses/MIT";
    }

    public String getVersion() {
        return "";
    }

    public String readSummaryTextFromResources(Context context) {
        return getContent(context, R.raw.mit_summary);
    }

    public String readFullTextFromResources(Context context) {
        return getContent(context, R.raw.mit_full);
    }
}
