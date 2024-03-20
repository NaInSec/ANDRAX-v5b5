package com.thecrackertechnology.dragonterminal.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J9\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0002\u0010\fJ\u001a\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\u0012"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/utils/MediaUtils;", "", "()V", "getDataColumn", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "selection", "selectionArgs", "", "(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/String;", "getPath", "isDownloadsDocument", "", "isExternalStorageDocument", "isMediaDocument", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: MediaUtils.kt */
public final class MediaUtils {
    public static final MediaUtils INSTANCE = new MediaUtils();

    private MediaUtils() {
    }

    public final String getPath(Context context, Uri uri) {
        List list;
        boolean z;
        List list2;
        boolean z2;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        if (!(Build.VERSION.SDK_INT >= 19) || !DocumentsContract.isDocumentUri(context, uri)) {
            if (StringsKt.equals("content", uri.getScheme(), true)) {
                return getDataColumn(context, uri, (String) null, (String[]) null);
            }
            if (StringsKt.equals("file", uri.getScheme(), true)) {
                return uri.getPath();
            }
        } else if (isExternalStorageDocument(uri)) {
            String documentId = DocumentsContract.getDocumentId(uri);
            Intrinsics.checkExpressionValueIsNotNull(documentId, "docId");
            List<String> split = new Regex(":").split(documentId, 0);
            if (!split.isEmpty()) {
                ListIterator<String> listIterator = split.listIterator(split.size());
                while (true) {
                    if (!listIterator.hasPrevious()) {
                        break;
                    }
                    if (listIterator.previous().length() == 0) {
                        z2 = true;
                        continue;
                    } else {
                        z2 = false;
                        continue;
                    }
                    if (!z2) {
                        list2 = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            }
            list2 = CollectionsKt.emptyList();
            Object[] array = list2.toArray(new String[0]);
            if (array != null) {
                String[] strArr = (String[]) array;
                String str = strArr[0];
                if (StringsKt.equals("primary", str, true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + strArr[1];
                }
                return "/storage/" + str + '/' + strArr[1];
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        } else if (isDownloadsDocument(uri)) {
            String documentId2 = DocumentsContract.getDocumentId(uri);
            Uri parse = Uri.parse("content://downloads/public_downloads");
            Long valueOf = Long.valueOf(documentId2);
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            Uri withAppendedId = ContentUris.withAppendedId(parse, valueOf.longValue());
            Intrinsics.checkExpressionValueIsNotNull(withAppendedId, "contentUri");
            return getDataColumn(context, withAppendedId, (String) null, (String[]) null);
        } else if (isMediaDocument(uri)) {
            String documentId3 = DocumentsContract.getDocumentId(uri);
            Intrinsics.checkExpressionValueIsNotNull(documentId3, "docId");
            List<String> split2 = new Regex(":").split(documentId3, 0);
            if (!split2.isEmpty()) {
                ListIterator<String> listIterator2 = split2.listIterator(split2.size());
                while (true) {
                    if (!listIterator2.hasPrevious()) {
                        break;
                    }
                    if (listIterator2.previous().length() == 0) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (!z) {
                        list = CollectionsKt.take(split2, listIterator2.nextIndex() + 1);
                        break;
                    }
                }
            }
            list = CollectionsKt.emptyList();
            Object[] array2 = list.toArray(new String[0]);
            if (array2 != null) {
                String[] strArr2 = (String[]) array2;
                String str2 = strArr2[0];
                Uri uri2 = null;
                if (Intrinsics.areEqual((Object) "image", (Object) str2)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if (Intrinsics.areEqual((Object) "video", (Object) str2)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if (Intrinsics.areEqual((Object) "audio", (Object) str2)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String[] strArr3 = {strArr2[1]};
                if (uri2 == null) {
                    Intrinsics.throwNpe();
                }
                return getDataColumn(context, uri2, "_id=?", strArr3);
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return null;
    }

    private final String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, (String) null);
            if (cursor == null || !cursor.moveToFirst()) {
                return null;
            }
            String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
            cursor.close();
            return string;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private final boolean isExternalStorageDocument(Uri uri) {
        return Intrinsics.areEqual((Object) "com.android.externalstorage.documents", (Object) uri.getAuthority());
    }

    private final boolean isDownloadsDocument(Uri uri) {
        return Intrinsics.areEqual((Object) "com.android.providers.downloads.documents", (Object) uri.getAuthority());
    }

    private final boolean isMediaDocument(Uri uri) {
        return Intrinsics.areEqual((Object) "com.android.providers.media.documents", (Object) uri.getAuthority());
    }
}
