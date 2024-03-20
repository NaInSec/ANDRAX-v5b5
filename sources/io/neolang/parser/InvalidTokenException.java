package io.neolang.parser;

import com.onesignal.OneSignalDbContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lio/neolang/parser/InvalidTokenException;", "Lio/neolang/parser/ParseException;", "message", "", "(Ljava/lang/String;)V", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: InvalidTokenException.kt */
public class InvalidTokenException extends ParseException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InvalidTokenException(String str) {
        super(str);
        Intrinsics.checkParameterIsNotNull(str, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
    }
}
