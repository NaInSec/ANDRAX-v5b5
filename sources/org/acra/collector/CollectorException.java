package org.acra.collector;

public class CollectorException extends Exception {
    public CollectorException() {
    }

    public CollectorException(String str) {
        super(str);
    }

    public CollectorException(String str, Throwable th) {
        super(str, th);
    }

    public CollectorException(Throwable th) {
        super(th);
    }
}
