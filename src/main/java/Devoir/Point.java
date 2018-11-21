package Devoir;

public class Point implements Comparable{
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Object o) {
        Point point = (Point) o;

        if(point.x > this.x)
            return -1;
        else if(point.x < this.x)
            return 1;
        else
            return 0;
    }
}
