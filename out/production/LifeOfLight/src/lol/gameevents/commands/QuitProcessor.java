package lol.gameevents.commands;

import lol.gameevents.GameEvent;

import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public class QuitProcessor extends CommandProcessor {

    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        System.exit(0);
        return null;
    }
}
