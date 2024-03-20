package de.mrapp.android.util.multithreading;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.view.View;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.logging.LogLevel;
import de.mrapp.android.util.logging.Logger;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractDataBinder<DataType, KeyType, ViewType extends View, ParamType> extends Handler {
    public static final int CACHE_SIZE = 10;
    private final LruCache<KeyType, DataType> cache;
    private final Object cancelLock;
    private boolean canceled;
    private final Context context;
    private final Set<Listener<DataType, KeyType, ViewType, ParamType>> listeners;
    private final Logger logger;
    private final ExecutorService threadPool;
    private boolean useCache;
    private final Map<ViewType, KeyType> views;

    public interface Listener<DataType, KeyType, ViewType extends View, ParamType> {
        void onCanceled(AbstractDataBinder<DataType, KeyType, ViewType, ParamType> abstractDataBinder);

        void onFinished(AbstractDataBinder<DataType, KeyType, ViewType, ParamType> abstractDataBinder, KeyType keytype, DataType datatype, ViewType viewtype, ParamType... paramtypeArr);

        boolean onLoadData(AbstractDataBinder<DataType, KeyType, ViewType, ParamType> abstractDataBinder, KeyType keytype, ParamType... paramtypeArr);
    }

    /* access modifiers changed from: protected */
    public abstract DataType doInBackground(KeyType keytype, ParamType... paramtypeArr);

    /* access modifiers changed from: protected */
    public abstract void onPostExecute(ViewType viewtype, DataType datatype, ParamType... paramtypeArr);

    /* access modifiers changed from: protected */
    public void onPreExecute(ViewType viewtype, ParamType... paramtypeArr) {
    }

    private static class Task<DataType, KeyType, ViewType extends View, ParamType> {
        /* access modifiers changed from: private */
        public final KeyType key;
        /* access modifiers changed from: private */
        public final ParamType[] params;
        /* access modifiers changed from: private */
        public DataType result = null;
        /* access modifiers changed from: private */
        public final ViewType view;

        Task(ViewType viewtype, KeyType keytype, ParamType[] paramtypeArr) {
            this.view = viewtype;
            this.key = keytype;
            this.params = paramtypeArr;
        }
    }

    /* access modifiers changed from: private */
    @SafeVarargs
    public final boolean notifyOnLoad(KeyType keytype, ParamType... paramtypeArr) {
        boolean z;
        synchronized (this.listeners) {
            z = true;
            for (Listener<DataType, KeyType, ViewType, ParamType> onLoadData : this.listeners) {
                z &= onLoadData.onLoadData(this, keytype, paramtypeArr);
            }
        }
        return z;
    }

    @SafeVarargs
    private final void notifyOnFinished(KeyType keytype, DataType datatype, ViewType viewtype, ParamType... paramtypeArr) {
        synchronized (this.listeners) {
            for (Listener<DataType, KeyType, ViewType, ParamType> onFinished : this.listeners) {
                onFinished.onFinished(this, keytype, datatype, viewtype, paramtypeArr);
            }
        }
    }

    private void notifyOnCanceled() {
        synchronized (this.listeners) {
            for (Listener<DataType, KeyType, ViewType, ParamType> onCanceled : this.listeners) {
                onCanceled.onCanceled(this);
            }
        }
    }

    private DataType getCachedData(KeyType keytype) {
        DataType datatype;
        synchronized (this.cache) {
            datatype = this.cache.get(keytype);
        }
        return datatype;
    }

    private void cacheData(KeyType keytype, DataType datatype) {
        synchronized (this.cache) {
            if (this.useCache) {
                this.cache.put(keytype, datatype);
            }
        }
    }

    private void loadDataAsynchronously(final Task<DataType, KeyType, ViewType, ParamType> task) {
        this.threadPool.submit(new Runnable() {
            public void run() {
                if (!AbstractDataBinder.this.isCanceled()) {
                    while (!AbstractDataBinder.this.notifyOnLoad(task.key, task.params)) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException unused) {
                        }
                    }
                    Task task = task;
                    Object unused2 = task.result = AbstractDataBinder.this.loadData(task);
                    Message obtain = Message.obtain();
                    obtain.obj = task;
                    AbstractDataBinder.this.sendMessage(obtain);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public DataType loadData(Task<DataType, KeyType, ViewType, ParamType> task) {
        try {
            DataType doInBackground = doInBackground(task.key, task.params);
            if (doInBackground != null) {
                cacheData(task.key, doInBackground);
            }
            Logger logger2 = this.logger;
            Class<?> cls = getClass();
            logger2.logInfo(cls, "Loaded data with key " + task.key);
            return doInBackground;
        } catch (Exception e) {
            Logger logger3 = this.logger;
            Class<?> cls2 = getClass();
            logger3.logError(cls2, "An error occurred while loading data with key " + task.key, e);
            return null;
        }
    }

    private void setCanceled(boolean z) {
        synchronized (this.cancelLock) {
            this.canceled = z;
        }
    }

    public AbstractDataBinder(Context context2) {
        this(context2, Executors.newCachedThreadPool());
    }

    public AbstractDataBinder(Context context2, ExecutorService executorService) {
        this(context2, executorService, new LruCache(10));
    }

    public AbstractDataBinder(Context context2, LruCache<KeyType, DataType> lruCache) {
        this(context2, Executors.newCachedThreadPool(), lruCache);
    }

    public AbstractDataBinder(Context context2, ExecutorService executorService, LruCache<KeyType, DataType> lruCache) {
        Condition.ensureNotNull(context2, "The context may not be null");
        Condition.ensureNotNull(executorService, "The executor service may not be null");
        Condition.ensureNotNull(lruCache, "The cache may not be null");
        this.context = context2;
        this.logger = new Logger(LogLevel.INFO);
        this.listeners = new LinkedHashSet();
        this.cache = lruCache;
        this.views = Collections.synchronizedMap(new WeakHashMap());
        this.threadPool = executorService;
        this.cancelLock = new Object();
        this.canceled = false;
        this.useCache = true;
    }

    public final Context getContext() {
        return this.context;
    }

    public final LogLevel getLogLevel() {
        return this.logger.getLogLevel();
    }

    public final void setLogLevel(LogLevel logLevel) {
        this.logger.setLogLevel(logLevel);
    }

    public final void addListener(Listener<DataType, KeyType, ViewType, ParamType> listener) {
        Condition.ensureNotNull(listener, "The listener may not be null");
        synchronized (this.listeners) {
            this.listeners.add(listener);
        }
    }

    public final void removeListener(Listener<DataType, KeyType, ViewType, ParamType> listener) {
        Condition.ensureNotNull(listener, "The listener may not be null");
        synchronized (this.listeners) {
            this.listeners.remove(listener);
        }
    }

    @SafeVarargs
    public final void load(KeyType keytype, ViewType viewtype, ParamType... paramtypeArr) {
        load(keytype, viewtype, true, paramtypeArr);
    }

    @SafeVarargs
    public final void load(KeyType keytype, ViewType viewtype, boolean z, ParamType... paramtypeArr) {
        Condition.ensureNotNull(keytype, "The key may not be null");
        Condition.ensureNotNull(viewtype, "The view may not be null");
        Condition.ensureNotNull(paramtypeArr, "The array may not be null");
        setCanceled(false);
        this.views.put(viewtype, keytype);
        Object cachedData = getCachedData(keytype);
        if (isCanceled()) {
            return;
        }
        if (cachedData != null) {
            onPostExecute(viewtype, cachedData, paramtypeArr);
            notifyOnFinished(keytype, cachedData, viewtype, paramtypeArr);
            Logger logger2 = this.logger;
            Class<?> cls = getClass();
            logger2.logInfo(cls, "Loaded data with key " + keytype + " from cache");
            return;
        }
        onPreExecute(viewtype, paramtypeArr);
        Task task = new Task(viewtype, keytype, paramtypeArr);
        if (z) {
            loadDataAsynchronously(task);
            return;
        }
        Object loadData = loadData(task);
        onPostExecute(viewtype, loadData, paramtypeArr);
        notifyOnFinished(keytype, loadData, viewtype, paramtypeArr);
    }

    public final void cancel() {
        setCanceled(true);
        notifyOnCanceled();
        this.logger.logInfo(getClass(), "Canceled to load data");
    }

    public final boolean isCanceled() {
        boolean z;
        synchronized (this.cancelLock) {
            z = this.canceled;
        }
        return z;
    }

    public final boolean isCached(KeyType keytype) {
        boolean z;
        Condition.ensureNotNull(keytype, "The key may not be null");
        synchronized (this.cache) {
            z = this.cache.get(keytype) != null;
        }
        return z;
    }

    public final boolean isCacheUsed() {
        boolean z;
        synchronized (this.cache) {
            z = this.useCache;
        }
        return z;
    }

    public final void useCache(boolean z) {
        synchronized (this.cache) {
            this.useCache = z;
            this.logger.logDebug(getClass(), z ? "Enabled" : "Disabled caching");
            if (!z) {
                clearCache();
            }
        }
    }

    public final void clearCache() {
        synchronized (this.cache) {
            this.cache.evictAll();
            this.logger.logDebug(getClass(), "Cleared cache");
        }
    }

    public final void handleMessage(Message message) {
        Task task = (Task) message.obj;
        if (!isCanceled()) {
            KeyType keytype = this.views.get(task.view);
            if (keytype == null || !keytype.equals(task.key)) {
                Logger logger2 = this.logger;
                Class<?> cls = getClass();
                logger2.logVerbose(cls, "Data with key " + task.key + " not displayed. View has been recycled");
                return;
            }
            onPostExecute(task.view, task.result, task.params);
            notifyOnFinished(task.key, task.result, task.view, task.params);
            return;
        }
        Logger logger3 = this.logger;
        Class<?> cls2 = getClass();
        logger3.logVerbose(cls2, "Data with key " + task.key + " not displayed. Loading data has been canceled");
    }
}
