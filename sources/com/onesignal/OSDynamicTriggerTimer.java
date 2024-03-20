package com.onesignal;

import java.util.Timer;
import java.util.TimerTask;

class OSDynamicTriggerTimer {
    OSDynamicTriggerTimer() {
    }

    static void scheduleTrigger(TimerTask timerTask, String str, long j) {
        new Timer("trigger_timer:" + str).schedule(timerTask, j);
    }
}
