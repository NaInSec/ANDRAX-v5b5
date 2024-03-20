package es.dmoral.coloromatic.colormode.mode;

import android.graphics.Color;
import es.dmoral.coloromatic.R;
import es.dmoral.coloromatic.colormode.Channel;
import java.util.ArrayList;
import java.util.List;

public class HSV implements AbstractColorMode {
    /* access modifiers changed from: package-private */
    public float[] colorToHSV(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        return fArr;
    }

    public List<Channel> getChannels() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Channel(R.string.channel_hue, 0, 360, new Channel.ColorExtractor() {
            public int extract(int i) {
                return (int) HSV.this.colorToHSV(i)[0];
            }
        }));
        arrayList.add(new Channel(R.string.channel_saturation, 0, 100, new Channel.ColorExtractor() {
            public int extract(int i) {
                return (int) (HSV.this.colorToHSV(i)[1] * 100.0f);
            }
        }));
        arrayList.add(new Channel(R.string.channel_value, 0, 100, new Channel.ColorExtractor() {
            public int extract(int i) {
                return (int) (HSV.this.colorToHSV(i)[2] * 100.0f);
            }
        }));
        return arrayList;
    }

    public int evaluateColor(List<Channel> list) {
        return Color.HSVToColor(new float[]{(float) list.get(0).getProgress(), ((float) list.get(1).getProgress()) / 100.0f, ((float) list.get(2).getProgress()) / 100.0f});
    }
}
