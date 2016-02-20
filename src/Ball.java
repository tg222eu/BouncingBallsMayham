import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Created by totte on 2016-01-08.
 */
public class Ball extends Group{

    double x;
    double y;
    int deltaX;
    int deltaY;
    double delta2X = 1;
    double delta2Y = 1;
    public Enum pressedKey;
    public char ch;
    public Text healthText = new Text("100");
    public int health = 100;

    public boolean deltaXpositive = true;
    public boolean deltaYpositive= true;
    public int degree = 0;

    public Circle circle = new Circle();

    static boolean gravityMode = false;

    public Circle bullet = new Circle();

    public Timeline animation;

    Cannon cannon = new Cannon();
    Button button = new Button("run");

    public boolean goNorth = false;
    public boolean goSouth = false;
    public boolean goWest = false;
    public boolean goEast = false;
    public boolean goClockwise = false;
    public boolean goCounterClock = false;
    public boolean shootBullet = false;


    public Ball(int cordX, int cordY){

        circle.setRadius(30);
        bullet.setRadius(5);
        bullet.setFill(Color.RED);
        healthText.setText(Integer.toString(health));
        healthText.setFill(Color.RED);

        //cannon = new Cannon();



        x = cordX;
        y = cordY;
        deltaX = 1;
        deltaY = 1;

        getChildren().addAll(cannon);

        circle.setLayoutX(x);
        circle.setLayoutY(y);

        animation = new Timeline(new KeyFrame(Duration.millis(10), Event -> run()));
        animation.setCycleCount(Timeline.INDEFINITE);
        this.animation.play();

    }

    public void run(){

            getHealth().setLayoutX(x - 13);
            getHealth().setLayoutY(y - 35);
            healthText.setText(Integer.toString(health));
            if (!gravityMode) {
                if (goNorth) deltaY = -1;
                if (goSouth) deltaY = 1;
                if (goEast) deltaX = 1;
                if (goWest) deltaX = -1;

                if (goNorth == true || goSouth == true) {
                    y = y + deltaY;
                }
                if (goEast == true || goWest == true) {
                    x = x + deltaX;
                }

                circle.setLayoutX(x);
                circle.setLayoutY(y);

                updateCannon(x + deltaX, y + deltaY, goClockwise, goCounterClock);
            } else {


                delta2X = Math.cos(degree * (Math.PI / 180));
                delta2Y = Math.sin(degree * (Math.PI / 180));

                if (goEast) degree -= 1;
                if (goWest) degree += 1;

                if (x >= 570) {
                    degree = 180-degree;
                    x = 568;
                }

                if (x <= 30) {
                    degree = 180-degree;
                    x = 32;
                }
                if (y >= 570) {
                    degree = 360 - degree;
                    y = 568;
                }
                if (y <= 30) {
                    degree = 360 - degree;
                    y = 32;
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

    public Text getHealth(){
        return healthText;
    }

    public void updateCannon(double x, double y, boolean cw, boolean cc){
        cannon.update(x,y,cw,cc);
    }

}
