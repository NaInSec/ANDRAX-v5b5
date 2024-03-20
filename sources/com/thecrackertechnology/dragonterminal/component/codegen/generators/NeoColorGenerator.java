package com.thecrackertechnology.dragonterminal.component.codegen.generators;

import com.thecrackertechnology.dragonterminal.component.codegen.CodeGenParameter;
import com.thecrackertechnology.dragonterminal.component.codegen.interfaces.CodeGenObject;
import com.thecrackertechnology.dragonterminal.component.codegen.interfaces.CodeGenerator;
import com.thecrackertechnology.dragonterminal.component.colorscheme.NeoColorScheme;
import com.thecrackertechnology.dragonterminal.component.config.ConfigureComponent;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u001c\u0010\u000e\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\bj\u0002`\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u001c\u0010\u0011\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\bj\u0002`\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u000bH\u0016J\u0014\u0010\u0013\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0002¨\u0006\u0014"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/codegen/generators/NeoColorGenerator;", "Lcom/thecrackertechnology/dragonterminal/component/codegen/interfaces/CodeGenerator;", "parameter", "Lcom/thecrackertechnology/dragonterminal/component/codegen/CodeGenParameter;", "(Lcom/thecrackertechnology/dragonterminal/component/codegen/CodeGenParameter;)V", "end", "", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "generateCode", "", "codeGenObject", "Lcom/thecrackertechnology/dragonterminal/component/codegen/interfaces/CodeGenObject;", "generateColors", "colorScheme", "Lcom/thecrackertechnology/dragonterminal/component/colorscheme/NeoColorScheme;", "generateMetaData", "getGeneratorName", "start", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoColorGenerator.kt */
public final class NeoColorGenerator extends CodeGenerator {
    public String getGeneratorName() {
        return "NeoColorScheme-Generator";
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NeoColorGenerator(CodeGenParameter codeGenParameter) {
        super(codeGenParameter);
        Intrinsics.checkParameterIsNotNull(codeGenParameter, "parameter");
    }

    public String generateCode(CodeGenObject codeGenObject) {
        Intrinsics.checkParameterIsNotNull(codeGenObject, "codeGenObject");
        if (codeGenObject instanceof NeoColorScheme) {
            StringBuilder sb = new StringBuilder();
            start(sb);
            NeoColorScheme neoColorScheme = (NeoColorScheme) codeGenObject;
            generateMetaData(sb, neoColorScheme);
            generateColors(sb, neoColorScheme);
            end(sb);
            String sb2 = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
            return sb2;
        }
        throw new RuntimeException("Invalid object type, expected NeoColorScheme, got " + codeGenObject.getClass().getSimpleName());
    }

    private final void start(StringBuilder sb) {
        sb.append("color-scheme: {\n");
    }

    private final void end(StringBuilder sb) {
        sb.append("}\n");
    }

    private final void generateMetaData(StringBuilder sb, NeoColorScheme neoColorScheme) {
        ConfigureComponent configureComponent = (ConfigureComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, ConfigureComponent.class, false, 2, (Object) null);
        sb.append("    name: \"" + neoColorScheme.getColorName() + "\"\n");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("    version: ");
        Object colorVersion = neoColorScheme.getColorVersion();
        if (colorVersion == null) {
            colorVersion = Integer.valueOf(configureComponent.getLoaderVersion());
        }
        sb2.append(colorVersion);
        sb2.append(10);
        sb.append(sb2.toString());
        sb.append(StringUtils.LF);
    }

    private final void generateColors(StringBuilder sb, NeoColorScheme neoColorScheme) {
        sb.append("    colors: {\n");
        sb.append("        background: " + neoColorScheme.getBackgroundColor() + 10);
        sb.append("        foreground: " + neoColorScheme.getForegroundColor() + 10);
        sb.append("        cursor: " + neoColorScheme.getCursorColor() + 10);
        for (Map.Entry entry : neoColorScheme.getColor().entrySet()) {
            sb.append("        color" + ((Number) entry.getKey()).intValue() + ": " + ((String) entry.getValue()) + 10);
        }
        sb.append("    }\n");
    }
}
