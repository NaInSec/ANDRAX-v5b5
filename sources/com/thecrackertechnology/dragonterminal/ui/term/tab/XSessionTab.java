package com.thecrackertechnology.dragonterminal.ui.term.tab;

import android.content.res.Configuration;
import com.onesignal.OneSignalDbContract;
import com.thecrackertechnology.dragonterminal.frontend.session.xorg.XSession;
import com.thecrackertechnology.dragonterminal.frontend.session.xorg.client.XSessionData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0010H\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\b\u0010\u0015\u001a\u00020\u0010H\u0016J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/term/tab/XSessionTab;", "Lcom/thecrackertechnology/dragonterminal/ui/term/tab/NeoTab;", "title", "", "(Ljava/lang/CharSequence;)V", "session", "Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/XSession;", "getSession", "()Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/XSession;", "setSession", "(Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/XSession;)V", "sessionData", "Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/client/XSessionData;", "getSessionData", "()Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/client/XSessionData;", "onConfigurationChanged", "", "newConfig", "Landroid/content/res/Configuration;", "onDestroy", "onPause", "onResume", "onWindowFocusChanged", "hasFocus", "", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: XSessionTab.kt */
public final class XSessionTab extends NeoTab {
    private XSession session;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public XSessionTab(CharSequence charSequence) {
        super(charSequence);
        Intrinsics.checkParameterIsNotNull(charSequence, OneSignalDbContract.NotificationTable.COLUMN_NAME_TITLE);
    }

    public final XSession getSession() {
        return this.session;
    }

    public final void setSession(XSession xSession) {
        this.session = xSession;
    }

    public final XSessionData getSessionData() {
        XSession xSession = this.session;
        if (xSession != null) {
            return xSession.getMSessionData();
        }
        return null;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!z) {
            onPause();
        } else {
            onResume();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkParameterIsNotNull(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        XSession xSession = this.session;
        if (xSession != null) {
            xSession.updateScreenOrientation();
        }
    }

    public void onPause() {
        XSession xSession = this.session;
        if (xSession != null) {
            xSession.onPause();
        }
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
        XSession xSession = this.session;
        if (xSession != null) {
            xSession.onDestroy();
        }
    }

    public void onResume() {
        super.onResume();
        XSession xSession = this.session;
        if (xSession != null) {
            xSession.onResume();
        }
    }
}
