package lol.gameevents.processors.global;

import lol.events.EventManager;
import lol.gameentities.State;
import lol.gameentities.items.GameItem;
import lol.gameentities.players.Player;
import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.util.List;

/**
 * Created by huynq on 8/8/17.
 */
public class StoreProcessor extends Processor {
    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        if (commands.size() == 0) return null;
        String itemToStore = commands.get(0);
        Player player = State.instance.getPlayer();

        GameItem removedItem = player.store(itemToStore);
        if (removedItem == null) {
            EventManager.pushUIMessage("Item not found");
        } else {
            EventManager.pushUIMessage(String.format("%s was stored", removedItem.name));
        }

        return null;
    }
}
