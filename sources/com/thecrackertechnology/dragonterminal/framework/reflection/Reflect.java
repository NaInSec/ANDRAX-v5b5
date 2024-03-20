package com.thecrackertechnology.dragonterminal.framework.reflection;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Reflect {
    private final boolean isClass = true;
    private final Object mObject;

    private Reflect(Class<?> cls) {
        this.mObject = cls;
    }

    private Reflect(Object obj) {
        this.mObject = obj;
    }

    public static Reflect on(String str) throws ReflectionException {
        return on(forName(str));
    }

    public static Reflect on(String str, ClassLoader classLoader) throws ReflectionException {
        return on(forName(str, classLoader));
    }

    public static Reflect on(Class<?> cls) {
        return new Reflect(cls);
    }

    public static Reflect on(Object obj) {
        return new Reflect(obj);
    }

    private static Reflect on(Method method, Object obj, Object... objArr) throws ReflectionException {
        try {
            makeAccessible(method);
            if (method.getReturnType() != Void.TYPE) {
                return on(method.invoke(obj, objArr));
            }
            method.invoke(obj, objArr);
            return on(obj);
        } catch (Exception e) {
            throw new ReflectionException(e);
        }
    }

    public static <T extends AccessibleObject> T makeAccessible(T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof Member) {
            Member member = (Member) t;
            if (Modifier.isPublic(member.getModifiers()) && Modifier.isPublic(member.getDeclaringClass().getModifiers())) {
                return t;
            }
        }
        if (!t.isAccessible()) {
            t.setAccessible(true);
        }
        return t;
    }

    private static String property(String str) {
        int length = str.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            return str.toLowerCase();
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    private static Reflect on(Constructor<?> constructor, Object... objArr) throws ReflectionException {
        try {
            return on(((Constructor) makeAccessible(constructor)).newInstance(objArr));
        } catch (Exception e) {
            throw new ReflectionException(e);
        }
    }

    private static Object unwrap(Object obj) {
        return obj instanceof Reflect ? ((Reflect) obj).get() : obj;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Class<?>[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Class<?>[] convertTypes(java.lang.Object... r3) {
        /*
            r0 = 0
            if (r3 != 0) goto L_0x0006
            java.lang.Class[] r3 = new java.lang.Class[r0]
            return r3
        L_0x0006:
            int r1 = r3.length
            java.lang.Class[] r1 = new java.lang.Class[r1]
        L_0x0009:
            int r2 = r3.length
            if (r0 >= r2) goto L_0x001c
            r2 = r3[r0]
            if (r2 != 0) goto L_0x0013
            java.lang.Class<com.thecrackertechnology.dragonterminal.framework.reflection.NullPointer> r2 = com.thecrackertechnology.dragonterminal.framework.reflection.NullPointer.class
            goto L_0x0017
        L_0x0013:
            java.lang.Class r2 = r2.getClass()
        L_0x0017:
            r1[r0] = r2
            int r0 = r0 + 1
            goto L_0x0009
        L_0x001c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.framework.reflection.Reflect.convertTypes(java.lang.Object[]):java.lang.Class[]");
    }

    private static Class<?> forName(String str) throws ReflectionException {
        try {
            return Class.forName(str);
        } catch (Exception e) {
            throw new ReflectionException(e);
        }
    }

    private static Class<?> forName(String str, ClassLoader classLoader) throws ReflectionException {
        try {
            return Class.forName(str, true, classLoader);
        } catch (Exception e) {
            throw new ReflectionException(e);
        }
    }

    private static Class<?> wrapClassType(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        if (!cls.isPrimitive()) {
            return cls;
        }
        if (Boolean.TYPE == cls) {
            return Boolean.class;
        }
        if (Integer.TYPE == cls) {
            return Integer.class;
        }
        if (Long.TYPE == cls) {
            return Long.class;
        }
        if (Short.TYPE == cls) {
            return Short.class;
        }
        if (Byte.TYPE == cls) {
            return Byte.class;
        }
        if (Double.TYPE == cls) {
            return Double.class;
        }
        if (Float.TYPE == cls) {
            return Float.class;
        }
        if (Character.TYPE == cls) {
            return Character.class;
        }
        return Void.TYPE == cls ? Void.class : cls;
    }

    public <T> T get() {
        return this.mObject;
    }

    public Reflect set(String str, Object obj) throws ReflectionException {
        try {
            Field lookupField = lookupField(str);
            lookupField.setAccessible(true);
            lookupField.set(this.mObject, unwrap(obj));
            return this;
        } catch (Exception e) {
            throw new ReflectionException(e);
        }
    }

    public <T> T get(String str) throws ReflectionException {
        return field(str).get();
    }

    public Reflect field(String str) throws ReflectionException {
        try {
            return on(lookupField(str).get(this.mObject));
        } catch (Exception e) {
            throw new ReflectionException(e);
        }
    }

    private Field lookupField(String str) throws ReflectionException {
        Class type = type();
        try {
            return type.getField(str);
        } catch (NoSuchFieldException e) {
            do {
                try {
                    return (Field) makeAccessible(type.getDeclaredField(str));
                } catch (NoSuchFieldException unused) {
                    type = type.getSuperclass();
                    if (type == null) {
                        throw new ReflectionException(e);
                    }
                }
            } while (type == null);
            throw new ReflectionException(e);
        }
    }

    public Map<String, Reflect> fields() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Class type = type();
        do {
            for (Field field : type.getDeclaredFields()) {
                if ((!this.isClass) ^ Modifier.isStatic(field.getModifiers())) {
                    String name = field.getName();
                    if (!linkedHashMap.containsKey(name)) {
                        linkedHashMap.put(name, field(name));
                    }
                }
            }
            type = type.getSuperclass();
        } while (type != null);
        return linkedHashMap;
    }

    public Reflect call(String str) throws ReflectionException {
        return call(str, new Object[0]);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0019, code lost:
        return on(lookupSimilarMethod(r4, r0), r3.mObject, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001a, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        throw new com.thecrackertechnology.dragonterminal.framework.reflection.ReflectionException(r4);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x000f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.thecrackertechnology.dragonterminal.framework.reflection.Reflect call(java.lang.String r4, java.lang.Object... r5) throws com.thecrackertechnology.dragonterminal.framework.reflection.ReflectionException {
        /*
            r3 = this;
            java.lang.Class[] r0 = convertTypes(r5)
            java.lang.reflect.Method r1 = r3.exactMethod(r4, r0)     // Catch:{ NoSuchMethodException -> 0x000f }
            java.lang.Object r2 = r3.mObject     // Catch:{ NoSuchMethodException -> 0x000f }
            com.thecrackertechnology.dragonterminal.framework.reflection.Reflect r4 = on(r1, r2, r5)     // Catch:{ NoSuchMethodException -> 0x000f }
            return r4
        L_0x000f:
            java.lang.reflect.Method r4 = r3.lookupSimilarMethod(r4, r0)     // Catch:{ NoSuchMethodException -> 0x001a }
            java.lang.Object r0 = r3.mObject     // Catch:{ NoSuchMethodException -> 0x001a }
            com.thecrackertechnology.dragonterminal.framework.reflection.Reflect r4 = on(r4, r0, r5)     // Catch:{ NoSuchMethodException -> 0x001a }
            return r4
        L_0x001a:
            r4 = move-exception
            com.thecrackertechnology.dragonterminal.framework.reflection.ReflectionException r5 = new com.thecrackertechnology.dragonterminal.framework.reflection.ReflectionException
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.framework.reflection.Reflect.call(java.lang.String, java.lang.Object[]):com.thecrackertechnology.dragonterminal.framework.reflection.Reflect");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        throw new java.lang.NoSuchMethodException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000d, code lost:
        return r0.getDeclaredMethod(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000e, code lost:
        r0 = r0.getSuperclass();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        if (r0 != null) goto L_0x0009;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0009 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.reflect.Method exactMethod(java.lang.String r2, java.lang.Class<?>[] r3) throws java.lang.NoSuchMethodException {
        /*
            r1 = this;
            java.lang.Class r0 = r1.type()
            java.lang.reflect.Method r2 = r0.getMethod(r2, r3)     // Catch:{ NoSuchMethodException -> 0x0009 }
            return r2
        L_0x0009:
            java.lang.reflect.Method r2 = r0.getDeclaredMethod(r2, r3)     // Catch:{ NoSuchMethodException -> 0x000e }
            return r2
        L_0x000e:
            java.lang.Class r0 = r0.getSuperclass()
            if (r0 == 0) goto L_0x0015
            goto L_0x0009
        L_0x0015:
            java.lang.NoSuchMethodException r2 = new java.lang.NoSuchMethodException
            r2.<init>()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.framework.reflection.Reflect.exactMethod(java.lang.String, java.lang.Class[]):java.lang.reflect.Method");
    }

    private Method lookupSimilarMethod(String str, Class<?>[] clsArr) throws NoSuchMethodException {
        Class type = type();
        for (Method method : type.getMethods()) {
            if (isSignatureSimilar(method, str, clsArr)) {
                return method;
            }
        }
        do {
            for (Method method2 : type.getDeclaredMethods()) {
                if (isSignatureSimilar(method2, str, clsArr)) {
                    return method2;
                }
            }
            type = type.getSuperclass();
        } while (type != null);
        throw new NoSuchMethodException("No similar method " + str + " with params " + Arrays.toString(clsArr) + " could be found on type " + type() + ".");
    }

    private boolean isSignatureSimilar(Method method, String str, Class<?>[] clsArr) {
        return method.getName().equals(str) && match(method.getParameterTypes(), clsArr);
    }

    public Reflect create() throws ReflectionException {
        return create(new Object[0]);
    }

    public Reflect create(Object... objArr) throws ReflectionException {
        Class[] convertTypes = convertTypes(objArr);
        try {
            return on(type().getDeclaredConstructor(convertTypes), objArr);
        } catch (NoSuchMethodException e) {
            for (Constructor constructor : type().getDeclaredConstructors()) {
                if (match(constructor.getParameterTypes(), convertTypes)) {
                    return on((Constructor<?>) constructor, objArr);
                }
            }
            throw new ReflectionException(e);
        }
    }

    public <P> P as(Class<P> cls) {
        $$Lambda$Reflect$acEHPoLEFsVyHarS7tNdOtsqNc r1 = new InvocationHandler(this.mObject instanceof Map) {
            private final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final Object invoke(Object obj, Method method, Object[] objArr) {
                return Reflect.this.lambda$as$0$Reflect(this.f$1, obj, method, objArr);
            }
        };
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, r1);
    }

    public /* synthetic */ Object lambda$as$0$Reflect(boolean z, Object obj, Method method, Object[] objArr) throws Throwable {
        int i;
        String name = method.getName();
        try {
            return on(this.mObject).call(name, objArr).get();
        } catch (ReflectionException e) {
            if (z) {
                Map map = (Map) this.mObject;
                if (objArr == null) {
                    i = 0;
                } else {
                    i = objArr.length;
                }
                if (i == 0 && name.startsWith("get")) {
                    return map.get(property(name.substring(3)));
                }
                if (i == 0 && name.startsWith("is")) {
                    return map.get(property(name.substring(2)));
                }
                if (i == 1 && name.startsWith("set")) {
                    map.put(property(name.substring(3)), objArr[0]);
                    return null;
                }
            }
            throw e;
        }
    }

    private boolean match(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        for (int i = 0; i < clsArr2.length; i++) {
            if (clsArr2[i] != NullPointer.class && !wrapClassType(clsArr[i]).isAssignableFrom(wrapClassType(clsArr2[i]))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return this.mObject.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Reflect) {
            return this.mObject.equals(((Reflect) obj).get());
        }
        return false;
    }

    public String toString() {
        return this.mObject.toString();
    }

    public Class<?> type() {
        if (this.isClass) {
            return (Class) this.mObject;
        }
        return this.mObject.getClass();
    }
}
