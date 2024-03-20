package org.acra.config;

import android.content.Context;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;
import org.acra.ACRA;
import org.acra.ACRAConstants;
import org.acra.ReportField;
import org.acra.log.ACRALog;
import org.acra.util.StubCreator;

public final class BaseCoreConfigurationBuilder {
    private final List<ConfigurationBuilder> configurationBuilders = new ArrayList();
    private List<Configuration> configurations;
    private final Map<ReportField, Boolean> reportContentChanges = new EnumMap(ReportField.class);

    static /* synthetic */ Object lambda$getPluginConfigurationBuilder$0(Object obj, Method method, Object[] objArr) throws Throwable {
        return obj;
    }

    BaseCoreConfigurationBuilder(Context context) {
        Iterator<S> it = ServiceLoader.load(ConfigurationBuilderFactory.class, getClass().getClassLoader()).iterator();
        while (it.hasNext()) {
            try {
                ConfigurationBuilderFactory configurationBuilderFactory = (ConfigurationBuilderFactory) it.next();
                if (ACRA.DEV_LOGGING) {
                    ACRALog aCRALog = ACRA.log;
                    String str = ACRA.LOG_TAG;
                    aCRALog.d(str, "Discovered and loaded plugin of type " + configurationBuilderFactory.getClass().getSimpleName().replace("BuilderFactory", ""));
                }
                this.configurationBuilders.add(configurationBuilderFactory.create(context));
            } catch (ServiceConfigurationError e) {
                ACRA.log.e(ACRA.LOG_TAG, "Unable to load ConfigurationBuilderFactory", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void preBuild() throws ACRAConfigurationException {
        this.configurations = new ArrayList();
        for (ConfigurationBuilder build : this.configurationBuilders) {
            this.configurations.add(build.build());
        }
    }

    /* access modifiers changed from: package-private */
    public Set<ReportField> transformReportContent(ReportField[] reportFieldArr) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (reportFieldArr.length != 0) {
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Using custom Report Fields");
            }
            linkedHashSet.addAll(Arrays.asList(reportFieldArr));
        } else {
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Using default Report Fields");
            }
            linkedHashSet.addAll(Arrays.asList(ACRAConstants.DEFAULT_REPORT_FIELDS));
        }
        for (Map.Entry next : this.reportContentChanges.entrySet()) {
            if (((Boolean) next.getValue()).booleanValue()) {
                linkedHashSet.add(next.getKey());
            } else {
                linkedHashSet.remove(next.getKey());
            }
        }
        return linkedHashSet;
    }

    public void setReportField(ReportField reportField, boolean z) {
        this.reportContentChanges.put(reportField, Boolean.valueOf(z));
    }

    /* access modifiers changed from: package-private */
    public List<Configuration> pluginConfigurations() {
        return this.configurations;
    }

    public <R extends ConfigurationBuilder> R getPluginConfigurationBuilder(Class<R> cls) {
        Iterator<ConfigurationBuilder> it = this.configurationBuilders.iterator();
        while (it.hasNext()) {
            R r = (ConfigurationBuilder) it.next();
            if (cls.isAssignableFrom(r.getClass())) {
                return r;
            }
        }
        if (cls.isInterface()) {
            ACRALog aCRALog = ACRA.log;
            String str = ACRA.LOG_TAG;
            aCRALog.w(str, "Couldn't find ConfigurationBuilder " + cls.getSimpleName() + ". ALL CALLS TO IT WILL BE IGNORED!");
            return (ConfigurationBuilder) StubCreator.createStub(cls, $$Lambda$BaseCoreConfigurationBuilder$bIkqtXdpCl2MC6p3YJQnSsYOLxY.INSTANCE);
        }
        throw new IllegalArgumentException("Class " + cls.getName() + " is not a registered ConfigurationBuilder");
    }
}
