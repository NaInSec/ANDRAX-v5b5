package com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.view.GravityCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import com.thecrackertechnology.andrax.AndraxApp;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.component.extrakey.ExtraKeyComponent;
import com.thecrackertechnology.dragonterminal.component.extrakey.NeoExtraKey;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.button.ControlButton;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.button.IExtraButton;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.button.RepeatableButton;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.button.StatedControlButton;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.impl.ArrowButton;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000_\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t*\u0001\u000b\u0018\u0000 62\u00020\u0001:\u00016B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fJ\u0018\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u000fH\u0002J \u0010\u001b\u001a\u00020\u00192\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\u0006\u0010\u001a\u001a\u00020\u000fH\u0002J\u000e\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fJ\b\u0010 \u001a\u00020!H\u0002J\u0006\u0010\"\u001a\u00020\u0019J\b\u0010#\u001a\u00020\u0001H\u0002J\u0019\u0010$\u001a\u00020\u00192\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0012H\u0002¢\u0006\u0002\u0010&J\u0010\u0010'\u001a\u00020\u00012\u0006\u0010(\u001a\u00020!H\u0002J\b\u0010)\u001a\u00020\u0019H\u0002J\u0006\u0010*\u001a\u00020\u0019J\u001a\u0010+\u001a\u00020\u00122\u0006\u0010,\u001a\u00020!2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\u0006\u0010/\u001a\u00020\u0012J\u0006\u00100\u001a\u00020\u0012J\u000e\u00101\u001a\u00020\u00192\u0006\u00102\u001a\u00020!J\u0010\u00103\u001a\u00020\u00192\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\b\u00104\u001a\u00020\u0019H\u0002J\u0006\u00105\u001a\u00020\u0019R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0004¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "ALT", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/StatedControlButton;", "CTRL", "EXPAND_BUTTONS", "com/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView$EXPAND_BUTTONS$1", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView$EXPAND_BUTTONS$1;", "builtinKeys", "", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/IExtraButton;", "buttonBars", "buttonPanelExpanded", "", "extraKeyComponent", "Lcom/thecrackertechnology/dragonterminal/component/extrakey/ExtraKeyComponent;", "typeface", "Landroid/graphics/Typeface;", "userKeys", "addBuiltinKey", "", "button", "addKeyButton", "contentView", "extraButton", "buttons", "addUserKey", "calculateButtonWidth", "", "clearUserKeys", "createNewButtonBar", "expandButtonPanel", "forceSetExpanded", "(Ljava/lang/Boolean;)V", "getButtonBarOrNew", "position", "initBuiltinKeys", "loadDefaultUserKeys", "onKeyDown", "keyCode", "event", "Landroid/view/KeyEvent;", "readAltButton", "readControlButton", "setTextColor", "textColor", "setTypeface", "updateButtonBars", "updateButtons", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ExtraKeysView.kt */
public final class ExtraKeysView extends LinearLayout {
    private static final ArrowButton ARROW_DOWN = new ArrowButton(IExtraButton.Companion.getKEY_ARROW_DOWN());
    private static final ArrowButton ARROW_LEFT = new ArrowButton(IExtraButton.Companion.getKEY_ARROW_LEFT());
    private static final ArrowButton ARROW_RIGHT = new ArrowButton(IExtraButton.Companion.getKEY_ARROW_RIGHT());
    private static final ArrowButton ARROW_UP = new ArrowButton(IExtraButton.Companion.getKEY_ARROW_UP());
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final float DEFAULT_ALPHA = DEFAULT_ALPHA;
    private static final ControlButton END = new ControlButton(IExtraButton.Companion.getKEY_END());
    private static final ControlButton ESC = new ControlButton(IExtraButton.Companion.getKEY_ESC());
    private static final float EXPANDED_ALPHA = EXPANDED_ALPHA;
    private static final ControlButton HOME = new ControlButton(IExtraButton.Companion.getKEY_HOME());
    private static final int MAX_BUTTONS_PER_LINE = 7;
    private static final RepeatableButton PAGE_DOWN = new RepeatableButton(IExtraButton.Companion.getKEY_PAGE_DOWN());
    private static final RepeatableButton PAGE_UP = new RepeatableButton(IExtraButton.Companion.getKEY_PAGE_UP());
    private static final ControlButton TAB = new ControlButton(IExtraButton.Companion.getKEY_TAB());
    private static final ExtraKeysView$Companion$TOGGLE_IME$1 TOGGLE_IME = new ExtraKeysView$Companion$TOGGLE_IME$1(IExtraButton.Companion.getKEY_TOGGLE_IME());
    private static final int USER_KEYS_BUTTON_LINE_START = 2;
    private final StatedControlButton ALT = new StatedControlButton(IExtraButton.Companion.getKEY_ALT(), false, 2, (DefaultConstructorMarker) null);
    private final StatedControlButton CTRL = new StatedControlButton(IExtraButton.Companion.getKEY_CTRL(), false, 2, (DefaultConstructorMarker) null);
    private final ExtraKeysView$EXPAND_BUTTONS$1 EXPAND_BUTTONS = new ExtraKeysView$EXPAND_BUTTONS$1(this, IExtraButton.Companion.getKEY_SHOW_ALL_BUTTONS());
    private HashMap _$_findViewCache;
    private final List<IExtraButton> builtinKeys = new ArrayList();
    private final List<LinearLayout> buttonBars = new ArrayList();
    private boolean buttonPanelExpanded;
    private final ExtraKeyComponent extraKeyComponent;
    private Typeface typeface;
    private final List<IExtraButton> userKeys = new ArrayList();

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExtraKeysView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attrs");
        setAlpha(DEFAULT_ALPHA);
        setGravity(48);
        setOrientation(1);
        this.typeface = Typeface.createFromAsset(context.getAssets(), "eks_font.ttf");
        this.extraKeyComponent = (ExtraKeyComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, ExtraKeyComponent.class, false, 2, (Object) null);
        initBuiltinKeys();
        loadDefaultUserKeys();
        updateButtons();
        expandButtonPanel(false);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00007\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0004*\u0001\u0016\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXD¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tXD¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0010XD¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView$Companion;", "", "()V", "ARROW_DOWN", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/impl/ArrowButton;", "ARROW_LEFT", "ARROW_RIGHT", "ARROW_UP", "DEFAULT_ALPHA", "", "END", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/ControlButton;", "ESC", "EXPANDED_ALPHA", "HOME", "MAX_BUTTONS_PER_LINE", "", "PAGE_DOWN", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/RepeatableButton;", "PAGE_UP", "TAB", "TOGGLE_IME", "com/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView$Companion$TOGGLE_IME$1", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView$Companion$TOGGLE_IME$1;", "USER_KEYS_BUTTON_LINE_START", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: ExtraKeysView.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent == null || keyEvent.getAction() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        if (!this.buttonPanelExpanded) {
            return false;
        }
        expandButtonPanel$default(this, (Boolean) null, 1, (Object) null);
        return true;
    }

    public final void setTextColor(int i) {
        IExtraButton.Companion.setNORMAL_TEXT_COLOR(i);
        updateButtons();
    }

    public final void setTypeface(Typeface typeface2) {
        this.typeface = typeface2;
        updateButtons();
    }

    public final boolean readControlButton() {
        return this.CTRL.readState();
    }

    public final boolean readAltButton() {
        return this.ALT.readState();
    }

    public final void addUserKey(IExtraButton iExtraButton) {
        Intrinsics.checkParameterIsNotNull(iExtraButton, "button");
        addKeyButton(this.userKeys, iExtraButton);
    }

    public final void addBuiltinKey(IExtraButton iExtraButton) {
        Intrinsics.checkParameterIsNotNull(iExtraButton, "button");
        addKeyButton(this.builtinKeys, iExtraButton);
    }

    public final void clearUserKeys() {
        this.userKeys.clear();
    }

    public final void loadDefaultUserKeys() {
        clearUserKeys();
        ExtraKeyComponent extraKeyComponent2 = this.extraKeyComponent;
        StringBuilder sb = new StringBuilder();
        File filesDir = AndraxApp.Companion.get().getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "AndraxApp.get().filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/home/.neoterm/eks/default.nl");
        NeoExtraKey neoExtraKey = (NeoExtraKey) extraKeyComponent2.loadConfigure(new File(sb.toString()));
        if (neoExtraKey != null) {
            this.userKeys.addAll(neoExtraKey.getShortcutKeys());
        }
    }

    public final void updateButtons() {
        for (LinearLayout removeAllViews : this.buttonBars) {
            removeAllViews.removeAllViews();
        }
        int i = 0;
        int i2 = 0;
        for (Object next : CollectionsKt.plus(this.builtinKeys, this.userKeys)) {
            int i3 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            addKeyButton(getButtonBarOrNew(i2), (IExtraButton) next);
            i2 = i3 / MAX_BUTTONS_PER_LINE;
            i = i3;
        }
        updateButtonBars();
    }

    private final void updateButtonBars() {
        removeAllViews();
        for (T addView : CollectionsKt.asReversedMutable(this.buttonBars)) {
            addView(addView);
        }
    }

    static /* synthetic */ void expandButtonPanel$default(ExtraKeysView extraKeysView, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = null;
        }
        extraKeysView.expandButtonPanel(bool);
    }

    private final void expandButtonPanel(Boolean bool) {
        if (this.buttonBars.size() > 2) {
            int i = 0;
            this.buttonPanelExpanded = bool != null ? bool.booleanValue() : !this.buttonPanelExpanded;
            if (!this.buttonPanelExpanded) {
                i = 8;
            }
            setAlpha(this.buttonPanelExpanded ? EXPANDED_ALPHA : DEFAULT_ALPHA);
            Iterable intRange = new IntRange(USER_KEYS_BUTTON_LINE_START, this.buttonBars.size() - 1);
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
            Iterator it = intRange.iterator();
            while (it.hasNext()) {
                arrayList.add(this.buttonBars.get(((IntIterator) it).nextInt()));
            }
            for (LinearLayout visibility : (List) arrayList) {
                visibility.setVisibility(i);
            }
        }
    }

    private final LinearLayout createNewButtonBar() {
        LinearLayout.LayoutParams layoutParams;
        LinearLayout linearLayout = new LinearLayout(getContext());
        if (NeoPreference.INSTANCE.isExplicitExtraKeysWeightEnabled()) {
            layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
        } else {
            layoutParams = new LinearLayout.LayoutParams(-1, -2);
        }
        layoutParams.setMargins(0, 0, 0, 0);
        linearLayout.setPadding(0, 0, 0, 0);
        linearLayout.setGravity(GravityCompat.START);
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams(layoutParams);
        return linearLayout;
    }

    private final LinearLayout getButtonBarOrNew(int i) {
        if (i >= this.buttonBars.size()) {
            int i2 = 0;
            int size = (i - this.buttonBars.size()) + 1;
            if (size >= 0) {
                while (true) {
                    this.buttonBars.add(createNewButtonBar());
                    if (i2 == size) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return this.buttonBars.get(i);
    }

    private final void addKeyButton(List<IExtraButton> list, IExtraButton iExtraButton) {
        if (list != null && !list.contains(iExtraButton)) {
            list.add(iExtraButton);
        }
    }

    private final void addKeyButton(LinearLayout linearLayout, IExtraButton iExtraButton) {
        Button makeButton = iExtraButton.makeButton(getContext(), (AttributeSet) null, 16843567);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.setGravity(17);
        layoutParams.width = calculateButtonWidth();
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        layoutParams.height = context.getResources().getDimensionPixelSize(R.dimen.eks_height);
        layoutParams.setMargins(0, 0, 0, 0);
        makeButton.setLayoutParams(layoutParams);
        makeButton.setMaxLines(1);
        makeButton.setTypeface(this.typeface);
        makeButton.setText(iExtraButton.getDisplayText());
        makeButton.setPadding(0, 0, 0, 0);
        makeButton.setTextColor(IExtraButton.Companion.getNORMAL_TEXT_COLOR());
        makeButton.setAllCaps(false);
        makeButton.setOnClickListener(new ExtraKeysView$addKeyButton$1(this, makeButton, iExtraButton));
        linearLayout.addView(makeButton);
    }

    private final void initBuiltinKeys() {
        addBuiltinKey(ESC);
        addBuiltinKey(TAB);
        addBuiltinKey(PAGE_DOWN);
        addBuiltinKey(ARROW_LEFT);
        addBuiltinKey(ARROW_DOWN);
        addBuiltinKey(ARROW_RIGHT);
        addBuiltinKey(TOGGLE_IME);
        addBuiltinKey(this.CTRL);
        addBuiltinKey(this.ALT);
        addBuiltinKey(PAGE_UP);
        addBuiltinKey(HOME);
        addBuiltinKey(ARROW_UP);
        addBuiltinKey(END);
        addBuiltinKey(this.EXPAND_BUTTONS);
    }

    private final int calculateButtonWidth() {
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        return resources.getDisplayMetrics().widthPixels / MAX_BUTTONS_PER_LINE;
    }
}
