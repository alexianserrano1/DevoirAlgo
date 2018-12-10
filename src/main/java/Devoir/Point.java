package Devoir;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Point implements Comparable{
    double x, y;
    boolean libre;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        libre = true;
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

    public static void draw(ArrayList<Point> points, GraphicsContext graphicsContext) {
        for(Point point : points) {
            graphicsContext.fillOval(point.x-2, point.y-2, 2*2, 2*2);
        }
    }
}
