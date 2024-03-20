package com.thecrackertechnology.dragonterminal.frontend.session.shell;

import com.thecrackertechnology.andrax.AndraxApp;
import com.thecrackertechnology.dragonterminal.component.colorscheme.ColorSchemeComponent;
import com.thecrackertechnology.dragonterminal.component.font.FontComponent;
import com.thecrackertechnology.dragonterminal.component.profile.NeoProfile;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.config.DefaultValues;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference;
import io.neolang.visitor.ConfigVisitor;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 32\u00020\u0001:\u00013B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001a\u0010\u001e\u001a\u00020\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010!\"\u0004\b&\u0010#R\u001a\u0010'\u001a\u00020\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010!\"\u0004\b)\u0010#R\u001a\u0010*\u001a\u00020\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010!\"\u0004\b,\u0010#R\u0014\u0010-\u001a\u00020\u001fXD¢\u0006\b\n\u0000\u001a\u0004\b.\u0010!¨\u00064"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellProfile;", "Lcom/thecrackertechnology/dragonterminal/component/profile/NeoProfile;", "()V", "enableAutoCompletion", "", "getEnableAutoCompletion", "()Z", "setEnableAutoCompletion", "(Z)V", "enableBackKeyToEscape", "getEnableBackKeyToEscape", "setEnableBackKeyToEscape", "enableBell", "getEnableBell", "setEnableBell", "enableExecveWrapper", "getEnableExecveWrapper", "setEnableExecveWrapper", "enableExtraKeys", "getEnableExtraKeys", "setEnableExtraKeys", "enableSpecialVolumeKeys", "getEnableSpecialVolumeKeys", "setEnableSpecialVolumeKeys", "enableVibrate", "getEnableVibrate", "setEnableVibrate", "enableWordBasedIme", "getEnableWordBasedIme", "setEnableWordBasedIme", "initialCommand", "", "getInitialCommand", "()Ljava/lang/String;", "setInitialCommand", "(Ljava/lang/String;)V", "loginShell", "getLoginShell", "setLoginShell", "profileColorScheme", "getProfileColorScheme", "setProfileColorScheme", "profileFont", "getProfileFont", "setProfileFont", "profileMetaName", "getProfileMetaName", "onConfigLoaded", "", "configVisitor", "Lio/neolang/visitor/ConfigVisitor;", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ShellProfile.kt */
public final class ShellProfile extends NeoProfile {
    private static final String AUTO_COMPLETION = "auto-completion";
    private static final String BACK_KEY_TO_ESC = "back-key-esc";
    private static final String BELL = "bell";
    private static final String COLOR_SCHEME = "color-scheme";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String EXECVE_WRAPPER = "execve-wrapper";
    private static final String EXTRA_KEYS = "extra-keys";
    private static final String FONT = "font";
    private static final String INITIAL_COMMAND = "init-command";
    private static final String LOGIN_SHELL = "login-shell";
    public static final String PROFILE_META_NAME = "profile-shell";
    private static final String SPECIAL_VOLUME_KEYS = "special-volume-keys";
    private static final String VIBRATE = "vibrate";
    private static final String WORD_BASED_IME = "word-based-ime";
    private boolean enableAutoCompletion;
    private boolean enableBackKeyToEscape;
    private boolean enableBell;
    private boolean enableExecveWrapper;
    private boolean enableExtraKeys;
    private boolean enableSpecialVolumeKeys;
    private boolean enableVibrate;
    private boolean enableWordBasedIme;
    private String initialCommand;
    private String loginShell;
    private String profileColorScheme;
    private String profileFont;
    private final String profileMetaName = PROFILE_META_NAME;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellProfile$Companion;", "", "()V", "AUTO_COMPLETION", "", "BACK_KEY_TO_ESC", "BELL", "COLOR_SCHEME", "EXECVE_WRAPPER", "EXTRA_KEYS", "FONT", "INITIAL_COMMAND", "LOGIN_SHELL", "PROFILE_META_NAME", "SPECIAL_VOLUME_KEYS", "VIBRATE", "WORD_BASED_IME", "create", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellProfile;", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: ShellProfile.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ShellProfile create() {
            return new ShellProfile();
        }
    }

    public ShellProfile() {
        StringBuilder sb = new StringBuilder();
        File filesDir = AndraxApp.Companion.get().getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "AndraxApp.get().filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/bin/");
        sb.append(DefaultValues.INSTANCE.getLoginShell());
        this.loginShell = sb.toString();
        this.initialCommand = DefaultValues.INSTANCE.getInitialCommand();
        this.enableBell = DefaultValues.INSTANCE.getEnableBell();
        this.enableVibrate = DefaultValues.INSTANCE.getEnableVibrate();
        this.enableExecveWrapper = DefaultValues.INSTANCE.getEnableExecveWrapper();
        this.enableSpecialVolumeKeys = DefaultValues.INSTANCE.getEnableSpecialVolumeKeys();
        this.enableAutoCompletion = DefaultValues.INSTANCE.getEnableAutoCompletion();
        this.enableBackKeyToEscape = DefaultValues.INSTANCE.getEnableBackButtonBeMappedToEscape();
        this.enableExtraKeys = DefaultValues.INSTANCE.getEnableExtraKeys();
        this.enableWordBasedIme = DefaultValues.INSTANCE.getEnableWordBasedIme();
        this.profileFont = ((FontComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, FontComponent.class, false, 2, (Object) null)).getCurrentFontName();
        this.profileColorScheme = ((ColorSchemeComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, ColorSchemeComponent.class, false, 2, (Object) null)).getCurrentColorSchemeName();
        this.loginShell = NeoPreference.INSTANCE.getLoginShellPath();
        this.initialCommand = NeoPreference.INSTANCE.getInitialCommand();
        this.enableBell = NeoPreference.INSTANCE.isBellEnabled();
        this.enableVibrate = NeoPreference.INSTANCE.isVibrateEnabled();
        this.enableExecveWrapper = NeoPreference.INSTANCE.isExecveWrapperEnabled();
        this.enableSpecialVolumeKeys = NeoPreference.INSTANCE.isSpecialVolumeKeysEnabled();
        this.enableAutoCompletion = NeoPreference.INSTANCE.isAutoCompletionEnabled();
        this.enableBackKeyToEscape = NeoPreference.INSTANCE.isBackButtonBeMappedToEscapeEnabled();
        this.enableExtraKeys = NeoPreference.INSTANCE.isExtraKeysEnabled();
        this.enableWordBasedIme = NeoPreference.INSTANCE.isWordBasedImeEnabled();
    }

    public String getProfileMetaName() {
        return this.profileMetaName;
    }

    public final String getLoginShell() {
        return this.loginShell;
    }

    public final void setLoginShell(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.loginShell = str;
    }

    public final String getInitialCommand() {
        return this.initialCommand;
    }

    public final void setInitialCommand(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.initialCommand = str;
    }

    public final boolean getEnableBell() {
        return this.enableBell;
    }

    public final void setEnableBell(boolean z) {
        this.enableBell = z;
    }

    public final boolean getEnableVibrate() {
        return this.enableVibrate;
    }

    public final void setEnableVibrate(boolean z) {
        this.enableVibrate = z;
    }

    public final boolean getEnableExecveWrapper() {
        return this.enableExecveWrapper;
    }

    public final void setEnableExecveWrapper(boolean z) {
        this.enableExecveWrapper = z;
    }

    public final boolean getEnableSpecialVolumeKeys() {
        return this.enableSpecialVolumeKeys;
    }

    public final void setEnableSpecialVolumeKeys(boolean z) {
        this.enableSpecialVolumeKeys = z;
    }

    public final boolean getEnableAutoCompletion() {
        return this.enableAutoCompletion;
    }

    public final void setEnableAutoCompletion(boolean z) {
        this.enableAutoCompletion = z;
    }

    public final boolean getEnableBackKeyToEscape() {
        return this.enableBackKeyToEscape;
    }

    public final void setEnableBackKeyToEscape(boolean z) {
        this.enableBackKeyToEscape = z;
    }

    public final boolean getEnableExtraKeys() {
        return this.enableExtraKeys;
    }

    public final void setEnableExtraKeys(boolean z) {
        this.enableExtraKeys = z;
    }

    public final boolean getEnableWordBasedIme() {
        return this.enableWordBasedIme;
    }

    public final void setEnableWordBasedIme(boolean z) {
        this.enableWordBasedIme = z;
    }

    public final String getProfileFont() {
        return this.profileFont;
    }

    public final void setProfileFont(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.profileFont = str;
    }

    public final String getProfileColorScheme() {
        return this.profileColorScheme;
    }

    public final void setProfileColorScheme(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.profileColorScheme = str;
    }

    public void onConfigLoaded(ConfigVisitor configVisitor) {
        Intrinsics.checkParameterIsNotNull(configVisitor, "configVisitor");
        super.onConfigLoaded(configVisitor);
        this.loginShell = getProfileString(configVisitor, LOGIN_SHELL, this.loginShell);
        this.initialCommand = getProfileString(configVisitor, INITIAL_COMMAND, this.initialCommand);
        this.enableBell = getProfileBoolean(configVisitor, BELL, this.enableBell);
        this.enableVibrate = getProfileBoolean(configVisitor, VIBRATE, this.enableVibrate);
        this.enableExecveWrapper = getProfileBoolean(configVisitor, EXECVE_WRAPPER, this.enableExecveWrapper);
        this.enableSpecialVolumeKeys = getProfileBoolean(configVisitor, SPECIAL_VOLUME_KEYS, this.enableSpecialVolumeKeys);
        this.enableAutoCompletion = getProfileBoolean(configVisitor, AUTO_COMPLETION, this.enableAutoCompletion);
        this.enableBackKeyToEscape = getProfileBoolean(configVisitor, BACK_KEY_TO_ESC, this.enableBackKeyToEscape);
        this.enableExtraKeys = getProfileBoolean(configVisitor, EXTRA_KEYS, this.enableExtraKeys);
        this.enableWordBasedIme = getProfileBoolean(configVisitor, WORD_BASED_IME, this.enableWordBasedIme);
        this.profileFont = getProfileString(configVisitor, FONT, this.profileFont);
        this.profileColorScheme = getProfileString(configVisitor, "color-scheme", this.profileColorScheme);
    }
}
