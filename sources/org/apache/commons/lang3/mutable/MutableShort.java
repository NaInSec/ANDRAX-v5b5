package org.apache.commons.lang3.mutable;

import org.apache.commons.lang3.math.NumberUtils;

public class MutableShort extends Number implements Comparable<MutableShort>, Mutable<Number> {
    private static final long serialVersionUID = -2135791679;
    private short value;

    public MutableShort() {
    }

    public MutableShort(short s) {
        this.value = s;
    }

    public MutableShort(Number number) {
        this.value = number.shortValue();
    }

    public MutableShort(String str) throws NumberFormatException {
        this.value = Short.parseShort(str);
    }

    public Short getValue() {
        return Short.valueOf(this.value);
    }

    public void setValue(short s) {
        this.value = s;
    }

    public void setValue(Number number) {
        this.value = number.shortValue();
    }

    public void increment() {
        this.value = (short) (this.value + 1);
    }

    public short getAndIncrement() {
        short s = this.value;
        this.value = (short) (s + 1);
        return s;
    }

    public short incrementAndGet() {
        this.value = (short) (this.value + 1);
        return this.value;
    }

    public void decrement() {
        this.value = (short) (this.value - 1);
    }

    public short getAndDecrement() {
        short s = this.value;
        this.value = (short) (s - 1);
        return s;
    }

    public short decrementAndGet() {
        this.value = (short) (this.value - 1);
        return this.value;
    }

    public void add(short s) {
        this.value = (short) (this.value + s);
    }

    public void add(Number number) {
        this.value = (short) (this.value + number.shortValue());
    }

    public void subtract(short s) {
        this.value = (short) (this.value - s);
    }

    public void subtract(Number number) {
        this.value = (short) (this.value - number.shortValue());
    }

    public short addAndGet(short s) {
        this.value = (short) (this.value + s);
        return this.value;
    }

    public short addAndGet(Number number) {
        this.value = (short) (this.value + number.shortValue());
        return this.value;
    }

    public short getAndAdd(short s) {
        short s2 = this.value;
        this.value = (short) (s + s2);
        return s2;
    }

    public short getAndAdd(Number number) {
        short s = this.value;
        this.value = (short) (number.shortValue() + s);
        return s;
    }

    public short shortValue() {
        return this.value;
    }

    public int intValue() {
        return this.value;
    }

    public long longValue() {
        return (long) this.value;
    }

    public float floatValue() {
        return (float) this.value;
    }

    public double doubleValue() {
        return (double) this.value;
    }

    public Short toShort() {
        return Short.valueOf(shortValue());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MutableShort) || this.value != ((MutableShort) obj).shortValue()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.value;
    }

    public int compareTo(MutableShort mutableShort) {
        return NumberUtils.compare(this.value, mutableShort.value);
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
