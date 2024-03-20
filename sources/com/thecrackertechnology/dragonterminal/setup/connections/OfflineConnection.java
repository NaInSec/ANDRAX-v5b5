package com.thecrackertechnology.dragonterminal.setup.connections;

import com.thecrackertechnology.dragonterminal.setup.SourceConnection;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u0004H$R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/setup/connections/OfflineConnection;", "Lcom/thecrackertechnology/dragonterminal/setup/SourceConnection;", "()V", "inputStream", "Ljava/io/InputStream;", "close", "", "getInputStream", "getSize", "", "openInputStream", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: OfflineConnection.kt */
public abstract class OfflineConnection implements SourceConnection {
    private InputStream inputStream;

    /* access modifiers changed from: protected */
    public abstract InputStream openInputStream() throws IOException;

    public InputStream getInputStream() throws IOException {
        if (this.inputStream == null) {
            this.inputStream = openInputStream();
        }
        InputStream inputStream2 = this.inputStream;
        if (inputStream2 == null) {
            Intrinsics.throwNpe();
        }
        return inputStream2;
    }

    public int getSize() {
        InputStream inputStream2 = this.inputStream;
        if (inputStream2 == null) {
            return 0;
        }
        if (inputStream2 == null) {
            try {
                Intrinsics.throwNpe();
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return inputStream2.available();
    }

    public void close() {
        InputStream inputStream2 = this.inputStream;
        if (inputStream2 != null) {
            if (inputStream2 == null) {
                try {
                    Intrinsics.throwNpe();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            inputStream2.close();
        }
    }
}
