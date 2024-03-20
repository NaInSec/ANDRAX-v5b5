package com.thecrackertechnology.andrax;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import com.thecrackertechnology.dragonterminal.bridge.Bridge;

public class Dco_Wireless_Hacking extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.dco_wireless_hacking);
        CardView cardView = (CardView) findViewById(R.id.card_view_hcxdumptool);
        CardView cardView2 = (CardView) findViewById(R.id.card_view_wifiarp);
        CardView cardView3 = (CardView) findViewById(R.id.card_view_wifidns);
        CardView cardView4 = (CardView) findViewById(R.id.card_view_wifiping);
        CardView cardView5 = (CardView) findViewById(R.id.card_view_wifitap);
        CardView cardView6 = (CardView) findViewById(R.id.card_view_eapmd5pass);
        CardView cardView7 = (CardView) findViewById(R.id.card_view_hciconfig);
        CardView cardView8 = (CardView) findViewById(R.id.card_view_bluetoothctl);
        CardView cardView9 = (CardView) findViewById(R.id.card_view_gatttool);
        CardView cardView10 = (CardView) findViewById(R.id.card_view_pixiewps);
        CardView cardView11 = (CardView) findViewById(R.id.card_view_wifite2);
        ((CardView) findViewById(R.id.card_view_aircrack)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("aircrack-ng");
            }
        });
        ((CardView) findViewById(R.id.card_view_cowpatty)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("cowpatty");
            }
        });
        ((CardView) findViewById(R.id.card_view_mdk3)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("mdk3");
            }
        });
        ((CardView) findViewById(R.id.card_view_mdk4)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("mdk4");
            }
        });
        ((CardView) findViewById(R.id.card_view_bully)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("bully");
            }
        });
        ((CardView) findViewById(R.id.card_view_reaver)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("reaver");
            }
        });
        ((CardView) findViewById(R.id.card_view_wash)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("wash");
            }
        });
        ((CardView) findViewById(R.id.card_view_eaphammer)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("eaphammer -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_hostapd)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("hostapd -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_bluesnarfer)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("bluesnarfer");
            }
        });
        ((CardView) findViewById(R.id.card_view_crackle)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("crackle");
            }
        });
        ((CardView) findViewById(R.id.card_view_blescan)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("blescan -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_btlejack)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("btlejack");
            }
        });
        ((CardView) findViewById(R.id.card_view_btscanner)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("btscanner -h");
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("hcxdumptool");
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("wifiarp");
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("wifidns");
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("wifiping");
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("wifitap");
            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("eapmd5pass");
            }
        });
        cardView7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("hciconfig");
            }
        });
        cardView8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("bluetoothctl");
            }
        });
        cardView9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("gatttool");
            }
        });
        cardView10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("pixiewps");
            }
        });
        cardView11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Wireless_Hacking.this.run_hack_cmd("wifite");
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
