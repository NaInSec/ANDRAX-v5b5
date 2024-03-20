package com.thecrackertechnology.dragonterminal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.thecrackertechnology.dragonterminal.SettingsMenu;
import com.thecrackertechnology.dragonterminal.xorg.R;
import java.util.ArrayList;
import java.util.Iterator;

class SettingsMenuMouse extends SettingsMenu {
    SettingsMenuMouse() {
    }

    static class MouseConfigMainMenu extends SettingsMenu.Menu {
        MouseConfigMainMenu() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.mouse_emulation);
        }

        /* access modifiers changed from: package-private */
        public boolean enabled() {
            return Globals.AppUsesMouse;
        }

        /* access modifiers changed from: package-private */
        public void run(MainActivity mainActivity) {
            showMenuOptionsList(mainActivity, new SettingsMenu.Menu[]{new DisplaySizeConfig(false), new LeftClickConfig(), new RightClickConfig(), new AdditionalMouseConfig(), new JoystickMouseConfig(), new TouchPressureMeasurementTool(), new CalibrateTouchscreenMenu(), new SettingsMenu.OkButton()});
        }
    }

    static class DisplaySizeConfig extends SettingsMenu.Menu {
        boolean firstStart;

        DisplaySizeConfig() {
            this.firstStart = false;
            this.firstStart = true;
        }

        DisplaySizeConfig(boolean z) {
            this.firstStart = false;
            this.firstStart = z;
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.display_size_mouse);
        }

        /* access modifiers changed from: package-private */
        public void run(MainActivity mainActivity) {
            int i;
            int i2;
            int i3;
            final int i4;
            CharSequence[] charSequenceArr;
            final MainActivity mainActivity2 = mainActivity;
            final int i5 = 3;
            CharSequence[] charSequenceArr2 = {mainActivity.getResources().getString(R.string.display_size_small), mainActivity.getResources().getString(R.string.display_size_small_touchpad), mainActivity.getResources().getString(R.string.display_size_large), mainActivity.getResources().getString(R.string.display_size_desktop)};
            if (!Globals.SwVideoMode) {
                charSequenceArr2 = new CharSequence[]{mainActivity.getResources().getString(R.string.display_size_small_touchpad), mainActivity.getResources().getString(R.string.display_size_large), mainActivity.getResources().getString(R.string.display_size_desktop)};
                i4 = 2;
                i3 = 1000;
                i2 = 0;
                i = 1;
            } else {
                i4 = 3;
                i3 = 0;
                i2 = 1;
                i = 2;
            }
            if (this.firstStart) {
                charSequenceArr2 = new CharSequence[]{mainActivity.getResources().getString(R.string.display_size_small), mainActivity.getResources().getString(R.string.display_size_small_touchpad), mainActivity.getResources().getString(R.string.display_size_large), mainActivity.getResources().getString(R.string.display_size_desktop), mainActivity.getResources().getString(R.string.show_more_options)};
                if (!Globals.SwVideoMode) {
                    charSequenceArr = new CharSequence[]{mainActivity.getResources().getString(R.string.display_size_small_touchpad), mainActivity.getResources().getString(R.string.display_size_large), mainActivity.getResources().getString(R.string.display_size_desktop), mainActivity.getResources().getString(R.string.show_more_options)};
                    AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity2);
                    builder.setTitle(R.string.display_size);
                    final int i6 = i;
                    final int i7 = i3;
                    final int i8 = i2;
                    final MainActivity mainActivity3 = mainActivity;
                    builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            if (i == i4) {
                                Globals.LeftClickMethod = 0;
                                Globals.RelativeMouseMovement = false;
                                Globals.ShowScreenUnderFinger = 0;
                                Globals.ForceHardwareMouse = true;
                            }
                            if (i == i6) {
                                Globals.LeftClickMethod = 0;
                                Globals.RelativeMouseMovement = false;
                                Globals.ShowScreenUnderFinger = 0;
                                Globals.ForceHardwareMouse = false;
                            }
                            if (i == i7) {
                                Globals.LeftClickMethod = 1;
                                Globals.RelativeMouseMovement = false;
                                Globals.ShowScreenUnderFinger = 1;
                                Globals.ForceHardwareMouse = false;
                            }
                            if (i == i8) {
                                Globals.LeftClickMethod = 7;
                                Globals.RelativeMouseMovement = true;
                                Globals.ShowScreenUnderFinger = 0;
                                Globals.ForceHardwareMouse = false;
                            }
                            if (i == i5) {
                                SettingsMenu.menuStack.clear();
                                new SettingsMenu.MainMenu().run(mainActivity3);
                                return;
                            }
                            SettingsMenu.goBack(mainActivity3);
                        }
                    });
                    builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialogInterface) {
                            SettingsMenu.goBack(mainActivity2);
                        }
                    });
                    AlertDialog create = builder.create();
                    create.setOwnerActivity(mainActivity2);
                    create.show();
                }
            }
            charSequenceArr = charSequenceArr2;
            i5 = 4;
            AlertDialog.Builder builder2 = new AlertDialog.Builder(mainActivity2);
            builder2.setTitle(R.string.display_size);
            final int i62 = i;
            final int i72 = i3;
            final int i82 = i2;
            final MainActivity mainActivity32 = mainActivity;
            builder2.setItems(charSequenceArr, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    if (i == i4) {
                        Globals.LeftClickMethod = 0;
                        Globals.RelativeMouseMovement = false;
                        Globals.ShowScreenUnderFinger = 0;
                        Globals.ForceHardwareMouse = true;
                    }
                    if (i == i62) {
                        Globals.LeftClickMethod = 0;
                        Globals.RelativeMouseMovement = false;
                        Globals.ShowScreenUnderFinger = 0;
                        Globals.ForceHardwareMouse = false;
                    }
                    if (i == i72) {
                        Globals.LeftClickMethod = 1;
                        Globals.RelativeMouseMovement = false;
                        Globals.ShowScreenUnderFinger = 1;
                        Globals.ForceHardwareMouse = false;
                    }
                    if (i == i82) {
                        Globals.LeftClickMethod = 7;
                        Globals.RelativeMouseMovement = true;
                        Globals.ShowScreenUnderFinger = 0;
                        Globals.ForceHardwareMouse = false;
                    }
                    if (i == i5) {
                        SettingsMenu.menuStack.clear();
                        new SettingsMenu.MainMenu().run(mainActivity32);
                        return;
                    }
                    SettingsMenu.goBack(mainActivity32);
                }
            });
            builder2.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBack(mainActivity2);
                }
            });
            AlertDialog create2 = builder2.create();
            create2.setOwnerActivity(mainActivity2);
            create2.show();
        }
    }

    static class LeftClickConfig extends SettingsMenu.Menu {
        LeftClickConfig() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.leftclick_question);
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.leftclick_normal), mainActivity.getResources().getString(R.string.leftclick_near_cursor), mainActivity.getResources().getString(R.string.leftclick_multitouch), mainActivity.getResources().getString(R.string.leftclick_pressure), mainActivity.getResources().getString(R.string.rightclick_key), mainActivity.getResources().getString(R.string.leftclick_timeout), mainActivity.getResources().getString(R.string.leftclick_tap), mainActivity.getResources().getString(R.string.leftclick_tap_or_timeout)};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(R.string.leftclick_question);
            builder.setSingleChoiceItems(charSequenceArr, Globals.LeftClickMethod, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    Globals.LeftClickMethod = i;
                    if (i == 4) {
                        mainActivity.getVideoLayout().setOnKeyListener(new KeyRemapToolMouseClick(mainActivity, true));
                    } else if (i == 5 || i == 7) {
                        LeftClickConfig.showLeftClickTimeoutConfig(mainActivity);
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

        static void showLeftClickTimeoutConfig(final MainActivity mainActivity) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.leftclick_timeout_time_0), mainActivity.getResources().getString(R.string.leftclick_timeout_time_1), mainActivity.getResources().getString(R.string.leftclick_timeout_time_2), mainActivity.getResources().getString(R.string.leftclick_timeout_time_3), mainActivity.getResources().getString(R.string.leftclick_timeout_time_4)};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(R.string.leftclick_timeout_time);
            builder.setSingleChoiceItems(charSequenceArr, Globals.LeftClickTimeout, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Globals.LeftClickTimeout = i;
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

    static class RightClickConfig extends SettingsMenu.Menu {
        RightClickConfig() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.rightclick_question);
        }

        /* access modifiers changed from: package-private */
        public boolean enabled() {
            return Globals.AppNeedsTwoButtonMouse;
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.rightclick_none), mainActivity.getResources().getString(R.string.rightclick_multitouch), mainActivity.getResources().getString(R.string.rightclick_pressure), mainActivity.getResources().getString(R.string.rightclick_key), mainActivity.getResources().getString(R.string.leftclick_timeout)};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(R.string.rightclick_question);
            builder.setSingleChoiceItems(charSequenceArr, Globals.RightClickMethod, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Globals.RightClickMethod = i;
                    dialogInterface.dismiss();
                    if (i == 3) {
                        mainActivity.getVideoLayout().setOnKeyListener(new KeyRemapToolMouseClick(mainActivity, false));
                    } else if (i == 4) {
                        RightClickConfig.showRightClickTimeoutConfig(mainActivity);
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

        static void showRightClickTimeoutConfig(final MainActivity mainActivity) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.leftclick_timeout_time_0), mainActivity.getResources().getString(R.string.leftclick_timeout_time_1), mainActivity.getResources().getString(R.string.leftclick_timeout_time_2), mainActivity.getResources().getString(R.string.leftclick_timeout_time_3), mainActivity.getResources().getString(R.string.leftclick_timeout_time_4)};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(R.string.leftclick_timeout_time);
            builder.setSingleChoiceItems(charSequenceArr, Globals.RightClickTimeout, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Globals.RightClickTimeout = i;
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

    public static class KeyRemapToolMouseClick implements View.OnKeyListener {
        boolean leftClick;
        MainActivity p;

        public KeyRemapToolMouseClick(MainActivity mainActivity, boolean z) {
            this.p = mainActivity;
            MainActivity mainActivity2 = this.p;
            mainActivity2.setText(mainActivity2.getResources().getString(R.string.remap_hwkeys_press));
            this.leftClick = z;
        }

        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            this.p.getVideoLayout().setOnKeyListener((View.OnKeyListener) null);
            int i2 = 0;
            if (i < 0) {
                i = 0;
            }
            if (i <= 255) {
                i2 = i;
            }
            if (this.leftClick) {
                Globals.LeftClickKey = i2;
            } else {
                Globals.RightClickKey = i2;
            }
            SettingsMenu.goBack(this.p);
            return true;
        }
    }

    static class AdditionalMouseConfig extends SettingsMenu.Menu {
        AdditionalMouseConfig() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.advanced);
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.mouse_hover_jitter_filter), mainActivity.getResources().getString(R.string.mouse_joystickmouse), mainActivity.getResources().getString(R.string.click_with_dpadcenter), mainActivity.getResources().getString(R.string.mouse_relative), mainActivity.getResources().getString(R.string.mouse_gyroscope_mouse), mainActivity.getResources().getString(R.string.mouse_finger_hover), mainActivity.getResources().getString(R.string.mouse_subframe_touch_events)};
            boolean[] zArr = {Globals.HoverJitterFilter, Globals.MoveMouseWithJoystick, Globals.ClickMouseWithDpad, Globals.RelativeMouseMovement, Globals.MoveMouseWithGyroscope, Globals.FingerHover, Globals.GenerateSubframeTouchEvents};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(mainActivity.getResources().getString(R.string.advanced));
            builder.setMultiChoiceItems(charSequenceArr, zArr, new DialogInterface.OnMultiChoiceClickListener() {
                public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                    if (i == 0) {
                        Globals.HoverJitterFilter = z;
                    }
                    if (i == 1) {
                        Globals.MoveMouseWithJoystick = z;
                    }
                    if (i == 2) {
                        Globals.ClickMouseWithDpad = z;
                    }
                    if (i == 3) {
                        Globals.RelativeMouseMovement = z;
                    }
                    if (i == 4) {
                        Globals.MoveMouseWithGyroscope = z;
                    }
                    if (i == 5) {
                        Globals.FingerHover = z;
                    }
                    if (i == 6) {
                        Globals.GenerateSubframeTouchEvents = z;
                    }
                }
            });
            builder.setPositiveButton(mainActivity.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    AdditionalMouseConfig.showGyroscopeMouseMovementConfig(mainActivity);
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

        static void showGyroscopeMouseMovementConfig(final MainActivity mainActivity) {
            if (!Globals.MoveMouseWithGyroscope) {
                showRelativeMouseMovementConfig(mainActivity);
                return;
            }
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.accel_veryslow), mainActivity.getResources().getString(R.string.accel_slow), mainActivity.getResources().getString(R.string.accel_medium), mainActivity.getResources().getString(R.string.accel_fast), mainActivity.getResources().getString(R.string.accel_veryfast)};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(R.string.mouse_gyroscope_mouse_sensitivity);
            builder.setSingleChoiceItems(charSequenceArr, Globals.MoveMouseWithGyroscopeSpeed, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Globals.MoveMouseWithGyroscopeSpeed = i;
                    dialogInterface.dismiss();
                    AdditionalMouseConfig.showRelativeMouseMovementConfig(mainActivity);
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

        static void showRelativeMouseMovementConfig(final MainActivity mainActivity) {
            if (!Globals.RelativeMouseMovement) {
                SettingsMenu.goBack(mainActivity);
                return;
            }
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.accel_veryslow), mainActivity.getResources().getString(R.string.accel_slow), mainActivity.getResources().getString(R.string.accel_medium), mainActivity.getResources().getString(R.string.accel_fast), mainActivity.getResources().getString(R.string.accel_veryfast)};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(R.string.mouse_relative_speed);
            builder.setSingleChoiceItems(charSequenceArr, Globals.RelativeMouseMovementSpeed, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Globals.RelativeMouseMovementSpeed = i;
                    dialogInterface.dismiss();
                    AdditionalMouseConfig.showRelativeMouseMovementConfig1(mainActivity);
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

        static void showRelativeMouseMovementConfig1(final MainActivity mainActivity) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.none), mainActivity.getResources().getString(R.string.accel_slow), mainActivity.getResources().getString(R.string.accel_medium), mainActivity.getResources().getString(R.string.accel_fast)};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(R.string.mouse_relative_accel);
            builder.setSingleChoiceItems(charSequenceArr, Globals.RelativeMouseMovementAccel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Globals.RelativeMouseMovementAccel = i;
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

    static class JoystickMouseConfig extends SettingsMenu.Menu {
        JoystickMouseConfig() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.mouse_joystickmousespeed);
        }

        /* access modifiers changed from: package-private */
        public boolean enabled() {
            return Globals.MoveMouseWithJoystick;
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.accel_slow), mainActivity.getResources().getString(R.string.accel_medium), mainActivity.getResources().getString(R.string.accel_fast)};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(R.string.mouse_joystickmousespeed);
            builder.setSingleChoiceItems(charSequenceArr, Globals.MoveMouseWithJoystickSpeed, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Globals.MoveMouseWithJoystickSpeed = i;
                    dialogInterface.dismiss();
                    JoystickMouseConfig.showJoystickMouseAccelConfig(mainActivity);
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

        static void showJoystickMouseAccelConfig(final MainActivity mainActivity) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.none), mainActivity.getResources().getString(R.string.accel_slow), mainActivity.getResources().getString(R.string.accel_medium), mainActivity.getResources().getString(R.string.accel_fast)};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(R.string.mouse_joystickmouseaccel);
            builder.setSingleChoiceItems(charSequenceArr, Globals.MoveMouseWithJoystickAccel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Globals.MoveMouseWithJoystickAccel = i;
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

    static class TouchPressureMeasurementTool extends SettingsMenu.Menu {
        TouchPressureMeasurementTool() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.measurepressure);
        }

        /* access modifiers changed from: package-private */
        public boolean enabled() {
            return Globals.RightClickMethod == 2 || Globals.LeftClickMethod == 3;
        }

        /* access modifiers changed from: package-private */
        public void run(MainActivity mainActivity) {
            mainActivity.setText(mainActivity.getResources().getString(R.string.measurepressure_touchplease));
            mainActivity.getVideoLayout().setOnTouchListener(new TouchMeasurementTool(mainActivity));
        }

        public static class TouchMeasurementTool implements View.OnTouchListener {
            static final int maxEventAmount = 100;
            ArrayList<Integer> force = new ArrayList<>();
            MainActivity p;
            ArrayList<Integer> radius = new ArrayList<>();

            public TouchMeasurementTool(MainActivity mainActivity) {
                this.p = mainActivity;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                this.force.add(new Integer((int) (((double) motionEvent.getPressure()) * 1000.0d)));
                this.radius.add(new Integer((int) (((double) motionEvent.getSize()) * 1000.0d)));
                MainActivity mainActivity = this.p;
                Resources resources = mainActivity.getResources();
                int i = R.string.measurepressure_response;
                ArrayList<Integer> arrayList = this.force;
                ArrayList<Integer> arrayList2 = this.radius;
                mainActivity.setText(resources.getString(i, new Object[]{arrayList.get(arrayList.size() - 1), arrayList2.get(arrayList2.size() - 1)}));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException unused) {
                }
                if (this.force.size() >= 100) {
                    this.p.getVideoLayout().setOnTouchListener((View.OnTouchListener) null);
                    Globals.ClickScreenPressure = getAverageForce();
                    Globals.ClickScreenTouchspotSize = getAverageRadius();
                    Log.i("SDL", "SDL: measured average force " + Globals.ClickScreenPressure + " radius " + Globals.ClickScreenTouchspotSize);
                    SettingsMenu.goBack(this.p);
                }
                return true;
            }

            /* access modifiers changed from: package-private */
            public int getAverageForce() {
                Iterator<Integer> it = this.force.iterator();
                int i = 0;
                while (it.hasNext()) {
                    i += it.next().intValue();
                }
                return i / this.force.size();
            }

            /* access modifiers changed from: package-private */
            public int getAverageRadius() {
                Iterator<Integer> it = this.radius.iterator();
                int i = 0;
                while (it.hasNext()) {
                    i += it.next().intValue();
                }
                return i / this.radius.size();
            }
        }
    }

    static class CalibrateTouchscreenMenu extends SettingsMenu.Menu {
        CalibrateTouchscreenMenu() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.calibrate_touchscreen);
        }

        /* access modifiers changed from: package-private */
        public void run(MainActivity mainActivity) {
            mainActivity.setText(mainActivity.getResources().getString(R.string.calibrate_touchscreen_touch));
            Globals.TouchscreenCalibration[0] = 0;
            Globals.TouchscreenCalibration[1] = 0;
            Globals.TouchscreenCalibration[2] = 0;
            Globals.TouchscreenCalibration[3] = 0;
            ScreenEdgesCalibrationTool screenEdgesCalibrationTool = new ScreenEdgesCalibrationTool(mainActivity);
            mainActivity.getVideoLayout().setOnTouchListener(screenEdgesCalibrationTool);
            mainActivity.getVideoLayout().setOnKeyListener(screenEdgesCalibrationTool);
        }

        static class ScreenEdgesCalibrationTool implements View.OnTouchListener, View.OnKeyListener {
            Bitmap bmp;
            ImageView img = new ImageView(this.p);
            MainActivity p;

            public ScreenEdgesCalibrationTool(MainActivity mainActivity) {
                this.p = mainActivity;
                this.img.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                this.img.setScaleType(ImageView.ScaleType.MATRIX);
                this.bmp = BitmapFactory.decodeResource(this.p.getResources(), R.drawable.calibrate);
                this.img.setImageBitmap(this.bmp);
                Matrix matrix = new Matrix();
                matrix.setRectToRect(new RectF(0.0f, 0.0f, (float) this.bmp.getWidth(), (float) this.bmp.getHeight()), new RectF((float) Globals.TouchscreenCalibration[0], (float) Globals.TouchscreenCalibration[1], (float) Globals.TouchscreenCalibration[2], (float) Globals.TouchscreenCalibration[3]), Matrix.ScaleToFit.FILL);
                this.img.setImageMatrix(matrix);
                this.p.getVideoLayout().addView(this.img);
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (Globals.TouchscreenCalibration[0] == Globals.TouchscreenCalibration[1] && Globals.TouchscreenCalibration[1] == Globals.TouchscreenCalibration[2] && Globals.TouchscreenCalibration[2] == Globals.TouchscreenCalibration[3]) {
                    Globals.TouchscreenCalibration[0] = (int) motionEvent.getX();
                    Globals.TouchscreenCalibration[1] = (int) motionEvent.getY();
                    Globals.TouchscreenCalibration[2] = (int) motionEvent.getX();
                    Globals.TouchscreenCalibration[3] = (int) motionEvent.getY();
                }
                if (motionEvent.getX() < ((float) Globals.TouchscreenCalibration[0])) {
                    Globals.TouchscreenCalibration[0] = (int) motionEvent.getX();
                }
                if (motionEvent.getY() < ((float) Globals.TouchscreenCalibration[1])) {
                    Globals.TouchscreenCalibration[1] = (int) motionEvent.getY();
                }
                if (motionEvent.getX() > ((float) Globals.TouchscreenCalibration[2])) {
                    Globals.TouchscreenCalibration[2] = (int) motionEvent.getX();
                }
                if (motionEvent.getY() > ((float) Globals.TouchscreenCalibration[3])) {
                    Globals.TouchscreenCalibration[3] = (int) motionEvent.getY();
                }
                Matrix matrix = new Matrix();
                matrix.setRectToRect(new RectF(0.0f, 0.0f, (float) this.bmp.getWidth(), (float) this.bmp.getHeight()), new RectF((float) Globals.TouchscreenCalibration[0], (float) Globals.TouchscreenCalibration[1], (float) Globals.TouchscreenCalibration[2], (float) Globals.TouchscreenCalibration[3]), Matrix.ScaleToFit.FILL);
                this.img.setImageMatrix(matrix);
                return true;
            }

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                this.p.getVideoLayout().setOnTouchListener((View.OnTouchListener) null);
                this.p.getVideoLayout().setOnKeyListener((View.OnKeyListener) null);
                this.p.getVideoLayout().removeView(this.img);
                SettingsMenu.goBack(this.p);
                return true;
            }
        }
    }
}
