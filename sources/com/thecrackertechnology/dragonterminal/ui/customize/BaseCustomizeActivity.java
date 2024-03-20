package com.thecrackertechnology.dragonterminal.ui.customize;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import com.thecrackertechnology.andrax.AndraxApp;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellParameter;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.BasicSessionCallback;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.BasicViewClient;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.ExtraKeysView;
import com.thecrackertechnology.dragonterminal.utils.TerminalUtils;
import java.io.File;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$J\u0012\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006)"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/customize/BaseCustomizeActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "extraKeysView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView;", "getExtraKeysView", "()Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView;", "setExtraKeysView", "(Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView;)V", "session", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "getSession", "()Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "setSession", "(Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;)V", "sessionCallback", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/BasicSessionCallback;", "getSessionCallback", "()Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/BasicSessionCallback;", "setSessionCallback", "(Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/BasicSessionCallback;)V", "terminalView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "getTerminalView", "()Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "setTerminalView", "(Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;)V", "viewClient", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/BasicViewClient;", "getViewClient", "()Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/BasicViewClient;", "setViewClient", "(Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/BasicViewClient;)V", "initCustomizationComponent", "", "layoutId", "", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: BaseCustomizeActivity.kt */
public class BaseCustomizeActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    public ExtraKeysView extraKeysView;
    public TerminalSession session;
    public BasicSessionCallback sessionCallback;
    public TerminalView terminalView;
    public BasicViewClient viewClient;

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

    public final TerminalView getTerminalView() {
        TerminalView terminalView2 = this.terminalView;
        if (terminalView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("terminalView");
        }
        return terminalView2;
    }

    public final void setTerminalView(TerminalView terminalView2) {
        Intrinsics.checkParameterIsNotNull(terminalView2, "<set-?>");
        this.terminalView = terminalView2;
    }

    public final BasicViewClient getViewClient() {
        BasicViewClient basicViewClient = this.viewClient;
        if (basicViewClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewClient");
        }
        return basicViewClient;
    }

    public final void setViewClient(BasicViewClient basicViewClient) {
        Intrinsics.checkParameterIsNotNull(basicViewClient, "<set-?>");
        this.viewClient = basicViewClient;
    }

    public final BasicSessionCallback getSessionCallback() {
        BasicSessionCallback basicSessionCallback = this.sessionCallback;
        if (basicSessionCallback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionCallback");
        }
        return basicSessionCallback;
    }

    public final void setSessionCallback(BasicSessionCallback basicSessionCallback) {
        Intrinsics.checkParameterIsNotNull(basicSessionCallback, "<set-?>");
        this.sessionCallback = basicSessionCallback;
    }

    public final TerminalSession getSession() {
        TerminalSession terminalSession = this.session;
        if (terminalSession == null) {
            Intrinsics.throwUninitializedPropertyAccessException("session");
        }
        return terminalSession;
    }

    public final void setSession(TerminalSession terminalSession) {
        Intrinsics.checkParameterIsNotNull(terminalSession, "<set-?>");
        this.session = terminalSession;
    }

    public final ExtraKeysView getExtraKeysView() {
        ExtraKeysView extraKeysView2 = this.extraKeysView;
        if (extraKeysView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("extraKeysView");
        }
        return extraKeysView2;
    }

    public final void setExtraKeysView(ExtraKeysView extraKeysView2) {
        Intrinsics.checkParameterIsNotNull(extraKeysView2, "<set-?>");
        this.extraKeysView = extraKeysView2;
    }

    public final void initCustomizationComponent(int i) {
        setContentView(i);
        Toolbar toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.blackfull));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        View findViewById = findViewById(R.id.terminal_view);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.terminal_view)");
        this.terminalView = (TerminalView) findViewById;
        View findViewById2 = findViewById(R.id.custom_extra_keys);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById(R.id.custom_extra_keys)");
        this.extraKeysView = (ExtraKeysView) findViewById2;
        TerminalView terminalView2 = this.terminalView;
        if (terminalView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("terminalView");
        }
        this.viewClient = new BasicViewClient(terminalView2);
        TerminalView terminalView3 = this.terminalView;
        if (terminalView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("terminalView");
        }
        this.sessionCallback = new BasicSessionCallback(terminalView3);
        TerminalUtils terminalUtils = TerminalUtils.INSTANCE;
        TerminalView terminalView4 = this.terminalView;
        if (terminalView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("terminalView");
        }
        BasicViewClient basicViewClient = this.viewClient;
        if (basicViewClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewClient");
        }
        terminalUtils.setupTerminalView(terminalView4, basicViewClient);
        TerminalUtils terminalUtils2 = TerminalUtils.INSTANCE;
        ExtraKeysView extraKeysView2 = this.extraKeysView;
        if (extraKeysView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("extraKeysView");
        }
        terminalUtils2.setupExtraKeysView(extraKeysView2);
        getResources().getStringArray(R.array.custom_preview_script_colors);
        ShellParameter shellParameter = new ShellParameter();
        StringBuilder sb = new StringBuilder();
        File filesDir = AndraxApp.Companion.get().getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "AndraxApp.get().filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/bin/testcolors.sh");
        ShellParameter arguments = shellParameter.executablePath(sb.toString()).arguments(new String[]{"testcolors.sh"});
        BasicSessionCallback basicSessionCallback = this.sessionCallback;
        if (basicSessionCallback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionCallback");
        }
        this.session = TerminalUtils.INSTANCE.createSession((Context) this, arguments.callback(basicSessionCallback).systemShell(true));
        new Handler().postDelayed(new BaseCustomizeActivity$initCustomizationComponent$1(this), 1000);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Integer valueOf = menuItem != null ? Integer.valueOf(menuItem.getItemId()) : null;
        if (valueOf != null && valueOf.intValue() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
