package com.bumptech.glide.load.model;

import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.InputStream;

public class ImageVideoModelLoader<A> implements ModelLoader<A, ImageVideoWrapper> {
    private static final String TAG = "IVML";
    private final ModelLoader<A, ParcelFileDescriptor> fileDescriptorLoader;
    private final ModelLoader<A, InputStream> streamLoader;

    public ImageVideoModelLoader(ModelLoader<A, InputStream> modelLoader, ModelLoader<A, ParcelFileDescriptor> modelLoader2) {
        if (modelLoader == null && modelLoader2 == null) {
            throw new NullPointerException("At least one of streamLoader and fileDescriptorLoader must be non null");
        }
        this.streamLoader = modelLoader;
        this.fileDescriptorLoader = modelLoader2;
    }

    public DataFetcher<ImageVideoWrapper> getResourceFetcher(A a, int i, int i2) {
        ModelLoader<A, InputStream> modelLoader = this.streamLoader;
        DataFetcher<InputStream> resourceFetcher = modelLoader != null ? modelLoader.getResourceFetcher(a, i, i2) : null;
        ModelLoader<A, ParcelFileDescriptor> modelLoader2 = this.fileDescriptorLoader;
        DataFetcher<ParcelFileDescriptor> resourceFetcher2 = modelLoader2 != null ? modelLoader2.getResourceFetcher(a, i, i2) : null;
        if (resourceFetcher == null && resourceFetcher2 == null) {
            return null;
        }
        return new ImageVideoFetcher(resourceFetcher, resourceFetcher2);
    }

    static class ImageVideoFetcher implements DataFetcher<ImageVideoWrapper> {
        private final DataFetcher<ParcelFileDescriptor> fileDescriptorFetcher;
        private final DataFetcher<InputStream> streamFetcher;

        public ImageVideoFetcher(DataFetcher<InputStream> dataFetcher, DataFetcher<ParcelFileDescriptor> dataFetcher2) {
            this.streamFetcher = dataFetcher;
            this.fileDescriptorFetcher = dataFetcher2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0026 A[SYNTHETIC, Splitter:B:14:0x0026] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.bumptech.glide.load.model.ImageVideoWrapper loadData(com.bumptech.glide.Priority r6) throws java.lang.Exception {
            /*
                r5 = this;
                com.bumptech.glide.load.data.DataFetcher<java.io.InputStream> r0 = r5.streamFetcher
                r1 = 2
                r2 = 0
                java.lang.String r3 = "IVML"
                if (r0 == 0) goto L_0x0021
                java.lang.Object r0 = r0.loadData(r6)     // Catch:{ Exception -> 0x000f }
                java.io.InputStream r0 = (java.io.InputStream) r0     // Catch:{ Exception -> 0x000f }
                goto L_0x0022
            L_0x000f:
                r0 = move-exception
                boolean r4 = android.util.Log.isLoggable(r3, r1)
                if (r4 == 0) goto L_0x001b
                java.lang.String r4 = "Exception fetching input stream, trying ParcelFileDescriptor"
                android.util.Log.v(r3, r4, r0)
            L_0x001b:
                com.bumptech.glide.load.data.DataFetcher<android.os.ParcelFileDescriptor> r4 = r5.fileDescriptorFetcher
                if (r4 == 0) goto L_0x0020
                goto L_0x0021
            L_0x0020:
                throw r0
            L_0x0021:
                r0 = r2
            L_0x0022:
                com.bumptech.glide.load.data.DataFetcher<android.os.ParcelFileDescriptor> r4 = r5.fileDescriptorFetcher
                if (r4 == 0) goto L_0x003d
                java.lang.Object r6 = r4.loadData(r6)     // Catch:{ Exception -> 0x002d }
                android.os.ParcelFileDescriptor r6 = (android.os.ParcelFileDescriptor) r6     // Catch:{ Exception -> 0x002d }
                goto L_0x003e
            L_0x002d:
                r6 = move-exception
                boolean r1 = android.util.Log.isLoggable(r3, r1)
                if (r1 == 0) goto L_0x0039
                java.lang.String r1 = "Exception fetching ParcelFileDescriptor"
                android.util.Log.v(r3, r1, r6)
            L_0x0039:
                if (r0 == 0) goto L_0x003c
                goto L_0x003d
            L_0x003c:
                throw r6
            L_0x003d:
                r6 = r2
            L_0x003e:
                com.bumptech.glide.load.model.ImageVideoWrapper r1 = new com.bumptech.glide.load.model.ImageVideoWrapper
                r1.<init>(r0, r6)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.model.ImageVideoModelLoader.ImageVideoFetcher.loadData(com.bumptech.glide.Priority):com.bumptech.glide.load.model.ImageVideoWrapper");
        }

        public void cleanup() {
            DataFetcher<InputStream> dataFetcher = this.streamFetcher;
            if (dataFetcher != null) {
                dataFetcher.cleanup();
            }
            DataFetcher<ParcelFileDescriptor> dataFetcher2 = this.fileDescriptorFetcher;
            if (dataFetcher2 != null) {
                dataFetcher2.cleanup();
            }
        }

        public String getId() {
            DataFetcher<InputStream> dataFetcher = this.streamFetcher;
            if (dataFetcher != null) {
                return dataFetcher.getId();
            }
            return this.fileDescriptorFetcher.getId();
        }

        public void cancel() {
            DataFetcher<InputStream> dataFetcher = this.streamFetcher;
            if (dataFetcher != null) {
                dataFetcher.cancel();
            }
            DataFetcher<ParcelFileDescriptor> dataFetcher2 = this.fileDescriptorFetcher;
            if (dataFetcher2 != null) {
                dataFetcher2.cancel();
            }
        }
    }
}
