package de.mrapp.android.tabswitcher.model;

import de.mrapp.android.util.Condition;

public class Tag implements Cloneable {
    private boolean closing;
    private float position;
    private State state;

    public Tag() {
        setPosition(Float.NaN);
        setState(State.HIDDEN);
        setClosing(false);
    }

    public final float getPosition() {
        return this.position;
    }

    public final void setPosition(float f) {
        this.position = f;
    }

    public final State getState() {
        return this.state;
    }

    public final void setState(State state2) {
        Condition.ensureNotNull(state2, "The state may not be null");
        this.state = state2;
    }

    public final boolean isClosing() {
        return this.closing;
    }

    public final void setClosing(boolean z) {
        this.closing = z;
    }

    public final Tag clone() {
        Tag tag;
        try {
            tag = (Tag) super.clone();
        } catch (ClassCastException | CloneNotSupportedException unused) {
            tag = new Tag();
        }
        tag.position = this.position;
        tag.state = this.state;
        tag.closing = this.closing;
        return tag;
    }
}
