package com.thecrackertechnology.dragonterminal.frontend.completion.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0006\"\u0004\b\n\u0010\u0004R\u001a\u0010\u000b\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0006\"\u0004\b\r\u0010\u0004¨\u0006\u000e"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/completion/model/CompletionCandidate;", "", "completeString", "", "(Ljava/lang/String;)V", "getCompleteString", "()Ljava/lang/String;", "setCompleteString", "description", "getDescription", "setDescription", "displayName", "getDisplayName", "setDisplayName", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: CompletionCandidate.kt */
public final class CompletionCandidate {
    private String completeString;
    private String description;
    private String displayName = this.completeString;

    public CompletionCandidate(String str) {
        Intrinsics.checkParameterIsNotNull(str, "completeString");
        this.completeString = str;
    }

    public final String getCompleteString() {
        return this.completeString;
    }

    public final void setCompleteString(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.completeString = str;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public final void setDisplayName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.displayName = str;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(String str) {
        this.description = str;
    }
}
