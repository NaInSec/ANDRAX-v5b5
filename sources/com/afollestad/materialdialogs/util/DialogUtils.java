package com.afollestad.materialdialogs.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

public class DialogUtils {
    public static int getDisabledColor(Context context) {
        return adjustAlpha(isColorDark(resolveColor(context, 16842806)) ? ViewCompat.MEASURED_STATE_MASK : -1, 0.3f);
    }

    public static int adjustAlpha(int i, float f) {
        return Color.argb(Math.round(((float) Color.alpha(i)) * f), Color.red(i), Color.green(i), Color.blue(i));
    }

    public static int resolveColor(Context context, int i) {
        return resolveColor(context, i, 0);
    }

    public static int resolveColor(Context context, int i, int i2) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        try {
            return obtainStyledAttributes.getColor(0, i2);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static ColorStateList resolveActionTextColorStateList(Context context, int i, ColorStateList colorStateList) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        try {
            TypedValue peekValue = obtainStyledAttributes.peekValue(0);
            if (peekValue == null) {
                return colorStateList;
            }
            if (peekValue.type < 28 || peekValue.type > 31) {
                ColorStateList colorStateList2 = obtainStyledAttributes.getColorStateList(0);
                if (colorStateList2 != null) {
                    obtainStyledAttributes.recycle();
                    return colorStateList2;
                }
                obtainStyledAttributes.recycle();
                return colorStateList;
            }
            ColorStateList actionTextStateList = getActionTextStateList(context, peekValue.data);
            obtainStyledAttributes.recycle();
            return actionTextStateList;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static ColorStateList getActionTextColorStateList(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        context.getResources().getValue(i, typedValue, true);
        if (typedValue.type >= 28 && typedValue.type <= 31) {
            return getActionTextStateList(context, typedValue.data);
        }
        if (Build.VERSION.SDK_INT <= 22) {
            return context.getResources().getColorStateList(i);
        }
        return context.getColorStateList(i);
    }

    public static int getColor(Context context, int i) {
        return ContextCompat.getColor(context, i);
    }

    public static String resolveString(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return (String) typedValue.string;
    }

    /* renamed from: com.afollestad.materialdialogs.util.DialogUtils$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$afollestad$materialdialogs$GravityEnum = new int[GravityEnum.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.afollestad.materialdialogs.GravityEnum[] r0 = com.afollestad.materialdialogs.GravityEnum.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$afollestad$materialdialogs$GravityEnum = r0
                int[] r0 = $SwitchMap$com$afollestad$materialdialogs$GravityEnum     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.afollestad.materialdialogs.GravityEnum r1 = com.afollestad.materialdialogs.GravityEnum.CENTER     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$afollestad$materialdialogs$GravityEnum     // Catch:{ NoSuchFieldError -> 0x001f }
                com.afollestad.materialdialogs.GravityEnum r1 = com.afollestad.materialdialogs.GravityEnum.END     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.afollestad.materialdialogs.util.DialogUtils.AnonymousClass2.<clinit>():void");
        }
    }

    private static int gravityEnumToAttrInt(GravityEnum gravityEnum) {
        int i = AnonymousClass2.$SwitchMap$com$afollestad$materialdialogs$GravityEnum[gravityEnum.ordinal()];
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                return 0;
            }
        }
        return i2;
    }

    public static GravityEnum resolveGravityEnum(Context context, int i, GravityEnum gravityEnum) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        try {
            int i2 = obtainStyledAttributes.getInt(0, gravityEnumToAttrInt(gravityEnum));
            if (i2 == 1) {
                GravityEnum gravityEnum2 = GravityEnum.CENTER;
                obtainStyledAttributes.recycle();
                return gravityEnum2;
            } else if (i2 != 2) {
                return GravityEnum.START;
            } else {
                GravityEnum gravityEnum3 = GravityEnum.END;
                obtainStyledAttributes.recycle();
                return gravityEnum3;
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static Drawable resolveDrawable(Context context, int i) {
        return resolveDrawable(context, i, (Drawable) null);
    }

    private static Drawable resolveDrawable(Context context, int i, Drawable drawable) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        try {
            Drawable drawable2 = obtainStyledAttributes.getDrawable(0);
            if (drawable2 == null && drawable != null) {
                drawable2 = drawable;
            }
            return drawable2;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static int resolveDimension(Context context, int i) {
        return resolveDimension(context, i, -1);
    }

    private static int resolveDimension(Context context, int i, int i2) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        try {
            return obtainStyledAttributes.getDimensionPixelSize(0, i2);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static boolean resolveBoolean(Context context, int i, boolean z) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        try {
            return obtainStyledAttributes.getBoolean(0, z);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static boolean resolveBoolean(Context context, int i) {
        return resolveBoolean(context, i, false);
    }

    public static boolean isColorDark(int i) {
        return 1.0d - ((((((double) Color.red(i)) * 0.299d) + (((double) Color.green(i)) * 0.587d)) + (((double) Color.blue(i)) * 0.114d)) / 255.0d) >= 0.5d;
    }

    public static void setBackgroundCompat(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT < 16) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }

    public static void showKeyboard(DialogInterface dialogInterface, final MaterialDialog.Builder builder) {
        final MaterialDialog materialDialog = (MaterialDialog) dialogInterface;
        if (materialDialog.getInputEditText() != null) {
            materialDialog.getInputEditText().post(new Runnable() {
                public void run() {
                    materialDialog.getInputEditText().requestFocus();
                    InputMethodManager inputMethodManager = (InputMethodManager) builder.getContext().getSystemService("input_method");
                    if (inputMethodManager != null) {
                        inputMethodManager.showSoftInput(materialDialog.getInputEditText(), 1);
                    }
                }
            });
        }
    }

    public static void hideKeyboard(DialogInterface dialogInterface, MaterialDialog.Builder builder) {
        InputMethodManager inputMethodManager;
        MaterialDialog materialDialog = (MaterialDialog) dialogInterface;
        if (materialDialog.getInputEditText() != null && (inputMethodManager = (InputMethodManager) builder.getContext().getSystemService("input_method")) != null) {
            View currentFocus = materialDialog.getCurrentFocus();
            IBinder iBinder = null;
            if (currentFocus != null) {
                iBinder = currentFocus.getWindowToken();
            } else if (materialDialog.getView() != null) {
                iBinder = materialDialog.getView().getWindowToken();
            }
            if (iBinder != null) {
                inputMethodManager.hideSoftInputFromWindow(iBinder, 0);
            }
        }
    }

    public static ColorStateList getActionTextStateList(Context context, int i) {
        int resolveColor = resolveColor(context, 16842806);
        if (i != 0) {
            resolveColor = i;
        }
        return new ColorStateList(new int[][]{new int[]{-16842910}, new int[0]}, new int[]{adjustAlpha(resolveColor, 0.4f), resolveColor});
    }

    public static int[] getColorArray(Context context, int i) {
        if (i == 0) {
            return null;
        }
        TypedArray obtainTypedArray = context.getResources().obtainTypedArray(i);
        int[] iArr = new int[obtainTypedArray.length()];
        for (int i2 = 0; i2 < obtainTypedArray.length(); i2++) {
            iArr[i2] = obtainTypedArray.getColor(i2, 0);
        }
        obtainTypedArray.recycle();
        return iArr;
    }

    public static <T> boolean isIn(T t, T[] tArr) {
        if (!(tArr == null || tArr.length == 0)) {
            for (T equals : tArr) {
                if (equals.equals(t)) {
                    return true;
                }
            }
        }
        return false;
    }
}
