package com.thecrackertechnology.andrax;

import android.app.Activity;
import android.os.Bundle;

public class RootIt extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.rootit);
    }

    public void onPause() {
        super.onPause();
        finish();
    }
}
