package com.thecrackertechnology.dragonterminal.component.font;

import android.graphics.Typeface;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.ExtraKeysView;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J!\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0000¢\u0006\u0002\b\u000eJ\n\u0010\u000f\u001a\u0004\u0018\u00010\u0006H\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/font/NeoFont;", "", "fontFile", "Ljava/io/File;", "(Ljava/io/File;)V", "typeface", "Landroid/graphics/Typeface;", "(Landroid/graphics/Typeface;)V", "applyFont", "", "terminalView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "extraKeysView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView;", "applyFont$app_release", "getTypeFace", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoFont.kt */
public final class NeoFont {
    private File fontFile;
    private Typeface typeface;

    public NeoFont(File file) {
        Intrinsics.checkParameterIsNotNull(file, "fontFile");
        this.fontFile = file;
    }

    public NeoFont(Typeface typeface2) {
        Intrinsics.checkParameterIsNotNull(typeface2, "typeface");
        this.typeface = typeface2;
    }

    public final void applyFont$app_release(TerminalView terminalView, ExtraKeysView extraKeysView) {
        Typeface typeFace = getTypeFace();
        if (terminalView != null) {
            terminalView.setTypeface(typeFace);
        }
        if (extraKeysView != null) {
            extraKeysView.setTypeface(typeFace);
        }
    }

    private final Typeface getTypeFace() {
        if (this.typeface == null && this.fontFile == null) {
            return null;
        }
        if (this.typeface == null) {
            this.typeface = Typeface.createFromFile(this.fontFile);
        }
        return this.typeface;
    }
}
