package de.psdev.licensesdialog.licenses;

import android.content.Context;
import de.psdev.licensesdialog.R;

public class ISCLicense extends License {
    private static final long serialVersionUID = -4636435634132169860L;

    public String getName() {
        return "ISC License";
    }

    public String getUrl() {
        return "https://opensource.org/licenses/isc-license.txt";
    }

    public String getVersion() {
        return "";
    }

    public String readSummaryTextFromResources(Context context) {
        return getContent(context, R.raw.isc_summary);
    }

    public String readFullTextFromResources(Context context) {
        return getContent(context, R.raw.isc_full);
    }
}
