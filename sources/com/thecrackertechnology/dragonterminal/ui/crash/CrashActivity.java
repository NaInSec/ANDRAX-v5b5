package com.thecrackertechnology.dragonterminal.ui.crash;

import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.thecrackertechnology.andrax.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0002J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014¨\u0006\f"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/crash/CrashActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "collectAppInfo", "", "collectExceptionInfo", "collectModelInfo", "determineArchName", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: CrashActivity.kt */
public final class CrashActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.ui_crash);
        setSupportActionBar((Toolbar) findViewById(R.id.crash_toolbar));
        View findViewById = findViewById(R.id.crash_model);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "(findViewById<TextView>(R.id.crash_model))");
        ((TextView) findViewById).setText(getString(R.string.crash_model, new Object[]{collectModelInfo()}));
        View findViewById2 = findViewById(R.id.crash_app_version);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "(findViewById<TextView>(R.id.crash_app_version))");
        ((TextView) findViewById2).setText(getString(R.string.crash_app, new Object[]{collectAppInfo()}));
        View findViewById3 = findViewById(R.id.crash_details);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "(findViewById<TextView>(R.id.crash_details))");
        ((TextView) findViewById3).setText(collectExceptionInfo());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0048, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0049, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004c, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String collectExceptionInfo() {
        /*
            r4 = this;
            android.content.Intent r0 = r4.getIntent()
            java.lang.String r1 = "exception"
            java.io.Serializable r0 = r0.getSerializableExtra(r1)
            if (r0 == 0) goto L_0x004d
            boolean r1 = r0 instanceof java.lang.Throwable
            if (r1 == 0) goto L_0x004d
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            java.io.PrintStream r2 = new java.io.PrintStream
            r3 = r1
            java.io.OutputStream r3 = (java.io.OutputStream) r3
            r2.<init>(r3)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            java.lang.Throwable r3 = r0.getCause()
            if (r3 == 0) goto L_0x0026
            r0 = r3
        L_0x0026:
            r0.printStackTrace(r2)
            r0 = r1
            java.io.Closeable r0 = (java.io.Closeable) r0
            r2 = 0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r3 = r0
            java.io.ByteArrayOutputStream r3 = (java.io.ByteArrayOutputStream) r3     // Catch:{ all -> 0x0046 }
            java.lang.String r3 = "utf-8"
            java.lang.String r1 = r1.toString(r3)     // Catch:{ all -> 0x0046 }
            java.lang.String r3 = "byteArrayOutput.toString(\"utf-8\")"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r3)     // Catch:{ all -> 0x0046 }
            kotlin.io.CloseableKt.closeFinally(r0, r2)
            java.lang.String r0 = "byteArrayOutput.use {\n  …ng(\"utf-8\")\n            }"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r0)
            return r1
        L_0x0046:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            throw r2
        L_0x004d:
            java.lang.String r0 = "are.you.kidding.me.NoExceptionFoundException: This is a bug, please contact developers!"
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.ui.crash.CrashActivity.collectExceptionInfo():java.lang.String");
    }

    private final String collectAppInfo() {
        PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        return packageInfo.versionName + " (" + packageInfo.versionCode + ')';
    }

    private final String collectModelInfo() {
        return Build.MODEL + " (Android " + Build.VERSION.RELEASE + ' ' + determineArchName() + ')';
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x003d, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String determineArchName() {
        /*
            r5 = this;
            java.lang.String[] r0 = android.os.Build.SUPPORTED_ABIS
            int r1 = r0.length
            r2 = 0
        L_0x0004:
            if (r2 >= r1) goto L_0x0040
            r3 = r0[r2]
            if (r3 != 0) goto L_0x000b
            goto L_0x003d
        L_0x000b:
            int r4 = r3.hashCode()
            switch(r4) {
                case -806050265: goto L_0x0034;
                case 117110: goto L_0x0029;
                case 145444210: goto L_0x001e;
                case 1431565292: goto L_0x0013;
                default: goto L_0x0012;
            }
        L_0x0012:
            goto L_0x003d
        L_0x0013:
            java.lang.String r4 = "arm64-v8a"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x003d
            java.lang.String r0 = "aarch64"
            return r0
        L_0x001e:
            java.lang.String r4 = "armeabi-v7a"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x003d
            java.lang.String r0 = "arm"
            return r0
        L_0x0029:
            java.lang.String r4 = "x86"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x003d
            java.lang.String r0 = "i686"
            return r0
        L_0x0034:
            java.lang.String r4 = "x86_64"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x003d
            return r4
        L_0x003d:
            int r2 = r2 + 1
            goto L_0x0004
        L_0x0040:
            java.lang.String r0 = "Unknown Arch"
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.ui.crash.CrashActivity.determineArchName():java.lang.String");
    }
}
