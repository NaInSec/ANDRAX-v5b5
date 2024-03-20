package com.thecrackertechnology.dragonterminal.component.config.loaders;

import com.thecrackertechnology.dragonterminal.frontend.config.NeoConfigureFile;
import io.neolang.visitor.ConfigVisitor;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/config/loaders/OldColorSchemeConfigureFile;", "Lcom/thecrackertechnology/dragonterminal/frontend/config/NeoConfigureFile;", "configureFile", "Ljava/io/File;", "(Ljava/io/File;)V", "configVisitor", "Lio/neolang/visitor/ConfigVisitor;", "getConfigVisitor", "()Lio/neolang/visitor/ConfigVisitor;", "setConfigVisitor", "(Lio/neolang/visitor/ConfigVisitor;)V", "parseConfigure", "", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: OldColorSchemeConfigureFile.kt */
public final class OldColorSchemeConfigureFile extends NeoConfigureFile {
    private ConfigVisitor configVisitor;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OldColorSchemeConfigureFile(File file) {
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

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r3, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a3, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean parseConfigure() {
        /*
            r11 = this;
            r0 = 1
            r1 = 0
            io.neolang.visitor.ConfigVisitor r2 = new io.neolang.visitor.ConfigVisitor     // Catch:{ Exception -> 0x00a4 }
            r2.<init>()     // Catch:{ Exception -> 0x00a4 }
            r2.onStart()     // Catch:{ Exception -> 0x00a4 }
            java.lang.String r3 = "color-scheme"
            r2.onEnterContext(r3)     // Catch:{ Exception -> 0x00a4 }
            io.neolang.runtime.context.NeoLangContext r3 = r2.getCurrentContext()     // Catch:{ Exception -> 0x00a4 }
            java.lang.String r4 = "name"
            io.neolang.runtime.type.NeoLangValue r5 = new io.neolang.runtime.type.NeoLangValue     // Catch:{ Exception -> 0x00a4 }
            java.io.File r6 = r11.getConfigureFile()     // Catch:{ Exception -> 0x00a4 }
            java.lang.String r6 = kotlin.io.FilesKt.getNameWithoutExtension(r6)     // Catch:{ Exception -> 0x00a4 }
            r5.<init>(r6)     // Catch:{ Exception -> 0x00a4 }
            io.neolang.runtime.context.NeoLangContext r3 = r3.defineAttribute(r4, r5)     // Catch:{ Exception -> 0x00a4 }
            java.lang.String r4 = "version"
            io.neolang.runtime.type.NeoLangValue r5 = new io.neolang.runtime.type.NeoLangValue     // Catch:{ Exception -> 0x00a4 }
            java.lang.String r6 = "1.0"
            r5.<init>(r6)     // Catch:{ Exception -> 0x00a4 }
            r3.defineAttribute(r4, r5)     // Catch:{ Exception -> 0x00a4 }
            java.lang.String r3 = "colors"
            r2.onEnterContext(r3)     // Catch:{ Exception -> 0x00a4 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00a4 }
            java.io.File r4 = r11.getConfigureFile()     // Catch:{ Exception -> 0x00a4 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x00a4 }
            java.io.Closeable r3 = (java.io.Closeable) r3     // Catch:{ Exception -> 0x00a4 }
            r4 = r1
            java.lang.Throwable r4 = (java.lang.Throwable) r4     // Catch:{ Exception -> 0x00a4 }
            r5 = r3
            java.io.FileInputStream r5 = (java.io.FileInputStream) r5     // Catch:{ all -> 0x009d }
            java.util.Properties r6 = new java.util.Properties     // Catch:{ all -> 0x009d }
            r6.<init>()     // Catch:{ all -> 0x009d }
            java.io.InputStream r5 = (java.io.InputStream) r5     // Catch:{ all -> 0x009d }
            r6.load(r5)     // Catch:{ all -> 0x009d }
            java.util.Map r6 = (java.util.Map) r6     // Catch:{ all -> 0x009d }
            java.util.Set r5 = r6.entrySet()     // Catch:{ all -> 0x009d }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x009d }
        L_0x005c:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x009d }
            if (r6 == 0) goto L_0x0093
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x009d }
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6     // Catch:{ all -> 0x009d }
            io.neolang.runtime.context.NeoLangContext r7 = r2.getCurrentContext()     // Catch:{ all -> 0x009d }
            java.lang.Object r8 = r6.getKey()     // Catch:{ all -> 0x009d }
            java.lang.String r9 = "null cannot be cast to non-null type kotlin.String"
            if (r8 == 0) goto L_0x008d
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x009d }
            io.neolang.runtime.type.NeoLangValue r10 = new io.neolang.runtime.type.NeoLangValue     // Catch:{ all -> 0x009d }
            java.lang.Object r6 = r6.getValue()     // Catch:{ all -> 0x009d }
            if (r6 == 0) goto L_0x0087
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x009d }
            r10.<init>(r6)     // Catch:{ all -> 0x009d }
            r7.defineAttribute(r8, r10)     // Catch:{ all -> 0x009d }
            goto L_0x005c
        L_0x0087:
            kotlin.TypeCastException r2 = new kotlin.TypeCastException     // Catch:{ all -> 0x009d }
            r2.<init>(r9)     // Catch:{ all -> 0x009d }
            throw r2     // Catch:{ all -> 0x009d }
        L_0x008d:
            kotlin.TypeCastException r2 = new kotlin.TypeCastException     // Catch:{ all -> 0x009d }
            r2.<init>(r9)     // Catch:{ all -> 0x009d }
            throw r2     // Catch:{ all -> 0x009d }
        L_0x0093:
            r2.onFinish()     // Catch:{ all -> 0x009d }
            r11.setConfigVisitor(r2)     // Catch:{ all -> 0x009d }
            kotlin.io.CloseableKt.closeFinally(r3, r4)     // Catch:{ Exception -> 0x00a4 }
            return r0
        L_0x009d:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x009f }
        L_0x009f:
            r4 = move-exception
            kotlin.io.CloseableKt.closeFinally(r3, r2)     // Catch:{ Exception -> 0x00a4 }
            throw r4     // Catch:{ Exception -> 0x00a4 }
        L_0x00a4:
            r2 = move-exception
            io.neolang.visitor.ConfigVisitor r1 = (io.neolang.visitor.ConfigVisitor) r1
            r11.setConfigVisitor(r1)
            com.thecrackertechnology.dragonterminal.frontend.logging.NLog r1 = com.thecrackertechnology.dragonterminal.frontend.logging.NLog.INSTANCE
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = 0
            java.lang.String r5 = "Error while loading old config"
            r3[r4] = r5
            r3[r0] = r2
            java.lang.String r0 = "ConfigureLoader"
            r1.e(r0, r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.component.config.loaders.OldColorSchemeConfigureFile.parseConfigure():boolean");
    }
}
