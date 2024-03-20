package com.thecrackertechnology.dragonterminal.ui.bonus;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import de.mrapp.android.util.view.ExpandableGridView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"com/thecrackertechnology/dragonterminal/ui/bonus/BonusActivity$onAttachedToWindow$stick$1", "Landroid/view/View;", "mPaint", "Landroid/graphics/Paint;", "getMPaint$app_release", "()Landroid/graphics/Paint;", "setMPaint$app_release", "(Landroid/graphics/Paint;)V", "mShadow", "Landroid/graphics/Path;", "getMShadow$app_release", "()Landroid/graphics/Path;", "setMShadow$app_release", "(Landroid/graphics/Path;)V", "onAttachedToWindow", "", "onDraw", "c", "Landroid/graphics/Canvas;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: BonusActivity.kt */
public final class BonusActivity$onAttachedToWindow$stick$1 extends View {
    final /* synthetic */ int $size;
    private Paint mPaint = new Paint();
    private Path mShadow = new Path();
    final /* synthetic */ BonusActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BonusActivity$onAttachedToWindow$stick$1(BonusActivity bonusActivity, int i, Context context) {
        super(context);
        this.this$0 = bonusActivity;
        this.$size = i;
    }

    public final Paint getMPaint$app_release() {
        return this.mPaint;
    }

    public final void setMPaint$app_release(Paint paint) {
        Intrinsics.checkParameterIsNotNull(paint, "<set-?>");
        this.mPaint = paint;
    }

    public final Path getMShadow$app_release() {
        return this.mShadow;
    }

    public final void setMShadow$app_release(Path path) {
        Intrinsics.checkParameterIsNotNull(path, "<set-?>");
        this.mShadow = path;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setWillNotDraw(false);
        setOutlineProvider(new BonusActivity$onAttachedToWindow$stick$1$onAttachedToWindow$1(this));
    }

    public void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "c");
        int width = canvas.getWidth();
        int height = canvas.getHeight() / 2;
        canvas.translate(0.0f, (float) height);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
        float f = (float) width;
        gradientDrawable.setGradientCenter(0.75f * f, 0.0f);
        int i = (int) 4289374890L;
        gradientDrawable.setColors(new int[]{(int) ExpandableGridView.PACKED_POSITION_VALUE_NULL, i});
        gradientDrawable.setBounds(0, 0, width, height);
        gradientDrawable.draw(canvas);
        this.mPaint.setColor(i);
        this.mShadow.reset();
        this.mShadow.moveTo(0.0f, 0.0f);
        this.mShadow.lineTo(f, 0.0f);
        this.mShadow.lineTo(f, ((float) (this.$size / 2)) + (1.5f * f));
        this.mShadow.lineTo(0.0f, (float) (this.$size / 2));
        this.mShadow.close();
        canvas.drawPath(this.mShadow, this.mPaint);
    }
}
