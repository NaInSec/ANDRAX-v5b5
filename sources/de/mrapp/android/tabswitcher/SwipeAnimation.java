package de.mrapp.android.tabswitcher;

import android.view.animation.Interpolator;
import de.mrapp.android.tabswitcher.Animation;
import de.mrapp.android.util.Condition;

public class SwipeAnimation extends Animation {
    private final SwipeDirection direction;

    public enum SwipeDirection {
        LEFT,
        RIGHT
    }

    public static class Builder extends Animation.Builder<SwipeAnimation, Builder> {
        private SwipeDirection direction;

        public Builder() {
            setDirection(SwipeDirection.RIGHT);
        }

        public final Builder setDirection(SwipeDirection swipeDirection) {
            Condition.ensureNotNull(swipeDirection, "The direction may not be null");
            this.direction = swipeDirection;
            return (Builder) self();
        }

        public final SwipeAnimation create() {
            return new SwipeAnimation(this.duration, this.interpolator, this.direction);
        }
    }

    private SwipeAnimation(long j, Interpolator interpolator, SwipeDirection swipeDirection) {
        super(j, interpolator);
        Condition.ensureNotNull(swipeDirection, "The direction may not be null");
        this.direction = swipeDirection;
    }

    public final SwipeDirection getDirection() {
        return this.direction;
    }
}
