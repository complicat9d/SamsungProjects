abstract public class GeometryObjects implements Comparable<GeometryObjects>{
    double square;
    abstract double getSquare();
    abstract void setScale(double x);
    @Override
    public int compareTo(GeometryObjects o) {
        if (this.square > o.square) {
            return 1;
        } else if (this.square < o.square) {
            return -1;
        }
        return 0;
    }
}
