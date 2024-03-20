package com.bumptech.glide;

import android.widget.AbsListView;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Util;
import java.util.List;
import java.util.Queue;

public class ListPreloader<T> implements AbsListView.OnScrollListener {
    private boolean isIncreasing;
    private int lastEnd;
    private int lastFirstVisible;
    private int lastStart;
    private final int maxPreload;
    private final PreloadSizeProvider<T> preloadDimensionProvider;
    private final PreloadModelProvider<T> preloadModelProvider;
    private final PreloadTargetQueue preloadTargetQueue;
    private int totalItemCount;

    public interface PreloadModelProvider<U> {
        List<U> getPreloadItems(int i);

        GenericRequestBuilder getPreloadRequestBuilder(U u);
    }

    public interface PreloadSizeProvider<T> {
        int[] getPreloadSize(T t, int i, int i2);
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    @Deprecated
    public ListPreloader(int i) {
        this.isIncreasing = true;
        this.preloadModelProvider = new PreloadModelProvider<T>() {
            public List<T> getPreloadItems(int i) {
                return ListPreloader.this.getItems(i, i + 1);
            }

            public GenericRequestBuilder getPreloadRequestBuilder(T t) {
                return ListPreloader.this.getRequestBuilder(t);
            }
        };
        this.preloadDimensionProvider = new PreloadSizeProvider<T>() {
            public int[] getPreloadSize(T t, int i, int i2) {
                return ListPreloader.this.getDimensions(t);
            }
        };
        this.maxPreload = i;
        this.preloadTargetQueue = new PreloadTargetQueue(i + 1);
    }

    public ListPreloader(PreloadModelProvider<T> preloadModelProvider2, PreloadSizeProvider<T> preloadSizeProvider, int i) {
        this.isIncreasing = true;
        this.preloadModelProvider = preloadModelProvider2;
        this.preloadDimensionProvider = preloadSizeProvider;
        this.maxPreload = i;
        this.preloadTargetQueue = new PreloadTargetQueue(i + 1);
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.totalItemCount = i3;
        int i4 = this.lastFirstVisible;
        if (i > i4) {
            preload(i2 + i, true);
        } else if (i < i4) {
            preload(i, false);
        }
        this.lastFirstVisible = i;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public int[] getDimensions(T t) {
        throw new IllegalStateException("You must either provide a PreloadDimensionProvider or override getDimensions()");
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public List<T> getItems(int i, int i2) {
        throw new IllegalStateException("You must either provide a PreloadModelProvider or override getItems()");
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public GenericRequestBuilder getRequestBuilder(T t) {
        throw new IllegalStateException("You must either provide a PreloadModelProvider, or override getRequestBuilder()");
    }

    private void preload(int i, boolean z) {
        if (this.isIncreasing != z) {
            this.isIncreasing = z;
            cancelAll();
        }
        preload(i, (z ? this.maxPreload : -this.maxPreload) + i);
    }

    private void preload(int i, int i2) {
        int i3;
        int i4;
        if (i < i2) {
            i3 = Math.max(this.lastEnd, i);
            i4 = i2;
        } else {
            i4 = Math.min(this.lastStart, i);
            i3 = i2;
        }
        int min = Math.min(this.totalItemCount, i4);
        int min2 = Math.min(this.totalItemCount, Math.max(0, i3));
        if (i < i2) {
            for (int i5 = min2; i5 < min; i5++) {
                preloadAdapterPosition(this.preloadModelProvider.getPreloadItems(i5), i5, true);
            }
        } else {
            for (int i6 = min - 1; i6 >= min2; i6--) {
                preloadAdapterPosition(this.preloadModelProvider.getPreloadItems(i6), i6, false);
            }
        }
        this.lastStart = min2;
        this.lastEnd = min;
    }

    private void preloadAdapterPosition(List<T> list, int i, boolean z) {
        int size = list.size();
        if (z) {
            for (int i2 = 0; i2 < size; i2++) {
                preloadItem(list.get(i2), i, i2);
            }
            return;
        }
        for (int i3 = size - 1; i3 >= 0; i3--) {
            preloadItem(list.get(i3), i, i3);
        }
    }

    private void preloadItem(T t, int i, int i2) {
        int[] preloadSize = this.preloadDimensionProvider.getPreloadSize(t, i, i2);
        if (preloadSize != null) {
            this.preloadModelProvider.getPreloadRequestBuilder(t).into(this.preloadTargetQueue.next(preloadSize[0], preloadSize[1]));
        }
    }

    private void cancelAll() {
        for (int i = 0; i < this.maxPreload; i++) {
            Glide.clear((Target<?>) this.preloadTargetQueue.next(0, 0));
        }
    }

    private static final class PreloadTargetQueue {
        private final Queue<PreloadTarget> queue;

        public PreloadTargetQueue(int i) {
            this.queue = Util.createQueue(i);
            for (int i2 = 0; i2 < i; i2++) {
                this.queue.offer(new PreloadTarget());
            }
        }

        public PreloadTarget next(int i, int i2) {
            PreloadTarget poll = this.queue.poll();
            this.queue.offer(poll);
            int unused = poll.photoWidth = i;
            int unused2 = poll.photoHeight = i2;
            return poll;
        }
    }

    private static class PreloadTarget extends BaseTarget<Object> {
        /* access modifiers changed from: private */
        public int photoHeight;
        /* access modifiers changed from: private */
        public int photoWidth;

        public void onResourceReady(Object obj, GlideAnimation<? super Object> glideAnimation) {
        }

        private PreloadTarget() {
        }

        public void getSize(SizeReadyCallback sizeReadyCallback) {
            sizeReadyCallback.onSizeReady(this.photoWidth, this.photoHeight);
        }
    }
}
