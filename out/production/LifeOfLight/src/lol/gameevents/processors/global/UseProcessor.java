package lol.gameevents.processors.global;


import lol.events.EventManager;
import lol.gameentities.State;
import lol.gameentities.items.GameItem;
import lol.gameentities.players.Player;
import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.util.List;

/**
 * Created by huynq on 8/5/17.
 */
public class UseProcessor extends Processor {
    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        if (commands.size() == 0) return null;
        String itemId = commands.get(0);

        Player player = State.instance.getPlayer();

        GameItem item = player.getItem(itemId);

        if (item == null) {
            EventManager.pushUIMessage("No such item");
        } else {
            player.use(item);
            EventManager.pushUIMessage(String.format("%s was used", item.name));
        }

        return null;
    }
}
