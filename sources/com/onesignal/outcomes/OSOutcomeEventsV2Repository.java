package com.onesignal.outcomes;

import com.onesignal.OSLogger;
import com.onesignal.OneSignalApiResponseHandler;
import com.onesignal.outcomes.domain.OutcomeEventsService;
import com.onesignal.outcomes.model.OSOutcomeEventParams;
import org.json.JSONException;
import org.json.JSONObject;

class OSOutcomeEventsV2Repository extends OSOutcomeEventsRepository {
    OSOutcomeEventsV2Repository(OSLogger oSLogger, OSOutcomeEventsCache oSOutcomeEventsCache, OutcomeEventsService outcomeEventsService) {
        super(oSLogger, oSOutcomeEventsCache, outcomeEventsService);
    }

    public void requestMeasureOutcomeEvent(String str, int i, OSOutcomeEventParams oSOutcomeEventParams, OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        try {
            JSONObject jSONObject = oSOutcomeEventParams.toJSONObject();
            jSONObject.put("app_id", str);
            jSONObject.put("device_type", i);
            this.outcomeEventsService.sendOutcomeEvent(jSONObject, oneSignalApiResponseHandler);
        } catch (JSONException e) {
            this.logger.error("Generating indirect outcome:JSON Failed.", e);
        }
    }
}
