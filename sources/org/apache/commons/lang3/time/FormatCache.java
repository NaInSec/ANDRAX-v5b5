package org.apache.commons.lang3.time;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.lang3.Validate;

abstract class FormatCache<F extends Format> {
    static final int NONE = -1;
    private static final ConcurrentMap<MultipartKey, String> cDateTimeInstanceCache = new ConcurrentHashMap(7);
    private final ConcurrentMap<MultipartKey, F> cInstanceCache = new ConcurrentHashMap(7);

    /* access modifiers changed from: protected */
    public abstract F createInstance(String str, TimeZone timeZone, Locale locale);

    FormatCache() {
    }

    public F getInstance() {
        return getDateTimeInstance(3, 3, TimeZone.getDefault(), Locale.getDefault());
    }

    public F getInstance(String str, TimeZone timeZone, Locale locale) {
        Validate.notNull(str, "pattern must not be null", new Object[0]);
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        MultipartKey multipartKey = new MultipartKey(str, timeZone, locale);
        F f = (Format) this.cInstanceCache.get(multipartKey);
        if (f != null) {
            return f;
        }
        F createInstance = createInstance(str, timeZone, locale);
        F f2 = (Format) this.cInstanceCache.putIfAbsent(multipartKey, createInstance);
        return f2 != null ? f2 : createInstance;
    }

    private F getDateTimeInstance(Integer num, Integer num2, TimeZone timeZone, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return getInstance(getPatternForStyle(num, num2, locale), timeZone, locale);
    }

    /* access modifiers changed from: package-private */
    public F getDateTimeInstance(int i, int i2, TimeZone timeZone, Locale locale) {
        return getDateTimeInstance(Integer.valueOf(i), Integer.valueOf(i2), timeZone, locale);
    }

    /* access modifiers changed from: package-private */
    public F getDateInstance(int i, TimeZone timeZone, Locale locale) {
        return getDateTimeInstance(Integer.valueOf(i), (Integer) null, timeZone, locale);
    }

    /* access modifiers changed from: package-private */
    public F getTimeInstance(int i, TimeZone timeZone, Locale locale) {
        return getDateTimeInstance((Integer) null, Integer.valueOf(i), timeZone, locale);
    }

    static String getPatternForStyle(Integer num, Integer num2, Locale locale) {
        DateFormat dateFormat;
        MultipartKey multipartKey = new MultipartKey(num, num2, locale);
        String str = (String) cDateTimeInstanceCache.get(multipartKey);
        if (str != null) {
            return str;
        }
        if (num == null) {
            try {
                dateFormat = DateFormat.getTimeInstance(num2.intValue(), locale);
            } catch (ClassCastException unused) {
                throw new IllegalArgumentException("No date time pattern for locale: " + locale);
            }
        } else if (num2 == null) {
            dateFormat = DateFormat.getDateInstance(num.intValue(), locale);
        } else {
            dateFormat = DateFormat.getDateTimeInstance(num.intValue(), num2.intValue(), locale);
        }
        String pattern = ((SimpleDateFormat) dateFormat).toPattern();
        String putIfAbsent = cDateTimeInstanceCache.putIfAbsent(multipartKey, pattern);
        return putIfAbsent != null ? putIfAbsent : pattern;
    }

    private static class MultipartKey {
        private int hashCode;
        private final Object[] keys;

        MultipartKey(Object... objArr) {
            this.keys = objArr;
        }

        public boolean equals(Object obj) {
            return Arrays.equals(this.keys, ((MultipartKey) obj).keys);
        }

        public int hashCode() {
            if (this.hashCode == 0) {
                int i = 0;
                for (Object obj : this.keys) {
                    if (obj != null) {
                        i = (i * 7) + obj.hashCode();
                    }
                }
                this.hashCode = i;
            }
            return this.hashCode;
        }
    }
}
