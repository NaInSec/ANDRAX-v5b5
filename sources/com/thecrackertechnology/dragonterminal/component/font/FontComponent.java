package com.thecrackertechnology.dragonterminal.component.font;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import com.thecrackertechnology.andrax.AndraxApp;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.frontend.component.NeoComponent;
import com.thecrackertechnology.dragonterminal.frontend.config.DefaultValues;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoTermPath;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.ExtraKeysView;
import com.thecrackertechnology.dragonterminal.utils.AssetsUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004J\b\u0010\u000f\u001a\u00020\tH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0007H\u0002J\u0010\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0006\u0010\u0017\u001a\u00020\u0004J\u0006\u0010\u0018\u001a\u00020\u0007J\u000e\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0007J\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u001bJ\u0010\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u001d\u001a\u00020\tH\u0016J\b\u0010\u001e\u001a\u00020\tH\u0016J\b\u0010\u001f\u001a\u00020\tH\u0016J\u0006\u0010 \u001a\u00020\u0011J\u000e\u0010!\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00040\u0006X.¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/font/FontComponent;", "Lcom/thecrackertechnology/dragonterminal/frontend/component/NeoComponent;", "()V", "DEFAULT_FONT", "Lcom/thecrackertechnology/dragonterminal/component/font/NeoFont;", "fonts", "", "", "applyFont", "", "terminalView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "extraKeysView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView;", "font", "checkForFiles", "extractDefaultFont", "", "context", "Landroid/content/Context;", "fontFile", "Ljava/io/File;", "fontName", "getCurrentFont", "getCurrentFontName", "getFont", "getFontNames", "", "loadDefaultFontFromAsset", "onServiceDestroy", "onServiceInit", "onServiceObtained", "reloadFonts", "setCurrentFont", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: FontComponent.kt */
public final class FontComponent implements NeoComponent {
    private NeoFont DEFAULT_FONT;
    private Map<String, NeoFont> fonts;

    public void onServiceDestroy() {
    }

    public final void applyFont(TerminalView terminalView, ExtraKeysView extraKeysView, NeoFont neoFont) {
        if (neoFont != null) {
            neoFont.applyFont$app_release(terminalView, extraKeysView);
        }
    }

    public final NeoFont getCurrentFont() {
        Map<String, NeoFont> map = this.fonts;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fonts");
        }
        NeoFont neoFont = map.get(getCurrentFontName());
        if (neoFont == null) {
            Intrinsics.throwNpe();
        }
        return neoFont;
    }

    public final void setCurrentFont(String str) {
        Intrinsics.checkParameterIsNotNull(str, "fontName");
        NeoPreference.INSTANCE.store((int) R.string.key_customization_font, (Object) str);
    }

    public final String getCurrentFontName() {
        String defaultFont = DefaultValues.INSTANCE.getDefaultFont();
        String loadString = NeoPreference.INSTANCE.loadString((int) R.string.key_customization_font, defaultFont);
        Map<String, NeoFont> map = this.fonts;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fonts");
        }
        if (map.containsKey(loadString)) {
            return loadString;
        }
        NeoPreference.INSTANCE.store((int) R.string.key_customization_font, (Object) defaultFont);
        return defaultFont;
    }

    public final NeoFont getFont(String str) {
        Intrinsics.checkParameterIsNotNull(str, "fontName");
        Map<String, NeoFont> map = this.fonts;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fonts");
        }
        if (!map.containsKey(str)) {
            return getCurrentFont();
        }
        Map<String, NeoFont> map2 = this.fonts;
        if (map2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fonts");
        }
        NeoFont neoFont = map2.get(str);
        if (neoFont == null) {
            Intrinsics.throwNpe();
        }
        return neoFont;
    }

    public final List<String> getFontNames() {
        ArrayList arrayList = new ArrayList();
        Collection collection = arrayList;
        Map<String, NeoFont> map = this.fonts;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fonts");
        }
        CollectionsKt.addAll(collection, map.keySet());
        return arrayList;
    }

    public final boolean reloadFonts() {
        Map<String, NeoFont> map = this.fonts;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fonts");
        }
        map.clear();
        Map<String, NeoFont> map2 = this.fonts;
        if (map2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fonts");
        }
        Typeface typeface = Typeface.MONOSPACE;
        Intrinsics.checkExpressionValueIsNotNull(typeface, "Typeface.MONOSPACE");
        map2.put("Monospace", new NeoFont(typeface));
        Map<String, NeoFont> map3 = this.fonts;
        if (map3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fonts");
        }
        Typeface typeface2 = Typeface.SANS_SERIF;
        Intrinsics.checkExpressionValueIsNotNull(typeface2, "Typeface.SANS_SERIF");
        map3.put("Sans Serif", new NeoFont(typeface2));
        Map<String, NeoFont> map4 = this.fonts;
        if (map4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fonts");
        }
        Typeface typeface3 = Typeface.SERIF;
        Intrinsics.checkExpressionValueIsNotNull(typeface3, "Typeface.SERIF");
        map4.put("Serif", new NeoFont(typeface3));
        for (File file : new File(NeoTermPath.FONT_PATH).listFiles(FontComponent$reloadFonts$1.INSTANCE)) {
            Intrinsics.checkExpressionValueIsNotNull(file, "file");
            String fontName = fontName(file);
            NeoFont neoFont = new NeoFont(file);
            Map<String, NeoFont> map5 = this.fonts;
            if (map5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fonts");
            }
            map5.put(fontName, neoFont);
        }
        String defaultFont = DefaultValues.INSTANCE.getDefaultFont();
        Map<String, NeoFont> map6 = this.fonts;
        if (map6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fonts");
        }
        if (!map6.containsKey(defaultFont)) {
            return false;
        }
        Map<String, NeoFont> map7 = this.fonts;
        if (map7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fonts");
        }
        NeoFont neoFont2 = map7.get(defaultFont);
        if (neoFont2 == null) {
            Intrinsics.throwNpe();
        }
        this.DEFAULT_FONT = neoFont2;
        return true;
    }

    public void onServiceInit() {
        checkForFiles();
    }

    public void onServiceObtained() {
        checkForFiles();
    }

    private final NeoFont loadDefaultFontFromAsset(Context context) {
        String defaultFont = DefaultValues.INSTANCE.getDefaultFont();
        AssetManager assets = context.getAssets();
        Typeface createFromAsset = Typeface.createFromAsset(assets, "fonts/" + defaultFont + ".ttf");
        Intrinsics.checkExpressionValueIsNotNull(createFromAsset, "Typeface.createFromAsset…\"fonts/$defaultFont.ttf\")");
        return new NeoFont(createFromAsset);
    }

    private final boolean extractDefaultFont(Context context) {
        try {
            AssetsUtils.INSTANCE.extractAssetsDir(context, "fonts", NeoTermPath.FONT_PATH);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private final File fontFile(String str) {
        return new File(NeoTermPath.FONT_PATH + '/' + str + ".ttf");
    }

    private final String fontName(File file) {
        return FilesKt.getNameWithoutExtension(file);
    }

    private final void checkForFiles() {
        new File(NeoTermPath.FONT_PATH).mkdirs();
        this.fonts = new LinkedHashMap();
        AndraxApp andraxApp = AndraxApp.Companion.get();
        String defaultFont = DefaultValues.INSTANCE.getDefaultFont();
        if (!fontFile(defaultFont).exists()) {
            Context context = andraxApp;
            if (!extractDefaultFont(context)) {
                this.DEFAULT_FONT = loadDefaultFontFromAsset(context);
                Map<String, NeoFont> map = this.fonts;
                if (map == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fonts");
                }
                NeoFont neoFont = this.DEFAULT_FONT;
                if (neoFont == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("DEFAULT_FONT");
                }
                map.put(defaultFont, neoFont);
                return;
            }
        }
        if (!reloadFonts()) {
            this.DEFAULT_FONT = loadDefaultFontFromAsset(andraxApp);
            Map<String, NeoFont> map2 = this.fonts;
            if (map2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fonts");
            }
            NeoFont neoFont2 = this.DEFAULT_FONT;
            if (neoFont2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("DEFAULT_FONT");
            }
            map2.put(defaultFont, neoFont2);
        }
    }
}
