package com.onesignal;

import com.onesignal.OneSignal;
import com.onesignal.OneSignalRestClient;
import com.onesignal.OneSignalStateSynchronizer;
import com.onesignal.UserStateSynchronizer;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class UserStatePushSynchronizer extends UserStateSynchronizer {
    /* access modifiers changed from: private */
    public static boolean serverSuccess;

    /* access modifiers changed from: protected */
    public void addOnSessionOrCreateExtras(JSONObject jSONObject) {
    }

    UserStatePushSynchronizer() {
        super(OneSignalStateSynchronizer.UserStateSynchronizerType.PUSH);
    }

    /* access modifiers changed from: protected */
    public UserState newUserState(String str, boolean z) {
        return new UserStatePush(str, z);
    }

    /* access modifiers changed from: protected */
    public OneSignal.LOG_LEVEL getLogLevel() {
        return OneSignal.LOG_LEVEL.ERROR;
    }

    /* access modifiers changed from: package-private */
    public boolean getSubscribed() {
        return getToSyncUserState().isSubscribed();
    }

    /* access modifiers changed from: package-private */
    public UserStateSynchronizer.GetTagsResult getTags(boolean z) {
        UserStateSynchronizer.GetTagsResult getTagsResult;
        if (z) {
            String userId = OneSignal.getUserId();
            String savedAppId = OneSignal.getSavedAppId();
            OneSignalRestClient.getSync("players/" + userId + "?app_id=" + savedAppId, new OneSignalRestClient.ResponseHandler() {
                /* access modifiers changed from: package-private */
                public void onSuccess(String str) {
                    boolean unused = UserStatePushSynchronizer.serverSuccess = true;
                    if (str == null || str.isEmpty()) {
                        str = "{}";
                    }
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject.has("tags")) {
                            synchronized (UserStatePushSynchronizer.this.syncLock) {
                                JSONObject generateJsonDiff = UserStatePushSynchronizer.this.generateJsonDiff(UserStatePushSynchronizer.this.currentUserState.syncValues.optJSONObject("tags"), UserStatePushSynchronizer.this.getToSyncUserState().syncValues.optJSONObject("tags"), (JSONObject) null, (Set<String>) null);
                                UserStatePushSynchronizer.this.currentUserState.syncValues.put("tags", jSONObject.optJSONObject("tags"));
                                UserStatePushSynchronizer.this.currentUserState.persistState();
                                UserStatePushSynchronizer.this.getToSyncUserState().mergeTags(jSONObject, generateJsonDiff);
                                UserStatePushSynchronizer.this.getToSyncUserState().persistState();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, "CACHE_KEY_GET_TAGS");
        }
        synchronized (this.syncLock) {
            getTagsResult = new UserStateSynchronizer.GetTagsResult(serverSuccess, JSONUtils.getJSONObjectWithoutBlankValues(this.toSyncUserState.syncValues, "tags"));
        }
        return getTagsResult;
    }

    /* access modifiers changed from: package-private */
    public String getExternalId(boolean z) {
        String optString;
        synchronized (this.syncLock) {
            optString = this.toSyncUserState.syncValues.optString("external_user_id", (String) null);
        }
        return optString;
    }

    /* access modifiers changed from: protected */
    public void scheduleSyncToServer() {
        getNetworkHandlerThread(0).runNewJobDelayed();
    }

    /* access modifiers changed from: package-private */
    public void updateState(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("identifier", jSONObject.optString("identifier", (String) null));
            if (jSONObject.has("device_type")) {
                jSONObject2.put("device_type", jSONObject.optInt("device_type"));
            }
            jSONObject2.putOpt("parent_player_id", jSONObject.optString("parent_player_id", (String) null));
            JSONObject jSONObject3 = getUserStateForModification().syncValues;
            generateJsonDiff(jSONObject3, jSONObject2, jSONObject3, (Set<String>) null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jSONObject4 = new JSONObject();
            if (jSONObject.has("subscribableStatus")) {
                jSONObject4.put("subscribableStatus", jSONObject.optInt("subscribableStatus"));
            }
            if (jSONObject.has("androidPermission")) {
                jSONObject4.put("androidPermission", jSONObject.optBoolean("androidPermission"));
            }
            JSONObject jSONObject5 = getUserStateForModification().dependValues;
            generateJsonDiff(jSONObject5, jSONObject4, jSONObject5, (Set<String>) null);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public void setEmail(String str, String str2) {
        try {
            UserState userStateForModification = getUserStateForModification();
            userStateForModification.dependValues.put("email_auth_hash", str2);
            JSONObject jSONObject = userStateForModification.syncValues;
            generateJsonDiff(jSONObject, new JSONObject().put("email", str), jSONObject, (Set<String>) null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public void setSubscription(boolean z) {
        try {
            getUserStateForModification().dependValues.put("userSubscribePref", z);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean getUserSubscribePreference() {
        return getToSyncUserState().dependValues.optBoolean("userSubscribePref", true);
    }

    public void setPermission(boolean z) {
        try {
            getUserStateForModification().dependValues.put("androidPermission", z);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public String getId() {
        return OneSignal.getUserId();
    }

    /* access modifiers changed from: package-private */
    public void updateIdDependents(String str) {
        OneSignal.updateUserIdDependents(str);
    }

    /* access modifiers changed from: package-private */
    public void logoutEmail() {
        try {
            getUserStateForModification().dependValues.put("logoutEmail", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void fireEventsForUpdateFailure(JSONObject jSONObject) {
        if (jSONObject.has("email")) {
            OneSignal.fireEmailUpdateFailure();
        }
    }

    /* access modifiers changed from: protected */
    public void onSuccessfulSync(JSONObject jSONObject) {
        if (jSONObject.has("email")) {
            OneSignal.fireEmailUpdateSuccess();
        }
        if (jSONObject.has("identifier")) {
            OneSignal.fireIdsAvailableCallback();
        }
    }
}
