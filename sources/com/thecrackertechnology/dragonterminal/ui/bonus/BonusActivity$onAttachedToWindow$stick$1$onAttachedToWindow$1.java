package com.thecrackertechnology.dragonterminal.ui.bonus;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/thecrackertechnology/dragonterminal/ui/bonus/BonusActivity$onAttachedToWindow$stick$1$onAttachedToWindow$1", "Landroid/view/ViewOutlineProvider;", "getOutline", "", "view", "Landroid/view/View;", "outline", "Landroid/graphics/Outline;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: BonusActivity.kt */
public final class BonusActivity$onAttachedToWindow$stick$1$onAttachedToWindow$1 extends ViewOutlineProvider {
    final /* synthetic */ BonusActivity$onAttachedToWindow$stick$1 this$0;

    BonusActivity$onAttachedToWindow$stick$1$onAttachedToWindow$1(BonusActivity$onAttachedToWindow$stick$1 bonusActivity$onAttachedToWindow$stick$1) {
        this.this$0 = bonusActivity$onAttachedToWindow$stick$1;
    }

    public void getOutline(View view, Outline outline) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(outline, "outline");
        outline.setRect(0, this.this$0.getHeight() / 2, this.this$0.getWidth(), this.this$0.getHeight());
    }
}
