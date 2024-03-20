package de.psdev.licensesdialog;

import android.content.Context;
import de.psdev.licensesdialog.licenses.License;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;
import java.util.HashMap;
import java.util.Map;

public final class NoticesHtmlBuilder {
    private final Context mContext;
    private final Map<License, String> mLicenseTextCache = new HashMap();
    private Notice mNotice;
    private Notices mNotices;
    private boolean mShowFullLicenseText;
    private String mStyle;

    public static NoticesHtmlBuilder create(Context context) {
        return new NoticesHtmlBuilder(context);
    }

    private NoticesHtmlBuilder(Context context) {
        this.mContext = context;
        this.mStyle = context.getResources().getString(R.string.notices_default_style);
        this.mShowFullLicenseText = false;
    }

    public NoticesHtmlBuilder setNotices(Notices notices) {
        this.mNotices = notices;
        this.mNotice = null;
        return this;
    }

    public NoticesHtmlBuilder setNotice(Notice notice) {
        this.mNotice = notice;
        this.mNotices = null;
        return this;
    }

    public NoticesHtmlBuilder setStyle(String str) {
        this.mStyle = str;
        return this;
    }

    public NoticesHtmlBuilder setShowFullLicenseText(boolean z) {
        this.mShowFullLicenseText = z;
        return this;
    }

    public String build() {
        StringBuilder sb = new StringBuilder(500);
        appendNoticesContainerStart(sb);
        Notice notice = this.mNotice;
        if (notice != null) {
            appendNoticeBlock(sb, notice);
        } else {
            Notices notices = this.mNotices;
            if (notices != null) {
                for (Notice appendNoticeBlock : notices.getNotices()) {
                    appendNoticeBlock(sb, appendNoticeBlock);
                }
            } else {
                throw new IllegalStateException("no notice(s) set");
            }
        }
        appendNoticesContainerEnd(sb);
        return sb.toString();
    }

    private void appendNoticesContainerStart(StringBuilder sb) {
        sb.append("<!DOCTYPE html><html><head>");
        sb.append("<style type=\"text/css\">");
        sb.append(this.mStyle);
        sb.append("</style>");
        sb.append("</head><body>");
    }

    private void appendNoticeBlock(StringBuilder sb, Notice notice) {
        sb.append("<ul><li>");
        sb.append(notice.getName());
        String url = notice.getUrl();
        if (url != null && url.length() > 0) {
            sb.append(" (<a href=\"");
            sb.append(url);
            sb.append("\" target=\"_blank\">");
            sb.append(url);
            sb.append("</a>)");
        }
        sb.append("</li></ul>");
        sb.append("<pre>");
        String copyright = notice.getCopyright();
        if (copyright != null) {
            sb.append(copyright);
            sb.append("<br/><br/>");
        }
        sb.append(getLicenseText(notice.getLicense()));
        sb.append("</pre>");
    }

    private void appendNoticesContainerEnd(StringBuilder sb) {
        sb.append("</body></html>");
    }

    private String getLicenseText(License license) {
        if (license == null) {
            return "";
        }
        if (!this.mLicenseTextCache.containsKey(license)) {
            this.mLicenseTextCache.put(license, this.mShowFullLicenseText ? license.getFullText(this.mContext) : license.getSummaryText(this.mContext));
        }
        return this.mLicenseTextCache.get(license);
    }
}
