package lol.gameevents.processors.main;

import lol.events.EventManager;
import lol.gameentities.maps.mapitems.*;
import lol.gameevents.CombatEvent;
import lol.gameevents.GameEvent;
import lol.gameentities.MapPosition;
import lol.gameentities.State;
import lol.gameentities.maps.Map;
import lol.gameentities.players.Player;
import lol.gameevents.processors.Processor;
import lol.gameevents.processors.maps.ExitEventProcessor;
import lol.gameevents.processors.maps.MainItemProcessor;
import lol.gameevents.processors.world.WorldEventProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static lol.gameentities.maps.mapitems.MapItemType.*;

/**
 * Created by huynq on 8/1/17.
 */
public class MoveProcessor extends Processor {

    private Random random = new Random();

    HashMap<MapItemType, Processor> newPositionProcessors = new HashMap<MapItemType, Processor>() {{
       put(MAIN, new MainItemProcessor());
       put(EXIT, new ExitEventProcessor());
       put(EVENT, new WorldEventProcessor());
    }};

    @Override
    public GameEvent process(List<String> subCommands, GameEvent currentEvent) {
        if (subCommands.size() < 1) return null;

        Map map = State.instance.getMap();
        Player player = State.instance.getPlayer();

        String direction = subCommands.get(0);
        MapPosition moveDirection = new MapPosition();
        StringBuilder message = new StringBuilder();

        extractDirection(direction, moveDirection, message);

        MapPosition futurePosition = player.mapPosition.add(moveDirection);
        MapItem mapItem = map.getMapItem(futurePosition);
        if (mapItem == null) {
            EventManager.pushUIMessage(";#6B2083Không gian vô định;, bạn không thể đi ");
        }
        else if (mapItem.getType() == WALL) {
            EventManager.pushUIMessage("Bạn vừa húc đầu vào ;#6e7f89tường;");
        } else {
            if (player.getStat().stamina == 0) {
                EventManager.pushUIMessage("Bạn đã hết sức lực, bạn cần nghỉ ngơi (;#FF0000rest;)");
            } else {
                player.move(moveDirection.x, moveDirection.y);
                burnStamina();
                EventManager.pushUIMessage(message.toString());
                if (newPositionProcessors.containsKey(mapItem.getType())) {
                    return newPositionProcessors.get(mapItem.getType())
                            .process(subCommands, currentEvent);
                }
                return generateRandomEvent();
            }
        }
        return null;
    }

    private void burnStamina() {
        Player player = State.instance.getPlayer();
        player.changeStamina(-1);
    }

    private GameEvent generateRandomEvent() {
        return random.nextInt(100) < 10 ? new CombatEvent() : null;
    }

    private void extractDirection(String direction, final MapPosition moveDirection, final StringBuilder message) {
        switch (direction) {
            case "UP":
                moveDirection.y = - 1;
                message.append("Bạn vừa đi ;#00FF00lên trên");
                break;
            case "DOWN":
                moveDirection.y = 1;
                message.append("Bạn vừa đi ;#00FF00xuống dưới");
                break;
            case "RIGHT":
                moveDirection.x = 1;
                message.append("Bạn vừa đi ;#00FF00sang phải");
                break;
            case "LEFT":
                moveDirection.x = -1;
                message.append("Bạn vừa đi ;#00FF00sang trái");
                break;
        }
    }
}
