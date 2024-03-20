package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.EngineRunnable;
import com.bumptech.glide.request.ResourceCallback;
import com.bumptech.glide.util.Util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

class EngineJob implements EngineRunnable.EngineRunnableManager {
    private static final EngineResourceFactory DEFAULT_FACTORY = new EngineResourceFactory();
    private static final Handler MAIN_THREAD_HANDLER = new Handler(Looper.getMainLooper(), new MainThreadCallback());
    private static final int MSG_COMPLETE = 1;
    private static final int MSG_EXCEPTION = 2;
    private final List<ResourceCallback> cbs;
    private final ExecutorService diskCacheService;
    private EngineResource<?> engineResource;
    private final EngineResourceFactory engineResourceFactory;
    private EngineRunnable engineRunnable;
    private Exception exception;
    private volatile Future<?> future;
    private boolean hasException;
    private boolean hasResource;
    private Set<ResourceCallback> ignoredCallbacks;
    private final boolean isCacheable;
    private boolean isCancelled;
    private final Key key;
    private final EngineJobListener listener;
    private Resource<?> resource;
    private final ExecutorService sourceService;

    public EngineJob(Key key2, ExecutorService executorService, ExecutorService executorService2, boolean z, EngineJobListener engineJobListener) {
        this(key2, executorService, executorService2, z, engineJobListener, DEFAULT_FACTORY);
    }

    public EngineJob(Key key2, ExecutorService executorService, ExecutorService executorService2, boolean z, EngineJobListener engineJobListener, EngineResourceFactory engineResourceFactory2) {
        this.cbs = new ArrayList();
        this.key = key2;
        this.diskCacheService = executorService;
        this.sourceService = executorService2;
        this.isCacheable = z;
        this.listener = engineJobListener;
        this.engineResourceFactory = engineResourceFactory2;
    }

    public void start(EngineRunnable engineRunnable2) {
        this.engineRunnable = engineRunnable2;
        this.future = this.diskCacheService.submit(engineRunnable2);
    }

    public void submitForSource(EngineRunnable engineRunnable2) {
        this.future = this.sourceService.submit(engineRunnable2);
    }

    public void addCallback(ResourceCallback resourceCallback) {
        Util.assertMainThread();
        if (this.hasResource) {
            resourceCallback.onResourceReady(this.engineResource);
        } else if (this.hasException) {
            resourceCallback.onException(this.exception);
        } else {
            this.cbs.add(resourceCallback);
        }
    }

    public void removeCallback(ResourceCallback resourceCallback) {
        Util.assertMainThread();
        if (this.hasResource || this.hasException) {
            addIgnoredCallback(resourceCallback);
            return;
        }
        this.cbs.remove(resourceCallback);
        if (this.cbs.isEmpty()) {
            cancel();
        }
    }

    private void addIgnoredCallback(ResourceCallback resourceCallback) {
        if (this.ignoredCallbacks == null) {
            this.ignoredCallbacks = new HashSet();
        }
        this.ignoredCallbacks.add(resourceCallback);
    }

    private boolean isInIgnoredCallbacks(ResourceCallback resourceCallback) {
        Set<ResourceCallback> set = this.ignoredCallbacks;
        return set != null && set.contains(resourceCallback);
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
        if (!this.hasException && !this.hasResource && !this.isCancelled) {
            this.engineRunnable.cancel();
            Future<?> future2 = this.future;
            if (future2 != null) {
                future2.cancel(true);
            }
            this.isCancelled = true;
            this.listener.onEngineJobCancelled(this, this.key);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void onResourceReady(Resource<?> resource2) {
        this.resource = resource2;
        MAIN_THREAD_HANDLER.obtainMessage(1, this).sendToTarget();
    }

    /* access modifiers changed from: private */
    public void handleResultOnMainThread() {
        if (this.isCancelled) {
            this.resource.recycle();
        } else if (!this.cbs.isEmpty()) {
            this.engineResource = this.engineResourceFactory.build(this.resource, this.isCacheable);
            this.hasResource = true;
            this.engineResource.acquire();
            this.listener.onEngineJobComplete(this.key, this.engineResource);
            for (ResourceCallback next : this.cbs) {
                if (!isInIgnoredCallbacks(next)) {
                    this.engineResource.acquire();
                    next.onResourceReady(this.engineResource);
                }
            }
            this.engineResource.release();
        } else {
            throw new IllegalStateException("Received a resource without any callbacks to notify");
        }
    }

    public void onException(Exception exc) {
        this.exception = exc;
        MAIN_THREAD_HANDLER.obtainMessage(2, this).sendToTarget();
    }

    /* access modifiers changed from: private */
    public void handleExceptionOnMainThread() {
        if (!this.isCancelled) {
            if (!this.cbs.isEmpty()) {
                this.hasException = true;
                this.listener.onEngineJobComplete(this.key, (EngineResource<?>) null);
                for (ResourceCallback next : this.cbs) {
                    if (!isInIgnoredCallbacks(next)) {
                        next.onException(this.exception);
                    }
                }
                return;
            }
            throw new IllegalStateException("Received an exception without any callbacks to notify");
        }
    }

    static class EngineResourceFactory {
        EngineResourceFactory() {
        }

        public <R> EngineResource<R> build(Resource<R> resource, boolean z) {
            return new EngineResource<>(resource, z);
        }
    }

    private static class MainThreadCallback implements Handler.Callback {
        private MainThreadCallback() {
        }

        public boolean handleMessage(Message message) {
            if (1 != message.what && 2 != message.what) {
                return false;
            }
            EngineJob engineJob = (EngineJob) message.obj;
            if (1 == message.what) {
                engineJob.handleResultOnMainThread();
            } else {
                engineJob.handleExceptionOnMainThread();
            }
            return true;
        }
    }
}
