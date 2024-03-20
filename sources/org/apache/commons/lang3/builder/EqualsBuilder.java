package org.apache.commons.lang3.builder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.tuple.Pair;

public class EqualsBuilder implements Builder<Boolean> {
    private static final ThreadLocal<Set<Pair<IDKey, IDKey>>> REGISTRY = new ThreadLocal<>();
    private String[] excludeFields = null;
    private boolean isEquals = true;
    private Class<?> reflectUpToClass = null;
    private boolean testRecursive = false;
    private boolean testTransients = false;

    static Set<Pair<IDKey, IDKey>> getRegistry() {
        return REGISTRY.get();
    }

    static Pair<IDKey, IDKey> getRegisterPair(Object obj, Object obj2) {
        return Pair.of(new IDKey(obj), new IDKey(obj2));
    }

    static boolean isRegistered(Object obj, Object obj2) {
        Set<Pair<IDKey, IDKey>> registry = getRegistry();
        Pair<IDKey, IDKey> registerPair = getRegisterPair(obj, obj2);
        return registry != null && (registry.contains(registerPair) || registry.contains(Pair.of(registerPair.getLeft(), registerPair.getRight())));
    }

    private static void register(Object obj, Object obj2) {
        Set registry = getRegistry();
        if (registry == null) {
            registry = new HashSet();
            REGISTRY.set(registry);
        }
        registry.add(getRegisterPair(obj, obj2));
    }

    private static void unregister(Object obj, Object obj2) {
        Set<Pair<IDKey, IDKey>> registry = getRegistry();
        if (registry != null) {
            registry.remove(getRegisterPair(obj, obj2));
            if (registry.isEmpty()) {
                REGISTRY.remove();
            }
        }
    }

    public EqualsBuilder setTestTransients(boolean z) {
        this.testTransients = z;
        return this;
    }

    public EqualsBuilder setTestRecursive(boolean z) {
        this.testRecursive = z;
        return this;
    }

    public EqualsBuilder setReflectUpToClass(Class<?> cls) {
        this.reflectUpToClass = cls;
        return this;
    }

    public EqualsBuilder setExcludeFields(String... strArr) {
        this.excludeFields = strArr;
        return this;
    }

    public static boolean reflectionEquals(Object obj, Object obj2, Collection<String> collection) {
        return reflectionEquals(obj, obj2, ReflectionToStringBuilder.toNoNullStringArray(collection));
    }

    public static boolean reflectionEquals(Object obj, Object obj2, String... strArr) {
        return reflectionEquals(obj, obj2, false, (Class<?>) null, strArr);
    }

    public static boolean reflectionEquals(Object obj, Object obj2, boolean z) {
        return reflectionEquals(obj, obj2, z, (Class<?>) null, new String[0]);
    }

    public static boolean reflectionEquals(Object obj, Object obj2, boolean z, Class<?> cls, String... strArr) {
        return reflectionEquals(obj, obj2, z, cls, false, strArr);
    }

    public static boolean reflectionEquals(Object obj, Object obj2, boolean z, Class<?> cls, boolean z2, String... strArr) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return new EqualsBuilder().setExcludeFields(strArr).setReflectUpToClass(cls).setTestTransients(z).setTestRecursive(z2).reflectionAppend(obj, obj2).isEquals();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        if (r2.isInstance(r5) == false) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        if (r1.isInstance(r6) == false) goto L_0x0031;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.lang3.builder.EqualsBuilder reflectionAppend(java.lang.Object r5, java.lang.Object r6) {
        /*
            r4 = this;
            boolean r0 = r4.isEquals
            if (r0 != 0) goto L_0x0005
            return r4
        L_0x0005:
            if (r5 != r6) goto L_0x0008
            return r4
        L_0x0008:
            r0 = 0
            if (r5 == 0) goto L_0x0057
            if (r6 != 0) goto L_0x000e
            goto L_0x0057
        L_0x000e:
            java.lang.Class r1 = r5.getClass()
            java.lang.Class r2 = r6.getClass()
            boolean r3 = r1.isInstance(r6)
            if (r3 == 0) goto L_0x0023
            boolean r3 = r2.isInstance(r5)
            if (r3 != 0) goto L_0x0031
            goto L_0x0030
        L_0x0023:
            boolean r3 = r2.isInstance(r5)
            if (r3 == 0) goto L_0x0054
            boolean r3 = r1.isInstance(r6)
            if (r3 != 0) goto L_0x0030
            goto L_0x0031
        L_0x0030:
            r1 = r2
        L_0x0031:
            boolean r2 = r1.isArray()     // Catch:{ IllegalArgumentException -> 0x0051 }
            if (r2 == 0) goto L_0x003b
            r4.append((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ IllegalArgumentException -> 0x0051 }
            goto L_0x0050
        L_0x003b:
            r4.reflectionAppend(r5, r6, r1)     // Catch:{ IllegalArgumentException -> 0x0051 }
        L_0x003e:
            java.lang.Class r2 = r1.getSuperclass()     // Catch:{ IllegalArgumentException -> 0x0051 }
            if (r2 == 0) goto L_0x0050
            java.lang.Class<?> r2 = r4.reflectUpToClass     // Catch:{ IllegalArgumentException -> 0x0051 }
            if (r1 == r2) goto L_0x0050
            java.lang.Class r1 = r1.getSuperclass()     // Catch:{ IllegalArgumentException -> 0x0051 }
            r4.reflectionAppend(r5, r6, r1)     // Catch:{ IllegalArgumentException -> 0x0051 }
            goto L_0x003e
        L_0x0050:
            return r4
        L_0x0051:
            r4.isEquals = r0
            return r4
        L_0x0054:
            r4.isEquals = r0
            return r4
        L_0x0057:
            r4.isEquals = r0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.builder.EqualsBuilder.reflectionAppend(java.lang.Object, java.lang.Object):org.apache.commons.lang3.builder.EqualsBuilder");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:21|22|23|24|25) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0060 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void reflectionAppend(java.lang.Object r5, java.lang.Object r6, java.lang.Class<?> r7) {
        /*
            r4 = this;
            boolean r0 = isRegistered(r5, r6)
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            register(r5, r6)     // Catch:{ all -> 0x006f }
            java.lang.reflect.Field[] r7 = r7.getDeclaredFields()     // Catch:{ all -> 0x006f }
            r0 = 1
            java.lang.reflect.AccessibleObject.setAccessible(r7, r0)     // Catch:{ all -> 0x006f }
            r0 = 0
        L_0x0013:
            int r1 = r7.length     // Catch:{ all -> 0x006f }
            if (r0 >= r1) goto L_0x006b
            boolean r1 = r4.isEquals     // Catch:{ all -> 0x006f }
            if (r1 == 0) goto L_0x006b
            r1 = r7[r0]     // Catch:{ all -> 0x006f }
            java.lang.String[] r2 = r4.excludeFields     // Catch:{ all -> 0x006f }
            java.lang.String r3 = r1.getName()     // Catch:{ all -> 0x006f }
            boolean r2 = org.apache.commons.lang3.ArrayUtils.contains((java.lang.Object[]) r2, (java.lang.Object) r3)     // Catch:{ all -> 0x006f }
            if (r2 != 0) goto L_0x0068
            java.lang.String r2 = r1.getName()     // Catch:{ all -> 0x006f }
            java.lang.String r3 = "$"
            boolean r2 = r2.contains(r3)     // Catch:{ all -> 0x006f }
            if (r2 != 0) goto L_0x0068
            boolean r2 = r4.testTransients     // Catch:{ all -> 0x006f }
            if (r2 != 0) goto L_0x0042
            int r2 = r1.getModifiers()     // Catch:{ all -> 0x006f }
            boolean r2 = java.lang.reflect.Modifier.isTransient(r2)     // Catch:{ all -> 0x006f }
            if (r2 != 0) goto L_0x0068
        L_0x0042:
            int r2 = r1.getModifiers()     // Catch:{ all -> 0x006f }
            boolean r2 = java.lang.reflect.Modifier.isStatic(r2)     // Catch:{ all -> 0x006f }
            if (r2 != 0) goto L_0x0068
            java.lang.Class<org.apache.commons.lang3.builder.EqualsExclude> r2 = org.apache.commons.lang3.builder.EqualsExclude.class
            boolean r2 = r1.isAnnotationPresent(r2)     // Catch:{ all -> 0x006f }
            if (r2 != 0) goto L_0x0068
            java.lang.Object r2 = r1.get(r5)     // Catch:{ IllegalAccessException -> 0x0060 }
            java.lang.Object r1 = r1.get(r6)     // Catch:{ IllegalAccessException -> 0x0060 }
            r4.append((java.lang.Object) r2, (java.lang.Object) r1)     // Catch:{ IllegalAccessException -> 0x0060 }
            goto L_0x0068
        L_0x0060:
            java.lang.InternalError r7 = new java.lang.InternalError     // Catch:{ all -> 0x006f }
            java.lang.String r0 = "Unexpected IllegalAccessException"
            r7.<init>(r0)     // Catch:{ all -> 0x006f }
            throw r7     // Catch:{ all -> 0x006f }
        L_0x0068:
            int r0 = r0 + 1
            goto L_0x0013
        L_0x006b:
            unregister(r5, r6)
            return
        L_0x006f:
            r7 = move-exception
            unregister(r5, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.builder.EqualsBuilder.reflectionAppend(java.lang.Object, java.lang.Object, java.lang.Class):void");
    }

    public EqualsBuilder appendSuper(boolean z) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = z;
        return this;
    }

    public EqualsBuilder append(Object obj, Object obj2) {
        if (!this.isEquals || obj == obj2) {
            return this;
        }
        if (obj == null || obj2 == null) {
            setEquals(false);
            return this;
        }
        Class<?> cls = obj.getClass();
        if (cls.isArray()) {
            appendArray(obj, obj2);
        } else if (!this.testRecursive || ClassUtils.isPrimitiveOrWrapper(cls)) {
            this.isEquals = obj.equals(obj2);
        } else {
            reflectionAppend(obj, obj2);
        }
        return this;
    }

    private void appendArray(Object obj, Object obj2) {
        if (obj.getClass() != obj2.getClass()) {
            setEquals(false);
        } else if (obj instanceof long[]) {
            append((long[]) obj, (long[]) obj2);
        } else if (obj instanceof int[]) {
            append((int[]) obj, (int[]) obj2);
        } else if (obj instanceof short[]) {
            append((short[]) obj, (short[]) obj2);
        } else if (obj instanceof char[]) {
            append((char[]) obj, (char[]) obj2);
        } else if (obj instanceof byte[]) {
            append((byte[]) obj, (byte[]) obj2);
        } else if (obj instanceof double[]) {
            append((double[]) obj, (double[]) obj2);
        } else if (obj instanceof float[]) {
            append((float[]) obj, (float[]) obj2);
        } else if (obj instanceof boolean[]) {
            append((boolean[]) obj, (boolean[]) obj2);
        } else {
            append((Object[]) obj, (Object[]) obj2);
        }
    }

    public EqualsBuilder append(long j, long j2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = j == j2;
        return this;
    }

    public EqualsBuilder append(int i, int i2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = i == i2;
        return this;
    }

    public EqualsBuilder append(short s, short s2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = s == s2;
        return this;
    }

    public EqualsBuilder append(char c, char c2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = c == c2;
        return this;
    }

    public EqualsBuilder append(byte b, byte b2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = b == b2;
        return this;
    }

    public EqualsBuilder append(double d, double d2) {
        if (!this.isEquals) {
            return this;
        }
        return append(Double.doubleToLongBits(d), Double.doubleToLongBits(d2));
    }

    public EqualsBuilder append(float f, float f2) {
        if (!this.isEquals) {
            return this;
        }
        return append(Float.floatToIntBits(f), Float.floatToIntBits(f2));
    }

    public EqualsBuilder append(boolean z, boolean z2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = z == z2;
        return this;
    }

    public EqualsBuilder append(Object[] objArr, Object[] objArr2) {
        if (!this.isEquals || objArr == objArr2) {
            return this;
        }
        if (objArr == null || objArr2 == null) {
            setEquals(false);
            return this;
        } else if (objArr.length != objArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < objArr.length && this.isEquals; i++) {
                append(objArr[i], objArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder append(long[] jArr, long[] jArr2) {
        if (!this.isEquals || jArr == jArr2) {
            return this;
        }
        if (jArr == null || jArr2 == null) {
            setEquals(false);
            return this;
        } else if (jArr.length != jArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < jArr.length && this.isEquals; i++) {
                append(jArr[i], jArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder append(int[] iArr, int[] iArr2) {
        if (!this.isEquals || iArr == iArr2) {
            return this;
        }
        if (iArr == null || iArr2 == null) {
            setEquals(false);
            return this;
        } else if (iArr.length != iArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < iArr.length && this.isEquals; i++) {
                append(iArr[i], iArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder append(short[] sArr, short[] sArr2) {
        if (!this.isEquals || sArr == sArr2) {
            return this;
        }
        if (sArr == null || sArr2 == null) {
            setEquals(false);
            return this;
        } else if (sArr.length != sArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < sArr.length && this.isEquals; i++) {
                append(sArr[i], sArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder append(char[] cArr, char[] cArr2) {
        if (!this.isEquals || cArr == cArr2) {
            return this;
        }
        if (cArr == null || cArr2 == null) {
            setEquals(false);
            return this;
        } else if (cArr.length != cArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < cArr.length && this.isEquals; i++) {
                append(cArr[i], cArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder append(byte[] bArr, byte[] bArr2) {
        if (!this.isEquals || bArr == bArr2) {
            return this;
        }
        if (bArr == null || bArr2 == null) {
            setEquals(false);
            return this;
        } else if (bArr.length != bArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < bArr.length && this.isEquals; i++) {
                append(bArr[i], bArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder append(double[] dArr, double[] dArr2) {
        if (!this.isEquals || dArr == dArr2) {
            return this;
        }
        if (dArr == null || dArr2 == null) {
            setEquals(false);
            return this;
        } else if (dArr.length != dArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < dArr.length && this.isEquals; i++) {
                append(dArr[i], dArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder append(float[] fArr, float[] fArr2) {
        if (!this.isEquals || fArr == fArr2) {
            return this;
        }
        if (fArr == null || fArr2 == null) {
            setEquals(false);
            return this;
        } else if (fArr.length != fArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < fArr.length && this.isEquals; i++) {
                append(fArr[i], fArr2[i]);
            }
            return this;
        }
    }

    public EqualsBuilder append(boolean[] zArr, boolean[] zArr2) {
        if (!this.isEquals || zArr == zArr2) {
            return this;
        }
        if (zArr == null || zArr2 == null) {
            setEquals(false);
            return this;
        } else if (zArr.length != zArr2.length) {
            setEquals(false);
            return this;
        } else {
            for (int i = 0; i < zArr.length && this.isEquals; i++) {
                append(zArr[i], zArr2[i]);
            }
            return this;
        }
    }

    public boolean isEquals() {
        return this.isEquals;
    }

    public Boolean build() {
        return Boolean.valueOf(isEquals());
    }

    /* access modifiers changed from: protected */
    public void setEquals(boolean z) {
        this.isEquals = z;
    }

    public void reset() {
        this.isEquals = true;
    }
}
