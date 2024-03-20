package com.thecrackertechnology.dragonterminal.setup.connections;

import com.thecrackertechnology.dragonterminal.setup.SetupHelper;
import com.thecrackertechnology.dragonterminal.setup.SourceConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0006H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/setup/connections/NetworkConnection;", "Lcom/thecrackertechnology/dragonterminal/setup/SourceConnection;", "sourceUrl", "", "(Ljava/lang/String;)V", "connection", "Ljava/net/HttpURLConnection;", "close", "", "getInputStream", "Ljava/io/InputStream;", "getSize", "", "openHttpConnection", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NetworkConnection.kt */
public final class NetworkConnection implements SourceConnection {
    private HttpURLConnection connection;
    private final String sourceUrl;

    public NetworkConnection(String str) {
        Intrinsics.checkParameterIsNotNull(str, "sourceUrl");
        this.sourceUrl = str;
    }

    public InputStream getInputStream() throws IOException {
        if (this.connection == null) {
            this.connection = openHttpConnection();
            HttpURLConnection httpURLConnection = this.connection;
            if (httpURLConnection == null) {
                Intrinsics.throwNpe();
            }
            httpURLConnection.setConnectTimeout(8000);
            HttpURLConnection httpURLConnection2 = this.connection;
            if (httpURLConnection2 == null) {
                Intrinsics.throwNpe();
            }
            httpURLConnection2.setReadTimeout(8000);
        }
        HttpURLConnection httpURLConnection3 = this.connection;
        if (httpURLConnection3 == null) {
            Intrinsics.throwNpe();
        }
        InputStream inputStream = httpURLConnection3.getInputStream();
        Intrinsics.checkExpressionValueIsNotNull(inputStream, "connection!!.inputStream");
        return inputStream;
    }

    public int getSize() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection == null) {
            return 0;
        }
        if (httpURLConnection == null) {
            Intrinsics.throwNpe();
        }
        return httpURLConnection.getContentLength();
    }

    public void close() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            if (httpURLConnection == null) {
                Intrinsics.throwNpe();
            }
            httpURLConnection.disconnect();
        }
    }

    private final HttpURLConnection openHttpConnection() throws IOException {
        String determineArchName = SetupHelper.INSTANCE.determineArchName();
        URLConnection openConnection = new URL(this.sourceUrl + "/boot/" + determineArchName + ".zip").openConnection();
        if (openConnection != null) {
            return (HttpURLConnection) openConnection;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.net.HttpURLConnection");
    }
}
