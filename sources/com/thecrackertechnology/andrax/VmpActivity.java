package com.thecrackertechnology.andrax;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class VmpActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "HotSpot";
    private Button mCancel;
    private EditText mPassword;
    private RadioButton mRadioBtn;
    private RadioGroup mRadioGrp;
    private EditText mSSID;
    private String mSecurityPref;
    private Button mStart;
    private String mUserInput;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_vmp);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.mStart = (Button) findViewById(R.id.start);
        this.mCancel = (Button) findViewById(R.id.cancel);
        this.mSSID = (EditText) findViewById(R.id.ssid);
        this.mPassword = (EditText) findViewById(R.id.password);
        this.mRadioGrp = (RadioGroup) findViewById(R.id.rdgrp);
        this.mStart.setOnClickListener(this);
        this.mCancel.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == this.mStart) {
            this.mRadioBtn = (RadioButton) findViewById(this.mRadioGrp.getCheckedRadioButtonId());
            this.mSecurityPref = this.mRadioBtn.getText().toString();
            initConnection(this);
        } else if (view == this.mCancel) {
            this.mSSID.setText("");
            this.mPassword.setText("");
        }
    }

    private void initConnection(Context context) {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService("wifi");
        if (wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = this.mSSID.getText().toString();
        wifiConfiguration.preSharedKey = this.mPassword.getText().toString();
        wifiConfiguration.allowedAuthAlgorithms.set(0);
        wifiConfiguration.allowedProtocols.set(1);
        wifiConfiguration.allowedProtocols.set(0);
        wifiConfiguration.allowedPairwiseCiphers.set(2);
        wifiConfiguration.allowedPairwiseCiphers.set(1);
        wifiConfiguration.allowedGroupCiphers.set(3);
        wifiConfiguration.allowedGroupCiphers.set(2);
        if (this.mSecurityPref.equals("Open")) {
            wifiConfiguration.allowedKeyManagement.set(0);
            Toast.makeText(this, "Security: Open", 1).show();
        } else if (this.mSecurityPref.equals("WPA-PSK")) {
            wifiConfiguration.allowedKeyManagement.set(1);
            Toast.makeText(this, "Security: WPA/PSK", 1).show();
        }
        try {
            ((Boolean) wifiManager.getClass().getMethod("setWifiApEnabled", new Class[]{WifiConfiguration.class, Boolean.TYPE}).invoke(wifiManager, new Object[]{wifiConfiguration, true})).booleanValue();
            while (!((Boolean) wifiManager.getClass().getMethod("isWifiApEnabled", new Class[0]).invoke(wifiManager, new Object[0])).booleanValue()) {
            }
            ((Integer) wifiManager.getClass().getMethod("getWifiApState", new Class[0]).invoke(wifiManager, new Object[0])).intValue();
            WifiConfiguration wifiConfiguration2 = (WifiConfiguration) wifiManager.getClass().getMethod("getWifiApConfiguration", new Class[0]).invoke(wifiManager, new Object[0]);
        } catch (Exception e) {
            Log.e(getClass().toString(), "", e);
        }
    }
}
