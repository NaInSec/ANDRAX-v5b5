package com.github.wrdlbrnft.modularadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.wrdlbrnft.modularadapter.f;
import com.github.wrdlbrnft.modularadapter.itemmanager.ItemManager;
import java.util.ArrayList;
import java.util.List;

public abstract class ModularAdapter<T> extends RecyclerView.Adapter<ViewHolder<? extends T>> {
    private final ItemManager<T> a;
    private final LayoutInflater b;

    public static class Builder<T> {
        private final List<f.a<?, ?>> a = new ArrayList();
        private final Context b;
        private final ItemManager<T> c;

        public Builder(Context context, ItemManager<T> itemManager) {
            this.b = context;
            this.c = itemManager;
        }

        public <M extends T, VH extends ViewHolder<M>> Builder<T> add(Class<M> cls, ViewHolderFactory<VH> viewHolderFactory) {
            List<f.a<?, ?>> list = this.a;
            list.add(new f.a(list.size(), cls, viewHolderFactory));
            return this;
        }

        public ModularAdapter<T> build() {
            return new f(this.b, this.c, this.a);
        }
    }

    public static abstract class ViewHolder<T> extends RecyclerView.ViewHolder {
        private T a;

        public ViewHolder(View view) {
            super(view);
        }

        public final void bind(T t) {
            this.a = t;
            performBind(t);
        }

        public final T getCurrentItem() {
            return this.a;
        }

        /* access modifiers changed from: protected */
        public abstract void performBind(T t);
    }

    public interface ViewHolderFactory<VH extends ViewHolder<?>> {
        VH create(LayoutInflater layoutInflater, ViewGroup viewGroup);
    }

    public ModularAdapter(Context context, ItemManager<T> itemManager) {
        this.b = LayoutInflater.from(context);
        this.a = itemManager;
        itemManager.addChangeSetCallback(a.a(this));
    }

    public final T getItem(int i) {
        return this.a.getItem(i);
    }

    public final int getItemCount() {
        return this.a.getItemCount();
    }

    public ItemManager<T> getItemManager() {
        return this.a;
    }

    public final void onBindViewHolder(ViewHolder<? extends T> viewHolder, int i) {
        viewHolder.bind(getItem(i));
    }

    /* access modifiers changed from: protected */
    public abstract ViewHolder<? extends T> onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i);

    public final ViewHolder<? extends T> onCreateViewHolder(ViewGroup viewGroup, int i) {
        return onCreateViewHolder(this.b, viewGroup, i);
    }
}
