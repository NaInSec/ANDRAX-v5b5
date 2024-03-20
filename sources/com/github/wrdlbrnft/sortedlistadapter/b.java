package com.github.wrdlbrnft.sortedlistadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter.ViewModel;
import java.util.Comparator;
import java.util.List;

final class b<T extends SortedListAdapter.ViewModel> extends SortedListAdapter<T> {
    private final List<a<?, ?>> a;

    static class a<M extends SortedListAdapter.ViewModel, VH extends SortedListAdapter.ViewHolder<M>> {
        final int a;
        final Class<M> b;
        final SortedListAdapter.ViewHolderFactory<VH> c;

        a(int i, Class<M> cls, SortedListAdapter.ViewHolderFactory<VH> viewHolderFactory) {
            this.a = i;
            this.b = cls;
            this.c = viewHolderFactory;
        }
    }

    b(Context context, Class<T> cls, Comparator<T> comparator, List<a<?, ?>> list) {
        super(context, cls, comparator);
        this.a = list;
    }

    public final int getItemViewType(int i) {
        Class<?> cls = ((SortedListAdapter.ViewModel) getItem(i)).getClass();
        for (a next : this.a) {
            if (next.b.isAssignableFrom(cls)) {
                return next.a;
            }
        }
        throw new IllegalStateException("No mapping for " + cls + " exists.");
    }

    /* access modifiers changed from: protected */
    public final SortedListAdapter.ViewHolder<? extends T> onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        for (a next : this.a) {
            if (next.a == i) {
                return next.c.create(layoutInflater, viewGroup);
            }
        }
        throw new IllegalStateException("No mapping for " + i + " exists.");
    }
}
