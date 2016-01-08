import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuModule {

    MenuBar menubar;

    public MenuModule(){
        Menu file = new Menu("File");
        Menu option = new Menu("Option");
        Menu about = new Menu("About");

        MenuItem start = new MenuItem("Start");
        MenuItem quit = new MenuItem("Quit");

        file.getItems().addAll(start, quit);

        menubar = new MenuBar();
        menubar.getMenus().addAll(file, option, about);
    }

    public MenuBar getMenu(){
        return menubar;
    }

}
