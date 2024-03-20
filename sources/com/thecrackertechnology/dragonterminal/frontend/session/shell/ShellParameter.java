package com.thecrackertechnology.dragonterminal.frontend.session.shell;

import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.bridge.SessionId;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\u00020\u00002\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\u0002\u00105J\u0010\u00106\u001a\u00020\u00002\b\u00106\u001a\u0004\u0018\u00010\u001eJ\u0010\u00107\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005J'\u00108\u001a\u00020\u00002\u001a\u0010\u0010\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0011\u0018\u00010\u0004¢\u0006\u0002\u00109J\u0010\u0010\u0017\u001a\u00020\u00002\b\u0010\u0017\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u001a\u001a\u00020\u00002\b\u0010\u001a\u001a\u0004\u0018\u00010\u0005J\u000e\u0010:\u001a\u00020\u00002\u0006\u0010)\u001a\u00020*J\u0010\u0010;\u001a\u00020\u00002\b\u0010#\u001a\u0004\u0018\u00010$J\u000e\u0010/\u001a\u00020\u00002\u0006\u0010/\u001a\u000200J\u0006\u0010<\u001a\u000200R$\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR0\u0010\u0010\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0011\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000fR\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\r\"\u0004\b\u001c\u0010\u000fR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010#\u001a\u0004\u0018\u00010$X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001c\u0010)\u001a\u0004\u0018\u00010*X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u000200X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104¨\u0006="}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellParameter;", "", "()V", "arguments", "", "", "getArguments", "()[Ljava/lang/String;", "setArguments", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "cwd", "getCwd", "()Ljava/lang/String;", "setCwd", "(Ljava/lang/String;)V", "env", "Lkotlin/Pair;", "getEnv", "()[Lkotlin/Pair;", "setEnv", "([Lkotlin/Pair;)V", "[Lkotlin/Pair;", "executablePath", "getExecutablePath", "setExecutablePath", "initialCommand", "getInitialCommand", "setInitialCommand", "sessionCallback", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession$SessionChangedCallback;", "getSessionCallback", "()Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession$SessionChangedCallback;", "setSessionCallback", "(Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession$SessionChangedCallback;)V", "sessionId", "Lcom/thecrackertechnology/dragonterminal/bridge/SessionId;", "getSessionId", "()Lcom/thecrackertechnology/dragonterminal/bridge/SessionId;", "setSessionId", "(Lcom/thecrackertechnology/dragonterminal/bridge/SessionId;)V", "shellProfile", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellProfile;", "getShellProfile", "()Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellProfile;", "setShellProfile", "(Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellProfile;)V", "systemShell", "", "getSystemShell", "()Z", "setSystemShell", "(Z)V", "([Ljava/lang/String;)Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellParameter;", "callback", "currentWorkingDirectory", "environment", "([Lkotlin/Pair;)Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellParameter;", "profile", "session", "willCreateNewSession", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ShellParameter.kt */
public final class ShellParameter {
    private String[] arguments;
    private String cwd;
    private Pair<String, String>[] env;
    private String executablePath;
    private String initialCommand;
    private TerminalSession.SessionChangedCallback sessionCallback;
    private SessionId sessionId;
    private ShellProfile shellProfile;
    private boolean systemShell;

    public final SessionId getSessionId() {
        return this.sessionId;
    }

    public final void setSessionId(SessionId sessionId2) {
        this.sessionId = sessionId2;
    }

    public final String getExecutablePath() {
        return this.executablePath;
    }

    public final void setExecutablePath(String str) {
        this.executablePath = str;
    }

    public final String[] getArguments() {
        return this.arguments;
    }

    public final void setArguments(String[] strArr) {
        this.arguments = strArr;
    }

    public final String getCwd() {
        return this.cwd;
    }

    public final void setCwd(String str) {
        this.cwd = str;
    }

    public final String getInitialCommand() {
        return this.initialCommand;
    }

    public final void setInitialCommand(String str) {
        this.initialCommand = str;
    }

    public final Pair<String, String>[] getEnv() {
        return this.env;
    }

    public final void setEnv(Pair<String, String>[] pairArr) {
        this.env = pairArr;
    }

    public final TerminalSession.SessionChangedCallback getSessionCallback() {
        return this.sessionCallback;
    }

    public final void setSessionCallback(TerminalSession.SessionChangedCallback sessionChangedCallback) {
        this.sessionCallback = sessionChangedCallback;
    }

    public final boolean getSystemShell() {
        return this.systemShell;
    }

    public final void setSystemShell(boolean z) {
        this.systemShell = z;
    }

    public final ShellProfile getShellProfile() {
        return this.shellProfile;
    }

    public final void setShellProfile(ShellProfile shellProfile2) {
        this.shellProfile = shellProfile2;
    }

    public final ShellParameter executablePath(String str) {
        this.executablePath = str;
        return this;
    }

    public final ShellParameter arguments(String[] strArr) {
        this.arguments = strArr;
        return this;
    }

    public final ShellParameter currentWorkingDirectory(String str) {
        this.cwd = str;
        return this;
    }

    public final ShellParameter initialCommand(String str) {
        this.initialCommand = str;
        return this;
    }

    public final ShellParameter environment(Pair<String, String>[] pairArr) {
        this.env = pairArr;
        return this;
    }

    public final ShellParameter callback(TerminalSession.SessionChangedCallback sessionChangedCallback) {
        this.sessionCallback = sessionChangedCallback;
        return this;
    }

    public final ShellParameter systemShell(boolean z) {
        this.systemShell = z;
        return this;
    }

    public final ShellParameter profile(ShellProfile shellProfile2) {
        Intrinsics.checkParameterIsNotNull(shellProfile2, "shellProfile");
        this.shellProfile = shellProfile2;
        return this;
    }

    public final ShellParameter session(SessionId sessionId2) {
        this.sessionId = sessionId2;
        return this;
    }

    public final boolean willCreateNewSession() {
        SessionId sessionId2 = this.sessionId;
        if (sessionId2 != null) {
            return sessionId2.equals(SessionId.NEW_SESSION);
        }
        return true;
    }
}
