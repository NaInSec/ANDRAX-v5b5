package com.thecrackertechnology.dragonterminal.ui.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0016J\u0012\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0002¨\u0006\u0014"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/settings/GeneralSettingsActivity;", "Lcom/thecrackertechnology/dragonterminal/ui/settings/BasePreferenceActivity;", "()V", "onBuildHeaders", "", "target", "", "Landroid/preference/PreferenceActivity$Header;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "postChangeShell", "shellName", "", "requestInstallShell", "currentShell", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: GeneralSettingsActivity.kt */
public final class GeneralSettingsActivity extends BasePreferenceActivity {
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

    public void onBuildHeaders(List<PreferenceActivity.Header> list) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) getString(R.string.general_settings));
        }
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setDisplayHomeAsUpEnabled(true);
        }
        ActionBar supportActionBar3 = getSupportActionBar();
        if (supportActionBar3 != null) {
            supportActionBar3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.color.blackfull));
        }
        addPreferencesFromResource(R.xml.setting_general);
    }

    /* access modifiers changed from: private */
    public final void postChangeShell(String str) {
        NeoPreference.INSTANCE.setLoginShellName(str);
    }

    private final void requestInstallShell(String str, String str2) {
        new AlertDialog.Builder(this).setTitle(getString(R.string.shell_not_found, new Object[]{str})).setMessage(R.string.shell_not_found_message).setPositiveButton(R.string.install, new GeneralSettingsActivity$requestInstallShell$1(this, str)).setNegativeButton(17039369, (DialogInterface.OnClickListener) null).setOnDismissListener(new GeneralSettingsActivity$requestInstallShell$2(this, str2)).show();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Integer valueOf = menuItem != null ? Integer.valueOf(menuItem.getItemId()) : null;
        if (valueOf != null && valueOf.intValue() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
