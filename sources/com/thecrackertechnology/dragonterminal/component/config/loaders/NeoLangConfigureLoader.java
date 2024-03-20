package com.thecrackertechnology.dragonterminal.component.config.loaders;

import com.thecrackertechnology.dragonterminal.component.config.IConfigureLoader;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoConfigureFile;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/config/loaders/NeoLangConfigureLoader;", "Lcom/thecrackertechnology/dragonterminal/component/config/IConfigureLoader;", "configFile", "Ljava/io/File;", "(Ljava/io/File;)V", "loadConfigure", "Lcom/thecrackertechnology/dragonterminal/frontend/config/NeoConfigureFile;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangConfigureLoader.kt */
public final class NeoLangConfigureLoader implements IConfigureLoader {
    private final File configFile;

    public NeoLangConfigureLoader(File file) {
        Intrinsics.checkParameterIsNotNull(file, "configFile");
        this.configFile = file;
    }

    public NeoConfigureFile loadConfigure() {
        NeoConfigureFile neoConfigureFile = new NeoConfigureFile(this.configFile);
        if (neoConfigureFile.parseConfigure()) {
            return neoConfigureFile;
        }
        return null;
    }
}
