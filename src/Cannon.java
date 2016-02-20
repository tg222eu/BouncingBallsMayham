import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Created by benjamin on 2016-02-20.
 */
public class Cannon extends Group{

    public ArrayList<Ball> ballList = new ArrayList<>();
    static ArrayList<Bullet> bull = new ArrayList<>();

    private Line line;
    public int degree;
    public double endX;
    public double endY;



    public Cannon(){

        line = new Line();
        getChildren().addAll(line);
    }
    public void update(double x, double y, boolean cw, boolean cc){

        line.setStroke(Color.GRAY);

        line.setStartX(x);
        line.setStartY(y);



        if (cw){
            degree = degree + 1;

            if(degree == 360){
                degree = 1;
            }

        }
        else if (cc){
            degree = degree - 1;

            if (degree == 0){
                degree = 359;

            }

        }

        endX = x + 40 * Math.cos(degree * (Math.PI / 180));
        endY = y + 40 * Math.sin(degree * (Math.PI / 180));

        line.setEndX(endX);
        line.setEndY(endY);
    }

    public Line getLine() { return line; }

    public int getDegree() { return degree; }

    public void shoot(){


        if (bull.size() < 20){

            bull.add(new Bullet(degree,endX,endY));


            getChildren().add(bull.get(bull.size()-1));

        }

    }
    public void removeBullet(Bullet bullet){

        getChildren().removeAll(bullet);
        bull.remove(bullet);

    }
}
