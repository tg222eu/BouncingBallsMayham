import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Time;

/**
 * Created by totte on 2016-01-07.
 */
public class program extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws InterruptedException{

        Pane pane = new Pane();

        Ball ball = new Ball();



        pane.getChildren().addAll(ball.getBall());

        primaryStage.setScene(new Scene(pane, 600, 600));
        primaryStage.show();
    }

}
