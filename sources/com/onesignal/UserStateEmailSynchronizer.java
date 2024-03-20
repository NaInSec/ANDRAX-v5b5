package com.onesignal;

import com.onesignal.OneSignal;
import com.onesignal.OneSignalStateSynchronizer;
import com.onesignal.UserStateSynchronizer;
import org.json.JSONException;
import org.json.JSONObject;

class UserStateEmailSynchronizer extends UserStateSynchronizer {
    /* access modifiers changed from: package-private */
    public String getExternalId(boolean z) {
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean getSubscribed() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public UserStateSynchronizer.GetTagsResult getTags(boolean z) {
        return null;
    }

    public boolean getUserSubscribePreference() {
        return false;
    }

    public void setPermission(boolean z) {
    }

    /* access modifiers changed from: package-private */
    public void setSubscription(boolean z) {
    }

    /* access modifiers changed from: package-private */
    public void updateState(JSONObject jSONObject) {
    }

    UserStateEmailSynchronizer() {
        super(OneSignalStateSynchronizer.UserStateSynchronizerType.EMAIL);
    }

    /* access modifiers changed from: protected */
    public UserState newUserState(String str, boolean z) {
        return new UserStateEmail(str, z);
    }

    /* access modifiers changed from: protected */
    public OneSignal.LOG_LEVEL getLogLevel() {
        return OneSignal.LOG_LEVEL.INFO;
    }

    /* access modifiers changed from: package-private */
    public void refresh() {
        scheduleSyncToServer();
    }

    /* access modifiers changed from: protected */
    public void scheduleSyncToServer() {
        if (!(getId() == null && getRegistrationId() == null) && OneSignal.getUserId() != null) {
            getNetworkHandlerThread(0).runNewJobDelayed();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setEmail(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            com.onesignal.UserState r0 = r7.getUserStateForModification()
            org.json.JSONObject r0 = r0.syncValues
            java.lang.String r1 = "identifier"
            java.lang.String r2 = r0.optString(r1)
            boolean r2 = r8.equals(r2)
            java.lang.String r3 = ""
            java.lang.String r4 = "email_auth_hash"
            if (r2 == 0) goto L_0x0027
            java.lang.String r2 = r0.optString(r4)
            if (r9 != 0) goto L_0x001e
            r5 = r3
            goto L_0x001f
        L_0x001e:
            r5 = r9
        L_0x001f:
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x0027
            r2 = 1
            goto L_0x0028
        L_0x0027:
            r2 = 0
        L_0x0028:
            if (r2 == 0) goto L_0x002e
            com.onesignal.OneSignal.fireEmailUpdateSuccess()
            return
        L_0x002e:
            r2 = 0
            java.lang.String r5 = r0.optString(r1, r2)
            if (r5 != 0) goto L_0x0038
            r7.setNewSession()
        L_0x0038:
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ JSONException -> 0x005f }
            r6.<init>()     // Catch:{ JSONException -> 0x005f }
            r6.put(r1, r8)     // Catch:{ JSONException -> 0x005f }
            if (r9 == 0) goto L_0x0045
            r6.put(r4, r9)     // Catch:{ JSONException -> 0x005f }
        L_0x0045:
            if (r9 != 0) goto L_0x0058
            if (r5 == 0) goto L_0x0058
            boolean r8 = r5.equals(r8)     // Catch:{ JSONException -> 0x005f }
            if (r8 != 0) goto L_0x0058
            com.onesignal.OneSignal.saveEmailId(r3)     // Catch:{ JSONException -> 0x005f }
            r7.resetCurrentState()     // Catch:{ JSONException -> 0x005f }
            r7.setNewSession()     // Catch:{ JSONException -> 0x005f }
        L_0x0058:
            r7.generateJsonDiff(r0, r6, r0, r2)     // Catch:{ JSONException -> 0x005f }
            r7.scheduleSyncToServer()     // Catch:{ JSONException -> 0x005f }
            goto L_0x0063
        L_0x005f:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0063:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.UserStateEmailSynchronizer.setEmail(java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: protected */
    public String getId() {
        return OneSignal.getEmailId();
    }

    /* access modifiers changed from: package-private */
    public void updateIdDependents(String str) {
        OneSignal.updateEmailIdDependents(str);
    }

    /* access modifiers changed from: protected */
    public void addOnSessionOrCreateExtras(JSONObject jSONObject) {
        try {
            jSONObject.put("device_type", 11);
            jSONObject.putOpt("device_player_id", OneSignal.getUserId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public void logoutEmail() {
        OneSignal.saveEmailId("");
        resetCurrentState();
        getToSyncUserState().syncValues.remove("identifier");
        this.toSyncUserState.syncValues.remove("email_auth_hash");
        this.toSyncUserState.syncValues.remove("device_player_id");
        this.toSyncUserState.syncValues.remove("external_user_id");
        this.toSyncUserState.persistState();
        OneSignal.getPermissionSubscriptionState().emailSubscriptionStatus.clearEmailAndId();
    }

    /* access modifiers changed from: protected */
    public void fireEventsForUpdateFailure(JSONObject jSONObject) {
        if (jSONObject.has("identifier")) {
            OneSignal.fireEmailUpdateFailure();
        }
    }

    /* access modifiers changed from: protected */
    public void onSuccessfulSync(JSONObject jSONObject) {
        if (jSONObject.has("identifier")) {
            OneSignal.fireEmailUpdateSuccess();
        }
    }
}
