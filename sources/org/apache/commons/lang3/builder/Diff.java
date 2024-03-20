package org.apache.commons.lang3.builder;

import java.lang.reflect.Type;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.apache.commons.lang3.tuple.Pair;

public abstract class Diff<T> extends Pair<T, T> {
    private static final long serialVersionUID = 1;
    private final String fieldName;
    private final Type type;

    protected Diff(String str) {
        Class<Diff> cls = Diff.class;
        this.type = (Type) ObjectUtils.defaultIfNull(TypeUtils.getTypeArguments(getClass(), cls).get(cls.getTypeParameters()[0]), Object.class);
        this.fieldName = str;
    }

    public final Type getType() {
        return this.type;
    }

    public final String getFieldName() {
        return this.fieldName;
    }

    public final String toString() {
        return String.format("[%s: %s, %s]", new Object[]{this.fieldName, getLeft(), getRight()});
    }

    public final T setValue(T t) {
        throw new UnsupportedOperationException("Cannot alter Diff object.");
    }
}
