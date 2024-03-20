package com.thecrackertechnology.dragonterminal.component.extrakey;

import android.content.Context;
import com.thecrackertechnology.andrax.AndraxApp;
import com.thecrackertechnology.dragonterminal.frontend.component.helper.ConfigFileBasedComponent;
import com.thecrackertechnology.dragonterminal.frontend.logging.NLog;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.ExtraKeysView;
import com.thecrackertechnology.dragonterminal.utils.AssetsUtils;
import io.neolang.visitor.ConfigVisitor;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0002H\u0002J\b\u0010\u0015\u001a\u00020\fH\u0002J\u0018\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/extrakey/ExtraKeyComponent;", "Lcom/thecrackertechnology/dragonterminal/frontend/component/helper/ConfigFileBasedComponent;", "Lcom/thecrackertechnology/dragonterminal/component/extrakey/NeoExtraKey;", "()V", "checkComponentFileWhenObtained", "", "getCheckComponentFileWhenObtained", "()Z", "extraKeys", "", "", "extractDefaultConfig", "", "context", "Landroid/content/Context;", "onCheckComponentFiles", "onCreateComponentObject", "configVisitor", "Lio/neolang/visitor/ConfigVisitor;", "registerShortcutKeys", "extraKey", "reloadExtraKeyConfig", "showShortcutKeys", "program", "extraKeysView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ExtraKeyComponent.kt */
public final class ExtraKeyComponent extends ConfigFileBasedComponent<NeoExtraKey> {
    private final Map<String, NeoExtraKey> extraKeys = new LinkedHashMap();

    public boolean getCheckComponentFileWhenObtained() {
        return true;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ExtraKeyComponent() {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.thecrackertechnology.andrax.AndraxApp$Companion r1 = com.thecrackertechnology.andrax.AndraxApp.Companion
            com.thecrackertechnology.andrax.AndraxApp r1 = r1.get()
            java.io.File r1 = r1.getFilesDir()
            java.lang.String r2 = "AndraxApp.get().filesDir"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            java.lang.String r1 = r1.getAbsolutePath()
            r0.append(r1)
            java.lang.String r1 = "/home/.neoterm/eks"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r3.<init>(r0)
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            r3.extraKeys = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.component.extrakey.ExtraKeyComponent.<init>():void");
    }

    public void onCheckComponentFiles() {
        StringBuilder sb = new StringBuilder();
        File filesDir = AndraxApp.Companion.get().getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "AndraxApp.get().filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/home/.neoterm/eks/default.nl");
        if (!new File(sb.toString()).exists()) {
            extractDefaultConfig(AndraxApp.Companion.get());
        }
        reloadExtraKeyConfig();
    }

    public NeoExtraKey onCreateComponentObject(ConfigVisitor configVisitor) {
        Intrinsics.checkParameterIsNotNull(configVisitor, "configVisitor");
        return new NeoExtraKey();
    }

    public final void showShortcutKeys(String str, ExtraKeysView extraKeysView) {
        Intrinsics.checkParameterIsNotNull(str, NeoExtraKey.EKS_META_PROGRAM);
        if (extraKeysView != null) {
            NeoExtraKey neoExtraKey = this.extraKeys.get(str);
            if (neoExtraKey != null) {
                neoExtraKey.applyExtraKeys(extraKeysView);
            } else {
                extraKeysView.loadDefaultUserKeys();
            }
        }
    }

    private final void registerShortcutKeys(NeoExtraKey neoExtraKey) {
        for (String put : neoExtraKey.getProgramNames()) {
            this.extraKeys.put(put, neoExtraKey);
        }
    }

    private final void extractDefaultConfig(Context context) {
        try {
            AssetsUtils assetsUtils = AssetsUtils.INSTANCE;
            StringBuilder sb = new StringBuilder();
            File filesDir = AndraxApp.Companion.get().getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir, "AndraxApp.get().filesDir");
            sb.append(filesDir.getAbsolutePath());
            sb.append("/home/.neoterm/eks");
            assetsUtils.extractAssetsDir(context, "eks", sb.toString());
        } catch (Exception e) {
            NLog nLog = NLog.INSTANCE;
            nLog.e("ExtraKey", "Failed to extract configure: " + e.getLocalizedMessage());
        }
    }

    private final void reloadExtraKeyConfig() {
        this.extraKeys.clear();
        StringBuilder sb = new StringBuilder();
        File filesDir = AndraxApp.Companion.get().getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "AndraxApp.get().filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/home/.neoterm/eks");
        File[] listFiles = new File(sb.toString()).listFiles(ConfigFileBasedComponent.Companion.getNEOLANG_FILTER());
        Intrinsics.checkExpressionValueIsNotNull(listFiles, "File(AndraxApp.get().fil…listFiles(NEOLANG_FILTER)");
        Collection arrayList = new ArrayList();
        for (File file : listFiles) {
            Intrinsics.checkExpressionValueIsNotNull(file, "it");
            String absolutePath = file.getAbsolutePath();
            StringBuilder sb2 = new StringBuilder();
            File filesDir2 = AndraxApp.Companion.get().getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir2, "AndraxApp.get().filesDir");
            sb2.append(filesDir2.getAbsolutePath());
            sb2.append("/home/.neoterm/eks");
            if (!Intrinsics.areEqual((Object) absolutePath, (Object) sb2.toString())) {
                arrayList.add(file);
            }
        }
        Collection arrayList2 = new ArrayList();
        for (File file2 : (List) arrayList) {
            Intrinsics.checkExpressionValueIsNotNull(file2, "it");
            NeoExtraKey neoExtraKey = (NeoExtraKey) loadConfigure(file2);
            if (neoExtraKey != null) {
                arrayList2.add(neoExtraKey);
            }
        }
        for (NeoExtraKey registerShortcutKeys : (List) arrayList2) {
            registerShortcutKeys(registerShortcutKeys);
        }
    }
}
