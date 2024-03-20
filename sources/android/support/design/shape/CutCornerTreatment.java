package android.support.design.shape;

public class CutCornerTreatment extends CornerTreatment {
    private final float size;

    public CutCornerTreatment(float f) {
        this.size = f;
    }

    public void getCornerPath(float f, float f2, ShapePath shapePath) {
        shapePath.reset(0.0f, this.size * f2);
        double d = (double) f;
        double d2 = (double) f2;
        shapePath.lineTo((float) (Math.sin(d) * ((double) this.size) * d2), (float) (Math.cos(d) * ((double) this.size) * d2));
    }
}
