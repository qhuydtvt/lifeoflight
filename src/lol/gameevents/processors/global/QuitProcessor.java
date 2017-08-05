package lol.gameevents.processors.global;

import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public class QuitProcessor extends Processor {

    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        System.exit(0);
        return null;
    }
}
