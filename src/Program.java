import lol.GameWindow;
import lol.states.State;
import lol.states.StateManager;
import lol.states.maps.Map;

/**
 * Created by huynq on 7/28/17.
 */
public class Program {
    public static void main(String[] args) {
        StateManager.instance.loadInitialMap();
        System.out.println(State.instance.toString());
        GameWindow gameWindow = new GameWindow();
        gameWindow.gameLoop();
    }
}
