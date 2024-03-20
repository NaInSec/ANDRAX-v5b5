package de.mrapp.android.tabswitcher;

import android.view.animation.Interpolator;
import de.mrapp.android.util.Condition;

public abstract class Animation {
    private final long duration;
    private final Interpolator interpolator;

    protected static abstract class Builder<AnimationType, BuilderType> {
        protected long duration;
        protected Interpolator interpolator;

        public abstract AnimationType create();

        /* access modifiers changed from: protected */
        public final BuilderType self() {
            return this;
        }

        public Builder() {
            setDuration(-1);
            setInterpolator((Interpolator) null);
        }

        public final BuilderType setDuration(long j) {
            Condition.ensureAtLeast(j, -1, "The duration must be at least -1");
            this.duration = j;
            return self();
        }

        public final BuilderType setInterpolator(Interpolator interpolator2) {
            this.interpolator = interpolator2;
            return self();
        }
    }

    protected Animation(long j, Interpolator interpolator2) {
        Condition.ensureAtLeast(j, -1, "The duration must be at least -1");
        this.duration = j;
        this.interpolator = interpolator2;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final Interpolator getInterpolator() {
        return this.interpolator;
    }
}
