package de.mrapp.android.tabswitcher;

import android.view.animation.Interpolator;
import de.mrapp.android.tabswitcher.Animation;

public class RevealAnimation extends Animation {
    private final float x;
    private final float y;

    public static class Builder extends Animation.Builder<RevealAnimation, Builder> {
        private float x;
        private float y;

        public Builder() {
            setX(0.0f);
            setY(0.0f);
        }

        public final Builder setX(float f) {
            this.x = f;
            return (Builder) self();
        }

        public final Builder setY(float f) {
            this.y = f;
            return (Builder) self();
        }

        public final RevealAnimation create() {
            return new RevealAnimation(this.duration, this.interpolator, this.x, this.y);
        }
    }

    private RevealAnimation(long j, Interpolator interpolator, float f, float f2) {
        super(j, interpolator);
        this.x = f;
        this.y = f2;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }
}
