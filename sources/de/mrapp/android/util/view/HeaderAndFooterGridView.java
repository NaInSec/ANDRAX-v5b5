package de.mrapp.android.util.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;
import com.thecrackertechnology.dragonterminal.backend.KeyHandler;
import de.mrapp.android.util.Condition;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HeaderAndFooterGridView extends GridView {
    private AdapterWrapper adapter;
    /* access modifiers changed from: private */
    public final List<FullWidthItem> footers = new ArrayList();
    /* access modifiers changed from: private */
    public final List<FullWidthItem> headers = new ArrayList();

    protected class FullWidthContainer extends FrameLayout {
        public FullWidthContainer(View view) {
            super(view.getContext());
            addView(view);
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec((HeaderAndFooterGridView.this.getMeasuredWidth() - HeaderAndFooterGridView.this.getPaddingLeft()) - HeaderAndFooterGridView.this.getPaddingRight(), View.MeasureSpec.getMode(i)), i2);
        }
    }

    protected class PlaceholderView extends View {
        public PlaceholderView(Context context) {
            super(context);
            setVisibility(4);
        }
    }

    private class FullWidthItem {
        /* access modifiers changed from: private */
        public final Object data;
        /* access modifiers changed from: private */
        public final boolean selectable;
        /* access modifiers changed from: private */
        public final FullWidthContainer view;

        public FullWidthItem(View view2, Object obj, boolean z) {
            Condition.ensureNotNull(view2, "The view may not be null");
            this.view = new FullWidthContainer(view2);
            this.data = obj;
            this.selectable = z;
        }
    }

    private class AdapterWrapper extends BaseAdapter implements WrapperListAdapter, Filterable {
        private final ListAdapter encapsulatedAdapter;

        public long getItemId(int i) {
            return (long) i;
        }

        private DataSetObserver createDataSetObserver() {
            return new DataSetObserver() {
                public void onChanged() {
                    AdapterWrapper.this.notifyDataSetChanged();
                }

                public void onInvalidated() {
                    AdapterWrapper.this.notifyDataSetInvalidated();
                }
            };
        }

        public AdapterWrapper(ListAdapter listAdapter) {
            Condition.ensureNotNull(listAdapter, "The adapter may not be null");
            this.encapsulatedAdapter = listAdapter;
            this.encapsulatedAdapter.registerDataSetObserver(createDataSetObserver());
        }

        public ListAdapter getEncapsulatedAdapter() {
            return this.encapsulatedAdapter;
        }

        public int getCount() {
            int numColumnsCompatible = HeaderAndFooterGridView.this.getNumColumnsCompatible();
            return ((HeaderAndFooterGridView.this.getHeaderViewsCount() + HeaderAndFooterGridView.this.getFooterViewsCount()) * numColumnsCompatible) + this.encapsulatedAdapter.getCount() + HeaderAndFooterGridView.this.getNumberOfPlaceholderViews();
        }

        public Object getItem(int i) {
            int numColumnsCompatible = HeaderAndFooterGridView.this.getNumColumnsCompatible();
            int headerViewsCount = HeaderAndFooterGridView.this.getHeaderViewsCount() * numColumnsCompatible;
            int count = this.encapsulatedAdapter.getCount();
            if (i < headerViewsCount) {
                if (i % numColumnsCompatible == 0) {
                    return ((FullWidthItem) HeaderAndFooterGridView.this.headers.get(i / numColumnsCompatible)).data;
                }
                return null;
            } else if (i < HeaderAndFooterGridView.this.getHeaderViewsCount() + count + HeaderAndFooterGridView.this.getNumberOfPlaceholderViews()) {
                if (i < count + headerViewsCount) {
                    return this.encapsulatedAdapter.getItem(i - headerViewsCount);
                }
                return null;
            } else if (i % numColumnsCompatible == 0) {
                return ((FullWidthItem) HeaderAndFooterGridView.this.footers.get((((i - headerViewsCount) - count) - HeaderAndFooterGridView.this.getNumberOfPlaceholderViews()) / numColumnsCompatible)).data;
            } else {
                return null;
            }
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            int numColumnsCompatible = HeaderAndFooterGridView.this.getNumColumnsCompatible();
            int headerViewsCount = HeaderAndFooterGridView.this.getHeaderViewsCount() * numColumnsCompatible;
            int count = this.encapsulatedAdapter.getCount();
            if (i < headerViewsCount) {
                FullWidthContainer access$400 = ((FullWidthItem) HeaderAndFooterGridView.this.headers.get(i / numColumnsCompatible)).view;
                if (i % numColumnsCompatible == 0) {
                    return access$400;
                }
                return HeaderAndFooterGridView.this.inflatePlaceholderView(view, access$400.getHeight());
            }
            int i2 = headerViewsCount + count;
            if (i >= HeaderAndFooterGridView.this.getNumberOfPlaceholderViews() + i2) {
                FullWidthContainer access$4002 = ((FullWidthItem) HeaderAndFooterGridView.this.footers.get((((i - headerViewsCount) - count) - HeaderAndFooterGridView.this.getNumberOfPlaceholderViews()) / numColumnsCompatible)).view;
                if (i % numColumnsCompatible == 0) {
                    return access$4002;
                }
                return HeaderAndFooterGridView.this.inflatePlaceholderView(view, access$4002.getHeight());
            } else if (i < i2) {
                ListAdapter listAdapter = this.encapsulatedAdapter;
                int i3 = i - headerViewsCount;
                if ((view instanceof FullWidthContainer) || (view instanceof PlaceholderView)) {
                    view = null;
                }
                return listAdapter.getView(i3, view, viewGroup);
            } else {
                HeaderAndFooterGridView headerAndFooterGridView = HeaderAndFooterGridView.this;
                return headerAndFooterGridView.inflatePlaceholderView(view, headerAndFooterGridView.getViewHeight(this, i - 1));
            }
        }

        public boolean isEnabled(int i) {
            int numColumnsCompatible = HeaderAndFooterGridView.this.getNumColumnsCompatible();
            int headerViewsCount = HeaderAndFooterGridView.this.getHeaderViewsCount() * numColumnsCompatible;
            int count = this.encapsulatedAdapter.getCount();
            if (i >= headerViewsCount) {
                int i2 = headerViewsCount + count;
                if (i < HeaderAndFooterGridView.this.getNumberOfPlaceholderViews() + i2) {
                    if (i >= i2 || !this.encapsulatedAdapter.isEnabled(i - headerViewsCount)) {
                        return false;
                    }
                    return true;
                } else if (i % numColumnsCompatible != 0 || !((FullWidthItem) HeaderAndFooterGridView.this.footers.get((((i - headerViewsCount) - count) - HeaderAndFooterGridView.this.getNumberOfPlaceholderViews()) / numColumnsCompatible)).selectable) {
                    return false;
                } else {
                    return true;
                }
            } else if (i % numColumnsCompatible != 0 || !((FullWidthItem) HeaderAndFooterGridView.this.headers.get(i / numColumnsCompatible)).selectable) {
                return false;
            } else {
                return true;
            }
        }

        public boolean areAllItemsEnabled() {
            boolean areAllItemsEnabled = this.encapsulatedAdapter.areAllItemsEnabled();
            for (FullWidthItem access$500 : HeaderAndFooterGridView.this.headers) {
                areAllItemsEnabled &= access$500.selectable;
            }
            for (FullWidthItem access$5002 : HeaderAndFooterGridView.this.footers) {
                areAllItemsEnabled &= access$5002.selectable;
            }
            return areAllItemsEnabled;
        }

        public boolean hasStableIds() {
            return this.encapsulatedAdapter.hasStableIds();
        }

        public ListAdapter getWrappedAdapter() {
            return this.encapsulatedAdapter;
        }

        public Filter getFilter() {
            ListAdapter listAdapter = this.encapsulatedAdapter;
            if (listAdapter instanceof Filterable) {
                return ((Filterable) listAdapter).getFilter();
            }
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final View inflatePlaceholderView(View view, int i) {
        if (!(view instanceof PlaceholderView)) {
            view = new PlaceholderView(getContext());
        }
        view.setMinimumHeight(i);
        return view;
    }

    /* access modifiers changed from: protected */
    public final int getViewHeight(ListAdapter listAdapter, int i) {
        View view = listAdapter.getView(i, (View) null, this);
        AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new AbsListView.LayoutParams(-1, -2);
            view.setLayoutParams(layoutParams);
        }
        view.measure(getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(getColumnWidthCompatible(), KeyHandler.KEYMOD_CTRL), 0, layoutParams.width), getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), 0, layoutParams.height));
        return view.getMeasuredHeight();
    }

    /* access modifiers changed from: protected */
    public final int getColumnWidthCompatible() {
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getColumnWidth();
        }
        try {
            Field declaredField = GridView.class.getDeclaredField("mColumnWidth");
            declaredField.setAccessible(true);
            return declaredField.getInt(this);
        } catch (Exception e) {
            throw new RuntimeException("Unable to retrieve column width", e);
        }
    }

    /* access modifiers changed from: protected */
    public final int getNumColumnsCompatible() {
        if (Build.VERSION.SDK_INT >= 11) {
            return super.getNumColumns();
        }
        try {
            Field declaredField = GridView.class.getDeclaredField("mNumColumns");
            declaredField.setAccessible(true);
            return declaredField.getInt(this);
        } catch (Exception e) {
            throw new RuntimeException("Unable to retrieve number of columns", e);
        }
    }

    private AdapterView.OnItemClickListener createItemClickListener(final AdapterView.OnItemClickListener onItemClickListener) {
        return new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                onItemClickListener.onItemClick(adapterView, view, HeaderAndFooterGridView.this.getItemPosition(i), j);
            }
        };
    }

    private AdapterView.OnItemLongClickListener createItemLongClickListener(final AdapterView.OnItemLongClickListener onItemLongClickListener) {
        return new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                return onItemLongClickListener.onItemLongClick(adapterView, view, HeaderAndFooterGridView.this.getItemPosition(i), j);
            }
        };
    }

    /* access modifiers changed from: private */
    public int getItemPosition(int i) {
        int numColumnsCompatible = getNumColumnsCompatible();
        int headerViewsCount = getHeaderViewsCount() * numColumnsCompatible;
        int count = this.adapter.getEncapsulatedAdapter().getCount();
        if (i < headerViewsCount) {
            return i / numColumnsCompatible;
        }
        if (i < headerViewsCount + count + getNumberOfPlaceholderViews()) {
            return (i - headerViewsCount) + getHeaderViewsCount();
        }
        return getHeaderViewsCount() + count + ((((i - headerViewsCount) - count) - getNumberOfPlaceholderViews()) / numColumnsCompatible);
    }

    /* access modifiers changed from: private */
    public int getNumberOfPlaceholderViews() {
        int numColumnsCompatible = getNumColumnsCompatible();
        int count = this.adapter.getEncapsulatedAdapter().getCount() % numColumnsCompatible;
        if (count > 0) {
            return numColumnsCompatible - count;
        }
        return 0;
    }

    private void notifyDataSetChanged() {
        AdapterWrapper adapterWrapper = this.adapter;
        if (adapterWrapper != null) {
            adapterWrapper.notifyDataSetChanged();
        }
    }

    public HeaderAndFooterGridView(Context context) {
        super(context);
    }

    public HeaderAndFooterGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HeaderAndFooterGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public HeaderAndFooterGridView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public final void addHeaderView(View view) {
        addHeaderView(view, (Object) null, true);
    }

    public final void addHeaderView(View view, Object obj, boolean z) {
        Condition.ensureNotNull(view, "The view may not be null");
        this.headers.add(new FullWidthItem(view, obj, z));
        notifyDataSetChanged();
    }

    public final void removeHeaderView(View view) {
        Condition.ensureNotNull(view, "The view may not be null");
        for (int headerViewsCount = getHeaderViewsCount() - 1; headerViewsCount >= 0; headerViewsCount--) {
            if (this.headers.get(headerViewsCount).view.getChildAt(0) == view) {
                this.headers.remove(headerViewsCount);
                notifyDataSetChanged();
                return;
            }
        }
    }

    public final int getHeaderViewsCount() {
        return this.headers.size();
    }

    public final void addFooterView(View view) {
        addFooterView(view, (Object) null, true);
    }

    public final void addFooterView(View view, Object obj, boolean z) {
        Condition.ensureNotNull(view, "The view may not be null");
        this.footers.add(new FullWidthItem(view, obj, z));
        notifyDataSetChanged();
    }

    public final void removeFooterView(View view) {
        Condition.ensureNotNull(view, "The view may not be null");
        for (int footerViewsCount = getFooterViewsCount() - 1; footerViewsCount >= 0; footerViewsCount--) {
            if (this.footers.get(footerViewsCount).view.getChildAt(0) == view) {
                this.footers.remove(footerViewsCount);
                notifyDataSetChanged();
                return;
            }
        }
    }

    public final int getFooterViewsCount() {
        return this.footers.size();
    }

    public final void setClipChildren(boolean z) {
        if (z) {
            super.setClipChildren(false);
            return;
        }
        throw new IllegalArgumentException("Header and footer views require the GridView's children to not be clipped");
    }

    public final ListAdapter getAdapter() {
        AdapterWrapper adapterWrapper = this.adapter;
        if (adapterWrapper != null) {
            return adapterWrapper.getEncapsulatedAdapter();
        }
        return null;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (listAdapter != null) {
            this.adapter = new AdapterWrapper(listAdapter);
            super.setAdapter(this.adapter);
            return;
        }
        this.adapter = null;
        super.setAdapter((ListAdapter) null);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        super.setOnItemClickListener(onItemClickListener != null ? createItemClickListener(onItemClickListener) : null);
    }

    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener onItemLongClickListener) {
        super.setOnItemLongClickListener(onItemLongClickListener != null ? createItemLongClickListener(onItemLongClickListener) : null);
    }
}
