package org.acra.config;

public class ACRAConfigurationException extends Exception {
    private static final long serialVersionUID = -7355339673505996110L;

    public ACRAConfigurationException(String str) {
        super(str);
    }

    public ACRAConfigurationException(String str, Throwable th) {
        super(str, th);
    }
}
