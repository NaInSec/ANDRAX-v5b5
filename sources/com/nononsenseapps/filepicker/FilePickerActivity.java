package com.nononsenseapps.filepicker;

import android.os.Environment;
import java.io.File;

public class FilePickerActivity extends AbstractFilePickerActivity<File> {
    /* access modifiers changed from: protected */
    public AbstractFilePickerFragment<File> getFragment(String str, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        FilePickerFragment filePickerFragment = new FilePickerFragment();
        if (str == null) {
            str = Environment.getExternalStorageDirectory().getPath();
        }
        filePickerFragment.setArgs(str, i, z, z2, z3, z4);
        return filePickerFragment;
    }
}
