package com.thecrackertechnology.dragonterminal.ui.bonus;

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: BonusActivity.kt */
final class BonusActivity$onAttachedToWindow$1 implements View.OnClickListener {
    final /* synthetic */ ImageView $im;
    final /* synthetic */ Drawable $platlogo;
    final /* synthetic */ BonusActivity$onAttachedToWindow$stick$1 $stick;
    final /* synthetic */ BonusActivity this$0;

    BonusActivity$onAttachedToWindow$1(BonusActivity bonusActivity, ImageView imageView, Drawable drawable, BonusActivity$onAttachedToWindow$stick$1 bonusActivity$onAttachedToWindow$stick$1) {
        this.this$0 = bonusActivity;
        this.$im = imageView;
        this.$platlogo = drawable;
        this.$stick = bonusActivity$onAttachedToWindow$stick$1;
    }

    public final void onClick(View view) {
        if (this.this$0.getMTapCount$app_release() == 0) {
            this.$im.animate().translationZ(40.0f).scaleX(1.0f).scaleY(1.0f).setInterpolator(this.this$0.getMInterpolator$app_release()).setDuration(700).setStartDelay(500).start();
            ObjectAnimator ofInt = ObjectAnimator.ofInt(this.$platlogo, "alpha", new int[]{0, 255});
            Intrinsics.checkExpressionValueIsNotNull(ofInt, NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY);
            ofInt.setInterpolator(this.this$0.getMInterpolator$app_release());
            ofInt.setStartDelay(1000);
            ofInt.start();
            this.$stick.animate().translationZ(20.0f).alpha(1.0f).setInterpolator(this.this$0.getMInterpolator$app_release()).setDuration(700).setStartDelay(750).start();
        } else {
            this.$im.setBackground(this.this$0.makeRipple$app_release());
        }
        BonusActivity bonusActivity = this.this$0;
        bonusActivity.setMTapCount$app_release(bonusActivity.getMTapCount$app_release() + 1);
    }
}
