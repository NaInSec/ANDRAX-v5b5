package de.psdev.licensesdialog.licenses;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

public abstract class License implements Serializable {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final long serialVersionUID = 3100331505738956523L;
    private String mCachedFullText = null;
    private String mCachedSummaryText = null;

    public abstract String getName();

    public abstract String getUrl();

    public abstract String getVersion();

    public abstract String readFullTextFromResources(Context context);

    public abstract String readSummaryTextFromResources(Context context);

    public final String getSummaryText(Context context) {
        if (this.mCachedSummaryText == null) {
            this.mCachedSummaryText = readSummaryTextFromResources(context);
        }
        return this.mCachedSummaryText;
    }

    public final String getFullText(Context context) {
        if (this.mCachedFullText == null) {
            this.mCachedFullText = readFullTextFromResources(context);
        }
        return this.mCachedFullText;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0036 A[SYNTHETIC, Splitter:B:24:0x0036] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getContent(android.content.Context r3, int r4) {
        /*
            r2 = this;
            r0 = 0
            android.content.res.Resources r3 = r3.getResources()     // Catch:{ IOException -> 0x002d }
            java.io.InputStream r3 = r3.openRawResource(r4)     // Catch:{ IOException -> 0x002d }
            if (r3 == 0) goto L_0x0023
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ IOException -> 0x002d }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x002d }
            r1.<init>(r3)     // Catch:{ IOException -> 0x002d }
            r4.<init>(r1)     // Catch:{ IOException -> 0x002d }
            java.lang.String r3 = r2.toString(r4)     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            r4.close()     // Catch:{ IOException -> 0x001c }
        L_0x001c:
            return r3
        L_0x001d:
            r3 = move-exception
            r0 = r4
            goto L_0x0034
        L_0x0020:
            r3 = move-exception
            r0 = r4
            goto L_0x002e
        L_0x0023:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ IOException -> 0x002d }
            java.lang.String r4 = "Error opening license file."
            r3.<init>(r4)     // Catch:{ IOException -> 0x002d }
            throw r3     // Catch:{ IOException -> 0x002d }
        L_0x002b:
            r3 = move-exception
            goto L_0x0034
        L_0x002d:
            r3 = move-exception
        L_0x002e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x002b }
            r4.<init>(r3)     // Catch:{ all -> 0x002b }
            throw r4     // Catch:{ all -> 0x002b }
        L_0x0034:
            if (r0 == 0) goto L_0x0039
            r0.close()     // Catch:{ IOException -> 0x0039 }
        L_0x0039:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: de.psdev.licensesdialog.licenses.License.getContent(android.content.Context, int):java.lang.String");
    }

    private String toString(BufferedReader bufferedReader) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return sb.toString();
            }
            sb.append(readLine);
            sb.append(LINE_SEPARATOR);
        }
    }
}
