package de.psdev.licensesdialog.licenses;

import android.content.Context;
import de.psdev.licensesdialog.R;

public class GnuLesserGeneralPublicLicense3 extends License {
    public String getName() {
        return "GNU Lesser General Public License 3";
    }

    public String getUrl() {
        return "https://www.gnu.org/licenses/lgpl.html";
    }

    public String getVersion() {
        return "3";
    }

    public String readSummaryTextFromResources(Context context) {
        return getContent(context, R.raw.lgpl_3_summary);
    }

    public String readFullTextFromResources(Context context) {
        return getContent(context, R.raw.lgpl_3_full);
    }
}
