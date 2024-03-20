package com.thecrackertechnology.andrax;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.thecrackertechnology.dragonterminal.bridge.Bridge;

public class DnstoolFragment extends Fragment implements View.OnClickListener {
    public String domainssl;
    EditText editTextdomain;
    EditText editTexthost;
    EditText editTextinterface;
    public String hostssl;
    public String interfacessl;

    public void onClick(View view) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_dnstool, viewGroup, false);
        this.editTextinterface = (EditText) inflate.findViewById(R.id.editinterface);
        this.editTextdomain = (EditText) inflate.findViewById(R.id.editdomain);
        this.editTexthost = (EditText) inflate.findViewById(R.id.edithost);
        ((Button) inflate.findViewById(R.id.btnsslcracker)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DnstoolFragment dnstoolFragment = DnstoolFragment.this;
                dnstoolFragment.interfacessl = dnstoolFragment.editTextinterface.getText().toString();
                DnstoolFragment dnstoolFragment2 = DnstoolFragment.this;
                dnstoolFragment2.domainssl = dnstoolFragment2.editTextdomain.getText().toString();
                DnstoolFragment dnstoolFragment3 = DnstoolFragment.this;
                dnstoolFragment3.hostssl = dnstoolFragment3.editTexthost.getText().toString();
                DnstoolFragment dnstoolFragment4 = DnstoolFragment.this;
                dnstoolFragment4.run_hack_cmd("sslcracker -i " + DnstoolFragment.this.interfacessl + " -dns " + DnstoolFragment.this.domainssl + " -ip " + DnstoolFragment.this.hostssl);
            }
        });
        return inflate;
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void run_hack_cmd(String str) {
        Intent createExecuteIntent = Bridge.createExecuteIntent(str);
        createExecuteIntent.setFlags(131072);
        startActivity(createExecuteIntent);
    }
}
