package com.thecrackertechnology.dragonterminal.frontend.completion.provider;

import com.thecrackertechnology.dragonterminal.frontend.completion.model.CompletionCandidate;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&J\u0018\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0006\u0010\b\u001a\u00020\u0003H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\f"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/completion/provider/ICandidateProvider;", "", "providerName", "", "getProviderName", "()Ljava/lang/String;", "canComplete", "", "text", "provideCandidates", "", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/model/CompletionCandidate;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ICandidateProvider.kt */
public interface ICandidateProvider {
    boolean canComplete(String str);

    String getProviderName();

    List<CompletionCandidate> provideCandidates(String str);
}
