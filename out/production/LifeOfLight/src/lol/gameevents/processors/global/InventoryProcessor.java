package lol.gameevents.processors.global;

import lol.events.EventManager;
import lol.gameentities.State;
import lol.gameentities.items.GameItem;
import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huynq on 8/5/17.
 */
public class InventoryProcessor extends Processor {
    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        HashMap<GameItem, Integer> hashMapItems = new HashMap<>();
        for(GameItem gameItem: State.instance.getPlayer().gameItems) {
            if (!hashMapItems.containsKey(gameItem)) {
                hashMapItems.put(gameItem, 1);
            } else {
                hashMapItems.put(gameItem, hashMapItems.get(gameItem) + 1);
            }
        }

        for(Map.Entry<GameItem, Integer> pair : hashMapItems.entrySet()) {
            EventManager.pushUIMessage(String.format(" %s x %s (id=%s)",pair.getKey().name, pair.getValue(), pair.getKey().id));
        }
        return null;
    }
}
