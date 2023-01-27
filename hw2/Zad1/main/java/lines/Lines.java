package lines;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;

public class Lines extends Application {
    @Override
    public void start(Stage stage) {
        Group group = new Group();
        final double  SCENE_SIZE = 300;
        Scene scene = new Scene(group, SCENE_SIZE, SCENE_SIZE);
        Line line;
        double height;

        for(double width = 0; width <= SCENE_SIZE; width += 30) {
            height = SCENE_SIZE - width;

            line = new Line(0, 0, width, height);
            line.setStroke(Color.BLUE);
            line.setStrokeWidth(2);

            group.getChildren().add(line);
        }
        stage.setTitle("Draw mesh");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}