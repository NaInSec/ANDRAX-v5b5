package com.thecrackertechnology.dragonterminal.frontend.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.system.ErrnoException;
import android.system.Os;
import android.util.TypedValue;
import com.thecrackertechnology.andrax.AndraxApp;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.component.extrakey.NeoExtraKey;
import com.thecrackertechnology.dragonterminal.frontend.logging.NLog;
import com.thecrackertechnology.dragonterminal.utils.FileUtils;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001a\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u0004J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0006\u0010\u0019\u001a\u00020\nJ\u0006\u0010\u001a\u001a\u00020\u0004J\u0006\u0010\u001b\u001a\u00020\u0004J\u0006\u0010\u001c\u001a\u00020\u0004J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020\"J\u0006\u0010$\u001a\u00020\"J\u0006\u0010%\u001a\u00020\"J\u0006\u0010&\u001a\u00020\"J\u0006\u0010'\u001a\u00020\"J\u0006\u0010(\u001a\u00020\"J\u0006\u0010)\u001a\u00020\"J\u0006\u0010*\u001a\u00020\"J\u0006\u0010+\u001a\u00020\"J\u0006\u0010,\u001a\u00020\"J\u0006\u0010-\u001a\u00020\"J\u0016\u0010.\u001a\u00020\"2\u0006\u0010/\u001a\u00020\n2\u0006\u00100\u001a\u00020\"J\u0018\u0010.\u001a\u00020\"2\b\u0010/\u001a\u0004\u0018\u00010\u00042\u0006\u00100\u001a\u00020\"J\u0016\u00101\u001a\u00020\n2\u0006\u0010/\u001a\u00020\n2\u0006\u00100\u001a\u00020\nJ\u0018\u00101\u001a\u00020\n2\b\u0010/\u001a\u0004\u0018\u00010\u00042\u0006\u00100\u001a\u00020\nJ\u0018\u00102\u001a\u00020\u00042\u0006\u0010/\u001a\u00020\n2\b\u00100\u001a\u0004\u0018\u00010\u0004J\u001a\u00102\u001a\u00020\u00042\b\u0010/\u001a\u0004\u0018\u00010\u00042\b\u00100\u001a\u0004\u0018\u00010\u0004J\u0010\u00103\u001a\u00020\"2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004J\u0016\u00104\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020\n2\u0006\u00105\u001a\u00020\u0001J\u0016\u00104\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0001J\u000e\u00106\u001a\u00020\u001e2\u0006\u00107\u001a\u00020\u0016J\u0010\u00108\u001a\u00020\u001e2\u0006\u00109\u001a\u00020\u0004H\u0002J\u000e\u0010:\u001a\u00020\n2\u0006\u0010;\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u000e\u0010\u0010\u001a\u00020\nXT¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/config/NeoPreference;", "", "()V", "KEY_CURRENT_SESSION", "", "KEY_FONT_SIZE", "KEY_HAPPY_EGG", "KEY_SOURCES", "KEY_SYSTEM_SHELL", "<set-?>", "", "MAX_FONT_SIZE", "getMAX_FONT_SIZE", "()I", "MIN_FONT_SIZE", "getMIN_FONT_SIZE", "VALUE_HAPPY_EGG_TRIGGER", "preference", "Landroid/content/SharedPreferences;", "findLoginProgram", "loginProgramName", "getCurrentSession", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "termService", "Lcom/thecrackertechnology/dragonterminal/services/NeoTermService;", "getFontSize", "getInitialCommand", "getLoginShellName", "getLoginShellPath", "init", "", "context", "Landroid/content/Context;", "isAutoCompletionEnabled", "", "isBackButtonBeMappedToEscapeEnabled", "isBellEnabled", "isExecveWrapperEnabled", "isExplicitExtraKeysWeightEnabled", "isExtraKeysEnabled", "isFullScreenEnabled", "isHideToolbarEnabled", "isNextTabEnabled", "isSpecialVolumeKeysEnabled", "isVibrateEnabled", "isWordBasedImeEnabled", "loadBoolean", "key", "defaultValue", "loadInt", "loadString", "setLoginShellName", "store", "value", "storeCurrentSession", "session", "symlinkLoginShell", "loginProgramPath", "validateFontSize", "fontSize", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoPreference.kt */
public final class NeoPreference {
    public static final NeoPreference INSTANCE = new NeoPreference();
    public static final String KEY_CURRENT_SESSION = "neoterm_service_current_session";
    public static final String KEY_FONT_SIZE = "neoterm_general_font_size";
    public static final String KEY_HAPPY_EGG = "neoterm_fun_happy";
    public static final String KEY_SOURCES = "neoterm_package_enabled_sources";
    public static final String KEY_SYSTEM_SHELL = "neoterm_core_system_shell";
    private static int MAX_FONT_SIZE = 0;
    private static int MIN_FONT_SIZE = 0;
    public static final int VALUE_HAPPY_EGG_TRIGGER = 8;
    private static SharedPreferences preference;

    private NeoPreference() {
    }

    public final int getMIN_FONT_SIZE() {
        return MIN_FONT_SIZE;
    }

    public final int getMAX_FONT_SIZE() {
        return MAX_FONT_SIZE;
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        preference = PreferenceManager.getDefaultSharedPreferences(context);
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        MIN_FONT_SIZE = (int) (TypedValue.applyDimension(1, 1.0f, resources.getDisplayMetrics()) * 4.0f);
        MAX_FONT_SIZE = 256;
        File file = new File("");
        if (FileUtils.INSTANCE.readFile(file) != null) {
            byte[] readFile = FileUtils.INSTANCE.readFile(file);
            if (readFile == null) {
                Intrinsics.throwNpe();
            }
            String obj = StringsKt.trim((CharSequence) new String(readFile, Charsets.UTF_8)).toString();
            if (obj != null) {
                List split$default = StringsKt.split$default((CharSequence) StringsKt.trimEnd((CharSequence) obj).toString(), new String[]{StringUtils.SPACE}, false, 0, 6, (Object) null);
                if (split$default.size() >= 2 && Intrinsics.areEqual((Object) (String) split$default.get(0), (Object) "deb")) {
                    store((int) R.string.key_package_source, split$default.get(1));
                    return;
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
    }

    public final void store(int i, Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "value");
        String string = AndraxApp.Companion.get().getString(i);
        Intrinsics.checkExpressionValueIsNotNull(string, "AndraxApp.get().getString(key)");
        store(string, obj);
    }

    public final void store(String str, Object obj) {
        Intrinsics.checkParameterIsNotNull(str, NeoExtraKey.EKS_META_KEY);
        Intrinsics.checkParameterIsNotNull(obj, "value");
        if (obj instanceof Integer) {
            SharedPreferences sharedPreferences = preference;
            if (sharedPreferences == null) {
                Intrinsics.throwNpe();
            }
            sharedPreferences.edit().putInt(str, ((Number) obj).intValue()).apply();
        } else if (obj instanceof String) {
            SharedPreferences sharedPreferences2 = preference;
            if (sharedPreferences2 == null) {
                Intrinsics.throwNpe();
            }
            sharedPreferences2.edit().putString(str, (String) obj).apply();
        } else if (obj instanceof Boolean) {
            SharedPreferences sharedPreferences3 = preference;
            if (sharedPreferences3 == null) {
                Intrinsics.throwNpe();
            }
            sharedPreferences3.edit().putBoolean(str, ((Boolean) obj).booleanValue()).apply();
        }
    }

    public final int loadInt(int i, int i2) {
        return loadInt(AndraxApp.Companion.get().getString(i), i2);
    }

    public final String loadString(int i, String str) {
        return loadString(AndraxApp.Companion.get().getString(i), str);
    }

    public final boolean loadBoolean(int i, boolean z) {
        return loadBoolean(AndraxApp.Companion.get().getString(i), z);
    }

    public final int loadInt(String str, int i) {
        SharedPreferences sharedPreferences = preference;
        if (sharedPreferences == null) {
            Intrinsics.throwNpe();
        }
        return sharedPreferences.getInt(str, i);
    }

    public final String loadString(String str, String str2) {
        SharedPreferences sharedPreferences = preference;
        if (sharedPreferences == null) {
            Intrinsics.throwNpe();
        }
        String string = sharedPreferences.getString(str, str2);
        Intrinsics.checkExpressionValueIsNotNull(string, "preference!!.getString(key, defaultValue)");
        return string;
    }

    public final boolean loadBoolean(String str, boolean z) {
        SharedPreferences sharedPreferences = preference;
        if (sharedPreferences == null) {
            Intrinsics.throwNpe();
        }
        return sharedPreferences.getBoolean(str, z);
    }

    public final void storeCurrentSession(TerminalSession terminalSession) {
        Intrinsics.checkParameterIsNotNull(terminalSession, "session");
        SharedPreferences sharedPreferences = preference;
        if (sharedPreferences == null) {
            Intrinsics.throwNpe();
        }
        sharedPreferences.edit().putString(KEY_CURRENT_SESSION, terminalSession.mHandle).apply();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: com.thecrackertechnology.dragonterminal.backend.TerminalSession} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: com.thecrackertechnology.dragonterminal.backend.TerminalSession} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: com.thecrackertechnology.dragonterminal.backend.TerminalSession} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: com.thecrackertechnology.dragonterminal.backend.TerminalSession} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: com.thecrackertechnology.dragonterminal.backend.TerminalSession} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.thecrackertechnology.dragonterminal.backend.TerminalSession getCurrentSession(com.thecrackertechnology.dragonterminal.services.NeoTermService r7) {
        /*
            r6 = this;
            if (r7 != 0) goto L_0x0005
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0005:
            r0 = r7
            android.content.Context r0 = (android.content.Context) r0
            android.content.SharedPreferences r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0)
            java.lang.String r1 = "neoterm_service_current_session"
            java.lang.String r2 = ""
            java.lang.String r0 = r0.getString(r1, r2)
            java.util.List r7 = r7.getSessions()
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            r1 = 0
            java.util.Iterator r7 = r7.iterator()
            r2 = 0
            r3 = r2
        L_0x0021:
            boolean r4 = r7.hasNext()
            if (r4 == 0) goto L_0x003c
            java.lang.Object r4 = r7.next()
            r5 = r4
            com.thecrackertechnology.dragonterminal.backend.TerminalSession r5 = (com.thecrackertechnology.dragonterminal.backend.TerminalSession) r5
            java.lang.String r5 = r5.mHandle
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r0)
            if (r5 == 0) goto L_0x0021
            if (r1 == 0) goto L_0x0039
            goto L_0x0040
        L_0x0039:
            r1 = 1
            r3 = r4
            goto L_0x0021
        L_0x003c:
            if (r1 != 0) goto L_0x003f
            goto L_0x0040
        L_0x003f:
            r2 = r3
        L_0x0040:
            com.thecrackertechnology.dragonterminal.backend.TerminalSession r2 = (com.thecrackertechnology.dragonterminal.backend.TerminalSession) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference.getCurrentSession(com.thecrackertechnology.dragonterminal.services.NeoTermService):com.thecrackertechnology.dragonterminal.backend.TerminalSession");
    }

    public final boolean setLoginShellName(String str) {
        if (str == null) {
            return false;
        }
        store((int) R.string.key_general_shell, (Object) str);
        return true;
    }

    public final String getLoginShellName() {
        StringBuilder sb = new StringBuilder();
        File filesDir = AndraxApp.Companion.get().getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "AndraxApp.get().filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/bin/");
        sb.append(DefaultValues.INSTANCE.getLoginShell());
        return loadString((int) R.string.key_general_shell, sb.toString());
    }

    public final String getLoginShellPath() {
        String loginShellName = getLoginShellName();
        File file = new File(NeoTermPath.NEOTERM_LOGIN_SHELL_PATH);
        String findLoginProgram = findLoginProgram(loginShellName);
        if (findLoginProgram == null) {
            findLoginProgram = (String) NeoPreference$getLoginShellPath$loginProgramPath$1.INSTANCE.invoke();
        }
        if (!file.exists()) {
            symlinkLoginShell(findLoginProgram);
        }
        return findLoginProgram;
    }

    public final int validateFontSize(int i) {
        return Math.max(MIN_FONT_SIZE, Math.min(i, MAX_FONT_SIZE));
    }

    private final void symlinkLoginShell(String str) {
        new File(NeoTermPath.CUSTOM_PATH).mkdirs();
        try {
            File file = new File(NeoTermPath.NEOTERM_LOGIN_SHELL_PATH);
            if (file.exists()) {
                file.delete();
            }
            Os.symlink(str, NeoTermPath.NEOTERM_LOGIN_SHELL_PATH);
            Os.chmod(NeoTermPath.NEOTERM_LOGIN_SHELL_PATH, 448);
        } catch (ErrnoException e) {
            NLog nLog = NLog.INSTANCE;
            nLog.e("Preference", "Failed to symlink login shell: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public final String findLoginProgram(String str) {
        Intrinsics.checkParameterIsNotNull(str, "loginProgramName");
        File file = new File(str);
        if (file.canExecute()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public final int getFontSize() {
        return loadInt(KEY_FONT_SIZE, DefaultValues.INSTANCE.getFontSize());
    }

    public final String getInitialCommand() {
        return loadString((int) R.string.key_general_initial_command, DefaultValues.INSTANCE.getInitialCommand());
    }

    public final boolean isBellEnabled() {
        return loadBoolean((int) R.string.key_general_bell, DefaultValues.INSTANCE.getEnableBell());
    }

    public final boolean isVibrateEnabled() {
        return loadBoolean((int) R.string.key_general_vibrate, DefaultValues.INSTANCE.getEnableVibrate());
    }

    public final boolean isExecveWrapperEnabled() {
        return loadBoolean((int) R.string.key_general_use_execve_wrapper, DefaultValues.INSTANCE.getEnableExecveWrapper());
    }

    public final boolean isSpecialVolumeKeysEnabled() {
        return loadBoolean((int) R.string.key_general_volume_as_control, DefaultValues.INSTANCE.getEnableSpecialVolumeKeys());
    }

    public final boolean isAutoCompletionEnabled() {
        return loadBoolean((int) R.string.key_general_auto_completion, DefaultValues.INSTANCE.getEnableAutoCompletion());
    }

    public final boolean isBackButtonBeMappedToEscapeEnabled() {
        return loadBoolean((int) R.string.key_generaL_backspace_map_to_esc, DefaultValues.INSTANCE.getEnableBackButtonBeMappedToEscape());
    }

    public final boolean isExtraKeysEnabled() {
        return loadBoolean((int) R.string.key_ui_eks_enabled, DefaultValues.INSTANCE.getEnableExtraKeys());
    }

    public final boolean isExplicitExtraKeysWeightEnabled() {
        return loadBoolean((int) R.string.key_ui_eks_weight_explicit, DefaultValues.INSTANCE.getEnableExplicitExtraKeysWeight());
    }

    public final boolean isFullScreenEnabled() {
        return loadBoolean((int) R.string.key_ui_fullscreen, DefaultValues.INSTANCE.getEnableFullScreen());
    }

    public final boolean isHideToolbarEnabled() {
        return loadBoolean((int) R.string.key_ui_hide_toolbar, DefaultValues.INSTANCE.getEnableAutoHideToolbar());
    }

    public final boolean isNextTabEnabled() {
        return loadBoolean((int) R.string.key_ui_next_tab_anim, DefaultValues.INSTANCE.getEnableSwitchNextTab());
    }

    public final boolean isWordBasedImeEnabled() {
        return loadBoolean((int) R.string.key_general_enable_word_based_ime, DefaultValues.INSTANCE.getEnableWordBasedIme());
    }
}
