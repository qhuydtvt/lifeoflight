package lol.gameentities.players;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lol.bases.Utils;
import lol.gameentities.CombatStat;
import lol.gameentities.CombatUnit;
import lol.gameentities.MapPosition;
import lol.gameentities.players.inventories.InventoryItem;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by huynq on 7/30/17.
 */
public class Player extends CombatUnit {

    @SerializedName("exp")
    public Integer exp;

    @SerializedName("item")
    public String item;

    @SerializedName("skill")
    public String skill;

    @SerializedName("status")
    public String status;

    @SerializedName("ability")
    public String ability;

    @SerializedName("inventory")
    public List<InventoryItem> inventoryItems;

    @SerializedName("current_level")
    public Integer currentLevel;

    @SerializedName("nextLevelFormula")
    public NextLevelFormula nextLevelFormula;

    public InventoryItem leftHandItem;
    public InventoryItem rightHandItem;
    public InventoryItem bodyItem;
    public InventoryItem headItem;

    public MapPosition mapPosition;

    public Player() {
        mapPosition = new MapPosition();
    }

    public void changeMaxHP(int amount) {
        this.stat.maxHp += amount;
    }

    public void move(int dx, int dy) {
        this.mapPosition.addUp(dx, dy);
    }

    public void changeExp(int amount) {
        this.exp += amount;
    }

    public boolean levelUp() {
        int nextLevelEXP = stat.nextLevelMinExp;
        if (nextLevelEXP != -1 && exp >= nextLevelEXP) {
            currentLevel++;
            exp -= nextLevelEXP;
            stat = nextLevelFormula.calculate(stat);
            stat.init();
            return true;
        }
        return false;
    }

    public InventoryItem getItem(int id) {
        for(InventoryItem item : this.inventoryItems) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void use(InventoryItem item) {
        CombatStat newStat = this.stat.clone();
        // TODO: Handle weareable
        if (item.getType() == InventoryItem.TYPE_ONE_TIME) {
            item.affect(newStat, this.stat);
            this.stat = newStat;
            if (this.inventoryItems.contains(item)) {
                this.inventoryItems.remove(item);
            }
        }
    }

    public void collect(InventoryItem item) {
        // TODO: check max
        this.inventoryItems.add(item);
    }

    public void getHit(int damage) {
        this.stat.hp -= damage;
        if (this.stat.hp < 0) this.stat.hp = 0;
    }

    public void rebirth() {
        this.stat.hp = this.stat.maxHp;
        this.exp = this.exp * 75 / 100;
    }

    public static Player parseFile(String url) {
        Type playerListType = new TypeToken<List<Player>>(){}.getType();
        List<Player> playerConfigs = new Gson().fromJson(Utils.loadFileContent(url), playerListType);
        if (playerConfigs.size() == 0) {
            System.out.println("Player config file is empty or there was problem with loading this file");
            return null;
        } else {
            System.out.println(playerConfigs.get(0));
            return playerConfigs.get(0);
        }
    }
}
