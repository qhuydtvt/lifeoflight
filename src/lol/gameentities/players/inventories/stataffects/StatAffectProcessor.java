package lol.gameentities.players.inventories.stataffects;

import lol.gameentities.players.PlayerStat;

/**
 * Created by huynq on 8/5/17.
 */
public interface StatAffectProcessor {
    void affect(PlayerStat currentStat, PlayerStat originStat);
}
