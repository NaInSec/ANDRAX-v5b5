package com.thecrackertechnology.dragonterminal.component.session;

import android.app.Activity;
import android.content.Context;
import com.thecrackertechnology.dragonterminal.Globals;
import com.thecrackertechnology.dragonterminal.frontend.component.NeoComponent;
import com.thecrackertechnology.dragonterminal.frontend.logging.NLog;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellParameter;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellTermSession;
import com.thecrackertechnology.dragonterminal.frontend.session.xorg.XParameter;
import com.thecrackertechnology.dragonterminal.frontend.session.xorg.XSession;
import com.thecrackertechnology.dragonterminal.frontend.session.xorg.client.XSessionData;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\fH\u0016¨\u0006\u0010"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/session/SessionComponent;", "Lcom/thecrackertechnology/dragonterminal/frontend/component/NeoComponent;", "()V", "createSession", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellTermSession;", "context", "Landroid/content/Context;", "parameter", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellParameter;", "Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/XSession;", "Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/XParameter;", "onServiceDestroy", "", "onServiceInit", "onServiceObtained", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: SessionComponent.kt */
public final class SessionComponent implements NeoComponent {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static boolean IS_LIBRARIES_LOADED;

    public void onServiceDestroy() {
    }

    public void onServiceInit() {
    }

    public void onServiceObtained() {
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0003J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/session/SessionComponent$Companion;", "", "()V", "IS_LIBRARIES_LOADED", "", "checkLibrariesLoaded", "loadLibraries", "wrapLibraryName", "", "libName", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: SessionComponent.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final String wrapLibraryName(String str) {
            return "lib" + str + ".so";
        }

        private final boolean loadLibraries() {
            try {
                if (Globals.NeedGles3) {
                    System.loadLibrary("GLESv3");
                    NLog.INSTANCE.e("SessionComponent", "Loaded GLESv3 lib");
                } else if (Globals.NeedGles2) {
                    System.loadLibrary("GLESv2");
                    NLog.INSTANCE.e("SessionComponent", "Loaded GLESv2 lib");
                }
            } catch (UnsatisfiedLinkError unused) {
                NLog.INSTANCE.e("SessionComponent", "Cannot load GLESv3 or GLESv2 lib");
            }
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            try {
                String[] strArr = Globals.XLIBS;
                Intrinsics.checkExpressionValueIsNotNull(strArr, "Globals.XLIBS");
                String[] strArr2 = Globals.XAPP_LIBS;
                Intrinsics.checkExpressionValueIsNotNull(strArr2, "Globals.XAPP_LIBS");
                for (Object obj : ArraysKt.plus((T[]) strArr, (T[]) strArr2)) {
                    String str = (String) obj;
                }
                booleanRef.element = true;
            } catch (UnsatisfiedLinkError e) {
                NLog nLog = NLog.INSTANCE;
                String localizedMessage = e.getLocalizedMessage();
                Intrinsics.checkExpressionValueIsNotNull(localizedMessage, "ignore.localizedMessage");
                nLog.e("SessionComponent", localizedMessage);
                booleanRef.element = false;
            }
            return booleanRef.element;
        }

        /* access modifiers changed from: private */
        public final boolean checkLibrariesLoaded() {
            if (!SessionComponent.IS_LIBRARIES_LOADED) {
                synchronized (SessionComponent.class) {
                    if (!SessionComponent.IS_LIBRARIES_LOADED) {
                        SessionComponent.IS_LIBRARIES_LOADED = SessionComponent.Companion.loadLibraries();
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return SessionComponent.IS_LIBRARIES_LOADED;
        }
    }

    public final XSession createSession(Context context, XParameter xParameter) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(xParameter, "parameter");
        if (!(context instanceof Activity)) {
            throw new RuntimeException("Creating X sessions requires Activity, but got Context");
        } else if (Companion.checkLibrariesLoaded()) {
            return new XSession((Activity) context, new XSessionData());
        } else {
            throw new RuntimeException("Cannot load libraries!");
        }
    }

    public final ShellTermSession createSession(Context context, ShellParameter shellParameter) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(shellParameter, "parameter");
        return new ShellTermSession.Builder().executablePath(shellParameter.getExecutablePath()).currentWorkingDirectory(shellParameter.getCwd()).callback(shellParameter.getSessionCallback()).systemShell(shellParameter.getSystemShell()).envArray(shellParameter.getEnv()).argArray(shellParameter.getArguments()).initialCommand(shellParameter.getInitialCommand()).profile(shellParameter.getShellProfile()).create(context);
    }
}
