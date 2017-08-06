package lol.gameentities.items.stataffects.processors;

import lol.gameentities.CombatStat;

/**
 * Created by huynq on 8/5/17.
 */
public interface StatAffectProcessor {
    void affect(CombatStat currentStat, CombatStat originStat);
}
