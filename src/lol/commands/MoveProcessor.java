package lol.commands;

import lol.events.EventManager;
import lol.states.MapPosition;
import lol.states.State;
import lol.states.maps.Map;
import lol.states.maps.mapitems.MapItem;
import lol.states.maps.mapitems.Wall;
import lol.states.players.Player;

import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public class MoveProcessor extends CommandProcessor {

    @Override
    public void process(List<String> subCommands) {
        System.out.println(subCommands);
        if (subCommands.size() < 1) return;

        Map map = State.instance.getMap();
        Player player = State.instance.getPlayer();

        String direction = subCommands.get(0);

        MapPosition moveDirection = new MapPosition();
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
    }
}
