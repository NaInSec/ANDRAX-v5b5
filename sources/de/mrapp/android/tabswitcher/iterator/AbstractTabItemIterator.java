package de.mrapp.android.tabswitcher.iterator;

import de.mrapp.android.tabswitcher.model.TabItem;
import de.mrapp.android.util.Condition;
import java.util.Iterator;

public abstract class AbstractTabItemIterator implements Iterator<TabItem> {
    private TabItem current;
    private TabItem first;
    private int index;
    private TabItem previous;
    private boolean reverse;

    public abstract int getCount();

    public abstract TabItem getItem(int i);

    public static abstract class AbstractBuilder<BuilderType extends AbstractBuilder<?, ProductType>, ProductType extends AbstractTabItemIterator> {
        protected boolean reverse;
        protected int start;

        private BuilderType self() {
            return this;
        }

        public abstract ProductType create();

        protected AbstractBuilder() {
            reverse(false);
            start(-1);
        }

        public BuilderType reverse(boolean z) {
            this.reverse = z;
            return self();
        }

        public BuilderType start(int i) {
            Condition.ensureAtLeast(i, -1, "The start must be at least -1");
            this.start = i;
            return self();
        }
    }

    /* access modifiers changed from: protected */
    public final void initialize(boolean z, int i) {
        Condition.ensureAtLeast(i, -1, "The start must be at least -1");
        this.reverse = z;
        this.previous = null;
        if (i == -1) {
            i = z ? getCount() - 1 : 0;
        }
        this.index = i;
        int i2 = z ? this.index + 1 : this.index - 1;
        if (i2 < 0 || i2 >= getCount()) {
            this.current = null;
        } else {
            this.current = getItem(i2);
        }
    }

    public final TabItem first() {
        return this.first;
    }

    public final TabItem previous() {
        return this.previous;
    }

    public final TabItem peek() {
        int i = this.index;
        if (i < 0 || i >= getCount()) {
            return null;
        }
        return getItem(this.index);
    }

    public final boolean hasNext() {
        if (this.reverse) {
            if (this.index >= 0) {
                return true;
            }
            return false;
        } else if (getCount() - this.index >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public final TabItem next() {
        if (!hasNext()) {
            return null;
        }
        TabItem tabItem = this.current;
        this.previous = tabItem;
        if (this.first == null) {
            this.first = tabItem;
        }
        this.current = getItem(this.index);
        this.index += this.reverse ? -1 : 1;
        return this.current;
    }
}
