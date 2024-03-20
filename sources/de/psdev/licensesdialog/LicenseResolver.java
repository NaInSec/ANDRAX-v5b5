package de.psdev.licensesdialog;

import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.licenses.BSD2ClauseLicense;
import de.psdev.licensesdialog.licenses.BSD3ClauseLicense;
import de.psdev.licensesdialog.licenses.CreativeCommonsAttribution30Unported;
import de.psdev.licensesdialog.licenses.CreativeCommonsAttributionNoDerivs30Unported;
import de.psdev.licensesdialog.licenses.EclipsePublicLicense10;
import de.psdev.licensesdialog.licenses.GnuGeneralPublicLicense20;
import de.psdev.licensesdialog.licenses.GnuGeneralPublicLicense30;
import de.psdev.licensesdialog.licenses.GnuLesserGeneralPublicLicense21;
import de.psdev.licensesdialog.licenses.GnuLesserGeneralPublicLicense3;
import de.psdev.licensesdialog.licenses.ISCLicense;
import de.psdev.licensesdialog.licenses.License;
import de.psdev.licensesdialog.licenses.MITLicense;
import de.psdev.licensesdialog.licenses.MozillaPublicLicense11;
import de.psdev.licensesdialog.licenses.MozillaPublicLicense20;
import de.psdev.licensesdialog.licenses.SILOpenFontLicense11;
import java.util.HashMap;
import java.util.Map;

public final class LicenseResolver {
    private static final int INITIAL_LICENSES_COUNT = 4;
    private static final Map<String, License> sLicenses = new HashMap(4);

    static {
        registerDefaultLicenses();
    }

    static void registerDefaultLicenses() {
        sLicenses.clear();
        registerLicense(new ApacheSoftwareLicense20());
        registerLicense(new BSD2ClauseLicense());
        registerLicense(new BSD3ClauseLicense());
        registerLicense(new ISCLicense());
        registerLicense(new MITLicense());
        registerLicense(new GnuLesserGeneralPublicLicense21());
        registerLicense(new GnuLesserGeneralPublicLicense3());
        registerLicense(new CreativeCommonsAttributionNoDerivs30Unported());
        registerLicense(new GnuGeneralPublicLicense30());
        registerLicense(new GnuGeneralPublicLicense20());
        registerLicense(new MozillaPublicLicense11());
        registerLicense(new SILOpenFontLicense11());
        registerLicense(new MozillaPublicLicense20());
        registerLicense(new CreativeCommonsAttribution30Unported());
        registerLicense(new EclipsePublicLicense10());
    }

    public static void registerLicense(License license) {
        sLicenses.put(license.getName(), license);
    }

    public static License read(String str) {
        String trim = str.trim();
        if (sLicenses.containsKey(trim)) {
            return sLicenses.get(trim);
        }
        throw new IllegalStateException(String.format("no such license available: %s, did you forget to register it?", new Object[]{trim}));
    }

    private LicenseResolver() {
    }
}
