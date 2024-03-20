package es.dmoral.coloromatic.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import es.dmoral.coloromatic.ColorOMaticUtil;
import es.dmoral.coloromatic.IndicatorMode;
import es.dmoral.coloromatic.R;
import es.dmoral.coloromatic.colormode.Channel;
import es.dmoral.coloromatic.colormode.ColorMode;
import es.dmoral.coloromatic.view.ChannelView;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class ColorOMaticView extends RelativeLayout {
    public static final int DEFAULT_COLOR = -7829368;
    public static final IndicatorMode DEFAULT_INDICATOR = IndicatorMode.DECIMAL;
    public static final ColorMode DEFAULT_MODE = ColorMode.RGB;
    public static final boolean DEFAULT_TEXT_INDICATOR_STATE = false;
    private final ColorMode colorMode;
    /* access modifiers changed from: private */
    public int currentColor;
    private IndicatorMode indicatorMode;
    private boolean showTextIndicator;

    public interface ButtonBarListener {
        void onNegativeButtonClick();

        void onPositiveButtonClick(int i);
    }

    public ColorOMaticView(Context context) {
        this(DEFAULT_COLOR, false, DEFAULT_MODE, DEFAULT_INDICATOR, context);
    }

    public ColorOMaticView(int i, ColorMode colorMode2, Context context) {
        this(i, false, colorMode2, DEFAULT_INDICATOR, context);
    }

    public ColorOMaticView(int i, boolean z, ColorMode colorMode2, IndicatorMode indicatorMode2, Context context) {
        super(context);
        this.indicatorMode = indicatorMode2;
        this.colorMode = colorMode2;
        this.currentColor = i;
        this.showTextIndicator = z;
        init();
    }

    /* access modifiers changed from: package-private */
    public void init() {
        inflate(getContext(), R.layout.chroma_view, this);
        setClipToPadding(false);
        final View findViewById = findViewById(R.id.color_view);
        findViewById.setBackgroundColor(this.currentColor);
        final TextView textView = (TextView) findViewById(R.id.tv_color_indicator);
        if (this.showTextIndicator) {
            textView.setVisibility(0);
        }
        List<Channel> channels = this.colorMode.getColorMode().getChannels();
        final ArrayList<ChannelView> arrayList = new ArrayList<>();
        for (Channel channelView : channels) {
            arrayList.add(new ChannelView(channelView, this.currentColor, getContext()));
        }
        updateText(findViewById, textView, arrayList, channels);
        AnonymousClass1 r0 = new ChannelView.OnProgressChangedListener() {
            public void onProgressChanged() {
                ArrayList arrayList = new ArrayList();
                for (ChannelView channel : arrayList) {
                    arrayList.add(channel.getChannel());
                }
                ColorOMaticView.this.updateText(findViewById, textView, arrayList, arrayList);
            }
        };
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.channel_container);
        for (ChannelView channelView2 : arrayList) {
            viewGroup.addView(channelView2);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) channelView2.getLayoutParams();
            layoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.channel_view_margin_top);
            layoutParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen.channel_view_margin_bottom);
            channelView2.registerListener(r0);
        }
    }

    /* access modifiers changed from: private */
    public void updateText(View view, TextView textView, List<ChannelView> list, List<Channel> list2) {
        this.currentColor = this.colorMode.getColorMode().evaluateColor(list2);
        view.setBackgroundColor(this.currentColor);
        if (this.indicatorMode == IndicatorMode.HEX) {
            textView.setText(ColorOMaticUtil.getFormattedColorString(this.currentColor, this.colorMode == ColorMode.ARGB));
        } else {
            String str = "";
            for (ChannelView channel : list) {
                str = str + channel.getChannel().getProgress() + StringUtils.SPACE;
            }
            textView.setText(String.valueOf(str.trim()));
        }
        if (!getResources().getBoolean(R.bool.tablet_mode)) {
            textView.setTextColor(getInverseColor(this.currentColor));
        }
    }

    private int getInverseColor(int i) {
        return Color.argb(Color.alpha(i), 255 - Color.red(i), 255 - Color.green(i), 255 - Color.blue(i));
    }

    public ColorMode getColorMode() {
        return this.colorMode;
    }

    public int getCurrentColor() {
        return this.currentColor;
    }

    public IndicatorMode getIndicatorMode() {
        return this.indicatorMode;
    }

    public boolean isShowTextIndicator() {
        return this.showTextIndicator;
    }

    public void enableButtonBar(final ButtonBarListener buttonBarListener) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.button_bar);
        TextView textView = (TextView) linearLayout.findViewById(R.id.positive_button);
        TextView textView2 = (TextView) linearLayout.findViewById(R.id.negative_button);
        if (buttonBarListener != null) {
            linearLayout.setVisibility(0);
            textView.setTextColor(ColorOMaticUtil.getThemeAccentColor(getContext()));
            textView2.setTextColor(ColorOMaticUtil.getThemeAccentColor(getContext()));
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    buttonBarListener.onPositiveButtonClick(ColorOMaticView.this.currentColor);
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    buttonBarListener.onNegativeButtonClick();
                }
            });
            return;
        }
        linearLayout.setVisibility(8);
        textView.setOnClickListener((View.OnClickListener) null);
        textView2.setOnClickListener((View.OnClickListener) null);
    }
}
