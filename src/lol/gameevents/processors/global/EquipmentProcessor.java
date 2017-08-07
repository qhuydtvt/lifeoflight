package lol.gameevents.processors.global;

import lol.events.EventManager;
import lol.gameentities.State;
import lol.gameentities.items.GameItem;
import lol.gameentities.players.Player;
import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.awt.*;
import java.util.List;

/**
 * Created by huynq on 8/8/17.
 */
public class EquipmentProcessor extends Processor {
    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        Player player = State.instance.getPlayer();

        if(player.headItem == null) {
            EventManager.pushUIMessage("Đầu chưa đội gì");
        } else {
            EventManager.pushUIMessage("Đầu đội:");
            EventManager.pushUIMessage(player.headItem.name);
        }

        EventManager.pushUIMessage(";#FFFF00*;");

        if (player.bodyItem == null) {
            EventManager.pushUIMessage("Đang cởi trần");
        } else {
            EventManager.pushUIMessage("Người mặc:");
            EventManager.pushUIMessage(player.bodyItem.name);
        }

        EventManager.pushUIMessage(";#FFFF00*;");

        if (player.handItems == null || player.handItems.size() == 0) {
            EventManager.pushUIMessage("Hai bàn tay trắng");
        } else {
            EventManager.pushUIMessage("Tay cầm:");
            for (GameItem item : player.handItems) {
                EventManager.pushUIMessage(item.name);
            }
        }

        EventManager.pushUIMessage(";#FFFF00*;");

        if(player.feetItem == null) {
            EventManager.pushUIMessage("Bàn chân trần trên cát");
        } else {
            EventManager.pushUIMessage("Chân đi:");
            EventManager.pushUIMessage(player.feetItem.name);
        }

        return null;
    }
}
