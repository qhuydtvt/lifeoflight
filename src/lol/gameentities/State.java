package lol.gameentities;

import lol.gameentities.players.Player;
import lol.gameentities.maps.Map;

/**
 * Created by huynq on 7/30/17.
 */
public class State {
    private Map map = null;
    private Player player = new Player();

    public static State instance = new State();

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
}
