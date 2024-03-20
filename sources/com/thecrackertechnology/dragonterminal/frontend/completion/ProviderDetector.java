package com.thecrackertechnology.dragonterminal.frontend.completion;

import com.thecrackertechnology.dragonterminal.frontend.completion.listener.MarkScoreListener;
import com.thecrackertechnology.dragonterminal.frontend.completion.provider.ICandidateProvider;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/completion/ProviderDetector;", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/listener/MarkScoreListener;", "providers", "", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/provider/ICandidateProvider;", "(Ljava/util/List;)V", "detectedProvider", "getProviders", "()Ljava/util/List;", "detectBest", "onMarkScore", "", "score", "", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ProviderDetector.kt */
public final class ProviderDetector implements MarkScoreListener {
    private ICandidateProvider detectedProvider;
    private final List<ICandidateProvider> providers;

    public void onMarkScore(int i) {
    }

    public ProviderDetector(List<? extends ICandidateProvider> list) {
        Intrinsics.checkParameterIsNotNull(list, "providers");
        this.providers = list;
    }

    public final List<ICandidateProvider> getProviders() {
        return this.providers;
    }

    public final ICandidateProvider detectBest() {
        ICandidateProvider iCandidateProvider;
        if (this.providers.isEmpty()) {
            iCandidateProvider = null;
        } else {
            iCandidateProvider = this.providers.get(0);
        }
        this.detectedProvider = iCandidateProvider;
        return this.detectedProvider;
    }
}
