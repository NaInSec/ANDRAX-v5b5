package com.thecrackertechnology.dragonterminal.ui.pm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.ui.pm.adapter.holder.PackageViewHolder;
import com.thecrackertechnology.dragonterminal.ui.pm.model.PackageModel;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0016B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J(\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000eH\u0014R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/pm/adapter/PackageAdapter;", "Lcom/github/wrdlbrnft/sortedlistadapter/SortedListAdapter;", "Lcom/thecrackertechnology/dragonterminal/ui/pm/model/PackageModel;", "Lcom/simplecityapps/recyclerview_fastscroll/views/FastScrollRecyclerView$SectionedAdapter;", "context", "Landroid/content/Context;", "comparator", "Ljava/util/Comparator;", "listener", "Lcom/thecrackertechnology/dragonterminal/ui/pm/adapter/PackageAdapter$Listener;", "(Landroid/content/Context;Ljava/util/Comparator;Lcom/thecrackertechnology/dragonterminal/ui/pm/adapter/PackageAdapter$Listener;)V", "getSectionName", "", "position", "", "onCreateViewHolder", "Lcom/github/wrdlbrnft/sortedlistadapter/SortedListAdapter$ViewHolder;", "inflater", "Landroid/view/LayoutInflater;", "parent", "Landroid/view/ViewGroup;", "viewType", "Listener", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: PackageAdapter.kt */
public final class PackageAdapter extends SortedListAdapter<PackageModel> implements FastScrollRecyclerView.SectionedAdapter {
    private final Listener listener;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/pm/adapter/PackageAdapter$Listener;", "", "onModelClicked", "", "model", "Lcom/thecrackertechnology/dragonterminal/ui/pm/model/PackageModel;", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: PackageAdapter.kt */
    public interface Listener {
        void onModelClicked(PackageModel packageModel);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PackageAdapter(Context context, Comparator<PackageModel> comparator, Listener listener2) {
        super(context, PackageModel.class, comparator);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        Intrinsics.checkParameterIsNotNull(listener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listener = listener2;
    }

    public String getSectionName(int i) {
        String packageName = ((PackageModel) getItem(i)).getPackageInfo().getPackageName();
        if (packageName != null) {
            if (packageName != null) {
                String substring = packageName.substring(0, 1);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                if (substring != null) {
                    return substring;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
        return "#";
    }

    /* access modifiers changed from: protected */
    public SortedListAdapter.ViewHolder<? extends PackageModel> onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        View inflate = layoutInflater.inflate(R.layout.item_package, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "rootView");
        return new PackageViewHolder(inflate, this.listener);
    }
}
