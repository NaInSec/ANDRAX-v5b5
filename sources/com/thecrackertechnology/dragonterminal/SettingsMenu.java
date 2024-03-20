package com.thecrackertechnology.dragonterminal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.thecrackertechnology.dragonterminal.SettingsMenuKeyboard;
import com.thecrackertechnology.dragonterminal.SettingsMenuMisc;
import com.thecrackertechnology.dragonterminal.SettingsMenuMouse;
import com.thecrackertechnology.dragonterminal.xorg.R;
import java.util.ArrayList;

class SettingsMenu {
    static ArrayList<Menu> menuStack = new ArrayList<>();

    SettingsMenu() {
    }

    public static abstract class Menu {
        /* access modifiers changed from: package-private */
        public boolean enabled() {
            return true;
        }

        /* access modifiers changed from: package-private */
        public abstract void run(MainActivity mainActivity);

        /* access modifiers changed from: package-private */
        public abstract String title(MainActivity mainActivity);

        /* access modifiers changed from: package-private */
        public boolean enabledOrHidden() {
            for (Menu menu : Globals.HiddenMenuOptions) {
                if (menu.getClass().getName().equals(getClass().getName())) {
                    return false;
                }
            }
            return enabled();
        }

        /* access modifiers changed from: package-private */
        public void showMenuOptionsList(final MainActivity mainActivity, final Menu[] menuArr) {
            SettingsMenu.menuStack.add(this);
            ArrayList arrayList = new ArrayList();
            for (Menu menu : menuArr) {
                if (menu.enabledOrHidden()) {
                    arrayList.add(menu.title(mainActivity));
                }
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(title(mainActivity));
            builder.setItems((CharSequence[]) arrayList.toArray(new CharSequence[0]), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    int i2 = 0;
                    for (Menu menu : menuArr) {
                        if (menu.enabledOrHidden()) {
                            if (i2 == i) {
                                menu.run(mainActivity);
                                return;
                            }
                            i2++;
                        }
                    }
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBackOuterMenu(mainActivity);
                }
            });
            AlertDialog create = builder.create();
            create.setOwnerActivity(mainActivity);
            create.show();
        }
    }

    public static void showConfig(MainActivity mainActivity, boolean z) {
        Settings.settingsChanged = true;
        if (Globals.OptionalDataDownload == null) {
            String[] strArr = Globals.DataDownloadUrl;
            Globals.OptionalDataDownload = new boolean[strArr.length];
            boolean z2 = true;
            for (int i = 0; i < strArr.length; i++) {
                if (strArr[i].indexOf("!") == 0) {
                    Globals.OptionalDataDownload[i] = true;
                    z2 = false;
                }
            }
            if (z2) {
                Globals.OptionalDataDownload[0] = true;
            }
        }
        if (!z) {
            new MainMenu().run(mainActivity);
            return;
        }
        if (Globals.StartupMenuButtonTimeout > 0) {
            for (Menu menu : Globals.FirstStartMenuOptions) {
                boolean z3 = false;
                for (Menu menu2 : Globals.HiddenMenuOptions) {
                    if (menu2.getClass().getName().equals(menu.getClass().getName())) {
                        z3 = true;
                    }
                }
                if (!z3) {
                    menuStack.add(0, menu);
                }
            }
        }
        goBack(mainActivity);
    }

    static void goBack(MainActivity mainActivity) {
        if (menuStack.isEmpty()) {
            Settings.Save(mainActivity);
            return;
        }
        ArrayList<Menu> arrayList = menuStack;
        arrayList.remove(arrayList.size() - 1).run(mainActivity);
    }

    static void goBackOuterMenu(MainActivity mainActivity) {
        if (!menuStack.isEmpty()) {
            ArrayList<Menu> arrayList = menuStack;
            arrayList.remove(arrayList.size() - 1);
        }
        goBack(mainActivity);
    }

    static class OkButton extends Menu {
        OkButton() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.ok);
        }

        /* access modifiers changed from: package-private */
        public void run(MainActivity mainActivity) {
            SettingsMenu.goBackOuterMenu(mainActivity);
        }
    }

    static class DummyMenu extends Menu {
        DummyMenu() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.ok);
        }

        /* access modifiers changed from: package-private */
        public void run(MainActivity mainActivity) {
            SettingsMenu.goBack(mainActivity);
        }
    }

    static class MainMenu extends Menu {
        MainMenu() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.device_config);
        }

        /* access modifiers changed from: package-private */
        public void run(MainActivity mainActivity) {
            showMenuOptionsList(mainActivity, new Menu[]{new SettingsMenuMisc.DownloadConfig(), new SettingsMenuMisc.OptionalDownloadConfig(false), new SettingsMenuKeyboard.KeyboardConfigMainMenu(), new SettingsMenuMouse.MouseConfigMainMenu(), new SettingsMenuMisc.AudioConfig(), new SettingsMenuKeyboard.RemapHwKeysConfig(), new SettingsMenuKeyboard.ScreenGesturesConfig(), new SettingsMenuMisc.VideoSettingsConfig(), new SettingsMenuMisc.CommandlineConfig(), new SettingsMenuMisc.ResetToDefaultsConfig(), new OkButton()});
        }
    }
}
