package org.apache.commons.lang3.concurrent;

import java.util.concurrent.atomic.AtomicLong;

public class ThresholdCircuitBreaker extends AbstractCircuitBreaker<Long> {
    private static final long INITIAL_COUNT = 0;
    private final long threshold;
    private final AtomicLong used = new AtomicLong(0);

    public ThresholdCircuitBreaker(long j) {
        this.threshold = j;
    }

    public long getThreshold() {
        return this.threshold;
    }

    public boolean checkState() throws CircuitBreakingException {
        return isOpen();
    }

    public void close() {
        super.close();
        this.used.set(0);
    }

    public boolean incrementAndCheckState(Long l) throws CircuitBreakingException {
        if (this.threshold == 0) {
            open();
        }
        if (this.used.addAndGet(l.longValue()) > this.threshold) {
            open();
        }
        return checkState();
    }
}
