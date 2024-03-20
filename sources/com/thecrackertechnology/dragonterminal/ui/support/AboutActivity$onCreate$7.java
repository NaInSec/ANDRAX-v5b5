package com.thecrackertechnology.dragonterminal.ui.support;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import com.thecrackertechnology.andrax.R;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: AboutActivity.kt */
final class AboutActivity$onCreate$7 implements View.OnClickListener {
    final /* synthetic */ AboutActivity this$0;

    AboutActivity$onCreate$7(AboutActivity aboutActivity) {
        this.this$0 = aboutActivity;
    }

    public final void onClick(View view) {
        new AlertDialog.Builder(this.this$0).setMessage((int) R.string.reset_app_warning).setPositiveButton((int) R.string.yes, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(this) {
            final /* synthetic */ AboutActivity$onCreate$7 this$0;

            {
                this.this$0 = r1;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                this.this$0.this$0.resetApp();
            }
        }).setNegativeButton(17039369, (DialogInterface.OnClickListener) null).show();
    }
}
