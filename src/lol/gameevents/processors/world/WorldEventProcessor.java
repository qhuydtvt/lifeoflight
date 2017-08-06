package lol.gameevents.processors.world;

import lol.formulas.ItemRateFormula;
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
        if (eventConfig.getType() == null) {
            System.out.println(String.format("EventConfig: Type is null %s", eventConfig.beginText));
        } else {
            switch (eventConfig.getType().toUpperCase()) {
                case "ROLL":
                    return new WorldEventRollProcessor(eventConfig).process(commands, currentEvent);
                case "CHOICE":
                    return new WorldChoiceEvent(eventConfig);
                case "ITEM":
                    return new WordEventItemProcessor(eventConfig, ItemRateFormula.eventInstance).process(commands, currentEvent);
            }
        }
        return null;
    }
}
