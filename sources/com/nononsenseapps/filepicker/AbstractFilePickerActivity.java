package com.nononsenseapps.filepicker;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.nononsenseapps.filepicker.AbstractFilePickerFragment;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFilePickerActivity<T> extends AppCompatActivity implements AbstractFilePickerFragment.OnFilePickedListener {
    public static final String EXTRA_ALLOW_CREATE_DIR = "nononsense.intent.ALLOW_CREATE_DIR";
    public static final String EXTRA_ALLOW_EXISTING_FILE = "android.intent.extra.ALLOW_EXISTING_FILE";
    public static final String EXTRA_ALLOW_MULTIPLE = "android.intent.extra.ALLOW_MULTIPLE";
    public static final String EXTRA_MODE = "nononsense.intent.MODE";
    public static final String EXTRA_PATHS = "nononsense.intent.PATHS";
    public static final String EXTRA_SINGLE_CLICK = "nononsense.intent.SINGLE_CLICK";
    public static final String EXTRA_START_PATH = "nononsense.intent.START_PATH";
    public static final int MODE_DIR = 1;
    public static final int MODE_FILE = 0;
    public static final int MODE_FILE_AND_DIR = 2;
    public static final int MODE_NEW_FILE = 3;
    protected static final String TAG = "filepicker_fragment";
    protected boolean allowCreateDir = false;
    private boolean allowExistingFile = true;
    protected boolean allowMultiple = false;
    protected int mode = 0;
    protected boolean singleClick = false;
    protected String startPath = null;

    /* access modifiers changed from: protected */
    public abstract AbstractFilePickerFragment<T> getFragment(String str, int i, boolean z, boolean z2, boolean z3, boolean z4);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.nnf_activity_filepicker);
        Intent intent = getIntent();
        if (intent != null) {
            this.startPath = intent.getStringExtra(EXTRA_START_PATH);
            this.mode = intent.getIntExtra(EXTRA_MODE, this.mode);
            this.allowCreateDir = intent.getBooleanExtra(EXTRA_ALLOW_CREATE_DIR, this.allowCreateDir);
            this.allowMultiple = intent.getBooleanExtra(EXTRA_ALLOW_MULTIPLE, this.allowMultiple);
            this.allowExistingFile = intent.getBooleanExtra(EXTRA_ALLOW_EXISTING_FILE, this.allowExistingFile);
            this.singleClick = intent.getBooleanExtra(EXTRA_SINGLE_CLICK, this.singleClick);
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        AbstractFilePickerFragment abstractFilePickerFragment = (AbstractFilePickerFragment) supportFragmentManager.findFragmentByTag(TAG);
        if (abstractFilePickerFragment == null) {
            abstractFilePickerFragment = getFragment(this.startPath, this.mode, this.allowMultiple, this.allowCreateDir, this.allowExistingFile, this.singleClick);
        }
        if (abstractFilePickerFragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment, abstractFilePickerFragment, TAG).commit();
        }
        setResult(0);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void onFilePicked(Uri uri) {
        Intent intent = new Intent();
        intent.setData(uri);
        setResult(-1, intent);
        finish();
    }

    public void onFilesPicked(List<Uri> list) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ALLOW_MULTIPLE, true);
        ArrayList arrayList = new ArrayList();
        for (Uri uri : list) {
            arrayList.add(uri.toString());
        }
        intent.putStringArrayListExtra(EXTRA_PATHS, arrayList);
        if (Build.VERSION.SDK_INT >= 16) {
            ClipData clipData = null;
            for (Uri next : list) {
                if (clipData == null) {
                    clipData = new ClipData("Paths", new String[0], new ClipData.Item(next));
                } else {
                    clipData.addItem(new ClipData.Item(next));
                }
            }
            intent.setClipData(clipData);
        }
        setResult(-1, intent);
        finish();
    }

    public void onCancelled() {
        setResult(0);
        finish();
    }
}
