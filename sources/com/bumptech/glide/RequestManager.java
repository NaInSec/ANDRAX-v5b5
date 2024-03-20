package com.bumptech.glide;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.file_descriptor.FileDescriptorModelLoader;
import com.bumptech.glide.load.model.stream.MediaStoreStreamLoader;
import com.bumptech.glide.load.model.stream.StreamByteArrayLoader;
import com.bumptech.glide.load.model.stream.StreamModelLoader;
import com.bumptech.glide.manager.ConnectivityMonitor;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.LifecycleListener;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.signature.ApplicationVersionSignature;
import com.bumptech.glide.signature.MediaStoreSignature;
import com.bumptech.glide.signature.StringSignature;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

public class RequestManager implements LifecycleListener {
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final Glide glide;
    /* access modifiers changed from: private */
    public final Lifecycle lifecycle;
    /* access modifiers changed from: private */
    public DefaultOptions options;
    /* access modifiers changed from: private */
    public final OptionsApplier optionsApplier;
    /* access modifiers changed from: private */
    public final RequestTracker requestTracker;
    private final RequestManagerTreeNode treeNode;

    public interface DefaultOptions {
        <T> void apply(GenericRequestBuilder<T, ?, ?, ?> genericRequestBuilder);
    }

    public RequestManager(Context context2, Lifecycle lifecycle2, RequestManagerTreeNode requestManagerTreeNode) {
        this(context2, lifecycle2, requestManagerTreeNode, new RequestTracker(), new ConnectivityMonitorFactory());
    }

    RequestManager(Context context2, final Lifecycle lifecycle2, RequestManagerTreeNode requestManagerTreeNode, RequestTracker requestTracker2, ConnectivityMonitorFactory connectivityMonitorFactory) {
        this.context = context2.getApplicationContext();
        this.lifecycle = lifecycle2;
        this.treeNode = requestManagerTreeNode;
        this.requestTracker = requestTracker2;
        this.glide = Glide.get(context2);
        this.optionsApplier = new OptionsApplier();
        ConnectivityMonitor build = connectivityMonitorFactory.build(context2, new RequestManagerConnectivityListener(requestTracker2));
        if (Util.isOnBackgroundThread()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    lifecycle2.addListener(RequestManager.this);
                }
            });
        } else {
            lifecycle2.addListener(this);
        }
        lifecycle2.addListener(build);
    }

    public void onTrimMemory(int i) {
        this.glide.trimMemory(i);
    }

    public void onLowMemory() {
        this.glide.clearMemory();
    }

    public void setDefaultOptions(DefaultOptions defaultOptions) {
        this.options = defaultOptions;
    }

    public boolean isPaused() {
        Util.assertMainThread();
        return this.requestTracker.isPaused();
    }

    public void pauseRequests() {
        Util.assertMainThread();
        this.requestTracker.pauseRequests();
    }

    public void pauseRequestsRecursive() {
        Util.assertMainThread();
        pauseRequests();
        for (RequestManager pauseRequests : this.treeNode.getDescendants()) {
            pauseRequests.pauseRequests();
        }
    }

    public void resumeRequests() {
        Util.assertMainThread();
        this.requestTracker.resumeRequests();
    }

    public void resumeRequestsRecursive() {
        Util.assertMainThread();
        resumeRequests();
        for (RequestManager resumeRequests : this.treeNode.getDescendants()) {
            resumeRequests.resumeRequests();
        }
    }

    public void onStart() {
        resumeRequests();
    }

    public void onStop() {
        pauseRequests();
    }

    public void onDestroy() {
        this.requestTracker.clearRequests();
    }

    public <A, T> GenericModelRequest<A, T> using(ModelLoader<A, T> modelLoader, Class<T> cls) {
        return new GenericModelRequest<>(modelLoader, cls);
    }

    public <T> ImageModelRequest<T> using(StreamModelLoader<T> streamModelLoader) {
        return new ImageModelRequest<>(streamModelLoader);
    }

    public ImageModelRequest<byte[]> using(StreamByteArrayLoader streamByteArrayLoader) {
        return new ImageModelRequest<>(streamByteArrayLoader);
    }

    public <T> VideoModelRequest<T> using(FileDescriptorModelLoader<T> fileDescriptorModelLoader) {
        return new VideoModelRequest<>(fileDescriptorModelLoader);
    }

    public DrawableTypeRequest<String> load(String str) {
        return (DrawableTypeRequest) fromString().load(str);
    }

    public DrawableTypeRequest<String> fromString() {
        return loadGeneric(String.class);
    }

    public DrawableTypeRequest<Uri> load(Uri uri) {
        return (DrawableTypeRequest) fromUri().load(uri);
    }

    public DrawableTypeRequest<Uri> fromUri() {
        return loadGeneric(Uri.class);
    }

    @Deprecated
    public DrawableTypeRequest<Uri> loadFromMediaStore(Uri uri, String str, long j, int i) {
        return (DrawableTypeRequest) loadFromMediaStore(uri).signature((Key) new MediaStoreSignature(str, j, i));
    }

    public DrawableTypeRequest<Uri> loadFromMediaStore(Uri uri) {
        return (DrawableTypeRequest) fromMediaStore().load(uri);
    }

    public DrawableTypeRequest<Uri> fromMediaStore() {
        MediaStoreStreamLoader mediaStoreStreamLoader = new MediaStoreStreamLoader(this.context, Glide.buildStreamModelLoader(Uri.class, this.context));
        ModelLoader<Uri, ParcelFileDescriptor> buildFileDescriptorModelLoader = Glide.buildFileDescriptorModelLoader(Uri.class, this.context);
        OptionsApplier optionsApplier2 = this.optionsApplier;
        return (DrawableTypeRequest) optionsApplier2.apply(new DrawableTypeRequest(Uri.class, mediaStoreStreamLoader, buildFileDescriptorModelLoader, this.context, this.glide, this.requestTracker, this.lifecycle, optionsApplier2));
    }

    public DrawableTypeRequest<File> load(File file) {
        return (DrawableTypeRequest) fromFile().load(file);
    }

    public DrawableTypeRequest<File> fromFile() {
        return loadGeneric(File.class);
    }

    public DrawableTypeRequest<Integer> load(Integer num) {
        return (DrawableTypeRequest) fromResource().load(num);
    }

    public DrawableTypeRequest<Integer> fromResource() {
        return (DrawableTypeRequest) loadGeneric(Integer.class).signature(ApplicationVersionSignature.obtain(this.context));
    }

    @Deprecated
    public DrawableTypeRequest<URL> load(URL url) {
        return (DrawableTypeRequest) fromUrl().load(url);
    }

    @Deprecated
    public DrawableTypeRequest<URL> fromUrl() {
        return loadGeneric(URL.class);
    }

    @Deprecated
    public DrawableTypeRequest<byte[]> load(byte[] bArr, String str) {
        return (DrawableTypeRequest) load(bArr).signature((Key) new StringSignature(str));
    }

    public DrawableTypeRequest<byte[]> load(byte[] bArr) {
        return (DrawableTypeRequest) fromBytes().load(bArr);
    }

    public DrawableTypeRequest<byte[]> fromBytes() {
        return (DrawableTypeRequest) loadGeneric(byte[].class).signature((Key) new StringSignature(UUID.randomUUID().toString())).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true);
    }

    public <T> DrawableTypeRequest<T> load(T t) {
        return (DrawableTypeRequest) loadGeneric(getSafeClass(t)).load(t);
    }

    public <T> DrawableTypeRequest<T> from(Class<T> cls) {
        return loadGeneric(cls);
    }

    private <T> DrawableTypeRequest<T> loadGeneric(Class<T> cls) {
        ModelLoader<T, InputStream> buildStreamModelLoader = Glide.buildStreamModelLoader(cls, this.context);
        ModelLoader<T, ParcelFileDescriptor> buildFileDescriptorModelLoader = Glide.buildFileDescriptorModelLoader(cls, this.context);
        if (cls != null && buildStreamModelLoader == null && buildFileDescriptorModelLoader == null) {
            throw new IllegalArgumentException("Unknown type " + cls + ". You must provide a Model of a type for" + " which there is a registered ModelLoader, if you are using a custom model, you must first call" + " Glide#register with a ModelLoaderFactory for your custom model class");
        }
        OptionsApplier optionsApplier2 = this.optionsApplier;
        return (DrawableTypeRequest) optionsApplier2.apply(new DrawableTypeRequest(cls, buildStreamModelLoader, buildFileDescriptorModelLoader, this.context, this.glide, this.requestTracker, this.lifecycle, optionsApplier2));
    }

    /* access modifiers changed from: private */
    public static <T> Class<T> getSafeClass(T t) {
        if (t != null) {
            return t.getClass();
        }
        return null;
    }

    public final class VideoModelRequest<T> {
        private final ModelLoader<T, ParcelFileDescriptor> loader;

        VideoModelRequest(ModelLoader<T, ParcelFileDescriptor> modelLoader) {
            this.loader = modelLoader;
        }

        public DrawableTypeRequest<T> load(T t) {
            return (DrawableTypeRequest) ((DrawableTypeRequest) RequestManager.this.optionsApplier.apply(new DrawableTypeRequest(RequestManager.getSafeClass(t), (ModelLoader) null, this.loader, RequestManager.this.context, RequestManager.this.glide, RequestManager.this.requestTracker, RequestManager.this.lifecycle, RequestManager.this.optionsApplier))).load(t);
        }
    }

    public final class ImageModelRequest<T> {
        private final ModelLoader<T, InputStream> loader;

        ImageModelRequest(ModelLoader<T, InputStream> modelLoader) {
            this.loader = modelLoader;
        }

        public DrawableTypeRequest<T> from(Class<T> cls) {
            return (DrawableTypeRequest) RequestManager.this.optionsApplier.apply(new DrawableTypeRequest(cls, this.loader, (ModelLoader) null, RequestManager.this.context, RequestManager.this.glide, RequestManager.this.requestTracker, RequestManager.this.lifecycle, RequestManager.this.optionsApplier));
        }

        public DrawableTypeRequest<T> load(T t) {
            return (DrawableTypeRequest) from(RequestManager.getSafeClass(t)).load(t);
        }
    }

    public final class GenericModelRequest<A, T> {
        /* access modifiers changed from: private */
        public final Class<T> dataClass;
        /* access modifiers changed from: private */
        public final ModelLoader<A, T> modelLoader;

        GenericModelRequest(ModelLoader<A, T> modelLoader2, Class<T> cls) {
            this.modelLoader = modelLoader2;
            this.dataClass = cls;
        }

        public GenericModelRequest<A, T>.GenericTypeRequest from(Class<A> cls) {
            return new GenericTypeRequest(cls);
        }

        public GenericModelRequest<A, T>.GenericTypeRequest load(A a) {
            return new GenericTypeRequest(a);
        }

        public final class GenericTypeRequest {
            private final A model;
            private final Class<A> modelClass;
            private final boolean providedModel;

            GenericTypeRequest(A a) {
                this.providedModel = true;
                this.model = a;
                this.modelClass = RequestManager.getSafeClass(a);
            }

            GenericTypeRequest(Class<A> cls) {
                this.providedModel = false;
                this.model = null;
                this.modelClass = cls;
            }

            public <Z> GenericTranscodeRequest<A, T, Z> as(Class<Z> cls) {
                GenericTranscodeRequest<A, T, Z> genericTranscodeRequest = (GenericTranscodeRequest) RequestManager.this.optionsApplier.apply(new GenericTranscodeRequest(RequestManager.this.context, RequestManager.this.glide, this.modelClass, GenericModelRequest.this.modelLoader, GenericModelRequest.this.dataClass, cls, RequestManager.this.requestTracker, RequestManager.this.lifecycle, RequestManager.this.optionsApplier));
                if (this.providedModel) {
                    genericTranscodeRequest.load(this.model);
                }
                return genericTranscodeRequest;
            }
        }
    }

    class OptionsApplier {
        OptionsApplier() {
        }

        public <A, X extends GenericRequestBuilder<A, ?, ?, ?>> X apply(X x) {
            if (RequestManager.this.options != null) {
                RequestManager.this.options.apply(x);
            }
            return x;
        }
    }

    private static class RequestManagerConnectivityListener implements ConnectivityMonitor.ConnectivityListener {
        private final RequestTracker requestTracker;

        public RequestManagerConnectivityListener(RequestTracker requestTracker2) {
            this.requestTracker = requestTracker2;
        }

        public void onConnectivityChanged(boolean z) {
            if (z) {
                this.requestTracker.restartRequests();
            }
        }
    }
}
