package lol.gameevents;

import lol.commands.CommandProcessor;
import lol.commands.MapProcessor;
import lol.commands.MoveProcessor;
import lol.events.EventManager;
import lol.inputs.CommandListener;
import lol.states.State;
import lol.states.maps.Map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public class GameEventManager implements CommandListener {
    public static final GameEventManager instance = new GameEventManager();

    State state = State.instance;



    private GameEvent currentEvent;

    private GameEventManager() {
        currentEvent = new MainGameEvent();


    }

    public void loadInitialMap() {
        Map map = Map.parseFile("assets/maps/lvl1.txt");
        state.setMap(map);
        state.getPlayer().setPosition(
                map.getPlayerStartX(),
                map.getPlayerStartY()
        );
    }

    @Override
    public void onCommandFinished(String command) {
        List<String> commands = Arrays.asList(command.toUpperCase().split("_"));
        GameEvent resultEvent = currentEvent.process(commands);

        if (resultEvent != null) {
            currentEvent = resultEvent;
        }
    }

    @Override
    public void commandChanged(String command) {

    }
}
