package com.thecrackertechnology.dragonterminal.setup;

import java.io.IOException;
import java.io.InputStream;

public interface SourceConnection {
    void close();

    InputStream getInputStream() throws IOException;

    int getSize();
}
