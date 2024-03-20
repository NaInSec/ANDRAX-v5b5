package com.bumptech.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.provider.LoadProvider;
import com.bumptech.glide.request.animation.GlideAnimationFactory;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import java.util.Queue;

public final class GenericRequest<A, T, Z, R> implements Request, SizeReadyCallback, ResourceCallback {
    private static final Queue<GenericRequest<?, ?, ?, ?>> REQUEST_POOL = Util.createQueue(0);
    private static final String TAG = "GenericRequest";
    private static final double TO_MEGABYTE = 9.5367431640625E-7d;
    private GlideAnimationFactory<R> animationFactory;
    private Context context;
    private DiskCacheStrategy diskCacheStrategy;
    private Engine engine;
    private Drawable errorDrawable;
    private int errorResourceId;
    private Drawable fallbackDrawable;
    private int fallbackResourceId;
    private boolean isMemoryCacheable;
    private LoadProvider<A, T, Z, R> loadProvider;
    private Engine.LoadStatus loadStatus;
    private boolean loadedFromMemoryCache;
    private A model;
    private int overrideHeight;
    private int overrideWidth;
    private Drawable placeholderDrawable;
    private int placeholderResourceId;
    private Priority priority;
    private RequestCoordinator requestCoordinator;
    private RequestListener<? super A, R> requestListener;
    private Resource<?> resource;
    private Key signature;
    private float sizeMultiplier;
    private long startTime;
    private Status status;
    private final String tag = String.valueOf(hashCode());
    private Target<R> target;
    private Class<R> transcodeClass;
    private Transformation<Z> transformation;

    private enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CANCELLED,
        CLEARED,
        PAUSED
    }

    public static <A, T, Z, R> GenericRequest<A, T, Z, R> obtain(LoadProvider<A, T, Z, R> loadProvider2, A a, Key key, Context context2, Priority priority2, Target<R> target2, float f, Drawable drawable, int i, Drawable drawable2, int i2, Drawable drawable3, int i3, RequestListener<? super A, R> requestListener2, RequestCoordinator requestCoordinator2, Engine engine2, Transformation<Z> transformation2, Class<R> cls, boolean z, GlideAnimationFactory<R> glideAnimationFactory, int i4, int i5, DiskCacheStrategy diskCacheStrategy2) {
        GenericRequest<A, T, Z, R> poll = REQUEST_POOL.poll();
        if (poll == null) {
            poll = new GenericRequest<>();
        }
        poll.init(loadProvider2, a, key, context2, priority2, target2, f, drawable, i, drawable2, i2, drawable3, i3, requestListener2, requestCoordinator2, engine2, transformation2, cls, z, glideAnimationFactory, i4, i5, diskCacheStrategy2);
        return poll;
    }

    private GenericRequest() {
    }

    public void recycle() {
        this.loadProvider = null;
        this.model = null;
        this.context = null;
        this.target = null;
        this.placeholderDrawable = null;
        this.errorDrawable = null;
        this.fallbackDrawable = null;
        this.requestListener = null;
        this.requestCoordinator = null;
        this.transformation = null;
        this.animationFactory = null;
        this.loadedFromMemoryCache = false;
        this.loadStatus = null;
        REQUEST_POOL.offer(this);
    }

    private void init(LoadProvider<A, T, Z, R> loadProvider2, A a, Key key, Context context2, Priority priority2, Target<R> target2, float f, Drawable drawable, int i, Drawable drawable2, int i2, Drawable drawable3, int i3, RequestListener<? super A, R> requestListener2, RequestCoordinator requestCoordinator2, Engine engine2, Transformation<Z> transformation2, Class<R> cls, boolean z, GlideAnimationFactory<R> glideAnimationFactory, int i4, int i5, DiskCacheStrategy diskCacheStrategy2) {
        A a2 = a;
        Transformation<Z> transformation3 = transformation2;
        this.loadProvider = loadProvider2;
        this.model = a2;
        this.signature = key;
        this.fallbackDrawable = drawable3;
        this.fallbackResourceId = i3;
        this.context = context2.getApplicationContext();
        this.priority = priority2;
        this.target = target2;
        this.sizeMultiplier = f;
        this.placeholderDrawable = drawable;
        this.placeholderResourceId = i;
        this.errorDrawable = drawable2;
        this.errorResourceId = i2;
        this.requestListener = requestListener2;
        this.requestCoordinator = requestCoordinator2;
        this.engine = engine2;
        this.transformation = transformation3;
        this.transcodeClass = cls;
        this.isMemoryCacheable = z;
        this.animationFactory = glideAnimationFactory;
        this.overrideWidth = i4;
        this.overrideHeight = i5;
        this.diskCacheStrategy = diskCacheStrategy2;
        this.status = Status.PENDING;
        if (a2 != null) {
            check("ModelLoader", loadProvider2.getModelLoader(), "try .using(ModelLoader)");
            check("Transcoder", loadProvider2.getTranscoder(), "try .as*(Class).transcode(ResourceTranscoder)");
            check("Transformation", transformation3, "try .transform(UnitTransformation.get())");
            if (diskCacheStrategy2.cacheSource()) {
                check("SourceEncoder", loadProvider2.getSourceEncoder(), "try .sourceEncoder(Encoder) or .diskCacheStrategy(NONE/RESULT)");
            } else {
                check("SourceDecoder", loadProvider2.getSourceDecoder(), "try .decoder/.imageDecoder/.videoDecoder(ResourceDecoder) or .diskCacheStrategy(ALL/SOURCE)");
            }
            if (diskCacheStrategy2.cacheSource() || diskCacheStrategy2.cacheResult()) {
                check("CacheDecoder", loadProvider2.getCacheDecoder(), "try .cacheDecoder(ResouceDecoder) or .diskCacheStrategy(NONE)");
            }
            if (diskCacheStrategy2.cacheResult()) {
                check("Encoder", loadProvider2.getEncoder(), "try .encode(ResourceEncoder) or .diskCacheStrategy(NONE/SOURCE)");
            }
        }
    }

    private static void check(String str, Object obj, String str2) {
        if (obj == null) {
            StringBuilder sb = new StringBuilder(str);
            sb.append(" must not be null");
            if (str2 != null) {
                sb.append(", ");
                sb.append(str2);
            }
            throw new NullPointerException(sb.toString());
        }
    }

    public void begin() {
        this.startTime = LogTime.getLogTime();
        if (this.model == null) {
            onException((Exception) null);
            return;
        }
        this.status = Status.WAITING_FOR_SIZE;
        if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) {
            onSizeReady(this.overrideWidth, this.overrideHeight);
        } else {
            this.target.getSize(this);
        }
        if (!isComplete() && !isFailed() && canNotifyStatusChanged()) {
            this.target.onLoadStarted(getPlaceholderDrawable());
        }
        if (Log.isLoggable(TAG, 2)) {
            logV("finished run method in " + LogTime.getElapsedMillis(this.startTime));
        }
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
        this.status = Status.CANCELLED;
        Engine.LoadStatus loadStatus2 = this.loadStatus;
        if (loadStatus2 != null) {
            loadStatus2.cancel();
            this.loadStatus = null;
        }
    }

    public void clear() {
        Util.assertMainThread();
        if (this.status != Status.CLEARED) {
            cancel();
            Resource<?> resource2 = this.resource;
            if (resource2 != null) {
                releaseResource(resource2);
            }
            if (canNotifyStatusChanged()) {
                this.target.onLoadCleared(getPlaceholderDrawable());
            }
            this.status = Status.CLEARED;
        }
    }

    public boolean isPaused() {
        return this.status == Status.PAUSED;
    }

    public void pause() {
        clear();
        this.status = Status.PAUSED;
    }

    private void releaseResource(Resource resource2) {
        this.engine.release(resource2);
        this.resource = null;
    }

    public boolean isRunning() {
        return this.status == Status.RUNNING || this.status == Status.WAITING_FOR_SIZE;
    }

    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }

    public boolean isResourceSet() {
        return isComplete();
    }

    public boolean isCancelled() {
        return this.status == Status.CANCELLED || this.status == Status.CLEARED;
    }

    public boolean isFailed() {
        return this.status == Status.FAILED;
    }

    private Drawable getFallbackDrawable() {
        if (this.fallbackDrawable == null && this.fallbackResourceId > 0) {
            this.fallbackDrawable = this.context.getResources().getDrawable(this.fallbackResourceId);
        }
        return this.fallbackDrawable;
    }

    private void setErrorPlaceholder(Exception exc) {
        if (canNotifyStatusChanged()) {
            Drawable fallbackDrawable2 = this.model == null ? getFallbackDrawable() : null;
            if (fallbackDrawable2 == null) {
                fallbackDrawable2 = getErrorDrawable();
            }
            if (fallbackDrawable2 == null) {
                fallbackDrawable2 = getPlaceholderDrawable();
            }
            this.target.onLoadFailed(exc, fallbackDrawable2);
        }
    }

    private Drawable getErrorDrawable() {
        if (this.errorDrawable == null && this.errorResourceId > 0) {
            this.errorDrawable = this.context.getResources().getDrawable(this.errorResourceId);
        }
        return this.errorDrawable;
    }

    private Drawable getPlaceholderDrawable() {
        if (this.placeholderDrawable == null && this.placeholderResourceId > 0) {
            this.placeholderDrawable = this.context.getResources().getDrawable(this.placeholderResourceId);
        }
        return this.placeholderDrawable;
    }

    public void onSizeReady(int i, int i2) {
        if (Log.isLoggable(TAG, 2)) {
            logV("Got onSizeReady in " + LogTime.getElapsedMillis(this.startTime));
        }
        if (this.status == Status.WAITING_FOR_SIZE) {
            this.status = Status.RUNNING;
            int round = Math.round(this.sizeMultiplier * ((float) i));
            int round2 = Math.round(this.sizeMultiplier * ((float) i2));
            DataFetcher<T> resourceFetcher = this.loadProvider.getModelLoader().getResourceFetcher(this.model, round, round2);
            if (resourceFetcher == null) {
                onException(new Exception("Failed to load model: '" + this.model + "'"));
                return;
            }
            ResourceTranscoder<Z, R> transcoder = this.loadProvider.getTranscoder();
            if (Log.isLoggable(TAG, 2)) {
                logV("finished setup for calling load in " + LogTime.getElapsedMillis(this.startTime));
            }
            boolean z = true;
            this.loadedFromMemoryCache = true;
            this.loadStatus = this.engine.load(this.signature, round, round2, resourceFetcher, this.loadProvider, this.transformation, transcoder, this.priority, this.isMemoryCacheable, this.diskCacheStrategy, this);
            if (this.resource == null) {
                z = false;
            }
            this.loadedFromMemoryCache = z;
            if (Log.isLoggable(TAG, 2)) {
                logV("finished onSizeReady in " + LogTime.getElapsedMillis(this.startTime));
            }
        }
    }

    private boolean canSetResource() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || requestCoordinator2.canSetImage(this);
    }

    private boolean canNotifyStatusChanged() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || requestCoordinator2.canNotifyStatusChanged(this);
    }

    private boolean isFirstReadyResource() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || !requestCoordinator2.isAnyResourceSet();
    }

    private void notifyLoadSuccess() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        if (requestCoordinator2 != null) {
            requestCoordinator2.onRequestSuccess(this);
        }
    }

    public void onResourceReady(Resource<?> resource2) {
        if (resource2 == null) {
            onException(new Exception("Expected to receive a Resource<R> with an object of " + this.transcodeClass + " inside, but instead got null."));
            return;
        }
        Object obj = resource2.get();
        if (obj == null || !this.transcodeClass.isAssignableFrom(obj.getClass())) {
            releaseResource(resource2);
            StringBuilder sb = new StringBuilder();
            sb.append("Expected to receive an object of ");
            sb.append(this.transcodeClass);
            sb.append(" but instead got ");
            String str = "";
            sb.append(obj != null ? obj.getClass() : str);
            sb.append("{");
            sb.append(obj);
            sb.append("}");
            sb.append(" inside Resource{");
            sb.append(resource2);
            sb.append("}.");
            if (obj == null) {
                str = " To indicate failure return a null Resource object, rather than a Resource object containing null data.";
            }
            sb.append(str);
            onException(new Exception(sb.toString()));
        } else if (!canSetResource()) {
            releaseResource(resource2);
            this.status = Status.COMPLETE;
        } else {
            onResourceReady(resource2, obj);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x001a, code lost:
        if (r0.onResourceReady(r9, r7.model, r7.target, r7.loadedFromMemoryCache, r6) == false) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void onResourceReady(com.bumptech.glide.load.engine.Resource<?> r8, R r9) {
        /*
            r7 = this;
            boolean r6 = r7.isFirstReadyResource()
            com.bumptech.glide.request.GenericRequest$Status r0 = com.bumptech.glide.request.GenericRequest.Status.COMPLETE
            r7.status = r0
            r7.resource = r8
            com.bumptech.glide.request.RequestListener<? super A, R> r0 = r7.requestListener
            if (r0 == 0) goto L_0x001c
            A r2 = r7.model
            com.bumptech.glide.request.target.Target<R> r3 = r7.target
            boolean r4 = r7.loadedFromMemoryCache
            r1 = r9
            r5 = r6
            boolean r0 = r0.onResourceReady(r1, r2, r3, r4, r5)
            if (r0 != 0) goto L_0x0029
        L_0x001c:
            com.bumptech.glide.request.animation.GlideAnimationFactory<R> r0 = r7.animationFactory
            boolean r1 = r7.loadedFromMemoryCache
            com.bumptech.glide.request.animation.GlideAnimation r0 = r0.build(r1, r6)
            com.bumptech.glide.request.target.Target<R> r1 = r7.target
            r1.onResourceReady(r9, r0)
        L_0x0029:
            r7.notifyLoadSuccess()
            r9 = 2
            java.lang.String r0 = "GenericRequest"
            boolean r9 = android.util.Log.isLoggable(r0, r9)
            if (r9 == 0) goto L_0x006a
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "Resource ready in "
            r9.append(r0)
            long r0 = r7.startTime
            double r0 = com.bumptech.glide.util.LogTime.getElapsedMillis(r0)
            r9.append(r0)
            java.lang.String r0 = " size: "
            r9.append(r0)
            int r8 = r8.getSize()
            double r0 = (double) r8
            r2 = 4517110426252607488(0x3eb0000000000000, double:9.5367431640625E-7)
            double r0 = r0 * r2
            r9.append(r0)
            java.lang.String r8 = " fromCache: "
            r9.append(r8)
            boolean r8 = r7.loadedFromMemoryCache
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            r7.logV(r8)
        L_0x006a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.GenericRequest.onResourceReady(com.bumptech.glide.load.engine.Resource, java.lang.Object):void");
    }

    public void onException(Exception exc) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "load failed", exc);
        }
        this.status = Status.FAILED;
        RequestListener<? super A, R> requestListener2 = this.requestListener;
        if (requestListener2 == null || !requestListener2.onException(exc, this.model, this.target, isFirstReadyResource())) {
            setErrorPlaceholder(exc);
        }
    }

    private void logV(String str) {
        Log.v(TAG, str + " this: " + this.tag);
    }
}
