package lol.gameevents.processors.global;

import lol.events.EventManager;
import lol.gameentities.State;
import lol.gameentities.items.GameItem;
import lol.gameentities.players.Player;
import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.util.List;

/**
 * Created by huynq on 8/9/17.
 */
public class TrashProcessor extends Processor {
    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        if (commands.size() == 0) return null;

        Player player = State.instance.getPlayer();
        String itemId = commands.get(0);
        GameItem gameItem = player.findInAllItems(itemId);
        if (gameItem == null) {
            EventManager.pushUIMessage("Không tìm thấy vật phẩm này");
            return null;
        } else {
            player.removeItem(gameItem);
            EventManager.pushUIMessage(String.format("Bạn đã vứt '%s' đi", gameItem.name));
            return null;
        }
    }
}
