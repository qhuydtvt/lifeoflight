package lol.gameentities.stataffects.processors;

import lol.gameentities.CombatStat;
import lol.gameentities.stataffects.operators.StatOperator;

/**
 * Created by huynq on 8/7/17.
 */
public class MaxHpAffectProcessor extends StatAffectProcessor {
    public MaxHpAffectProcessor(float amount, StatOperator operator) {
        super(amount, operator);
    }

    @Override
    public void affect(CombatStat currentStat, final CombatStat originStat) {
        currentStat.maxHp = (int)operator.op(currentStat.maxHp, originStat.maxHp, amount);
    }
}
