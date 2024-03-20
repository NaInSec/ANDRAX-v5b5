package com.thecrackertechnology.dragonterminal.ui.bonus;

import android.support.v4.app.NotificationCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n \u0004*\u0004\u0018\u00010\b0\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "v", "Landroid/view/View;", "kotlin.jvm.PlatformType", "keyCode", "", "event", "Landroid/view/KeyEvent;", "onKey"}, k = 3, mv = {1, 1, 15})
/* compiled from: BonusActivity.kt */
final class BonusActivity$onAttachedToWindow$2 implements View.OnKeyListener {
    final /* synthetic */ ImageView $im;
    final /* synthetic */ BonusActivity this$0;

    BonusActivity$onAttachedToWindow$2(BonusActivity bonusActivity, ImageView imageView) {
        this.this$0 = bonusActivity;
        this.$im = imageView;
    }

    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i != 4) {
            Intrinsics.checkExpressionValueIsNotNull(keyEvent, NotificationCompat.CATEGORY_EVENT);
            if (keyEvent.getAction() == 0) {
                BonusActivity bonusActivity = this.this$0;
                bonusActivity.setMKeyCount$app_release(bonusActivity.getMKeyCount$app_release() + 1);
                bonusActivity.getMKeyCount$app_release();
                if (this.this$0.getMKeyCount$app_release() <= 2) {
                    return true;
                }
                if (this.this$0.getMTapCount$app_release() > 5) {
                    this.$im.performLongClick();
                    return true;
                }
                this.$im.performClick();
                return true;
            }
        }
        return false;
    }
}
