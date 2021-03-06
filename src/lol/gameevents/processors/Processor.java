package lol.gameevents.processors;

import lol.gameevents.GameEvent;

import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public abstract class Processor {
    public abstract GameEvent process(List<String> commands, GameEvent currentEvent);

    public static GameEvent forward(List<String> commands, Processor subProcessor, GameEvent currentEvent) {
        if (commands.size() == 0) {
            return subProcessor.process(null, currentEvent);
        } else {
            return subProcessor.process(commands.subList(1, commands.size()), currentEvent);
        }
    }
}
