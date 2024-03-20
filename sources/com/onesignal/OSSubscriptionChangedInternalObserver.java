package com.onesignal;

class OSSubscriptionChangedInternalObserver {
    OSSubscriptionChangedInternalObserver() {
    }

    public void changed(OSSubscriptionState oSSubscriptionState) {
        fireChangesToPublicObserver(oSSubscriptionState);
    }

    static void fireChangesToPublicObserver(OSSubscriptionState oSSubscriptionState) {
        OSSubscriptionStateChanges oSSubscriptionStateChanges = new OSSubscriptionStateChanges();
        oSSubscriptionStateChanges.from = OneSignal.lastSubscriptionState;
        oSSubscriptionStateChanges.to = (OSSubscriptionState) oSSubscriptionState.clone();
        if (OneSignal.getSubscriptionStateChangesObserver().notifyChange(oSSubscriptionStateChanges)) {
            OneSignal.lastSubscriptionState = (OSSubscriptionState) oSSubscriptionState.clone();
            OneSignal.lastSubscriptionState.persistAsFrom();
        }
    }
}
