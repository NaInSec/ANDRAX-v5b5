package com.thecrackertechnology.dragonterminal.setup.connections;

import android.content.Context;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/setup/connections/LocalFileConnection;", "Lcom/thecrackertechnology/dragonterminal/setup/connections/OfflineUriConnection;", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "(Landroid/content/Context;Landroid/net/Uri;)V", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: LocalFileConnection.kt */
public class LocalFileConnection extends OfflineUriConnection {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LocalFileConnection(Context context, Uri uri) {
        super(context, uri);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(uri, "uri");
    }
}
