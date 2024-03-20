package com.onesignal;

import org.json.JSONException;

class UserStatePush extends UserState {
    UserStatePush(String str, boolean z) {
        super(str, z);
    }

    /* access modifiers changed from: package-private */
    public UserState newInstance(String str) {
        return new UserStatePush(str, false);
    }

    /* access modifiers changed from: protected */
    public void addDependFields() {
        try {
            this.syncValues.put("notification_types", getNotificationTypes());
        } catch (JSONException unused) {
        }
    }

    private int getNotificationTypes() {
        int optInt = this.dependValues.optInt("subscribableStatus", 1);
        if (optInt < -2) {
            return optInt;
        }
        if (!this.dependValues.optBoolean("androidPermission", true)) {
            return 0;
        }
        return !this.dependValues.optBoolean("userSubscribePref", true) ? -2 : 1;
    }

    /* access modifiers changed from: package-private */
    public boolean isSubscribed() {
        return getNotificationTypes() > 0;
    }
}
