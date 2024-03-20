package com.onesignal.influence;

import com.onesignal.OSLogger;
import com.onesignal.influence.model.OSInfluence;
import com.onesignal.influence.model.OSInfluenceChannel;
import com.onesignal.influence.model.OSInfluenceType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class OSInAppMessageTracker extends OSChannelTracker {
    private static final String IAM_ID = "iam_id";
    public static final String TAG = OSInAppMessageTracker.class.getCanonicalName();

    /* access modifiers changed from: package-private */
    public void addSessionData(JSONObject jSONObject, OSInfluence oSInfluence) {
    }

    public String getIdTag() {
        return IAM_ID;
    }

    OSInAppMessageTracker(OSInfluenceDataRepository oSInfluenceDataRepository, OSLogger oSLogger) {
        super(oSInfluenceDataRepository, oSLogger);
    }

    /* access modifiers changed from: package-private */
    public OSInfluenceChannel getChannelType() {
        return OSInfluenceChannel.IAM;
    }

    /* access modifiers changed from: package-private */
    public JSONArray getLastChannelObjectsReceivedByNewId(String str) {
        try {
            JSONArray lastChannelObjects = getLastChannelObjects();
            try {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < lastChannelObjects.length(); i++) {
                    if (!str.equals(lastChannelObjects.getJSONObject(i).getString(getIdTag()))) {
                        jSONArray.put(lastChannelObjects.getJSONObject(i));
                    }
                }
                return jSONArray;
            } catch (JSONException e) {
                this.logger.error("Before KITKAT API, Generating tracker lastChannelObjectReceived get JSONObject ", e);
                return lastChannelObjects;
            }
        } catch (JSONException e2) {
            this.logger.error("Generating IAM tracker getLastChannelObjects JSONObject ", e2);
            return new JSONArray();
        }
    }

    /* access modifiers changed from: package-private */
    public JSONArray getLastChannelObjects() throws JSONException {
        return this.dataRepository.getLastIAMsReceivedData();
    }

    /* access modifiers changed from: package-private */
    public int getChannelLimit() {
        return this.dataRepository.getIAMLimit();
    }

    /* access modifiers changed from: package-private */
    public int getIndirectAttributionWindow() {
        return this.dataRepository.getIAMIndirectAttributionWindow();
    }

    /* access modifiers changed from: package-private */
    public void saveChannelObjects(JSONArray jSONArray) {
        this.dataRepository.saveIAMs(jSONArray);
    }

    /* access modifiers changed from: package-private */
    public void initInfluencedTypeFromCache() {
        setInfluenceType(this.dataRepository.getIAMCachedInfluenceType());
        if (this.influenceType != null && this.influenceType.isIndirect()) {
            setIndirectIds(getLastReceivedIds());
        }
        OSLogger oSLogger = this.logger;
        oSLogger.debug("OneSignal InAppMessageTracker initInfluencedTypeFromCache: " + toString());
    }

    public void cacheState() {
        OSInfluenceType oSInfluenceType = this.influenceType == null ? OSInfluenceType.UNATTRIBUTED : this.influenceType;
        OSInfluenceDataRepository oSInfluenceDataRepository = this.dataRepository;
        if (oSInfluenceType == OSInfluenceType.DIRECT) {
            oSInfluenceType = OSInfluenceType.INDIRECT;
        }
        oSInfluenceDataRepository.cacheIAMInfluenceType(oSInfluenceType);
    }
}
