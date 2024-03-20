package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

final class g {
    public static int a(Class<?>[] clsArr, Class<?> cls) {
        for (int i = 0; i < clsArr.length; i++) {
            if (clsArr[i].isAssignableFrom(cls)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean b(Class<?>[] clsArr, Class<?> cls) {
        for (Class<?> isAssignableFrom : clsArr) {
            if (isAssignableFrom.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }
}
