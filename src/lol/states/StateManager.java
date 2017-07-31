package lol.states;

import lol.events.EventManager;
import lol.events.EventType;
import lol.inputs.CommandListener;
import lol.states.maps.Map;
import lol.states.players.Player;
import lol.uis.TextScreen;

/**
 * Created by huynq on 8/1/17.
 */
public class StateManager implements CommandListener {
    public static final StateManager instance = new StateManager();

    State state = State.instance;

    Player player = State.instance.getPlayer();

    private StateManager() {

    }

    public void loadInitialMap() {
        Map map = Map.parseFile("assets/maps/lvl1.txt");
        state.setMap(map);
        state.getPlayer().setPosition(
                map.getPlayerStartX(),
                map.getPlayerStartY()
        );
    }

    public void processCommand(String command) {
        String[] commands = command.toUpperCase().split("_");

        if (commands.length < 1) return;

        String mainCommand = commands[0];
        switch (mainCommand) {
            case "MOVE":
                if (commands.length < 2) return;
                String direction = commands[1];
                switch (direction) {
                    case "UP":
                        player.move(0, -1);
                        EventManager.push(EventType.UI_MESSAGE, "You just go ;#00FF00up");
                        break;
                    case "DOWN":
                        player.move(0, 1);
                        EventManager.push(EventType.UI_MESSAGE, "You just go ;#00FF00down");
                        break;
                    case "RIGHT":
                        player.move(1, 0);
                        EventManager.push(EventType.UI_MESSAGE, "You just go ;#00FF00left");
                        break;
                    case "LEFT":
                        player.move(-1, 0);
                        EventManager.push(EventType.UI_MESSAGE, "You just go ;#00FF00right");
                        break;
                }
                System.out.println(player);
                break;
        }
    }

    @Override
    public void onCommandFinished(String command) {
        processCommand(command);
    }

    @Override
    public void commandChanged(String command) {

    }
}
