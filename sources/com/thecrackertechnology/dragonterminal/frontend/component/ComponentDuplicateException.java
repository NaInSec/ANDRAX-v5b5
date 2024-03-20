package com.thecrackertechnology.dragonterminal.frontend.component;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/component/ComponentDuplicateException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "serviceName", "", "(Ljava/lang/String;)V", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ComponentDuplicateException.kt */
public final class ComponentDuplicateException extends RuntimeException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ComponentDuplicateException(String str) {
        super("Service " + str + " duplicate");
        Intrinsics.checkParameterIsNotNull(str, "serviceName");
    }
}
