package lol.gameentities.players.inventories;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lol.bases.Utils;
import lol.gameentities.CombatStat;
import lol.gameentities.players.inventories.stataffects.StatAffect;
import lol.gameentities.players.inventories.stataffects.processors.StatAffectProcessor;

import java.lang.reflect.Type;
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

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private int type;
    @SerializedName("statAffects")
    private List<StatAffect> statAffects;
    @SerializedName("description")
    private String description;

    private transient List<StatAffectProcessor> processors;

    public static final List<InventoryItem> combatItems;
    public static final List<InventoryItem> evenItems;

    static {
        Type combatItemsListType = new TypeToken<List<InventoryItem>>(){}.getType();
        combatItems = Utils.parseJSON("assets/item/item_battle.json", combatItemsListType);
        evenItems = Utils.parseJSON("assets/item/item_event.json", combatItemsListType);
    }

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
