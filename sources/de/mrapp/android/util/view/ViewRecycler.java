package de.mrapp.android.util.view;

import android.content.Context;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.logging.Logger;
import java.util.Map;

public class ViewRecycler<ItemType, ParamType> extends AbstractViewRecycler<ItemType, ParamType> {
    public ViewRecycler(Context context) {
        this(LayoutInflater.from(context));
    }

    public ViewRecycler(LayoutInflater layoutInflater) {
        super(layoutInflater);
    }

    @SafeVarargs
    public final Pair<View, Boolean> inflate(ItemType itemtype, ViewGroup viewGroup, ParamType... paramtypeArr) {
        return inflate(itemtype, viewGroup, true, paramtypeArr);
    }

    @SafeVarargs
    public final Pair<View, Boolean> inflate(ItemType itemtype, ViewGroup viewGroup, boolean z, ParamType... paramtypeArr) {
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
                View onInflateView = getAdapter().onInflateView(getLayoutInflater(), viewGroup, itemtype, viewType, paramtypeArr);
                z2 = true;
                Logger logger = getLogger();
                Class<?> cls = getClass();
                logger.logInfo(cls, "Inflated view to visualize item " + itemtype + " using view type " + viewType);
                view = onInflateView;
            } else {
                Logger logger2 = getLogger();
                Class<?> cls2 = getClass();
                logger2.logInfo(cls2, "Reusing view to visualize item " + itemtype + " using view type " + viewType);
            }
            getActiveViews().put(itemtype, view);
        }
        getAdapter().onShowView(getContext(), view, itemtype, z2, paramtypeArr);
        Logger logger3 = getLogger();
        Class<?> cls3 = getClass();
        logger3.logDebug(cls3, "Updated view of item " + itemtype);
        return Pair.create(view, Boolean.valueOf(z2));
    }

    @SafeVarargs
    public final Pair<View, Boolean> inflate(ItemType itemtype, boolean z, ParamType... paramtypeArr) {
        return inflate(itemtype, (ViewGroup) null, z, paramtypeArr);
    }

    public final void remove(ItemType itemtype) {
        Condition.ensureNotNull(itemtype, "The item may not be null");
        Condition.ensureNotNull(getAdapter(), "No adapter has been set", IllegalStateException.class);
        View view = (View) getActiveViews().remove(itemtype);
        if (view != null) {
            getAdapter().onRemoveView(view, itemtype);
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
        for (Map.Entry entry : getActiveViews().entrySet()) {
            Object key = entry.getKey();
            View view = (View) entry.getValue();
            getAdapter().onRemoveView(view, key);
            addUnusedView(view, getAdapter().getViewType(key));
        }
        getActiveViews().clear();
        getLogger().logInfo(getClass(), "Removed all views");
    }
}
