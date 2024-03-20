package org.acra.interaction;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.acra.ACRA;
import org.acra.config.CoreConfiguration;
import org.acra.log.ACRALog;

public class ReportInteractionExecutor {
    private final CoreConfiguration config;
    private final Context context;
    private final List<ReportInteraction> reportInteractions = new ArrayList();

    public ReportInteractionExecutor(Context context2, CoreConfiguration coreConfiguration) {
        this.context = context2;
        this.config = coreConfiguration;
        Iterator<S> it = ServiceLoader.load(ReportInteraction.class, getClass().getClassLoader()).iterator();
        while (it.hasNext()) {
            try {
                ReportInteraction reportInteraction = (ReportInteraction) it.next();
                if (reportInteraction.enabled(coreConfiguration)) {
                    this.reportInteractions.add(reportInteraction);
                } else if (ACRA.DEV_LOGGING) {
                    ACRALog aCRALog = ACRA.log;
                    String str = ACRA.LOG_TAG;
                    aCRALog.d(str, "Ignoring disabled ReportInteraction of type " + reportInteraction.getClass().getSimpleName());
                }
            } catch (ServiceConfigurationError e) {
                ACRA.log.e(ACRA.LOG_TAG, "Unable to load ReportInteraction", e);
            }
        }
    }

    public boolean hasInteractions() {
        return this.reportInteractions.size() > 0;
    }

    public boolean performInteractions(File file) {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ArrayList<Future> arrayList = new ArrayList<>();
        for (ReportInteraction r3 : this.reportInteractions) {
            arrayList.add(newCachedThreadPool.submit(new Callable(r3, file) {
                private final /* synthetic */ ReportInteraction f$1;
                private final /* synthetic */ File f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final Object call() {
                    return ReportInteractionExecutor.this.lambda$performInteractions$0$ReportInteractionExecutor(this.f$1, this.f$2);
                }
            }));
        }
        boolean z = true;
        for (Future future : arrayList) {
            while (!future.isDone()) {
                try {
                    z &= ((Boolean) future.get()).booleanValue();
                } catch (InterruptedException unused) {
                } catch (ExecutionException unused2) {
                }
            }
        }
        return z;
    }

    public /* synthetic */ Boolean lambda$performInteractions$0$ReportInteractionExecutor(ReportInteraction reportInteraction, File file) throws Exception {
        if (ACRA.DEV_LOGGING) {
            ACRALog aCRALog = ACRA.log;
            String str = ACRA.LOG_TAG;
            aCRALog.d(str, "Calling ReportInteraction of class " + reportInteraction.getClass().getName());
        }
        return Boolean.valueOf(reportInteraction.performInteraction(this.context, this.config, file));
    }
}
