package lol.gameentities.players.inventories.stataffects.processors;

import lol.gameentities.CombatStat;
import lol.gameentities.players.inventories.stataffects.operators.StatOperator;

/**
 * Created by huynq on 8/5/17.
 */
public class StrengthAffectProcessor implements StatAffectProcessor {
    private int amount;
    private StatOperator operator;

    public StrengthAffectProcessor(int amount, StatOperator operator) {
        this.amount = amount;
        this.operator = operator;
    }

    @Override
    public void affect(CombatStat currentStat, CombatStat originStat) {
        currentStat.str = (int)operator.op(currentStat.str, originStat.str, amount);
    }
}
