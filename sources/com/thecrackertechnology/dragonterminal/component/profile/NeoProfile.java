package com.thecrackertechnology.dragonterminal.component.profile;

import com.thecrackertechnology.dragonterminal.component.codegen.CodeGenParameter;
import com.thecrackertechnology.dragonterminal.component.codegen.generators.NeoProfileGenerator;
import com.thecrackertechnology.dragonterminal.component.codegen.interfaces.CodeGenObject;
import com.thecrackertechnology.dragonterminal.component.codegen.interfaces.CodeGenerator;
import com.thecrackertechnology.dragonterminal.component.config.ConfigureComponent;
import com.thecrackertechnology.dragonterminal.component.extrakey.NeoExtraKey;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.component.helper.ConfigFileBasedObject;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoConfigureFile;
import com.thecrackertechnology.dragonterminal.frontend.logging.NLog;
import io.neolang.visitor.ConfigVisitor;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000 !2\u00020\u00012\u00020\u0002:\u0001!B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u001b\u0010\u001c\u001a\u0004\u0018\u00010\u0019*\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0005H\u0004¢\u0006\u0002\u0010\u001eJ\u001c\u0010\u001c\u001a\u00020\u0019*\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u0019H\u0004J\u0016\u0010 \u001a\u0004\u0018\u00010\u0005*\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0005H\u0004J\u001c\u0010 \u001a\u00020\u0005*\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u0005H\u0004R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t8BX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0007\"\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/profile/NeoProfile;", "Lcom/thecrackertechnology/dragonterminal/component/codegen/interfaces/CodeGenObject;", "Lcom/thecrackertechnology/dragonterminal/frontend/component/helper/ConfigFileBasedObject;", "()V", "profileMetaName", "", "getProfileMetaName", "()Ljava/lang/String;", "profileMetaPath", "", "getProfileMetaPath", "()[Ljava/lang/String;", "profileName", "getProfileName", "setProfileName", "(Ljava/lang/String;)V", "getCodeGenerator", "Lcom/thecrackertechnology/dragonterminal/component/codegen/interfaces/CodeGenerator;", "parameter", "Lcom/thecrackertechnology/dragonterminal/component/codegen/CodeGenParameter;", "onConfigLoaded", "", "configVisitor", "Lio/neolang/visitor/ConfigVisitor;", "testLoadConfigure", "", "file", "Ljava/io/File;", "getProfileBoolean", "key", "(Lio/neolang/visitor/ConfigVisitor;Ljava/lang/String;)Ljava/lang/Boolean;", "fallback", "getProfileString", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoProfile.kt */
public abstract class NeoProfile implements CodeGenObject, ConfigFileBasedObject {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String PROFILE_NAME = "name";
    private String profileName = "Unknown Profile";

    public abstract String getProfileMetaName();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/profile/NeoProfile$Companion;", "", "()V", "PROFILE_NAME", "", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: NeoProfile.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final String[] getProfileMetaPath() {
        return new String[]{getProfileMetaName()};
    }

    public final String getProfileName() {
        return this.profileName;
    }

    public final void setProfileName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.profileName = str;
    }

    public void onConfigLoaded(ConfigVisitor configVisitor) {
        Intrinsics.checkParameterIsNotNull(configVisitor, "configVisitor");
        this.profileName = getProfileString(configVisitor, "name", this.profileName);
    }

    public CodeGenerator getCodeGenerator(CodeGenParameter codeGenParameter) {
        Intrinsics.checkParameterIsNotNull(codeGenParameter, "parameter");
        return new NeoProfileGenerator(codeGenParameter);
    }

    public final boolean testLoadConfigure(File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        try {
            NeoConfigureFile loadConfigure = ((ConfigureComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, ConfigureComponent.class, false, 2, (Object) null)).newLoader(file).loadConfigure();
            if (loadConfigure != null) {
                onConfigLoaded(loadConfigure.getVisitor());
                return true;
            }
            throw new RuntimeException("Parse configuration failed.");
        } catch (Exception e) {
            NLog nLog = NLog.INSTANCE;
            nLog.e("Profile", "Failed to load profile: " + file.getAbsolutePath() + ": " + e.getLocalizedMessage());
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final String getProfileString(ConfigVisitor configVisitor, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(configVisitor, "$this$getProfileString");
        Intrinsics.checkParameterIsNotNull(str, NeoExtraKey.EKS_META_KEY);
        Intrinsics.checkParameterIsNotNull(str2, "fallback");
        String profileString = getProfileString(configVisitor, str);
        return profileString != null ? profileString : str2;
    }

    /* access modifiers changed from: protected */
    public final boolean getProfileBoolean(ConfigVisitor configVisitor, String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(configVisitor, "$this$getProfileBoolean");
        Intrinsics.checkParameterIsNotNull(str, NeoExtraKey.EKS_META_KEY);
        Boolean profileBoolean = getProfileBoolean(configVisitor, str);
        return profileBoolean != null ? profileBoolean.booleanValue() : z;
    }

    /* access modifiers changed from: protected */
    public final String getProfileString(ConfigVisitor configVisitor, String str) {
        Intrinsics.checkParameterIsNotNull(configVisitor, "$this$getProfileString");
        Intrinsics.checkParameterIsNotNull(str, NeoExtraKey.EKS_META_KEY);
        return configVisitor.getStringValue(getProfileMetaPath(), str);
    }

    /* access modifiers changed from: protected */
    public final Boolean getProfileBoolean(ConfigVisitor configVisitor, String str) {
        Intrinsics.checkParameterIsNotNull(configVisitor, "$this$getProfileBoolean");
        Intrinsics.checkParameterIsNotNull(str, NeoExtraKey.EKS_META_KEY);
        return configVisitor.getBooleanValue(getProfileMetaPath(), str);
    }
}
