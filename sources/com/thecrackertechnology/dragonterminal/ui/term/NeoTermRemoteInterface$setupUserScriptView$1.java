package com.thecrackertechnology.dragonterminal.ui.term;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.thecrackertechnology.andrax.R;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\u0010\u0000\u001a\u00020\u00012\u0016\u0010\u0002\u001a\u0012\u0012\u0002\b\u0003 \u0004*\b\u0012\u0002\b\u0003\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\n¢\u0006\u0002\b\u000b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/AdapterView;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "Landroid/view/View;", "position", "", "<anonymous parameter 3>", "", "onItemClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: NeoTermRemoteInterface.kt */
final class NeoTermRemoteInterface$setupUserScriptView$1 implements AdapterView.OnItemClickListener {
    final /* synthetic */ ArrayAdapter $filesAdapter;
    final /* synthetic */ List $filesToHandle;
    final /* synthetic */ NeoTermRemoteInterface this$0;

    NeoTermRemoteInterface$setupUserScriptView$1(NeoTermRemoteInterface neoTermRemoteInterface, List list, ArrayAdapter arrayAdapter) {
        this.this$0 = neoTermRemoteInterface;
        this.$filesToHandle = list;
        this.$filesAdapter = arrayAdapter;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, final int i, long j) {
        new AlertDialog.Builder(this.this$0).setMessage((int) R.string.confirm_remove_file_from_list).setPositiveButton(17039379, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(this) {
            final /* synthetic */ NeoTermRemoteInterface$setupUserScriptView$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                this.this$0.$filesToHandle.remove(i);
                this.this$0.$filesAdapter.notifyDataSetChanged();
            }
        }).setNegativeButton(17039369, (DialogInterface.OnClickListener) null).show();
    }
}
