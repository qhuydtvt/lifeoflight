package lol.gameentities.players;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lol.bases.Utils;
import lol.gameentities.CombatStat;
import lol.gameentities.CombatUnit;
import lol.gameentities.MapPosition;
import lol.gameentities.items.GameItem;
import lol.settings.Settings;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static lol.gameentities.items.GameItem.*;

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
    public List<GameItem> gameItems;

    @SerializedName("current_level")
    public Integer currentLevel;

    @SerializedName("nextLevelFormula")
    public NextLevelFormula nextLevelFormula;

    public List<GameItem> handItems;
    public GameItem bodyItem;
    public GameItem headItem;
    public GameItem feetItem;

    public MapPosition mapPosition;

    private transient CombatStat temporaryStat;

    public Player() {
        mapPosition = new MapPosition();
        handItems = new ArrayList<>();
    }

    public void init() {
        recalculateStat();
    }

    public void changeMaxHP(int amount) {
        stat.maxHp += amount;
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

    public GameItem getItem(String id) {
        for (GameItem item : this.gameItems) {
            if (item.id.equals(id)) {
                return item;
            }
        }
        return null;
    }

    //TODO: Message
    public boolean use(GameItem item) {
        if (!this.gameItems.contains(item)) return false;
        if (item.type == GameItem.TYPE_ONE_TIME) {
            CombatStat newStat = this.stat.clone();
            item.affect(newStat, this.stat);
            if (Settings.DEBUG) {
                System.out.println(item);
            }
            this.stat = newStat;
            this.gameItems.remove(item);
            recalculateStat();
            return true;
        } else if (item.isWearable()) {
            switch (item.type) {
                case TYPE_ONE_HAND:
                    if (handsBusy(1)) {
                        return false;
                    } else {
                        handItems.add(item);
                        this.gameItems.remove(item);
                    }
                    break;
                case TYPE_TWO_HAND:
                    if (handsBusy(2)) {
                        return false;
                    } else {
                        handItems.add(item);
                        this.gameItems.remove(item);
                    }
                    break;
                case TYPE_BODY:
                    if (bodyItem != null) return false;
                    bodyItem = item;
                    this.gameItems.remove(item);
                    break;
                case TYPE_HEAD:
                    if (headItem != null) return false;
                    headItem = item;
                    this.gameItems.remove(item);
                    break;
                case TYPE_FEET:
                    if (feetItem != null) return false;
                    feetItem = item;
                    this.gameItems.remove(item);
                    break;
            }
            recalculateStat();
            return true;
        }
        return false;
    }

    private boolean handsBusy(int more) {
        return handItems.size() != 0 && (handItems.get(0).type == TYPE_TWO_HAND ||
                handItems.size() == 2 ||
                (handItems.size() + more > 2));
    }

    private void recalculateStat() {
        System.out.println("Recalculating stats");
        temporaryStat = stat.clone();

        List<GameItem> items = new ArrayList<>();
        items.addAll(handItems);
        items.add(bodyItem);
        items.add(headItem);
        items.add(feetItem);
        for (GameItem gameItem : items) {
            if (gameItem != null) {
                if (Settings.DEBUG) {
                    System.out.println(gameItem);
                }
                gameItem.affect(temporaryStat, this.stat);
            }
        }
    }

    public void collect(GameItem item) {
        // TODO: check inventory capacity
        this.gameItems.add(item);
    }

    public void getHit(int damage) {
        stat.hp -= damage;
        if (this.stat.hp < 0) stat.hp = 0;
        recalculateStat();
    }

    @Override
    public CombatStat getStat() {
        return temporaryStat;
    }

    public CombatStat getOriginStat() {
        return stat;
    }

    public void rebirth() {
        this.stat.hp = this.stat.maxHp;
        this.exp = this.exp * 75 / 100;
        recalculateStat();
    }

    public static Player parseFile(String url) {
        Type playerListType = new TypeToken<List<Player>>() {
        }.getType();
        List<Player> playerConfigs = new Gson().fromJson(Utils.loadFileContent(url), playerListType);
        if (playerConfigs.size() == 0) {
            System.out.println("Player config file is empty or there was problem with loading this file");
            return null;
        } else {
            System.out.println(playerConfigs.get(0));
            return playerConfigs.get(0);
        }
    }

    public GameItem store(String itemId) {
        GameItem result = null;

        if (headItem!= null &&headItem.id.equalsIgnoreCase(itemId)) {
            this.gameItems.add(headItem);
            result = headItem;
            headItem = null;
        } else if (bodyItem != null && bodyItem.id.equalsIgnoreCase(itemId)) {
            this.gameItems.add(bodyItem);
            result = bodyItem;
            bodyItem = null;
        } else if (feetItem != null && feetItem.id.equalsIgnoreCase(itemId)) {
            this.gameItems.add(feetItem);
            result = feetItem;
            feetItem = null;
        } else {
            Iterator<GameItem> iterator = handItems.iterator();
            while (iterator.hasNext()) {
                GameItem gameItem = iterator.next();
                if (gameItem.id.equalsIgnoreCase(itemId)) {
                    gameItems.add(gameItem);
                    result = gameItem;
                    iterator.remove();
                }
            }
        }

        recalculateStat();

        return result;
    }
}
