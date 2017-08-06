package lol.gameevents.processors.global;

import lol.events.EventManager;
import lol.gameentities.State;
import lol.gameentities.players.inventories.InventoryItem;
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
        HashMap<InventoryItem, Integer> hashMapItems = new HashMap<>();
        for(InventoryItem inventoryItem: State.instance.getPlayer().gameItems) {
            if (!hashMapItems.containsKey(inventoryItem)) {
                hashMapItems.put(inventoryItem, 1);
            } else {
                hashMapItems.put(inventoryItem, hashMapItems.get(inventoryItem) + 1);
            }
        }

        for(Map.Entry<InventoryItem, Integer> pair : hashMapItems.entrySet()) {
            EventManager.pushUIMessage(String.format(" %s x %s (id=%s)",pair.getKey().getName(), pair.getValue(), pair.getKey().getId()));
        }
        return null;
    }
}
