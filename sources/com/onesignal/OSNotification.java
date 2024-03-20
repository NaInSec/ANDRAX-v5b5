package com.onesignal;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OSNotification {
    public int androidNotificationId;
    public DisplayType displayType;
    public List<OSNotificationPayload> groupedNotifications;
    public boolean isAppInFocus;
    public OSNotificationPayload payload;
    public boolean shown;

    public enum DisplayType {
        Notification,
        InAppAlert,
        None
    }

    public OSNotification() {
    }

    public OSNotification(JSONObject jSONObject) {
        this.isAppInFocus = jSONObject.optBoolean("isAppInFocus");
        this.shown = jSONObject.optBoolean("shown", this.shown);
        this.androidNotificationId = jSONObject.optInt(GenerateNotification.BUNDLE_KEY_ANDROID_NOTIFICATION_ID);
        this.displayType = DisplayType.values()[jSONObject.optInt("displayType")];
        if (jSONObject.has("groupedNotifications")) {
            JSONArray optJSONArray = jSONObject.optJSONArray("groupedNotifications");
            this.groupedNotifications = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                this.groupedNotifications.add(new OSNotificationPayload(optJSONArray.optJSONObject(i)));
            }
        }
        if (jSONObject.has("payload")) {
            this.payload = new OSNotificationPayload(jSONObject.optJSONObject("payload"));
        }
    }

    @Deprecated
    public String stringify() {
        JSONObject jSONObject = toJSONObject();
        try {
            if (jSONObject.has("additionalData")) {
                jSONObject.put("additionalData", jSONObject.optJSONObject("additionalData").toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isAppInFocus", this.isAppInFocus);
            jSONObject.put("shown", this.shown);
            jSONObject.put(GenerateNotification.BUNDLE_KEY_ANDROID_NOTIFICATION_ID, this.androidNotificationId);
            jSONObject.put("displayType", this.displayType.ordinal());
            if (this.groupedNotifications != null) {
                JSONArray jSONArray = new JSONArray();
                for (OSNotificationPayload jSONObject2 : this.groupedNotifications) {
                    jSONArray.put(jSONObject2.toJSONObject());
                }
                jSONObject.put("groupedNotifications", jSONArray);
            }
            jSONObject.put("payload", this.payload.toJSONObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
