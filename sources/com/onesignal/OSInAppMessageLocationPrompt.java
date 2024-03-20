package com.onesignal;

import com.onesignal.OneSignal;

class OSInAppMessageLocationPrompt extends OSInAppMessagePrompt {
    static final String LOCATION_PROMPT_KEY = "location";

    /* access modifiers changed from: package-private */
    public String getPromptKey() {
        return LOCATION_PROMPT_KEY;
    }

    OSInAppMessageLocationPrompt() {
    }

    /* access modifiers changed from: package-private */
    public void handlePrompt(OneSignal.OSPromptActionCompletionCallback oSPromptActionCompletionCallback) {
        OneSignal.promptLocation(oSPromptActionCompletionCallback, true);
    }
}
