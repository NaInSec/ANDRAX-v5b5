package com.thecrackertechnology.dragonterminal.ui.term.tab;

import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;
import com.onesignal.OneSignalDbContract;
import com.thecrackertechnology.dragonterminal.component.colorscheme.ColorSchemeComponent;
import com.thecrackertechnology.dragonterminal.frontend.completion.listener.OnAutoCompleteListener;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.TermSessionData;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.TermUiPresenter;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.TermViewClient;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.event.CreateNewSessionEvent;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.event.SwitchIndexedSessionEvent;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.event.SwitchSessionEvent;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.event.TabCloseEvent;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.event.TitleChangedEvent;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.event.ToggleFullScreenEvent;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.ExtraKeysView;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 (2\u00020\u00012\u00020\u0002:\u0001(B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u0013H\u0016J\b\u0010\u0018\u001a\u00020\u0013H\u0016J\b\u0010\u0019\u001a\u00020\u0016H\u0016J\b\u0010\u001a\u001a\u00020\u0013H\u0016J\b\u0010\u001b\u001a\u00020\u0013H\u0016J\b\u0010\u001c\u001a\u00020\u0013H\u0016J\u0010\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u0013H\u0016J\b\u0010!\u001a\u00020\u0013H\u0016J\b\u0010\"\u001a\u00020\u0013H\u0016J\u0012\u0010#\u001a\u00020\u00132\b\u0010\u0003\u001a\u0004\u0018\u00010$H\u0016J\u0006\u0010%\u001a\u00020\u0013J\u0006\u0010&\u001a\u00020\u0013J\u0006\u0010'\u001a\u00020\u0013R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006)"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/term/tab/TermTab;", "Lcom/thecrackertechnology/dragonterminal/ui/term/tab/NeoTab;", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermUiPresenter;", "title", "", "(Ljava/lang/CharSequence;)V", "termData", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermSessionData;", "getTermData", "()Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermSessionData;", "setTermData", "(Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermSessionData;)V", "toolbar", "Landroid/support/v7/widget/Toolbar;", "getToolbar", "()Landroid/support/v7/widget/Toolbar;", "setToolbar", "(Landroid/support/v7/widget/Toolbar;)V", "cleanup", "", "onFullScreenModeChanged", "fullScreen", "", "requireClose", "requireCreateNew", "requireFinishAutoCompletion", "requireHideIme", "requireOnSessionFinished", "requirePaste", "requireSwitchTo", "index", "", "requireSwitchToNext", "requireSwitchToPrevious", "requireToggleFullScreen", "requireUpdateTitle", "", "resetAutoCompleteStatus", "resetStatus", "updateColorScheme", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: TermTab.kt */
public final class TermTab extends NeoTab implements TermUiPresenter {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String PARAMETER_SHOW_EKS = PARAMETER_SHOW_EKS;
    private TermSessionData termData = new TermSessionData();
    private Toolbar toolbar;

    public void requireOnSessionFinished() {
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/term/tab/TermTab$Companion;", "", "()V", "PARAMETER_SHOW_EKS", "", "getPARAMETER_SHOW_EKS", "()Ljava/lang/String;", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: TermTab.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getPARAMETER_SHOW_EKS() {
            return TermTab.PARAMETER_SHOW_EKS;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TermTab(CharSequence charSequence) {
        super(charSequence);
        Intrinsics.checkParameterIsNotNull(charSequence, OneSignalDbContract.NotificationTable.COLUMN_NAME_TITLE);
    }

    public final TermSessionData getTermData() {
        return this.termData;
    }

    public final void setTermData(TermSessionData termSessionData) {
        Intrinsics.checkParameterIsNotNull(termSessionData, "<set-?>");
        this.termData = termSessionData;
    }

    public final Toolbar getToolbar() {
        return this.toolbar;
    }

    public final void setToolbar(Toolbar toolbar2) {
        this.toolbar = toolbar2;
    }

    public final void updateColorScheme() {
        ColorSchemeComponent colorSchemeComponent = (ColorSchemeComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, ColorSchemeComponent.class, false, 2, (Object) null);
        colorSchemeComponent.applyColorScheme(this.termData.getTermView(), this.termData.getExtraKeysView(), colorSchemeComponent.getCurrentColorScheme());
    }

    public final void cleanup() {
        this.termData.cleanup();
        this.toolbar = null;
    }

    public final void onFullScreenModeChanged(boolean z) {
        resetAutoCompleteStatus();
    }

    public void requireHideIme() {
        TerminalView termView = this.termData.getTermView();
        if (termView != null) {
            Object systemService = termView.getContext().getSystemService("input_method");
            if (systemService != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) systemService;
                if (inputMethodManager.isActive()) {
                    inputMethodManager.hideSoftInputFromWindow(termView.getWindowToken(), 2);
                    return;
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        }
    }

    public boolean requireFinishAutoCompletion() {
        OnAutoCompleteListener onAutoCompleteListener = this.termData.getOnAutoCompleteListener();
        if (onAutoCompleteListener != null) {
            return onAutoCompleteListener.onFinishCompletion();
        }
        return false;
    }

    public void requireToggleFullScreen() {
        EventBus.getDefault().post(new ToggleFullScreenEvent());
    }

    public void requirePaste() {
        TerminalView termView = this.termData.getTermView();
        if (termView != null) {
            termView.pasteFromClipboard();
        }
    }

    public void requireClose() {
        requireHideIme();
        EventBus.getDefault().post(new TabCloseEvent(this));
    }

    public void requireUpdateTitle(String str) {
        if (str != null) {
            CharSequence charSequence = str;
            if (charSequence.length() > 0) {
                setTitle(charSequence);
                EventBus.getDefault().post(new TitleChangedEvent(str));
                TermViewClient viewClient = this.termData.getViewClient();
                if (viewClient != null) {
                    TermViewClient.updateExtraKeys$default(viewClient, str, false, 2, (Object) null);
                }
            }
        }
    }

    public void requireCreateNew() {
        EventBus.getDefault().post(new CreateNewSessionEvent());
    }

    public void requireSwitchToPrevious() {
        EventBus.getDefault().post(new SwitchSessionEvent(false));
    }

    public void requireSwitchToNext() {
        EventBus.getDefault().post(new SwitchSessionEvent(true));
    }

    public void requireSwitchTo(int i) {
        EventBus.getDefault().post(new SwitchIndexedSessionEvent(i));
    }

    public final void resetAutoCompleteStatus() {
        OnAutoCompleteListener onAutoCompleteListener = this.termData.getOnAutoCompleteListener();
        if (onAutoCompleteListener != null) {
            onAutoCompleteListener.onCleanUp();
        }
        this.termData.setOnAutoCompleteListener((OnAutoCompleteListener) null);
    }

    public final void resetStatus() {
        resetAutoCompleteStatus();
        ExtraKeysView extraKeysView = this.termData.getExtraKeysView();
        if (extraKeysView != null) {
            extraKeysView.updateButtons();
        }
        TerminalView termView = this.termData.getTermView();
        if (termView != null) {
            termView.updateSize();
        }
        TerminalView termView2 = this.termData.getTermView();
        if (termView2 != null) {
            termView2.onScreenUpdated();
        }
    }
}
