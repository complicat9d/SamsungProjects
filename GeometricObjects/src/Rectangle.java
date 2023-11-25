public class Rectangle extends GeometryObjects{
    double a, b;

    @Override
    void setScale(double x) {
        this.a *= x;
        this.b *= x;
        square = getSquare();
    }

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
        square = getSquare();
    }
    @Override
    double getSquare() {
        return this.a * this.b;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "a=" + a +
                ", b=" + b +
                ", square=" + square +
                '}';
    }
}
