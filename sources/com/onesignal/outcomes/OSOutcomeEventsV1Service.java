package com.onesignal.outcomes;

import com.onesignal.OneSignalAPIClient;
import com.onesignal.OneSignalApiResponseHandler;
import org.json.JSONObject;

class OSOutcomeEventsV1Service extends OSOutcomeEventsClient {
    OSOutcomeEventsV1Service(OneSignalAPIClient oneSignalAPIClient) {
        super(oneSignalAPIClient);
    }

    public void sendOutcomeEvent(JSONObject jSONObject, OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        this.client.post("outcomes/measure", jSONObject, oneSignalApiResponseHandler);
    }
}
