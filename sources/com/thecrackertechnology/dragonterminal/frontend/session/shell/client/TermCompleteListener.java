package com.thecrackertechnology.dragonterminal.frontend.session.shell.client;

import android.content.Context;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.frontend.completion.CompletionManager;
import com.thecrackertechnology.dragonterminal.frontend.completion.listener.OnAutoCompleteListener;
import com.thecrackertechnology.dragonterminal.frontend.completion.listener.OnCandidateSelectedListener;
import com.thecrackertechnology.dragonterminal.frontend.completion.model.CompletionCandidate;
import com.thecrackertechnology.dragonterminal.frontend.completion.model.CompletionResult;
import com.thecrackertechnology.dragonterminal.frontend.completion.view.CandidatePopupWindow;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0011H\u0016J\u0012\u0010\u0019\u001a\u00020\u00112\b\u0010\u001a\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\nH\u0016J\b\u0010 \u001a\u00020\u0011H\u0002J\u0010\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\bH\u0002J\u0010\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u0014H\u0002J\u0010\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020'H\u0002J\b\u0010(\u001a\u00020\u0011H\u0002R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0005¨\u0006)"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermCompleteListener;", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/listener/OnAutoCompleteListener;", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/listener/OnCandidateSelectedListener;", "terminalView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "(Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;)V", "inputStack", "Ljava/util/Stack;", "", "lastCompletedIndex", "", "popupWindow", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/view/CandidatePopupWindow;", "getTerminalView", "()Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "setTerminalView", "clearChars", "", "fixLastCompletedIndex", "getCurrentEditingText", "", "onCandidateSelected", "candidate", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/model/CompletionCandidate;", "onCleanUp", "onCompletionRequired", "newText", "onFinishCompletion", "", "onKeyCode", "keyCode", "keyMod", "popChar", "pushChar", "char", "pushString", "string", "showAutoCompleteCandidates", "result", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/model/CompletionResult;", "triggerCompletion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: TermCompleteListener.kt */
public final class TermCompleteListener implements OnAutoCompleteListener, OnCandidateSelectedListener {
    private final Stack<Character> inputStack = new Stack<>();
    private int lastCompletedIndex;
    private CandidatePopupWindow popupWindow;
    private TerminalView terminalView;

    public TermCompleteListener(TerminalView terminalView2) {
        this.terminalView = terminalView2;
    }

    public final TerminalView getTerminalView() {
        return this.terminalView;
    }

    public final void setTerminalView(TerminalView terminalView2) {
        this.terminalView = terminalView2;
    }

    public void onKeyCode(int i, int i2) {
        if (i == 66) {
            clearChars();
            CandidatePopupWindow candidatePopupWindow = this.popupWindow;
            if (candidatePopupWindow != null) {
                candidatePopupWindow.dismiss();
            }
        } else if (i == 67) {
            popChar();
            fixLastCompletedIndex();
            triggerCompletion();
        }
    }

    private final void fixLastCompletedIndex() {
        this.lastCompletedIndex = Math.min(this.lastCompletedIndex, getCurrentEditingText().length() - 1);
    }

    public void onCompletionRequired(String str) {
        if (str != null) {
            if (!(str.length() == 0)) {
                pushString(str);
                triggerCompletion();
            }
        }
    }

    public void onCleanUp() {
        CandidatePopupWindow candidatePopupWindow = this.popupWindow;
        if (candidatePopupWindow != null) {
            candidatePopupWindow.dismiss();
        }
        CandidatePopupWindow candidatePopupWindow2 = this.popupWindow;
        if (candidatePopupWindow2 != null) {
            candidatePopupWindow2.cleanup();
        }
        this.popupWindow = null;
        this.terminalView = null;
    }

    public boolean onFinishCompletion() {
        CandidatePopupWindow candidatePopupWindow = this.popupWindow;
        if (candidatePopupWindow == null || !candidatePopupWindow.isShowing()) {
            return false;
        }
        candidatePopupWindow.dismiss();
        return true;
    }

    public void onCandidateSelected(CompletionCandidate completionCandidate) {
        TerminalSession currentSession;
        Intrinsics.checkParameterIsNotNull(completionCandidate, "candidate");
        TerminalView terminalView2 = this.terminalView;
        if (terminalView2 != null && (currentSession = terminalView2.getCurrentSession()) != null) {
            String currentEditingText = getCurrentEditingText();
            int i = this.lastCompletedIndex + 1;
            if (currentEditingText != null) {
                String substring = currentEditingText.substring(i);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                String completeString = completionCandidate.getCompleteString();
                int indexOf$default = StringsKt.indexOf$default((CharSequence) completeString, substring, 0, false, 6, (Object) null) + substring.length();
                if (indexOf$default > 0) {
                    for (int i2 = 0; i2 < indexOf$default; i2++) {
                        currentSession.write("\b");
                        popChar();
                    }
                }
                pushString(completeString);
                currentSession.write(completeString);
                this.lastCompletedIndex = this.inputStack.size();
                triggerCompletion();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
    }

    private final void triggerCompletion() {
        String currentEditingText = getCurrentEditingText();
        if (!(currentEditingText.length() == 0)) {
            CompletionResult tryCompleteFor = CompletionManager.INSTANCE.tryCompleteFor(currentEditingText);
            if (!tryCompleteFor.hasResult()) {
                tryCompleteFor.markScore(0);
                onFinishCompletion();
                return;
            }
            showAutoCompleteCandidates(tryCompleteFor);
        }
    }

    private final void showAutoCompleteCandidates(CompletionResult completionResult) {
        TerminalView terminalView2 = this.terminalView;
        CandidatePopupWindow candidatePopupWindow = this.popupWindow;
        if (terminalView2 != null) {
            if (candidatePopupWindow == null) {
                Context context = terminalView2.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "termView.context");
                candidatePopupWindow = new CandidatePopupWindow(context);
                candidatePopupWindow.setOnCandidateSelectedListener(this);
                this.popupWindow = candidatePopupWindow;
            }
            candidatePopupWindow.setCandidates(completionResult.getCandidates());
            candidatePopupWindow.show(terminalView2);
        }
    }

    private final String getCurrentEditingText() {
        StringBuilder sb = new StringBuilder();
        int size = this.inputStack.size();
        int lastIndexOf = this.inputStack.lastIndexOf(' ');
        if (lastIndexOf < 0) {
            lastIndexOf = -1;
        }
        Iterable intRange = new IntRange(lastIndexOf + 1, size - 1);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        Iterator it = intRange.iterator();
        while (it.hasNext()) {
            arrayList.add((Character) this.inputStack.get(((IntIterator) it).nextInt()));
        }
        ArrayList<Character> arrayList2 = new ArrayList<>();
        for (Object next : (List) arrayList) {
            Character ch = (Character) next;
            boolean z = false;
            char c = (char) 0;
            if ((ch == null || ch.charValue() != c) && (ch == null || ch.charValue() != ' ')) {
                z = true;
            }
            if (!z) {
                break;
            }
            arrayList2.add(next);
        }
        for (Character ch2 : arrayList2) {
            Intrinsics.checkExpressionValueIsNotNull(ch2, "it");
            sb.append(ch2.charValue());
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "builder.toString()");
        return sb2;
    }

    private final void clearChars() {
        this.inputStack.clear();
        this.lastCompletedIndex = 0;
    }

    private final void popChar() {
        if (!this.inputStack.isEmpty()) {
            this.inputStack.pop();
        }
    }

    private final void pushString(String str) {
        if (str != null) {
            char[] charArray = str.toCharArray();
            Intrinsics.checkExpressionValueIsNotNull(charArray, "(this as java.lang.String).toCharArray()");
            for (char pushChar : charArray) {
                pushChar(pushChar);
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private final void pushChar(char c) {
        this.inputStack.push(Character.valueOf(c));
    }
}
