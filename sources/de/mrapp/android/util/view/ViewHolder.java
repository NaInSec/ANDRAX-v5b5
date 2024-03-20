package de.mrapp.android.util.view;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

public class ViewHolder extends RecyclerView.ViewHolder {
    private final View parentView;
    private SparseArray<View> views = null;

    public ViewHolder(View view) {
        super(view);
        this.parentView = view;
    }

    public final View getParentView() {
        return this.parentView;
    }

    public final View findViewById(int i) {
        View view;
        SparseArray<View> sparseArray = this.views;
        if (sparseArray != null) {
            view = sparseArray.get(i);
        } else {
            this.views = new SparseArray<>();
            view = null;
        }
        if (view != null) {
            return view;
        }
        View findViewById = this.parentView.findViewById(i);
        this.views.put(i, findViewById);
        return findViewById;
    }

    public final void clear() {
        SparseArray<View> sparseArray = this.views;
        if (sparseArray != null) {
            sparseArray.clear();
            this.views = null;
        }
    }
}
