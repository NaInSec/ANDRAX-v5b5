package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalRestClient;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class TrackGooglePurchase {
    /* access modifiers changed from: private */
    public static Class<?> IInAppBillingServiceClass = null;
    /* access modifiers changed from: private */
    public static int iapEnabled = -99;
    /* access modifiers changed from: private */
    public Context appContext;
    /* access modifiers changed from: private */
    public Method getPurchasesMethod;
    private Method getSkuDetailsMethod;
    /* access modifiers changed from: private */
    public boolean isWaitingForPurchasesRequest;
    /* access modifiers changed from: private */
    public Object mIInAppBillingService;
    private ServiceConnection mServiceConn;
    /* access modifiers changed from: private */
    public boolean newAsExisting = true;
    /* access modifiers changed from: private */
    public ArrayList<String> purchaseTokens;

    TrackGooglePurchase(Context context) {
        boolean z = false;
        this.isWaitingForPurchasesRequest = false;
        this.appContext = context;
        this.purchaseTokens = new ArrayList<>();
        try {
            JSONArray jSONArray = new JSONArray(OneSignalPrefs.getString(OneSignalPrefs.PREFS_PLAYER_PURCHASES, "purchaseTokens", "[]"));
            for (int i = 0; i < jSONArray.length(); i++) {
                this.purchaseTokens.add(jSONArray.get(i).toString());
            }
            this.newAsExisting = jSONArray.length() == 0 ? true : z;
            if (this.newAsExisting) {
                this.newAsExisting = OneSignalPrefs.getBool(OneSignalPrefs.PREFS_PLAYER_PURCHASES, "ExistingPurchases", true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        trackIAP();
    }

    static boolean CanTrack(Context context) {
        if (iapEnabled == -99) {
            iapEnabled = context.checkCallingOrSelfPermission("com.android.vending.BILLING");
        }
        try {
            if (iapEnabled == 0) {
                IInAppBillingServiceClass = Class.forName("com.android.vending.billing.IInAppBillingService");
            }
            if (iapEnabled == 0) {
                return true;
            }
            return false;
        } catch (Throwable unused) {
            iapEnabled = 0;
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void trackIAP() {
        if (this.mServiceConn == null) {
            this.mServiceConn = new ServiceConnection() {
                public void onServiceDisconnected(ComponentName componentName) {
                    int unused = TrackGooglePurchase.iapEnabled = -99;
                    Object unused2 = TrackGooglePurchase.this.mIInAppBillingService = null;
                }

                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    try {
                        Method access$200 = TrackGooglePurchase.getAsInterfaceMethod(Class.forName("com.android.vending.billing.IInAppBillingService$Stub"));
                        access$200.setAccessible(true);
                        Object unused = TrackGooglePurchase.this.mIInAppBillingService = access$200.invoke((Object) null, new Object[]{iBinder});
                        TrackGooglePurchase.this.QueryBoughtItems();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            };
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage("com.android.vending");
            this.appContext.bindService(intent, this.mServiceConn, 1);
        } else if (this.mIInAppBillingService != null) {
            QueryBoughtItems();
        }
    }

    /* access modifiers changed from: private */
    public void QueryBoughtItems() {
        if (!this.isWaitingForPurchasesRequest) {
            new Thread(new Runnable() {
                public void run() {
                    boolean unused = TrackGooglePurchase.this.isWaitingForPurchasesRequest = true;
                    try {
                        if (TrackGooglePurchase.this.getPurchasesMethod == null) {
                            Method unused2 = TrackGooglePurchase.this.getPurchasesMethod = TrackGooglePurchase.getGetPurchasesMethod(TrackGooglePurchase.IInAppBillingServiceClass);
                            TrackGooglePurchase.this.getPurchasesMethod.setAccessible(true);
                        }
                        Bundle bundle = (Bundle) TrackGooglePurchase.this.getPurchasesMethod.invoke(TrackGooglePurchase.this.mIInAppBillingService, new Object[]{3, TrackGooglePurchase.this.appContext.getPackageName(), "inapp", null});
                        if (bundle.getInt("RESPONSE_CODE") == 0) {
                            ArrayList arrayList = new ArrayList();
                            ArrayList arrayList2 = new ArrayList();
                            ArrayList<String> stringArrayList = bundle.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                            ArrayList<String> stringArrayList2 = bundle.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                            for (int i = 0; i < stringArrayList2.size(); i++) {
                                String str = stringArrayList.get(i);
                                String string = new JSONObject(stringArrayList2.get(i)).getString("purchaseToken");
                                if (!TrackGooglePurchase.this.purchaseTokens.contains(string) && !arrayList2.contains(string)) {
                                    arrayList2.add(string);
                                    arrayList.add(str);
                                }
                            }
                            if (arrayList.size() > 0) {
                                TrackGooglePurchase.this.sendPurchases(arrayList, arrayList2);
                            } else if (stringArrayList2.size() == 0) {
                                boolean unused3 = TrackGooglePurchase.this.newAsExisting = false;
                                OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_PLAYER_PURCHASES, "ExistingPurchases", false);
                            }
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    boolean unused4 = TrackGooglePurchase.this.isWaitingForPurchasesRequest = false;
                }
            }).start();
        }
    }

    /* access modifiers changed from: private */
    public void sendPurchases(ArrayList<String> arrayList, final ArrayList<String> arrayList2) {
        try {
            if (this.getSkuDetailsMethod == null) {
                this.getSkuDetailsMethod = getGetSkuDetailsMethod(IInAppBillingServiceClass);
                this.getSkuDetailsMethod.setAccessible(true);
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("ITEM_ID_LIST", arrayList);
            Bundle bundle2 = (Bundle) this.getSkuDetailsMethod.invoke(this.mIInAppBillingService, new Object[]{3, this.appContext.getPackageName(), "inapp", bundle});
            if (bundle2.getInt("RESPONSE_CODE") == 0) {
                ArrayList<String> stringArrayList = bundle2.getStringArrayList("DETAILS_LIST");
                HashMap hashMap = new HashMap();
                Iterator<String> it = stringArrayList.iterator();
                while (it.hasNext()) {
                    JSONObject jSONObject = new JSONObject(it.next());
                    String string = jSONObject.getString("productId");
                    BigDecimal divide = new BigDecimal(jSONObject.getString("price_amount_micros")).divide(new BigDecimal(1000000));
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("sku", string);
                    jSONObject2.put("iso", jSONObject.getString("price_currency_code"));
                    jSONObject2.put("amount", divide.toString());
                    hashMap.put(string, jSONObject2);
                }
                JSONArray jSONArray = new JSONArray();
                Iterator<String> it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    String next = it2.next();
                    if (hashMap.containsKey(next)) {
                        jSONArray.put(hashMap.get(next));
                    }
                }
                if (jSONArray.length() > 0) {
                    OneSignal.sendPurchases(jSONArray, this.newAsExisting, new OneSignalRestClient.ResponseHandler() {
                        public void onFailure(int i, JSONObject jSONObject, Throwable th) {
                            OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "HTTP sendPurchases failed to send.", th);
                            boolean unused = TrackGooglePurchase.this.isWaitingForPurchasesRequest = false;
                        }

                        public void onSuccess(String str) {
                            TrackGooglePurchase.this.purchaseTokens.addAll(arrayList2);
                            OneSignalPrefs.saveString(OneSignalPrefs.PREFS_PLAYER_PURCHASES, "purchaseTokens", TrackGooglePurchase.this.purchaseTokens.toString());
                            OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_PLAYER_PURCHASES, "ExistingPurchases", true);
                            boolean unused = TrackGooglePurchase.this.newAsExisting = false;
                            boolean unused2 = TrackGooglePurchase.this.isWaitingForPurchasesRequest = false;
                        }
                    });
                }
            }
        } catch (Throwable th) {
            OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Failed to track IAP purchases", th);
        }
    }

    /* access modifiers changed from: private */
    public static Method getAsInterfaceMethod(Class cls) {
        for (Method method : cls.getMethods()) {
            Class<IBinder>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0] == IBinder.class) {
                return method;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static Method getGetPurchasesMethod(Class cls) {
        for (Method method : cls.getMethods()) {
            Class<String>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 4 && parameterTypes[0] == Integer.TYPE && parameterTypes[1] == String.class && parameterTypes[2] == String.class && parameterTypes[3] == String.class) {
                return method;
            }
        }
        return null;
    }

    private static Method getGetSkuDetailsMethod(Class cls) {
        for (Method method : cls.getMethods()) {
            Class<Bundle>[] parameterTypes = method.getParameterTypes();
            Class<?> returnType = method.getReturnType();
            if (parameterTypes.length == 4 && parameterTypes[0] == Integer.TYPE && parameterTypes[1] == String.class && parameterTypes[2] == String.class && parameterTypes[3] == Bundle.class && returnType == Bundle.class) {
                return method;
            }
        }
        return null;
    }
}
