package de.psdev.licensesdialog.licenses;

import android.content.Context;
import de.psdev.licensesdialog.R;

public class SILOpenFontLicense11 extends License {
    public String getName() {
        return "SIL Open Font License v1.1";
    }

    public String getUrl() {
        return "https://opensource.org/licenses/OFL-1.1";
    }

    public String getVersion() {
        return "1.1";
    }

    public String readSummaryTextFromResources(Context context) {
        return getContent(context, R.raw.sil_ofl_11_summary);
    }

    public String readFullTextFromResources(Context context) {
        return getContent(context, R.raw.sil_ofl_11_full);
    }
}
