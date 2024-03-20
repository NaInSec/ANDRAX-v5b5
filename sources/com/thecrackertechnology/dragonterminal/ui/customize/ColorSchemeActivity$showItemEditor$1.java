package com.thecrackertechnology.dragonterminal.ui.customize;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.backend.TerminalColors;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0016J*\u0010\r\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0016¨\u0006\u000e"}, d2 = {"com/thecrackertechnology/dragonterminal/ui/customize/ColorSchemeActivity$showItemEditor$1", "Landroid/text/TextWatcher;", "afterTextChanged", "", "editable", "Landroid/text/Editable;", "beforeTextChanged", "p0", "", "p1", "", "p2", "p3", "onTextChanged", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ColorSchemeActivity.kt */
public final class ColorSchemeActivity$showItemEditor$1 implements TextWatcher {
    final /* synthetic */ EditText $edit;
    final /* synthetic */ ColorSchemeActivity this$0;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    ColorSchemeActivity$showItemEditor$1(ColorSchemeActivity colorSchemeActivity, EditText editText) {
        this.this$0 = colorSchemeActivity;
        this.$edit = editText;
    }

    public void afterTextChanged(Editable editable) {
        if (editable != null) {
            if (editable.length() > 0) {
                int parse = TerminalColors.parse(editable.toString());
                if (parse != 0) {
                    this.$edit.setTextColor(parse);
                } else {
                    this.$edit.setTextColor(this.this$0.getResources().getColor(R.color.textColor));
                }
            }
        }
    }
}
