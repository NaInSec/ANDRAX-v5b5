package de.mrapp.android.util.view;

import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AttachedViewRecycler<ItemType, ParamType> extends AbstractViewRecycler<ItemType, ParamType> {
    private final Comparator<ItemType> comparator;
    private final List<ItemType> items;
    private final ViewGroup parent;

    public AttachedViewRecycler(ViewGroup viewGroup) {
        this(viewGroup, LayoutInflater.from(viewGroup.getContext()));
    }

    public AttachedViewRecycler(ViewGroup viewGroup, Comparator<ItemType> comparator2) {
        this(viewGroup, LayoutInflater.from(viewGroup.getContext()), comparator2);
    }

    public AttachedViewRecycler(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        this(viewGroup, layoutInflater, (Comparator) null);
    }

    public AttachedViewRecycler(ViewGroup viewGroup, LayoutInflater layoutInflater, Comparator<ItemType> comparator2) {
        super(layoutInflater);
        Condition.ensureNotNull(viewGroup, "The parent may not be null");
        this.parent = viewGroup;
        this.comparator = comparator2;
        this.items = new ArrayList();
    }

    @SafeVarargs
    public final Pair<View, Boolean> inflate(ItemType itemtype, boolean z, ParamType... paramtypeArr) {
        int i;
        Condition.ensureNotNull(paramtypeArr, "The array may not be null");
        Condition.ensureNotNull(getAdapter(), "No adapter has been set", IllegalStateException.class);
        View view = getView(itemtype);
        boolean z2 = false;
        if (view == null) {
            int viewType = getAdapter().getViewType(itemtype);
            if (z) {
                view = pollUnusedView(viewType);
            }
            if (view == null) {
                view = getAdapter().onInflateView(getLayoutInflater(), this.parent, itemtype, viewType, paramtypeArr);
                z2 = true;
                Logger logger = getLogger();
                Class<?> cls = getClass();
                logger.logInfo(cls, "Inflated view to visualize item " + itemtype + " using view type " + viewType);
            } else {
                Logger logger2 = getLogger();
                Class<?> cls2 = getClass();
                logger2.logInfo(cls2, "Reusing view to visualize item " + itemtype + " using view type " + viewType);
            }
            getActiveViews().put(itemtype, view);
            Comparator<ItemType> comparator2 = this.comparator;
            if (comparator2 != null) {
                i = Collections.binarySearch(this.items, itemtype, comparator2);
                if (i < 0) {
                    i = ~i;
                }
            } else {
                i = this.items.size();
            }
            this.items.add(i, itemtype);
            this.parent.addView(view, i);
            Logger logger3 = getLogger();
            Class<?> cls3 = getClass();
            logger3.logDebug(cls3, "Added view of item " + itemtype + " at index " + i);
        }
        getAdapter().onShowView(getContext(), view, itemtype, z2, paramtypeArr);
        Logger logger4 = getLogger();
        Class<?> cls4 = getClass();
        logger4.logDebug(cls4, "Updated view of item " + itemtype);
        return Pair.create(view, Boolean.valueOf(z2));
    }

    public final void remove(ItemType itemtype) {
        Condition.ensureNotNull(itemtype, "The item may not be null");
        Condition.ensureNotNull(getAdapter(), "No adapter has been set", IllegalStateException.class);
        int indexOf = this.items.indexOf(itemtype);
        if (indexOf != -1) {
            this.items.remove(indexOf);
            View view = (View) getActiveViews().remove(itemtype);
            getAdapter().onRemoveView(view, itemtype);
            this.parent.removeViewAt(indexOf);
            addUnusedView(view, getAdapter().getViewType(itemtype));
            Logger logger = getLogger();
            Class<?> cls = getClass();
            logger.logInfo(cls, "Removed view of item " + itemtype);
            return;
        }
        Logger logger2 = getLogger();
        Class<?> cls2 = getClass();
        logger2.logDebug(cls2, "Did not remove view of item " + itemtype + ". View is not inflated");
    }

    public final void removeAll() {
        Condition.ensureNotNull(getAdapter(), "No adapter has been set", IllegalStateException.class);
        for (int size = this.items.size() - 1; size >= 0; size--) {
            ItemType remove = this.items.remove(size);
            View view = (View) getActiveViews().remove(remove);
            getAdapter().onRemoveView(view, remove);
            this.parent.removeViewAt(size);
            addUnusedView(view, getAdapter().getViewType(remove));
        }
        getLogger().logInfo(getClass(), "Removed all views");
    }
}
