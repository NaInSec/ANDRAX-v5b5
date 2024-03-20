package de.mrapp.android.util.view;

import android.content.Context;
import android.support.v4.util.Pair;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.logging.LogLevel;
import de.mrapp.android.util.logging.Logger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public abstract class AbstractViewRecycler<ItemType, ParamType> {
    private final Map<ItemType, View> activeViews = new HashMap();
    private Adapter<ItemType, ParamType> adapter = null;
    private final Context context;
    private final LayoutInflater inflater;
    private final Logger logger = new Logger(LogLevel.INFO);
    private SparseArray<Queue<View>> unusedViews = null;
    private boolean useCache = true;

    public static abstract class Adapter<ItemType, ParamType> {
        public int getViewType(ItemType itemtype) {
            return 0;
        }

        public int getViewTypeCount() {
            return 1;
        }

        public abstract View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, ItemType itemtype, int i, ParamType... paramtypeArr);

        public void onRemoveView(View view, ItemType itemtype) {
        }

        public abstract void onShowView(Context context, View view, ItemType itemtype, boolean z, ParamType... paramtypeArr);
    }

    public abstract Pair<View, Boolean> inflate(ItemType itemtype, boolean z, ParamType... paramtypeArr);

    public abstract void remove(ItemType itemtype);

    public abstract void removeAll();

    /* access modifiers changed from: protected */
    public final void addUnusedView(View view, int i) {
        if (this.useCache) {
            if (this.unusedViews == null) {
                this.unusedViews = new SparseArray<>(this.adapter.getViewTypeCount());
            }
            Queue queue = this.unusedViews.get(i);
            if (queue == null) {
                queue = new LinkedList();
                this.unusedViews.put(i, queue);
            }
            queue.add(view);
        }
    }

    /* access modifiers changed from: protected */
    public final View pollUnusedView(int i) {
        SparseArray<Queue<View>> sparseArray;
        Queue queue;
        if (!this.useCache || (sparseArray = this.unusedViews) == null || (queue = sparseArray.get(i)) == null) {
            return null;
        }
        return (View) queue.poll();
    }

    /* access modifiers changed from: protected */
    public final Logger getLogger() {
        return this.logger;
    }

    /* access modifiers changed from: protected */
    public final LayoutInflater getLayoutInflater() {
        return this.inflater;
    }

    /* access modifiers changed from: protected */
    public Map<ItemType, View> getActiveViews() {
        return this.activeViews;
    }

    public AbstractViewRecycler(LayoutInflater layoutInflater) {
        Condition.ensureNotNull(layoutInflater, "The layout inflater may not be null");
        this.context = layoutInflater.getContext();
        this.inflater = layoutInflater;
    }

    public final Pair<View, Boolean> inflate(ItemType itemtype, ParamType... paramtypeArr) {
        return inflate(itemtype, true, paramtypeArr);
    }

    public final Context getContext() {
        return this.context;
    }

    public final Adapter<ItemType, ParamType> getAdapter() {
        return this.adapter;
    }

    public final void setAdapter(Adapter<ItemType, ParamType> adapter2) {
        this.adapter = adapter2;
        clearCache();
    }

    public final LogLevel getLogLevel() {
        return this.logger.getLogLevel();
    }

    public final void setLogLevel(LogLevel logLevel) {
        this.logger.setLogLevel(logLevel);
    }

    public final View getView(ItemType itemtype) {
        Condition.ensureNotNull(itemtype, "The item may not be null");
        return this.activeViews.get(itemtype);
    }

    public final boolean isInflated(ItemType itemtype) {
        return getView(itemtype) != null;
    }

    public final void clearCache() {
        SparseArray<Queue<View>> sparseArray = this.unusedViews;
        if (sparseArray != null) {
            sparseArray.clear();
            this.unusedViews = null;
        }
        this.logger.logDebug(getClass(), "Removed all unused views from cache");
    }

    public final void clearCache(int i) {
        SparseArray<Queue<View>> sparseArray = this.unusedViews;
        if (sparseArray != null) {
            sparseArray.remove(i);
        }
        Logger logger2 = this.logger;
        Class<?> cls = getClass();
        logger2.logDebug(cls, "Removed all unused views of view type " + i + " from cache");
    }

    public final boolean isCacheUsed() {
        return this.useCache;
    }

    public final void useCache(boolean z) {
        this.useCache = z;
        if (!z) {
            clearCache();
        }
    }
}
