package lol.gameentities.items.stataffects.processors;

import lol.gameentities.CombatStat;
import lol.gameentities.items.stataffects.operators.StatOperator;

/**
 * Created by huynq on 8/5/17.
 */
public class HPAffectProcessor implements StatAffectProcessor {
    private float amount;
    private StatOperator operator;

    public HPAffectProcessor(float amount, StatOperator operator) {
        this.amount = amount;
        this.operator = operator;
    }

    @Override
    public void affect(CombatStat currentStat, CombatStat originStat) {
        currentStat.hp = (int)operator.op(currentStat.hp, originStat.hp, amount);
        if (currentStat.hp > currentStat.maxHp) {
            currentStat.hp = currentStat.maxHp;
        }
    }
}
