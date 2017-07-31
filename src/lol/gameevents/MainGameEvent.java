package lol.gameevents;

import lol.commands.CommandProcessor;
import lol.commands.MapProcessor;
import lol.commands.MoveProcessor;
import lol.events.EventManager;

import java.util.HashMap;
import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public class MainGameEvent implements GameEvent {

    HashMap<String, CommandProcessor> commandProcessors;

    public MainGameEvent() {
        commandProcessors = new HashMap<String, CommandProcessor>() {{
            put("MOVE", new MoveProcessor());
            put("MAP", new MapProcessor());
        }};
    }

    @Override
    public GameEvent process(List<String> commands) {
        int commandsNumber = commands.size();

        if (commandsNumber < 1) return null;

        String mainCommand = commands.get(0);

        if (commandProcessors.containsKey(mainCommand)) {
            CommandProcessor.forward(commands, commandProcessors.get(mainCommand));
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
