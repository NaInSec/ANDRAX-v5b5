package com.thecrackertechnology.dragonterminal;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import com.thecrackertechnology.dragonterminal.xorg.NeoXorgViewClient;
import com.thecrackertechnology.dragonterminal.xorg.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.zip.GZIPInputStream;
import org.apache.commons.lang3.StringUtils;

public class Settings {
    static final int SDL_ANDROID_CONFIG_VIDEO_DEPTH_BPP = 0;
    static final int SETTINGS_FILE_VERSION = 5;
    static String SettingsFileName = "libsdl-settings.cfg";
    static boolean convertButtonSizeFromOldSdlVersion = false;
    static boolean settingsChanged = false;
    static boolean settingsLoaded = false;

    public static native void nativeChdir(String str);

    public static native int nativeChmod(String str, int i);

    private static native int nativeGetKeymapKey(int i);

    private static native int nativeGetKeymapKeyMultitouchGesture(int i);

    private static native int nativeGetKeymapKeyScreenKb(int i);

    private static native void nativeInitKeymap();

    private static native void nativeSetAccelerometerSettings(int i, int i2);

    private static native void nativeSetAccelerometerUsed();

    private static native void nativeSetCompatibilityHacks();

    public static native void nativeSetEnv(String str, String str2);

    private static native void nativeSetJoystickUsed(int i);

    private static native void nativeSetKeymapKey(int i, int i2);

    private static native void nativeSetKeymapKeyMultitouchGesture(int i, int i2);

    private static native void nativeSetKeymapKeyScreenKb(int i, int i2);

    private static native void nativeSetMouseUsed(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23);

    private static native void nativeSetMultitouchGestureSensitivity(int i);

    private static native void nativeSetMultitouchUsed();

    private static native void nativeSetScreenKbKeyLayout(int i, int i2, int i3, int i4, int i5);

    private static native void nativeSetScreenKbKeyUsed(int i, int i2);

    private static native void nativeSetTouchscreenCalibration(int i, int i2, int i3, int i4);

    private static native void nativeSetTouchscreenKeyboardUsed();

    private static native void nativeSetVideoDepth(int i, int i2, int i3);

    private static native void nativeSetVideoForceSoftwareMode();

    private static native void nativeSetVideoLinearFilter();

    private static native void nativeSetVideoMultithreaded();

    private static native void nativeSetupScreenKeyboard(int i, int i2, int i3, int i4, int i5, int i6);

    private static native void nativeSetupScreenKeyboardButtons(byte[] bArr);

    static void Save(NeoXorgViewClient neoXorgViewClient) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(neoXorgViewClient.getContext().openFileOutput(SettingsFileName, 0));
            objectOutputStream.writeInt(5);
            objectOutputStream.writeBoolean(Globals.DownloadToSdcard);
            objectOutputStream.writeBoolean(Globals.PhoneHasArrowKeys);
            objectOutputStream.writeBoolean(false);
            objectOutputStream.writeBoolean(Globals.UseAccelerometerAsArrowKeys);
            objectOutputStream.writeBoolean(Globals.UseTouchscreenKeyboard);
            objectOutputStream.writeInt(Globals.TouchscreenKeyboardSize);
            objectOutputStream.writeInt(Globals.AccelerometerSensitivity);
            objectOutputStream.writeInt(Globals.AccelerometerCenterPos);
            objectOutputStream.writeInt(0);
            objectOutputStream.writeInt(Globals.AudioBufferConfig);
            objectOutputStream.writeInt(Globals.TouchscreenKeyboardTheme);
            objectOutputStream.writeInt(Globals.RightClickMethod);
            objectOutputStream.writeInt(Globals.ShowScreenUnderFinger);
            objectOutputStream.writeInt(Globals.LeftClickMethod);
            objectOutputStream.writeBoolean(Globals.MoveMouseWithJoystick);
            objectOutputStream.writeBoolean(Globals.ClickMouseWithDpad);
            objectOutputStream.writeInt(Globals.ClickScreenPressure);
            objectOutputStream.writeInt(Globals.ClickScreenTouchspotSize);
            objectOutputStream.writeBoolean(Globals.KeepAspectRatio);
            objectOutputStream.writeInt(Globals.MoveMouseWithJoystickSpeed);
            objectOutputStream.writeInt(Globals.MoveMouseWithJoystickAccel);
            objectOutputStream.writeInt(255);
            for (int i = 0; i < 255; i++) {
                objectOutputStream.writeInt(Globals.RemapHwKeycode[i]);
            }
            objectOutputStream.writeInt(Globals.RemapScreenKbKeycode.length);
            for (int writeInt : Globals.RemapScreenKbKeycode) {
                objectOutputStream.writeInt(writeInt);
            }
            objectOutputStream.writeInt(Globals.ScreenKbControlsShown.length);
            for (boolean writeBoolean : Globals.ScreenKbControlsShown) {
                objectOutputStream.writeBoolean(writeBoolean);
            }
            objectOutputStream.writeInt(Globals.TouchscreenKeyboardTransparency);
            objectOutputStream.writeInt(Globals.RemapMultitouchGestureKeycode.length);
            for (int i2 = 0; i2 < Globals.RemapMultitouchGestureKeycode.length; i2++) {
                objectOutputStream.writeInt(Globals.RemapMultitouchGestureKeycode[i2]);
                objectOutputStream.writeBoolean(Globals.MultitouchGesturesUsed[i2]);
            }
            objectOutputStream.writeInt(Globals.MultitouchGestureSensitivity);
            for (int writeInt2 : Globals.TouchscreenCalibration) {
                objectOutputStream.writeInt(writeInt2);
            }
            objectOutputStream.writeInt(Globals.DataDir.length());
            for (int i3 = 0; i3 < Globals.DataDir.length(); i3++) {
                objectOutputStream.writeChar(Globals.DataDir.charAt(i3));
            }
            objectOutputStream.writeInt(Globals.CommandLine.length());
            for (int i4 = 0; i4 < Globals.CommandLine.length(); i4++) {
                objectOutputStream.writeChar(Globals.CommandLine.charAt(i4));
            }
            objectOutputStream.writeInt(Globals.ScreenKbControlsLayout.length);
            for (int i5 = 0; i5 < Globals.ScreenKbControlsLayout.length; i5++) {
                for (int i6 = 0; i6 < 4; i6++) {
                    objectOutputStream.writeInt(Globals.ScreenKbControlsLayout[i5][i6]);
                }
            }
            objectOutputStream.writeInt(Globals.LeftClickKey);
            objectOutputStream.writeInt(Globals.RightClickKey);
            objectOutputStream.writeBoolean(Globals.VideoLinearFilter);
            objectOutputStream.writeInt(Globals.LeftClickTimeout);
            objectOutputStream.writeInt(Globals.RightClickTimeout);
            objectOutputStream.writeBoolean(Globals.RelativeMouseMovement);
            objectOutputStream.writeInt(Globals.RelativeMouseMovementSpeed);
            objectOutputStream.writeInt(Globals.RelativeMouseMovementAccel);
            objectOutputStream.writeBoolean(Globals.MultiThreadedVideo);
            objectOutputStream.writeInt(Globals.OptionalDataDownload.length);
            for (boolean writeBoolean2 : Globals.OptionalDataDownload) {
                objectOutputStream.writeBoolean(writeBoolean2);
            }
            objectOutputStream.writeBoolean(false);
            objectOutputStream.writeInt(Globals.TouchscreenKeyboardDrawSize);
            objectOutputStream.writeFloat(0.0f);
            objectOutputStream.writeFloat(0.0f);
            objectOutputStream.writeFloat(0.0f);
            objectOutputStream.writeFloat(0.0f);
            objectOutputStream.writeFloat(0.0f);
            objectOutputStream.writeFloat(0.0f);
            objectOutputStream.writeFloat(0.0f);
            objectOutputStream.writeFloat(0.0f);
            objectOutputStream.writeFloat(0.0f);
            objectOutputStream.writeBoolean(Globals.OuyaEmulation);
            objectOutputStream.writeBoolean(Globals.HoverJitterFilter);
            objectOutputStream.writeBoolean(Globals.MoveMouseWithGyroscope);
            objectOutputStream.writeInt(Globals.MoveMouseWithGyroscopeSpeed);
            objectOutputStream.writeBoolean(Globals.FingerHover);
            objectOutputStream.writeBoolean(Globals.FloatingScreenJoystick);
            objectOutputStream.writeBoolean(Globals.GenerateSubframeTouchEvents);
            objectOutputStream.writeInt(Globals.VideoDepthBpp);
            objectOutputStream.writeBoolean(Globals.HorizontalOrientation);
            objectOutputStream.writeBoolean(Globals.ImmersiveMode);
            objectOutputStream.writeBoolean(Globals.AutoDetectOrientation);
            objectOutputStream.writeBoolean(Globals.TvBorders);
            objectOutputStream.writeBoolean(Globals.ForceHardwareMouse);
            objectOutputStream.close();
            settingsLoaded = true;
        } catch (FileNotFoundException | IOException | SecurityException unused) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:140:0x044b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void Load(com.thecrackertechnology.dragonterminal.xorg.NeoXorgViewClient r11) {
        /*
            boolean r0 = settingsLoaded
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            java.lang.String r0 = "SDL"
            java.lang.String r1 = "libSDL: Settings.Load(): enter"
            android.util.Log.i(r0, r1)
            nativeInitKeymap()
            r1 = 0
            r2 = 0
        L_0x0011:
            r3 = 255(0xff, float:3.57E-43)
            if (r2 >= r3) goto L_0x0035
            int r3 = nativeGetKeymapKey(r2)
            r4 = 0
            r5 = 0
        L_0x001b:
            java.lang.Integer[] r6 = com.thecrackertechnology.dragonterminal.SDL_Keys.values
            int r6 = r6.length
            if (r4 >= r6) goto L_0x002e
            java.lang.Integer[] r6 = com.thecrackertechnology.dragonterminal.SDL_Keys.values
            r6 = r6[r4]
            int r6 = r6.intValue()
            if (r6 != r3) goto L_0x002b
            r5 = r4
        L_0x002b:
            int r4 = r4 + 1
            goto L_0x001b
        L_0x002e:
            int[] r3 = com.thecrackertechnology.dragonterminal.Globals.RemapHwKeycode
            r3[r2] = r5
            int r2 = r2 + 1
            goto L_0x0011
        L_0x0035:
            r2 = 0
        L_0x0036:
            int[] r3 = com.thecrackertechnology.dragonterminal.Globals.RemapScreenKbKeycode
            int r3 = r3.length
            if (r2 >= r3) goto L_0x005b
            int r3 = nativeGetKeymapKeyScreenKb(r2)
            r4 = 0
            r5 = 0
        L_0x0041:
            java.lang.Integer[] r6 = com.thecrackertechnology.dragonterminal.SDL_Keys.values
            int r6 = r6.length
            if (r4 >= r6) goto L_0x0054
            java.lang.Integer[] r6 = com.thecrackertechnology.dragonterminal.SDL_Keys.values
            r6 = r6[r4]
            int r6 = r6.intValue()
            if (r6 != r3) goto L_0x0051
            r5 = r4
        L_0x0051:
            int r4 = r4 + 1
            goto L_0x0041
        L_0x0054:
            int[] r3 = com.thecrackertechnology.dragonterminal.Globals.RemapScreenKbKeycode
            r3[r2] = r5
            int r2 = r2 + 1
            goto L_0x0036
        L_0x005b:
            boolean[] r2 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsShown
            boolean r3 = com.thecrackertechnology.dragonterminal.Globals.AppNeedsArrowKeys
            r4 = 1
            if (r3 != 0) goto L_0x0069
            boolean r3 = com.thecrackertechnology.dragonterminal.Globals.AppUsesJoystick
            if (r3 == 0) goto L_0x0067
            goto L_0x0069
        L_0x0067:
            r3 = 0
            goto L_0x006a
        L_0x0069:
            r3 = 1
        L_0x006a:
            r2[r1] = r3
            boolean[] r2 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsShown
            boolean r3 = com.thecrackertechnology.dragonterminal.Globals.AppNeedsTextInput
            r2[r4] = r3
            r2 = 2
            r3 = 2
        L_0x0074:
            boolean[] r5 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsShown
            int r5 = r5.length
            if (r3 >= r5) goto L_0x0089
            boolean[] r5 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsShown
            int r6 = r3 + -2
            int r7 = com.thecrackertechnology.dragonterminal.Globals.AppTouchscreenKeyboardKeysAmount
            if (r6 >= r7) goto L_0x0083
            r6 = 1
            goto L_0x0084
        L_0x0083:
            r6 = 0
        L_0x0084:
            r5[r3] = r6
            int r3 = r3 + 1
            goto L_0x0074
        L_0x0089:
            boolean r3 = com.thecrackertechnology.dragonterminal.Globals.AppUsesSecondJoystick
            if (r3 == 0) goto L_0x0093
            boolean[] r3 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsShown
            r5 = 8
            r3[r5] = r4
        L_0x0093:
            boolean r3 = com.thecrackertechnology.dragonterminal.Globals.AppUsesThirdJoystick
            if (r3 == 0) goto L_0x009d
            boolean[] r3 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsShown
            r5 = 9
            r3[r5] = r4
        L_0x009d:
            r3 = 0
        L_0x009e:
            int[] r5 = com.thecrackertechnology.dragonterminal.Globals.RemapMultitouchGestureKeycode
            int r5 = r5.length
            if (r3 >= r5) goto L_0x00c3
            int r5 = nativeGetKeymapKeyMultitouchGesture(r3)
            r6 = 0
            r7 = 0
        L_0x00a9:
            java.lang.Integer[] r8 = com.thecrackertechnology.dragonterminal.SDL_Keys.values
            int r8 = r8.length
            if (r6 >= r8) goto L_0x00bc
            java.lang.Integer[] r8 = com.thecrackertechnology.dragonterminal.SDL_Keys.values
            r8 = r8[r6]
            int r8 = r8.intValue()
            if (r8 != r5) goto L_0x00b9
            r7 = r6
        L_0x00b9:
            int r6 = r6 + 1
            goto L_0x00a9
        L_0x00bc:
            int[] r5 = com.thecrackertechnology.dragonterminal.Globals.RemapMultitouchGestureKeycode
            r5[r3] = r7
            int r3 = r3 + 1
            goto L_0x009e
        L_0x00c3:
            r3 = 0
        L_0x00c4:
            boolean[] r5 = com.thecrackertechnology.dragonterminal.Globals.MultitouchGesturesUsed
            int r5 = r5.length
            if (r3 >= r5) goto L_0x00d0
            boolean[] r5 = com.thecrackertechnology.dragonterminal.Globals.MultitouchGesturesUsed
            r5[r3] = r4
            int r3 = r3 + 1
            goto L_0x00c4
        L_0x00d0:
            r3 = 800(0x320, float:1.121E-42)
            r5 = 480(0x1e0, float:6.73E-43)
            android.util.DisplayMetrics r6 = new android.util.DisplayMetrics     // Catch:{ Exception -> 0x00e8 }
            r6.<init>()     // Catch:{ Exception -> 0x00e8 }
            android.view.WindowManager r7 = r11.getWindowManager()     // Catch:{ Exception -> 0x00e8 }
            android.view.Display r7 = r7.getDefaultDisplay()     // Catch:{ Exception -> 0x00e8 }
            r7.getMetrics(r6)     // Catch:{ Exception -> 0x00e8 }
            int r3 = r6.widthPixels     // Catch:{ Exception -> 0x00e8 }
            int r5 = r6.heightPixels     // Catch:{ Exception -> 0x00e8 }
        L_0x00e8:
            r6 = 0
        L_0x00e9:
            int[][] r7 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsLayout
            int r7 = r7.length
            if (r6 >= r7) goto L_0x0162
            int[][] r7 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsLayout
            r7 = r7[r6]
            r8 = r7[r1]
            float r8 = (float) r8
            float r9 = (float) r3
            r10 = 1145569280(0x44480000, float:800.0)
            float r9 = r9 / r10
            float r8 = r8 * r9
            int r8 = (int) r8
            r7[r1] = r8
            int[][] r7 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsLayout
            r7 = r7[r6]
            r8 = r7[r2]
            float r8 = (float) r8
            float r8 = r8 * r9
            int r8 = (int) r8
            r7[r2] = r8
            int[][] r7 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsLayout
            r7 = r7[r6]
            r8 = r7[r4]
            float r8 = (float) r8
            float r9 = (float) r5
            r10 = 1139802112(0x43f00000, float:480.0)
            float r9 = r9 / r10
            float r8 = r8 * r9
            int r8 = (int) r8
            r7[r4] = r8
            int[][] r7 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsLayout
            r7 = r7[r6]
            r8 = 3
            r10 = r7[r8]
            float r10 = (float) r10
            float r10 = r10 * r9
            int r9 = (int) r10
            r7[r8] = r9
            int[][] r7 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsLayout
            r7 = r7[r6]
            r7 = r7[r2]
            int[][] r9 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsLayout
            r9 = r9[r6]
            r9 = r9[r1]
            int r7 = r7 - r9
            int[][] r9 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsLayout
            r9 = r9[r6]
            r9 = r9[r8]
            int[][] r10 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsLayout
            r10 = r10[r6]
            r10 = r10[r4]
            int r9 = r9 - r10
            int r7 = java.lang.Math.min(r7, r9)
            int[][] r9 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsLayout
            r9 = r9[r6]
            int[][] r10 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsLayout
            r10 = r10[r6]
            r10 = r10[r1]
            int r10 = r10 + r7
            r9[r2] = r10
            int[][] r9 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsLayout
            r9 = r9[r6]
            int[][] r10 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsLayout
            r10 = r10[r6]
            r10 = r10[r4]
            int r10 = r10 + r7
            r9[r8] = r10
            int r6 = r6 + 1
            goto L_0x00e9
        L_0x0162:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "android.os.Build.MODEL: "
            r2.append(r3)
            java.lang.String r3 = android.os.Build.MODEL
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r0, r2)
            convertButtonSizeFromOldSdlVersion = r1
            r2 = 4
            java.io.ObjectInputStream r3 = new java.io.ObjectInputStream     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r6.<init>()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            android.content.Context r7 = r11.getContext()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            java.io.File r7 = r7.getFilesDir()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            java.lang.String r7 = r7.getAbsolutePath()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r6.append(r7)     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            java.lang.String r7 = "/"
            r6.append(r7)     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            java.lang.String r7 = SettingsFileName     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r6.append(r7)     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            java.lang.String r6 = r6.toString()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r5.<init>(r6)     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.<init>(r5)     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r6 = 5
            if (r5 != r6) goto L_0x03eb
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.DownloadToSdcard = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.PhoneHasArrowKeys = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.UseAccelerometerAsArrowKeys = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.UseTouchscreenKeyboard = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.TouchscreenKeyboardSize = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            convertButtonSizeFromOldSdlVersion = r4     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.AccelerometerSensitivity = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.AccelerometerCenterPos = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.AudioBufferConfig = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.TouchscreenKeyboardTheme = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.RightClickMethod = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.ShowScreenUnderFinger = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.LeftClickMethod = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.MoveMouseWithJoystick = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.ClickMouseWithDpad = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.ClickScreenPressure = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.ClickScreenTouchspotSize = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.KeepAspectRatio = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.MoveMouseWithJoystickSpeed = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.MoveMouseWithJoystickAccel = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r6 = 0
        L_0x022d:
            if (r6 >= r5) goto L_0x023a
            int[] r7 = com.thecrackertechnology.dragonterminal.Globals.RemapHwKeycode     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r8 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r7[r6] = r8     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r6 = r6 + 1
            goto L_0x022d
        L_0x023a:
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int[] r6 = com.thecrackertechnology.dragonterminal.Globals.RemapScreenKbKeycode     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r6 = r6.length     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            if (r5 != r6) goto L_0x03e5
            r5 = 0
        L_0x0244:
            int[] r6 = com.thecrackertechnology.dragonterminal.Globals.RemapScreenKbKeycode     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r6 = r6.length     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            if (r5 >= r6) goto L_0x0254
            int[] r6 = com.thecrackertechnology.dragonterminal.Globals.RemapScreenKbKeycode     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r7 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r6[r5] = r7     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r5 + 1
            goto L_0x0244
        L_0x0254:
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean[] r6 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsShown     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r6 = r6.length     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            if (r5 != r6) goto L_0x03df
            r5 = 0
        L_0x025e:
            boolean[] r6 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsShown     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r6 = r6.length     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            if (r5 >= r6) goto L_0x026e
            boolean[] r6 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsShown     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r7 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r6[r5] = r7     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r5 + 1
            goto L_0x025e
        L_0x026e:
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.TouchscreenKeyboardTransparency = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int[] r6 = com.thecrackertechnology.dragonterminal.Globals.RemapMultitouchGestureKeycode     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r6 = r6.length     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            if (r5 != r6) goto L_0x03d9
            r5 = 0
        L_0x027e:
            int[] r6 = com.thecrackertechnology.dragonterminal.Globals.RemapMultitouchGestureKeycode     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r6 = r6.length     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            if (r5 >= r6) goto L_0x0296
            int[] r6 = com.thecrackertechnology.dragonterminal.Globals.RemapMultitouchGestureKeycode     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r7 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r6[r5] = r7     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean[] r6 = com.thecrackertechnology.dragonterminal.Globals.MultitouchGesturesUsed     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r7 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r6[r5] = r7     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r5 + 1
            goto L_0x027e
        L_0x0296:
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.MultitouchGestureSensitivity = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r5 = 0
        L_0x029d:
            int[] r6 = com.thecrackertechnology.dragonterminal.Globals.TouchscreenCalibration     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r6 = r6.length     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            if (r5 >= r6) goto L_0x02ad
            int[] r6 = com.thecrackertechnology.dragonterminal.Globals.TouchscreenCalibration     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r7 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r6[r5] = r7     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r5 + 1
            goto L_0x029d
        L_0x02ad:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r5.<init>()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r6 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r7 = 0
        L_0x02b7:
            if (r7 >= r6) goto L_0x02c3
            char r8 = r3.readChar()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r5.append(r8)     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r7 = r7 + 1
            goto L_0x02b7
        L_0x02c3:
            java.lang.String r5 = r5.toString()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.DataDir = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r5.<init>()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r6 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r7 = 0
        L_0x02d3:
            if (r7 >= r6) goto L_0x02df
            char r8 = r3.readChar()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r5.append(r8)     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r7 = r7 + 1
            goto L_0x02d3
        L_0x02df:
            java.lang.String r5 = r5.toString()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.CommandLine = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int[][] r6 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsLayout     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r6 = r6.length     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            if (r5 != r6) goto L_0x03d3
            r5 = 0
        L_0x02ef:
            int[][] r6 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsLayout     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r6 = r6.length     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            if (r5 >= r6) goto L_0x0307
            r6 = 0
        L_0x02f5:
            if (r6 >= r2) goto L_0x0304
            int[][] r7 = com.thecrackertechnology.dragonterminal.Globals.ScreenKbControlsLayout     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r7 = r7[r5]     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r8 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r7[r6] = r8     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r6 = r6 + 1
            goto L_0x02f5
        L_0x0304:
            int r5 = r5 + 1
            goto L_0x02ef
        L_0x0307:
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.LeftClickKey = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.RightClickKey = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.VideoLinearFilter = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.LeftClickTimeout = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.RightClickTimeout = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.RelativeMouseMovement = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.RelativeMouseMovementSpeed = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.RelativeMouseMovementAccel = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.MultiThreadedVideo = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean[] r5 = new boolean[r5]     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.OptionalDataDownload = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r5 = 0
        L_0x0346:
            boolean[] r6 = com.thecrackertechnology.dragonterminal.Globals.OptionalDataDownload     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r6 = r6.length     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            if (r5 >= r6) goto L_0x0356
            boolean[] r6 = com.thecrackertechnology.dragonterminal.Globals.OptionalDataDownload     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r7 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r6[r5] = r7     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r5 + 1
            goto L_0x0346
        L_0x0356:
            r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.TouchscreenKeyboardDrawSize = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.readFloat()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.readFloat()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.readFloat()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.readFloat()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.readFloat()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.readFloat()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.readFloat()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.readFloat()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.readFloat()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.OuyaEmulation = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.HoverJitterFilter = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.MoveMouseWithGyroscope = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.MoveMouseWithGyroscopeSpeed = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.FingerHover = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.FloatingScreenJoystick = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.GenerateSubframeTouchEvents = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            int r5 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.VideoDepthBpp = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.HorizontalOrientation = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.ImmersiveMode = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.AutoDetectOrientation = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.TvBorders = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            boolean r5 = r3.readBoolean()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            com.thecrackertechnology.dragonterminal.Globals.ForceHardwareMouse = r5     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            settingsLoaded = r4     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            java.lang.String r5 = "libSDL: Settings.Load(): loaded settings successfully"
            android.util.Log.i(r0, r5)     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.close()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            return
        L_0x03d3:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.<init>()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            throw r3     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
        L_0x03d9:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.<init>()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            throw r3     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
        L_0x03df:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.<init>()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            throw r3     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
        L_0x03e5:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.<init>()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            throw r3     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
        L_0x03eb:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            r3.<init>()     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
            throw r3     // Catch:{ FileNotFoundException -> 0x042e, SecurityException -> 0x0418, IOException -> 0x03f1 }
        L_0x03f1:
            r3 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "libSDL: settings file cannot be read: "
            r5.append(r6)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            android.util.Log.i(r0, r3)
            DeleteFilesOnUpgrade(r11)
            boolean r3 = convertButtonSizeFromOldSdlVersion
            if (r3 == 0) goto L_0x0443
            int r3 = com.thecrackertechnology.dragonterminal.Globals.TouchscreenKeyboardSize
            int r3 = r3 + r4
            if (r3 >= r2) goto L_0x0443
            int r2 = com.thecrackertechnology.dragonterminal.Globals.TouchscreenKeyboardSize
            int r2 = r2 + r4
            com.thecrackertechnology.dragonterminal.Globals.TouchscreenKeyboardSize = r2
            goto L_0x0443
        L_0x0418:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "libSDL: settings file cannot be opened: "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            android.util.Log.i(r0, r2)
            goto L_0x0443
        L_0x042e:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "libSDL: settings file not found: "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            android.util.Log.i(r0, r2)
        L_0x0443:
            java.lang.String r2 = com.thecrackertechnology.dragonterminal.Globals.DataDir
            int r2 = r2.length()
            if (r2 != 0) goto L_0x0495
            java.lang.String r2 = android.os.Environment.getExternalStorageState()
            java.lang.String r3 = "mounted"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0476
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "libSDL: SD card or external storage is not mounted (state "
            r2.append(r3)
            java.lang.String r3 = android.os.Environment.getExternalStorageState()
            r2.append(r3)
            java.lang.String r3 = "), switching to the internal storage."
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r0, r2)
            com.thecrackertechnology.dragonterminal.Globals.DownloadToSdcard = r1
        L_0x0476:
            boolean r1 = com.thecrackertechnology.dragonterminal.Globals.DownloadToSdcard
            if (r1 == 0) goto L_0x0487
            com.thecrackertechnology.dragonterminal.Settings$SdcardAppPath r1 = com.thecrackertechnology.dragonterminal.Settings.SdcardAppPath.get()
            android.content.Context r2 = r11.getContext()
            java.lang.String r1 = r1.bestPath(r2)
            goto L_0x0493
        L_0x0487:
            android.content.Context r1 = r11.getContext()
            java.io.File r1 = r1.getFilesDir()
            java.lang.String r1 = r1.getAbsolutePath()
        L_0x0493:
            com.thecrackertechnology.dragonterminal.Globals.DataDir = r1
        L_0x0495:
            java.lang.String r1 = "libSDL: Settings.Load(): loading settings failed, running config dialog"
            android.util.Log.i(r0, r1)
            r11.initScreenOrientation()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.Settings.Load(com.thecrackertechnology.dragonterminal.xorg.NeoXorgViewClient):void");
    }

    public static boolean deleteRecursively(File file) {
        boolean z = true;
        if (file.isDirectory()) {
            String[] list = file.list();
            boolean z2 = true;
            for (String file2 : list) {
                if (!deleteRecursively(new File(file, file2))) {
                    z2 = false;
                }
            }
            z = z2;
        }
        if (!file.delete()) {
            return false;
        }
        return z;
    }

    public static boolean deleteRecursivelyAndLog(File file) {
        Log.v("SDL", "Deleting old file: " + file.getAbsolutePath() + " exists " + file.exists());
        boolean z = true;
        if (file.isDirectory()) {
            String[] list = file.list();
            boolean z2 = true;
            for (String file2 : list) {
                if (!deleteRecursively(new File(file, file2))) {
                    z2 = false;
                }
            }
            z = z2;
        }
        if (!file.delete()) {
            return false;
        }
        return z;
    }

    public static void DeleteFilesOnUpgrade(NeoXorgViewClient neoXorgViewClient) {
        for (String str : Globals.DeleteFilesOnUpgrade.split(StringUtils.SPACE)) {
            if (!str.equals("")) {
                deleteRecursivelyAndLog(new File(neoXorgViewClient.getContext().getFilesDir().getAbsolutePath() + "/" + str));
                for (String str2 : SdcardAppPath.get().allPaths(neoXorgViewClient.getContext())) {
                    deleteRecursivelyAndLog(new File(str2 + "/" + str));
                }
            }
        }
    }

    public static void DeleteSdlConfigOnUpgradeAndRestart(NeoXorgViewClient neoXorgViewClient) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(neoXorgViewClient.getContext().openFileOutput(SettingsFileName, 0));
            objectOutputStream.writeInt(-1);
            objectOutputStream.close();
        } catch (FileNotFoundException | IOException unused) {
        }
        new File(neoXorgViewClient.getContext().getFilesDir() + "/" + SettingsFileName).delete();
    }

    static void applyMouseEmulationOptions() {
        if (Globals.AppUsesMouse) {
            int i = Globals.RightClickMethod;
            int i2 = Globals.ShowScreenUnderFinger;
            int i3 = Globals.LeftClickMethod;
            boolean z = Globals.MoveMouseWithJoystick;
            boolean z2 = Globals.ClickMouseWithDpad;
            int i4 = Globals.ClickScreenPressure;
            int i5 = Globals.ClickScreenTouchspotSize;
            int i6 = Globals.MoveMouseWithJoystickSpeed;
            int i7 = Globals.MoveMouseWithJoystickAccel;
            int i8 = Globals.LeftClickKey;
            int i9 = Globals.RightClickKey;
            int i10 = Globals.LeftClickTimeout;
            int i11 = Globals.RightClickTimeout;
            boolean z3 = Globals.RelativeMouseMovement;
            int i12 = Globals.RelativeMouseMovementSpeed;
            int i13 = Globals.RelativeMouseMovementAccel;
            boolean z4 = Globals.ShowMouseCursor;
            boolean z5 = Globals.HoverJitterFilter;
            boolean z6 = Globals.RightMouseButtonLongPress;
            boolean z7 = Globals.MoveMouseWithGyroscope;
            nativeSetMouseUsed(i, i2, i3, z ? 1 : 0, z2 ? 1 : 0, i4, i5, i6, i7, i8, i9, i10, i11, z3 ? 1 : 0, i12, i13, z4 ? 1 : 0, z5 ? 1 : 0, z6 ? 1 : 0, z7 ? 1 : 0, Globals.MoveMouseWithGyroscopeSpeed, Globals.CompatibilityHacksForceScreenUpdateMouseClick ? 1 : 0, Globals.ScreenFollowsMouse ? 1 : 0);
        }
    }

    static void Apply(NeoXorgViewClient neoXorgViewClient) {
        setEnvVars(neoXorgViewClient);
        nativeSetVideoDepth(Globals.VideoDepthBpp, Globals.NeedGles2 ? 1 : 0, Globals.NeedGles3 ? 1 : 0);
        if (Globals.VideoLinearFilter) {
            nativeSetVideoLinearFilter();
        }
        if (Globals.CompatibilityHacksVideo) {
            Globals.MultiThreadedVideo = true;
            Globals.SwVideoMode = true;
            nativeSetCompatibilityHacks();
        }
        if (Globals.SwVideoMode) {
            nativeSetVideoForceSoftwareMode();
        }
        if (Globals.SwVideoMode && Globals.MultiThreadedVideo) {
            nativeSetVideoMultithreaded();
        }
        applyMouseEmulationOptions();
        nativeSetJoystickUsed(Globals.AppUsesThirdJoystick ? 3 : Globals.AppUsesSecondJoystick ? 2 : Globals.AppUsesJoystick ? 1 : 0);
        if (Globals.AppUsesAccelerometer) {
            nativeSetAccelerometerUsed();
        }
        if (Globals.AppUsesMultitouch) {
            nativeSetMultitouchUsed();
        }
        nativeSetAccelerometerSettings(Globals.AccelerometerSensitivity, Globals.AccelerometerCenterPos);
        if (Globals.UseTouchscreenKeyboard) {
            boolean z = false;
            for (boolean z2 : Globals.ScreenKbControlsShown) {
                if (z2) {
                    z = true;
                }
            }
            if (neoXorgViewClient.isRunningOnOUYA()) {
                z = false;
            }
            if (z) {
                nativeSetTouchscreenKeyboardUsed();
                nativeSetupScreenKeyboard(Globals.TouchscreenKeyboardSize, Globals.TouchscreenKeyboardDrawSize, Globals.TouchscreenKeyboardTheme, Globals.TouchscreenKeyboardTransparency, Globals.FloatingScreenJoystick ? 1 : 0, Globals.AppTouchscreenKeyboardKeysAmount);
                SetupTouchscreenKeyboardGraphics(neoXorgViewClient.getContext());
                for (int i = 0; i < Globals.RemapScreenKbKeycode.length; i++) {
                    nativeSetKeymapKeyScreenKb(i, SDL_Keys.values[Globals.RemapScreenKbKeycode[i]].intValue());
                }
                if (Globals.TouchscreenKeyboardSize == 4) {
                    for (int i2 = 0; i2 < Globals.ScreenKbControlsLayout.length; i2++) {
                        if (Globals.ScreenKbControlsLayout[i2][0] < Globals.ScreenKbControlsLayout[i2][2]) {
                            nativeSetScreenKbKeyLayout(i2, Globals.ScreenKbControlsLayout[i2][0], Globals.ScreenKbControlsLayout[i2][1], Globals.ScreenKbControlsLayout[i2][2], Globals.ScreenKbControlsLayout[i2][3]);
                        }
                    }
                }
                for (int i3 = 0; i3 < Globals.ScreenKbControlsShown.length; i3++) {
                    nativeSetScreenKbKeyUsed(i3, Globals.ScreenKbControlsShown[i3] ? 1 : 0);
                }
            } else {
                Globals.UseTouchscreenKeyboard = false;
            }
        }
        for (int i4 = 0; i4 < 255; i4++) {
            nativeSetKeymapKey(i4, SDL_Keys.values[Globals.RemapHwKeycode[i4]].intValue());
        }
        for (int i5 = 0; i5 < Globals.RemapMultitouchGestureKeycode.length; i5++) {
            nativeSetKeymapKeyMultitouchGesture(i5, Globals.MultitouchGesturesUsed[i5] ? SDL_Keys.values[Globals.RemapMultitouchGestureKeycode[i5]].intValue() : 0);
        }
        nativeSetMultitouchGestureSensitivity(Globals.MultitouchGestureSensitivity);
        if (Globals.TouchscreenCalibration[2] > Globals.TouchscreenCalibration[0]) {
            nativeSetTouchscreenCalibration(Globals.TouchscreenCalibration[0], Globals.TouchscreenCalibration[1], Globals.TouchscreenCalibration[2], Globals.TouchscreenCalibration[3]);
        }
    }

    static void setEnvVars(NeoXorgViewClient neoXorgViewClient) {
        String language = Locale.getDefault().getLanguage();
        if (Locale.getDefault().getCountry().length() > 0) {
            language = language + "_" + Locale.getDefault().getCountry();
        }
        Log.i("SDL", "libSDL: setting env LANGUAGE to '" + language + "'");
        nativeSetEnv("LANG", language);
        nativeSetEnv("LANGUAGE", language);
        nativeSetEnv("APPDIR", neoXorgViewClient.getContext().getFilesDir().getAbsolutePath());
        nativeSetEnv("SECURE_STORAGE_DIR", neoXorgViewClient.getContext().getFilesDir().getAbsolutePath());
        nativeSetEnv("DATADIR", Globals.DataDir);
        nativeSetEnv("UNSECURE_STORAGE_DIR", Globals.UnSecureDataDir);
        SdcardAppPath.get().setEnv(neoXorgViewClient.getContext());
        nativeSetEnv("HOME", Globals.HomeDir);
        nativeSetEnv("SDCARD", Environment.getExternalStorageDirectory().getAbsolutePath());
        nativeSetEnv("SDCARD_DOWNLOADS", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        nativeSetEnv("SDCARD_PICTURES", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        nativeSetEnv("SDCARD_MOVIES", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath());
        nativeSetEnv("SDCARD_DCIM", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
        nativeSetEnv("SDCARD_MUSIC", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath());
        nativeSetEnv("ANDROID_VERSION", String.valueOf(Build.VERSION.SDK_INT));
        nativeSetEnv("ANDROID_PACKAGE_NAME", neoXorgViewClient.getContext().getPackageName());
        nativeSetEnv("ANDROID_PACKAGE_PATH", neoXorgViewClient.getContext().getPackageCodePath());
        nativeSetEnv("ANDROID_MY_OWN_APP_FILE", neoXorgViewClient.getContext().getPackageResourcePath());
        try {
            nativeSetEnv("ANDROID_APP_NAME", neoXorgViewClient.getContext().getString(neoXorgViewClient.getContext().getApplicationInfo().labelRes));
        } catch (Exception unused) {
        }
        Log.d("SDL", "libSDL: Is running on OUYA: " + neoXorgViewClient.isRunningOnOUYA());
        if (neoXorgViewClient.isRunningOnOUYA()) {
            nativeSetEnv("OUYA", "1");
            nativeSetEnv("TV", "1");
            nativeSetEnv("ANDROID_TV", "1");
        }
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            neoXorgViewClient.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float f = ((float) displayMetrics.widthPixels) / displayMetrics.xdpi;
            float f2 = ((float) displayMetrics.heightPixels) / displayMetrics.ydpi;
            float max = Math.max(f, f2);
            float min = Math.min(f, f2);
            float sqrt = (float) Math.sqrt((double) ((max * max) + (min * min)));
            nativeSetEnv("DISPLAY_SIZE", String.valueOf(sqrt));
            nativeSetEnv("DISPLAY_SIZE_MM", String.valueOf((int) (sqrt * 25.4f)));
            nativeSetEnv("DISPLAY_WIDTH", String.valueOf(max));
            nativeSetEnv("DISPLAY_HEIGHT", String.valueOf(min));
            nativeSetEnv("DISPLAY_WIDTH_MM", String.valueOf((int) (max * 25.4f)));
            nativeSetEnv("DISPLAY_HEIGHT_MM", String.valueOf((int) (min * 25.4f)));
            nativeSetEnv("DISPLAY_RESOLUTION_WIDTH", String.valueOf(Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels)));
            nativeSetEnv("DISPLAY_RESOLUTION_HEIGHT", String.valueOf(Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels)));
        } catch (Exception unused2) {
        }
    }

    static byte[] loadRaw(Context context, int i) {
        int i2;
        byte[] bArr = new byte[131072];
        byte[] bArr2 = new byte[5242880];
        try {
            GZIPInputStream gZIPInputStream = new GZIPInputStream(context.getResources().openRawResource(i));
            i2 = 0;
            while (true) {
                try {
                    int read = gZIPInputStream.read(bArr);
                    if (read < 0) {
                        break;
                    }
                    int i3 = i2 + read;
                    if (i3 > bArr2.length) {
                        byte[] bArr3 = new byte[i3];
                        System.arraycopy(bArr2, 0, bArr3, 0, i2);
                        bArr2 = bArr3;
                    }
                    System.arraycopy(bArr, 0, bArr2, i2, read);
                    i2 = i3;
                } catch (Exception unused) {
                }
            }
        } catch (Exception unused2) {
            i2 = 0;
        }
        byte[] bArr4 = new byte[i2];
        System.arraycopy(bArr2, 0, bArr4, 0, i2);
        return bArr4;
    }

    static void SetupTouchscreenKeyboardGraphics(Context context) {
        if (Globals.UseTouchscreenKeyboard) {
            if (Globals.TouchscreenKeyboardTheme < 0) {
                Globals.TouchscreenKeyboardTheme = 0;
            }
            if (Globals.TouchscreenKeyboardTheme > 9) {
                Globals.TouchscreenKeyboardTheme = 9;
            }
            if (Globals.TouchscreenKeyboardTheme == 0) {
                nativeSetupScreenKeyboardButtons(loadRaw(context, R.raw.ultimatedroid));
            }
            if (Globals.TouchscreenKeyboardTheme == 1) {
                nativeSetupScreenKeyboardButtons(loadRaw(context, R.raw.simpletheme));
            }
            if (Globals.TouchscreenKeyboardTheme == 2) {
                nativeSetupScreenKeyboardButtons(loadRaw(context, R.raw.sun));
            }
            if (Globals.TouchscreenKeyboardTheme == 3) {
                nativeSetupScreenKeyboardButtons(loadRaw(context, R.raw.keen));
            }
            if (Globals.TouchscreenKeyboardTheme == 4) {
                nativeSetupScreenKeyboardButtons(loadRaw(context, R.raw.retro));
            }
            if (Globals.TouchscreenKeyboardTheme == 5) {
                nativeSetupScreenKeyboardButtons(loadRaw(context, R.raw.gba));
            }
            if (Globals.TouchscreenKeyboardTheme == 6) {
                nativeSetupScreenKeyboardButtons(loadRaw(context, R.raw.psx));
            }
            if (Globals.TouchscreenKeyboardTheme == 7) {
                nativeSetupScreenKeyboardButtons(loadRaw(context, R.raw.snes));
            }
            if (Globals.TouchscreenKeyboardTheme == 8) {
                nativeSetupScreenKeyboardButtons(loadRaw(context, R.raw.dualshock));
            }
            if (Globals.TouchscreenKeyboardTheme == 9) {
                nativeSetupScreenKeyboardButtons(loadRaw(context, R.raw.n64));
            }
        }
    }

    static abstract class SdcardAppPath {
        SdcardAppPath() {
        }

        public static SdcardAppPath get() {
            return Kitkat.Holder.sInstance;
        }

        public String path(Context context) {
            return get().path(context);
        }

        public void setEnv(Context context) {
            get().setEnv(context);
        }

        public String bestPath(Context context) {
            return get().bestPath(context);
        }

        public String[] allPaths(Context context) {
            return get().allPaths(context);
        }

        private static class Froyo extends SdcardAppPath {
            private Froyo() {
            }

            public String path(Context context) {
                if (context.getExternalFilesDir((String) null) != null) {
                    return context.getExternalFilesDir((String) null).getAbsolutePath();
                }
                if (Environment.getExternalStorageDirectory() == null) {
                    return "/sdcard/Android/data/" + context.getPackageName() + "/files";
                }
                return Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + context.getPackageName() + "/files";
            }

            public void setEnv(Context context) {
                Settings.nativeSetEnv("UNSECURE_STORAGE_DIR_0", Globals.DataDir);
            }

            public String bestPath(Context context) {
                return path(context);
            }

            public String[] allPaths(Context context) {
                return new String[]{path(context)};
            }
        }

        private static class Kitkat extends Froyo {

            private static class Holder {
                /* access modifiers changed from: private */
                public static final Kitkat sInstance = new Kitkat();

                private Holder() {
                }
            }

            private Kitkat() {
                super();
            }

            public String bestPath(Context context) {
                long j;
                File[] externalFilesDirs = context.getExternalFilesDirs((String) null);
                String path = path(context);
                long j2 = -1;
                for (File file : externalFilesDirs) {
                    if (file != null) {
                        try {
                            StatFs statFs = new StatFs(file.getPath());
                            j = ((((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                        } catch (Exception unused) {
                            j = -1;
                        }
                        try {
                            file.mkdirs();
                            new FileOutputStream(new File(file, ".nomedia")).close();
                        } catch (Exception unused2) {
                            j = -1;
                        }
                        if (j > j2) {
                            path = file.getAbsolutePath();
                            j2 = j;
                        }
                    }
                }
                return path;
            }

            public void setEnv(Context context) {
                int i = 0;
                for (File file : context.getExternalFilesDirs((String) null)) {
                    if (file != null) {
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        Settings.nativeSetEnv("UNSECURE_STORAGE_DIR_" + i, file.getAbsolutePath());
                        i++;
                    }
                }
            }

            public String[] allPaths(Context context) {
                ArrayList arrayList = new ArrayList();
                for (File file : context.getExternalFilesDirs((String) null)) {
                    if (file != null) {
                        try {
                            file.mkdirs();
                            new FileOutputStream(new File(file, ".nomedia")).close();
                            arrayList.add(file.getAbsolutePath());
                        } catch (Exception unused) {
                        }
                    }
                }
                return (String[]) arrayList.toArray(new String[0]);
            }
        }
    }

    public static void setConfigOptionFromSDL(int i, int i2) {
        if (i != 0) {
            Log.e("SDL", "setConfigOptionFromSDL: cannot find option with ID " + i + ", value " + i2);
        } else {
            Globals.VideoDepthBpp = i2;
        }
        Save(MainActivity.instance);
    }
}
