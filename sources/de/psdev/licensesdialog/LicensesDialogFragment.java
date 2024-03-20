package de.psdev.licensesdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;

public class LicensesDialogFragment extends DialogFragment {
    private static final String ARGUMENT_DIVIDER_COLOR = "ARGUMENT_DIVIDER_COLOR";
    private static final String ARGUMENT_FULL_LICENSE_TEXT = "ARGUMENT_FULL_LICENSE_TEXT";
    private static final String ARGUMENT_INCLUDE_OWN_LICENSE = "ARGUMENT_INCLUDE_OWN_LICENSE";
    private static final String ARGUMENT_NOTICES = "ARGUMENT_NOTICES";
    private static final String ARGUMENT_NOTICES_XML_ID = "ARGUMENT_NOTICES_XML_ID";
    private static final String ARGUMENT_NOTICE_STYLE = "ARGUMENT_NOTICE_STYLE";
    private static final String ARGUMENT_THEME_XML_ID = "ARGUMENT_THEME_XML_ID";
    private static final String ARGUMENT_USE_APPCOMPAT = "ARGUMENT_USE_APPCOMPAT";
    private static final String STATE_CLOSE_TEXT = "close_text";
    private static final String STATE_DIVIDER_COLOR = "divider_color";
    private static final String STATE_LICENSES_TEXT = "licenses_text";
    private static final String STATE_THEME_XML_ID = "theme_xml_id";
    private static final String STATE_TITLE_TEXT = "title_text";
    private String mCloseButtonText;
    private int mDividerColor;
    private String mLicensesText;
    private DialogInterface.OnDismissListener mOnDismissListener;
    private int mThemeResourceId;
    private String mTitleText;

    /* access modifiers changed from: private */
    public static LicensesDialogFragment newInstance(Notices notices, boolean z, boolean z2, String str, int i, int i2, boolean z3) {
        LicensesDialogFragment licensesDialogFragment = new LicensesDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT_NOTICES, notices);
        bundle.putBoolean(ARGUMENT_FULL_LICENSE_TEXT, z);
        bundle.putBoolean(ARGUMENT_INCLUDE_OWN_LICENSE, z2);
        bundle.putString(ARGUMENT_NOTICE_STYLE, str);
        bundle.putInt(ARGUMENT_THEME_XML_ID, i);
        bundle.putInt(ARGUMENT_DIVIDER_COLOR, i2);
        bundle.putBoolean(ARGUMENT_USE_APPCOMPAT, z3);
        licensesDialogFragment.setArguments(bundle);
        return licensesDialogFragment;
    }

    /* access modifiers changed from: private */
    public static LicensesDialogFragment newInstance(int i, boolean z, boolean z2, String str, int i2, int i3, boolean z3) {
        LicensesDialogFragment licensesDialogFragment = new LicensesDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_NOTICES_XML_ID, i);
        bundle.putBoolean(ARGUMENT_FULL_LICENSE_TEXT, z);
        bundle.putBoolean(ARGUMENT_INCLUDE_OWN_LICENSE, z2);
        bundle.putString(ARGUMENT_NOTICE_STYLE, str);
        bundle.putInt(ARGUMENT_THEME_XML_ID, i2);
        bundle.putInt(ARGUMENT_DIVIDER_COLOR, i3);
        bundle.putBoolean(ARGUMENT_USE_APPCOMPAT, z3);
        licensesDialogFragment.setArguments(bundle);
        return licensesDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        Notices notices;
        super.onCreate(bundle);
        Resources resources = getResources();
        if (bundle != null) {
            this.mTitleText = bundle.getString(STATE_TITLE_TEXT);
            this.mLicensesText = bundle.getString(STATE_LICENSES_TEXT);
            this.mCloseButtonText = bundle.getString(STATE_CLOSE_TEXT);
            if (bundle.containsKey(STATE_THEME_XML_ID)) {
                this.mThemeResourceId = bundle.getInt(STATE_THEME_XML_ID);
            }
            if (bundle.containsKey(STATE_DIVIDER_COLOR)) {
                this.mDividerColor = bundle.getInt(STATE_DIVIDER_COLOR);
                return;
            }
            return;
        }
        this.mTitleText = resources.getString(R.string.notices_title);
        this.mCloseButtonText = resources.getString(R.string.notices_close);
        try {
            Bundle arguments = getArguments();
            if (arguments != null) {
                if (arguments.containsKey(ARGUMENT_NOTICES_XML_ID)) {
                    notices = NoticesXmlParser.parse(resources.openRawResource(getNoticesXmlResourceId()));
                } else if (arguments.containsKey(ARGUMENT_NOTICES)) {
                    notices = (Notices) arguments.getParcelable(ARGUMENT_NOTICES);
                } else {
                    throw new IllegalStateException("Missing ARGUMENT_NOTICES_XML_ID / ARGUMENT_NOTICES");
                }
                if (arguments.getBoolean(ARGUMENT_INCLUDE_OWN_LICENSE, false)) {
                    notices.getNotices().add(LicensesDialog.LICENSES_DIALOG_NOTICE);
                }
                boolean z = arguments.getBoolean(ARGUMENT_FULL_LICENSE_TEXT, false);
                if (arguments.containsKey(ARGUMENT_THEME_XML_ID)) {
                    if (Build.VERSION.SDK_INT >= 14) {
                        this.mThemeResourceId = arguments.getInt(ARGUMENT_THEME_XML_ID, 16974130);
                    } else {
                        this.mThemeResourceId = arguments.getInt(ARGUMENT_THEME_XML_ID);
                    }
                }
                if (arguments.containsKey(ARGUMENT_DIVIDER_COLOR)) {
                    if (Build.VERSION.SDK_INT >= 14) {
                        this.mDividerColor = arguments.getInt(ARGUMENT_DIVIDER_COLOR, 17170450);
                    } else {
                        this.mDividerColor = arguments.getInt(ARGUMENT_DIVIDER_COLOR);
                    }
                }
                String string = arguments.getString(ARGUMENT_NOTICE_STYLE);
                if (string == null) {
                    string = resources.getString(R.string.notices_default_style);
                }
                this.mLicensesText = NoticesHtmlBuilder.create(getActivity()).setNotices(notices).setShowFullLicenseText(z).setStyle(string).build();
                return;
            }
            throw new IllegalStateException("Missing arguments");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(STATE_TITLE_TEXT, this.mTitleText);
        bundle.putString(STATE_LICENSES_TEXT, this.mLicensesText);
        bundle.putString(STATE_CLOSE_TEXT, this.mCloseButtonText);
        int i = this.mThemeResourceId;
        if (i != 0) {
            bundle.putInt(STATE_THEME_XML_ID, i);
        }
        int i2 = this.mDividerColor;
        if (i2 != 0) {
            bundle.putInt(STATE_DIVIDER_COLOR, i2);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        LicensesDialog build = new LicensesDialog.Builder(getActivity()).setNotices(this.mLicensesText).setTitle(this.mTitleText).setCloseText(this.mCloseButtonText).setThemeResourceId(this.mThemeResourceId).setDividerColor(this.mDividerColor).build();
        if (getArguments().getBoolean(ARGUMENT_USE_APPCOMPAT, false)) {
            return build.createAppCompat();
        }
        return build.create();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        DialogInterface.OnDismissListener onDismissListener = this.mOnDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialogInterface);
        }
    }

    public DialogInterface.OnDismissListener getOnDismissListener() {
        return this.mOnDismissListener;
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    private int getNoticesXmlResourceId() {
        int i = R.raw.notices;
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(ARGUMENT_NOTICES_XML_ID)) {
            i = arguments.getInt(ARGUMENT_NOTICES_XML_ID);
            if (!"raw".equalsIgnoreCase(getResources().getResourceTypeName(i))) {
                throw new IllegalStateException("not a raw resource");
            }
        }
        return i;
    }

    public static class Builder {
        private final Context mContext;
        private int mDividerColor;
        private boolean mIncludeOwnLicense = true;
        private Notices mNotices;
        private String mNoticesStyle;
        private Integer mRawNoticesResourceId;
        private boolean mShowFullLicenseText = false;
        private int mThemeResourceId;
        private boolean mUseAppCompat;

        public Builder(Context context) {
            this.mContext = context;
            this.mNoticesStyle = context.getString(R.string.notices_default_style);
            this.mThemeResourceId = 0;
            this.mDividerColor = 0;
            this.mUseAppCompat = false;
        }

        public Builder setNotice(Notice notice) {
            this.mNotices = new Notices();
            this.mNotices.addNotice(notice);
            return this;
        }

        public Builder setNotices(Notices notices) {
            this.mNotices = notices;
            return this;
        }

        public Builder setNotices(int i) {
            this.mRawNoticesResourceId = Integer.valueOf(i);
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

        public Builder setNoticesCssStyle(int i) {
            this.mNoticesStyle = this.mContext.getString(i);
            return this;
        }

        public Builder setNoticesCssStyle(String str) {
            this.mNoticesStyle = str;
            return this;
        }

        public Builder setThemeResourceId(int i) {
            this.mThemeResourceId = i;
            return this;
        }

        public Builder setDividerColorRes(int i) {
            this.mDividerColor = this.mContext.getResources().getColor(i);
            return this;
        }

        public Builder setDividerColor(int i) {
            this.mDividerColor = i;
            return this;
        }

        public Builder setUseAppCompat(boolean z) {
            this.mUseAppCompat = z;
            return this;
        }

        public LicensesDialogFragment build() {
            Notices notices = this.mNotices;
            if (notices != null) {
                return LicensesDialogFragment.newInstance(notices, this.mShowFullLicenseText, this.mIncludeOwnLicense, this.mNoticesStyle, this.mThemeResourceId, this.mDividerColor, this.mUseAppCompat);
            }
            Integer num = this.mRawNoticesResourceId;
            if (num != null) {
                return LicensesDialogFragment.newInstance(num.intValue(), this.mShowFullLicenseText, this.mIncludeOwnLicense, this.mNoticesStyle, this.mThemeResourceId, this.mDividerColor, this.mUseAppCompat);
            }
            throw new IllegalStateException("Required parameter not set. You need to call setNotices.");
        }
    }
}
