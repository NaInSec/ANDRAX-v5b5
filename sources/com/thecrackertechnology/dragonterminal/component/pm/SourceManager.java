package com.thecrackertechnology.dragonterminal.component.pm;

import com.thecrackertechnology.dragonterminal.framework.NeoTermDatabase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u0007J\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ\u0006\u0010\u0012\u001a\u00020\tJ\u000e\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0014\u0010\u0014\u001a\u00020\u00072\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/pm/SourceManager;", "", "()V", "database", "Lcom/thecrackertechnology/dragonterminal/framework/NeoTermDatabase;", "kotlin.jvm.PlatformType", "addSource", "", "sourceUrl", "", "repo", "enabled", "", "applyChanges", "getAllSources", "", "Lcom/thecrackertechnology/dragonterminal/component/pm/Source;", "getEnabledSources", "getMainPackageSource", "removeSource", "updateAll", "sources", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: SourceManager.kt */
public final class SourceManager {
    private final NeoTermDatabase database = NeoTermDatabase.instance("sources");

    public final void addSource(String str, String str2, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "sourceUrl");
        Intrinsics.checkParameterIsNotNull(str2, "repo");
        this.database.saveBean(new Source(str, str2, z));
    }

    public final void removeSource(String str) {
        Intrinsics.checkParameterIsNotNull(str, "sourceUrl");
        this.database.deleteBeanByWhere(Source.class, "url == '" + str + '\'');
    }

    public final void updateAll(List<? extends Source> list) {
        Intrinsics.checkParameterIsNotNull(list, "sources");
        this.database.dropAllTable();
        this.database.saveBeans(list);
    }

    public final List<Source> getAllSources() {
        List<Source> findAll = this.database.findAll(Source.class);
        Intrinsics.checkExpressionValueIsNotNull(findAll, "database.findAll<Source>(Source::class.java)");
        return findAll;
    }

    public final List<Source> getEnabledSources() {
        Collection arrayList = new ArrayList();
        for (Object next : getAllSources()) {
            if (((Source) next).enabled) {
                arrayList.add(next);
            }
        }
        return (List) arrayList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getMainPackageSource() {
        /*
            r7 = this;
            java.util.List r0 = r7.getEnabledSources()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 10
            int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r2)
            r1.<init>(r2)
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r0 = r0.iterator()
        L_0x0017:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0029
            java.lang.Object r2 = r0.next()
            com.thecrackertechnology.dragonterminal.component.pm.Source r2 = (com.thecrackertechnology.dragonterminal.component.pm.Source) r2
            java.lang.String r2 = r2.repo
            r1.add(r2)
            goto L_0x0017
        L_0x0029:
            java.util.List r1 = (java.util.List) r1
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            r0 = 0
            java.util.Iterator r1 = r1.iterator()
            r2 = 0
            r3 = r2
        L_0x0034:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x0068
            java.lang.Object r4 = r1.next()
            r5 = r4
            java.lang.String r5 = (java.lang.String) r5
            java.lang.String r6 = "it"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            if (r5 == 0) goto L_0x0060
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            java.lang.CharSequence r5 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r5)
            java.lang.String r5 = r5.toString()
            java.lang.String r6 = "stable main"
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r5 == 0) goto L_0x0034
            if (r0 == 0) goto L_0x005d
            goto L_0x006c
        L_0x005d:
            r0 = 1
            r3 = r4
            goto L_0x0034
        L_0x0060:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.CharSequence"
            r0.<init>(r1)
            throw r0
        L_0x0068:
            if (r0 != 0) goto L_0x006b
            goto L_0x006c
        L_0x006b:
            r2 = r3
        L_0x006c:
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto L_0x0071
            goto L_0x0073
        L_0x0071:
            java.lang.String r2 = ""
        L_0x0073:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.component.pm.SourceManager.getMainPackageSource():java.lang.String");
    }

    public final void applyChanges() {
        this.database.vacuum();
    }
}
