package lol.gameevents;

import lol.gameentities.State;
import lol.gameevents.processors.Processor;
import lol.gameevents.processors.main.MapProcessor;
import lol.gameevents.processors.main.MoveProcessor;
import lol.events.EventManager;
import lol.gameevents.processors.main.RebirthProcessor;

import java.util.HashMap;
import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public class MainGameEvent implements GameEvent {

    private HashMap<String, Processor> commandProcessors;

    public MainGameEvent() {
        commandProcessors = new HashMap<String, Processor>() {{
            put("MOVE", new MoveProcessor());
            put("MAP", new MapProcessor());
            put("REBIRTH", new RebirthProcessor());
        }};
    }

    @Override
    public GameEvent preProcess() {
        return null;
    }

    @Override
    public GameEvent process(List<String> commands) {
        int commandsNumber = commands.size();

        if (commandsNumber < 1) return null;

        String mainCommand = commands.get(0);

        if (State.instance.getPlayer().getStat().hp <= 0 && !mainCommand.equals("REBIRTH")) {
            EventManager.pushUIMessage("You're dead, type ;#FF0000rebirth; to play again");
        } else {
            if (commandProcessors.containsKey(mainCommand)) {
                GameEvent nextEvent = Processor.forward(commands, commandProcessors.get(mainCommand), this);
                if (nextEvent != null) return nextEvent;
                return null;
            } else {
                EventManager.pushUIMessage("Command not found, type ;#FF0000help; to get support");
            }
        }

        return null;
    }


    @Override
    public GameEvent postProcess() {
        return null;
    }

}
