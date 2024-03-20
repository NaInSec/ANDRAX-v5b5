package com.thecrackertechnology.andrax;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import com.thecrackertechnology.dragonterminal.bridge.Bridge;

public class Dco_Password_Hacking extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.dco_password_hacking);
        CardView cardView = (CardView) findViewById(R.id.card_view_cr3d0v3r);
        ((CardView) findViewById(R.id.card_view_hydra)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Password_Hacking.this.run_hack_cmd("hydra");
            }
        });
        ((CardView) findViewById(R.id.card_view_ncrack)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Password_Hacking.this.run_hack_cmd("ncrack");
            }
        });
        ((CardView) findViewById(R.id.card_view_john)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Password_Hacking.this.run_hack_cmd("john");
            }
        });
        ((CardView) findViewById(R.id.card_view_hashcat)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Password_Hacking.this.run_hack_cmd("hashcat -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_ciscopwdecrypt)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Password_Hacking.this.run_hack_cmd("cisco_pwdecrypt -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_hashboy)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Password_Hacking.this.run_hack_cmd("hashboy");
            }
        });
        ((CardView) findViewById(R.id.card_view_crunch)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Password_Hacking.this.run_hack_cmd("crunch");
            }
        });
        ((CardView) findViewById(R.id.card_view_maskprocessor)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Password_Hacking.this.run_hack_cmd("maskprocessor --help");
            }
        });
        ((CardView) findViewById(R.id.card_view_cewl)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Password_Hacking.this.run_hack_cmd("cewl -h");
            }
        });
        ((CardView) findViewById(R.id.card_view_massh)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Password_Hacking.this.run_hack_cmd("sudo massh-enum --help");
            }
        });
        ((CardView) findViewById(R.id.card_view_acccheck)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Password_Hacking.this.run_hack_cmd("acccheck");
            }
        });
        ((CardView) findViewById(R.id.card_view_ssh_auditor)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Password_Hacking.this.run_hack_cmd("ssh-auditor");
            }
        });
        ((CardView) findViewById(R.id.card_view_bopscrk)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Password_Hacking.this.run_hack_cmd("bopscrk");
            }
        });
        ((CardView) findViewById(R.id.card_view_pskcrack)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Password_Hacking.this.run_hack_cmd("psk-crack");
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dco_Password_Hacking.this.run_hack_cmd("cr3d0v3r -h");
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
