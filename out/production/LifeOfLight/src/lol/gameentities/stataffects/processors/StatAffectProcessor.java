package lol.gameentities.stataffects.processors;

import lol.gameentities.CombatStat;
import lol.gameentities.stataffects.operators.StatOperator;

/**
 * Created by huynq on 8/5/17.
 */
public abstract class StatAffectProcessor {
    protected float amount;
    protected StatOperator operator;

    public StatAffectProcessor(float amount, StatOperator operator) {
        this.amount = amount;
        this.operator = operator;
    }

    public abstract void affect(CombatStat currentStat, final CombatStat originStat);

    @Override
    public String toString() {
        return "StatAffectProcessor{" +
                "amount=" + amount +
                ", operator=" + operator +
                '}';
    }
}
