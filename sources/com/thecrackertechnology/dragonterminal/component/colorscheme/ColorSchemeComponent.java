package com.thecrackertechnology.dragonterminal.component.colorscheme;

import android.content.Context;
import com.thecrackertechnology.andrax.AndraxApp;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.component.codegen.CodeGenComponent;
import com.thecrackertechnology.dragonterminal.component.codegen.interfaces.CodeGenObject;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.component.helper.ConfigFileBasedComponent;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoTermPath;
import com.thecrackertechnology.dragonterminal.frontend.logging.NLog;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.ExtraKeysView;
import com.thecrackertechnology.dragonterminal.utils.AssetsUtils;
import com.thecrackertechnology.dragonterminal.utils.FileUtils;
import io.neolang.visitor.ConfigVisitor;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 $2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0003J$\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u000e\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u000bJ\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0019J\u0006\u0010\u001a\u001a\u00020\u0002J\u0006\u0010\u001b\u001a\u00020\u000bJ\b\u0010\u001c\u001a\u00020\rH\u0016J\u0010\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0006\u0010 \u001a\u00020\u0006J\u000e\u0010!\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0002J\u000e\u0010\"\u001a\u00020\r2\u0006\u0010#\u001a\u00020\u0002J\u000e\u0010\"\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0002X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/colorscheme/ColorSchemeComponent;", "Lcom/thecrackertechnology/dragonterminal/frontend/component/helper/ConfigFileBasedComponent;", "Lcom/thecrackertechnology/dragonterminal/component/colorscheme/NeoColorScheme;", "()V", "DEFAULT_COLOR", "checkComponentFileWhenObtained", "", "getCheckComponentFileWhenObtained", "()Z", "colors", "", "", "applyColorScheme", "", "view", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "extraKeysView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView;", "colorScheme", "extractDefaultColor", "context", "Landroid/content/Context;", "getColorScheme", "colorName", "getColorSchemeNames", "", "getCurrentColorScheme", "getCurrentColorSchemeName", "onCheckComponentFiles", "onCreateComponentObject", "configVisitor", "Lio/neolang/visitor/ConfigVisitor;", "reloadColorSchemes", "saveColorScheme", "setCurrentColorScheme", "color", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ColorSchemeComponent.kt */
public final class ColorSchemeComponent extends ConfigFileBasedComponent<NeoColorScheme> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private NeoColorScheme DEFAULT_COLOR;
    private Map<String, NeoColorScheme> colors = new LinkedHashMap();

    public boolean getCheckComponentFileWhenObtained() {
        return true;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/colorscheme/ColorSchemeComponent$Companion;", "", "()V", "colorFile", "Ljava/io/File;", "colorName", "", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: ColorSchemeComponent.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final File colorFile(String str) {
            Intrinsics.checkParameterIsNotNull(str, "colorName");
            return new File(NeoTermPath.COLORS_PATH + '/' + str + ".nl");
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ColorSchemeComponent() {
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
            java.lang.String r1 = "/home/.neoterm/color"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r3.<init>(r0)
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            r3.colors = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.component.colorscheme.ColorSchemeComponent.<init>():void");
    }

    public void onCheckComponentFiles() {
        if (!Companion.colorFile(DefaultColorScheme.INSTANCE.getColorName()).exists() && !extractDefaultColor(AndraxApp.Companion.get())) {
            this.DEFAULT_COLOR = DefaultColorScheme.INSTANCE;
            Map<String, NeoColorScheme> map = this.colors;
            NeoColorScheme neoColorScheme = this.DEFAULT_COLOR;
            if (neoColorScheme == null) {
                Intrinsics.throwUninitializedPropertyAccessException("DEFAULT_COLOR");
            }
            String colorName = neoColorScheme.getColorName();
            NeoColorScheme neoColorScheme2 = this.DEFAULT_COLOR;
            if (neoColorScheme2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("DEFAULT_COLOR");
            }
            map.put(colorName, neoColorScheme2);
        } else if (!reloadColorSchemes()) {
            this.DEFAULT_COLOR = DefaultColorScheme.INSTANCE;
            Map<String, NeoColorScheme> map2 = this.colors;
            NeoColorScheme neoColorScheme3 = this.DEFAULT_COLOR;
            if (neoColorScheme3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("DEFAULT_COLOR");
            }
            String colorName2 = neoColorScheme3.getColorName();
            NeoColorScheme neoColorScheme4 = this.DEFAULT_COLOR;
            if (neoColorScheme4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("DEFAULT_COLOR");
            }
            map2.put(colorName2, neoColorScheme4);
        }
    }

    public NeoColorScheme onCreateComponentObject(ConfigVisitor configVisitor) {
        Intrinsics.checkParameterIsNotNull(configVisitor, "configVisitor");
        return new NeoColorScheme();
    }

    public final boolean reloadColorSchemes() {
        this.colors.clear();
        StringBuilder sb = new StringBuilder();
        File filesDir = AndraxApp.Companion.get().getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "AndraxApp.get().filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/home/.neoterm/color");
        File[] listFiles = new File(sb.toString()).listFiles(ConfigFileBasedComponent.Companion.getNEOLANG_FILTER());
        Intrinsics.checkExpressionValueIsNotNull(listFiles, "File(AndraxApp.get().fil…listFiles(NEOLANG_FILTER)");
        Collection arrayList = new ArrayList();
        for (File file : listFiles) {
            Intrinsics.checkExpressionValueIsNotNull(file, "it");
            NeoColorScheme neoColorScheme = (NeoColorScheme) loadConfigure(file);
            if (neoColorScheme != null) {
                arrayList.add(neoColorScheme);
            }
        }
        for (NeoColorScheme neoColorScheme2 : (List) arrayList) {
            this.colors.put(neoColorScheme2.getColorName(), neoColorScheme2);
        }
        if (!this.colors.containsKey(DefaultColorScheme.INSTANCE.getColorName())) {
            return false;
        }
        NeoColorScheme neoColorScheme3 = this.colors.get(DefaultColorScheme.INSTANCE.getColorName());
        if (neoColorScheme3 == null) {
            Intrinsics.throwNpe();
        }
        this.DEFAULT_COLOR = neoColorScheme3;
        return true;
    }

    public final void applyColorScheme(TerminalView terminalView, ExtraKeysView extraKeysView, NeoColorScheme neoColorScheme) {
        if (neoColorScheme != null) {
            neoColorScheme.applyColorScheme$app_release(terminalView, extraKeysView);
        }
    }

    public final NeoColorScheme getCurrentColorScheme() {
        NeoColorScheme neoColorScheme = this.colors.get(getCurrentColorSchemeName());
        if (neoColorScheme == null) {
            Intrinsics.throwNpe();
        }
        return neoColorScheme;
    }

    public final String getCurrentColorSchemeName() {
        String loadString = NeoPreference.INSTANCE.loadString((int) R.string.key_customization_color_scheme, DefaultColorScheme.INSTANCE.getColorName());
        if (this.colors.containsKey(loadString)) {
            return loadString;
        }
        String colorName = DefaultColorScheme.INSTANCE.getColorName();
        NeoPreference.INSTANCE.store((int) R.string.key_customization_color_scheme, (Object) DefaultColorScheme.INSTANCE.getColorName());
        return colorName;
    }

    public final NeoColorScheme getColorScheme(String str) {
        Intrinsics.checkParameterIsNotNull(str, "colorName");
        if (!this.colors.containsKey(str)) {
            return getCurrentColorScheme();
        }
        NeoColorScheme neoColorScheme = this.colors.get(str);
        if (neoColorScheme == null) {
            Intrinsics.throwNpe();
        }
        return neoColorScheme;
    }

    public final List<String> getColorSchemeNames() {
        ArrayList arrayList = new ArrayList();
        CollectionsKt.addAll(arrayList, this.colors.keySet());
        return arrayList;
    }

    public final void setCurrentColorScheme(String str) {
        Intrinsics.checkParameterIsNotNull(str, "colorName");
        NeoPreference.INSTANCE.store((int) R.string.key_customization_color_scheme, (Object) str);
    }

    public final void setCurrentColorScheme(NeoColorScheme neoColorScheme) {
        Intrinsics.checkParameterIsNotNull(neoColorScheme, NeoColorScheme.COLOR_PREFIX);
        setCurrentColorScheme(neoColorScheme.getColorName());
    }

    private final boolean extractDefaultColor(Context context) {
        try {
            AssetsUtils assetsUtils = AssetsUtils.INSTANCE;
            StringBuilder sb = new StringBuilder();
            File filesDir = AndraxApp.Companion.get().getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir, "AndraxApp.get().filesDir");
            sb.append(filesDir.getAbsolutePath());
            sb.append("/home/.neoterm/color/");
            assetsUtils.extractAssetsDir(context, NeoColorScheme.CONTEXT_COLOR_NAME, sb.toString());
            return true;
        } catch (Exception e) {
            NLog nLog = NLog.INSTANCE;
            nLog.e("ColorScheme", "Failed to extract default colors: " + e.getLocalizedMessage());
            return false;
        }
    }

    public final void saveColorScheme(NeoColorScheme neoColorScheme) {
        Intrinsics.checkParameterIsNotNull(neoColorScheme, "colorScheme");
        File colorFile = Companion.colorFile(neoColorScheme.getColorName());
        if (!colorFile.exists()) {
            CodeGenObject codeGenObject = neoColorScheme;
            String generateCode = ((CodeGenComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, CodeGenComponent.class, false, 2, (Object) null)).newGenerator(codeGenObject).generateCode(codeGenObject);
            FileUtils fileUtils = FileUtils.INSTANCE;
            Charset charset = Charsets.UTF_8;
            if (generateCode != null) {
                byte[] bytes = generateCode.getBytes(charset);
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                if (!fileUtils.writeFile(colorFile, bytes)) {
                    throw new RuntimeException("Failed to save file " + colorFile.getAbsolutePath());
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        throw new RuntimeException("ColorScheme already " + neoColorScheme.getColorName() + " exists!");
    }
}
