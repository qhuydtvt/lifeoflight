package lol.gameentities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lol.bases.Utils;
import lol.gameentities.monsters.Monster;
import lol.gameentities.players.Player;
import lol.gameentities.maps.Map;
import lol.settings.Settings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynq on 7/30/17.
 */
public class State {

    private Map map = null;

    @SerializedName("player")
    private Player player = null;

    @SerializedName("current_level")
    private int currentLevel;

    @SerializedName("usedItemIds")
    private List<String> usedItemIds;

//    private transient List<Monster> monstersInMap;

    private static final String DATA_URL = "data/main_data.json";
    private static final String DATA_FOLDER = "data";

    public static final State instance = new State();

    private State() {
        if (Settings.TEST) {
            currentLevel = 3;
        } else {
            currentLevel = 1;
        }
        this.player = new Player();
    }

    public void load() {
        if (Utils.fileExists(DATA_URL)) {
            State savedState = new Gson().fromJson(Utils.loadFileContent(DATA_URL), State.class);
            instance.player = savedState.player;
            instance.player.init();
            instance.currentLevel = savedState.currentLevel;
            instance.map = savedState.map;
        } else {
            instance.loadInitialPlayer();
            instance.loadInitialMap();
        }
    }

//    private void loadMonsterInMap() {
//        System.out.println("Loading monsters in map");
//        monstersInMap = Monster.monsterInLevel(currentLevel);
//        System.out.println(monstersInMap);
//    }

    public void addUsedItem(String itemId) {
        if (usedItemIds == null) {
            usedItemIds = new ArrayList<>();
        }
        if (usedItemIds.contains(itemId)) {
            System.out.println("Item already added");
        } else {
            usedItemIds.add(itemId);
        }
    }

    public boolean itemAlreadyUsed(String itemId) {
        if (usedItemIds == null) return false;
        return usedItemIds.contains(itemId);
    }

    public void save() {
        if (!Utils.fileExists(DATA_FOLDER)) {
            new File(DATA_FOLDER).mkdir();
        }
        Utils.saveFileContent(DATA_URL, new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(this));
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "" +
                "map=" + map +
                ", player=" + player;
    }

    private void loadInitialMap() {
        loadMap(mapUrl(currentLevel));
    }

    public void reset() {
        currentLevel = 1;
        loadMap(mapUrl(currentLevel));
    }

    public boolean loadNextMap() {
        currentLevel++;
        if (!mapExists(currentLevel)) {
            return false;
        } else {
            loadMap(mapUrl(currentLevel));
            return true;
        }
    }

    private boolean mapExists(int currentLevel) {
        return new File(mapUrl(currentLevel)).exists();
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void removeItemAtPlayerPosition() {
        map.removeItem(player.mapPosition);
    }

    private String mapUrl(int lvl) {
        return String.format("assets/maps/lvl%s.txt", lvl);
    }

    private void loadMap(String url) {
        map = Map.parseFile(url);
        player.mapPosition.set(map.getPlayerStartX(), map.getPlayerStartY());
    }

    public Monster randomMonster(int monsterLevel) {
        return Utils.choice(Monster.monsterInLevel(monsterLevel));
    }

    private void loadInitialPlayer() {
        loadPlayer("assets/character/character_list.json");
    }

    private void loadPlayer(String url) {
        player = Player.parseFile(url);
        player.init();
    }

    public void rebirth() {
        player.rebirth();
        player.mapPosition.set(map.getPlayerStartX(), map.getPlayerStartY());
    }
}
