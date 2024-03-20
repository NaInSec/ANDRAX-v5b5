package com.thecrackertechnology.dragonterminal.component.completion.provider;

import com.thecrackertechnology.dragonterminal.frontend.completion.model.CompletionCandidate;
import com.thecrackertechnology.dragonterminal.frontend.completion.provider.ICandidateProvider;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0016J.\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\b\u0018\u00010\u0010H\u0002J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0016J1\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\u0006\u0010\u0015\u001a\u00020\u000e2\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\b\u0018\u00010\u0010H\u0002¢\u0006\u0002\u0010\u0016J\u0018\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\u0006\u0010\t\u001a\u00020\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0018"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/completion/provider/FileCompletionProvider;", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/provider/ICandidateProvider;", "()V", "providerName", "", "getProviderName", "()Ljava/lang/String;", "canComplete", "", "text", "generateCandidateList", "", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/model/CompletionCandidate;", "file", "Ljava/io/File;", "filter", "Lkotlin/Function1;", "generateDesc", "generateDisplayName", "listDirectory", "", "path", "(Ljava/io/File;Lkotlin/jvm/functions/Function1;)[Ljava/io/File;", "provideCandidates", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: FileCompletionProvider.kt */
public class FileCompletionProvider implements ICandidateProvider {
    public String generateDesc(File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        return null;
    }

    public String getProviderName() {
        return "NeoTermProvider.FileCompletionProvider";
    }

    public List<CompletionCandidate> provideCandidates(String str) {
        Intrinsics.checkParameterIsNotNull(str, "text");
        File file = new File(str);
        Function1 function1 = null;
        if (!file.isDirectory()) {
            String name = file.getName();
            file = file.getParentFile();
            Intrinsics.checkExpressionValueIsNotNull(file, "file.parentFile");
            function1 = new FileCompletionProvider$provideCandidates$1(name);
        }
        return generateCandidateList(file, function1);
    }

    public boolean canComplete(String str) {
        Intrinsics.checkParameterIsNotNull(str, "text");
        return StringsKt.startsWith$default((CharSequence) str, File.separatorChar, false, 2, (Object) null) || StringsKt.startsWith$default(str, "\\./", false, 2, (Object) null);
    }

    private final File[] listDirectory(File file, Function1<? super File, Boolean> function1) {
        if (function1 != null) {
            File[] listFiles = file.listFiles(new FileCompletionProvider$sam$java_io_FileFilter$0(function1));
            Intrinsics.checkExpressionValueIsNotNull(listFiles, "path.listFiles(filter)");
            return listFiles;
        }
        File[] listFiles2 = file.listFiles();
        Intrinsics.checkExpressionValueIsNotNull(listFiles2, "path.listFiles()");
        return listFiles2;
    }

    private final List<CompletionCandidate> generateCandidateList(File file, Function1<? super File, Boolean> function1) {
        if (!file.canRead()) {
            return null;
        }
        List<CompletionCandidate> arrayList = new ArrayList<>();
        for (File file2 : listDirectory(file, function1)) {
            String name = file2.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "it.name");
            CompletionCandidate completionCandidate = new CompletionCandidate(name);
            completionCandidate.setDescription(generateDesc(file2));
            completionCandidate.setDisplayName(generateDisplayName(file2));
            arrayList.add(completionCandidate);
        }
        Collection collection = arrayList;
        return arrayList;
    }

    public String generateDisplayName(File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        if (file.isDirectory()) {
            return file.getName() + '/';
        }
        String name = file.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "file.name");
        return name;
    }
}
