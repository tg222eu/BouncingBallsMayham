import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.concurrent.ExecutorService;

import java.util.ArrayList;
import java.util.concurrent.Executors;

/**
 * Created by totte on 2016-01-08.
 */
public class BallPane extends Pane {

    public ArrayList<Ball> ballList = new ArrayList<>();

    Ball ball = new Ball(500, 300);
    Ball ball2 = new Ball(100, 300);

    ArrayList<Bullet> bull = new ArrayList<>();

    public BallPane(){

        Button mode = new Button("Mode");

        ball.setOnKeyPressed(Event -> {
            pressedKey(Event.getCode());
        });

        ball.setOnKeyReleased(Event -> {
            releasedKey(Event.getCode());
        });



        //ball2.playBall(ball2);

        getChildren().addAll(ball.getBall(), ball.getHealth(), ball2.getBall(), ball2.getHealth(),ball, ball2);

    }

    public void pressedKey(KeyCode e){
        switch (e) {
            case UP:    ball.goNorth = true; break;
            case DOWN:  ball.goSouth = true; break;
            case LEFT:  ball.goWest  = true; break;
            case RIGHT: ball.goEast  = true; break;
            case P:     ball.goClockwise = true; break;
            case I:     ball.goCounterClock = true; break;
            case W:     ball2.goNorth = true; break;
            case S:     ball2.goSouth = true; break;
            case A:     ball2.goWest  = true; break;
            case D:     ball2.goEast  = true; break;
            case O:     ball.cannon.shoot(); break;
            case M:     ball.gravityMode = false; break;
            case N:     ball.gravityMode = true; break;
        }
    }

    public void releasedKey(KeyCode e){
        switch (e) {
            case UP:    ball.goNorth = false; break;
            case DOWN:  ball.goSouth = false; break;
            case LEFT:  ball.goWest  = false; break;
            case RIGHT: ball.goEast  = false; break;
            case P:     ball.goClockwise = false; break;
            case I:     ball.goCounterClock = false; break;
            case O:     ball.shootBullet = false; break;

            case W:    ball2.goNorth = false; break;
            case S:    ball2.goSouth = false; break;
            case A:    ball2.goWest  = false; break;
            case D:    ball2.goEast  = false; break;
        }
    }
}

