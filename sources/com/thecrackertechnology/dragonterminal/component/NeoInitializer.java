package com.thecrackertechnology.dragonterminal.component;

import android.content.Context;
import com.thecrackertechnology.dragonterminal.component.codegen.CodeGenComponent;
import com.thecrackertechnology.dragonterminal.component.colorscheme.ColorSchemeComponent;
import com.thecrackertechnology.dragonterminal.component.completion.CompletionComponent;
import com.thecrackertechnology.dragonterminal.component.config.ConfigureComponent;
import com.thecrackertechnology.dragonterminal.component.extrakey.ExtraKeyComponent;
import com.thecrackertechnology.dragonterminal.component.font.FontComponent;
import com.thecrackertechnology.dragonterminal.component.pm.PackageComponent;
import com.thecrackertechnology.dragonterminal.component.profile.ProfileComponent;
import com.thecrackertechnology.dragonterminal.component.session.SessionComponent;
import com.thecrackertechnology.dragonterminal.component.userscript.UserScriptComponent;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.logging.NLog;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellProfile;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0004¨\u0006\b"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/NeoInitializer;", "", "()V", "init", "", "context", "Landroid/content/Context;", "initComponents", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoInitializer.kt */
public final class NeoInitializer {
    public static final NeoInitializer INSTANCE = new NeoInitializer();

    private NeoInitializer() {
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        NLog.INSTANCE.init(context);
        initComponents();
    }

    public final void initComponents() {
        ComponentManager.INSTANCE.registerComponent(ConfigureComponent.class);
        ComponentManager.INSTANCE.registerComponent(CodeGenComponent.class);
        ComponentManager.INSTANCE.registerComponent(ColorSchemeComponent.class);
        ComponentManager.INSTANCE.registerComponent(FontComponent.class);
        ComponentManager.INSTANCE.registerComponent(UserScriptComponent.class);
        ComponentManager.INSTANCE.registerComponent(ExtraKeyComponent.class);
        ComponentManager.INSTANCE.registerComponent(CompletionComponent.class);
        ComponentManager.INSTANCE.registerComponent(PackageComponent.class);
        ComponentManager.INSTANCE.registerComponent(SessionComponent.class);
        ComponentManager.INSTANCE.registerComponent(ProfileComponent.class);
        ((ProfileComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, ProfileComponent.class, false, 2, (Object) null)).registerProfile(ShellProfile.PROFILE_META_NAME, ShellProfile.class);
    }
}
