package org.apache.commons.lang3;

import java.util.Random;

@Deprecated
public class RandomStringUtils {
    private static final Random RANDOM = new Random();

    public static String random(int i) {
        return random(i, false, false);
    }

    public static String randomAscii(int i) {
        return random(i, 32, 127, false, false);
    }

    public static String randomAscii(int i, int i2) {
        return randomAscii(RandomUtils.nextInt(i, i2));
    }

    public static String randomAlphabetic(int i) {
        return random(i, true, false);
    }

    public static String randomAlphabetic(int i, int i2) {
        return randomAlphabetic(RandomUtils.nextInt(i, i2));
    }

    public static String randomAlphanumeric(int i) {
        return random(i, true, true);
    }

    public static String randomAlphanumeric(int i, int i2) {
        return randomAlphanumeric(RandomUtils.nextInt(i, i2));
    }

    public static String randomGraph(int i) {
        return random(i, 33, SDL_1_3_Keycodes.SDLK_FIND, false, false);
    }

    public static String randomGraph(int i, int i2) {
        return randomGraph(RandomUtils.nextInt(i, i2));
    }

    public static String randomNumeric(int i) {
        return random(i, false, true);
    }

    public static String randomNumeric(int i, int i2) {
        return randomNumeric(RandomUtils.nextInt(i, i2));
    }

    public static String randomPrint(int i) {
        return random(i, 32, SDL_1_3_Keycodes.SDLK_FIND, false, false);
    }

    public static String randomPrint(int i, int i2) {
        return randomPrint(RandomUtils.nextInt(i, i2));
    }

    public static String random(int i, boolean z, boolean z2) {
        return random(i, 0, 0, z, z2);
    }

    public static String random(int i, int i2, int i3, boolean z, boolean z2) {
        return random(i, i2, i3, z, z2, (char[]) null, RANDOM);
    }

    public static String random(int i, int i2, int i3, boolean z, boolean z2, char... cArr) {
        return random(i, i2, i3, z, z2, cArr, RANDOM);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0084, code lost:
        if (r2 != 19) goto L_0x0091;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String random(int r4, int r5, int r6, boolean r7, boolean r8, char[] r9, java.util.Random r10) {
        /*
            if (r4 != 0) goto L_0x0005
            java.lang.String r4 = ""
            return r4
        L_0x0005:
            if (r4 < 0) goto L_0x00e3
            if (r9 == 0) goto L_0x0015
            int r0 = r9.length
            if (r0 == 0) goto L_0x000d
            goto L_0x0015
        L_0x000d:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "The chars array must not be empty"
            r4.<init>(r5)
            throw r4
        L_0x0015:
            java.lang.String r0 = "Parameter end ("
            if (r5 != 0) goto L_0x002c
            if (r6 != 0) goto L_0x002c
            if (r9 == 0) goto L_0x001f
            int r6 = r9.length
            goto L_0x002e
        L_0x001f:
            if (r7 != 0) goto L_0x0027
            if (r8 != 0) goto L_0x0027
            r6 = 1114111(0x10ffff, float:1.561202E-39)
            goto L_0x002e
        L_0x0027:
            r6 = 123(0x7b, float:1.72E-43)
            r5 = 32
            goto L_0x002e
        L_0x002c:
            if (r6 <= r5) goto L_0x00c1
        L_0x002e:
            if (r9 != 0) goto L_0x0067
            r1 = 65
            r2 = 48
            if (r8 == 0) goto L_0x0038
            if (r6 <= r2) goto L_0x003d
        L_0x0038:
            if (r7 == 0) goto L_0x0067
            if (r6 <= r1) goto L_0x003d
            goto L_0x0067
        L_0x003d:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            r5.append(r6)
            java.lang.String r6 = ") must be greater then ("
            r5.append(r6)
            r5.append(r2)
            java.lang.String r6 = ") for generating digits or greater then ("
            r5.append(r6)
            r5.append(r1)
            java.lang.String r6 = ") for generating letters."
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x0067:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r4)
            int r6 = r6 - r5
        L_0x006d:
            int r1 = r4 + -1
            if (r4 == 0) goto L_0x00bc
            if (r9 != 0) goto L_0x008a
            int r4 = r10.nextInt(r6)
            int r4 = r4 + r5
            int r2 = java.lang.Character.getType(r4)
            if (r2 == 0) goto L_0x0087
            r3 = 18
            if (r2 == r3) goto L_0x0087
            r3 = 19
            if (r2 == r3) goto L_0x0087
            goto L_0x0091
        L_0x0087:
            int r4 = r1 + 1
            goto L_0x006d
        L_0x008a:
            int r4 = r10.nextInt(r6)
            int r4 = r4 + r5
            char r4 = r9[r4]
        L_0x0091:
            int r2 = java.lang.Character.charCount(r4)
            if (r1 != 0) goto L_0x009b
            r3 = 1
            if (r2 <= r3) goto L_0x009b
            goto L_0x0087
        L_0x009b:
            if (r7 == 0) goto L_0x00a3
            boolean r3 = java.lang.Character.isLetter(r4)
            if (r3 != 0) goto L_0x00af
        L_0x00a3:
            if (r8 == 0) goto L_0x00ab
            boolean r3 = java.lang.Character.isDigit(r4)
            if (r3 != 0) goto L_0x00af
        L_0x00ab:
            if (r7 != 0) goto L_0x00b9
            if (r8 != 0) goto L_0x00b9
        L_0x00af:
            r0.appendCodePoint(r4)
            r4 = 2
            if (r2 != r4) goto L_0x00b7
            int r1 = r1 + -1
        L_0x00b7:
            r4 = r1
            goto L_0x006d
        L_0x00b9:
            int r1 = r1 + 1
            goto L_0x00b7
        L_0x00bc:
            java.lang.String r4 = r0.toString()
            return r4
        L_0x00c1:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r0)
            r7.append(r6)
            java.lang.String r6 = ") must be greater than start ("
            r7.append(r6)
            r7.append(r5)
            java.lang.String r5 = ")"
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            r4.<init>(r5)
            throw r4
        L_0x00e3:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Requested random string length "
            r6.append(r7)
            r6.append(r4)
            java.lang.String r4 = " is less than 0."
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.RandomStringUtils.random(int, int, int, boolean, boolean, char[], java.util.Random):java.lang.String");
    }

    public static String random(int i, String str) {
        if (str != null) {
            return random(i, str.toCharArray());
        }
        return random(i, 0, 0, false, false, (char[]) null, RANDOM);
    }

    public static String random(int i, char... cArr) {
        if (cArr == null) {
            return random(i, 0, 0, false, false, (char[]) null, RANDOM);
        }
        return random(i, 0, cArr.length, false, false, cArr, RANDOM);
    }
}
