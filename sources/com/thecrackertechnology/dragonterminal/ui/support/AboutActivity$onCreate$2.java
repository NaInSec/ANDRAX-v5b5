package com.thecrackertechnology.dragonterminal.ui.support;

import android.view.View;
import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.licenses.GnuGeneralPublicLicense30;
import de.psdev.licensesdialog.licenses.MITLicense;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: AboutActivity.kt */
final class AboutActivity$onCreate$2 implements View.OnClickListener {
    final /* synthetic */ AboutActivity this$0;

    AboutActivity$onCreate$2(AboutActivity aboutActivity) {
        this.this$0 = aboutActivity;
    }

    public final void onClick(View view) {
        Notices notices = new Notices();
        notices.addNotice(new Notice("ADBToolkitInstaller", "https://github.com/Crixec/ADBToolKitsInstaller", "Copyright (c) 2017 Crixec", new GnuGeneralPublicLicense30()));
        notices.addNotice(new Notice("Android-Terminal-Emulator", "https://github.com/jackpal/Android-Terminal-Emulator", "Copyright (c) 2011-2016 Steven Luo", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("ChromeLikeTabSwitcher", "https://github.com/michael-rapp/ChromeLikeTabSwitcher", "Copyright (c) 2016-2017 Michael Rapp", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("Color-O-Matic", "https://github.com/GrenderG/Color-O-Matic", "Copyright 2016-2017 GrenderG", new GnuGeneralPublicLicense30()));
        notices.addNotice(new Notice("EventBus", "http://greenrobot.org", "Copyright (C) 2012-2016 Markus Junginger, greenrobot (http://greenrobot.org)", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("ModularAdapter", "https://wrdlbrnft.github.io/ModularAdapter", "Copyright (c) 2017 Wrdlbrnft", new MITLicense()));
        notices.addNotice(new Notice("RecyclerTabLayout", "https://github.com/nshmura/RecyclerTabLayout", "Copyright (C) 2017 nshmura", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("RecyclerView-FastScroll", "Copyright (c) 2016, Tim Malseed", "Copyright (c) 2016, Tim Malseed", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("SortedListAdapter", "https://wrdlbrnft.github.io/SortedListAdapter/", "Copyright (c) 2017 Wrdlbrnft", new MITLicense()));
        notices.addNotice(new Notice("Termux", "https://termux.com", "Copyright 2016-2017 Fredrik Fornwall", new GnuGeneralPublicLicense30()));
        new LicensesDialog.Builder(this.this$0).setNotices(notices).setIncludeOwnLicense(true).build().show();
    }
}
