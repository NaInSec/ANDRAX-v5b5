package com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.combine.CombinedSequence;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J$\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/TextButton;", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/IExtraButton;", "text", "", "withEnter", "", "(Ljava/lang/String;Z)V", "getWithEnter", "()Z", "makeButton", "Landroid/widget/Button;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "onClick", "", "view", "Landroid/view/View;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: TextButton.kt */
public class TextButton extends IExtraButton {
    private final boolean withEnter;

    public TextButton(String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "text");
        this.withEnter = z;
        setButtonKeys(CombinedSequence.Companion.solveString(str));
        setDisplayText(str);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TextButton(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? false : z);
    }

    public final boolean getWithEnter() {
        return this.withEnter;
    }

    public void onClick(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        CombinedSequence buttonKeys = getButtonKeys();
        if (buttonKeys == null) {
            Intrinsics.throwNpe();
        }
        for (String sendKey : buttonKeys.getKeys()) {
            IExtraButton.Companion.sendKey(view, sendKey);
        }
        if (this.withEnter) {
            IExtraButton.Companion.sendKey(view, StringUtils.LF);
        }
    }

    public Button makeButton(Context context, AttributeSet attributeSet, int i) {
        return new Button(context, attributeSet, i);
    }
}
