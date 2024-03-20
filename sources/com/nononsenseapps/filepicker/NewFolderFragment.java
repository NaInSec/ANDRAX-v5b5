package com.nononsenseapps.filepicker;

import android.support.v4.app.FragmentManager;
import com.nononsenseapps.filepicker.NewItemFragment;

public class NewFolderFragment extends NewItemFragment {
    private static final String TAG = "new_folder_fragment";

    public static void showDialog(FragmentManager fragmentManager, NewItemFragment.OnNewFolderListener onNewFolderListener) {
        NewFolderFragment newFolderFragment = new NewFolderFragment();
        newFolderFragment.setListener(onNewFolderListener);
        newFolderFragment.show(fragmentManager, TAG);
    }

    /* access modifiers changed from: protected */
    public boolean validateName(String str) {
        return Utils.isValidFileName(str);
    }
}
