package lol.gameevents;

import lol.events.EventManager;
import lol.gameentities.State;
import lol.gameentities.eventconfigs.Choice;
import lol.gameentities.eventconfigs.EventConfig;

import java.util.List;

/**
 * Created by huynq on 8/2/17.
 */
public class WorldChoiceEvent implements GameEvent {
    private EventConfig eventConfig;

    public WorldChoiceEvent(EventConfig eventConfig) {
        this.eventConfig = eventConfig;
        EventManager.pushUIMessage(eventConfig.getBeginText());
        for (Choice choice : eventConfig.getChoices()) {
            EventManager.pushUIMessage(choice.displayString());
        }
    }

    @Override
    public GameEvent preProcess() {
        return null;
    }

    @Override
    public GameEvent process(List<String> commands) {
        if (commands.size() == 0) {
            EventManager.pushUIMessage("You must choose an answer");
            return null;
        }

        String label = commands.get(0);
        Choice choice = eventConfig.findChoice(label);
        if (choice == null) {
            EventManager.pushUIMessage("Choose again");
            return null;
        }

        System.out.println(choice);

        Integer result = eventConfig.results.get(choice.resultIndex);

        State.instance.getPlayer().changeMaxHP(result);

        EventManager.pushUIMessage(eventConfig.renderTextSuccess(
                choice.resultIndex, result));

        return new MainGameEvent();
    }

    @Override
    public GameEvent postProcess() {
        return null;
    }
}
