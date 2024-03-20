package com.thecrackertechnology.dragonterminal.component.userscript;

import android.content.Context;
import android.system.Os;
import com.thecrackertechnology.andrax.AndraxApp;
import com.thecrackertechnology.dragonterminal.frontend.component.NeoComponent;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoTermPath;
import com.thecrackertechnology.dragonterminal.frontend.logging.NLog;
import com.thecrackertechnology.dragonterminal.utils.AssetsUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000bH\u0016J\b\u0010\u0011\u001a\u00020\u000bH\u0016J\b\u0010\u0012\u001a\u00020\u000bH\u0016J\u0006\u0010\u0013\u001a\u00020\u000bR \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/userscript/UserScriptComponent;", "Lcom/thecrackertechnology/dragonterminal/frontend/component/NeoComponent;", "()V", "userScripts", "", "Lcom/thecrackertechnology/dragonterminal/component/userscript/UserScript;", "getUserScripts", "()Ljava/util/List;", "setUserScripts", "(Ljava/util/List;)V", "checkForFiles", "", "extractDefaultScript", "", "context", "Landroid/content/Context;", "onServiceDestroy", "onServiceInit", "onServiceObtained", "reloadScripts", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: UserScriptComponent.kt */
public final class UserScriptComponent implements NeoComponent {
    public List<UserScript> userScripts;

    public void onServiceDestroy() {
    }

    public final List<UserScript> getUserScripts() {
        List<UserScript> list = this.userScripts;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userScripts");
        }
        return list;
    }

    public final void setUserScripts(List<UserScript> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.userScripts = list;
    }

    public void onServiceInit() {
        checkForFiles();
    }

    public void onServiceObtained() {
        checkForFiles();
    }

    private final boolean extractDefaultScript(Context context) {
        try {
            AssetsUtils.INSTANCE.extractAssetsDir(context, "scripts", NeoTermPath.USER_SCRIPT_PATH);
            File[] listFiles = new File(NeoTermPath.USER_SCRIPT_PATH).listFiles();
            Intrinsics.checkExpressionValueIsNotNull(listFiles, "File(NeoTermPath.USER_SC…             .listFiles()");
            for (File file : listFiles) {
                Intrinsics.checkExpressionValueIsNotNull(file, "it");
                Os.chmod(file.getAbsolutePath(), 448);
            }
            return true;
        } catch (Exception e) {
            NLog.INSTANCE.e("UserScript", "Failed to extract default user scripts: " + e.getLocalizedMessage());
            return false;
        }
    }

    private final void checkForFiles() {
        new File(NeoTermPath.USER_SCRIPT_PATH).mkdirs();
        this.userScripts = new ArrayList();
        extractDefaultScript(AndraxApp.Companion.get());
        reloadScripts();
    }

    public final void reloadScripts() {
        File file = new File(NeoTermPath.USER_SCRIPT_PATH);
        file.mkdirs();
        List<UserScript> list = this.userScripts;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userScripts");
        }
        list.clear();
        File[] listFiles = file.listFiles();
        Intrinsics.checkExpressionValueIsNotNull(listFiles, "userScriptDir.listFiles()");
        ArrayList arrayList = new ArrayList();
        for (File file2 : listFiles) {
            if (!file2.canExecute()) {
                break;
            }
            arrayList.add(file2);
        }
        Iterable<File> iterable = arrayList;
        List<UserScript> list2 = this.userScripts;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userScripts");
        }
        Collection collection = list2;
        for (File file3 : iterable) {
            Intrinsics.checkExpressionValueIsNotNull(file3, "it");
            collection.add(new UserScript(file3));
        }
    }
}
