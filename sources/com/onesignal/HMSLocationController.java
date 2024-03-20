package com.onesignal;

import android.location.Location;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.location.FusedLocationProviderClient;
import com.huawei.hms.location.LocationCallback;
import com.huawei.hms.location.LocationRequest;
import com.huawei.hms.location.LocationResult;
import com.huawei.hms.location.LocationServices;
import com.onesignal.OneSignal;

class HMSLocationController extends LocationController {
    /* access modifiers changed from: private */
    public static FusedLocationProviderClient hmsFusedLocationClient;
    static LocationUpdateListener locationUpdateListener;

    HMSLocationController() {
    }

    static void startGetLocation() {
        initHuaweiLocation();
    }

    private static void initHuaweiLocation() {
        synchronized (syncLock) {
            if (hmsFusedLocationClient == null) {
                try {
                    hmsFusedLocationClient = LocationServices.getFusedLocationProviderClient(classContext);
                } catch (Exception e) {
                    OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                    OneSignal.Log(log_level, "Huawei LocationServices getFusedLocationProviderClient failed! " + e);
                    fireFailedComplete();
                    return;
                }
            }
            if (lastLocation != null) {
                fireCompleteForLocation(lastLocation);
            } else {
                hmsFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    public void onSuccess(Location location) {
                        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.WARN;
                        OneSignal.Log(log_level, "Huawei LocationServices getLastLocation returned location: " + location);
                        if (location == null) {
                            HMSLocationController.fireFailedComplete();
                            return;
                        }
                        LocationController.lastLocation = location;
                        LocationController.fireCompleteForLocation(LocationController.lastLocation);
                        HMSLocationController.locationUpdateListener = new LocationUpdateListener(HMSLocationController.hmsFusedLocationClient);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    public void onFailure(Exception exc) {
                        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Huawei LocationServices getLastLocation failed!", exc);
                        HMSLocationController.fireFailedComplete();
                    }
                });
            }
        }
    }

    static void fireFailedComplete() {
        synchronized (syncLock) {
            hmsFusedLocationClient = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void onFocusChange() {
        /*
            java.lang.Object r0 = syncLock
            monitor-enter(r0)
            com.onesignal.OneSignal$LOG_LEVEL r1 = com.onesignal.OneSignal.LOG_LEVEL.DEBUG     // Catch:{ all -> 0x0030 }
            java.lang.String r2 = "HMSLocationController onFocusChange!"
            com.onesignal.OneSignal.Log(r1, r2)     // Catch:{ all -> 0x0030 }
            boolean r1 = isHMSAvailable()     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x0016
            com.huawei.hms.location.FusedLocationProviderClient r1 = hmsFusedLocationClient     // Catch:{ all -> 0x0030 }
            if (r1 != 0) goto L_0x0016
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            return
        L_0x0016:
            com.huawei.hms.location.FusedLocationProviderClient r1 = hmsFusedLocationClient     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x002e
            com.onesignal.HMSLocationController$LocationUpdateListener r1 = locationUpdateListener     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x0025
            com.huawei.hms.location.FusedLocationProviderClient r1 = hmsFusedLocationClient     // Catch:{ all -> 0x0030 }
            com.onesignal.HMSLocationController$LocationUpdateListener r2 = locationUpdateListener     // Catch:{ all -> 0x0030 }
            r1.removeLocationUpdates(r2)     // Catch:{ all -> 0x0030 }
        L_0x0025:
            com.onesignal.HMSLocationController$LocationUpdateListener r1 = new com.onesignal.HMSLocationController$LocationUpdateListener     // Catch:{ all -> 0x0030 }
            com.huawei.hms.location.FusedLocationProviderClient r2 = hmsFusedLocationClient     // Catch:{ all -> 0x0030 }
            r1.<init>(r2)     // Catch:{ all -> 0x0030 }
            locationUpdateListener = r1     // Catch:{ all -> 0x0030 }
        L_0x002e:
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            return
        L_0x0030:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.HMSLocationController.onFocusChange():void");
    }

    static class LocationUpdateListener extends LocationCallback {
        private FusedLocationProviderClient huaweiFusedLocationProviderClient;

        LocationUpdateListener(FusedLocationProviderClient fusedLocationProviderClient) {
            this.huaweiFusedLocationProviderClient = fusedLocationProviderClient;
            init();
        }

        private void init() {
            long j = OneSignal.isForeground() ? 270000 : 570000;
            LocationRequest priority = LocationRequest.create().setFastestInterval(j).setInterval(j).setMaxWaitTime((long) (((double) j) * 1.5d)).setPriority(102);
            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "HMSLocationController Huawei LocationServices requestLocationUpdates!");
            this.huaweiFusedLocationProviderClient.requestLocationUpdates(priority, this, LocationController.locationHandlerThread.getLooper());
        }

        public void onLocationResult(LocationResult locationResult) {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.Log(log_level, "HMSLocationController onLocationResult: " + locationResult);
            if (locationResult != null) {
                LocationController.lastLocation = locationResult.getLastLocation();
            }
        }
    }
}
