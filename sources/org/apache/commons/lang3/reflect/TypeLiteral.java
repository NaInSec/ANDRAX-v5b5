package org.apache.commons.lang3.reflect;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import org.apache.commons.lang3.Validate;

public abstract class TypeLiteral<T> implements Typed<T> {
    private static final TypeVariable<Class<TypeLiteral>> T = TypeLiteral.class.getTypeParameters()[0];
    private final String toString;
    public final Type value;

    protected TypeLiteral() {
        Class<TypeLiteral> cls = TypeLiteral.class;
        this.value = (Type) Validate.notNull(TypeUtils.getTypeArguments(getClass(), cls).get(T), "%s does not assign type parameter %s", getClass(), TypeUtils.toLongString(T));
        this.toString = String.format("%s<%s>", new Object[]{cls.getSimpleName(), TypeUtils.toString(this.value)});
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TypeLiteral)) {
            return false;
        }
        return TypeUtils.equals(this.value, ((TypeLiteral) obj).value);
    }

    public int hashCode() {
        return this.value.hashCode() | 592;
    }

    public String toString() {
        return this.toString;
    }

    public Type getType() {
        return this.value;
    }
}
