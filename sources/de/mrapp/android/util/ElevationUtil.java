package de.mrapp.android.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.v4.view.ViewCompat;

public final class ElevationUtil {
    private static final float BOTTOM_SCALE_FACTOR = 1.0f;
    private static final float FULL_ARC_DEGRESS = 360.0f;
    private static final float LEFT_SCALE_FACTOR = 0.5f;
    private static final int MAX_BOTTOM_ALPHA = 51;
    public static final int MAX_ELEVATION = 16;
    private static final int MAX_LEFT_ALPHA = 49;
    private static final int MAX_RIGHT_ALPHA = 49;
    private static final int MAX_TOP_ALPHA = 15;
    private static final int MIN_BOTTOM_ALPHA = 45;
    private static final int MIN_LEFT_ALPHA = 26;
    private static final int MIN_RIGHT_ALPHA = 26;
    private static final int MIN_TOP_ALPHA = 8;
    private static final float QUARTER_ARC_DEGRESS = 90.0f;
    private static final int REFERENCE_ELEVATION = 10;
    private static final float REFERENCE_SHADOW_WIDTH = 16.5f;
    private static final float RIGHT_SCALE_FACTOR = 0.5f;
    private static final float TOP_SCALE_FACTOR = 0.33333334f;

    public enum Orientation {
        LEFT(0),
        TOP(1),
        RIGHT(2),
        BOTTOM(3),
        TOP_LEFT(4),
        TOP_RIGHT(5),
        BOTTOM_LEFT(6),
        BOTTOM_RIGHT(7);
        
        private int value;

        private Orientation(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }

        public static Orientation fromValue(int i) {
            for (Orientation orientation : values()) {
                if (orientation.getValue() == i) {
                    return orientation;
                }
            }
            throw new IllegalArgumentException("Invalid enum value: " + i);
        }
    }

    private static Bitmap createEdgeShadow(Context context, int i, Orientation orientation, boolean z) {
        double d;
        if (i == 0) {
            return null;
        }
        float shadowWidth = getShadowWidth(context, i, orientation, z);
        int shadowColor = getShadowColor(i, orientation, z);
        double d2 = 1.0d;
        if (orientation == Orientation.LEFT || orientation == Orientation.RIGHT) {
            d = Math.ceil((double) shadowWidth);
        } else {
            d = 1.0d;
        }
        int round = (int) Math.round(d);
        if (orientation == Orientation.TOP || orientation == Orientation.BOTTOM) {
            d2 = Math.ceil((double) shadowWidth);
        }
        int round2 = (int) Math.round(d2);
        Bitmap createBitmap = Bitmap.createBitmap(round, round2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Shader createLinearGradient = createLinearGradient(orientation, round, round2, shadowWidth, shadowColor);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setShader(createLinearGradient);
        canvas.drawRect(0.0f, 0.0f, (float) round, (float) round2, paint);
        return createBitmap;
    }

    private static Bitmap createCornerShadow(Context context, int i, Orientation orientation, boolean z) {
        Orientation orientation2 = orientation;
        if (i == 0) {
            return null;
        }
        float horizontalShadowWidth = getHorizontalShadowWidth(context, i, orientation, z);
        float verticalShadowWidth = getVerticalShadowWidth(context, i, orientation, z);
        int horizontalShadowColor = getHorizontalShadowColor(i, orientation, z);
        int verticalShadowColor = getVerticalShadowColor(i, orientation, z);
        int round = (int) Math.round(Math.ceil((double) verticalShadowWidth));
        int round2 = (int) Math.round(Math.ceil((double) horizontalShadowWidth));
        int max = Math.max(round, round2);
        Bitmap createBitmap = Bitmap.createBitmap(max, max, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        RectF cornerBounds = getCornerBounds(orientation2, max);
        float cornerAngle = getCornerAngle(orientation);
        int[] cornerColors = getCornerColors(orientation2, horizontalShadowColor, verticalShadowColor);
        float width = cornerBounds.left + (cornerBounds.width() / 2.0f);
        float height = cornerBounds.top + (cornerBounds.height() / 2.0f);
        float f = cornerAngle / FULL_ARC_DEGRESS;
        paint.setShader(new SweepGradient(width, height, cornerColors, new float[]{f, f + 0.25f}));
        Canvas canvas2 = canvas;
        Paint paint2 = paint;
        canvas2.drawArc(cornerBounds, cornerAngle, QUARTER_ARC_DEGRESS, true, paint);
        paint2.setShader(createRadialGradient(orientation2, max, Math.max(horizontalShadowWidth, verticalShadowWidth)));
        paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        float f2 = (float) max;
        canvas2.drawRect(0.0f, 0.0f, f2, f2, paint);
        return BitmapUtil.resize(createBitmap, round, round2);
    }

    private static int[] getCornerColors(Orientation orientation, int i, int i2) {
        int i3 = AnonymousClass1.$SwitchMap$de$mrapp$android$util$ElevationUtil$Orientation[orientation.ordinal()];
        if (i3 == 1) {
            return new int[]{i, i2};
        } else if (i3 == 2 || i3 == 3 || i3 == 4) {
            return new int[]{i2, i};
        } else {
            throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }
    }

    private static RectF getCornerBounds(Orientation orientation, int i) {
        int i2 = AnonymousClass1.$SwitchMap$de$mrapp$android$util$ElevationUtil$Orientation[orientation.ordinal()];
        if (i2 == 1) {
            return new RectF((float) (-i), 0.0f, (float) i, (float) (i * 2));
        }
        if (i2 == 2) {
            float f = (float) (i * 2);
            return new RectF(0.0f, 0.0f, f, f);
        } else if (i2 == 3) {
            return new RectF(0.0f, (float) (-i), (float) (i * 2), (float) i);
        } else {
            if (i2 == 4) {
                float f2 = (float) (-i);
                float f3 = (float) i;
                return new RectF(f2, f2, f3, f3);
            }
            throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }
    }

    private static float getHorizontalShadowWidth(Context context, int i, Orientation orientation, boolean z) {
        int i2 = AnonymousClass1.$SwitchMap$de$mrapp$android$util$ElevationUtil$Orientation[orientation.ordinal()];
        if (i2 == 1 || i2 == 2) {
            return getShadowWidth(context, i, Orientation.TOP, z);
        }
        if (i2 == 3 || i2 == 4) {
            return getShadowWidth(context, i, Orientation.BOTTOM, z);
        }
        throw new IllegalArgumentException("Invalid orientation: " + orientation);
    }

    private static float getVerticalShadowWidth(Context context, int i, Orientation orientation, boolean z) {
        int i2 = AnonymousClass1.$SwitchMap$de$mrapp$android$util$ElevationUtil$Orientation[orientation.ordinal()];
        if (i2 != 1) {
            if (i2 == 2 || i2 == 3) {
                return getShadowWidth(context, i, Orientation.LEFT, z);
            }
            if (i2 != 4) {
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
            }
        }
        return getShadowWidth(context, i, Orientation.RIGHT, z);
    }

    private static float getShadowWidth(Context context, int i, Orientation orientation, boolean z) {
        float f;
        float f2 = (((float) i) / 10.0f) * REFERENCE_SHADOW_WIDTH;
        if (!z) {
            int i2 = AnonymousClass1.$SwitchMap$de$mrapp$android$util$ElevationUtil$Orientation[orientation.ordinal()];
            if (i2 != 5) {
                if (i2 == 6) {
                    f = f2 * TOP_SCALE_FACTOR;
                    return DisplayUtil.dpToPixels(context, f);
                } else if (i2 != 7) {
                    if (i2 != 8) {
                        throw new IllegalArgumentException("Invalid orientation: " + orientation);
                    }
                }
            }
            f = f2 * 0.5f;
            return DisplayUtil.dpToPixels(context, f);
        }
        f = f2 * BOTTOM_SCALE_FACTOR;
        return DisplayUtil.dpToPixels(context, f);
    }

    private static int getHorizontalShadowColor(int i, Orientation orientation, boolean z) {
        int i2 = AnonymousClass1.$SwitchMap$de$mrapp$android$util$ElevationUtil$Orientation[orientation.ordinal()];
        if (i2 == 1 || i2 == 2) {
            return getShadowColor(i, Orientation.TOP, z);
        }
        if (i2 == 3 || i2 == 4) {
            return getShadowColor(i, Orientation.BOTTOM, z);
        }
        throw new IllegalArgumentException("Invalid orientation: " + orientation);
    }

    private static int getVerticalShadowColor(int i, Orientation orientation, boolean z) {
        int i2 = AnonymousClass1.$SwitchMap$de$mrapp$android$util$ElevationUtil$Orientation[orientation.ordinal()];
        if (i2 != 1) {
            if (i2 == 2 || i2 == 3) {
                return getShadowColor(i, Orientation.LEFT, z);
            }
            if (i2 != 4) {
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
            }
        }
        return getShadowColor(i, Orientation.RIGHT, z);
    }

    private static int getShadowColor(int i, Orientation orientation, boolean z) {
        int i2;
        if (z) {
            i2 = getShadowAlpha(i, 45, 51);
        } else {
            int i3 = AnonymousClass1.$SwitchMap$de$mrapp$android$util$ElevationUtil$Orientation[orientation.ordinal()];
            if (i3 == 5) {
                i2 = getShadowAlpha(i, 26, 49);
            } else if (i3 == 6) {
                i2 = getShadowAlpha(i, 8, 15);
            } else if (i3 == 7) {
                i2 = getShadowAlpha(i, 26, 49);
            } else if (i3 == 8) {
                i2 = getShadowAlpha(i, 45, 51);
            } else {
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
            }
        }
        return Color.argb(i2, 0, 0, 0);
    }

    private static int getShadowAlpha(int i, int i2, int i3) {
        return Math.round(((float) i2) + ((((float) i) / 16.0f) * ((float) (i3 - i2))));
    }

    private static Shader createLinearGradient(Orientation orientation, int i, int i2, float f, int i3) {
        RectF rectF = new RectF();
        int i4 = AnonymousClass1.$SwitchMap$de$mrapp$android$util$ElevationUtil$Orientation[orientation.ordinal()];
        if (i4 == 5) {
            float f2 = (float) i;
            rectF.left = f2;
            rectF.right = f2 - f;
        } else if (i4 == 6) {
            float f3 = (float) i2;
            rectF.top = f3;
            rectF.bottom = f3 - f;
        } else if (i4 == 7) {
            rectF.right = f;
        } else if (i4 == 8) {
            rectF.bottom = f;
        } else {
            throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }
        return new LinearGradient(rectF.left, rectF.top, rectF.right, rectF.bottom, i3, 0, Shader.TileMode.CLAMP);
    }

    private static Shader createRadialGradient(Orientation orientation, int i, float f) {
        PointF pointF = new PointF();
        int i2 = AnonymousClass1.$SwitchMap$de$mrapp$android$util$ElevationUtil$Orientation[orientation.ordinal()];
        if (i2 == 1) {
            pointF.y = (float) i;
        } else if (i2 == 2) {
            float f2 = (float) i;
            pointF.x = f2;
            pointF.y = f2;
        } else if (i2 == 3) {
            pointF.x = (float) i;
        } else if (i2 != 4) {
            throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }
        return new RadialGradient(pointF.x, pointF.y, f, 0, ViewCompat.MEASURED_STATE_MASK, Shader.TileMode.CLAMP);
    }

    private static float getCornerAngle(Orientation orientation) {
        int i = AnonymousClass1.$SwitchMap$de$mrapp$android$util$ElevationUtil$Orientation[orientation.ordinal()];
        if (i == 1) {
            return 270.0f;
        }
        if (i == 2) {
            return 180.0f;
        }
        if (i == 3) {
            return QUARTER_ARC_DEGRESS;
        }
        if (i == 4) {
            return 0.0f;
        }
        throw new IllegalArgumentException("Invalid orientation: " + orientation);
    }

    private ElevationUtil() {
    }

    public static Bitmap createElevationShadow(Context context, int i, Orientation orientation) {
        return createElevationShadow(context, i, orientation, false);
    }

    public static Bitmap createElevationShadow(Context context, int i, Orientation orientation, boolean z) {
        Condition.ensureNotNull(context, "The context may not be null");
        Condition.ensureAtLeast(i, 0, "The elevation must be at least 0");
        Condition.ensureAtMaximum(i, 16, "The elevation must be at maximum 16");
        Condition.ensureNotNull(orientation, "The orientation may not be null");
        switch (orientation) {
            case TOP_RIGHT:
            case TOP_LEFT:
            case BOTTOM_LEFT:
            case BOTTOM_RIGHT:
                return createCornerShadow(context, i, orientation, z);
            case LEFT:
            case TOP:
            case RIGHT:
            case BOTTOM:
                return createEdgeShadow(context, i, orientation, z);
            default:
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }
    }
}
