package com.thecrackertechnology.andrax;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TutorialActivity extends AppCompatActivity {
    public static final int CONNECTION_TIMEOUT = 20000;
    public static final int READ_TIMEOUT = 25000;
    /* access modifiers changed from: private */
    public AdapterTutorial mAdapter;
    /* access modifiers changed from: private */
    public RecyclerView mRTutorial;
    /* access modifiers changed from: private */
    public ProgressBar progressBarTutorial;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_tutorial);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.progressBarTutorial = (ProgressBar) findViewById(R.id.progressTutorial);
        new AsyncGetTutorials().execute(new String[0]);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tutorials, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_submit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle((CharSequence) "Submit Tutorial");
            builder.setMessage((CharSequence) "If you have a channel on youtube, website, blog... about computer security or hacking send your tutorial and we will help spread your work\n\nOnly tutorials or articles about ANDRAX!");
            builder.setIcon((int) R.mipmap.ic_launcher);
            builder.setCancelable(false);
            builder.setPositiveButton((CharSequence) "SUBMIT", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent("android.intent.action.SENDTO");
                    intent.setData(Uri.parse("mailto:weidsom@thecrackertechnology.com?subject=" + Uri.encode("Submit Tutorial")));
                    try {
                        TutorialActivity.this.startActivity(intent);
                    } catch (ActivityNotFoundException unused) {
                    }
                }
            });
            builder.setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.create().show();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private class AsyncGetTutorials extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        URL url;

        private AsyncGetTutorials() {
            this.url = null;
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
        }

        /* access modifiers changed from: protected */
        public String doInBackground(String... strArr) {
            try {
                this.url = new URL("https://andrax.thecrackertechnology.com/tutorials.json");
                try {
                    this.conn = (HttpURLConnection) this.url.openConnection();
                    this.conn.setReadTimeout(TutorialActivity.READ_TIMEOUT);
                    this.conn.setConnectTimeout(TutorialActivity.CONNECTION_TIMEOUT);
                    this.conn.setInstanceFollowRedirects(true);
                    this.conn.setRequestMethod("GET");
                    this.conn.setDoOutput(false);
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.conn.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                return sb.toString();
                            }
                            sb.append(readLine);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        return e.toString();
                    } finally {
                        this.conn.disconnect();
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return e2.toString();
                }
            } catch (MalformedURLException e3) {
                e3.printStackTrace();
                return e3.toString();
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String str) {
            TutorialActivity.this.progressBarTutorial.setVisibility(4);
            TutorialActivity.this.progressBarTutorial.setVisibility(4);
            hacktutorials(str);
        }

        public void hacktutorials(String str) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    DataTutorial dataTutorial = new DataTutorial();
                    dataTutorial.TutorialImage = jSONObject.getString("tutorialimg");
                    dataTutorial.TutorialName = jSONObject.getString("tutorialname");
                    dataTutorial.Tutorialdesc = jSONObject.getString("tutorialdesc");
                    dataTutorial.Tutoriallink = jSONObject.getString("tutoriallink");
                    arrayList.add(dataTutorial);
                }
                RecyclerView unused = TutorialActivity.this.mRTutorial = (RecyclerView) TutorialActivity.this.findViewById(R.id.tutorialrecycle);
                AdapterTutorial unused2 = TutorialActivity.this.mAdapter = new AdapterTutorial(TutorialActivity.this, arrayList);
                TutorialActivity.this.mRTutorial.setAdapter(TutorialActivity.this.mAdapter);
                TutorialActivity.this.mRTutorial.setLayoutManager(new GridLayoutManager(TutorialActivity.this, 2));
            } catch (JSONException e) {
                Toast.makeText(TutorialActivity.this, e.toString(), 1).show();
            }
        }
    }
}
