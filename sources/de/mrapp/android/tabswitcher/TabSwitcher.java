package de.mrapp.android.tabswitcher;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import de.mrapp.android.tabswitcher.layout.AbstractTabSwitcherLayout;
import de.mrapp.android.tabswitcher.layout.TabSwitcherLayout;
import de.mrapp.android.tabswitcher.layout.phone.PhoneArithmetics;
import de.mrapp.android.tabswitcher.layout.phone.PhoneTabSwitcherLayout;
import de.mrapp.android.tabswitcher.model.Model;
import de.mrapp.android.tabswitcher.model.TabSwitcherModel;
import de.mrapp.android.tabswitcher.view.TabSwitcherButton;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.DisplayUtil;
import de.mrapp.android.util.ViewUtil;
import de.mrapp.android.util.logging.LogLevel;
import de.mrapp.android.util.view.AbstractSavedState;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class TabSwitcher extends FrameLayout implements TabSwitcherLayout, Model {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    /* access modifiers changed from: private */
    public AbstractTabSwitcherLayout layout;
    private LayoutPolicy layoutPolicy;
    private Set<TabSwitcherListener> listeners;
    /* access modifiers changed from: private */
    public TabSwitcherModel model;
    private Queue<Runnable> pendingActions;

    private static class TabSwitcherState extends AbstractSavedState {
        public static Parcelable.Creator<TabSwitcherState> CREATOR = new Parcelable.Creator<TabSwitcherState>() {
            public TabSwitcherState createFromParcel(Parcel parcel) {
                return new TabSwitcherState(parcel);
            }

            public TabSwitcherState[] newArray(int i) {
                return new TabSwitcherState[i];
            }
        };
        /* access modifiers changed from: private */
        public LayoutPolicy layoutPolicy;
        /* access modifiers changed from: private */
        public Bundle modelState;

        private TabSwitcherState(Parcel parcel) {
            super(parcel);
            this.layoutPolicy = (LayoutPolicy) parcel.readSerializable();
            this.modelState = parcel.readBundle(getClass().getClassLoader());
        }

        TabSwitcherState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeSerializable(this.layoutPolicy);
            parcel.writeBundle(this.modelState);
        }
    }

    private void initialize(AttributeSet attributeSet, int i, int i2) {
        this.pendingActions = new LinkedList();
        this.listeners = new CopyOnWriteArraySet(new LinkedHashSet());
        this.model = new TabSwitcherModel(this);
        this.model.addListener(createModelListener());
        getViewTreeObserver().addOnGlobalLayoutListener(new AbstractTabSwitcherLayout.LayoutListenerWrapper(this, createGlobalLayoutListener(false)));
        setPadding(super.getPaddingLeft(), super.getPaddingTop(), super.getPaddingRight(), super.getPaddingBottom());
        obtainStyledAttributes(attributeSet, i, i2);
    }

    /* access modifiers changed from: private */
    public void initializeLayout(Layout layout2, boolean z) {
        if (layout2 == Layout.TABLET) {
            this.layout = new PhoneTabSwitcherLayout(this, this.model, new PhoneArithmetics(this));
        } else {
            this.layout = new PhoneTabSwitcherLayout(this, this.model, new PhoneArithmetics(this));
        }
        this.layout.setCallback(createLayoutCallback());
        this.model.addListener(this.layout);
        this.layout.inflateLayout(z);
        final ViewGroup tabContainer = getTabContainer();
        if (ViewCompat.isLaidOut(tabContainer)) {
            this.layout.onGlobalLayout();
        } else {
            tabContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    ViewUtil.removeOnGlobalLayoutListener(tabContainer.getViewTreeObserver(), this);
                    if (TabSwitcher.this.layout != null) {
                        TabSwitcher.this.layout.onGlobalLayout();
                    }
                }
            });
        }
    }

    private void obtainStyledAttributes(AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.TabSwitcher, i, i2);
        try {
            obtainLayoutPolicy(obtainStyledAttributes);
            obtainBackground(obtainStyledAttributes);
            obtainTabIcon(obtainStyledAttributes);
            obtainTabBackgroundColor(obtainStyledAttributes);
            obtainTabTitleTextColor(obtainStyledAttributes);
            obtainTabCloseButtonIcon(obtainStyledAttributes);
            obtainToolbarTitle(obtainStyledAttributes);
            obtainToolbarNavigationIcon(obtainStyledAttributes);
            obtainToolbarMenu(obtainStyledAttributes);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void obtainLayoutPolicy(TypedArray typedArray) {
        setLayoutPolicy(LayoutPolicy.fromValue(typedArray.getInt(R.styleable.TabSwitcher_layoutPolicy, LayoutPolicy.AUTO.getValue())));
    }

    private void obtainBackground(TypedArray typedArray) {
        int resourceId = typedArray.getResourceId(R.styleable.TabSwitcher_android_background, 0);
        if (resourceId != 0) {
            ViewUtil.setBackground(this, ContextCompat.getDrawable(getContext(), resourceId));
            return;
        }
        setBackgroundColor(typedArray.getColor(R.styleable.TabSwitcher_android_background, ContextCompat.getColor(getContext(), R.color.tab_switcher_background_color)));
    }

    private void obtainTabIcon(TypedArray typedArray) {
        int resourceId = typedArray.getResourceId(R.styleable.TabSwitcher_tabIcon, -1);
        if (resourceId != -1) {
            setTabIcon(resourceId);
        }
    }

    private void obtainTabBackgroundColor(TypedArray typedArray) {
        ColorStateList colorStateList = typedArray.getColorStateList(R.styleable.TabSwitcher_tabBackgroundColor);
        if (colorStateList != null) {
            setTabBackgroundColor(colorStateList);
        }
    }

    private void obtainTabTitleTextColor(TypedArray typedArray) {
        ColorStateList colorStateList = typedArray.getColorStateList(R.styleable.TabSwitcher_tabTitleTextColor);
        if (colorStateList != null) {
            setTabTitleTextColor(colorStateList);
        }
    }

    private void obtainTabCloseButtonIcon(TypedArray typedArray) {
        int resourceId = typedArray.getResourceId(R.styleable.TabSwitcher_tabCloseButtonIcon, -1);
        if (resourceId != -1) {
            setTabCloseButtonIcon(resourceId);
        }
    }

    private void obtainToolbarTitle(TypedArray typedArray) {
        CharSequence text = typedArray.getText(R.styleable.TabSwitcher_toolbarTitle);
        if (!TextUtils.isEmpty(text)) {
            setToolbarTitle(text);
        }
    }

    private void obtainToolbarNavigationIcon(TypedArray typedArray) {
        Drawable drawable = typedArray.getDrawable(R.styleable.TabSwitcher_toolbarNavigationIcon);
        if (drawable != null) {
            setToolbarNavigationIcon(drawable, (View.OnClickListener) null);
        }
    }

    private void obtainToolbarMenu(TypedArray typedArray) {
        int resourceId = typedArray.getResourceId(R.styleable.TabSwitcher_toolbarMenu, -1);
        if (resourceId != -1) {
            inflateToolbarMenu(resourceId, (Toolbar.OnMenuItemClickListener) null);
        }
    }

    private void enqueuePendingAction(Runnable runnable) {
        Condition.ensureNotNull(runnable, "The action may not be null");
        this.pendingActions.add(runnable);
        executePendingAction();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r2.pendingActions.poll();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executePendingAction() {
        /*
            r2 = this;
            boolean r0 = r2.isAnimationRunning()
            if (r0 != 0) goto L_0x0018
            java.util.Queue<java.lang.Runnable> r0 = r2.pendingActions
            java.lang.Object r0 = r0.poll()
            java.lang.Runnable r0 = (java.lang.Runnable) r0
            if (r0 == 0) goto L_0x0018
            de.mrapp.android.tabswitcher.TabSwitcher$2 r1 = new de.mrapp.android.tabswitcher.TabSwitcher$2
            r1.<init>(r0)
            r1.run()
        L_0x0018:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: de.mrapp.android.tabswitcher.TabSwitcher.executePendingAction():void");
    }

    private Model.Listener createModelListener() {
        return new Model.Listener() {
            public void onDecoratorChanged(TabSwitcherDecorator tabSwitcherDecorator) {
            }

            public void onLogLevelChanged(LogLevel logLevel) {
            }

            public void onPaddingChanged(int i, int i2, int i3, int i4) {
            }

            public void onTabBackgroundColorChanged(ColorStateList colorStateList) {
            }

            public void onTabCloseButtonIconChanged(Drawable drawable) {
            }

            public void onTabIconChanged(Drawable drawable) {
            }

            public void onTabTitleColorChanged(ColorStateList colorStateList) {
            }

            public void onToolbarMenuInflated(int i, Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
            }

            public void onToolbarNavigationIconChanged(Drawable drawable, View.OnClickListener onClickListener) {
            }

            public void onToolbarTitleChanged(CharSequence charSequence) {
            }

            public void onToolbarVisibilityChanged(boolean z) {
            }

            public void onSwitcherShown() {
                TabSwitcher.this.notifyOnSwitcherShown();
            }

            public void onSwitcherHidden() {
                TabSwitcher.this.notifyOnSwitcherHidden();
            }

            public void onSelectionChanged(int i, int i2, Tab tab, boolean z) {
                TabSwitcher.this.notifyOnSelectionChanged(i2, tab);
                if (z) {
                    TabSwitcher.this.notifyOnSwitcherHidden();
                }
            }

            public void onTabAdded(int i, Tab tab, int i2, int i3, boolean z, Animation animation) {
                TabSwitcher.this.notifyOnTabAdded(i, tab, animation);
                if (i2 != i3) {
                    TabSwitcher tabSwitcher = TabSwitcher.this;
                    tabSwitcher.notifyOnSelectionChanged(i3, i3 != -1 ? tabSwitcher.getTab(i3) : null);
                }
                if (z) {
                    TabSwitcher.this.notifyOnSwitcherHidden();
                }
            }

            public void onAllTabsAdded(int i, Tab[] tabArr, int i2, int i3, Animation animation) {
                for (Tab access$600 : tabArr) {
                    TabSwitcher.this.notifyOnTabAdded(i, access$600, animation);
                }
                if (i2 != i3) {
                    TabSwitcher tabSwitcher = TabSwitcher.this;
                    tabSwitcher.notifyOnSelectionChanged(i3, i3 != -1 ? tabSwitcher.getTab(i3) : null);
                }
            }

            public void onTabRemoved(int i, Tab tab, int i2, int i3, Animation animation) {
                TabSwitcher.this.notifyOnTabRemoved(i, tab, animation);
                if (i2 != i3) {
                    TabSwitcher tabSwitcher = TabSwitcher.this;
                    tabSwitcher.notifyOnSelectionChanged(i3, i3 != -1 ? tabSwitcher.getTab(i3) : null);
                }
            }

            public void onAllTabsRemoved(Tab[] tabArr, Animation animation) {
                TabSwitcher.this.notifyOnAllTabsRemoved(tabArr, animation);
                TabSwitcher.this.notifyOnSelectionChanged(-1, (Tab) null);
            }
        };
    }

    private AbstractTabSwitcherLayout.Callback createLayoutCallback() {
        return new AbstractTabSwitcherLayout.Callback() {
            public void onAnimationsEnded() {
                TabSwitcher.this.executePendingAction();
            }
        };
    }

    private ViewTreeObserver.OnGlobalLayoutListener createGlobalLayoutListener(final boolean z) {
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                Condition.ensureNotNull(TabSwitcher.this.getDecorator(), "No decorator has been set", IllegalStateException.class);
                TabSwitcher tabSwitcher = TabSwitcher.this;
                tabSwitcher.initializeLayout(tabSwitcher.getLayout(), z);
            }
        };
    }

    /* access modifiers changed from: private */
    public void notifyOnSwitcherShown() {
        for (TabSwitcherListener onSwitcherShown : this.listeners) {
            onSwitcherShown.onSwitcherShown(this);
        }
    }

    /* access modifiers changed from: private */
    public void notifyOnSwitcherHidden() {
        for (TabSwitcherListener onSwitcherHidden : this.listeners) {
            onSwitcherHidden.onSwitcherHidden(this);
        }
    }

    /* access modifiers changed from: private */
    public void notifyOnSelectionChanged(int i, Tab tab) {
        for (TabSwitcherListener onSelectionChanged : this.listeners) {
            onSelectionChanged.onSelectionChanged(this, i, tab);
        }
    }

    /* access modifiers changed from: private */
    public void notifyOnTabAdded(int i, Tab tab, Animation animation) {
        for (TabSwitcherListener onTabAdded : this.listeners) {
            onTabAdded.onTabAdded(this, i, tab, animation);
        }
    }

    /* access modifiers changed from: private */
    public void notifyOnTabRemoved(int i, Tab tab, Animation animation) {
        for (TabSwitcherListener onTabRemoved : this.listeners) {
            onTabRemoved.onTabRemoved(this, i, tab, animation);
        }
    }

    /* access modifiers changed from: private */
    public void notifyOnAllTabsRemoved(Tab[] tabArr, Animation animation) {
        for (TabSwitcherListener onAllTabsRemoved : this.listeners) {
            onAllTabsRemoved.onAllTabsRemoved(this, tabArr, animation);
        }
    }

    public TabSwitcher(Context context) {
        this(context, (AttributeSet) null);
    }

    public TabSwitcher(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(attributeSet, 0, 0);
    }

    public TabSwitcher(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(attributeSet, i, 0);
    }

    public TabSwitcher(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initialize(attributeSet, i, i2);
    }

    public static void setupWithMenu(TabSwitcher tabSwitcher, Menu menu, View.OnClickListener onClickListener) {
        Condition.ensureNotNull(tabSwitcher, "The tab switcher may not be null");
        Condition.ensureNotNull(menu, "The menu may not be null");
        for (int i = 0; i < menu.size(); i++) {
            View actionView = menu.getItem(i).getActionView();
            if (actionView instanceof TabSwitcherButton) {
                TabSwitcherButton tabSwitcherButton = (TabSwitcherButton) actionView;
                tabSwitcherButton.setOnClickListener(onClickListener);
                tabSwitcherButton.setCount(tabSwitcher.getCount());
                tabSwitcher.addListener(tabSwitcherButton);
            }
        }
    }

    public final void addListener(TabSwitcherListener tabSwitcherListener) {
        Condition.ensureNotNull(tabSwitcherListener, "The listener may not be null");
        this.listeners.add(tabSwitcherListener);
    }

    public final void removeListener(TabSwitcherListener tabSwitcherListener) {
        Condition.ensureNotNull(tabSwitcherListener, "The listener may not be null");
        this.listeners.remove(tabSwitcherListener);
    }

    public final LayoutPolicy getLayoutPolicy() {
        return this.layoutPolicy;
    }

    public final void setLayoutPolicy(LayoutPolicy layoutPolicy2) {
        Layout layout2;
        Condition.ensureNotNull(layoutPolicy2, "The layout policy may not be null");
        if (this.layoutPolicy != layoutPolicy2) {
            Layout layout3 = getLayout();
            this.layoutPolicy = layoutPolicy2;
            if (this.layout != null && layout3 != (layout2 = getLayout())) {
                this.layout.detachLayout(false);
                this.model.removeListener(this.layout);
                initializeLayout(layout2, false);
            }
        }
    }

    public final Layout getLayout() {
        if (this.layoutPolicy == LayoutPolicy.TABLET || (this.layoutPolicy == LayoutPolicy.AUTO && DisplayUtil.getDeviceType(getContext()) == DisplayUtil.DeviceType.TABLET)) {
            return Layout.TABLET;
        }
        return DisplayUtil.getOrientation(getContext()) == DisplayUtil.Orientation.LANDSCAPE ? Layout.PHONE_LANDSCAPE : Layout.PHONE_PORTRAIT;
    }

    public final void addTab(final Tab tab) {
        enqueuePendingAction(new Runnable() {
            public void run() {
                TabSwitcher.this.model.addTab(tab);
            }
        });
    }

    public final void addTab(final Tab tab, final int i) {
        enqueuePendingAction(new Runnable() {
            public void run() {
                TabSwitcher.this.model.addTab(tab, i);
            }
        });
    }

    public final void addTab(final Tab tab, final int i, final Animation animation) {
        enqueuePendingAction(new Runnable() {
            public void run() {
                TabSwitcher.this.model.addTab(tab, i, animation);
            }
        });
    }

    public final void addAllTabs(final Collection<? extends Tab> collection) {
        enqueuePendingAction(new Runnable() {
            public void run() {
                TabSwitcher.this.model.addAllTabs((Collection<? extends Tab>) collection);
            }
        });
    }

    public final void addAllTabs(final Collection<? extends Tab> collection, final int i) {
        enqueuePendingAction(new Runnable() {
            public void run() {
                TabSwitcher.this.model.addAllTabs((Collection<? extends Tab>) collection, i);
            }
        });
    }

    public final void addAllTabs(final Collection<? extends Tab> collection, final int i, final Animation animation) {
        enqueuePendingAction(new Runnable() {
            public void run() {
                TabSwitcher.this.model.addAllTabs((Collection<? extends Tab>) collection, i, animation);
            }
        });
    }

    public final void addAllTabs(final Tab[] tabArr) {
        enqueuePendingAction(new Runnable() {
            public void run() {
                TabSwitcher.this.model.addAllTabs(tabArr);
            }
        });
    }

    public final void addAllTabs(final Tab[] tabArr, final int i) {
        enqueuePendingAction(new Runnable() {
            public void run() {
                TabSwitcher.this.model.addAllTabs(tabArr, i);
            }
        });
    }

    public final void addAllTabs(final Tab[] tabArr, final int i, final Animation animation) {
        enqueuePendingAction(new Runnable() {
            public void run() {
                TabSwitcher.this.model.addAllTabs(tabArr, i, animation);
            }
        });
    }

    public final void removeTab(final Tab tab) {
        enqueuePendingAction(new Runnable() {
            public void run() {
                TabSwitcher.this.model.removeTab(tab);
            }
        });
    }

    public final void removeTab(final Tab tab, final Animation animation) {
        enqueuePendingAction(new Runnable() {
            public void run() {
                TabSwitcher.this.model.removeTab(tab, animation);
            }
        });
    }

    public final void clear() {
        enqueuePendingAction(new Runnable() {
            public void run() {
                TabSwitcher.this.model.clear();
            }
        });
    }

    public final void clear(final Animation animation) {
        enqueuePendingAction(new Runnable() {
            public void run() {
                TabSwitcher.this.model.clear(animation);
            }
        });
    }

    public final void selectTab(final Tab tab) {
        enqueuePendingAction(new Runnable() {
            public void run() {
                TabSwitcher.this.model.selectTab(tab);
            }
        });
    }

    public final Tab getSelectedTab() {
        return this.model.getSelectedTab();
    }

    public final int getSelectedTabIndex() {
        return this.model.getSelectedTabIndex();
    }

    public final Iterator<Tab> iterator() {
        return this.model.iterator();
    }

    public final boolean isEmpty() {
        return this.model.isEmpty();
    }

    public final int getCount() {
        return this.model.getCount();
    }

    public final Tab getTab(int i) {
        return this.model.getTab(i);
    }

    public final int indexOf(Tab tab) {
        return this.model.indexOf(tab);
    }

    public final boolean isSwitcherShown() {
        return this.model.isSwitcherShown();
    }

    public final void showSwitcher() {
        enqueuePendingAction(new Runnable() {
            public void run() {
                TabSwitcher.this.model.showSwitcher();
            }
        });
    }

    public final void hideSwitcher() {
        enqueuePendingAction(new Runnable() {
            public void run() {
                TabSwitcher.this.model.hideSwitcher();
            }
        });
    }

    public final void toggleSwitcherVisibility() {
        enqueuePendingAction(new Runnable() {
            public void run() {
                TabSwitcher.this.model.toggleSwitcherVisibility();
            }
        });
    }

    public final void setDecorator(TabSwitcherDecorator tabSwitcherDecorator) {
        this.model.setDecorator(tabSwitcherDecorator);
    }

    public final TabSwitcherDecorator getDecorator() {
        return this.model.getDecorator();
    }

    public final LogLevel getLogLevel() {
        return this.model.getLogLevel();
    }

    public final void setLogLevel(LogLevel logLevel) {
        this.model.setLogLevel(logLevel);
    }

    public final void setPadding(int i, int i2, int i3, int i4) {
        this.model.setPadding(i, i2, i3, i4);
    }

    public final int getPaddingLeft() {
        return this.model.getPaddingLeft();
    }

    public final int getPaddingTop() {
        return this.model.getPaddingTop();
    }

    public final int getPaddingRight() {
        return this.model.getPaddingRight();
    }

    public final int getPaddingBottom() {
        return this.model.getPaddingBottom();
    }

    public final int getPaddingStart() {
        return this.model.getPaddingStart();
    }

    public final int getPaddingEnd() {
        return this.model.getPaddingEnd();
    }

    public final Drawable getTabIcon() {
        return this.model.getTabIcon();
    }

    public final void setTabIcon(int i) {
        this.model.setTabIcon(i);
    }

    public final void setTabIcon(Bitmap bitmap) {
        this.model.setTabIcon(bitmap);
    }

    public final ColorStateList getTabBackgroundColor() {
        return this.model.getTabBackgroundColor();
    }

    public final void setTabBackgroundColor(int i) {
        this.model.setTabBackgroundColor(i);
    }

    public final void setTabBackgroundColor(ColorStateList colorStateList) {
        this.model.setTabBackgroundColor(colorStateList);
    }

    public final ColorStateList getTabTitleTextColor() {
        return this.model.getTabTitleTextColor();
    }

    public final void setTabTitleTextColor(int i) {
        this.model.setTabTitleTextColor(i);
    }

    public final void setTabTitleTextColor(ColorStateList colorStateList) {
        this.model.setTabTitleTextColor(colorStateList);
    }

    public final Drawable getTabCloseButtonIcon() {
        return this.model.getTabCloseButtonIcon();
    }

    public final void setTabCloseButtonIcon(int i) {
        this.model.setTabCloseButtonIcon(i);
    }

    public final void setTabCloseButtonIcon(Bitmap bitmap) {
        this.model.setTabCloseButtonIcon(bitmap);
    }

    public final boolean areToolbarsShown() {
        return this.model.areToolbarsShown();
    }

    public final void showToolbars(boolean z) {
        this.model.showToolbars(z);
    }

    public final CharSequence getToolbarTitle() {
        Toolbar[] toolbars = getToolbars();
        return toolbars != null ? toolbars[0].getTitle() : this.model.getToolbarTitle();
    }

    public final void setToolbarTitle(int i) {
        this.model.setToolbarTitle(i);
    }

    public final void setToolbarTitle(CharSequence charSequence) {
        this.model.setToolbarTitle(charSequence);
    }

    public final Drawable getToolbarNavigationIcon() {
        Toolbar[] toolbars = getToolbars();
        if (toolbars != null) {
            return toolbars[0].getNavigationIcon();
        }
        return this.model.getToolbarNavigationIcon();
    }

    public final void setToolbarNavigationIcon(Drawable drawable, View.OnClickListener onClickListener) {
        this.model.setToolbarNavigationIcon(drawable, onClickListener);
    }

    public final void setToolbarNavigationIcon(int i, View.OnClickListener onClickListener) {
        this.model.setToolbarNavigationIcon(i, onClickListener);
    }

    public final void inflateToolbarMenu(int i, Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
        this.model.inflateToolbarMenu(i, onMenuItemClickListener);
    }

    public final void addCloseTabListener(TabCloseListener tabCloseListener) {
        this.model.addCloseTabListener(tabCloseListener);
    }

    public final void removeCloseTabListener(TabCloseListener tabCloseListener) {
        this.model.removeCloseTabListener(tabCloseListener);
    }

    public final void addTabPreviewListener(TabPreviewListener tabPreviewListener) {
        this.model.addTabPreviewListener(tabPreviewListener);
    }

    public final void removeTabPreviewListener(TabPreviewListener tabPreviewListener) {
        this.model.removeTabPreviewListener(tabPreviewListener);
    }

    public final boolean isAnimationRunning() {
        AbstractTabSwitcherLayout abstractTabSwitcherLayout = this.layout;
        return abstractTabSwitcherLayout != null && abstractTabSwitcherLayout.isAnimationRunning();
    }

    public final ViewGroup getTabContainer() {
        AbstractTabSwitcherLayout abstractTabSwitcherLayout = this.layout;
        if (abstractTabSwitcherLayout != null) {
            return abstractTabSwitcherLayout.getTabContainer();
        }
        return null;
    }

    public final Toolbar[] getToolbars() {
        AbstractTabSwitcherLayout abstractTabSwitcherLayout = this.layout;
        if (abstractTabSwitcherLayout != null) {
            return abstractTabSwitcherLayout.getToolbars();
        }
        return null;
    }

    public final Menu getToolbarMenu() {
        AbstractTabSwitcherLayout abstractTabSwitcherLayout = this.layout;
        if (abstractTabSwitcherLayout != null) {
            return abstractTabSwitcherLayout.getToolbarMenu();
        }
        return null;
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        AbstractTabSwitcherLayout abstractTabSwitcherLayout = this.layout;
        return (abstractTabSwitcherLayout != null && abstractTabSwitcherLayout.handleTouchEvent(motionEvent)) || super.onTouchEvent(motionEvent);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x001e, code lost:
        r0 = r7.layout;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Parcelable onSaveInstanceState() {
        /*
            r7 = this;
            android.os.Parcelable r0 = super.onSaveInstanceState()
            de.mrapp.android.tabswitcher.TabSwitcher$TabSwitcherState r1 = new de.mrapp.android.tabswitcher.TabSwitcher$TabSwitcherState
            r1.<init>((android.os.Parcelable) r0)
            de.mrapp.android.tabswitcher.LayoutPolicy r0 = r7.layoutPolicy
            de.mrapp.android.tabswitcher.LayoutPolicy unused = r1.layoutPolicy = r0
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            android.os.Bundle unused = r1.modelState = r0
            int r0 = r7.getCount()
            r2 = 1
            r3 = 0
            if (r0 <= 0) goto L_0x0027
            de.mrapp.android.tabswitcher.layout.AbstractTabSwitcherLayout r0 = r7.layout
            if (r0 == 0) goto L_0x0027
            android.support.v4.util.Pair r0 = r0.detachLayout(r2)
            goto L_0x0028
        L_0x0027:
            r0 = r3
        L_0x0028:
            if (r0 == 0) goto L_0x0067
            android.os.Bundle r4 = r1.modelState
            java.lang.String r5 = de.mrapp.android.tabswitcher.model.TabSwitcherModel.FIRST_VISIBLE_TAB_INDEX_EXTRA
            F r6 = r0.first
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r4.putInt(r5, r6)
            android.os.Bundle r4 = r1.modelState
            java.lang.String r5 = de.mrapp.android.tabswitcher.model.TabSwitcherModel.FIRST_VISIBLE_TAB_POSITION_EXTRA
            S r6 = r0.second
            java.lang.Float r6 = (java.lang.Float) r6
            float r6 = r6.floatValue()
            r4.putFloat(r5, r6)
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r4 = r7.model
            F r5 = r0.first
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            r4.setFirstVisibleTabIndex(r5)
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r4 = r7.model
            S r0 = r0.second
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            r4.setFirstVisibleTabPosition(r0)
            goto L_0x0074
        L_0x0067:
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r0 = r7.model
            r4 = -1082130432(0xffffffffbf800000, float:-1.0)
            r0.setFirstVisibleTabPosition(r4)
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r0 = r7.model
            r4 = -1
            r0.setFirstVisibleTabIndex(r4)
        L_0x0074:
            de.mrapp.android.tabswitcher.layout.AbstractTabSwitcherLayout r0 = r7.layout
            if (r0 == 0) goto L_0x007f
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r4 = r7.model
            r4.removeListener(r0)
            r7.layout = r3
        L_0x007f:
            r7.executePendingAction()
            android.view.ViewTreeObserver r0 = r7.getViewTreeObserver()
            de.mrapp.android.tabswitcher.layout.AbstractTabSwitcherLayout$LayoutListenerWrapper r3 = new de.mrapp.android.tabswitcher.layout.AbstractTabSwitcherLayout$LayoutListenerWrapper
            android.view.ViewTreeObserver$OnGlobalLayoutListener r2 = r7.createGlobalLayoutListener(r2)
            r3.<init>(r7, r2)
            r0.addOnGlobalLayoutListener(r3)
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r0 = r7.model
            android.os.Bundle r2 = r1.modelState
            r0.saveInstanceState(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: de.mrapp.android.tabswitcher.TabSwitcher.onSaveInstanceState():android.os.Parcelable");
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof TabSwitcherState) {
            TabSwitcherState tabSwitcherState = (TabSwitcherState) parcelable;
            this.layoutPolicy = tabSwitcherState.layoutPolicy;
            this.model.restoreInstanceState(tabSwitcherState.modelState);
            super.onRestoreInstanceState(tabSwitcherState.getSuperState());
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }
}
