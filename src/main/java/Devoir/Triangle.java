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

    double sign (Point p1, Point p2, Point p3) {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
    }

    boolean contains (Point pt) {
        double d1, d2, d3;
        boolean has_neg, has_pos;

        d1 = sign(pt, a, b);
        d2 = sign(pt, b, c);
        d3 = sign(pt, c, a);

        has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
        has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);

        return !(has_neg && has_pos);
    }
}
