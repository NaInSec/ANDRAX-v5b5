package com.thecrackertechnology.dragonterminal.frontend.session.shell;

import android.content.Context;
import android.os.Handler;
import com.thecrackertechnology.andrax.AndraxApp;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoTermPath;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.TermSessionCallback;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001:\u0001\u001fBM\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018H\u0014J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0018H\u0016J\u0012\u0010\u001d\u001a\u00020\u001a2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0003H\u0002R\"\u0010\u000e\u001a\n \u000f*\u0004\u0018\u00010\u00030\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006 "}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellTermSession;", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "shellPath", "", "cwd", "args", "", "env", "changeCallback", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession$SessionChangedCallback;", "initialCommand", "shellProfile", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellProfile;", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession$SessionChangedCallback;Ljava/lang/String;Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellProfile;)V", "exitPrompt", "kotlin.jvm.PlatformType", "getExitPrompt", "()Ljava/lang/String;", "setExitPrompt", "(Ljava/lang/String;)V", "getShellProfile", "()Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellProfile;", "getExitDescription", "exitCode", "", "initializeEmulator", "", "columns", "rows", "sendInitialCommand", "command", "Builder", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ShellTermSession.kt */
public class ShellTermSession extends TerminalSession {
    private String exitPrompt;
    private final String initialCommand;
    private final ShellProfile shellProfile;

    public /* synthetic */ ShellTermSession(String str, String str2, String[] strArr, String[] strArr2, TerminalSession.SessionChangedCallback sessionChangedCallback, String str3, ShellProfile shellProfile2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, strArr, strArr2, sessionChangedCallback, str3, shellProfile2);
    }

    public final ShellProfile getShellProfile() {
        return this.shellProfile;
    }

    private ShellTermSession(String str, String str2, String[] strArr, String[] strArr2, TerminalSession.SessionChangedCallback sessionChangedCallback, String str3, ShellProfile shellProfile2) {
        super(str, str2, strArr, strArr2, sessionChangedCallback);
        this.initialCommand = str3;
        this.shellProfile = shellProfile2;
        this.exitPrompt = AndraxApp.Companion.get().getString(R.string.process_exit_prompt);
    }

    public final String getExitPrompt() {
        return this.exitPrompt;
    }

    public final void setExitPrompt(String str) {
        this.exitPrompt = str;
    }

    public void initializeEmulator(int i, int i2) {
        super.initializeEmulator(i, i2);
        sendInitialCommand(this.shellProfile.getInitialCommand());
        sendInitialCommand(this.initialCommand);
    }

    /* access modifiers changed from: protected */
    public String getExitDescription(int i) {
        StringBuilder sb = new StringBuilder("\r\n[ ");
        AndraxApp andraxApp = AndraxApp.Companion.get();
        sb.append(andraxApp.getString(R.string.process_exit_info));
        if (i > 0) {
            sb.append(" (");
            sb.append(andraxApp.getString(R.string.process_exit_code, new Object[]{Integer.valueOf(i)}));
            sb.append(")");
        } else if (i < 0) {
            sb.append(" (");
            sb.append(andraxApp.getString(R.string.process_exit_signal, new Object[]{Integer.valueOf(-i)}));
            sb.append(")");
        }
        sb.append(" - " + this.exitPrompt + " ]");
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "builder.toString()");
        return sb2;
    }

    private final void sendInitialCommand(String str) {
        if (str != null) {
            if (str.length() > 0) {
                new Handler().postDelayed(new ShellTermSession$sendInitialCommand$1(this, str), 2500);
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00002\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005J\u001b\u0010\u0012\u001a\u00020\u00002\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0013¢\u0006\u0002\u0010\u0014J%\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\b\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0002¢\u0006\u0002\u0010\u0016J\b\u0010\u0017\u001a\u00020\u0005H\u0002J\b\u0010\u0018\u001a\u00020\u0005H\u0002J\b\u0010\u0019\u001a\u00020\u0005H\u0002J\b\u0010\u001a\u001a\u00020\u0005H\u0002J\u0010\u0010\u001b\u001a\u00020\u00002\b\u0010\u001b\u001a\u0004\u0018\u00010\u0007J\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0010\u0010 \u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u0005J\u001c\u0010\t\u001a\u00020\u00002\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\nJ'\u0010!\u001a\u00020\u00002\u001a\u0010\t\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\n\u0018\u00010\u0013¢\u0006\u0002\u0010\"J\u0010\u0010\u000b\u001a\u00020\u00002\b\u0010#\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\f\u001a\u00020\u00002\b\u0010$\u001a\u0004\u0018\u00010\u0005J\u0010\u0010%\u001a\u00020\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J1\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00132\u001a\u0010\t\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\n\u0018\u00010\u0004H\u0002¢\u0006\u0002\u0010'R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\"\u0010\t\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\n\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellTermSession$Builder;", "", "()V", "args", "", "", "changeCallback", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession$SessionChangedCallback;", "cwd", "env", "Lkotlin/Pair;", "executablePath", "initialCommand", "shellProfile", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellProfile;", "systemShell", "", "arg", "argArray", "", "([Ljava/lang/String;)Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellTermSession$Builder;", "buildEnvironment", "(Ljava/lang/String;Z)[Ljava/lang/String;", "buildLdLibraryEnv", "buildOriginLdLibEnv", "buildOriginPathEnv", "buildPathEnv", "callback", "create", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellTermSession;", "context", "Landroid/content/Context;", "currentWorkingDirectory", "envArray", "([Lkotlin/Pair;)Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellTermSession$Builder;", "shell", "command", "profile", "transformEnvironment", "(Ljava/util/List;)[Ljava/lang/String;", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: ShellTermSession.kt */
    public static final class Builder {
        private List<String> args;
        private TerminalSession.SessionChangedCallback changeCallback;
        private String cwd;
        private List<Pair<String, String>> env;
        private String executablePath;
        private String initialCommand;
        private ShellProfile shellProfile = new ShellProfile();
        private boolean systemShell;

        public final Builder profile(ShellProfile shellProfile2) {
            if (shellProfile2 != null) {
                this.shellProfile = shellProfile2;
            }
            return this;
        }

        public final Builder initialCommand(String str) {
            this.initialCommand = str;
            return this;
        }

        public final Builder executablePath(String str) {
            this.executablePath = str;
            return this;
        }

        public final Builder currentWorkingDirectory(String str) {
            this.cwd = str;
            return this;
        }

        public final Builder arg(String str) {
            if (str != null) {
                List<String> list = this.args;
                if (list == null) {
                    this.args = CollectionsKt.mutableListOf(str);
                } else {
                    if (list == null) {
                        Intrinsics.throwNpe();
                    }
                    list.add(str);
                }
            } else {
                this.args = null;
            }
            return this;
        }

        public final Builder argArray(String[] strArr) {
            if (strArr != null) {
                if (strArr.length == 0) {
                    this.args = null;
                    return this;
                }
                for (String arg : strArr) {
                    arg(arg);
                }
            } else {
                this.args = null;
            }
            return this;
        }

        public final Builder env(Pair<String, String> pair) {
            if (pair != null) {
                List<Pair<String, String>> list = this.env;
                if (list == null) {
                    this.env = CollectionsKt.mutableListOf(pair);
                } else {
                    if (list == null) {
                        Intrinsics.throwNpe();
                    }
                    list.add(pair);
                }
            } else {
                this.env = null;
            }
            return this;
        }

        public final Builder envArray(Pair<String, String>[] pairArr) {
            if (pairArr != null) {
                if (pairArr.length == 0) {
                    this.env = null;
                    return this;
                }
                for (Pair<String, String> env2 : pairArr) {
                    env(env2);
                }
            } else {
                this.env = null;
            }
            return this;
        }

        public final Builder callback(TerminalSession.SessionChangedCallback sessionChangedCallback) {
            this.changeCallback = sessionChangedCallback;
            return this;
        }

        public final Builder systemShell(boolean z) {
            this.systemShell = z;
            return this;
        }

        public final ShellTermSession create(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            String str = this.cwd;
            if (str == null) {
                str = AndraxApp.Companion.get().getApplicationInfo().dataDir;
            }
            String str2 = str;
            String str3 = this.executablePath;
            if (str3 == null) {
                if (this.systemShell) {
                    str3 = "/system/bin/sh";
                } else {
                    str3 = this.shellProfile.getLoginShell();
                }
            }
            String str4 = str3;
            List<String> list = this.args;
            if (list == null) {
                list = CollectionsKt.mutableListOf(str4);
            }
            String[] transformEnvironment = transformEnvironment(this.env);
            if (transformEnvironment == null) {
                transformEnvironment = buildEnvironment(str2, this.systemShell);
            }
            String[] strArr = transformEnvironment;
            TerminalSession.SessionChangedCallback sessionChangedCallback = this.changeCallback;
            if (sessionChangedCallback == null) {
                sessionChangedCallback = new TermSessionCallback();
            }
            TerminalSession.SessionChangedCallback sessionChangedCallback2 = sessionChangedCallback;
            Intrinsics.checkExpressionValueIsNotNull(str2, "cwd");
            Object[] array = list.toArray(new String[0]);
            if (array != null) {
                String[] strArr2 = (String[]) array;
                String str5 = this.initialCommand;
                if (str5 == null) {
                    str5 = "";
                }
                return new ShellTermSession(str4, str2, strArr2, strArr, sessionChangedCallback2, str5, this.shellProfile, (DefaultConstructorMarker) null);
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }

        private final String[] transformEnvironment(List<Pair<String, String>> list) {
            if (list == null) {
                return null;
            }
            List arrayList = new ArrayList();
            for (Pair pair : list) {
                arrayList.add(((String) pair.getFirst()) + '=' + ((String) pair.getSecond()));
            }
            Object[] array = arrayList.toArray(new String[0]);
            if (array != null) {
                return (String[]) array;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }

        private final String[] buildEnvironment(String str, boolean z) {
            String[] strArr;
            String str2;
            String str3 = str != null ? str : AndraxApp.Companion.get().getApplicationInfo().dataDir;
            new File(NeoTermPath.HOME_PATH).mkdirs();
            String str4 = "HOME=" + AndraxApp.Companion.get().getApplicationInfo().dataDir;
            String str5 = "ANDROID_ROOT=" + System.getenv("ANDROID_ROOT");
            String str6 = "ANDROID_DATA=" + System.getenv("ANDROID_DATA");
            String str7 = "EXTERNAL_STORAGE=" + System.getenv("EXTERNAL_STORAGE");
            if (z) {
                strArr = new String[]{"TERM=xterm-256color", str4, "PATH=" + System.getenv("PATH"), str5, str6, str7};
            } else {
                String str8 = "PATH=" + buildPathEnv();
                String str9 = "LD_LIBRARY_PATH=" + buildLdLibraryEnv();
                String str10 = "PWD=" + str3;
                if (this.shellProfile.getEnableExecveWrapper()) {
                    str2 = "LD_PRELOAD=" + AndraxApp.Companion.get().getApplicationInfo().nativeLibraryDir + "/libnexec.so";
                } else {
                    str2 = "";
                }
                strArr = new String[]{"TERM=xterm-256color", str4, str8, "PS1=$ ", str9, str10, str5, str6, str7, "TMPDIR=/data/local/tmp", str2};
            }
            Collection arrayList = new ArrayList();
            for (String str11 : strArr) {
                if (str11.length() > 0) {
                    arrayList.add(str11);
                }
            }
            Object[] array = ((List) arrayList).toArray(new String[0]);
            if (array != null) {
                return (String[]) array;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }

        private final String buildOriginPathEnv() {
            String str = System.getenv("PATH");
            return str != null ? str : "";
        }

        private final String buildOriginLdLibEnv() {
            String str = System.getenv("LD_LIBRARY_PATH");
            return str != null ? str : "";
        }

        private final String buildLdLibraryEnv() {
            String str = System.getenv("LD_LIBRARY_PATH");
            return str != null ? str : "";
        }

        private final String buildPathEnv() {
            StringBuilder sb = new StringBuilder();
            File filesDir = AndraxApp.Companion.get().getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir, "AndraxApp.get().filesDir");
            sb.append(filesDir.getAbsolutePath());
            sb.append("/bin:");
            sb.append(System.getenv("PATH"));
            return sb.toString();
        }
    }
}
