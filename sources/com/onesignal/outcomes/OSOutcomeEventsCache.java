package com.onesignal.outcomes;

import android.content.ContentValues;
import android.database.Cursor;
import com.onesignal.OSLogger;
import com.onesignal.OSSharedPreferences;
import com.onesignal.OneSignalDb;
import com.onesignal.influence.model.OSInfluenceChannel;
import com.onesignal.influence.model.OSInfluenceType;
import com.onesignal.outcomes.model.OSCachedUniqueOutcome;
import com.onesignal.outcomes.model.OSOutcomeEventParams;
import com.onesignal.outcomes.model.OSOutcomeSource;
import com.onesignal.outcomes.model.OSOutcomeSourceBody;
import com.thecrackertechnology.dragonterminal.component.colorscheme.NeoColorScheme;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

class OSOutcomeEventsCache {
    private static final String PREFS_OS_UNATTRIBUTED_UNIQUE_OUTCOME_EVENTS_SENT = "PREFS_OS_UNATTRIBUTED_UNIQUE_OUTCOME_EVENTS_SENT";
    private OneSignalDb dbHelper;
    private OSLogger logger;
    private OSSharedPreferences preferences;

    OSOutcomeEventsCache(OSLogger oSLogger, OneSignalDb oneSignalDb, OSSharedPreferences oSSharedPreferences) {
        this.logger = oSLogger;
        this.dbHelper = oneSignalDb;
        this.preferences = oSSharedPreferences;
    }

    /* access modifiers changed from: package-private */
    public boolean isOutcomesV2ServiceEnabled() {
        OSSharedPreferences oSSharedPreferences = this.preferences;
        return oSSharedPreferences.getBool(oSSharedPreferences.getPreferencesName(), this.preferences.getOutcomesV2KeyName(), false);
    }

    /* access modifiers changed from: package-private */
    public Set<String> getUnattributedUniqueOutcomeEventsSentByChannel() {
        OSSharedPreferences oSSharedPreferences = this.preferences;
        return oSSharedPreferences.getStringSet(oSSharedPreferences.getPreferencesName(), PREFS_OS_UNATTRIBUTED_UNIQUE_OUTCOME_EVENTS_SENT, (Set<String>) null);
    }

    /* access modifiers changed from: package-private */
    public void saveUnattributedUniqueOutcomeEventsSentByChannel(Set<String> set) {
        OSSharedPreferences oSSharedPreferences = this.preferences;
        oSSharedPreferences.saveStringSet(oSSharedPreferences.getPreferencesName(), PREFS_OS_UNATTRIBUTED_UNIQUE_OUTCOME_EVENTS_SENT, set);
    }

    /* access modifiers changed from: package-private */
    public synchronized void deleteOldOutcomeEvent(OSOutcomeEventParams oSOutcomeEventParams) {
        this.dbHelper.delete(OSOutcomeTableProvider.OUTCOME_EVENT_TABLE, "timestamp = ?", new String[]{String.valueOf(oSOutcomeEventParams.getTimestamp())});
    }

    /* access modifiers changed from: package-private */
    public synchronized void saveOutcomeEvent(OSOutcomeEventParams oSOutcomeEventParams) {
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        OSInfluenceType oSInfluenceType = OSInfluenceType.UNATTRIBUTED;
        OSInfluenceType oSInfluenceType2 = OSInfluenceType.UNATTRIBUTED;
        if (oSOutcomeEventParams.getOutcomeSource() != null) {
            OSOutcomeSource outcomeSource = oSOutcomeEventParams.getOutcomeSource();
            if (outcomeSource.getDirectBody() != null) {
                OSOutcomeSourceBody directBody = outcomeSource.getDirectBody();
                if (directBody.getNotificationIds() != null && directBody.getNotificationIds().length() > 0) {
                    oSInfluenceType = OSInfluenceType.DIRECT;
                    jSONArray = outcomeSource.getDirectBody().getNotificationIds();
                }
                if (directBody.getInAppMessagesIds() != null && directBody.getInAppMessagesIds().length() > 0) {
                    oSInfluenceType2 = OSInfluenceType.DIRECT;
                    jSONArray2 = outcomeSource.getDirectBody().getInAppMessagesIds();
                }
            }
            if (outcomeSource.getIndirectBody() != null) {
                OSOutcomeSourceBody indirectBody = outcomeSource.getIndirectBody();
                if (indirectBody.getNotificationIds() != null && indirectBody.getNotificationIds().length() > 0) {
                    OSInfluenceType oSInfluenceType3 = OSInfluenceType.INDIRECT;
                    oSInfluenceType = oSInfluenceType3;
                    jSONArray = outcomeSource.getIndirectBody().getNotificationIds();
                }
                if (indirectBody.getInAppMessagesIds() != null && indirectBody.getInAppMessagesIds().length() > 0) {
                    oSInfluenceType2 = OSInfluenceType.INDIRECT;
                    jSONArray2 = outcomeSource.getIndirectBody().getInAppMessagesIds();
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("notification_ids", jSONArray.toString());
        contentValues.put("iam_ids", jSONArray2.toString());
        contentValues.put("notification_influence_type", oSInfluenceType.toString().toLowerCase());
        contentValues.put("iam_influence_type", oSInfluenceType2.toString().toLowerCase());
        contentValues.put(NeoColorScheme.COLOR_META_NAME, oSOutcomeEventParams.getOutcomeId());
        contentValues.put("weight", oSOutcomeEventParams.getWeight());
        contentValues.put("timestamp", Long.valueOf(oSOutcomeEventParams.getTimestamp()));
        this.dbHelper.insert(OSOutcomeTableProvider.OUTCOME_EVENT_TABLE, (String) null, contentValues);
    }

    /* access modifiers changed from: package-private */
    public synchronized List<OSOutcomeEventParams> getAllEventsToSend() {
        ArrayList arrayList;
        OSOutcomeSource oSOutcomeSource;
        synchronized (this) {
            arrayList = new ArrayList();
            Cursor query = this.dbHelper.query(OSOutcomeTableProvider.OUTCOME_EVENT_TABLE, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
            if (query.moveToFirst()) {
                do {
                    OSInfluenceType fromString = OSInfluenceType.fromString(query.getString(query.getColumnIndex("notification_influence_type")));
                    OSInfluenceType fromString2 = OSInfluenceType.fromString(query.getString(query.getColumnIndex("iam_influence_type")));
                    String string = query.getString(query.getColumnIndex("notification_ids"));
                    if (string == null) {
                        string = "[]";
                    }
                    String string2 = query.getString(query.getColumnIndex("iam_ids"));
                    if (string2 == null) {
                        string2 = "[]";
                    }
                    String string3 = query.getString(query.getColumnIndex(NeoColorScheme.COLOR_META_NAME));
                    float f = query.getFloat(query.getColumnIndex("weight"));
                    long j = query.getLong(query.getColumnIndex("timestamp"));
                    try {
                        OSOutcomeSourceBody oSOutcomeSourceBody = new OSOutcomeSourceBody();
                        OSOutcomeSourceBody oSOutcomeSourceBody2 = new OSOutcomeSourceBody();
                        int i = AnonymousClass1.$SwitchMap$com$onesignal$influence$model$OSInfluenceType[fromString.ordinal()];
                        if (i == 1) {
                            oSOutcomeSourceBody.setNotificationIds(new JSONArray(string));
                            oSOutcomeSource = new OSOutcomeSource(oSOutcomeSourceBody, (OSOutcomeSourceBody) null);
                        } else if (i != 2) {
                            oSOutcomeSource = null;
                        } else {
                            oSOutcomeSourceBody2.setNotificationIds(new JSONArray(string));
                            oSOutcomeSource = new OSOutcomeSource((OSOutcomeSourceBody) null, oSOutcomeSourceBody2);
                        }
                        int i2 = AnonymousClass1.$SwitchMap$com$onesignal$influence$model$OSInfluenceType[fromString2.ordinal()];
                        if (i2 == 1) {
                            oSOutcomeSourceBody.setInAppMessagesIds(new JSONArray(string2));
                            oSOutcomeSource = oSOutcomeSource == null ? new OSOutcomeSource(oSOutcomeSourceBody, (OSOutcomeSourceBody) null) : oSOutcomeSource.setDirectBody(oSOutcomeSourceBody);
                        } else if (i2 == 2) {
                            oSOutcomeSourceBody2.setInAppMessagesIds(new JSONArray(string2));
                            oSOutcomeSource = oSOutcomeSource == null ? new OSOutcomeSource((OSOutcomeSourceBody) null, oSOutcomeSourceBody2) : oSOutcomeSource.setIndirectBody(oSOutcomeSourceBody2);
                        }
                        arrayList.add(new OSOutcomeEventParams(string3, oSOutcomeSource, f, j));
                    } catch (JSONException e) {
                        this.logger.error("Generating JSONArray from notifications ids outcome:JSON Failed.", e);
                    }
                } while (query.moveToNext());
            }
            query.close();
        }
        return arrayList;
    }

    /* renamed from: com.onesignal.outcomes.OSOutcomeEventsCache$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.outcomes.OSOutcomeEventsCache.AnonymousClass1.<clinit>():void");
        }
    }

    private void addIdToListFromChannel(List<OSCachedUniqueOutcome> list, JSONArray jSONArray, OSInfluenceChannel oSInfluenceChannel) {
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    list.add(new OSCachedUniqueOutcome(jSONArray.getString(i), oSInfluenceChannel));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addIdsToListFromSource(List<OSCachedUniqueOutcome> list, OSOutcomeSourceBody oSOutcomeSourceBody) {
        if (oSOutcomeSourceBody != null) {
            JSONArray inAppMessagesIds = oSOutcomeSourceBody.getInAppMessagesIds();
            JSONArray notificationIds = oSOutcomeSourceBody.getNotificationIds();
            addIdToListFromChannel(list, inAppMessagesIds, OSInfluenceChannel.IAM);
            addIdToListFromChannel(list, notificationIds, OSInfluenceChannel.NOTIFICATION);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void saveUniqueOutcomeEventParams(OSOutcomeEventParams oSOutcomeEventParams) {
        OSLogger oSLogger = this.logger;
        oSLogger.debug("OneSignal saveUniqueOutcomeEventParams: " + oSOutcomeEventParams.toString());
        if (oSOutcomeEventParams.getOutcomeSource() != null) {
            String outcomeId = oSOutcomeEventParams.getOutcomeId();
            ArrayList<OSCachedUniqueOutcome> arrayList = new ArrayList<>();
            OSOutcomeSourceBody directBody = oSOutcomeEventParams.getOutcomeSource().getDirectBody();
            OSOutcomeSourceBody indirectBody = oSOutcomeEventParams.getOutcomeSource().getIndirectBody();
            addIdsToListFromSource(arrayList, directBody);
            addIdsToListFromSource(arrayList, indirectBody);
            for (OSCachedUniqueOutcome oSCachedUniqueOutcome : arrayList) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(OSOutcomeTableProvider.CACHE_UNIQUE_OUTCOME_COLUMN_CHANNEL_INFLUENCE_ID, oSCachedUniqueOutcome.getInfluenceId());
                contentValues.put(OSOutcomeTableProvider.CACHE_UNIQUE_OUTCOME_COLUMN_CHANNEL_TYPE, String.valueOf(oSCachedUniqueOutcome.getChannel()));
                contentValues.put(NeoColorScheme.COLOR_META_NAME, outcomeId);
                this.dbHelper.insert(OSOutcomeTableProvider.CACHE_UNIQUE_OUTCOME_TABLE, (String) null, contentValues);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0082, code lost:
        if (r3.isClosed() == false) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0084, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0095, code lost:
        if (r3.isClosed() == false) goto L_0x0084;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0091 A[SYNTHETIC, Splitter:B:37:0x0091] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x009c A[SYNTHETIC, Splitter:B:43:0x009c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.List<com.onesignal.influence.model.OSInfluence> getNotCachedUniqueInfluencesForOutcome(java.lang.String r21, java.util.List<com.onesignal.influence.model.OSInfluence> r22) {
        /*
            r20 = this;
            r1 = r20
            monitor-enter(r20)
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x00a6 }
            r2.<init>()     // Catch:{ all -> 0x00a6 }
            r3 = 0
            java.util.Iterator r0 = r22.iterator()     // Catch:{ JSONException -> 0x008b }
        L_0x000d:
            boolean r4 = r0.hasNext()     // Catch:{ JSONException -> 0x008b }
            if (r4 == 0) goto L_0x007c
            java.lang.Object r4 = r0.next()     // Catch:{ JSONException -> 0x008b }
            com.onesignal.influence.model.OSInfluence r4 = (com.onesignal.influence.model.OSInfluence) r4     // Catch:{ JSONException -> 0x008b }
            org.json.JSONArray r5 = new org.json.JSONArray     // Catch:{ JSONException -> 0x008b }
            r5.<init>()     // Catch:{ JSONException -> 0x008b }
            org.json.JSONArray r6 = r4.getIds()     // Catch:{ JSONException -> 0x008b }
            if (r6 != 0) goto L_0x0025
            goto L_0x000d
        L_0x0025:
            r7 = 0
            r8 = r3
            r3 = 0
        L_0x0028:
            int r9 = r6.length()     // Catch:{ JSONException -> 0x0079, all -> 0x0077 }
            if (r3 >= r9) goto L_0x0065
            java.lang.String r9 = r6.getString(r3)     // Catch:{ JSONException -> 0x0079, all -> 0x0077 }
            com.onesignal.influence.model.OSInfluenceChannel r10 = r4.getInfluenceChannel()     // Catch:{ JSONException -> 0x0079, all -> 0x0077 }
            java.lang.String[] r13 = new java.lang.String[r7]     // Catch:{ JSONException -> 0x0079, all -> 0x0077 }
            java.lang.String r14 = "channel_influence_id = ? AND channel_type = ? AND name = ?"
            r11 = 3
            java.lang.String[] r15 = new java.lang.String[r11]     // Catch:{ JSONException -> 0x0079, all -> 0x0077 }
            r15[r7] = r9     // Catch:{ JSONException -> 0x0079, all -> 0x0077 }
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ JSONException -> 0x0079, all -> 0x0077 }
            r11 = 1
            r15[r11] = r10     // Catch:{ JSONException -> 0x0079, all -> 0x0077 }
            r10 = 2
            r15[r10] = r21     // Catch:{ JSONException -> 0x0079, all -> 0x0077 }
            com.onesignal.OneSignalDb r11 = r1.dbHelper     // Catch:{ JSONException -> 0x0079, all -> 0x0077 }
            java.lang.String r12 = "cached_unique_outcome"
            r16 = 0
            r17 = 0
            r18 = 0
            java.lang.String r19 = "1"
            android.database.Cursor r8 = r11.query(r12, r13, r14, r15, r16, r17, r18, r19)     // Catch:{ JSONException -> 0x0079, all -> 0x0077 }
            int r10 = r8.getCount()     // Catch:{ JSONException -> 0x0079, all -> 0x0077 }
            if (r10 != 0) goto L_0x0062
            r5.put(r9)     // Catch:{ JSONException -> 0x0079, all -> 0x0077 }
        L_0x0062:
            int r3 = r3 + 1
            goto L_0x0028
        L_0x0065:
            int r3 = r5.length()     // Catch:{ JSONException -> 0x0079, all -> 0x0077 }
            if (r3 <= 0) goto L_0x0075
            com.onesignal.influence.model.OSInfluence r3 = r4.copy()     // Catch:{ JSONException -> 0x0079, all -> 0x0077 }
            r3.setIds(r5)     // Catch:{ JSONException -> 0x0079, all -> 0x0077 }
            r2.add(r3)     // Catch:{ JSONException -> 0x0079, all -> 0x0077 }
        L_0x0075:
            r3 = r8
            goto L_0x000d
        L_0x0077:
            r0 = move-exception
            goto L_0x009a
        L_0x0079:
            r0 = move-exception
            r3 = r8
            goto L_0x008c
        L_0x007c:
            if (r3 == 0) goto L_0x0098
            boolean r0 = r3.isClosed()     // Catch:{ all -> 0x00a6 }
            if (r0 != 0) goto L_0x0098
        L_0x0084:
            r3.close()     // Catch:{ all -> 0x00a6 }
            goto L_0x0098
        L_0x0088:
            r0 = move-exception
            r8 = r3
            goto L_0x009a
        L_0x008b:
            r0 = move-exception
        L_0x008c:
            r0.printStackTrace()     // Catch:{ all -> 0x0088 }
            if (r3 == 0) goto L_0x0098
            boolean r0 = r3.isClosed()     // Catch:{ all -> 0x00a6 }
            if (r0 != 0) goto L_0x0098
            goto L_0x0084
        L_0x0098:
            monitor-exit(r20)
            return r2
        L_0x009a:
            if (r8 == 0) goto L_0x00a5
            boolean r2 = r8.isClosed()     // Catch:{ all -> 0x00a6 }
            if (r2 != 0) goto L_0x00a5
            r8.close()     // Catch:{ all -> 0x00a6 }
        L_0x00a5:
            throw r0     // Catch:{ all -> 0x00a6 }
        L_0x00a6:
            r0 = move-exception
            monitor-exit(r20)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.outcomes.OSOutcomeEventsCache.getNotCachedUniqueInfluencesForOutcome(java.lang.String, java.util.List):java.util.List");
    }
}
