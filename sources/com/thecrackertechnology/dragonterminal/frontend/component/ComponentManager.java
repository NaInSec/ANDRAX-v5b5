package com.thecrackertechnology.dragonterminal.frontend.component;

import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\u00062\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005H\u0002J\u001a\u0010\t\u001a\u0002H\n\"\n\b\u0000\u0010\n\u0018\u0001*\u00020\u0006H\b¢\u0006\u0002\u0010\u000bJ-\u0010\t\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\u00052\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u000e\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005J\u0016\u0010\u0012\u001a\u00020\u00102\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005R\"\u0010\u0003\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/component/ComponentManager;", "", "()V", "COMPONENTS", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/lang/Class;", "Lcom/thecrackertechnology/dragonterminal/frontend/component/NeoComponent;", "createServiceInstance", "componentInterface", "getComponent", "T", "()Lcom/thecrackertechnology/dragonterminal/frontend/component/NeoComponent;", "errorThrow", "", "(Ljava/lang/Class;Z)Lcom/thecrackertechnology/dragonterminal/frontend/component/NeoComponent;", "registerComponent", "", "componentClass", "unregisterComponent", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ComponentManager.kt */
public final class ComponentManager {
    private static final ConcurrentHashMap<Class<? extends NeoComponent>, NeoComponent> COMPONENTS = new ConcurrentHashMap<>();
    public static final ComponentManager INSTANCE = new ComponentManager();

    private ComponentManager() {
    }

    public final void registerComponent(Class<? extends NeoComponent> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "componentClass");
        if (!COMPONENTS.containsKey(cls)) {
            NeoComponent createServiceInstance = createServiceInstance(cls);
            COMPONENTS.put(cls, createServiceInstance);
            createServiceInstance.onServiceInit();
            return;
        }
        String simpleName = cls.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "componentClass.simpleName");
        throw new ComponentDuplicateException(simpleName);
    }

    public final void unregisterComponent(Class<? extends NeoComponent> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "componentInterface");
        NeoComponent neoComponent = COMPONENTS.get(cls);
        if (neoComponent != null) {
            neoComponent.onServiceDestroy();
            COMPONENTS.remove(cls);
        }
    }

    public static /* synthetic */ NeoComponent getComponent$default(ComponentManager componentManager, Class cls, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return componentManager.getComponent(cls, z);
    }

    public final <T extends NeoComponent> T getComponent(Class<T> cls, boolean z) {
        Intrinsics.checkParameterIsNotNull(cls, "componentInterface");
        T t = (NeoComponent) COMPONENTS.get(cls);
        if (t != null) {
            t.onServiceObtained();
            if (t != null) {
                return t;
            }
            throw new TypeCastException("null cannot be cast to non-null type T");
        }
        String simpleName = cls.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "componentInterface.simpleName");
        throw new ComponentNotFoundException(simpleName);
    }

    public final /* synthetic */ <T extends NeoComponent> T getComponent() {
        Intrinsics.reifiedOperationMarker(4, "T");
        return getComponent$default(this, NeoComponent.class, false, 2, (Object) null);
    }

    private final NeoComponent createServiceInstance(Class<? extends NeoComponent> cls) {
        Object newInstance = cls.newInstance();
        Intrinsics.checkExpressionValueIsNotNull(newInstance, "componentInterface.newInstance()");
        return (NeoComponent) newInstance;
    }
}
