package com.thecrackertechnology.dragonterminal.frontend.config;

import com.thecrackertechnology.andrax.AndraxApp;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\n \r*\u0004\u0018\u00010\u00040\u00048\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/config/NeoTermPath;", "", "()V", "COLORS_PATH", "", "CUSTOM_PATH", "EKS_DEFAULT_FILE", "EKS_PATH", "FONT_PATH", "HOME_PATH", "NEOTERM_LOGIN_SHELL_PATH", "PROFILE_PATH", "ROOT_PATH", "kotlin.jvm.PlatformType", "USER_SCRIPT_PATH", "USR_PATH", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoTermPath.kt */
public final class NeoTermPath {
    public static final String COLORS_PATH = (CUSTOM_PATH + "/color");
    public static final String CUSTOM_PATH = (HOME_PATH + "/.neoterm");
    public static final String EKS_DEFAULT_FILE = (EKS_PATH + "/default.nl");
    public static final String EKS_PATH = (CUSTOM_PATH + "/eks");
    public static final String FONT_PATH = (CUSTOM_PATH + "/font");
    public static final String HOME_PATH = (ROOT_PATH + "/home");
    public static final NeoTermPath INSTANCE = new NeoTermPath();
    public static final String NEOTERM_LOGIN_SHELL_PATH = (CUSTOM_PATH + "/shell");
    public static final String PROFILE_PATH = (CUSTOM_PATH + "/profile");
    public static String ROOT_PATH;
    public static final String USER_SCRIPT_PATH = (CUSTOM_PATH + "/script");
    public static final String USR_PATH = (ROOT_PATH + "/usr");

    static {
        File filesDir = AndraxApp.Companion.get().getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "AndraxApp.get().filesDir");
        ROOT_PATH = filesDir.getAbsolutePath();
    }

    private NeoTermPath() {
    }
}
