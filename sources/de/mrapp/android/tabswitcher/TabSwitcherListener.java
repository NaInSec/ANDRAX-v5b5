package de.mrapp.android.tabswitcher;

public interface TabSwitcherListener {
    void onAllTabsRemoved(TabSwitcher tabSwitcher, Tab[] tabArr, Animation animation);

    void onSelectionChanged(TabSwitcher tabSwitcher, int i, Tab tab);

    void onSwitcherHidden(TabSwitcher tabSwitcher);

    void onSwitcherShown(TabSwitcher tabSwitcher);

    void onTabAdded(TabSwitcher tabSwitcher, int i, Tab tab, Animation animation);

    void onTabRemoved(TabSwitcher tabSwitcher, int i, Tab tab, Animation animation);
}
