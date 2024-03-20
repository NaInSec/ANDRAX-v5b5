package io.neolang.runtime.type;

import com.thecrackertechnology.dragonterminal.component.extrakey.NeoExtraKey;
import io.neolang.runtime.context.NeoLangContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010(\n\u0002\b\u0002\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0015B\u001f\b\u0002\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0011\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0002J\u0016\u0010\u000f\u001a\u00020\r2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016J\u0011\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0006H\u0002J\b\u0010\u0012\u001a\u00020\rH\u0016J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u0014H\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lio/neolang/runtime/type/NeoLangArray;", "", "Lio/neolang/runtime/type/NeoLangArrayElement;", "elements", "", "size", "", "(Ljava/util/List;I)V", "getElements", "()Ljava/util/List;", "getSize", "()I", "contains", "", "element", "containsAll", "get", "index", "isEmpty", "iterator", "", "Companion", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangArray.kt */
public final class NeoLangArray implements Collection<NeoLangArrayElement>, KMappedMarker {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<NeoLangArrayElement> elements;
    private final int size;

    public boolean add(NeoLangArrayElement neoLangArrayElement) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection<? extends NeoLangArrayElement> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean removeIf(Predicate<? super NeoLangArrayElement> predicate) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    public <T> T[] toArray(T[] tArr) {
        return CollectionToArray.toArray(this, tArr);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\t"}, d2 = {"Lio/neolang/runtime/type/NeoLangArray$Companion;", "", "()V", "createFromContext", "Lio/neolang/runtime/type/NeoLangArray;", "context", "Lio/neolang/runtime/context/NeoLangContext;", "BlockElement", "PrimaryElement", "NeoLang"}, k = 1, mv = {1, 1, 15})
    /* compiled from: NeoLangArray.kt */
    public static final class Companion {

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lio/neolang/runtime/type/NeoLangArray$Companion$PrimaryElement;", "Lio/neolang/runtime/type/NeoLangArrayElement;", "primaryValue", "Lio/neolang/runtime/type/NeoLangValue;", "(Lio/neolang/runtime/type/NeoLangValue;)V", "getPrimaryValue", "()Lio/neolang/runtime/type/NeoLangValue;", "eval", "NeoLang"}, k = 1, mv = {1, 1, 15})
        /* compiled from: NeoLangArray.kt */
        public static final class PrimaryElement extends NeoLangArrayElement {
            private final NeoLangValue primaryValue;

            public PrimaryElement(NeoLangValue neoLangValue) {
                Intrinsics.checkParameterIsNotNull(neoLangValue, "primaryValue");
                this.primaryValue = neoLangValue;
            }

            public final NeoLangValue getPrimaryValue() {
                return this.primaryValue;
            }

            public NeoLangValue eval() {
                return this.primaryValue;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lio/neolang/runtime/type/NeoLangArray$Companion$BlockElement;", "Lio/neolang/runtime/type/NeoLangArrayElement;", "blockContext", "Lio/neolang/runtime/context/NeoLangContext;", "(Lio/neolang/runtime/context/NeoLangContext;)V", "getBlockContext", "()Lio/neolang/runtime/context/NeoLangContext;", "eval", "Lio/neolang/runtime/type/NeoLangValue;", "key", "", "isBlock", "", "NeoLang"}, k = 1, mv = {1, 1, 15})
        /* compiled from: NeoLangArray.kt */
        public static final class BlockElement extends NeoLangArrayElement {
            private final NeoLangContext blockContext;

            public boolean isBlock() {
                return true;
            }

            public BlockElement(NeoLangContext neoLangContext) {
                Intrinsics.checkParameterIsNotNull(neoLangContext, "blockContext");
                this.blockContext = neoLangContext;
            }

            public final NeoLangContext getBlockContext() {
                return this.blockContext;
            }

            public NeoLangValue eval(String str) {
                Intrinsics.checkParameterIsNotNull(str, NeoExtraKey.EKS_META_KEY);
                return this.blockContext.getAttribute(str);
            }
        }

        public final NeoLangArray createFromContext(NeoLangContext neoLangContext) {
            Intrinsics.checkParameterIsNotNull(neoLangContext, "context");
            List arrayList = new ArrayList();
            for (Map.Entry entry : neoLangContext.getAttributes().entrySet()) {
                arrayList.add(Integer.parseInt((String) entry.getKey()), new PrimaryElement((NeoLangValue) entry.getValue()));
            }
            for (NeoLangContext neoLangContext2 : neoLangContext.getChildren()) {
                arrayList.add(Integer.parseInt(neoLangContext2.getContextName()), new BlockElement(neoLangContext2));
            }
            return new NeoLangArray(arrayList, 0, 2, (DefaultConstructorMarker) null);
        }
    }

    private NeoLangArray(List<? extends NeoLangArrayElement> list, int i) {
        this.elements = list;
        this.size = i;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ NeoLangArray(List list, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i2 & 2) != 0 ? list.size() : i);
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof NeoLangArrayElement) {
            return contains((NeoLangArrayElement) obj);
        }
        return false;
    }

    public final List<NeoLangArrayElement> getElements() {
        return this.elements;
    }

    public int getSize() {
        return this.size;
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public final NeoLangArrayElement get(int i) {
        return this.elements.get(i);
    }

    public boolean contains(NeoLangArrayElement neoLangArrayElement) {
        Intrinsics.checkParameterIsNotNull(neoLangArrayElement, "element");
        return this.elements.contains(neoLangArrayElement);
    }

    public boolean containsAll(Collection<? extends Object> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "elements");
        return this.elements.containsAll(collection);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterator<NeoLangArrayElement> iterator() {
        return this.elements.iterator();
    }
}
