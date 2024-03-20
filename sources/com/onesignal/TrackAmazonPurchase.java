package com.onesignal;

import android.content.Context;
import com.amazon.device.iap.PurchasingListener;
import com.amazon.device.iap.PurchasingService;
import com.amazon.device.iap.model.Product;
import com.amazon.device.iap.model.ProductDataResponse;
import com.amazon.device.iap.model.PurchaseResponse;
import com.amazon.device.iap.model.PurchaseUpdatesResponse;
import com.amazon.device.iap.model.RequestId;
import com.amazon.device.iap.model.UserDataResponse;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalRestClient;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class TrackAmazonPurchase {
    private boolean canTrack = false;
    private Context context;
    private Field listenerHandlerField;
    private Object listenerHandlerObject;
    private OSPurchasingListener osPurchasingListener;

    TrackAmazonPurchase(Context context2) {
        this.context = context2;
        try {
            Class<?> cls = Class.forName("com.amazon.device.iap.internal.d");
            this.listenerHandlerObject = cls.getMethod("d", new Class[0]).invoke((Object) null, new Object[0]);
            this.listenerHandlerField = cls.getDeclaredField("f");
            this.listenerHandlerField.setAccessible(true);
            this.osPurchasingListener = new OSPurchasingListener(this, (AnonymousClass1) null);
            this.osPurchasingListener.orgPurchasingListener = (PurchasingListener) this.listenerHandlerField.get(this.listenerHandlerObject);
            this.canTrack = true;
            setListener();
        } catch (ClassNotFoundException e) {
            logAmazonIAPListenerError(e);
        } catch (IllegalAccessException e2) {
            logAmazonIAPListenerError(e2);
        } catch (InvocationTargetException e3) {
            logAmazonIAPListenerError(e3);
        } catch (NoSuchMethodException e4) {
            logAmazonIAPListenerError(e4);
        } catch (NoSuchFieldException e5) {
            logAmazonIAPListenerError(e5);
        }
    }

    private static void logAmazonIAPListenerError(Exception exc) {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error adding Amazon IAP listener.", exc);
        exc.printStackTrace();
    }

    private void setListener() {
        PurchasingService.registerListener(this.context, this.osPurchasingListener);
    }

    /* access modifiers changed from: package-private */
    public void checkListener() {
        if (this.canTrack) {
            try {
                OSPurchasingListener oSPurchasingListener = (PurchasingListener) this.listenerHandlerField.get(this.listenerHandlerObject);
                if (oSPurchasingListener != this.osPurchasingListener) {
                    this.osPurchasingListener.orgPurchasingListener = oSPurchasingListener;
                    setListener();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private class OSPurchasingListener implements PurchasingListener {
        private String currentMarket;
        private RequestId lastRequestId;
        PurchasingListener orgPurchasingListener;

        private OSPurchasingListener() {
        }

        /* synthetic */ OSPurchasingListener(TrackAmazonPurchase trackAmazonPurchase, AnonymousClass1 r2) {
            this();
        }

        /* JADX WARNING: Removed duplicated region for block: B:52:0x0098 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:53:0x009b A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:54:0x009e A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:55:0x00a1 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:56:0x00a4 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:57:0x00a7 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x00aa A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:59:0x00ad A[RETURN] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.String marketToCurrencyCode(java.lang.String r3) {
            /*
                r2 = this;
                int r0 = r3.hashCode()
                r1 = 2100(0x834, float:2.943E-42)
                if (r0 == r1) goto L_0x0089
                r1 = 2128(0x850, float:2.982E-42)
                if (r0 == r1) goto L_0x007e
                r1 = 2142(0x85e, float:3.002E-42)
                if (r0 == r1) goto L_0x0074
                r1 = 2177(0x881, float:3.05E-42)
                if (r0 == r1) goto L_0x006a
                r1 = 2222(0x8ae, float:3.114E-42)
                if (r0 == r1) goto L_0x0060
                r1 = 2252(0x8cc, float:3.156E-42)
                if (r0 == r1) goto L_0x0056
                r1 = 2267(0x8db, float:3.177E-42)
                if (r0 == r1) goto L_0x004c
                r1 = 2347(0x92b, float:3.289E-42)
                if (r0 == r1) goto L_0x0042
                r1 = 2374(0x946, float:3.327E-42)
                if (r0 == r1) goto L_0x0038
                r1 = 2718(0xa9e, float:3.809E-42)
                if (r0 == r1) goto L_0x002e
                goto L_0x0094
            L_0x002e:
                java.lang.String r0 = "US"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0094
                r3 = 0
                goto L_0x0095
            L_0x0038:
                java.lang.String r0 = "JP"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0094
                r3 = 6
                goto L_0x0095
            L_0x0042:
                java.lang.String r0 = "IT"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0094
                r3 = 5
                goto L_0x0095
            L_0x004c:
                java.lang.String r0 = "GB"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0094
                r3 = 1
                goto L_0x0095
            L_0x0056:
                java.lang.String r0 = "FR"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0094
                r3 = 3
                goto L_0x0095
            L_0x0060:
                java.lang.String r0 = "ES"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0094
                r3 = 4
                goto L_0x0095
            L_0x006a:
                java.lang.String r0 = "DE"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0094
                r3 = 2
                goto L_0x0095
            L_0x0074:
                java.lang.String r0 = "CA"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0094
                r3 = 7
                goto L_0x0095
            L_0x007e:
                java.lang.String r0 = "BR"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0094
                r3 = 8
                goto L_0x0095
            L_0x0089:
                java.lang.String r0 = "AU"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0094
                r3 = 9
                goto L_0x0095
            L_0x0094:
                r3 = -1
            L_0x0095:
                switch(r3) {
                    case 0: goto L_0x00ad;
                    case 1: goto L_0x00aa;
                    case 2: goto L_0x00a7;
                    case 3: goto L_0x00a7;
                    case 4: goto L_0x00a7;
                    case 5: goto L_0x00a7;
                    case 6: goto L_0x00a4;
                    case 7: goto L_0x00a1;
                    case 8: goto L_0x009e;
                    case 9: goto L_0x009b;
                    default: goto L_0x0098;
                }
            L_0x0098:
                java.lang.String r3 = ""
                return r3
            L_0x009b:
                java.lang.String r3 = "AUD"
                return r3
            L_0x009e:
                java.lang.String r3 = "BRL"
                return r3
            L_0x00a1:
                java.lang.String r3 = "CDN"
                return r3
            L_0x00a4:
                java.lang.String r3 = "JPY"
                return r3
            L_0x00a7:
                java.lang.String r3 = "EUR"
                return r3
            L_0x00aa:
                java.lang.String r3 = "GBP"
                return r3
            L_0x00ad:
                java.lang.String r3 = "USD"
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.TrackAmazonPurchase.OSPurchasingListener.marketToCurrencyCode(java.lang.String):java.lang.String");
        }

        public void onProductDataResponse(ProductDataResponse productDataResponse) {
            RequestId requestId = this.lastRequestId;
            if (requestId == null || !requestId.toString().equals(productDataResponse.getRequestId().toString())) {
                PurchasingListener purchasingListener = this.orgPurchasingListener;
                if (purchasingListener != null) {
                    purchasingListener.onProductDataResponse(productDataResponse);
                    return;
                }
                return;
            }
            try {
                if (AnonymousClass1.$SwitchMap$com$amazon$device$iap$model$ProductDataResponse$RequestStatus[productDataResponse.getRequestStatus().ordinal()] == 1) {
                    JSONArray jSONArray = new JSONArray();
                    Map productData = productDataResponse.getProductData();
                    for (String str : productData.keySet()) {
                        Product product = (Product) productData.get(str);
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("sku", product.getSku());
                        jSONObject.put("iso", marketToCurrencyCode(this.currentMarket));
                        String price = product.getPrice();
                        if (!price.matches("^[0-9]")) {
                            price = price.substring(1);
                        }
                        jSONObject.put("amount", price);
                        jSONArray.put(jSONObject);
                    }
                    OneSignal.sendPurchases(jSONArray, false, (OneSignalRestClient.ResponseHandler) null);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void onPurchaseResponse(PurchaseResponse purchaseResponse) {
            if (purchaseResponse.getRequestStatus() == PurchaseResponse.RequestStatus.SUCCESSFUL) {
                this.currentMarket = purchaseResponse.getUserData().getMarketplace();
                HashSet hashSet = new HashSet();
                hashSet.add(purchaseResponse.getReceipt().getSku());
                this.lastRequestId = PurchasingService.getProductData(hashSet);
            }
            PurchasingListener purchasingListener = this.orgPurchasingListener;
            if (purchasingListener != null) {
                purchasingListener.onPurchaseResponse(purchaseResponse);
            }
        }

        public void onPurchaseUpdatesResponse(PurchaseUpdatesResponse purchaseUpdatesResponse) {
            PurchasingListener purchasingListener = this.orgPurchasingListener;
            if (purchasingListener != null) {
                purchasingListener.onPurchaseUpdatesResponse(purchaseUpdatesResponse);
            }
        }

        public void onUserDataResponse(UserDataResponse userDataResponse) {
            PurchasingListener purchasingListener = this.orgPurchasingListener;
            if (purchasingListener != null) {
                purchasingListener.onUserDataResponse(userDataResponse);
            }
        }
    }

    /* renamed from: com.onesignal.TrackAmazonPurchase$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$iap$model$ProductDataResponse$RequestStatus = new int[ProductDataResponse.RequestStatus.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$iap$model$ProductDataResponse$RequestStatus[ProductDataResponse.RequestStatus.SUCCESSFUL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }
}
