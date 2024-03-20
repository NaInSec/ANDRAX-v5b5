package com.thecrackertechnology.andrax;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
            }
        }, 2000);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    public boolean isInstalledOnSdCard() {
        getApplicationContext();
        try {
            String str = getApplicationInfo().dataDir;
            if (str.startsWith("/data/")) {
                return false;
            }
            if (str.contains("/mnt/") || str.contains("/sdcard/")) {
                return true;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }
}
