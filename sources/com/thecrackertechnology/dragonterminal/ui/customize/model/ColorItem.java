package com.thecrackertechnology.dragonterminal.ui.customize.model;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.thecrackertechnology.andrax.AndraxApp;
import com.thecrackertechnology.andrax.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0013\u001a\u00020\u0014\"\u0004\b\u0000\u0010\u00152\u0006\u0010\u0016\u001a\u0002H\u0015H\u0016¢\u0006\u0002\u0010\u0017J\u001b\u0010\u0018\u001a\u00020\u0014\"\u0004\b\u0000\u0010\u00152\u0006\u0010\u0016\u001a\u0002H\u0015H\u0016¢\u0006\u0002\u0010\u0017R\"\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00050\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/customize/model/ColorItem;", "Lcom/github/wrdlbrnft/sortedlistadapter/SortedListAdapter$ViewModel;", "colorType", "", "colorValue", "", "(ILjava/lang/String;)V", "colorName", "kotlin.jvm.PlatformType", "getColorName", "()Ljava/lang/String;", "setColorName", "(Ljava/lang/String;)V", "getColorType", "()I", "setColorType", "(I)V", "getColorValue", "setColorValue", "isContentTheSameAs", "", "T", "t", "(Ljava/lang/Object;)Z", "isSameModelAs", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ColorItem.kt */
public final class ColorItem implements SortedListAdapter.ViewModel {
    private String colorName = AndraxApp.Companion.get().getResources().getStringArray(R.array.color_item_names)[this.colorType + 3];
    private int colorType;
    private String colorValue;

    public ColorItem(int i, String str) {
        Intrinsics.checkParameterIsNotNull(str, "colorValue");
        this.colorType = i;
        this.colorValue = str;
    }

    public final int getColorType() {
        return this.colorType;
    }

    public final String getColorValue() {
        return this.colorValue;
    }

    public final void setColorType(int i) {
        this.colorType = i;
    }

    public final void setColorValue(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.colorValue = str;
    }

    public <T> boolean isSameModelAs(T t) {
        if (!(t instanceof ColorItem)) {
            return false;
        }
        ColorItem colorItem = (ColorItem) t;
        if (!Intrinsics.areEqual((Object) colorItem.colorName, (Object) this.colorName) || !Intrinsics.areEqual((Object) colorItem.colorValue, (Object) this.colorValue) || colorItem.colorType != this.colorType) {
            return false;
        }
        return true;
    }

    public <T> boolean isContentTheSameAs(T t) {
        return isSameModelAs(t);
    }

    public final String getColorName() {
        return this.colorName;
    }

    public final void setColorName(String str) {
        this.colorName = str;
    }
}
