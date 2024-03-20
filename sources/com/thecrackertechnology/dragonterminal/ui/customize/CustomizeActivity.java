package com.thecrackertechnology.dragonterminal.ui.customize;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.component.colorscheme.ColorSchemeComponent;
import com.thecrackertechnology.dragonterminal.component.font.FontComponent;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoTermPath;
import com.thecrackertechnology.dragonterminal.utils.MediaUtils;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0002J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\"\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0012\u0010\u0013\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0007H\u0014J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0007H\u0014J.\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00042\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u001f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020\u0007H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/customize/CustomizeActivity;", "Lcom/thecrackertechnology/dragonterminal/ui/customize/BaseCustomizeActivity;", "()V", "REQUEST_SELECT_COLOR", "", "REQUEST_SELECT_FONT", "installColor", "", "selected", "", "installFileTo", "file", "targetDir", "installFont", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onResume", "setupSpinner", "Landroid/widget/Spinner;", "id", "", "listener", "Landroid/widget/AdapterView$OnItemSelectedListener;", "setupSpinners", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: CustomizeActivity.kt */
public final class CustomizeActivity extends BaseCustomizeActivity {
    /* access modifiers changed from: private */
    public final int REQUEST_SELECT_COLOR = 22223;
    /* access modifiers changed from: private */
    public final int REQUEST_SELECT_FONT = 22222;
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
        initCustomizationComponent(R.layout.ui_customize);
        findViewById(R.id.custom_install_font_button).setOnClickListener(new CustomizeActivity$onCreate$1(this));
        findViewById(R.id.custom_install_color_button).setOnClickListener(new CustomizeActivity$onCreate$2(this));
    }

    private final void setupSpinners() {
        FontComponent fontComponent = (FontComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, FontComponent.class, false, 2, (Object) null);
        ColorSchemeComponent colorSchemeComponent = (ColorSchemeComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, ColorSchemeComponent.class, false, 2, (Object) null);
        setupSpinner(R.id.custom_font_spinner, fontComponent.getFontNames(), fontComponent.getCurrentFontName(), new CustomizeActivity$setupSpinners$1(this, fontComponent));
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add(getString(R.string.new_color_scheme));
        Object[] array = colorSchemeComponent.getColorSchemeNames().toArray(new String[0]);
        if (array != null) {
            spreadBuilder.addSpread(array);
            setupSpinner(R.id.custom_color_spinner, CollectionsKt.listOf((String[]) spreadBuilder.toArray(new String[spreadBuilder.size()])), colorSchemeComponent.getCurrentColorSchemeName(), new CustomizeActivity$setupSpinners$2(this, colorSchemeComponent));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final Spinner setupSpinner(int i, List<String> list, String str, AdapterView.OnItemSelectedListener onItemSelectedListener) {
        Spinner spinner = (Spinner) findViewById(i);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, 17367048, list);
        arrayAdapter.setDropDownViewResource(17367049);
        Intrinsics.checkExpressionValueIsNotNull(spinner, "spinner");
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(onItemSelectedListener);
        spinner.setSelection(list.contains(str) ? list.indexOf(str) : 0);
        return spinner;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        setupSpinners();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        getSession().finishIfRunning();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && intent != null) {
            Uri data = intent.getData();
            Intrinsics.checkExpressionValueIsNotNull(data, "data.data");
            String path = MediaUtils.INSTANCE.getPath(this, data);
            if (path != null) {
                if (path.length() > 0) {
                    if (i == this.REQUEST_SELECT_FONT) {
                        installFont(path);
                    } else if (i == this.REQUEST_SELECT_COLOR) {
                        installColor(path);
                    }
                }
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    private final void installColor(String str) {
        installFileTo(str, NeoTermPath.COLORS_PATH);
        setupSpinners();
    }

    private final void installFont(String str) {
        installFileTo(str, NeoTermPath.FONT_PATH);
        setupSpinners();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void installFileTo(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0031 }
            r0.<init>(r4)     // Catch:{ Exception -> 0x0031 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0031 }
            java.lang.String r1 = r0.getAbsolutePath()     // Catch:{ Exception -> 0x0031 }
            r4.<init>(r1)     // Catch:{ Exception -> 0x0031 }
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0031 }
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x0031 }
            r1.<init>(r5, r0)     // Catch:{ Exception -> 0x0031 }
            java.io.Closeable r4 = (java.io.Closeable) r4     // Catch:{ Exception -> 0x0031 }
            r5 = 0
            java.lang.Throwable r5 = (java.lang.Throwable) r5     // Catch:{ Exception -> 0x0031 }
            r0 = r4
            java.io.FileInputStream r0 = (java.io.FileInputStream) r0     // Catch:{ all -> 0x002a }
            com.thecrackertechnology.dragonterminal.utils.FileUtils r2 = com.thecrackertechnology.dragonterminal.utils.FileUtils.INSTANCE     // Catch:{ all -> 0x002a }
            java.io.InputStream r0 = (java.io.InputStream) r0     // Catch:{ all -> 0x002a }
            r2.writeFile((java.io.File) r1, (java.io.InputStream) r0)     // Catch:{ all -> 0x002a }
            kotlin.io.CloseableKt.closeFinally(r4, r5)     // Catch:{ Exception -> 0x0031 }
            goto L_0x005e
        L_0x002a:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x002c }
        L_0x002c:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r4, r5)     // Catch:{ Exception -> 0x0031 }
            throw r0     // Catch:{ Exception -> 0x0031 }
        L_0x0031:
            r4 = move-exception
            r5 = r3
            android.content.Context r5 = (android.content.Context) r5
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 2131755184(0x7f1000b0, float:1.914124E38)
            java.lang.String r1 = r3.getString(r1)
            r0.append(r1)
            java.lang.String r1 = ": "
            r0.append(r1)
            java.lang.String r4 = r4.getLocalizedMessage()
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r0 = 1
            android.widget.Toast r4 = android.widget.Toast.makeText(r5, r4, r0)
            r4.show()
        L_0x005e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.ui.customize.CustomizeActivity.installFileTo(java.lang.String, java.lang.String):void");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Integer valueOf = menuItem != null ? Integer.valueOf(menuItem.getItemId()) : null;
        if (valueOf != null && valueOf.intValue() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
