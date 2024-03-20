package com.thecrackertechnology.dragonterminal.setup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import com.onesignal.OneSignalDbContract;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoTermPath;
import java.io.File;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004J\u0006\u0010\u000e\u001a\u00020\u000fJ\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017¨\u0006\u0018"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/setup/SetupHelper;", "", "()V", "determineArchName", "", "makeErrorDialog", "Landroid/app/AlertDialog;", "context", "Landroid/content/Context;", "messageId", "", "message", "makeProgressDialog", "Landroid/app/ProgressDialog;", "needSetup", "", "setup", "", "activity", "Landroid/app/Activity;", "connection", "Lcom/thecrackertechnology/dragonterminal/setup/SourceConnection;", "resultListener", "Lcom/thecrackertechnology/dragonterminal/setup/ResultListener;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: SetupHelper.kt */
public final class SetupHelper {
    public static final SetupHelper INSTANCE = new SetupHelper();

    private SetupHelper() {
    }

    public final boolean needSetup() {
        return !new File(NeoTermPath.USR_PATH).isDirectory();
    }

    public final void setup(Activity activity, SourceConnection sourceConnection, ResultListener resultListener) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(sourceConnection, "connection");
        Intrinsics.checkParameterIsNotNull(resultListener, "resultListener");
        if (!needSetup()) {
            resultListener.onResult((Exception) null);
            return;
        }
        File file = new File(NeoTermPath.USR_PATH);
        ProgressDialog makeProgressDialog = makeProgressDialog(activity);
        makeProgressDialog.setMax(100);
        makeProgressDialog.show();
        new SetupThread(activity, sourceConnection, file, resultListener, makeProgressDialog).start();
    }

    private final ProgressDialog makeProgressDialog(Context context) {
        String string = context.getString(R.string.installer_message);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.installer_message)");
        return makeProgressDialog(context, string);
    }

    public final ProgressDialog makeProgressDialog(Context context, String str) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(str, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(str);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(1);
        return progressDialog;
    }

    public final AlertDialog makeErrorDialog(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String string = context.getString(i);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(messageId)");
        return makeErrorDialog(context, string);
    }

    public final AlertDialog makeErrorDialog(Context context, String str) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(str, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        AlertDialog create = new AlertDialog.Builder(context).setTitle(R.string.error).setMessage(str).setPositiveButton(17039379, (DialogInterface.OnClickListener) null).setNeutralButton(R.string.show_help, SetupHelper$makeErrorDialog$1.INSTANCE).create();
        Intrinsics.checkExpressionValueIsNotNull(create, "AlertDialog.Builder(cont…                .create()");
        return create;
    }

    public final String determineArchName() {
        for (String str : Build.SUPPORTED_ABIS) {
            if (str != null) {
                int hashCode = str.hashCode();
                if (hashCode != -806050265) {
                    if (hashCode != 145444210) {
                        if (hashCode == 1431565292 && str.equals("arm64-v8a")) {
                            return "aarch64";
                        }
                    } else if (str.equals("armeabi-v7a")) {
                        return "arm";
                    }
                } else if (str.equals("x86_64")) {
                    return "x86_64";
                }
            }
        }
        throw new RuntimeException("Unable to determine arch from Build.SUPPORTED_ABIS =  " + Arrays.toString(Build.SUPPORTED_ABIS));
    }
}
