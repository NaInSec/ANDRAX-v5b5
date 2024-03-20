package com.thecrackertechnology.dragonterminal.component.extrakey;

import com.thecrackertechnology.dragonterminal.component.config.ConfigureComponent;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.component.helper.ConfigFileBasedObject;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoConfigureFile;
import com.thecrackertechnology.dragonterminal.frontend.logging.NLog;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.ExtraKeysView;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.button.IExtraButton;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.button.TextButton;
import io.neolang.runtime.type.NeoLangArray;
import io.neolang.runtime.type.NeoLangArrayElement;
import io.neolang.runtime.type.NeoLangValue;
import io.neolang.visitor.ConfigVisitor;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0005H\u0002J\u0010\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u001dH\u0016J\u000e\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006%"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/extrakey/NeoExtraKey;", "Lcom/thecrackertechnology/dragonterminal/frontend/component/helper/ConfigFileBasedObject;", "()V", "programNames", "", "", "getProgramNames", "()Ljava/util/List;", "shortcutKeys", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/IExtraButton;", "getShortcutKeys", "version", "", "getVersion", "()I", "setVersion", "(I)V", "withDefaultKeys", "", "getWithDefaultKeys", "()Z", "setWithDefaultKeys", "(Z)V", "applyExtraKeys", "", "extraKeysView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView;", "getMetaByVisitor", "visitor", "Lio/neolang/visitor/ConfigVisitor;", "metaName", "onConfigLoaded", "configVisitor", "testLoadConfigure", "file", "Ljava/io/File;", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoExtraKey.kt */
public final class NeoExtraKey implements ConfigFileBasedObject {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String EKS_META_CODE = "code";
    public static final String EKS_META_CONTEXT_NAME = "extra-key";
    /* access modifiers changed from: private */
    public static final String[] EKS_META_CONTEXT_PATH = {EKS_META_CONTEXT_NAME};
    public static final String EKS_META_DISPLAY = "display";
    public static final String EKS_META_KEY = "key";
    public static final String EKS_META_PROGRAM = "program";
    public static final String EKS_META_VERSION = "version";
    public static final String EKS_META_WITH_DEFAULT = "with-default";
    public static final String EKS_META_WITH_ENTER = "with-enter";
    private final List<String> programNames = new ArrayList();
    private final List<IExtraButton> shortcutKeys = new ArrayList();
    private int version;
    private boolean withDefaultKeys = true;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\n\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/extrakey/NeoExtraKey$Companion;", "", "()V", "EKS_META_CODE", "", "EKS_META_CONTEXT_NAME", "EKS_META_CONTEXT_PATH", "", "getEKS_META_CONTEXT_PATH", "()[Ljava/lang/String;", "[Ljava/lang/String;", "EKS_META_DISPLAY", "EKS_META_KEY", "EKS_META_PROGRAM", "EKS_META_VERSION", "EKS_META_WITH_DEFAULT", "EKS_META_WITH_ENTER", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: NeoExtraKey.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String[] getEKS_META_CONTEXT_PATH() {
            return NeoExtraKey.EKS_META_CONTEXT_PATH;
        }
    }

    public final int getVersion() {
        return this.version;
    }

    public final void setVersion(int i) {
        this.version = i;
    }

    public final List<String> getProgramNames() {
        return this.programNames;
    }

    public final List<IExtraButton> getShortcutKeys() {
        return this.shortcutKeys;
    }

    public final boolean getWithDefaultKeys() {
        return this.withDefaultKeys;
    }

    public final void setWithDefaultKeys(boolean z) {
        this.withDefaultKeys = z;
    }

    public final void applyExtraKeys(ExtraKeysView extraKeysView) {
        Intrinsics.checkParameterIsNotNull(extraKeysView, "extraKeysView");
        if (this.withDefaultKeys) {
            extraKeysView.loadDefaultUserKeys();
        }
        for (IExtraButton addUserKey : this.shortcutKeys) {
            extraKeysView.addUserKey(addUserKey);
        }
    }

    public void onConfigLoaded(ConfigVisitor configVisitor) {
        Intrinsics.checkParameterIsNotNull(configVisitor, "configVisitor");
        NeoLangArray<NeoLangArrayElement> array = configVisitor.getArray(EKS_META_CONTEXT_PATH, EKS_META_PROGRAM);
        if (!array.isEmpty()) {
            for (NeoLangArrayElement neoLangArrayElement : array) {
                if (!neoLangArrayElement.isBlock()) {
                    this.programNames.add(neoLangArrayElement.eval().asString());
                }
            }
            ArrayList<NeoLangArrayElement> arrayList = new ArrayList<>();
            for (Object next : configVisitor.getArray(EKS_META_CONTEXT_PATH, EKS_META_KEY)) {
                if (!((NeoLangArrayElement) next).isBlock()) {
                    break;
                }
                arrayList.add(next);
            }
            for (NeoLangArrayElement neoLangArrayElement2 : arrayList) {
                NeoLangValue eval = neoLangArrayElement2.eval(EKS_META_DISPLAY);
                NeoLangValue eval2 = neoLangArrayElement2.eval(EKS_META_CODE);
                if (eval2.isValid()) {
                    String asString = eval2.asString();
                    String asString2 = eval.isValid() ? eval.asString() : asString;
                    TextButton textButton = new TextButton(asString, Intrinsics.areEqual((Object) neoLangArrayElement2.eval(EKS_META_WITH_ENTER).asString(), (Object) "true"));
                    textButton.setDisplayText(asString2);
                    this.shortcutKeys.add(textButton);
                } else {
                    throw new RuntimeException("Key must have a code");
                }
            }
            String metaByVisitor = getMetaByVisitor(configVisitor, "version");
            this.version = metaByVisitor != null ? (int) Double.parseDouble(metaByVisitor) : 0;
            this.withDefaultKeys = Intrinsics.areEqual((Object) "true", (Object) getMetaByVisitor(configVisitor, EKS_META_WITH_DEFAULT));
            return;
        }
        throw new RuntimeException("Extra Key must have programs attribute");
    }

    private final String getMetaByVisitor(ConfigVisitor configVisitor, String str) {
        return configVisitor.getStringValue(EKS_META_CONTEXT_PATH, str);
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
            nLog.e("ExtraKey", "Failed to load extra key config: " + file.getAbsolutePath() + ": " + e.getLocalizedMessage());
            return false;
        }
    }
}
