package com.thecrackertechnology.dragonterminal.ui.support;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.ui.setup.SetupActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u0004H\u0002¨\u0006\u000f"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/support/AboutActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "openUrl", "url", "", "resetApp", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: AboutActivity.kt */
public final class AboutActivity extends AppCompatActivity {
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
        setContentView((int) R.layout.ui_about);
        setSupportActionBar((Toolbar) findViewById(R.id.about_toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        try {
            String str = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            View findViewById = findViewById(R.id.app_version);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "(findViewById<TextView>(R.id.app_version))");
            ((TextView) findViewById).setText(str);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        findViewById(R.id.about_developers_view).setOnClickListener(new AboutActivity$onCreate$1(this));
        findViewById(R.id.about_licenses_view).setOnClickListener(new AboutActivity$onCreate$2(this));
        findViewById(R.id.about_version_view).setOnClickListener(new AboutActivity$onCreate$3(this));
        findViewById(R.id.about_source_code_view).setOnClickListener(new AboutActivity$onCreate$4(this));
        findViewById(R.id.about_donate_view).setOnClickListener(new AboutActivity$onCreate$5(this));
        findViewById(R.id.about_show_help_view).setOnClickListener(AboutActivity$onCreate$6.INSTANCE);
        findViewById(R.id.about_reset_app_view).setOnClickListener(new AboutActivity$onCreate$7(this));
    }

    /* access modifiers changed from: private */
    public final void resetApp() {
        startActivity(new Intent(this, SetupActivity.class));
    }

    /* access modifiers changed from: private */
    public final void openUrl(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Integer valueOf = menuItem != null ? Integer.valueOf(menuItem.getItemId()) : null;
        if (valueOf != null && valueOf.intValue() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
