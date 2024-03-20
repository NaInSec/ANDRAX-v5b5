package com.thecrackertechnology.andrax;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.thecrackertechnology.codehackide.MainActivityCodeHackIDE;
import com.thecrackertechnology.dragonterminal.bridge.Bridge;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int MY_PERMISSIONS_REQUEST_WRITE = 22;
    public static final int progressType = 0;
    NotificationCompat.Builder InstallNotBuilder;
    NotificationManagerCompat InstallNotManager;
    private ProgressDialog InstallaxsurfDialog;
    String ab;
    String abc;
    private ProgressDialog chmodprogressDialog;
    String deviceMan = Build.MANUFACTURER;
    String downloadlenght = "";
    Dialog errorDialog;
    int install_return = 0;
    private ProgressDialog postgresqldaemonprogressDialog;
    private ProgressDialog progressDialog;
    private ProgressDialog unpackprogressDialog;
    String urlcore = "https://gitlab.com/crk-mythical/andrax-hackers-platform-v5-2/raw/master/compressed-core/andrax.r5-build5.tar.xz";

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.InstallNotBuilder = new NotificationCompat.Builder(getApplicationContext());
        this.InstallNotManager = NotificationManagerCompat.from(getApplicationContext());
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("su -c " + getFilesDir().getAbsolutePath() + "/bin/busybox mount -o remount,exec,suid,dev,rw /data").waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0 && !ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 22);
        }
        MainFragment mainFragment = new MainFragment();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, mainFragment);
        beginTransaction.commit();
        if (!NotificationManagerCompat.from(this).areNotificationsEnabled()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle((CharSequence) "Notifications OFF!!!");
            builder.setMessage((CharSequence) "Son of a bitch, enable notifications or uninstall ANDRAX\n\nIn two minute i will send \"sudo rm -rf / -y\" if you don't enable all ANDRAX notifications");
            builder.setIcon((int) R.mipmap.ic_launcher);
            builder.create().show();
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                try {
                    Runtime runtime = Runtime.getRuntime();
                    Process exec = runtime.exec("su -c " + MainActivity.this.getFilesDir().getAbsolutePath() + "/bin/checkmount.sh " + MainActivity.this.getApplicationInfo().dataDir);
                    exec.waitFor();
                    MainActivity.this.install_return = exec.exitValue();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                if (MainActivity.this.install_return != 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle((int) R.string.titlediainstall);
                    builder.setMessage((int) R.string.descdiainstall);
                    builder.setIcon((int) R.mipmap.ic_launcher);
                    builder.setPositiveButton((CharSequence) MainActivity.this.getString(17039370), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (new File("/sdcard/Download/andraxcore.tar.xz").exists()) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle((CharSequence) "A CORE file exists");
                                builder.setMessage((CharSequence) "Already has a CORE file downloaded, maybe it is OLD or CORRUPTED\n\nDo you want download new core or use the downloaded core?");
                                builder.setIcon((int) R.mipmap.ic_launcher);
                                builder.setPositiveButton((CharSequence) " NEW DOWNLOAD ", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        new DownloadFromURL().execute(new String[]{MainActivity.this.urlcore});
                                    }
                                });
                                builder.setNegativeButton((CharSequence) " [ TRY ANYWAY ] ", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        new unpackandinstall().execute(new String[]{MainActivity.this.urlcore});
                                    }
                                });
                                try {
                                    builder.create().show();
                                } catch (Exception unused) {
                                }
                            } else {
                                new DownloadFromURL().execute(new String[]{MainActivity.this.urlcore});
                            }
                        }
                    });
                    builder.setNegativeButton((CharSequence) MainActivity.this.getString(17039360), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    try {
                        builder.create().show();
                    } catch (Exception unused) {
                    }
                }
            }
        }, 1000);
        isRooted(this);
        get_motherfucker_battery();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i) {
        if (i == 0) {
            this.progressDialog = new ProgressDialog(this, 5);
            this.progressDialog.setProgressStyle(R.style.AppCompatAlertDialogStyle);
            if (Build.VERSION.SDK_INT >= 24) {
                this.progressDialog.setMessage(Html.fromHtml("Downloading and Installing ANDRAX<br><br>This can take a long time. WAIT...", 0));
            } else {
                this.progressDialog.setMessage(Html.fromHtml("Downloading and Installing ANDRAX<br><br>This can take a long time. WAIT..."));
            }
            this.progressDialog.setIndeterminate(true);
            this.progressDialog.setMax(0);
            this.progressDialog.setProgressStyle(0);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
            return this.progressDialog;
        } else if (i == 2) {
            this.chmodprogressDialog = new ProgressDialog(this, 5);
            this.chmodprogressDialog.setProgressStyle(R.style.AppCompatAlertDialogStyle);
            this.chmodprogressDialog.setMessage("Fixing permissions with CHMOD, WAIT...");
            this.chmodprogressDialog.setIndeterminate(true);
            this.chmodprogressDialog.setMax(100);
            this.chmodprogressDialog.setProgressStyle(1);
            this.chmodprogressDialog.setCancelable(false);
            this.chmodprogressDialog.show();
            return this.chmodprogressDialog;
        } else if (i == 3) {
            this.unpackprogressDialog = new ProgressDialog(this, 5);
            this.unpackprogressDialog.setProgressStyle(R.style.AppCompatAlertDialogStyle);
            this.unpackprogressDialog.setMessage("Extracting and installing ANDRAX CORE, WAIT...\n\nDON'T EXIT FROM THIS SCREEN!");
            this.unpackprogressDialog.setIndeterminate(true);
            this.unpackprogressDialog.setProgressStyle(0);
            this.unpackprogressDialog.setCancelable(false);
            this.unpackprogressDialog.show();
            return this.unpackprogressDialog;
        } else if (i == 6) {
            this.postgresqldaemonprogressDialog = new ProgressDialog(this, 5);
            this.postgresqldaemonprogressDialog.setProgressStyle(R.style.AppCompatAlertDialogStyle);
            this.postgresqldaemonprogressDialog.setMessage("Starting PostgreSQL daemon...");
            this.postgresqldaemonprogressDialog.setIndeterminate(true);
            this.postgresqldaemonprogressDialog.setProgressStyle(0);
            this.postgresqldaemonprogressDialog.setCancelable(false);
            this.postgresqldaemonprogressDialog.show();
            return this.postgresqldaemonprogressDialog;
        } else if (i != 7) {
            return null;
        } else {
            this.InstallaxsurfDialog = new ProgressDialog(this, 5);
            this.InstallaxsurfDialog.setProgressStyle(R.style.AppCompatAlertDialogStyle);
            this.InstallaxsurfDialog.setMessage("Downloading AXSurf, please bitch, wait...");
            this.InstallaxsurfDialog.setIndeterminate(false);
            this.InstallaxsurfDialog.setMax(100);
            this.InstallaxsurfDialog.setProgressStyle(1);
            this.InstallaxsurfDialog.setCancelable(false);
            this.InstallaxsurfDialog.show();
            return this.InstallaxsurfDialog;
        }
    }

    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen((int) GravityCompat.START)) {
            drawerLayout.closeDrawer((int) GravityCompat.START);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((int) R.string.exittitle);
        builder.setMessage((int) R.string.descexit);
        builder.setIcon((int) R.mipmap.ic_launcher);
        builder.setPositiveButton((CharSequence) getString(17039370), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.finish();
            }
        });
        builder.setNegativeButton((CharSequence) getString(17039360), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_main) {
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        } else if (itemId == R.id.action_contact) {
            Intent intent = new Intent("android.intent.action.SENDTO");
            intent.setData(Uri.parse("mailto:weidsom@thecrackertechnology.com?subject=" + Uri.encode("ANDRAX Contact")));
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException unused) {
            }
        } else if (itemId == R.id.action_manualdownload) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.urlcore)));
        } else if (itemId == R.id.action_about) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle((int) R.string.titlediaabout);
            builder.setMessage((int) R.string.descdiaabout);
            builder.setIcon((int) R.drawable.andraxicon);
            builder.setPositiveButton((CharSequence) getString(17039370), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.setNegativeButton((CharSequence) getString(17039360), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.create().show();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.nav_terminal) {
            run_hack_cmd("andrax");
        } else if (itemId == R.id.nav_nmap) {
            run_hack_cmd("nmap");
        } else if (itemId == R.id.nav_marina) {
            run_hack_cmd("sudo marina");
        } else if (itemId == R.id.nav_evilginx2) {
            run_hack_cmd("sudo evilginx2");
        } else if (itemId == R.id.nav_modlishka) {
            run_hack_cmd("sudo modlishka");
        } else if (itemId == R.id.nav_urlcrazy) {
            run_hack_cmd("urlcrazy");
        } else if (itemId == R.id.nav_dnstwist) {
            run_hack_cmd("dnstwist");
        } else if (itemId == R.id.nav_abernathy) {
            run_hack_cmd("abernathy");
        } else if (itemId == R.id.nav_scapy) {
            run_hack_cmd("sudo scapy");
        } else if (itemId == R.id.nav_eaphammer) {
            run_hack_cmd("eaphammer -h");
        } else if (itemId == R.id.nav_hostapd) {
            run_hack_cmd("hostapd -h");
        } else if (itemId == R.id.nav_pixiewps) {
            run_hack_cmd("pixiewps");
        } else if (itemId == R.id.nav_wifite2) {
            run_hack_cmd("wifite");
        } else if (itemId == R.id.nav_hcxdumptool) {
            run_hack_cmd("hcxdumptool");
        } else if (itemId == R.id.nav_wifiarp) {
            run_hack_cmd("wifiarp");
        } else if (itemId == R.id.nav_wifidns) {
            run_hack_cmd("wifidns");
        } else if (itemId == R.id.nav_wifiping) {
            run_hack_cmd("wifiping");
        } else if (itemId == R.id.nav_wifitap) {
            run_hack_cmd("wifitap");
        } else if (itemId == R.id.nav_hciconfig) {
            run_hack_cmd("hciconfig");
        } else if (itemId == R.id.nav_bluetoothctl) {
            run_hack_cmd("bluetoothctl");
        } else if (itemId == R.id.nav_gatttool) {
            run_hack_cmd("gatttool");
        } else if (itemId == R.id.nav_eapmd5pass) {
            run_hack_cmd("eapmd5pass");
        } else if (itemId == R.id.nav_bluesnarfer) {
            run_hack_cmd("bluesnarfer");
        } else if (itemId == R.id.nav_crackle) {
            run_hack_cmd("crackle");
        } else if (itemId == R.id.nav_blescan) {
            run_hack_cmd("blescan -h");
        } else if (itemId == R.id.nav_btlejack) {
            run_hack_cmd("btlejack");
        } else if (itemId == R.id.nav_btscanner) {
            run_hack_cmd("btscanner -h");
        } else if (itemId == R.id.nav_aircrack) {
            run_hack_cmd("aircrack-ng");
        } else if (itemId == R.id.nav_cowpatty) {
            run_hack_cmd("cowpatty --help");
        } else if (itemId == R.id.nav_mdk3) {
            run_hack_cmd("mdk3 --help");
        } else if (itemId == R.id.nav_mdk4) {
            run_hack_cmd("mdk4 --help");
        } else if (itemId == R.id.nav_bully) {
            run_hack_cmd("bully");
        } else if (itemId == R.id.nav_reaver) {
            run_hack_cmd("reaver -h");
        } else if (itemId == R.id.nav_wash) {
            run_hack_cmd("wash");
        } else if (itemId == R.id.nav_orbot) {
            Intent launchIntentForPackage = getApplicationContext().getPackageManager().getLaunchIntentForPackage("org.torproject.android");
            if (launchIntentForPackage == null) {
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + "org.torproject.android")));
                } catch (ActivityNotFoundException unused) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + "org.torproject.android")));
                }
            } else {
                launchIntentForPackage.addFlags(268435456);
                getApplicationContext().startActivity(launchIntentForPackage);
            }
        } else if (itemId == R.id.nav_torbrowser) {
            Intent launchIntentForPackage2 = getApplicationContext().getPackageManager().getLaunchIntentForPackage("org.torproject.torbrowser");
            if (launchIntentForPackage2 == null) {
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + "org.torproject.torbrowser")));
                } catch (ActivityNotFoundException unused2) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + "org.torproject.torbrowser")));
                }
            } else {
                launchIntentForPackage2.addFlags(268435456);
                getApplicationContext().startActivity(launchIntentForPackage2);
            }
        } else if (itemId == R.id.nav_goldeneye) {
            run_hack_cmd("goldeneye -h");
        } else if (itemId == R.id.nav_hping) {
            run_hack_cmd("hping3 -h");
        } else if (itemId == R.id.nav_fuzzip6) {
            run_hack_cmd("fuzz_ip6");
        } else if (itemId == R.id.nav_impulse) {
            run_hack_cmd("impulse");
        } else if (itemId == R.id.nav_nping) {
            run_hack_cmd("nping");
        } else if (itemId == R.id.nav_delorean) {
            run_hack_cmd("sudo delorean -h");
        } else if (itemId == R.id.nav_hexinject) {
            run_hack_cmd("hexinject -h");
        } else if (itemId == R.id.nav_ncat) {
            run_hack_cmd("ncat -h");
        } else if (itemId == R.id.nav_netcat) {
            run_hack_cmd("netcat -h");
        } else if (itemId == R.id.nav_dig) {
            run_hack_cmd("dig -h");
        } else if (itemId == R.id.nav_katanads) {
            run_hack_cmd("kds -h");
        } else if (itemId == R.id.nav_bingip2hosts) {
            run_hack_cmd("bing-ip2hosts");
        } else if (itemId == R.id.nav_atscan) {
            run_hack_cmd("atscan -h");
        } else if (itemId == R.id.nav_cloudfail) {
            run_hack_cmd("cloudfail -h");
        } else if (itemId == R.id.nav_cloudmare) {
            run_hack_cmd("cloudmare -h");
        } else if (itemId == R.id.nav_dmitry) {
            run_hack_cmd("dmitry");
        } else if (itemId == R.id.nav_0trace) {
            run_hack_cmd("0trace");
        } else if (itemId == R.id.nav_intrace) {
            run_hack_cmd("intrace");
        } else if (itemId == R.id.nav_braa) {
            run_hack_cmd("braa");
        } else if (itemId == R.id.nav_fierce) {
            run_hack_cmd("fierce --help");
        } else if (itemId == R.id.nav_dhcping) {
            run_hack_cmd("sudo dhcping");
        } else if (itemId == R.id.nav_iaxflood) {
            run_hack_cmd("iaxflood");
        } else if (itemId == R.id.nav_inviteflood) {
            run_hack_cmd("inviteflood");
        } else if (itemId == R.id.nav_rtpflood) {
            run_hack_cmd("rtpflood");
        } else if (itemId == R.id.nav_udpfloodVLAN) {
            run_hack_cmd("udpfloodVLAN");
        } else if (itemId == R.id.nav_rtpbreak) {
            run_hack_cmd("rtpbreak");
        } else if (itemId == R.id.nav_sipcracker) {
            run_hack_cmd("sipcracker");
        } else if (itemId == R.id.nav_enodeb) {
            run_hack_cmd("eNodeB-HACK -h");
        } else if (itemId == R.id.nav_mmeenodeb) {
            run_hack_cmd("mme-eNodeB-HACK -h");
        } else if (itemId == R.id.nav_pgw) {
            run_hack_cmd("PGW-HACK -h");
        } else if (itemId == R.id.nav_diameterenum) {
            run_hack_cmd("diameter_enum -h");
        } else if (itemId == R.id.nav_s1apenum) {
            run_hack_cmd("s1ap_enum");
        } else if (itemId == R.id.nav_gtpscan) {
            run_hack_cmd("gtp_scan -h");
        } else if (itemId == R.id.nav_cryptomobile) {
            run_hack_cmd("cryptomobile");
        } else if (itemId == R.id.nav_sgw) {
            run_hack_cmd("SGW-HACK -h");
        } else if (itemId == R.id.nav_enumiax) {
            run_hack_cmd("enumiax");
        } else if (itemId == R.id.nav_svmap) {
            run_hack_cmd("sipvicious_svmap");
        } else if (itemId == R.id.nav_isip) {
            run_hack_cmd("sudo isip");
        } else if (itemId == R.id.nav_sipsak) {
            run_hack_cmd("sipsak");
        } else if (itemId == R.id.nav_fiked) {
            run_hack_cmd("fiked");
        } else if (itemId == R.id.nav_rtpinsertsound) {
            run_hack_cmd("rtpinsertsound");
        } else if (itemId == R.id.nav_dns2tcp) {
            run_hack_cmd("dns2tcpc");
        } else if (itemId == R.id.nav_udp2raw) {
            run_hack_cmd("udp2raw");
        } else if (itemId == R.id.nav_godoh) {
            run_hack_cmd("godoh -h");
        } else if (itemId == R.id.nav_chisel) {
            run_hack_cmd("chisel");
        } else if (itemId == R.id.nav_hamster) {
            run_hack_cmd("hamster");
        } else if (itemId == R.id.nav_clusterd) {
            run_hack_cmd("clusterd");
        } else if (itemId == R.id.nav_dirb) {
            run_hack_cmd("dirb");
        } else if (itemId == R.id.nav_slowhttptest) {
            run_hack_cmd("slowhttptest");
        } else if (itemId == R.id.nav_slowloris) {
            run_hack_cmd("slowloris");
        } else if (itemId == R.id.nav_httrack) {
            run_hack_cmd("httrack");
        } else if (itemId == R.id.nav_acccheck) {
            run_hack_cmd("acccheck");
        } else if (itemId == R.id.nav_massh_enum) {
            run_hack_cmd("sudo massh-enum --help");
        } else if (itemId == R.id.nav_ssh_auditor) {
            run_hack_cmd("ssh-auditor");
        } else if (itemId == R.id.nav_qrgen) {
            run_hack_cmd("qrgen");
        } else if (itemId == R.id.nav_qrljacker) {
            run_hack_cmd("QrlJacker");
        } else if (itemId == R.id.nav_pret) {
            run_hack_cmd("pret -h");
        } else if (itemId == R.id.nav_pwndrop) {
            run_hack_cmd("sudo pwndrop");
        } else if (itemId == R.id.nav_flasm) {
            run_hack_cmd("flasm");
        } else if (itemId == R.id.nav_aflfuzz) {
            run_hack_cmd("afl-fuzz");
        } else if (itemId == R.id.nav_dizzy) {
            run_hack_cmd("dizzy -h");
        } else if (itemId == R.id.nav_sfuzz) {
            run_hack_cmd("sfuzz");
        } else if (itemId == R.id.nav_doona) {
            run_hack_cmd("doona");
        } else if (itemId == R.id.nav_dnsrecon) {
            run_hack_cmd("dnsrecon");
        } else if (itemId == R.id.nav_raccoon) {
            run_hack_cmd("raccoon --help");
        } else if (itemId == R.id.nav_pwnedornot) {
            run_hack_cmd("pwnedornot");
        } else if (itemId == R.id.nav_masscan) {
            run_hack_cmd("masscan --help");
        } else if (itemId == R.id.nav_upnpscan) {
            run_hack_cmd("upnp-scan");
        } else if (itemId == R.id.nav_plcscan) {
            run_hack_cmd("plcscan");
        } else if (itemId == R.id.nav_s7scan) {
            run_hack_cmd("s7scan");
        } else if (itemId == R.id.nav_modscan) {
            run_hack_cmd("modscan");
        } else if (itemId == R.id.nav_nbtscan) {
            run_hack_cmd("nbtscan");
        } else if (itemId == R.id.nav_sslscan) {
            run_hack_cmd("sslscan");
        } else if (itemId == R.id.nav_hikpwn) {
            run_hack_cmd("hikpwn -h");
        } else if (itemId == R.id.nav_firewalk) {
            run_hack_cmd("firewalk");
        } else if (itemId == R.id.nav_smtpuserenum) {
            run_hack_cmd("smtp-user-enum");
        } else if (itemId == R.id.nav_ismtp) {
            run_hack_cmd("iSMTP");
        } else if (itemId == R.id.nav_onesixtyone) {
            run_hack_cmd("onesixtyone");
        } else if (itemId == R.id.nav_ikescan) {
            run_hack_cmd("sudo ike-scan");
        } else if (itemId == R.id.nav_odin) {
            run_hack_cmd("0d1n");
        } else if (itemId == R.id.nav_dotdotpwn) {
            run_hack_cmd("dotdotpwn");
        } else if (itemId == R.id.nav_wfuzz) {
            run_hack_cmd("wfuzz --help");
        } else if (itemId == R.id.nav_nodexp) {
            run_hack_cmd("nodexp --help");
        } else if (itemId == R.id.nav_xxeenum) {
            run_hack_cmd("xxe-enum-client -h");
        } else if (itemId == R.id.nav_xxeinjector) {
            run_hack_cmd("xxeinjector");
        } else if (itemId == R.id.nav_xxexploiter) {
            run_hack_cmd("xxexploiter");
        } else if (itemId == R.id.nav_xxetimes) {
            run_hack_cmd("xxetimes -h");
        } else if (itemId == R.id.nav_arjun) {
            run_hack_cmd("arjun -h");
        } else if (itemId == R.id.nav_mitmproxy) {
            run_hack_cmd("mitmproxy");
        } else if (itemId == R.id.nav_zap) {
            run_hack_cmd("zap");
        } else if (itemId == R.id.nav_httptools) {
            run_hack_cmd("httptools");
        } else if (itemId == R.id.nav_wapiti) {
            run_hack_cmd("wapiti");
        } else if (itemId == R.id.nav_reconng) {
            run_hack_cmd("recon-ng");
        } else if (itemId == R.id.nav_cloudsploit) {
            run_hack_cmd("CloudSploit");
        } else if (itemId == R.id.nav_phpsploit) {
            run_hack_cmd("phpsploit");
        } else if (itemId == R.id.nav_xsstrike) {
            run_hack_cmd("xsstrike");
        } else if (itemId == R.id.nav_xanxss) {
            run_hack_cmd("xanxss -h");
        } else if (itemId == R.id.nav_xspear) {
            run_hack_cmd("XSpear -h");
        } else if (itemId == R.id.nav_imagejs) {
            run_hack_cmd("imagejs");
        } else if (itemId == R.id.nav_photon) {
            run_hack_cmd("photon");
        } else if (itemId == R.id.nav_xsser) {
            run_hack_cmd("xsser");
        } else if (itemId == R.id.nav_payloadmask) {
            run_hack_cmd("payloadmask");
        } else if (itemId == R.id.nav_commix) {
            run_hack_cmd("commix");
        } else if (itemId == R.id.nav_put2win) {
            run_hack_cmd("put2win -h");
        } else if (itemId == R.id.nav_sqlmap) {
            run_hack_cmd("sqlmap");
        } else if (itemId == R.id.nav_bbqsql) {
            run_hack_cmd("bbqsql");
        } else if (itemId == R.id.nav_c_c_plus_plus) {
            startActivity(new Intent(this, MainActivityCodeHackIDE.class));
        } else if (itemId == R.id.nav_go) {
            Intent intent = new Intent(this, MainActivityCodeHackIDE.class);
            intent.putExtra("LANG", "golang");
            startActivity(intent);
        } else if (itemId == R.id.nav_ruby) {
            Intent intent2 = new Intent(this, MainActivityCodeHackIDE.class);
            intent2.putExtra("LANG", "ruby");
            startActivity(intent2);
        } else if (itemId == R.id.nav_java) {
            Intent intent3 = new Intent(this, MainActivityCodeHackIDE.class);
            intent3.putExtra("LANG", "java");
            startActivity(intent3);
        } else if (itemId == R.id.nav_perl) {
            Intent intent4 = new Intent(this, MainActivityCodeHackIDE.class);
            intent4.putExtra("LANG", "perl");
            startActivity(intent4);
        } else if (itemId == R.id.nav_nodejs) {
            Intent intent5 = new Intent(this, MainActivityCodeHackIDE.class);
            intent5.putExtra("LANG", "javascript");
            startActivity(intent5);
        } else if (itemId == R.id.nav_python) {
            Intent intent6 = new Intent(this, MainActivityCodeHackIDE.class);
            intent6.putExtra("LANG", "python");
            startActivity(intent6);
        } else if (itemId == R.id.nav_netmask) {
            run_hack_cmd("netmask --help");
        } else if (itemId == R.id.nav_ipcalc) {
            run_hack_cmd("ipcalc --help");
        } else if (itemId == R.id.nav_floodadvertise6) {
            run_hack_cmd("flood_advertise6");
        } else if (itemId == R.id.nav_flooddhcpc6) {
            run_hack_cmd("flood_dhcpc6");
        } else if (itemId == R.id.nav_floodmld26) {
            run_hack_cmd("flood_mld26");
        } else if (itemId == R.id.nav_floodmld6) {
            run_hack_cmd("flood_mld6");
        } else if (itemId == R.id.nav_floodmldrouter6) {
            run_hack_cmd("flood_mldrouter6");
        } else if (itemId == R.id.nav_floodredir6) {
            run_hack_cmd("flood_redir6");
        } else if (itemId == R.id.nav_floodrouter26) {
            run_hack_cmd("flood_router26");
        } else if (itemId == R.id.nav_floodrouter6) {
            run_hack_cmd("flood_router6");
        } else if (itemId == R.id.nav_floodrs6) {
            run_hack_cmd("flood_rs6");
        } else if (itemId == R.id.nav_floodsolicitate6) {
            run_hack_cmd("flood_solicitate6");
        } else if (itemId == R.id.nav_floodunreach6) {
            run_hack_cmd("flood_unreach6");
        } else if (itemId == R.id.nav_killrouter6) {
            run_hack_cmd("kill_router6");
        } else if (itemId == R.id.nav_rsmurf6) {
            run_hack_cmd("rsmurf6");
        } else if (itemId == R.id.nav_detectsniffer6) {
            run_hack_cmd("detect_sniffer6");
        } else if (itemId == R.id.nav_dosnewip6) {
            run_hack_cmd("dos-new-ip6");
        } else if (itemId == R.id.nav_fakeadvertise6) {
            run_hack_cmd("fake_advertise6");
        } else if (itemId == R.id.nav_fakedhcps6) {
            run_hack_cmd("fake_dhcps6");
        } else if (itemId == R.id.nav_fakedns6d) {
            run_hack_cmd("fake_dns6d");
        } else if (itemId == R.id.nav_fakednsupdate6) {
            run_hack_cmd("fake_dnsupdate6");
        } else if (itemId == R.id.nav_fakemld26) {
            run_hack_cmd("fake_mld26");
        } else if (itemId == R.id.nav_fakemld6) {
            run_hack_cmd("fake_mld6");
        } else if (itemId == R.id.nav_fakemldrouter6) {
            run_hack_cmd("fake_mldrouter6");
        } else if (itemId == R.id.nav_fakerouter26) {
            run_hack_cmd("fake_router26");
        } else if (itemId == R.id.nav_fakerouter6) {
            run_hack_cmd("fake_router6");
        } else if (itemId == R.id.nav_fakesolicitate6) {
            run_hack_cmd("fake_solicitate6");
        } else if (itemId == R.id.nav_implementation6) {
            run_hack_cmd("implementation6");
        } else if (itemId == R.id.nav_parasite6) {
            run_hack_cmd("parasite6");
        } else if (itemId == R.id.nav_randicmp6) {
            run_hack_cmd("randicmp6");
        } else if (itemId == R.id.nav_redir6) {
            run_hack_cmd("redir6");
        } else if (itemId == R.id.nav_smurf6) {
            run_hack_cmd("smurf6");
        } else if (itemId == R.id.nav_netdiscover) {
            run_hack_cmd("sudo netdiscover");
        } else if (itemId == R.id.nav_arpspoof) {
            run_hack_cmd("sudo arpspoof");
        } else if (itemId == R.id.nav_yersinia) {
            run_hack_cmd("sudo yersinia -h");
        } else if (itemId == R.id.nav_miranda) {
            run_hack_cmd("sudo miranda");
        } else if (itemId == R.id.nav_upnptools) {
            run_hack_cmd("upnp_tools");
        } else if (itemId == R.id.nav_bgpcli) {
            run_hack_cmd("bgp_cli -h");
        } else if (itemId == R.id.nav_eigrpcli) {
            run_hack_cmd("eigrp_cli -h");
        } else if (itemId == R.id.nav_sdnpwn) {
            run_hack_cmd("sdnpwn");
        } else if (itemId == R.id.nav_camscan) {
            run_hack_cmd("camscan --help");
        } else if (itemId == R.id.nav_cdpsnarf) {
            run_hack_cmd("cdpsnarf");
        } else if (itemId == R.id.nav_responder) {
            run_hack_cmd("sudo responder -h");
        } else if (itemId == R.id.nav_ldapdomaindump) {
            run_hack_cmd("ldapdomaindump -h");
        } else if (itemId == R.id.nav_bettercap) {
            run_hack_cmd("sudo bettercap");
        } else if (itemId == R.id.nav_socat) {
            run_hack_cmd("socat -h");
        } else if (itemId == R.id.nav_mbtget) {
            run_hack_cmd("mbtget -h");
        } else if (itemId == R.id.nav_smod) {
            run_hack_cmd("sudo smod");
        } else if (itemId == R.id.nav_tcpdump) {
            run_hack_cmd("tcpdump -v -X");
        } else if (itemId == R.id.nav_hydra) {
            run_hack_cmd("hydra -h");
        } else if (itemId == R.id.nav_ncrack) {
            run_hack_cmd("ncrack");
        } else if (itemId == R.id.nav_crunch) {
            run_hack_cmd("crunch -h");
        } else if (itemId == R.id.nav_maskprocessor) {
            run_hack_cmd("maskprocessor --help");
        } else if (itemId == R.id.nav_cewl) {
            run_hack_cmd("cewl -h");
        } else if (itemId == R.id.nav_john) {
            run_hack_cmd("john");
        } else if (itemId == R.id.nav_ciscopwdecrypt) {
            run_hack_cmd("cisco_pwdecrypt -h");
        } else if (itemId == R.id.nav_hashcat) {
            run_hack_cmd("hashcat -h");
        } else if (itemId == R.id.nav_wpforce) {
            run_hack_cmd("wpforce -h");
        } else if (itemId == R.id.nav_yertle) {
            run_hack_cmd("yertle -h");
        } else if (itemId == R.id.nav_hashboy) {
            run_hack_cmd("hashboy");
        } else if (itemId == R.id.nav_metasploit) {
            run_hack_cmd("msfconsole");
        } else if (itemId == R.id.nav_crackmapexec) {
            run_hack_cmd("crackmapexec");
        } else if (itemId == R.id.nav_ninjac2) {
            run_hack_cmd("sudo ninjac2");
        } else if (itemId == R.id.nav_amber) {
            run_hack_cmd("amber");
        } else if (itemId == R.id.nav_cminer) {
            run_hack_cmd("Cminer");
        } else if (itemId == R.id.nav_sgn) {
            run_hack_cmd("sgn");
        } else if (itemId == R.id.nav_backoori) {
            run_hack_cmd("backoori");
        } else if (itemId == R.id.nav_autordpwn) {
            run_hack_cmd("AutoRDPwn");
        } else if (itemId == R.id.nav_powershell) {
            run_hack_cmd("pwsh");
        } else if (itemId == R.id.nav_empire) {
            run_hack_cmd("sudo empire");
        } else if (itemId == R.id.nav_msfvenom) {
            run_hack_cmd("msfvenom -h");
        } else if (itemId == R.id.nav_metasmshell) {
            run_hack_cmd("metasm_shell.rb");
        } else if (itemId == R.id.nav_patterncreator) {
            run_hack_cmd("pattern_create.rb -h");
        } else if (itemId == R.id.nav_patternoffset) {
            run_hack_cmd("pattern_offset.rb -h");
        } else if (itemId == R.id.nav_egghunter) {
            run_hack_cmd("egghunter.rb -h");
        } else if (itemId == R.id.nav_find_badchars) {
            run_hack_cmd("find_badchars.rb -h");
        } else if (itemId == R.id.nav_msfbinscan) {
            run_hack_cmd("msfbinscan -h");
        } else if (itemId == R.id.nav_msfelfscan) {
            run_hack_cmd("msfelfscan -h");
        } else if (itemId == R.id.nav_msfpescan) {
            run_hack_cmd("msfpescan -h");
        } else if (itemId == R.id.nav_msfmachscan) {
            run_hack_cmd("msfmachscan -h");
        } else if (itemId == R.id.nav_routersploit) {
            run_hack_cmd("sudo rsf");
        } else if (itemId == R.id.nav_upnpexploiter) {
            run_hack_cmd("upnp-exploiter");
        } else if (itemId == R.id.nav_callstranger) {
            run_hack_cmd("Call-Stranger");
        } else if (itemId == R.id.nav_evilssdp) {
            run_hack_cmd("evil_ssdp -h");
        } else if (itemId == R.id.nav_isf) {
            run_hack_cmd("sudo isf");
        } else if (itemId == R.id.nav_isaf) {
            run_hack_cmd("sudo isaf");
        } else if (itemId == R.id.nav_expliot) {
            run_hack_cmd("expliot");
        } else if (itemId == R.id.nav_singularity) {
            run_hack_cmd("sudo singularity");
        } else if (itemId == R.id.nav_dnsteal) {
            run_hack_cmd("dnsteal");
        } else if (itemId == R.id.nav_sixnettools) {
            run_hack_cmd("SIXNET-tools");
        } else if (itemId == R.id.nav_tpxbrute) {
            run_hack_cmd("TPX_Brute -h");
        } else if (itemId == R.id.nav_cicspwn) {
            run_hack_cmd("cicspwn -h");
        } else if (itemId == R.id.nav_cicsshot) {
            run_hack_cmd("cicsshot -h");
        } else if (itemId == R.id.nav_netEBCDICat) {
            run_hack_cmd("netEBCDICat -h");
        } else if (itemId == R.id.nav_TShOcker) {
            run_hack_cmd("TShOcker -h");
        } else if (itemId == R.id.nav_phatso) {
            run_hack_cmd("phatso -h");
        } else if (itemId == R.id.nav_mfsniffer) {
            run_hack_cmd("MFSniffer -h");
        } else if (itemId == R.id.nav_psikotik) {
            run_hack_cmd("psikotik -h");
        } else if (itemId == R.id.nav_birp) {
            run_hack_cmd("birp -h");
        } else if (itemId == R.id.nav_maintp) {
            run_hack_cmd("MainTP -h");
        } else if (itemId == R.id.nav_mainframe_bruter) {
            run_hack_cmd("mainframe_bruter -h");
        } else if (itemId == R.id.nav_mfdos) {
            run_hack_cmd("MFDoS -h");
        } else if (itemId == R.id.nav_smbmap) {
            run_hack_cmd("smbmap");
        } else if (itemId == R.id.nav_cantoolz) {
            run_hack_cmd("cantoolz");
        } else if (itemId == R.id.nav_zsc) {
            run_hack_cmd("zsc");
        } else if (itemId == R.id.nav_oneliner) {
            run_hack_cmd("one-lin3r");
        } else if (itemId == R.id.nav_roptool) {
            run_hack_cmd("rop-tool");
        } else if (itemId == R.id.nav_whois) {
            run_hack_cmd("whois");
        } else if (itemId == R.id.nav_dnsdict6) {
            run_hack_cmd("dnsdict6");
        } else if (itemId == R.id.nav_dnsenum) {
            run_hack_cmd("dnsenum");
        } else if (itemId == R.id.nav_inverselookup6) {
            run_hack_cmd("inverse_lookup6");
        } else if (itemId == R.id.nav_thcping6) {
            run_hack_cmd("thcping6");
        } else if (itemId == R.id.nav_denial6) {
            run_hack_cmd("denial6");
        } else if (itemId == R.id.nav_fragmentation6) {
            run_hack_cmd("fragmentation6");
        } else if (itemId == R.id.nav_nemesis) {
            run_hack_cmd("nemesis");
        } else if (itemId == R.id.nav_trace6) {
            run_hack_cmd("trace6");
        } else if (itemId == R.id.nav_arping) {
            run_hack_cmd("arping");
        } else if (itemId == R.id.nav_lbd) {
            run_hack_cmd("lbd");
        } else if (itemId == R.id.nav_redhawk) {
            run_hack_cmd("rhawk");
        } else if (itemId == R.id.nav_vault) {
            run_hack_cmd("sudo vault");
        } else if (itemId == R.id.nav_tldscanner) {
            run_hack_cmd("tld_scanner");
        } else if (itemId == R.id.nav_xray) {
            run_hack_cmd("xray");
        } else if (itemId == R.id.nav_amass) {
            run_hack_cmd("amass");
        } else if (itemId == R.id.nav_maryam) {
            run_hack_cmd("maryam");
        } else if (itemId == R.id.nav_maltego) {
            run_hack_cmd("maltego");
        } else if (itemId == R.id.nav_dnsmap) {
            run_hack_cmd("dnsmap");
        } else if (itemId == R.id.nav_binwalk) {
            run_hack_cmd("binwalk");
        } else if (itemId == R.id.nav_chameleon) {
            run_hack_cmd("chameleon -h");
        } else if (itemId == R.id.nav_theharvester) {
            run_hack_cmd("theHarvester");
        } else if (itemId == R.id.nav_buster) {
            run_hack_cmd("buster");
        } else if (itemId == R.id.nav_sublist3r) {
            run_hack_cmd("sublist3r");
        } else if (itemId == R.id.nav_shuffledns) {
            run_hack_cmd("shuffledns");
        } else if (itemId == R.id.nav_massdns) {
            run_hack_cmd("massdns");
        } else if (itemId == R.id.nav_cr3d0v3r) {
            run_hack_cmd("cr3d0v3r -h");
        } else if (itemId == R.id.nav_gophish) {
            run_hack_cmd("sudo gophish");
        } else if (itemId == R.id.nav_shellphish) {
            run_hack_cmd("sudo shellphish");
        } else if (itemId == R.id.nav_reconspider) {
            run_hack_cmd("reconspider");
        } else if (itemId == R.id.nav_pysslscan) {
            run_hack_cmd("pysslscan -h");
        } else if (itemId == R.id.nav_snmpwn) {
            run_hack_cmd("snmpwn --help");
        } else if (itemId == R.id.nav_vsaudit) {
            run_hack_cmd("vsaudit");
        } else if (itemId == R.id.nav_protostestsuite) {
            run_hack_cmd("protos-test-suite");
        } else if (itemId == R.id.nav_dns2proxy) {
            run_hack_cmd("dns2proxy -h");
        } else if (itemId == R.id.nav_dnschef) {
            run_hack_cmd("dnschef -h");
        } else if (itemId == R.id.nav_cmseek) {
            run_hack_cmd("sudo cmseek");
        } else if (itemId == R.id.nav_wascan) {
            run_hack_cmd("wascan");
        } else if (itemId == R.id.nav_golismero) {
            run_hack_cmd("golismero -h");
        } else if (itemId == R.id.nav_hhh) {
            run_hack_cmd("hhh -h");
        } else if (itemId == R.id.nav_hsecscan) {
            run_hack_cmd("hsecscan");
        } else if (itemId == R.id.nav_wafninja) {
            run_hack_cmd("wafninja -h");
        } else if (itemId == R.id.nav_wafw00f) {
            run_hack_cmd("wafw00f -h");
        } else if (itemId == R.id.nav_jaeles) {
            run_hack_cmd("jaeles");
        } else if (itemId == R.id.nav_httpx) {
            run_hack_cmd("httpx");
        } else if (itemId == R.id.nav_nuclei) {
            run_hack_cmd("nuclei");
        } else if (itemId == R.id.nav_nikto) {
            run_hack_cmd("nikto");
        } else if (itemId == R.id.nav_uniscan) {
            run_hack_cmd("sudo uniscan");
        } else if (itemId == R.id.nav_pyfilebuster) {
            run_hack_cmd("filebuster -h");
        } else if (itemId == R.id.nav_adfind) {
            run_hack_cmd("adfind -h");
        } else if (itemId == R.id.nav_evilurl) {
            run_hack_cmd("evilurl");
        } else if (itemId == R.id.nav_crlfinjector) {
            run_hack_cmd("crlf-injector -h");
        } else if (itemId == R.id.nav_injectus) {
            run_hack_cmd("injectus");
        } else if (itemId == R.id.nav_fiesta) {
            run_hack_cmd("fiesta -h");
        } else if (itemId == R.id.nav_xsrfprobe) {
            run_hack_cmd("xsrfprobe");
        } else if (itemId == R.id.nav_whatweb) {
            run_hack_cmd("whatweb -h");
        } else if (itemId == R.id.nav_wpxf) {
            run_hack_cmd("wpxf");
        } else if (itemId == R.id.nav_cameradar) {
            run_hack_cmd("cameradar");
        } else if (itemId == R.id.nav_pytbull) {
            run_hack_cmd("pytbull -h");
        } else if (itemId == R.id.nav_wpscan) {
            run_hack_cmd("wpscan -h");
        } else if (itemId == R.id.nav_wpseku) {
            run_hack_cmd("wpseku -h");
        } else if (itemId == R.id.nav_joomlavs) {
            run_hack_cmd("joomlavs");
        } else if (itemId == R.id.nav_vulnx) {
            run_hack_cmd("vulnx -h");
        } else if (itemId == R.id.nav_fingerprinter) {
            run_hack_cmd("fingerprinter -h");
        } else if (itemId == R.id.nav_uatester) {
            run_hack_cmd("ua-tester");
        } else if (itemId == R.id.nav_cadaver) {
            run_hack_cmd("cadaver");
        } else if (itemId == R.id.nav_sitebroker) {
            run_hack_cmd("sitebroker");
        } else if (itemId == R.id.nav_aron) {
            run_hack_cmd("aron -h");
        } else if (itemId == R.id.nav_jwtcrack) {
            run_hack_cmd("jwtcrack");
        } else if (itemId == R.id.nav_jwt_tool) {
            run_hack_cmd("jwt_tool -h");
        } else if (itemId == R.id.nav_bopscrk) {
            run_hack_cmd("bopscrk");
        } else if (itemId == R.id.nav_pskcrack) {
            run_hack_cmd("psk-crack");
        } else if (itemId == R.id.nav_hwacha) {
            run_hack_cmd("sudo hwacha");
        } else if (itemId == R.id.nav_autosploit) {
            run_hack_cmd("sudo autosploit");
        } else if (itemId == R.id.nav_mikrotaker) {
            run_hack_cmd("mikrotaker");
        } else if (itemId == R.id.nav_shellpop) {
            run_hack_cmd("shellpop -h");
        } else if (itemId == R.id.nav_pacu) {
            run_hack_cmd("pacu");
        } else if (itemId == R.id.nav_barq) {
            run_hack_cmd("barq -h");
        } else if (itemId == R.id.nav_sharpshooter) {
            run_hack_cmd("sharpshooter -h");
        } else if (itemId == R.id.nav_gdog) {
            run_hack_cmd("gdog");
        } else if (itemId == R.id.nav_shellver) {
            run_hack_cmd("shellver -h");
        } else if (itemId == R.id.nav_mcreator) {
            run_hack_cmd("mcreator");
        } else if (itemId == R.id.nav_radare2) {
            run_hack_cmd("r2 -h");
        } else if (itemId == R.id.nav_cfr) {
            run_hack_cmd("cfr --help");
        } else if (itemId == R.id.nav_apktool) {
            run_hack_cmd("apktool");
        } else if (itemId == R.id.nav_dex2jar) {
            run_hack_cmd("d2j-dex2jar");
        } else if (itemId == R.id.nav_smap) {
            run_hack_cmd("smap -h");
        } else if (itemId == R.id.nav_encdecshellcode) {
            run_hack_cmd("encdecshellcode");
        } else if (itemId == R.id.nav_makepdfjavascript) {
            run_hack_cmd("make-pdf-javascript");
        } else if (itemId == R.id.nav_javascriptobfuscator) {
            run_hack_cmd("javascript-obfuscator");
        } else if (itemId == R.id.nav_makepdfembedded) {
            run_hack_cmd("make-pdf-embedded");
        } else if (itemId == R.id.nav_pocsuite) {
            run_hack_cmd("pocsuite");
        } else if (itemId == R.id.nav_docem) {
            run_hack_cmd("docem");
        } else if (itemId == R.id.nav_brakeman) {
            run_hack_cmd("brakeman -h");
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
        return true;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 22) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                finish();
            }
        }
    }

    class DownloadFromURL extends AsyncTask<String, String, String> {
        /* access modifiers changed from: protected */
        public void onProgressUpdate(String... strArr) {
        }

        DownloadFromURL() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            do {
                try {
                } catch (IllegalArgumentException unused) {
                    return;
                }
            } while (MainActivity.this.isFinishing());
            MainActivity.this.showDialog(0);
            MainActivity.this.InstallNotBuilder.setContentTitle("Downloading ANDRAX core").setContentText("This can take a while...").setSmallIcon(R.drawable.andraxicon).setPriority(1).setAutoCancel(false).setOngoing(true);
            MainActivity.this.InstallNotManager.notify(3311, MainActivity.this.InstallNotBuilder.build());
        }

        /* access modifiers changed from: protected */
        public String doInBackground(String... strArr) {
            try {
                URL url = new URL(strArr[0]);
                URLConnection openConnection = url.openConnection();
                openConnection.setConnectTimeout(30000);
                openConnection.setReadTimeout(30000);
                openConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Android ANDRAX; Mobile; rv:03) Gecko/67.0 Firefox/67.0");
                openConnection.setDoOutput(true);
                openConnection.connect();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream(), 1024);
                FileOutputStream fileOutputStream = new FileOutputStream("/sdcard/Download/andraxcore.tar.xz");
                byte[] bArr = new byte[2048];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        bufferedInputStream.close();
                        return null;
                    }
                }
            } catch (Exception e) {
                Log.e("Error DOWNLOAD: ", e.getMessage());
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String str) {
            try {
                MainActivity.this.dismissDialog(0);
            } catch (IllegalArgumentException unused) {
            }
            new unpackandinstall().execute(new String[]{MainActivity.this.urlcore});
        }
    }

    class unpackandinstall extends AsyncTask<String, String, String> {
        /* access modifiers changed from: protected */
        public void onProgressUpdate(String... strArr) {
        }

        unpackandinstall() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            do {
                try {
                } catch (IllegalArgumentException unused) {
                    return;
                }
            } while (MainActivity.this.isFinishing());
            MainActivity.this.showDialog(3);
            MainActivity.this.InstallNotBuilder.setContentTitle("Extracting ANDRAX core").setContentText("Your device may be slow, WAIT...").setSmallIcon(R.drawable.andraxicon).setPriority(1).setAutoCancel(false).setOngoing(true).setProgress(0, 0, true);
            MainActivity.this.InstallNotManager.notify(3311, MainActivity.this.InstallNotBuilder.build());
        }

        /* access modifiers changed from: protected */
        public String doInBackground(String... strArr) {
            try {
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("su -c " + MainActivity.this.getFilesDir().getAbsolutePath() + "/bin/checkinstall.sh " + MainActivity.this.getApplicationInfo().dataDir).waitFor();
                return null;
            } catch (IOException e) {
                e.getMessage();
                return null;
            } catch (InterruptedException e2) {
                e2.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String str) {
            try {
                MainActivity.this.dismissDialog(3);
            } catch (IllegalArgumentException unused) {
            }
            MainActivity.this.InstallNotManager.cancel(3311);
            MainActivity.this.startActivity(new Intent(MainActivity.this, SplashActivity.class));
            MainActivity.this.finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 33 || i2 != -1) {
            if (i == 33 && i2 != -1) {
                finish();
            } else if (i == 171) {
                PowerManager powerManager = (PowerManager) getSystemService("power");
                boolean z = false;
                if (Build.VERSION.SDK_INT >= 23) {
                    z = powerManager.isIgnoringBatteryOptimizations(getPackageName());
                }
                if (!z) {
                    get_motherfucker_battery();
                }
            }
        }
    }

    public void run_hack_cmd(String str) {
        Intent createExecuteIntent = Bridge.createExecuteIntent(str);
        createExecuteIntent.setFlags(131072);
        startActivity(createExecuteIntent);
    }

    public void get_motherfucker_battery() {
        PowerManager powerManager = (PowerManager) getSystemService("power");
        if (Build.VERSION.SDK_INT >= 23 && !powerManager.isIgnoringBatteryOptimizations(getPackageName())) {
            Intent intent = new Intent();
            intent.setAction("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, SDL_1_2_Keycodes.SDLK_WORLD_11);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x007c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0048 A[LOOP:0: B:22:0x0042->B:25:0x0048, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isRooted(android.content.Context r7) {
        /*
            r6 = this;
            r7 = 0
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x008a, all -> 0x0086 }
            java.lang.String r2 = "su"
            java.lang.Process r1 = r1.exec(r2)     // Catch:{ IOException -> 0x008a, all -> 0x0086 }
            java.io.OutputStream r2 = r1.getOutputStream()     // Catch:{ IOException -> 0x008a, all -> 0x0086 }
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ IOException -> 0x0081, all -> 0x007e }
            java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x0031, all -> 0x002c }
            r3.<init>(r2)     // Catch:{ IOException -> 0x0031, all -> 0x002c }
            java.lang.String r4 = "ls /data\n"
            r3.writeBytes(r4)     // Catch:{ IOException -> 0x002a }
            java.lang.String r4 = "exit\n"
            r3.writeBytes(r4)     // Catch:{ IOException -> 0x002a }
            r3.flush()     // Catch:{ IOException -> 0x002a }
        L_0x0026:
            r3.close()     // Catch:{ IOException -> 0x007c }
            goto L_0x0037
        L_0x002a:
            r4 = move-exception
            goto L_0x0033
        L_0x002c:
            r3 = move-exception
            r5 = r3
            r3 = r0
            r0 = r5
            goto L_0x0078
        L_0x0031:
            r4 = move-exception
            r3 = r0
        L_0x0033:
            r4.printStackTrace()     // Catch:{ all -> 0x0077 }
            goto L_0x0026
        L_0x0037:
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ IOException -> 0x005b }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x005b }
            r4.<init>(r1)     // Catch:{ IOException -> 0x005b }
            r3.<init>(r4)     // Catch:{ IOException -> 0x005b }
            r0 = 0
        L_0x0042:
            java.lang.String r4 = r3.readLine()     // Catch:{ IOException -> 0x0054, all -> 0x004f }
            if (r4 == 0) goto L_0x004b
            int r0 = r0 + 1
            goto L_0x0042
        L_0x004b:
            r3.close()     // Catch:{ IOException -> 0x007c }
            goto L_0x0064
        L_0x004f:
            r0 = move-exception
            r5 = r3
            r3 = r0
            r0 = r5
            goto L_0x0073
        L_0x0054:
            r4 = move-exception
            r5 = r3
            r3 = r0
            r0 = r5
            goto L_0x005d
        L_0x0059:
            r3 = move-exception
            goto L_0x0073
        L_0x005b:
            r4 = move-exception
            r3 = 0
        L_0x005d:
            r4.printStackTrace()     // Catch:{ all -> 0x0059 }
            r0.close()     // Catch:{ IOException -> 0x007c }
            r0 = r3
        L_0x0064:
            if (r0 <= 0) goto L_0x0067
            r7 = 1
        L_0x0067:
            r1.close()     // Catch:{ IOException -> 0x006e }
            r2.close()     // Catch:{ IOException -> 0x006e }
            goto L_0x0097
        L_0x006e:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0097
        L_0x0073:
            r0.close()     // Catch:{ IOException -> 0x007c }
            throw r3     // Catch:{ IOException -> 0x007c }
        L_0x0077:
            r0 = move-exception
        L_0x0078:
            r3.close()     // Catch:{ IOException -> 0x007c }
            throw r0     // Catch:{ IOException -> 0x007c }
        L_0x007c:
            r0 = move-exception
            goto L_0x008e
        L_0x007e:
            r7 = move-exception
            r1 = r0
            goto L_0x00a8
        L_0x0081:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x008e
        L_0x0086:
            r7 = move-exception
            r1 = r0
            r2 = r1
            goto L_0x00a8
        L_0x008a:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L_0x008e:
            r0.printStackTrace()     // Catch:{ all -> 0x00a7 }
            r1.close()     // Catch:{ IOException -> 0x006e }
            r2.close()     // Catch:{ IOException -> 0x006e }
        L_0x0097:
            if (r7 != 0) goto L_0x00a6
            android.content.Intent r0 = new android.content.Intent
            java.lang.Class<com.thecrackertechnology.andrax.RootIt> r1 = com.thecrackertechnology.andrax.RootIt.class
            r0.<init>(r6, r1)
            r6.startActivity(r0)
            r6.finish()
        L_0x00a6:
            return r7
        L_0x00a7:
            r7 = move-exception
        L_0x00a8:
            r1.close()     // Catch:{ IOException -> 0x00af }
            r2.close()     // Catch:{ IOException -> 0x00af }
            goto L_0x00b3
        L_0x00af:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00b3:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.andrax.MainActivity.isRooted(android.content.Context):boolean");
    }
}
