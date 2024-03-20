package org.acra.legacy;

import java.io.File;
import java.io.FilenameFilter;
import org.acra.ACRAConstants;

/* renamed from: org.acra.legacy.-$$Lambda$ReportMigrator$yKiW9Ll1HFwaZ9CdauG8grunHA0  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$ReportMigrator$yKiW9Ll1HFwaZ9CdauG8grunHA0 implements FilenameFilter {
    public static final /* synthetic */ $$Lambda$ReportMigrator$yKiW9Ll1HFwaZ9CdauG8grunHA0 INSTANCE = new $$Lambda$ReportMigrator$yKiW9Ll1HFwaZ9CdauG8grunHA0();

    private /* synthetic */ $$Lambda$ReportMigrator$yKiW9Ll1HFwaZ9CdauG8grunHA0() {
    }

    public final boolean accept(File file, String str) {
        return str.endsWith(ACRAConstants.REPORTFILE_EXTENSION);
    }
}
