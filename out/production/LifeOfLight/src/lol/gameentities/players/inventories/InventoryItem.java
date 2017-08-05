package lol.gameentities.players.inventories;

import lol.gameentities.CombatStat;
import lol.gameentities.players.inventories.stataffects.StatAffect;
import lol.gameentities.players.inventories.stataffects.processors.StatAffectProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynq on 8/5/17.
 */
public class InventoryItem {
    public static final int TYPE_ONE_TIME = 0;

    public static final int TYPE_ONE_HAND = 1;
    public static final int TYPE_TWO_HAND = 2;
    public static final int TYPE_BODY = 3;
    public static final int TYPE_HEAD = 4;
    public static final int TYPE_FEET = 5;

    private int id;
    private String name;
    private int type;
    private List<StatAffect> statAffects;
    private String description;

    private transient List<StatAffectProcessor> processors;

    private void generateProcessors() {
        if (processors == null) {
            processors = new ArrayList<>();
            for (StatAffect statAffect : statAffects) {
                processors.add(statAffect.getProcessor());
            }
        }
    }

    public int getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        InventoryItem item = (InventoryItem) obj;
        return this.id == item.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public void affect(CombatStat currentStat, CombatStat originStat) {
        generateProcessors();
        for (StatAffectProcessor processor : processors) {
            processor.affect(currentStat, originStat);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String dialog() {
        StringBuilder result = new StringBuilder();

        for (StatAffect affect : statAffects) {
            result.append(affect.dialog());
        }

        return result.toString();
    }

    public static InventoryItem createNoddle() {
        InventoryItem item = new InventoryItem();
        item.type = TYPE_ONE_TIME;
        item.statAffects = new ArrayList<StatAffect>() {{
            add(new StatAffect("HP", 5, false));
        }};
        item.name = "Mỳ tôm trứng";
        item.description = "Mỳ tôm hảo hảo chua cay đập thêm quả trứng";
        item.id = 1;
        return item;
    }
}
