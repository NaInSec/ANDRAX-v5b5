package com.google.android.gms.dynamic;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.dynamic.IFragmentWrapper;

public final class FragmentWrapper extends IFragmentWrapper.Stub {
    private Fragment zzhy;

    public static FragmentWrapper wrap(Fragment fragment) {
        if (fragment != null) {
            return new FragmentWrapper(fragment);
        }
        return null;
    }

    private FragmentWrapper(Fragment fragment) {
        this.zzhy = fragment;
    }

    public final IObjectWrapper zzad() {
        return ObjectWrapper.wrap(this.zzhy.getActivity());
    }

    public final Bundle getArguments() {
        return this.zzhy.getArguments();
    }

    public final int getId() {
        return this.zzhy.getId();
    }

    public final IFragmentWrapper zzae() {
        return wrap(this.zzhy.getParentFragment());
    }

    public final IObjectWrapper zzaf() {
        return ObjectWrapper.wrap(this.zzhy.getResources());
    }

    public final boolean getRetainInstance() {
        return this.zzhy.getRetainInstance();
    }

    public final String getTag() {
        return this.zzhy.getTag();
    }

    public final IFragmentWrapper zzag() {
        return wrap(this.zzhy.getTargetFragment());
    }

    public final int getTargetRequestCode() {
        return this.zzhy.getTargetRequestCode();
    }

    public final boolean getUserVisibleHint() {
        return this.zzhy.getUserVisibleHint();
    }

    public final IObjectWrapper zzah() {
        return ObjectWrapper.wrap(this.zzhy.getView());
    }

    public final boolean isAdded() {
        return this.zzhy.isAdded();
    }

    public final boolean isDetached() {
        return this.zzhy.isDetached();
    }

    public final boolean isHidden() {
        return this.zzhy.isHidden();
    }

    public final boolean isInLayout() {
        return this.zzhy.isInLayout();
    }

    public final boolean isRemoving() {
        return this.zzhy.isRemoving();
    }

    public final boolean isResumed() {
        return this.zzhy.isResumed();
    }

    public final boolean isVisible() {
        return this.zzhy.isVisible();
    }

    public final void zza(IObjectWrapper iObjectWrapper) {
        this.zzhy.registerForContextMenu((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void setHasOptionsMenu(boolean z) {
        this.zzhy.setHasOptionsMenu(z);
    }

    public final void setMenuVisibility(boolean z) {
        this.zzhy.setMenuVisibility(z);
    }

    public final void setRetainInstance(boolean z) {
        this.zzhy.setRetainInstance(z);
    }

    public final void setUserVisibleHint(boolean z) {
        this.zzhy.setUserVisibleHint(z);
    }

    public final void startActivity(Intent intent) {
        this.zzhy.startActivity(intent);
    }

    public final void startActivityForResult(Intent intent, int i) {
        this.zzhy.startActivityForResult(intent, i);
    }

    public final void zzb(IObjectWrapper iObjectWrapper) {
        this.zzhy.unregisterForContextMenu((View) ObjectWrapper.unwrap(iObjectWrapper));
    }
}
