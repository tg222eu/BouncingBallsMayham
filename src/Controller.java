/**
 * Created by totte on 2016-01-08.
 */
public class Controller {

    BallPane pane;

    public Controller(){
        pane = new BallPane();

    }

    public void shoot(){
        //pane.createBullet();
    }

    public BallPane getPane(){
        return pane;
    }

}
