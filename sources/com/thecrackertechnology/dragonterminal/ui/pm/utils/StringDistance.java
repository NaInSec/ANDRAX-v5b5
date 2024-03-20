package com.thecrackertechnology.dragonterminal.ui.pm.utils;

import java.lang.reflect.Array;

public class StringDistance {
    public static int distance(String str, String str2) {
        int i;
        char[] charArray = str.toCharArray();
        char[] charArray2 = str2.toCharArray();
        int length = charArray.length;
        int length2 = charArray2.length;
        int[][] iArr = (int[][]) Array.newInstance(int.class, new int[]{length + 1, length2 + 1});
        for (int i2 = 0; i2 <= length; i2++) {
            iArr[i2][0] = i2;
        }
        for (int i3 = 0; i3 <= length2; i3++) {
            iArr[0][i3] = i3;
        }
        for (int i4 = 1; i4 <= length; i4++) {
            for (int i5 = 1; i5 <= length2; i5++) {
                int i6 = i4 - 1;
                int i7 = i5 - 1;
                if (charArray[i6] == charArray2[i7]) {
                    iArr[i4][i5] = iArr[i6][i7];
                } else {
                    int i8 = iArr[i4][i7] + 1;
                    int i9 = iArr[i6][i5] + 1;
                    int i10 = iArr[i6][i7] + 1;
                    int[] iArr2 = iArr[i4];
                    if (Math.min(i8, i9) > Math.min(i9, i10)) {
                        i = Math.min(i9, i10);
                    } else {
                        i = Math.min(i8, i9);
                    }
                    iArr2[i5] = i;
                }
            }
        }
        return iArr[length][length2];
    }
}
