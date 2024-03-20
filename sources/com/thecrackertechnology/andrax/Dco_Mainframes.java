package com.thecrackertechnology.andrax;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import com.thecrackertechnology.dragonterminal.bridge.Bridge;

public class Dco_Mainframes extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.dco_mainframes);
        ((CardView) findViewById(R.id.card_view_tpxbrute)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Mainframes.this.run_hack_cmd("TPX_Brute -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_cicspwn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Mainframes.this.run_hack_cmd("cicspwn -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_cicsshot)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Mainframes.this.run_hack_cmd("cicsshot -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_netEBCDICat)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Mainframes.this.run_hack_cmd("netEBCDICat -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_TShOcker)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Mainframes.this.run_hack_cmd("TShOcker -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_phatso)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Mainframes.this.run_hack_cmd("phatso -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_mfsniffer)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Mainframes.this.run_hack_cmd("MFSniffer -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_psikotik)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Mainframes.this.run_hack_cmd("psikotik -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_birp)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Mainframes.this.run_hack_cmd("birp -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_maintp)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Mainframes.this.run_hack_cmd("MainTP -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_mainframe_bruter)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Mainframes.this.run_hack_cmd("mainframe_bruter -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_mfdos)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Mainframes.this.run_hack_cmd("MFDoS -h");
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
