package lol.gameentities.items;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lol.bases.Utils;
import lol.gameentities.CombatStat;
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

    public static final int TYPE_ONE_TIME = 0;
    public static final int TYPE_ONE_HAND = 1;
    public static final int TYPE_TWO_HAND = 2;
    public static final int TYPE_BODY = 3;
    public static final int TYPE_HEAD = 4;
    public static final int TYPE_FEET = 5;

    static {
        Type subItemListType = new TypeToken<List<GameItem>>() {
        }.getType();

        prefixItems = Utils.parseJSON("assets/item/bases/item_prefix.json", subItemListType);
        rootItems = Utils.parseJSON("assets/item/bases/item_root.json", subItemListType);
        suffixItems = Utils.parseJSON("assets/item/bases/item_sufifx.json", subItemListType);
        eatableItems = Utils.parseJSON("assets/item/item_battle.json", subItemListType);

    }

    @SerializedName("name")
    public String name;
    @SerializedName("id")
    public String id;
    @SerializedName("type")
    public Integer type;
    @SerializedName("description")
    public String description;
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

    public GameItem solidifyStatAffects() {
        GameItem gameItem = Utils.clone(this, GameItem.class);
        gameItem.statAffects = StatAffect.solidify(gameItem.statAffects);
        return gameItem;
    }

    private void generateProcessors() {
        if (processors == null) {
            processors = new ArrayList<>();
            for (StatAffect statAffect : statAffects) {
                processors.add(statAffect.getProcessor());
            }
        }
    }

    public void affect(CombatStat currentStat, CombatStat originStat) {
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

    public static GameItem randomFromEvent() {
        GameItem prefix = Utils.choice(prefixItems);
        GameItem suffix = Utils.choice(suffixItems);
        GameItem root = Utils.choice(rootItems);

        GameItem item = new GameItem();

        item.name = String.format("%s %s %s", prefix.name, root.name, suffix.name);
        item.id = String.format("%s-%s-%s", prefix.id, root.id, suffix.id);
        item.type = root.type;

        item.statAffects.addAll(prefix.statAffects);
        item.statAffects.addAll(root.statAffects);
        item.statAffects.addAll(suffix.statAffects);

        return item.solidifyStatAffects();
    }

    public static GameItem randomFromCombat() {
        return Utils.choice(eatableItems).solidifyStatAffects();
    }

    private static List<StatAffect> shorten(List<StatAffect> affectList) {
        HashMap<StatAffect, StatAffect> map = new HashMap<>();
        for (StatAffect statAffect : affectList) {
            if (map.containsKey(statAffect)) {
                map.put(statAffect, map.get(statAffect).add(statAffect));
            } else {
                map.put(statAffect, statAffect);
            }
        }
        return new ArrayList<>(map.values());
    }
}
