package com.thecrackertechnology.dragonterminal.bridge;

import android.content.ComponentName;
import android.content.Intent;
import java.util.Objects;

public class Bridge {
    public static final String ACTION_EXECUTE = "neoterm.action.remote.execute";
    public static final String ACTION_SILENT_RUN = "neoterm.action.remote.silent-run";
    public static final String EXTRA_COMMAND = "neoterm.extra.remote.execute.command";
    public static final String EXTRA_FOREGROUND = "neoterm.extra.remote.execute.foreground";
    public static final String EXTRA_SESSION_ID = "neoterm.extra.remote.execute.session";
    private static final ComponentName NEOTERM_COMPONENT = new ComponentName("com.thecrackertechnology.andrax", NEOTERM_REMOTE_INTERFACE);
    private static final String NEOTERM_PACKAGE = "com.thecrackertechnology.andrax";
    private static final String NEOTERM_REMOTE_INTERFACE = "com.thecrackertechnology.dragonterminal.ui.term.NeoTermRemoteInterface";

    private Bridge() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static Intent createExecuteIntent(SessionId sessionId, String str, boolean z) {
        Objects.requireNonNull(str, "command");
        Objects.requireNonNull(sessionId, "session id");
        Intent intent = new Intent(ACTION_EXECUTE);
        intent.setComponent(NEOTERM_COMPONENT);
        intent.putExtra(EXTRA_COMMAND, str);
        intent.putExtra(EXTRA_SESSION_ID, sessionId.getSessionId());
        intent.putExtra(EXTRA_FOREGROUND, z);
        return intent;
    }

    public static Intent createExecuteIntent(SessionId sessionId, String str) {
        return createExecuteIntent(sessionId, str, true);
    }

    public static Intent createExecuteIntent(String str) {
        return createExecuteIntent(SessionId.NEW_SESSION, str);
    }

    public static Intent createExecuteIntent(String str, boolean z) {
        return createExecuteIntent(SessionId.NEW_SESSION, str, z);
    }

    public static SessionId parseResult(Intent intent) {
        Objects.requireNonNull(intent, "data");
        if (intent.hasExtra(EXTRA_SESSION_ID)) {
            return SessionId.of(intent.getStringExtra(EXTRA_SESSION_ID));
        }
        return null;
    }
}
