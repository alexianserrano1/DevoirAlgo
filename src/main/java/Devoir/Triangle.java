package Devoir;

import org.omg.PortableServer.POA;

import java.util.ArrayList;

public class Triangle {
    Point a, b, c;
    boolean explored;

    Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
        explored = false;
    }

    double area(Point a, Point b, Point c) {
        return Math.abs((a.x*(b.y-c.y) + b.x*(c.y-a.y) + c.x*(a.y-b.y))/2.0);
    }

    public boolean contains(Point x) {
        double A = area(a, b, c);
        double A1 = area(x, b, c);
        double A2 = area(a, x, c);
        double A3 = area(a, b, x);

        return (A==A1+A2+A3);
    }

    public static boolean allExplored(ArrayList<Triangle> triangles) {
        for(Triangle triangle : triangles)
            if(!triangle.explored)
                return false;
        return true;
    }
}
