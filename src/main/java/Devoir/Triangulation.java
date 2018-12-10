package Devoir;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Triangulation {

    public static void draw(ArrayList<Point> points, ArrayList<Point> env, GraphicsContext graphicsContext) {
        int index;
        ArrayList<Triangle> triangles = new ArrayList<>();

        points.get(points.indexOf(env.get(0))).libre = false;
        for(index = 0; index < env.size()-1; index++) {
            triangles.add(new Triangle(env.get(0), env.get(index), env.get(index+1)));
            points.get(points.indexOf(env.get(index))).libre = false;
            points.get(points.indexOf(env.get(index+1))).libre = false;
        }

        for(index = 0; index < triangles.size(); index++) {
            for(Point point : points) {
                if(point.libre && triangles.get(index).contains(point)) {
                    triangles.add(new Triangle(triangles.get(index).a, triangles.get(index).b, point));
                    triangles.add(new Triangle(triangles.get(index).a, triangles.get(index).c, point));
                    triangles.add(new Triangle(triangles.get(index).b, triangles.get(index).c, point));
                    triangles.remove(index);
                    point.libre = false;
                }
            }
        }

        for(Triangle triangle : triangles) {
            graphicsContext.strokeLine(triangle.a.x, triangle.a.y, triangle.b.x, triangle.b.y);
            graphicsContext.strokeLine(triangle.b.x, triangle.b.y, triangle.c.x, triangle.c.y);
            graphicsContext.strokeLine(triangle.c.x, triangle.c.y, triangle.a.x, triangle.a.y);
        }
    }
}
