package de.mrapp.android.tabswitcher.model;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import de.mrapp.android.tabswitcher.Animation;
import de.mrapp.android.tabswitcher.Tab;
import de.mrapp.android.tabswitcher.TabCloseListener;
import de.mrapp.android.tabswitcher.TabPreviewListener;
import de.mrapp.android.tabswitcher.TabSwitcherDecorator;
import de.mrapp.android.util.logging.LogLevel;
import java.util.Collection;

public interface Model extends Iterable<Tab> {

    public interface Listener {
        void onAllTabsAdded(int i, Tab[] tabArr, int i2, int i3, Animation animation);

        void onAllTabsRemoved(Tab[] tabArr, Animation animation);

        void onDecoratorChanged(TabSwitcherDecorator tabSwitcherDecorator);

        void onLogLevelChanged(LogLevel logLevel);

        void onPaddingChanged(int i, int i2, int i3, int i4);

        void onSelectionChanged(int i, int i2, Tab tab, boolean z);

        void onSwitcherHidden();

        void onSwitcherShown();

        void onTabAdded(int i, Tab tab, int i2, int i3, boolean z, Animation animation);

        void onTabBackgroundColorChanged(ColorStateList colorStateList);

        void onTabCloseButtonIconChanged(Drawable drawable);

        void onTabIconChanged(Drawable drawable);

        void onTabRemoved(int i, Tab tab, int i2, int i3, Animation animation);

        void onTabTitleColorChanged(ColorStateList colorStateList);

        void onToolbarMenuInflated(int i, Toolbar.OnMenuItemClickListener onMenuItemClickListener);

        void onToolbarNavigationIconChanged(Drawable drawable, View.OnClickListener onClickListener);

        void onToolbarTitleChanged(CharSequence charSequence);

        void onToolbarVisibilityChanged(boolean z);
    }

    void addAllTabs(Collection<? extends Tab> collection);

    void addAllTabs(Collection<? extends Tab> collection, int i);

    void addAllTabs(Collection<? extends Tab> collection, int i, Animation animation);

    void addAllTabs(Tab[] tabArr);

    void addAllTabs(Tab[] tabArr, int i);

    void addAllTabs(Tab[] tabArr, int i, Animation animation);

    void addCloseTabListener(TabCloseListener tabCloseListener);

    void addTab(Tab tab);

    void addTab(Tab tab, int i);

    void addTab(Tab tab, int i, Animation animation);

    void addTabPreviewListener(TabPreviewListener tabPreviewListener);

    boolean areToolbarsShown();

    void clear();

    void clear(Animation animation);

    Context getContext();

    int getCount();

    TabSwitcherDecorator getDecorator();

    LogLevel getLogLevel();

    int getPaddingBottom();

    int getPaddingEnd();

    int getPaddingLeft();

    int getPaddingRight();

    int getPaddingStart();

    int getPaddingTop();

    Tab getSelectedTab();

    int getSelectedTabIndex();

    Tab getTab(int i);

    ColorStateList getTabBackgroundColor();

    Drawable getTabCloseButtonIcon();

    Drawable getTabIcon();

    ColorStateList getTabTitleTextColor();

    Drawable getToolbarNavigationIcon();

    CharSequence getToolbarTitle();

    void hideSwitcher();

    int indexOf(Tab tab);

    void inflateToolbarMenu(int i, Toolbar.OnMenuItemClickListener onMenuItemClickListener);

    boolean isEmpty();

    boolean isSwitcherShown();

    void removeCloseTabListener(TabCloseListener tabCloseListener);

    void removeTab(Tab tab);

    void removeTab(Tab tab, Animation animation);

    void removeTabPreviewListener(TabPreviewListener tabPreviewListener);

    void selectTab(Tab tab);

    void setDecorator(TabSwitcherDecorator tabSwitcherDecorator);

    void setLogLevel(LogLevel logLevel);

    void setPadding(int i, int i2, int i3, int i4);

    void setTabBackgroundColor(int i);

    void setTabBackgroundColor(ColorStateList colorStateList);

    void setTabCloseButtonIcon(int i);

    void setTabCloseButtonIcon(Bitmap bitmap);

    void setTabIcon(int i);

    void setTabIcon(Bitmap bitmap);

    void setTabTitleTextColor(int i);

    void setTabTitleTextColor(ColorStateList colorStateList);

    void setToolbarNavigationIcon(int i, View.OnClickListener onClickListener);

    void setToolbarNavigationIcon(Drawable drawable, View.OnClickListener onClickListener);

    void setToolbarTitle(int i);

    void setToolbarTitle(CharSequence charSequence);

    void showSwitcher();

    void showToolbars(boolean z);

    void toggleSwitcherVisibility();
}
