package com.thecrackertechnology.andrax;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import com.thecrackertechnology.dragonterminal.bridge.Bridge;

public class Dco_phishing extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.dco_phishing);
        ((CardView) findViewById(R.id.card_view_shellphish)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_phishing.this.run_hack_cmd("sudo shellphish");
            }
        });
        ((CardView) findViewById(R.id.card_view_gophish)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_phishing.this.run_hack_cmd("sudo gophish");
            }
        });
        ((CardView) findViewById(R.id.card_view_evilginx2)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_phishing.this.run_hack_cmd("sudo evilginx2");
            }
        });
        ((CardView) findViewById(R.id.card_view_modlishka)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_phishing.this.run_hack_cmd("sudo modlishka");
            }
        });
        ((CardView) findViewById(R.id.card_view_urlcrazy)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_phishing.this.run_hack_cmd("urlcrazy");
            }
        });
        ((CardView) findViewById(R.id.card_view_dnstwist)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_phishing.this.run_hack_cmd("dnstwist");
            }
        });
    }

    public void run_hack_cmd(String str) {
        Intent createExecuteIntent = Bridge.createExecuteIntent(str);
        createExecuteIntent.setFlags(131072);
        startActivity(createExecuteIntent);
    }

    public void onPause() {
        super.onPause();
        finish();
    }
}
