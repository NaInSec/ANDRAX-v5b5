package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.Key;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class DiskCacheWriteLocker {
    private final Map<Key, WriteLock> locks = new HashMap();
    private final WriteLockPool writeLockPool = new WriteLockPool();

    DiskCacheWriteLocker() {
    }

    /* access modifiers changed from: package-private */
    public void acquire(Key key) {
        WriteLock writeLock;
        synchronized (this) {
            writeLock = this.locks.get(key);
            if (writeLock == null) {
                writeLock = this.writeLockPool.obtain();
                this.locks.put(key, writeLock);
            }
            writeLock.interestedThreads++;
        }
        writeLock.lock.lock();
    }

    /* access modifiers changed from: package-private */
    public void release(Key key) {
        WriteLock writeLock;
        synchronized (this) {
            writeLock = this.locks.get(key);
            if (writeLock != null) {
                if (writeLock.interestedThreads > 0) {
                    int i = writeLock.interestedThreads - 1;
                    writeLock.interestedThreads = i;
                    if (i == 0) {
                        WriteLock remove = this.locks.remove(key);
                        if (remove.equals(writeLock)) {
                            this.writeLockPool.offer(remove);
                        } else {
                            throw new IllegalStateException("Removed the wrong lock, expected to remove: " + writeLock + ", but actually removed: " + remove + ", key: " + key);
                        }
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot release a lock that is not held, key: ");
            sb.append(key);
            sb.append(", interestedThreads: ");
            sb.append(writeLock == null ? 0 : writeLock.interestedThreads);
            throw new IllegalArgumentException(sb.toString());
        }
        writeLock.lock.unlock();
    }

    private static class WriteLock {
        int interestedThreads;
        final Lock lock;

        private WriteLock() {
            this.lock = new ReentrantLock();
        }
    }

    private static class WriteLockPool {
        private static final int MAX_POOL_SIZE = 10;
        private final Queue<WriteLock> pool;

        private WriteLockPool() {
            this.pool = new ArrayDeque();
        }

        /* access modifiers changed from: package-private */
        public WriteLock obtain() {
            WriteLock poll;
            synchronized (this.pool) {
                poll = this.pool.poll();
            }
            return poll == null ? new WriteLock() : poll;
        }

        /* access modifiers changed from: package-private */
        public void offer(WriteLock writeLock) {
            synchronized (this.pool) {
                if (this.pool.size() < 10) {
                    this.pool.offer(writeLock);
                }
            }
        }
    }
}
