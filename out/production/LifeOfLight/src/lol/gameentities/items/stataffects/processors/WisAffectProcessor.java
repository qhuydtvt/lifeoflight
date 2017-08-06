package lol.gameentities.items.stataffects.processors;

import lol.gameentities.CombatStat;
import lol.gameentities.items.stataffects.operators.StatOperator;

/**
 * Created by huynq on 8/7/17.
 */
public class WisAffectProcessor extends StatAffectProcessor {

    public WisAffectProcessor(float amount, StatOperator operator) {
        super(amount, operator);
    }

    @Override
    public void affect(CombatStat currentStat, CombatStat originStat) {
        currentStat.wis = (int)operator.op(currentStat.wis, originStat.wis, amount);
    }
}
