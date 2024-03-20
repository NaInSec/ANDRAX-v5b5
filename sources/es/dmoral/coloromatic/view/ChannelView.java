package es.dmoral.coloromatic.view;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import es.dmoral.coloromatic.R;
import es.dmoral.coloromatic.colormode.Channel;

public class ChannelView extends RelativeLayout {
    /* access modifiers changed from: private */
    public final Channel channel;
    private Context context;
    /* access modifiers changed from: private */
    public OnProgressChangedListener listener;

    public interface OnProgressChangedListener {
        void onProgressChanged();
    }

    public ChannelView(Channel channel2, int i, Context context2) {
        super(context2);
        this.channel = channel2;
        this.context = context2;
        channel2.setProgress(channel2.getExtractor().extract(i));
        if (channel2.getProgress() < channel2.getMin() || channel2.getProgress() > channel2.getMax()) {
            throw new IllegalArgumentException("Initial progress for channel: " + channel2.getClass().getSimpleName() + " must be between " + channel2.getMin() + " and " + channel2.getMax());
        }
        bindViews(inflate(context2, R.layout.channel_row, this));
    }

    private void bindViews(View view) {
        ((TextView) view.findViewById(R.id.label)).setText(this.context.getString(this.channel.getNameResourceId()));
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekbar);
        seekBar.setMax(this.channel.getMax());
        seekBar.setProgress(this.channel.getProgress());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                ChannelView.this.channel.setProgress(i);
                if (ChannelView.this.listener != null) {
                    ChannelView.this.listener.onProgressChanged();
                }
            }
        });
    }

    public void registerListener(OnProgressChangedListener onProgressChangedListener) {
        this.listener = onProgressChangedListener;
    }

    public Channel getChannel() {
        return this.channel;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.listener = null;
    }
}
