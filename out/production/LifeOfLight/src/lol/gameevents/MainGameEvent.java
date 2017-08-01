package lol.gameevents;

import lol.gameevents.commands.CommandProcessor;
import lol.gameevents.commands.MapProcessor;
import lol.gameevents.commands.MoveProcessor;
import lol.events.EventManager;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by huynq on 8/1/17.
 */
public class MainGameEvent implements GameEvent {

    private HashMap<String, CommandProcessor> commandProcessors;

    private Random random;

    private int monsterPercent = 10;

    public MainGameEvent() {
        commandProcessors = new HashMap<String, CommandProcessor>() {{
            put("MOVE", new MoveProcessor());
            put("MAP", new MapProcessor());
        }};
        random = new Random();
    }

    @Override
    public GameEvent process(List<String> commands) {
        int commandsNumber = commands.size();

        if (commandsNumber < 1) return null;

        String mainCommand = commands.get(0);

        if (commandProcessors.containsKey(mainCommand)) {
            GameEvent nextEvent = CommandProcessor.forward(commands, commandProcessors.get(mainCommand), this);
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
