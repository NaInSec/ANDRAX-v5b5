package de.mrapp.android.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

public final class ThemeUtil {
    private static TypedArray obtainStyledAttributes(Context context, int i, int i2) {
        Condition.ensureNotNull(context, "The context may not be null");
        Resources.Theme theme = context.getTheme();
        int[] iArr = {i2};
        if (i != -1) {
            return theme.obtainStyledAttributes(i, iArr);
        }
        return theme.obtainStyledAttributes(iArr);
    }

    private ThemeUtil() {
    }

    public static int getColor(Context context, int i) {
        return getColor(context, -1, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getColor(android.content.Context r3, int r4, int r5) {
        /*
            android.content.res.TypedArray r4 = obtainStyledAttributes(r3, r4, r5)     // Catch:{ all -> 0x003f }
            r0 = 0
            r1 = -1
            int r2 = r4.getColor(r0, r1)     // Catch:{ all -> 0x003d }
            if (r2 != r1) goto L_0x0037
            int r0 = r4.getResourceId(r0, r1)     // Catch:{ all -> 0x003d }
            int r2 = android.support.v4.content.ContextCompat.getColor(r3, r0)     // Catch:{ all -> 0x003d }
            if (r2 == 0) goto L_0x0017
            goto L_0x0037
        L_0x0017:
            android.content.res.Resources$NotFoundException r3 = new android.content.res.Resources$NotFoundException     // Catch:{ all -> 0x003d }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x003d }
            r0.<init>()     // Catch:{ all -> 0x003d }
            java.lang.String r1 = "Resource ID #0x"
            r0.append(r1)     // Catch:{ all -> 0x003d }
            java.lang.String r5 = java.lang.Integer.toHexString(r5)     // Catch:{ all -> 0x003d }
            r0.append(r5)     // Catch:{ all -> 0x003d }
            java.lang.String r5 = " is not valid"
            r0.append(r5)     // Catch:{ all -> 0x003d }
            java.lang.String r5 = r0.toString()     // Catch:{ all -> 0x003d }
            r3.<init>(r5)     // Catch:{ all -> 0x003d }
            throw r3     // Catch:{ all -> 0x003d }
        L_0x0037:
            if (r4 == 0) goto L_0x003c
            r4.recycle()
        L_0x003c:
            return r2
        L_0x003d:
            r3 = move-exception
            goto L_0x0041
        L_0x003f:
            r3 = move-exception
            r4 = 0
        L_0x0041:
            if (r4 == 0) goto L_0x0046
            r4.recycle()
        L_0x0046:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: de.mrapp.android.util.ThemeUtil.getColor(android.content.Context, int, int):int");
    }

    public static ColorStateList getColorStateList(Context context, int i) {
        return getColorStateList(context, -1, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.res.ColorStateList getColorStateList(android.content.Context r2, int r3, int r4) {
        /*
            android.content.res.TypedArray r2 = obtainStyledAttributes(r2, r3, r4)     // Catch:{ all -> 0x0033 }
            r3 = 0
            android.content.res.ColorStateList r3 = r2.getColorStateList(r3)     // Catch:{ all -> 0x0031 }
            if (r3 == 0) goto L_0x0011
            if (r2 == 0) goto L_0x0010
            r2.recycle()
        L_0x0010:
            return r3
        L_0x0011:
            android.content.res.Resources$NotFoundException r3 = new android.content.res.Resources$NotFoundException     // Catch:{ all -> 0x0031 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0031 }
            r0.<init>()     // Catch:{ all -> 0x0031 }
            java.lang.String r1 = "Resource ID #0x"
            r0.append(r1)     // Catch:{ all -> 0x0031 }
            java.lang.String r4 = java.lang.Integer.toHexString(r4)     // Catch:{ all -> 0x0031 }
            r0.append(r4)     // Catch:{ all -> 0x0031 }
            java.lang.String r4 = " is not valid"
            r0.append(r4)     // Catch:{ all -> 0x0031 }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x0031 }
            r3.<init>(r4)     // Catch:{ all -> 0x0031 }
            throw r3     // Catch:{ all -> 0x0031 }
        L_0x0031:
            r3 = move-exception
            goto L_0x0035
        L_0x0033:
            r3 = move-exception
            r2 = 0
        L_0x0035:
            if (r2 == 0) goto L_0x003a
            r2.recycle()
        L_0x003a:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: de.mrapp.android.util.ThemeUtil.getColorStateList(android.content.Context, int, int):android.content.res.ColorStateList");
    }

    public static String getString(Context context, int i) {
        return getString(context, -1, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getString(android.content.Context r2, int r3, int r4) {
        /*
            java.lang.String r0 = "The context may not be null"
            de.mrapp.android.util.Condition.ensureNotNull(r2, r0)
            android.content.res.TypedArray r2 = obtainStyledAttributes(r2, r3, r4)     // Catch:{ all -> 0x0038 }
            r3 = 0
            java.lang.String r3 = r2.getString(r3)     // Catch:{ all -> 0x0036 }
            if (r3 == 0) goto L_0x0016
            if (r2 == 0) goto L_0x0015
            r2.recycle()
        L_0x0015:
            return r3
        L_0x0016:
            android.content.res.Resources$NotFoundException r3 = new android.content.res.Resources$NotFoundException     // Catch:{ all -> 0x0036 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0036 }
            r0.<init>()     // Catch:{ all -> 0x0036 }
            java.lang.String r1 = "Resource ID #0x"
            r0.append(r1)     // Catch:{ all -> 0x0036 }
            java.lang.String r4 = java.lang.Integer.toHexString(r4)     // Catch:{ all -> 0x0036 }
            r0.append(r4)     // Catch:{ all -> 0x0036 }
            java.lang.String r4 = " is not valid"
            r0.append(r4)     // Catch:{ all -> 0x0036 }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x0036 }
            r3.<init>(r4)     // Catch:{ all -> 0x0036 }
            throw r3     // Catch:{ all -> 0x0036 }
        L_0x0036:
            r3 = move-exception
            goto L_0x003a
        L_0x0038:
            r3 = move-exception
            r2 = 0
        L_0x003a:
            if (r2 == 0) goto L_0x003f
            r2.recycle()
        L_0x003f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: de.mrapp.android.util.ThemeUtil.getString(android.content.Context, int, int):java.lang.String");
    }

    public static CharSequence getText(Context context, int i) {
        return getText(context, -1, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.CharSequence getText(android.content.Context r2, int r3, int r4) {
        /*
            android.content.res.TypedArray r2 = obtainStyledAttributes(r2, r3, r4)     // Catch:{ all -> 0x0033 }
            r3 = 0
            java.lang.CharSequence r3 = r2.getText(r3)     // Catch:{ all -> 0x0031 }
            if (r3 == 0) goto L_0x0011
            if (r2 == 0) goto L_0x0010
            r2.recycle()
        L_0x0010:
            return r3
        L_0x0011:
            android.content.res.Resources$NotFoundException r3 = new android.content.res.Resources$NotFoundException     // Catch:{ all -> 0x0031 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0031 }
            r0.<init>()     // Catch:{ all -> 0x0031 }
            java.lang.String r1 = "Resource ID #0x"
            r0.append(r1)     // Catch:{ all -> 0x0031 }
            java.lang.String r4 = java.lang.Integer.toHexString(r4)     // Catch:{ all -> 0x0031 }
            r0.append(r4)     // Catch:{ all -> 0x0031 }
            java.lang.String r4 = " is not valid"
            r0.append(r4)     // Catch:{ all -> 0x0031 }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x0031 }
            r3.<init>(r4)     // Catch:{ all -> 0x0031 }
            throw r3     // Catch:{ all -> 0x0031 }
        L_0x0031:
            r3 = move-exception
            goto L_0x0035
        L_0x0033:
            r3 = move-exception
            r2 = 0
        L_0x0035:
            if (r2 == 0) goto L_0x003a
            r2.recycle()
        L_0x003a:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: de.mrapp.android.util.ThemeUtil.getText(android.content.Context, int, int):java.lang.CharSequence");
    }

    public static CharSequence[] getTextArray(Context context, int i) {
        return getTextArray(context, -1, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.CharSequence[] getTextArray(android.content.Context r2, int r3, int r4) {
        /*
            android.content.res.TypedArray r2 = obtainStyledAttributes(r2, r3, r4)     // Catch:{ all -> 0x0033 }
            r3 = 0
            java.lang.CharSequence[] r3 = r2.getTextArray(r3)     // Catch:{ all -> 0x0031 }
            if (r3 == 0) goto L_0x0011
            if (r2 == 0) goto L_0x0010
            r2.recycle()
        L_0x0010:
            return r3
        L_0x0011:
            android.content.res.Resources$NotFoundException r3 = new android.content.res.Resources$NotFoundException     // Catch:{ all -> 0x0031 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0031 }
            r0.<init>()     // Catch:{ all -> 0x0031 }
            java.lang.String r1 = "Resource ID #0x"
            r0.append(r1)     // Catch:{ all -> 0x0031 }
            java.lang.String r4 = java.lang.Integer.toHexString(r4)     // Catch:{ all -> 0x0031 }
            r0.append(r4)     // Catch:{ all -> 0x0031 }
            java.lang.String r4 = " is not valid"
            r0.append(r4)     // Catch:{ all -> 0x0031 }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x0031 }
            r3.<init>(r4)     // Catch:{ all -> 0x0031 }
            throw r3     // Catch:{ all -> 0x0031 }
        L_0x0031:
            r3 = move-exception
            goto L_0x0035
        L_0x0033:
            r3 = move-exception
            r2 = 0
        L_0x0035:
            if (r2 == 0) goto L_0x003a
            r2.recycle()
        L_0x003a:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: de.mrapp.android.util.ThemeUtil.getTextArray(android.content.Context, int, int):java.lang.CharSequence[]");
    }

    public static float getDimension(Context context, int i) {
        return getDimension(context, -1, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static float getDimension(android.content.Context r2, int r3, int r4) {
        /*
            android.content.res.TypedArray r2 = obtainStyledAttributes(r2, r3, r4)     // Catch:{ all -> 0x0037 }
            r3 = 0
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r3 = r2.getDimension(r3, r0)     // Catch:{ all -> 0x0035 }
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 == 0) goto L_0x0015
            if (r2 == 0) goto L_0x0014
            r2.recycle()
        L_0x0014:
            return r3
        L_0x0015:
            android.content.res.Resources$NotFoundException r3 = new android.content.res.Resources$NotFoundException     // Catch:{ all -> 0x0035 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0035 }
            r0.<init>()     // Catch:{ all -> 0x0035 }
            java.lang.String r1 = "Resource ID #0x"
            r0.append(r1)     // Catch:{ all -> 0x0035 }
            java.lang.String r4 = java.lang.Integer.toHexString(r4)     // Catch:{ all -> 0x0035 }
            r0.append(r4)     // Catch:{ all -> 0x0035 }
            java.lang.String r4 = " is not valid"
            r0.append(r4)     // Catch:{ all -> 0x0035 }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x0035 }
            r3.<init>(r4)     // Catch:{ all -> 0x0035 }
            throw r3     // Catch:{ all -> 0x0035 }
        L_0x0035:
            r3 = move-exception
            goto L_0x0039
        L_0x0037:
            r3 = move-exception
            r2 = 0
        L_0x0039:
            if (r2 == 0) goto L_0x003e
            r2.recycle()
        L_0x003e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: de.mrapp.android.util.ThemeUtil.getDimension(android.content.Context, int, int):float");
    }

    public static int getDimensionPixelSize(Context context, int i) {
        return getDimensionPixelSize(context, -1, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getDimensionPixelSize(android.content.Context r2, int r3, int r4) {
        /*
            android.content.res.TypedArray r2 = obtainStyledAttributes(r2, r3, r4)     // Catch:{ all -> 0x0034 }
            r3 = 0
            r0 = -1
            int r3 = r2.getDimensionPixelSize(r3, r0)     // Catch:{ all -> 0x0032 }
            if (r3 == r0) goto L_0x0012
            if (r2 == 0) goto L_0x0011
            r2.recycle()
        L_0x0011:
            return r3
        L_0x0012:
            android.content.res.Resources$NotFoundException r3 = new android.content.res.Resources$NotFoundException     // Catch:{ all -> 0x0032 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0032 }
            r0.<init>()     // Catch:{ all -> 0x0032 }
            java.lang.String r1 = "Resource ID #0x"
            r0.append(r1)     // Catch:{ all -> 0x0032 }
            java.lang.String r4 = java.lang.Integer.toHexString(r4)     // Catch:{ all -> 0x0032 }
            r0.append(r4)     // Catch:{ all -> 0x0032 }
            java.lang.String r4 = " is not valid"
            r0.append(r4)     // Catch:{ all -> 0x0032 }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x0032 }
            r3.<init>(r4)     // Catch:{ all -> 0x0032 }
            throw r3     // Catch:{ all -> 0x0032 }
        L_0x0032:
            r3 = move-exception
            goto L_0x0036
        L_0x0034:
            r3 = move-exception
            r2 = 0
        L_0x0036:
            if (r2 == 0) goto L_0x003b
            r2.recycle()
        L_0x003b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: de.mrapp.android.util.ThemeUtil.getDimensionPixelSize(android.content.Context, int, int):int");
    }

    public static int getDimensionPixelOffset(Context context, int i) {
        return getDimensionPixelOffset(context, -1, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getDimensionPixelOffset(android.content.Context r2, int r3, int r4) {
        /*
            android.content.res.TypedArray r2 = obtainStyledAttributes(r2, r3, r4)     // Catch:{ all -> 0x0034 }
            r3 = 0
            r0 = -1
            int r3 = r2.getDimensionPixelOffset(r3, r0)     // Catch:{ all -> 0x0032 }
            if (r3 == r0) goto L_0x0012
            if (r2 == 0) goto L_0x0011
            r2.recycle()
        L_0x0011:
            return r3
        L_0x0012:
            android.content.res.Resources$NotFoundException r3 = new android.content.res.Resources$NotFoundException     // Catch:{ all -> 0x0032 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0032 }
            r0.<init>()     // Catch:{ all -> 0x0032 }
            java.lang.String r1 = "Resource ID #0x"
            r0.append(r1)     // Catch:{ all -> 0x0032 }
            java.lang.String r4 = java.lang.Integer.toHexString(r4)     // Catch:{ all -> 0x0032 }
            r0.append(r4)     // Catch:{ all -> 0x0032 }
            java.lang.String r4 = " is not valid"
            r0.append(r4)     // Catch:{ all -> 0x0032 }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x0032 }
            r3.<init>(r4)     // Catch:{ all -> 0x0032 }
            throw r3     // Catch:{ all -> 0x0032 }
        L_0x0032:
            r3 = move-exception
            goto L_0x0036
        L_0x0034:
            r3 = move-exception
            r2 = 0
        L_0x0036:
            if (r2 == 0) goto L_0x003b
            r2.recycle()
        L_0x003b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: de.mrapp.android.util.ThemeUtil.getDimensionPixelOffset(android.content.Context, int, int):int");
    }

    public static Drawable getDrawable(Context context, int i) {
        return getDrawable(context, -1, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.drawable.Drawable getDrawable(android.content.Context r2, int r3, int r4) {
        /*
            android.content.res.TypedArray r2 = obtainStyledAttributes(r2, r3, r4)     // Catch:{ all -> 0x0033 }
            r3 = 0
            android.graphics.drawable.Drawable r3 = r2.getDrawable(r3)     // Catch:{ all -> 0x0031 }
            if (r3 == 0) goto L_0x0011
            if (r2 == 0) goto L_0x0010
            r2.recycle()
        L_0x0010:
            return r3
        L_0x0011:
            android.content.res.Resources$NotFoundException r3 = new android.content.res.Resources$NotFoundException     // Catch:{ all -> 0x0031 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0031 }
            r0.<init>()     // Catch:{ all -> 0x0031 }
            java.lang.String r1 = "Resource ID #0x"
            r0.append(r1)     // Catch:{ all -> 0x0031 }
            java.lang.String r4 = java.lang.Integer.toHexString(r4)     // Catch:{ all -> 0x0031 }
            r0.append(r4)     // Catch:{ all -> 0x0031 }
            java.lang.String r4 = " is not valid"
            r0.append(r4)     // Catch:{ all -> 0x0031 }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x0031 }
            r3.<init>(r4)     // Catch:{ all -> 0x0031 }
            throw r3     // Catch:{ all -> 0x0031 }
        L_0x0031:
            r3 = move-exception
            goto L_0x0035
        L_0x0033:
            r3 = move-exception
            r2 = 0
        L_0x0035:
            if (r2 == 0) goto L_0x003a
            r2.recycle()
        L_0x003a:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: de.mrapp.android.util.ThemeUtil.getDrawable(android.content.Context, int, int):android.graphics.drawable.Drawable");
    }

    public static float getFraction(Context context, int i, int i2, int i3) {
        return getFraction(context, -1, i, i2, i3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static float getFraction(android.content.Context r1, int r2, int r3, int r4, int r5) {
        /*
            android.content.res.TypedArray r1 = obtainStyledAttributes(r1, r2, r3)     // Catch:{ all -> 0x0037 }
            r2 = 0
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r2 = r1.getFraction(r2, r4, r5, r0)     // Catch:{ all -> 0x0035 }
            int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r4 == 0) goto L_0x0015
            if (r1 == 0) goto L_0x0014
            r1.recycle()
        L_0x0014:
            return r2
        L_0x0015:
            android.content.res.Resources$NotFoundException r2 = new android.content.res.Resources$NotFoundException     // Catch:{ all -> 0x0035 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0035 }
            r4.<init>()     // Catch:{ all -> 0x0035 }
            java.lang.String r5 = "Resource ID #0x"
            r4.append(r5)     // Catch:{ all -> 0x0035 }
            java.lang.String r3 = java.lang.Integer.toHexString(r3)     // Catch:{ all -> 0x0035 }
            r4.append(r3)     // Catch:{ all -> 0x0035 }
            java.lang.String r3 = " is not valid"
            r4.append(r3)     // Catch:{ all -> 0x0035 }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x0035 }
            r2.<init>(r3)     // Catch:{ all -> 0x0035 }
            throw r2     // Catch:{ all -> 0x0035 }
        L_0x0035:
            r2 = move-exception
            goto L_0x0039
        L_0x0037:
            r2 = move-exception
            r1 = 0
        L_0x0039:
            if (r1 == 0) goto L_0x003e
            r1.recycle()
        L_0x003e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: de.mrapp.android.util.ThemeUtil.getFraction(android.content.Context, int, int, int, int):float");
    }

    public static boolean getBoolean(Context context, int i) {
        return getBoolean(context, -1, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0015  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean getBoolean(android.content.Context r0, int r1, int r2) {
        /*
            android.content.res.TypedArray r0 = obtainStyledAttributes(r0, r1, r2)     // Catch:{ all -> 0x0011 }
            r1 = 0
            boolean r1 = r0.getBoolean(r1, r1)     // Catch:{ all -> 0x000f }
            if (r0 == 0) goto L_0x000e
            r0.recycle()
        L_0x000e:
            return r1
        L_0x000f:
            r1 = move-exception
            goto L_0x0013
        L_0x0011:
            r1 = move-exception
            r0 = 0
        L_0x0013:
            if (r0 == 0) goto L_0x0018
            r0.recycle()
        L_0x0018:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: de.mrapp.android.util.ThemeUtil.getBoolean(android.content.Context, int, int):boolean");
    }

    public static int getInteger(Context context, int i) {
        return getInteger(context, -1, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0015  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getInteger(android.content.Context r0, int r1, int r2) {
        /*
            android.content.res.TypedArray r0 = obtainStyledAttributes(r0, r1, r2)     // Catch:{ all -> 0x0011 }
            r1 = 0
            int r1 = r0.getInteger(r1, r1)     // Catch:{ all -> 0x000f }
            if (r0 == 0) goto L_0x000e
            r0.recycle()
        L_0x000e:
            return r1
        L_0x000f:
            r1 = move-exception
            goto L_0x0013
        L_0x0011:
            r1 = move-exception
            r0 = 0
        L_0x0013:
            if (r0 == 0) goto L_0x0018
            r0.recycle()
        L_0x0018:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: de.mrapp.android.util.ThemeUtil.getInteger(android.content.Context, int, int):int");
    }

    public static float getFloat(Context context, int i) {
        return getFloat(context, -1, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0016  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static float getFloat(android.content.Context r0, int r1, int r2) {
        /*
            android.content.res.TypedArray r0 = obtainStyledAttributes(r0, r1, r2)     // Catch:{ all -> 0x0012 }
            r1 = 0
            r2 = 0
            float r1 = r0.getFloat(r1, r2)     // Catch:{ all -> 0x0010 }
            if (r0 == 0) goto L_0x000f
            r0.recycle()
        L_0x000f:
            return r1
        L_0x0010:
            r1 = move-exception
            goto L_0x0014
        L_0x0012:
            r1 = move-exception
            r0 = 0
        L_0x0014:
            if (r0 == 0) goto L_0x0019
            r0.recycle()
        L_0x0019:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: de.mrapp.android.util.ThemeUtil.getFloat(android.content.Context, int, int):float");
    }
}
