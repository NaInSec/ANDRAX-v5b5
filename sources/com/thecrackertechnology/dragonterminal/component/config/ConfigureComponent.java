package com.thecrackertechnology.dragonterminal.component.config;

import com.thecrackertechnology.dragonterminal.component.config.loaders.NeoLangConfigureLoader;
import com.thecrackertechnology.dragonterminal.component.config.loaders.OldConfigureLoader;
import com.thecrackertechnology.dragonterminal.frontend.component.NeoComponent;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u000f\u001a\u00020\rH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/config/ConfigureComponent;", "Lcom/thecrackertechnology/dragonterminal/frontend/component/NeoComponent;", "()V", "CONFIG_LOADER_VERSION", "", "getCONFIG_LOADER_VERSION", "()I", "getLoaderVersion", "newLoader", "Lcom/thecrackertechnology/dragonterminal/component/config/IConfigureLoader;", "configFile", "Ljava/io/File;", "onServiceDestroy", "", "onServiceInit", "onServiceObtained", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ConfigureComponent.kt */
public final class ConfigureComponent implements NeoComponent {
    private final int CONFIG_LOADER_VERSION = 20;

    public void onServiceDestroy() {
    }

    public void onServiceInit() {
    }

    public void onServiceObtained() {
    }

    public final int getCONFIG_LOADER_VERSION() {
        return this.CONFIG_LOADER_VERSION;
    }

    public final int getLoaderVersion() {
        return this.CONFIG_LOADER_VERSION;
    }

    public final IConfigureLoader newLoader(File file) {
        Intrinsics.checkParameterIsNotNull(file, "configFile");
        String extension = FilesKt.getExtension(file);
        if (extension.hashCode() == 3518 && extension.equals("nl")) {
            return new NeoLangConfigureLoader(file);
        }
        return new OldConfigureLoader(file);
    }
}
