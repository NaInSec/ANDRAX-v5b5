package es.dmoral.coloromatic.colormode.mode;

import es.dmoral.coloromatic.colormode.Channel;
import java.util.List;

public interface AbstractColorMode {
    int evaluateColor(List<Channel> list);

    List<Channel> getChannels();
}
