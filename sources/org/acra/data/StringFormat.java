package org.acra.data;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.acra.ReportField;
import org.acra.collections.ImmutableSet;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public enum StringFormat {
    JSON("application/json") {
        public String toFormattedString(CrashReportData crashReportData, ImmutableSet<ReportField> immutableSet, String str, String str2, boolean z) throws JSONException {
            Map<String, Object> map = crashReportData.toMap();
            JSONStringer object = new JSONStringer().object();
            Iterator<ReportField> it = immutableSet.iterator();
            while (it.hasNext()) {
                ReportField next = it.next();
                object.key(next.toString()).value(map.remove(next.toString()));
            }
            for (Map.Entry next2 : map.entrySet()) {
                object.key((String) next2.getKey()).value(next2.getValue());
            }
            return object.endObject().toString();
        }
    },
    KEY_VALUE_LIST("application/x-www-form-urlencoded") {
        public String toFormattedString(CrashReportData crashReportData, ImmutableSet<ReportField> immutableSet, String str, String str2, boolean z) throws UnsupportedEncodingException {
            Map<String, String> stringMap = toStringMap(crashReportData.toMap(), str2);
            StringBuilder sb = new StringBuilder();
            Iterator<ReportField> it = immutableSet.iterator();
            while (it.hasNext()) {
                ReportField next = it.next();
                append(sb, next.toString(), stringMap.remove(next.toString()), str, z);
            }
            for (Map.Entry next2 : stringMap.entrySet()) {
                append(sb, (String) next2.getKey(), (String) next2.getValue(), str, z);
            }
            return sb.toString();
        }

        private void append(StringBuilder sb, String str, String str2, String str3, boolean z) throws UnsupportedEncodingException {
            if (sb.length() > 0) {
                sb.append(str3);
            }
            if (z) {
                str = str != null ? URLEncoder.encode(str, "UTF-8") : null;
                str2 = str2 != null ? URLEncoder.encode(str2, "UTF-8") : null;
            }
            sb.append(str);
            sb.append('=');
            sb.append(str2);
        }

        private Map<String, String> toStringMap(Map<String, Object> map, String str) {
            HashMap hashMap = new HashMap(map.size());
            for (Map.Entry next : map.entrySet()) {
                hashMap.put(next.getKey(), valueToString(str, next.getValue()));
            }
            return hashMap;
        }

        private String valueToString(String str, Object obj) {
            if (obj instanceof JSONObject) {
                return TextUtils.join(str, flatten((JSONObject) obj));
            }
            return String.valueOf(obj);
        }

        private List<String> flatten(JSONObject jSONObject) {
            Object obj;
            ArrayList arrayList = new ArrayList();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    obj = jSONObject.get(next);
                } catch (JSONException unused) {
                    obj = null;
                }
                if (obj instanceof JSONObject) {
                    for (String str : flatten((JSONObject) obj)) {
                        arrayList.add(next + "." + str);
                    }
                } else {
                    arrayList.add(next + "=" + obj);
                }
            }
            return arrayList;
        }
    };
    
    private final String contentType;

    public abstract String toFormattedString(CrashReportData crashReportData, ImmutableSet<ReportField> immutableSet, String str, String str2, boolean z) throws Exception;

    private StringFormat(String str) {
        this.contentType = str;
    }

    public String getMatchingHttpContentType() {
        return this.contentType;
    }
}
