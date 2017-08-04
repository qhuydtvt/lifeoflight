package lol.gameevents.processors.maps;

import lol.events.EventManager;
import lol.gameentities.State;
import lol.gameentities.maps.Map;
import lol.gameentities.players.Player;
import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.util.List;

/**
 * Created by huynq on 8/2/17.
 */
public class MainItemProcessor extends Processor {
    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        Map map = State.instance.getMap();
        Player player = State.instance.getPlayer();
        map.removeItem(player.mapPosition);
        EventManager.pushUIMessage("You just collected a ;#0000FFmain item;");
        return null;
    }
}
