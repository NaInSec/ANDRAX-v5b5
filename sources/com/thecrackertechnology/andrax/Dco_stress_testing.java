package com.thecrackertechnology.andrax;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import com.thecrackertechnology.dragonterminal.bridge.Bridge;

public class Dco_stress_testing extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.dco_stress_testing);
        CardView cardView = (CardView) findViewById(R.id.card_view_rsmurf6);
        CardView cardView2 = (CardView) findViewById(R.id.card_view_dosnewip6);
        CardView cardView3 = (CardView) findViewById(R.id.card_view_randicmp6);
        CardView cardView4 = (CardView) findViewById(R.id.card_view_slowhttptest);
        CardView cardView5 = (CardView) findViewById(R.id.card_view_slowloris);
        CardView cardView6 = (CardView) findViewById(R.id.card_view_goldeneye);
        ((CardView) findViewById(R.id.card_view_impulse)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("impulse");
            }
        });
        ((CardView) findViewById(R.id.card_view_fuzzip6)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("fuzz_ip6");
            }
        });
        ((CardView) findViewById(R.id.card_view_denial6)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("denial6");
            }
        });
        ((CardView) findViewById(R.id.card_view_flooddhcpc6)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("flood_dhcpc6");
            }
        });
        ((CardView) findViewById(R.id.card_view_floodadvertise6)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("flood_advertise6");
            }
        });
        ((CardView) findViewById(R.id.card_view_floodmld6)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("flood_mld6");
            }
        });
        ((CardView) findViewById(R.id.card_view_floodmld26)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("flood_mld26");
            }
        });
        ((CardView) findViewById(R.id.card_view_floodmldrouter6)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("flood_mldrouter6");
            }
        });
        ((CardView) findViewById(R.id.card_view_floodredir6)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("flood_redir6");
            }
        });
        ((CardView) findViewById(R.id.card_view_floodrouter6)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("flood_router6");
            }
        });
        ((CardView) findViewById(R.id.card_view_floodrouter26)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("flood_router26");
            }
        });
        ((CardView) findViewById(R.id.card_view_floodrs6)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("flood_rs6");
            }
        });
        ((CardView) findViewById(R.id.card_view_floodsolicitate6)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("flood_solicitate6");
            }
        });
        ((CardView) findViewById(R.id.card_view_floodunreach6)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("flood_unreach6");
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("rsmurf6");
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("dos-new-ip6");
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("randicmp6");
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("slowhttptest");
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("slowloris");
            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_stress_testing.this.run_hack_cmd("goldeneye");
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
