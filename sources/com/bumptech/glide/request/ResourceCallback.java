package com.bumptech.glide.request;

import com.bumptech.glide.load.engine.Resource;

public interface ResourceCallback {
    void onException(Exception exc);

    void onResourceReady(Resource<?> resource);
}
