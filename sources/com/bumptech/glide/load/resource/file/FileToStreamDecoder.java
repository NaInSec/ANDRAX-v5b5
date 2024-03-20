package com.bumptech.glide.load.resource.file;

import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileToStreamDecoder<T> implements ResourceDecoder<File, T> {
    private static final FileOpener DEFAULT_FILE_OPENER = new FileOpener();
    private final FileOpener fileOpener;
    private ResourceDecoder<InputStream, T> streamDecoder;

    public String getId() {
        return "";
    }

    public FileToStreamDecoder(ResourceDecoder<InputStream, T> resourceDecoder) {
        this(resourceDecoder, DEFAULT_FILE_OPENER);
    }

    FileToStreamDecoder(ResourceDecoder<InputStream, T> resourceDecoder, FileOpener fileOpener2) {
        this.streamDecoder = resourceDecoder;
        this.fileOpener = fileOpener2;
    }

    public Resource<T> decode(File file, int i, int i2) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = this.fileOpener.open(file);
            return this.streamDecoder.decode(inputStream, i, i2);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
            }
        }
    }

    static class FileOpener {
        FileOpener() {
        }

        public InputStream open(File file) throws FileNotFoundException {
            return new FileInputStream(file);
        }
    }
}
