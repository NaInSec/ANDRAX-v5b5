package com.thecrackertechnology.dragonterminal.ui.pm;

import android.content.DialogInterface;
import android.widget.EditText;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.component.pm.SourceManager;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: PackageManagerActivity.kt */
final class PackageManagerActivity$changeSourceToUserInput$1 implements DialogInterface.OnClickListener {
    final /* synthetic */ EditText $repoEditor;
    final /* synthetic */ SourceManager $sourceManager;
    final /* synthetic */ EditText $urlEditor;
    final /* synthetic */ PackageManagerActivity this$0;

    PackageManagerActivity$changeSourceToUserInput$1(PackageManagerActivity packageManagerActivity, EditText editText, EditText editText2, SourceManager sourceManager) {
        this.this$0 = packageManagerActivity;
        this.$urlEditor = editText;
        this.$repoEditor = editText2;
        this.$sourceManager = sourceManager;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        boolean z;
        EditText editText = this.$urlEditor;
        Intrinsics.checkExpressionValueIsNotNull(editText, "urlEditor");
        String obj = editText.getText().toString();
        EditText editText2 = this.$repoEditor;
        Intrinsics.checkExpressionValueIsNotNull(editText2, "repoEditor");
        String obj2 = editText2.getText().toString();
        if (obj != null) {
            boolean z2 = false;
            if (StringsKt.trim((CharSequence) obj).toString().length() == 0) {
                EditText editText3 = this.$urlEditor;
                Intrinsics.checkExpressionValueIsNotNull(editText3, "urlEditor");
                editText3.setError(this.this$0.getString(R.string.error_new_source_url));
                z = true;
            } else {
                z = false;
            }
            if (obj2 != null) {
                if (StringsKt.trim((CharSequence) obj2).toString().length() == 0) {
                    z2 = true;
                }
                if (z2) {
                    EditText editText4 = this.$repoEditor;
                    Intrinsics.checkExpressionValueIsNotNull(editText4, "repoEditor");
                    editText4.setError(this.this$0.getString(R.string.error_new_source_repo));
                    z = true;
                }
                if (!z) {
                    EditText editText5 = this.$urlEditor;
                    Intrinsics.checkExpressionValueIsNotNull(editText5, "urlEditor");
                    this.$sourceManager.addSource(editText5.getText().toString(), obj2, true);
                    this.this$0.postChangeSource(this.$sourceManager);
                    return;
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }
}
