package org.acra.file;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import org.acra.ACRAConstants;

public final class CrashReportFileNameParser {
    public boolean isSilent(String str) {
        return str.contains(ACRAConstants.SILENT_SUFFIX);
    }

    @Deprecated
    public boolean isApproved(String str) {
        return isSilent(str) || str.contains(ACRAConstants.APPROVED_SUFFIX);
    }

    public Calendar getTimestamp(String str) {
        String replace = str.replace(ACRAConstants.REPORTFILE_EXTENSION, "").replace(ACRAConstants.SILENT_SUFFIX, "");
        Calendar instance = Calendar.getInstance();
        try {
            instance.setTime(new SimpleDateFormat(ACRAConstants.DATE_TIME_FORMAT_STRING, Locale.ENGLISH).parse(replace));
        } catch (ParseException unused) {
        }
        return instance;
    }
}
