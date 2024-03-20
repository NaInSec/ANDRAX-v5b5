package org.apache.commons.lang3.time;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateFormatUtils {
    public static final FastDateFormat ISO_8601_EXTENDED_DATETIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss");
    public static final FastDateFormat ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ssZZ");
    public static final FastDateFormat ISO_8601_EXTENDED_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
    public static final FastDateFormat ISO_8601_EXTENDED_TIME_FORMAT = FastDateFormat.getInstance("HH:mm:ss");
    public static final FastDateFormat ISO_8601_EXTENDED_TIME_TIME_ZONE_FORMAT = FastDateFormat.getInstance("HH:mm:ssZZ");
    @Deprecated
    public static final FastDateFormat ISO_DATETIME_FORMAT = ISO_8601_EXTENDED_DATETIME_FORMAT;
    @Deprecated
    public static final FastDateFormat ISO_DATETIME_TIME_ZONE_FORMAT = ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT;
    @Deprecated
    public static final FastDateFormat ISO_DATE_FORMAT = ISO_8601_EXTENDED_DATE_FORMAT;
    @Deprecated
    public static final FastDateFormat ISO_DATE_TIME_ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-ddZZ");
    @Deprecated
    public static final FastDateFormat ISO_TIME_FORMAT = FastDateFormat.getInstance("'T'HH:mm:ss");
    @Deprecated
    public static final FastDateFormat ISO_TIME_NO_T_FORMAT = ISO_8601_EXTENDED_TIME_FORMAT;
    @Deprecated
    public static final FastDateFormat ISO_TIME_NO_T_TIME_ZONE_FORMAT = ISO_8601_EXTENDED_TIME_TIME_ZONE_FORMAT;
    @Deprecated
    public static final FastDateFormat ISO_TIME_TIME_ZONE_FORMAT = FastDateFormat.getInstance("'T'HH:mm:ssZZ");
    public static final FastDateFormat SMTP_DATETIME_FORMAT = FastDateFormat.getInstance("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
    private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("GMT");

    public static String formatUTC(long j, String str) {
        return format(new Date(j), str, UTC_TIME_ZONE, (Locale) null);
    }

    public static String formatUTC(Date date, String str) {
        return format(date, str, UTC_TIME_ZONE, (Locale) null);
    }

    public static String formatUTC(long j, String str, Locale locale) {
        return format(new Date(j), str, UTC_TIME_ZONE, locale);
    }

    public static String formatUTC(Date date, String str, Locale locale) {
        return format(date, str, UTC_TIME_ZONE, locale);
    }

    public static String format(long j, String str) {
        return format(new Date(j), str, (TimeZone) null, (Locale) null);
    }

    public static String format(Date date, String str) {
        return format(date, str, (TimeZone) null, (Locale) null);
    }

    public static String format(Calendar calendar, String str) {
        return format(calendar, str, (TimeZone) null, (Locale) null);
    }

    public static String format(long j, String str, TimeZone timeZone) {
        return format(new Date(j), str, timeZone, (Locale) null);
    }

    public static String format(Date date, String str, TimeZone timeZone) {
        return format(date, str, timeZone, (Locale) null);
    }

    public static String format(Calendar calendar, String str, TimeZone timeZone) {
        return format(calendar, str, timeZone, (Locale) null);
    }

    public static String format(long j, String str, Locale locale) {
        return format(new Date(j), str, (TimeZone) null, locale);
    }

    public static String format(Date date, String str, Locale locale) {
        return format(date, str, (TimeZone) null, locale);
    }

    public static String format(Calendar calendar, String str, Locale locale) {
        return format(calendar, str, (TimeZone) null, locale);
    }

    public static String format(long j, String str, TimeZone timeZone, Locale locale) {
        return format(new Date(j), str, timeZone, locale);
    }

    public static String format(Date date, String str, TimeZone timeZone, Locale locale) {
        return FastDateFormat.getInstance(str, timeZone, locale).format(date);
    }

    public static String format(Calendar calendar, String str, TimeZone timeZone, Locale locale) {
        return FastDateFormat.getInstance(str, timeZone, locale).format(calendar);
    }
}
