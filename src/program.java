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

    double x;
    double y;
    int deltaX;
    int deltaY;
    public Enum pressedKey;
    public char ch;

    public boolean goNorth = false;
    public boolean goSouth = false;
    public boolean goWest = false;
    public boolean goEast = false;

    Circle circle;
    private Timeline animation;

    public void start(Stage primaryStage) throws InterruptedException{

        Pane pane = new Pane();

        circle = new Circle();
        circle.setRadius(30);

        x = 300;
        y = 250;
        deltaX = 1;
        deltaY = 1;

        circle.setLayoutX(x);
        circle.setLayoutY(y);

        animation = new Timeline(new KeyFrame(Duration.millis(10), Event -> run()));
        animation.setCycleCount(Timeline.INDEFINITE);

        Button button = new Button("run");

        button.setOnKeyPressed(Event -> {
            switch (Event.getCode()) {
                case UP:    goNorth = true; break;
                case DOWN:  goSouth = true; break;
                case LEFT:  goWest  = true; break;
                case RIGHT: goEast  = true; break;
            }

            //pressedKey = Event.getCode();
            animation.play();
        });

        button.setOnKeyReleased(Event -> {
            switch (Event.getCode()) {
                case UP:
                    goNorth = false;
                    break;
                case DOWN:
                    goSouth = false;
                    break;
                case LEFT:
                    goWest = false;
                    break;
                case RIGHT:
                    goEast = false;
                    break;
            }
        });


        pane.getChildren().addAll(circle, button);

        primaryStage.setScene(new Scene(pane, 600, 600));
        primaryStage.show();
    }

    public void run(){
        if (goNorth) deltaY -= 1;
        if (goSouth) deltaY += 1;
        if (goEast)  deltaX += 1;
        if (goWest)  deltaX -= 1;

        circle.setLayoutX(x+deltaX);
        circle.setLayoutY(y+deltaY);
    }

}
