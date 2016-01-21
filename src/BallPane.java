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

        ball.circle.setOnKeyPressed(Event -> {
            pressedKey(Event.getCode());
        });

        ball.circle.setOnKeyReleased(Event -> {
            releasedKey(Event.getCode());
        });

        //ball2.playBall(ball2);

        getChildren().addAll(ball.getBall(), ball.getCannon(), ball.getHealth(), ball2.getBall(), ball2.getCannon(), ball2.getHealth());

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

            //int calcX = (int)(Math.sqrt(Math.pow(ball.x, 2) - Math.pow(x, 2)));
            //int calcY = (int)(Math.sqrt(Math.pow(ball.y, 2) - Math.pow(y, 2)));

            double calcX = ball.x - x;
            double calcY = ball.y - y;

            if(calcX < 0){
                calcX *= -1;
            }
            if(calcY < 0){
                calcY *= -1;
            }

            if(calcX < 30 && calcY < 30){
                ball.health -= 10;
                removeBullet(bull.indexOf(this));
                animation.stop();
            }

            if (distance>800){
                removeBullet(bull.indexOf(this));
                animation.stop();
            }

            setLayoutX(x);
            setLayoutY(y);

        }
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
            case O:     shoot(); break;
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

