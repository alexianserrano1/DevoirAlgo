package Devoir;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.Collections;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Generator generator = new Generator(50);
        generator.generate();
        Collections.sort(generator.points);

        EnveloppeConvexe convexe = new EnveloppeConvexe(generator.points);
        convexe.createConvexe();

        Group root = new Group();
        Canvas canvas = new Canvas(760, 500);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        for(Point point : convexe.points) {
            graphicsContext.fillOval(point.x-1, point.y-1, 1*2, 1*2);
        }

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
