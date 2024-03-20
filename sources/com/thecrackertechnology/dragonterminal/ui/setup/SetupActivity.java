package com.thecrackertechnology.dragonterminal.ui.setup;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.component.pm.SourceHelper;
import com.thecrackertechnology.dragonterminal.setup.ResultListener;
import com.thecrackertechnology.dragonterminal.setup.SetupHelper;
import com.thecrackertechnology.dragonterminal.setup.SourceConnection;
import com.thecrackertechnology.dragonterminal.setup.connections.BackupFileConnection;
import com.thecrackertechnology.dragonterminal.setup.connections.LocalFileConnection;
import com.thecrackertechnology.dragonterminal.setup.connections.NetworkConnection;
import com.thecrackertechnology.dragonterminal.setup.helper.URLAvailability;
import com.thecrackertechnology.dragonterminal.utils.MediaUtils;
import com.thecrackertechnology.dragonterminal.utils.PackageUtils;
import java.io.File;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 .2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001.B\u0005¢\u0006\u0002\u0010\u0004J\"\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000eH\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\u0010\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0010H\u0002J\b\u0010\u0019\u001a\u00020\u0015H\u0002J\b\u0010\u001a\u001a\u00020\u0015H\u0002J\"\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\t2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\u0012\u0010 \u001a\u00020\u00152\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0012\u0010#\u001a\u00020\u00152\b\u0010$\u001a\u0004\u0018\u00010%H\u0014J\u0012\u0010&\u001a\u00020\u00152\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0018\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020+2\u0006\u0010\u0011\u001a\u00020\tH\u0002J\u0010\u0010,\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0010H\u0002J\u001a\u0010-\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/setup/SetupActivity;", "Landroid/support/v7/app/AppCompatActivity;", "Landroid/view/View$OnClickListener;", "Lcom/thecrackertechnology/dragonterminal/setup/ResultListener;", "()V", "aptUpdated", "", "hintMapping", "", "", "[Ljava/lang/Integer;", "setupParameter", "", "setupParameterUri", "Landroid/net/Uri;", "createSourceConnection", "Lcom/thecrackertechnology/dragonterminal/setup/SourceConnection;", "id", "parameter", "parameterUri", "doPrepareSetup", "", "doSelectParameter", "doSetup", "connection", "executeAptUpdate", "executeAptUpgrade", "onActivityResult", "requestCode", "resultCode", "resultData", "Landroid/content/Intent;", "onClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResult", "error", "Ljava/lang/Exception;", "setDefaultValue", "parameterEditor", "Landroid/widget/EditText;", "showConfirmDialog", "validateParameter", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: SetupActivity.kt */
public final class SetupActivity extends AppCompatActivity implements View.OnClickListener, ResultListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int REQUEST_SELECT_PARAMETER = 520;
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public boolean aptUpdated;
    /* access modifiers changed from: private */
    public final Integer[] hintMapping = {Integer.valueOf(R.id.setup_method_online), Integer.valueOf(R.string.setup_hint_online), Integer.valueOf(R.id.setup_method_local), Integer.valueOf(R.string.setup_hint_local), Integer.valueOf(R.id.setup_method_backup), Integer.valueOf(R.string.setup_hint_backup)};
    /* access modifiers changed from: private */
    public String setupParameter = "";
    /* access modifiers changed from: private */
    public Uri setupParameterUri;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[URLAvailability.ResultCode.values().length];

        static {
            $EnumSwitchMapping$0[URLAvailability.ResultCode.URL_NO_INTERNET.ordinal()] = 1;
            $EnumSwitchMapping$0[URLAvailability.ResultCode.URL_CONNECTION_FAILED.ordinal()] = 2;
            $EnumSwitchMapping$0[URLAvailability.ResultCode.URL_INVALID.ordinal()] = 3;
        }
    }

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/setup/SetupActivity$Companion;", "", "()V", "REQUEST_SELECT_PARAMETER", "", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: SetupActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.ui_setup);
        CompoundButton.OnCheckedChangeListener setupActivity$onCreate$onCheckedChangeListener$1 = new SetupActivity$onCreate$onCheckedChangeListener$1(this, (TextView) findViewById(R.id.setup_url_tip_text));
        ((RadioButton) findViewById(R.id.setup_method_online)).setOnCheckedChangeListener(setupActivity$onCreate$onCheckedChangeListener$1);
        ((RadioButton) findViewById(R.id.setup_method_local)).setOnCheckedChangeListener(setupActivity$onCreate$onCheckedChangeListener$1);
        ((RadioButton) findViewById(R.id.setup_method_backup)).setOnCheckedChangeListener(setupActivity$onCreate$onCheckedChangeListener$1);
        View.OnClickListener onClickListener = this;
        ((Button) findViewById(R.id.setup_next)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.setup_source_parameter_select)).setOnClickListener(onClickListener);
    }

    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.setup_next:
                    doPrepareSetup();
                    return;
                case R.id.setup_source_parameter_select:
                    doSelectParameter();
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == REQUEST_SELECT_PARAMETER && i2 == -1 && intent != null) {
            this.setupParameterUri = intent.getData();
            MediaUtils mediaUtils = MediaUtils.INSTANCE;
            Context context = this;
            Uri uri = this.setupParameterUri;
            if (uri == null) {
                Intrinsics.throwNpe();
            }
            mediaUtils.getPath(context, uri);
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    private final void doPrepareSetup() {
        View findViewById = findViewById(R.id.setup_method_group);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<RadioGroup>(R.id.setup_method_group)");
        int checkedRadioButtonId = ((RadioGroup) findViewById).getCheckedRadioButtonId();
        if (this.setupParameterUri == null && (checkedRadioButtonId == R.id.setup_method_backup || checkedRadioButtonId == R.id.setup_method_local)) {
            SetupHelper.INSTANCE.makeErrorDialog((Context) this, (int) R.string.setup_error_parameter_null).show();
            return;
        }
        String string = getString(R.string.setup_preparing);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.setup_preparing)");
        ProgressDialog makeProgressDialog = SetupHelper.INSTANCE.makeProgressDialog(this, string);
        makeProgressDialog.show();
        new Thread(new SetupActivity$doPrepareSetup$1(this, checkedRadioButtonId, makeProgressDialog)).start();
    }

    private final void doSelectParameter() {
        View findViewById = findViewById(R.id.setup_method_group);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<RadioGroup>(R.id.setup_method_group)");
        switch (((RadioGroup) findViewById).getCheckedRadioButtonId()) {
            case R.id.setup_method_backup:
            case R.id.setup_method_local:
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("*/*");
                try {
                    startActivityForResult(Intent.createChooser(intent, getString(R.string.setup_local)), REQUEST_SELECT_PARAMETER);
                    return;
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(this, R.string.no_file_picker, 0).show();
                    return;
                }
            case R.id.setup_method_online:
                Context context = this;
                View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_edit_text, (ViewGroup) null, false);
                View findViewById2 = inflate.findViewById(R.id.dialog_edit_text_info);
                Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById<TextVi…id.dialog_edit_text_info)");
                ((TextView) findViewById2).setText(getString(R.string.input_new_source_url));
                new AlertDialog.Builder(context).setTitle(R.string.new_source).setView(inflate).setPositiveButton(17039379, new SetupActivity$doSelectParameter$1((EditText) inflate.findViewById(R.id.dialog_edit_text_editor))).setNegativeButton(17039369, (DialogInterface.OnClickListener) null).show();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public final SourceConnection createSourceConnection(int i, String str, Uri uri) {
        switch (i) {
            case R.id.setup_method_backup:
                Context context = this;
                if (uri == null) {
                    Intrinsics.throwNpe();
                }
                return new BackupFileConnection(context, uri);
            case R.id.setup_method_local:
                Context context2 = this;
                if (uri == null) {
                    Intrinsics.throwNpe();
                }
                return new LocalFileConnection(context2, uri);
            case R.id.setup_method_online:
                return new NetworkConnection(str);
            default:
                throw new IllegalArgumentException("Unexpected setup method!");
        }
    }

    /* access modifiers changed from: private */
    public final String validateParameter(int i, String str) {
        switch (i) {
            case R.id.setup_method_backup:
            case R.id.setup_method_local:
                if (new File(str).exists()) {
                    return null;
                }
                return getString(R.string.setup_error_file_not_found);
            case R.id.setup_method_online:
                URLAvailability.ResultCode checkUrlAvailability = URLAvailability.checkUrlAvailability(this, str);
                if (checkUrlAvailability == null) {
                    return null;
                }
                int i2 = WhenMappings.$EnumSwitchMapping$0[checkUrlAvailability.ordinal()];
                if (i2 == 1) {
                    return getString(R.string.setup_error_no_internet);
                }
                if (i2 == 2) {
                    return getString(R.string.setup_error_connection_failed);
                }
                if (i2 != 3) {
                    return null;
                }
                return getString(R.string.setup_error_invalid_url);
            default:
                return null;
        }
    }

    private final void setDefaultValue(EditText editText, int i) {
        this.setupParameter = "";
        editText.setText(this.setupParameter);
    }

    /* access modifiers changed from: private */
    public final void showConfirmDialog(SourceConnection sourceConnection) {
        boolean needSetup = SetupHelper.INSTANCE.needSetup();
        new AlertDialog.Builder(this).setTitle(needSetup ? R.string.setup_confirm : R.string.setup_reset_confirm).setMessage(needSetup ? R.string.setup_confirm_text : R.string.setup_reset_confirm_text).setPositiveButton(17039379, new SetupActivity$showConfirmDialog$1(this, sourceConnection)).setNegativeButton(17039369, (DialogInterface.OnClickListener) null).show();
    }

    /* access modifiers changed from: private */
    public final void doSetup(SourceConnection sourceConnection) {
        SetupHelper.INSTANCE.setup(this, sourceConnection, this);
    }

    public void onResult(Exception exc) {
        if (exc == null) {
            setResult(-1);
            SourceHelper.INSTANCE.syncSource();
            executeAptUpdate();
            return;
        }
        new AlertDialog.Builder(this).setTitle(R.string.error).setMessage(exc.toString()).setNegativeButton(R.string.use_system_shell, new SetupActivity$onResult$1(this)).setNeutralButton(R.string.show_help, SetupActivity$onResult$2.INSTANCE).setPositiveButton(17039379, (DialogInterface.OnClickListener) null).show();
    }

    private final void executeAptUpdate() {
        PackageUtils.INSTANCE.apt(this, "update", (String[]) null, new SetupActivity$executeAptUpdate$1(this));
    }

    /* access modifiers changed from: private */
    public final void executeAptUpgrade() {
        PackageUtils.INSTANCE.apt(this, "upgrade", new String[]{"-y"}, new SetupActivity$executeAptUpgrade$1(this));
    }
}
