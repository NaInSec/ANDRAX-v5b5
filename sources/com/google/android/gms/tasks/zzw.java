package com.google.android.gms.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class zzw implements Continuation<Void, List<TResult>> {
    private final /* synthetic */ Collection zzae;

    zzw(Collection collection) {
        this.zzae = collection;
    }

    public final /* synthetic */ Object then(Task task) throws Exception {
        if (this.zzae.size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Task result : this.zzae) {
            arrayList.add(result.getResult());
        }
        return arrayList;
    }
}
