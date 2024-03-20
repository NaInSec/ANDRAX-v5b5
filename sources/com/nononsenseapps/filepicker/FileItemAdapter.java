package com.nononsenseapps.filepicker;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.nononsenseapps.filepicker.AbstractFilePickerFragment;

public class FileItemAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected SortedList<T> mList = null;
    protected final LogicHandler<T> mLogic;

    public FileItemAdapter(LogicHandler<T> logicHandler) {
        this.mLogic = logicHandler;
    }

    public void setList(SortedList<T> sortedList) {
        this.mList = sortedList;
        notifyDataSetChanged();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.mLogic.onCreateViewHolder(viewGroup, i);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (i == 0) {
            this.mLogic.onBindHeaderViewHolder((AbstractFilePickerFragment.HeaderViewHolder) viewHolder);
            return;
        }
        int i2 = i - 1;
        this.mLogic.onBindViewHolder((AbstractFilePickerFragment.DirViewHolder) viewHolder, i2, this.mList.get(i2));
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        int i2 = i - 1;
        return this.mLogic.getItemViewType(i2, this.mList.get(i2));
    }

    public int getItemCount() {
        SortedList<T> sortedList = this.mList;
        if (sortedList == null) {
            return 0;
        }
        return sortedList.size() + 1;
    }

    /* access modifiers changed from: protected */
    public T getItem(int i) {
        if (i == 0) {
            return null;
        }
        return this.mList.get(i - 1);
    }
}
