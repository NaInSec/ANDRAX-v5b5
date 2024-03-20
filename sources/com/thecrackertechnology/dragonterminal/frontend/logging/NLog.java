package com.thecrackertechnology.dragonterminal.frontend.logging;

import android.content.Context;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.RetentionPolicy;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001EB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0001J'\u0010!\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u00062\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010%\"\u00020\u0001¢\u0006\u0002\u0010&J\u000e\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020(J\u0012\u0010*\u001a\u00020\u001c2\b\u0010+\u001a\u0004\u0018\u00010,H\u0002J\u0010\u0010-\u001a\u00020\u001c2\u0006\u0010.\u001a\u00020\u0006H\u0002J\u000e\u0010/\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0001J'\u0010/\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u00062\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010%\"\u00020\u0001¢\u0006\u0002\u0010&J\u000e\u00100\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0001J'\u00100\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u00062\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010%\"\u00020\u0001¢\u0006\u0002\u0010&J\u000e\u0010+\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0001J\u0016\u0010+\u001a\u00020\"2\u0006\u00101\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0001J\u001e\u0010+\u001a\u00020\"2\u0006\u00101\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0001J\u0016\u0010+\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0001J\u000e\u00102\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0001J'\u00102\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u00062\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010%\"\u00020\u0001¢\u0006\u0002\u0010&J\u000e\u00103\u001a\u00020\"2\u0006\u00104\u001a\u000205J\u0012\u00106\u001a\u00020\u001c2\b\u00107\u001a\u0004\u0018\u00010\u0006H\u0002J1\u00108\u001a\u00020\"2\u0006\u00101\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00062\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010%\"\u00020\u0001H\u0002¢\u0006\u0002\u00109J \u0010:\u001a\u00020\"2\u0006\u00101\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\u0006H\u0002J \u0010<\u001a\u00020\"2\u0006\u00101\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\u0006H\u0002J \u0010=\u001a\u00020\"2\u0006\u00101\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\u0006H\u0002J!\u0010>\u001a\u00020\u00062\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010%\"\u00020\u0001H\u0002¢\u0006\u0002\u0010?J\u001b\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00060%2\u0006\u0010$\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010AJ\u000e\u0010B\u001a\u00020(2\u0006\u0010)\u001a\u00020(J\u000e\u0010C\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0001J'\u0010C\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u00062\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010%\"\u00020\u0001¢\u0006\u0002\u0010&J\u000e\u0010D\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0001J'\u0010D\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u00062\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010%\"\u00020\u0001¢\u0006\u0002\u0010&R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000e*\u0004\u0018\u00010\u00060\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/logging/NLog;", "", "()V", "A", "", "ARGS", "", "D", "E", "FILE", "FORMAT", "Ljava/text/SimpleDateFormat;", "I", "LINE_SEP", "kotlin.jvm.PlatformType", "MAX_LEN", "NULL_TIPS", "T", "", "V", "W", "executor", "Ljava/util/concurrent/ExecutorService;", "logDir", "sConsoleFilter", "sFileFilter", "sGlobalTag", "sLog2ConsoleSwitch", "", "sLog2FileSwitch", "sLogHeadSwitch", "sLogSwitch", "sTagIsSpace", "a", "", "contents", "tag", "", "(Ljava/lang/String;[Ljava/lang/Object;)V", "compress", "", "input", "createOrExistsDir", "file", "Ljava/io/File;", "createOrExistsFile", "filePath", "d", "e", "type", "i", "init", "context", "Landroid/content/Context;", "isSpace", "s", "log", "(ILjava/lang/String;[Ljava/lang/Object;)V", "print", "msg", "printToConsole", "printToFile", "processBody", "([Ljava/lang/Object;)Ljava/lang/String;", "processTagAndHead", "(Ljava/lang/String;)[Ljava/lang/String;", "uncompress", "v", "w", "TYPE", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NLog.kt */
public final class NLog {
    public static final int A = 7;
    private static final String ARGS = ARGS;
    public static final int D = 3;
    public static final int E = 6;
    private static final int FILE = 16;
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss.SSS ", Locale.getDefault());
    public static final int I = 4;
    public static final NLog INSTANCE = new NLog();
    private static final String LINE_SEP = System.getProperty("line.separator");
    private static final int MAX_LEN = MAX_LEN;
    private static final String NULL_TIPS = NULL_TIPS;
    private static final char[] T = {'V', 'D', 'I', 'W', 'E', 'A'};
    public static final int V = 2;
    public static final int W = 5;
    private static ExecutorService executor;
    private static String logDir;
    private static int sConsoleFilter = 2;
    private static int sFileFilter = 2;
    private static String sGlobalTag = "";
    private static boolean sLog2ConsoleSwitch = true;
    private static boolean sLog2FileSwitch;
    private static boolean sLogHeadSwitch = true;
    private static boolean sLogSwitch = true;
    private static boolean sTagIsSpace = true;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/logging/NLog$TYPE;", "", "app_release"}, k = 1, mv = {1, 1, 15})
    @Retention(AnnotationRetention.SOURCE)
    @java.lang.annotation.Retention(RetentionPolicy.SOURCE)
    /* compiled from: NLog.kt */
    private @interface TYPE {
    }

    private NLog() {
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        File dir = context.getDir("logs", 0);
        Intrinsics.checkExpressionValueIsNotNull(dir, "context.getDir(\"logs\", Context.MODE_PRIVATE)");
        logDir = dir.getAbsolutePath();
        sGlobalTag = "Dragon Terminal";
    }

    public final void v(Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "contents");
        log(2, sGlobalTag, obj);
    }

    public final void v(String str, Object... objArr) {
        Intrinsics.checkParameterIsNotNull(str, "tag");
        Intrinsics.checkParameterIsNotNull(objArr, "contents");
        log(2, str, Arrays.copyOf(objArr, objArr.length));
    }

    public final void d(Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "contents");
        log(3, sGlobalTag, obj);
    }

    public final void d(String str, Object... objArr) {
        Intrinsics.checkParameterIsNotNull(str, "tag");
        Intrinsics.checkParameterIsNotNull(objArr, "contents");
        log(3, str, Arrays.copyOf(objArr, objArr.length));
    }

    public final void i(Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "contents");
        log(4, sGlobalTag, obj);
    }

    public final void i(String str, Object... objArr) {
        Intrinsics.checkParameterIsNotNull(str, "tag");
        Intrinsics.checkParameterIsNotNull(objArr, "contents");
        log(4, str, Arrays.copyOf(objArr, objArr.length));
    }

    public final void w(Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "contents");
        log(5, sGlobalTag, obj);
    }

    public final void w(String str, Object... objArr) {
        Intrinsics.checkParameterIsNotNull(str, "tag");
        Intrinsics.checkParameterIsNotNull(objArr, "contents");
        log(5, str, Arrays.copyOf(objArr, objArr.length));
    }

    public final void e(Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "contents");
        log(6, sGlobalTag, obj);
    }

    public final void e(String str, Object... objArr) {
        Intrinsics.checkParameterIsNotNull(str, "tag");
        Intrinsics.checkParameterIsNotNull(objArr, "contents");
        log(6, str, Arrays.copyOf(objArr, objArr.length));
    }

    public final void a(Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "contents");
        log(7, sGlobalTag, obj);
    }

    public final void a(String str, Object... objArr) {
        Intrinsics.checkParameterIsNotNull(str, "tag");
        Intrinsics.checkParameterIsNotNull(objArr, "contents");
        log(7, str, Arrays.copyOf(objArr, objArr.length));
    }

    public final void file(Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "contents");
        log(FILE | 3, sGlobalTag, obj);
    }

    public final void file(int i, Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "contents");
        log(i | FILE, sGlobalTag, obj);
    }

    public final void file(String str, Object obj) {
        Intrinsics.checkParameterIsNotNull(str, "tag");
        Intrinsics.checkParameterIsNotNull(obj, "contents");
        log(FILE | 3, str, obj);
    }

    public final void file(int i, String str, Object obj) {
        Intrinsics.checkParameterIsNotNull(str, "tag");
        Intrinsics.checkParameterIsNotNull(obj, "contents");
        log(i | FILE, str, obj);
    }

    private final void log(int i, String str, Object... objArr) {
        if (!sLogSwitch) {
            return;
        }
        if (sLog2ConsoleSwitch || sLog2FileSwitch) {
            int i2 = i & 15;
            int i3 = i & SDL_1_2_Keycodes.SDLK_WORLD_80;
            if (i2 >= sConsoleFilter || i2 >= sFileFilter) {
                String[] processTagAndHead = processTagAndHead(str);
                String processBody = processBody(Arrays.copyOf(objArr, objArr.length));
                if (sLog2ConsoleSwitch && i2 >= sConsoleFilter) {
                    String str2 = processTagAndHead[0];
                    printToConsole(i2, str2, processTagAndHead[1] + processBody);
                }
                if ((sLog2FileSwitch || i3 == FILE) && i2 >= sFileFilter) {
                    String str3 = processTagAndHead[0];
                    printToFile(i2, str3, processTagAndHead[2] + processBody);
                }
            }
        }
    }

    private final String[] processTagAndHead(String str) {
        String str2;
        List list;
        List list2;
        boolean z;
        boolean z2;
        if (sTagIsSpace || sLogHeadSwitch) {
            str2 = sGlobalTag + '-' + str;
            StackTraceElement stackTraceElement = new Throwable().getStackTrace()[3];
            Intrinsics.checkExpressionValueIsNotNull(stackTraceElement, "targetElement");
            String className = stackTraceElement.getClassName();
            Intrinsics.checkExpressionValueIsNotNull(className, "className");
            List<String> split = new Regex("\\.").split(className, 0);
            if (!split.isEmpty()) {
                ListIterator<String> listIterator = split.listIterator(split.size());
                while (true) {
                    if (!listIterator.hasPrevious()) {
                        break;
                    }
                    if (listIterator.previous().length() == 0) {
                        z2 = true;
                        continue;
                    } else {
                        z2 = false;
                        continue;
                    }
                    if (!z2) {
                        list = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            }
            list = CollectionsKt.emptyList();
            Object[] array = list.toArray(new String[0]);
            if (array != null) {
                String[] strArr = (String[]) array;
                if (!(strArr.length == 0)) {
                    className = strArr[strArr.length - 1];
                }
                Intrinsics.checkExpressionValueIsNotNull(className, "className");
                CharSequence charSequence = className;
                if (StringsKt.contains$default(charSequence, (CharSequence) "$", false, 2, (Object) null)) {
                    List<String> split2 = new Regex("\\$").split(charSequence, 0);
                    if (!split2.isEmpty()) {
                        ListIterator<String> listIterator2 = split2.listIterator(split2.size());
                        while (true) {
                            if (!listIterator2.hasPrevious()) {
                                break;
                            }
                            if (listIterator2.previous().length() == 0) {
                                z = true;
                                continue;
                            } else {
                                z = false;
                                continue;
                            }
                            if (!z) {
                                list2 = CollectionsKt.take(split2, listIterator2.nextIndex() + 1);
                                break;
                            }
                        }
                    }
                    list2 = CollectionsKt.emptyList();
                    Object[] array2 = list2.toArray(new String[0]);
                    if (array2 != null) {
                        className = ((String[]) array2)[0];
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                }
                if (sTagIsSpace && isSpace(str2)) {
                    Intrinsics.checkExpressionValueIsNotNull(className, "className");
                    str2 = className;
                }
                if (sLogHeadSwitch) {
                    Formatter formatter = new Formatter();
                    Thread currentThread = Thread.currentThread();
                    Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                    String formatter2 = formatter.format("[Thread:%s], %s(%s:%d): ", new Object[]{currentThread.getName(), stackTraceElement.getMethodName(), stackTraceElement.getFileName(), Integer.valueOf(stackTraceElement.getLineNumber())}).toString();
                    Intrinsics.checkExpressionValueIsNotNull(formatter2, "Formatter()\n            …              .toString()");
                    return new String[]{str2, formatter2, formatter2};
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        } else {
            str2 = sGlobalTag;
        }
        return new String[]{str2, "", ": "};
    }

    private final String processBody(Object... objArr) {
        String str = NULL_TIPS;
        int i = 0;
        if (!(!(objArr.length == 0))) {
            return str;
        }
        if (objArr.length == 1) {
            return objArr[0].toString();
        }
        StringBuilder sb = new StringBuilder();
        int length = objArr.length;
        int i2 = 0;
        while (i < length) {
            Object obj = objArr[i];
            sb.append(ARGS);
            sb.append("[");
            sb.append(i2);
            sb.append("]");
            sb.append(" = ");
            sb.append(obj.toString());
            sb.append(LINE_SEP);
            i++;
            i2++;
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    private final void printToConsole(int i, String str, String str2) {
        int length = str2.length();
        int i2 = MAX_LEN;
        int i3 = length / i2;
        if (i3 <= 0) {
            print(i, str, str2);
        } else if (str2 != null) {
            String substring = str2.substring(0, i2);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            print(i, str, substring);
            int i4 = MAX_LEN;
            int i5 = 1;
            int i6 = i3 - 1;
            if (1 <= i6) {
                while (true) {
                    int i7 = MAX_LEN + i4;
                    if (str2 != null) {
                        String substring2 = str2.substring(i4, i7);
                        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        print(i, str, substring2);
                        i4 += MAX_LEN;
                        if (i5 == i6) {
                            break;
                        }
                        i5++;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                }
            }
            if (str2 != null) {
                String substring3 = str2.substring(i4, length);
                Intrinsics.checkExpressionValueIsNotNull(substring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                print(i, str, substring3);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        } else {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
    }

    private final void print(int i, String str, String str2) {
        Log.println(i, str, str2);
    }

    private final void printToFile(int i, String str, String str2) {
        String format = FORMAT.format(new Date(System.currentTimeMillis()));
        Intrinsics.checkExpressionValueIsNotNull(format, "format");
        if (format != null) {
            String substring = format.substring(0, 5);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            String substring2 = format.substring(6);
            Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
            String str3 = logDir + substring + ".txt";
            if (!createOrExistsFile(str3)) {
                Log.e(str, "log to " + str3 + " failed!");
                return;
            }
            String str4 = substring2 + T[i - 2] + "/" + str + str2 + LINE_SEP;
            Intrinsics.checkExpressionValueIsNotNull(str4, "sb.toString()");
            if (executor == null) {
                executor = Executors.newSingleThreadExecutor();
            }
            ExecutorService executorService = executor;
            if (executorService == null) {
                Intrinsics.throwNpe();
            }
            executorService.execute(new NLog$printToFile$1(str3, str4, str));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private final boolean createOrExistsFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private final boolean createOrExistsDir(File file) {
        if (file != null) {
            if (file.exists() ? file.isDirectory() : file.mkdirs()) {
                return true;
            }
        }
        return false;
    }

    private final boolean isSpace(String str) {
        return str == null || str.length() == 0;
    }

    /* JADX INFO: finally extract failed */
    public final byte[] compress(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "input");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Deflater deflater = new Deflater(1);
        try {
            deflater.setInput(bArr);
            deflater.finish();
            byte[] bArr2 = new byte[2048];
            while (!deflater.finished()) {
                byteArrayOutputStream.write(bArr2, 0, deflater.deflate(bArr2));
            }
            deflater.end();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            Intrinsics.checkExpressionValueIsNotNull(byteArray, "bos.toByteArray()");
            return byteArray;
        } catch (Throwable th) {
            deflater.end();
            throw th;
        }
    }

    public final byte[] uncompress(byte[] bArr) {
        int i;
        Intrinsics.checkParameterIsNotNull(bArr, "input");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Inflater inflater = new Inflater();
        try {
            inflater.setInput(bArr);
            byte[] bArr2 = new byte[2048];
            while (!inflater.finished()) {
                i = inflater.inflate(bArr2);
                byteArrayOutputStream.write(bArr2, 0, i);
            }
            inflater.end();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            Intrinsics.checkExpressionValueIsNotNull(byteArray, "bos.toByteArray()");
            return byteArray;
        } catch (DataFormatException e) {
            e.printStackTrace();
            i = 0;
        } catch (Throwable th) {
            inflater.end();
            throw th;
        }
    }
}
