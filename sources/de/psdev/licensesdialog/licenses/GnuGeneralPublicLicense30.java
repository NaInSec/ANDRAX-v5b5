package de.psdev.licensesdialog.licenses;

import android.content.Context;
import de.psdev.licensesdialog.R;

public class GnuGeneralPublicLicense30 extends License {
    public String getName() {
        return "GNU General Public License 3.0";
    }

    public String getUrl() {
        return "https://www.gnu.org/licenses/";
    }

    public String getVersion() {
        return "3.0";
    }

    public String readSummaryTextFromResources(Context context) {
        return getContent(context, R.raw.gpl_30_summary);
    }

    public String readFullTextFromResources(Context context) {
        return getContent(context, R.raw.gpl_30_full);
    }
}
