package com.bumptech.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.UnitTransformation;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.provider.ChildLoadProvider;
import com.bumptech.glide.provider.LoadProvider;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.GenericRequest;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestCoordinator;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.ThumbnailRequestCoordinator;
import com.bumptech.glide.request.animation.GlideAnimationFactory;
import com.bumptech.glide.request.animation.NoAnimation;
import com.bumptech.glide.request.animation.ViewAnimationFactory;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.animation.ViewPropertyAnimationFactory;
import com.bumptech.glide.request.target.PreloadTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.EmptySignature;
import com.bumptech.glide.util.Util;
import java.io.File;

public class GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> implements Cloneable {
    private GlideAnimationFactory<TranscodeType> animationFactory;
    protected final Context context;
    private DiskCacheStrategy diskCacheStrategy;
    private int errorId;
    private Drawable errorPlaceholder;
    private Drawable fallbackDrawable;
    private int fallbackResource;
    protected final Glide glide;
    private boolean isCacheable;
    private boolean isModelSet;
    private boolean isThumbnailBuilt;
    private boolean isTransformationSet;
    protected final Lifecycle lifecycle;
    private ChildLoadProvider<ModelType, DataType, ResourceType, TranscodeType> loadProvider;
    private ModelType model;
    protected final Class<ModelType> modelClass;
    private int overrideHeight;
    private int overrideWidth;
    private Drawable placeholderDrawable;
    private int placeholderId;
    private Priority priority;
    private RequestListener<? super ModelType, TranscodeType> requestListener;
    protected final RequestTracker requestTracker;
    private Key signature;
    private Float sizeMultiplier;
    private Float thumbSizeMultiplier;
    private GenericRequestBuilder<?, ?, ?, TranscodeType> thumbnailRequestBuilder;
    protected final Class<TranscodeType> transcodeClass;
    private Transformation<ResourceType> transformation;

    /* access modifiers changed from: package-private */
    public void applyCenterCrop() {
    }

    /* access modifiers changed from: package-private */
    public void applyFitCenter() {
    }

    GenericRequestBuilder(LoadProvider<ModelType, DataType, ResourceType, TranscodeType> loadProvider2, Class<TranscodeType> cls, GenericRequestBuilder<ModelType, ?, ?, ?> genericRequestBuilder) {
        this(genericRequestBuilder.context, genericRequestBuilder.modelClass, loadProvider2, cls, genericRequestBuilder.glide, genericRequestBuilder.requestTracker, genericRequestBuilder.lifecycle);
        this.model = genericRequestBuilder.model;
        this.isModelSet = genericRequestBuilder.isModelSet;
        this.signature = genericRequestBuilder.signature;
        this.diskCacheStrategy = genericRequestBuilder.diskCacheStrategy;
        this.isCacheable = genericRequestBuilder.isCacheable;
    }

    GenericRequestBuilder(Context context2, Class<ModelType> cls, LoadProvider<ModelType, DataType, ResourceType, TranscodeType> loadProvider2, Class<TranscodeType> cls2, Glide glide2, RequestTracker requestTracker2, Lifecycle lifecycle2) {
        this.signature = EmptySignature.obtain();
        this.sizeMultiplier = Float.valueOf(1.0f);
        ChildLoadProvider<ModelType, DataType, ResourceType, TranscodeType> childLoadProvider = null;
        this.priority = null;
        this.isCacheable = true;
        this.animationFactory = NoAnimation.getFactory();
        this.overrideHeight = -1;
        this.overrideWidth = -1;
        this.diskCacheStrategy = DiskCacheStrategy.RESULT;
        this.transformation = UnitTransformation.get();
        this.context = context2;
        this.modelClass = cls;
        this.transcodeClass = cls2;
        this.glide = glide2;
        this.requestTracker = requestTracker2;
        this.lifecycle = lifecycle2;
        this.loadProvider = loadProvider2 != null ? new ChildLoadProvider<>(loadProvider2) : childLoadProvider;
        if (context2 == null) {
            throw new NullPointerException("Context can't be null");
        } else if (cls != null && loadProvider2 == null) {
            throw new NullPointerException("LoadProvider must not be null");
        }
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> thumbnail(GenericRequestBuilder<?, ?, ?, TranscodeType> genericRequestBuilder) {
        if (!equals(genericRequestBuilder)) {
            this.thumbnailRequestBuilder = genericRequestBuilder;
            return this;
        }
        throw new IllegalArgumentException("You cannot set a request as a thumbnail for itself. Consider using clone() on the request you are passing to thumbnail()");
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> thumbnail(float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.thumbSizeMultiplier = Float.valueOf(f);
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> sizeMultiplier(float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.sizeMultiplier = Float.valueOf(f);
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> decoder(ResourceDecoder<DataType, ResourceType> resourceDecoder) {
        ChildLoadProvider<ModelType, DataType, ResourceType, TranscodeType> childLoadProvider = this.loadProvider;
        if (childLoadProvider != null) {
            childLoadProvider.setSourceDecoder(resourceDecoder);
        }
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> cacheDecoder(ResourceDecoder<File, ResourceType> resourceDecoder) {
        ChildLoadProvider<ModelType, DataType, ResourceType, TranscodeType> childLoadProvider = this.loadProvider;
        if (childLoadProvider != null) {
            childLoadProvider.setCacheDecoder(resourceDecoder);
        }
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> sourceEncoder(Encoder<DataType> encoder) {
        ChildLoadProvider<ModelType, DataType, ResourceType, TranscodeType> childLoadProvider = this.loadProvider;
        if (childLoadProvider != null) {
            childLoadProvider.setSourceEncoder(encoder);
        }
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> diskCacheStrategy(DiskCacheStrategy diskCacheStrategy2) {
        this.diskCacheStrategy = diskCacheStrategy2;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> encoder(ResourceEncoder<ResourceType> resourceEncoder) {
        ChildLoadProvider<ModelType, DataType, ResourceType, TranscodeType> childLoadProvider = this.loadProvider;
        if (childLoadProvider != null) {
            childLoadProvider.setEncoder(resourceEncoder);
        }
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> priority(Priority priority2) {
        this.priority = priority2;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> transform(Transformation<ResourceType>... transformationArr) {
        this.isTransformationSet = true;
        if (transformationArr.length == 1) {
            this.transformation = transformationArr[0];
        } else {
            this.transformation = new MultiTransformation((Transformation<T>[]) transformationArr);
        }
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> dontTransform() {
        return transform(UnitTransformation.get());
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> transcoder(ResourceTranscoder<ResourceType, TranscodeType> resourceTranscoder) {
        ChildLoadProvider<ModelType, DataType, ResourceType, TranscodeType> childLoadProvider = this.loadProvider;
        if (childLoadProvider != null) {
            childLoadProvider.setTranscoder(resourceTranscoder);
        }
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> dontAnimate() {
        return animate(NoAnimation.getFactory());
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> animate(int i) {
        return animate(new ViewAnimationFactory(this.context, i));
    }

    @Deprecated
    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> animate(Animation animation) {
        return animate(new ViewAnimationFactory(animation));
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> animate(ViewPropertyAnimation.Animator animator) {
        return animate(new ViewPropertyAnimationFactory(animator));
    }

    /* access modifiers changed from: package-private */
    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> animate(GlideAnimationFactory<TranscodeType> glideAnimationFactory) {
        if (glideAnimationFactory != null) {
            this.animationFactory = glideAnimationFactory;
            return this;
        }
        throw new NullPointerException("Animation factory must not be null!");
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> placeholder(int i) {
        this.placeholderId = i;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> placeholder(Drawable drawable) {
        this.placeholderDrawable = drawable;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> fallback(Drawable drawable) {
        this.fallbackDrawable = drawable;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> fallback(int i) {
        this.fallbackResource = i;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> error(int i) {
        this.errorId = i;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> error(Drawable drawable) {
        this.errorPlaceholder = drawable;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> listener(RequestListener<? super ModelType, TranscodeType> requestListener2) {
        this.requestListener = requestListener2;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> skipMemoryCache(boolean z) {
        this.isCacheable = !z;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> override(int i, int i2) {
        if (Util.isValidDimensions(i, i2)) {
            this.overrideWidth = i;
            this.overrideHeight = i2;
            return this;
        }
        throw new IllegalArgumentException("Width and height must be Target#SIZE_ORIGINAL or > 0");
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> signature(Key key) {
        if (key != null) {
            this.signature = key;
            return this;
        }
        throw new NullPointerException("Signature must not be null");
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> load(ModelType modeltype) {
        this.model = modeltype;
        this.isModelSet = true;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> clone() {
        try {
            GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> genericRequestBuilder = (GenericRequestBuilder) super.clone();
            genericRequestBuilder.loadProvider = this.loadProvider != null ? this.loadProvider.clone() : null;
            return genericRequestBuilder;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public <Y extends Target<TranscodeType>> Y into(Y y) {
        Util.assertMainThread();
        if (y == null) {
            throw new IllegalArgumentException("You must pass in a non null Target");
        } else if (this.isModelSet) {
            Request request = y.getRequest();
            if (request != null) {
                request.clear();
                this.requestTracker.removeRequest(request);
                request.recycle();
            }
            Request buildRequest = buildRequest(y);
            y.setRequest(buildRequest);
            this.lifecycle.addListener(y);
            this.requestTracker.runRequest(buildRequest);
            return y;
        } else {
            throw new IllegalArgumentException("You must first set a model (try #load())");
        }
    }

    public Target<TranscodeType> into(ImageView imageView) {
        Util.assertMainThread();
        if (imageView != null) {
            if (!this.isTransformationSet && imageView.getScaleType() != null) {
                int i = AnonymousClass2.$SwitchMap$android$widget$ImageView$ScaleType[imageView.getScaleType().ordinal()];
                if (i == 1) {
                    applyCenterCrop();
                } else if (i == 2 || i == 3 || i == 4) {
                    applyFitCenter();
                }
            }
            return into(this.glide.buildImageViewTarget(imageView, this.transcodeClass));
        }
        throw new IllegalArgumentException("You must pass in a non null View");
    }

    /* renamed from: com.bumptech.glide.GenericRequestBuilder$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                android.widget.ImageView$ScaleType[] r0 = android.widget.ImageView.ScaleType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$android$widget$ImageView$ScaleType = r0
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_CROP     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x001f }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_CENTER     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x002a }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_START     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0035 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_END     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.GenericRequestBuilder.AnonymousClass2.<clinit>():void");
        }
    }

    public FutureTarget<TranscodeType> into(int i, int i2) {
        final RequestFutureTarget requestFutureTarget = new RequestFutureTarget(this.glide.getMainHandler(), i, i2);
        this.glide.getMainHandler().post(new Runnable() {
            public void run() {
                if (!requestFutureTarget.isCancelled()) {
                    GenericRequestBuilder.this.into(requestFutureTarget);
                }
            }
        });
        return requestFutureTarget;
    }

    public Target<TranscodeType> preload(int i, int i2) {
        return into(PreloadTarget.obtain(i, i2));
    }

    public Target<TranscodeType> preload() {
        return preload(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    private Priority getThumbnailPriority() {
        if (this.priority == Priority.LOW) {
            return Priority.NORMAL;
        }
        if (this.priority == Priority.NORMAL) {
            return Priority.HIGH;
        }
        return Priority.IMMEDIATE;
    }

    private Request buildRequest(Target<TranscodeType> target) {
        if (this.priority == null) {
            this.priority = Priority.NORMAL;
        }
        return buildRequestRecursive(target, (ThumbnailRequestCoordinator) null);
    }

    private Request buildRequestRecursive(Target<TranscodeType> target, ThumbnailRequestCoordinator thumbnailRequestCoordinator) {
        GenericRequestBuilder<?, ?, ?, TranscodeType> genericRequestBuilder = this.thumbnailRequestBuilder;
        if (genericRequestBuilder != null) {
            if (!this.isThumbnailBuilt) {
                if (genericRequestBuilder.animationFactory.equals(NoAnimation.getFactory())) {
                    this.thumbnailRequestBuilder.animationFactory = this.animationFactory;
                }
                GenericRequestBuilder<?, ?, ?, TranscodeType> genericRequestBuilder2 = this.thumbnailRequestBuilder;
                if (genericRequestBuilder2.priority == null) {
                    genericRequestBuilder2.priority = getThumbnailPriority();
                }
                if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) {
                    GenericRequestBuilder<?, ?, ?, TranscodeType> genericRequestBuilder3 = this.thumbnailRequestBuilder;
                    if (!Util.isValidDimensions(genericRequestBuilder3.overrideWidth, genericRequestBuilder3.overrideHeight)) {
                        this.thumbnailRequestBuilder.override(this.overrideWidth, this.overrideHeight);
                    }
                }
                ThumbnailRequestCoordinator thumbnailRequestCoordinator2 = new ThumbnailRequestCoordinator(thumbnailRequestCoordinator);
                Request obtainRequest = obtainRequest(target, this.sizeMultiplier.floatValue(), this.priority, thumbnailRequestCoordinator2);
                this.isThumbnailBuilt = true;
                Request buildRequestRecursive = this.thumbnailRequestBuilder.buildRequestRecursive(target, thumbnailRequestCoordinator2);
                this.isThumbnailBuilt = false;
                thumbnailRequestCoordinator2.setRequests(obtainRequest, buildRequestRecursive);
                return thumbnailRequestCoordinator2;
            }
            throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
        } else if (this.thumbSizeMultiplier == null) {
            return obtainRequest(target, this.sizeMultiplier.floatValue(), this.priority, thumbnailRequestCoordinator);
        } else {
            ThumbnailRequestCoordinator thumbnailRequestCoordinator3 = new ThumbnailRequestCoordinator(thumbnailRequestCoordinator);
            thumbnailRequestCoordinator3.setRequests(obtainRequest(target, this.sizeMultiplier.floatValue(), this.priority, thumbnailRequestCoordinator3), obtainRequest(target, this.thumbSizeMultiplier.floatValue(), getThumbnailPriority(), thumbnailRequestCoordinator3));
            return thumbnailRequestCoordinator3;
        }
    }

    private Request obtainRequest(Target<TranscodeType> target, float f, Priority priority2, RequestCoordinator requestCoordinator) {
        Priority priority3 = priority2;
        ChildLoadProvider<ModelType, DataType, ResourceType, TranscodeType> childLoadProvider = this.loadProvider;
        return GenericRequest.obtain(childLoadProvider, this.model, this.signature, this.context, priority3, target, f, this.placeholderDrawable, this.placeholderId, this.errorPlaceholder, this.errorId, this.fallbackDrawable, this.fallbackResource, this.requestListener, requestCoordinator, this.glide.getEngine(), this.transformation, this.transcodeClass, this.isCacheable, this.animationFactory, this.overrideWidth, this.overrideHeight, this.diskCacheStrategy);
    }
}
