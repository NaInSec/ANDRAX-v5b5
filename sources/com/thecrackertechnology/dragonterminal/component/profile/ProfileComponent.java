package com.thecrackertechnology.dragonterminal.component.profile;

import com.thecrackertechnology.dragonterminal.frontend.component.helper.ConfigFileBasedComponent;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoTermPath;
import com.thecrackertechnology.dragonterminal.frontend.logging.NLog;
import io.neolang.runtime.context.NeoLangContext;
import io.neolang.visitor.ConfigVisitor;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\nJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001e\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\n2\u000e\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\rJ\u0006\u0010\u0018\u001a\u00020\u0012J\u000e\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\nR\u0014\u0010\u0004\u001a\u00020\u00058VX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R \u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u000b0\tX\u0004¢\u0006\u0002\n\u0000R\"\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\n\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00020\r0\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/profile/ProfileComponent;", "Lcom/thecrackertechnology/dragonterminal/frontend/component/helper/ConfigFileBasedComponent;", "Lcom/thecrackertechnology/dragonterminal/component/profile/NeoProfile;", "()V", "checkComponentFileWhenObtained", "", "getCheckComponentFileWhenObtained", "()Z", "profileList", "", "", "", "profileRegistry", "Ljava/lang/Class;", "getProfiles", "", "metaName", "onCheckComponentFiles", "", "onCreateComponentObject", "configVisitor", "Lio/neolang/visitor/ConfigVisitor;", "registerProfile", "prototype", "reloadProfiles", "unregisterProfile", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ProfileComponent.kt */
public final class ProfileComponent extends ConfigFileBasedComponent<NeoProfile> {
    private final Map<String, List<NeoProfile>> profileList = new LinkedHashMap();
    private final Map<String, Class<? extends NeoProfile>> profileRegistry = new LinkedHashMap();

    public boolean getCheckComponentFileWhenObtained() {
        return true;
    }

    public ProfileComponent() {
        super(NeoTermPath.PROFILE_PATH);
    }

    public void onCheckComponentFiles() {
        reloadProfiles();
    }

    public NeoProfile onCreateComponentObject(ConfigVisitor configVisitor) {
        Intrinsics.checkParameterIsNotNull(configVisitor, "configVisitor");
        Collection arrayList = new ArrayList();
        for (NeoLangContext contextName : configVisitor.getRootContext().getChildren()) {
            Class cls = this.profileRegistry.get(contextName.getContextName());
            if (cls != null) {
                arrayList.add(cls);
            }
        }
        Class cls2 = (Class) CollectionsKt.singleOrNull((List) arrayList);
        if (cls2 != null) {
            NLog nLog = NLog.INSTANCE;
            nLog.e("ProfileComponent", "Loaded profile: " + cls2.getName());
            Object newInstance = cls2.newInstance();
            Intrinsics.checkExpressionValueIsNotNull(newInstance, "profileClass.newInstance()");
            return (NeoProfile) newInstance;
        }
        throw new IllegalArgumentException("No proper profile registry found");
    }

    public final List<NeoProfile> getProfiles(String str) {
        Intrinsics.checkParameterIsNotNull(str, "metaName");
        List<NeoProfile> list = this.profileList.get(str);
        return list != null ? list : CollectionsKt.emptyList();
    }

    public final void reloadProfiles() {
        this.profileList.clear();
        File[] listFiles = new File(getBaseDir()).listFiles(ConfigFileBasedComponent.Companion.getNEOLANG_FILTER());
        Intrinsics.checkExpressionValueIsNotNull(listFiles, "File(baseDir)\n          …listFiles(NEOLANG_FILTER)");
        Collection arrayList = new ArrayList();
        for (File file : listFiles) {
            Intrinsics.checkExpressionValueIsNotNull(file, "it");
            NeoProfile neoProfile = (NeoProfile) loadConfigure(file);
            if (neoProfile != null) {
                arrayList.add(neoProfile);
            }
        }
        for (NeoProfile neoProfile2 : (List) arrayList) {
            List list = this.profileList.get(neoProfile2.getProfileMetaName());
            if (list != null) {
                list.add(neoProfile2);
            } else {
                this.profileList.put(neoProfile2.getProfileMetaName(), CollectionsKt.mutableListOf(neoProfile2));
            }
        }
    }

    public final void registerProfile(String str, Class<? extends NeoProfile> cls) {
        Intrinsics.checkParameterIsNotNull(str, "metaName");
        Intrinsics.checkParameterIsNotNull(cls, "prototype");
        this.profileRegistry.put(str, cls);
        reloadProfiles();
    }

    public final void unregisterProfile(String str) {
        Intrinsics.checkParameterIsNotNull(str, "metaName");
        this.profileRegistry.remove(str);
    }
}
