import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * Created by totte on 2016-01-08.
 */
public class BallPane extends Pane {

    Ball ball = new Ball(300, 300);

    Button butt = new Button("shoot");

    public BallPane(){
        getChildren().addAll(ball.getBall(), ball.getButton(), ball.getCannon());


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

            animation = new Timeline(new KeyFrame(Duration.millis(10), Event -> run()));
            animation.setCycleCount(Timeline.INDEFINITE);

            x = ball.cannon.endX;
            y = ball.cannon.endY;
            dX = Math.cos(degree * (Math.PI / 180));
            dY = Math.sin(degree * (Math.PI / 180));


            if (degree < 180 && degree < 270){

                dX = dX * -1;

            }

        }

        public void run(){

            x = x + dX;

            setLayoutX(x);

        }

    }
}

