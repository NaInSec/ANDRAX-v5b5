package com.thecrackertechnology.dragonterminal.ui.term;

import android.view.View;
import android.widget.AdapterView;
import com.thecrackertechnology.dragonterminal.component.userscript.UserScript;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\u0010\u0000\u001a\u00020\u00012\u0016\u0010\u0002\u001a\u0012\u0012\u0002\b\u0003 \u0004*\b\u0012\u0002\b\u0003\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\n¢\u0006\u0002\b\u000b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/AdapterView;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "Landroid/view/View;", "position", "", "<anonymous parameter 3>", "", "onItemClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: NeoTermRemoteInterface.kt */
final class NeoTermRemoteInterface$setupUserScriptView$3 implements AdapterView.OnItemClickListener {
    final /* synthetic */ List $filesToHandle;
    final /* synthetic */ List $userScripts;
    final /* synthetic */ NeoTermRemoteInterface this$0;

    NeoTermRemoteInterface$setupUserScriptView$3(NeoTermRemoteInterface neoTermRemoteInterface, List list, List list2) {
        this.this$0 = neoTermRemoteInterface;
        this.$userScripts = list;
        this.$filesToHandle = list2;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        UserScript userScript = (UserScript) this.$userScripts.get(i);
        String absolutePath = userScript.getScriptFile().getAbsolutePath();
        NeoTermRemoteInterface neoTermRemoteInterface = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(absolutePath, "userScriptPath");
        this.this$0.openCustomExecTerm(absolutePath, neoTermRemoteInterface.buildUserScriptArgument(absolutePath, this.$filesToHandle), userScript.getScriptFile().getParent());
        this.this$0.finish();
    }
}
