package com.thecrackertechnology.dragonterminal.frontend.completion.model;

import com.thecrackertechnology.dragonterminal.frontend.completion.listener.MarkScoreListener;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0014"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/completion/model/CompletionResult;", "", "candidates", "", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/model/CompletionCandidate;", "scoreMarker", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/listener/MarkScoreListener;", "(Ljava/util/List;Lcom/thecrackertechnology/dragonterminal/frontend/completion/listener/MarkScoreListener;)V", "getCandidates", "()Ljava/util/List;", "getScoreMarker", "()Lcom/thecrackertechnology/dragonterminal/frontend/completion/listener/MarkScoreListener;", "setScoreMarker", "(Lcom/thecrackertechnology/dragonterminal/frontend/completion/listener/MarkScoreListener;)V", "hasResult", "", "markScore", "", "score", "", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: CompletionResult.kt */
public final class CompletionResult {
    private final List<CompletionCandidate> candidates;
    private MarkScoreListener scoreMarker;

    public CompletionResult(List<CompletionCandidate> list, MarkScoreListener markScoreListener) {
        Intrinsics.checkParameterIsNotNull(list, "candidates");
        Intrinsics.checkParameterIsNotNull(markScoreListener, "scoreMarker");
        this.candidates = list;
        this.scoreMarker = markScoreListener;
    }

    public final List<CompletionCandidate> getCandidates() {
        return this.candidates;
    }

    public final MarkScoreListener getScoreMarker() {
        return this.scoreMarker;
    }

    public final void setScoreMarker(MarkScoreListener markScoreListener) {
        Intrinsics.checkParameterIsNotNull(markScoreListener, "<set-?>");
        this.scoreMarker = markScoreListener;
    }

    public final void markScore(int i) {
        this.scoreMarker.onMarkScore(i);
    }

    public final boolean hasResult() {
        return !this.candidates.isEmpty();
    }
}
