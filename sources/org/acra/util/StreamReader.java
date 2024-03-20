package org.acra.util;

import android.text.TextUtils;
import com.android.internal.util.Predicate;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.acra.collections.BoundedLinkedList;
import org.apache.commons.lang3.StringUtils;

public class StreamReader {
    private static final int INDEFINITE = -1;
    private static final int NO_LIMIT = -1;
    private Predicate<String> filter;
    private final InputStream inputStream;
    private int limit;
    private int timeout;

    public StreamReader(String str) throws FileNotFoundException {
        this(new File(str));
    }

    public StreamReader(File file) throws FileNotFoundException {
        this((InputStream) new FileInputStream(file));
    }

    public StreamReader(InputStream inputStream2) {
        this.limit = -1;
        this.timeout = -1;
        this.filter = null;
        this.inputStream = inputStream2;
    }

    public StreamReader setLimit(int i) {
        this.limit = i;
        return this;
    }

    public StreamReader setTimeout(int i) {
        this.timeout = i;
        return this;
    }

    public StreamReader setFilter(Predicate<String> predicate) {
        this.filter = predicate;
        return this;
    }

    public String read() throws IOException {
        String[] split;
        int i;
        String readFully = this.timeout == -1 ? readFully() : readWithTimeout();
        if (this.filter != null) {
            String[] split2 = readFully.split("\\r?\\n");
            int i2 = this.limit;
            List linkedList = i2 == -1 ? new LinkedList() : new BoundedLinkedList(i2);
            for (String str : split2) {
                if (this.filter.apply(str)) {
                    linkedList.add(str);
                }
            }
            return TextUtils.join(StringUtils.LF, linkedList);
        } else if (this.limit != -1 && (split = readFully.split("\\r?\\n")).length > (i = this.limit)) {
            return TextUtils.join(StringUtils.LF, Arrays.copyOfRange(split, split.length - i, split.length));
        } else {
            return readFully;
        }
    }

    private String readFully() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(this.inputStream);
        try {
            StringWriter stringWriter = new StringWriter();
            char[] cArr = new char[8192];
            while (true) {
                int read = inputStreamReader.read(cArr);
                if (read == -1) {
                    return stringWriter.toString();
                }
                stringWriter.write(cArr, 0, read);
            }
        } finally {
            IOUtils.safeClose(inputStreamReader);
        }
    }

    private String readWithTimeout() throws IOException {
        long currentTimeMillis = System.currentTimeMillis() + ((long) this.timeout);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[8192];
            while (true) {
                int fillBufferUntil = fillBufferUntil(bArr, currentTimeMillis);
                if (fillBufferUntil == -1) {
                    return byteArrayOutputStream.toString();
                }
                byteArrayOutputStream.write(bArr, 0, fillBufferUntil);
            }
        } finally {
            IOUtils.safeClose(this.inputStream);
        }
    }

    private int fillBufferUntil(byte[] bArr, long j) throws IOException {
        int i = 0;
        while (System.currentTimeMillis() < j && i < bArr.length) {
            InputStream inputStream2 = this.inputStream;
            int read = inputStream2.read(bArr, i, Math.min(inputStream2.available(), bArr.length - i));
            if (read == -1) {
                break;
            }
            i += read;
        }
        return i;
    }
}
