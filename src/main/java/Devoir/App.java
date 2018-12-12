package Devoir;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Generator generator = new Generator(1000);
        generator.generate();

        ArrayList<Point> env = new ArrayList<>();
        Convexe convexe = new Convexe();
        convexe.makeConvex(generator.points, env);

        Group root = new Group();
        Canvas canvas = new Canvas(1080, 900);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        Point.draw(generator.points, graphicsContext);
        convexe.draw(env, graphicsContext);

        //Triangulation.draw(generator.points, env, graphicsContext);

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
