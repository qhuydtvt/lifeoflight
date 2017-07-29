import lol.GameWindow;
import lol.states.maps.Map;

/**
 * Created by huynq on 7/28/17.
 */
public class Program {
    public static void main(String[] args) {
        testMap();
        GameWindow gameWindow = new GameWindow();
        gameWindow.gameLoop();
    }

    private static void loadMap() {

    }

    private static void testMap() {
        Map map = Map.parseFile("assets/maps/lvl1.txt");
        assert map != null;
        System.out.println(map);
    }
}
