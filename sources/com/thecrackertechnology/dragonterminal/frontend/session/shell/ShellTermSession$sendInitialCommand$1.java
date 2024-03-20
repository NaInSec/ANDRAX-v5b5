package com.thecrackertechnology.dragonterminal.frontend.session.shell;

import kotlin.Metadata;
import org.apache.commons.lang3.CharUtils;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: ShellTermSession.kt */
final class ShellTermSession$sendInitialCommand$1 implements Runnable {
    final /* synthetic */ String $command;
    final /* synthetic */ ShellTermSession this$0;

    ShellTermSession$sendInitialCommand$1(ShellTermSession shellTermSession, String str) {
        this.this$0 = shellTermSession;
        this.$command = str;
    }

    public final void run() {
        if (this.$command.equals("andrax")) {
            ShellTermSession shellTermSession = this.this$0;
            shellTermSession.write("clear\r" + this.$command + CharUtils.CR);
            return;
        }
        ShellTermSession shellTermSession2 = this.this$0;
        shellTermSession2.write("clear\randrax\r" + this.$command + CharUtils.CR);
    }
}
