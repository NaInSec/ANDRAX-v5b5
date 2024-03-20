package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import com.google.android.gms.dynamic.IFragmentWrapper;

public final class SupportFragmentWrapper extends IFragmentWrapper.Stub {
    private Fragment zzic;

    public static SupportFragmentWrapper wrap(Fragment fragment) {
        if (fragment != null) {
            return new SupportFragmentWrapper(fragment);
        }
        return null;
    }

    private SupportFragmentWrapper(Fragment fragment) {
        this.zzic = fragment;
    }

    public final IObjectWrapper zzad() {
        return ObjectWrapper.wrap(this.zzic.getActivity());
    }

    public final Bundle getArguments() {
        return this.zzic.getArguments();
    }

    public final int getId() {
        return this.zzic.getId();
    }

    public final IFragmentWrapper zzae() {
        return wrap(this.zzic.getParentFragment());
    }

    public final IObjectWrapper zzaf() {
        return ObjectWrapper.wrap(this.zzic.getResources());
    }

    public final boolean getRetainInstance() {
        return this.zzic.getRetainInstance();
    }

    public final String getTag() {
        return this.zzic.getTag();
    }

    public final IFragmentWrapper zzag() {
        return wrap(this.zzic.getTargetFragment());
    }

    public final int getTargetRequestCode() {
        return this.zzic.getTargetRequestCode();
    }

    public final boolean getUserVisibleHint() {
        return this.zzic.getUserVisibleHint();
    }

    public final IObjectWrapper zzah() {
        return ObjectWrapper.wrap(this.zzic.getView());
    }

    public final boolean isAdded() {
        return this.zzic.isAdded();
    }

    public final boolean isDetached() {
        return this.zzic.isDetached();
    }

    public final boolean isHidden() {
        return this.zzic.isHidden();
    }

    public final boolean isInLayout() {
        return this.zzic.isInLayout();
    }

    public final boolean isRemoving() {
        return this.zzic.isRemoving();
    }

    public final boolean isResumed() {
        return this.zzic.isResumed();
    }

    public final boolean isVisible() {
        return this.zzic.isVisible();
    }

    public final void zza(IObjectWrapper iObjectWrapper) {
        this.zzic.registerForContextMenu((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void setHasOptionsMenu(boolean z) {
        this.zzic.setHasOptionsMenu(z);
    }

    public final void setMenuVisibility(boolean z) {
        this.zzic.setMenuVisibility(z);
    }

    public final void setRetainInstance(boolean z) {
        this.zzic.setRetainInstance(z);
    }

    public final void setUserVisibleHint(boolean z) {
        this.zzic.setUserVisibleHint(z);
    }

    public final void startActivity(Intent intent) {
        this.zzic.startActivity(intent);
    }

    public final void startActivityForResult(Intent intent, int i) {
        this.zzic.startActivityForResult(intent, i);
    }

    public final void zzb(IObjectWrapper iObjectWrapper) {
        this.zzic.unregisterForContextMenu((View) ObjectWrapper.unwrap(iObjectWrapper));
    }
}
