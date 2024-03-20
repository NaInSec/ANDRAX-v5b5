package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.zzu;
import com.google.android.gms.location.zzx;
import java.util.HashMap;
import java.util.Map;

public final class zzas {
    private final zzbj<zzao> zzcb;
    private final Context zzcu;
    private ContentProviderClient zzcv = null;
    private boolean zzcw = false;
    private final Map<ListenerHolder.ListenerKey<LocationListener>, zzax> zzcx = new HashMap();
    private final Map<ListenerHolder.ListenerKey<Object>, zzaw> zzcy = new HashMap();
    private final Map<ListenerHolder.ListenerKey<LocationCallback>, zzat> zzcz = new HashMap();

    public zzas(Context context, zzbj<zzao> zzbj) {
        this.zzcu = context;
        this.zzcb = zzbj;
    }

    private final zzax zza(ListenerHolder<LocationListener> listenerHolder) {
        zzax zzax;
        synchronized (this.zzcx) {
            zzax = this.zzcx.get(listenerHolder.getListenerKey());
            if (zzax == null) {
                zzax = new zzax(listenerHolder);
            }
            this.zzcx.put(listenerHolder.getListenerKey(), zzax);
        }
        return zzax;
    }

    private final zzat zzb(ListenerHolder<LocationCallback> listenerHolder) {
        zzat zzat;
        synchronized (this.zzcz) {
            zzat = this.zzcz.get(listenerHolder.getListenerKey());
            if (zzat == null) {
                zzat = new zzat(listenerHolder);
            }
            this.zzcz.put(listenerHolder.getListenerKey(), zzat);
        }
        return zzat;
    }

    public final Location getLastLocation() throws RemoteException {
        this.zzcb.checkConnected();
        return this.zzcb.getService().zza(this.zzcu.getPackageName());
    }

    public final void removeAllListeners() throws RemoteException {
        synchronized (this.zzcx) {
            for (zzax next : this.zzcx.values()) {
                if (next != null) {
                    this.zzcb.getService().zza(zzbf.zza((zzx) next, (zzaj) null));
                }
            }
            this.zzcx.clear();
        }
        synchronized (this.zzcz) {
            for (zzat next2 : this.zzcz.values()) {
                if (next2 != null) {
                    this.zzcb.getService().zza(zzbf.zza((zzu) next2, (zzaj) null));
                }
            }
            this.zzcz.clear();
        }
        synchronized (this.zzcy) {
            for (zzaw next3 : this.zzcy.values()) {
                if (next3 != null) {
                    this.zzcb.getService().zza(new zzo(2, (zzm) null, next3.asBinder(), (IBinder) null));
                }
            }
            this.zzcy.clear();
        }
    }

    public final LocationAvailability zza() throws RemoteException {
        this.zzcb.checkConnected();
        return this.zzcb.getService().zzb(this.zzcu.getPackageName());
    }

    public final void zza(PendingIntent pendingIntent, zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(new zzbf(2, (zzbd) null, (IBinder) null, pendingIntent, (IBinder) null, zzaj != null ? zzaj.asBinder() : null));
    }

    public final void zza(Location location) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(location);
    }

    public final void zza(ListenerHolder.ListenerKey<LocationListener> listenerKey, zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        Preconditions.checkNotNull(listenerKey, "Invalid null listener key");
        synchronized (this.zzcx) {
            zzax remove = this.zzcx.remove(listenerKey);
            if (remove != null) {
                remove.release();
                this.zzcb.getService().zza(zzbf.zza((zzx) remove, zzaj));
            }
        }
    }

    public final void zza(zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(zzaj);
    }

    public final void zza(zzbd zzbd, ListenerHolder<LocationCallback> listenerHolder, zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(new zzbf(1, zzbd, (IBinder) null, (PendingIntent) null, zzb(listenerHolder).asBinder(), zzaj != null ? zzaj.asBinder() : null));
    }

    public final void zza(LocationRequest locationRequest, PendingIntent pendingIntent, zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(new zzbf(1, zzbd.zza(locationRequest), (IBinder) null, pendingIntent, (IBinder) null, zzaj != null ? zzaj.asBinder() : null));
    }

    public final void zza(LocationRequest locationRequest, ListenerHolder<LocationListener> listenerHolder, zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(new zzbf(1, zzbd.zza(locationRequest), zza(listenerHolder).asBinder(), (PendingIntent) null, (IBinder) null, zzaj != null ? zzaj.asBinder() : null));
    }

    public final void zza(boolean z) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(z);
        this.zzcw = z;
    }

    public final void zzb() throws RemoteException {
        if (this.zzcw) {
            zza(false);
        }
    }

    public final void zzb(ListenerHolder.ListenerKey<LocationCallback> listenerKey, zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        Preconditions.checkNotNull(listenerKey, "Invalid null listener key");
        synchronized (this.zzcz) {
            zzat remove = this.zzcz.remove(listenerKey);
            if (remove != null) {
                remove.release();
                this.zzcb.getService().zza(zzbf.zza((zzu) remove, zzaj));
            }
        }
    }
}
