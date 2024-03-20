package com.thecrackertechnology.dragonterminal.frontend.completion;

import com.thecrackertechnology.dragonterminal.frontend.completion.model.CompletionCandidate;
import com.thecrackertechnology.dragonterminal.frontend.completion.model.CompletionResult;
import com.thecrackertechnology.dragonterminal.frontend.completion.provider.ICandidateProvider;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0002J\u0010\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\u0005J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0005J\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006J\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/completion/CompletionManager;", "", "()V", "candidateProviders", "", "", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/provider/ICandidateProvider;", "detectProviders", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/ProviderDetector;", "text", "getProvider", "providerName", "registerProvider", "", "provider", "tryCompleteFor", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/model/CompletionResult;", "unregisterProvider", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: CompletionManager.kt */
public final class CompletionManager {
    public static final CompletionManager INSTANCE = new CompletionManager();
    private static final Map<String, ICandidateProvider> candidateProviders = new LinkedHashMap();

    private CompletionManager() {
    }

    public final void registerProvider(ICandidateProvider iCandidateProvider) {
        Intrinsics.checkParameterIsNotNull(iCandidateProvider, "provider");
        candidateProviders.put(iCandidateProvider.getProviderName(), iCandidateProvider);
    }

    public final void unregisterProvider(String str) {
        Intrinsics.checkParameterIsNotNull(str, "providerName");
        candidateProviders.remove(str);
    }

    public final void unregisterProvider(ICandidateProvider iCandidateProvider) {
        Intrinsics.checkParameterIsNotNull(iCandidateProvider, "provider");
        unregisterProvider(iCandidateProvider.getProviderName());
    }

    public final ICandidateProvider getProvider(String str) {
        Intrinsics.checkParameterIsNotNull(str, "providerName");
        return candidateProviders.get(str);
    }

    public final CompletionResult tryCompleteFor(String str) {
        List<CompletionCandidate> list;
        Intrinsics.checkParameterIsNotNull(str, "text");
        ProviderDetector detectProviders = detectProviders(str);
        ICandidateProvider detectBest = detectProviders.detectBest();
        if (detectBest == null || (list = detectBest.provideCandidates(str)) == null) {
            list = CollectionsKt.emptyList();
        }
        return new CompletionResult(list, detectProviders);
    }

    private final ProviderDetector detectProviders(String str) {
        ArrayList arrayList = new ArrayList();
        for (Object next : candidateProviders.values()) {
            if (!((ICandidateProvider) next).canComplete(str)) {
                break;
            }
            arrayList.add(next);
        }
        return new ProviderDetector(arrayList);
    }
}
