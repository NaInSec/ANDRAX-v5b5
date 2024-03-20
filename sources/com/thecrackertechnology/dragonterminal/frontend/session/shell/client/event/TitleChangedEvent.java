package com.thecrackertechnology.dragonterminal.frontend.session.shell.client.event;

import com.onesignal.OneSignalDbContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/event/TitleChangedEvent;", "", "title", "", "(Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: TitleChangedEvent.kt */
public final class TitleChangedEvent {
    private final String title;

    public TitleChangedEvent(String str) {
        Intrinsics.checkParameterIsNotNull(str, OneSignalDbContract.NotificationTable.COLUMN_NAME_TITLE);
        this.title = str;
    }

    public final String getTitle() {
        return this.title;
    }
}
