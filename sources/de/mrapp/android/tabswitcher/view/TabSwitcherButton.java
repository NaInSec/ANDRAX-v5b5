package de.mrapp.android.tabswitcher.view;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import de.mrapp.android.tabswitcher.Animation;
import de.mrapp.android.tabswitcher.R;
import de.mrapp.android.tabswitcher.Tab;
import de.mrapp.android.tabswitcher.TabSwitcher;
import de.mrapp.android.tabswitcher.TabSwitcherListener;
import de.mrapp.android.tabswitcher.drawable.TabSwitcherDrawable;
import de.mrapp.android.util.ThemeUtil;
import de.mrapp.android.util.ViewUtil;

public class TabSwitcherButton extends AppCompatImageButton implements TabSwitcherListener {
    private TabSwitcherDrawable drawable;

    private void initialize() {
        this.drawable = new TabSwitcherDrawable(getContext());
        setImageDrawable(this.drawable);
        ViewUtil.setBackground(this, ThemeUtil.getDrawable(getContext(), R.attr.selectableItemBackgroundBorderless));
        setContentDescription((CharSequence) null);
        setClickable(true);
        setFocusable(true);
    }

    public TabSwitcherButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public TabSwitcherButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize();
    }

    public TabSwitcherButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize();
    }

    public final void setCount(int i) {
        this.drawable.setCount(i);
    }

    public final void onSwitcherShown(TabSwitcher tabSwitcher) {
        this.drawable.onSwitcherShown(tabSwitcher);
    }

    public final void onSwitcherHidden(TabSwitcher tabSwitcher) {
        this.drawable.onSwitcherHidden(tabSwitcher);
    }

    public final void onSelectionChanged(TabSwitcher tabSwitcher, int i, Tab tab) {
        this.drawable.onSelectionChanged(tabSwitcher, i, tab);
    }

    public final void onTabAdded(TabSwitcher tabSwitcher, int i, Tab tab, Animation animation) {
        this.drawable.onTabAdded(tabSwitcher, i, tab, animation);
    }

    public final void onTabRemoved(TabSwitcher tabSwitcher, int i, Tab tab, Animation animation) {
        this.drawable.onTabRemoved(tabSwitcher, i, tab, animation);
    }

    public final void onAllTabsRemoved(TabSwitcher tabSwitcher, Tab[] tabArr, Animation animation) {
        this.drawable.onAllTabsRemoved(tabSwitcher, tabArr, animation);
    }
}
