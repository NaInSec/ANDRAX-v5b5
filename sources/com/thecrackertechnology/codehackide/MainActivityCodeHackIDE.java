package com.thecrackertechnology.codehackide;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.nononsenseapps.filepicker.Utils;
import com.thecrackertechnology.dragonterminal.bridge.Bridge;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

public class MainActivityCodeHackIDE extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int FILE_SELECT_CODE = 10;
    private static final int MY_PERMISSIONS_REQUEST_WRITE = 22;
    public Runnable changelanghandler = new Runnable() {
        public void run() {
            WebView webView = MainActivityCodeHackIDE.this.web;
            webView.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/" + MainActivityCodeHackIDE.this.extralanguages + "\"); })();");
            MainActivityCodeHackIDE.this.mHandler.removeCallbacks(MainActivityCodeHackIDE.this.changelanghandler);
        }
    };
    String ext;
    String extralanguages = "";
    String filecontent;
    String filenametmp;
    String finalfilepath;
    String idetheme = "vibrant_ink";
    String installchecker;
    String languageselected = "C/C++";
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler();
    String myfileuri;
    String pathexternal;
    String pathopened = "";
    String pathtosave;
    Intent startservnot;
    WebView web;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main_codehack);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.startservnot = new Intent(this, MyNot.class);
        startService(this.startservnot);
        ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0 && !ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 22);
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navopen, R.string.navclose);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
        JsInterface jsInterface = new JsInterface();
        this.web = (WebView) findViewById(R.id.webide);
        this.web.setWebViewClient(new myWebClient());
        this.web.getSettings().setJavaScriptEnabled(true);
        this.web.addJavascriptInterface(jsInterface, "android");
        this.web.getSettings().setAppCacheEnabled(false);
        this.web.getSettings().setCacheMode(2);
        this.web.loadUrl("file:///android_asset/ideindex.html");
        this.web.getSettings().setAllowContentAccess(true);
        this.web.setWebChromeClient(new WebChromeClient() {
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.extralanguages = extras.getString("LANG");
            if (this.extralanguages != null) {
                this.mHandler.postDelayed(this.changelanghandler, 1000);
                Context applicationContext = getApplicationContext();
                Toast.makeText(applicationContext, "LANGUAGE " + this.extralanguages, 1).show();
                return;
            }
            this.extralanguages = "c_cpp";
        }
    }

    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen((int) GravityCompat.START)) {
            drawerLayout.closeDrawer((int) GravityCompat.START);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((CharSequence) "Exit?");
        builder.setMessage((CharSequence) "Exit from IDE? Your project will not be saved!");
        builder.setIcon(R.drawable.codehack);
        builder.setPositiveButton((CharSequence) getString(17039370), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (MainActivityCodeHackIDE.this.finalfilepath != null && !MainActivityCodeHackIDE.this.finalfilepath.isEmpty()) {
                    boolean delete = new File(MainActivityCodeHackIDE.this.finalfilepath).delete();
                    Log.w("TMPDELETE", "File deleted: " + delete);
                }
                MainActivityCodeHackIDE mainActivityCodeHackIDE = MainActivityCodeHackIDE.this;
                mainActivityCodeHackIDE.stopService(mainActivityCodeHackIDE.startservnot);
                MainActivityCodeHackIDE.this.finish();
                MainActivityCodeHackIDE.this.finish();
                MainActivityCodeHackIDE.this.finish();
            }
        });
        builder.setNegativeButton((CharSequence) getString(17039360), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_codehack, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_newfile) {
            this.myfileuri = null;
            this.filecontent = null;
            this.pathtosave = null;
            this.pathexternal = null;
            this.web.loadUrl("file:///android_asset/ideindex.html");
            setTitle("untitled.c");
        } else if (itemId == R.id.action_save) {
            if (this.myfileuri == null) {
                this.pathexternal = "/sdcard/";
                runnewsave();
                String str = this.finalfilepath;
                if (str != null && !str.isEmpty()) {
                    boolean delete = new File(this.finalfilepath).delete();
                    Log.w("TMPDELETE", "File deleted: " + delete);
                }
            } else {
                this.web.evaluateJavascript("(function() { return(editor.getValue()) })();", new ValueCallback<String>() {
                    public void onReceiveValue(String str) {
                        String replace = StringEscapeUtils.unescapeHtml4(str).replace("\\u003C", "<").replace("\\n", StringUtils.LF).replace("\\\n", "\\n").replace("\\\"", "\"").replace("\\\\", "\\").replace("\\t", "\t");
                        String substring = replace.substring(1, replace.length() - 1);
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(MainActivityCodeHackIDE.this.pathtosave);
                            fileOutputStream.write(substring.getBytes());
                            fileOutputStream.close();
                            MainActivityCodeHackIDE.this.myfileuri = MainActivityCodeHackIDE.this.pathtosave;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        boolean delete = new File(MainActivityCodeHackIDE.this.finalfilepath).delete();
                        Log.w("TMPDELETE", "File deleted: " + delete);
                        Context applicationContext = MainActivityCodeHackIDE.this.getApplicationContext();
                        Toast.makeText(applicationContext, "File saved: " + MainActivityCodeHackIDE.this.pathtosave, 1).show();
                        MainActivityCodeHackIDE mainActivityCodeHackIDE = MainActivityCodeHackIDE.this;
                        mainActivityCodeHackIDE.extralanguages = mainActivityCodeHackIDE.getExt(mainActivityCodeHackIDE.pathtosave);
                        MainActivityCodeHackIDE.this.mHandler.postDelayed(MainActivityCodeHackIDE.this.changelanghandler, 1000);
                    }
                });
            }
            return true;
        } else if (itemId == R.id.action_save_as) {
            this.pathexternal = "/sdcard/";
            runnewsave();
            return true;
        } else if (itemId == R.id.action_compile) {
            new MaterialDialog.Builder(this).title((CharSequence) "Args for compiler").content((CharSequence) "Ex: {-fPIE -pie}, -i {args}...").backgroundColorRes(R.color.blackfull).inputType(1).positiveText((CharSequence) "Compile/Run").input(R.string.cflags, R.string.fontsizeselected, (MaterialDialog.InputCallback) new MaterialDialog.InputCallback() {
                public void onInput(MaterialDialog materialDialog, CharSequence charSequence) {
                    if (MainActivityCodeHackIDE.this.extralanguages.equals("c_cpp")) {
                        MainActivityCodeHackIDE mainActivityCodeHackIDE = MainActivityCodeHackIDE.this;
                        mainActivityCodeHackIDE.run_hack_cmd("gcc " + charSequence + StringUtils.SPACE + MainActivityCodeHackIDE.this.pathtosave);
                    } else if (MainActivityCodeHackIDE.this.extralanguages.equals("python")) {
                        MainActivityCodeHackIDE mainActivityCodeHackIDE2 = MainActivityCodeHackIDE.this;
                        mainActivityCodeHackIDE2.run_hack_cmd("python3 " + charSequence + StringUtils.SPACE + MainActivityCodeHackIDE.this.pathtosave);
                    } else if (MainActivityCodeHackIDE.this.extralanguages.equals("javascript")) {
                        MainActivityCodeHackIDE mainActivityCodeHackIDE3 = MainActivityCodeHackIDE.this;
                        mainActivityCodeHackIDE3.run_hack_cmd("node " + charSequence + StringUtils.SPACE + MainActivityCodeHackIDE.this.pathtosave);
                    } else if (MainActivityCodeHackIDE.this.extralanguages.equals("ruby")) {
                        MainActivityCodeHackIDE mainActivityCodeHackIDE4 = MainActivityCodeHackIDE.this;
                        mainActivityCodeHackIDE4.run_hack_cmd("ruby " + charSequence + StringUtils.SPACE + MainActivityCodeHackIDE.this.pathtosave);
                    } else if (MainActivityCodeHackIDE.this.extralanguages.equals("php")) {
                        MainActivityCodeHackIDE mainActivityCodeHackIDE5 = MainActivityCodeHackIDE.this;
                        mainActivityCodeHackIDE5.run_hack_cmd("php " + charSequence + StringUtils.SPACE + MainActivityCodeHackIDE.this.pathtosave);
                    }
                }
            }).show();
            return true;
        } else if (itemId == R.id.action_exit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle((CharSequence) "Exit?");
            builder.setMessage((CharSequence) "Exit from IDE? Your project will not be saved!");
            builder.setIcon(R.drawable.codehack);
            builder.setPositiveButton((CharSequence) getString(17039370), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (MainActivityCodeHackIDE.this.finalfilepath != null && !MainActivityCodeHackIDE.this.finalfilepath.isEmpty()) {
                        boolean delete = new File(MainActivityCodeHackIDE.this.finalfilepath).delete();
                        Log.e("TMPDELETE", "File deleted: " + delete);
                    }
                    MainActivityCodeHackIDE mainActivityCodeHackIDE = MainActivityCodeHackIDE.this;
                    mainActivityCodeHackIDE.stopService(mainActivityCodeHackIDE.startservnot);
                    MainActivityCodeHackIDE.this.finish();
                    MainActivityCodeHackIDE.this.finish();
                    MainActivityCodeHackIDE.this.finish();
                }
            });
            builder.setNegativeButton((CharSequence) getString(17039360), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.create().show();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.nav_openfile) {
            Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            startActivityForResult(intent, 5);
        } else if (itemId == R.id.nav_fontsize) {
            new MaterialDialog.Builder(this).title((CharSequence) "Change font size").content((CharSequence) "Default: 12px").backgroundColorRes(R.color.blackfull).inputType(1).positiveText((CharSequence) "Change").input(R.string.fontdefault, R.string.fontsizeselected, (MaterialDialog.InputCallback) new MaterialDialog.InputCallback() {
                public void onInput(MaterialDialog materialDialog, CharSequence charSequence) {
                    if (charSequence != null) {
                        WebView webView = MainActivityCodeHackIDE.this.web;
                        webView.loadUrl("javascript:(function() { document.getElementById('editor').style.fontSize='" + charSequence + "px' })();");
                    }
                }
            }).show();
        } else if (itemId == R.id.nav_language) {
            new MaterialDialog.Builder(this).title((CharSequence) "Language").items(R.array.itemlang).backgroundColorRes(R.color.blackfull).itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                public boolean onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                    MainActivityCodeHackIDE.this.languageselected = charSequence.toString();
                    if (charSequence.equals("C/C++")) {
                        MainActivityCodeHackIDE.this.web.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/c_cpp\"); })();");
                        MainActivityCodeHackIDE.this.extralanguages = "c_cpp";
                        return true;
                    } else if (charSequence.equals("ASM")) {
                        MainActivityCodeHackIDE.this.web.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/assembly_x86\"); })();");
                        MainActivityCodeHackIDE.this.extralanguages = "assembly_x86";
                        return true;
                    } else if (charSequence.equals("Makefile")) {
                        MainActivityCodeHackIDE.this.web.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/makefile\"); })();");
                        MainActivityCodeHackIDE.this.extralanguages = "makefile";
                        return true;
                    } else if (charSequence.equals("Python")) {
                        MainActivityCodeHackIDE.this.web.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/python\"); })();");
                        MainActivityCodeHackIDE.this.extralanguages = "python";
                        return true;
                    } else if (charSequence.equals("Perl")) {
                        MainActivityCodeHackIDE.this.web.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/perl\"); })();");
                        MainActivityCodeHackIDE.this.extralanguages = "perl";
                        return true;
                    } else if (charSequence.equals("Lua")) {
                        MainActivityCodeHackIDE.this.web.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/lua\"); })();");
                        MainActivityCodeHackIDE.this.extralanguages = "lua";
                        return true;
                    } else if (charSequence.equals("Shell")) {
                        MainActivityCodeHackIDE.this.web.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/sh\"); })();");
                        MainActivityCodeHackIDE.this.extralanguages = "sh";
                        return true;
                    } else if (charSequence.equals("R")) {
                        MainActivityCodeHackIDE.this.web.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/r\"); })();");
                        MainActivityCodeHackIDE.this.extralanguages = "r";
                        return true;
                    } else if (charSequence.equals("Haskell")) {
                        MainActivityCodeHackIDE.this.web.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/haskell\"); })();");
                        MainActivityCodeHackIDE.this.extralanguages = "haskell";
                        return true;
                    } else if (charSequence.equals("Java")) {
                        MainActivityCodeHackIDE.this.web.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/java\"); })();");
                        MainActivityCodeHackIDE.this.extralanguages = "java";
                        return true;
                    } else if (charSequence.equals("Go")) {
                        MainActivityCodeHackIDE.this.web.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/golang\"); })();");
                        MainActivityCodeHackIDE.this.extralanguages = "golang";
                        return true;
                    } else if (charSequence.equals("PHP")) {
                        MainActivityCodeHackIDE.this.web.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/php\"); })();");
                        MainActivityCodeHackIDE.this.extralanguages = "php";
                        return true;
                    } else if (charSequence.equals("Html")) {
                        MainActivityCodeHackIDE.this.web.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/html\"); })();");
                        MainActivityCodeHackIDE.this.extralanguages = "html";
                        return true;
                    } else if (charSequence.equals("CSS")) {
                        MainActivityCodeHackIDE.this.web.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/css\"); })();");
                        MainActivityCodeHackIDE.this.extralanguages = "css";
                        return true;
                    } else if (charSequence.equals("Javascript")) {
                        MainActivityCodeHackIDE.this.web.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/javascript\"); })();");
                        MainActivityCodeHackIDE.this.extralanguages = "javascript";
                        return true;
                    } else if (charSequence.equals("Ruby")) {
                        MainActivityCodeHackIDE.this.web.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/ruby\"); })();");
                        MainActivityCodeHackIDE.this.extralanguages = "ruby";
                        return true;
                    } else if (!charSequence.equals("C#")) {
                        return true;
                    } else {
                        MainActivityCodeHackIDE.this.web.loadUrl("javascript:(function() { editor.getSession().setMode(\"ace/mode/csharp\"); })();");
                        MainActivityCodeHackIDE.this.extralanguages = "csharp";
                        return true;
                    }
                }
            }).negativeText((CharSequence) "Select").show();
        } else if (itemId == R.id.nav_theme) {
            new MaterialDialog.Builder(this).title((CharSequence) "Theme").backgroundColorRes(R.color.blackfull).items(R.array.itemtheme).itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                public boolean onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                    MainActivityCodeHackIDE.this.idetheme = charSequence.toString();
                    WebView webView = MainActivityCodeHackIDE.this.web;
                    webView.loadUrl("javascript:(function() { editor.setTheme(\"ace/theme/" + MainActivityCodeHackIDE.this.idetheme + "\"); })();");
                    return true;
                }
            }).negativeText((CharSequence) "Select").show();
        } else if (itemId == R.id.nav_about) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle((CharSequence) "About");
            builder.setMessage((CharSequence) "Code HACK IDE, is the official IDE of the ANDRAX Mobile Pentest DISTRO\n\nDeveloped by The Cracker Technology as part of the Android Hacking project a standardization of computer security from smartphones\n\nDev:\nWeidsom Nascimento\n\n\thttp://andrax.thecrackertechnology.com");
            builder.setIcon(R.drawable.codehack);
            builder.setPositiveButton((CharSequence) getString(17039370), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.create().show();
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
        return true;
    }

    private String getRealPathFromURI(Uri uri) {
        Cursor query = getContentResolver().query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return uri.getPath();
        }
        query.moveToFirst();
        String string = query.getString(query.getColumnIndex("_data"));
        query.close();
        return string;
    }

    private void webViewGoBack() {
        this.web.goBack();
    }

    public class myWebClient extends WebViewClient {
        public myWebClient() {
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Log.e("PAGESTART", "Started");
            super.onPageStarted(webView, str, bitmap);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }

        public void onPageFinished(WebView webView, String str) {
            WebView webView2 = MainActivityCodeHackIDE.this.web;
            webView2.loadUrl("javascript:(function() { editor.setTheme(\"ace/theme/" + MainActivityCodeHackIDE.this.idetheme + "\")})();");
            super.onPageFinished(webView, str);
            Log.e("PAGEFIM", "Finalizado");
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            MainActivityCodeHackIDE.this.web.loadUrl("file:///android_asset/error.html");
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 3) {
            if (i != 5) {
                if (i == 10 && i2 == -1) {
                    Uri data = intent.getData();
                    Log.d("FileCHOOSED", "File Uri: " + data);
                    String path = data.getPath();
                    Log.d("FileCHOOSED", "File Path: " + path);
                    try {
                        this.myfileuri = getFilePath(getApplicationContext(), data);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    setcodeoffile();
                }
            } else if (i2 == -1) {
                for (Uri filePath : Utils.getSelectedFilesFromResult(intent)) {
                    try {
                        this.myfileuri = getFilePath(getApplicationContext(), filePath);
                    } catch (URISyntaxException e2) {
                        e2.printStackTrace();
                    }
                    setcodeoffile();
                }
            }
        } else if (i2 == -1) {
            for (Uri next : Utils.getSelectedFilesFromResult(intent)) {
                try {
                    this.pathexternal = getFilePath(getApplicationContext(), next);
                    this.pathtosave = getFilePath(getApplicationContext(), next);
                } catch (URISyntaxException e3) {
                    e3.printStackTrace();
                }
                this.extralanguages = getExt(this.pathtosave);
                this.mHandler.postDelayed(this.changelanghandler, 1000);
                runnewsave();
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 22) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                stopService(this.startservnot);
                finish();
                finish();
                finish();
            }
        }
    }

    public void setcodeoffile() {
        String str = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(this.myfileuri));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine + StringUtils.LF);
            }
            fileInputStream.close();
            str = sb.toString();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            Log.d("FILECHOOSED", e.getMessage());
        } catch (IOException e2) {
            Log.d("FILECHOOSED", e2.getMessage());
        }
        this.filecontent = str;
        writetempfile(this.filecontent, getApplicationContext());
    }

    public void writetempfile(String str, Context context) {
        String substring = this.myfileuri.substring(this.myfileuri.lastIndexOf(47) + 1);
        String str2 = this.myfileuri;
        String substring2 = str2.substring(0, str2.lastIndexOf(47) + 1);
        String trim = str.trim();
        this.finalfilepath = "/sdcard/." + substring + ".html";
        Toast.makeText(getApplicationContext(), "PATH " + this.finalfilepath, 1).show();
        this.pathtosave = substring2 + substring;
        this.pathopened = substring2;
        this.ext = getExt(substring);
        this.extralanguages = this.ext;
        this.mHandler.postDelayed(this.changelanghandler, 1000);
        setTitle(substring);
        String str3 = "<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n  <meta charset=\"UTF-8\">\n  <title>Fuck You Offensive Security</title>\n  <style type=\"text/css\" media=\"screen\">\n    body {\n        overflow: hidden;\n    }\n    \n    #editor { \n        margin: 0;\n        position: absolute;\n        top: 0;\n        bottom: 0;\n        left: 0;\n        right: 0;\n    }\n  </style>\n</head>\n<body>\n<pre id=\"editor\">" + StringEscapeUtils.escapeHtml4(trim).replace("\\\\", "\\") + "</pre>\n\n<!-- load ace -->\n<script src=\"file:///android_asset/src-noconflict/ace.js\"></script>\n<!-- load ace language tools -->\n<script src=\"file:///android_asset/src-noconflict/ext-language_tools.js\"></script>\n<script>\n    // trigger extension\n    ace.require(\"ace/ext/language_tools\");\n    var editor = ace.edit(\"editor\");\n    editor.session.setMode(\"ace/mode/" + this.ext + "\");\n    editor.setShowPrintMargin(false);\n    editor.getSession().setUseWrapMode(true);\n    var code = editor.getValue();\n    //editor.setValue(\"new code \" + code);\n    //enable autocompletion and snippets\n    editor.setOptions({\n        enableBasicAutocompletion: true,\n        enableSnippets: true,\n        enableLiveAutocompletion: true\n    });\n</script>\n</body>\n</html>";
        try {
            FileWriter fileWriter = new FileWriter(this.finalfilepath);
            fileWriter.write(str3);
            fileWriter.flush();
            fileWriter.close();
            readsavedtmpfile(this.finalfilepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readsavedtmpfile(String str) {
        WebView webView = this.web;
        webView.loadUrl("file://" + str);
        this.mHandler.postDelayed(this.changelanghandler, 1000);
    }

    public String getExt(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf <= 0) {
            return null;
        }
        int i = lastIndexOf + 1;
        if (str.substring(i).toLowerCase().equals("txt")) {
            return null;
        }
        if (!str.substring(i).toLowerCase().equals("c") && !str.substring(i).toLowerCase().equals("cpp") && !str.substring(i).toLowerCase().equals("h") && !str.substring(i).toLowerCase().equals("cc")) {
            if (str.substring(i).toLowerCase().equals("py")) {
                return "python";
            }
            if (str.substring(i).toLowerCase().equals("asm")) {
                return "assembly_x86";
            }
            if (str.substring(i).toLowerCase().equals("rb")) {
                return "ruby";
            }
            if (str.substring(i).toLowerCase().equals("js")) {
                return "javascript";
            }
            if (str.substring(i).toLowerCase().equals("mk")) {
                return "makefile";
            }
            if (str.substring(i).toLowerCase().equals("php")) {
                return "php";
            }
            if (str.substring(i).toLowerCase().equals("html")) {
                return "html";
            }
            if (str.substring(i).toLowerCase().equals("go")) {
                return "golang";
            }
            if (str.substring(i).toLowerCase().equals("java")) {
                return "java";
            }
            if (str.substring(i).toLowerCase().equals("css")) {
                return "css";
            }
        }
        return "c_cpp";
    }

    public void runnewsave() {
        if (this.pathexternal != null) {
            MaterialDialog.Builder title = new MaterialDialog.Builder(this).title((CharSequence) "New file name");
            title.content((CharSequence) "The file will be saved in: " + this.pathexternal).inputType(1).positiveText((CharSequence) "Save").contentColor(-1).backgroundColorRes(R.color.blackfull).input(R.string.newfilename, R.string.fontsizeselected, (MaterialDialog.InputCallback) new MaterialDialog.InputCallback() {
                public void onInput(MaterialDialog materialDialog, final CharSequence charSequence) {
                    MainActivityCodeHackIDE.this.web.evaluateJavascript("(function() { return(editor.getValue()) })();", new ValueCallback<String>() {
                        public void onReceiveValue(String str) {
                            String replace = StringEscapeUtils.unescapeHtml4(str).replace("\\u003C", "<").replace("\\n", StringUtils.LF).replace("\\\n", "\\n").replace("\\\"", "\"").replace("\\\\", "\\").replace("\\t", "\t");
                            String substring = replace.substring(1, replace.length() - 1);
                            try {
                                FileOutputStream fileOutputStream = new FileOutputStream(MainActivityCodeHackIDE.this.pathexternal + "/" + charSequence);
                                fileOutputStream.write(substring.getBytes());
                                fileOutputStream.close();
                                MainActivityCodeHackIDE mainActivityCodeHackIDE = MainActivityCodeHackIDE.this;
                                mainActivityCodeHackIDE.pathtosave = MainActivityCodeHackIDE.this.pathexternal + "/" + charSequence;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            MainActivityCodeHackIDE.this.setTitle(charSequence);
                            Context applicationContext = MainActivityCodeHackIDE.this.getApplicationContext();
                            Toast.makeText(applicationContext, "New file saved: " + charSequence, 1).show();
                            MainActivityCodeHackIDE.this.extralanguages = MainActivityCodeHackIDE.this.getExt(MainActivityCodeHackIDE.this.pathtosave);
                            MainActivityCodeHackIDE.this.mHandler.postDelayed(MainActivityCodeHackIDE.this.changelanghandler, 1000);
                        }
                    });
                }
            }).show();
            return;
        }
        Toast.makeText(getApplicationContext(), "Not saved!", 1).show();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getFilePath(android.content.Context r11, android.net.Uri r12) throws java.net.URISyntaxException {
        /*
            android.content.Context r0 = r11.getApplicationContext()
            boolean r0 = android.provider.DocumentsContract.isDocumentUri(r0, r12)
            r1 = 0
            if (r0 == 0) goto L_0x008d
            boolean r0 = isExternalStorageDocument(r12)
            java.lang.String r2 = ":"
            r3 = 1
            if (r0 == 0) goto L_0x0037
            java.lang.String r11 = android.provider.DocumentsContract.getDocumentId(r12)
            java.lang.String[] r11 = r11.split(r2)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()
            r12.append(r0)
            java.lang.String r0 = "/"
            r12.append(r0)
            r11 = r11[r3]
            r12.append(r11)
            java.lang.String r11 = r12.toString()
            return r11
        L_0x0037:
            boolean r0 = isDownloadsDocument(r12)
            if (r0 == 0) goto L_0x0050
            java.lang.String r12 = android.provider.DocumentsContract.getDocumentId(r12)
            java.lang.String r0 = "content://downloads/public_downloads"
            android.net.Uri r0 = android.net.Uri.parse(r0)
            long r2 = java.lang.Long.parseLong(r12)
            android.net.Uri r12 = android.content.ContentUris.withAppendedId(r0, r2)
            goto L_0x008d
        L_0x0050:
            boolean r0 = isMediaDocument(r12)
            if (r0 == 0) goto L_0x008d
            java.lang.String r0 = android.provider.DocumentsContract.getDocumentId(r12)
            java.lang.String[] r0 = r0.split(r2)
            r2 = 0
            r4 = r0[r2]
            java.lang.String r5 = "image"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x006c
            android.net.Uri r12 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            goto L_0x0081
        L_0x006c:
            java.lang.String r5 = "video"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x0077
            android.net.Uri r12 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            goto L_0x0081
        L_0x0077:
            java.lang.String r5 = "audio"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0081
            android.net.Uri r12 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        L_0x0081:
            java.lang.String[] r4 = new java.lang.String[r3]
            r0 = r0[r3]
            r4[r2] = r0
            java.lang.String r0 = "_id=?"
            r6 = r12
            r8 = r0
            r9 = r4
            goto L_0x0090
        L_0x008d:
            r6 = r12
            r8 = r1
            r9 = r8
        L_0x0090:
            java.lang.String r12 = r6.getScheme()
            java.lang.String r0 = "content"
            boolean r12 = r0.equalsIgnoreCase(r12)
            if (r12 == 0) goto L_0x00ba
            java.lang.String r12 = "_data"
            java.lang.String[] r7 = new java.lang.String[]{r12}
            android.content.ContentResolver r5 = r11.getContentResolver()     // Catch:{ Exception -> 0x00cb }
            r10 = 0
            android.database.Cursor r11 = r5.query(r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x00cb }
            int r12 = r11.getColumnIndexOrThrow(r12)     // Catch:{ Exception -> 0x00cb }
            boolean r0 = r11.moveToFirst()     // Catch:{ Exception -> 0x00cb }
            if (r0 == 0) goto L_0x00cb
            java.lang.String r11 = r11.getString(r12)     // Catch:{ Exception -> 0x00cb }
            return r11
        L_0x00ba:
            java.lang.String r11 = r6.getScheme()
            java.lang.String r12 = "file"
            boolean r11 = r12.equalsIgnoreCase(r11)
            if (r11 == 0) goto L_0x00cb
            java.lang.String r11 = r6.getPath()
            return r11
        L_0x00cb:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.codehackide.MainActivityCodeHackIDE.getFilePath(android.content.Context, android.net.Uri):java.lang.String");
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private class JsInterface {
        public void log(String str) {
        }

        private JsInterface() {
        }
    }

    public void run_hack_cmd(String str) {
        Intent createExecuteIntent = Bridge.createExecuteIntent(str);
        createExecuteIntent.setFlags(131072);
        startActivity(createExecuteIntent);
    }
}
