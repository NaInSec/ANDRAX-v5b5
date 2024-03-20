package org.apache.commons.lang3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SerializationUtils {
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002f, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T extends java.io.Serializable> T clone(T r2) {
        /*
            if (r2 != 0) goto L_0x0004
            r2 = 0
            return r2
        L_0x0004:
            byte[] r0 = serialize(r2)
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream
            r1.<init>(r0)
            org.apache.commons.lang3.SerializationUtils$ClassLoaderAwareObjectInputStream r0 = new org.apache.commons.lang3.SerializationUtils$ClassLoaderAwareObjectInputStream     // Catch:{ ClassNotFoundException -> 0x0039, IOException -> 0x0030 }
            java.lang.Class r2 = r2.getClass()     // Catch:{ ClassNotFoundException -> 0x0039, IOException -> 0x0030 }
            java.lang.ClassLoader r2 = r2.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x0039, IOException -> 0x0030 }
            r0.<init>(r1, r2)     // Catch:{ ClassNotFoundException -> 0x0039, IOException -> 0x0030 }
            java.lang.Object r2 = r0.readObject()     // Catch:{ all -> 0x0024 }
            java.io.Serializable r2 = (java.io.Serializable) r2     // Catch:{ all -> 0x0024 }
            r0.close()     // Catch:{ ClassNotFoundException -> 0x0039, IOException -> 0x0030 }
            return r2
        L_0x0024:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0026 }
        L_0x0026:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x002b }
            goto L_0x002f
        L_0x002b:
            r0 = move-exception
            r2.addSuppressed(r0)     // Catch:{ ClassNotFoundException -> 0x0039, IOException -> 0x0030 }
        L_0x002f:
            throw r1     // Catch:{ ClassNotFoundException -> 0x0039, IOException -> 0x0030 }
        L_0x0030:
            r2 = move-exception
            org.apache.commons.lang3.SerializationException r0 = new org.apache.commons.lang3.SerializationException
            java.lang.String r1 = "IOException while reading or closing cloned object data"
            r0.<init>(r1, r2)
            throw r0
        L_0x0039:
            r2 = move-exception
            org.apache.commons.lang3.SerializationException r0 = new org.apache.commons.lang3.SerializationException
            java.lang.String r1 = "ClassNotFoundException while reading cloned object data"
            r0.<init>(r1, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.SerializationUtils.clone(java.io.Serializable):java.io.Serializable");
    }

    public static <T extends Serializable> T roundtrip(T t) {
        return (Serializable) deserialize(serialize(t));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001b, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0024, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void serialize(java.io.Serializable r3, java.io.OutputStream r4) {
        /*
            r0 = 0
            if (r4 == 0) goto L_0x0005
            r1 = 1
            goto L_0x0006
        L_0x0005:
            r1 = 0
        L_0x0006:
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r2 = "The OutputStream must not be null"
            org.apache.commons.lang3.Validate.isTrue((boolean) r1, (java.lang.String) r2, (java.lang.Object[]) r0)
            java.io.ObjectOutputStream r0 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x0025 }
            r0.<init>(r4)     // Catch:{ IOException -> 0x0025 }
            r0.writeObject(r3)     // Catch:{ all -> 0x0019 }
            r0.close()     // Catch:{ IOException -> 0x0025 }
            return
        L_0x0019:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x001b }
        L_0x001b:
            r4 = move-exception
            r0.close()     // Catch:{ all -> 0x0020 }
            goto L_0x0024
        L_0x0020:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch:{ IOException -> 0x0025 }
        L_0x0024:
            throw r4     // Catch:{ IOException -> 0x0025 }
        L_0x0025:
            r3 = move-exception
            org.apache.commons.lang3.SerializationException r4 = new org.apache.commons.lang3.SerializationException
            r4.<init>((java.lang.Throwable) r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.SerializationUtils.serialize(java.io.Serializable, java.io.OutputStream):void");
    }

    public static byte[] serialize(Serializable serializable) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        serialize(serializable, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0025, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T deserialize(java.io.InputStream r3) {
        /*
            r0 = 0
            if (r3 == 0) goto L_0x0005
            r1 = 1
            goto L_0x0006
        L_0x0005:
            r1 = 0
        L_0x0006:
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r2 = "The InputStream must not be null"
            org.apache.commons.lang3.Validate.isTrue((boolean) r1, (java.lang.String) r2, (java.lang.Object[]) r0)
            java.io.ObjectInputStream r0 = new java.io.ObjectInputStream     // Catch:{ ClassNotFoundException -> 0x0028, IOException -> 0x0026 }
            r0.<init>(r3)     // Catch:{ ClassNotFoundException -> 0x0028, IOException -> 0x0026 }
            java.lang.Object r3 = r0.readObject()     // Catch:{ all -> 0x001a }
            r0.close()     // Catch:{ ClassNotFoundException -> 0x0028, IOException -> 0x0026 }
            return r3
        L_0x001a:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x001c }
        L_0x001c:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch:{ ClassNotFoundException -> 0x0028, IOException -> 0x0026 }
        L_0x0025:
            throw r1     // Catch:{ ClassNotFoundException -> 0x0028, IOException -> 0x0026 }
        L_0x0026:
            r3 = move-exception
            goto L_0x0029
        L_0x0028:
            r3 = move-exception
        L_0x0029:
            org.apache.commons.lang3.SerializationException r0 = new org.apache.commons.lang3.SerializationException
            r0.<init>((java.lang.Throwable) r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.SerializationUtils.deserialize(java.io.InputStream):java.lang.Object");
    }

    public static <T> T deserialize(byte[] bArr) {
        Validate.isTrue(bArr != null, "The byte[] must not be null", new Object[0]);
        return deserialize((InputStream) new ByteArrayInputStream(bArr));
    }

    static class ClassLoaderAwareObjectInputStream extends ObjectInputStream {
        private static final Map<String, Class<?>> primitiveTypes = new HashMap();
        private final ClassLoader classLoader;

        static {
            primitiveTypes.put("byte", Byte.TYPE);
            primitiveTypes.put("short", Short.TYPE);
            primitiveTypes.put("int", Integer.TYPE);
            primitiveTypes.put("long", Long.TYPE);
            primitiveTypes.put("float", Float.TYPE);
            primitiveTypes.put("double", Double.TYPE);
            primitiveTypes.put("boolean", Boolean.TYPE);
            primitiveTypes.put("char", Character.TYPE);
            primitiveTypes.put("void", Void.TYPE);
        }

        ClassLoaderAwareObjectInputStream(InputStream inputStream, ClassLoader classLoader2) throws IOException {
            super(inputStream);
            this.classLoader = classLoader2;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0024, code lost:
            return r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
            throw r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0018, code lost:
            return java.lang.Class.forName(r3, false, java.lang.Thread.currentThread().getContextClassLoader());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0019, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
            r3 = primitiveTypes.get(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
            if (r3 != null) goto L_0x0024;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x000c */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Class<?> resolveClass(java.io.ObjectStreamClass r3) throws java.io.IOException, java.lang.ClassNotFoundException {
            /*
                r2 = this;
                java.lang.String r3 = r3.getName()
                r0 = 0
                java.lang.ClassLoader r1 = r2.classLoader     // Catch:{ ClassNotFoundException -> 0x000c }
                java.lang.Class r3 = java.lang.Class.forName(r3, r0, r1)     // Catch:{ ClassNotFoundException -> 0x000c }
                return r3
            L_0x000c:
                java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ ClassNotFoundException -> 0x0019 }
                java.lang.ClassLoader r1 = r1.getContextClassLoader()     // Catch:{ ClassNotFoundException -> 0x0019 }
                java.lang.Class r3 = java.lang.Class.forName(r3, r0, r1)     // Catch:{ ClassNotFoundException -> 0x0019 }
                return r3
            L_0x0019:
                r0 = move-exception
                java.util.Map<java.lang.String, java.lang.Class<?>> r1 = primitiveTypes
                java.lang.Object r3 = r1.get(r3)
                java.lang.Class r3 = (java.lang.Class) r3
                if (r3 == 0) goto L_0x0025
                return r3
            L_0x0025:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.SerializationUtils.ClassLoaderAwareObjectInputStream.resolveClass(java.io.ObjectStreamClass):java.lang.Class");
        }
    }
}
