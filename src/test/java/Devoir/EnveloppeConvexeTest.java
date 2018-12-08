package Devoir;

//import javafx.geometry.Point2D;
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

        List<Point> points = new ArrayList<Point>();
        points.add(point1);
        points.add(point3);
        points.add(point2);

        EnveloppeConvexe Conv = new EnveloppeConvexe(points);
        Conv.sortX();
        for(Point point : Conv.points)
            System.out.println(point.x+" "+point.y+" ");
    }


}