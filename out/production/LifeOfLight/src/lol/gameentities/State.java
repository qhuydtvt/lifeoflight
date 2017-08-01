package lol.gameentities;

import lol.gameentities.players.Player;
import lol.gameentities.maps.Map;

/**
 * Created by huynq on 7/30/17.
 */
public class State {
    private Map map = null;
    private Player player = new Player();
    private int currentLevel;

    public static State instance = new State();

    private State() {
        currentLevel = 0;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "" +
                "map=" + map +
                ", player=" + player;
    }

    public void loadInitialMap() {
        loadMap(mapUrl(currentLevel));
    }

    public void loadNextMap() {
        currentLevel++;
        loadMap(mapUrl(currentLevel));
    }

    private String mapUrl(int lvl) {
        return String.format("assets/maps/lvl%s.txt", lvl);
    }

    private void loadMap(String url) {
        map = Map.parseFile(url);
        player.setPosition(map.getPlayerStartX(), map.getPlayerStartY());
    }
}
