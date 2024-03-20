package org.apache.commons.lang3.mutable;

public class MutableDouble extends Number implements Comparable<MutableDouble>, Mutable<Number> {
    private static final long serialVersionUID = 1587163916;
    private double value;

    public MutableDouble() {
    }

    public MutableDouble(double d) {
        this.value = d;
    }

    public MutableDouble(Number number) {
        this.value = number.doubleValue();
    }

    public MutableDouble(String str) throws NumberFormatException {
        this.value = Double.parseDouble(str);
    }

    public Double getValue() {
        return Double.valueOf(this.value);
    }

    public void setValue(double d) {
        this.value = d;
    }

    public void setValue(Number number) {
        this.value = number.doubleValue();
    }

    public boolean isNaN() {
        return Double.isNaN(this.value);
    }

    public boolean isInfinite() {
        return Double.isInfinite(this.value);
    }

    public void increment() {
        this.value += 1.0d;
    }

    public double getAndIncrement() {
        double d = this.value;
        this.value = 1.0d + d;
        return d;
    }

    public double incrementAndGet() {
        this.value += 1.0d;
        return this.value;
    }

    public void decrement() {
        this.value -= 1.0d;
    }

    public double getAndDecrement() {
        double d = this.value;
        this.value = d - 1.0d;
        return d;
    }

    public double decrementAndGet() {
        this.value -= 1.0d;
        return this.value;
    }

    public void add(double d) {
        this.value += d;
    }

    public void add(Number number) {
        this.value += number.doubleValue();
    }

    public void subtract(double d) {
        this.value -= d;
    }

    public void subtract(Number number) {
        this.value -= number.doubleValue();
    }

    public double addAndGet(double d) {
        this.value += d;
        return this.value;
    }

    public double addAndGet(Number number) {
        this.value += number.doubleValue();
        return this.value;
    }

    public double getAndAdd(double d) {
        double d2 = this.value;
        this.value = d + d2;
        return d2;
    }

    public double getAndAdd(Number number) {
        double d = this.value;
        this.value = number.doubleValue() + d;
        return d;
    }

    public int intValue() {
        return (int) this.value;
    }

    public long longValue() {
        return (long) this.value;
    }

    public float floatValue() {
        return (float) this.value;
    }

    public double doubleValue() {
        return this.value;
    }

    public Double toDouble() {
        return Double.valueOf(doubleValue());
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableDouble) && Double.doubleToLongBits(((MutableDouble) obj).value) == Double.doubleToLongBits(this.value);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.value);
        return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    }

    public int compareTo(MutableDouble mutableDouble) {
        return Double.compare(this.value, mutableDouble.value);
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
