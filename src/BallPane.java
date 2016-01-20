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

/**
 * Created by totte on 2016-01-08.
 */
public class BallPane extends Pane {

    Ball ball = new Ball(300, 300);
    
    //Bullet[] bull = new Bullet[20];

    ArrayList<Bullet> bull = new ArrayList<>();

    public BallPane(){

        Button mode = new Button("Mode");

        ball.circle.setOnKeyPressed(Event -> {
            pressedKey(Event.getCode());
            ball.animation.play();
        });

        ball.circle.setOnKeyReleased(Event -> {
            releasedKey(Event.getCode());
        });

        getChildren().addAll(ball.getBall(), ball.getCannon(), ball.getHealth());

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
            case W:     shoot(); break;
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
            case E:     ball.goClockwise = false; break;
            case Q:     ball.goCounterClock = false; break;
            case R:     ball.shootBullet = false; break;
        }
    }

    public void shoot(){


        if (bull.size() < 20){

            bull.add(new Bullet());

            getChildren().add(bull.get(bull.size()-1));

        }

    }
    public void removeBullet(int n){

        getChildren().removeAll(bull.get(n));
        bull.remove(n);

    }


    class Bullet extends Circle {

        private Timeline animation;
        private int degree;

        private double x, y;
        private double dX, dY;

        private int distance;

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

        public int getDistance(){
            return distance;
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

            distance++;

            if (distance>800){
                removeBullet(bull.indexOf(this));
                animation.stop();
            }

            setLayoutX(x);
            setLayoutY(y);

        }
    }
}

