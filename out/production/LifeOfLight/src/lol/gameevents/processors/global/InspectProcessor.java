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
public class InspectProcessor extends Processor {
    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        if (commands.size() == 0) return null;
        String itemId = commands.get(0);
        Player player = State.instance.getPlayer();
        GameItem gameItem = player.findInAllItems(itemId);
        if (gameItem == null) {
            EventManager.pushUIMessage(String.format("Không tìm thấy vật phẩm với id = %s", itemId));
        } else {
            EventManager.pushUIMessage(gameItem.nameAndId());
            EventManager.pushUIMessage(gameItem.getDescription());
            EventManager.pushUIMessage(gameItem.statText());
        }
        return null;
    }
}
