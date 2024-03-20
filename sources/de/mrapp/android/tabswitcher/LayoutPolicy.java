package de.mrapp.android.tabswitcher;

public enum LayoutPolicy {
    AUTO(0),
    PHONE(1),
    TABLET(2);
    
    private int value;

    private LayoutPolicy(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }

    public static LayoutPolicy fromValue(int i) {
        for (LayoutPolicy layoutPolicy : values()) {
            if (layoutPolicy.getValue() == i) {
                return layoutPolicy;
            }
        }
        throw new IllegalArgumentException("Invalid enum value: " + i);
    }
}
