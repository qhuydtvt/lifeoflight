package lol.gameevents.processors.main;


import lol.gameentities.State;
import lol.gameentities.players.Player;
import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.util.List;

/**
 * Created by huynq on 8/8/17.
 */
public class RestProcessor extends Processor {
    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        Player player = State.instance.getPlayer();
        player.changeStamina(5);
        return null;
    }
}
