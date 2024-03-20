package com.thecrackertechnology.dragonterminal.component.pm;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/thecrackertechnology/dragonterminal/component/pm/Source;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: SourceHelper.kt */
final class SourceHelper$syncSource$content$1$1 extends Lambda implements Function1<Source, String> {
    public static final SourceHelper$syncSource$content$1$1 INSTANCE = new SourceHelper$syncSource$content$1$1();

    SourceHelper$syncSource$content$1$1() {
        super(1);
    }

    public final String invoke(Source source) {
        Intrinsics.checkParameterIsNotNull(source, "it");
        return "deb " + source.url + ' ' + source.repo + 10;
    }
}
