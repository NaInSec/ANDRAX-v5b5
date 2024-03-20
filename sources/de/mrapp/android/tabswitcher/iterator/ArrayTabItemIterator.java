package de.mrapp.android.tabswitcher.iterator;

import de.mrapp.android.tabswitcher.Tab;
import de.mrapp.android.tabswitcher.iterator.AbstractTabItemIterator;
import de.mrapp.android.tabswitcher.model.TabItem;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.view.AttachedViewRecycler;

public class ArrayTabItemIterator extends AbstractTabItemIterator {
    private final Tab[] array;
    private final AttachedViewRecycler<TabItem, ?> viewRecycler;

    public static class Builder extends AbstractTabItemIterator.AbstractBuilder<Builder, ArrayTabItemIterator> {
        private final Tab[] array;
        private final AttachedViewRecycler<TabItem, ?> viewRecycler;

        public Builder(AttachedViewRecycler<TabItem, ?> attachedViewRecycler, Tab[] tabArr) {
            Condition.ensureNotNull(attachedViewRecycler, "The view recycler may not be null");
            Condition.ensureNotNull(tabArr, "The array may not be null");
            this.viewRecycler = attachedViewRecycler;
            this.array = tabArr;
        }

        public ArrayTabItemIterator create() {
            return new ArrayTabItemIterator(this.viewRecycler, this.array, this.reverse, this.start);
        }
    }

    private ArrayTabItemIterator(AttachedViewRecycler<TabItem, ?> attachedViewRecycler, Tab[] tabArr, boolean z, int i) {
        Condition.ensureNotNull(attachedViewRecycler, "The view recycler may not be null");
        Condition.ensureNotNull(tabArr, "The array may not be null");
        this.viewRecycler = attachedViewRecycler;
        this.array = tabArr;
        initialize(z, i);
    }

    public final int getCount() {
        return this.array.length;
    }

    public final TabItem getItem(int i) {
        return TabItem.create(this.viewRecycler, i, this.array[i]);
    }
}
