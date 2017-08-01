package lol.gameevents.processors.world;

import lol.gameentities.State;
import lol.gameentities.eventconfigs.EventConfig;
import lol.gameevents.GameEvent;
import lol.gameevents.WorldChoiceEvent;
import lol.gameevents.processors.Processor;

import java.util.List;

/**
 * Created by huynq on 8/2/17.
 */
public class WorldEventProcessor extends Processor {
    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        EventConfig eventConfig = EventConfig.random();
        State.instance.removeItemAtPlayerPosition();
        System.out.println(eventConfig.getType().toUpperCase());
        switch (eventConfig.getType().toUpperCase()) {
            case "ROLL":
                return new WorldEventRollProcessor(eventConfig).process(commands, currentEvent);
            case "CHOICE":
                return new WorldChoiceEvent(eventConfig);
        }
        return null;
    }
}
