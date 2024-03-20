package de.mrapp.android.util.logging;

public enum LogLevel {
    ALL(0),
    VERBOSE(1),
    DEBUG(2),
    INFO(3),
    WARN(4),
    ERROR(5),
    OFF(6);
    
    private int rank;

    private LogLevel(int i) {
        this.rank = i;
    }

    public final int getRank() {
        return this.rank;
    }

    public static LogLevel fromRank(int i) {
        switch (i) {
            case 0:
                return ALL;
            case 1:
                return VERBOSE;
            case 2:
                return DEBUG;
            case 3:
                return INFO;
            case 4:
                return WARN;
            case 5:
                return ERROR;
            case 6:
                return OFF;
            default:
                throw new IllegalArgumentException();
        }
    }
}
