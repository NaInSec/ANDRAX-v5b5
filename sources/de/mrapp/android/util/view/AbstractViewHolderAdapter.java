package de.mrapp.android.util.view;

import android.view.View;
import de.mrapp.android.util.Condition;

public abstract class AbstractViewHolderAdapter {
    private View currentParentView;

    /* access modifiers changed from: protected */
    public final void setCurrentParentView(View view) {
        Condition.ensureNotNull(view, "The parent view may not be null");
        this.currentParentView = view;
    }

    /* access modifiers changed from: protected */
    public final <ViewType extends View> ViewType findViewById(int i) {
        Condition.ensureNotNull(this.currentParentView, "No parent view set", IllegalStateException.class);
        ViewHolder viewHolder = (ViewHolder) this.currentParentView.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder(this.currentParentView);
            this.currentParentView.setTag(viewHolder);
        }
        return viewHolder.findViewById(i);
    }
}
