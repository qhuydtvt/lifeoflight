package lol.gameentities.items.stataffects.processors;

import lol.gameentities.CombatStat;
import lol.gameentities.items.stataffects.operators.StatOperator;

/**
 * Created by huynq on 8/5/17.
 */
public class StrengthAffectProcessor implements StatAffectProcessor {
    private float amount;
    private StatOperator operator;

    public StrengthAffectProcessor(float amount, StatOperator operator) {
        this.amount = amount;
        this.operator = operator;
    }

    @Override
    public void affect(CombatStat currentStat, CombatStat originStat) {
        currentStat.str = (int)operator.op(currentStat.str, originStat.str, amount);
    }
}
