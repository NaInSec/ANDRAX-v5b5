package com.onesignal;

import com.onesignal.OneSignalRestClient;
import org.json.JSONObject;

class OneSignalRestClientWrapper implements OneSignalAPIClient {
    OneSignalRestClientWrapper() {
    }

    public void put(String str, JSONObject jSONObject, final OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        OneSignalRestClient.put(str, jSONObject, new OneSignalRestClient.ResponseHandler() {
            public void onSuccess(String str) {
                oneSignalApiResponseHandler.onSuccess(str);
            }

            public void onFailure(int i, String str, Throwable th) {
                oneSignalApiResponseHandler.onFailure(i, str, th);
            }
        });
    }

    public void post(String str, JSONObject jSONObject, final OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        OneSignalRestClient.post(str, jSONObject, new OneSignalRestClient.ResponseHandler() {
            public void onSuccess(String str) {
                oneSignalApiResponseHandler.onSuccess(str);
            }

            public void onFailure(int i, String str, Throwable th) {
                oneSignalApiResponseHandler.onFailure(i, str, th);
            }
        });
    }

    public void get(String str, final OneSignalApiResponseHandler oneSignalApiResponseHandler, String str2) {
        OneSignalRestClient.get(str, new OneSignalRestClient.ResponseHandler() {
            public void onSuccess(String str) {
                oneSignalApiResponseHandler.onSuccess(str);
            }

            public void onFailure(int i, String str, Throwable th) {
                oneSignalApiResponseHandler.onFailure(i, str, th);
            }
        }, str2);
    }

    public void getSync(String str, final OneSignalApiResponseHandler oneSignalApiResponseHandler, String str2) {
        OneSignalRestClient.getSync(str, new OneSignalRestClient.ResponseHandler() {
            public void onSuccess(String str) {
                oneSignalApiResponseHandler.onSuccess(str);
            }

            public void onFailure(int i, String str, Throwable th) {
                oneSignalApiResponseHandler.onFailure(i, str, th);
            }
        }, str2);
    }

    public void putSync(String str, JSONObject jSONObject, final OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        OneSignalRestClient.putSync(str, jSONObject, new OneSignalRestClient.ResponseHandler() {
            public void onSuccess(String str) {
                oneSignalApiResponseHandler.onSuccess(str);
            }

            public void onFailure(int i, String str, Throwable th) {
                oneSignalApiResponseHandler.onFailure(i, str, th);
            }
        });
    }

    public void postSync(String str, JSONObject jSONObject, final OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        OneSignalRestClient.postSync(str, jSONObject, new OneSignalRestClient.ResponseHandler() {
            public void onSuccess(String str) {
                oneSignalApiResponseHandler.onSuccess(str);
            }

            public void onFailure(int i, String str, Throwable th) {
                oneSignalApiResponseHandler.onFailure(i, str, th);
            }
        });
    }
}
