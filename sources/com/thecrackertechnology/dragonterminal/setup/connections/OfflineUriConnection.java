package com.thecrackertechnology.dragonterminal.setup.connections;

import android.content.Context;
import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/setup/connections/OfflineUriConnection;", "Lcom/thecrackertechnology/dragonterminal/setup/connections/OfflineConnection;", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "(Landroid/content/Context;Landroid/net/Uri;)V", "openInputStream", "Ljava/io/InputStream;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: OfflineUriConnection.kt */
public class OfflineUriConnection extends OfflineConnection {
    private final Context context;
    private final Uri uri;

    public OfflineUriConnection(Context context2, Uri uri2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(uri2, "uri");
        this.context = context2;
        this.uri = uri2;
    }

    /* access modifiers changed from: protected */
    public InputStream openInputStream() throws IOException {
        InputStream openInputStream = this.context.getContentResolver().openInputStream(this.uri);
        Intrinsics.checkExpressionValueIsNotNull(openInputStream, "context.contentResolver.openInputStream(uri)");
        return openInputStream;
    }
}
