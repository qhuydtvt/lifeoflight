package lol.gameentities.items.stataffects.processors;

import lol.gameentities.CombatStat;
import lol.gameentities.items.stataffects.operators.StatOperator;

/**
 * Created by huynq on 8/7/17.
 */
public class DexAffectProcessor extends StatAffectProcessor {
    public DexAffectProcessor(float amount, StatOperator operator) {
        super(amount, operator);
    }

    @Override
    public void affect(CombatStat currentStat, CombatStat originStat) {
        currentStat.dex = (int)operator.op(currentStat.dex, originStat.dex, amount);
    }
}
