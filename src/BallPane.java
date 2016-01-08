import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * Created by totte on 2016-01-08.
 */
public class BallPane extends Pane {

    Ball ball = new Ball(300, 300);

    public BallPane(){
        getChildren().addAll(ball.getBall(), ball.getButton(), ball.getCannon());
    }

    public void shoot(){
        getChildren().add(new Bullet());
    }

    class Bullet extends Circle {

    }
}

