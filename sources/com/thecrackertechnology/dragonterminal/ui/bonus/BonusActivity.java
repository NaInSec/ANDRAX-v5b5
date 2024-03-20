package com.thecrackertechnology.dragonterminal.ui.bonus;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.thecrackertechnology.andrax.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000  2\u00020\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0002J\r\u0010\u0018\u001a\u00020\u0019H\u0000¢\u0006\u0002\b\u001aJ\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0012\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000e¨\u0006!"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/bonus/BonusActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "mInterpolator", "Landroid/view/animation/PathInterpolator;", "getMInterpolator$app_release", "()Landroid/view/animation/PathInterpolator;", "setMInterpolator$app_release", "(Landroid/view/animation/PathInterpolator;)V", "mKeyCount", "", "getMKeyCount$app_release", "()I", "setMKeyCount$app_release", "(I)V", "mLayout", "Landroid/widget/FrameLayout;", "getMLayout$app_release", "()Landroid/widget/FrameLayout;", "setMLayout$app_release", "(Landroid/widget/FrameLayout;)V", "mTapCount", "getMTapCount$app_release", "setMTapCount$app_release", "makeRipple", "Landroid/graphics/drawable/Drawable;", "makeRipple$app_release", "onAttachedToWindow", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: BonusActivity.kt */
public final class BonusActivity extends AppCompatActivity {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final int[] FLAVORS = {(int) 4288423856L, (int) 4290406600L, (int) 4294940672L, (int) 4294948685L, (int) 4293943954L, (int) 4294491088L, (int) 4289705003L, (int) 4291681337L, (int) 4286141768L, (int) 4288776319L};
    private HashMap _$_findViewCache;
    private PathInterpolator mInterpolator = new PathInterpolator(0.0f, 0.0f, 0.5f, 1.0f);
    private int mKeyCount;
    public FrameLayout mLayout;
    private int mTapCount;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final FrameLayout getMLayout$app_release() {
        FrameLayout frameLayout = this.mLayout;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayout");
        }
        return frameLayout;
    }

    public final void setMLayout$app_release(FrameLayout frameLayout) {
        Intrinsics.checkParameterIsNotNull(frameLayout, "<set-?>");
        this.mLayout = frameLayout;
    }

    public final int getMTapCount$app_release() {
        return this.mTapCount;
    }

    public final void setMTapCount$app_release(int i) {
        this.mTapCount = i;
    }

    public final int getMKeyCount$app_release() {
        return this.mKeyCount;
    }

    public final void setMKeyCount$app_release(int i) {
        this.mKeyCount = i;
    }

    public final PathInterpolator getMInterpolator$app_release() {
        return this.mInterpolator;
    }

    public final void setMInterpolator$app_release(PathInterpolator pathInterpolator) {
        Intrinsics.checkParameterIsNotNull(pathInterpolator, "<set-?>");
        this.mInterpolator = pathInterpolator;
    }

    public final Drawable makeRipple$app_release() {
        int newColorIndex$app_release = Companion.newColorIndex$app_release();
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        Paint paint = shapeDrawable.getPaint();
        Intrinsics.checkExpressionValueIsNotNull(paint, "lollipopBackground.paint");
        paint.setColor(FLAVORS[newColorIndex$app_release]);
        return new RippleDrawable(ColorStateList.valueOf(FLAVORS[newColorIndex$app_release + 1]), shapeDrawable, (Drawable) null);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mLayout = new FrameLayout(this);
        getWindow().setFlags(1024, 1024);
        FrameLayout frameLayout = this.mLayout;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayout");
        }
        setContentView((View) frameLayout);
    }

    public void onAttachedToWindow() {
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        float f = displayMetrics.density;
        int min = (int) (Math.min((float) Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels), ((float) 600) * f) - (((float) 100) * f));
        Context context = this;
        BonusActivity$onAttachedToWindow$stick$1 bonusActivity$onAttachedToWindow$stick$1 = new BonusActivity$onAttachedToWindow$stick$1(this, min, context);
        FrameLayout frameLayout = this.mLayout;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayout");
        }
        frameLayout.addView(bonusActivity$onAttachedToWindow$stick$1, new FrameLayout.LayoutParams((int) (((float) 32) * f), -1, 1));
        bonusActivity$onAttachedToWindow$stick$1.setAlpha(0.0f);
        ImageView imageView = new ImageView(context);
        imageView.setTranslationZ(20.0f);
        imageView.setScaleX(0.0f);
        imageView.setScaleY(0.0f);
        Drawable drawable = getDrawable(R.drawable.plat_logo);
        if (drawable == null) {
            Intrinsics.throwNpe();
        }
        drawable.setAlpha(0);
        imageView.setImageDrawable(drawable);
        imageView.setBackground(makeRipple$app_release());
        imageView.setClickable(true);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        Paint paint = shapeDrawable.getPaint();
        Intrinsics.checkExpressionValueIsNotNull(paint, "highlight.paint");
        paint.setColor(285212671);
        float f2 = (float) min;
        int i = (int) (0.15f * f2);
        int i2 = (int) (f2 * 0.6f);
        shapeDrawable.setBounds(i, i, i2, i2);
        imageView.getOverlay().add(shapeDrawable);
        imageView.setOnClickListener(new BonusActivity$onAttachedToWindow$1(this, imageView, drawable, bonusActivity$onAttachedToWindow$stick$1));
        imageView.setFocusable(true);
        imageView.requestFocus();
        imageView.setOnKeyListener(new BonusActivity$onAttachedToWindow$2(this, imageView));
        FrameLayout frameLayout2 = this.mLayout;
        if (frameLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayout");
        }
        frameLayout2.addView(imageView, new FrameLayout.LayoutParams(min, min, 17));
        imageView.animate().scaleX(0.3f).scaleY(0.3f).setInterpolator(this.mInterpolator).setDuration(500).setStartDelay(800).start();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tR\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/bonus/BonusActivity$Companion;", "", "()V", "FLAVORS", "", "getFLAVORS$app_release", "()[I", "newColorIndex", "", "newColorIndex$app_release", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: BonusActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int[] getFLAVORS$app_release() {
            return BonusActivity.FLAVORS;
        }

        public final int newColorIndex$app_release() {
            return ((int) ((Math.random() * ((double) getFLAVORS$app_release().length)) / ((double) 2))) * 2;
        }
    }
}
