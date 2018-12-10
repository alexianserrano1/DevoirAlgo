package Devoir;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Generator generator = new Generator(50);
        generator.generate();
        /*Collections.sort(generator.points);

        EnveloppeConvexe convexe = new EnveloppeConvexe(generator.points);
        convexe.partition(convexe.points, convexe.pointsConv);
        System.out.println("Fin de l'algo");*/

        ArrayList<Point> env = new ArrayList<>();
        Convexe convexe = new Convexe();
        convexe.partition(generator.points, env);

        Group root = new Group();
        Canvas canvas = new Canvas(760, 500);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        for(Point point : generator.points) {
            graphicsContext.fillOval(point.x-1, point.y-1, 1*2, 1*2);
        }
        graphicsContext.setFill(Color.AQUAMARINE);
        for(int index = 0; index < env.size()-1; index++) {
            graphicsContext.moveTo(env.get(index).x, env.get(index).y);
            graphicsContext.lineTo(env.get(index+1).x, env.get(index).y);
            graphicsContext.stroke();
        }

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
