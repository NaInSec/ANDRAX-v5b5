package de.mrapp.android.tabswitcher.layout;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.mrapp.android.tabswitcher.Tab;
import de.mrapp.android.tabswitcher.TabSwitcher;
import de.mrapp.android.tabswitcher.TabSwitcherDecorator;
import de.mrapp.android.tabswitcher.model.Restorable;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.view.AbstractViewRecycler;

public class ChildRecyclerAdapter extends AbstractViewRecycler.Adapter<Tab, Void> implements Restorable {
    private static final String SAVED_INSTANCE_STATES_EXTRA = (ChildRecyclerAdapter.class.getName() + "::SavedInstanceStates");
    private final TabSwitcherDecorator decorator;
    private SparseArray<Bundle> savedInstanceStates = new SparseArray<>();
    private final TabSwitcher tabSwitcher;

    public ChildRecyclerAdapter(TabSwitcher tabSwitcher2, TabSwitcherDecorator tabSwitcherDecorator) {
        Condition.ensureNotNull(tabSwitcher2, "The tab switcher may not be null");
        Condition.ensureNotNull(tabSwitcherDecorator, "The decorator may not be null");
        this.tabSwitcher = tabSwitcher2;
        this.decorator = tabSwitcherDecorator;
    }

    public final View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Tab tab, int i, Void... voidArr) {
        return this.decorator.inflateView(layoutInflater, viewGroup, tab, this.tabSwitcher.indexOf(tab));
    }

    public final void onShowView(Context context, View view, Tab tab, boolean z, Void... voidArr) {
        Context context2 = context;
        this.decorator.applyDecorator(context2, this.tabSwitcher, view, tab, this.tabSwitcher.indexOf(tab), this.savedInstanceStates.get(tab.hashCode()));
    }

    public final void onRemoveView(View view, Tab tab) {
        this.savedInstanceStates.put(tab.hashCode(), this.decorator.saveInstanceState(view, tab, this.tabSwitcher.indexOf(tab)));
    }

    public final int getViewTypeCount() {
        return this.decorator.getViewTypeCount();
    }

    public final int getViewType(Tab tab) {
        return this.decorator.getViewType(tab, this.tabSwitcher.indexOf(tab));
    }

    public final void saveInstanceState(Bundle bundle) {
        bundle.putSparseParcelableArray(SAVED_INSTANCE_STATES_EXTRA, this.savedInstanceStates);
    }

    public final void restoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.savedInstanceStates = bundle.getSparseParcelableArray(SAVED_INSTANCE_STATES_EXTRA);
        }
    }
}
