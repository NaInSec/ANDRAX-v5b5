package com.thecrackertechnology.dragonterminal.component.pm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.lang3.StringUtils;

public class NeoPackageParser {
    private static final String KEY_ARCH = "Architecture";
    private static final String KEY_DEPENDS = "Depends";
    private static final String KEY_DESC = "Description";
    private static final String KEY_ESSENTIAL = "Essential";
    private static final String KEY_FILENAME = "Filename";
    private static final String KEY_HOMEPAGE = "Homepage";
    private static final String KEY_INSTALLED_SIZE = "Installed-Size";
    private static final String KEY_MAINTAINER = "Maintainer";
    private static final String KEY_MD5 = "MD5sum";
    private static final String KEY_PACKAGE_NAME = "Package";
    private static final String KEY_SHA1 = "SHA1";
    private static final String KEY_SHA256 = "SHA256";
    private static final String KEY_SIZE = "Size";
    private static final String KEY_VERSION = "Version";
    private BufferedReader reader;
    private ParseStateListener stateListener;

    public interface ParseStateListener {
        NeoPackageInfo onCreatePackageInfo();

        void onEndParsePackage(NeoPackageInfo neoPackageInfo);

        void onEndState();

        void onStartParsePackage(String str, NeoPackageInfo neoPackageInfo);

        void onStartState();
    }

    NeoPackageParser(InputStream inputStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    /* access modifiers changed from: package-private */
    public void setStateListener(ParseStateListener parseStateListener) {
        this.stateListener = parseStateListener;
    }

    public void parse() throws IOException {
        String str;
        boolean z;
        ParseStateListener parseStateListener = this.stateListener;
        if (parseStateListener != null) {
            String[] strArr = new String[2];
            parseStateListener.onStartState();
            NeoPackageInfo neoPackageInfo = null;
            String str2 = null;
            while (true) {
                String readLine = this.reader.readLine();
                if (readLine == null) {
                    if (neoPackageInfo != null) {
                        this.stateListener.onEndParsePackage(neoPackageInfo);
                    }
                    this.stateListener.onEndState();
                    return;
                } else if (!readLine.isEmpty()) {
                    if (splitKeyAndValue(readLine, strArr)) {
                        str2 = strArr[0];
                        str = strArr[1];
                        z = false;
                    } else if (str2 != null) {
                        str = readLine.trim();
                        z = true;
                    }
                    if (str2.equals(KEY_PACKAGE_NAME)) {
                        if (neoPackageInfo != null) {
                            this.stateListener.onEndParsePackage(neoPackageInfo);
                        }
                        neoPackageInfo = this.stateListener.onCreatePackageInfo();
                        neoPackageInfo.setPackageName(str);
                        this.stateListener.onStartParsePackage(str, neoPackageInfo);
                    }
                    if (neoPackageInfo != null) {
                        if (z) {
                            str = appendToLastValue(neoPackageInfo, str2, str);
                        }
                        char c = 65535;
                        switch (str2.hashCode()) {
                            case -2026030067:
                                if (str2.equals(KEY_MD5)) {
                                    c = 8;
                                    break;
                                }
                                break;
                            case -1850268089:
                                if (str2.equals(KEY_SHA256)) {
                                    c = 10;
                                    break;
                                }
                                break;
                            case -1076162713:
                                if (str2.equals(KEY_DEPENDS)) {
                                    c = 1;
                                    break;
                                }
                                break;
                            case -909613160:
                                if (str2.equals(KEY_MAINTAINER)) {
                                    c = 7;
                                    break;
                                }
                                break;
                            case -670124505:
                                if (str2.equals(KEY_FILENAME)) {
                                    c = 4;
                                    break;
                                }
                                break;
                            case -420727794:
                                if (str2.equals(KEY_HOMEPAGE)) {
                                    c = 5;
                                    break;
                                }
                                break;
                            case -56677412:
                                if (str2.equals(KEY_DESC)) {
                                    c = 2;
                                    break;
                                }
                                break;
                            case 2543909:
                                if (str2.equals(KEY_SHA1)) {
                                    c = 9;
                                    break;
                                }
                                break;
                            case 2577441:
                                if (str2.equals(KEY_SIZE)) {
                                    c = 11;
                                    break;
                                }
                                break;
                            case 379818798:
                                if (str2.equals(KEY_ESSENTIAL)) {
                                    c = 3;
                                    break;
                                }
                                break;
                            case 1003994483:
                                if (str2.equals(KEY_ARCH)) {
                                    c = 0;
                                    break;
                                }
                                break;
                            case 1288087220:
                                if (str2.equals(KEY_INSTALLED_SIZE)) {
                                    c = 6;
                                    break;
                                }
                                break;
                            case 2016261304:
                                if (str2.equals(KEY_VERSION)) {
                                    c = 12;
                                    break;
                                }
                                break;
                        }
                        switch (c) {
                            case 0:
                                neoPackageInfo.setArchitecture(Architecture.Companion.parse(str));
                                break;
                            case 1:
                                neoPackageInfo.setDependenciesString(str);
                                break;
                            case 2:
                                neoPackageInfo.setDescription(str);
                                break;
                            case 3:
                                neoPackageInfo.setEssential(str.equals("yes"));
                                break;
                            case 4:
                                neoPackageInfo.setFileName(str);
                                break;
                            case 5:
                                neoPackageInfo.setHomePage(str);
                                break;
                            case 6:
                                neoPackageInfo.setInstalledSizeInBytes(Long.parseLong(str));
                                break;
                            case 7:
                                neoPackageInfo.setMaintainer(str);
                                break;
                            case 8:
                                neoPackageInfo.setMd5(str);
                                break;
                            case 9:
                                neoPackageInfo.setSha1(str);
                                break;
                            case 10:
                                neoPackageInfo.setSha256(str);
                                break;
                            case 11:
                                neoPackageInfo.setSizeInBytes(Long.parseLong(str));
                                break;
                            case 12:
                                neoPackageInfo.setVersion(str);
                                break;
                        }
                    }
                }
            }
        }
    }

    private String appendToLastValue(NeoPackageInfo neoPackageInfo, String str, String str2) {
        if (((str.hashCode() == -56677412 && str.equals(KEY_DESC)) ? (char) 0 : 65535) != 0) {
            return str2;
        }
        return neoPackageInfo.getDescription() + StringUtils.SPACE + str2;
    }

    private boolean splitKeyAndValue(String str, String[] strArr) {
        int indexOf = str.indexOf(58);
        if (indexOf < 0) {
            return false;
        }
        strArr[0] = str.substring(0, indexOf).trim();
        if (indexOf != str.length()) {
            indexOf++;
        }
        strArr[1] = str.substring(indexOf).trim();
        return true;
    }
}
