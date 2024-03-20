package io.neolang.main;

import io.neolang.ast.base.NeoLangAst;
import io.neolang.ast.visitor.AstVisitor;
import io.neolang.parser.NeoLangParser;
import io.neolang.visitor.DisplayProcessVisitor;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lio/neolang/main/Main;", "", "()V", "Companion", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: Main.kt */
public final class Main {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @JvmStatic
    public static final void main(String[] strArr) {
        Companion.main(strArr);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H\u0002¨\u0006\u000b"}, d2 = {"Lio/neolang/main/Main$Companion;", "", "()V", "main", "", "args", "", "", "([Ljava/lang/String;)V", "readFully", "file", "NeoLang"}, k = 1, mv = {1, 1, 15})
    /* compiled from: Main.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void main(String[] strArr) {
            Intrinsics.checkParameterIsNotNull(strArr, "args");
            if (strArr.length == 0) {
                System.out.println("Usage: NeoLang <program.nl>");
                return;
            }
            NeoLangParser neoLangParser = new NeoLangParser();
            for (String str : strArr) {
                neoLangParser.setInputSource(Main.Companion.readFully(str));
                NeoLangAst parse = neoLangParser.parse();
                System.out.println("Compile `" + str + '\'');
                AstVisitor visitor = parse.visit().getVisitor(DisplayProcessVisitor.class);
                if (visitor != null) {
                    visitor.start();
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0027, code lost:
            throw r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
            kotlin.io.CloseableKt.closeFinally(r0, r5);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final java.lang.String readFully(java.lang.String r5) {
            /*
                r4 = this;
                java.io.FileInputStream r0 = new java.io.FileInputStream
                r0.<init>(r5)
                java.io.Closeable r0 = (java.io.Closeable) r0
                r5 = 0
                java.lang.Throwable r5 = (java.lang.Throwable) r5
                r1 = r0
                java.io.FileInputStream r1 = (java.io.FileInputStream) r1     // Catch:{ all -> 0x0021 }
                int r2 = r1.available()     // Catch:{ all -> 0x0021 }
                byte[] r2 = new byte[r2]     // Catch:{ all -> 0x0021 }
                r1.read(r2)     // Catch:{ all -> 0x0021 }
                java.lang.String r1 = new java.lang.String     // Catch:{ all -> 0x0021 }
                java.nio.charset.Charset r3 = kotlin.text.Charsets.UTF_8     // Catch:{ all -> 0x0021 }
                r1.<init>(r2, r3)     // Catch:{ all -> 0x0021 }
                kotlin.io.CloseableKt.closeFinally(r0, r5)
                return r1
            L_0x0021:
                r5 = move-exception
                throw r5     // Catch:{ all -> 0x0023 }
            L_0x0023:
                r1 = move-exception
                kotlin.io.CloseableKt.closeFinally(r0, r5)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.neolang.main.Main.Companion.readFully(java.lang.String):java.lang.String");
        }
    }
}
