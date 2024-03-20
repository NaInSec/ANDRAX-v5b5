package de.mrapp.android.tabswitcher.model;

import android.view.View;
import de.mrapp.android.tabswitcher.R;
import de.mrapp.android.tabswitcher.Tab;
import de.mrapp.android.tabswitcher.TabSwitcher;
import de.mrapp.android.tabswitcher.layout.phone.PhoneTabViewHolder;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.view.AttachedViewRecycler;

public class TabItem {
    private final int index;
    private final Tab tab;
    private Tag tag = new Tag();
    private View view = null;
    private PhoneTabViewHolder viewHolder = null;

    public static class Comparator implements java.util.Comparator<TabItem> {
        private final TabSwitcher tabSwitcher;

        public Comparator(TabSwitcher tabSwitcher2) {
            Condition.ensureNotNull(tabSwitcher2, "The tab switcher may not be null");
            this.tabSwitcher = tabSwitcher2;
        }

        public int compare(TabItem tabItem, TabItem tabItem2) {
            Tab tab = tabItem.getTab();
            Tab tab2 = tabItem2.getTab();
            int indexOf = this.tabSwitcher.indexOf(tab);
            int indexOf2 = this.tabSwitcher.indexOf(tab2);
            if (indexOf2 == -1) {
                indexOf2 = tabItem2.getIndex();
            }
            if (indexOf == -1 || indexOf2 == -1) {
                throw new RuntimeException("Tab not contained by tab switcher");
            } else if (indexOf < indexOf2) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public TabItem(int i, Tab tab2) {
        Condition.ensureAtLeast(i, 0, "The index must be at least 0");
        Condition.ensureNotNull(tab2, "The tab may not be null");
        this.index = i;
        this.tab = tab2;
    }

    public static TabItem create(Model model, AttachedViewRecycler<TabItem, ?> attachedViewRecycler, int i) {
        return create(attachedViewRecycler, i, model.getTab(i));
    }

    public static TabItem create(AttachedViewRecycler<TabItem, ?> attachedViewRecycler, int i, Tab tab2) {
        TabItem tabItem = new TabItem(i, tab2);
        View view2 = attachedViewRecycler.getView(tabItem);
        if (view2 != null) {
            tabItem.setView(view2);
            tabItem.setViewHolder((PhoneTabViewHolder) view2.getTag(R.id.tag_view_holder));
            Tag tag2 = (Tag) view2.getTag(R.id.tag_properties);
            if (tag2 != null) {
                tabItem.setTag(tag2);
            }
        }
        return tabItem;
    }

    public final int getIndex() {
        return this.index;
    }

    public final Tab getTab() {
        return this.tab;
    }

    public final View getView() {
        return this.view;
    }

    public final void setView(View view2) {
        this.view = view2;
    }

    public final PhoneTabViewHolder getViewHolder() {
        return this.viewHolder;
    }

    public final void setViewHolder(PhoneTabViewHolder phoneTabViewHolder) {
        this.viewHolder = phoneTabViewHolder;
    }

    public final Tag getTag() {
        return this.tag;
    }

    public final void setTag(Tag tag2) {
        Condition.ensureNotNull(tag2, "The tag may not be null");
        this.tag = tag2;
    }

    public final boolean isInflated() {
        return (this.view == null || this.viewHolder == null) ? false : true;
    }

    public final boolean isVisible() {
        return this.tag.getState() != State.HIDDEN || this.tag.isClosing();
    }

    public final String toString() {
        return "TabItem [index = " + this.index + "]";
    }

    public final int hashCode() {
        return this.tab.hashCode();
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            return this.tab.equals(((TabItem) obj).tab);
        }
        return false;
    }
}
