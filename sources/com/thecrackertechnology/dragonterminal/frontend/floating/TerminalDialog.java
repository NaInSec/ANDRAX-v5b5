package com.thecrackertechnology.dragonterminal.frontend.floating;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellParameter;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellTermSession;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.BasicSessionCallback;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.client.BasicViewClient;
import com.thecrackertechnology.dragonterminal.utils.TerminalUtils;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001$B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0013\u001a\u00020\u0000J#\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0018¢\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010\u001d\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\fJ\u0010\u0010 \u001a\u00020\u00002\b\u0010!\u001a\u0004\u0018\u00010\u0016J\u0010\u0010\"\u001a\u00020#2\b\u0010!\u001a\u0004\u0018\u00010\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/floating/TerminalDialog;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "cancelListener", "Landroid/content/DialogInterface$OnCancelListener;", "getContext", "()Landroid/content/Context;", "dialog", "Landroid/app/AlertDialog;", "sessionFinishedCallback", "Lcom/thecrackertechnology/dragonterminal/frontend/floating/TerminalDialog$SessionFinishedCallback;", "termWindowView", "Lcom/thecrackertechnology/dragonterminal/frontend/floating/WindowTermView;", "terminalSession", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "terminalSessionCallback", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/BasicSessionCallback;", "dismiss", "execute", "executablePath", "", "arguments", "", "(Ljava/lang/String;[Ljava/lang/String;)Lcom/thecrackertechnology/dragonterminal/frontend/floating/TerminalDialog;", "imeEnabled", "enabled", "", "onDismiss", "onFinish", "finishedCallback", "setTitle", "title", "show", "", "SessionFinishedCallback", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: TerminalDialog.kt */
public final class TerminalDialog {
    /* access modifiers changed from: private */
    public DialogInterface.OnCancelListener cancelListener;
    private final Context context;
    private AlertDialog dialog;
    /* access modifiers changed from: private */
    public SessionFinishedCallback sessionFinishedCallback;
    private WindowTermView termWindowView = new WindowTermView(this.context);
    /* access modifiers changed from: private */
    public TerminalSession terminalSession;
    private BasicSessionCallback terminalSessionCallback;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/floating/TerminalDialog$SessionFinishedCallback;", "", "onSessionFinished", "", "dialog", "Lcom/thecrackertechnology/dragonterminal/frontend/floating/TerminalDialog;", "finishedSession", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: TerminalDialog.kt */
    public interface SessionFinishedCallback {
        void onSessionFinished(TerminalDialog terminalDialog, TerminalSession terminalSession);
    }

    public TerminalDialog(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
        WindowTermView windowTermView = this.termWindowView;
        windowTermView.setTerminalViewClient(new BasicViewClient(windowTermView.getTerminalView()));
        this.terminalSessionCallback = new BasicSessionCallback(this, this.termWindowView.getTerminalView()) {
            final /* synthetic */ TerminalDialog this$0;

            {
                this.this$0 = r1;
            }

            public void onSessionFinished(TerminalSession terminalSession) {
                SessionFinishedCallback access$getSessionFinishedCallback$p = this.this$0.sessionFinishedCallback;
                if (access$getSessionFinishedCallback$p != null) {
                    access$getSessionFinishedCallback$p.onSessionFinished(this.this$0, terminalSession);
                }
                super.onSessionFinished(terminalSession);
            }
        };
    }

    public final Context getContext() {
        return this.context;
    }

    public final TerminalDialog execute(String str, String[] strArr) {
        Intrinsics.checkParameterIsNotNull(str, "executablePath");
        TerminalSession terminalSession2 = this.terminalSession;
        if (!(terminalSession2 == null || terminalSession2 == null)) {
            terminalSession2.finishIfRunning();
        }
        this.dialog = new AlertDialog.Builder(this.context).setView(this.termWindowView.getRootView()).setOnCancelListener(new TerminalDialog$execute$1(this)).create();
        this.terminalSession = TerminalUtils.INSTANCE.createSession(this.context, new ShellParameter().executablePath(str).arguments(strArr).callback(this.terminalSessionCallback).systemShell(false));
        TerminalSession terminalSession3 = this.terminalSession;
        if (terminalSession3 instanceof ShellTermSession) {
            if (terminalSession3 != null) {
                ((ShellTermSession) terminalSession3).setExitPrompt(this.context.getString(R.string.process_exit_prompt_press_back));
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellTermSession");
            }
        }
        this.termWindowView.attachSession(this.terminalSession);
        return this;
    }

    public final TerminalDialog onDismiss(DialogInterface.OnCancelListener onCancelListener) {
        this.cancelListener = onCancelListener;
        return this;
    }

    public final TerminalDialog setTitle(String str) {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.setTitle(str);
        }
        return this;
    }

    public final TerminalDialog onFinish(SessionFinishedCallback sessionFinishedCallback2) {
        Intrinsics.checkParameterIsNotNull(sessionFinishedCallback2, "finishedCallback");
        this.sessionFinishedCallback = sessionFinishedCallback2;
        return this;
    }

    public final void show(String str) {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.setTitle(str);
        }
        AlertDialog alertDialog2 = this.dialog;
        if (alertDialog2 != null) {
            alertDialog2.setCanceledOnTouchOutside(false);
        }
        AlertDialog alertDialog3 = this.dialog;
        if (alertDialog3 != null) {
            alertDialog3.show();
        }
    }

    public final TerminalDialog dismiss() {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        return this;
    }

    public final TerminalDialog imeEnabled(boolean z) {
        if (z) {
            this.termWindowView.setInputMethodEnabled(true);
        }
        return this;
    }
}
