package es.dmoral.coloromatic;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.View;
import es.dmoral.coloromatic.colormode.ColorMode;
import es.dmoral.coloromatic.view.ColorOMaticView;

public class ColorOMaticDialog extends DialogFragment {
    private static final String ARG_COLOR_MODE_ID = "arg_color_mode_id";
    private static final String ARG_INDICATOR_MODE = "arg_indicator_mode";
    private static final String ARG_INITIAL_COLOR = "arg_initial_color";
    private static final String ARG_SHOW_COLOR_INDICATOR = "arg_show_color_indicator";
    private ColorOMaticView colorOMaticView;
    /* access modifiers changed from: private */
    public OnColorSelectedListener listener;

    /* access modifiers changed from: private */
    public static ColorOMaticDialog newInstance(int i, ColorMode colorMode, IndicatorMode indicatorMode, boolean z) {
        ColorOMaticDialog colorOMaticDialog = new ColorOMaticDialog();
        colorOMaticDialog.setArguments(makeArgs(i, colorMode, indicatorMode, z));
        return colorOMaticDialog;
    }

    private static Bundle makeArgs(int i, ColorMode colorMode, IndicatorMode indicatorMode, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_INITIAL_COLOR, i);
        bundle.putInt(ARG_COLOR_MODE_ID, colorMode.ordinal());
        bundle.putInt(ARG_INDICATOR_MODE, indicatorMode.ordinal());
        bundle.putBoolean(ARG_SHOW_COLOR_INDICATOR, z);
        return bundle;
    }

    public void setListener(OnColorSelectedListener onColorSelectedListener) {
        this.listener = onColorSelectedListener;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Bundle bundle2 = bundle;
        if (bundle2 == null) {
            this.colorOMaticView = new ColorOMaticView(getArguments().getInt(ARG_INITIAL_COLOR), getArguments().getBoolean(ARG_SHOW_COLOR_INDICATOR), ColorMode.values()[getArguments().getInt(ARG_COLOR_MODE_ID)], IndicatorMode.values()[getArguments().getInt(ARG_INDICATOR_MODE)], getActivity());
        } else {
            this.colorOMaticView = new ColorOMaticView(bundle2.getInt(ARG_INITIAL_COLOR, ColorOMaticView.DEFAULT_COLOR), bundle2.getBoolean(ARG_SHOW_COLOR_INDICATOR), ColorMode.values()[bundle2.getInt(ARG_COLOR_MODE_ID)], IndicatorMode.values()[bundle2.getInt(ARG_INDICATOR_MODE)], getActivity());
        }
        this.colorOMaticView.enableButtonBar(new ColorOMaticView.ButtonBarListener() {
            public void onPositiveButtonClick(int i) {
                if (ColorOMaticDialog.this.listener != null) {
                    ColorOMaticDialog.this.listener.onColorSelected(i);
                }
                ColorOMaticDialog.this.dismiss();
            }

            public void onNegativeButtonClick() {
                ColorOMaticDialog.this.dismiss();
            }
        });
        final AlertDialog create = new AlertDialog.Builder(getActivity(), getTheme()).setView((View) this.colorOMaticView).create();
        if (Build.VERSION.SDK_INT >= 8) {
            create.setOnShowListener(new DialogInterface.OnShowListener() {
                public void onShow(DialogInterface dialogInterface) {
                    ColorOMaticDialog.this.measureLayout(create);
                }
            });
        } else {
            measureLayout(create);
        }
        return create;
    }

    /* access modifiers changed from: package-private */
    public void measureLayout(AlertDialog alertDialog) {
        double d = getResources().getConfiguration().orientation == 2 ? getResources().getBoolean(R.bool.tablet_mode) ? 2.0d : 1.5d : 1.0d;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        alertDialog.getWindow().setLayout((int) (((double) getResources().getDimensionPixelSize(R.dimen.chroma_dialog_width)) * d), getResources().getConfiguration().orientation == 2 ? (int) (((double) displayMetrics.heightPixels) * 0.8d) : -2);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putAll(makeArgs(this.colorOMaticView.getCurrentColor(), this.colorOMaticView.getColorMode(), this.colorOMaticView.getIndicatorMode(), this.colorOMaticView.isShowTextIndicator()));
        super.onSaveInstanceState(bundle);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.listener = null;
    }

    public static class Builder {
        private ColorMode colorMode = ColorOMaticView.DEFAULT_MODE;
        private IndicatorMode indicatorMode = IndicatorMode.DECIMAL;
        private int initialColor = ColorOMaticView.DEFAULT_COLOR;
        private OnColorSelectedListener listener = null;
        private boolean showColorIndicator = false;

        public Builder initialColor(int i) {
            this.initialColor = i;
            return this;
        }

        public Builder colorMode(ColorMode colorMode2) {
            this.colorMode = colorMode2;
            return this;
        }

        public Builder showColorIndicator(boolean z) {
            this.showColorIndicator = z;
            return this;
        }

        public Builder indicatorMode(IndicatorMode indicatorMode2) {
            this.indicatorMode = indicatorMode2;
            return this;
        }

        public Builder onColorSelected(OnColorSelectedListener onColorSelectedListener) {
            this.listener = onColorSelectedListener;
            return this;
        }

        public ColorOMaticDialog create() {
            ColorOMaticDialog access$100 = ColorOMaticDialog.newInstance(this.initialColor, this.colorMode, this.indicatorMode, this.showColorIndicator);
            access$100.setListener(this.listener);
            return access$100;
        }
    }
}
