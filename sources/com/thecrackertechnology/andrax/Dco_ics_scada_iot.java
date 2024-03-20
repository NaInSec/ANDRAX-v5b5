package com.thecrackertechnology.andrax;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import com.thecrackertechnology.dragonterminal.bridge.Bridge;

public class Dco_ics_scada_iot extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.dco_ics_scada_iot);
        ((CardView) findViewById(R.id.card_view_plcscan)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_ics_scada_iot.this.run_hack_cmd("plcscan");
            }
        });
        ((CardView) findViewById(R.id.card_view_s7scan)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_ics_scada_iot.this.run_hack_cmd("s7scan");
            }
        });
        ((CardView) findViewById(R.id.card_view_modscan)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_ics_scada_iot.this.run_hack_cmd("modscan");
            }
        });
        ((CardView) findViewById(R.id.card_view_mbtget)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_ics_scada_iot.this.run_hack_cmd("mbtget -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_smod)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_ics_scada_iot.this.run_hack_cmd("sudo smod");
            }
        });
        ((CardView) findViewById(R.id.card_view_isaf)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_ics_scada_iot.this.run_hack_cmd("sudo isaf");
            }
        });
        ((CardView) findViewById(R.id.card_view_isf)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_ics_scada_iot.this.run_hack_cmd("sudo isf");
            }
        });
        ((CardView) findViewById(R.id.card_view_expliot)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_ics_scada_iot.this.run_hack_cmd("expliot");
            }
        });
        ((CardView) findViewById(R.id.card_view_sixnettools)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_ics_scada_iot.this.run_hack_cmd("SIXNET-tools");
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
