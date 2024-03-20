package com.thecrackertechnology.dragonterminal.ui.customize;

import android.widget.AdapterView;
import com.thecrackertechnology.dragonterminal.component.font.FontComponent;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005H\u0016¨\u0006\r"}, d2 = {"com/thecrackertechnology/dragonterminal/ui/customize/CustomizeActivity$setupSpinners$1", "Landroid/widget/AdapterView$OnItemSelectedListener;", "onItemSelected", "", "parent", "Landroid/widget/AdapterView;", "view", "Landroid/view/View;", "position", "", "id", "", "onNothingSelected", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: CustomizeActivity.kt */
public final class CustomizeActivity$setupSpinners$1 implements AdapterView.OnItemSelectedListener {
    final /* synthetic */ FontComponent $fontComponent;
    final /* synthetic */ CustomizeActivity this$0;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    CustomizeActivity$setupSpinners$1(CustomizeActivity customizeActivity, FontComponent fontComponent) {
        this.this$0 = customizeActivity;
        this.$fontComponent = fontComponent;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.AdapterView<?>, android.widget.AdapterView] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onItemSelected(android.widget.AdapterView<?> r1, android.view.View r2, int r3, long r4) {
        /*
            r0 = this;
            if (r1 != 0) goto L_0x0005
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0005:
            android.widget.Adapter r1 = r1.getAdapter()
            if (r1 != 0) goto L_0x000e
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x000e:
            java.lang.Object r1 = r1.getItem(r3)
            if (r1 == 0) goto L_0x0033
            java.lang.String r1 = (java.lang.String) r1
            com.thecrackertechnology.dragonterminal.component.font.FontComponent r2 = r0.$fontComponent
            com.thecrackertechnology.dragonterminal.component.font.NeoFont r2 = r2.getFont(r1)
            com.thecrackertechnology.dragonterminal.component.font.FontComponent r3 = r0.$fontComponent
            com.thecrackertechnology.dragonterminal.ui.customize.CustomizeActivity r4 = r0.this$0
            com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView r4 = r4.getTerminalView()
            com.thecrackertechnology.dragonterminal.ui.customize.CustomizeActivity r5 = r0.this$0
            com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.ExtraKeysView r5 = r5.getExtraKeysView()
            r3.applyFont(r4, r5, r2)
            com.thecrackertechnology.dragonterminal.component.font.FontComponent r2 = r0.$fontComponent
            r2.setCurrentFont(r1)
            return
        L_0x0033:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            java.lang.String r2 = "null cannot be cast to non-null type kotlin.String"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.ui.customize.CustomizeActivity$setupSpinners$1.onItemSelected(android.widget.AdapterView, android.view.View, int, long):void");
    }
}
