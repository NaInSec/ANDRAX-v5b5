package com.thecrackertechnology.dragonterminal.ui.customize.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.component.colorscheme.NeoColorScheme;
import com.thecrackertechnology.dragonterminal.ui.customize.adapter.holder.ColorItemViewHolder;
import com.thecrackertechnology.dragonterminal.ui.customize.model.ColorItem;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u001cB+\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J(\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0014H\u0014R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/customize/adapter/ColorItemAdapter;", "Lcom/github/wrdlbrnft/sortedlistadapter/SortedListAdapter;", "Lcom/thecrackertechnology/dragonterminal/ui/customize/model/ColorItem;", "Lcom/simplecityapps/recyclerview_fastscroll/views/FastScrollRecyclerView$SectionedAdapter;", "context", "Landroid/content/Context;", "initColorScheme", "Lcom/thecrackertechnology/dragonterminal/component/colorscheme/NeoColorScheme;", "comparator", "Ljava/util/Comparator;", "listener", "Lcom/thecrackertechnology/dragonterminal/ui/customize/adapter/ColorItemAdapter$Listener;", "(Landroid/content/Context;Lcom/thecrackertechnology/dragonterminal/component/colorscheme/NeoColorScheme;Ljava/util/Comparator;Lcom/thecrackertechnology/dragonterminal/ui/customize/adapter/ColorItemAdapter$Listener;)V", "colorList", "", "getColorList", "()Ljava/util/List;", "getSectionName", "", "position", "", "onCreateViewHolder", "Lcom/github/wrdlbrnft/sortedlistadapter/SortedListAdapter$ViewHolder;", "inflater", "Landroid/view/LayoutInflater;", "parent", "Landroid/view/ViewGroup;", "viewType", "Listener", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ColorItemAdapter.kt */
public final class ColorItemAdapter extends SortedListAdapter<ColorItem> implements FastScrollRecyclerView.SectionedAdapter {
    private final List<ColorItem> colorList = new ArrayList();
    private final Listener listener;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/customize/adapter/ColorItemAdapter$Listener;", "", "onModelClicked", "", "model", "Lcom/thecrackertechnology/dragonterminal/ui/customize/model/ColorItem;", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: ColorItemAdapter.kt */
    public interface Listener {
        void onModelClicked(ColorItem colorItem);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ColorItemAdapter(Context context, NeoColorScheme neoColorScheme, Comparator<ColorItem> comparator, Listener listener2) {
        super(context, ColorItem.class, comparator);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(neoColorScheme, "initColorScheme");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        Intrinsics.checkParameterIsNotNull(listener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listener = listener2;
        Iterator it = new IntRange(-3, 15).iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            List<ColorItem> list = this.colorList;
            String color = neoColorScheme.getColor(nextInt);
            if (color == null) {
                color = "";
            }
            list.add(new ColorItem(nextInt, color));
        }
        edit().add(this.colorList).commit();
    }

    public final List<ColorItem> getColorList() {
        return this.colorList;
    }

    public String getSectionName(int i) {
        return String.valueOf(this.colorList.get(i).getColorName().charAt(0));
    }

    /* access modifiers changed from: protected */
    public SortedListAdapter.ViewHolder<? extends ColorItem> onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        View inflate = layoutInflater.inflate(R.layout.item_color, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "rootView");
        return new ColorItemViewHolder(inflate, this.listener);
    }
}
