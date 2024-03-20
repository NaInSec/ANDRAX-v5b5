package com.thecrackertechnology.dragonterminal.component.config.loaders;

import com.thecrackertechnology.dragonterminal.component.colorscheme.NeoColorScheme;
import com.thecrackertechnology.dragonterminal.component.config.IConfigureLoader;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoConfigureFile;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/config/loaders/OldConfigureLoader;", "Lcom/thecrackertechnology/dragonterminal/component/config/IConfigureLoader;", "configFile", "Ljava/io/File;", "(Ljava/io/File;)V", "loadConfigure", "Lcom/thecrackertechnology/dragonterminal/frontend/config/NeoConfigureFile;", "returnConfigure", "configureFile", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: OldConfigureLoader.kt */
public final class OldConfigureLoader implements IConfigureLoader {
    private final File configFile;

    public OldConfigureLoader(File file) {
        Intrinsics.checkParameterIsNotNull(file, "configFile");
        this.configFile = file;
    }

    public NeoConfigureFile loadConfigure() {
        String extension = FilesKt.getExtension(this.configFile);
        int hashCode = extension.hashCode();
        if (hashCode != 100493) {
            if (hashCode == 94842723 && extension.equals(NeoColorScheme.COLOR_PREFIX)) {
                return returnConfigure(new OldColorSchemeConfigureFile(this.configFile));
            }
        } else if (extension.equals("eks")) {
            return returnConfigure(new OldExtraKeysConfigureFile(this.configFile));
        }
        return null;
    }

    private final NeoConfigureFile returnConfigure(NeoConfigureFile neoConfigureFile) {
        if (neoConfigureFile.parseConfigure()) {
            return neoConfigureFile;
        }
        return null;
    }
}
