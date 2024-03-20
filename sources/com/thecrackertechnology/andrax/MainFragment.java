package com.thecrackertechnology.andrax;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.thecrackertechnology.dragonterminal.bridge.Bridge;

public class MainFragment extends Fragment implements View.OnClickListener {
    public void clickimagerefresh() {
    }

    public void onClick(View view) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_main, viewGroup, false);
        ((ImageView) inflate.findViewById(R.id.imageViewmainbanner)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainFragment.this.clickimagerefresh();
            }
        });
        ((Button) inflate.findViewById(R.id.btnrun)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainFragment.this.run_hack_cmd("andrax");
            }
        });
        ((Button) inflate.findViewById(R.id.btntutorials)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainFragment.this.startActivity(new Intent(MainFragment.this.getActivity(), TutorialActivity.class));
            }
        });
        ((Button) inflate.findViewById(R.id.btnyoutube)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.youtube.com/channel/UCbjj-0WFsITWSb_Xf9WE5EQ")));
            }
        });
        ((Button) inflate.findViewById(R.id.btncontact)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SENDTO");
                intent.setData(Uri.parse("mailto:weidsom@thecrackertechnology.com?subject=" + Uri.encode("ANDRAX Contact")));
                try {
                    MainFragment.this.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                }
            }
        });
        ((Button) inflate.findViewById(R.id.btnmanual)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://andrax.thecrackertechnology.com/documentation/")));
            }
        });
        return inflate;
    }

    public void clicktraining() {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://androidhacking.thecrackertechnology.com/")));
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
