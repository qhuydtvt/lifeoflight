package lol.gameentities.stataffects.processors;

import lol.gameentities.CombatStat;
import lol.gameentities.stataffects.operators.StatOperator;

/**
 * Created by huynq on 8/5/17.
 */
public class StrAffectProcessor extends StatAffectProcessor {

    public StrAffectProcessor(float amount, StatOperator operator) {
        super(amount, operator);
    }

    @Override
    public void affect(CombatStat currentStat, CombatStat originStat) {
        currentStat.str = (int)operator.op(currentStat.str, originStat.str, amount);
    }
}
