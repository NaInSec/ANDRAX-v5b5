package com.afollestad.materialdialogs;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.internal.MDTintHelper;
import com.afollestad.materialdialogs.util.DialogUtils;

class DefaultRvAdapter extends RecyclerView.Adapter<DefaultVH> {
    /* access modifiers changed from: private */
    public InternalListCallback callback;
    /* access modifiers changed from: private */
    public final MaterialDialog dialog;
    private final GravityEnum itemGravity;
    private final int layout;

    interface InternalListCallback {
        boolean onItemSelected(MaterialDialog materialDialog, View view, int i, CharSequence charSequence, boolean z);
    }

    DefaultRvAdapter(MaterialDialog materialDialog, int i) {
        this.dialog = materialDialog;
        this.layout = i;
        this.itemGravity = materialDialog.builder.itemsGravity;
    }

    /* access modifiers changed from: package-private */
    public void setCallback(InternalListCallback internalListCallback) {
        this.callback = internalListCallback;
    }

    public DefaultVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(this.layout, viewGroup, false);
        DialogUtils.setBackgroundCompat(inflate, this.dialog.getListSelector());
        return new DefaultVH(inflate, this);
    }

    public void onBindViewHolder(DefaultVH defaultVH, int i) {
        View view = defaultVH.itemView;
        boolean isIn = DialogUtils.isIn(Integer.valueOf(i), this.dialog.builder.disabledIndices);
        int adjustAlpha = isIn ? DialogUtils.adjustAlpha(this.dialog.builder.itemColor, 0.4f) : this.dialog.builder.itemColor;
        defaultVH.itemView.setEnabled(!isIn);
        int i2 = AnonymousClass1.$SwitchMap$com$afollestad$materialdialogs$MaterialDialog$ListType[this.dialog.listType.ordinal()];
        if (i2 == 1) {
            RadioButton radioButton = (RadioButton) defaultVH.control;
            boolean z = this.dialog.builder.selectedIndex == i;
            if (this.dialog.builder.choiceWidgetColor != null) {
                MDTintHelper.setTint(radioButton, this.dialog.builder.choiceWidgetColor);
            } else {
                MDTintHelper.setTint(radioButton, this.dialog.builder.widgetColor);
            }
            radioButton.setChecked(z);
            radioButton.setEnabled(!isIn);
        } else if (i2 == 2) {
            CheckBox checkBox = (CheckBox) defaultVH.control;
            boolean contains = this.dialog.selectedIndicesList.contains(Integer.valueOf(i));
            if (this.dialog.builder.choiceWidgetColor != null) {
                MDTintHelper.setTint(checkBox, this.dialog.builder.choiceWidgetColor);
            } else {
                MDTintHelper.setTint(checkBox, this.dialog.builder.widgetColor);
            }
            checkBox.setChecked(contains);
            checkBox.setEnabled(!isIn);
        }
        defaultVH.title.setText(this.dialog.builder.items.get(i));
        defaultVH.title.setTextColor(adjustAlpha);
        this.dialog.setTypeface(defaultVH.title, this.dialog.builder.regularFont);
        ViewGroup viewGroup = (ViewGroup) view;
        setupGravity(viewGroup);
        if (this.dialog.builder.itemIds != null) {
            if (i < this.dialog.builder.itemIds.length) {
                view.setId(this.dialog.builder.itemIds[i]);
            } else {
                view.setId(-1);
            }
        }
        if (Build.VERSION.SDK_INT >= 21 && viewGroup.getChildCount() == 2) {
            if (viewGroup.getChildAt(0) instanceof CompoundButton) {
                viewGroup.getChildAt(0).setBackground((Drawable) null);
            } else if (viewGroup.getChildAt(1) instanceof CompoundButton) {
                viewGroup.getChildAt(1).setBackground((Drawable) null);
            }
        }
    }

    /* renamed from: com.afollestad.materialdialogs.DefaultRvAdapter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$afollestad$materialdialogs$MaterialDialog$ListType = new int[MaterialDialog.ListType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.afollestad.materialdialogs.MaterialDialog$ListType[] r0 = com.afollestad.materialdialogs.MaterialDialog.ListType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$afollestad$materialdialogs$MaterialDialog$ListType = r0
                int[] r0 = $SwitchMap$com$afollestad$materialdialogs$MaterialDialog$ListType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.afollestad.materialdialogs.MaterialDialog$ListType r1 = com.afollestad.materialdialogs.MaterialDialog.ListType.SINGLE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$afollestad$materialdialogs$MaterialDialog$ListType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.afollestad.materialdialogs.MaterialDialog$ListType r1 = com.afollestad.materialdialogs.MaterialDialog.ListType.MULTI     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.afollestad.materialdialogs.DefaultRvAdapter.AnonymousClass1.<clinit>():void");
        }
    }

    public int getItemCount() {
        if (this.dialog.builder.items != null) {
            return this.dialog.builder.items.size();
        }
        return 0;
    }

    private void setupGravity(ViewGroup viewGroup) {
        ((LinearLayout) viewGroup).setGravity(this.itemGravity.getGravityInt() | 16);
        if (viewGroup.getChildCount() != 2) {
            return;
        }
        if (this.itemGravity == GravityEnum.END && !isRTL() && (viewGroup.getChildAt(0) instanceof CompoundButton)) {
            CompoundButton compoundButton = (CompoundButton) viewGroup.getChildAt(0);
            viewGroup.removeView(compoundButton);
            TextView textView = (TextView) viewGroup.getChildAt(0);
            viewGroup.removeView(textView);
            textView.setPadding(textView.getPaddingRight(), textView.getPaddingTop(), textView.getPaddingLeft(), textView.getPaddingBottom());
            viewGroup.addView(textView);
            viewGroup.addView(compoundButton);
        } else if (this.itemGravity == GravityEnum.START && isRTL() && (viewGroup.getChildAt(1) instanceof CompoundButton)) {
            CompoundButton compoundButton2 = (CompoundButton) viewGroup.getChildAt(1);
            viewGroup.removeView(compoundButton2);
            TextView textView2 = (TextView) viewGroup.getChildAt(0);
            viewGroup.removeView(textView2);
            textView2.setPadding(textView2.getPaddingRight(), textView2.getPaddingTop(), textView2.getPaddingRight(), textView2.getPaddingBottom());
            viewGroup.addView(compoundButton2);
            viewGroup.addView(textView2);
        }
    }

    private boolean isRTL() {
        if (Build.VERSION.SDK_INT >= 17 && this.dialog.getBuilder().getContext().getResources().getConfiguration().getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }

    static class DefaultVH extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        final DefaultRvAdapter adapter;
        final CompoundButton control;
        final TextView title;

        DefaultVH(View view, DefaultRvAdapter defaultRvAdapter) {
            super(view);
            this.control = (CompoundButton) view.findViewById(R.id.md_control);
            this.title = (TextView) view.findViewById(R.id.md_title);
            this.adapter = defaultRvAdapter;
            view.setOnClickListener(this);
            if (defaultRvAdapter.dialog.builder.listLongCallback != null) {
                view.setOnLongClickListener(this);
            }
        }

        public void onClick(View view) {
            if (this.adapter.callback != null && getAdapterPosition() != -1) {
                CharSequence charSequence = null;
                if (this.adapter.dialog.builder.items != null && getAdapterPosition() < this.adapter.dialog.builder.items.size()) {
                    charSequence = this.adapter.dialog.builder.items.get(getAdapterPosition());
                }
                View view2 = view;
                this.adapter.callback.onItemSelected(this.adapter.dialog, view2, getAdapterPosition(), charSequence, false);
            }
        }

        public boolean onLongClick(View view) {
            if (this.adapter.callback == null || getAdapterPosition() == -1) {
                return false;
            }
            CharSequence charSequence = null;
            if (this.adapter.dialog.builder.items != null && getAdapterPosition() < this.adapter.dialog.builder.items.size()) {
                charSequence = this.adapter.dialog.builder.items.get(getAdapterPosition());
            }
            return this.adapter.callback.onItemSelected(this.adapter.dialog, view, getAdapterPosition(), charSequence, true);
        }
    }
}
