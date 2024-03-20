package com.thecrackertechnology.andrax;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import com.thecrackertechnology.dragonterminal.bridge.Bridge;

public class Dco_Packet_Crafting extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.dco_packet_crafting);
        ((CardView) findViewById(R.id.card_view_hping3)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Packet_Crafting.this.run_hack_cmd("hping3 --help");
            }
        });
        ((CardView) findViewById(R.id.card_view_nping)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Packet_Crafting.this.run_hack_cmd("nping");
            }
        });
        ((CardView) findViewById(R.id.card_view_scapy)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Packet_Crafting.this.run_hack_cmd("sudo scapy");
            }
        });
        ((CardView) findViewById(R.id.card_view_hexinject)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Packet_Crafting.this.run_hack_cmd("hexinject -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_ncat)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Packet_Crafting.this.run_hack_cmd("ncat -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_fragmentation6)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Packet_Crafting.this.run_hack_cmd("fragmentation6");
            }
        });
        ((CardView) findViewById(R.id.card_view_nemesis)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Packet_Crafting.this.run_hack_cmd("nemesis");
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
