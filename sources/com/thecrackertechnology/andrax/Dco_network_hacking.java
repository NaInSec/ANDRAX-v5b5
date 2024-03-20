package com.thecrackertechnology.andrax;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import com.thecrackertechnology.dragonterminal.bridge.Bridge;

public class Dco_network_hacking extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.dco_network_hacking);
        CardView cardView = (CardView) findViewById(R.id.card_view_bgpcli);
        CardView cardView2 = (CardView) findViewById(R.id.card_view_cdpsnarf);
        CardView cardView3 = (CardView) findViewById(R.id.card_view_camscan);
        CardView cardView4 = (CardView) findViewById(R.id.card_view_sdnpwn);
        CardView cardView5 = (CardView) findViewById(R.id.card_view_eigrpcli);
        CardView cardView6 = (CardView) findViewById(R.id.card_view_yersinia);
        CardView cardView7 = (CardView) findViewById(R.id.card_view_miranda);
        CardView cardView8 = (CardView) findViewById(R.id.card_view_upnptools);
        CardView cardView9 = (CardView) findViewById(R.id.card_view_killrouter6);
        CardView cardView10 = (CardView) findViewById(R.id.card_view_detctsniffer6);
        CardView cardView11 = (CardView) findViewById(R.id.card_view_fakeadvertise6);
        CardView cardView12 = (CardView) findViewById(R.id.card_view_fakedhcps6);
        CardView cardView13 = (CardView) findViewById(R.id.card_view_fakedns6d);
        CardView cardView14 = (CardView) findViewById(R.id.card_view_fakednsupdate6);
        CardView cardView15 = (CardView) findViewById(R.id.card_view_fakemld26);
        CardView cardView16 = (CardView) findViewById(R.id.card_view_fakemld6);
        CardView cardView17 = (CardView) findViewById(R.id.card_view_fakemldrouter6);
        CardView cardView18 = (CardView) findViewById(R.id.card_view_fakerouter26);
        CardView cardView19 = (CardView) findViewById(R.id.card_view_fakerouter6);
        CardView cardView20 = (CardView) findViewById(R.id.card_view_fakesolicitate6);
        CardView cardView21 = (CardView) findViewById(R.id.card_view_implementation6);
        CardView cardView22 = (CardView) findViewById(R.id.card_view_parasite6);
        CardView cardView23 = (CardView) findViewById(R.id.card_view_redir6);
        CardView cardView24 = (CardView) findViewById(R.id.card_view_smurf6);
        CardView cardView25 = (CardView) findViewById(R.id.card_view_delorean);
        CardView cardView26 = (CardView) findViewById(R.id.card_view_smbmap);
        CardView cardView27 = (CardView) findViewById(R.id.card_view_fiked);
        CardView cardView28 = (CardView) findViewById(R.id.card_view_dhcping);
        ((CardView) findViewById(R.id.card_view_ipcalc)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("ipcalc --help");
            }
        });
        ((CardView) findViewById(R.id.card_view_netmask)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("netmask --help");
            }
        });
        ((CardView) findViewById(R.id.card_view_arpspoof)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("sudo arpspoof");
            }
        });
        ((CardView) findViewById(R.id.card_view_responder)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("sudo responder -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_ldapdomaindump)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("ldapdomaindump -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_bettercap)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("sudo bettercap");
            }
        });
        ((CardView) findViewById(R.id.card_view_socat)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("socat -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_godoh)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("godoh -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_chisel)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("chisel");
            }
        });
        ((CardView) findViewById(R.id.card_view_dns2tcp)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("dns2tcpc");
            }
        });
        ((CardView) findViewById(R.id.card_view_udp2raw)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("udp2raw");
            }
        });
        ((CardView) findViewById(R.id.card_view_dns2proxy)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("dns2proxy -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_dnschef)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("dnschef -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_tshark)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("tshark --help");
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("eigrp_cli -h");
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("sdnpwn");
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("camscan --help");
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("cdpsnarf");
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("bgp_cli -h");
            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("sudo yersinia -h");
            }
        });
        cardView7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("sudo miranda");
            }
        });
        cardView8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("upnp_tools");
            }
        });
        cardView9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("kill_router6");
            }
        });
        cardView10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("detect_sniffer6");
            }
        });
        cardView11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("fake_advertise6");
            }
        });
        cardView12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("fake_dhcps6");
            }
        });
        cardView13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("fake_dns6d");
            }
        });
        cardView14.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("fake_dnsupdate6");
            }
        });
        cardView15.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("fake_mld26");
            }
        });
        cardView16.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("fake_mld6");
            }
        });
        cardView17.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("fake_mldrouter6");
            }
        });
        cardView18.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("fake_router26");
            }
        });
        cardView19.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("fake_router6");
            }
        });
        cardView20.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("fake_solicitate6");
            }
        });
        cardView21.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("implementation6");
            }
        });
        cardView22.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("parasite6");
            }
        });
        cardView23.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("redir6");
            }
        });
        cardView24.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("smurf6");
            }
        });
        cardView25.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("sudo delorean -h");
            }
        });
        cardView26.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("smbmap");
            }
        });
        cardView27.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("fiked");
            }
        });
        cardView28.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_network_hacking.this.run_hack_cmd("sudo dhcping");
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
