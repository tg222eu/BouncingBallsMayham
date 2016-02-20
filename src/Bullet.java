import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * Created by benjamin on 2016-02-20.
 */
class Bullet extends Circle {

    private Timeline animation;
    private int degree;

    private double x, y;
    private double dX, dY;

    private int distance;

    public Bullet(int degree, double x, double y){

        setRadius(5);


        this.degree = degree;

        animation = new Timeline(new KeyFrame(Duration.millis(5), Event -> run()));
        animation.setCycleCount(Timeline.INDEFINITE);

        animation.play();

        this.x = x;//ball.cannon.endX;
        this.y = y;//ball.cannon.endY;

        dX = Math.cos(degree * (Math.PI / 180));
        dY = Math.sin(degree * (Math.PI / 180));

        System.out.println("DELTA X:"+dX);
        System.out.println("DELTA y:"+dY);




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

        /*
        if (x==ball.x){

        }
*/
        distance++;

        //int calcX = (int)(Math.sqrt(Math.pow(ball.x, 2) - Math.pow(x, 2)));
        //int calcY = (int)(Math.sqrt(Math.pow(ball.y, 2) - Math.pow(y, 2)));
/*
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
*/
        if (distance>800){
            //Ball.cannon.removeBullet(this);

            animation.stop();
        }

        setLayoutX(x);
        setLayoutY(y);

    }
}