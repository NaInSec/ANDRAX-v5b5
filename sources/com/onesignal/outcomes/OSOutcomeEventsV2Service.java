package com.onesignal.outcomes;

import com.onesignal.OneSignalAPIClient;
import com.onesignal.OneSignalApiResponseHandler;
import org.json.JSONObject;

class OSOutcomeEventsV2Service extends OSOutcomeEventsClient {
    OSOutcomeEventsV2Service(OneSignalAPIClient oneSignalAPIClient) {
        super(oneSignalAPIClient);
    }

    public void sendOutcomeEvent(JSONObject jSONObject, OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        this.client.post("outcomes/measure_sources", jSONObject, oneSignalApiResponseHandler);
    }
}
