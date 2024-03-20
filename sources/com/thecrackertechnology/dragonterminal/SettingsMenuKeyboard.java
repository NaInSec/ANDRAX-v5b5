package com.thecrackertechnology.dragonterminal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.thecrackertechnology.dragonterminal.SettingsMenu;
import com.thecrackertechnology.dragonterminal.xorg.R;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

class SettingsMenuKeyboard extends SettingsMenu {
    SettingsMenuKeyboard() {
    }

    static class KeyboardConfigMainMenu extends SettingsMenu.Menu {
        KeyboardConfigMainMenu() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.controls_screenkb);
        }

        /* access modifiers changed from: package-private */
        public boolean enabled() {
            return Globals.UseTouchscreenKeyboard;
        }

        /* access modifiers changed from: package-private */
        public void run(MainActivity mainActivity) {
            showMenuOptionsList(mainActivity, new SettingsMenu.Menu[]{new ScreenKeyboardThemeConfig(), new ScreenKeyboardSizeConfig(), new ScreenKeyboardDrawSizeConfig(), new ScreenKeyboardTransparencyConfig(), new RemapScreenKbConfig(), new CustomizeScreenKbLayout(), new ScreenKeyboardAdvanced(), new SettingsMenu.OkButton()});
        }
    }

    static class ScreenKeyboardSizeConfig extends SettingsMenu.Menu {
        ScreenKeyboardSizeConfig() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.controls_screenkb_size);
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.controls_screenkb_large), mainActivity.getResources().getString(R.string.controls_screenkb_medium), mainActivity.getResources().getString(R.string.controls_screenkb_small), mainActivity.getResources().getString(R.string.controls_screenkb_tiny), mainActivity.getResources().getString(R.string.controls_screenkb_custom)};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(mainActivity.getResources().getString(R.string.controls_screenkb_size));
            builder.setSingleChoiceItems(charSequenceArr, Globals.TouchscreenKeyboardSize, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Globals.TouchscreenKeyboardSize = i;
                    dialogInterface.dismiss();
                    if (Globals.TouchscreenKeyboardSize == 4) {
                        new CustomizeScreenKbLayout().run(mainActivity);
                    } else {
                        SettingsMenu.goBack(mainActivity);
                    }
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBack(mainActivity);
                }
            });
            AlertDialog create = builder.create();
            create.setOwnerActivity(mainActivity);
            create.show();
        }
    }

    static class ScreenKeyboardDrawSizeConfig extends SettingsMenu.Menu {
        ScreenKeyboardDrawSizeConfig() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.controls_screenkb_drawsize);
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.controls_screenkb_large), mainActivity.getResources().getString(R.string.controls_screenkb_medium), mainActivity.getResources().getString(R.string.controls_screenkb_small), mainActivity.getResources().getString(R.string.controls_screenkb_tiny)};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(mainActivity.getResources().getString(R.string.controls_screenkb_drawsize));
            builder.setSingleChoiceItems(charSequenceArr, Globals.TouchscreenKeyboardDrawSize, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Globals.TouchscreenKeyboardDrawSize = i;
                    dialogInterface.dismiss();
                    SettingsMenu.goBack(mainActivity);
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBack(mainActivity);
                }
            });
            AlertDialog create = builder.create();
            create.setOwnerActivity(mainActivity);
            create.show();
        }
    }

    static class ScreenKeyboardThemeConfig extends SettingsMenu.Menu {
        ScreenKeyboardThemeConfig() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.controls_screenkb_theme);
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.controls_screenkb_by, new Object[]{"Ultimate Droid", "Sean Stieber"}), mainActivity.getResources().getString(R.string.controls_screenkb_by, new Object[]{"Simple Theme", "Beholder"}), mainActivity.getResources().getString(R.string.controls_screenkb_by, new Object[]{"Sun", "Sirea"}), mainActivity.getResources().getString(R.string.controls_screenkb_by, new Object[]{"Keen", "Gerstrong"}), mainActivity.getResources().getString(R.string.controls_screenkb_by, new Object[]{"Retro", "Santiago Radeff"}), mainActivity.getResources().getString(R.string.controls_screenkb_by, new Object[]{"Gba", "from RetroArch"}), mainActivity.getResources().getString(R.string.controls_screenkb_by, new Object[]{"Psx", "from RetroArch"}), mainActivity.getResources().getString(R.string.controls_screenkb_by, new Object[]{"Snes", "from RetroArch"}), mainActivity.getResources().getString(R.string.controls_screenkb_by, new Object[]{"DualShock", "from RetroArch"}), mainActivity.getResources().getString(R.string.controls_screenkb_by, new Object[]{"N64", "from RetroArch"})};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(mainActivity.getResources().getString(R.string.controls_screenkb_theme));
            builder.setSingleChoiceItems(charSequenceArr, Globals.TouchscreenKeyboardTheme, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Globals.TouchscreenKeyboardTheme = i;
                    dialogInterface.dismiss();
                    SettingsMenu.goBack(mainActivity);
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBack(mainActivity);
                }
            });
            AlertDialog create = builder.create();
            create.setOwnerActivity(mainActivity);
            create.show();
        }
    }

    static class ScreenKeyboardTransparencyConfig extends SettingsMenu.Menu {
        ScreenKeyboardTransparencyConfig() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.controls_screenkb_transparency);
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.controls_screenkb_trans_0), mainActivity.getResources().getString(R.string.controls_screenkb_trans_1), mainActivity.getResources().getString(R.string.controls_screenkb_trans_2), mainActivity.getResources().getString(R.string.controls_screenkb_trans_3), mainActivity.getResources().getString(R.string.controls_screenkb_trans_4)};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(mainActivity.getResources().getString(R.string.controls_screenkb_transparency));
            builder.setSingleChoiceItems(charSequenceArr, Globals.TouchscreenKeyboardTransparency, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Globals.TouchscreenKeyboardTransparency = i;
                    dialogInterface.dismiss();
                    SettingsMenu.goBack(mainActivity);
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBack(mainActivity);
                }
            });
            AlertDialog create = builder.create();
            create.setOwnerActivity(mainActivity);
            create.show();
        }
    }

    static class RemapHwKeysConfig extends SettingsMenu.Menu {
        RemapHwKeysConfig() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.remap_hwkeys);
        }

        /* access modifiers changed from: package-private */
        public void run(MainActivity mainActivity) {
            mainActivity.setText(mainActivity.getResources().getString(R.string.remap_hwkeys_press));
            mainActivity.getVideoLayout().setOnKeyListener(new KeyRemapTool(mainActivity));
        }

        public static class KeyRemapTool implements View.OnKeyListener {
            MainActivity p;

            public KeyRemapTool(MainActivity mainActivity) {
                this.p = mainActivity;
            }

            public boolean onKey(View view, final int i, KeyEvent keyEvent) {
                this.p.getVideoLayout().setOnKeyListener((View.OnKeyListener) null);
                if (i < 0) {
                    i = 0;
                }
                if (i > 255) {
                    i = 0;
                }
                CharSequence[] charSequenceArr = {SDL_Keys.names[Globals.RemapScreenKbKeycode[0]], SDL_Keys.names[Globals.RemapScreenKbKeycode[1]], SDL_Keys.names[Globals.RemapScreenKbKeycode[2]], SDL_Keys.names[Globals.RemapScreenKbKeycode[3]], SDL_Keys.names[Globals.RemapScreenKbKeycode[4]], SDL_Keys.names[Globals.RemapScreenKbKeycode[5]], this.p.getResources().getString(R.string.remap_hwkeys_select_more_keys)};
                for (int i2 = 0; i2 < Math.min(6, Globals.AppTouchscreenKeyboardKeysNames.length); i2++) {
                    charSequenceArr[i2] = Globals.AppTouchscreenKeyboardKeysNames[i2].replace("_", StringUtils.SPACE);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this.p);
                builder.setTitle(R.string.remap_hwkeys_select_simple);
                builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (i >= 6) {
                            KeyRemapTool.this.ShowAllKeys(i);
                            return;
                        }
                        Globals.RemapHwKeycode[i] = Globals.RemapScreenKbKeycode[i];
                        SettingsMenu.goBack(KeyRemapTool.this.p);
                    }
                });
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        SettingsMenu.goBack(KeyRemapTool.this.p);
                    }
                });
                AlertDialog create = builder.create();
                create.setOwnerActivity(this.p);
                create.show();
                return true;
            }

            public void ShowAllKeys(final int i) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this.p);
                builder.setTitle(R.string.remap_hwkeys_select);
                builder.setSingleChoiceItems(SDL_Keys.namesSorted, SDL_Keys.namesSortedBackIdx[Globals.RemapHwKeycode[i]].intValue(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Globals.RemapHwKeycode[i] = SDL_Keys.namesSortedIdx[i].intValue();
                        dialogInterface.dismiss();
                        SettingsMenu.goBack(KeyRemapTool.this.p);
                    }
                });
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        SettingsMenu.goBack(KeyRemapTool.this.p);
                    }
                });
                AlertDialog create = builder.create();
                create.setOwnerActivity(this.p);
                create.show();
            }
        }
    }

    static class RemapScreenKbConfig extends SettingsMenu.Menu {
        RemapScreenKbConfig() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.remap_screenkb);
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            String string = mainActivity.getResources().getString(R.string.remap_screenkb_joystick);
            CharSequence[] charSequenceArr = {string, mainActivity.getResources().getString(R.string.remap_screenkb_button_text), mainActivity.getResources().getString(R.string.remap_screenkb_button) + " 1", mainActivity.getResources().getString(R.string.remap_screenkb_button) + " 2", mainActivity.getResources().getString(R.string.remap_screenkb_button) + " 3", mainActivity.getResources().getString(R.string.remap_screenkb_button) + " 4", mainActivity.getResources().getString(R.string.remap_screenkb_button) + " 5", mainActivity.getResources().getString(R.string.remap_screenkb_button) + " 6"};
            boolean[] copyOf = Arrays.copyOf(Globals.ScreenKbControlsShown, Globals.ScreenKbControlsShown.length);
            if (Globals.AppUsesSecondJoystick) {
                charSequenceArr = (CharSequence[]) Arrays.copyOf(charSequenceArr, charSequenceArr.length + 1);
                charSequenceArr[charSequenceArr.length - 1] = mainActivity.getResources().getString(R.string.remap_screenkb_joystick) + " 2";
                copyOf = Arrays.copyOf(copyOf, copyOf.length + 1);
                copyOf[copyOf.length - 1] = true;
            }
            if (Globals.AppUsesThirdJoystick) {
                charSequenceArr = (CharSequence[]) Arrays.copyOf(charSequenceArr, charSequenceArr.length + 1);
                charSequenceArr[charSequenceArr.length - 1] = mainActivity.getResources().getString(R.string.remap_screenkb_joystick) + " 3";
                copyOf = Arrays.copyOf(copyOf, copyOf.length + 1);
                copyOf[copyOf.length - 1] = true;
            }
            for (int i = 0; i < Math.min(6, Globals.AppTouchscreenKeyboardKeysNames.length); i++) {
                int i2 = i + 2;
                charSequenceArr[i2] = charSequenceArr[i2] + " - " + Globals.AppTouchscreenKeyboardKeysNames[i].replace("_", StringUtils.SPACE);
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(mainActivity.getResources().getString(R.string.remap_screenkb));
            builder.setMultiChoiceItems(charSequenceArr, copyOf, new DialogInterface.OnMultiChoiceClickListener() {
                public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                    Globals.ScreenKbControlsShown[i] = z;
                }
            });
            builder.setPositiveButton(mainActivity.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    RemapScreenKbConfig.showRemapScreenKbConfig2(mainActivity, 0);
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBack(mainActivity);
                }
            });
            AlertDialog create = builder.create();
            create.setOwnerActivity(mainActivity);
            create.show();
        }

        static void showRemapScreenKbConfig2(final MainActivity mainActivity, final int i) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.remap_screenkb_button) + " 1", mainActivity.getResources().getString(R.string.remap_screenkb_button) + " 2", mainActivity.getResources().getString(R.string.remap_screenkb_button) + " 3", mainActivity.getResources().getString(R.string.remap_screenkb_button) + " 4", mainActivity.getResources().getString(R.string.remap_screenkb_button) + " 5", mainActivity.getResources().getString(R.string.remap_screenkb_button) + " 6"};
            for (int i2 = 0; i2 < Math.min(6, Globals.AppTouchscreenKeyboardKeysNames.length); i2++) {
                charSequenceArr[i2] = charSequenceArr[i2] + " - " + Globals.AppTouchscreenKeyboardKeysNames[i2].replace("_", StringUtils.SPACE);
            }
            if (i >= Globals.RemapScreenKbKeycode.length) {
                SettingsMenu.goBack(mainActivity);
            } else if (!Globals.ScreenKbControlsShown[i + 2]) {
                showRemapScreenKbConfig2(mainActivity, i + 1);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                builder.setTitle(charSequenceArr[i]);
                builder.setSingleChoiceItems(SDL_Keys.namesSorted, SDL_Keys.namesSortedBackIdx[Globals.RemapScreenKbKeycode[i]].intValue(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Globals.RemapScreenKbKeycode[i] = SDL_Keys.namesSortedIdx[i].intValue();
                        dialogInterface.dismiss();
                        RemapScreenKbConfig.showRemapScreenKbConfig2(mainActivity, i + 1);
                    }
                });
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        SettingsMenu.goBack(mainActivity);
                    }
                });
                AlertDialog create = builder.create();
                create.setOwnerActivity(mainActivity);
                create.show();
            }
        }
    }

    static class ScreenGesturesConfig extends SettingsMenu.Menu {
        ScreenGesturesConfig() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.remap_screenkb_button_gestures);
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.remap_screenkb_button_zoomin), mainActivity.getResources().getString(R.string.remap_screenkb_button_zoomout), mainActivity.getResources().getString(R.string.remap_screenkb_button_rotateleft), mainActivity.getResources().getString(R.string.remap_screenkb_button_rotateright)};
            boolean[] zArr = {Globals.MultitouchGesturesUsed[0], Globals.MultitouchGesturesUsed[1], Globals.MultitouchGesturesUsed[2], Globals.MultitouchGesturesUsed[3]};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(mainActivity.getResources().getString(R.string.remap_screenkb_button_gestures));
            builder.setMultiChoiceItems(charSequenceArr, zArr, new DialogInterface.OnMultiChoiceClickListener() {
                public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                    Globals.MultitouchGesturesUsed[i] = z;
                }
            });
            builder.setPositiveButton(mainActivity.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    ScreenGesturesConfig.showScreenGesturesConfig2(mainActivity);
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBack(mainActivity);
                }
            });
            AlertDialog create = builder.create();
            create.setOwnerActivity(mainActivity);
            create.show();
        }

        static void showScreenGesturesConfig2(final MainActivity mainActivity) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.accel_slow), mainActivity.getResources().getString(R.string.accel_medium), mainActivity.getResources().getString(R.string.accel_fast), mainActivity.getResources().getString(R.string.accel_veryfast)};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(R.string.remap_screenkb_button_gestures_sensitivity);
            builder.setSingleChoiceItems(charSequenceArr, Globals.MultitouchGestureSensitivity, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Globals.MultitouchGestureSensitivity = i;
                    dialogInterface.dismiss();
                    ScreenGesturesConfig.showScreenGesturesConfig3(mainActivity, 0);
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBack(mainActivity);
                }
            });
            AlertDialog create = builder.create();
            create.setOwnerActivity(mainActivity);
            create.show();
        }

        static void showScreenGesturesConfig3(final MainActivity mainActivity, final int i) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.remap_screenkb_button_zoomin), mainActivity.getResources().getString(R.string.remap_screenkb_button_zoomout), mainActivity.getResources().getString(R.string.remap_screenkb_button_rotateleft), mainActivity.getResources().getString(R.string.remap_screenkb_button_rotateright)};
            if (i >= Globals.RemapMultitouchGestureKeycode.length) {
                SettingsMenu.goBack(mainActivity);
            } else if (!Globals.MultitouchGesturesUsed[i]) {
                showScreenGesturesConfig3(mainActivity, i + 1);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                builder.setTitle(charSequenceArr[i]);
                builder.setSingleChoiceItems(SDL_Keys.namesSorted, SDL_Keys.namesSortedBackIdx[Globals.RemapMultitouchGestureKeycode[i]].intValue(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Globals.RemapMultitouchGestureKeycode[i] = SDL_Keys.namesSortedIdx[i].intValue();
                        dialogInterface.dismiss();
                        ScreenGesturesConfig.showScreenGesturesConfig3(mainActivity, i + 1);
                    }
                });
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        SettingsMenu.goBack(mainActivity);
                    }
                });
                AlertDialog create = builder.create();
                create.setOwnerActivity(mainActivity);
                create.show();
            }
        }
    }

    static class CustomizeScreenKbLayout extends SettingsMenu.Menu {
        CustomizeScreenKbLayout() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.screenkb_custom_layout);
        }

        /* access modifiers changed from: package-private */
        public void run(MainActivity mainActivity) {
            mainActivity.setText(mainActivity.getResources().getString(R.string.screenkb_custom_layout_help));
            new CustomizeScreenKbLayoutTool(mainActivity);
            Globals.TouchscreenKeyboardSize = 4;
        }

        static class CustomizeScreenKbLayoutTool implements View.OnTouchListener, View.OnKeyListener {
            Bitmap[] bmps = new Bitmap[Globals.ScreenKbControlsLayout.length];
            ImageView boundary = null;
            Bitmap boundaryBmp = null;
            int[] buttons;
            int currentButton;
            ImageView[] imgs = new ImageView[Globals.ScreenKbControlsLayout.length];
            FrameLayout layout = null;
            int oldX;
            int oldY;
            MainActivity p;
            boolean resizing;

            public CustomizeScreenKbLayoutTool(MainActivity mainActivity) {
                int i = 0;
                this.currentButton = 0;
                char c = 1;
                this.buttons = new int[]{R.drawable.dpad, R.drawable.keyboard, R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6, R.drawable.dpad, R.drawable.dpad};
                this.oldX = 0;
                this.oldY = 0;
                this.resizing = false;
                this.p = mainActivity;
                this.layout = new FrameLayout(this.p);
                this.p.getVideoLayout().addView(this.layout);
                this.layout.setFocusable(true);
                this.layout.setFocusableInTouchMode(true);
                this.layout.requestFocus();
                this.layout.setOnTouchListener(this);
                this.layout.setOnKeyListener(this);
                this.boundary = new ImageView(this.p);
                this.boundary.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                this.boundary.setScaleType(ImageView.ScaleType.MATRIX);
                this.boundaryBmp = BitmapFactory.decodeResource(this.p.getResources(), R.drawable.rectangle);
                this.boundary.setImageBitmap(this.boundaryBmp);
                this.layout.addView(this.boundary);
                this.currentButton = -1;
                if (Globals.TouchscreenKeyboardTheme == 2) {
                    this.buttons = new int[]{R.drawable.sun_dpad, R.drawable.sun_keyboard, R.drawable.sun_b1, R.drawable.sun_b2, R.drawable.sun_b3, R.drawable.sun_b4, R.drawable.sun_b5, R.drawable.sun_b6, R.drawable.sun_dpad, R.drawable.sun_dpad};
                }
                int i2 = 800;
                int i3 = 480;
                try {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    this.p.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    i2 = displayMetrics.widthPixels;
                    i3 = displayMetrics.heightPixels;
                } catch (Exception unused) {
                }
                int i4 = 0;
                while (i4 < Globals.ScreenKbControlsLayout.length) {
                    if (Globals.ScreenKbControlsShown[i4]) {
                        if (this.currentButton == -1) {
                            this.currentButton = i4;
                        }
                        int i5 = i3 / 12;
                        if (Globals.ScreenKbControlsLayout[i4][i] > Globals.ScreenKbControlsLayout[i4][2] - i5) {
                            Globals.ScreenKbControlsLayout[i4][i] = Globals.ScreenKbControlsLayout[i4][2] - i5;
                        }
                        if (Globals.ScreenKbControlsLayout[i4][c] > Globals.ScreenKbControlsLayout[i4][3] - i5) {
                            Globals.ScreenKbControlsLayout[i4][c] = Globals.ScreenKbControlsLayout[i4][3] - i5;
                        }
                        int i6 = (i3 * 2) / 3;
                        if (Globals.ScreenKbControlsLayout[i4][i] < Globals.ScreenKbControlsLayout[i4][2] - i6) {
                            Globals.ScreenKbControlsLayout[i4][i] = Globals.ScreenKbControlsLayout[i4][2] - i6;
                        }
                        if (Globals.ScreenKbControlsLayout[i4][c] < Globals.ScreenKbControlsLayout[i4][3] - i6) {
                            Globals.ScreenKbControlsLayout[i4][c] = Globals.ScreenKbControlsLayout[i4][3] - i6;
                        }
                        if (Globals.ScreenKbControlsLayout[i4][i] < 0) {
                            int[] iArr = Globals.ScreenKbControlsLayout[i4];
                            iArr[2] = iArr[2] + (-Globals.ScreenKbControlsLayout[i4][i]);
                            Globals.ScreenKbControlsLayout[i4][i] = i;
                        }
                        if (Globals.ScreenKbControlsLayout[i4][2] > i2) {
                            int[] iArr2 = Globals.ScreenKbControlsLayout[i4];
                            iArr2[i] = iArr2[i] - (Globals.ScreenKbControlsLayout[i4][2] - i2);
                            Globals.ScreenKbControlsLayout[i4][2] = i2;
                        }
                        if (Globals.ScreenKbControlsLayout[i4][c] < 0) {
                            int[] iArr3 = Globals.ScreenKbControlsLayout[i4];
                            iArr3[3] = iArr3[3] + (-Globals.ScreenKbControlsLayout[i4][c]);
                            Globals.ScreenKbControlsLayout[i4][c] = i;
                        }
                        if (Globals.ScreenKbControlsLayout[i4][3] > i3) {
                            int[] iArr4 = Globals.ScreenKbControlsLayout[i4];
                            iArr4[c] = iArr4[c] - (Globals.ScreenKbControlsLayout[i4][3] - i3);
                            Globals.ScreenKbControlsLayout[i4][3] = i3;
                        }
                        this.imgs[i4] = new ImageView(this.p);
                        this.imgs[i4].setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                        this.imgs[i4].setScaleType(ImageView.ScaleType.MATRIX);
                        this.bmps[i4] = BitmapFactory.decodeResource(this.p.getResources(), this.buttons[i4]);
                        this.imgs[i4].setImageBitmap(this.bmps[i4]);
                        this.imgs[i4].setAlpha(128);
                        this.layout.addView(this.imgs[i4]);
                        Matrix matrix = new Matrix();
                        matrix.setRectToRect(new RectF(0.0f, 0.0f, (float) this.bmps[i4].getWidth(), (float) this.bmps[i4].getHeight()), new RectF((float) Globals.ScreenKbControlsLayout[i4][i], (float) Globals.ScreenKbControlsLayout[i4][c], (float) Globals.ScreenKbControlsLayout[i4][2], (float) Globals.ScreenKbControlsLayout[i4][3]), Matrix.ScaleToFit.FILL);
                        this.imgs[i4].setImageMatrix(matrix);
                    }
                    i4++;
                    i = 0;
                    c = 1;
                }
                this.boundary.bringToFront();
                int i7 = this.currentButton;
                if (i7 == -1) {
                    onKey((View) null, 4, (KeyEvent) null);
                } else {
                    setupButton(i7);
                }
            }

            /* access modifiers changed from: package-private */
            public void setupButton(int i) {
                String str;
                int i2;
                Matrix matrix = new Matrix();
                RectF rectF = new RectF(0.0f, 0.0f, (float) this.bmps[i].getWidth(), (float) this.bmps[i].getHeight());
                RectF rectF2 = new RectF((float) Globals.ScreenKbControlsLayout[i][0], (float) Globals.ScreenKbControlsLayout[i][1], (float) Globals.ScreenKbControlsLayout[i][2], (float) Globals.ScreenKbControlsLayout[i][3]);
                matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
                this.imgs[i].setImageMatrix(matrix);
                Matrix matrix2 = new Matrix();
                matrix2.setRectToRect(new RectF(0.0f, 0.0f, (float) this.boundaryBmp.getWidth(), (float) this.boundaryBmp.getHeight()), rectF2, Matrix.ScaleToFit.FILL);
                this.boundary.setImageMatrix(matrix2);
                if (i < 2 || i > 7) {
                    str = "";
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.p.getResources().getString(R.string.remap_screenkb_button));
                    sb.append(i - 2);
                    str = sb.toString();
                }
                if (i >= 2 && i - 2 < Globals.AppTouchscreenKeyboardKeysNames.length) {
                    str = Globals.AppTouchscreenKeyboardKeysNames[i2].replace("_", StringUtils.SPACE);
                }
                if (i == 0) {
                    str = "Joystick";
                }
                if (i == 1) {
                    str = "Text input";
                }
                if (i == 8) {
                    str = "Joystick 2";
                }
                if (i == 9) {
                    str = "Joystick 3";
                }
                MainActivity mainActivity = this.p;
                mainActivity.setText(this.p.getResources().getString(R.string.screenkb_custom_layout_help) + StringUtils.LF + str);
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    this.oldX = (int) motionEvent.getX();
                    this.oldY = (int) motionEvent.getY();
                    this.resizing = true;
                    int i = 0;
                    while (true) {
                        if (i >= Globals.ScreenKbControlsLayout.length) {
                            break;
                        } else if (Globals.ScreenKbControlsShown[i] && Globals.ScreenKbControlsLayout[i][0] <= this.oldX && Globals.ScreenKbControlsLayout[i][2] >= this.oldX && Globals.ScreenKbControlsLayout[i][1] <= this.oldY && Globals.ScreenKbControlsLayout[i][3] >= this.oldY) {
                            this.currentButton = i;
                            setupButton(this.currentButton);
                            this.resizing = false;
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                if (motionEvent.getAction() == 2) {
                    int x = ((int) motionEvent.getX()) - this.oldX;
                    int y = ((int) motionEvent.getY()) - this.oldY;
                    if (this.resizing) {
                        int i2 = x / 6;
                        int i3 = y / 6;
                        if (Globals.ScreenKbControlsLayout[this.currentButton][0] <= Globals.ScreenKbControlsLayout[this.currentButton][2] + (i2 * 2)) {
                            int[] iArr = Globals.ScreenKbControlsLayout[this.currentButton];
                            iArr[0] = iArr[0] - i2;
                            int[] iArr2 = Globals.ScreenKbControlsLayout[this.currentButton];
                            iArr2[2] = iArr2[2] + i2;
                        }
                        if (Globals.ScreenKbControlsLayout[this.currentButton][1] <= Globals.ScreenKbControlsLayout[this.currentButton][3] + (i3 * 2)) {
                            int[] iArr3 = Globals.ScreenKbControlsLayout[this.currentButton];
                            iArr3[1] = iArr3[1] + i3;
                            int[] iArr4 = Globals.ScreenKbControlsLayout[this.currentButton];
                            iArr4[3] = iArr4[3] - i3;
                        }
                        x = i2 * 6;
                        y = i3 * 6;
                    } else {
                        int[] iArr5 = Globals.ScreenKbControlsLayout[this.currentButton];
                        iArr5[0] = iArr5[0] + x;
                        int[] iArr6 = Globals.ScreenKbControlsLayout[this.currentButton];
                        iArr6[2] = iArr6[2] + x;
                        int[] iArr7 = Globals.ScreenKbControlsLayout[this.currentButton];
                        iArr7[1] = iArr7[1] + y;
                        int[] iArr8 = Globals.ScreenKbControlsLayout[this.currentButton];
                        iArr8[3] = iArr8[3] + y;
                    }
                    this.oldX += x;
                    this.oldY += y;
                    Matrix matrix = new Matrix();
                    RectF rectF = new RectF(0.0f, 0.0f, (float) this.bmps[this.currentButton].getWidth(), (float) this.bmps[this.currentButton].getHeight());
                    RectF rectF2 = new RectF((float) Globals.ScreenKbControlsLayout[this.currentButton][0], (float) Globals.ScreenKbControlsLayout[this.currentButton][1], (float) Globals.ScreenKbControlsLayout[this.currentButton][2], (float) Globals.ScreenKbControlsLayout[this.currentButton][3]);
                    matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
                    this.imgs[this.currentButton].setImageMatrix(matrix);
                    Matrix matrix2 = new Matrix();
                    matrix2.setRectToRect(new RectF(0.0f, 0.0f, (float) this.boundaryBmp.getWidth(), (float) this.boundaryBmp.getHeight()), rectF2, Matrix.ScaleToFit.FILL);
                    this.boundary.setImageMatrix(matrix2);
                }
                return true;
            }

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i != 4) {
                    return true;
                }
                this.p.getVideoLayout().removeView(this.layout);
                this.layout = null;
                SettingsMenu.goBack(this.p);
                return true;
            }
        }
    }

    static class ScreenKeyboardAdvanced extends SettingsMenu.Menu {
        ScreenKeyboardAdvanced() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.advanced);
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.screenkb_floating_joystick)};
            boolean[] zArr = {Globals.FloatingScreenJoystick};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(mainActivity.getResources().getString(R.string.advanced));
            builder.setMultiChoiceItems(charSequenceArr, zArr, new DialogInterface.OnMultiChoiceClickListener() {
                public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                    if (i == 0) {
                        Globals.FloatingScreenJoystick = z;
                    }
                }
            });
            builder.setPositiveButton(mainActivity.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    SettingsMenu.goBack(mainActivity);
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBack(mainActivity);
                }
            });
            AlertDialog create = builder.create();
            create.setOwnerActivity(mainActivity);
            create.show();
        }
    }
}
