import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
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
    public Enum pressedKey;
    public char ch;

    private Timeline animation;

    public Circle circle;

    Cannon cannon = new Cannon();
    Button button = new Button("run");

    public boolean goNorth = false;
    public boolean goSouth = false;
    public boolean goWest = false;
    public boolean goEast = false;

    public Ball(int cordX, int cordY){

        circle = new Circle();
        circle.setRadius(30);

        cannon = new Cannon();
        
        x = cordX;
        y = cordY;
        deltaX = 1;
        deltaY = 1;

        circle.setLayoutX(x);
        circle.setLayoutY(y);

        animation = new Timeline(new KeyFrame(Duration.millis(10), Event -> run()));
        animation.setCycleCount(Timeline.INDEFINITE);

        button.setOnKeyPressed(Event -> {
            pressedKey(Event.getCode());
            animation.play();
        });

        button.setOnKeyReleased(Event -> {
            releasedKey(Event.getCode());
        });

    }

    public void run(){

        updateCannon();
        if (goNorth) deltaY -= 1;
        if (goSouth) deltaY += 1;
        if (goEast)  deltaX += 1;
        if (goWest)  deltaX -= 1;

        circle.setLayoutX(x+deltaX);
        circle.setLayoutY(y+deltaY);
    }

    public void pressedKey(KeyCode e){
        switch (e) {
            case UP:    goNorth = true; break;
            case DOWN:  goSouth = true; break;
            case LEFT:  goWest  = true; break;
            case RIGHT: goEast  = true; break;
        }
    }

    public void releasedKey(KeyCode e){
        switch (e) {
            case UP:    goNorth = false; break;
            case DOWN:  goSouth = false; break;
            case LEFT:  goWest  = false; break;
            case RIGHT: goEast  = false; break;
        }
    }

    public Button getButton(){
        return button;
    }

    public Circle getBall(){
        return circle;
    }

    public Line getCannon() { return cannon.getLine(); }

    public void updateCannon(){
        cannon.update(circle.getLayoutX(),circle.getLayoutY());
    }
}
