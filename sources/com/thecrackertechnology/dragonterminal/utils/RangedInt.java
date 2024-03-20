package com.thecrackertechnology.dragonterminal.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\u0003J\u0006\u0010\f\u001a\u00020\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/utils/RangedInt;", "", "number", "", "range", "Lkotlin/ranges/IntRange;", "(ILkotlin/ranges/IntRange;)V", "getNumber", "()I", "getRange", "()Lkotlin/ranges/IntRange;", "decreaseOne", "increaseOne", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: RangedInt.kt */
public final class RangedInt {
    private final int number;
    private final IntRange range;

    public RangedInt(int i, IntRange intRange) {
        Intrinsics.checkParameterIsNotNull(intRange, "range");
        this.number = i;
        this.range = intRange;
    }

    public final int getNumber() {
        return this.number;
    }

    public final IntRange getRange() {
        return this.range;
    }

    public final int increaseOne() {
        int i = this.number + 1;
        if (i > this.range.getLast()) {
            return 0;
        }
        return i;
    }

    public final int decreaseOne() {
        int i = this.number - 1;
        return i < 0 ? this.range.getLast() : i;
    }
}
