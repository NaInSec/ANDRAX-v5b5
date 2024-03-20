package de.mrapp.android.tabswitcher.model;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import de.mrapp.android.tabswitcher.Animation;
import de.mrapp.android.tabswitcher.PeekAnimation;
import de.mrapp.android.tabswitcher.RevealAnimation;
import de.mrapp.android.tabswitcher.SwipeAnimation;
import de.mrapp.android.tabswitcher.Tab;
import de.mrapp.android.tabswitcher.TabCloseListener;
import de.mrapp.android.tabswitcher.TabPreviewListener;
import de.mrapp.android.tabswitcher.TabSwitcher;
import de.mrapp.android.tabswitcher.TabSwitcherDecorator;
import de.mrapp.android.tabswitcher.layout.ChildRecyclerAdapter;
import de.mrapp.android.tabswitcher.model.Model;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.logging.LogLevel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class TabSwitcherModel implements Model, Restorable {
    public static final String FIRST_VISIBLE_TAB_INDEX_EXTRA;
    public static final String FIRST_VISIBLE_TAB_POSITION_EXTRA;
    private static final String LOG_LEVEL_EXTRA;
    private static final String PADDING_EXTRA;
    private static final String SELECTED_TAB_EXTRA;
    private static final String SHOW_TOOLBARS_EXTRA = (TabSwitcher.class.getName() + "::ShowToolbars");
    private static final String SWITCHER_SHOWN_EXTRA;
    private static final String TABS_EXTRA;
    private static final String TAB_BACKGROUND_COLOR_EXTRA;
    private static final String TAB_CLOSE_BUTTON_ICON_BITMAP_EXTRA = (TabSwitcher.class.getName() + "::TabCloseButtonIconBitmap");
    private static final String TAB_CLOSE_BUTTON_ICON_ID_EXTRA;
    private static final String TAB_ICON_BITMAP_EXTRA;
    private static final String TAB_ICON_ID_EXTRA;
    private static final String TAB_TITLE_TEXT_COLOR_EXTRA;
    private static final String TOOLBAR_TITLE_EXTRA = (TabSwitcher.class.getName() + "::ToolbarTitle");
    private ChildRecyclerAdapter childRecyclerAdapter = null;
    private TabSwitcherDecorator decorator = null;
    private int firstVisibleTabIndex = -1;
    private float firstVisibleTabPosition = -1.0f;
    private final Set<Model.Listener> listeners = new LinkedHashSet();
    private LogLevel logLevel = LogLevel.INFO;
    private int[] padding = {0, 0, 0, 0};
    private Tab selectedTab = null;
    private boolean showToolbars = false;
    private boolean switcherShown = false;
    private ColorStateList tabBackgroundColor = null;
    private Bitmap tabCloseButtonIconBitmap = null;
    private int tabCloseButtonIconId = -1;
    private final Set<TabCloseListener> tabCloseListeners = new LinkedHashSet();
    private Bitmap tabIconBitmap = null;
    private int tabIconId = -1;
    private final Set<TabPreviewListener> tabPreviewListeners = new LinkedHashSet();
    private final TabSwitcher tabSwitcher;
    private ColorStateList tabTitleTextColor = null;
    private ArrayList<Tab> tabs = new ArrayList<>();
    private int toolbarMenuId = -1;
    private Toolbar.OnMenuItemClickListener toolbarMenuItemListener = null;
    private Drawable toolbarNavigationIcon = null;
    private View.OnClickListener toolbarNavigationIconListener = null;
    private CharSequence toolbarTitle = null;

    static {
        Class<TabSwitcherModel> cls = TabSwitcherModel.class;
        FIRST_VISIBLE_TAB_INDEX_EXTRA = cls.getName() + "::FirstVisibleIndex";
        FIRST_VISIBLE_TAB_POSITION_EXTRA = cls.getName() + "::FirstVisiblePosition";
        LOG_LEVEL_EXTRA = cls.getName() + "::LogLevel";
        TABS_EXTRA = cls.getName() + "::Tabs";
        SWITCHER_SHOWN_EXTRA = cls.getName() + "::SwitcherShown";
        SELECTED_TAB_EXTRA = cls.getName() + "::SelectedTab";
        PADDING_EXTRA = cls.getName() + "::Padding";
        TAB_ICON_ID_EXTRA = cls.getName() + "::TabIconId";
        TAB_ICON_BITMAP_EXTRA = cls.getName() + "::TabIconBitmap";
        TAB_BACKGROUND_COLOR_EXTRA = cls.getName() + "::TabBackgroundColor";
        TAB_TITLE_TEXT_COLOR_EXTRA = cls.getName() + "::TabTitleTextColor";
        TAB_CLOSE_BUTTON_ICON_ID_EXTRA = cls.getName() + "::TabCloseButtonIconId";
    }

    private int indexOfOrThrowException(Tab tab) {
        int indexOf = indexOf(tab);
        Integer valueOf = Integer.valueOf(indexOf);
        Condition.ensureNotEqual(valueOf, -1, "No such tab: " + tab, NoSuchElementException.class);
        return indexOf;
    }

    private boolean setSwitcherShown(boolean z) {
        if (this.switcherShown == z) {
            return false;
        }
        this.switcherShown = z;
        return true;
    }

    private void notifyOnLogLevelChanged(LogLevel logLevel2) {
        for (Model.Listener onLogLevelChanged : this.listeners) {
            onLogLevelChanged.onLogLevelChanged(logLevel2);
        }
    }

    private void notifyOnDecoratorChanged(TabSwitcherDecorator tabSwitcherDecorator) {
        for (Model.Listener onDecoratorChanged : this.listeners) {
            onDecoratorChanged.onDecoratorChanged(tabSwitcherDecorator);
        }
    }

    private void notifyOnSwitcherShown() {
        for (Model.Listener onSwitcherShown : this.listeners) {
            onSwitcherShown.onSwitcherShown();
        }
    }

    private void notifyOnSwitcherHidden() {
        for (Model.Listener onSwitcherHidden : this.listeners) {
            onSwitcherHidden.onSwitcherHidden();
        }
    }

    private void notifyOnSelectionChanged(int i, int i2, Tab tab, boolean z) {
        for (Model.Listener onSelectionChanged : this.listeners) {
            onSelectionChanged.onSelectionChanged(i, i2, tab, z);
        }
    }

    private void notifyOnTabAdded(int i, Tab tab, int i2, int i3, boolean z, Animation animation) {
        for (Model.Listener onTabAdded : this.listeners) {
            onTabAdded.onTabAdded(i, tab, i2, i3, z, animation);
        }
    }

    private void notifyOnAllTabsAdded(int i, Tab[] tabArr, int i2, int i3, Animation animation) {
        for (Model.Listener onAllTabsAdded : this.listeners) {
            onAllTabsAdded.onAllTabsAdded(i, tabArr, i2, i3, animation);
        }
    }

    private void notifyOnTabRemoved(int i, Tab tab, int i2, int i3, Animation animation) {
        for (Model.Listener onTabRemoved : this.listeners) {
            onTabRemoved.onTabRemoved(i, tab, i2, i3, animation);
        }
    }

    private void notifyOnAllTabsRemoved(Tab[] tabArr, Animation animation) {
        for (Model.Listener onAllTabsRemoved : this.listeners) {
            onAllTabsRemoved.onAllTabsRemoved(tabArr, animation);
        }
    }

    private void notifyOnPaddingChanged(int i, int i2, int i3, int i4) {
        for (Model.Listener onPaddingChanged : this.listeners) {
            onPaddingChanged.onPaddingChanged(i, i2, i3, i4);
        }
    }

    private void notifyOnTabIconChanged(Drawable drawable) {
        for (Model.Listener onTabIconChanged : this.listeners) {
            onTabIconChanged.onTabIconChanged(drawable);
        }
    }

    private void notifyOnTabBackgroundColorChanged(ColorStateList colorStateList) {
        for (Model.Listener onTabBackgroundColorChanged : this.listeners) {
            onTabBackgroundColorChanged.onTabBackgroundColorChanged(colorStateList);
        }
    }

    private void notifyOnTabTitleColorChanged(ColorStateList colorStateList) {
        for (Model.Listener onTabTitleColorChanged : this.listeners) {
            onTabTitleColorChanged.onTabTitleColorChanged(colorStateList);
        }
    }

    private void notifyOnTabCloseButtonIconChanged(Drawable drawable) {
        for (Model.Listener onTabCloseButtonIconChanged : this.listeners) {
            onTabCloseButtonIconChanged.onTabCloseButtonIconChanged(drawable);
        }
    }

    private void notifyOnToolbarVisibilityChanged(boolean z) {
        for (Model.Listener onToolbarVisibilityChanged : this.listeners) {
            onToolbarVisibilityChanged.onToolbarVisibilityChanged(z);
        }
    }

    private void notifyOnToolbarTitleChanged(CharSequence charSequence) {
        for (Model.Listener onToolbarTitleChanged : this.listeners) {
            onToolbarTitleChanged.onToolbarTitleChanged(charSequence);
        }
    }

    private void notifyOnToolbarMenuInflated(int i, Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
        for (Model.Listener onToolbarMenuInflated : this.listeners) {
            onToolbarMenuInflated.onToolbarMenuInflated(i, onMenuItemClickListener);
        }
    }

    private void notifyOnToolbarNavigationIconChanged(Drawable drawable, View.OnClickListener onClickListener) {
        for (Model.Listener onToolbarNavigationIconChanged : this.listeners) {
            onToolbarNavigationIconChanged.onToolbarNavigationIconChanged(drawable, onClickListener);
        }
    }

    public TabSwitcherModel(TabSwitcher tabSwitcher2) {
        Condition.ensureNotNull(tabSwitcher2, "The tab switcher may not be null");
        this.tabSwitcher = tabSwitcher2;
    }

    public final void addListener(Model.Listener listener) {
        Condition.ensureNotNull(listener, "The listener may not be null");
        this.listeners.add(listener);
    }

    public final void removeListener(Model.Listener listener) {
        Condition.ensureNotNull(listener, "The listener may not be null");
        this.listeners.remove(listener);
    }

    public final int getFirstVisibleTabIndex() {
        return this.firstVisibleTabIndex;
    }

    public final void setFirstVisibleTabIndex(int i) {
        this.firstVisibleTabIndex = i;
    }

    public final float getFirstVisibleTabPosition() {
        return this.firstVisibleTabPosition;
    }

    public final void setFirstVisibleTabPosition(float f) {
        this.firstVisibleTabPosition = f;
    }

    public final View.OnClickListener getToolbarNavigationIconListener() {
        return this.toolbarNavigationIconListener;
    }

    public final int getToolbarMenuId() {
        return this.toolbarMenuId;
    }

    public final Toolbar.OnMenuItemClickListener getToolbarMenuItemListener() {
        return this.toolbarMenuItemListener;
    }

    public final Set<TabCloseListener> getTabCloseListeners() {
        return this.tabCloseListeners;
    }

    public final Set<TabPreviewListener> getTabPreviewListeners() {
        return this.tabPreviewListeners;
    }

    public final ChildRecyclerAdapter getChildRecyclerAdapter() {
        return this.childRecyclerAdapter;
    }

    public final Context getContext() {
        return this.tabSwitcher.getContext();
    }

    public final void setDecorator(TabSwitcherDecorator tabSwitcherDecorator) {
        Condition.ensureNotNull(tabSwitcherDecorator, "The decorator may not be null");
        this.decorator = tabSwitcherDecorator;
        this.childRecyclerAdapter = new ChildRecyclerAdapter(this.tabSwitcher, tabSwitcherDecorator);
        notifyOnDecoratorChanged(tabSwitcherDecorator);
    }

    public final TabSwitcherDecorator getDecorator() {
        return this.decorator;
    }

    public final LogLevel getLogLevel() {
        return this.logLevel;
    }

    public final void setLogLevel(LogLevel logLevel2) {
        Condition.ensureNotNull(logLevel2, "The log level may not be null");
        this.logLevel = logLevel2;
        notifyOnLogLevelChanged(logLevel2);
    }

    public final boolean isEmpty() {
        return this.tabs.isEmpty();
    }

    public final int getCount() {
        return this.tabs.size();
    }

    public final Tab getTab(int i) {
        return this.tabs.get(i);
    }

    public final int indexOf(Tab tab) {
        Condition.ensureNotNull(tab, "The tab may not be null");
        return this.tabs.indexOf(tab);
    }

    public final void addTab(Tab tab) {
        addTab(tab, getCount());
    }

    public final void addTab(Tab tab, int i) {
        addTab(tab, i, new SwipeAnimation.Builder().create());
    }

    public final void addTab(Tab tab, int i, Animation animation) {
        int i2;
        int i3;
        Condition.ensureNotNull(tab, "The tab may not be null");
        Condition.ensureNotNull(animation, "The animation may not be null");
        this.tabs.add(i, tab);
        int selectedTabIndex = getSelectedTabIndex();
        if (selectedTabIndex == -1) {
            this.selectedTab = tab;
            i2 = i;
        } else {
            i2 = selectedTabIndex;
        }
        boolean z = false;
        if (animation instanceof RevealAnimation) {
            this.selectedTab = tab;
            z = setSwitcherShown(false);
            i3 = i;
        } else {
            i3 = i2;
        }
        notifyOnTabAdded(i, tab, selectedTabIndex, i3, animation instanceof PeekAnimation ? setSwitcherShown(true) : z, animation);
    }

    public final void addAllTabs(Collection<? extends Tab> collection) {
        addAllTabs(collection, getCount());
    }

    public final void addAllTabs(Collection<? extends Tab> collection, int i) {
        addAllTabs(collection, i, (Animation) new SwipeAnimation.Builder().create());
    }

    public final void addAllTabs(Collection<? extends Tab> collection, int i, Animation animation) {
        Condition.ensureNotNull(collection, "The collection may not be null");
        Tab[] tabArr = new Tab[collection.size()];
        collection.toArray(tabArr);
        addAllTabs(tabArr, i, animation);
    }

    public final void addAllTabs(Tab[] tabArr) {
        addAllTabs(tabArr, getCount());
    }

    public final void addAllTabs(Tab[] tabArr, int i) {
        addAllTabs(tabArr, i, (Animation) new SwipeAnimation.Builder().create());
    }

    public final void addAllTabs(Tab[] tabArr, int i, Animation animation) {
        int i2;
        Condition.ensureNotNull(tabArr, "The array may not be null");
        Condition.ensureNotNull(animation, "The animation may not be null");
        if (tabArr.length > 0) {
            int selectedTabIndex = getSelectedTabIndex();
            for (int i3 = 0; i3 < tabArr.length; i3++) {
                this.tabs.add(i + i3, tabArr[i3]);
            }
            if (selectedTabIndex == -1) {
                this.selectedTab = tabArr[0];
                i2 = 0;
            } else {
                i2 = selectedTabIndex;
            }
            notifyOnAllTabsAdded(i, tabArr, selectedTabIndex, i2, animation);
        }
    }

    public final void removeTab(Tab tab) {
        removeTab(tab, new SwipeAnimation.Builder().create());
    }

    public final void removeTab(Tab tab, Animation animation) {
        int i;
        Condition.ensureNotNull(tab, "The tab may not be null");
        Condition.ensureNotNull(animation, "The animation may not be null");
        int indexOfOrThrowException = indexOfOrThrowException(tab);
        int selectedTabIndex = getSelectedTabIndex();
        this.tabs.remove(indexOfOrThrowException);
        if (isEmpty()) {
            this.selectedTab = null;
            i = -1;
        } else if (indexOfOrThrowException == selectedTabIndex) {
            int i2 = indexOfOrThrowException > 0 ? indexOfOrThrowException - 1 : selectedTabIndex;
            this.selectedTab = getTab(i2);
            i = i2;
        } else {
            i = selectedTabIndex;
        }
        notifyOnTabRemoved(indexOfOrThrowException, tab, selectedTabIndex, i, animation);
    }

    public final void clear() {
        clear(new SwipeAnimation.Builder().create());
    }

    public final void clear(Animation animation) {
        Condition.ensureNotNull(animation, "The animation may not be null");
        Tab[] tabArr = new Tab[this.tabs.size()];
        this.tabs.toArray(tabArr);
        this.tabs.clear();
        notifyOnAllTabsRemoved(tabArr, animation);
        this.selectedTab = null;
    }

    public final boolean isSwitcherShown() {
        return this.switcherShown;
    }

    public final void showSwitcher() {
        setSwitcherShown(true);
        notifyOnSwitcherShown();
    }

    public final void hideSwitcher() {
        setSwitcherShown(false);
        notifyOnSwitcherHidden();
    }

    public final void toggleSwitcherVisibility() {
        if (isSwitcherShown()) {
            hideSwitcher();
        } else {
            showSwitcher();
        }
    }

    public final Tab getSelectedTab() {
        return this.selectedTab;
    }

    public final int getSelectedTabIndex() {
        Tab tab = this.selectedTab;
        if (tab != null) {
            return indexOf(tab);
        }
        return -1;
    }

    public final void selectTab(Tab tab) {
        Condition.ensureNotNull(tab, "The tab may not be null");
        int selectedTabIndex = getSelectedTabIndex();
        int indexOfOrThrowException = indexOfOrThrowException(tab);
        this.selectedTab = tab;
        notifyOnSelectionChanged(selectedTabIndex, indexOfOrThrowException, tab, setSwitcherShown(false));
    }

    public final Iterator<Tab> iterator() {
        return this.tabs.iterator();
    }

    public final void setPadding(int i, int i2, int i3, int i4) {
        this.padding = new int[]{i, i2, i3, i4};
        notifyOnPaddingChanged(i, i2, i3, i4);
    }

    public final int getPaddingLeft() {
        return this.padding[0];
    }

    public final int getPaddingTop() {
        return this.padding[1];
    }

    public final int getPaddingRight() {
        return this.padding[2];
    }

    public final int getPaddingBottom() {
        return this.padding[3];
    }

    public final int getPaddingStart() {
        if (Build.VERSION.SDK_INT >= 17) {
            return this.tabSwitcher.getLayoutDirection() == 1 ? getPaddingRight() : getPaddingLeft();
        }
        return getPaddingLeft();
    }

    public final int getPaddingEnd() {
        if (Build.VERSION.SDK_INT >= 17) {
            return this.tabSwitcher.getLayoutDirection() == 1 ? getPaddingLeft() : getPaddingRight();
        }
        return getPaddingRight();
    }

    public final Drawable getTabIcon() {
        if (this.tabIconId != -1) {
            return ContextCompat.getDrawable(getContext(), this.tabIconId);
        }
        if (this.tabIconBitmap != null) {
            return new BitmapDrawable(getContext().getResources(), this.tabIconBitmap);
        }
        return null;
    }

    public final void setTabIcon(int i) {
        this.tabIconId = i;
        this.tabIconBitmap = null;
        notifyOnTabIconChanged(getTabIcon());
    }

    public final void setTabIcon(Bitmap bitmap) {
        this.tabIconId = -1;
        this.tabIconBitmap = bitmap;
        notifyOnTabIconChanged(getTabIcon());
    }

    public final ColorStateList getTabBackgroundColor() {
        return this.tabBackgroundColor;
    }

    public final void setTabBackgroundColor(int i) {
        setTabBackgroundColor(i != -1 ? ColorStateList.valueOf(i) : null);
    }

    public final void setTabBackgroundColor(ColorStateList colorStateList) {
        this.tabBackgroundColor = colorStateList;
        notifyOnTabBackgroundColorChanged(colorStateList);
    }

    public final ColorStateList getTabTitleTextColor() {
        return this.tabTitleTextColor;
    }

    public final void setTabTitleTextColor(int i) {
        setTabTitleTextColor(i != -1 ? ColorStateList.valueOf(i) : null);
    }

    public final void setTabTitleTextColor(ColorStateList colorStateList) {
        this.tabTitleTextColor = colorStateList;
        notifyOnTabTitleColorChanged(colorStateList);
    }

    public final Drawable getTabCloseButtonIcon() {
        if (this.tabCloseButtonIconId != -1) {
            return ContextCompat.getDrawable(getContext(), this.tabCloseButtonIconId);
        }
        if (this.tabCloseButtonIconBitmap != null) {
            return new BitmapDrawable(getContext().getResources(), this.tabCloseButtonIconBitmap);
        }
        return null;
    }

    public final void setTabCloseButtonIcon(int i) {
        this.tabCloseButtonIconId = i;
        this.tabCloseButtonIconBitmap = null;
        notifyOnTabCloseButtonIconChanged(getTabCloseButtonIcon());
    }

    public final void setTabCloseButtonIcon(Bitmap bitmap) {
        this.tabCloseButtonIconId = -1;
        this.tabCloseButtonIconBitmap = bitmap;
        notifyOnTabCloseButtonIconChanged(getTabCloseButtonIcon());
    }

    public final boolean areToolbarsShown() {
        return this.showToolbars;
    }

    public final void showToolbars(boolean z) {
        this.showToolbars = z;
        notifyOnToolbarVisibilityChanged(z);
    }

    public final CharSequence getToolbarTitle() {
        return this.toolbarTitle;
    }

    public void setToolbarTitle(int i) {
        setToolbarTitle(getContext().getText(i));
    }

    public final void setToolbarTitle(CharSequence charSequence) {
        this.toolbarTitle = charSequence;
        notifyOnToolbarTitleChanged(charSequence);
    }

    public final Drawable getToolbarNavigationIcon() {
        return this.toolbarNavigationIcon;
    }

    public final void setToolbarNavigationIcon(int i, View.OnClickListener onClickListener) {
        setToolbarNavigationIcon(ContextCompat.getDrawable(getContext(), i), onClickListener);
    }

    public final void setToolbarNavigationIcon(Drawable drawable, View.OnClickListener onClickListener) {
        this.toolbarNavigationIcon = drawable;
        this.toolbarNavigationIconListener = onClickListener;
        notifyOnToolbarNavigationIconChanged(drawable, onClickListener);
    }

    public final void inflateToolbarMenu(int i, Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
        this.toolbarMenuId = i;
        this.toolbarMenuItemListener = onMenuItemClickListener;
        notifyOnToolbarMenuInflated(i, onMenuItemClickListener);
    }

    public final void addCloseTabListener(TabCloseListener tabCloseListener) {
        Condition.ensureNotNull(tabCloseListener, "The listener may not be null");
        this.tabCloseListeners.add(tabCloseListener);
    }

    public final void removeCloseTabListener(TabCloseListener tabCloseListener) {
        Condition.ensureNotNull(tabCloseListener, "The listener may not be null");
        this.tabCloseListeners.remove(tabCloseListener);
    }

    public final void addTabPreviewListener(TabPreviewListener tabPreviewListener) {
        Condition.ensureNotNull(tabPreviewListener, "The listener may not be null");
        this.tabPreviewListeners.add(tabPreviewListener);
    }

    public final void removeTabPreviewListener(TabPreviewListener tabPreviewListener) {
        Condition.ensureNotNull(tabPreviewListener, "The listener may not be null");
        this.tabPreviewListeners.remove(tabPreviewListener);
    }

    public final void saveInstanceState(Bundle bundle) {
        bundle.putSerializable(LOG_LEVEL_EXTRA, this.logLevel);
        bundle.putParcelableArrayList(TABS_EXTRA, this.tabs);
        bundle.putBoolean(SWITCHER_SHOWN_EXTRA, this.switcherShown);
        bundle.putParcelable(SELECTED_TAB_EXTRA, this.selectedTab);
        bundle.putIntArray(PADDING_EXTRA, this.padding);
        bundle.putInt(TAB_ICON_ID_EXTRA, this.tabIconId);
        bundle.putParcelable(TAB_ICON_BITMAP_EXTRA, this.tabIconBitmap);
        bundle.putParcelable(TAB_BACKGROUND_COLOR_EXTRA, this.tabBackgroundColor);
        bundle.putParcelable(TAB_TITLE_TEXT_COLOR_EXTRA, this.tabTitleTextColor);
        bundle.putInt(TAB_CLOSE_BUTTON_ICON_ID_EXTRA, this.tabCloseButtonIconId);
        bundle.putParcelable(TAB_CLOSE_BUTTON_ICON_BITMAP_EXTRA, this.tabCloseButtonIconBitmap);
        bundle.putBoolean(SHOW_TOOLBARS_EXTRA, this.showToolbars);
        bundle.putCharSequence(TOOLBAR_TITLE_EXTRA, this.toolbarTitle);
        this.childRecyclerAdapter.saveInstanceState(bundle);
    }

    public final void restoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.firstVisibleTabIndex = bundle.getInt(FIRST_VISIBLE_TAB_INDEX_EXTRA, -1);
            this.firstVisibleTabPosition = bundle.getFloat(FIRST_VISIBLE_TAB_POSITION_EXTRA, -1.0f);
            this.logLevel = (LogLevel) bundle.getSerializable(LOG_LEVEL_EXTRA);
            this.tabs = bundle.getParcelableArrayList(TABS_EXTRA);
            this.switcherShown = bundle.getBoolean(SWITCHER_SHOWN_EXTRA);
            this.selectedTab = (Tab) bundle.getParcelable(SELECTED_TAB_EXTRA);
            this.padding = bundle.getIntArray(PADDING_EXTRA);
            this.tabIconId = bundle.getInt(TAB_ICON_ID_EXTRA);
            this.tabIconBitmap = (Bitmap) bundle.getParcelable(TAB_ICON_BITMAP_EXTRA);
            this.tabBackgroundColor = (ColorStateList) bundle.getParcelable(TAB_BACKGROUND_COLOR_EXTRA);
            this.tabTitleTextColor = (ColorStateList) bundle.getParcelable(TAB_TITLE_TEXT_COLOR_EXTRA);
            this.tabCloseButtonIconId = bundle.getInt(TAB_CLOSE_BUTTON_ICON_ID_EXTRA);
            this.tabCloseButtonIconBitmap = (Bitmap) bundle.getParcelable(TAB_CLOSE_BUTTON_ICON_BITMAP_EXTRA);
            this.showToolbars = bundle.getBoolean(SHOW_TOOLBARS_EXTRA);
            this.toolbarTitle = bundle.getCharSequence(TOOLBAR_TITLE_EXTRA);
            this.childRecyclerAdapter.restoreInstanceState(bundle);
        }
    }
}
