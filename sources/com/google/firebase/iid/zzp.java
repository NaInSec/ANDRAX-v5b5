package com.google.firebase.iid;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzp implements OnCompleteListener {
    private final FirebaseInstanceId zzat;
    private final String zzau;
    private final String zzav;
    private final TaskCompletionSource zzaw;
    private final String zzax;

    zzp(FirebaseInstanceId firebaseInstanceId, String str, String str2, TaskCompletionSource taskCompletionSource, String str3) {
        this.zzat = firebaseInstanceId;
        this.zzau = str;
        this.zzav = str2;
        this.zzaw = taskCompletionSource;
        this.zzax = str3;
    }

    public final void onComplete(Task task) {
        this.zzat.zza(this.zzau, this.zzav, this.zzaw, this.zzax, task);
    }
}
