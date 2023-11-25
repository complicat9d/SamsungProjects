import static java.lang.Math.sqrt;

public class Triangle extends GeometryObjects{
    double a, b, c;
    public Triangle (double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        square = getSquare();
    }

    @Override
    void setScale(double x) {
        this.a *= x;
        this.b *= x;
        this.c *= x;
        square = getSquare();
    }

    @Override
    double getSquare() {
        double p = (this.a + this.b + this.c)/2;
        return sqrt((p - this.a)*(p - this.b)*(p - this.c)*p);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", square=" + square +
                '}';
    }
}
