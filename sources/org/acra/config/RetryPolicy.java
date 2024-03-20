package org.acra.config;

import java.util.List;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

public interface RetryPolicy {
    boolean shouldRetrySend(List<ReportSender> list, List<FailedSender> list2);

    public static class FailedSender {
        private final ReportSenderException exception;
        private final ReportSender sender;

        public FailedSender(ReportSender reportSender, ReportSenderException reportSenderException) {
            this.sender = reportSender;
            this.exception = reportSenderException;
        }

        public ReportSender getSender() {
            return this.sender;
        }

        public ReportSenderException getException() {
            return this.exception;
        }
    }
}
