package de.mrapp.android.util.view;

import android.os.Parcel;
import android.os.Parcelable;
import de.mrapp.android.util.Condition;

public abstract class AbstractSavedState implements Parcelable {
    private final Parcelable superState;

    public int describeContents() {
        return 0;
    }

    protected AbstractSavedState(Parcel parcel) {
        Condition.ensureNotNull(parcel, "The parcel may not be null");
        this.superState = parcel.readParcelable(getClass().getClassLoader());
    }

    protected AbstractSavedState(Parcelable parcelable) {
        this.superState = parcelable;
    }

    public final Parcelable getSuperState() {
        return this.superState;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.superState, i);
    }
}
