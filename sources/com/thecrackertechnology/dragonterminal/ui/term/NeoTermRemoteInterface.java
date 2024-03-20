package com.thecrackertechnology.dragonterminal.ui.term;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.thecrackertechnology.andrax.AndraxApp;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.bridge.Bridge;
import com.thecrackertechnology.dragonterminal.bridge.SessionId;
import com.thecrackertechnology.dragonterminal.component.userscript.UserScript;
import com.thecrackertechnology.dragonterminal.component.userscript.UserScriptComponent;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellParameter;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.TermSessionCallback;
import com.thecrackertechnology.dragonterminal.services.NeoTermService;
import com.thecrackertechnology.dragonterminal.utils.MediaUtils;
import com.thecrackertechnology.dragonterminal.utils.TerminalUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.ClassUtils;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J)\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH\u0002¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0010H\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\u0012\u0010\u0014\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0010H\u0014J\u001c\u0010\u0018\u001a\u00020\u00102\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0012\u0010\u001d\u001a\u00020\u00102\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J1\u0010\u001e\u001a\u00020\u00102\b\u0010\u001f\u001a\u0004\u0018\u00010\b2\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\b\u0010!\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0002\u0010\"J\u001a\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020\u000eH\u0002J(\u0010#\u001a\u00020\u00102\b\u0010'\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)2\b\b\u0002\u0010&\u001a\u00020\u000eH\u0002J$\u0010*\u001a\u00020\u00102\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\u000bH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/term/NeoTermRemoteInterface;", "Landroid/support/v7/app/AppCompatActivity;", "Landroid/content/ServiceConnection;", "()V", "termService", "Lcom/thecrackertechnology/dragonterminal/services/NeoTermService;", "buildUserScriptArgument", "", "", "userScriptPath", "files", "", "(Ljava/lang/String;Ljava/util/List;)[Ljava/lang/String;", "detectSystemShell", "", "handleIntent", "", "handleNormal", "handleTermHere", "handleUserScript", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onServiceConnected", "name", "Landroid/content/ComponentName;", "service", "Landroid/os/IBinder;", "onServiceDisconnected", "openCustomExecTerm", "executablePath", "arguments", "cwd", "(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)V", "openTerm", "parameter", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellParameter;", "foreground", "initialCommand", "sessionId", "Lcom/thecrackertechnology/dragonterminal/bridge/SessionId;", "setupUserScriptView", "filesToHandle", "", "userScripts", "Lcom/thecrackertechnology/dragonterminal/component/userscript/UserScript;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoTermRemoteInterface.kt */
public final class NeoTermRemoteInterface extends AppCompatActivity implements ServiceConnection {
    private HashMap _$_findViewCache;
    private NeoTermService termService;

    private final boolean detectSystemShell() {
        return false;
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

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = this;
        Intent intent = new Intent(context, NeoTermService.class);
        startService(intent);
        if (!bindService(intent, this, 0)) {
            AndraxApp.Companion.get().errorDialog(context, (int) R.string.service_connection_failed, (Function0<Unit>) new NeoTermRemoteInterface$onCreate$1(this));
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
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
            unbindService(this);
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        if (this.termService != null) {
            finish();
        }
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder != null) {
            this.termService = ((NeoTermService.NeoTermBinder) iBinder).getService();
            if (this.termService == null) {
                finish();
            } else {
                handleIntent();
            }
        } else {
            throw new TypeCastException("null cannot be cast to non-null type com.thecrackertechnology.dragonterminal.services.NeoTermService.NeoTermBinder");
        }
    }

    private final void handleIntent() {
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        ComponentName component = intent.getComponent();
        Intrinsics.checkExpressionValueIsNotNull(component, "intent.component");
        String className = component.getClassName();
        Intrinsics.checkExpressionValueIsNotNull(className, "intent.component.className");
        String substringAfterLast$default = StringsKt.substringAfterLast$default(className, (char) ClassUtils.PACKAGE_SEPARATOR_CHAR, (String) null, 2, (Object) null);
        int hashCode = substringAfterLast$default.hashCode();
        if (hashCode != -1117596004) {
            if (hashCode == -725073770 && substringAfterLast$default.equals("UserScript")) {
                handleUserScript();
                return;
            }
        } else if (substringAfterLast$default.equals("TermHere")) {
            handleTermHere();
            return;
        }
        handleNormal();
    }

    private final void handleNormal() {
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        String action = intent.getAction();
        if (action == null || action.hashCode() != -587149285 || !action.equals(Bridge.ACTION_EXECUTE)) {
            openTerm$default(this, (String) null, (SessionId) null, false, 4, (Object) null);
        } else if (!getIntent().hasExtra(Bridge.EXTRA_COMMAND)) {
            AndraxApp.Companion.get().errorDialog((Context) this, (int) R.string.no_command_extra, (Function0<Unit>) new NeoTermRemoteInterface$handleNormal$1(this));
            return;
        } else {
            openTerm(getIntent().getStringExtra(Bridge.EXTRA_COMMAND), SessionId.of(getIntent().getStringExtra(Bridge.EXTRA_SESSION_ID)), getIntent().getBooleanExtra(Bridge.EXTRA_FOREGROUND, true));
        }
        finish();
    }

    private final void handleTermHere() {
        if (getIntent().hasExtra("android.intent.extra.STREAM")) {
            Intent intent = getIntent();
            Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
            Object obj = intent.getExtras().get("android.intent.extra.STREAM");
            if (obj instanceof Uri) {
                String path = MediaUtils.INSTANCE.getPath(this, (Uri) obj);
                File file = new File(path);
                if (!file.isDirectory()) {
                    path = file.getParent();
                }
                openTerm$default(this, "cd " + TerminalUtils.INSTANCE.escapeString(path), (SessionId) null, false, 4, (Object) null);
            }
            finish();
            return;
        }
        AndraxApp andraxApp = AndraxApp.Companion.get();
        Context context = this;
        Object[] objArr = new Object[1];
        Intent intent2 = getIntent();
        objArr[0] = intent2 != null ? intent2.toString() : null;
        String string = getString(R.string.unsupported_term_here, objArr);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.unsup…here, intent?.toString())");
        andraxApp.errorDialog(context, string, (Function0<Unit>) new NeoTermRemoteInterface$handleTermHere$1(this));
    }

    private final void handleUserScript() {
        List arrayList = new ArrayList();
        String str = null;
        List<UserScript> userScripts = ((UserScriptComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, UserScriptComponent.class, false, 2, (Object) null)).getUserScripts();
        if (userScripts.isEmpty()) {
            AndraxApp.Companion.get().errorDialog((Context) this, (int) R.string.no_user_script_found, (Function0<Unit>) new NeoTermRemoteInterface$handleUserScript$1(this));
            return;
        }
        if (getIntent().hasExtra("android.intent.extra.STREAM")) {
            Intent intent = getIntent();
            Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
            Object obj = intent.getExtras().get("android.intent.extra.STREAM");
            if (obj instanceof ArrayList) {
                ArrayList arrayList2 = new ArrayList();
                for (Object next : (Iterable) obj) {
                    if (!(next instanceof Uri)) {
                        break;
                    }
                    arrayList2.add(next);
                }
                for (Object next2 : arrayList2) {
                    Collection collection = arrayList;
                    if (next2 != null) {
                        String absolutePath = new File(MediaUtils.INSTANCE.getPath(this, (Uri) next2)).getAbsolutePath();
                        Intrinsics.checkExpressionValueIsNotNull(absolutePath, "File(MediaUtils.getPath(this, uri)).absolutePath");
                        collection.add(absolutePath);
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type android.net.Uri");
                    }
                }
                Collection collection2 = arrayList;
            } else if (obj instanceof Uri) {
                String absolutePath2 = new File(MediaUtils.INSTANCE.getPath(this, (Uri) obj)).getAbsolutePath();
                Intrinsics.checkExpressionValueIsNotNull(absolutePath2, "File(MediaUtils.getPath(this, extra)).absolutePath");
                arrayList.add(absolutePath2);
            }
        } else {
            Intent intent2 = getIntent();
            Intrinsics.checkExpressionValueIsNotNull(intent2, "intent");
            if (intent2.getData() != null) {
                Intent intent3 = getIntent();
                Intrinsics.checkExpressionValueIsNotNull(intent3, "intent");
                Uri data = intent3.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "intent.data");
                String absolutePath3 = new File(data.getPath()).getAbsolutePath();
                Intrinsics.checkExpressionValueIsNotNull(absolutePath3, "File(intent.data.path).absolutePath");
                arrayList.add(absolutePath3);
            }
        }
        if (!arrayList.isEmpty()) {
            setupUserScriptView(arrayList, userScripts);
            return;
        }
        AndraxApp andraxApp = AndraxApp.Companion.get();
        Context context = this;
        Object[] objArr = new Object[1];
        Intent intent4 = getIntent();
        if (intent4 != null) {
            str = intent4.toString();
        }
        objArr[0] = str;
        String string = getString(R.string.no_files_selected, objArr);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.no_fi…cted, intent?.toString())");
        andraxApp.errorDialog(context, string, (Function0<Unit>) new NeoTermRemoteInterface$handleUserScript$4(this));
    }

    private final void setupUserScriptView(List<String> list, List<UserScript> list2) {
        setContentView((int) R.layout.ui_user_script_list);
        ListView listView = (ListView) findViewById(R.id.user_script_file_list);
        Context context = this;
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, 17367043, list);
        Intrinsics.checkExpressionValueIsNotNull(listView, "filesList");
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new NeoTermRemoteInterface$setupUserScriptView$1(this, list, arrayAdapter));
        ListView listView2 = (ListView) findViewById(R.id.user_script_script_list);
        List arrayList = new ArrayList();
        for (UserScript scriptFile : list2) {
            arrayList.add(FilesKt.getNameWithoutExtension(scriptFile.getScriptFile()));
        }
        Collection collection = arrayList;
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(context, 17367043, arrayList);
        Intrinsics.checkExpressionValueIsNotNull(listView2, "scriptsList");
        listView2.setAdapter(arrayAdapter2);
        listView2.setOnItemClickListener(new NeoTermRemoteInterface$setupUserScriptView$3(this, list2, list));
    }

    /* access modifiers changed from: private */
    public final String[] buildUserScriptArgument(String str, List<String> list) {
        List mutableListOf = CollectionsKt.mutableListOf(str);
        mutableListOf.addAll(list);
        Object[] array = mutableListOf.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    static /* synthetic */ void openTerm$default(NeoTermRemoteInterface neoTermRemoteInterface, ShellParameter shellParameter, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        neoTermRemoteInterface.openTerm(shellParameter, z);
    }

    private final void openTerm(ShellParameter shellParameter, boolean z) {
        NeoTermService neoTermService = this.termService;
        if (neoTermService == null) {
            Intrinsics.throwNpe();
        }
        TerminalSession createTermSession = neoTermService.createTermSession(shellParameter);
        Intent intent = new Intent();
        intent.putExtra(Bridge.EXTRA_SESSION_ID, createTermSession.mHandle);
        setResult(-1, intent);
        if (z) {
            NeoPreference.INSTANCE.storeCurrentSession(createTermSession);
            Intent intent2 = new Intent(this, NeoTermActivity.class);
            intent2.addCategory("android.intent.category.DEFAULT");
            intent2.addFlags(268435456);
            startActivity(intent2);
        }
    }

    static /* synthetic */ void openTerm$default(NeoTermRemoteInterface neoTermRemoteInterface, String str, SessionId sessionId, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            sessionId = null;
        }
        if ((i & 4) != 0) {
            z = true;
        }
        neoTermRemoteInterface.openTerm(str, sessionId, z);
    }

    private final void openTerm(String str, SessionId sessionId, boolean z) {
        openTerm(new ShellParameter().initialCommand(str).callback(new TermSessionCallback()).systemShell(detectSystemShell()).session(sessionId), z);
    }

    /* access modifiers changed from: private */
    public final void openCustomExecTerm(String str, String[] strArr, String str2) {
        openTerm$default(this, new ShellParameter().executablePath(str).arguments(strArr).currentWorkingDirectory(str2).callback(new TermSessionCallback()).systemShell(detectSystemShell()), false, 2, (Object) null);
    }
}
