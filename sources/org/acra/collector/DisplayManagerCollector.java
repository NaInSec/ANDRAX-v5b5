package org.acra.collector;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Display;
import com.thecrackertechnology.dragonterminal.component.colorscheme.NeoColorScheme;
import java.lang.reflect.Field;
import java.util.Arrays;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class DisplayManagerCollector extends BaseReportFieldCollector {
    public DisplayManagerCollector() {
        super(ReportField.DISPLAY, new ReportField[0]);
    }

    /* access modifiers changed from: package-private */
    public void collect(ReportField reportField, Context context, CoreConfiguration coreConfiguration, ReportBuilder reportBuilder, CrashReportData crashReportData) {
        JSONObject jSONObject = new JSONObject();
        for (Display display : DisplayManagerCompat.getInstance(context).getDisplays()) {
            try {
                jSONObject.put(String.valueOf(display.getDisplayId()), collectDisplayData(display));
            } catch (JSONException e) {
                ACRA.log.w(ACRA.LOG_TAG, "Failed to collect data for display " + display.getDisplayId(), e);
            }
        }
        crashReportData.put(ReportField.DISPLAY, jSONObject);
    }

    private JSONObject collectDisplayData(Display display) throws JSONException {
        display.getMetrics(new DisplayMetrics());
        JSONObject jSONObject = new JSONObject();
        collectCurrentSizeRange(display, jSONObject);
        collectFlags(display, jSONObject);
        collectMetrics(display, jSONObject);
        collectRealMetrics(display, jSONObject);
        collectName(display, jSONObject);
        collectRealSize(display, jSONObject);
        collectRectSize(display, jSONObject);
        collectSize(display, jSONObject);
        collectRotation(display, jSONObject);
        collectIsValid(display, jSONObject);
        jSONObject.put("orientation", display.getRotation()).put("refreshRate", (double) display.getRefreshRate());
        jSONObject.put("height", display.getHeight()).put("width", display.getWidth()).put("pixelFormat", display.getPixelFormat());
        return jSONObject;
    }

    private void collectIsValid(Display display, JSONObject jSONObject) throws JSONException {
        if (Build.VERSION.SDK_INT >= 17) {
            jSONObject.put("isValid", display.isValid());
        }
    }

    private void collectRotation(Display display, JSONObject jSONObject) throws JSONException {
        jSONObject.put("rotation", rotationToString(display.getRotation()));
    }

    private String rotationToString(int i) {
        if (i == 0) {
            return "ROTATION_0";
        }
        if (i == 1) {
            return "ROTATION_90";
        }
        if (i != 2) {
            return i != 3 ? String.valueOf(i) : "ROTATION_270";
        }
        return "ROTATION_180";
    }

    private void collectRectSize(Display display, JSONObject jSONObject) throws JSONException {
        Rect rect = new Rect();
        display.getRectSize(rect);
        jSONObject.put("rectSize", new JSONArray(Arrays.asList(new Integer[]{Integer.valueOf(rect.top), Integer.valueOf(rect.left), Integer.valueOf(rect.width()), Integer.valueOf(rect.height())})));
    }

    private void collectSize(Display display, JSONObject jSONObject) throws JSONException {
        Point point = new Point();
        display.getSize(point);
        jSONObject.put("size", new JSONArray(Arrays.asList(new Integer[]{Integer.valueOf(point.x), Integer.valueOf(point.y)})));
    }

    private void collectRealSize(Display display, JSONObject jSONObject) throws JSONException {
        if (Build.VERSION.SDK_INT >= 17) {
            Point point = new Point();
            display.getRealSize(point);
            jSONObject.put("realSize", new JSONArray(Arrays.asList(new Integer[]{Integer.valueOf(point.x), Integer.valueOf(point.y)})));
        }
    }

    private void collectCurrentSizeRange(Display display, JSONObject jSONObject) throws JSONException {
        if (Build.VERSION.SDK_INT >= 16) {
            Point point = new Point();
            Point point2 = new Point();
            display.getCurrentSizeRange(point, point2);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("smallest", new JSONArray(Arrays.asList(new Integer[]{Integer.valueOf(point.x), Integer.valueOf(point.y)})));
            jSONObject2.put("largest", new JSONArray(Arrays.asList(new Integer[]{Integer.valueOf(point2.x), Integer.valueOf(point2.y)})));
            jSONObject.put("currentSizeRange", jSONObject2);
        }
    }

    private void collectFlags(Display display, JSONObject jSONObject) throws JSONException {
        if (Build.VERSION.SDK_INT >= 17) {
            SparseArray sparseArray = new SparseArray();
            int flags = display.getFlags();
            for (Field field : display.getClass().getFields()) {
                if (field.getName().startsWith("FLAG_")) {
                    try {
                        sparseArray.put(field.getInt((Object) null), field.getName());
                    } catch (IllegalAccessException unused) {
                    }
                }
            }
            jSONObject.put("flags", activeFlags(sparseArray, flags));
        }
    }

    private void collectName(Display display, JSONObject jSONObject) throws JSONException {
        if (Build.VERSION.SDK_INT >= 17) {
            jSONObject.put(NeoColorScheme.COLOR_META_NAME, display.getName());
        }
    }

    private void collectMetrics(Display display, JSONObject jSONObject) throws JSONException {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        JSONObject jSONObject2 = new JSONObject();
        collectMetrics(displayMetrics, jSONObject2);
        jSONObject.put("metrics", jSONObject2);
    }

    private void collectRealMetrics(Display display, JSONObject jSONObject) throws JSONException {
        if (Build.VERSION.SDK_INT >= 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            display.getRealMetrics(displayMetrics);
            JSONObject jSONObject2 = new JSONObject();
            collectMetrics(displayMetrics, jSONObject2);
            jSONObject.put("realMetrics", jSONObject2);
        }
    }

    private void collectMetrics(DisplayMetrics displayMetrics, JSONObject jSONObject) throws JSONException {
        JSONObject put = jSONObject.put("density", (double) displayMetrics.density).put("densityDpi", displayMetrics.densityDpi);
        put.put("scaledDensity", "x" + displayMetrics.scaledDensity).put("widthPixels", displayMetrics.widthPixels).put("heightPixels", displayMetrics.heightPixels).put("xdpi", (double) displayMetrics.xdpi).put("ydpi", (double) displayMetrics.ydpi);
    }

    private String activeFlags(SparseArray<String> sparseArray, int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            int keyAt = sparseArray.keyAt(i2) & i;
            if (keyAt > 0) {
                if (sb.length() > 0) {
                    sb.append('+');
                }
                sb.append(sparseArray.get(keyAt));
            }
        }
        return sb.toString();
    }
}
