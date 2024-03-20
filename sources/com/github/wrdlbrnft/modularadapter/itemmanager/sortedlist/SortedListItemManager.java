package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.util.SortedList;
import com.github.wrdlbrnft.modularadapter.itemmanager.ChangeSet;
import com.github.wrdlbrnft.modularadapter.itemmanager.ItemManager;
import com.github.wrdlbrnft.modularadapter.itemmanager.ModifiableItemManager;
import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager.ViewModel;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class SortedListItemManager<T extends ViewModel> implements ModifiableItemManager<T> {
    /* access modifiers changed from: private */
    public static final Handler a = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final SortedListItemManager<T>.c b = new c(this, (byte) 0);
    /* access modifiers changed from: private */
    public final BlockingDeque<List<a<T>>> c = new LinkedBlockingDeque();
    /* access modifiers changed from: private */
    public final AtomicBoolean d = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public final List<ItemManager.ChangeSetCallback> e = new ArrayList();
    /* access modifiers changed from: private */
    public final List<ItemManager.StateCallback> f = new ArrayList();
    /* access modifiers changed from: private */
    public final Class<T> g;
    /* access modifiers changed from: private */
    public final Comparator<T> h;
    /* access modifiers changed from: private */
    public final SortedList<T> i;

    public interface ViewModel {
        <T> boolean isContentTheSameAs(T t);

        <T> boolean isSameModelAs(T t);
    }

    private interface a<T extends ViewModel> {
        void a();
    }

    private interface b {
        void a(a aVar);
    }

    private class c extends SortedList.Callback<T> {
        final List<b> a;
        final d<T> b;

        private c() {
            this.a = new ArrayList();
            this.b = new d();
        }

        /* synthetic */ c(SortedListItemManager sortedListItemManager, byte b2) {
            this();
        }

        static /* synthetic */ void a(c cVar, List list, List list2) {
            cVar.b.a(list);
            for (ItemManager.ChangeSetCallback onChangeSetAvailable : SortedListItemManager.this.e) {
                onChangeSetAvailable.onChangeSetAvailable(m.a(list2));
            }
        }

        static /* synthetic */ void a(List list, ChangeSet.MoveCallback moveCallback, ChangeSet.AddCallback addCallback, ChangeSet.RemoveCallback removeCallback, ChangeSet.ChangeCallback changeCallback) {
            b bVar = new b(moveCallback, addCallback, removeCallback, changeCallback);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((b) it.next()).a(bVar);
            }
        }

        /* access modifiers changed from: package-private */
        public final List<T> a() {
            ArrayList arrayList = new ArrayList();
            int size = SortedListItemManager.this.i.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(SortedListItemManager.this.i.get(i));
            }
            return arrayList;
        }

        public final /* synthetic */ boolean areContentsTheSame(Object obj, Object obj2) {
            return ((ViewModel) obj).isContentTheSameAs((ViewModel) obj2);
        }

        public final /* synthetic */ boolean areItemsTheSame(Object obj, Object obj2) {
            return ((ViewModel) obj).isSameModelAs((ViewModel) obj2);
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            return SortedListItemManager.this.h.compare((ViewModel) obj, (ViewModel) obj2);
        }

        public final void onChanged(int i, int i2) {
            this.a.add(new l(i, i2));
        }

        public final void onInserted(int i, int i2) {
            this.a.add(new i(i, i2));
        }

        public final void onMoved(int i, int i2) {
            this.a.add(new k(i, i2));
        }

        public final void onRemoved(int i, int i2) {
            this.a.add(new j(i, i2));
        }
    }

    interface d<T> {
        int a();

        T a(int i);

        void a(List<T> list);
    }

    private class e implements ModifiableItemManager.Transaction<T> {
        private final List<a<T>> b;

        private e() {
            this.b = new ArrayList();
        }

        /* synthetic */ e(SortedListItemManager sortedListItemManager, byte b2) {
            this();
        }

        private void a() {
            for (ItemManager.StateCallback onChangesInProgress : SortedListItemManager.this.f) {
                onChangesInProgress.onChangesInProgress();
            }
        }

        static /* synthetic */ void a(e eVar, Collection collection) {
            ViewModel[] viewModelArr = (ViewModel[]) collection.toArray((ViewModel[]) Array.newInstance(SortedListItemManager.this.g, collection.size()));
            Arrays.sort(viewModelArr, SortedListItemManager.this.h);
            for (int size = SortedListItemManager.this.i.size() - 1; size >= 0; size--) {
                ViewModel viewModel = (ViewModel) SortedListItemManager.this.i.get(size);
                if (Arrays.binarySearch(viewModelArr, viewModel, SortedListItemManager.this.h) < 0) {
                    SortedListItemManager.this.i.remove(viewModel);
                }
            }
            SortedListItemManager.this.i.addAll(viewModelArr, true);
        }

        static /* synthetic */ void a(e eVar, List list) {
            SortedListItemManager.this.c.add(list);
            if (!SortedListItemManager.this.d.getAndSet(true)) {
                new Thread(w.a(eVar)).start();
                eVar.a();
            }
        }

        /* access modifiers changed from: private */
        public void b() {
            for (ItemManager.StateCallback onChangesFinished : SortedListItemManager.this.f) {
                onChangesFinished.onChangesFinished();
            }
        }

        static /* synthetic */ void b(e eVar) {
            Handler a2;
            Runnable a3;
            while (true) {
                try {
                    if (SortedListItemManager.this.c.isEmpty()) {
                        SortedListItemManager.this.d.set(false);
                        a2 = SortedListItemManager.a;
                        a3 = o.a(eVar);
                        break;
                    }
                    List<a> list = (List) SortedListItemManager.this.c.pollFirst();
                    if (list == null) {
                        SortedListItemManager.this.d.set(false);
                        a2 = SortedListItemManager.a;
                        a3 = x.a(eVar);
                        break;
                    }
                    SortedListItemManager.this.i.beginBatchedUpdates();
                    for (a a4 : list) {
                        SortedList unused = SortedListItemManager.this.i;
                        a4.a();
                    }
                    SortedListItemManager.this.i.endBatchedUpdates();
                    c e = SortedListItemManager.this.b;
                    ArrayList arrayList = new ArrayList(e.a);
                    e.a.clear();
                    SortedListItemManager.a.post(h.a(e, e.a(), arrayList));
                } catch (Throwable th) {
                    SortedListItemManager.this.d.set(false);
                    SortedListItemManager.a.post(p.a(eVar));
                    throw th;
                }
            }
            a2.post(a3);
        }

        static /* synthetic */ void b(e eVar, Collection collection) {
            ViewModel[] viewModelArr = (ViewModel[]) collection.toArray((ViewModel[]) Array.newInstance(SortedListItemManager.this.g, collection.size()));
            Arrays.sort(viewModelArr, SortedListItemManager.this.h);
            for (ViewModel remove : viewModelArr) {
                SortedListItemManager.this.i.remove(remove);
            }
        }

        public final /* synthetic */ ModifiableItemManager.Transaction add(Object obj) {
            this.b.add(new n(this, (ViewModel) obj));
            return this;
        }

        public final ModifiableItemManager.Transaction<T> add(Collection<T> collection) {
            this.b.add(new q(this, collection));
            return this;
        }

        public final void commit() {
            ArrayList arrayList = new ArrayList(this.b);
            this.b.clear();
            SortedListItemManager.a.post(v.a(this, arrayList));
        }

        public final /* synthetic */ ModifiableItemManager.Transaction remove(Object obj) {
            this.b.add(new r(this, (ViewModel) obj));
            return this;
        }

        public final ModifiableItemManager.Transaction<T> remove(Collection<T> collection) {
            this.b.add(new s(this, collection));
            return this;
        }

        public final ModifiableItemManager.Transaction<T> removeAll() {
            this.b.add(new u(this));
            return this;
        }

        public final ModifiableItemManager.Transaction<T> replaceAll(Collection<T> collection) {
            this.b.add(new t(this, collection));
            return this;
        }
    }

    public SortedListItemManager(Class<T> cls, Comparator<T> comparator) {
        this.g = cls;
        this.h = comparator;
        this.i = new SortedList<>(this.g, this.b);
    }

    public void addChangeSetCallback(ItemManager.ChangeSetCallback changeSetCallback) {
        this.e.add(changeSetCallback);
    }

    public void addStateCallback(ItemManager.StateCallback stateCallback) {
        this.f.add(stateCallback);
    }

    public T getItem(int i2) {
        return (ViewModel) this.b.b.a(i2);
    }

    public int getItemCount() {
        return this.b.b.a();
    }

    public ModifiableItemManager.Transaction<T> newTransaction() {
        return new e(this, (byte) 0);
    }

    public void removeChangeSetCallback(ItemManager.ChangeSetCallback changeSetCallback) {
        this.e.remove(changeSetCallback);
    }

    public void removeStateCallback(ItemManager.StateCallback stateCallback) {
        this.f.remove(stateCallback);
    }
}
