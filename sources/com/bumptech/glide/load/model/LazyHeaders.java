package com.bumptech.glide.load.model;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LazyHeaders implements Headers {
    private volatile Map<String, String> combinedHeaders;
    private final Map<String, List<LazyHeaderFactory>> headers;

    LazyHeaders(Map<String, List<LazyHeaderFactory>> map) {
        this.headers = Collections.unmodifiableMap(map);
    }

    public Map<String, String> getHeaders() {
        if (this.combinedHeaders == null) {
            synchronized (this) {
                if (this.combinedHeaders == null) {
                    this.combinedHeaders = Collections.unmodifiableMap(generateHeaders());
                }
            }
        }
        return this.combinedHeaders;
    }

    private Map<String, String> generateHeaders() {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : this.headers.entrySet()) {
            StringBuilder sb = new StringBuilder();
            List list = (List) next.getValue();
            for (int i = 0; i < list.size(); i++) {
                sb.append(((LazyHeaderFactory) list.get(i)).buildHeader());
                if (i != list.size() - 1) {
                    sb.append(',');
                }
            }
            hashMap.put(next.getKey(), sb.toString());
        }
        return hashMap;
    }

    public String toString() {
        return "LazyHeaders{headers=" + this.headers + '}';
    }

    public boolean equals(Object obj) {
        if (obj instanceof LazyHeaders) {
            return this.headers.equals(((LazyHeaders) obj).headers);
        }
        return false;
    }

    public int hashCode() {
        return this.headers.hashCode();
    }

    public static final class Builder {
        private static final String DEFAULT_ENCODING = "identity";
        private static final Map<String, List<LazyHeaderFactory>> DEFAULT_HEADERS;
        private static final String DEFAULT_USER_AGENT = System.getProperty("http.agent");
        private static final String ENCODING_HEADER = "Accept-Encoding";
        private static final String USER_AGENT_HEADER = "User-Agent";
        private boolean copyOnModify = true;
        private Map<String, List<LazyHeaderFactory>> headers = DEFAULT_HEADERS;
        private boolean isEncodingDefault = true;
        private boolean isUserAgentDefault = true;

        static {
            HashMap hashMap = new HashMap(2);
            if (!TextUtils.isEmpty(DEFAULT_USER_AGENT)) {
                hashMap.put(USER_AGENT_HEADER, Collections.singletonList(new StringHeaderFactory(DEFAULT_USER_AGENT)));
            }
            hashMap.put(ENCODING_HEADER, Collections.singletonList(new StringHeaderFactory(DEFAULT_ENCODING)));
            DEFAULT_HEADERS = Collections.unmodifiableMap(hashMap);
        }

        public Builder addHeader(String str, String str2) {
            return addHeader(str, (LazyHeaderFactory) new StringHeaderFactory(str2));
        }

        public Builder addHeader(String str, LazyHeaderFactory lazyHeaderFactory) {
            if ((this.isEncodingDefault && ENCODING_HEADER.equalsIgnoreCase(str)) || (this.isUserAgentDefault && USER_AGENT_HEADER.equalsIgnoreCase(str))) {
                return setHeader(str, lazyHeaderFactory);
            }
            copyIfNecessary();
            getFactories(str).add(lazyHeaderFactory);
            return this;
        }

        public Builder setHeader(String str, String str2) {
            return setHeader(str, (LazyHeaderFactory) str2 == null ? null : new StringHeaderFactory(str2));
        }

        public Builder setHeader(String str, LazyHeaderFactory lazyHeaderFactory) {
            copyIfNecessary();
            if (lazyHeaderFactory == null) {
                this.headers.remove(str);
            } else {
                List<LazyHeaderFactory> factories = getFactories(str);
                factories.clear();
                factories.add(lazyHeaderFactory);
            }
            if (this.isEncodingDefault && ENCODING_HEADER.equalsIgnoreCase(str)) {
                this.isEncodingDefault = false;
            }
            if (this.isUserAgentDefault && USER_AGENT_HEADER.equalsIgnoreCase(str)) {
                this.isUserAgentDefault = false;
            }
            return this;
        }

        private List<LazyHeaderFactory> getFactories(String str) {
            List<LazyHeaderFactory> list = this.headers.get(str);
            if (list != null) {
                return list;
            }
            ArrayList arrayList = new ArrayList();
            this.headers.put(str, arrayList);
            return arrayList;
        }

        private void copyIfNecessary() {
            if (this.copyOnModify) {
                this.copyOnModify = false;
                this.headers = copyHeaders();
            }
        }

        public LazyHeaders build() {
            this.copyOnModify = true;
            return new LazyHeaders(this.headers);
        }

        private Map<String, List<LazyHeaderFactory>> copyHeaders() {
            HashMap hashMap = new HashMap(this.headers.size());
            for (Map.Entry next : this.headers.entrySet()) {
                hashMap.put(next.getKey(), new ArrayList((Collection) next.getValue()));
            }
            return hashMap;
        }
    }

    static final class StringHeaderFactory implements LazyHeaderFactory {
        private final String value;

        StringHeaderFactory(String str) {
            this.value = str;
        }

        public String buildHeader() {
            return this.value;
        }

        public String toString() {
            return "StringHeaderFactory{value='" + this.value + '\'' + '}';
        }

        public boolean equals(Object obj) {
            if (obj instanceof StringHeaderFactory) {
                return this.value.equals(((StringHeaderFactory) obj).value);
            }
            return false;
        }

        public int hashCode() {
            return this.value.hashCode();
        }
    }
}
