package com.nononsenseapps.filepicker;

import android.content.Context;
import android.net.Uri;
import android.os.FileObserver;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.Loader;
import android.support.v7.util.SortedList;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.widget.Toast;
import java.io.File;

public class FilePickerFragment extends AbstractFilePickerFragment<File> {
    protected static final int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private File mRequestedPath = null;
    protected boolean showHiddenItems = false;

    public void showHiddenItems(boolean z) {
        this.showHiddenItems = z;
    }

    public boolean areHiddenItemsShown() {
        return this.showHiddenItems;
    }

    /* access modifiers changed from: protected */
    public boolean hasPermission(File file) {
        return ContextCompat.checkSelfPermission(getContext(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    /* access modifiers changed from: protected */
    public void handlePermission(File file) {
        this.mRequestedPath = file;
        requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (strArr.length == 0) {
            if (this.mListener != null) {
                this.mListener.onCancelled();
            }
        } else if (iArr[0] == 0) {
            File file = this.mRequestedPath;
            if (file != null) {
                refresh(file);
            }
        } else {
            Toast.makeText(getContext(), R.string.nnf_permission_external_write_denied, 0).show();
            if (this.mListener != null) {
                this.mListener.onCancelled();
            }
        }
    }

    public boolean isDir(File file) {
        return file.isDirectory();
    }

    public String getName(File file) {
        return file.getName();
    }

    public File getParent(File file) {
        return (!file.getPath().equals(getRoot().getPath()) && file.getParentFile() != null) ? file.getParentFile() : file;
    }

    public File getPath(String str) {
        return new File(str);
    }

    public String getFullPath(File file) {
        return file.getPath();
    }

    public File getRoot() {
        return new File("/");
    }

    public Uri toUri(File file) {
        Context context = getContext();
        return FileProvider.getUriForFile(context, getContext().getApplicationContext().getPackageName() + ".provider", file);
    }

    public Loader<SortedList<File>> getLoader() {
        return new AsyncTaskLoader<SortedList<File>>(getActivity()) {
            FileObserver fileObserver;

            public SortedList<File> loadInBackground() {
                int i;
                File[] listFiles = ((File) FilePickerFragment.this.mCurrentPath).listFiles();
                if (listFiles == null) {
                    i = 0;
                } else {
                    i = listFiles.length;
                }
                SortedList<File> sortedList = new SortedList<>(File.class, new SortedListAdapterCallback<File>(FilePickerFragment.this.getDummyAdapter()) {
                    public int compare(File file, File file2) {
                        return FilePickerFragment.this.compareFiles(file, file2);
                    }

                    public boolean areContentsTheSame(File file, File file2) {
                        return file.getAbsolutePath().equals(file2.getAbsolutePath()) && file.isFile() == file2.isFile();
                    }

                    public boolean areItemsTheSame(File file, File file2) {
                        return areContentsTheSame(file, file2);
                    }
                }, i);
                sortedList.beginBatchedUpdates();
                if (listFiles != null) {
                    for (File file : listFiles) {
                        if (FilePickerFragment.this.isItemVisible(file)) {
                            sortedList.add(file);
                        }
                    }
                }
                sortedList.endBatchedUpdates();
                return sortedList;
            }

            /* access modifiers changed from: protected */
            public void onStartLoading() {
                super.onStartLoading();
                if (FilePickerFragment.this.mCurrentPath == null || !((File) FilePickerFragment.this.mCurrentPath).isDirectory()) {
                    FilePickerFragment filePickerFragment = FilePickerFragment.this;
                    filePickerFragment.mCurrentPath = filePickerFragment.getRoot();
                }
                this.fileObserver = new FileObserver(((File) FilePickerFragment.this.mCurrentPath).getPath(), 960) {
                    public void onEvent(int i, String str) {
                        AnonymousClass1.this.onContentChanged();
                    }
                };
                this.fileObserver.startWatching();
                forceLoad();
            }

            /* access modifiers changed from: protected */
            public void onReset() {
                super.onReset();
                FileObserver fileObserver2 = this.fileObserver;
                if (fileObserver2 != null) {
                    fileObserver2.stopWatching();
                    this.fileObserver = null;
                }
            }
        };
    }

    public void onNewFolder(String str) {
        File file = new File((File) this.mCurrentPath, str);
        if (file.mkdir()) {
            refresh(file);
        } else {
            Toast.makeText(getActivity(), R.string.nnf_create_folder_error, 0).show();
        }
    }

    /* access modifiers changed from: protected */
    public boolean isItemVisible(File file) {
        if (this.showHiddenItems || !file.isHidden()) {
            return super.isItemVisible(file);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public int compareFiles(File file, File file2) {
        if (file.isDirectory() && !file2.isDirectory()) {
            return -1;
        }
        if (!file2.isDirectory() || file.isDirectory()) {
            return file.getName().compareToIgnoreCase(file2.getName());
        }
        return 1;
    }
}
