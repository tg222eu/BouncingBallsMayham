import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * Created by totte on 2016-01-08.
 */
public class BallPane extends Pane {

    Ball ball = new Ball(300, 300);

    public BallPane(){

        Button shoot = new Button("Shoot");
        shoot.setLayoutX(300);

        ball.button.setOnKeyPressed(Event -> {
            pressedKey(Event.getCode());
            ball.animation.play();
        });

        ball.button.setOnKeyReleased(Event -> {
            ball.releasedKey(Event.getCode());
        });

        getChildren().addAll(ball.getBall(), ball.getButton(), ball.getCannon());


    }

    public void pressedKey(KeyCode e){
        switch (e) {
            case UP:    ball.goNorth = true; break;
            case DOWN:  ball.goSouth = true; break;
            case LEFT:  ball.goWest  = true; break;
            case RIGHT: ball.goEast  = true; break;
            case E:     ball.goClockwise = true; break;
            case Q:     ball.goCounterClock = true; break;
            case R:     ball.shootBullet = true; break;
            case W:     shoot();
        }
    }

    public void releasedKey(KeyCode e){
        switch (e) {
            case UP:    ball.goNorth = false; break;
            case DOWN:  ball.goSouth = false; break;
            case LEFT:  ball.goWest  = false; break;
            case RIGHT: ball.goEast  = false; break;
            case E:     ball.goClockwise = false; break;
            case Q:     ball.goCounterClock = false; break;
            case R:     ball.shootBullet = false; break;
        }
    }

    public void shoot(){
        getChildren().add(new Bullet());
    }


    class Bullet extends Circle {

        private Timeline animation;
        private int degree;

        private double x, y;
        private double dX, dY;

        public Bullet(){

            setRadius(5);

            degree = ball.cannon.degree;

            animation = new Timeline(new KeyFrame(Duration.millis(5), Event -> run()));
            animation.setCycleCount(Timeline.INDEFINITE);

            animation.play();

            x = ball.cannon.endX;
            y = ball.cannon.endY;

            dX = Math.cos(degree * (Math.PI / 180));
            dY = Math.sin(degree * (Math.PI / 180));

        }

        public void run(){

            x = x + dX;
            y = y + dY;

            if(x <= 0 || x >= 600){
                dX *= -1;
            }
            if(y <= 0 || y >= 600){
                dY *= -1;
            }

            setLayoutX(x);
            setLayoutY(y);

        }

    }
}

