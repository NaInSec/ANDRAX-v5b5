package de.mrapp.android.util.datastructure;

public class Triple<F, S, T> {
    public final F first;
    public final S second;
    public final T third;

    public Triple(F f, S s, T t) {
        this.first = f;
        this.second = s;
        this.third = t;
    }

    public static <F, S, T> Triple<F, S, T> create(F f, S s, T t) {
        return new Triple<>(f, s, t);
    }

    public final int hashCode() {
        F f = this.first;
        int i = 0;
        int hashCode = ((f == null ? 0 : f.hashCode()) + 31) * 31;
        S s = this.second;
        int hashCode2 = (hashCode + (s == null ? 0 : s.hashCode())) * 31;
        T t = this.third;
        if (t != null) {
            i = t.hashCode();
        }
        return hashCode2 + i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Triple triple = (Triple) obj;
        F f = this.first;
        if (f == null) {
            if (triple.first != null) {
                return false;
            }
        } else if (!f.equals(triple.first)) {
            return false;
        }
        S s = this.second;
        if (s == null) {
            if (triple.second != null) {
                return false;
            }
        } else if (!s.equals(triple.second)) {
            return false;
        }
        T t = this.third;
        if (t == null) {
            if (triple.third != null) {
                return false;
            }
        } else if (!t.equals(triple.third)) {
            return false;
        }
        return true;
    }
}
