package lol.commands;

import lol.events.EventManager;
import lol.states.State;
import lol.states.maps.Map;
import lol.states.maps.mapitems.MapItem;
import lol.states.players.Player;

import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public class MapProcessor extends CommandProcessor {
    @Override
    public void process(List<String> subCommands) {
        Map map = State.instance.getMap();
        Player player = State.instance.getPlayer();

        List<List<MapItem>> block = map.getMapItems(player.getPosition(), player.getVision());
        EventManager.pushUIMessage(" ");
        for (int y = 0; y < block.size(); y ++) {
            StringBuilder rowMessage = new StringBuilder();
            for(int x = 0; x < block.get(0).size(); x ++) {
                MapItem item = block.get(y).get(x);
                if (x == block.size() / 2 && y == block.get(0).size() / 2) {
                    rowMessage.append("@ ");
                } else if (item == null){
                    rowMessage.append("x ");
                } else {
                    rowMessage.append(item.getSymbol()).append(" ");
                }
            }
            EventManager.pushUIMessage(rowMessage.toString());
        }
        EventManager.pushUIMessage(" ");
    }
}
