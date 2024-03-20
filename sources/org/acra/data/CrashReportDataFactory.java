package org.acra.data;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.acra.ACRA;
import org.acra.builder.ReportBuilder;
import org.acra.collector.ApplicationStartupCollector;
import org.acra.collector.Collector;
import org.acra.collector.CollectorException;
import org.acra.config.CoreConfiguration;
import org.acra.log.ACRALog;

public final class CrashReportDataFactory {
    private final List<Collector> collectors = new ArrayList();
    private final CoreConfiguration config;
    private final Context context;

    public CrashReportDataFactory(Context context2, CoreConfiguration coreConfiguration) {
        this.context = context2;
        this.config = coreConfiguration;
        Iterator<S> it = ServiceLoader.load(Collector.class, getClass().getClassLoader()).iterator();
        while (it.hasNext()) {
            try {
                Collector collector = (Collector) it.next();
                if (ACRA.DEV_LOGGING) {
                    ACRALog aCRALog = ACRA.log;
                    String str = ACRA.LOG_TAG;
                    aCRALog.d(str, "Loaded collector of class " + collector.getClass().getName());
                }
                this.collectors.add(collector);
            } catch (ServiceConfigurationError e) {
                ACRA.log.e(ACRA.LOG_TAG, "Unable to load collector", e);
            }
        }
        Collections.sort(this.collectors, $$Lambda$CrashReportDataFactory$w4ZRNFc7G4q3tBC1_4dG1ejRHE.INSTANCE);
    }

    static /* synthetic */ int lambda$new$0(Collector collector, Collector collector2) {
        Collector.Order order;
        Collector.Order order2;
        try {
            order = collector.getOrder();
        } catch (Throwable unused) {
            order = Collector.Order.NORMAL;
        }
        try {
            order2 = collector2.getOrder();
        } catch (Throwable unused2) {
            order2 = Collector.Order.NORMAL;
        }
        return order.ordinal() - order2.ordinal();
    }

    public CrashReportData createCrashData(ReportBuilder reportBuilder) {
        ExecutorService newCachedThreadPool = this.config.parallel() ? Executors.newCachedThreadPool() : Executors.newSingleThreadExecutor();
        CrashReportData crashReportData = new CrashReportData();
        ArrayList<Future> arrayList = new ArrayList<>();
        for (Collector r4 : this.collectors) {
            arrayList.add(newCachedThreadPool.submit(new Runnable(r4, reportBuilder, crashReportData) {
                private final /* synthetic */ Collector f$1;
                private final /* synthetic */ ReportBuilder f$2;
                private final /* synthetic */ CrashReportData f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    CrashReportDataFactory.this.lambda$createCrashData$1$CrashReportDataFactory(this.f$1, this.f$2, this.f$3);
                }
            }));
        }
        for (Future future : arrayList) {
            while (!future.isDone()) {
                try {
                    future.get();
                } catch (InterruptedException unused) {
                } catch (ExecutionException unused2) {
                }
            }
        }
        return crashReportData;
    }

    public /* synthetic */ void lambda$createCrashData$1$CrashReportDataFactory(Collector collector, ReportBuilder reportBuilder, CrashReportData crashReportData) {
        try {
            if (ACRA.DEV_LOGGING) {
                ACRALog aCRALog = ACRA.log;
                String str = ACRA.LOG_TAG;
                aCRALog.d(str, "Calling collector " + collector.getClass().getName());
            }
            collector.collect(this.context, this.config, reportBuilder, crashReportData);
            if (ACRA.DEV_LOGGING) {
                ACRALog aCRALog2 = ACRA.log;
                String str2 = ACRA.LOG_TAG;
                aCRALog2.d(str2, "Collector " + collector.getClass().getName() + " completed");
            }
        } catch (CollectorException e) {
            ACRA.log.w(ACRA.LOG_TAG, (Throwable) e);
        } catch (Throwable th) {
            ACRALog aCRALog3 = ACRA.log;
            String str3 = ACRA.LOG_TAG;
            aCRALog3.e(str3, "Error in collector " + collector.getClass().getSimpleName(), th);
        }
    }

    public void collectStartUp() {
        for (Collector next : this.collectors) {
            if (next instanceof ApplicationStartupCollector) {
                try {
                    ((ApplicationStartupCollector) next).collectApplicationStartUp(this.context, this.config);
                } catch (Throwable th) {
                    ACRALog aCRALog = ACRA.log;
                    String str = ACRA.LOG_TAG;
                    aCRALog.w(str, next.getClass().getSimpleName() + " failed to collect its startup data", th);
                }
            }
        }
    }
}
