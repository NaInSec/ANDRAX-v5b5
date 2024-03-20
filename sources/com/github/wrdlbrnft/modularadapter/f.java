package com.github.wrdlbrnft.modularadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.github.wrdlbrnft.modularadapter.ModularAdapter;
import com.github.wrdlbrnft.modularadapter.itemmanager.ItemManager;
import java.util.List;

final class f<T> extends ModularAdapter<T> {
    private final List<a<?, ?>> a;

    static class a<M, VH extends ModularAdapter.ViewHolder<M>> {
        final int a;
        final Class<M> b;
        final ModularAdapter.ViewHolderFactory<VH> c;

        a(int i, Class<M> cls, ModularAdapter.ViewHolderFactory<VH> viewHolderFactory) {
            this.a = i;
            this.b = cls;
            this.c = viewHolderFactory;
        }
    }

    f(Context context, ItemManager<T> itemManager, List<a<?, ?>> list) {
        super(context, itemManager);
        this.a = list;
    }

    public final int getItemViewType(int i) {
        Class<?> cls = getItem(i).getClass();
        for (a next : this.a) {
            if (next.b.isAssignableFrom(cls)) {
                return next.a;
            }
        }
        throw new IllegalStateException("No mapping for " + cls + " exists.");
    }

    /* access modifiers changed from: protected */
    public final ModularAdapter.ViewHolder<? extends T> onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        for (a next : this.a) {
            if (next.a == i) {
                return next.c.create(layoutInflater, viewGroup);
            }
        }
        throw new IllegalStateException("No mapping for " + i + " exists.");
    }
}
