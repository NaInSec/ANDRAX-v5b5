package com.onesignal.outcomes;

import com.onesignal.OSLogger;
import com.onesignal.OneSignalApiResponseHandler;
import com.onesignal.OutcomeEvent;
import com.onesignal.influence.model.OSInfluenceType;
import com.onesignal.outcomes.domain.OutcomeEventsService;
import com.onesignal.outcomes.model.OSOutcomeEventParams;
import org.json.JSONException;
import org.json.JSONObject;

class OSOutcomeEventsV1Repository extends OSOutcomeEventsRepository {
    private static final String DIRECT = "direct";

    OSOutcomeEventsV1Repository(OSLogger oSLogger, OSOutcomeEventsCache oSOutcomeEventsCache, OutcomeEventsService outcomeEventsService) {
        super(oSLogger, oSOutcomeEventsCache, outcomeEventsService);
    }

    /* renamed from: com.onesignal.outcomes.OSOutcomeEventsV1Repository$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$influence$model$OSInfluenceType = new int[OSInfluenceType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.onesignal.influence.model.OSInfluenceType[] r0 = com.onesignal.influence.model.OSInfluenceType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$onesignal$influence$model$OSInfluenceType = r0
                int[] r0 = $SwitchMap$com$onesignal$influence$model$OSInfluenceType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.onesignal.influence.model.OSInfluenceType r1 = com.onesignal.influence.model.OSInfluenceType.DIRECT     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$onesignal$influence$model$OSInfluenceType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.onesignal.influence.model.OSInfluenceType r1 = com.onesignal.influence.model.OSInfluenceType.INDIRECT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$onesignal$influence$model$OSInfluenceType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.onesignal.influence.model.OSInfluenceType r1 = com.onesignal.influence.model.OSInfluenceType.UNATTRIBUTED     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$com$onesignal$influence$model$OSInfluenceType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.onesignal.influence.model.OSInfluenceType r1 = com.onesignal.influence.model.OSInfluenceType.DISABLED     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.outcomes.OSOutcomeEventsV1Repository.AnonymousClass1.<clinit>():void");
        }
    }

    public void requestMeasureOutcomeEvent(String str, int i, OSOutcomeEventParams oSOutcomeEventParams, OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        OutcomeEvent fromOutcomeEventParamsV2toOutcomeEventV1 = OutcomeEvent.fromOutcomeEventParamsV2toOutcomeEventV1(oSOutcomeEventParams);
        int i2 = AnonymousClass1.$SwitchMap$com$onesignal$influence$model$OSInfluenceType[fromOutcomeEventParamsV2toOutcomeEventV1.getSession().ordinal()];
        if (i2 == 1) {
            requestMeasureDirectOutcomeEvent(str, i, fromOutcomeEventParamsV2toOutcomeEventV1, oneSignalApiResponseHandler);
        } else if (i2 == 2) {
            requestMeasureIndirectOutcomeEvent(str, i, fromOutcomeEventParamsV2toOutcomeEventV1, oneSignalApiResponseHandler);
        } else if (i2 == 3) {
            requestMeasureUnattributedOutcomeEvent(str, i, fromOutcomeEventParamsV2toOutcomeEventV1, oneSignalApiResponseHandler);
        }
    }

    private void requestMeasureDirectOutcomeEvent(String str, int i, OutcomeEvent outcomeEvent, OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        try {
            JSONObject jSONObjectForMeasure = outcomeEvent.toJSONObjectForMeasure();
            jSONObjectForMeasure.put("app_id", str);
            jSONObjectForMeasure.put("device_type", i);
            jSONObjectForMeasure.put(DIRECT, true);
            this.outcomeEventsService.sendOutcomeEvent(jSONObjectForMeasure, oneSignalApiResponseHandler);
        } catch (JSONException e) {
            this.logger.error("Generating direct outcome:JSON Failed.", e);
        }
    }

    private void requestMeasureIndirectOutcomeEvent(String str, int i, OutcomeEvent outcomeEvent, OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        try {
            JSONObject jSONObjectForMeasure = outcomeEvent.toJSONObjectForMeasure();
            jSONObjectForMeasure.put("app_id", str);
            jSONObjectForMeasure.put("device_type", i);
            jSONObjectForMeasure.put(DIRECT, false);
            this.outcomeEventsService.sendOutcomeEvent(jSONObjectForMeasure, oneSignalApiResponseHandler);
        } catch (JSONException e) {
            this.logger.error("Generating indirect outcome:JSON Failed.", e);
        }
    }

    private void requestMeasureUnattributedOutcomeEvent(String str, int i, OutcomeEvent outcomeEvent, OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        try {
            JSONObject jSONObjectForMeasure = outcomeEvent.toJSONObjectForMeasure();
            jSONObjectForMeasure.put("app_id", str);
            jSONObjectForMeasure.put("device_type", i);
            this.outcomeEventsService.sendOutcomeEvent(jSONObjectForMeasure, oneSignalApiResponseHandler);
        } catch (JSONException e) {
            this.logger.error("Generating unattributed outcome:JSON Failed.", e);
        }
    }
}
