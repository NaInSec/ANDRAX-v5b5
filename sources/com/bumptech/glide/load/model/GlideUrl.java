package com.bumptech.glide.load.model;

import android.net.Uri;
import android.text.TextUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class GlideUrl {
    private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
    private final Headers headers;
    private String safeStringUrl;
    private URL safeUrl;
    private final String stringUrl;
    private final URL url;

    public GlideUrl(URL url2) {
        this(url2, Headers.DEFAULT);
    }

    public GlideUrl(String str) {
        this(str, Headers.DEFAULT);
    }

    public GlideUrl(URL url2, Headers headers2) {
        if (url2 == null) {
            throw new IllegalArgumentException("URL must not be null!");
        } else if (headers2 != null) {
            this.url = url2;
            this.stringUrl = null;
            this.headers = headers2;
        } else {
            throw new IllegalArgumentException("Headers must not be null");
        }
    }

    public GlideUrl(String str, Headers headers2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("String url must not be empty or null: " + str);
        } else if (headers2 != null) {
            this.stringUrl = str;
            this.url = null;
            this.headers = headers2;
        } else {
            throw new IllegalArgumentException("Headers must not be null");
        }
    }

    public URL toURL() throws MalformedURLException {
        return getSafeUrl();
    }

    private URL getSafeUrl() throws MalformedURLException {
        if (this.safeUrl == null) {
            this.safeUrl = new URL(getSafeStringUrl());
        }
        return this.safeUrl;
    }

    public String toStringUrl() {
        return getSafeStringUrl();
    }

    private String getSafeStringUrl() {
        if (TextUtils.isEmpty(this.safeStringUrl)) {
            String str = this.stringUrl;
            if (TextUtils.isEmpty(str)) {
                str = this.url.toString();
            }
            this.safeStringUrl = Uri.encode(str, ALLOWED_URI_CHARS);
        }
        return this.safeStringUrl;
    }

    public Map<String, String> getHeaders() {
        return this.headers.getHeaders();
    }

    public String getCacheKey() {
        String str = this.stringUrl;
        return str != null ? str : this.url.toString();
    }

    public String toString() {
        return getCacheKey() + 10 + this.headers.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GlideUrl)) {
            return false;
        }
        GlideUrl glideUrl = (GlideUrl) obj;
        if (!getCacheKey().equals(glideUrl.getCacheKey()) || !this.headers.equals(glideUrl.headers)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (getCacheKey().hashCode() * 31) + this.headers.hashCode();
    }
}
