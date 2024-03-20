package de.mrapp.android.tabswitcher;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import de.mrapp.android.util.Condition;
import java.util.LinkedHashSet;
import java.util.Set;

public class Tab implements Parcelable {
    public static final Parcelable.Creator<Tab> CREATOR = new Parcelable.Creator<Tab>() {
        public Tab createFromParcel(Parcel parcel) {
            return new Tab(parcel);
        }

        public Tab[] newArray(int i) {
            return new Tab[i];
        }
    };
    private ColorStateList backgroundColor;
    private final Set<Callback> callbacks;
    private Bitmap closeButtonIconBitmap;
    private int closeButtonIconId;
    private boolean closeable;
    private Bitmap iconBitmap;
    private int iconId;
    private Bundle parameters;
    private CharSequence title;
    private ColorStateList titleTextColor;

    public interface Callback {
        void onBackgroundColorChanged(Tab tab);

        void onCloseButtonIconChanged(Tab tab);

        void onCloseableChanged(Tab tab);

        void onIconChanged(Tab tab);

        void onTitleChanged(Tab tab);

        void onTitleTextColorChanged(Tab tab);
    }

    public final int describeContents() {
        return 0;
    }

    private void notifyOnTitleChanged() {
        for (Callback onTitleChanged : this.callbacks) {
            onTitleChanged.onTitleChanged(this);
        }
    }

    private void notifyOnIconChanged() {
        for (Callback onIconChanged : this.callbacks) {
            onIconChanged.onIconChanged(this);
        }
    }

    private void notifyOnCloseableChanged() {
        for (Callback onCloseableChanged : this.callbacks) {
            onCloseableChanged.onCloseableChanged(this);
        }
    }

    private void notifyOnCloseButtonIconChanged() {
        for (Callback onCloseButtonIconChanged : this.callbacks) {
            onCloseButtonIconChanged.onCloseButtonIconChanged(this);
        }
    }

    private void notifyOnBackgroundColorChanged() {
        for (Callback onBackgroundColorChanged : this.callbacks) {
            onBackgroundColorChanged.onBackgroundColorChanged(this);
        }
    }

    private void notifyOnTitleTextColorChanged() {
        for (Callback onTitleTextColorChanged : this.callbacks) {
            onTitleTextColorChanged.onTitleTextColorChanged(this);
        }
    }

    protected Tab(Parcel parcel) {
        this.callbacks = new LinkedHashSet();
        this.title = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.iconId = parcel.readInt();
        this.iconBitmap = (Bitmap) parcel.readParcelable(getClass().getClassLoader());
        this.closeable = parcel.readInt() > 0;
        this.closeButtonIconId = parcel.readInt();
        this.closeButtonIconBitmap = (Bitmap) parcel.readParcelable(getClass().getClassLoader());
        this.backgroundColor = (ColorStateList) parcel.readParcelable(getClass().getClassLoader());
        this.titleTextColor = (ColorStateList) parcel.readParcelable(getClass().getClassLoader());
        this.parameters = parcel.readBundle(getClass().getClassLoader());
    }

    public Tab(CharSequence charSequence) {
        this.callbacks = new LinkedHashSet();
        setTitle(charSequence);
        this.closeable = true;
        this.closeButtonIconId = -1;
        this.closeButtonIconBitmap = null;
        this.iconId = -1;
        this.iconBitmap = null;
        this.backgroundColor = null;
        this.titleTextColor = null;
        this.parameters = null;
    }

    public Tab(Context context, int i) {
        this((CharSequence) context.getString(i));
    }

    public final CharSequence getTitle() {
        return this.title;
    }

    public final void setTitle(CharSequence charSequence) {
        Condition.ensureNotNull(charSequence, "The title may not be null");
        Condition.ensureNotEmpty(charSequence, "The title may not be empty");
        this.title = charSequence;
        notifyOnTitleChanged();
    }

    public final void setTitle(Context context, int i) {
        setTitle(context.getText(i));
    }

    public final Drawable getIcon(Context context) {
        Condition.ensureNotNull(context, "The context may not be null");
        int i = this.iconId;
        if (i != -1) {
            return ContextCompat.getDrawable(context, i);
        }
        if (this.iconBitmap != null) {
            return new BitmapDrawable(context.getResources(), this.iconBitmap);
        }
        return null;
    }

    public final void setIcon(int i) {
        this.iconId = i;
        this.iconBitmap = null;
        notifyOnIconChanged();
    }

    public final void setIcon(Bitmap bitmap) {
        this.iconId = -1;
        this.iconBitmap = bitmap;
        notifyOnIconChanged();
    }

    public final boolean isCloseable() {
        return this.closeable;
    }

    public final void setCloseable(boolean z) {
        this.closeable = z;
        notifyOnCloseableChanged();
    }

    public final Drawable getCloseButtonIcon(Context context) {
        Condition.ensureNotNull(context, "The context may not be null");
        int i = this.closeButtonIconId;
        if (i != -1) {
            return ContextCompat.getDrawable(context, i);
        }
        if (this.closeButtonIconBitmap != null) {
            return new BitmapDrawable(context.getResources(), this.closeButtonIconBitmap);
        }
        return null;
    }

    public final void setCloseButtonIcon(int i) {
        this.closeButtonIconId = i;
        this.closeButtonIconBitmap = null;
        notifyOnCloseButtonIconChanged();
    }

    public final void setCloseButtonIcon(Bitmap bitmap) {
        this.closeButtonIconId = -1;
        this.closeButtonIconBitmap = bitmap;
        notifyOnCloseButtonIconChanged();
    }

    public final ColorStateList getBackgroundColor() {
        return this.backgroundColor;
    }

    public final void setBackgroundColor(int i) {
        setBackgroundColor(i != -1 ? ColorStateList.valueOf(i) : null);
    }

    public final void setBackgroundColor(ColorStateList colorStateList) {
        this.backgroundColor = colorStateList;
        notifyOnBackgroundColorChanged();
    }

    public final ColorStateList getTitleTextColor() {
        return this.titleTextColor;
    }

    public final void setTitleTextColor(int i) {
        setTitleTextColor(i != -1 ? ColorStateList.valueOf(i) : null);
    }

    public final void setTitleTextColor(ColorStateList colorStateList) {
        this.titleTextColor = colorStateList;
        notifyOnTitleTextColorChanged();
    }

    public final Bundle getParameters() {
        return this.parameters;
    }

    public final void setParameters(Bundle bundle) {
        this.parameters = bundle;
    }

    public final void addCallback(Callback callback) {
        Condition.ensureNotNull(callback, "The callback may not be null");
        this.callbacks.add(callback);
    }

    public final void removeCallback(Callback callback) {
        Condition.ensureNotNull(callback, "The callback may not be null");
        this.callbacks.remove(callback);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        TextUtils.writeToParcel(this.title, parcel, i);
        parcel.writeInt(this.iconId);
        parcel.writeParcelable(this.iconBitmap, i);
        parcel.writeInt(this.closeable ? 1 : 0);
        parcel.writeInt(this.closeButtonIconId);
        parcel.writeParcelable(this.closeButtonIconBitmap, i);
        parcel.writeParcelable(this.backgroundColor, i);
        parcel.writeParcelable(this.titleTextColor, i);
        parcel.writeBundle(this.parameters);
    }
}
