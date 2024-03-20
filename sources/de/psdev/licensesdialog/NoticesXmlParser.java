package de.psdev.licensesdialog;

import android.util.Xml;
import com.google.android.gms.common.internal.ImagesContract;
import com.thecrackertechnology.dragonterminal.component.colorscheme.NeoColorScheme;
import de.psdev.licensesdialog.licenses.License;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class NoticesXmlParser {
    private static void skip(XmlPullParser xmlPullParser) {
    }

    private NoticesXmlParser() {
    }

    public static Notices parse(InputStream inputStream) throws Exception {
        try {
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
            newPullParser.setInput(inputStream, (String) null);
            newPullParser.nextTag();
            return parse(newPullParser);
        } finally {
            inputStream.close();
        }
    }

    private static Notices parse(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        Notices notices = new Notices();
        xmlPullParser.require(2, (String) null, "notices");
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if ("notice".equals(xmlPullParser.getName())) {
                    notices.addNotice(readNotice(xmlPullParser));
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return notices;
    }

    private static Notice readNotice(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        String str = null;
        xmlPullParser.require(2, (String) null, "notice");
        String str2 = null;
        String str3 = null;
        License license = null;
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (NeoColorScheme.COLOR_META_NAME.equals(name)) {
                    str = readName(xmlPullParser);
                } else if (ImagesContract.URL.equals(name)) {
                    str2 = readUrl(xmlPullParser);
                } else if ("copyright".equals(name)) {
                    str3 = readCopyright(xmlPullParser);
                } else if ("license".equals(name)) {
                    license = readLicense(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return new Notice(str, str2, str3, license);
    }

    private static String readName(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        return readTag(xmlPullParser, NeoColorScheme.COLOR_META_NAME);
    }

    private static String readUrl(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        return readTag(xmlPullParser, ImagesContract.URL);
    }

    private static String readCopyright(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        return readTag(xmlPullParser, "copyright");
    }

    private static License readLicense(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        return LicenseResolver.read(readTag(xmlPullParser, "license"));
    }

    private static String readTag(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, str);
        String readText = readText(xmlPullParser);
        xmlPullParser.require(3, (String) null, str);
        return readText;
    }

    private static String readText(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        if (xmlPullParser.next() != 4) {
            return "";
        }
        String text = xmlPullParser.getText();
        xmlPullParser.nextTag();
        return text;
    }
}
