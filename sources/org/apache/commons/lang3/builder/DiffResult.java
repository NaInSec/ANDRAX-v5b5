package org.apache.commons.lang3.builder;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.Validate;

public class DiffResult implements Iterable<Diff<?>> {
    private static final String DIFFERS_STRING = "differs from";
    public static final String OBJECTS_SAME_STRING = "";
    private final List<Diff<?>> diffs;
    private final Object lhs;
    private final Object rhs;
    private final ToStringStyle style;

    DiffResult(Object obj, Object obj2, List<Diff<?>> list, ToStringStyle toStringStyle) {
        boolean z = true;
        Validate.isTrue(obj != null, "Left hand object cannot be null", new Object[0]);
        Validate.isTrue(obj2 != null, "Right hand object cannot be null", new Object[0]);
        Validate.isTrue(list == null ? false : z, "List of differences cannot be null", new Object[0]);
        this.diffs = list;
        this.lhs = obj;
        this.rhs = obj2;
        if (toStringStyle == null) {
            this.style = ToStringStyle.DEFAULT_STYLE;
        } else {
            this.style = toStringStyle;
        }
    }

    public List<Diff<?>> getDiffs() {
        return Collections.unmodifiableList(this.diffs);
    }

    public int getNumberOfDiffs() {
        return this.diffs.size();
    }

    public ToStringStyle getToStringStyle() {
        return this.style;
    }

    public String toString() {
        return toString(this.style);
    }

    public String toString(ToStringStyle toStringStyle) {
        if (this.diffs.size() == 0) {
            return "";
        }
        ToStringBuilder toStringBuilder = new ToStringBuilder(this.lhs, toStringStyle);
        ToStringBuilder toStringBuilder2 = new ToStringBuilder(this.rhs, toStringStyle);
        for (Diff next : this.diffs) {
            toStringBuilder.append(next.getFieldName(), next.getLeft());
            toStringBuilder2.append(next.getFieldName(), next.getRight());
        }
        return String.format("%s %s %s", new Object[]{toStringBuilder.build(), DIFFERS_STRING, toStringBuilder2.build()});
    }

    public Iterator<Diff<?>> iterator() {
        return this.diffs.iterator();
    }
}
