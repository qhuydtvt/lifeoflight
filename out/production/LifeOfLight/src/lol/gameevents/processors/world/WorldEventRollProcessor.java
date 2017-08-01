package lol.gameevents.processors.world;

import lol.bases.Utils;
import lol.events.EventManager;
import lol.gameentities.State;
import lol.gameentities.eventconfigs.Action;
import lol.gameentities.eventconfigs.EventConfig;
import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.util.List;

/**
 * Created by huynq on 8/2/17.
 */
public class WorldEventRollProcessor extends Processor {
    private EventConfig eventConfig;

    public WorldEventRollProcessor(EventConfig eventConfig) {
        this.eventConfig = eventConfig;
    }

    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        EventManager.pushUIMessage(eventConfig.getBeginText());
        Action action = eventConfig.getAction();
        // TODO: Get affect here
        if (action.type.toUpperCase().equals("ROLL")) {
            if (action.roll()) {
                Integer result = Utils.choice(eventConfig.getResults());
                State.instance.getPlayer().changeMaxHP(result);
                EventManager.pushUIMessage(eventConfig.renderTextSuccess(0, result));
            } else {
                EventManager.pushUIMessage(eventConfig.renderRndTextFailed(""));
            }
        }

        return null;
    }
}
