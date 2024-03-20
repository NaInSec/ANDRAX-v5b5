package com.thecrackertechnology.andrax;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import com.thecrackertechnology.dragonterminal.bridge.Bridge;

public class Dco_voip_3g_4g extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.dco_voip_3g_4g);
        CardView cardView = (CardView) findViewById(R.id.card_view_iaxflood);
        CardView cardView2 = (CardView) findViewById(R.id.card_view_inviteflood);
        CardView cardView3 = (CardView) findViewById(R.id.card_view_rtpflood);
        CardView cardView4 = (CardView) findViewById(R.id.card_view_udpfloodVLAN);
        CardView cardView5 = (CardView) findViewById(R.id.card_view_rtpbreak);
        CardView cardView6 = (CardView) findViewById(R.id.card_view_sipcracker);
        CardView cardView7 = (CardView) findViewById(R.id.card_view_rtpinsertsound);
        ((CardView) findViewById(R.id.card_view_enodebhack)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("eNodeB-HACK -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_mmeenodebhack)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("mme-eNodeB-HACK -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_pgwhack)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("PGW-HACK -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_diameterenum)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("diameter_enum -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_s1apenum)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("s1ap_enum");
            }
        });
        ((CardView) findViewById(R.id.card_view_gtpscan)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("gtp_scan -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_cryptomobile)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("cryptomobile");
            }
        });
        ((CardView) findViewById(R.id.card_view_sgwhack)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("SGW-HACK -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_enumiax)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("enumiax");
            }
        });
        ((CardView) findViewById(R.id.card_view_svmap)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("sipvicious_svmap");
            }
        });
        ((CardView) findViewById(R.id.card_view_isip)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("sudo isip");
            }
        });
        ((CardView) findViewById(R.id.card_view_sipsak)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("sipsak");
            }
        });
        ((CardView) findViewById(R.id.card_view_vsaudit)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("vsaudit");
            }
        });
        ((CardView) findViewById(R.id.card_view_protostestsuite)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("protos-test-suite");
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("iaxflood");
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("inviteflood");
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("rtpflood");
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("udpfloodVLAN");
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("rtpbreak");
            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("sipcracker");
            }
        });
        cardView7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_voip_3g_4g.this.run_hack_cmd("rtpinsertsound");
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
