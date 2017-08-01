package lol.gameevents;

import lol.gameevents.processors.Processor;
import lol.gameevents.processors.normal.MapProcessor;
import lol.gameevents.processors.normal.MoveProcessor;
import lol.events.EventManager;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by huynq on 8/1/17.
 */
public class MainGameEvent implements GameEvent {

    private HashMap<String, Processor> commandProcessors;

    private Random random;

    private int monsterPercent = 10;

    public MainGameEvent() {
        commandProcessors = new HashMap<String, Processor>() {{
            put("MOVE", new MoveProcessor());
            put("MAP", new MapProcessor());
        }};
        random = new Random();
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

        if (commandProcessors.containsKey(mainCommand)) {
            GameEvent nextEvent = Processor.forward(commands, commandProcessors.get(mainCommand), this);
            if (nextEvent != null) return nextEvent;
            return null;
        } else {
            EventManager.pushUIMessage("Command not found, type ;#FF0000help; to get support");
        }

        return null;
    }


    @Override
    public GameEvent postProcess() {

        return null;
    }

}
