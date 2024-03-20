package org.acra.file;

import java.io.File;
import java.io.IOException;
import org.acra.data.CrashReportData;
import org.acra.util.IOUtils;
import org.acra.util.StreamReader;
import org.json.JSONException;

public final class CrashReportPersister {
    public CrashReportData load(File file) throws IOException, JSONException {
        return new CrashReportData(new StreamReader(file).read());
    }

    public void store(CrashReportData crashReportData, File file) throws IOException, JSONException {
        IOUtils.writeStringToFile(file, crashReportData.toJSON());
    }
}
