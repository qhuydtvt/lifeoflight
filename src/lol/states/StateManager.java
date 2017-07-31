package lol.states;

import lol.events.EventManager;
import lol.inputs.CommandListener;
import lol.states.maps.Map;
import lol.states.maps.mapitems.MapItem;
import lol.states.maps.mapitems.Wall;
import lol.states.players.Player;

import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public class StateManager implements CommandListener {
    public static final StateManager instance = new StateManager();

    State state = State.instance;



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

        Map map = state.getMap();

        Player player = state.getPlayer();

        String[] commands = command.toUpperCase().split("_");

        if (commands.length < 1) return;

        String mainCommand = commands[0];
        MapPosition moveDirection = new MapPosition();
        switch (mainCommand) {
            case "MOVE":
                if (commands.length < 2) return;
                String direction = commands[1];
                String message = "";
                switch (direction) {
                    case "UP":
                        moveDirection.y = - 1;
                        message = "You just go ;#00FF00up";
                        break;
                    case "DOWN":
                        moveDirection.y = 1;
                        message = "You just go ;#00FF00down";
                        break;
                    case "RIGHT":
                        moveDirection.x = 1;
                        message = "You just go ;#00FF00right";
                        break;
                    case "LEFT":
                        moveDirection.x = -1;
                        message = "You just go ;#00FF00left";
                        break;
                }

                MapPosition futurePosition = player.getPosition().add(moveDirection);
                MapItem mapItem = map.getMapItem(futurePosition);

                if (mapItem instanceof Wall) {
                    EventManager.pushUIMessage("You just hit the ;#6e7f89wall;, can't move there");
                } else {
                    // TODO: Handle event
                    player.move(moveDirection.x, moveDirection.y);
                    EventManager.pushUIMessage(message);
                }
                break;
            case "LOOK":
                List<List<MapItem>> block = map.getMapItems(player.getPosition(), player.getVision());
                EventManager.pushUIMessage(" ");
                for (int y = 0; y < block.size(); y ++) {
                    StringBuilder rowMessage = new StringBuilder();
                    for(int x = 0; x < block.get(0).size(); x ++) {
                        MapItem item = block.get(y).get(x);
                        if (x == block.size() / 2 && y == block.get(0).size() / 2) {
                            rowMessage.append("@ ");
                        } else if (item == null){
                            rowMessage.append("x ");
                        } else {
                            rowMessage.append(item.getSymbol()).append(" ");
                        }
                    }
                    EventManager.pushUIMessage(rowMessage.toString());
                }
                EventManager.pushUIMessage(" ");
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
