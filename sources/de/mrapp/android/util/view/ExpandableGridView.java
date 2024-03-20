package de.mrapp.android.util.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.util.Pair;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ListAdapter;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.view.HeaderAndFooterGridView;
import java.util.HashSet;
import java.util.Set;

public class ExpandableGridView extends HeaderAndFooterGridView {
    private static final long PACKED_POSITION_INT_MASK_GROUP = 2147483647L;
    private static final long PACKED_POSITION_MASK_CHILD = 4294967295L;
    private static final long PACKED_POSITION_MASK_GROUP = 9223372032559808512L;
    private static final long PACKED_POSITION_MASK_TYPE = Long.MIN_VALUE;
    private static final long PACKED_POSITION_SHIFT_GROUP = 32;
    private static final long PACKED_POSITION_SHIFT_TYPE = 63;
    public static final int PACKED_POSITION_TYPE_CHILD = 1;
    public static final int PACKED_POSITION_TYPE_GROUP = 0;
    public static final int PACKED_POSITION_TYPE_NULL = 2;
    public static final long PACKED_POSITION_VALUE_NULL = 4294967295L;
    private AdapterWrapper adapter;
    private OnChildClickListener childClickListener;
    private Set<Integer> expandedGroups;
    private OnGroupClickListener groupClickListener;
    private AdapterView.OnItemClickListener itemClickListener;
    private AdapterView.OnItemLongClickListener itemLongClickListener;

    public interface OnChildClickListener {
        boolean onChildClick(ExpandableGridView expandableGridView, View view, int i, int i2, long j);
    }

    public interface OnGroupClickListener {
        boolean onGroupClick(ExpandableGridView expandableGridView, View view, int i, long j);
    }

    public static int getPackedPositionChild(long j) {
        if (j == 4294967295L || (j & Long.MIN_VALUE) != Long.MIN_VALUE) {
            return -1;
        }
        return (int) (j & 4294967295L);
    }

    public static long getPackedPositionForChild(int i, int i2) {
        return ((long) i2) | ((((long) i) & PACKED_POSITION_INT_MASK_GROUP) << 32) | Long.MIN_VALUE;
    }

    public static long getPackedPositionForGroup(int i) {
        return (((long) i) & PACKED_POSITION_INT_MASK_GROUP) << 32;
    }

    public static int getPackedPositionGroup(long j) {
        if (j == 4294967295L) {
            return -1;
        }
        return (int) ((j & PACKED_POSITION_MASK_GROUP) >> 32);
    }

    public static int getPackedPositionType(long j) {
        if (j == 4294967295L) {
            return 2;
        }
        return (j & Long.MIN_VALUE) == Long.MIN_VALUE ? 1 : 0;
    }

    private class AdapterWrapper extends BaseAdapter {
        private final ExpandableListAdapter encapsulatedAdapter;

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

        public AdapterWrapper(ExpandableListAdapter expandableListAdapter) {
            Condition.ensureNotNull(expandableListAdapter, "The adapter may not be null");
            this.encapsulatedAdapter = expandableListAdapter;
            this.encapsulatedAdapter.registerDataSetObserver(createDataSetObserver());
        }

        public ExpandableListAdapter getEncapsulatedAdapter() {
            return this.encapsulatedAdapter;
        }

        public int getCount() {
            int numColumnsCompatible = ExpandableGridView.this.getNumColumnsCompatible();
            int i = 0;
            for (int i2 = 0; i2 < this.encapsulatedAdapter.getGroupCount(); i2++) {
                i += numColumnsCompatible;
                if (ExpandableGridView.this.isGroupExpanded(i2)) {
                    int childrenCount = this.encapsulatedAdapter.getChildrenCount(i2);
                    int i3 = childrenCount % numColumnsCompatible;
                    i += childrenCount + (i3 > 0 ? numColumnsCompatible - i3 : 0);
                }
            }
            return i;
        }

        public Object getItem(int i) {
            Pair access$000 = ExpandableGridView.this.getItemPosition(i);
            int intValue = ((Integer) access$000.first).intValue();
            int intValue2 = ((Integer) access$000.second).intValue();
            if (intValue == -1 && intValue2 == -1) {
                return null;
            }
            if (intValue2 != -1) {
                return this.encapsulatedAdapter.getChild(intValue, intValue2);
            }
            return this.encapsulatedAdapter.getGroup(intValue);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            Pair access$000 = ExpandableGridView.this.getItemPosition(i);
            int intValue = ((Integer) access$000.first).intValue();
            int intValue2 = ((Integer) access$000.second).intValue();
            if (intValue == -1 && intValue2 == -1) {
                ExpandableGridView expandableGridView = ExpandableGridView.this;
                return expandableGridView.inflatePlaceholderView(view, expandableGridView.getViewHeight(this, i - 1));
            } else if (intValue2 != -1) {
                ExpandableListAdapter expandableListAdapter = this.encapsulatedAdapter;
                return expandableListAdapter.getChildView(intValue, intValue2, intValue2 == expandableListAdapter.getChildrenCount(intValue) - 1, view, viewGroup);
            } else {
                ExpandableGridView expandableGridView2 = ExpandableGridView.this;
                return new HeaderAndFooterGridView.FullWidthContainer(this.encapsulatedAdapter.getGroupView(intValue, expandableGridView2.isGroupExpanded(intValue), (View) null, viewGroup));
            }
        }

        public boolean areAllItemsEnabled() {
            return this.encapsulatedAdapter.areAllItemsEnabled();
        }

        public boolean hasStableIds() {
            return this.encapsulatedAdapter.hasStableIds();
        }
    }

    private void initialize() {
        this.expandedGroups = new HashSet();
        super.setOnItemClickListener(createItemClickListener());
        super.setOnItemLongClickListener(createItemLongClickListener());
    }

    private AdapterView.OnItemClickListener createItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                long j2;
                ExpandableGridView expandableGridView = ExpandableGridView.this;
                Pair access$000 = expandableGridView.getItemPosition(i - expandableGridView.getHeaderViewsCount());
                int intValue = ((Integer) access$000.first).intValue();
                int intValue2 = ((Integer) access$000.second).intValue();
                if (intValue2 != -1) {
                    j2 = ExpandableGridView.getPackedPositionForChild(intValue, intValue2);
                    boolean unused = ExpandableGridView.this.notifyOnChildClicked(view, intValue, intValue2, j2);
                } else if (intValue != -1) {
                    j2 = ExpandableGridView.getPackedPositionForGroup(intValue);
                    boolean unused2 = ExpandableGridView.this.notifyOnGroupClicked(view, intValue, j2);
                } else {
                    j2 = ExpandableGridView.getPackedPositionForChild(Integer.MAX_VALUE, i);
                }
                ExpandableGridView expandableGridView2 = ExpandableGridView.this;
                expandableGridView2.notifyOnItemClicked(view, expandableGridView2.getPackedPosition(i), j2);
            }
        };
    }

    private AdapterView.OnItemLongClickListener createItemLongClickListener() {
        return new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                long j2;
                ExpandableGridView expandableGridView = ExpandableGridView.this;
                Pair access$000 = expandableGridView.getItemPosition(i - expandableGridView.getHeaderViewsCount());
                int intValue = ((Integer) access$000.first).intValue();
                int intValue2 = ((Integer) access$000.second).intValue();
                if (intValue2 != -1) {
                    j2 = ExpandableGridView.getPackedPositionForChild(intValue, intValue2);
                } else if (intValue != -1) {
                    j2 = ExpandableGridView.getPackedPositionForGroup(intValue);
                } else {
                    j2 = ExpandableGridView.getPackedPositionForChild(Integer.MAX_VALUE, i);
                }
                ExpandableGridView expandableGridView2 = ExpandableGridView.this;
                return expandableGridView2.notifyOnItemLongClicked(view, expandableGridView2.getPackedPosition(i), j2);
            }
        };
    }

    /* access modifiers changed from: private */
    public int getPackedPosition(int i) {
        if (i < getHeaderViewsCount()) {
            return i;
        }
        Pair<Integer, Integer> itemPosition = getItemPosition(i - getHeaderViewsCount());
        int intValue = ((Integer) itemPosition.first).intValue();
        int intValue2 = ((Integer) itemPosition.second).intValue();
        if (intValue2 == -1 && intValue == -1) {
            int i2 = 0;
            for (int i3 = 0; i3 < getExpandableListAdapter().getGroupCount(); i3++) {
                i2 += getExpandableListAdapter().getChildrenCount(i3);
            }
            return (((getHeaderViewsCount() + getExpandableListAdapter().getGroupCount()) + i2) + i) - (getHeaderViewsCount() + this.adapter.getCount());
        } else if (intValue2 != -1) {
            return getPackedChildPosition(intValue, intValue2);
        } else {
            return getPackedGroupPosition(intValue);
        }
    }

    private int getPackedGroupPosition(int i) {
        int headerViewsCount = getHeaderViewsCount();
        if (i > 0) {
            for (int i2 = i - 1; i2 >= 0; i2--) {
                headerViewsCount += getExpandableListAdapter().getChildrenCount(i2) + 1;
            }
        }
        return headerViewsCount;
    }

    private int getPackedChildPosition(int i, int i2) {
        return getPackedGroupPosition(i) + i2 + 1;
    }

    /* access modifiers changed from: private */
    public Pair<Integer, Integer> getItemPosition(int i) {
        int i2;
        int numColumnsCompatible = getNumColumnsCompatible();
        int i3 = i;
        int i4 = 0;
        while (true) {
            i2 = -1;
            if (i4 < getExpandableListAdapter().getGroupCount()) {
                if (i3 == 0) {
                    break;
                } else if (i3 < numColumnsCompatible) {
                    break;
                } else {
                    i2 = i3 - numColumnsCompatible;
                    if (isGroupExpanded(i4)) {
                        int childrenCount = getExpandableListAdapter().getChildrenCount(i4);
                        if (i2 < childrenCount) {
                            break;
                        }
                        int i5 = childrenCount % numColumnsCompatible;
                        i2 -= childrenCount + (i5 > 0 ? numColumnsCompatible - i5 : 0);
                    }
                    i3 = i2;
                    i4++;
                }
            } else {
                break;
            }
        }
        i4 = -1;
        return new Pair<>(Integer.valueOf(i4), Integer.valueOf(i2));
    }

    /* access modifiers changed from: private */
    public boolean notifyOnGroupClicked(View view, int i, long j) {
        OnGroupClickListener onGroupClickListener = this.groupClickListener;
        return onGroupClickListener != null && onGroupClickListener.onGroupClick(this, view, i, j);
    }

    /* access modifiers changed from: private */
    public boolean notifyOnChildClicked(View view, int i, int i2, long j) {
        OnChildClickListener onChildClickListener = this.childClickListener;
        return onChildClickListener != null && onChildClickListener.onChildClick(this, view, i, i2, j);
    }

    /* access modifiers changed from: private */
    public void notifyOnItemClicked(View view, int i, long j) {
        AdapterView.OnItemClickListener onItemClickListener = this.itemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(this, view, i, j);
        }
    }

    /* access modifiers changed from: private */
    public boolean notifyOnItemLongClicked(View view, int i, long j) {
        AdapterView.OnItemLongClickListener onItemLongClickListener = this.itemLongClickListener;
        return onItemLongClickListener != null && onItemLongClickListener.onItemLongClick(this, view, i, j);
    }

    private void notifyDataSetChanged() {
        AdapterWrapper adapterWrapper = this.adapter;
        if (adapterWrapper != null) {
            adapterWrapper.notifyDataSetChanged();
        }
    }

    public ExpandableGridView(Context context) {
        super(context, (AttributeSet) null);
        initialize();
    }

    public ExpandableGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize();
    }

    public ExpandableGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize();
    }

    public ExpandableGridView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initialize();
    }

    public final void setAdapter(ExpandableListAdapter expandableListAdapter) {
        this.expandedGroups.clear();
        if (expandableListAdapter != null) {
            this.adapter = new AdapterWrapper(expandableListAdapter);
            super.setAdapter((ListAdapter) this.adapter);
            return;
        }
        this.adapter = null;
        super.setAdapter((ListAdapter) null);
    }

    public ExpandableListAdapter getExpandableListAdapter() {
        AdapterWrapper adapterWrapper = this.adapter;
        if (adapterWrapper != null) {
            return adapterWrapper.getEncapsulatedAdapter();
        }
        return null;
    }

    public final boolean isGroupExpanded(int i) {
        return this.expandedGroups.contains(Integer.valueOf(i));
    }

    public final boolean expandGroup(int i) {
        if (getExpandableListAdapter() == null || isGroupExpanded(i)) {
            return false;
        }
        this.expandedGroups.add(Integer.valueOf(i));
        notifyDataSetChanged();
        return true;
    }

    public final boolean collapseGroup(int i) {
        if (getExpandableListAdapter() == null || !isGroupExpanded(i)) {
            return false;
        }
        this.expandedGroups.remove(Integer.valueOf(i));
        notifyDataSetChanged();
        return true;
    }

    public final void setOnGroupClickListener(OnGroupClickListener onGroupClickListener) {
        this.groupClickListener = onGroupClickListener;
    }

    public final void setOnChildClickListener(OnChildClickListener onChildClickListener) {
        this.childClickListener = onChildClickListener;
    }

    public final void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }

    public final void setOnItemLongClickListener(AdapterView.OnItemLongClickListener onItemLongClickListener) {
        this.itemLongClickListener = onItemLongClickListener;
    }

    public final void setAdapter(ListAdapter listAdapter) {
        this.expandedGroups.clear();
        super.setAdapter(listAdapter);
    }
}
