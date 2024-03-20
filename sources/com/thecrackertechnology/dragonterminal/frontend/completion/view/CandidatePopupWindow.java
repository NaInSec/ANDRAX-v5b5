package com.thecrackertechnology.dragonterminal.frontend.completion.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.backend.TerminalColors;
import com.thecrackertechnology.dragonterminal.component.colorscheme.ColorSchemeComponent;
import com.thecrackertechnology.dragonterminal.frontend.completion.listener.OnCandidateSelectedListener;
import com.thecrackertechnology.dragonterminal.frontend.completion.model.CompletionCandidate;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002$%B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\u0019H\u0002J\u0006\u0010\u001f\u001a\u00020\u001dJ\u0006\u0010 \u001a\u00020\u001bJ\u000e\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\"\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/completion/view/CandidatePopupWindow;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "candidateAdapter", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/view/CandidatePopupWindow$CandidateAdapter;", "candidateListView", "Landroid/widget/ListView;", "candidates", "", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/model/CompletionCandidate;", "getCandidates", "()Ljava/util/List;", "setCandidates", "(Ljava/util/List;)V", "getContext", "()Landroid/content/Context;", "onCandidateSelectedListener", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/listener/OnCandidateSelectedListener;", "getOnCandidateSelectedListener", "()Lcom/thecrackertechnology/dragonterminal/frontend/completion/listener/OnCandidateSelectedListener;", "setOnCandidateSelectedListener", "(Lcom/thecrackertechnology/dragonterminal/frontend/completion/listener/OnCandidateSelectedListener;)V", "popupWindow", "Landroid/widget/PopupWindow;", "wantsToFinish", "", "cleanup", "", "createPopupWindow", "dismiss", "isShowing", "show", "terminalView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "CandidateAdapter", "CandidateViewHolder", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: CandidatePopupWindow.kt */
public final class CandidatePopupWindow {
    private CandidateAdapter candidateAdapter;
    private ListView candidateListView;
    private List<CompletionCandidate> candidates;
    private final Context context;
    private OnCandidateSelectedListener onCandidateSelectedListener;
    private PopupWindow popupWindow;
    private boolean wantsToFinish;

    public CandidatePopupWindow(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
    }

    public final Context getContext() {
        return this.context;
    }

    public final List<CompletionCandidate> getCandidates() {
        return this.candidates;
    }

    public final void setCandidates(List<CompletionCandidate> list) {
        this.candidates = list;
    }

    public final OnCandidateSelectedListener getOnCandidateSelectedListener() {
        return this.onCandidateSelectedListener;
    }

    public final void setOnCandidateSelectedListener(OnCandidateSelectedListener onCandidateSelectedListener2) {
        this.onCandidateSelectedListener = onCandidateSelectedListener2;
    }

    public final void show(TerminalView terminalView) {
        Intrinsics.checkParameterIsNotNull(terminalView, "terminalView");
        if (this.popupWindow == null && !this.wantsToFinish) {
            this.popupWindow = createPopupWindow();
        }
        CandidateAdapter candidateAdapter2 = this.candidateAdapter;
        if (candidateAdapter2 != null) {
            candidateAdapter2.notifyDataSetChanged();
        }
        PopupWindow popupWindow2 = this.popupWindow;
        if (popupWindow2 != null) {
            View contentView = popupWindow2.getContentView();
            if (contentView instanceof MaxHeightView) {
                ((MaxHeightView) contentView).setMaxHeight(terminalView.getHeight());
            }
            popupWindow2.showAtLocation(terminalView, 0, terminalView.getCursorAbsoluteX(), terminalView.getCursorAbsoluteY());
        }
    }

    public final void dismiss() {
        PopupWindow popupWindow2 = this.popupWindow;
        if (popupWindow2 != null) {
            popupWindow2.dismiss();
        }
    }

    public final boolean isShowing() {
        PopupWindow popupWindow2 = this.popupWindow;
        if (popupWindow2 != null) {
            return popupWindow2.isShowing();
        }
        return false;
    }

    private final PopupWindow createPopupWindow() {
        PopupWindow popupWindow2 = new PopupWindow(this.context);
        popupWindow2.setOutsideTouchable(true);
        popupWindow2.setTouchable(true);
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.popup_auto_complete, (ViewGroup) null, false);
        ListView listView = (ListView) inflate.findViewById(R.id.popup_complete_candidate_list);
        this.candidateAdapter = new CandidateAdapter(this);
        Intrinsics.checkExpressionValueIsNotNull(listView, "listView");
        listView.setAdapter(this.candidateAdapter);
        listView.setOnItemClickListener(new CandidatePopupWindow$createPopupWindow$1(this));
        this.candidateListView = listView;
        popupWindow2.setContentView(inflate);
        return popupWindow2;
    }

    public final void cleanup() {
        this.wantsToFinish = true;
        this.popupWindow = null;
        this.candidateListView = null;
        this.candidateAdapter = null;
        this.candidates = null;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\bH\u0016J$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/completion/view/CandidatePopupWindow$CandidateAdapter;", "Landroid/widget/BaseAdapter;", "candidatePopupWindow", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/view/CandidatePopupWindow;", "(Lcom/thecrackertechnology/dragonterminal/frontend/completion/view/CandidatePopupWindow;)V", "getCandidatePopupWindow", "()Lcom/thecrackertechnology/dragonterminal/frontend/completion/view/CandidatePopupWindow;", "getCount", "", "getItem", "", "position", "getItemId", "", "getView", "Landroid/view/View;", "convertView", "parent", "Landroid/view/ViewGroup;", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: CandidatePopupWindow.kt */
    public static final class CandidateAdapter extends BaseAdapter {
        private final CandidatePopupWindow candidatePopupWindow;

        public long getItemId(int i) {
            return (long) i;
        }

        public CandidateAdapter(CandidatePopupWindow candidatePopupWindow2) {
            Intrinsics.checkParameterIsNotNull(candidatePopupWindow2, "candidatePopupWindow");
            this.candidatePopupWindow = candidatePopupWindow2;
        }

        public final CandidatePopupWindow getCandidatePopupWindow() {
            return this.candidatePopupWindow;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            CandidateViewHolder candidateViewHolder;
            if (view != null) {
                Object tag = view.getTag();
                if (tag != null) {
                    candidateViewHolder = (CandidateViewHolder) tag;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type com.thecrackertechnology.dragonterminal.frontend.completion.view.CandidatePopupWindow.CandidateViewHolder");
                }
            } else {
                view = LayoutInflater.from(this.candidatePopupWindow.getContext()).inflate(R.layout.item_complete_candidate, (ViewGroup) null, false);
                Intrinsics.checkExpressionValueIsNotNull(view, "convertView");
                candidateViewHolder = new CandidateViewHolder(view);
                view.setTag(candidateViewHolder);
            }
            Object item = getItem(i);
            if (item != null) {
                CompletionCandidate completionCandidate = (CompletionCandidate) item;
                candidateViewHolder.getDisplay().setText(completionCandidate.getDisplayName());
                if (completionCandidate.getDescription() != null) {
                    candidateViewHolder.getSplitView().setVisibility(0);
                    candidateViewHolder.getDescription().setVisibility(0);
                    candidateViewHolder.getDescription().setText(completionCandidate.getDescription());
                } else {
                    candidateViewHolder.getSplitView().setVisibility(8);
                    candidateViewHolder.getDescription().setVisibility(8);
                }
                return view;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.thecrackertechnology.dragonterminal.frontend.completion.model.CompletionCandidate");
        }

        public Object getItem(int i) {
            List<CompletionCandidate> candidates = this.candidatePopupWindow.getCandidates();
            if (candidates != null) {
                return candidates.get(i);
            }
            return null;
        }

        public int getCount() {
            List<CompletionCandidate> candidates = this.candidatePopupWindow.getCandidates();
            if (candidates != null) {
                return candidates.size();
            }
            return 0;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/completion/view/CandidatePopupWindow$CandidateViewHolder;", "", "rootView", "Landroid/view/View;", "(Landroid/view/View;)V", "description", "Landroid/widget/TextView;", "getDescription", "()Landroid/widget/TextView;", "display", "getDisplay", "splitView", "getSplitView", "()Landroid/view/View;", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: CandidatePopupWindow.kt */
    public static final class CandidateViewHolder {
        private final TextView description;
        private final TextView display;
        private final View splitView;

        public CandidateViewHolder(View view) {
            Intrinsics.checkParameterIsNotNull(view, "rootView");
            View findViewById = view.findViewById(R.id.complete_display);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "rootView.findViewById<Te…w>(R.id.complete_display)");
            this.display = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.complete_description);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "rootView.findViewById<Te….id.complete_description)");
            this.description = (TextView) findViewById2;
            View findViewById3 = view.findViewById(R.id.complete_split);
            Intrinsics.checkExpressionValueIsNotNull(findViewById3, "rootView.findViewById(R.id.complete_split)");
            this.splitView = findViewById3;
            int parse = TerminalColors.parse(((ColorSchemeComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, ColorSchemeComponent.class, false, 2, (Object) null)).getCurrentColorScheme().getForegroundColor());
            this.display.setTextColor(parse);
            this.description.setTextColor(parse);
        }

        public final TextView getDisplay() {
            return this.display;
        }

        public final TextView getDescription() {
            return this.description;
        }

        public final View getSplitView() {
            return this.splitView;
        }
    }
}
