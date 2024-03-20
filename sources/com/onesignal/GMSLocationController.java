package com.onesignal;

import android.location.Location;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.onesignal.OneSignal;

class GMSLocationController extends LocationController {
    static final int API_FALLBACK_TIME = 30000;
    /* access modifiers changed from: private */
    public static GoogleApiClientCompatProxy googleApiClient;
    static LocationUpdateListener locationUpdateListener;

    /* access modifiers changed from: private */
    public static int getApiFallbackWait() {
        return API_FALLBACK_TIME;
    }

    GMSLocationController() {
    }

    static void startGetLocation() {
        initGoogleLocation();
    }

    private static void initGoogleLocation() {
        if (fallbackFailThread == null) {
            synchronized (syncLock) {
                startFallBackThread();
                if (googleApiClient != null) {
                    if (lastLocation != null) {
                        if (lastLocation != null) {
                            fireCompleteForLocation(lastLocation);
                        }
                    }
                }
                GoogleApiClientListener googleApiClientListener = new GoogleApiClientListener();
                googleApiClient = new GoogleApiClientCompatProxy(new GoogleApiClient.Builder(classContext).addApi(LocationServices.API).addConnectionCallbacks(googleApiClientListener).addOnConnectionFailedListener(googleApiClientListener).setHandler(locationHandlerThread.mHandler).build());
                googleApiClient.connect();
            }
        }
    }

    private static void startFallBackThread() {
        fallbackFailThread = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep((long) GMSLocationController.getApiFallbackWait());
                    OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Location permission exists but GoogleApiClient timed out. Maybe related to mismatch google-play aar versions.");
                    LocationController.fireFailedComplete();
                    LocationController.scheduleUpdate(LocationController.classContext);
                } catch (InterruptedException unused) {
                }
            }
        }, "OS_GMS_LOCATION_FALLBACK");
        fallbackFailThread.start();
    }

    static void fireFailedComplete() {
        synchronized (syncLock) {
            if (googleApiClient != null) {
                googleApiClient.disconnect();
            }
            googleApiClient = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0038, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void onFocusChange() {
        /*
            java.lang.Object r0 = syncLock
            monitor-enter(r0)
            com.onesignal.OneSignal$LOG_LEVEL r1 = com.onesignal.OneSignal.LOG_LEVEL.DEBUG     // Catch:{ all -> 0x003b }
            java.lang.String r2 = "GMSLocationController onFocusChange!"
            com.onesignal.OneSignal.Log(r1, r2)     // Catch:{ all -> 0x003b }
            com.onesignal.GoogleApiClientCompatProxy r1 = googleApiClient     // Catch:{ all -> 0x003b }
            if (r1 == 0) goto L_0x0039
            com.onesignal.GoogleApiClientCompatProxy r1 = googleApiClient     // Catch:{ all -> 0x003b }
            com.google.android.gms.common.api.GoogleApiClient r1 = r1.realInstance()     // Catch:{ all -> 0x003b }
            boolean r1 = r1.isConnected()     // Catch:{ all -> 0x003b }
            if (r1 != 0) goto L_0x001b
            goto L_0x0039
        L_0x001b:
            com.onesignal.GoogleApiClientCompatProxy r1 = googleApiClient     // Catch:{ all -> 0x003b }
            if (r1 == 0) goto L_0x0037
            com.onesignal.GoogleApiClientCompatProxy r1 = googleApiClient     // Catch:{ all -> 0x003b }
            com.google.android.gms.common.api.GoogleApiClient r1 = r1.realInstance()     // Catch:{ all -> 0x003b }
            com.onesignal.GMSLocationController$LocationUpdateListener r2 = locationUpdateListener     // Catch:{ all -> 0x003b }
            if (r2 == 0) goto L_0x0030
            com.google.android.gms.location.FusedLocationProviderApi r2 = com.google.android.gms.location.LocationServices.FusedLocationApi     // Catch:{ all -> 0x003b }
            com.onesignal.GMSLocationController$LocationUpdateListener r3 = locationUpdateListener     // Catch:{ all -> 0x003b }
            r2.removeLocationUpdates((com.google.android.gms.common.api.GoogleApiClient) r1, (com.google.android.gms.location.LocationListener) r3)     // Catch:{ all -> 0x003b }
        L_0x0030:
            com.onesignal.GMSLocationController$LocationUpdateListener r2 = new com.onesignal.GMSLocationController$LocationUpdateListener     // Catch:{ all -> 0x003b }
            r2.<init>(r1)     // Catch:{ all -> 0x003b }
            locationUpdateListener = r2     // Catch:{ all -> 0x003b }
        L_0x0037:
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            return
        L_0x0039:
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            return
        L_0x003b:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.GMSLocationController.onFocusChange():void");
    }

    private static class GoogleApiClientListener implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        private GoogleApiClientListener() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0074, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onConnected(android.os.Bundle r4) {
            /*
                r3 = this;
                java.lang.Object r4 = com.onesignal.LocationController.syncLock
                monitor-enter(r4)
                r0 = 0
                com.onesignal.PermissionsActivity.answered = r0     // Catch:{ all -> 0x0075 }
                com.onesignal.GoogleApiClientCompatProxy r0 = com.onesignal.GMSLocationController.googleApiClient     // Catch:{ all -> 0x0075 }
                if (r0 == 0) goto L_0x0073
                com.onesignal.GoogleApiClientCompatProxy r0 = com.onesignal.GMSLocationController.googleApiClient     // Catch:{ all -> 0x0075 }
                com.google.android.gms.common.api.GoogleApiClient r0 = r0.realInstance()     // Catch:{ all -> 0x0075 }
                if (r0 != 0) goto L_0x0017
                goto L_0x0073
            L_0x0017:
                com.onesignal.OneSignal$LOG_LEVEL r0 = com.onesignal.OneSignal.LOG_LEVEL.DEBUG     // Catch:{ all -> 0x0075 }
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0075 }
                r1.<init>()     // Catch:{ all -> 0x0075 }
                java.lang.String r2 = "LocationController GoogleApiClientListener onConnected lastLocation: "
                r1.append(r2)     // Catch:{ all -> 0x0075 }
                android.location.Location r2 = com.onesignal.LocationController.lastLocation     // Catch:{ all -> 0x0075 }
                r1.append(r2)     // Catch:{ all -> 0x0075 }
                java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0075 }
                com.onesignal.OneSignal.Log(r0, r1)     // Catch:{ all -> 0x0075 }
                android.location.Location r0 = com.onesignal.LocationController.lastLocation     // Catch:{ all -> 0x0075 }
                if (r0 != 0) goto L_0x0062
                com.onesignal.GoogleApiClientCompatProxy r0 = com.onesignal.GMSLocationController.googleApiClient     // Catch:{ all -> 0x0075 }
                com.google.android.gms.common.api.GoogleApiClient r0 = r0.realInstance()     // Catch:{ all -> 0x0075 }
                android.location.Location r0 = com.onesignal.GMSLocationController.FusedLocationApiWrapper.getLastLocation(r0)     // Catch:{ all -> 0x0075 }
                com.onesignal.LocationController.lastLocation = r0     // Catch:{ all -> 0x0075 }
                com.onesignal.OneSignal$LOG_LEVEL r0 = com.onesignal.OneSignal.LOG_LEVEL.DEBUG     // Catch:{ all -> 0x0075 }
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0075 }
                r1.<init>()     // Catch:{ all -> 0x0075 }
                java.lang.String r2 = "LocationController GoogleApiClientListener lastLocation: "
                r1.append(r2)     // Catch:{ all -> 0x0075 }
                android.location.Location r2 = com.onesignal.LocationController.lastLocation     // Catch:{ all -> 0x0075 }
                r1.append(r2)     // Catch:{ all -> 0x0075 }
                java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0075 }
                com.onesignal.OneSignal.Log(r0, r1)     // Catch:{ all -> 0x0075 }
                android.location.Location r0 = com.onesignal.LocationController.lastLocation     // Catch:{ all -> 0x0075 }
                if (r0 == 0) goto L_0x0062
                android.location.Location r0 = com.onesignal.LocationController.lastLocation     // Catch:{ all -> 0x0075 }
                com.onesignal.LocationController.fireCompleteForLocation(r0)     // Catch:{ all -> 0x0075 }
            L_0x0062:
                com.onesignal.GMSLocationController$LocationUpdateListener r0 = new com.onesignal.GMSLocationController$LocationUpdateListener     // Catch:{ all -> 0x0075 }
                com.onesignal.GoogleApiClientCompatProxy r1 = com.onesignal.GMSLocationController.googleApiClient     // Catch:{ all -> 0x0075 }
                com.google.android.gms.common.api.GoogleApiClient r1 = r1.realInstance()     // Catch:{ all -> 0x0075 }
                r0.<init>(r1)     // Catch:{ all -> 0x0075 }
                com.onesignal.GMSLocationController.locationUpdateListener = r0     // Catch:{ all -> 0x0075 }
                monitor-exit(r4)     // Catch:{ all -> 0x0075 }
                return
            L_0x0073:
                monitor-exit(r4)     // Catch:{ all -> 0x0075 }
                return
            L_0x0075:
                r0 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x0075 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.GMSLocationController.GoogleApiClientListener.onConnected(android.os.Bundle):void");
        }

        public void onConnectionSuspended(int i) {
            GMSLocationController.fireFailedComplete();
        }

        public void onConnectionFailed(ConnectionResult connectionResult) {
            GMSLocationController.fireFailedComplete();
        }
    }

    static class LocationUpdateListener implements LocationListener {
        private GoogleApiClient googleApiClient;

        LocationUpdateListener(GoogleApiClient googleApiClient2) {
            this.googleApiClient = googleApiClient2;
            init();
        }

        private void init() {
            long j = OneSignal.isForeground() ? 270000 : 570000;
            if (this.googleApiClient != null) {
                LocationRequest priority = LocationRequest.create().setFastestInterval(j).setInterval(j).setMaxWaitTime((long) (((double) j) * 1.5d)).setPriority(102);
                OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "GMSLocationController GoogleApiClient requestLocationUpdates!");
                FusedLocationApiWrapper.requestLocationUpdates(this.googleApiClient, priority, this);
            }
        }

        public void onLocationChanged(Location location) {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.Log(log_level, "GMSLocationController onLocationChanged: " + location);
            LocationController.lastLocation = location;
        }
    }

    static class FusedLocationApiWrapper {
        FusedLocationApiWrapper() {
        }

        static void requestLocationUpdates(GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener) {
            try {
                synchronized (GMSLocationController.syncLock) {
                    if (googleApiClient.isConnected()) {
                        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, locationListener);
                    }
                }
            } catch (Throwable th) {
                OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "FusedLocationApi.requestLocationUpdates failed!", th);
            }
        }

        static Location getLastLocation(GoogleApiClient googleApiClient) {
            synchronized (GMSLocationController.syncLock) {
                if (!googleApiClient.isConnected()) {
                    return null;
                }
                Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                return lastLocation;
            }
        }
    }
}
