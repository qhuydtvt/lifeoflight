package lol.gameevents;

import lol.events.EventManager;
import lol.gameentities.eventconfigs.EventConfig;
import lol.inputs.CommandListener;
import lol.gameentities.State;
import lol.gameentities.maps.Map;

import java.util.Arrays;
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

    public void loadData() {
        state.loadInitialMap();
        EventConfig.load();
    }

    @Override
    public void onCommandFinished(String command) {
        List<String> commands = Arrays.asList(command.toUpperCase().split("_"));
        if (commands.size() > 0) {
            if (commands.get(0).equals("TEST")) {
                EventManager.pushUIMessage("Văn học là khoa học nghiên cứu về văn chương. Nó lấy các hiện tượng văn chương nghệ thuật làm đối tượng cho mình. Quan hệ giữa văn chương và văn học là quan hệ giữa đối tượng và chủ thể, giữa nghệ thuật và khoa học; văn chương (nghệ thuật) là đối tượng của văn học (khoa học). Chính thế bạn tăng ;#46ff2d2 HP");
                return;
            }

            if (commands.get(0).equals("CLEAR")) {
                EventManager.pushClearUI();
                return;
            }

            if (commands.get(0).equals("QUIT")) {
                // TODO: Save status here
                System.exit(0);
                return;
            }
        }

        GameEvent resultEvent = currentEvent.process(commands);
        if (resultEvent != null) {
            currentEvent = resultEvent;
        }
    }

    @Override
    public void commandChanged(String command) {

    }
}
