package de.mrapp.android.tabswitcher.model;

import android.os.Bundle;

public interface Restorable {
    void restoreInstanceState(Bundle bundle);

    void saveInstanceState(Bundle bundle);
}
