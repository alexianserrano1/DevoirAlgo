package Devoir;

import org.junit.Test;

public class TriangleTest {

    @Test
    public void containsTest() {
        Point a = new Point(3.0, 7.0);
        Point b = new Point(7.0, 1.0);
        Point c = new Point(10.0, 7.0);
        Triangle triangle = new Triangle(a, b, c);
        Point m = new Point(3.0, 7.0);

        if (triangle.contains(m))
            System.out.println("Contenu");
        else
            System.out.println("Pas dedans");
    }
}
