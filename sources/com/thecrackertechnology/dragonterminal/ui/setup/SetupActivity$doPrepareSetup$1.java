package com.thecrackertechnology.dragonterminal.ui.setup;

import android.app.ProgressDialog;
import android.content.Context;
import com.thecrackertechnology.dragonterminal.setup.SetupHelper;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: SetupActivity.kt */
final class SetupActivity$doPrepareSetup$1 implements Runnable {
    final /* synthetic */ ProgressDialog $dialog;
    final /* synthetic */ int $id;
    final /* synthetic */ SetupActivity this$0;

    SetupActivity$doPrepareSetup$1(SetupActivity setupActivity, int i, ProgressDialog progressDialog) {
        this.this$0 = setupActivity;
        this.$id = i;
        this.$dialog = progressDialog;
    }

    public final void run() {
        SetupActivity setupActivity = this.this$0;
        final String access$validateParameter = setupActivity.validateParameter(this.$id, setupActivity.setupParameter);
        this.this$0.runOnUiThread(new Runnable(this) {
            final /* synthetic */ SetupActivity$doPrepareSetup$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void run() {
                this.this$0.$dialog.dismiss();
                if (access$validateParameter != null) {
                    SetupHelper.INSTANCE.makeErrorDialog((Context) this.this$0.this$0, access$validateParameter).show();
                    return;
                }
                this.this$0.this$0.showConfirmDialog(this.this$0.this$0.createSourceConnection(this.this$0.$id, this.this$0.this$0.setupParameter, this.this$0.this$0.setupParameterUri));
            }
        });
    }
}
