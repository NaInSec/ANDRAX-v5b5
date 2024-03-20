package android.support.design.shape;

public class ShapePathModel {
    private static final CornerTreatment DEFAULT_CORNER_TREATMENT = new CornerTreatment();
    private static final EdgeTreatment DEFAULT_EDGE_TREATMENT = new EdgeTreatment();
    private EdgeTreatment bottomEdge;
    private CornerTreatment bottomLeftCorner;
    private CornerTreatment bottomRightCorner;
    private EdgeTreatment leftEdge;
    private EdgeTreatment rightEdge;
    private EdgeTreatment topEdge;
    private CornerTreatment topLeftCorner;
    private CornerTreatment topRightCorner;

    public ShapePathModel() {
        CornerTreatment cornerTreatment = DEFAULT_CORNER_TREATMENT;
        this.topLeftCorner = cornerTreatment;
        this.topRightCorner = cornerTreatment;
        this.bottomRightCorner = cornerTreatment;
        this.bottomLeftCorner = cornerTreatment;
        EdgeTreatment edgeTreatment = DEFAULT_EDGE_TREATMENT;
        this.topEdge = edgeTreatment;
        this.rightEdge = edgeTreatment;
        this.bottomEdge = edgeTreatment;
        this.leftEdge = edgeTreatment;
    }

    public void setAllCorners(CornerTreatment cornerTreatment) {
        this.topLeftCorner = cornerTreatment;
        this.topRightCorner = cornerTreatment;
        this.bottomRightCorner = cornerTreatment;
        this.bottomLeftCorner = cornerTreatment;
    }

    public void setAllEdges(EdgeTreatment edgeTreatment) {
        this.leftEdge = edgeTreatment;
        this.topEdge = edgeTreatment;
        this.rightEdge = edgeTreatment;
        this.bottomEdge = edgeTreatment;
    }

    public void setCornerTreatments(CornerTreatment cornerTreatment, CornerTreatment cornerTreatment2, CornerTreatment cornerTreatment3, CornerTreatment cornerTreatment4) {
        this.topLeftCorner = cornerTreatment;
        this.topRightCorner = cornerTreatment2;
        this.bottomRightCorner = cornerTreatment3;
        this.bottomLeftCorner = cornerTreatment4;
    }

    public void setEdgeTreatments(EdgeTreatment edgeTreatment, EdgeTreatment edgeTreatment2, EdgeTreatment edgeTreatment3, EdgeTreatment edgeTreatment4) {
        this.leftEdge = edgeTreatment;
        this.topEdge = edgeTreatment2;
        this.rightEdge = edgeTreatment3;
        this.bottomEdge = edgeTreatment4;
    }

    public CornerTreatment getTopLeftCorner() {
        return this.topLeftCorner;
    }

    public void setTopLeftCorner(CornerTreatment cornerTreatment) {
        this.topLeftCorner = cornerTreatment;
    }

    public CornerTreatment getTopRightCorner() {
        return this.topRightCorner;
    }

    public void setTopRightCorner(CornerTreatment cornerTreatment) {
        this.topRightCorner = cornerTreatment;
    }

    public CornerTreatment getBottomRightCorner() {
        return this.bottomRightCorner;
    }

    public void setBottomRightCorner(CornerTreatment cornerTreatment) {
        this.bottomRightCorner = cornerTreatment;
    }

    public CornerTreatment getBottomLeftCorner() {
        return this.bottomLeftCorner;
    }

    public void setBottomLeftCorner(CornerTreatment cornerTreatment) {
        this.bottomLeftCorner = cornerTreatment;
    }

    public EdgeTreatment getTopEdge() {
        return this.topEdge;
    }

    public void setTopEdge(EdgeTreatment edgeTreatment) {
        this.topEdge = edgeTreatment;
    }

    public EdgeTreatment getRightEdge() {
        return this.rightEdge;
    }

    public void setRightEdge(EdgeTreatment edgeTreatment) {
        this.rightEdge = edgeTreatment;
    }

    public EdgeTreatment getBottomEdge() {
        return this.bottomEdge;
    }

    public void setBottomEdge(EdgeTreatment edgeTreatment) {
        this.bottomEdge = edgeTreatment;
    }

    public EdgeTreatment getLeftEdge() {
        return this.leftEdge;
    }

    public void setLeftEdge(EdgeTreatment edgeTreatment) {
        this.leftEdge = edgeTreatment;
    }
}
