package com.thecrackertechnology.dragonterminal.ui.term;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import com.thecrackertechnology.andrax.AndraxApp;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.component.profile.NeoProfile;
import com.thecrackertechnology.dragonterminal.component.profile.ProfileComponent;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoPermission;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellParameter;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellProfile;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.TermSessionCallback;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.TermViewClient;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.event.CreateNewSessionEvent;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.event.SwitchIndexedSessionEvent;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.event.SwitchSessionEvent;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.event.TabCloseEvent;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.event.TitleChangedEvent;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.event.ToggleFullScreenEvent;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.event.ToggleImeEvent;
import com.thecrackertechnology.dragonterminal.frontend.session.xorg.XParameter;
import com.thecrackertechnology.dragonterminal.frontend.session.xorg.XSession;
import com.thecrackertechnology.dragonterminal.services.NeoTermService;
import com.thecrackertechnology.dragonterminal.ui.settings.SettingActivity;
import com.thecrackertechnology.dragonterminal.ui.term.tab.NeoTab;
import com.thecrackertechnology.dragonterminal.ui.term.tab.NeoTabDecorator;
import com.thecrackertechnology.dragonterminal.ui.term.tab.TermTab;
import com.thecrackertechnology.dragonterminal.ui.term.tab.XSessionTab;
import com.thecrackertechnology.dragonterminal.utils.FullScreenHelper;
import com.thecrackertechnology.dragonterminal.utils.RangedInt;
import de.mrapp.android.tabswitcher.Animation;
import de.mrapp.android.tabswitcher.RevealAnimation;
import de.mrapp.android.tabswitcher.Tab;
import de.mrapp.android.tabswitcher.TabSwitcher;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¾\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 ©\u00012\u00020\u00012\u00020\u00022\u00020\u0003:\u0002©\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010%\u001a\u00020&H\u0002J\"\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020+H\u0002J\u0012\u0010,\u001a\u00020&2\b\u0010-\u001a\u0004\u0018\u00010.H\u0002J\u0010\u0010/\u001a\u00020&2\u0006\u00100\u001a\u000201H\u0002J*\u0010/\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020+2\u0006\u00100\u001a\u000201H\u0002J\u0018\u00102\u001a\u00020&2\u0006\u00103\u001a\u0002042\u0006\u0010*\u001a\u00020+H\u0002J\b\u00105\u001a\u00020&H\u0002J\u0012\u00105\u001a\u00020&2\b\u0010-\u001a\u0004\u0018\u000106H\u0002J\u000e\u00107\u001a\u00020&2\u0006\u00108\u001a\u00020(J\u0006\u00109\u001a\u00020&J\b\u0010:\u001a\u00020\u0006H\u0002J\b\u0010;\u001a\u00020+H\u0002J\u0012\u0010<\u001a\u0002042\b\u0010=\u001a\u0004\u0018\u00010(H\u0002J\b\u0010>\u001a\u00020?H\u0002J\u0012\u0010@\u001a\u0002042\b\u0010=\u001a\u0004\u0018\u00010(H\u0002J\b\u0010A\u001a\u00020&H\u0002J%\u0010B\u001a\u00020&\"\u0006\b\u0000\u0010C\u0018\u00012\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u0002HC\u0012\u0004\u0012\u00020&0EH\bJ\b\u0010F\u001a\u00020&H\u0002J\u0010\u0010G\u001a\u00020(2\u0006\u0010H\u001a\u00020(H\u0002J\u0010\u0010I\u001a\u00020(2\u0006\u0010H\u001a\u00020(H\u0002J\n\u0010J\u001a\u0004\u0018\u00010KH\u0002J\n\u0010L\u001a\u0004\u0018\u00010.H\u0002J\b\u0010M\u001a\u00020\u0010H\u0002J\u0006\u0010N\u001a\u00020&J\b\u0010O\u001a\u00020\u0010H\u0002J\u000e\u0010P\u001a\u00020\u00102\u0006\u0010Q\u001a\u00020RJ\"\u0010S\u001a\u00020&2\u0006\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020U2\b\u0010W\u001a\u0004\u0018\u00010XH\u0014J\u0012\u0010Y\u001a\u00020&2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0016J\u0012\u0010\\\u001a\u00020&2\b\u0010]\u001a\u0004\u0018\u00010^H\u0014J\u0010\u0010_\u001a\u00020&2\u0006\u0010`\u001a\u00020aH\u0007J\u0012\u0010b\u001a\u00020\u00102\b\u0010c\u001a\u0004\u0018\u00010dH\u0016J\b\u0010e\u001a\u00020&H\u0014J\u001a\u0010f\u001a\u00020\u00102\u0006\u0010g\u001a\u00020U2\b\u0010h\u001a\u0004\u0018\u00010iH\u0016J\u0012\u0010j\u001a\u00020\u00102\b\u0010k\u001a\u0004\u0018\u00010lH\u0016J\b\u0010m\u001a\u00020&H\u0014J-\u0010n\u001a\u00020&2\u0006\u0010T\u001a\u00020U2\u000e\u0010o\u001a\n\u0012\u0006\b\u0001\u0012\u00020(0p2\u0006\u0010q\u001a\u00020rH\u0016¢\u0006\u0002\u0010sJ\b\u0010t\u001a\u00020&H\u0014J\u001c\u0010u\u001a\u00020&2\b\u0010v\u001a\u0004\u0018\u00010w2\b\u0010x\u001a\u0004\u0018\u00010yH\u0016J\u0012\u0010z\u001a\u00020&2\b\u0010v\u001a\u0004\u0018\u00010wH\u0016J\u001c\u0010{\u001a\u00020&2\b\u0010|\u001a\u0004\u0018\u00010}2\b\u0010~\u001a\u0004\u0018\u00010(H\u0016J\b\u0010\u001a\u00020&H\u0014J\t\u0010\u0001\u001a\u00020&H\u0014J\u0013\u0010\u0001\u001a\u00020&2\b\u0010\u0001\u001a\u00030\u0001H\u0007J\u0013\u0010\u0001\u001a\u00020&2\b\u0010\u0001\u001a\u00030\u0001H\u0007J\u0013\u0010\u0001\u001a\u00020&2\b\u0010\u0001\u001a\u00030\u0001H\u0007J\u0013\u0010\u0001\u001a\u00020&2\b\u0010\u0001\u001a\u00030\u0001H\u0007J\u0013\u0010\u0001\u001a\u00020&2\b\u0010\u0001\u001a\u00030\u0001H\u0007J\u0013\u0010\u0001\u001a\u00020&2\b\u0010\u0001\u001a\u00030\u0001H\u0007J\u0012\u0010\u0001\u001a\u00020&2\u0007\u0010\u0001\u001a\u00020\u0010H\u0016J\t\u0010\u0001\u001a\u00020\u0010H\u0002J\"\u0010\u0001\u001a\u0002HC\"\t\b\u0000\u0010C*\u00030\u00012\u0006\u00103\u001a\u0002HCH\u0002¢\u0006\u0003\u0010\u0001J\t\u0010\u0001\u001a\u00020&H\u0016J\t\u0010\u0001\u001a\u00020&H\u0002J\u0012\u0010\u0001\u001a\u00020&2\u0007\u0010\u0001\u001a\u00020\u0010H\u0002J\u0013\u0010\u0001\u001a\u00020&2\n\u0010\u0001\u001a\u0005\u0018\u00010\u0001J\u0011\u0010 \u0001\u001a\u00020&2\u0006\u0010)\u001a\u00020\u0010H\u0002J\t\u0010¡\u0001\u001a\u00020&H\u0002J\u0013\u0010¢\u0001\u001a\u00020&2\b\u0010-\u001a\u0004\u0018\u00010.H\u0002J\u0013\u0010¢\u0001\u001a\u00020&2\b\u00103\u001a\u0004\u0018\u000104H\u0002J\u001b\u0010£\u0001\u001a\u00020&2\u0007\u0010¤\u0001\u001a\u00020\u00102\u0007\u0010¥\u0001\u001a\u00020\u0010H\u0002J\u001c\u0010¦\u0001\u001a\u00020&2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0007\u0010§\u0001\u001a\u00020\u0010H\u0002J\u0007\u0010¨\u0001\u001a\u00020&R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\u001cX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0012\"\u0004\b#\u0010$¨\u0006ª\u0001"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/term/NeoTermActivity;", "Landroid/support/v7/app/AppCompatActivity;", "Landroid/content/ServiceConnection;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "()V", "addSessionListener", "Landroid/view/View$OnClickListener;", "getAddSessionListener", "()Landroid/view/View$OnClickListener;", "setAddSessionListener", "(Landroid/view/View$OnClickListener;)V", "errorDialog", "Landroid/app/Dialog;", "fullScreenHelper", "Lcom/thecrackertechnology/dragonterminal/utils/FullScreenHelper;", "fullscreen", "", "getFullscreen", "()Z", "tabSwitcher", "Lde/mrapp/android/tabswitcher/TabSwitcher;", "getTabSwitcher", "()Lde/mrapp/android/tabswitcher/TabSwitcher;", "setTabSwitcher", "(Lde/mrapp/android/tabswitcher/TabSwitcher;)V", "termService", "Lcom/thecrackertechnology/dragonterminal/services/NeoTermService;", "toolbar", "Landroid/support/v7/widget/Toolbar;", "getToolbar", "()Landroid/support/v7/widget/Toolbar;", "setToolbar", "(Landroid/support/v7/widget/Toolbar;)V", "tshow", "getTshow", "setTshow", "(Z)V", "addNewSession", "", "sessionName", "", "systemShell", "animation", "Lde/mrapp/android/tabswitcher/Animation;", "addNewSessionFromExisting", "session", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "addNewSessionWithProfile", "profile", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellProfile;", "addNewTab", "tab", "Lde/mrapp/android/tabswitcher/Tab;", "addXSession", "Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/XSession;", "changehostname", "hostnameprovided", "checkinstallterm", "createAddSessionListener", "createRevealAnimation", "createTab", "tabTitle", "createWindowInsetsListener", "Landroid/support/v4/view/OnApplyWindowInsetsListener;", "createXTab", "enterMain", "forEachTab", "T", "callback", "Lkotlin/Function1;", "forceAddSystemSession", "generateSessionName", "prefix", "generateXSessionName", "getNavigationMenuItem", "Landroid/view/View;", "getStoredCurrentSessionOrLast", "getSystemShellMode", "get_motherfucker_battery", "isRecreating", "isRooted", "c", "Landroid/content/Context;", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateNewSessionEvent", "createNewSessionEvent", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/event/CreateNewSessionEvent;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onDestroy", "onKeyDown", "keyCode", "event", "Landroid/view/KeyEvent;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPause", "onRequestPermissionsResult", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "onServiceConnected", "name", "Landroid/content/ComponentName;", "service", "Landroid/os/IBinder;", "onServiceDisconnected", "onSharedPreferenceChanged", "sharedPreferences", "Landroid/content/SharedPreferences;", "key", "onStart", "onStop", "onSwitchIndexedSessionEvent", "switchIndexedSessionEvent", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/event/SwitchIndexedSessionEvent;", "onSwitchSessionEvent", "switchSessionEvent", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/event/SwitchSessionEvent;", "onTabCloseEvent", "tabCloseEvent", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/event/TabCloseEvent;", "onTitleChangedEvent", "titleChangedEvent", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/event/TitleChangedEvent;", "onToggleFullScreenEvent", "toggleFullScreenEvent", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/event/ToggleFullScreenEvent;", "onToggleImeEvent", "toggleImeEvent", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/event/ToggleImeEvent;", "onWindowFocusChanged", "hasFocus", "peekRecreating", "postTabCreated", "Lcom/thecrackertechnology/dragonterminal/ui/term/tab/NeoTab;", "(Lcom/thecrackertechnology/dragonterminal/ui/term/tab/NeoTab;)Lcom/thecrackertechnology/dragonterminal/ui/term/tab/NeoTab;", "recreate", "saveCurrentStatus", "setFullScreenMode", "fullScreen", "setPermissions", "path", "Ljava/io/File;", "setSystemShellMode", "showProfileDialog", "switchToSession", "toggleSwitcher", "showSwitcher", "easterEgg", "toggleToolbar", "visible", "update_colors", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoTermActivity.kt */
public final class NeoTermActivity extends AppCompatActivity implements ServiceConnection, SharedPreferences.OnSharedPreferenceChangeListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String KEY_NO_RESTORE = "no_restore";
    public static final int REQUEST_SETUP = 22313;
    private HashMap _$_findViewCache;
    private View.OnClickListener addSessionListener = createAddSessionListener();
    private Dialog errorDialog;
    private FullScreenHelper fullScreenHelper;
    private final boolean fullscreen = NeoPreference.INSTANCE.isFullScreenEnabled();
    public TabSwitcher tabSwitcher;
    /* access modifiers changed from: private */
    public NeoTermService termService;
    public Toolbar toolbar;
    private boolean tshow;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/term/NeoTermActivity$Companion;", "", "()V", "KEY_NO_RESTORE", "", "REQUEST_SETUP", "", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: NeoTermActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final TabSwitcher getTabSwitcher() {
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        return tabSwitcher2;
    }

    public final void setTabSwitcher(TabSwitcher tabSwitcher2) {
        Intrinsics.checkParameterIsNotNull(tabSwitcher2, "<set-?>");
        this.tabSwitcher = tabSwitcher2;
    }

    public final Toolbar getToolbar() {
        Toolbar toolbar2 = this.toolbar;
        if (toolbar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
        }
        return toolbar2;
    }

    public final void setToolbar(Toolbar toolbar2) {
        Intrinsics.checkParameterIsNotNull(toolbar2, "<set-?>");
        this.toolbar = toolbar2;
    }

    public final View.OnClickListener getAddSessionListener() {
        return this.addSessionListener;
    }

    public final void setAddSessionListener(View.OnClickListener onClickListener) {
        Intrinsics.checkParameterIsNotNull(onClickListener, "<set-?>");
        this.addSessionListener = onClickListener;
    }

    public final boolean getFullscreen() {
        return this.fullscreen;
    }

    public final boolean getTshow() {
        return this.tshow;
    }

    public final void setTshow(boolean z) {
        this.tshow = z;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        changehostname("ANDRAX-Hackers-Platform");
        Context context = this;
        isRooted(context);
        Activity activity = this;
        NeoPermission.INSTANCE.initAppPermission(activity, NeoPermission.REQUEST_APP_PERMISSION);
        if (this.fullscreen) {
            getWindow().setFlags(1024, 1024);
        }
        if (ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        }
        setContentView((int) R.layout.ui_main);
        View findViewById = findViewById(R.id.terminal_toolbar);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.terminal_toolbar)");
        this.toolbar = (Toolbar) findViewById;
        Toolbar toolbar2 = this.toolbar;
        if (toolbar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
        }
        setSupportActionBar(toolbar2);
        this.fullScreenHelper = FullScreenHelper.Companion.injectActivity(activity, this.fullscreen, peekRecreating());
        FullScreenHelper fullScreenHelper2 = this.fullScreenHelper;
        if (fullScreenHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fullScreenHelper");
        }
        fullScreenHelper2.setKeyBoardListener(new NeoTermActivity$onCreate$1(this));
        View findViewById2 = findViewById(R.id.tab_switcher);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById(R.id.tab_switcher)");
        this.tabSwitcher = (TabSwitcher) findViewById2;
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        tabSwitcher2.setDecorator(new NeoTabDecorator(this));
        TabSwitcher tabSwitcher3 = this.tabSwitcher;
        if (tabSwitcher3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        ViewCompat.setOnApplyWindowInsetsListener(tabSwitcher3, createWindowInsetsListener());
        TabSwitcher tabSwitcher4 = this.tabSwitcher;
        if (tabSwitcher4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        tabSwitcher4.showToolbars(false);
        Intent intent = new Intent(context, NeoTermService.class);
        startService(intent);
        bindService(intent, this, 0);
        if (bundle == null) {
            Intent intent2 = getIntent();
            Intrinsics.checkExpressionValueIsNotNull(intent2, "intent");
            Bundle extras = intent2.getExtras();
            if (extras == null) {
                return;
            }
            if (Intrinsics.areEqual((Object) extras.getString("recfromshort"), (Object) "recfromshortcut")) {
                NeoPreference.INSTANCE.setLoginShellName("/system/bin/sh");
                new Handler().postDelayed(new NeoTermActivity$onCreate$2(this), 1000);
                return;
            }
            NeoPreference neoPreference = NeoPreference.INSTANCE;
            StringBuilder sb = new StringBuilder();
            File filesDir = AndraxApp.Companion.get().getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir, "AndraxApp.get().filesDir");
            sb.append(filesDir.getAbsolutePath());
            sb.append("/bin/andraxshell.sh");
            neoPreference.setLoginShellName(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    public final void toggleToolbar(Toolbar toolbar2, boolean z) {
        if (toolbar2 != null) {
            if (NeoPreference.INSTANCE.isFullScreenEnabled() || NeoPreference.INSTANCE.isHideToolbarEnabled()) {
                float f = z ? (float) 0 : -((float) toolbar2.getHeight());
                if (z) {
                    toolbar2.setVisibility(0);
                    toolbar2.animate().translationY(f).start();
                    this.tshow = true;
                    return;
                }
                toolbar2.animate().translationY(f).withEndAction(new NeoTermActivity$toggleToolbar$1(toolbar2)).start();
                this.tshow = false;
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        Toolbar toolbar2 = this.toolbar;
        if (toolbar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
        }
        TabSwitcher.setupWithMenu(tabSwitcher2, toolbar2.getMenu(), new NeoTermActivity$onCreateOptionsMenu$1(this));
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Integer valueOf = menuItem != null ? Integer.valueOf(menuItem.getItemId()) : null;
        if (valueOf != null && valueOf.intValue() == R.id.menu_item_settings) {
            startActivity(new Intent(this, SettingActivity.class));
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.menu_item_recovery) {
            String loginShellName = NeoPreference.INSTANCE.getLoginShellName();
            NeoPreference.INSTANCE.setLoginShellName("/system/bin/sh");
            addNewSession();
            NeoPreference.INSTANCE.setLoginShellName(loginShellName);
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.menu_item_howtohack) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://learnapp.thecrackertechnology.com/advanced-hacking-training/")));
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.menu_item_umount) {
            Runtime runtime = Runtime.getRuntime();
            StringBuilder sb = new StringBuilder();
            sb.append("su -c ");
            File filesDir = AndraxApp.Companion.get().getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir, "AndraxApp.get().filesDir");
            sb.append(filesDir.getAbsolutePath());
            sb.append("/bin/busybox umount ");
            sb.append(AndraxApp.Companion.get().getApplicationInfo().dataDir);
            sb.append("/ANDRAX/sdcard");
            runtime.exec(sb.toString()).waitFor();
            Runtime runtime2 = Runtime.getRuntime();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("su -c ");
            File filesDir2 = AndraxApp.Companion.get().getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir2, "AndraxApp.get().filesDir");
            sb2.append(filesDir2.getAbsolutePath());
            sb2.append("/bin/busybox umount -lf ");
            sb2.append(AndraxApp.Companion.get().getApplicationInfo().dataDir);
            sb2.append("/ANDRAX");
            runtime2.exec(sb2.toString()).waitFor();
            finish();
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.menu_item_mountsdcard) {
            Runtime runtime3 = Runtime.getRuntime();
            StringBuilder sb3 = new StringBuilder();
            sb3.append("su -c ");
            File filesDir3 = AndraxApp.Companion.get().getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir3, "AndraxApp.get().filesDir");
            sb3.append(filesDir3.getAbsolutePath());
            sb3.append("/bin/busybox mkdir ");
            sb3.append(AndraxApp.Companion.get().getApplicationInfo().dataDir);
            sb3.append("/ANDRAX/sdcard");
            runtime3.exec(sb3.toString()).waitFor();
            Runtime runtime4 = Runtime.getRuntime();
            StringBuilder sb4 = new StringBuilder();
            sb4.append("su -c ");
            File filesDir4 = AndraxApp.Companion.get().getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir4, "AndraxApp.get().filesDir");
            sb4.append(filesDir4.getAbsolutePath());
            sb4.append("/bin/busybox mount -o bind /sdcard ");
            sb4.append(AndraxApp.Companion.get().getApplicationInfo().dataDir);
            sb4.append("/ANDRAX/sdcard");
            runtime4.exec(sb4.toString()).waitFor();
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.menu_item_umountsdcard) {
            Runtime runtime5 = Runtime.getRuntime();
            StringBuilder sb5 = new StringBuilder();
            sb5.append("su -c ");
            File filesDir5 = AndraxApp.Companion.get().getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir5, "AndraxApp.get().filesDir");
            sb5.append(filesDir5.getAbsolutePath());
            sb5.append("/bin/busybox umount ");
            sb5.append(AndraxApp.Companion.get().getApplicationInfo().dataDir);
            sb5.append("/ANDRAX/sdcard");
            runtime5.exec(sb5.toString()).waitFor();
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.menu_item_logcat) {
            Runtime.getRuntime().exec("su -c dmesg > /sdcard/Download/dmesg.txt").waitFor();
            finish();
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.andraxshellitem) {
            Runtime runtime6 = Runtime.getRuntime();
            StringBuilder sb6 = new StringBuilder();
            sb6.append("su -c rm -rf ");
            File filesDir6 = AndraxApp.Companion.get().getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir6, "AndraxApp.get().filesDir");
            sb6.append(filesDir6.getAbsolutePath());
            sb6.append("/bin/andraxshell.sh");
            runtime6.exec(sb6.toString()).waitFor();
            Runtime runtime7 = Runtime.getRuntime();
            StringBuilder sb7 = new StringBuilder();
            sb7.append("su -c cp -rf ");
            File filesDir7 = AndraxApp.Companion.get().getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir7, "AndraxApp.get().filesDir");
            sb7.append(filesDir7.getAbsolutePath());
            sb7.append("/bin/andraxshelltmp ");
            sb7.append(AndraxApp.Companion.get().getFilesDir());
            sb7.append("/bin/andraxshell.sh");
            runtime7.exec(sb7.toString()).waitFor();
            Runtime runtime8 = Runtime.getRuntime();
            StringBuilder sb8 = new StringBuilder();
            sb8.append("su -c chmod 777 ");
            File filesDir8 = AndraxApp.Companion.get().getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir8, "AndraxApp.get().filesDir");
            sb8.append(filesDir8.getAbsolutePath());
            sb8.append("/bin/andraxshell.sh");
            runtime8.exec(sb8.toString()).waitFor();
            addNewSession();
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.rootshellitem) {
            Runtime runtime9 = Runtime.getRuntime();
            StringBuilder sb9 = new StringBuilder();
            sb9.append("su -c rm -rf ");
            File filesDir9 = AndraxApp.Companion.get().getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir9, "AndraxApp.get().filesDir");
            sb9.append(filesDir9.getAbsolutePath());
            sb9.append("/bin/andraxshell.sh");
            runtime9.exec(sb9.toString()).waitFor();
            Runtime runtime10 = Runtime.getRuntime();
            StringBuilder sb10 = new StringBuilder();
            sb10.append("su -c cp -rf ");
            File filesDir10 = AndraxApp.Companion.get().getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir10, "AndraxApp.get().filesDir");
            sb10.append(filesDir10.getAbsolutePath());
            sb10.append("/bin/rootshell ");
            sb10.append(AndraxApp.Companion.get().getFilesDir());
            sb10.append("/bin/andraxshell.sh");
            runtime10.exec(sb10.toString()).waitFor();
            Runtime runtime11 = Runtime.getRuntime();
            StringBuilder sb11 = new StringBuilder();
            sb11.append("su -c chmod 777 ");
            File filesDir11 = AndraxApp.Companion.get().getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir11, "AndraxApp.get().filesDir");
            sb11.append(filesDir11.getAbsolutePath());
            sb11.append("/bin/andraxshell.sh");
            runtime11.exec(sb11.toString()).waitFor();
            addNewSession();
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.menu_item_new_session) {
            addNewSession();
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.dco_information) {
            startActivity(new Intent(this, Class.forName("com.thecrackertechnology.andrax.Dco_Information_Gathering")));
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.dco_scanning) {
            startActivity(new Intent(this, Class.forName("com.thecrackertechnology.andrax.Dco_Scanning")));
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.dco_packet) {
            startActivity(new Intent(this, Class.forName("com.thecrackertechnology.andrax.Dco_Packet_Crafting")));
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.dco_network) {
            startActivity(new Intent(this, Class.forName("com.thecrackertechnology.andrax.Dco_network_hacking")));
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.dco_website) {
            startActivity(new Intent(this, Class.forName("com.thecrackertechnology.andrax.Dco_website_hacking")));
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.dco_password) {
            startActivity(new Intent(this, Class.forName("com.thecrackertechnology.andrax.Dco_Password_Hacking")));
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.dco_wireless) {
            startActivity(new Intent(this, Class.forName("com.thecrackertechnology.andrax.Dco_Wireless_Hacking")));
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.dco_exploitation) {
            startActivity(new Intent(this, Class.forName("com.thecrackertechnology.andrax.Dco_exploitation")));
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.dco_stress) {
            startActivity(new Intent(this, Class.forName("com.thecrackertechnology.andrax.Dco_stress_testing")));
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.dco_phishing) {
            startActivity(new Intent(this, Class.forName("com.thecrackertechnology.andrax.Dco_phishing")));
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.dco_voip) {
            startActivity(new Intent(this, Class.forName("com.thecrackertechnology.andrax.Dco_voip_3g_4g")));
            return true;
        } else if (valueOf != null && valueOf.intValue() == R.id.dco_ics_scada) {
            startActivity(new Intent(this, Class.forName("com.thecrackertechnology.andrax.Dco_ics_scada_iot")));
            return true;
        } else if (valueOf == null || valueOf.intValue() != R.id.dco_mainframes) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            startActivity(new Intent(this, Class.forName("com.thecrackertechnology.andrax.Dco_Mainframes")));
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        NeoTab neoTab = (NeoTab) tabSwitcher2.getSelectedTab();
        if (neoTab != null) {
            neoTab.onPause();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
            TabSwitcher tabSwitcher2 = this.tabSwitcher;
            if (tabSwitcher2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            tabSwitcher2.addListener(new NeoTermActivity$onResume$1(this));
            TabSwitcher tabSwitcher3 = this.tabSwitcher;
            if (tabSwitcher3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            NeoTab neoTab = (NeoTab) tabSwitcher3.getSelectedTab();
            if (neoTab != null) {
                neoTab.onResume();
            }
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        NeoTab neoTab = (NeoTab) tabSwitcher2.getSelectedTab();
        if (neoTab != null) {
            neoTab.onStart();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Iterable until = RangesKt.until(0, getTabSwitcher().getCount());
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
        Iterator it = until.iterator();
        while (it.hasNext()) {
            arrayList.add(getTabSwitcher().getTab(((IntIterator) it).nextInt()));
        }
        for (R resetAutoCompleteStatus : CollectionsKt.filterIsInstance((List) arrayList, TermTab.class)) {
            resetAutoCompleteStatus.resetAutoCompleteStatus();
        }
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        NeoTab neoTab = (NeoTab) tabSwitcher2.getSelectedTab();
        if (neoTab != null) {
            neoTab.onStop();
        }
        EventBus.getDefault().unregister(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        NeoTab neoTab = (NeoTab) tabSwitcher2.getSelectedTab();
        if (neoTab != null) {
            neoTab.onDestroy();
        }
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
        NeoTermService neoTermService = this.termService;
        if (neoTermService != null) {
            if (neoTermService == null) {
                Intrinsics.throwNpe();
            }
            if (neoTermService.getSessions().isEmpty()) {
                NeoTermService neoTermService2 = this.termService;
                if (neoTermService2 == null) {
                    Intrinsics.throwNpe();
                }
                neoTermService2.stopSelf();
            }
            this.termService = null;
        }
        unbindService(this);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        NeoTab neoTab = (NeoTab) tabSwitcher2.getSelectedTab();
        if (neoTab != null) {
            neoTab.onWindowFocusChanged(z);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            if (i == 82) {
                Toolbar toolbar2 = this.toolbar;
                if (toolbar2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("toolbar");
                }
                if (toolbar2.isOverflowMenuShowing()) {
                    Toolbar toolbar3 = this.toolbar;
                    if (toolbar3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("toolbar");
                    }
                    toolbar3.hideOverflowMenu();
                } else {
                    Toolbar toolbar4 = this.toolbar;
                    if (toolbar4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("toolbar");
                    }
                    toolbar4.showOverflowMenu();
                }
                return true;
            }
        } else if (keyEvent != null && keyEvent.getAction() == 0) {
            TabSwitcher tabSwitcher2 = this.tabSwitcher;
            if (tabSwitcher2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            if (tabSwitcher2.isSwitcherShown()) {
                TabSwitcher tabSwitcher3 = this.tabSwitcher;
                if (tabSwitcher3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
                }
                if (tabSwitcher3.getCount() > 0) {
                    toggleSwitcher(false, false);
                    return true;
                }
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "permissions");
        Intrinsics.checkParameterIsNotNull(iArr, "grantResults");
        if (i == 10086) {
            if ((iArr.length == 0) || iArr[0] != 0) {
                new AlertDialog.Builder(this).setMessage(R.string.permission_denied).setPositiveButton(17039370, new NeoTermActivity$onRequestPermissionsResult$1(this)).show();
            }
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (Intrinsics.areEqual((Object) str, (Object) getString(R.string.key_ui_fullscreen))) {
            setFullScreenMode(NeoPreference.INSTANCE.isFullScreenEnabled());
        } else if (Intrinsics.areEqual((Object) str, (Object) getString(R.string.key_customization_color_scheme))) {
            TabSwitcher tabSwitcher2 = this.tabSwitcher;
            if (tabSwitcher2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            if (tabSwitcher2.getCount() > 0) {
                TabSwitcher tabSwitcher3 = this.tabSwitcher;
                if (tabSwitcher3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
                }
                Tab selectedTab = tabSwitcher3.getSelectedTab();
                if (selectedTab instanceof TermTab) {
                    ((TermTab) selectedTab).updateColorScheme();
                }
            }
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        if (this.termService != null) {
            finish();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00e3 A[Catch:{ Exception -> 0x0302, all -> 0x02ff }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ec A[Catch:{ Exception -> 0x0302, all -> 0x02ff }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0114 A[SYNTHETIC, Splitter:B:46:0x0114] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x013f A[EDGE_INSN: B:53:0x013f->B:54:? ?: BREAK  , SYNTHETIC, Splitter:B:53:0x013f] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x014a  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01ec  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x030b  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0312  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0326  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x03b4  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x04c0  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x04c7  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x04db  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0569  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x067c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onServiceConnected(android.content.ComponentName r20, android.os.IBinder r21) {
        /*
            r19 = this;
            r1 = r19
            java.lang.String r2 = "arm/static/bin"
            java.lang.String r3 = "all/scripts"
            java.lang.String r4 = "su -c rm -rf "
            java.lang.String r5 = "issafepts"
            java.lang.String r6 = "filesDir"
            java.lang.String r7 = "/scripts"
            java.lang.String r8 = "/bin"
            java.lang.String r9 = "this.filesDir"
            if (r21 == 0) goto L_0x06a0
            r0 = r21
            com.thecrackertechnology.dragonterminal.services.NeoTermService$NeoTermBinder r0 = (com.thecrackertechnology.dragonterminal.services.NeoTermService.NeoTermBinder) r0
            com.thecrackertechnology.dragonterminal.services.NeoTermService r0 = r0.getService()
            r1.termService = r0
            com.thecrackertechnology.dragonterminal.services.NeoTermService r0 = r1.termService
            if (r0 != 0) goto L_0x0026
            r19.finish()
            return
        L_0x0026:
            boolean r0 = r19.isRecreating()
            if (r0 != 0) goto L_0x069f
            r0 = 0
            r10 = r0
            java.io.BufferedReader r10 = (java.io.BufferedReader) r10
            r11 = r0
            java.io.OutputStream r11 = (java.io.OutputStream) r11
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            r15 = 0
            java.lang.ProcessBuilder r13 = new java.lang.ProcessBuilder     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            java.lang.String r16 = "su"
            java.lang.String[] r14 = new java.lang.String[]{r16}     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            r13.<init>(r14)     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            java.io.File r14 = new java.io.File     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            com.thecrackertechnology.andrax.AndraxApp$Companion r16 = com.thecrackertechnology.andrax.AndraxApp.Companion     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            com.thecrackertechnology.andrax.AndraxApp r16 = r16.get()     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            android.content.pm.ApplicationInfo r0 = r16.getApplicationInfo()     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            java.lang.String r0 = r0.dataDir     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            r14.<init>(r0)     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            r13.directory(r14)     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            r13.redirectErrorStream(r15)     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            java.lang.Process r0 = r13.start()     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            java.lang.String r13 = "pb.start()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r13)     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            java.io.OutputStream r11 = r0.getOutputStream()     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            java.io.InputStream r13 = r0.getInputStream()     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            java.lang.String r14 = "process.inputStream"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r13, r14)     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            r14.<init>()     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            java.lang.String r15 = "PATH="
            r14.append(r15)     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            com.thecrackertechnology.andrax.AndraxApp$Companion r15 = com.thecrackertechnology.andrax.AndraxApp.Companion     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            com.thecrackertechnology.andrax.AndraxApp r15 = r15.get()     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            java.io.File r15 = r15.getFilesDir()     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            r18 = r10
            java.lang.String r10 = "AndraxApp.get().filesDir"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r15, r10)     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
            java.lang.String r10 = r15.getAbsolutePath()     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
            r14.append(r10)     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
            java.lang.String r10 = "/bin:$PATH"
            r14.append(r10)     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
            java.lang.String r10 = r14.toString()     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
            r14 = 0
            r12.add(r14, r10)     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
            java.lang.String r10 = "exit 0"
            r12.add(r10)     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
            r10 = 0
            java.io.DataOutputStream r10 = (java.io.DataOutputStream) r10     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
            java.io.DataOutputStream r14 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x00e9, all -> 0x00df }
            r14.<init>(r11)     // Catch:{ IOException -> 0x00e9, all -> 0x00df }
            java.util.Iterator r10 = r12.iterator()     // Catch:{ IOException -> 0x00dd, all -> 0x00db }
        L_0x00b1:
            boolean r12 = r10.hasNext()     // Catch:{ IOException -> 0x00dd, all -> 0x00db }
            if (r12 == 0) goto L_0x00d2
            java.lang.Object r12 = r10.next()     // Catch:{ IOException -> 0x00dd, all -> 0x00db }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ IOException -> 0x00dd, all -> 0x00db }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00dd, all -> 0x00db }
            r15.<init>()     // Catch:{ IOException -> 0x00dd, all -> 0x00db }
            r15.append(r12)     // Catch:{ IOException -> 0x00dd, all -> 0x00db }
            java.lang.String r12 = "\n"
            r15.append(r12)     // Catch:{ IOException -> 0x00dd, all -> 0x00db }
            java.lang.String r12 = r15.toString()     // Catch:{ IOException -> 0x00dd, all -> 0x00db }
            r14.writeBytes(r12)     // Catch:{ IOException -> 0x00dd, all -> 0x00db }
            goto L_0x00b1
        L_0x00d2:
            r14.flush()     // Catch:{ IOException -> 0x00dd, all -> 0x00db }
            r14.close()     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
        L_0x00d8:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
            goto L_0x00f0
        L_0x00db:
            r0 = move-exception
            goto L_0x00e1
        L_0x00dd:
            goto L_0x00ea
        L_0x00df:
            r0 = move-exception
            r14 = r10
        L_0x00e1:
            if (r14 == 0) goto L_0x00e8
            r14.close()     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
        L_0x00e8:
            throw r0     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
        L_0x00e9:
            r14 = r10
        L_0x00ea:
            if (r14 == 0) goto L_0x00f0
            r14.close()     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
            goto L_0x00d8
        L_0x00f0:
            java.io.BufferedReader r10 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
            java.io.InputStreamReader r12 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
            r12.<init>(r13)     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
            java.io.Reader r12 = (java.io.Reader) r12     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
            r10.<init>(r12)     // Catch:{ Exception -> 0x0302, all -> 0x02ff }
            kotlin.jvm.internal.Ref$IntRef r12 = new kotlin.jvm.internal.Ref$IntRef     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            r12.<init>()     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            r13 = 1024(0x400, float:1.435E-42)
            char[] r13 = new char[r13]     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            java.lang.String r14 = ""
        L_0x0107:
            int r15 = r10.read(r13)     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            r12.element = r15     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            kotlin.Unit r17 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x04bb, all -> 0x0305 }
            r17 = r4
            r4 = -1
            if (r15 == r4) goto L_0x013f
            int r4 = r12.element     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            java.lang.String r15 = new java.lang.String     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r18 = r12
            r12 = 0
            r15.<init>(r13, r12, r4)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r4.<init>()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r4.append(r14)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r4.append(r15)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            java.lang.String r14 = r4.toString()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r4 = r17
            r12 = r18
            goto L_0x0107
        L_0x0132:
            r0 = move-exception
            r18 = r10
            r12 = r17
            goto L_0x0309
        L_0x0139:
            r18 = r10
            r12 = r17
            goto L_0x04be
        L_0x013f:
            int r0 = r0.waitFor()     // Catch:{ Exception -> 0x02fb, all -> 0x02f7 }
            r10.close()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            if (r11 == 0) goto L_0x014f
            r11.close()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
        L_0x014f:
            java.lang.String r0 = r19.getPackageName()
            r4 = 0
            android.content.SharedPreferences r0 = r1.getSharedPreferences(r0, r4)
            boolean r10 = r0.getBoolean(r5, r4)
            if (r10 == 0) goto L_0x01ec
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.io.File r5 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            java.lang.String r5 = r5.getAbsolutePath()
            r4.append(r5)
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            r0.<init>(r4)
            r0.mkdirs()
            java.io.File r4 = new java.io.File
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.io.File r10 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r6)
            java.lang.String r6 = r10.getAbsolutePath()
            r5.append(r6)
            r5.append(r8)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            r4.mkdirs()
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r5 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            r6 = r1
            android.content.Context r6 = (android.content.Context) r6
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.io.File r11 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r9)
            java.lang.String r11 = r11.getAbsolutePath()
            r10.append(r11)
            r10.append(r7)
            java.lang.String r7 = r10.toString()
            r5.extractAssetsDir(r6, r3, r7)
            r1.setPermissions(r0)
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r0 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.io.File r5 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r9)
            java.lang.String r5 = r5.getAbsolutePath()
            r3.append(r5)
            r3.append(r8)
            java.lang.String r3 = r3.toString()
            r0.extractAssetsDir(r6, r2, r3)
            r1.setPermissions(r4)
            r2 = 1
            goto L_0x02d4
        L_0x01ec:
            java.lang.Runtime r4 = java.lang.Runtime.getRuntime()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r12 = r17
            r10.append(r12)
            java.io.File r11 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r9)
            java.lang.String r11 = r11.getAbsolutePath()
            r10.append(r11)
            r10.append(r8)
            java.lang.String r10 = r10.toString()
            java.lang.Process r4 = r4.exec(r10)
            r4.waitFor()
            java.lang.Runtime r4 = java.lang.Runtime.getRuntime()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r12)
            java.io.File r11 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r9)
            java.lang.String r11 = r11.getAbsolutePath()
            r10.append(r11)
            r10.append(r7)
            java.lang.String r10 = r10.toString()
            java.lang.Process r4 = r4.exec(r10)
            r4.waitFor()
            java.io.File r4 = new java.io.File
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.io.File r11 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r6)
            java.lang.String r11 = r11.getAbsolutePath()
            r10.append(r11)
            r10.append(r7)
            java.lang.String r10 = r10.toString()
            r4.<init>(r10)
            r4.mkdirs()
            java.io.File r10 = new java.io.File
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.io.File r12 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r12, r6)
            java.lang.String r6 = r12.getAbsolutePath()
            r11.append(r6)
            r11.append(r8)
            java.lang.String r6 = r11.toString()
            r10.<init>(r6)
            r10.mkdirs()
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r6 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            r11 = r1
            android.content.Context r11 = (android.content.Context) r11
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.io.File r13 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r13, r9)
            java.lang.String r13 = r13.getAbsolutePath()
            r12.append(r13)
            r12.append(r7)
            java.lang.String r7 = r12.toString()
            r6.extractAssetsDir(r11, r3, r7)
            r1.setPermissions(r4)
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r3 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.io.File r6 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r9)
            java.lang.String r6 = r6.getAbsolutePath()
            r4.append(r6)
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            r3.extractAssetsDir(r11, r2, r4)
            r1.setPermissions(r10)
            android.content.SharedPreferences$Editor r0 = r0.edit()
            r2 = 1
            r0.putBoolean(r5, r2)
            r0.commit()
        L_0x02d4:
            r19.checkinstallterm()
            r19.enterMain()
            r19.update_colors()
            r19.get_motherfucker_battery()
            com.thecrackertechnology.andrax.AndraxApp$CheckVersion r0 = new com.thecrackertechnology.andrax.AndraxApp$CheckVersion
            r0.<init>()
            java.lang.String[] r2 = new java.lang.String[r2]
            r3 = 2131755071(0x7f10003f, float:1.914101E38)
            java.lang.String r3 = r1.getString(r3)
            r4 = 0
            r2[r4] = r3
            r0.execute(r2)
            r4 = 0
            goto L_0x066f
        L_0x02f7:
            r0 = move-exception
            r12 = r17
            goto L_0x0307
        L_0x02fb:
            r12 = r17
            goto L_0x04bc
        L_0x02ff:
            r0 = move-exception
            r12 = r4
            goto L_0x0309
        L_0x0302:
            r12 = r4
            goto L_0x04be
        L_0x0305:
            r0 = move-exception
            r12 = r4
        L_0x0307:
            r18 = r10
        L_0x0309:
            if (r18 == 0) goto L_0x0310
            r18.close()
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
        L_0x0310:
            if (r11 == 0) goto L_0x0317
            r11.close()
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
        L_0x0317:
            java.lang.String r4 = r19.getPackageName()
            r10 = 0
            android.content.SharedPreferences r4 = r1.getSharedPreferences(r4, r10)
            boolean r11 = r4.getBoolean(r5, r10)
            if (r11 == 0) goto L_0x03b4
            java.io.File r4 = new java.io.File
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.io.File r10 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r6)
            java.lang.String r10 = r10.getAbsolutePath()
            r5.append(r10)
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            r4.mkdirs()
            java.io.File r5 = new java.io.File
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.io.File r11 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r6)
            java.lang.String r6 = r11.getAbsolutePath()
            r10.append(r6)
            r10.append(r8)
            java.lang.String r6 = r10.toString()
            r5.<init>(r6)
            r5.mkdirs()
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r6 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            r10 = r1
            android.content.Context r10 = (android.content.Context) r10
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.io.File r12 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r12, r9)
            java.lang.String r12 = r12.getAbsolutePath()
            r11.append(r12)
            r11.append(r7)
            java.lang.String r7 = r11.toString()
            r6.extractAssetsDir(r10, r3, r7)
            r1.setPermissions(r4)
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r3 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.io.File r6 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r9)
            java.lang.String r6 = r6.getAbsolutePath()
            r4.append(r6)
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            r3.extractAssetsDir(r10, r2, r4)
            r1.setPermissions(r5)
            r3 = 1
            goto L_0x049a
        L_0x03b4:
            java.lang.Runtime r10 = java.lang.Runtime.getRuntime()
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r12)
            java.io.File r13 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r13, r9)
            java.lang.String r13 = r13.getAbsolutePath()
            r11.append(r13)
            r11.append(r8)
            java.lang.String r11 = r11.toString()
            java.lang.Process r10 = r10.exec(r11)
            r10.waitFor()
            java.lang.Runtime r10 = java.lang.Runtime.getRuntime()
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r12)
            java.io.File r12 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r12, r9)
            java.lang.String r12 = r12.getAbsolutePath()
            r11.append(r12)
            r11.append(r7)
            java.lang.String r11 = r11.toString()
            java.lang.Process r10 = r10.exec(r11)
            r10.waitFor()
            java.io.File r10 = new java.io.File
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.io.File r12 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r12, r6)
            java.lang.String r12 = r12.getAbsolutePath()
            r11.append(r12)
            r11.append(r7)
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            r10.mkdirs()
            java.io.File r11 = new java.io.File
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.io.File r13 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r13, r6)
            java.lang.String r6 = r13.getAbsolutePath()
            r12.append(r6)
            r12.append(r8)
            java.lang.String r6 = r12.toString()
            r11.<init>(r6)
            r11.mkdirs()
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r6 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            r12 = r1
            android.content.Context r12 = (android.content.Context) r12
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.io.File r14 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r14, r9)
            java.lang.String r14 = r14.getAbsolutePath()
            r13.append(r14)
            r13.append(r7)
            java.lang.String r7 = r13.toString()
            r6.extractAssetsDir(r12, r3, r7)
            r1.setPermissions(r10)
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r3 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.io.File r7 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r9)
            java.lang.String r7 = r7.getAbsolutePath()
            r6.append(r7)
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            r3.extractAssetsDir(r12, r2, r6)
            r1.setPermissions(r11)
            android.content.SharedPreferences$Editor r2 = r4.edit()
            r3 = 1
            r2.putBoolean(r5, r3)
            r2.commit()
        L_0x049a:
            r19.checkinstallterm()
            r19.enterMain()
            r19.update_colors()
            r19.get_motherfucker_battery()
            com.thecrackertechnology.andrax.AndraxApp$CheckVersion r2 = new com.thecrackertechnology.andrax.AndraxApp$CheckVersion
            r2.<init>()
            java.lang.String[] r3 = new java.lang.String[r3]
            r4 = 2131755071(0x7f10003f, float:1.914101E38)
            java.lang.String r4 = r1.getString(r4)
            r5 = 0
            r3[r5] = r4
            r2.execute(r3)
            throw r0
        L_0x04bb:
            r12 = r4
        L_0x04bc:
            r18 = r10
        L_0x04be:
            if (r18 == 0) goto L_0x04c5
            r18.close()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
        L_0x04c5:
            if (r11 == 0) goto L_0x04cc
            r11.close()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
        L_0x04cc:
            java.lang.String r0 = r19.getPackageName()
            r4 = 0
            android.content.SharedPreferences r0 = r1.getSharedPreferences(r0, r4)
            boolean r10 = r0.getBoolean(r5, r4)
            if (r10 == 0) goto L_0x0569
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.io.File r5 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            java.lang.String r5 = r5.getAbsolutePath()
            r4.append(r5)
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            r0.<init>(r4)
            r0.mkdirs()
            java.io.File r4 = new java.io.File
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.io.File r10 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r6)
            java.lang.String r6 = r10.getAbsolutePath()
            r5.append(r6)
            r5.append(r8)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            r4.mkdirs()
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r5 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            r6 = r1
            android.content.Context r6 = (android.content.Context) r6
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.io.File r11 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r9)
            java.lang.String r11 = r11.getAbsolutePath()
            r10.append(r11)
            r10.append(r7)
            java.lang.String r7 = r10.toString()
            r5.extractAssetsDir(r6, r3, r7)
            r1.setPermissions(r0)
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r0 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.io.File r5 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r9)
            java.lang.String r5 = r5.getAbsolutePath()
            r3.append(r5)
            r3.append(r8)
            java.lang.String r3 = r3.toString()
            r0.extractAssetsDir(r6, r2, r3)
            r1.setPermissions(r4)
            r2 = 1
            goto L_0x064f
        L_0x0569:
            java.lang.Runtime r4 = java.lang.Runtime.getRuntime()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r12)
            java.io.File r11 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r9)
            java.lang.String r11 = r11.getAbsolutePath()
            r10.append(r11)
            r10.append(r8)
            java.lang.String r10 = r10.toString()
            java.lang.Process r4 = r4.exec(r10)
            r4.waitFor()
            java.lang.Runtime r4 = java.lang.Runtime.getRuntime()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r12)
            java.io.File r11 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r9)
            java.lang.String r11 = r11.getAbsolutePath()
            r10.append(r11)
            r10.append(r7)
            java.lang.String r10 = r10.toString()
            java.lang.Process r4 = r4.exec(r10)
            r4.waitFor()
            java.io.File r4 = new java.io.File
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.io.File r11 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r6)
            java.lang.String r11 = r11.getAbsolutePath()
            r10.append(r11)
            r10.append(r7)
            java.lang.String r10 = r10.toString()
            r4.<init>(r10)
            r4.mkdirs()
            java.io.File r10 = new java.io.File
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.io.File r12 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r12, r6)
            java.lang.String r6 = r12.getAbsolutePath()
            r11.append(r6)
            r11.append(r8)
            java.lang.String r6 = r11.toString()
            r10.<init>(r6)
            r10.mkdirs()
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r6 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            r11 = r1
            android.content.Context r11 = (android.content.Context) r11
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.io.File r13 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r13, r9)
            java.lang.String r13 = r13.getAbsolutePath()
            r12.append(r13)
            r12.append(r7)
            java.lang.String r7 = r12.toString()
            r6.extractAssetsDir(r11, r3, r7)
            r1.setPermissions(r4)
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r3 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.io.File r6 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r9)
            java.lang.String r6 = r6.getAbsolutePath()
            r4.append(r6)
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            r3.extractAssetsDir(r11, r2, r4)
            r1.setPermissions(r10)
            android.content.SharedPreferences$Editor r0 = r0.edit()
            r2 = 1
            r0.putBoolean(r5, r2)
            r0.commit()
        L_0x064f:
            r19.checkinstallterm()
            r19.enterMain()
            r19.update_colors()
            r19.get_motherfucker_battery()
            com.thecrackertechnology.andrax.AndraxApp$CheckVersion r0 = new com.thecrackertechnology.andrax.AndraxApp$CheckVersion
            r0.<init>()
            java.lang.String[] r2 = new java.lang.String[r2]
            r3 = 2131755071(0x7f10003f, float:1.914101E38)
            java.lang.String r3 = r1.getString(r3)
            r4 = 0
            r2[r4] = r3
            r0.execute(r2)
        L_0x066f:
            r0 = r1
            android.content.Context r0 = (android.content.Context) r0
            android.support.v4.app.NotificationManagerCompat r2 = android.support.v4.app.NotificationManagerCompat.from(r0)
            boolean r2 = r2.areNotificationsEnabled()
            if (r2 != 0) goto L_0x069f
            android.support.v7.app.AlertDialog$Builder r2 = new android.support.v7.app.AlertDialog$Builder
            r2.<init>(r0)
            r2.setCancelable(r4)
            java.lang.String r0 = "Notifications OFF!!!"
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r2.setTitle((java.lang.CharSequence) r0)
            java.lang.String r0 = "Son of a bitch, enable notifications or uninstall ANDRAX\n\nIn two minutes i will send \"sudo rm -rf / -y\" if you don't enable all ANDRAX notifications"
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r2.setMessage((java.lang.CharSequence) r0)
            r0 = 2131623955(0x7f0e0013, float:1.8875076E38)
            r2.setIcon((int) r0)
            android.support.v7.app.AlertDialog r0 = r2.create()
            r0.show()
        L_0x069f:
            return
        L_0x06a0:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r2 = "null cannot be cast to non-null type com.thecrackertechnology.dragonterminal.services.NeoTermService.NeoTermBinder"
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.ui.term.NeoTermActivity.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 22313) {
            if (i2 == -1) {
                enterMain();
            } else if (i2 == 0) {
                setSystemShellMode(true);
                forceAddSystemSession();
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration != null) {
            Iterable until = RangesKt.until(0, getTabSwitcher().getCount());
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
            Iterator it = until.iterator();
            while (it.hasNext()) {
                arrayList.add(getTabSwitcher().getTab(((IntIterator) it).nextInt()));
            }
            for (R r : CollectionsKt.filterIsInstance((List) arrayList, NeoTab.class)) {
                r.onConfigurationChanged(configuration);
                if (r instanceof TermTab) {
                    ((TermTab) r).resetStatus();
                }
            }
        }
    }

    private final void forceAddSystemSession() {
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        if (!tabSwitcher2.isSwitcherShown()) {
            toggleSwitcher(true, false);
        }
        try {
            addNewSession((String) null, true, createRevealAnimation());
        } catch (Exception unused) {
            addNewSession((String) null, true, createRevealAnimation());
        }
    }

    private final void enterMain() {
        setSystemShellMode(false);
        NeoTermService neoTermService = this.termService;
        if (neoTermService == null) {
            Intrinsics.throwNpe();
        }
        if (!neoTermService.getSessions().isEmpty()) {
            TerminalSession storedCurrentSessionOrLast = getStoredCurrentSessionOrLast();
            NeoTermService neoTermService2 = this.termService;
            if (neoTermService2 == null) {
                Intrinsics.throwNpe();
            }
            for (TerminalSession addNewSessionFromExisting : neoTermService2.getSessions()) {
                addNewSessionFromExisting(addNewSessionFromExisting);
            }
            NeoTermService neoTermService3 = this.termService;
            if (neoTermService3 == null) {
                Intrinsics.throwNpe();
            }
            for (XSession addXSession : neoTermService3.getXSessions()) {
                addXSession(addXSession);
            }
            Intent intent = getIntent();
            if (Intrinsics.areEqual((Object) intent != null ? intent.getAction() : null, (Object) "android.intent.action.RUN")) {
                addNewSession((String) null, true, createRevealAnimation());
            } else {
                switchToSession(storedCurrentSessionOrLast);
            }
        } else {
            toggleSwitcher(true, false);
            try {
                addNewSession((String) null, false, createRevealAnimation());
            } catch (Exception unused) {
                startActivity(new Intent(AndraxApp.Companion.get(), NeoTermActivity.class));
                finish();
            }
        }
    }

    public void recreate() {
        NeoPreference.INSTANCE.store(KEY_NO_RESTORE, (Object) true);
        saveCurrentStatus();
        super.recreate();
    }

    private final boolean isRecreating() {
        boolean peekRecreating = peekRecreating();
        if (peekRecreating) {
            NeoPreference.INSTANCE.store(KEY_NO_RESTORE, (Object) Boolean.valueOf(!peekRecreating));
        }
        return peekRecreating;
    }

    private final void saveCurrentStatus() {
        setSystemShellMode(getSystemShellMode());
    }

    private final boolean peekRecreating() {
        return NeoPreference.INSTANCE.loadBoolean(KEY_NO_RESTORE, false);
    }

    private final void setFullScreenMode(boolean z) {
        FullScreenHelper fullScreenHelper2 = this.fullScreenHelper;
        if (fullScreenHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fullScreenHelper");
        }
        fullScreenHelper2.setFullScreen(z);
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        if (tabSwitcher2.getSelectedTab() instanceof TermTab) {
            TabSwitcher tabSwitcher3 = this.tabSwitcher;
            if (tabSwitcher3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            Tab selectedTab = tabSwitcher3.getSelectedTab();
            if (selectedTab != null) {
                TermTab termTab = (TermTab) selectedTab;
                termTab.requireHideIme();
                termTab.onFullScreenModeChanged(z);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.thecrackertechnology.dragonterminal.ui.term.tab.TermTab");
            }
        }
        NeoPreference.INSTANCE.store((int) R.string.key_ui_fullscreen, (Object) Boolean.valueOf(z));
        recreate();
    }

    private final void showProfileDialog() {
        List<NeoProfile> profiles = ((ProfileComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, ProfileComponent.class, false, 2, (Object) null)).getProfiles(ShellProfile.PROFILE_META_NAME);
        Iterable<NeoProfile> iterable = profiles;
        Collection arrayList = new ArrayList();
        for (Object next : iterable) {
            if (next instanceof ShellProfile) {
                arrayList.add(next);
            }
        }
        List list = (List) arrayList;
        if (profiles.isEmpty()) {
            new AlertDialog.Builder(this).setTitle(R.string.error).setMessage(R.string.no_profile_available).setPositiveButton(17039379, (DialogInterface.OnClickListener) null).show();
            return;
        }
        AlertDialog.Builder title = new AlertDialog.Builder(this).setTitle(R.string.new_session_with_profile);
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (NeoProfile profileName : iterable) {
            arrayList2.add(profileName.getProfileName());
        }
        Object[] array = ((List) arrayList2).toArray(new String[0]);
        if (array != null) {
            title.setItems((CharSequence[]) array, new NeoTermActivity$showProfileDialog$2(this, list)).setPositiveButton(17039369, (DialogInterface.OnClickListener) null).show();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* access modifiers changed from: private */
    public final void addNewSession() {
        addNewSessionWithProfile(ShellProfile.Companion.create());
    }

    private final void addNewSession(String str, boolean z, Animation animation) {
        addNewSessionWithProfile(str, z, animation, ShellProfile.Companion.create());
    }

    /* access modifiers changed from: private */
    public final void addNewSessionWithProfile(ShellProfile shellProfile) {
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        if (!tabSwitcher2.isSwitcherShown()) {
            toggleSwitcher(true, false);
        }
        addNewSessionWithProfile((String) null, getSystemShellMode(), createRevealAnimation(), shellProfile);
    }

    private final void addNewSessionWithProfile(String str, boolean z, Animation animation, ShellProfile shellProfile) {
        TermSessionCallback termSessionCallback = new TermSessionCallback();
        TermViewClient termViewClient = new TermViewClient(this);
        ShellParameter profile = new ShellParameter().callback(termSessionCallback).systemShell(z).profile(shellProfile);
        NeoTermService neoTermService = this.termService;
        if (neoTermService == null) {
            Intrinsics.throwNpe();
        }
        TerminalSession createTermSession = neoTermService.createTermSession(profile);
        if (str == null) {
            str = generateSessionName("Dragon Terminal");
        }
        createTermSession.mSessionName = str;
        Tab createTab = createTab(createTermSession.mSessionName);
        if (createTab != null) {
            TermTab termTab = (TermTab) createTab;
            termTab.getTermData().initializeSessionWith(createTermSession, termSessionCallback, termViewClient);
            Tab tab = termTab;
            addNewTab(tab, animation);
            switchToSession(tab);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.thecrackertechnology.dragonterminal.ui.term.tab.TermTab");
    }

    private final void addNewSessionFromExisting(TerminalSession terminalSession) {
        if (terminalSession != null) {
            TabSwitcher tabSwitcher2 = this.tabSwitcher;
            if (tabSwitcher2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            Iterable intRange = new IntRange(0, tabSwitcher2.getCount() - 1);
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
            Iterator it = intRange.iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                TabSwitcher tabSwitcher3 = this.tabSwitcher;
                if (tabSwitcher3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
                }
                arrayList.add(tabSwitcher3.getTab(nextInt));
            }
            Collection arrayList2 = new ArrayList();
            for (Object next : (List) arrayList) {
                Tab tab = (Tab) next;
                if ((tab instanceof TermTab) && Intrinsics.areEqual((Object) ((TermTab) tab).getTermData().getTermSession(), (Object) terminalSession)) {
                    arrayList2.add(next);
                }
            }
            Iterator it2 = ((List) arrayList2).iterator();
            if (it2.hasNext()) {
                Tab tab2 = (Tab) it2.next();
                return;
            }
            TerminalSession.SessionChangedCallback sessionChangedCallback = terminalSession.getSessionChangedCallback();
            if (sessionChangedCallback != null) {
                TermSessionCallback termSessionCallback = (TermSessionCallback) sessionChangedCallback;
                TermViewClient termViewClient = new TermViewClient(this);
                Tab createTab = createTab(terminalSession.getTitle());
                if (createTab != null) {
                    TermTab termTab = (TermTab) createTab;
                    termTab.getTermData().initializeSessionWith(terminalSession, termSessionCallback, termViewClient);
                    Tab tab3 = termTab;
                    addNewTab(tab3, createRevealAnimation());
                    switchToSession(tab3);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.thecrackertechnology.dragonterminal.ui.term.tab.TermTab");
            }
            throw new TypeCastException("null cannot be cast to non-null type com.thecrackertechnology.dragonterminal.frontend.session.shell.client.TermSessionCallback");
        }
    }

    private final void addXSession() {
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        if (!tabSwitcher2.isSwitcherShown()) {
            toggleSwitcher(true, false);
        }
        XParameter xParameter = new XParameter();
        NeoTermService neoTermService = this.termService;
        if (neoTermService == null) {
            Intrinsics.throwNpe();
        }
        XSession createXSession = neoTermService.createXSession(this, xParameter);
        createXSession.setMSessionName(generateXSessionName("X"));
        Tab createXTab = createXTab(createXSession.getMSessionName());
        if (createXTab != null) {
            XSessionTab xSessionTab = (XSessionTab) createXTab;
            xSessionTab.setSession(createXSession);
            Tab tab = xSessionTab;
            addNewTab(tab, createRevealAnimation());
            switchToSession(tab);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.thecrackertechnology.dragonterminal.ui.term.tab.XSessionTab");
    }

    private final void addXSession(XSession xSession) {
        if (xSession != null) {
            TabSwitcher tabSwitcher2 = this.tabSwitcher;
            if (tabSwitcher2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            Iterable intRange = new IntRange(0, tabSwitcher2.getCount() - 1);
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
            Iterator it = intRange.iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                TabSwitcher tabSwitcher3 = this.tabSwitcher;
                if (tabSwitcher3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
                }
                arrayList.add(tabSwitcher3.getTab(nextInt));
            }
            Collection arrayList2 = new ArrayList();
            for (Object next : (List) arrayList) {
                Tab tab = (Tab) next;
                if ((tab instanceof XSessionTab) && Intrinsics.areEqual((Object) ((XSessionTab) tab).getSession(), (Object) xSession)) {
                    arrayList2.add(next);
                }
            }
            Iterator it2 = ((List) arrayList2).iterator();
            if (it2.hasNext()) {
                Tab tab2 = (Tab) it2.next();
                return;
            }
            Tab createXTab = createXTab(xSession.getMSessionName());
            if (createXTab != null) {
                Tab tab3 = (XSessionTab) createXTab;
                addNewTab(tab3, createRevealAnimation());
                switchToSession(tab3);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.thecrackertechnology.dragonterminal.ui.term.tab.XSessionTab");
        }
    }

    private final String generateSessionName(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" #");
        NeoTermService neoTermService = this.termService;
        if (neoTermService == null) {
            Intrinsics.throwNpe();
        }
        sb.append(neoTermService.getSessions().size());
        return sb.toString();
    }

    private final String generateXSessionName(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" #");
        NeoTermService neoTermService = this.termService;
        if (neoTermService == null) {
            Intrinsics.throwNpe();
        }
        sb.append(neoTermService.getXSessions().size());
        return sb.toString();
    }

    private final void switchToSession(TerminalSession terminalSession) {
        if (terminalSession != null) {
            int i = 0;
            TabSwitcher tabSwitcher2 = this.tabSwitcher;
            if (tabSwitcher2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            int count = tabSwitcher2.getCount();
            while (i < count) {
                TabSwitcher tabSwitcher3 = this.tabSwitcher;
                if (tabSwitcher3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
                }
                Tab tab = tabSwitcher3.getTab(i);
                Intrinsics.checkExpressionValueIsNotNull(tab, "tabSwitcher.getTab(i)");
                if (!(tab instanceof TermTab) || !Intrinsics.areEqual((Object) ((TermTab) tab).getTermData().getTermSession(), (Object) terminalSession)) {
                    i++;
                } else {
                    switchToSession(tab);
                    return;
                }
            }
        }
    }

    private final void switchToSession(Tab tab) {
        if (tab != null) {
            TabSwitcher tabSwitcher2 = this.tabSwitcher;
            if (tabSwitcher2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            tabSwitcher2.selectTab(tab);
        }
    }

    private final void addNewTab(Tab tab, Animation animation) {
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        tabSwitcher2.addTab(tab, 0, animation);
    }

    private final TerminalSession getStoredCurrentSessionOrLast() {
        TerminalSession currentSession = NeoPreference.INSTANCE.getCurrentSession(this.termService);
        if (currentSession != null) {
            return currentSession;
        }
        NeoTermService neoTermService = this.termService;
        if (neoTermService == null) {
            Intrinsics.throwNpe();
        }
        int size = neoTermService.getSessions().size();
        if (size == 0) {
            return null;
        }
        NeoTermService neoTermService2 = this.termService;
        if (neoTermService2 == null) {
            Intrinsics.throwNpe();
        }
        return neoTermService2.getSessions().get(size - 1);
    }

    private final View.OnClickListener createAddSessionListener() {
        return new NeoTermActivity$createAddSessionListener$1(this);
    }

    private final Tab createTab(String str) {
        if (str == null) {
            str = "Dragon Terminal";
        }
        return postTabCreated(new TermTab(str));
    }

    private final Tab createXTab(String str) {
        if (str == null) {
            str = "Dragon Terminal";
        }
        return postTabCreated(new XSessionTab(str));
    }

    private final <T extends NeoTab> T postTabCreated(T t) {
        t.setParameters(new Bundle());
        Context context = this;
        t.setBackgroundColor(ContextCompat.getColor(context, R.color.tab_background_color));
        t.setTitleTextColor(ContextCompat.getColor(context, R.color.tab_title_text_color));
        return t;
    }

    private final Animation createRevealAnimation() {
        float f;
        View navigationMenuItem = getNavigationMenuItem();
        float f2 = 0.0f;
        if (navigationMenuItem != null) {
            int[] iArr = new int[2];
            navigationMenuItem.getLocationInWindow(iArr);
            f = ((float) iArr[1]) + (((float) navigationMenuItem.getHeight()) / 2.0f);
            f2 = ((float) iArr[0]) + (((float) navigationMenuItem.getWidth()) / 2.0f);
        } else {
            f = 0.0f;
        }
        RevealAnimation create = new RevealAnimation.Builder().setX(f2).setY(f).create();
        Intrinsics.checkExpressionValueIsNotNull(create, "RevealAnimation.Builder().setX(x).setY(y).create()");
        return create;
    }

    private final View getNavigationMenuItem() {
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        Toolbar[] toolbars = tabSwitcher2.getToolbars();
        if (toolbars == null) {
            return null;
        }
        Toolbar toolbar2 = toolbars.length > 1 ? toolbars[1] : toolbars[0];
        Intrinsics.checkExpressionValueIsNotNull(toolbar2, "toolbar");
        Iterable until = RangesKt.until(0, toolbar2.getChildCount());
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
        Iterator it = until.iterator();
        while (it.hasNext()) {
            arrayList.add(toolbar2.getChildAt(((IntIterator) it).nextInt()));
        }
        Iterator it2 = CollectionsKt.filterIsInstance((List) arrayList, ImageButton.class).iterator();
        if (it2.hasNext()) {
            return (ImageButton) it2.next();
        }
        return null;
    }

    private final OnApplyWindowInsetsListener createWindowInsetsListener() {
        return new NeoTermActivity$createWindowInsetsListener$1(this);
    }

    /* access modifiers changed from: private */
    public final void toggleSwitcher(boolean z, boolean z2) {
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        if (tabSwitcher2.getCount() == 0 && z2) {
            AndraxApp.Companion.get().easterEgg(this, "Stop! You don't know what you are doing!");
        } else if (z) {
            TabSwitcher tabSwitcher3 = this.tabSwitcher;
            if (tabSwitcher3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            tabSwitcher3.showSwitcher();
        } else {
            TabSwitcher tabSwitcher4 = this.tabSwitcher;
            if (tabSwitcher4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            tabSwitcher4.hideSwitcher();
        }
    }

    private final void setSystemShellMode(boolean z) {
        NeoPreference.INSTANCE.store(NeoPreference.KEY_SYSTEM_SHELL, (Object) Boolean.valueOf(z));
    }

    private final boolean getSystemShellMode() {
        return NeoPreference.INSTANCE.loadBoolean(NeoPreference.KEY_SYSTEM_SHELL, true);
    }

    private final /* synthetic */ <T> void forEachTab(Function1<? super T, Unit> function1) {
        Iterable until = RangesKt.until(0, getTabSwitcher().getCount());
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
        Iterator it = until.iterator();
        while (it.hasNext()) {
            arrayList.add(getTabSwitcher().getTab(((IntIterator) it).nextInt()));
        }
        Intrinsics.reifiedOperationMarker(4, "T");
        for (R invoke : CollectionsKt.filterIsInstance((List) arrayList, Object.class)) {
            function1.invoke(invoke);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onTabCloseEvent(TabCloseEvent tabCloseEvent) {
        Intrinsics.checkParameterIsNotNull(tabCloseEvent, "tabCloseEvent");
        TermTab termTab = tabCloseEvent.getTermTab();
        int i = 0;
        toggleSwitcher(true, false);
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        Tab tab = termTab;
        tabSwitcher2.removeTab(tab);
        TabSwitcher tabSwitcher3 = this.tabSwitcher;
        if (tabSwitcher3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        if (tabSwitcher3.getCount() > 1) {
            TabSwitcher tabSwitcher4 = this.tabSwitcher;
            if (tabSwitcher4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            int indexOf = tabSwitcher4.indexOf(tab);
            if (NeoPreference.INSTANCE.isNextTabEnabled()) {
                i = indexOf - 1;
                if (i < 0) {
                    TabSwitcher tabSwitcher5 = this.tabSwitcher;
                    if (tabSwitcher5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
                    }
                    i = tabSwitcher5.getCount() - 1;
                }
            } else {
                int i2 = indexOf + 1;
                TabSwitcher tabSwitcher6 = this.tabSwitcher;
                if (tabSwitcher6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
                }
                if (i2 < tabSwitcher6.getCount()) {
                    i = i2;
                }
            }
            TabSwitcher tabSwitcher7 = this.tabSwitcher;
            if (tabSwitcher7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            switchToSession(tabSwitcher7.getTab(i));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onToggleFullScreenEvent(ToggleFullScreenEvent toggleFullScreenEvent) {
        Intrinsics.checkParameterIsNotNull(toggleFullScreenEvent, "toggleFullScreenEvent");
        FullScreenHelper fullScreenHelper2 = this.fullScreenHelper;
        if (fullScreenHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fullScreenHelper");
        }
        setFullScreenMode(!fullScreenHelper2.getFullScreen());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onToggleImeEvent(ToggleImeEvent toggleImeEvent) {
        Intrinsics.checkParameterIsNotNull(toggleImeEvent, "toggleImeEvent");
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        if (!tabSwitcher2.isSwitcherShown()) {
            Object systemService = getSystemService("input_method");
            if (systemService != null) {
                ((InputMethodManager) systemService).toggleSoftInput(1, 0);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onTitleChangedEvent(TitleChangedEvent titleChangedEvent) {
        Intrinsics.checkParameterIsNotNull(titleChangedEvent, "titleChangedEvent");
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        if (!tabSwitcher2.isSwitcherShown()) {
            Toolbar toolbar2 = this.toolbar;
            if (toolbar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            }
            toolbar2.setTitle((CharSequence) titleChangedEvent.getTitle());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onCreateNewSessionEvent(CreateNewSessionEvent createNewSessionEvent) {
        Intrinsics.checkParameterIsNotNull(createNewSessionEvent, "createNewSessionEvent");
        addNewSession();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onSwitchSessionEvent(SwitchSessionEvent switchSessionEvent) {
        int i;
        Intrinsics.checkParameterIsNotNull(switchSessionEvent, "switchSessionEvent");
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        if (tabSwitcher2.getCount() >= 2) {
            TabSwitcher tabSwitcher3 = this.tabSwitcher;
            if (tabSwitcher3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            int selectedTabIndex = tabSwitcher3.getSelectedTabIndex();
            TabSwitcher tabSwitcher4 = this.tabSwitcher;
            if (tabSwitcher4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            RangedInt rangedInt = new RangedInt(selectedTabIndex, RangesKt.until(0, tabSwitcher4.getCount()));
            if (switchSessionEvent.getToNext()) {
                i = rangedInt.increaseOne();
            } else {
                i = rangedInt.decreaseOne();
            }
            TabSwitcher tabSwitcher5 = this.tabSwitcher;
            if (tabSwitcher5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            if (!tabSwitcher5.isSwitcherShown()) {
                TabSwitcher tabSwitcher6 = this.tabSwitcher;
                if (tabSwitcher6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
                }
                tabSwitcher6.showSwitcher();
            }
            TabSwitcher tabSwitcher7 = this.tabSwitcher;
            if (tabSwitcher7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            switchToSession(tabSwitcher7.getTab(i));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onSwitchIndexedSessionEvent(SwitchIndexedSessionEvent switchIndexedSessionEvent) {
        Intrinsics.checkParameterIsNotNull(switchIndexedSessionEvent, "switchIndexedSessionEvent");
        int index = switchIndexedSessionEvent.getIndex() - 1;
        TabSwitcher tabSwitcher2 = this.tabSwitcher;
        if (tabSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
        }
        int count = tabSwitcher2.getCount();
        if (index >= 0 && count > index) {
            TabSwitcher tabSwitcher3 = this.tabSwitcher;
            if (tabSwitcher3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
            }
            if (index != tabSwitcher3.getSelectedTabIndex()) {
                TabSwitcher tabSwitcher4 = this.tabSwitcher;
                if (tabSwitcher4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tabSwitcher");
                }
                switchToSession(tabSwitcher4.getTab(index));
            }
        }
    }

    public final void update_colors() {
        new Handler().postDelayed(new NeoTermActivity$update_colors$1(this), 500);
    }

    public final void checkinstallterm() {
        Runtime runtime = Runtime.getRuntime();
        StringBuilder sb = new StringBuilder();
        sb.append("su -c ");
        File filesDir = AndraxApp.Companion.get().getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "AndraxApp.get().filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/bin/checkmount.sh ");
        sb.append(AndraxApp.Companion.get().getApplicationInfo().dataDir);
        Process exec = runtime.exec(sb.toString());
        exec.waitFor();
        if (exec.exitValue() != 0) {
            new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppCompatAlertDialogStyle)).setTitle("INSTALL ANDRAX").setIcon(R.drawable.andraxicon).setMessage("ANDRAX core is not installed, yet... go to ANDRAX interface and install it, bitch!").setCancelable(true).setPositiveButton("INSTALL NOW, Bitch!", new NeoTermActivity$checkinstallterm$1(this)).show();
        } else {
            AndraxApp.Companion.get().checkcoreversion();
        }
    }

    public final void changehostname(String str) {
        Intrinsics.checkParameterIsNotNull(str, "hostnameprovided");
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("su -c hostname " + str).waitFor();
    }

    public final void get_motherfucker_battery() {
        Object systemService = getSystemService("power");
        if (systemService != null) {
            PowerManager powerManager = (PowerManager) systemService;
            if (Build.VERSION.SDK_INT >= 23 && !powerManager.isIgnoringBatteryOptimizations(getPackageName())) {
                Intent intent = new Intent();
                intent.setAction("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
                intent.setData(Uri.parse("package:" + getPackageName()));
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.os.PowerManager");
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x010c A[Catch:{ Exception -> 0x016a, all -> 0x015e }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0113 A[Catch:{ Exception -> 0x016a, all -> 0x015e }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0134 A[Catch:{ Exception -> 0x015c, all -> 0x0158 }, LOOP:2: B:44:0x012b->B:47:0x0134, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0161  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0166  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x016d  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x014b A[EDGE_INSN: B:70:0x014b->B:48:0x014b ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:71:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:73:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setPermissions(java.io.File r12) {
        /*
            r11 = this;
            java.lang.String r0 = "AndraxApp.get().filesDir"
            if (r12 != 0) goto L_0x0005
            return
        L_0x0005:
            boolean r1 = r12.exists()
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0037
            r12.setReadable(r2, r3)
            r12.setExecutable(r2, r3)
            java.io.File[] r12 = r12.listFiles()
            if (r12 == 0) goto L_0x0036
            int r1 = r12.length
            r4 = 0
        L_0x001b:
            if (r4 >= r1) goto L_0x0037
            r5 = r12[r4]
            java.lang.String r6 = "f"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            boolean r6 = r5.isDirectory()
            if (r6 == 0) goto L_0x002d
            r11.setPermissions(r5)
        L_0x002d:
            r5.setReadable(r2, r3)
            r5.setExecutable(r2, r3)
            int r4 = r4 + 1
            goto L_0x001b
        L_0x0036:
            return
        L_0x0037:
            r12 = 0
            r1 = r12
            java.io.BufferedReader r1 = (java.io.BufferedReader) r1
            r4 = r12
            java.io.OutputStream r4 = (java.io.OutputStream) r4
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.lang.ProcessBuilder r6 = new java.lang.ProcessBuilder     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.lang.String r7 = "su"
            java.lang.String[] r7 = new java.lang.String[]{r7}     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            r6.<init>(r7)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.io.File r7 = new java.io.File     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            com.thecrackertechnology.andrax.AndraxApp$Companion r8 = com.thecrackertechnology.andrax.AndraxApp.Companion     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            com.thecrackertechnology.andrax.AndraxApp r8 = r8.get()     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            android.content.pm.ApplicationInfo r8 = r8.getApplicationInfo()     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.lang.String r8 = r8.dataDir     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            r7.<init>(r8)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            r6.directory(r7)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            r6.redirectErrorStream(r3)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.lang.Process r6 = r6.start()     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.lang.String r7 = "pb.start()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.io.OutputStream r4 = r6.getOutputStream()     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.io.InputStream r7 = r6.getInputStream()     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.lang.String r8 = "process.inputStream"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            r8.<init>()     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.lang.String r9 = "chmod -R 777 "
            r8.append(r9)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            com.thecrackertechnology.andrax.AndraxApp$Companion r9 = com.thecrackertechnology.andrax.AndraxApp.Companion     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            com.thecrackertechnology.andrax.AndraxApp r9 = r9.get()     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.io.File r9 = r9.getFilesDir()     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r0)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.lang.String r9 = r9.getAbsolutePath()     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            r8.append(r9)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            r5.add(r3, r8)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            r8.<init>()     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.lang.String r9 = "rm -rf"
            r8.append(r9)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            com.thecrackertechnology.andrax.AndraxApp$Companion r9 = com.thecrackertechnology.andrax.AndraxApp.Companion     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            com.thecrackertechnology.andrax.AndraxApp r9 = r9.get()     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.io.File r9 = r9.getFilesDir()     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r0)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.lang.String r0 = r9.getAbsolutePath()     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            r8.append(r0)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.lang.String r0 = "/bin/su"
            r8.append(r0)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.lang.String r0 = r8.toString()     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            r5.add(r2, r0)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.lang.String r0 = "exit 0"
            r5.add(r0)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.io.DataOutputStream r12 = (java.io.DataOutputStream) r12     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.io.DataOutputStream r0 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x0110, all -> 0x0106 }
            r0.<init>(r4)     // Catch:{ IOException -> 0x0110, all -> 0x0106 }
            java.util.Iterator r12 = r5.iterator()     // Catch:{ IOException -> 0x0104, all -> 0x0102 }
        L_0x00da:
            boolean r2 = r12.hasNext()     // Catch:{ IOException -> 0x0104, all -> 0x0102 }
            if (r2 == 0) goto L_0x00fb
            java.lang.Object r2 = r12.next()     // Catch:{ IOException -> 0x0104, all -> 0x0102 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ IOException -> 0x0104, all -> 0x0102 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0104, all -> 0x0102 }
            r5.<init>()     // Catch:{ IOException -> 0x0104, all -> 0x0102 }
            r5.append(r2)     // Catch:{ IOException -> 0x0104, all -> 0x0102 }
            java.lang.String r2 = "\n"
            r5.append(r2)     // Catch:{ IOException -> 0x0104, all -> 0x0102 }
            java.lang.String r2 = r5.toString()     // Catch:{ IOException -> 0x0104, all -> 0x0102 }
            r0.writeBytes(r2)     // Catch:{ IOException -> 0x0104, all -> 0x0102 }
            goto L_0x00da
        L_0x00fb:
            r0.flush()     // Catch:{ IOException -> 0x0104, all -> 0x0102 }
        L_0x00fe:
            r0.close()     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            goto L_0x0114
        L_0x0102:
            r12 = move-exception
            goto L_0x010a
        L_0x0104:
            goto L_0x0111
        L_0x0106:
            r0 = move-exception
            r10 = r0
            r0 = r12
            r12 = r10
        L_0x010a:
            if (r0 == 0) goto L_0x010f
            r0.close()     // Catch:{ Exception -> 0x016a, all -> 0x015e }
        L_0x010f:
            throw r12     // Catch:{ Exception -> 0x016a, all -> 0x015e }
        L_0x0110:
            r0 = r12
        L_0x0111:
            if (r0 == 0) goto L_0x0114
            goto L_0x00fe
        L_0x0114:
            java.io.BufferedReader r12 = new java.io.BufferedReader     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            r0.<init>(r7)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            java.io.Reader r0 = (java.io.Reader) r0     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            r12.<init>(r0)     // Catch:{ Exception -> 0x016a, all -> 0x015e }
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef     // Catch:{ Exception -> 0x015c, all -> 0x0158 }
            r0.<init>()     // Catch:{ Exception -> 0x015c, all -> 0x0158 }
            r1 = 1024(0x400, float:1.435E-42)
            char[] r1 = new char[r1]     // Catch:{ Exception -> 0x015c, all -> 0x0158 }
            java.lang.String r2 = ""
        L_0x012b:
            int r5 = r12.read(r1)     // Catch:{ Exception -> 0x015c, all -> 0x0158 }
            r0.element = r5     // Catch:{ Exception -> 0x015c, all -> 0x0158 }
            r7 = -1
            if (r5 == r7) goto L_0x014b
            int r5 = r0.element     // Catch:{ Exception -> 0x015c, all -> 0x0158 }
            java.lang.String r7 = new java.lang.String     // Catch:{ Exception -> 0x015c, all -> 0x0158 }
            r7.<init>(r1, r3, r5)     // Catch:{ Exception -> 0x015c, all -> 0x0158 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x015c, all -> 0x0158 }
            r5.<init>()     // Catch:{ Exception -> 0x015c, all -> 0x0158 }
            r5.append(r2)     // Catch:{ Exception -> 0x015c, all -> 0x0158 }
            r5.append(r7)     // Catch:{ Exception -> 0x015c, all -> 0x0158 }
            java.lang.String r2 = r5.toString()     // Catch:{ Exception -> 0x015c, all -> 0x0158 }
            goto L_0x012b
        L_0x014b:
            int r0 = r6.waitFor()     // Catch:{ Exception -> 0x015c, all -> 0x0158 }
            r12.close()
            if (r4 == 0) goto L_0x0173
        L_0x0154:
            r4.close()
            goto L_0x0173
        L_0x0158:
            r0 = move-exception
            r1 = r12
            r12 = r0
            goto L_0x015f
        L_0x015c:
            r1 = r12
            goto L_0x016b
        L_0x015e:
            r12 = move-exception
        L_0x015f:
            if (r1 == 0) goto L_0x0164
            r1.close()
        L_0x0164:
            if (r4 == 0) goto L_0x0169
            r4.close()
        L_0x0169:
            throw r12
        L_0x016a:
        L_0x016b:
            if (r1 == 0) goto L_0x0170
            r1.close()
        L_0x0170:
            if (r4 == 0) goto L_0x0173
            goto L_0x0154
        L_0x0173:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.ui.term.NeoTermActivity.setPermissions(java.io.File):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0044, code lost:
        if (r4 != null) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0082, code lost:
        if (r0 != null) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0084, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0095, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0097, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        r7.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x009b, code lost:
        if (r1 != null) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x009d, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00a0, code lost:
        if (r0 != null) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00a3, code lost:
        if (r2 != false) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00a5, code lost:
        startActivity(new android.content.Intent(r6, com.thecrackertechnology.andrax.RootIt.class));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00b2, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00b3, code lost:
        if (r1 != null) goto L_0x00b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00b5, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00b8, code lost:
        if (r0 != null) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00ba, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00bd, code lost:
        throw r7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0076 A[SYNTHETIC, Splitter:B:39:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x008a A[SYNTHETIC, Splitter:B:49:0x008a] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0091 A[Catch:{ IOException -> 0x0097, all -> 0x0095 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isRooted(android.content.Context r7) {
        /*
            r6 = this;
            java.lang.String r0 = "c"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            r7 = 0
            r0 = r7
            java.io.OutputStream r0 = (java.io.OutputStream) r0
            r1 = r7
            java.io.InputStream r1 = (java.io.InputStream) r1
            r2 = 0
            java.lang.Runtime r3 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x0097 }
            java.lang.String r4 = "su"
            java.lang.Process r3 = r3.exec(r4)     // Catch:{ IOException -> 0x0097 }
            java.io.OutputStream r0 = r3.getOutputStream()     // Catch:{ IOException -> 0x0097 }
            java.io.InputStream r1 = r3.getInputStream()     // Catch:{ IOException -> 0x0097 }
            r3 = r7
            java.io.DataOutputStream r3 = (java.io.DataOutputStream) r3     // Catch:{ IOException -> 0x0097 }
            java.io.DataOutputStream r4 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x003d, all -> 0x003a }
            r4.<init>(r0)     // Catch:{ IOException -> 0x003d, all -> 0x003a }
            java.lang.String r3 = "ls /data\n"
            r4.writeBytes(r3)     // Catch:{ IOException -> 0x0038 }
            java.lang.String r3 = "exit\n"
            r4.writeBytes(r3)     // Catch:{ IOException -> 0x0038 }
            r4.flush()     // Catch:{ IOException -> 0x0038 }
        L_0x0034:
            r4.close()     // Catch:{ IOException -> 0x0097 }
            goto L_0x0047
        L_0x0038:
            r3 = move-exception
            goto L_0x0041
        L_0x003a:
            r7 = move-exception
            r4 = r3
            goto L_0x008f
        L_0x003d:
            r4 = move-exception
            r5 = r4
            r4 = r3
            r3 = r5
        L_0x0041:
            r3.printStackTrace()     // Catch:{ all -> 0x008e }
            if (r4 == 0) goto L_0x0047
            goto L_0x0034
        L_0x0047:
            java.io.BufferedReader r7 = (java.io.BufferedReader) r7     // Catch:{ IOException -> 0x0097 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ IOException -> 0x006f }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x006f }
            r4.<init>(r1)     // Catch:{ IOException -> 0x006f }
            java.io.Reader r4 = (java.io.Reader) r4     // Catch:{ IOException -> 0x006f }
            r3.<init>(r4)     // Catch:{ IOException -> 0x006f }
            r7 = 0
        L_0x0056:
            java.lang.String r4 = r3.readLine()     // Catch:{ IOException -> 0x0068, all -> 0x0063 }
            if (r4 == 0) goto L_0x005f
            int r7 = r7 + 1
            goto L_0x0056
        L_0x005f:
            r3.close()     // Catch:{ IOException -> 0x0097 }
            goto L_0x007a
        L_0x0063:
            r7 = move-exception
            r5 = r3
            r3 = r7
            r7 = r5
            goto L_0x0088
        L_0x0068:
            r4 = move-exception
            r5 = r3
            r3 = r7
            r7 = r5
            goto L_0x0071
        L_0x006d:
            r3 = move-exception
            goto L_0x0088
        L_0x006f:
            r4 = move-exception
            r3 = 0
        L_0x0071:
            r4.printStackTrace()     // Catch:{ all -> 0x006d }
            if (r7 == 0) goto L_0x0079
            r7.close()     // Catch:{ IOException -> 0x0097 }
        L_0x0079:
            r7 = r3
        L_0x007a:
            if (r7 <= 0) goto L_0x007d
            r2 = 1
        L_0x007d:
            if (r1 == 0) goto L_0x0082
            r1.close()
        L_0x0082:
            if (r0 == 0) goto L_0x00a3
        L_0x0084:
            r0.close()
            goto L_0x00a3
        L_0x0088:
            if (r7 == 0) goto L_0x008d
            r7.close()     // Catch:{ IOException -> 0x0097 }
        L_0x008d:
            throw r3     // Catch:{ IOException -> 0x0097 }
        L_0x008e:
            r7 = move-exception
        L_0x008f:
            if (r4 == 0) goto L_0x0094
            r4.close()     // Catch:{ IOException -> 0x0097 }
        L_0x0094:
            throw r7     // Catch:{ IOException -> 0x0097 }
        L_0x0095:
            r7 = move-exception
            goto L_0x00b3
        L_0x0097:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x0095 }
            if (r1 == 0) goto L_0x00a0
            r1.close()
        L_0x00a0:
            if (r0 == 0) goto L_0x00a3
            goto L_0x0084
        L_0x00a3:
            if (r2 != 0) goto L_0x00b2
            android.content.Intent r7 = new android.content.Intent
            r0 = r6
            android.content.Context r0 = (android.content.Context) r0
            java.lang.Class<com.thecrackertechnology.andrax.RootIt> r1 = com.thecrackertechnology.andrax.RootIt.class
            r7.<init>(r0, r1)
            r6.startActivity(r7)
        L_0x00b2:
            return r2
        L_0x00b3:
            if (r1 == 0) goto L_0x00b8
            r1.close()
        L_0x00b8:
            if (r0 == 0) goto L_0x00bd
            r0.close()
        L_0x00bd:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.ui.term.NeoTermActivity.isRooted(android.content.Context):boolean");
    }
}
