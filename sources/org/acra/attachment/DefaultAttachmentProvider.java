package org.acra.attachment;

import android.content.Context;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.acra.ACRA;
import org.acra.config.CoreConfiguration;
import org.acra.log.ACRALog;

public class DefaultAttachmentProvider implements AttachmentUriProvider {
    public List<Uri> getAttachments(Context context, CoreConfiguration coreConfiguration) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = coreConfiguration.attachmentUris().iterator();
        while (it.hasNext()) {
            String next = it.next();
            try {
                arrayList.add(Uri.parse(next));
            } catch (Exception e) {
                ACRALog aCRALog = ACRA.log;
                String str = ACRA.LOG_TAG;
                aCRALog.e(str, "Failed to parse Uri " + next, e);
            }
        }
        return arrayList;
    }
}
