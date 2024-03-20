package de.mrapp.android.tabswitcher.layout.phone;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v4.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.thecrackertechnology.dragonterminal.backend.KeyHandler;
import de.mrapp.android.tabswitcher.Tab;
import de.mrapp.android.tabswitcher.model.TabItem;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.multithreading.AbstractDataBinder;
import de.mrapp.android.util.view.ViewRecycler;

public class PreviewDataBinder extends AbstractDataBinder<Bitmap, Tab, ImageView, TabItem> {
    private final ViewRecycler<Tab, Void> childViewRecycler;
    private final ViewGroup parent;

    public PreviewDataBinder(ViewGroup viewGroup, ViewRecycler<Tab, Void> viewRecycler) {
        super(viewGroup.getContext(), new LruCache(7));
        Condition.ensureNotNull(viewGroup, "The parent may not be null");
        Condition.ensureNotNull(viewRecycler, "The child view recycler may not be null");
        this.parent = viewGroup;
        this.childViewRecycler = viewRecycler;
    }

    /* access modifiers changed from: protected */
    public final void onPreExecute(ImageView imageView, TabItem... tabItemArr) {
        TabItem tabItem = tabItemArr[0];
        PhoneTabViewHolder viewHolder = tabItem.getViewHolder();
        View view = viewHolder.child;
        Tab tab = tabItem.getTab();
        if (view == null) {
            view = (View) this.childViewRecycler.inflate(tab, viewHolder.childContainer, (ParamType[]) new Void[0]).first;
        } else {
            this.childViewRecycler.getAdapter().onShowView(getContext(), view, tab, false, new Void[0]);
        }
        viewHolder.child = view;
    }

    /* access modifiers changed from: protected */
    public final Bitmap doInBackground(Tab tab, TabItem... tabItemArr) {
        PhoneTabViewHolder viewHolder = tabItemArr[0].getViewHolder();
        View view = viewHolder.child;
        viewHolder.child = null;
        int width = this.parent.getWidth();
        int height = this.parent.getHeight();
        view.measure(View.MeasureSpec.makeMeasureSpec(width, KeyHandler.KEYMOD_CTRL), View.MeasureSpec.makeMeasureSpec(height, KeyHandler.KEYMOD_CTRL));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    /* access modifiers changed from: protected */
    public final void onPostExecute(ImageView imageView, Bitmap bitmap, TabItem... tabItemArr) {
        imageView.setImageBitmap(bitmap);
        imageView.setVisibility(bitmap != null ? 0 : 8);
        this.childViewRecycler.remove(tabItemArr[0].getTab());
    }
}
