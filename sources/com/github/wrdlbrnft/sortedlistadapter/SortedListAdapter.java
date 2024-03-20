package com.github.wrdlbrnft.sortedlistadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.wrdlbrnft.modularadapter.ModularAdapter;
import com.github.wrdlbrnft.modularadapter.itemmanager.ItemManager;
import com.github.wrdlbrnft.modularadapter.itemmanager.ModifiableItemManager;
import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter.ViewModel;
import com.github.wrdlbrnft.sortedlistadapter.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public abstract class SortedListAdapter<T extends ViewModel> extends ModularAdapter<T> {
    private final ItemManager.StateCallback a = new ItemManager.StateCallback() {
        public final void onChangesFinished() {
            for (Callback onEditFinished : SortedListAdapter.this.b) {
                onEditFinished.onEditFinished();
            }
        }

        public final void onChangesInProgress() {
            for (Callback onEditStarted : SortedListAdapter.this.b) {
                onEditStarted.onEditStarted();
            }
        }
    };
    /* access modifiers changed from: private */
    public final List<Callback> b = new ArrayList();

    public static class Builder<T extends ViewModel> {
        private final List<b.a<?, ?>> a = new ArrayList();
        private final Context b;
        private final Class<T> c;
        private final Comparator<T> d;

        public Builder(Context context, Class<T> cls, Comparator<T> comparator) {
            this.b = context;
            this.c = cls;
            this.d = comparator;
        }

        public <M extends T, VH extends ViewHolder<M>> Builder<T> add(Class<M> cls, ViewHolderFactory<VH> viewHolderFactory) {
            List<b.a<?, ?>> list = this.a;
            list.add(new b.a(list.size(), cls, viewHolderFactory));
            return this;
        }

        public SortedListAdapter<T> build() {
            return new b(this.b, this.c, this.d, this.a);
        }
    }

    public interface Callback {
        void onEditFinished();

        void onEditStarted();
    }

    public static class ComparatorBuilder<T extends ViewModel> extends com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.ComparatorBuilder<T> {
    }

    public interface Editor<T extends ViewModel> {
        Editor<T> add(T t);

        Editor<T> add(Collection<T> collection);

        void commit();

        Editor<T> remove(T t);

        Editor<T> remove(Collection<T> collection);

        Editor<T> removeAll();

        Editor<T> replaceAll(Collection<T> collection);
    }

    public static abstract class ViewHolder<T extends ViewModel> extends ModularAdapter.ViewHolder<T> {
        public ViewHolder(View view) {
            super(view);
        }
    }

    public interface ViewHolderFactory<VH extends ViewHolder<?>> {
        VH create(LayoutInflater layoutInflater, ViewGroup viewGroup);
    }

    public interface ViewModel extends SortedListItemManager.ViewModel {
    }

    public SortedListAdapter(Context context, Class<T> cls, Comparator<T> comparator) {
        super(context, new SortedListItemManager(cls, comparator));
        getItemManager().addStateCallback(this.a);
    }

    public void addCallback(Callback callback) {
        this.b.add(callback);
    }

    public final Editor<T> edit() {
        return new a(((ModifiableItemManager) getItemManager()).newTransaction());
    }

    /* access modifiers changed from: protected */
    public abstract ViewHolder<? extends T> onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i);

    public void removeCallback(Callback callback) {
        this.b.remove(callback);
    }
}
