package com.thecrackertechnology.dragonterminal.component.completion;

import com.thecrackertechnology.dragonterminal.component.completion.provider.FileCompletionProvider;
import com.thecrackertechnology.dragonterminal.component.completion.provider.ProgramCompletionProvider;
import com.thecrackertechnology.dragonterminal.frontend.completion.CompletionManager;
import com.thecrackertechnology.dragonterminal.frontend.component.NeoComponent;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016¨\u0006\u0007"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/completion/CompletionComponent;", "Lcom/thecrackertechnology/dragonterminal/frontend/component/NeoComponent;", "()V", "onServiceDestroy", "", "onServiceInit", "onServiceObtained", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: CompletionComponent.kt */
public final class CompletionComponent implements NeoComponent {
    public void onServiceDestroy() {
    }

    public void onServiceObtained() {
    }

    public void onServiceInit() {
        CompletionManager.INSTANCE.registerProvider(new FileCompletionProvider());
        CompletionManager.INSTANCE.registerProvider(new ProgramCompletionProvider());
    }
}
