package com.thecrackertechnology.andrax;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import com.thecrackertechnology.dragonterminal.bridge.Bridge;
import com.thecrackertechnology.dragonterminal.bridge.SessionId;

public class Dco_Information_Gathering extends Activity {
    int REQUEST_CODE_RUN = 1;
    private SessionId lastSessionId;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.dco_information_gathering);
        CardView cardView = (CardView) findViewById(R.id.card_view_ismtp);
        CardView cardView2 = (CardView) findViewById(R.id.card_view_firewalk);
        CardView cardView3 = (CardView) findViewById(R.id.card_view_braa);
        CardView cardView4 = (CardView) findViewById(R.id.card_view_0trace);
        CardView cardView5 = (CardView) findViewById(R.id.card_view_fierce);
        CardView cardView6 = (CardView) findViewById(R.id.card_view_dmitry);
        CardView cardView7 = (CardView) findViewById(R.id.card_view_intrace);
        CardView cardView8 = (CardView) findViewById(R.id.card_view_theharvester);
        CardView cardView9 = (CardView) findViewById(R.id.card_view_buster);
        CardView cardView10 = (CardView) findViewById(R.id.card_view_sublist3r);
        CardView cardView11 = (CardView) findViewById(R.id.card_view_shuffledns);
        CardView cardView12 = (CardView) findViewById(R.id.card_view_massdns);
        CardView cardView13 = (CardView) findViewById(R.id.card_view_chameleon);
        CardView cardView14 = (CardView) findViewById(R.id.card_view_dnsmap);
        CardView cardView15 = (CardView) findViewById(R.id.card_view_vault);
        CardView cardView16 = (CardView) findViewById(R.id.card_view_xray);
        CardView cardView17 = (CardView) findViewById(R.id.card_view_tld_scanner);
        CardView cardView18 = (CardView) findViewById(R.id.card_view_amass);
        CardView cardView19 = (CardView) findViewById(R.id.card_view_maryam);
        CardView cardView20 = (CardView) findViewById(R.id.card_view_maltego);
        CardView cardView21 = (CardView) findViewById(R.id.card_view_redhawk);
        CardView cardView22 = (CardView) findViewById(R.id.card_view_onesixtyone);
        CardView cardView23 = (CardView) findViewById(R.id.card_view_arping);
        CardView cardView24 = (CardView) findViewById(R.id.card_view_dnsdict6);
        CardView cardView25 = (CardView) findViewById(R.id.card_view_dnsenum);
        CardView cardView26 = (CardView) findViewById(R.id.card_view_inverselookup6);
        CardView cardView27 = (CardView) findViewById(R.id.card_view_thcping6);
        CardView cardView28 = (CardView) findViewById(R.id.card_view_trace6);
        CardView cardView29 = (CardView) findViewById(R.id.card_view_netdiscover);
        CardView cardView30 = (CardView) findViewById(R.id.card_view_snmpwn);
        ((CardView) findViewById(R.id.card_view_hping3)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("hping3 --help");
            }
        });
        ((CardView) findViewById(R.id.card_view_nping)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("nping");
            }
        });
        ((CardView) findViewById(R.id.card_view_whois)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("whois");
            }
        });
        ((CardView) findViewById(R.id.card_view_lbd)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("lbd");
            }
        });
        ((CardView) findViewById(R.id.card_view_dig)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("dig -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_katanads)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("kds -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_atscan)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("atscan -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_bingip2hosts)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("bing-ip2hosts");
            }
        });
        ((CardView) findViewById(R.id.card_view_cloudfail)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("cloudfail -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_cloudmare)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("cloudmare -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_dnsrecon)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("dnsrecon");
            }
        });
        ((CardView) findViewById(R.id.card_view_raccoon)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("raccoon --help");
            }
        });
        ((CardView) findViewById(R.id.card_view_pwnedornot)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("pwnedornot");
            }
        });
        ((CardView) findViewById(R.id.card_view_smtpuserenum)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("smtp-user-enum");
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("iSMTP");
            }
        });
        cardView22.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("onesixtyone");
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("firewalk");
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("0trace");
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("braa");
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("fierce --help");
            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("dmitry");
            }
        });
        cardView7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("intrace");
            }
        });
        cardView8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("theHarvester");
            }
        });
        cardView9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("buster");
            }
        });
        cardView13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("chameleon -h");
            }
        });
        cardView14.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("dnsmap");
            }
        });
        cardView15.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("sudo vault");
            }
        });
        cardView16.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("xray");
            }
        });
        cardView17.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("tld_scanner");
            }
        });
        cardView18.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("amass");
            }
        });
        cardView19.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("maryam");
            }
        });
        cardView20.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("maltego");
            }
        });
        cardView10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("sublist3r");
            }
        });
        cardView11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("shuffledns");
            }
        });
        cardView12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("massdns");
            }
        });
        cardView21.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("rhawk");
            }
        });
        cardView23.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("arping");
            }
        });
        cardView24.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("dnsdict6");
            }
        });
        cardView25.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("dnsenum");
            }
        });
        cardView26.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("inverse_lookup6");
            }
        });
        cardView27.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("thcping6");
            }
        });
        cardView28.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("trace6");
            }
        });
        cardView29.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("sudo netdiscover");
            }
        });
        cardView30.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Information_Gathering.this.run_hack_cmd("snmpwn --help");
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
