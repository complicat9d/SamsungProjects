public class Circle extends GeometryObjects{
    double r;
    public Circle (double r) {
        this.r = r;
        square = getSquare();
    }

    @Override
    void setScale(double x) {
        this.r *= x;
        square = getSquare();
    }

    @Override
    double getSquare() {
        return Math.PI*Math.pow(this.r, 2);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "r=" + r +
                ", square=" + square +
                '}';
    }
}
