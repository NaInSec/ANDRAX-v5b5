package com.thecrackertechnology.dragonterminal.ui.pm;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
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
import com.thecrackertechnology.dragonterminal.component.pm.NeoPackageInfo;
import com.thecrackertechnology.dragonterminal.component.pm.PackageComponent;
import com.thecrackertechnology.dragonterminal.component.pm.Source;
import com.thecrackertechnology.dragonterminal.component.pm.SourceHelper;
import com.thecrackertechnology.dragonterminal.component.pm.SourceManager;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference;
import com.thecrackertechnology.dragonterminal.frontend.floating.TerminalDialog;
import com.thecrackertechnology.dragonterminal.ui.pm.adapter.PackageAdapter;
import com.thecrackertechnology.dragonterminal.ui.pm.model.PackageModel;
import com.thecrackertechnology.dragonterminal.ui.pm.utils.StringDistance;
import com.thecrackertechnology.dragonterminal.utils.PackageUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\u001e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0002J\u0010\u0010$\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020 H\u0003J\b\u0010%\u001a\u00020\u001dH\u0002J\b\u0010&\u001a\u00020\u001dH\u0002J$\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00070\"2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\"2\u0006\u0010(\u001a\u00020)H\u0002J\u0012\u0010*\u001a\u00020\u001d2\b\u0010+\u001a\u0004\u0018\u00010)H\u0002J\u0012\u0010,\u001a\u00020\u001d2\b\u0010-\u001a\u0004\u0018\u00010.H\u0014J\u0012\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u000102H\u0016J\b\u00103\u001a\u00020\u001dH\u0016J\b\u00104\u001a\u00020\u001dH\u0016J\u0012\u00105\u001a\u0002002\b\u00106\u001a\u0004\u0018\u000107H\u0016J\u0012\u00108\u001a\u0002002\b\u00109\u001a\u0004\u0018\u00010)H\u0016J\u0012\u0010:\u001a\u0002002\b\u00109\u001a\u0004\u0018\u00010)H\u0016J\u0010\u0010;\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010<\u001a\u00020\u001dH\u0002JD\u0010=\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020?0>0\"2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\"2\u0006\u0010(\u001a\u00020)2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u00020)0AH\u0002R2\u0010\u0005\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u0007 \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u0007\u0018\u00010\u00060\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR*\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0010j\b\u0012\u0004\u0012\u00020\u0007`\u0011X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006C"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/pm/PackageManagerActivity;", "Landroid/support/v7/app/AppCompatActivity;", "Landroid/support/v7/widget/SearchView$OnQueryTextListener;", "Lcom/github/wrdlbrnft/sortedlistadapter/SortedListAdapter$Callback;", "()V", "COMPARATOR", "Ljava/util/Comparator;", "Lcom/thecrackertechnology/dragonterminal/ui/pm/model/PackageModel;", "kotlin.jvm.PlatformType", "adapter", "Lcom/thecrackertechnology/dragonterminal/ui/pm/adapter/PackageAdapter;", "getAdapter", "()Lcom/thecrackertechnology/dragonterminal/ui/pm/adapter/PackageAdapter;", "setAdapter", "(Lcom/thecrackertechnology/dragonterminal/ui/pm/adapter/PackageAdapter;)V", "models", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getModels", "()Ljava/util/ArrayList;", "setModels", "(Ljava/util/ArrayList;)V", "recyclerView", "Landroid/support/v7/widget/RecyclerView;", "getRecyclerView", "()Landroid/support/v7/widget/RecyclerView;", "setRecyclerView", "(Landroid/support/v7/widget/RecyclerView;)V", "changeSource", "", "changeSourceInternal", "sourceManager", "Lcom/thecrackertechnology/dragonterminal/component/pm/SourceManager;", "source", "", "Lcom/thecrackertechnology/dragonterminal/component/pm/Source;", "changeSourceToUserInput", "executeAptUpdate", "executeAptUpgrade", "filter", "query", "", "installPackage", "packageName", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onEditFinished", "onEditStarted", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onQueryTextChange", "text", "onQueryTextSubmit", "postChangeSource", "refreshPackageList", "sortDistance", "Lkotlin/Pair;", "", "mapper", "Lkotlin/Function1;", "Lcom/thecrackertechnology/dragonterminal/component/pm/NeoPackageInfo;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: PackageManagerActivity.kt */
public final class PackageManagerActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SortedListAdapter.Callback {
    private final Comparator<PackageModel> COMPARATOR = new SortedListAdapter.ComparatorBuilder().setOrderForModel(PackageModel.class, PackageManagerActivity$COMPARATOR$1.INSTANCE).build();
    private HashMap _$_findViewCache;
    public PackageAdapter adapter;
    public ArrayList<PackageModel> models;
    public RecyclerView recyclerView;

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

    public boolean onQueryTextSubmit(String str) {
        return false;
    }

    public final RecyclerView getRecyclerView() {
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        return recyclerView2;
    }

    public final void setRecyclerView(RecyclerView recyclerView2) {
        Intrinsics.checkParameterIsNotNull(recyclerView2, "<set-?>");
        this.recyclerView = recyclerView2;
    }

    public final PackageAdapter getAdapter() {
        PackageAdapter packageAdapter = this.adapter;
        if (packageAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        return packageAdapter;
    }

    public final void setAdapter(PackageAdapter packageAdapter) {
        Intrinsics.checkParameterIsNotNull(packageAdapter, "<set-?>");
        this.adapter = packageAdapter;
    }

    public final ArrayList<PackageModel> getModels() {
        ArrayList<PackageModel> arrayList = this.models;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("models");
        }
        return arrayList;
    }

    public final void setModels(ArrayList<PackageModel> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.models = arrayList;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.ui_pm_single_tab);
        setSupportActionBar((Toolbar) findViewById(R.id.pm_toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        View findViewById = findViewById(R.id.pm_package_list);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.pm_package_list)");
        this.recyclerView = (RecyclerView) findViewById;
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        recyclerView2.setHasFixedSize(true);
        Context context = this;
        Comparator<PackageModel> comparator = this.COMPARATOR;
        Intrinsics.checkExpressionValueIsNotNull(comparator, "COMPARATOR");
        this.adapter = new PackageAdapter(context, comparator, new PackageManagerActivity$onCreate$1(this));
        PackageAdapter packageAdapter = this.adapter;
        if (packageAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        packageAdapter.addCallback(this);
        RecyclerView recyclerView3 = this.recyclerView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        recyclerView3.setLayoutManager(new LinearLayoutManager(context));
        RecyclerView recyclerView4 = this.recyclerView;
        if (recyclerView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        PackageAdapter packageAdapter2 = this.adapter;
        if (packageAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        recyclerView4.setAdapter(packageAdapter2);
        this.models = new ArrayList<>();
        refreshPackageList();
    }

    /* access modifiers changed from: private */
    public final void installPackage(String str) {
        if (str != null) {
            Context context = this;
            TerminalDialog imeEnabled = new TerminalDialog(context).execute("", new String[]{"apt", "install", "-y", str}).onFinish(new PackageManagerActivity$installPackage$1(this)).imeEnabled(true);
            imeEnabled.show("Installing " + str);
            Toast.makeText(context, R.string.installing_topic, 1).show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pm, menu);
        if (menu == null) {
            Intrinsics.throwNpe();
        }
        View actionView = MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        if (actionView != null) {
            ((SearchView) actionView).setOnQueryTextListener(this);
            return true;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.support.v7.widget.SearchView");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Integer valueOf = menuItem != null ? Integer.valueOf(menuItem.getItemId()) : null;
        if (valueOf != null && valueOf.intValue() == 16908332) {
            finish();
        } else if (valueOf != null && valueOf.intValue() == R.id.action_source) {
            changeSource();
        } else if (valueOf != null && valueOf.intValue() == R.id.action_update_and_refresh) {
            executeAptUpdate();
        } else if (valueOf != null && valueOf.intValue() == R.id.action_refresh) {
            refreshPackageList();
        } else if (valueOf != null && valueOf.intValue() == R.id.action_upgrade) {
            executeAptUpgrade();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* access modifiers changed from: private */
    public final void changeSource() {
        SourceManager sourceManager = ((PackageComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, PackageComponent.class, false, 2, (Object) null)).getSourceManager();
        List<Source> allSources = sourceManager.getAllSources();
        AlertDialog.Builder title = new AlertDialog.Builder(this).setTitle((int) R.string.pref_package_source);
        Iterable<Source> iterable = allSources;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Source source : iterable) {
            arrayList.add(source.url + " :: " + source.repo);
        }
        Object[] array = ((List) arrayList).toArray(new String[0]);
        if (array != null) {
            CharSequence[] charSequenceArr = (CharSequence[]) array;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (Source source2 : iterable) {
                arrayList2.add(Boolean.valueOf(source2.enabled));
            }
            title.setMultiChoiceItems(charSequenceArr, CollectionsKt.toBooleanArray((List) arrayList2), (DialogInterface.OnMultiChoiceClickListener) new PackageManagerActivity$changeSource$3(allSources)).setPositiveButton(17039379, (DialogInterface.OnClickListener) new PackageManagerActivity$changeSource$4(this, sourceManager, allSources)).setNeutralButton((int) R.string.new_source, (DialogInterface.OnClickListener) new PackageManagerActivity$changeSource$5(this, sourceManager)).setNegativeButton(17039369, (DialogInterface.OnClickListener) null).show();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* access modifiers changed from: private */
    public final void changeSourceToUserInput(SourceManager sourceManager) {
        Context context = this;
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_edit_two_text, (ViewGroup) null, false);
        View findViewById = inflate.findViewById(R.id.dialog_edit_text_info);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById<TextVi…id.dialog_edit_text_info)");
        ((TextView) findViewById).setText(getString(R.string.input_new_source_url));
        View findViewById2 = inflate.findViewById(R.id.dialog_edit_text2_info);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById<TextVi…d.dialog_edit_text2_info)");
        ((TextView) findViewById2).setText(getString(R.string.input_new_source_repo));
        EditText editText = (EditText) inflate.findViewById(R.id.dialog_edit_text2_editor);
        editText.setText("stable main");
        new AlertDialog.Builder(context).setTitle((int) R.string.pref_package_source).setView(inflate).setNegativeButton(17039369, (DialogInterface.OnClickListener) null).setPositiveButton(17039379, (DialogInterface.OnClickListener) new PackageManagerActivity$changeSourceToUserInput$1(this, (EditText) inflate.findViewById(R.id.dialog_edit_text_editor), editText, sourceManager)).show();
    }

    /* access modifiers changed from: private */
    public final void changeSourceInternal(SourceManager sourceManager, List<? extends Source> list) {
        sourceManager.updateAll(list);
        postChangeSource(sourceManager);
    }

    /* access modifiers changed from: private */
    public final void postChangeSource(SourceManager sourceManager) {
        sourceManager.applyChanges();
        NeoPreference.INSTANCE.store((int) R.string.key_package_source, (Object) sourceManager.getMainPackageSource());
        SourceHelper.INSTANCE.syncSource(sourceManager);
        executeAptUpdate();
    }

    private final void executeAptUpdate() {
        PackageUtils.INSTANCE.apt(this, "update", (String[]) null, new PackageManagerActivity$executeAptUpdate$1(this));
    }

    private final void executeAptUpgrade() {
        PackageUtils.INSTANCE.apt(this, "update", (String[]) null, new PackageManagerActivity$executeAptUpgrade$1(this));
    }

    /* access modifiers changed from: private */
    public final void refreshPackageList() {
        ArrayList<PackageModel> arrayList = this.models;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("models");
        }
        arrayList.clear();
        new Thread(new PackageManagerActivity$refreshPackageList$1(this)).start();
    }

    private final List<Pair<PackageModel, Integer>> sortDistance(List<PackageModel> list, String str, Function1<? super NeoPackageInfo, String> function1) {
        Iterable<PackageModel> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (PackageModel packageModel : iterable) {
            String invoke = function1.invoke(packageModel.getPackageInfo());
            if (invoke != null) {
                String lowerCase = invoke.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                if (str != null) {
                    String lowerCase2 = str.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(lowerCase2, "(this as java.lang.String).toLowerCase()");
                    arrayList.add(new Pair(packageModel, Integer.valueOf(StringDistance.distance(lowerCase, lowerCase2))));
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
        return CollectionsKt.toList(CollectionsKt.sortedWith((List) arrayList, PackageManagerActivity$sortDistance$2.INSTANCE));
    }

    private final List<PackageModel> filter(List<PackageModel> list, String str) {
        List<PackageModel> arrayList = new ArrayList<>();
        Collection arrayList2 = new ArrayList();
        for (Object next : list) {
            PackageModel packageModel = (PackageModel) next;
            String packageName = packageModel.getPackageInfo().getPackageName();
            if (packageName == null) {
                Intrinsics.throwNpe();
            }
            CharSequence charSequence = str;
            boolean z = true;
            if (!StringsKt.contains((CharSequence) packageName, charSequence, true)) {
                String description = packageModel.getPackageInfo().getDescription();
                if (description == null) {
                    Intrinsics.throwNpe();
                }
                if (!StringsKt.contains((CharSequence) description, charSequence, true)) {
                    z = false;
                }
            }
            if (z) {
                arrayList2.add(next);
            }
        }
        List list2 = (List) arrayList2;
        for (Pair first : sortDistance(list2, str, PackageManagerActivity$filter$1.INSTANCE)) {
            arrayList.add((PackageModel) first.getFirst());
        }
        Collection collection = arrayList;
        for (Pair first2 : sortDistance(list2, str, PackageManagerActivity$filter$3.INSTANCE)) {
            collection.add((PackageModel) first2.getFirst());
        }
        return arrayList;
    }

    public boolean onQueryTextChange(String str) {
        if (str == null) {
            return true;
        }
        ArrayList<PackageModel> arrayList = this.models;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("models");
        }
        List<PackageModel> filter = filter(arrayList, str);
        PackageAdapter packageAdapter = this.adapter;
        if (packageAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        packageAdapter.edit().replaceAll(filter).commit();
        return true;
    }

    public void onEditStarted() {
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        recyclerView2.animate().alpha(0.5f);
    }

    public void onEditFinished() {
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        recyclerView2.scrollToPosition(0);
        RecyclerView recyclerView3 = this.recyclerView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        recyclerView3.animate().alpha(1.0f);
    }
}
