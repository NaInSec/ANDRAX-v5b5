package com.thecrackertechnology.dragonterminal.component.colorscheme;

import com.thecrackertechnology.dragonterminal.backend.TerminalColorScheme;
import com.thecrackertechnology.dragonterminal.backend.TerminalColors;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.component.codegen.CodeGenParameter;
import com.thecrackertechnology.dragonterminal.component.codegen.generators.NeoColorGenerator;
import com.thecrackertechnology.dragonterminal.component.codegen.interfaces.CodeGenObject;
import com.thecrackertechnology.dragonterminal.component.codegen.interfaces.CodeGenerator;
import com.thecrackertechnology.dragonterminal.component.config.ConfigureComponent;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.component.helper.ConfigFileBasedObject;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoConfigureFile;
import com.thecrackertechnology.dragonterminal.frontend.logging.NLog;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.ExtraKeysView;
import io.neolang.runtime.type.NeoLangValue;
import io.neolang.visitor.ConfigVisitor;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 62\u00020\u00012\u00020\u0002:\u00016B\u0005¢\u0006\u0002\u0010\u0003J!\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0000¢\u0006\u0002\b#J\u0006\u0010$\u001a\u00020\u0000J\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0016J\u0010\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010)\u001a\u00020\fJ\u001a\u0010*\u001a\u0004\u0018\u00010\u00052\u0006\u0010+\u001a\u00020,2\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\u001a\u0010-\u001a\u0004\u0018\u00010\u00052\u0006\u0010+\u001a\u00020,2\u0006\u0010.\u001a\u00020\u0005H\u0002J\u0010\u0010/\u001a\u00020\u001e2\u0006\u00100\u001a\u00020,H\u0016J\u0016\u0010\u000f\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u0005J\u000e\u00101\u001a\u0002022\u0006\u00103\u001a\u000204J\b\u00105\u001a\u00020\u001eH\u0002R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR&\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0005X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0007\"\u0004\b\u0013\u0010\tR\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0007\"\u0004\b\u0016\u0010\tR\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0007\"\u0004\b\u0019\u0010\tR\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0007\"\u0004\b\u001c\u0010\t¨\u00067"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/colorscheme/NeoColorScheme;", "Lcom/thecrackertechnology/dragonterminal/component/codegen/interfaces/CodeGenObject;", "Lcom/thecrackertechnology/dragonterminal/frontend/component/helper/ConfigFileBasedObject;", "()V", "backgroundColor", "", "getBackgroundColor", "()Ljava/lang/String;", "setBackgroundColor", "(Ljava/lang/String;)V", "color", "", "", "getColor", "()Ljava/util/Map;", "setColor", "(Ljava/util/Map;)V", "colorName", "getColorName", "setColorName", "colorVersion", "getColorVersion", "setColorVersion", "cursorColor", "getCursorColor", "setCursorColor", "foregroundColor", "getForegroundColor", "setForegroundColor", "applyColorScheme", "", "view", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "extraKeysView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView;", "applyColorScheme$app_release", "copy", "getCodeGenerator", "Lcom/thecrackertechnology/dragonterminal/component/codegen/interfaces/CodeGenerator;", "parameter", "Lcom/thecrackertechnology/dragonterminal/component/codegen/CodeGenParameter;", "type", "getColorByVisitor", "visitor", "Lio/neolang/visitor/ConfigVisitor;", "getMetaByVisitor", "metaName", "onConfigLoaded", "configVisitor", "testLoadConfigure", "", "file", "Ljava/io/File;", "validateColors", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoColorScheme.kt */
public class NeoColorScheme implements CodeGenObject, ConfigFileBasedObject {
    public static final int COLOR_BACKGROUND = -3;
    public static final int COLOR_BRIGHT_BLACK = 8;
    public static final int COLOR_BRIGHT_BLUE = 12;
    public static final int COLOR_BRIGHT_CYAN = 14;
    public static final int COLOR_BRIGHT_GREEN = 10;
    public static final int COLOR_BRIGHT_MAGENTA = 13;
    public static final int COLOR_BRIGHT_RED = 9;
    public static final int COLOR_BRIGHT_WHITE = 15;
    public static final int COLOR_BRIGHT_YELLOW = 11;
    public static final int COLOR_CURSOR = -1;
    public static final String COLOR_DEF_BACKGROUND = "background";
    public static final String COLOR_DEF_CURSOR = "cursor";
    public static final String COLOR_DEF_FOREGROUND = "foreground";
    public static final int COLOR_DIM_BLACK = 0;
    public static final int COLOR_DIM_BLUE = 4;
    public static final int COLOR_DIM_CYAN = 6;
    public static final int COLOR_DIM_GREEN = 2;
    public static final int COLOR_DIM_MAGENTA = 5;
    public static final int COLOR_DIM_RED = 1;
    public static final int COLOR_DIM_WHITE = 7;
    public static final int COLOR_DIM_YELLOW = 3;
    public static final int COLOR_FOREGROUND = -2;
    public static final String COLOR_META_NAME = "name";
    /* access modifiers changed from: private */
    public static final String[] COLOR_META_PATH = {CONTEXT_META_NAME};
    public static final String COLOR_META_VERSION = "version";
    /* access modifiers changed from: private */
    public static final String[] COLOR_PATH = {CONTEXT_META_NAME, CONTEXT_COLOR_NAME};
    public static final String COLOR_PREFIX = "color";
    public static final int COLOR_TYPE_BEGIN = -3;
    public static final int COLOR_TYPE_END = 15;
    public static final String CONTEXT_COLOR_NAME = "colors";
    public static final String CONTEXT_META_NAME = "color-scheme";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private String backgroundColor;
    private Map<Integer, String> color = new LinkedHashMap();
    public String colorName;
    private String colorVersion;
    private String cursorColor;
    private String foregroundColor;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\b\f\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000fXT¢\u0006\u0002\n\u0000R\u0019\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001d¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010!\u001a\u00020\u000fXT¢\u0006\u0002\n\u0000R\u0019\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001d¢\u0006\n\n\u0002\u0010 \u001a\u0004\b#\u0010\u001fR\u000e\u0010$\u001a\u00020\u000fXT¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u000fXT¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u000fXT¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/colorscheme/NeoColorScheme$Companion;", "", "()V", "COLOR_BACKGROUND", "", "COLOR_BRIGHT_BLACK", "COLOR_BRIGHT_BLUE", "COLOR_BRIGHT_CYAN", "COLOR_BRIGHT_GREEN", "COLOR_BRIGHT_MAGENTA", "COLOR_BRIGHT_RED", "COLOR_BRIGHT_WHITE", "COLOR_BRIGHT_YELLOW", "COLOR_CURSOR", "COLOR_DEF_BACKGROUND", "", "COLOR_DEF_CURSOR", "COLOR_DEF_FOREGROUND", "COLOR_DIM_BLACK", "COLOR_DIM_BLUE", "COLOR_DIM_CYAN", "COLOR_DIM_GREEN", "COLOR_DIM_MAGENTA", "COLOR_DIM_RED", "COLOR_DIM_WHITE", "COLOR_DIM_YELLOW", "COLOR_FOREGROUND", "COLOR_META_NAME", "COLOR_META_PATH", "", "getCOLOR_META_PATH", "()[Ljava/lang/String;", "[Ljava/lang/String;", "COLOR_META_VERSION", "COLOR_PATH", "getCOLOR_PATH", "COLOR_PREFIX", "COLOR_TYPE_BEGIN", "COLOR_TYPE_END", "CONTEXT_COLOR_NAME", "CONTEXT_META_NAME", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: NeoColorScheme.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String[] getCOLOR_META_PATH() {
            return NeoColorScheme.COLOR_META_PATH;
        }

        public final String[] getCOLOR_PATH() {
            return NeoColorScheme.COLOR_PATH;
        }
    }

    public final String getColorName() {
        String str = this.colorName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("colorName");
        }
        return str;
    }

    public final void setColorName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.colorName = str;
    }

    public final String getColorVersion() {
        return this.colorVersion;
    }

    public final void setColorVersion(String str) {
        this.colorVersion = str;
    }

    public final String getForegroundColor() {
        return this.foregroundColor;
    }

    public final void setForegroundColor(String str) {
        this.foregroundColor = str;
    }

    public final String getBackgroundColor() {
        return this.backgroundColor;
    }

    public final void setBackgroundColor(String str) {
        this.backgroundColor = str;
    }

    public final String getCursorColor() {
        return this.cursorColor;
    }

    public final void setCursorColor(String str) {
        this.cursorColor = str;
    }

    public final Map<Integer, String> getColor() {
        return this.color;
    }

    public final void setColor(Map<Integer, String> map) {
        Intrinsics.checkParameterIsNotNull(map, "<set-?>");
        this.color = map;
    }

    public final void setColor(int i, String str) {
        Intrinsics.checkParameterIsNotNull(str, COLOR_PREFIX);
        if (i >= 0) {
            this.color.put(Integer.valueOf(i), str);
        } else if (i == -3) {
            this.backgroundColor = str;
        } else if (i == -2) {
            this.foregroundColor = str;
        } else if (i == -1) {
            this.cursorColor = str;
        }
    }

    public final String getColor(int i) {
        validateColors();
        if (i == -3) {
            return this.backgroundColor;
        }
        if (i == -2) {
            return this.foregroundColor;
        }
        if (i == -1) {
            return this.cursorColor;
        }
        return (i >= 0 && this.color.size() > i) ? this.color.get(Integer.valueOf(i)) : "";
    }

    public final NeoColorScheme copy() {
        NeoColorScheme neoColorScheme = new NeoColorScheme();
        String str = this.colorName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("colorName");
        }
        neoColorScheme.colorName = str;
        neoColorScheme.backgroundColor = this.backgroundColor;
        neoColorScheme.foregroundColor = this.foregroundColor;
        neoColorScheme.cursorColor = this.cursorColor;
        for (Map.Entry next : this.color.entrySet()) {
            neoColorScheme.color.put(next.getKey(), next.getValue());
        }
        return neoColorScheme;
    }

    public void onConfigLoaded(ConfigVisitor configVisitor) throws RuntimeException {
        int i;
        Intrinsics.checkParameterIsNotNull(configVisitor, "configVisitor");
        String metaByVisitor = getMetaByVisitor(configVisitor, COLOR_META_NAME);
        if (metaByVisitor != null) {
            this.colorName = metaByVisitor;
            this.colorVersion = getMetaByVisitor(configVisitor, "version");
            this.backgroundColor = getColorByVisitor(configVisitor, COLOR_DEF_BACKGROUND);
            this.foregroundColor = getColorByVisitor(configVisitor, COLOR_DEF_FOREGROUND);
            this.cursorColor = getColorByVisitor(configVisitor, COLOR_DEF_CURSOR);
            for (Map.Entry next : configVisitor.getContext(COLOR_PATH).getAttributes().entrySet()) {
                if (StringsKt.startsWith$default((String) next.getKey(), COLOR_PREFIX, false, 2, (Object) null)) {
                    try {
                        i = Integer.parseInt(StringsKt.substringAfter$default((String) next.getKey(), COLOR_PREFIX, (String) null, 2, (Object) null));
                    } catch (Exception unused) {
                        i = -1;
                    }
                    if (i == -1) {
                        NLog nLog = NLog.INSTANCE;
                        nLog.w("ColorScheme", "Invalid color type: " + ((String) next.getKey()));
                    } else {
                        setColor(i, ((NeoLangValue) next.getValue()).asString());
                    }
                }
            }
            validateColors();
            return;
        }
        throw new RuntimeException("ColorScheme must have a name");
    }

    public final void applyColorScheme$app_release(TerminalView terminalView, ExtraKeysView extraKeysView) {
        validateColors();
        if (terminalView != null) {
            TerminalColorScheme terminalColorScheme = new TerminalColorScheme();
            terminalColorScheme.updateWith(this.foregroundColor, this.backgroundColor, this.cursorColor, this.color);
            TerminalSession currentSession = terminalView.getCurrentSession();
            if (!(currentSession == null || currentSession.getEmulator() == null)) {
                currentSession.getEmulator().setColorScheme(terminalColorScheme);
            }
            terminalView.setBackgroundColor(TerminalColors.parse(this.backgroundColor));
        }
        if (extraKeysView != null) {
            extraKeysView.setBackgroundColor(TerminalColors.parse(this.backgroundColor));
            extraKeysView.setTextColor(TerminalColors.parse(this.foregroundColor));
        }
    }

    public CodeGenerator getCodeGenerator(CodeGenParameter codeGenParameter) {
        Intrinsics.checkParameterIsNotNull(codeGenParameter, "parameter");
        return new NeoColorGenerator(codeGenParameter);
    }

    private final void validateColors() {
        String str = this.backgroundColor;
        if (str == null) {
            str = DefaultColorScheme.INSTANCE.getBackgroundColor();
        }
        this.backgroundColor = str;
        String str2 = this.foregroundColor;
        if (str2 == null) {
            str2 = DefaultColorScheme.INSTANCE.getForegroundColor();
        }
        this.foregroundColor = str2;
        String str3 = this.cursorColor;
        if (str3 == null) {
            str3 = DefaultColorScheme.INSTANCE.getCursorColor();
        }
        this.cursorColor = str3;
    }

    private final String getMetaByVisitor(ConfigVisitor configVisitor, String str) {
        return configVisitor.getStringValue(COLOR_META_PATH, str);
    }

    private final String getColorByVisitor(ConfigVisitor configVisitor, String str) {
        return configVisitor.getStringValue(COLOR_PATH, str);
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
