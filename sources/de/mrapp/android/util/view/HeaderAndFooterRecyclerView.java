package de.mrapp.android.util.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import de.mrapp.android.util.Condition;
import java.util.ArrayList;
import java.util.List;

public class HeaderAndFooterRecyclerView extends RecyclerView {
    private AdapterWrapper adapter;
    /* access modifiers changed from: private */
    public final List<View> footers = new ArrayList();
    /* access modifiers changed from: private */
    public final List<View> headers = new ArrayList();

    private class AdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int VIEW_TYPE_FOOTER = 4320;
        private static final int VIEW_TYPE_HEADER = 4319;
        private final RecyclerView.Adapter encapsulatedAdapter;

        public long getItemId(int i) {
            return (long) i;
        }

        private class HeaderViewHolder extends RecyclerView.ViewHolder {
            public HeaderViewHolder(ViewGroup viewGroup) {
                super(viewGroup);
            }
        }

        private class FooterViewHolder extends RecyclerView.ViewHolder {
            public FooterViewHolder(ViewGroup viewGroup) {
                super(viewGroup);
            }
        }

        private RecyclerView.AdapterDataObserver createDataObserver() {
            return new RecyclerView.AdapterDataObserver() {
                public void onChanged() {
                    super.onChanged();
                    AdapterWrapper.this.notifyDataSetChanged();
                }

                public void onItemRangeChanged(int i, int i2) {
                    super.onItemRangeChanged(i, i2);
                    AdapterWrapper adapterWrapper = AdapterWrapper.this;
                    adapterWrapper.notifyItemRangeChanged(i + HeaderAndFooterRecyclerView.this.getHeaderViewsCount(), i2);
                }

                public void onItemRangeChanged(int i, int i2, Object obj) {
                    super.onItemRangeChanged(i, i2, obj);
                    AdapterWrapper adapterWrapper = AdapterWrapper.this;
                    adapterWrapper.notifyItemRangeChanged(i + HeaderAndFooterRecyclerView.this.getHeaderViewsCount(), i2, obj);
                }

                public void onItemRangeInserted(int i, int i2) {
                    super.onItemRangeInserted(i, i2);
                    AdapterWrapper adapterWrapper = AdapterWrapper.this;
                    adapterWrapper.notifyItemRangeInserted(i + HeaderAndFooterRecyclerView.this.getHeaderViewsCount(), i2);
                }

                public void onItemRangeRemoved(int i, int i2) {
                    super.onItemRangeRemoved(i, i2);
                    AdapterWrapper adapterWrapper = AdapterWrapper.this;
                    adapterWrapper.notifyItemRangeRemoved(i + HeaderAndFooterRecyclerView.this.getHeaderViewsCount(), i2);
                }

                public void onItemRangeMoved(int i, int i2, int i3) {
                    super.onItemRangeMoved(i, i2, i3);
                    int headerViewsCount = HeaderAndFooterRecyclerView.this.getHeaderViewsCount();
                    for (int i4 = 0; i4 < i3; i4++) {
                        AdapterWrapper.this.notifyItemMoved(i + i4 + headerViewsCount, i2 + i4 + headerViewsCount);
                    }
                }
            };
        }

        public AdapterWrapper(RecyclerView.Adapter adapter) {
            Condition.ensureNotNull(adapter, "The adapter may not be null");
            this.encapsulatedAdapter = adapter;
            this.encapsulatedAdapter.registerAdapterDataObserver(createDataObserver());
        }

        public RecyclerView.Adapter getEncapsulatedAdapter() {
            return this.encapsulatedAdapter;
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == VIEW_TYPE_HEADER) {
                FrameLayout frameLayout = new FrameLayout(HeaderAndFooterRecyclerView.this.getContext());
                frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                return new HeaderViewHolder(frameLayout);
            } else if (i != VIEW_TYPE_FOOTER) {
                return this.encapsulatedAdapter.onCreateViewHolder(viewGroup, i);
            } else {
                FrameLayout frameLayout2 = new FrameLayout(HeaderAndFooterRecyclerView.this.getContext());
                frameLayout2.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                return new FooterViewHolder(frameLayout2);
            }
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            if (viewHolder instanceof HeaderViewHolder) {
                ViewGroup viewGroup = (ViewGroup) viewHolder.itemView;
                viewGroup.removeAllViews();
                viewGroup.addView((View) HeaderAndFooterRecyclerView.this.headers.get(i));
            } else if (viewHolder instanceof FooterViewHolder) {
                ViewGroup viewGroup2 = (ViewGroup) viewHolder.itemView;
                viewGroup2.removeAllViews();
                viewGroup2.addView((View) HeaderAndFooterRecyclerView.this.footers.get((i - this.encapsulatedAdapter.getItemCount()) - HeaderAndFooterRecyclerView.this.getHeaderViewsCount()));
            } else {
                this.encapsulatedAdapter.onBindViewHolder(viewHolder, i - HeaderAndFooterRecyclerView.this.getHeaderViewsCount());
            }
        }

        public int getItemViewType(int i) {
            if (i < HeaderAndFooterRecyclerView.this.getHeaderViewsCount()) {
                return VIEW_TYPE_HEADER;
            }
            return i < HeaderAndFooterRecyclerView.this.getHeaderViewsCount() + this.encapsulatedAdapter.getItemCount() ? this.encapsulatedAdapter.getItemViewType(i - HeaderAndFooterRecyclerView.this.getHeaderViewsCount()) : VIEW_TYPE_FOOTER;
        }

        public int getItemCount() {
            return this.encapsulatedAdapter.getItemCount() + HeaderAndFooterRecyclerView.this.getHeaderViewsCount() + HeaderAndFooterRecyclerView.this.getFooterViewsCount();
        }
    }

    private void notifyDataSetChanged() {
        AdapterWrapper adapterWrapper = this.adapter;
        if (adapterWrapper != null) {
            adapterWrapper.notifyDataSetChanged();
        }
    }

    public HeaderAndFooterRecyclerView(Context context) {
        super(context);
    }

    public HeaderAndFooterRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HeaderAndFooterRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public final void addHeaderView(View view) {
        Condition.ensureNotNull(view, "The view may not be null");
        this.headers.add(view);
        notifyDataSetChanged();
    }

    public final void removeHeaderView(View view) {
        Condition.ensureNotNull(view, "The view may not be null");
        for (int headerViewsCount = getHeaderViewsCount() - 1; headerViewsCount >= 0; headerViewsCount--) {
            if (this.headers.get(headerViewsCount) == view) {
                this.headers.remove(headerViewsCount);
                notifyDataSetChanged();
                return;
            }
        }
    }

    public final int getHeaderViewsCount() {
        return this.headers.size();
    }

    public final void addFooterView(View view) {
        Condition.ensureNotNull(view, "The view may not be null");
        this.footers.add(view);
        notifyDataSetChanged();
    }

    public final void removeFooterView(View view) {
        Condition.ensureNotNull(view, "The view may not be null");
        for (int footerViewsCount = getFooterViewsCount() - 1; footerViewsCount >= 0; footerViewsCount--) {
            if (this.footers.get(footerViewsCount) == view) {
                this.footers.remove(footerViewsCount);
                notifyDataSetChanged();
                return;
            }
        }
    }

    public final int getFooterViewsCount() {
        return this.footers.size();
    }

    public final RecyclerView.Adapter getAdapter() {
        AdapterWrapper adapterWrapper = this.adapter;
        if (adapterWrapper != null) {
            return adapterWrapper.getEncapsulatedAdapter();
        }
        return null;
    }

    public final void setAdapter(RecyclerView.Adapter adapter2) {
        if (adapter2 != null) {
            this.adapter = new AdapterWrapper(adapter2);
            super.setAdapter(this.adapter);
            return;
        }
        this.adapter = null;
        super.setAdapter((RecyclerView.Adapter) null);
    }
}
