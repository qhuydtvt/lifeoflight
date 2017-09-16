package lol.gameevents;

import lol.events.EventManager;
import lol.gameentities.eventconfigs.EventConfig;
import lol.gameevents.processors.Processor;
import lol.gameevents.processors.global.*;
import lol.inputs.CommandListener;
import lol.gameentities.State;

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

    private HashMap<String, Processor> globalCommandProcessors = new HashMap<String, Processor>() {{
        put("HELP", new HelpProcessor());
        put("INVENTORY", new InventoryProcessor());
        put("INSPECT", new InspectProcessor());
        put("TRASH", new TrashProcessor());
        put("QUIT", new QuitProcessor());
        put("USE", new UseProcessor());
        put("EQUIPMENT", new EquipmentProcessor());
        put("STORE", new StoreProcessor());
    }};

    private GameEventManager() {
        currentEvent = new MainGameEvent();
    }

    public void loadData() {
        state.load();
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
        }

        String mainCommand = commands.get(0);
        GameEvent resultEvent;

        if (globalCommandProcessors.containsKey(mainCommand)) {
            Processor processor = globalCommandProcessors.get(mainCommand);
            resultEvent = Processor.forward(commands, processor, currentEvent);
        } else {
            resultEvent = currentEvent.process(commands);
        }

        if (resultEvent != null) {
            GameEvent preProcessResult = resultEvent.preProcess();
            if (preProcessResult != null) {
                currentEvent = preProcessResult;
            } else {
                currentEvent = resultEvent;
            }
        }

    }

    @Override
    public void commandChanged(String command) {

    }
}
