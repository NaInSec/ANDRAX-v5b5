package com.thecrackertechnology.dragonterminal.frontend.session.shell.client;

import android.content.Context;
import android.media.SoundPool;
import android.os.Vibrator;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellTermSession;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/BellController;", "", "()V", "bellId", "", "lastBellTime", "", "soundPool", "Landroid/media/SoundPool;", "bellOrVibrate", "", "context", "Landroid/content/Context;", "session", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellTermSession;", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: BellController.kt */
public final class BellController {
    private static final int BELL_DELAY_MS = 100;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private int bellId;
    private long lastBellTime;
    private SoundPool soundPool;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/BellController$Companion;", "", "()V", "BELL_DELAY_MS", "", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: BellController.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final void bellOrVibrate(Context context, ShellTermSession shellTermSession) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(shellTermSession, "session");
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastBellTime >= ((long) BELL_DELAY_MS)) {
            this.lastBellTime = currentTimeMillis;
            if (shellTermSession.getShellProfile().getEnableBell()) {
                if (this.soundPool == null) {
                    this.soundPool = new SoundPool.Builder().setMaxStreams(1).build();
                    SoundPool soundPool2 = this.soundPool;
                    if (soundPool2 == null) {
                        Intrinsics.throwNpe();
                    }
                    this.bellId = soundPool2.load(context, R.raw.bell, 1);
                }
                SoundPool soundPool3 = this.soundPool;
                if (soundPool3 != null) {
                    soundPool3.play(this.bellId, 1.0f, 1.0f, 0, 0, 1.0f);
                }
            }
            if (shellTermSession.getShellProfile().getEnableVibrate()) {
                Object systemService = context.getSystemService("vibrator");
                if (systemService != null) {
                    ((Vibrator) systemService).vibrate(300);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type android.os.Vibrator");
            }
        }
    }
}
