package com.thecrackertechnology.dragonterminal.ui.customize;

import android.content.Intent;
import android.view.View;
import com.thecrackertechnology.andrax.R;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: CustomizeActivity.kt */
final class CustomizeActivity$onCreate$2 implements View.OnClickListener {
    final /* synthetic */ CustomizeActivity this$0;

    CustomizeActivity$onCreate$2(CustomizeActivity customizeActivity) {
        this.this$0 = customizeActivity;
    }

    public final void onClick(View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        CustomizeActivity customizeActivity = this.this$0;
        customizeActivity.startActivityForResult(Intent.createChooser(intent, customizeActivity.getString(R.string.install_color)), this.this$0.REQUEST_SELECT_COLOR);
    }
}
