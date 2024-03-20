package org.acra.config;

import java.lang.reflect.Modifier;

public final class ClassValidator {
    private ClassValidator() {
    }

    public static void check(Class<?>... clsArr) throws ACRAConfigurationException {
        int length = clsArr.length;
        int i = 0;
        while (i < length) {
            Class<?> cls = clsArr[i];
            if (cls.isInterface()) {
                throw new ACRAConfigurationException("Expected class, but found interface " + cls.getName() + ".");
            } else if (Modifier.isAbstract(cls.getModifiers())) {
                throw new ACRAConfigurationException("Class " + cls.getName() + " cannot be abstract.");
            } else if (cls.getEnclosingClass() == null || Modifier.isStatic(cls.getModifiers())) {
                try {
                    cls.getConstructor(new Class[0]);
                    i++;
                } catch (NoSuchMethodException e) {
                    throw new ACRAConfigurationException("Class " + cls.getName() + " is missing a no-args Constructor.", e);
                }
            } else {
                throw new ACRAConfigurationException("Class " + cls.getName() + " has to be static.");
            }
        }
    }
}
