package lol.states;

import lol.commands.CommandProcessor;
import lol.commands.LookProcessor;
import lol.commands.MoveProcessor;
import lol.events.EventManager;
import lol.inputs.CommandListener;
import lol.states.maps.Map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public class StateManager implements CommandListener {
    public static final StateManager instance = new StateManager();

    State state = State.instance;

    HashMap<String, CommandProcessor> commandProcessors;

    private StateManager() {
        commandProcessors = new HashMap<String, CommandProcessor>() {{
            put("MOVE", new MoveProcessor());
            put("LOOK", new LookProcessor());
        }};
    }

    public void loadInitialMap() {
        Map map = Map.parseFile("assets/maps/lvl1.txt");
        state.setMap(map);
        state.getPlayer().setPosition(
                map.getPlayerStartX(),
                map.getPlayerStartY()
        );
    }

    private void processCommand(String command) {

        List<String> commands = Arrays.asList(command.toUpperCase().split("_"));

        int commandsNumber = commands.size();

        if (commandsNumber < 1) return;

        String mainCommand = commands.get(0);

        if (commandProcessors.containsKey(mainCommand)) {
            CommandProcessor.forward(commands, commandProcessors.get(mainCommand));
        } else {
            EventManager.pushUIMessage("Command not found, type ;#FF0000help; to get support");
        }
    }

    @Override
    public void onCommandFinished(String command) {
        processCommand(command);
    }

    @Override
    public void commandChanged(String command) {

    }
}
