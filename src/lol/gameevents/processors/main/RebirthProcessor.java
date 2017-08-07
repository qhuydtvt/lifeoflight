package lol.gameevents.processors.main;

import lol.events.EventManager;
import lol.gameentities.State;
import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.util.List;

/**
 * Created by huynq on 8/5/17.
 */
public class RebirthProcessor extends Processor {

    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        if (State.instance.getPlayer().getStat().hp <= 0) {
            State.instance.rebirth();
            EventManager.pushUIMessage("You are born again, you EXP decreased by 25%");
        }
        else {
            EventManager.pushUIMessage("You're not dead, cannot ;#FF0000rebirth;");
        }
        return null;
    }
}
