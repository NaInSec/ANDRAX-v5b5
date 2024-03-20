package com.thecrackertechnology.dragonterminal.frontend.config;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0014\u0010\r\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR\u0014\u0010\u000f\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR\u0014\u0010\u0011\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\nR\u0014\u0010\u0013\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\nR\u0014\u0010\u0015\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\nR\u0014\u0010\u0017\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\nR\u0014\u0010\u0019\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\nR\u0014\u0010\u001b\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\nR\u0014\u0010\u001d\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\nR\u0014\u0010\u001f\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b \u0010\nR\u0014\u0010!\u001a\u00020\"XD¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0006R\u0014\u0010'\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0006¨\u0006)"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/config/DefaultValues;", "", "()V", "defaultFont", "", "getDefaultFont", "()Ljava/lang/String;", "enableAutoCompletion", "", "getEnableAutoCompletion", "()Z", "enableAutoHideToolbar", "getEnableAutoHideToolbar", "enableBackButtonBeMappedToEscape", "getEnableBackButtonBeMappedToEscape", "enableBell", "getEnableBell", "enableExecveWrapper", "getEnableExecveWrapper", "enableExplicitExtraKeysWeight", "getEnableExplicitExtraKeysWeight", "enableExtraKeys", "getEnableExtraKeys", "enableFullScreen", "getEnableFullScreen", "enableSpecialVolumeKeys", "getEnableSpecialVolumeKeys", "enableSwitchNextTab", "getEnableSwitchNextTab", "enableVibrate", "getEnableVibrate", "enableWordBasedIme", "getEnableWordBasedIme", "fontSize", "", "getFontSize", "()I", "initialCommand", "getInitialCommand", "loginShell", "getLoginShell", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: DefaultValues.kt */
public final class DefaultValues {
    public static final DefaultValues INSTANCE = new DefaultValues();
    private static final String defaultFont = defaultFont;
    private static final boolean enableAutoCompletion = false;
    private static final boolean enableAutoHideToolbar = true;
    private static final boolean enableBackButtonBeMappedToEscape = false;
    private static final boolean enableBell = true;
    private static final boolean enableExecveWrapper = false;
    private static final boolean enableExplicitExtraKeysWeight = false;
    private static final boolean enableExtraKeys = true;
    private static final boolean enableFullScreen = true;
    private static final boolean enableSpecialVolumeKeys = false;
    private static final boolean enableSwitchNextTab = false;
    private static final boolean enableVibrate = true;
    private static final boolean enableWordBasedIme = false;
    private static final int fontSize = 16;
    private static final String initialCommand = "";
    private static final String loginShell = loginShell;

    private DefaultValues() {
    }

    public final int getFontSize() {
        return fontSize;
    }

    public final boolean getEnableBell() {
        return enableBell;
    }

    public final boolean getEnableVibrate() {
        return enableVibrate;
    }

    public final boolean getEnableExecveWrapper() {
        return enableExecveWrapper;
    }

    public final boolean getEnableAutoCompletion() {
        return enableAutoCompletion;
    }

    public final boolean getEnableFullScreen() {
        return enableFullScreen;
    }

    public final boolean getEnableAutoHideToolbar() {
        return enableAutoHideToolbar;
    }

    public final boolean getEnableSwitchNextTab() {
        return enableSwitchNextTab;
    }

    public final boolean getEnableExtraKeys() {
        return enableExtraKeys;
    }

    public final boolean getEnableExplicitExtraKeysWeight() {
        return enableExplicitExtraKeysWeight;
    }

    public final boolean getEnableBackButtonBeMappedToEscape() {
        return enableBackButtonBeMappedToEscape;
    }

    public final boolean getEnableSpecialVolumeKeys() {
        return enableSpecialVolumeKeys;
    }

    public final boolean getEnableWordBasedIme() {
        return enableWordBasedIme;
    }

    public final String getLoginShell() {
        return loginShell;
    }

    public final String getInitialCommand() {
        return initialCommand;
    }

    public final String getDefaultFont() {
        return defaultFont;
    }
}
