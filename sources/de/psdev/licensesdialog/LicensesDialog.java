package de.psdev.licensesdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;
import javax.annotation.Nullable;

public class LicensesDialog {
    public static final Notice LICENSES_DIALOG_NOTICE = new Notice("LicensesDialog", "http://psdev.de/LicensesDialog", "Copyright 2013-2016 Philip Schiffer", new ApacheSoftwareLicense20());
    private final String mCloseText;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final int mDividerColor;
    private final String mLicensesText;
    /* access modifiers changed from: private */
    public DialogInterface.OnDismissListener mOnDismissListener;
    private final int mThemeResourceId;
    private final String mTitleText;

    private LicensesDialog(Context context, String str, String str2, String str3, int i, int i2) {
        this.mContext = context;
        this.mTitleText = str2;
        this.mLicensesText = str;
        this.mCloseText = str3;
        this.mThemeResourceId = i;
        this.mDividerColor = i2;
    }

    public LicensesDialog setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
        return this;
    }

    public Dialog create() {
        AlertDialog.Builder builder;
        WebView createWebView = createWebView(this.mContext);
        createWebView.loadDataWithBaseURL((String) null, this.mLicensesText, "text/html", "utf-8", (String) null);
        int i = this.mThemeResourceId;
        if (i != 0) {
            builder = new AlertDialog.Builder(new ContextThemeWrapper(this.mContext, i));
        } else {
            builder = new AlertDialog.Builder(this.mContext);
        }
        builder.setTitle(this.mTitleText).setView(createWebView).setPositiveButton(this.mCloseText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        final AlertDialog create = builder.create();
        create.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                if (LicensesDialog.this.mOnDismissListener != null) {
                    LicensesDialog.this.mOnDismissListener.onDismiss(dialogInterface);
                }
            }
        });
        create.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                View findViewById;
                if (LicensesDialog.this.mDividerColor != 0 && (findViewById = create.findViewById(LicensesDialog.this.mContext.getResources().getIdentifier("titleDivider", "id", "android"))) != null) {
                    findViewById.setBackgroundColor(LicensesDialog.this.mDividerColor);
                }
            }
        });
        return create;
    }

    public Dialog createAppCompat() {
        AlertDialog.Builder builder;
        WebView webView = new WebView(this.mContext);
        webView.loadDataWithBaseURL((String) null, this.mLicensesText, "text/html", "utf-8", (String) null);
        int i = this.mThemeResourceId;
        if (i != 0) {
            builder = new AlertDialog.Builder(new ContextThemeWrapper(this.mContext, i));
        } else {
            builder = new AlertDialog.Builder(this.mContext);
        }
        builder.setTitle((CharSequence) this.mTitleText).setView((View) webView).setPositiveButton((CharSequence) this.mCloseText, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        final android.support.v7.app.AlertDialog create = builder.create();
        create.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                if (LicensesDialog.this.mOnDismissListener != null) {
                    LicensesDialog.this.mOnDismissListener.onDismiss(dialogInterface);
                }
            }
        });
        create.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                View findViewById;
                if (LicensesDialog.this.mDividerColor != 0 && (findViewById = create.findViewById(LicensesDialog.this.mContext.getResources().getIdentifier("titleDivider", "id", "android"))) != null) {
                    findViewById.setBackgroundColor(LicensesDialog.this.mDividerColor);
                }
            }
        });
        return create;
    }

    public Dialog show() {
        Dialog create = create();
        create.show();
        return create;
    }

    public Dialog showAppCompat() {
        Dialog createAppCompat = createAppCompat();
        createAppCompat.show();
        return createAppCompat;
    }

    private static WebView createWebView(final Context context) {
        WebView webView = new WebView(context);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.setWebChromeClient(new WebChromeClient() {
            public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
                String extra = webView.getHitTestResult().getExtra();
                if (extra == null) {
                    return false;
                }
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(extra)));
                return false;
            }
        });
        return webView;
    }

    /* access modifiers changed from: private */
    public static Notices getNotices(Context context, int i) {
        try {
            Resources resources = context.getResources();
            if ("raw".equals(resources.getResourceTypeName(i))) {
                return NoticesXmlParser.parse(resources.openRawResource(i));
            }
            throw new IllegalStateException("not a raw resource");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: private */
    public static String getLicensesText(Context context, Notices notices, boolean z, boolean z2, String str) {
        if (z2) {
            try {
                notices.getNotices().add(LICENSES_DIALOG_NOTICE);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return NoticesHtmlBuilder.create(context).setShowFullLicenseText(z).setNotices(notices).setStyle(str).build();
    }

    /* access modifiers changed from: private */
    public static Notices getSingleNoticeNotices(Notice notice) {
        Notices notices = new Notices();
        notices.addNotice(notice);
        return notices;
    }

    public static final class Builder {
        private String mCloseText;
        private final Context mContext;
        private int mDividerColor = 0;
        private boolean mIncludeOwnLicense = false;
        @Nullable
        private Notices mNotices;
        private String mNoticesStyle;
        @Nullable
        private String mNoticesText;
        @Nullable
        private Integer mRawNoticesId;
        private boolean mShowFullLicenseText = false;
        private int mThemeResourceId = 0;
        private String mTitleText;

        public Builder(Context context) {
            this.mContext = context;
            this.mTitleText = context.getString(R.string.notices_title);
            this.mCloseText = context.getString(R.string.notices_close);
            this.mNoticesStyle = context.getString(R.string.notices_default_style);
        }

        public Builder setTitle(int i) {
            this.mTitleText = this.mContext.getString(i);
            return this;
        }

        public Builder setTitle(String str) {
            this.mTitleText = str;
            return this;
        }

        public Builder setCloseText(int i) {
            this.mCloseText = this.mContext.getString(i);
            return this;
        }

        public Builder setCloseText(String str) {
            this.mCloseText = str;
            return this;
        }

        public Builder setNotices(int i) {
            this.mRawNoticesId = Integer.valueOf(i);
            this.mNotices = null;
            return this;
        }

        public Builder setNotices(Notices notices) {
            this.mNotices = notices;
            this.mRawNoticesId = null;
            return this;
        }

        public Builder setNotices(Notice notice) {
            return setNotices(LicensesDialog.getSingleNoticeNotices(notice));
        }

        /* access modifiers changed from: package-private */
        public Builder setNotices(String str) {
            this.mNotices = null;
            this.mRawNoticesId = null;
            this.mNoticesText = str;
            return this;
        }

        public Builder setNoticesCssStyle(int i) {
            this.mNoticesStyle = this.mContext.getString(i);
            return this;
        }

        public Builder setNoticesCssStyle(String str) {
            this.mNoticesStyle = str;
            return this;
        }

        public Builder setShowFullLicenseText(boolean z) {
            this.mShowFullLicenseText = z;
            return this;
        }

        public Builder setIncludeOwnLicense(boolean z) {
            this.mIncludeOwnLicense = z;
            return this;
        }

        public Builder setThemeResourceId(int i) {
            this.mThemeResourceId = i;
            return this;
        }

        public Builder setDividerColor(int i) {
            this.mDividerColor = i;
            return this;
        }

        public Builder setDividerColorId(int i) {
            this.mDividerColor = this.mContext.getResources().getColor(i);
            return this;
        }

        public LicensesDialog build() {
            String str;
            Notices notices = this.mNotices;
            if (notices != null) {
                str = LicensesDialog.getLicensesText(this.mContext, notices, this.mShowFullLicenseText, this.mIncludeOwnLicense, this.mNoticesStyle);
            } else {
                Integer num = this.mRawNoticesId;
                if (num != null) {
                    Context context = this.mContext;
                    str = LicensesDialog.getLicensesText(context, LicensesDialog.getNotices(context, num.intValue()), this.mShowFullLicenseText, this.mIncludeOwnLicense, this.mNoticesStyle);
                } else {
                    str = this.mNoticesText;
                    if (str == null) {
                        throw new IllegalStateException("Notices have to be provided, see setNotices");
                    }
                }
            }
            return new LicensesDialog(this.mContext, str, this.mTitleText, this.mCloseText, this.mThemeResourceId, this.mDividerColor);
        }
    }
}
