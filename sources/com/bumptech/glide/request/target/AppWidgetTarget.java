package com.bumptech.glide.request.target;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.bumptech.glide.request.animation.GlideAnimation;

public class AppWidgetTarget extends SimpleTarget<Bitmap> {
    private final ComponentName componentName;
    private final Context context;
    private final RemoteViews remoteViews;
    private final int viewId;
    private final int[] widgetIds;

    public AppWidgetTarget(Context context2, RemoteViews remoteViews2, int i, int i2, int i3, int... iArr) {
        super(i2, i3);
        if (context2 == null) {
            throw new NullPointerException("Context can not be null!");
        } else if (iArr == null) {
            throw new NullPointerException("WidgetIds can not be null!");
        } else if (iArr.length == 0) {
            throw new IllegalArgumentException("WidgetIds must have length > 0");
        } else if (remoteViews2 != null) {
            this.context = context2;
            this.remoteViews = remoteViews2;
            this.viewId = i;
            this.widgetIds = iArr;
            this.componentName = null;
        } else {
            throw new NullPointerException("RemoteViews object can not be null!");
        }
    }

    public AppWidgetTarget(Context context2, RemoteViews remoteViews2, int i, int... iArr) {
        this(context2, remoteViews2, i, Integer.MIN_VALUE, Integer.MIN_VALUE, iArr);
    }

    public AppWidgetTarget(Context context2, RemoteViews remoteViews2, int i, int i2, int i3, ComponentName componentName2) {
        super(i2, i3);
        if (context2 == null) {
            throw new NullPointerException("Context can not be null!");
        } else if (componentName2 == null) {
            throw new NullPointerException("ComponentName can not be null!");
        } else if (remoteViews2 != null) {
            this.context = context2;
            this.remoteViews = remoteViews2;
            this.viewId = i;
            this.componentName = componentName2;
            this.widgetIds = null;
        } else {
            throw new NullPointerException("RemoteViews object can not be null!");
        }
    }

    public AppWidgetTarget(Context context2, RemoteViews remoteViews2, int i, ComponentName componentName2) {
        this(context2, remoteViews2, i, Integer.MIN_VALUE, Integer.MIN_VALUE, componentName2);
    }

    private void update() {
        AppWidgetManager instance = AppWidgetManager.getInstance(this.context);
        ComponentName componentName2 = this.componentName;
        if (componentName2 != null) {
            instance.updateAppWidget(componentName2, this.remoteViews);
        } else {
            instance.updateAppWidget(this.widgetIds, this.remoteViews);
        }
    }

    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
        this.remoteViews.setImageViewBitmap(this.viewId, bitmap);
        update();
    }
}
