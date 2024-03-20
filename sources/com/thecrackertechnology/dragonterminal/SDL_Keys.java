package com.thecrackertechnology.dragonterminal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: Keycodes */
class SDL_Keys {
    static final int JAVA_KEYCODE_LAST = 255;
    public static String[] names;
    public static String[] namesSorted;
    public static Integer[] namesSortedBackIdx;
    public static Integer[] namesSortedIdx;
    public static Integer[] values;

    SDL_Keys() {
    }

    static String getName(int i) {
        int i2 = 0;
        while (true) {
            Integer[] numArr = values;
            if (i2 >= numArr.length) {
                return names[0];
            }
            if (numArr[i2].intValue() == i) {
                return names[i2];
            }
            i2++;
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            for (Field field : SDL_1_2_Keycodes.class.getDeclaredFields()) {
                if (field.getName().startsWith("SDLK_")) {
                    arrayList2.add(Integer.valueOf(field.getInt((Object) null)));
                    arrayList.add(field.getName().substring(5).toUpperCase());
                }
            }
        } catch (IllegalAccessException unused) {
        }
        for (int i = 0; i < arrayList2.size(); i++) {
            for (int i2 = i; i2 < arrayList2.size(); i2++) {
                if (((Integer) arrayList2.get(i)).intValue() > ((Integer) arrayList2.get(i2)).intValue()) {
                    int intValue = ((Integer) arrayList2.get(i)).intValue();
                    arrayList2.set(i, arrayList2.get(i2));
                    arrayList2.set(i2, Integer.valueOf(intValue));
                    arrayList.set(i, arrayList.get(i2));
                    arrayList.set(i2, (String) arrayList.get(i));
                }
            }
        }
        names = (String[]) arrayList.toArray(new String[0]);
        values = (Integer[]) arrayList2.toArray(new Integer[0]);
        namesSorted = (String[]) arrayList.toArray(new String[0]);
        Integer[] numArr = values;
        namesSortedIdx = new Integer[numArr.length];
        namesSortedBackIdx = new Integer[numArr.length];
        Arrays.sort(namesSorted);
        for (int i3 = 0; i3 < namesSorted.length; i3++) {
            int i4 = 0;
            while (true) {
                String[] strArr = namesSorted;
                if (i4 >= strArr.length) {
                    break;
                } else if (strArr[i3].equals(names[i4])) {
                    namesSortedIdx[i3] = Integer.valueOf(i4);
                    namesSortedBackIdx[i4] = Integer.valueOf(i3);
                    break;
                } else {
                    i4++;
                }
            }
        }
    }
}
