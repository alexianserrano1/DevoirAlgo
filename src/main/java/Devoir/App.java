package Devoir;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Generator generator = new Generator(50);
        generator.generate();

        ArrayList<Point> env = new ArrayList<>();
        Convexe convexe = new Convexe();
        convexe.partition(generator.points, env);

        Group root = new Group();
        Canvas canvas = new Canvas(1080, 900);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        for(Point point : generator.points) {
            graphicsContext.fillOval(point.x-2, point.y-2, 2*2, 2*2);
        }
        graphicsContext.setFill(Color.RED);
        int index;
        for(index = 0; index < env.size()-1; index++) {
            graphicsContext.strokeLine(env.get(index).x, env.get(index).y, env.get(index+1).x, env.get(index+1).y);
        }
        graphicsContext.strokeLine(env.get(index).x, env.get(index).y, env.get(0).x, env.get(0).y);

        graphicsContext.setFill(Color.BLACK);
        ArrayList<Triangle> triangles = new ArrayList<>();
        for(index = 1; index < env.size()-1; index++) {
            triangles.add(new Triangle(env.get(0), env.get(index), env.get(index+1)));
        }

        /*boolean test = true;
        index = 0;
        int size = triangles.size();
        while(test) {
            for(Point point : generator.points) {
                if(!env.contains(point)) {
                    test = false;
                    if (triangles.get(index).contains(point)) {
                        triangles.add(new Triangle(triangles.get(index).a, triangles.get(index).b, point));
                        triangles.add(new Triangle(point, triangles.get(index).b, triangles.get(index).c));
                        triangles.add(new Triangle(triangles.get(index).c, point, triangles.get(index).a));

                        triangles.remove(triangles.get(index));
                        size = size + 2;
                        test = true;
                    }
                }
            }
            index = (index+1)%size;
        }*/
        for(Point point : generator.points) {
            if(!env.contains(point)) {
                for(index = 0; !Triangle.allExplored(triangles); index++) {
                    System.out.println(triangles.size() + " "+ index);
                    if(triangles.get(index%triangles.size()).contains(point)) {
                        triangles.get(index%triangles.size()).explored = true;
                        triangles.add(new Triangle(triangles.get(index%triangles.size()).a, triangles.get(index%triangles.size()).b, point));
                        triangles.add(new Triangle(point, triangles.get(index%triangles.size()).b, triangles.get(index%triangles.size()).c));
                        triangles.add(new Triangle(triangles.get(index%triangles.size()).c, point, triangles.get(index%triangles.size()).a));
                        triangles.remove(index%triangles.size());
                    }
                }
            }
        }

        for(Triangle triangle : triangles) {
            graphicsContext.strokeLine(triangle.a.x, triangle.a.y, triangle.b.x, triangle.b.y);
            graphicsContext.strokeLine(triangle.b.x, triangle.b.y, triangle.c.x, triangle.c.y);
            graphicsContext.strokeLine(triangle.c.x, triangle.c.y, triangle.a.x, triangle.a.y);
        }

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
