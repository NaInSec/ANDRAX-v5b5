package org.apache.commons.lang3.mutable;

public class MutableFloat extends Number implements Comparable<MutableFloat>, Mutable<Number> {
    private static final long serialVersionUID = 5787169186L;
    private float value;

    public MutableFloat() {
    }

    public MutableFloat(float f) {
        this.value = f;
    }

    public MutableFloat(Number number) {
        this.value = number.floatValue();
    }

    public MutableFloat(String str) throws NumberFormatException {
        this.value = Float.parseFloat(str);
    }

    public Float getValue() {
        return Float.valueOf(this.value);
    }

    public void setValue(float f) {
        this.value = f;
    }

    public void setValue(Number number) {
        this.value = number.floatValue();
    }

    public boolean isNaN() {
        return Float.isNaN(this.value);
    }

    public boolean isInfinite() {
        return Float.isInfinite(this.value);
    }

    public void increment() {
        this.value += 1.0f;
    }

    public float getAndIncrement() {
        float f = this.value;
        this.value = 1.0f + f;
        return f;
    }

    public float incrementAndGet() {
        this.value += 1.0f;
        return this.value;
    }

    public void decrement() {
        this.value -= 1.0f;
    }

    public float getAndDecrement() {
        float f = this.value;
        this.value = f - 1.0f;
        return f;
    }

    public float decrementAndGet() {
        this.value -= 1.0f;
        return this.value;
    }

    public void add(float f) {
        this.value += f;
    }

    public void add(Number number) {
        this.value += number.floatValue();
    }

    public void subtract(float f) {
        this.value -= f;
    }

    public void subtract(Number number) {
        this.value -= number.floatValue();
    }

    public float addAndGet(float f) {
        this.value += f;
        return this.value;
    }

    public float addAndGet(Number number) {
        this.value += number.floatValue();
        return this.value;
    }

    public float getAndAdd(float f) {
        float f2 = this.value;
        this.value = f + f2;
        return f2;
    }

    public float getAndAdd(Number number) {
        float f = this.value;
        this.value = number.floatValue() + f;
        return f;
    }

    public int intValue() {
        return (int) this.value;
    }

    public long longValue() {
        return (long) this.value;
    }

    public float floatValue() {
        return this.value;
    }

    public double doubleValue() {
        return (double) this.value;
    }

    public Float toFloat() {
        return Float.valueOf(floatValue());
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableFloat) && Float.floatToIntBits(((MutableFloat) obj).value) == Float.floatToIntBits(this.value);
    }

    public int hashCode() {
        return Float.floatToIntBits(this.value);
    }

    public int compareTo(MutableFloat mutableFloat) {
        return Float.compare(this.value, mutableFloat.value);
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
