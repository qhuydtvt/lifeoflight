package lol.gameentities.stataffects.processors;

import lol.gameentities.CombatStat;
import lol.gameentities.stataffects.operators.StatOperator;

/**
 * Created by huynq on 8/7/17.
 */
public class LuckAffectProcessor extends StatAffectProcessor {

    public LuckAffectProcessor(float amount, StatOperator operator) {
        super(amount, operator);
    }

    @Override
    public void affect(CombatStat currentStat, CombatStat originStat) {
        currentStat.luck = (int)operator.op(currentStat.luck, originStat.luck, amount);
    }
}
