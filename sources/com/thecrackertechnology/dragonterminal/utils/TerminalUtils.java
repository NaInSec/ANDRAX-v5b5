package com.thecrackertechnology.dragonterminal.utils;

import android.app.Activity;
import android.content.Context;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.component.font.FontComponent;
import com.thecrackertechnology.dragonterminal.component.session.SessionComponent;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellParameter;
import com.thecrackertechnology.dragonterminal.frontend.session.xorg.XParameter;
import com.thecrackertechnology.dragonterminal.frontend.session.xorg.XSession;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalViewClient;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.ExtraKeysView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u0003\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u001c\u0010\u0014\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018¨\u0006\u0019"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/utils/TerminalUtils;", "", "()V", "createSession", "Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/XSession;", "activity", "Landroid/app/Activity;", "parameter", "Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/XParameter;", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "context", "Landroid/content/Context;", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellParameter;", "escapeString", "", "s", "setupExtraKeysView", "", "extraKeysView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView;", "setupTerminalView", "terminalView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "terminalViewClient", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalViewClient;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: TerminalUtils.kt */
public final class TerminalUtils {
    public static final TerminalUtils INSTANCE = new TerminalUtils();

    private TerminalUtils() {
    }

    public static /* synthetic */ void setupTerminalView$default(TerminalUtils terminalUtils, TerminalView terminalView, TerminalViewClient terminalViewClient, int i, Object obj) {
        if ((i & 2) != 0) {
            terminalViewClient = null;
        }
        terminalUtils.setupTerminalView(terminalView, terminalViewClient);
    }

    public final void setupTerminalView(TerminalView terminalView, TerminalViewClient terminalViewClient) {
        if (terminalView != null) {
            terminalView.setTextSize(NeoPreference.INSTANCE.getFontSize());
        }
        FontComponent fontComponent = (FontComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, FontComponent.class, false, 2, (Object) null);
        fontComponent.applyFont(terminalView, (ExtraKeysView) null, fontComponent.getCurrentFont());
        if (terminalViewClient != null && terminalView != null) {
            terminalView.setTerminalViewClient(terminalViewClient);
        }
    }

    public final void setupExtraKeysView(ExtraKeysView extraKeysView) {
        FontComponent fontComponent = (FontComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, FontComponent.class, false, 2, (Object) null);
        fontComponent.applyFont((TerminalView) null, extraKeysView, fontComponent.getCurrentFont());
    }

    public final TerminalSession createSession(Context context, ShellParameter shellParameter) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(shellParameter, "parameter");
        return ((SessionComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, SessionComponent.class, false, 2, (Object) null)).createSession(context, shellParameter);
    }

    public final XSession createSession(Activity activity, XParameter xParameter) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(xParameter, "parameter");
        return ((SessionComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, SessionComponent.class, false, 2, (Object) null)).createSession((Context) activity, xParameter);
    }

    public final String escapeString(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.quote);
        int i = 0;
        int length = str.length() - 1;
        if (length >= 0) {
            while (true) {
                char charAt = str.charAt(i);
                if (StringsKt.indexOf$default((CharSequence) "\"\\$`!", charAt, 0, false, 6, (Object) null) >= 0) {
                    sb.append('\\');
                }
                sb.append(charAt);
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        sb.append(Typography.quote);
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "builder.toString()");
        return sb2;
    }
}
