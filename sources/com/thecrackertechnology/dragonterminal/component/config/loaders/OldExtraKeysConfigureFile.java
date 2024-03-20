package com.thecrackertechnology.dragonterminal.component.config.loaders;

import com.thecrackertechnology.dragonterminal.component.extrakey.NeoExtraKey;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoConfigureFile;
import com.thecrackertechnology.dragonterminal.frontend.logging.NLog;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.button.IExtraButton;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.button.TextButton;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.combine.CombinedSequence;
import io.neolang.runtime.context.NeoLangContext;
import io.neolang.runtime.type.NeoLangValue;
import io.neolang.visitor.ConfigVisitor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/config/loaders/OldExtraKeysConfigureFile;", "Lcom/thecrackertechnology/dragonterminal/frontend/config/NeoConfigureFile;", "configureFile", "Ljava/io/File;", "(Ljava/io/File;)V", "configVisitor", "Lio/neolang/visitor/ConfigVisitor;", "getConfigVisitor", "()Lio/neolang/visitor/ConfigVisitor;", "setConfigVisitor", "(Lio/neolang/visitor/ConfigVisitor;)V", "generateVisitor", "", "config", "Lcom/thecrackertechnology/dragonterminal/component/extrakey/NeoExtraKey;", "parseConfigure", "parseHeader", "", "line", "", "parseKeyDefine", "parseOldConfig", "source", "Ljava/io/BufferedReader;", "parseProgram", "parseWithDefault", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: OldExtraKeysConfigureFile.kt */
public final class OldExtraKeysConfigureFile extends NeoConfigureFile {
    private ConfigVisitor configVisitor;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OldExtraKeysConfigureFile(File file) {
        super(file);
        Intrinsics.checkParameterIsNotNull(file, "configureFile");
    }

    /* access modifiers changed from: protected */
    public ConfigVisitor getConfigVisitor() {
        return this.configVisitor;
    }

    /* access modifiers changed from: protected */
    public void setConfigVisitor(ConfigVisitor configVisitor2) {
        this.configVisitor = configVisitor2;
    }

    public boolean parseConfigure() {
        try {
            return generateVisitor(parseOldConfig(new BufferedReader(new FileReader(getConfigureFile()))));
        } catch (Exception e) {
            NLog nLog = NLog.INSTANCE;
            nLog.e("ConfigureLoader", "Failed to load old extra keys config: " + e.getLocalizedMessage());
            return false;
        }
    }

    private final boolean generateVisitor(NeoExtraKey neoExtraKey) {
        setConfigVisitor(new ConfigVisitor());
        ConfigVisitor configVisitor2 = getConfigVisitor();
        if (configVisitor2 == null) {
            Intrinsics.throwNpe();
        }
        configVisitor2.onStart();
        configVisitor2.onEnterContext(NeoExtraKey.EKS_META_CONTEXT_NAME);
        configVisitor2.getCurrentContext().defineAttribute("version", new NeoLangValue(Integer.valueOf(neoExtraKey.getVersion()))).defineAttribute(NeoExtraKey.EKS_META_WITH_DEFAULT, new NeoLangValue(Boolean.valueOf(neoExtraKey.getWithDefaultKeys())));
        configVisitor2.onEnterContext(NeoExtraKey.EKS_META_PROGRAM);
        int i = 0;
        int i2 = 0;
        for (Object next : neoExtraKey.getProgramNames()) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            configVisitor2.getCurrentContext().defineAttribute(String.valueOf(i2), new NeoLangValue((String) next));
            i2 = i3;
        }
        configVisitor2.onExitContext();
        configVisitor2.onEnterContext(NeoExtraKey.EKS_META_KEY);
        for (Object next2 : neoExtraKey.getShortcutKeys()) {
            int i4 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            IExtraButton iExtraButton = (IExtraButton) next2;
            if (iExtraButton instanceof TextButton) {
                configVisitor2.onEnterContext(String.valueOf(i));
                NeoLangContext defineAttribute = configVisitor2.getCurrentContext().defineAttribute(NeoExtraKey.EKS_META_WITH_ENTER, new NeoLangValue(Boolean.valueOf(((TextButton) iExtraButton).getWithEnter())));
                CombinedSequence buttonKeys = iExtraButton.getButtonKeys();
                if (buttonKeys == null) {
                    Intrinsics.throwNpe();
                }
                NeoLangContext defineAttribute2 = defineAttribute.defineAttribute(NeoExtraKey.EKS_META_DISPLAY, new NeoLangValue(buttonKeys));
                CombinedSequence buttonKeys2 = iExtraButton.getButtonKeys();
                if (buttonKeys2 == null) {
                    Intrinsics.throwNpe();
                }
                defineAttribute2.defineAttribute(NeoExtraKey.EKS_META_CODE, new NeoLangValue(buttonKeys2));
                configVisitor2.onExitContext();
            }
            i = i4;
        }
        configVisitor2.onExitContext();
        configVisitor2.onFinish();
        return true;
    }

    private final NeoExtraKey parseOldConfig(BufferedReader bufferedReader) {
        NeoExtraKey neoExtraKey = new NeoExtraKey();
        String readLine = bufferedReader.readLine();
        while (readLine != null) {
            String obj = StringsKt.trim((CharSequence) readLine).toString();
            if (obj != null) {
                String obj2 = StringsKt.trimEnd((CharSequence) obj).toString();
                if ((obj2.length() == 0) || StringsKt.startsWith$default(obj2, "#", false, 2, (Object) null)) {
                    readLine = bufferedReader.readLine();
                } else {
                    if (StringsKt.startsWith$default(obj2, "version", false, 2, (Object) null)) {
                        parseHeader(obj2, neoExtraKey);
                    } else if (StringsKt.startsWith$default(obj2, NeoExtraKey.EKS_META_PROGRAM, false, 2, (Object) null)) {
                        parseProgram(obj2, neoExtraKey);
                    } else if (StringsKt.startsWith$default(obj2, "define", false, 2, (Object) null)) {
                        parseKeyDefine(obj2, neoExtraKey);
                    } else if (StringsKt.startsWith$default(obj2, NeoExtraKey.EKS_META_WITH_DEFAULT, false, 2, (Object) null)) {
                        parseWithDefault(obj2, neoExtraKey);
                    }
                    readLine = bufferedReader.readLine();
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
        }
        if (neoExtraKey.getVersion() < 0) {
            throw new RuntimeException("Not a valid shortcut config file");
        } else if (neoExtraKey.getProgramNames().size() != 0) {
            return neoExtraKey;
        } else {
            throw new RuntimeException("At least one program name should be given");
        }
    }

    private final void parseWithDefault(String str, NeoExtraKey neoExtraKey) {
        if (str != null) {
            String substring = str.substring(12);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            if (substring != null) {
                String obj = StringsKt.trim((CharSequence) substring).toString();
                if (obj != null) {
                    neoExtraKey.setWithDefaultKeys(Intrinsics.areEqual((Object) StringsKt.trimEnd((CharSequence) obj).toString(), (Object) "true"));
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private final void parseKeyDefine(String str, NeoExtraKey neoExtraKey) {
        if (str != null) {
            String substring = str.substring(6);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            if (substring != null) {
                String obj = StringsKt.trim((CharSequence) substring).toString();
                if (obj != null) {
                    List split$default = StringsKt.split$default((CharSequence) StringsKt.trimEnd((CharSequence) obj).toString(), new String[]{StringUtils.SPACE}, false, 0, 6, (Object) null);
                    if (split$default.size() >= 2) {
                        neoExtraKey.getShortcutKeys().add(new TextButton((String) split$default.get(0), Intrinsics.areEqual((Object) (String) split$default.get(1), (Object) "true")));
                        return;
                    }
                    throw new RuntimeException("Bad define");
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private final void parseProgram(String str, NeoExtraKey neoExtraKey) {
        if (str != null) {
            String substring = str.substring(7);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            if (substring != null) {
                String obj = StringsKt.trim((CharSequence) substring).toString();
                if (obj != null) {
                    CharSequence obj2 = StringsKt.trimEnd((CharSequence) obj).toString();
                    if (!(obj2.length() == 0)) {
                        for (String add : StringsKt.split$default(obj2, new String[]{StringUtils.SPACE}, false, 0, 6, (Object) null)) {
                            neoExtraKey.getProgramNames().add(add);
                        }
                        return;
                    }
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private final void parseHeader(String str, NeoExtraKey neoExtraKey) {
        if (str != null) {
            String substring = str.substring(7);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            if (substring != null) {
                String obj = StringsKt.trim((CharSequence) substring).toString();
                if (obj != null) {
                    String obj2 = StringsKt.trimEnd((CharSequence) obj).toString();
                    try {
                        neoExtraKey.setVersion(Integer.parseInt(obj2));
                    } catch (NumberFormatException unused) {
                        throw new RuntimeException("Bad version '" + obj2 + '\'');
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
        } else {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
    }
}
