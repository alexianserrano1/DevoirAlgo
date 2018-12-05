package Devoir;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Point> fusion(List<Point> points1, List<Point> points2) {
        List<Point> points = new ArrayList<Point>();
        int index;
        for(index = 0; index < points1.size(); index++)
            points.add(points1.get(index));

        for(index = 0; index < points2.size(); index++)
            points.add(points2.get(index));

        return points;
    }
}
