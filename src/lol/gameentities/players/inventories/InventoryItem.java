package lol.gameentities.players.inventories;

import lol.gameentities.players.PlayerStat;
import lol.gameentities.players.inventories.stataffects.StatAffect;
import lol.gameentities.players.inventories.stataffects.StatAffectProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynq on 8/5/17.
 */
public class InventoryItem {
    public static int TYPE_ONE_TIME = 0;
    public static int TYPE_ONE_HAND = 1;
    public static int TYPE_TWO_HAND = 2;
    public static int TYPE_BODY = 3;
    public static int TYPE_HEAD = 4;

    private int type;
    private List<StatAffect> statAffects;
    private String description;

    private transient List<StatAffectProcessor> processors;

    private void generateProcessors() {
        if (processors == null) {
            processors = new ArrayList<>();
            for (StatAffect statAffect: statAffects) {
                processors.add(statAffect.getProcessor());
            }
        }
    }

    public void affect(PlayerStat currentStat, PlayerStat originStat) {
        generateProcessors();
        for (StatAffectProcessor processor: processors) {
            processor.affect(currentStat, originStat);
        }
    }
}
