package de.mrapp.android.tabswitcher.layout.phone;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import de.mrapp.android.tabswitcher.Animation;
import de.mrapp.android.tabswitcher.R;
import de.mrapp.android.tabswitcher.Tab;
import de.mrapp.android.tabswitcher.TabCloseListener;
import de.mrapp.android.tabswitcher.TabPreviewListener;
import de.mrapp.android.tabswitcher.TabSwitcher;
import de.mrapp.android.tabswitcher.TabSwitcherDecorator;
import de.mrapp.android.tabswitcher.iterator.TabItemIterator;
import de.mrapp.android.tabswitcher.model.Model;
import de.mrapp.android.tabswitcher.model.TabItem;
import de.mrapp.android.tabswitcher.model.TabSwitcherModel;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.ViewUtil;
import de.mrapp.android.util.logging.LogLevel;
import de.mrapp.android.util.multithreading.AbstractDataBinder;
import de.mrapp.android.util.view.AbstractViewRecycler;
import de.mrapp.android.util.view.AttachedViewRecycler;
import de.mrapp.android.util.view.ViewRecycler;

public class PhoneRecyclerAdapter extends AbstractViewRecycler.Adapter<TabItem, Integer> implements Tab.Callback, Model.Listener, AbstractDataBinder.Listener<Bitmap, Tab, ImageView, TabItem> {
    private final ViewRecycler<Tab, Void> childViewRecycler;
    private final AbstractDataBinder<Bitmap, Tab, ImageView, TabItem> dataBinder;
    private final TabSwitcherModel model;
    private final int tabBackgroundColor;
    private final int tabBorderWidth;
    private final int tabInset;
    /* access modifiers changed from: private */
    public final TabSwitcher tabSwitcher;
    private final int tabTitleContainerHeight;
    private final int tabTitleTextColor;
    private AttachedViewRecycler<TabItem, Integer> viewRecycler = null;

    public final void onAllTabsRemoved(Tab[] tabArr, Animation animation) {
    }

    public final void onCanceled(AbstractDataBinder<Bitmap, Tab, ImageView, TabItem> abstractDataBinder) {
    }

    public final void onDecoratorChanged(TabSwitcherDecorator tabSwitcherDecorator) {
    }

    public final void onFinished(AbstractDataBinder<Bitmap, Tab, ImageView, TabItem> abstractDataBinder, Tab tab, Bitmap bitmap, ImageView imageView, TabItem... tabItemArr) {
    }

    public final void onSwitcherHidden() {
    }

    public final void onSwitcherShown() {
    }

    public final void onToolbarMenuInflated(int i, Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
    }

    public final void onToolbarNavigationIconChanged(Drawable drawable, View.OnClickListener onClickListener) {
    }

    public final void onToolbarTitleChanged(CharSequence charSequence) {
    }

    public final void onToolbarVisibilityChanged(boolean z) {
    }

    private void addChildView(TabItem tabItem) {
        PhoneTabViewHolder viewHolder = tabItem.getViewHolder();
        View view = viewHolder.child;
        Tab tab = tabItem.getTab();
        if (view == null) {
            ViewGroup viewGroup = viewHolder.childContainer;
            View view2 = (View) this.childViewRecycler.inflate(tab, viewGroup, (ParamType[]) new Void[0]).first;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            layoutParams.setMargins(this.model.getPaddingLeft(), this.model.getPaddingTop(), this.model.getPaddingRight(), this.model.getPaddingBottom());
            viewGroup.addView(view2, 0, layoutParams);
            viewHolder.child = view2;
        } else {
            this.childViewRecycler.getAdapter().onShowView(this.model.getContext(), view, tab, false, new Void[0]);
        }
        viewHolder.previewImageView.setVisibility(8);
        viewHolder.previewImageView.setImageBitmap((Bitmap) null);
        viewHolder.borderView.setVisibility(8);
    }

    private void renderChildView(TabItem tabItem) {
        Tab tab = tabItem.getTab();
        PhoneTabViewHolder viewHolder = tabItem.getViewHolder();
        viewHolder.borderView.setVisibility(0);
        if (viewHolder.child != null) {
            this.childViewRecycler.getAdapter().onRemoveView(viewHolder.child, tab);
            this.dataBinder.load(tab, viewHolder.previewImageView, false, tabItem);
            removeChildView(viewHolder, tab);
            return;
        }
        this.dataBinder.load(tab, viewHolder.previewImageView, tabItem);
    }

    private void removeChildView(PhoneTabViewHolder phoneTabViewHolder, Tab tab) {
        if (phoneTabViewHolder.childContainer.getChildCount() > 2) {
            phoneTabViewHolder.childContainer.removeViewAt(0);
        }
        phoneTabViewHolder.child = null;
        this.childViewRecycler.remove(tab);
    }

    private void adaptLogLevel() {
        this.dataBinder.setLogLevel(this.model.getLogLevel());
    }

    private void adaptTitle(PhoneTabViewHolder phoneTabViewHolder, Tab tab) {
        phoneTabViewHolder.titleTextView.setText(tab.getTitle());
    }

    private void adaptIcon(PhoneTabViewHolder phoneTabViewHolder, Tab tab) {
        Drawable icon = tab.getIcon(this.model.getContext());
        TextView textView = phoneTabViewHolder.titleTextView;
        if (icon == null) {
            icon = this.model.getTabIcon();
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(icon, (Drawable) null, (Drawable) null, (Drawable) null);
    }

    private void adaptCloseButton(PhoneTabViewHolder phoneTabViewHolder, Tab tab) {
        phoneTabViewHolder.closeButton.setVisibility(tab.isCloseable() ? 0 : 8);
        phoneTabViewHolder.closeButton.setOnClickListener(tab.isCloseable() ? createCloseButtonClickListener(phoneTabViewHolder.closeButton, tab) : null);
    }

    private void adaptCloseButtonIcon(PhoneTabViewHolder phoneTabViewHolder, Tab tab) {
        Drawable closeButtonIcon = tab.getCloseButtonIcon(this.model.getContext());
        if (closeButtonIcon == null) {
            closeButtonIcon = this.model.getTabCloseButtonIcon();
        }
        if (closeButtonIcon != null) {
            phoneTabViewHolder.closeButton.setImageDrawable(closeButtonIcon);
        } else {
            phoneTabViewHolder.closeButton.setImageResource(R.drawable.ic_close_tab_18dp);
        }
    }

    private View.OnClickListener createCloseButtonClickListener(final ImageButton imageButton, final Tab tab) {
        return new View.OnClickListener() {
            public void onClick(View view) {
                if (PhoneRecyclerAdapter.this.notifyOnCloseTab(tab)) {
                    imageButton.setOnClickListener((View.OnClickListener) null);
                    PhoneRecyclerAdapter.this.tabSwitcher.removeTab(tab);
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public boolean notifyOnCloseTab(Tab tab) {
        boolean z = true;
        for (TabCloseListener onCloseTab : this.model.getTabCloseListeners()) {
            z &= onCloseTab.onCloseTab(this.tabSwitcher, tab);
        }
        return z;
    }

    private void adaptBackgroundColor(View view, PhoneTabViewHolder phoneTabViewHolder, Tab tab) {
        ColorStateList colorStateList;
        if (tab.getBackgroundColor() != null) {
            colorStateList = tab.getBackgroundColor();
        } else {
            colorStateList = this.model.getTabBackgroundColor();
        }
        int i = this.tabBackgroundColor;
        if (colorStateList != null) {
            i = colorStateList.getColorForState(this.model.getSelectedTab() == tab ? new int[]{16842913} : new int[0], colorStateList.getDefaultColor());
        }
        view.getBackground().setColorFilter(i, PorterDuff.Mode.MULTIPLY);
        phoneTabViewHolder.borderView.getBackground().setColorFilter(i, PorterDuff.Mode.MULTIPLY);
    }

    private void adaptTitleTextColor(PhoneTabViewHolder phoneTabViewHolder, Tab tab) {
        ColorStateList colorStateList;
        if (tab.getTitleTextColor() != null) {
            colorStateList = tab.getTitleTextColor();
        } else {
            colorStateList = this.model.getTabTitleTextColor();
        }
        if (colorStateList != null) {
            phoneTabViewHolder.titleTextView.setTextColor(colorStateList);
        } else {
            phoneTabViewHolder.titleTextView.setTextColor(this.tabTitleTextColor);
        }
    }

    private void adaptSelectionState(PhoneTabViewHolder phoneTabViewHolder, Tab tab) {
        boolean z = this.model.getSelectedTab() == tab;
        phoneTabViewHolder.titleTextView.setSelected(z);
        phoneTabViewHolder.closeButton.setSelected(z);
    }

    private void adaptAllSelectionStates() {
        TabItemIterator create = new TabItemIterator.Builder(this.model, this.viewRecycler).create();
        while (true) {
            TabItem next = create.next();
            if (next == null) {
                return;
            }
            if (next.isInflated()) {
                Tab tab = next.getTab();
                PhoneTabViewHolder viewHolder = next.getViewHolder();
                adaptSelectionState(viewHolder, tab);
                adaptBackgroundColor(next.getView(), viewHolder, tab);
            }
        }
    }

    private void adaptPadding(PhoneTabViewHolder phoneTabViewHolder) {
        if (phoneTabViewHolder.child != null) {
            ((FrameLayout.LayoutParams) phoneTabViewHolder.child.getLayoutParams()).setMargins(this.model.getPaddingLeft(), this.model.getPaddingTop(), this.model.getPaddingRight(), this.model.getPaddingBottom());
        }
        ((FrameLayout.LayoutParams) phoneTabViewHolder.previewImageView.getLayoutParams()).setMargins(this.model.getPaddingLeft(), this.model.getPaddingTop(), this.model.getPaddingRight(), this.model.getPaddingBottom());
    }

    private TabItem getTabItem(Tab tab) {
        Condition.ensureNotNull(this.viewRecycler, "No view recycler has been set", IllegalStateException.class);
        int indexOf = this.model.indexOf(tab);
        if (indexOf == -1) {
            return null;
        }
        TabItem create = TabItem.create((Model) this.model, (AttachedViewRecycler<TabItem, ?>) this.viewRecycler, indexOf);
        if (create.isInflated()) {
            return create;
        }
        return null;
    }

    public PhoneRecyclerAdapter(TabSwitcher tabSwitcher2, TabSwitcherModel tabSwitcherModel, ViewRecycler<Tab, Void> viewRecycler2) {
        Condition.ensureNotNull(tabSwitcher2, "The tab switcher may not be null");
        Condition.ensureNotNull(tabSwitcherModel, "The model may not be null");
        Condition.ensureNotNull(viewRecycler2, "The child view recycler may not be null");
        this.tabSwitcher = tabSwitcher2;
        this.model = tabSwitcherModel;
        this.childViewRecycler = viewRecycler2;
        this.dataBinder = new PreviewDataBinder(tabSwitcher2, viewRecycler2);
        this.dataBinder.addListener(this);
        Resources resources = tabSwitcher2.getResources();
        this.tabInset = resources.getDimensionPixelSize(R.dimen.tab_inset);
        this.tabBorderWidth = resources.getDimensionPixelSize(R.dimen.tab_border_width);
        this.tabTitleContainerHeight = resources.getDimensionPixelSize(R.dimen.tab_title_container_height);
        this.tabBackgroundColor = ContextCompat.getColor(tabSwitcher2.getContext(), R.color.tab_background_color);
        this.tabTitleTextColor = ContextCompat.getColor(tabSwitcher2.getContext(), R.color.tab_title_text_color);
        adaptLogLevel();
    }

    public final void setViewRecycler(AttachedViewRecycler<TabItem, Integer> attachedViewRecycler) {
        Condition.ensureNotNull(attachedViewRecycler, "The view recycler may not be null");
        this.viewRecycler = attachedViewRecycler;
    }

    public final void clearCachedPreviews() {
        this.dataBinder.clearCache();
    }

    public final View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, TabItem tabItem, int i, Integer... numArr) {
        PhoneTabViewHolder phoneTabViewHolder = new PhoneTabViewHolder();
        View inflate = layoutInflater.inflate(R.layout.phone_tab, this.tabSwitcher.getTabContainer(), false);
        ViewUtil.setBackground(inflate, ContextCompat.getDrawable(this.model.getContext(), R.drawable.phone_tab_background));
        int i2 = this.tabInset;
        int i3 = this.tabBorderWidth + i2;
        inflate.setPadding(i3, i2, i3, i3);
        phoneTabViewHolder.titleContainer = (ViewGroup) inflate.findViewById(R.id.tab_title_container);
        phoneTabViewHolder.titleTextView = (TextView) inflate.findViewById(R.id.tab_title_text_view);
        phoneTabViewHolder.closeButton = (ImageButton) inflate.findViewById(R.id.close_tab_button);
        phoneTabViewHolder.childContainer = (ViewGroup) inflate.findViewById(R.id.child_container);
        phoneTabViewHolder.previewImageView = (ImageView) inflate.findViewById(R.id.preview_image_view);
        adaptPadding(phoneTabViewHolder);
        phoneTabViewHolder.borderView = inflate.findViewById(R.id.border_view);
        ViewUtil.setBackground(phoneTabViewHolder.borderView, ContextCompat.getDrawable(this.model.getContext(), R.drawable.phone_tab_border));
        inflate.setTag(R.id.tag_view_holder, phoneTabViewHolder);
        tabItem.setView(inflate);
        tabItem.setViewHolder(phoneTabViewHolder);
        inflate.setTag(R.id.tag_properties, tabItem.getTag());
        return inflate;
    }

    public final void onShowView(Context context, View view, TabItem tabItem, boolean z, Integer... numArr) {
        PhoneTabViewHolder phoneTabViewHolder = (PhoneTabViewHolder) view.getTag(R.id.tag_view_holder);
        if (!tabItem.isInflated()) {
            tabItem.setView(view);
            tabItem.setViewHolder(phoneTabViewHolder);
            view.setTag(R.id.tag_properties, tabItem.getTag());
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        int i = -(this.tabInset + this.tabBorderWidth);
        int intValue = (numArr.length <= 0 || numArr[0].intValue() == -1) ? i : numArr[0].intValue();
        layoutParams.leftMargin = i;
        layoutParams.topMargin = -(this.tabInset + this.tabTitleContainerHeight);
        layoutParams.rightMargin = i;
        layoutParams.bottomMargin = intValue;
        view.setLayoutParams(layoutParams);
        Tab tab = tabItem.getTab();
        tab.addCallback(this);
        adaptTitle(phoneTabViewHolder, tab);
        adaptIcon(phoneTabViewHolder, tab);
        adaptCloseButton(phoneTabViewHolder, tab);
        adaptCloseButtonIcon(phoneTabViewHolder, tab);
        adaptBackgroundColor(view, phoneTabViewHolder, tab);
        adaptTitleTextColor(phoneTabViewHolder, tab);
        adaptSelectionState(phoneTabViewHolder, tab);
        if (this.model.isSwitcherShown()) {
            renderChildView(tabItem);
        } else if (tab == this.model.getSelectedTab()) {
            addChildView(tabItem);
        }
    }

    public final void onRemoveView(View view, TabItem tabItem) {
        Bitmap bitmap;
        PhoneTabViewHolder phoneTabViewHolder = (PhoneTabViewHolder) view.getTag(R.id.tag_view_holder);
        Tab tab = tabItem.getTab();
        tab.removeCallback(this);
        removeChildView(phoneTabViewHolder, tab);
        if (!this.dataBinder.isCached(tab)) {
            Drawable drawable = phoneTabViewHolder.previewImageView.getDrawable();
            phoneTabViewHolder.previewImageView.setImageBitmap((Bitmap) null);
            if ((drawable instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) drawable).getBitmap()) != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        } else {
            phoneTabViewHolder.previewImageView.setImageBitmap((Bitmap) null);
        }
        view.setTag(R.id.tag_properties, (Object) null);
    }

    public final void onTitleChanged(Tab tab) {
        TabItem tabItem = getTabItem(tab);
        if (tabItem != null) {
            adaptTitle(tabItem.getViewHolder(), tabItem.getTab());
        }
    }

    public final void onIconChanged(Tab tab) {
        TabItem tabItem = getTabItem(tab);
        if (tabItem != null) {
            adaptIcon(tabItem.getViewHolder(), tabItem.getTab());
        }
    }

    public final void onCloseableChanged(Tab tab) {
        TabItem tabItem = getTabItem(tab);
        if (tabItem != null) {
            adaptCloseButton(tabItem.getViewHolder(), tabItem.getTab());
        }
    }

    public final void onCloseButtonIconChanged(Tab tab) {
        TabItem tabItem = getTabItem(tab);
        if (tabItem != null) {
            adaptCloseButtonIcon(tabItem.getViewHolder(), tabItem.getTab());
        }
    }

    public final void onBackgroundColorChanged(Tab tab) {
        TabItem tabItem = getTabItem(tab);
        if (tabItem != null) {
            adaptBackgroundColor(tabItem.getView(), tabItem.getViewHolder(), tabItem.getTab());
        }
    }

    public final void onTitleTextColorChanged(Tab tab) {
        TabItem tabItem = getTabItem(tab);
        if (tabItem != null) {
            adaptTitleTextColor(tabItem.getViewHolder(), tabItem.getTab());
        }
    }

    public final void onLogLevelChanged(LogLevel logLevel) {
        adaptLogLevel();
    }

    public final void onSelectionChanged(int i, int i2, Tab tab, boolean z) {
        adaptAllSelectionStates();
    }

    public final void onTabAdded(int i, Tab tab, int i2, int i3, boolean z, Animation animation) {
        if (i2 != i3) {
            adaptAllSelectionStates();
        }
    }

    public final void onAllTabsAdded(int i, Tab[] tabArr, int i2, int i3, Animation animation) {
        if (i2 != i3) {
            adaptAllSelectionStates();
        }
    }

    public final void onTabRemoved(int i, Tab tab, int i2, int i3, Animation animation) {
        if (i2 != i3) {
            adaptAllSelectionStates();
        }
    }

    public final void onPaddingChanged(int i, int i2, int i3, int i4) {
        TabItemIterator create = new TabItemIterator.Builder(this.model, this.viewRecycler).create();
        while (true) {
            TabItem next = create.next();
            if (next == null) {
                return;
            }
            if (next.isInflated()) {
                adaptPadding(next.getViewHolder());
            }
        }
    }

    public final void onTabIconChanged(Drawable drawable) {
        TabItemIterator create = new TabItemIterator.Builder(this.model, this.viewRecycler).create();
        while (true) {
            TabItem next = create.next();
            if (next == null) {
                return;
            }
            if (next.isInflated()) {
                adaptIcon(next.getViewHolder(), next.getTab());
            }
        }
    }

    public final void onTabBackgroundColorChanged(ColorStateList colorStateList) {
        TabItemIterator create = new TabItemIterator.Builder(this.model, this.viewRecycler).create();
        while (true) {
            TabItem next = create.next();
            if (next == null) {
                return;
            }
            if (next.isInflated()) {
                adaptBackgroundColor(next.getView(), next.getViewHolder(), next.getTab());
            }
        }
    }

    public final void onTabTitleColorChanged(ColorStateList colorStateList) {
        TabItemIterator create = new TabItemIterator.Builder(this.model, this.viewRecycler).create();
        while (true) {
            TabItem next = create.next();
            if (next == null) {
                return;
            }
            if (next.isInflated()) {
                adaptTitleTextColor(next.getViewHolder(), next.getTab());
            }
        }
    }

    public final void onTabCloseButtonIconChanged(Drawable drawable) {
        TabItemIterator create = new TabItemIterator.Builder(this.model, this.viewRecycler).create();
        while (true) {
            TabItem next = create.next();
            if (next == null) {
                return;
            }
            if (next.isInflated()) {
                adaptCloseButtonIcon(next.getViewHolder(), next.getTab());
            }
        }
    }

    public final boolean onLoadData(AbstractDataBinder<Bitmap, Tab, ImageView, TabItem> abstractDataBinder, Tab tab, TabItem... tabItemArr) {
        boolean z = true;
        for (TabPreviewListener onLoadTabPreview : this.model.getTabPreviewListeners()) {
            z &= onLoadTabPreview.onLoadTabPreview(this.tabSwitcher, tab);
        }
        return z;
    }
}
