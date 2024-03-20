package org.apache.commons.lang3.mutable;

import org.apache.commons.lang3.math.NumberUtils;

public class MutableByte extends Number implements Comparable<MutableByte>, Mutable<Number> {
    private static final long serialVersionUID = -1585823265;
    private byte value;

    public MutableByte() {
    }

    public MutableByte(byte b) {
        this.value = b;
    }

    public MutableByte(Number number) {
        this.value = number.byteValue();
    }

    public MutableByte(String str) throws NumberFormatException {
        this.value = Byte.parseByte(str);
    }

    public Byte getValue() {
        return Byte.valueOf(this.value);
    }

    public void setValue(byte b) {
        this.value = b;
    }

    public void setValue(Number number) {
        this.value = number.byteValue();
    }

    public void increment() {
        this.value = (byte) (this.value + 1);
    }

    public byte getAndIncrement() {
        byte b = this.value;
        this.value = (byte) (b + 1);
        return b;
    }

    public byte incrementAndGet() {
        this.value = (byte) (this.value + 1);
        return this.value;
    }

    public void decrement() {
        this.value = (byte) (this.value - 1);
    }

    public byte getAndDecrement() {
        byte b = this.value;
        this.value = (byte) (b - 1);
        return b;
    }

    public byte decrementAndGet() {
        this.value = (byte) (this.value - 1);
        return this.value;
    }

    public void add(byte b) {
        this.value = (byte) (this.value + b);
    }

    public void add(Number number) {
        this.value = (byte) (this.value + number.byteValue());
    }

    public void subtract(byte b) {
        this.value = (byte) (this.value - b);
    }

    public void subtract(Number number) {
        this.value = (byte) (this.value - number.byteValue());
    }

    public byte addAndGet(byte b) {
        this.value = (byte) (this.value + b);
        return this.value;
    }

    public byte addAndGet(Number number) {
        this.value = (byte) (this.value + number.byteValue());
        return this.value;
    }

    public byte getAndAdd(byte b) {
        byte b2 = this.value;
        this.value = (byte) (b + b2);
        return b2;
    }

    public byte getAndAdd(Number number) {
        byte b = this.value;
        this.value = (byte) (number.byteValue() + b);
        return b;
    }

    public byte byteValue() {
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

    public Byte toByte() {
        return Byte.valueOf(byteValue());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MutableByte) || this.value != ((MutableByte) obj).byteValue()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.value;
    }

    public int compareTo(MutableByte mutableByte) {
        return NumberUtils.compare(this.value, mutableByte.value);
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
