package lol.gameentities.players;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lol.bases.Utils;
import lol.gameentities.MapPosition;
import lol.gameentities.players.inventories.InventoryItem;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by huynq on 7/30/17.
 */
public class Player {

    public PlayerStat stat;
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
    public int currentLevel;
    @SerializedName("level_stats")
    public List<PlayerStat> levelStats;

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
        int nextLevelEXP = nextLevelEXP();
        if (nextLevelEXP != -1 && exp >= nextLevelEXP) {
            currentLevel++;
            exp -= nextLevelEXP;
            loadStat(currentLevel);
            stat.init();
            return true;
        }
        return false;
    }

    public int nextLevelEXP() {
        if (levelStats.size() > currentLevel + 1) {
            return levelStats.get(currentLevel + 1).minExp;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "stat=" + stat +
                ", exp=" + exp +
                ", item='" + item + '\'' +
                ", skill='" + skill + '\'' +
                ", status='" + status + '\'' +
                ", ability='" + ability + '\'' +
                ", inventory=" + inventoryItems +
                ", currentLevel=" + currentLevel +
                ", levelStats=" + levelStats +
                ", mapPosition=" + mapPosition +
                '}';
    }

    public void loadStat(int level) {
        this.stat = levelStats.get(level).clone();
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
