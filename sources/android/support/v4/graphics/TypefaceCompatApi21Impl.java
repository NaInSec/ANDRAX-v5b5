package android.support.v4.graphics;

import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;

class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi21Impl";

    TypefaceCompatApi21Impl() {
    }

    private File getFile(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            String readlink = Os.readlink("/proc/self/fd/" + parcelFileDescriptor.getFd());
            if (OsConstants.S_ISREG(Os.stat(readlink).st_mode)) {
                return new File(readlink);
            }
        } catch (ErrnoException unused) {
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0047, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x004c, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r4.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0050, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0053, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0054, code lost:
        if (r5 != null) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x005e, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r4, android.os.CancellationSignal r5, android.support.v4.provider.FontsContractCompat.FontInfo[] r6, int r7) {
        /*
            r3 = this;
            int r0 = r6.length
            r1 = 0
            r2 = 1
            if (r0 >= r2) goto L_0x0006
            return r1
        L_0x0006:
            android.support.v4.provider.FontsContractCompat$FontInfo r6 = r3.findBestInfo(r6, r7)
            android.content.ContentResolver r7 = r4.getContentResolver()
            android.net.Uri r6 = r6.getUri()     // Catch:{ IOException -> 0x005f }
            java.lang.String r0 = "r"
            android.os.ParcelFileDescriptor r5 = r7.openFileDescriptor(r6, r0, r5)     // Catch:{ IOException -> 0x005f }
            java.io.File r6 = r3.getFile(r5)     // Catch:{ all -> 0x0051 }
            if (r6 == 0) goto L_0x002f
            boolean r7 = r6.canRead()     // Catch:{ all -> 0x0051 }
            if (r7 != 0) goto L_0x0025
            goto L_0x002f
        L_0x0025:
            android.graphics.Typeface r4 = android.graphics.Typeface.createFromFile(r6)     // Catch:{ all -> 0x0051 }
            if (r5 == 0) goto L_0x002e
            r5.close()     // Catch:{ IOException -> 0x005f }
        L_0x002e:
            return r4
        L_0x002f:
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ all -> 0x0051 }
            java.io.FileDescriptor r7 = r5.getFileDescriptor()     // Catch:{ all -> 0x0051 }
            r6.<init>(r7)     // Catch:{ all -> 0x0051 }
            android.graphics.Typeface r4 = super.createFromInputStream(r4, r6)     // Catch:{ all -> 0x0045 }
            r6.close()     // Catch:{ all -> 0x0051 }
            if (r5 == 0) goto L_0x0044
            r5.close()     // Catch:{ IOException -> 0x005f }
        L_0x0044:
            return r4
        L_0x0045:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r7 = move-exception
            r6.close()     // Catch:{ all -> 0x004c }
            goto L_0x0050
        L_0x004c:
            r6 = move-exception
            r4.addSuppressed(r6)     // Catch:{ all -> 0x0051 }
        L_0x0050:
            throw r7     // Catch:{ all -> 0x0051 }
        L_0x0051:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0053 }
        L_0x0053:
            r6 = move-exception
            if (r5 == 0) goto L_0x005e
            r5.close()     // Catch:{ all -> 0x005a }
            goto L_0x005e
        L_0x005a:
            r5 = move-exception
            r4.addSuppressed(r5)     // Catch:{ IOException -> 0x005f }
        L_0x005e:
            throw r6     // Catch:{ IOException -> 0x005f }
        L_0x005f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi21Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }
}
