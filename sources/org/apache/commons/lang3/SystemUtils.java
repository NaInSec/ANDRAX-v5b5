package org.apache.commons.lang3;

import java.io.File;
import java.io.PrintStream;

public class SystemUtils {
    public static final String AWT_TOOLKIT = getSystemProperty("awt.toolkit");
    public static final String FILE_ENCODING = getSystemProperty("file.encoding");
    @Deprecated
    public static final String FILE_SEPARATOR = getSystemProperty("file.separator");
    public static final boolean IS_JAVA_1_1 = getJavaVersionMatches("1.1");
    public static final boolean IS_JAVA_1_2 = getJavaVersionMatches("1.2");
    public static final boolean IS_JAVA_1_3 = getJavaVersionMatches("1.3");
    public static final boolean IS_JAVA_1_4 = getJavaVersionMatches("1.4");
    public static final boolean IS_JAVA_1_5 = getJavaVersionMatches("1.5");
    public static final boolean IS_JAVA_1_6 = getJavaVersionMatches("1.6");
    public static final boolean IS_JAVA_1_7 = getJavaVersionMatches("1.7");
    public static final boolean IS_JAVA_1_8 = getJavaVersionMatches("1.8");
    @Deprecated
    public static final boolean IS_JAVA_1_9 = getJavaVersionMatches("9");
    public static final boolean IS_JAVA_9 = getJavaVersionMatches("9");
    public static final boolean IS_OS_400 = getOSMatchesName("OS/400");
    public static final boolean IS_OS_AIX = getOSMatchesName("AIX");
    public static final boolean IS_OS_FREE_BSD = getOSMatchesName("FreeBSD");
    public static final boolean IS_OS_HP_UX = getOSMatchesName("HP-UX");
    public static final boolean IS_OS_IRIX = getOSMatchesName("Irix");
    public static final boolean IS_OS_LINUX = (getOSMatchesName("Linux") || getOSMatchesName("LINUX"));
    public static final boolean IS_OS_MAC = getOSMatchesName("Mac");
    public static final boolean IS_OS_MAC_OSX = getOSMatchesName("Mac OS X");
    public static final boolean IS_OS_MAC_OSX_CHEETAH = getOSMatches("Mac OS X", "10.0");
    public static final boolean IS_OS_MAC_OSX_EL_CAPITAN = getOSMatches("Mac OS X", "10.11");
    public static final boolean IS_OS_MAC_OSX_JAGUAR = getOSMatches("Mac OS X", "10.2");
    public static final boolean IS_OS_MAC_OSX_LEOPARD = getOSMatches("Mac OS X", "10.5");
    public static final boolean IS_OS_MAC_OSX_LION = getOSMatches("Mac OS X", "10.7");
    public static final boolean IS_OS_MAC_OSX_MAVERICKS = getOSMatches("Mac OS X", "10.9");
    public static final boolean IS_OS_MAC_OSX_MOUNTAIN_LION = getOSMatches("Mac OS X", "10.8");
    public static final boolean IS_OS_MAC_OSX_PANTHER = getOSMatches("Mac OS X", "10.3");
    public static final boolean IS_OS_MAC_OSX_PUMA = getOSMatches("Mac OS X", "10.1");
    public static final boolean IS_OS_MAC_OSX_SNOW_LEOPARD = getOSMatches("Mac OS X", "10.6");
    public static final boolean IS_OS_MAC_OSX_TIGER = getOSMatches("Mac OS X", "10.4");
    public static final boolean IS_OS_MAC_OSX_YOSEMITE = getOSMatches("Mac OS X", "10.10");
    public static final boolean IS_OS_NET_BSD = getOSMatchesName("NetBSD");
    public static final boolean IS_OS_OPEN_BSD = getOSMatchesName("OpenBSD");
    public static final boolean IS_OS_OS2 = getOSMatchesName("OS/2");
    public static final boolean IS_OS_SOLARIS = getOSMatchesName("Solaris");
    public static final boolean IS_OS_SUN_OS = getOSMatchesName("SunOS");
    public static final boolean IS_OS_UNIX;
    public static final boolean IS_OS_WINDOWS = getOSMatchesName(OS_NAME_WINDOWS_PREFIX);
    public static final boolean IS_OS_WINDOWS_10 = getOSMatchesName("Windows 10");
    public static final boolean IS_OS_WINDOWS_2000 = getOSMatchesName("Windows 2000");
    public static final boolean IS_OS_WINDOWS_2003 = getOSMatchesName("Windows 2003");
    public static final boolean IS_OS_WINDOWS_2008 = getOSMatchesName("Windows Server 2008");
    public static final boolean IS_OS_WINDOWS_2012 = getOSMatchesName("Windows Server 2012");
    public static final boolean IS_OS_WINDOWS_7 = getOSMatchesName("Windows 7");
    public static final boolean IS_OS_WINDOWS_8 = getOSMatchesName("Windows 8");
    public static final boolean IS_OS_WINDOWS_95 = getOSMatchesName("Windows 95");
    public static final boolean IS_OS_WINDOWS_98 = getOSMatchesName("Windows 98");
    public static final boolean IS_OS_WINDOWS_ME = getOSMatchesName("Windows Me");
    public static final boolean IS_OS_WINDOWS_NT = getOSMatchesName("Windows NT");
    public static final boolean IS_OS_WINDOWS_VISTA = getOSMatchesName("Windows Vista");
    public static final boolean IS_OS_WINDOWS_XP = getOSMatchesName("Windows XP");
    public static final boolean IS_OS_ZOS = getOSMatchesName("z/OS");
    public static final String JAVA_AWT_FONTS = getSystemProperty("java.awt.fonts");
    public static final String JAVA_AWT_GRAPHICSENV = getSystemProperty("java.awt.graphicsenv");
    public static final String JAVA_AWT_HEADLESS = getSystemProperty("java.awt.headless");
    public static final String JAVA_AWT_PRINTERJOB = getSystemProperty("java.awt.printerjob");
    public static final String JAVA_CLASS_PATH = getSystemProperty("java.class.path");
    public static final String JAVA_CLASS_VERSION = getSystemProperty("java.class.version");
    public static final String JAVA_COMPILER = getSystemProperty("java.compiler");
    public static final String JAVA_ENDORSED_DIRS = getSystemProperty("java.endorsed.dirs");
    public static final String JAVA_EXT_DIRS = getSystemProperty("java.ext.dirs");
    public static final String JAVA_HOME = getSystemProperty(JAVA_HOME_KEY);
    private static final String JAVA_HOME_KEY = "java.home";
    public static final String JAVA_IO_TMPDIR = getSystemProperty(JAVA_IO_TMPDIR_KEY);
    private static final String JAVA_IO_TMPDIR_KEY = "java.io.tmpdir";
    public static final String JAVA_LIBRARY_PATH = getSystemProperty("java.library.path");
    public static final String JAVA_RUNTIME_NAME = getSystemProperty("java.runtime.name");
    public static final String JAVA_RUNTIME_VERSION = getSystemProperty("java.runtime.version");
    public static final String JAVA_SPECIFICATION_NAME = getSystemProperty("java.specification.name");
    public static final String JAVA_SPECIFICATION_VENDOR = getSystemProperty("java.specification.vendor");
    public static final String JAVA_SPECIFICATION_VERSION = getSystemProperty("java.specification.version");
    private static final JavaVersion JAVA_SPECIFICATION_VERSION_AS_ENUM = JavaVersion.get(JAVA_SPECIFICATION_VERSION);
    public static final String JAVA_UTIL_PREFS_PREFERENCES_FACTORY = getSystemProperty("java.util.prefs.PreferencesFactory");
    public static final String JAVA_VENDOR = getSystemProperty("java.vendor");
    public static final String JAVA_VENDOR_URL = getSystemProperty("java.vendor.url");
    public static final String JAVA_VERSION = getSystemProperty("java.version");
    public static final String JAVA_VM_INFO = getSystemProperty("java.vm.info");
    public static final String JAVA_VM_NAME = getSystemProperty("java.vm.name");
    public static final String JAVA_VM_SPECIFICATION_NAME = getSystemProperty("java.vm.specification.name");
    public static final String JAVA_VM_SPECIFICATION_VENDOR = getSystemProperty("java.vm.specification.vendor");
    public static final String JAVA_VM_SPECIFICATION_VERSION = getSystemProperty("java.vm.specification.version");
    public static final String JAVA_VM_VENDOR = getSystemProperty("java.vm.vendor");
    public static final String JAVA_VM_VERSION = getSystemProperty("java.vm.version");
    @Deprecated
    public static final String LINE_SEPARATOR = getSystemProperty("line.separator");
    public static final String OS_ARCH = getSystemProperty("os.arch");
    public static final String OS_NAME = getSystemProperty("os.name");
    private static final String OS_NAME_WINDOWS_PREFIX = "Windows";
    public static final String OS_VERSION = getSystemProperty("os.version");
    @Deprecated
    public static final String PATH_SEPARATOR = getSystemProperty("path.separator");
    public static final String USER_COUNTRY;
    public static final String USER_DIR = getSystemProperty(USER_DIR_KEY);
    private static final String USER_DIR_KEY = "user.dir";
    public static final String USER_HOME = getSystemProperty(USER_HOME_KEY);
    private static final String USER_HOME_KEY = "user.home";
    public static final String USER_LANGUAGE = getSystemProperty("user.language");
    public static final String USER_NAME = getSystemProperty("user.name");
    public static final String USER_TIMEZONE = getSystemProperty("user.timezone");

    static {
        String str = "user.country";
        if (getSystemProperty(str) == null) {
            str = "user.region";
        }
        USER_COUNTRY = getSystemProperty(str);
        boolean z = false;
        if (IS_OS_AIX || IS_OS_HP_UX || IS_OS_IRIX || IS_OS_LINUX || IS_OS_MAC_OSX || IS_OS_SOLARIS || IS_OS_SUN_OS || IS_OS_FREE_BSD || IS_OS_OPEN_BSD || IS_OS_NET_BSD) {
            z = true;
        }
        IS_OS_UNIX = z;
    }

    public static File getJavaHome() {
        return new File(System.getProperty(JAVA_HOME_KEY));
    }

    public static String getHostName() {
        return System.getenv(IS_OS_WINDOWS ? "COMPUTERNAME" : "HOSTNAME");
    }

    public static File getJavaIoTmpDir() {
        return new File(System.getProperty(JAVA_IO_TMPDIR_KEY));
    }

    private static boolean getJavaVersionMatches(String str) {
        return isJavaVersionMatch(JAVA_SPECIFICATION_VERSION, str);
    }

    private static boolean getOSMatches(String str, String str2) {
        return isOSMatch(OS_NAME, OS_VERSION, str, str2);
    }

    private static boolean getOSMatchesName(String str) {
        return isOSNameMatch(OS_NAME, str);
    }

    private static String getSystemProperty(String str) {
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            PrintStream printStream = System.err;
            printStream.println("Caught a SecurityException reading the system property '" + str + "'; the SystemUtils property value will default to null.");
            return null;
        }
    }

    public static File getUserDir() {
        return new File(System.getProperty(USER_DIR_KEY));
    }

    public static File getUserHome() {
        return new File(System.getProperty(USER_HOME_KEY));
    }

    public static boolean isJavaAwtHeadless() {
        return Boolean.TRUE.toString().equals(JAVA_AWT_HEADLESS);
    }

    public static boolean isJavaVersionAtLeast(JavaVersion javaVersion) {
        return JAVA_SPECIFICATION_VERSION_AS_ENUM.atLeast(javaVersion);
    }

    static boolean isJavaVersionMatch(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str.startsWith(str2);
    }

    static boolean isOSMatch(String str, String str2, String str3, String str4) {
        return str != null && str2 != null && isOSNameMatch(str, str3) && isOSVersionMatch(str2, str4);
    }

    static boolean isOSNameMatch(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str.startsWith(str2);
    }

    static boolean isOSVersionMatch(String str, String str2) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str2.split("\\.");
        String[] split2 = str.split("\\.");
        for (int i = 0; i < Math.min(split.length, split2.length); i++) {
            if (!split[i].equals(split2[i])) {
                return false;
            }
        }
        return true;
    }
}
