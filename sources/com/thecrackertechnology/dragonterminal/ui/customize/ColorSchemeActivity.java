package com.thecrackertechnology.dragonterminal.ui.customize;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.backend.TerminalColors;
import com.thecrackertechnology.dragonterminal.component.colorscheme.ColorSchemeComponent;
import com.thecrackertechnology.dragonterminal.component.colorscheme.NeoColorScheme;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalViewClient;
import com.thecrackertechnology.dragonterminal.ui.customize.adapter.ColorItemAdapter;
import com.thecrackertechnology.dragonterminal.ui.customize.model.ColorItem;
import com.thecrackertechnology.dragonterminal.utils.TerminalUtils;
import java.util.Comparator;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00162\b\b\u0002\u0010\u001a\u001a\u00020\u000eH\u0002J\u0012\u0010\u001b\u001a\u00020\u00182\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u0012\u0010\u001e\u001a\u00020\u000e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u001a\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0012\u0010&\u001a\u00020\u000e2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0010\u0010)\u001a\u00020\u00182\u0006\u0010*\u001a\u00020\u0005H\u0002R2\u0010\u0003\u001a&\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005 \u0006*\u0012\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X.¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/customize/ColorSchemeActivity;", "Lcom/thecrackertechnology/dragonterminal/ui/customize/BaseCustomizeActivity;", "()V", "COMPARATOR", "Ljava/util/Comparator;", "Lcom/thecrackertechnology/dragonterminal/ui/customize/model/ColorItem;", "kotlin.jvm.PlatformType", "adapter", "Lcom/thecrackertechnology/dragonterminal/ui/customize/adapter/ColorItemAdapter;", "getAdapter", "()Lcom/thecrackertechnology/dragonterminal/ui/customize/adapter/ColorItemAdapter;", "setAdapter", "(Lcom/thecrackertechnology/dragonterminal/ui/customize/adapter/ColorItemAdapter;)V", "changed", "", "getChanged", "()Z", "setChanged", "(Z)V", "colorSchemeComponent", "Lcom/thecrackertechnology/dragonterminal/component/colorscheme/ColorSchemeComponent;", "editingColorScheme", "Lcom/thecrackertechnology/dragonterminal/component/colorscheme/NeoColorScheme;", "applyColorScheme", "", "colorScheme", "finishAfter", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onKeyDown", "keyCode", "", "event", "Landroid/view/KeyEvent;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "showItemEditor", "model", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ColorSchemeActivity.kt */
public final class ColorSchemeActivity extends BaseCustomizeActivity {
    private final Comparator<ColorItem> COMPARATOR = new SortedListAdapter.ComparatorBuilder().setOrderForModel(ColorItem.class, ColorSchemeActivity$COMPARATOR$1.INSTANCE).build();
    private HashMap _$_findViewCache;
    public ColorItemAdapter adapter;
    private boolean changed;
    /* access modifiers changed from: private */
    public final ColorSchemeComponent colorSchemeComponent = ((ColorSchemeComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, ColorSchemeComponent.class, false, 2, (Object) null));
    /* access modifiers changed from: private */
    public NeoColorScheme editingColorScheme;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public static final /* synthetic */ NeoColorScheme access$getEditingColorScheme$p(ColorSchemeActivity colorSchemeActivity) {
        NeoColorScheme neoColorScheme = colorSchemeActivity.editingColorScheme;
        if (neoColorScheme == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editingColorScheme");
        }
        return neoColorScheme;
    }

    public final boolean getChanged() {
        return this.changed;
    }

    public final void setChanged(boolean z) {
        this.changed = z;
    }

    public final ColorItemAdapter getAdapter() {
        ColorItemAdapter colorItemAdapter = this.adapter;
        if (colorItemAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        return colorItemAdapter;
    }

    public final void setAdapter(ColorItemAdapter colorItemAdapter) {
        Intrinsics.checkParameterIsNotNull(colorItemAdapter, "<set-?>");
        this.adapter = colorItemAdapter;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initCustomizationComponent(R.layout.ui_color_scheme);
        this.editingColorScheme = this.colorSchemeComponent.getCurrentColorScheme().copy();
        NeoColorScheme neoColorScheme = this.editingColorScheme;
        if (neoColorScheme == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editingColorScheme");
        }
        neoColorScheme.setColorName("");
        TerminalUtils.INSTANCE.setupTerminalView((TerminalView) findViewById(R.id.terminal_view), (TerminalViewClient) null);
        Context context = this;
        NeoColorScheme neoColorScheme2 = this.editingColorScheme;
        if (neoColorScheme2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editingColorScheme");
        }
        Comparator<ColorItem> comparator = this.COMPARATOR;
        Intrinsics.checkExpressionValueIsNotNull(comparator, "COMPARATOR");
        this.adapter = new ColorItemAdapter(context, neoColorScheme2, comparator, new ColorSchemeActivity$onCreate$1(this));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.custom_color_color_list);
        recyclerView.setHasFixedSize(true);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "recyclerView");
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        ColorItemAdapter colorItemAdapter = this.adapter;
        if (colorItemAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        recyclerView.setAdapter(colorItemAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_color_editor, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Integer valueOf = menuItem != null ? Integer.valueOf(menuItem.getItemId()) : null;
        if (valueOf != null && valueOf.intValue() == 16908332) {
            finish();
        } else if (valueOf != null && valueOf.intValue() == R.id.action_done) {
            NeoColorScheme neoColorScheme = this.editingColorScheme;
            if (neoColorScheme == null) {
                Intrinsics.throwUninitializedPropertyAccessException("editingColorScheme");
            }
            applyColorScheme$default(this, neoColorScheme, false, 2, (Object) null);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (keyEvent == null) {
                Intrinsics.throwNpe();
            }
            if (keyEvent.getAction() == 0 && this.changed) {
                new AlertDialog.Builder(this).setMessage(getString(R.string.discard_changes)).setPositiveButton(R.string.save, new ColorSchemeActivity$onKeyDown$1(this)).setNegativeButton(17039369, (DialogInterface.OnClickListener) null).setNeutralButton(R.string.exit, new ColorSchemeActivity$onKeyDown$2(this)).show();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* access modifiers changed from: private */
    public final void showItemEditor(ColorItem colorItem) {
        Context context = this;
        boolean z = false;
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_edit_text, (ViewGroup) null, false);
        View findViewById = inflate.findViewById(R.id.dialog_edit_text_info);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById<TextVi…id.dialog_edit_text_info)");
        ((TextView) findViewById).setText(getString(R.string.input_new_value));
        EditText editText = (EditText) inflate.findViewById(R.id.dialog_edit_text_editor);
        editText.setText(colorItem.getColorValue());
        if (colorItem.getColorValue().length() > 0) {
            z = true;
        }
        if (z) {
            editText.setTextColor(TerminalColors.parse(colorItem.getColorValue()));
        }
        editText.addTextChangedListener(new ColorSchemeActivity$showItemEditor$1(this, editText));
        Function1 colorSchemeActivity$showItemEditor$applyColor$1 = new ColorSchemeActivity$showItemEditor$applyColor$1(this, colorItem);
        new AlertDialog.Builder(context).setTitle(colorItem.getColorName()).setView(inflate).setNegativeButton(17039369, (DialogInterface.OnClickListener) null).setPositiveButton(17039379, new ColorSchemeActivity$showItemEditor$2(colorSchemeActivity$showItemEditor$applyColor$1, editText)).setNeutralButton(R.string.select_new_value, new ColorSchemeActivity$showItemEditor$3(this, colorItem, colorSchemeActivity$showItemEditor$applyColor$1)).show();
    }

    static /* synthetic */ void applyColorScheme$default(ColorSchemeActivity colorSchemeActivity, NeoColorScheme neoColorScheme, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        colorSchemeActivity.applyColorScheme(neoColorScheme, z);
    }

    /* access modifiers changed from: private */
    public final void applyColorScheme(NeoColorScheme neoColorScheme, boolean z) {
        if (neoColorScheme.getColorName().length() == 0) {
            Context context = this;
            View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_edit_text, (ViewGroup) null, false);
            View findViewById = inflate.findViewById(R.id.dialog_edit_text_info);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById<TextVi…id.dialog_edit_text_info)");
            ((TextView) findViewById).setText(getString(R.string.save_color_info));
            EditText editText = (EditText) inflate.findViewById(R.id.dialog_edit_text_editor);
            editText.setText(getString(R.string.save_color_scheme_name_template));
            new AlertDialog.Builder(context).setTitle(R.string.save_color).setView(inflate).setPositiveButton(17039379, new ColorSchemeActivity$applyColorScheme$1(this, neoColorScheme, editText, z)).setNegativeButton(17039369, (DialogInterface.OnClickListener) null).show();
            return;
        }
        try {
            this.colorSchemeComponent.saveColorScheme(neoColorScheme);
            this.colorSchemeComponent.reloadColorSchemes();
            this.colorSchemeComponent.setCurrentColorScheme(neoColorScheme);
            this.changed = false;
            Toast.makeText(this, R.string.done, 0).show();
            if (z) {
                finish();
            }
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.error) + ": " + e.getLocalizedMessage(), 1).show();
        }
    }
}
