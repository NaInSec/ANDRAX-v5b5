package com.thecrackertechnology.andrax;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import com.thecrackertechnology.dragonterminal.bridge.Bridge;

public class Dco_Scanning extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.dco_scanning);
        ((CardView) findViewById(R.id.card_view_nmap)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Scanning.this.run_hack_cmd("nmap");
            }
        });
        ((CardView) findViewById(R.id.card_view_masscan)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Scanning.this.run_hack_cmd("masscan --help");
            }
        });
        ((CardView) findViewById(R.id.card_view_upnpscan)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Scanning.this.run_hack_cmd("upnp-scan");
            }
        });
        ((CardView) findViewById(R.id.card_view_raccoon)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Scanning.this.run_hack_cmd("raccoon --help");
            }
        });
        ((CardView) findViewById(R.id.card_view_sslscan)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Scanning.this.run_hack_cmd("sslscan");
            }
        });
        ((CardView) findViewById(R.id.card_view_hikpwn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Scanning.this.run_hack_cmd("hikpwn -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_braa)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Scanning.this.run_hack_cmd("braa");
            }
        });
        ((CardView) findViewById(R.id.card_view_ssh_auditor)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Scanning.this.run_hack_cmd("ssh-auditor");
            }
        });
        ((CardView) findViewById(R.id.card_view_rapidscan)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Scanning.this.run_hack_cmd("rapidscan --help");
            }
        });
        ((CardView) findViewById(R.id.card_view_reconspider)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Scanning.this.run_hack_cmd("reconspider");
            }
        });
        ((CardView) findViewById(R.id.card_view_pysslscan)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Scanning.this.run_hack_cmd("pysslscan -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_tld_scanner)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Scanning.this.run_hack_cmd("tld_scanner");
            }
        });
        ((CardView) findViewById(R.id.card_view_nbtscan)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Scanning.this.run_hack_cmd("nbtscan");
            }
        });
        ((CardView) findViewById(R.id.card_view_ikescan)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Scanning.this.run_hack_cmd("sudo ike-scan");
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
