package arc.zad2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Arc extends Application {
    @Override
    public void start(Stage stage) {
        Group group = new Group();
        final double SCENE_SIZE = 360;

        Scene scene = new Scene(group, SCENE_SIZE, SCENE_SIZE);

        Line line;
        Circle circle;

        double startLineX = 0;
        double startLineY = 0;
        double endLineX;
        double endLineY = SCENE_SIZE;

        for(endLineX = 0; endLineX <= SCENE_SIZE; endLineX += 20)
        {
            line = new Line(startLineX, startLineY, endLineX, endLineY);
            line.setStroke(Color.BLUE);
            line.setStrokeWidth(1.5);

            group.getChildren().add(line);
        }

        startLineX = SCENE_SIZE;
        startLineY = SCENE_SIZE;
        endLineX = 0;

        for(endLineY = 0; endLineY <= SCENE_SIZE; endLineY += 20)
        {
            line = new Line(startLineX, startLineY, endLineX, endLineY);
            line.setStroke(Color.BLUE);
            line.setStrokeWidth(1.5);

            group.getChildren().add(line);
        }

        circle = new Circle(SCENE_SIZE, 0, SCENE_SIZE);
        circle.setStroke(Color.BLUE);
        circle.setStrokeWidth(1.5);
        circle.setFill(Color.WHITE);

        group.getChildren().add(circle);

        stage.setTitle("Draw mesh");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}