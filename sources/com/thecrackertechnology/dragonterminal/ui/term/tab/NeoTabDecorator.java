package com.thecrackertechnology.dragonterminal.ui.term.tab;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.component.colorscheme.ColorSchemeComponent;
import com.thecrackertechnology.dragonterminal.frontend.completion.listener.OnAutoCompleteListener;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.config.DefaultValues;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellProfile;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.TermCompleteListener;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.TermSessionData;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.TermViewClient;
import com.thecrackertechnology.dragonterminal.frontend.session.xorg.client.XSessionData;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalViewClient;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.ExtraKeysView;
import com.thecrackertechnology.dragonterminal.ui.term.NeoTermActivity;
import com.thecrackertechnology.dragonterminal.utils.TerminalUtils;
import de.mrapp.android.tabswitcher.Tab;
import de.mrapp.android.tabswitcher.TabSwitcher;
import de.mrapp.android.tabswitcher.TabSwitcherDecorator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 '2\u00020\u0001:\u0001'B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0010H\u0002J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0014H\u0016J\b\u0010\u0017\u001a\u00020\u0014H\u0016J\"\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u0014H\u0016JB\u0010\u001f\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u000b\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u00142\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0019\u0010%\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0019H\u0002¢\u0006\u0002\u0010&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006("}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/term/tab/NeoTabDecorator;", "Lde/mrapp/android/tabswitcher/TabSwitcherDecorator;", "context", "Lcom/thecrackertechnology/dragonterminal/ui/term/NeoTermActivity;", "(Lcom/thecrackertechnology/dragonterminal/ui/term/NeoTermActivity;)V", "getContext", "()Lcom/thecrackertechnology/dragonterminal/ui/term/NeoTermActivity;", "bindTerminalView", "", "tab", "Lcom/thecrackertechnology/dragonterminal/ui/term/tab/TermTab;", "view", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "extraKeysView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView;", "bindXSessionView", "Lcom/thecrackertechnology/dragonterminal/ui/term/tab/XSessionTab;", "createAutoCompleteListener", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/listener/OnAutoCompleteListener;", "getViewType", "", "Lde/mrapp/android/tabswitcher/Tab;", "index", "getViewTypeCount", "onInflateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "parent", "Landroid/view/ViewGroup;", "viewType", "onShowTab", "Landroid/content/Context;", "tabSwitcher", "Lde/mrapp/android/tabswitcher/TabSwitcher;", "savedInstanceState", "Landroid/os/Bundle;", "setViewLayerType", "(Landroid/view/View;)Lkotlin/Unit;", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoTabDecorator.kt */
public final class NeoTabDecorator extends TabSwitcherDecorator {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static int VIEW_TYPE_COUNT;
    private static final int VIEW_TYPE_TERM;
    private static final int VIEW_TYPE_X;
    private final NeoTermActivity context;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/term/tab/NeoTabDecorator$Companion;", "", "()V", "VIEW_TYPE_COUNT", "", "VIEW_TYPE_TERM", "VIEW_TYPE_X", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: NeoTabDecorator.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public NeoTabDecorator(NeoTermActivity neoTermActivity) {
        Intrinsics.checkParameterIsNotNull(neoTermActivity, "context");
        this.context = neoTermActivity;
    }

    public final NeoTermActivity getContext() {
        return this.context;
    }

    static {
        int i = VIEW_TYPE_COUNT;
        VIEW_TYPE_COUNT = i + 1;
        VIEW_TYPE_TERM = i;
        int i2 = VIEW_TYPE_COUNT;
        VIEW_TYPE_COUNT = i2 + 1;
        VIEW_TYPE_X = i2;
    }

    /* access modifiers changed from: private */
    public final Unit setViewLayerType(View view) {
        if (view == null) {
            return null;
        }
        view.setLayerType(0, (Paint) null);
        return Unit.INSTANCE;
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        if (i == VIEW_TYPE_TERM) {
            View inflate = layoutInflater.inflate(R.layout.ui_term, viewGroup, false);
            TerminalView terminalView = (TerminalView) inflate.findViewById(R.id.terminal_view);
            ExtraKeysView extraKeysView = (ExtraKeysView) inflate.findViewById(R.id.extra_keys);
            TerminalUtils.setupTerminalView$default(TerminalUtils.INSTANCE, terminalView, (TerminalViewClient) null, 2, (Object) null);
            TerminalUtils.INSTANCE.setupExtraKeysView(extraKeysView);
            ColorSchemeComponent colorSchemeComponent = (ColorSchemeComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, ColorSchemeComponent.class, false, 2, (Object) null);
            colorSchemeComponent.applyColorScheme(terminalView, extraKeysView, colorSchemeComponent.getCurrentColorScheme());
            Intrinsics.checkExpressionValueIsNotNull(inflate, "view");
            return inflate;
        } else if (i == VIEW_TYPE_X) {
            View inflate2 = layoutInflater.inflate(R.layout.ui_xorg, viewGroup, false);
            Intrinsics.checkExpressionValueIsNotNull(inflate2, "inflater.inflate(R.layout.ui_xorg, parent, false)");
            return inflate2;
        } else {
            throw new RuntimeException("Unknown view type");
        }
    }

    public void onShowTab(Context context2, TabSwitcher tabSwitcher, View view, Tab tab, int i, int i2, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(tabSwitcher, "tabSwitcher");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(tab, "tab");
        Toolbar toolbar = this.context.getToolbar();
        toolbar.setTitle(tabSwitcher.isSwitcherShown() ? null : tab.getTitle());
        boolean z = tabSwitcher.getSelectedTabIndex() != i;
        if (i2 == VIEW_TYPE_TERM) {
            TermTab termTab = (TermTab) tab;
            termTab.setToolbar(toolbar);
            TerminalView terminalView = (TerminalView) findViewById(R.id.terminal_view);
            if (z) {
                bindTerminalView(termTab, terminalView, (ExtraKeysView) null);
                return;
            }
            bindTerminalView(termTab, terminalView, (ExtraKeysView) findViewById(R.id.extra_keys));
            terminalView.requestFocus();
        } else if (i2 == VIEW_TYPE_X) {
            toolbar.setVisibility(8);
            bindXSessionView((XSessionTab) tab);
        }
    }

    private final void bindXSessionView(XSessionTab xSessionTab) {
        XSessionData sessionData = xSessionTab.getSessionData();
        if (sessionData != null) {
            if (sessionData.getVideoLayout() == null) {
                FrameLayout frameLayout = (FrameLayout) findViewById(R.id.xorg_video_layout);
                sessionData.setVideoLayout(frameLayout);
                setViewLayerType(frameLayout);
            }
            FrameLayout videoLayout = sessionData.getVideoLayout();
            if (videoLayout == null) {
                Intrinsics.throwNpe();
            }
            if (sessionData.getGlView() == null) {
                new Thread(new NeoTabDecorator$bindXSessionView$1(this, sessionData, videoLayout)).start();
            }
        }
    }

    private final void bindTerminalView(TermTab termTab, TerminalView terminalView, ExtraKeysView extraKeysView) {
        TermViewClient viewClient;
        if (terminalView != null) {
            TermSessionData termData = termTab.getTermData();
            termData.initializeViewWith(termTab, terminalView, extraKeysView);
            ShellProfile profile = termData.getProfile();
            terminalView.setEnableWordBasedIme(profile != null ? profile.getEnableWordBasedIme() : DefaultValues.INSTANCE.getEnableWordBasedIme());
            terminalView.setTerminalViewClient(termData.getViewClient());
            terminalView.attachSession(termData.getTermSession());
            if (NeoPreference.INSTANCE.loadBoolean((int) R.string.key_general_auto_completion, false)) {
                if (termData.getOnAutoCompleteListener() == null) {
                    termData.setOnAutoCompleteListener(createAutoCompleteListener(terminalView));
                }
                terminalView.setOnAutoCompleteListener(termData.getOnAutoCompleteListener());
            }
            if (termData.getTermSession() != null && (viewClient = termData.getViewClient()) != null) {
                TerminalSession termSession = termData.getTermSession();
                viewClient.updateExtraKeys(termSession != null ? termSession.getTitle() : null, true);
            }
        }
    }

    private final OnAutoCompleteListener createAutoCompleteListener(TerminalView terminalView) {
        return new TermCompleteListener(terminalView);
    }

    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    public int getViewType(Tab tab, int i) {
        Intrinsics.checkParameterIsNotNull(tab, "tab");
        if (tab instanceof TermTab) {
            return VIEW_TYPE_TERM;
        }
        if (tab instanceof XSessionTab) {
            return VIEW_TYPE_X;
        }
        return -1;
    }
}
