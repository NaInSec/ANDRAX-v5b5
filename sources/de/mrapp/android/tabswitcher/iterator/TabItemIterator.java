package de.mrapp.android.tabswitcher.iterator;

import de.mrapp.android.tabswitcher.iterator.AbstractTabItemIterator;
import de.mrapp.android.tabswitcher.model.Model;
import de.mrapp.android.tabswitcher.model.TabItem;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.view.AttachedViewRecycler;

public class TabItemIterator extends AbstractTabItemIterator {
    private final Model model;
    private final AttachedViewRecycler<TabItem, ?> viewRecycler;

    public static class Builder extends AbstractTabItemIterator.AbstractBuilder<Builder, TabItemIterator> {
        private final Model model;
        private final AttachedViewRecycler<TabItem, ?> viewRecycler;

        public Builder(Model model2, AttachedViewRecycler<TabItem, ?> attachedViewRecycler) {
            Condition.ensureNotNull(model2, "The model may not be null");
            Condition.ensureNotNull(attachedViewRecycler, "The view recycler may not be null");
            this.model = model2;
            this.viewRecycler = attachedViewRecycler;
        }

        public TabItemIterator create() {
            return new TabItemIterator(this.model, this.viewRecycler, this.reverse, this.start);
        }
    }

    private TabItemIterator(Model model2, AttachedViewRecycler<TabItem, ?> attachedViewRecycler, boolean z, int i) {
        Condition.ensureNotNull(model2, "The model may not be null");
        Condition.ensureNotNull(attachedViewRecycler, "The view recycler may not be null");
        this.model = model2;
        this.viewRecycler = attachedViewRecycler;
        initialize(z, i);
    }

    public final int getCount() {
        return this.model.getCount();
    }

    public final TabItem getItem(int i) {
        return TabItem.create(this.model, this.viewRecycler, i);
    }
}
