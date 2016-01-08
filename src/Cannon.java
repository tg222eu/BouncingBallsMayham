import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Created by benjamin on 2016-01-08.
 */
public class Cannon {

    private Line line;


    public Cannon(){

        line = new Line();

    }

    public void update(double x, double y){

        line.setStroke(Color.GRAY);

        line.setStartX(x);
        line.setStartY(y);

        line.setEndY(y+40);
        line.setEndX(x+40);
    }

    public Line getLine() { return line; }

}