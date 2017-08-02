package lol.gameevents.processors.normal;

import lol.events.EventManager;
import lol.gameevents.GameEvent;
import lol.gameentities.State;
import lol.gameentities.maps.Map;
import lol.gameentities.maps.mapitems.MapItem;
import lol.gameentities.players.Player;
import lol.gameevents.processors.Processor;

import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public class MapProcessor extends Processor {
    @Override
    public GameEvent process(List<String> subCommands, GameEvent currentEvent) {
        Map map = State.instance.getMap();
        Player player = State.instance.getPlayer();

        List<List<MapItem>> block = map.getMapItems(player.getPosition(), player.getVision());
        EventManager.pushUIMessage(" ");
        for (int y = 0; y < block.size(); y ++) {
            StringBuilder rowMessage = new StringBuilder();
            for(int x = 0; x < block.get(0).size(); x ++) {
                MapItem item = block.get(y).get(x);
                if (x == block.size() / 2 && y == block.get(0).size() / 2) {
                    rowMessage.append("⚇ ");
                } else if (item == null) {
                    rowMessage.append("☢ ");
                } else {
                    rowMessage.append(item.getSymbol()).append(" ");
                }
            }
            EventManager.pushUIMessage(rowMessage.toString());
        }
        EventManager.pushUIMessage(" ");
        return null;
    }
}
