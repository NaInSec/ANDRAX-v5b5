package de.mrapp.android.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import java.io.File;

public final class AppUtil {
    private AppUtil() {
    }

    public static void startCameraApp(Activity activity, int i, File file) {
        Condition.ensureNotNull(file, "The file may not be null");
        Condition.ensureFileIsNoDirectory(file, "The file must exist and must not be a directory");
        startCameraApp(activity, i, Uri.fromFile(file));
    }

    public static void startCameraApp(Activity activity, int i, Uri uri) {
        Condition.ensureNotNull(activity, "The activity may not be null");
        Condition.ensureNotNull(uri, "The URI may not be null");
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", uri);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(intent, i);
            return;
        }
        throw new ActivityNotFoundException("Camera app not available");
    }

    public static void startGalleryApp(Activity activity, int i) {
        Condition.ensureNotNull(activity, "The activity may not be null");
        Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(intent, i);
            return;
        }
        throw new ActivityNotFoundException("Gallery app not available");
    }

    public static void startGalleryApp(Context context, File file) {
        Condition.ensureNotNull(file, "The file may not be null");
        Condition.ensureFileIsNoDirectory(file, "The file must exist and must not be a directory");
        startGalleryApp(context, Uri.parse("file://" + file.getAbsolutePath()));
    }

    public static void startGalleryApp(Context context, Uri uri) {
        Condition.ensureNotNull(context, "The context may not be null");
        Condition.ensureNotNull(uri, "The URI may not be null");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "image/*");
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
            return;
        }
        throw new ActivityNotFoundException("Gallery app not available");
    }

    public static void startWebBrowser(Context context, String str) {
        Condition.ensureNotNull(str, "The URI may not be null");
        Condition.ensureNotEmpty((CharSequence) str, "The URI may not be empty");
        if (!str.startsWith("http://") && !str.startsWith("https://")) {
            str = "http://" + str;
        }
        startWebBrowser(context, Uri.parse(str));
    }

    public static void startWebBrowser(Context context, Uri uri) {
        Condition.ensureNotNull(context, "The context may not be null");
        Condition.ensureNotNull(uri, "The URI may not be null");
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
            return;
        }
        throw new ActivityNotFoundException("Web browser not available");
    }

    public static void startMailClient(Context context, String str) {
        startMailClient(context, str, (String) null, (String) null);
    }

    public static void startMailClient(Context context, String str, String str2, String str3) {
        Condition.ensureNotNull(str, "The e-mail address may not be null");
        Condition.ensureNotEmpty((CharSequence) str, "The e-mail address may not be empty");
        startMailClient(context, new String[]{str}, str2, str3);
    }

    public static void startMailClient(Context context, String[] strArr) {
        startMailClient(context, strArr, (String) null, (String) null);
    }

    public static void startMailClient(Context context, String[] strArr, String str, String str2) {
        Condition.ensureNotNull(context, "The context may not be null");
        Condition.ensureNotNull(strArr, "The array may not be null");
        Condition.ensureTrue(strArr.length > 0, "The array may not be empty");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("message/rfc822");
        intent.putExtra("android.intent.extra.EMAIL", strArr);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("android.intent.extra.SUBJECT", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("android.intent.extra.TEXT", str2);
        }
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
            return;
        }
        throw new ActivityNotFoundException("Mail client not available");
    }

    public static void startDialer(Context context, int i) {
        startDialer(context, Integer.toString(i));
    }

    public static void startDialer(Context context, long j) {
        startDialer(context, Long.toString(j));
    }

    public static void startDialer(Context context, String str) {
        Condition.ensureNotNull(context, "The context may not be null");
        Condition.ensureNotNull(str, "The phone number may not be null");
        Condition.ensureNotEmpty((CharSequence) str, "The phone number may not be empty");
        if (!str.startsWith("tel:")) {
            str = "tel:" + str;
        }
        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(str));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
            return;
        }
        throw new ActivityNotFoundException("Dialer app not available");
    }

    public static void startCall(Context context, int i) {
        startDialer(context, Integer.toString(i));
    }

    public static void startCall(Context context, long j) {
        startDialer(context, Long.toString(j));
    }

    public static void startCall(Context context, String str) {
        Condition.ensureNotNull(context, "The context may not be null");
        Condition.ensureNotNull(str, "The phone number may not be null");
        Condition.ensureNotEmpty((CharSequence) str, "The phone number may not be empty");
        if (!str.startsWith("tel:")) {
            str = "tel:" + str;
        }
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse(str));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
            return;
        }
        throw new ActivityNotFoundException("Dialer app not available");
    }

    public static void showAppInfo(Context context) {
        showAppInfo(context, context.getPackageName());
    }

    public static void showAppInfo(Context context, String str) {
        Condition.ensureNotNull(context, "The context may not be null");
        Condition.ensureNotNull(str, "The package name may not be null");
        Condition.ensureNotEmpty((CharSequence) str, "The package name may not be empty");
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", str, (String) null));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
            return;
        }
        throw new ActivityNotFoundException("App info for package " + str + " not available");
    }
}
