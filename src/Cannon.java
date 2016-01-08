import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Created by benjamin on 2016-01-08.
 */
public class Cannon {

    private Line line;
    public int degree;


    public Cannon(){

        line = new Line();

    }

    public void update(double x, double y){

        line.setStroke(Color.GRAY);

        line.setStartX(x);
        line.setStartY(y);

        degree = degree + 1;

        if(degree == 360){
            degree = 1;
        }

        double endX = x + 40 * Math.cos(degree * (Math.PI / 180));
        double endY = y + 40 * Math.sin(degree * (Math.PI / 180));

        line.setEndX(endX);
        line.setEndY(endY);
    }

    public Line getLine() { return line; }

}