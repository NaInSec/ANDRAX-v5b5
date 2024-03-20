package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import java.util.ArrayList;
import java.util.Map;

final class zaan extends zaau {
    final /* synthetic */ zaak zagi;
    private final Map<Api.Client, zaam> zagk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zaan(zaak zaak, Map<Api.Client, zaam> map) {
        super(zaak, (zaal) null);
        this.zagi = zaak;
        this.zagk = map;
    }

    public final void zaan() {
        GoogleApiAvailabilityCache googleApiAvailabilityCache = new GoogleApiAvailabilityCache(this.zagi.zaex);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Api.Client next : this.zagk.keySet()) {
            if (!next.requiresGooglePlayServices() || this.zagk.get(next).zaeb) {
                arrayList2.add(next);
            } else {
                arrayList.add(next);
            }
        }
        int i = -1;
        int i2 = 0;
        if (!arrayList.isEmpty()) {
            ArrayList arrayList3 = arrayList;
            int size = arrayList3.size();
            while (i2 < size) {
                Object obj = arrayList3.get(i2);
                i2++;
                i = googleApiAvailabilityCache.getClientAvailability(this.zagi.mContext, (Api.Client) obj);
                if (i != 0) {
                    break;
                }
            }
        } else {
            ArrayList arrayList4 = arrayList2;
            int size2 = arrayList4.size();
            while (i2 < size2) {
                Object obj2 = arrayList4.get(i2);
                i2++;
                i = googleApiAvailabilityCache.getClientAvailability(this.zagi.mContext, (Api.Client) obj2);
                if (i == 0) {
                    break;
                }
            }
        }
        if (i != 0) {
            this.zagi.zafs.zaa((zabf) new zaao(this, this.zagi, new ConnectionResult(i, (PendingIntent) null)));
            return;
        }
        if (this.zagi.zagc) {
            this.zagi.zaga.connect();
        }
        for (Api.Client next2 : this.zagk.keySet()) {
            BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks = this.zagk.get(next2);
            if (!next2.requiresGooglePlayServices() || googleApiAvailabilityCache.getClientAvailability(this.zagi.mContext, next2) == 0) {
                next2.connect(connectionProgressReportCallbacks);
            } else {
                this.zagi.zafs.zaa((zabf) new zaap(this, this.zagi, connectionProgressReportCallbacks));
            }
        }
    }
}
