package com.thecrackertechnology.dragonterminal.ui.term.tab;

import android.content.res.Configuration;
import com.onesignal.OneSignalDbContract;
import de.mrapp.android.tabswitcher.Tab;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u00020\u0006H\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0016J\b\u0010\f\u001a\u00020\u0006H\u0016J\b\u0010\r\u001a\u00020\u0006H\u0016J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/term/tab/NeoTab;", "Lde/mrapp/android/tabswitcher/Tab;", "title", "", "(Ljava/lang/CharSequence;)V", "onConfigurationChanged", "", "newConfig", "Landroid/content/res/Configuration;", "onDestroy", "onPause", "onResume", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoTab.kt */
public class NeoTab extends Tab {
    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkParameterIsNotNull(configuration, "newConfig");
    }

    public void onDestroy() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void onWindowFocusChanged(boolean z) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NeoTab(CharSequence charSequence) {
        super(charSequence);
        Intrinsics.checkParameterIsNotNull(charSequence, OneSignalDbContract.NotificationTable.COLUMN_NAME_TITLE);
    }
}
