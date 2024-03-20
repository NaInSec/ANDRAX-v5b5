package com.nononsenseapps.filepicker;

import android.net.Uri;
import android.support.v4.content.Loader;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface LogicHandler<T> {
    public static final int VIEWTYPE_CHECKABLE = 2;
    public static final int VIEWTYPE_DIR = 1;
    public static final int VIEWTYPE_HEADER = 0;

    String getFullPath(T t);

    int getItemViewType(int i, T t);

    Loader<SortedList<T>> getLoader();

    String getName(T t);

    T getParent(T t);

    T getPath(String str);

    T getRoot();

    boolean isDir(T t);

    void onBindHeaderViewHolder(AbstractFilePickerFragment<T>.HeaderViewHolder headerViewHolder);

    void onBindViewHolder(AbstractFilePickerFragment<T>.DirViewHolder dirViewHolder, int i, T t);

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i);

    Uri toUri(T t);
}
