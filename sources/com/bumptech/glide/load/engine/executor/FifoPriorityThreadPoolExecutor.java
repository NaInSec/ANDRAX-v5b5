package com.bumptech.glide.load.engine.executor;

import android.os.Process;
import android.util.Log;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FifoPriorityThreadPoolExecutor extends ThreadPoolExecutor {
    private static final String TAG = "PriorityExecutor";
    private final AtomicInteger ordering;
    private final UncaughtThrowableStrategy uncaughtThrowableStrategy;

    public enum UncaughtThrowableStrategy {
        IGNORE,
        LOG {
            /* access modifiers changed from: protected */
            public void handle(Throwable th) {
                if (Log.isLoggable(FifoPriorityThreadPoolExecutor.TAG, 6)) {
                    Log.e(FifoPriorityThreadPoolExecutor.TAG, "Request threw uncaught throwable", th);
                }
            }
        },
        THROW {
            /* access modifiers changed from: protected */
            public void handle(Throwable th) {
                super.handle(th);
                throw new RuntimeException(th);
            }
        };

        /* access modifiers changed from: protected */
        public void handle(Throwable th) {
        }
    }

    public FifoPriorityThreadPoolExecutor(int i) {
        this(i, UncaughtThrowableStrategy.LOG);
    }

    public FifoPriorityThreadPoolExecutor(int i, UncaughtThrowableStrategy uncaughtThrowableStrategy2) {
        this(i, i, 0, TimeUnit.MILLISECONDS, new DefaultThreadFactory(), uncaughtThrowableStrategy2);
    }

    public FifoPriorityThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, ThreadFactory threadFactory, UncaughtThrowableStrategy uncaughtThrowableStrategy2) {
        super(i, i2, j, timeUnit, new PriorityBlockingQueue(), threadFactory);
        this.ordering = new AtomicInteger();
        this.uncaughtThrowableStrategy = uncaughtThrowableStrategy2;
    }

    /* access modifiers changed from: protected */
    public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new LoadTask(runnable, t, this.ordering.getAndIncrement());
    }

    /* access modifiers changed from: protected */
    public void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        if (th == null && (runnable instanceof Future)) {
            Future future = (Future) runnable;
            if (future.isDone() && !future.isCancelled()) {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    this.uncaughtThrowableStrategy.handle(e);
                } catch (ExecutionException e2) {
                    this.uncaughtThrowableStrategy.handle(e2);
                }
            }
        }
    }

    public static class DefaultThreadFactory implements ThreadFactory {
        int threadNum = 0;

        public Thread newThread(Runnable runnable) {
            AnonymousClass1 r0 = new Thread(runnable, "fifo-pool-thread-" + this.threadNum) {
                public void run() {
                    Process.setThreadPriority(10);
                    super.run();
                }
            };
            this.threadNum = this.threadNum + 1;
            return r0;
        }
    }

    static class LoadTask<T> extends FutureTask<T> implements Comparable<LoadTask<?>> {
        private final int order;
        private final int priority;

        public LoadTask(Runnable runnable, T t, int i) {
            super(runnable, t);
            if (runnable instanceof Prioritized) {
                this.priority = ((Prioritized) runnable).getPriority();
                this.order = i;
                return;
            }
            throw new IllegalArgumentException("FifoPriorityThreadPoolExecutor must be given Runnables that implement Prioritized");
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof LoadTask)) {
                return false;
            }
            LoadTask loadTask = (LoadTask) obj;
            if (this.order == loadTask.order && this.priority == loadTask.priority) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.priority * 31) + this.order;
        }

        public int compareTo(LoadTask<?> loadTask) {
            int i = this.priority - loadTask.priority;
            return i == 0 ? this.order - loadTask.order : i;
        }
    }
}
