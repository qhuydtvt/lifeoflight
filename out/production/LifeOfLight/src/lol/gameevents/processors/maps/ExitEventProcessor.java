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
        State state = State.instance;
        Map map = state.getMap();
        if (map.getMainItemLeft() == 0) {

            if (State.instance.loadNextMap()) {
                EventManager.pushUIMessage("Congrats, you just cleared the dungeon");
                EventManager.pushUIMessage(String.format("Lên map cấp độ: %s", state.getCurrentLevel()));
            } else {
                // TODO: Load Victory script here
                EventManager.pushUIMessage("Tèn ten, chúc mừng bạn đã phá đảo");
                State.instance.reset();
                EventManager.pushUIMessage(String.format("Về level: %s", state.getCurrentLevel()));
            }
        } else {
            EventManager.pushUIMessage("You hit the exit, but you can't get out, try to collect ;#FF0000all; main items");
        }
        return null;
    }
}
