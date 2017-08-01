import lol.GameWindow;
import lol.gameevents.GameEventManager;

/**
 * Created by huynq on 7/28/17.
 */
public class Program {
    public static void main(String[] args) {
        GameEventManager.instance.loadData();
        GameWindow gameWindow = new GameWindow();
        gameWindow.gameLoop();
    }
}
