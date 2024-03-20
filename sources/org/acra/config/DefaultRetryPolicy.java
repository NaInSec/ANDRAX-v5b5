package org.acra.config;

import java.util.List;
import org.acra.config.RetryPolicy;
import org.acra.sender.ReportSender;

public class DefaultRetryPolicy implements RetryPolicy {
    public boolean shouldRetrySend(List<ReportSender> list, List<RetryPolicy.FailedSender> list2) {
        return list.size() == list2.size() && !list.isEmpty();
    }
}
