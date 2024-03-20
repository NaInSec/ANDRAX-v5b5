package de.mrapp.android.tabswitcher.layout;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.ViewGroup;

public interface TabSwitcherLayout {
    ViewGroup getTabContainer();

    Menu getToolbarMenu();

    Toolbar[] getToolbars();

    boolean isAnimationRunning();
}
