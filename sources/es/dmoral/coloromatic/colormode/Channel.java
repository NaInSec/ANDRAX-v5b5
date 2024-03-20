package es.dmoral.coloromatic.colormode;

public final class Channel {
    private final ColorExtractor extractor;
    private final int max;
    private final int min;
    private final int nameResourceId;
    private int progress = 0;

    public interface ColorExtractor {
        int extract(int i);
    }

    public Channel(int i, int i2, int i3, ColorExtractor colorExtractor) {
        this.nameResourceId = i;
        this.min = i2;
        this.max = i3;
        this.extractor = colorExtractor;
    }

    public Channel(int i, int i2, int i3, int i4, ColorExtractor colorExtractor) {
        this.nameResourceId = i;
        this.min = i2;
        this.max = i3;
        this.extractor = colorExtractor;
        this.progress = i4;
    }

    public int getNameResourceId() {
        return this.nameResourceId;
    }

    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.max;
    }

    public ColorExtractor getExtractor() {
        return this.extractor;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
    }
}
