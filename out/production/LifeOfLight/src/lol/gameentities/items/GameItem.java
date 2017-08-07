package lol.gameentities.items;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lol.bases.Utils;
import lol.gameentities.CombatStat;
import lol.gameentities.State;
import lol.gameentities.items.stataffects.StatAffect;
import lol.gameentities.items.stataffects.processors.StatAffectProcessor;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by huynq on 8/6/17.
 */
public class GameItem implements Cloneable {
    private static final List<GameItem> prefixItems;
    private static final List<GameItem> rootItems;
    private static final List<GameItem> suffixItems;

    private static final List<GameItem> eatableItems;
    private static final List<GameItem> legendaryItems;

    public static final int TYPE_ONE_TIME = 0;
    public static final int TYPE_ONE_HAND = 1;
    public static final int TYPE_TWO_HAND = 2;
    public static final int TYPE_BODY = 3;
    public static final int TYPE_HEAD = 4;
    public static final int TYPE_FEET = 5;

    public static final int TYPE_NULL = -1;
    public static final int TYPE_EATABLE = 0;
    public static final int TYPE_EQUIPABLE = 1;
    public static final int TYPE_LEGENDARY = 2;

    static {
        Type subItemListType = new TypeToken<List<GameItem>>() {
        }.getType();

        prefixItems = Utils.parseJSON("assets/item/normalitems/item_prefix.json", subItemListType);
        rootItems = Utils.parseJSON("assets/item/normalitems/item_root.json", subItemListType);
        suffixItems = Utils.parseJSON("assets/item/normalitems/item_sufifx.json", subItemListType);
        eatableItems = Utils.parseJSON("assets/item/item_battle.json", subItemListType);
        legendaryItems = Utils.parseJSON("assets/item/lengedary/item_legendary.json", subItemListType);
    }

    @SerializedName("name")
    public String name;
    @SerializedName("id")
    public String id;
    @SerializedName("type")
    public Integer type;
    @SerializedName("description")
    private String description;
    @SerializedName("statAffects")
    public List<StatAffect> statAffects;

    private transient List<StatAffectProcessor> processors;

    private GameItem() {
        statAffects = new ArrayList<>();
    }

    public String dialog() {
        StringBuilder result = new StringBuilder();

        for (StatAffect affect : statAffects) {
            result.append(affect.dialog());
        }

        return result.toString();
    }

    public  boolean isWearable() {
        return this.type > 0;
    }

    public GameItem solidifyStatAffects() {
        GameItem gameItem = Utils.clone(this, GameItem.class);
        gameItem.statAffects = StatAffect.solidify(gameItem.statAffects);
        return gameItem;
    }

    public String getDescription() {
        if (description == null) return "...";
        return description;
    }

    private void generateProcessors() {
        if (processors == null) {
            processors = new ArrayList<>();
            for (StatAffect statAffect : statAffects) {
                processors.add(statAffect.getProcessor());
            }
        }
    }

    public void affect(CombatStat currentStat, final CombatStat originStat) {
        generateProcessors();
        for (StatAffectProcessor processor : processors) {
            processor.affect(currentStat, originStat);
        }
    }

    @Override
    public String toString() {
        return "GameItem{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", statAffects=" + statAffects +
                ", processors=" + processors +
                '}';
    }

    public static GameItem random(int itemType, State state) {
        switch (itemType) {
            case TYPE_NULL: return null;
            case TYPE_EATABLE: return randomEatable();
            case TYPE_EQUIPABLE: return randomEquiptable(state);
            case TYPE_LEGENDARY: return randomLegendary(state);
        }
        return null;
    }

    private static GameItem randomLegendary(State state) {
        final long MAX_LOOP_DOWN = legendaryItems.size() * 20;
        long loopCount = 0;
        while(loopCount < MAX_LOOP_DOWN) {
            GameItem gameItem = Utils.choice(legendaryItems);
            if (!state.itemAlreadyUsed(gameItem.id)) {
                return gameItem.solidifyStatAffects();
            }
            loopCount++;
        }
        System.out.println("Legendary item not found");
        return null;
    }

    private static GameItem randomEquiptable(State state) {
        long loopCount = 0;

        do {
            GameItem prefix = Utils.choice(prefixItems);
            GameItem suffix = Utils.choice(suffixItems);
            GameItem root = Utils.choice(rootItems);
            String id = String.format("%s-%s-%s", prefix.id, root.id, suffix.id);
            if (!state.itemAlreadyUsed(id)) {
                GameItem item = new GameItem();

                item.name = String.format("%s %s %s", prefix.name, root.name, suffix.name);
                item.id = id;
                item.type = root.type;

                item.statAffects.addAll(prefix.statAffects);
                item.statAffects.addAll(root.statAffects);
                item.statAffects.addAll(suffix.statAffects);

                return item.mergeStatAffects().solidifyStatAffects();
            }
            loopCount++;
        }
        while (loopCount < 50000);

        System.out.println("Could not found item");

        return null;
    }

    private static GameItem randomEatable() {
        return Utils.choice(eatableItems).solidifyStatAffects();
    }

    public GameItem mergeStatAffects() {
        GameItem gameItem = Utils.clone(this, GameItem.class);

        HashMap<StatAffect, StatAffect> map = new HashMap<>();
        for (StatAffect statAffect : gameItem.statAffects) {
            if (map.containsKey(statAffect)) {
                map.put(statAffect, map.get(statAffect).addFormula(statAffect));
            } else {
                map.put(statAffect, statAffect);
            }
        }
        gameItem.statAffects = new ArrayList<>(map.values());

        return gameItem;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GameItem)) return false;
        return ((GameItem) obj).id.equals(this.id);
    }
}
