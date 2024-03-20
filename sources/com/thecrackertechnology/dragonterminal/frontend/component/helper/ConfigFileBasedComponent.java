package com.thecrackertechnology.dragonterminal.frontend.component.helper;

import com.thecrackertechnology.dragonterminal.component.config.ConfigureComponent;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.component.NeoComponent;
import com.thecrackertechnology.dragonterminal.frontend.component.helper.ConfigFileBasedObject;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoConfigureFile;
import com.thecrackertechnology.dragonterminal.frontend.logging.NLog;
import io.neolang.visitor.ConfigVisitor;
import java.io.File;
import java.io.FileFilter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000 \u001a*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003:\u0001\u001aB\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\r\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\b\u0010\u0011\u001a\u00020\u0012H&J\u0015\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00020\u0015H&¢\u0006\u0002\u0010\u0016J\b\u0010\u0017\u001a\u00020\u0012H\u0016J\b\u0010\u0018\u001a\u00020\u0012H\u0016J\b\u0010\u0019\u001a\u00020\u0012H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nXD¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001b"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/component/helper/ConfigFileBasedComponent;", "T", "Lcom/thecrackertechnology/dragonterminal/frontend/component/helper/ConfigFileBasedObject;", "Lcom/thecrackertechnology/dragonterminal/frontend/component/NeoComponent;", "baseDir", "", "(Ljava/lang/String;)V", "getBaseDir", "()Ljava/lang/String;", "checkComponentFileWhenObtained", "", "getCheckComponentFileWhenObtained", "()Z", "loadConfigure", "file", "Ljava/io/File;", "(Ljava/io/File;)Lcom/thecrackertechnology/dragonterminal/frontend/component/helper/ConfigFileBasedObject;", "onCheckComponentFiles", "", "onCreateComponentObject", "configVisitor", "Lio/neolang/visitor/ConfigVisitor;", "(Lio/neolang/visitor/ConfigVisitor;)Lcom/thecrackertechnology/dragonterminal/frontend/component/helper/ConfigFileBasedObject;", "onServiceDestroy", "onServiceInit", "onServiceObtained", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ConfigFileBasedComponent.kt */
public abstract class ConfigFileBasedComponent<T extends ConfigFileBasedObject> implements NeoComponent {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final FileFilter NEOLANG_FILTER = ConfigFileBasedComponent$Companion$NEOLANG_FILTER$1.INSTANCE;
    private static final String TAG = ConfigFileBasedComponent.class.getSimpleName();
    private final String baseDir;
    private final boolean checkComponentFileWhenObtained;

    public abstract void onCheckComponentFiles();

    public abstract T onCreateComponentObject(ConfigVisitor configVisitor);

    public void onServiceDestroy() {
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/component/helper/ConfigFileBasedComponent$Companion;", "", "()V", "NEOLANG_FILTER", "Ljava/io/FileFilter;", "getNEOLANG_FILTER", "()Ljava/io/FileFilter;", "TAG", "", "kotlin.jvm.PlatformType", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: ConfigFileBasedComponent.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FileFilter getNEOLANG_FILTER() {
            return ConfigFileBasedComponent.NEOLANG_FILTER;
        }
    }

    public ConfigFileBasedComponent(String str) {
        Intrinsics.checkParameterIsNotNull(str, "baseDir");
        this.baseDir = str;
    }

    /* access modifiers changed from: protected */
    public final String getBaseDir() {
        return this.baseDir;
    }

    public boolean getCheckComponentFileWhenObtained() {
        return this.checkComponentFileWhenObtained;
    }

    public void onServiceInit() {
        File file = new File(this.baseDir);
        if (file.exists() || file.mkdirs()) {
            onCheckComponentFiles();
            return;
        }
        throw new RuntimeException("Cannot create component config directory: " + file.getAbsolutePath());
    }

    public void onServiceObtained() {
        if (getCheckComponentFileWhenObtained()) {
            onCheckComponentFiles();
        }
    }

    public final T loadConfigure(File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        try {
            NeoConfigureFile loadConfigure = ((ConfigureComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, ConfigureComponent.class, false, 2, (Object) null)).newLoader(file).loadConfigure();
            if (loadConfigure != null) {
                ConfigVisitor visitor = loadConfigure.getVisitor();
                T onCreateComponentObject = onCreateComponentObject(visitor);
                onCreateComponentObject.onConfigLoaded(visitor);
                return onCreateComponentObject;
            }
            throw new RuntimeException("Parse configuration failed.");
        } catch (RuntimeException e) {
            NLog nLog = NLog.INSTANCE;
            String str = TAG;
            Intrinsics.checkExpressionValueIsNotNull(str, "TAG");
            nLog.e(str, "Failed to load config: " + file.getAbsolutePath() + ": " + e.getLocalizedMessage());
            return null;
        }
    }
}
