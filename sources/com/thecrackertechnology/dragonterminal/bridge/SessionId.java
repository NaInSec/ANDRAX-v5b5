package com.thecrackertechnology.dragonterminal.bridge;

import java.util.Objects;

public class SessionId {
    public static final SessionId CURRENT_SESSION = of("current");
    public static final SessionId NEW_SESSION = of("new");
    private final String sessionId;

    SessionId(String str) {
        this.sessionId = str;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public String toString() {
        return "TerminalSession { id = " + this.sessionId + " }";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.sessionId, ((SessionId) obj).sessionId);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.sessionId});
    }

    public static SessionId of(String str) {
        return new SessionId(str);
    }
}
