package com.thecrackertechnology.andrax;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.thecrackertechnology.dragonterminal.bridge.Bridge;

public class MARINA extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        run_hack_cmd("sudo marina");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        finish();
    }

    public void run_hack_cmd(String str) {
        Intent createExecuteIntent = Bridge.createExecuteIntent(str);
        createExecuteIntent.setFlags(131072);
        startActivity(createExecuteIntent);
    }
}
