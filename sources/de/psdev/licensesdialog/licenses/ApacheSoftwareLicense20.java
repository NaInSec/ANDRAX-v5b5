package de.psdev.licensesdialog.licenses;

import android.content.Context;
import de.psdev.licensesdialog.R;

public class ApacheSoftwareLicense20 extends License {
    private static final long serialVersionUID = 4854000061990891449L;

    public String getName() {
        return "Apache Software License 2.0";
    }

    public String getUrl() {
        return "https://www.apache.org/licenses/LICENSE-2.0.txt";
    }

    public String getVersion() {
        return "2.0";
    }

    public String readSummaryTextFromResources(Context context) {
        return getContent(context, R.raw.asl_20_summary);
    }

    public String readFullTextFromResources(Context context) {
        return getContent(context, R.raw.asl_20_full);
    }
}
