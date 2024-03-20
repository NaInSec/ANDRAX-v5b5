package com.afollestad.materialdialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import com.afollestad.materialdialogs.internal.MDRootLayout;

class DialogBase extends Dialog implements DialogInterface.OnShowListener {
    private DialogInterface.OnShowListener showListener;
    protected MDRootLayout view;

    DialogBase(Context context, int i) {
        super(context, i);
    }

    public View findViewById(int i) {
        return this.view.findViewById(i);
    }

    public final void setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        this.showListener = onShowListener;
    }

    /* access modifiers changed from: package-private */
    public final void setOnShowListenerInternal() {
        super.setOnShowListener(this);
    }

    /* access modifiers changed from: package-private */
    public final void setViewInternal(View view2) {
        super.setContentView(view2);
    }

    public void onShow(DialogInterface dialogInterface) {
        DialogInterface.OnShowListener onShowListener = this.showListener;
        if (onShowListener != null) {
            onShowListener.onShow(dialogInterface);
        }
    }

    @Deprecated
    public void setContentView(int i) throws IllegalAccessError {
        throw new IllegalAccessError("setContentView() is not supported in MaterialDialog. Specify a custom view in the Builder instead.");
    }

    @Deprecated
    public void setContentView(View view2) throws IllegalAccessError {
        throw new IllegalAccessError("setContentView() is not supported in MaterialDialog. Specify a custom view in the Builder instead.");
    }

    @Deprecated
    public void setContentView(View view2, ViewGroup.LayoutParams layoutParams) throws IllegalAccessError {
        throw new IllegalAccessError("setContentView() is not supported in MaterialDialog. Specify a custom view in the Builder instead.");
    }
}
