package lol.gameevents.commands;

import lol.events.EventManager;
import lol.gameevents.CombatEvent;
import lol.gameevents.GameEvent;
import lol.gameentities.MapPosition;
import lol.gameentities.State;
import lol.gameentities.maps.Map;
import lol.gameentities.maps.mapitems.MapItem;
import lol.gameentities.maps.mapitems.Wall;
import lol.gameentities.players.Player;

import java.util.List;
import java.util.Random;

/**
 * Created by huynq on 8/1/17.
 */
public class MoveProcessor extends CommandProcessor {

    Random random = new Random();

    @Override
    public GameEvent process(List<String> subCommands, GameEvent currentEvent) {
        if (subCommands.size() < 1) return null;

        Map map = State.instance.getMap();
        Player player = State.instance.getPlayer();

        String direction = subCommands.get(0);
        MapPosition moveDirection = new MapPosition();
        StringBuilder message = new StringBuilder();

        extractDirection(direction, moveDirection, message);

        MapPosition futurePosition = player.getPosition().add(moveDirection);
        MapItem mapItem = map.getMapItem(futurePosition);

        if (mapItem instanceof Wall) {
            EventManager.pushUIMessage("You just hit the ;#6e7f89wall;, can't move there");
        } else {
            player.move(moveDirection.x, moveDirection.y);
            EventManager.pushUIMessage(message.toString());
            return generateRandomEvent();
        }
        return null;
    }

    //TODO: Change combat possibilities here
    private GameEvent generateRandomEvent() {
        return random.nextInt(100) > 50 ? new CombatEvent() : null;
    }

    private void extractDirection(String direction, final MapPosition moveDirection, final StringBuilder message) {
        switch (direction) {
            case "UP":
                moveDirection.y = - 1;
                message.append("You just go ;#00FF00up");
                break;
            case "DOWN":
                moveDirection.y = 1;
                message.append("You just go ;#00FF00down");
                break;
            case "RIGHT":
                moveDirection.x = 1;
                message.append("You just go ;#00FF00right");
                break;
            case "LEFT":
                moveDirection.x = -1;
                message.append("You just go ;#00FF00left");
                break;
        }
    }
}
