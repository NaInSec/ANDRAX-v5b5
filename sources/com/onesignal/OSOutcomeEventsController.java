package com.onesignal;

import com.onesignal.OneSignal;
import com.onesignal.influence.model.OSInfluence;
import com.onesignal.influence.model.OSInfluenceChannel;
import com.onesignal.influence.model.OSInfluenceType;
import com.onesignal.outcomes.OSOutcomeEventsFactory;
import com.onesignal.outcomes.model.OSOutcomeEventParams;
import com.onesignal.outcomes.model.OSOutcomeSource;
import com.onesignal.outcomes.model.OSOutcomeSourceBody;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class OSOutcomeEventsController {
    private static final String OS_SAVE_OUTCOMES = "OS_SAVE_OUTCOMES";
    private static final String OS_SAVE_UNIQUE_OUTCOME_NOTIFICATIONS = "OS_SAVE_UNIQUE_OUTCOME_NOTIFICATIONS";
    private static final String OS_SEND_SAVED_OUTCOMES = "OS_SEND_SAVED_OUTCOMES";
    private final OSSessionManager osSessionManager;
    /* access modifiers changed from: private */
    public final OSOutcomeEventsFactory outcomeEventsFactory;
    private Set<String> unattributedUniqueOutcomeEventsSentOnSession;

    public OSOutcomeEventsController(OSSessionManager oSSessionManager, OSOutcomeEventsFactory oSOutcomeEventsFactory) {
        this.osSessionManager = oSSessionManager;
        this.outcomeEventsFactory = oSOutcomeEventsFactory;
        initUniqueOutcomeEventsSentSets();
    }

    private void initUniqueOutcomeEventsSentSets() {
        this.unattributedUniqueOutcomeEventsSentOnSession = OSUtils.newConcurrentSet();
        Set<String> unattributedUniqueOutcomeEventsSent = this.outcomeEventsFactory.getRepository().getUnattributedUniqueOutcomeEventsSent();
        if (unattributedUniqueOutcomeEventsSent != null) {
            this.unattributedUniqueOutcomeEventsSentOnSession = unattributedUniqueOutcomeEventsSent;
        }
    }

    /* access modifiers changed from: package-private */
    public void cleanOutcomes() {
        OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "OneSignal cleanOutcomes for session");
        this.unattributedUniqueOutcomeEventsSentOnSession = OSUtils.newConcurrentSet();
        saveUnattributedUniqueOutcomeEvents();
    }

    /* access modifiers changed from: package-private */
    public void sendSavedOutcomes() {
        new Thread(new Runnable() {
            public void run() {
                Thread.currentThread().setPriority(10);
                for (OSOutcomeEventParams access$100 : OSOutcomeEventsController.this.outcomeEventsFactory.getRepository().getSavedOutcomeEvents()) {
                    OSOutcomeEventsController.this.sendSavedOutcomeEvent(access$100);
                }
            }
        }, OS_SEND_SAVED_OUTCOMES).start();
    }

    /* access modifiers changed from: private */
    public void sendSavedOutcomeEvent(final OSOutcomeEventParams oSOutcomeEventParams) {
        int deviceType = new OSUtils().getDeviceType();
        this.outcomeEventsFactory.getRepository().requestMeasureOutcomeEvent(OneSignal.appId, deviceType, oSOutcomeEventParams, new OneSignalApiResponseHandler() {
            public void onFailure(int i, String str, Throwable th) {
            }

            public void onSuccess(String str) {
                OSOutcomeEventsController.this.outcomeEventsFactory.getRepository().removeEvent(oSOutcomeEventParams);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void sendClickActionOutcomes(List<OSInAppMessageOutcome> list) {
        for (OSInAppMessageOutcome next : list) {
            String name = next.getName();
            if (next.isUnique()) {
                sendUniqueOutcomeEvent(name, (OneSignal.OutcomeCallback) null);
            } else if (next.getWeight() > 0.0f) {
                sendOutcomeEventWithValue(name, next.getWeight(), (OneSignal.OutcomeCallback) null);
            } else {
                sendOutcomeEvent(name, (OneSignal.OutcomeCallback) null);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void sendUniqueOutcomeEvent(String str, OneSignal.OutcomeCallback outcomeCallback) {
        sendUniqueOutcomeEvent(str, this.osSessionManager.getInfluences(), outcomeCallback);
    }

    /* access modifiers changed from: package-private */
    public void sendOutcomeEvent(String str, OneSignal.OutcomeCallback outcomeCallback) {
        sendAndCreateOutcomeEvent(str, 0.0f, this.osSessionManager.getInfluences(), outcomeCallback);
    }

    /* access modifiers changed from: package-private */
    public void sendOutcomeEventWithValue(String str, float f, OneSignal.OutcomeCallback outcomeCallback) {
        sendAndCreateOutcomeEvent(str, f, this.osSessionManager.getInfluences(), outcomeCallback);
    }

    private void sendUniqueOutcomeEvent(String str, List<OSInfluence> list, OneSignal.OutcomeCallback outcomeCallback) {
        List<OSInfluence> removeDisabledInfluences = removeDisabledInfluences(list);
        if (removeDisabledInfluences.isEmpty()) {
            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "Unique Outcome disabled for current session");
            return;
        }
        boolean z = false;
        Iterator<OSInfluence> it = removeDisabledInfluences.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().getInfluenceType().isAttributed()) {
                    z = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (z) {
            List<OSInfluence> uniqueIds = getUniqueIds(str, removeDisabledInfluences);
            if (uniqueIds == null) {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
                OneSignal.Log(log_level, "Measure endpoint will not send because unique outcome already sent for: \nSessionInfluences: " + removeDisabledInfluences.toString() + "\nOutcome name: " + str);
                if (outcomeCallback != null) {
                    outcomeCallback.onSuccess((OutcomeEvent) null);
                    return;
                }
                return;
            }
            sendAndCreateOutcomeEvent(str, 0.0f, uniqueIds, outcomeCallback);
        } else if (this.unattributedUniqueOutcomeEventsSentOnSession.contains(str)) {
            OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.Log(log_level2, "Measure endpoint will not send because unique outcome already sent for: \nSession: " + OSInfluenceType.UNATTRIBUTED + "\nOutcome name: " + str);
            if (outcomeCallback != null) {
                outcomeCallback.onSuccess((OutcomeEvent) null);
            }
        } else {
            this.unattributedUniqueOutcomeEventsSentOnSession.add(str);
            sendAndCreateOutcomeEvent(str, 0.0f, removeDisabledInfluences, outcomeCallback);
        }
    }

    private void sendAndCreateOutcomeEvent(String str, float f, List<OSInfluence> list, OneSignal.OutcomeCallback outcomeCallback) {
        final long currentTimeMillis = System.currentTimeMillis() / 1000;
        int deviceType = new OSUtils().getDeviceType();
        String str2 = OneSignal.appId;
        OSOutcomeSourceBody oSOutcomeSourceBody = null;
        OSOutcomeSourceBody oSOutcomeSourceBody2 = null;
        boolean z = false;
        for (OSInfluence next : list) {
            int i = AnonymousClass5.$SwitchMap$com$onesignal$influence$model$OSInfluenceType[next.getInfluenceType().ordinal()];
            if (i == 1) {
                if (oSOutcomeSourceBody == null) {
                    oSOutcomeSourceBody = new OSOutcomeSourceBody();
                }
                oSOutcomeSourceBody = setSourceChannelIds(next, oSOutcomeSourceBody);
            } else if (i == 2) {
                if (oSOutcomeSourceBody2 == null) {
                    oSOutcomeSourceBody2 = new OSOutcomeSourceBody();
                }
                oSOutcomeSourceBody2 = setSourceChannelIds(next, oSOutcomeSourceBody2);
            } else if (i == 3) {
                z = true;
            } else if (i == 4) {
                OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "Outcomes disabled for channel: " + next.getInfluenceChannel());
                return;
            }
        }
        if (oSOutcomeSourceBody == null && oSOutcomeSourceBody2 == null && !z) {
            OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "Outcomes disabled for all channels");
            return;
        }
        OSOutcomeEventParams oSOutcomeEventParams = new OSOutcomeEventParams(str, new OSOutcomeSource(oSOutcomeSourceBody, oSOutcomeSourceBody2), f);
        final OSOutcomeEventParams oSOutcomeEventParams2 = oSOutcomeEventParams;
        final OneSignal.OutcomeCallback outcomeCallback2 = outcomeCallback;
        final String str3 = str;
        this.outcomeEventsFactory.getRepository().requestMeasureOutcomeEvent(str2, deviceType, oSOutcomeEventParams, new OneSignalApiResponseHandler() {
            public void onSuccess(String str) {
                OSOutcomeEventsController.this.saveUniqueOutcome(oSOutcomeEventParams2);
                OneSignal.OutcomeCallback outcomeCallback = outcomeCallback2;
                if (outcomeCallback != null) {
                    outcomeCallback.onSuccess(OutcomeEvent.fromOutcomeEventParamsV2toOutcomeEventV1(oSOutcomeEventParams2));
                }
            }

            public void onFailure(int i, String str, Throwable th) {
                new Thread(new Runnable() {
                    public void run() {
                        Thread.currentThread().setPriority(10);
                        oSOutcomeEventParams2.setTimestamp(currentTimeMillis);
                        OSOutcomeEventsController.this.outcomeEventsFactory.getRepository().saveOutcomeEvent(oSOutcomeEventParams2);
                    }
                }, OSOutcomeEventsController.OS_SAVE_OUTCOMES).start();
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.WARN;
                OneSignal.onesignalLog(log_level, "Sending outcome with name: " + str3 + " failed with status code: " + i + " and response: " + str + "\nOutcome event was cached and will be reattempted on app cold start");
                OneSignal.OutcomeCallback outcomeCallback = outcomeCallback2;
                if (outcomeCallback != null) {
                    outcomeCallback.onSuccess((OutcomeEvent) null);
                }
            }
        });
    }

    /* renamed from: com.onesignal.OSOutcomeEventsController$5  reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$influence$model$OSInfluenceChannel = new int[OSInfluenceChannel.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$influence$model$OSInfluenceType = new int[OSInfluenceType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|(2:1|2)|3|5|6|7|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0032 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0047 */
        static {
            /*
                com.onesignal.influence.model.OSInfluenceChannel[] r0 = com.onesignal.influence.model.OSInfluenceChannel.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$onesignal$influence$model$OSInfluenceChannel = r0
                r0 = 1
                int[] r1 = $SwitchMap$com$onesignal$influence$model$OSInfluenceChannel     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.onesignal.influence.model.OSInfluenceChannel r2 = com.onesignal.influence.model.OSInfluenceChannel.IAM     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = $SwitchMap$com$onesignal$influence$model$OSInfluenceChannel     // Catch:{ NoSuchFieldError -> 0x001f }
                com.onesignal.influence.model.OSInfluenceChannel r3 = com.onesignal.influence.model.OSInfluenceChannel.NOTIFICATION     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                com.onesignal.influence.model.OSInfluenceType[] r2 = com.onesignal.influence.model.OSInfluenceType.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$onesignal$influence$model$OSInfluenceType = r2
                int[] r2 = $SwitchMap$com$onesignal$influence$model$OSInfluenceType     // Catch:{ NoSuchFieldError -> 0x0032 }
                com.onesignal.influence.model.OSInfluenceType r3 = com.onesignal.influence.model.OSInfluenceType.DIRECT     // Catch:{ NoSuchFieldError -> 0x0032 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0032 }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0032 }
            L_0x0032:
                int[] r0 = $SwitchMap$com$onesignal$influence$model$OSInfluenceType     // Catch:{ NoSuchFieldError -> 0x003c }
                com.onesignal.influence.model.OSInfluenceType r2 = com.onesignal.influence.model.OSInfluenceType.INDIRECT     // Catch:{ NoSuchFieldError -> 0x003c }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x003c }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x003c }
            L_0x003c:
                int[] r0 = $SwitchMap$com$onesignal$influence$model$OSInfluenceType     // Catch:{ NoSuchFieldError -> 0x0047 }
                com.onesignal.influence.model.OSInfluenceType r1 = com.onesignal.influence.model.OSInfluenceType.UNATTRIBUTED     // Catch:{ NoSuchFieldError -> 0x0047 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0047 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0047 }
            L_0x0047:
                int[] r0 = $SwitchMap$com$onesignal$influence$model$OSInfluenceType     // Catch:{ NoSuchFieldError -> 0x0052 }
                com.onesignal.influence.model.OSInfluenceType r1 = com.onesignal.influence.model.OSInfluenceType.DISABLED     // Catch:{ NoSuchFieldError -> 0x0052 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0052 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0052 }
            L_0x0052:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OSOutcomeEventsController.AnonymousClass5.<clinit>():void");
        }
    }

    private OSOutcomeSourceBody setSourceChannelIds(OSInfluence oSInfluence, OSOutcomeSourceBody oSOutcomeSourceBody) {
        int i = AnonymousClass5.$SwitchMap$com$onesignal$influence$model$OSInfluenceChannel[oSInfluence.getInfluenceChannel().ordinal()];
        if (i == 1) {
            oSOutcomeSourceBody.setInAppMessagesIds(oSInfluence.getIds());
        } else if (i == 2) {
            oSOutcomeSourceBody.setNotificationIds(oSInfluence.getIds());
        }
        return oSOutcomeSourceBody;
    }

    private List<OSInfluence> removeDisabledInfluences(List<OSInfluence> list) {
        ArrayList arrayList = new ArrayList(list);
        for (OSInfluence next : list) {
            if (next.getInfluenceType().isDisabled()) {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
                OneSignal.onesignalLog(log_level, "Outcomes disabled for channel: " + next.getInfluenceChannel().toString());
                arrayList.remove(next);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void saveUniqueOutcome(OSOutcomeEventParams oSOutcomeEventParams) {
        if (oSOutcomeEventParams.isUnattributed()) {
            saveUnattributedUniqueOutcomeEvents();
        } else {
            saveAttributedUniqueOutcomeNotifications(oSOutcomeEventParams);
        }
    }

    private void saveAttributedUniqueOutcomeNotifications(final OSOutcomeEventParams oSOutcomeEventParams) {
        new Thread(new Runnable() {
            public void run() {
                Thread.currentThread().setPriority(10);
                OSOutcomeEventsController.this.outcomeEventsFactory.getRepository().saveUniqueOutcomeNotifications(oSOutcomeEventParams);
            }
        }, OS_SAVE_UNIQUE_OUTCOME_NOTIFICATIONS).start();
    }

    private void saveUnattributedUniqueOutcomeEvents() {
        this.outcomeEventsFactory.getRepository().saveUnattributedUniqueOutcomeEventsSent(this.unattributedUniqueOutcomeEventsSentOnSession);
    }

    private List<OSInfluence> getUniqueIds(String str, List<OSInfluence> list) {
        List<OSInfluence> notCachedUniqueOutcome = this.outcomeEventsFactory.getRepository().getNotCachedUniqueOutcome(str, list);
        if (notCachedUniqueOutcome.size() > 0) {
            return notCachedUniqueOutcome;
        }
        return null;
    }
}
