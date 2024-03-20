package es.dmoral.coloromatic.colormode.mode;

import android.graphics.Color;
import es.dmoral.coloromatic.R;
import es.dmoral.coloromatic.colormode.Channel;
import java.util.ArrayList;
import java.util.List;

public class ARGB implements AbstractColorMode {
    public List<Channel> getChannels() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Channel(R.string.channel_alpha, 0, 255, new Channel.ColorExtractor() {
            public int extract(int i) {
                return Color.alpha(i);
            }
        }));
        arrayList.add(new Channel(R.string.channel_red, 0, 255, new Channel.ColorExtractor() {
            public int extract(int i) {
                return Color.red(i);
            }
        }));
        arrayList.add(new Channel(R.string.channel_green, 0, 255, new Channel.ColorExtractor() {
            public int extract(int i) {
                return Color.green(i);
            }
        }));
        arrayList.add(new Channel(R.string.channel_blue, 0, 255, new Channel.ColorExtractor() {
            public int extract(int i) {
                return Color.blue(i);
            }
        }));
        return arrayList;
    }

    public int evaluateColor(List<Channel> list) {
        return Color.argb(list.get(0).getProgress(), list.get(1).getProgress(), list.get(2).getProgress(), list.get(3).getProgress());
    }
}
