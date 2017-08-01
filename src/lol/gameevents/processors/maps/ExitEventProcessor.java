package lol.gameevents.processors.maps;

import lol.events.EventManager;
import lol.gameentities.State;
import lol.gameentities.maps.Map;
import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.util.List;

/**
 * Created by huynq on 8/2/17.
 */
public class ExitEventProcessor extends Processor {
    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        Map map = State.instance.getMap();
        if (map.getMainItemLeft() == 0) {
            EventManager.pushUIMessage("Congrats, you just cleared the dungeon");
            State.instance.loadNextMap();
        } else {
            EventManager.pushUIMessage("You hit the exit, but you can't get out, try to collect ;#FF0000all; main items");
        }
        return null;
    }
}
