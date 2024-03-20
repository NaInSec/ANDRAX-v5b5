package de.mrapp.android.tabswitcher;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.mrapp.android.util.view.AbstractViewHolderAdapter;

public abstract class TabSwitcherDecorator extends AbstractViewHolderAdapter {
    private static final String VIEW_HIERARCHY_STATE_EXTRA = (TabSwitcherDecorator.class.getName() + "::ViewHierarchyState");

    public int getViewType(Tab tab, int i) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public abstract View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, int i);

    public void onSaveInstanceState(View view, Tab tab, int i, int i2, Bundle bundle) {
    }

    public abstract void onShowTab(Context context, TabSwitcher tabSwitcher, View view, Tab tab, int i, int i2, Bundle bundle);

    public final View inflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Tab tab, int i) {
        return onInflateView(layoutInflater, viewGroup, getViewType(tab, i));
    }

    public final void applyDecorator(Context context, TabSwitcher tabSwitcher, View view, Tab tab, int i, Bundle bundle) {
        SparseArray sparseParcelableArray;
        setCurrentParentView(view);
        int viewType = getViewType(tab, i);
        if (!(bundle == null || (sparseParcelableArray = bundle.getSparseParcelableArray(VIEW_HIERARCHY_STATE_EXTRA)) == null)) {
            view.restoreHierarchyState(sparseParcelableArray);
        }
        onShowTab(context, tabSwitcher, view, tab, i, viewType, bundle);
    }

    public final Bundle saveInstanceState(View view, Tab tab, int i) {
        setCurrentParentView(view);
        int viewType = getViewType(tab, i);
        Bundle bundle = new Bundle();
        SparseArray sparseArray = new SparseArray();
        view.saveHierarchyState(sparseArray);
        bundle.putSparseParcelableArray(VIEW_HIERARCHY_STATE_EXTRA, sparseArray);
        onSaveInstanceState(view, tab, i, viewType, bundle);
        return bundle;
    }
}
