package de.psdev.licensesdialog.licenses;

import android.content.Context;
import de.psdev.licensesdialog.R;

public class GnuLesserGeneralPublicLicense21 extends License {
    public String getName() {
        return "GNU Lesser General Public License 2.1";
    }

    public String getUrl() {
        return "https://www.gnu.org/licenses/old-licenses/lgpl-2.1.html";
    }

    public String getVersion() {
        return "2.1";
    }

    public String readSummaryTextFromResources(Context context) {
        return getContent(context, R.raw.lgpl_21_summary);
    }

    public String readFullTextFromResources(Context context) {
        return getContent(context, R.raw.lgpl_21_full);
    }
}
