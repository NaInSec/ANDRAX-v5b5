package android.support.transition;

import android.graphics.Matrix;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

class GhostViewUtils {
    static GhostViewImpl addGhost(View view, ViewGroup viewGroup, Matrix matrix) {
        if (Build.VERSION.SDK_INT >= 21) {
            return GhostViewApi21.addGhost(view, viewGroup, matrix);
        }
        return GhostViewApi14.addGhost(view, viewGroup);
    }

    static void removeGhost(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            GhostViewApi21.removeGhost(view);
        } else {
            GhostViewApi14.removeGhost(view);
        }
    }

    private GhostViewUtils() {
    }
}
