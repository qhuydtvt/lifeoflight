package lol.gameentities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lol.bases.Utils;
import lol.gameentities.players.Player;
import lol.gameentities.maps.Map;

import java.io.File;

/**
 * Created by huynq on 7/30/17.
 */
public class State {

    private Map map = null;

    @SerializedName("player")
    private Player player = null;

    @SerializedName("current_level")
    private int currentLevel;

    private static final String STATE_URL = "data/state.json";

    private static final String PLAYER_DATA_URL = "data/player.json";
    private static final String DATA_URL = "data/main_data.json";
    private static final String DATA_FOLDER = "data";

    public static final State instance = new State();

    private State() {
        currentLevel = 0;
        this.player = new Player();
    }

    public void load() {
        if (Utils.fileExists(DATA_URL)) {
            State savedState = new Gson().fromJson(Utils.loadFileContent(DATA_URL), State.class);
            instance.player = savedState.player;
            instance.currentLevel = savedState.currentLevel;
            instance.map = savedState.map;
        } else {
            instance.loadInitialPlayer();
            instance.loadInitialMap();
        }
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

    public void loadNextMap() {
        currentLevel++;
        loadMap(mapUrl(currentLevel));
    }

    public void removeItemAtPlayerPosition() {
        map.removeItem(player.mapPosition);
    }

    private String mapUrl(int lvl) {
        return String.format("assets/maps/lvl%s.txt", lvl);
    }

    private void loadMap(String url) {
        map = Map.parseFile(url);
        assert map != null;
        player.mapPosition.set(map.getPlayerStartX(), map.getPlayerStartY());
    }

    private void loadInitialPlayer() {
        loadPlayer("assets/character/character_list.json");
        player.loadStat(0);
    }

    private void loadPlayer(String url) {
        player = Player.parseFile(url);
    }

    public void rebirth() {
        player.rebirth();
        player.mapPosition.set(map.getPlayerStartX(), map.getPlayerStartY());
    }
}
