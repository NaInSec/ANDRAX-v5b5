package android.support.design.shape;

public class TriangleEdgeTreatment extends EdgeTreatment {
    private final boolean inside;
    private final float size;

    public TriangleEdgeTreatment(float f, boolean z) {
        this.size = f;
        this.inside = z;
    }

    public void getEdgePath(float f, float f2, ShapePath shapePath) {
        float f3 = f / 2.0f;
        shapePath.lineTo(f3 - (this.size * f2), 0.0f);
        shapePath.lineTo(f3, (this.inside ? this.size : -this.size) * f2);
        shapePath.lineTo(f3 + (this.size * f2), 0.0f);
        shapePath.lineTo(f, 0.0f);
    }
}
