package Devoir;

import javafx.geometry.Point2D;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EnveloppeConvexeTest {

    @Test
    public void testTri() {
        Point point1 = new Point(3.5, 8.2);
        Point point3 = new Point(13.8, 4);
        Point point2 = new Point(5, 2.7);

        Point[] points = new Point[3];
        points[0] = point1;
        points[1] = point3;
        points[2] = point2;


        EnveloppeConvexe Conv = new EnveloppeConvexe(points);
        Conv.sortX();
        for(Point point : Conv.points)
            System.out.println(point.x+" "+point.y+" ");
    }


}