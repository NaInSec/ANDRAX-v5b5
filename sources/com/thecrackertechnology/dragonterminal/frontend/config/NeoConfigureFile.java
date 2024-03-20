package com.thecrackertechnology.dragonterminal.frontend.config;

import com.thecrackertechnology.dragonterminal.frontend.logging.NLog;
import com.thecrackertechnology.dragonterminal.utils.FileUtils;
import io.neolang.ast.visitor.AstVisitor;
import io.neolang.parser.NeoLangParser;
import io.neolang.visitor.ConfigVisitor;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0006\u0010\u0011\u001a\u00020\bJ\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0014"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/config/NeoConfigureFile;", "", "configureFile", "Ljava/io/File;", "(Ljava/io/File;)V", "configParser", "Lio/neolang/parser/NeoLangParser;", "configVisitor", "Lio/neolang/visitor/ConfigVisitor;", "getConfigVisitor", "()Lio/neolang/visitor/ConfigVisitor;", "setConfigVisitor", "(Lio/neolang/visitor/ConfigVisitor;)V", "getConfigureFile", "()Ljava/io/File;", "checkParsed", "", "getVisitor", "parseConfigure", "", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoConfigureFile.kt */
public class NeoConfigureFile {
    private final NeoLangParser configParser = new NeoLangParser();
    private ConfigVisitor configVisitor;
    private final File configureFile;

    public NeoConfigureFile(File file) {
        Intrinsics.checkParameterIsNotNull(file, "configureFile");
        this.configureFile = file;
    }

    public final File getConfigureFile() {
        return this.configureFile;
    }

    /* access modifiers changed from: protected */
    public ConfigVisitor getConfigVisitor() {
        return this.configVisitor;
    }

    /* access modifiers changed from: protected */
    public void setConfigVisitor(ConfigVisitor configVisitor2) {
        this.configVisitor = configVisitor2;
    }

    public final ConfigVisitor getVisitor() {
        checkParsed();
        ConfigVisitor configVisitor2 = getConfigVisitor();
        if (configVisitor2 == null) {
            Intrinsics.throwNpe();
        }
        return configVisitor2;
    }

    public boolean parseConfigure() {
        byte[] readFile = FileUtils.INSTANCE.readFile(this.configureFile);
        if (readFile == null) {
            NLog nLog = NLog.INSTANCE;
            nLog.e("ConfigureFile", "Cannot read file " + this.configureFile);
            return false;
        }
        this.configParser.setInputSource(new String(readFile, Charsets.UTF_8));
        AstVisitor visitor = this.configParser.parse().visit().getVisitor(ConfigVisitor.class);
        if (visitor == null) {
            return false;
        }
        visitor.start();
        setConfigVisitor((ConfigVisitor) visitor.getCallback());
        return true;
    }

    private final void checkParsed() {
        if (getConfigVisitor() == null) {
            throw new IllegalStateException("Configure file not loaded or parse failed.");
        }
    }
}
