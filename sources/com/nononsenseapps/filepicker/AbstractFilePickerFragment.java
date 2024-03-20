package com.nononsenseapps.filepicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.nononsenseapps.filepicker.NewItemFragment;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractFilePickerFragment<T> extends Fragment implements LoaderManager.LoaderCallbacks<SortedList<T>>, NewItemFragment.OnNewFolderListener, LogicHandler<T> {
    public static final String KEY_ALLOW_DIR_CREATE = "KEY_ALLOW_DIR_CREATE";
    public static final String KEY_ALLOW_EXISTING_FILE = "KEY_ALLOW_EXISTING_FILE";
    public static final String KEY_ALLOW_MULTIPLE = "KEY_ALLOW_MULTIPLE";
    protected static final String KEY_CURRENT_PATH = "KEY_CURRENT_PATH";
    public static final String KEY_MODE = "KEY_MODE";
    public static final String KEY_SINGLE_CLICK = "KEY_SINGLE_CLICK";
    public static final String KEY_START_PATH = "KEY_START_PATH";
    public static final int MODE_DIR = 1;
    public static final int MODE_FILE = 0;
    public static final int MODE_FILE_AND_DIR = 2;
    public static final int MODE_NEW_FILE = 3;
    protected boolean allowCreateDir = false;
    protected boolean allowExistingFile = true;
    protected boolean allowMultiple = false;
    protected boolean isLoading = false;
    protected LinearLayoutManager layoutManager;
    protected FileItemAdapter<T> mAdapter = null;
    protected final HashSet<T> mCheckedItems = new HashSet<>();
    protected final HashSet<AbstractFilePickerFragment<T>.CheckableViewHolder> mCheckedVisibleViewHolders = new HashSet<>();
    protected TextView mCurrentDirView;
    protected T mCurrentPath = null;
    protected EditText mEditTextFileName;
    protected SortedList<T> mFiles = null;
    protected OnFilePickedListener mListener;
    protected View mNewFileButtonContainer = null;
    protected View mRegularButtonContainer = null;
    protected Toast mToast = null;
    protected int mode = 0;
    protected RecyclerView recyclerView;
    protected boolean singleClick = false;

    public interface OnFilePickedListener {
        void onCancelled();

        void onFilePicked(Uri uri);

        void onFilesPicked(List<Uri> list);
    }

    /* access modifiers changed from: protected */
    public void handlePermission(T t) {
    }

    /* access modifiers changed from: protected */
    public boolean hasPermission(T t) {
        return true;
    }

    public boolean onLongClickDir(View view, AbstractFilePickerFragment<T>.DirViewHolder dirViewHolder) {
        return false;
    }

    public AbstractFilePickerFragment() {
        setRetainInstance(true);
    }

    /* access modifiers changed from: protected */
    public FileItemAdapter<T> getAdapter() {
        return this.mAdapter;
    }

    /* access modifiers changed from: protected */
    public FileItemAdapter<T> getDummyAdapter() {
        return new FileItemAdapter<>(this);
    }

    public void setArgs(String str, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        if (i == 3 && z) {
            throw new IllegalArgumentException("MODE_NEW_FILE does not support 'allowMultiple'");
        } else if (!z4 || !z) {
            Bundle arguments = getArguments();
            if (arguments == null) {
                arguments = new Bundle();
            }
            if (str != null) {
                arguments.putString(KEY_START_PATH, str);
            }
            arguments.putBoolean(KEY_ALLOW_DIR_CREATE, z2);
            arguments.putBoolean(KEY_ALLOW_MULTIPLE, z);
            arguments.putBoolean(KEY_ALLOW_EXISTING_FILE, z3);
            arguments.putBoolean(KEY_SINGLE_CLICK, z4);
            arguments.putInt(KEY_MODE, i);
            setArguments(arguments);
        } else {
            throw new IllegalArgumentException("'singleClick' can not be used with 'allowMultiple'");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        TextView textView;
        View inflateRootView = inflateRootView(layoutInflater, viewGroup);
        Toolbar toolbar = (Toolbar) inflateRootView.findViewById(R.id.nnf_picker_toolbar);
        if (toolbar != null) {
            setupToolbar(toolbar);
        }
        this.recyclerView = (RecyclerView) inflateRootView.findViewById(16908298);
        this.recyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(getActivity());
        this.recyclerView.setLayoutManager(this.layoutManager);
        configureItemDecoration(layoutInflater, this.recyclerView);
        this.mAdapter = new FileItemAdapter<>(this);
        this.recyclerView.setAdapter(this.mAdapter);
        inflateRootView.findViewById(R.id.nnf_button_cancel).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AbstractFilePickerFragment.this.onClickCancel(view);
            }
        });
        inflateRootView.findViewById(R.id.nnf_button_ok).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AbstractFilePickerFragment.this.onClickOk(view);
            }
        });
        inflateRootView.findViewById(R.id.nnf_button_ok_newfile).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AbstractFilePickerFragment.this.onClickOk(view);
            }
        });
        this.mNewFileButtonContainer = inflateRootView.findViewById(R.id.nnf_newfile_button_container);
        this.mRegularButtonContainer = inflateRootView.findViewById(R.id.nnf_button_container);
        this.mEditTextFileName = (EditText) inflateRootView.findViewById(R.id.nnf_text_filename);
        this.mEditTextFileName.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                AbstractFilePickerFragment.this.clearSelections();
            }
        });
        this.mCurrentDirView = (TextView) inflateRootView.findViewById(R.id.nnf_current_dir);
        T t = this.mCurrentPath;
        if (!(t == null || (textView = this.mCurrentDirView) == null)) {
            textView.setText(getFullPath(t));
        }
        return inflateRootView;
    }

    /* access modifiers changed from: protected */
    public View inflateRootView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.nnf_fragment_filepicker, viewGroup, false);
    }

    /* access modifiers changed from: protected */
    public void configureItemDecoration(LayoutInflater layoutInflater, RecyclerView recyclerView2) {
        TypedArray obtainStyledAttributes = getActivity().obtainStyledAttributes(new int[]{R.attr.nnf_list_item_divider});
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        if (drawable != null) {
            recyclerView2.addItemDecoration(new DividerItemDecoration(drawable));
        }
    }

    public void onClickCancel(View view) {
        OnFilePickedListener onFilePickedListener = this.mListener;
        if (onFilePickedListener != null) {
            onFilePickedListener.onCancelled();
        }
    }

    public void onClickOk(View view) {
        Uri uri;
        if (this.mListener != null) {
            if ((this.allowMultiple || this.mode == 0) && (this.mCheckedItems.isEmpty() || getFirstCheckedItem() == null)) {
                if (this.mToast == null) {
                    this.mToast = Toast.makeText(getActivity(), R.string.nnf_select_something_first, 0);
                }
                this.mToast.show();
                return;
            }
            int i = this.mode;
            if (i == 3) {
                String newFileName = getNewFileName();
                if (newFileName.startsWith("/")) {
                    uri = toUri(getPath(newFileName));
                } else {
                    uri = toUri(getPath(Utils.appendPath(getFullPath(this.mCurrentPath), newFileName)));
                }
                this.mListener.onFilePicked(uri);
            } else if (this.allowMultiple) {
                this.mListener.onFilesPicked(toUri(this.mCheckedItems));
            } else if (i == 0) {
                this.mListener.onFilePicked(toUri(getFirstCheckedItem()));
            } else if (i == 1) {
                this.mListener.onFilePicked(toUri(this.mCurrentPath));
            } else if (this.mCheckedItems.isEmpty()) {
                this.mListener.onFilePicked(toUri(this.mCurrentPath));
            } else {
                this.mListener.onFilePicked(toUri(getFirstCheckedItem()));
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getNewFileName() {
        return this.mEditTextFileName.getText().toString();
    }

    /* access modifiers changed from: protected */
    public void setupToolbar(Toolbar toolbar) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    public T getFirstCheckedItem() {
        Iterator<T> it = this.mCheckedItems.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public List<Uri> toUri(Iterable<T> iterable) {
        ArrayList arrayList = new ArrayList();
        for (T uri : iterable) {
            arrayList.add(toUri(uri));
        }
        return arrayList;
    }

    public boolean isCheckable(T t) {
        if (!isDir(t)) {
            int i = this.mode;
            if (i == 0 || i == 2 || this.allowExistingFile) {
                return true;
            }
            return false;
        } else if ((this.mode != 1 || !this.allowMultiple) && (this.mode != 2 || !this.allowMultiple)) {
            return false;
        }
        return true;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mListener = (OnFilePickedListener) context;
        } catch (ClassCastException unused) {
            throw new ClassCastException(context.toString() + " must implement OnFilePickedListener");
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public void onActivityCreated(Bundle bundle) {
        String string;
        super.onActivityCreated(bundle);
        if (this.mCurrentPath == null) {
            if (bundle != null) {
                this.mode = bundle.getInt(KEY_MODE, this.mode);
                this.allowCreateDir = bundle.getBoolean(KEY_ALLOW_DIR_CREATE, this.allowCreateDir);
                this.allowMultiple = bundle.getBoolean(KEY_ALLOW_MULTIPLE, this.allowMultiple);
                this.allowExistingFile = bundle.getBoolean(KEY_ALLOW_EXISTING_FILE, this.allowExistingFile);
                this.singleClick = bundle.getBoolean(KEY_SINGLE_CLICK, this.singleClick);
                String string2 = bundle.getString(KEY_CURRENT_PATH);
                if (string2 != null) {
                    this.mCurrentPath = getPath(string2.trim());
                }
            } else if (getArguments() != null) {
                this.mode = getArguments().getInt(KEY_MODE, this.mode);
                this.allowCreateDir = getArguments().getBoolean(KEY_ALLOW_DIR_CREATE, this.allowCreateDir);
                this.allowMultiple = getArguments().getBoolean(KEY_ALLOW_MULTIPLE, this.allowMultiple);
                this.allowExistingFile = getArguments().getBoolean(KEY_ALLOW_EXISTING_FILE, this.allowExistingFile);
                this.singleClick = getArguments().getBoolean(KEY_SINGLE_CLICK, this.singleClick);
                if (getArguments().containsKey(KEY_START_PATH) && (string = getArguments().getString(KEY_START_PATH)) != null) {
                    T path = getPath(string.trim());
                    if (isDir(path)) {
                        this.mCurrentPath = path;
                    } else {
                        this.mCurrentPath = getParent(path);
                        this.mEditTextFileName.setText(getName(path));
                    }
                }
            }
        }
        setModeView();
        if (this.mCurrentPath == null) {
            this.mCurrentPath = getRoot();
        }
        refresh(this.mCurrentPath);
    }

    /* access modifiers changed from: protected */
    public void setModeView() {
        int i = 0;
        boolean z = this.mode == 3;
        this.mNewFileButtonContainer.setVisibility(z ? 0 : 8);
        View view = this.mRegularButtonContainer;
        if (z) {
            i = 8;
        }
        view.setVisibility(i);
        if (!z && this.singleClick) {
            getActivity().findViewById(R.id.nnf_button_ok).setVisibility(8);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.picker_actions, menu);
        menu.findItem(R.id.nnf_action_createdir).setVisible(this.allowCreateDir);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (R.id.nnf_action_createdir != menuItem.getItemId()) {
            return false;
        }
        FragmentActivity activity = getActivity();
        if (!(activity instanceof AppCompatActivity)) {
            return true;
        }
        NewFolderFragment.showDialog(((AppCompatActivity) activity).getSupportFragmentManager(), this);
        return true;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(KEY_CURRENT_PATH, this.mCurrentPath.toString());
        bundle.putBoolean(KEY_ALLOW_MULTIPLE, this.allowMultiple);
        bundle.putBoolean(KEY_ALLOW_EXISTING_FILE, this.allowExistingFile);
        bundle.putBoolean(KEY_ALLOW_DIR_CREATE, this.allowCreateDir);
        bundle.putBoolean(KEY_SINGLE_CLICK, this.singleClick);
        bundle.putInt(KEY_MODE, this.mode);
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    /* access modifiers changed from: protected */
    public void refresh(T t) {
        if (hasPermission(t)) {
            this.mCurrentPath = t;
            this.isLoading = true;
            getLoaderManager().restartLoader(0, (Bundle) null, this);
            return;
        }
        handlePermission(t);
    }

    public Loader<SortedList<T>> onCreateLoader(int i, Bundle bundle) {
        return getLoader();
    }

    public void onLoadFinished(Loader<SortedList<T>> loader, SortedList<T> sortedList) {
        this.isLoading = false;
        this.mCheckedItems.clear();
        this.mCheckedVisibleViewHolders.clear();
        this.mFiles = sortedList;
        this.mAdapter.setList(sortedList);
        TextView textView = this.mCurrentDirView;
        if (textView != null) {
            textView.setText(getFullPath(this.mCurrentPath));
        }
        getLoaderManager().destroyLoader(0);
    }

    public void onLoaderReset(Loader<SortedList<T>> loader) {
        this.isLoading = false;
    }

    public int getItemViewType(int i, T t) {
        return isCheckable(t) ? 2 : 1;
    }

    public void onBindHeaderViewHolder(AbstractFilePickerFragment<T>.HeaderViewHolder headerViewHolder) {
        headerViewHolder.text.setText("..");
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new HeaderViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.nnf_filepicker_listitem_dir, viewGroup, false));
        }
        if (i != 2) {
            return new DirViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.nnf_filepicker_listitem_dir, viewGroup, false));
        }
        return new CheckableViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.nnf_filepicker_listitem_checkable, viewGroup, false));
    }

    public void onBindViewHolder(AbstractFilePickerFragment<T>.DirViewHolder dirViewHolder, int i, T t) {
        dirViewHolder.file = t;
        dirViewHolder.icon.setVisibility(isDir(t) ? 0 : 8);
        dirViewHolder.text.setText(getName(t));
        if (!isCheckable(t)) {
            return;
        }
        if (this.mCheckedItems.contains(t)) {
            CheckableViewHolder checkableViewHolder = (CheckableViewHolder) dirViewHolder;
            this.mCheckedVisibleViewHolders.add(checkableViewHolder);
            checkableViewHolder.checkbox.setChecked(true);
            return;
        }
        this.mCheckedVisibleViewHolders.remove(dirViewHolder);
        ((CheckableViewHolder) dirViewHolder).checkbox.setChecked(false);
    }

    public void clearSelections() {
        Iterator<AbstractFilePickerFragment<T>.CheckableViewHolder> it = this.mCheckedVisibleViewHolders.iterator();
        while (it.hasNext()) {
            it.next().checkbox.setChecked(false);
        }
        this.mCheckedVisibleViewHolders.clear();
        this.mCheckedItems.clear();
    }

    public void onClickHeader(View view, AbstractFilePickerFragment<T>.HeaderViewHolder headerViewHolder) {
        goUp();
    }

    public void goUp() {
        goToDir(getParent(this.mCurrentPath));
    }

    public void onClickDir(View view, AbstractFilePickerFragment<T>.DirViewHolder dirViewHolder) {
        if (isDir(dirViewHolder.file)) {
            goToDir(dirViewHolder.file);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r2 = r1.mode;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isItemVisible(T r2) {
        /*
            r1 = this;
            boolean r2 = r1.isDir(r2)
            if (r2 != 0) goto L_0x0017
            int r2 = r1.mode
            if (r2 == 0) goto L_0x0017
            r0 = 2
            if (r2 == r0) goto L_0x0017
            r0 = 3
            if (r2 != r0) goto L_0x0015
            boolean r2 = r1.allowExistingFile
            if (r2 == 0) goto L_0x0015
            goto L_0x0017
        L_0x0015:
            r2 = 0
            goto L_0x0018
        L_0x0017:
            r2 = 1
        L_0x0018:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nononsenseapps.filepicker.AbstractFilePickerFragment.isItemVisible(java.lang.Object):boolean");
    }

    public void goToDir(T t) {
        if (!this.isLoading) {
            this.mCheckedItems.clear();
            this.mCheckedVisibleViewHolders.clear();
            refresh(t);
        }
    }

    public void onClickCheckable(View view, AbstractFilePickerFragment<T>.CheckableViewHolder checkableViewHolder) {
        if (isDir(checkableViewHolder.file)) {
            goToDir(checkableViewHolder.file);
            return;
        }
        onLongClickCheckable(view, checkableViewHolder);
        if (this.singleClick) {
            onClickOk(view);
        }
    }

    public boolean onLongClickCheckable(View view, AbstractFilePickerFragment<T>.CheckableViewHolder checkableViewHolder) {
        if (3 == this.mode) {
            this.mEditTextFileName.setText(getName(checkableViewHolder.file));
        }
        onClickCheckBox(checkableViewHolder);
        return true;
    }

    public void onClickCheckBox(AbstractFilePickerFragment<T>.CheckableViewHolder checkableViewHolder) {
        if (this.mCheckedItems.contains(checkableViewHolder.file)) {
            checkableViewHolder.checkbox.setChecked(false);
            this.mCheckedItems.remove(checkableViewHolder.file);
            this.mCheckedVisibleViewHolders.remove(checkableViewHolder);
            return;
        }
        if (!this.allowMultiple) {
            clearSelections();
        }
        checkableViewHolder.checkbox.setChecked(true);
        this.mCheckedItems.add(checkableViewHolder.file);
        this.mCheckedVisibleViewHolders.add(checkableViewHolder);
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView text;

        public HeaderViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.text = (TextView) view.findViewById(16908308);
        }

        public void onClick(View view) {
            AbstractFilePickerFragment.this.onClickHeader(view, this);
        }
    }

    public class DirViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public T file;
        public View icon;
        public TextView text;

        public DirViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            this.icon = view.findViewById(R.id.item_icon);
            this.text = (TextView) view.findViewById(16908308);
        }

        public void onClick(View view) {
            AbstractFilePickerFragment.this.onClickDir(view, this);
        }

        public boolean onLongClick(View view) {
            return AbstractFilePickerFragment.this.onLongClickDir(view, this);
        }
    }

    public class CheckableViewHolder extends AbstractFilePickerFragment<T>.DirViewHolder {
        public CheckBox checkbox;

        public CheckableViewHolder(View view) {
            super(view);
            int i = 0;
            boolean z = AbstractFilePickerFragment.this.mode == 3;
            this.checkbox = (CheckBox) view.findViewById(R.id.checkbox);
            this.checkbox.setVisibility((z || AbstractFilePickerFragment.this.singleClick) ? 8 : i);
            this.checkbox.setOnClickListener(new View.OnClickListener(AbstractFilePickerFragment.this) {
                public void onClick(View view) {
                    AbstractFilePickerFragment.this.onClickCheckBox(CheckableViewHolder.this);
                }
            });
        }

        public void onClick(View view) {
            AbstractFilePickerFragment.this.onClickCheckable(view, this);
        }

        public boolean onLongClick(View view) {
            return AbstractFilePickerFragment.this.onLongClickCheckable(view, this);
        }
    }
}
