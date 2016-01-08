import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 * Created by totte on 2016-01-08.
 */
public class Ball {

    double x;
    double y;
    int deltaX;
    int deltaY;
    double delta2X = 1;
    double delta2Y = 1;
    public Enum pressedKey;
    public char ch;

    public boolean gravityMode = false;

    public Circle bullet = new Circle();

    public Timeline animation;

    public Circle circle;

    Cannon cannon = new Cannon();

    public boolean goNorth = false;
    public boolean goSouth = false;
    public boolean goWest = false;
    public boolean goEast = false;
    public boolean goClockwise = false;
    public boolean goCounterClock = false;
    public boolean shootBullet = false;


    public Ball(int cordX, int cordY){

        circle = new Circle();
        circle.setRadius(30);
        bullet.setRadius(5);
        bullet.setFill(Color.RED);

        cannon = new Cannon();
        
        x = cordX;
        y = cordY;
        deltaX = 1;
        deltaY = 1;

        circle.setLayoutX(x);
        circle.setLayoutY(y);

        animation = new Timeline(new KeyFrame(Duration.millis(10), Event -> run()));
        animation.setCycleCount(Timeline.INDEFINITE);


    }

    public void run(){
        if(!gravityMode) {
            if (goNorth) deltaY = -1;
            if (goSouth) deltaY = 1;
            if (goEast) deltaX = 1;
            if (goWest) deltaX = -1;

            if(goNorth == true || goSouth == true) {
                y = y + deltaY;
            }
            if(goEast == true || goWest == true){
                x = x + deltaX;
            }

            circle.setLayoutX(x);
            circle.setLayoutY(y);

            updateCannon(x + deltaX, y + deltaY, goClockwise, goCounterClock);
        }else{
            if (goNorth && delta2Y > -1) delta2Y -= 0.01;
            if (goSouth && delta2Y < 1.01) delta2Y += 0.01;
            if (goEast && delta2X < 1.01) delta2X += 0.01;
            if (goWest && delta2X > -1) delta2X -= 0.01;

            if(x <= 0 || x >= 600){
                delta2X *= -1;
            }
            if(y <= 0 || y >= 600){
                delta2Y *= -1;
            }

            x = x + delta2X;
            y = y + delta2Y;
            circle.setLayoutX(x);
            circle.setLayoutY(y);
            updateCannon(x, y, goClockwise, goCounterClock);
        }
    }

    public Circle getBall(){
        return circle;
    }

    public Line getCannon() { return cannon.getLine(); }

    public void updateCannon(double x, double y, boolean cw, boolean cc){
        cannon.update(x,y,cw,cc);
    }
}
