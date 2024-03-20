package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.LinkedList;

public abstract class DeferredLifecycleHelper<T extends LifecycleDelegate> {
    /* access modifiers changed from: private */
    public T zare;
    /* access modifiers changed from: private */
    public Bundle zarf;
    /* access modifiers changed from: private */
    public LinkedList<zaa> zarg;
    private final OnDelegateCreatedListener<T> zarh = new zaa(this);

    private interface zaa {
        int getState();

        void zaa(LifecycleDelegate lifecycleDelegate);
    }

    /* access modifiers changed from: protected */
    public abstract void createDelegate(OnDelegateCreatedListener<T> onDelegateCreatedListener);

    public T getDelegate() {
        return this.zare;
    }

    private final void zal(int i) {
        while (!this.zarg.isEmpty() && this.zarg.getLast().getState() >= i) {
            this.zarg.removeLast();
        }
    }

    private final void zaa(Bundle bundle, zaa zaa2) {
        T t = this.zare;
        if (t != null) {
            zaa2.zaa(t);
            return;
        }
        if (this.zarg == null) {
            this.zarg = new LinkedList<>();
        }
        this.zarg.add(zaa2);
        if (bundle != null) {
            Bundle bundle2 = this.zarf;
            if (bundle2 == null) {
                this.zarf = (Bundle) bundle.clone();
            } else {
                bundle2.putAll(bundle);
            }
        }
        createDelegate(this.zarh);
    }

    public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
        zaa(bundle2, (zaa) new zab(this, activity, bundle, bundle2));
    }

    public void onCreate(Bundle bundle) {
        zaa(bundle, (zaa) new zac(this, bundle));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        zaa(bundle, (zaa) new zad(this, frameLayout, layoutInflater, viewGroup, bundle));
        if (this.zare == null) {
            handleGooglePlayUnavailable(frameLayout);
        }
        return frameLayout;
    }

    /* access modifiers changed from: protected */
    public void handleGooglePlayUnavailable(FrameLayout frameLayout) {
        showGooglePlayUnavailableMessage(frameLayout);
    }

    public static void showGooglePlayUnavailableMessage(FrameLayout frameLayout) {
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        Context context = frameLayout.getContext();
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        String errorMessage = ConnectionErrorMessages.getErrorMessage(context, isGooglePlayServicesAvailable);
        String errorDialogButtonMessage = ConnectionErrorMessages.getErrorDialogButtonMessage(context, isGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(errorMessage);
        linearLayout.addView(textView);
        Intent errorResolutionIntent = instance.getErrorResolutionIntent(context, isGooglePlayServicesAvailable, (String) null);
        if (errorResolutionIntent != null) {
            Button button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(errorDialogButtonMessage);
            linearLayout.addView(button);
            button.setOnClickListener(new zae(context, errorResolutionIntent));
        }
    }

    public void onStart() {
        zaa((Bundle) null, (zaa) new zaf(this));
    }

    public void onResume() {
        zaa((Bundle) null, (zaa) new zag(this));
    }

    public void onPause() {
        T t = this.zare;
        if (t != null) {
            t.onPause();
        } else {
            zal(5);
        }
    }

    public void onStop() {
        T t = this.zare;
        if (t != null) {
            t.onStop();
        } else {
            zal(4);
        }
    }

    public void onDestroyView() {
        T t = this.zare;
        if (t != null) {
            t.onDestroyView();
        } else {
            zal(2);
        }
    }

    public void onDestroy() {
        T t = this.zare;
        if (t != null) {
            t.onDestroy();
        } else {
            zal(1);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        T t = this.zare;
        if (t != null) {
            t.onSaveInstanceState(bundle);
            return;
        }
        Bundle bundle2 = this.zarf;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
    }

    public void onLowMemory() {
        T t = this.zare;
        if (t != null) {
            t.onLowMemory();
        }
    }
}
