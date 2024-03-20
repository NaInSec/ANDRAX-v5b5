package com.thecrackertechnology.dragonterminal.ui.support;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import java.net.URISyntaxException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/support/Donation;", "", "()V", "INTENT_URL_FORMAT", "", "donateByAlipay", "", "activity", "Landroid/app/Activity;", "payCode", "startIntentUrl", "intentFullUrl", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: Donation.kt */
public final class Donation {
    public static final Donation INSTANCE = new Donation();
    private static final String INTENT_URL_FORMAT = INTENT_URL_FORMAT;

    private Donation() {
    }

    public final boolean donateByAlipay(Activity activity, String str) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(str, "payCode");
        return startIntentUrl(activity, StringsKt.replace$default(INTENT_URL_FORMAT, "{payCode}", str, false, 4, (Object) null));
    }

    private final boolean startIntentUrl(Activity activity, String str) {
        try {
            activity.startActivity(Intent.parseUri(str, 1));
            return true;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        } catch (ActivityNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
