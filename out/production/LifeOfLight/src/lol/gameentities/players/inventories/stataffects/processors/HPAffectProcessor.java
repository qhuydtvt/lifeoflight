package lol.gameentities.players.inventories.stataffects.processors;

import lol.gameentities.CombatStat;
import lol.gameentities.players.inventories.stataffects.operators.StatOperator;

/**
 * Created by huynq on 8/5/17.
 */
public class HPAffectProcessor implements StatAffectProcessor {
    private int amount;
    private StatOperator operator;

    public HPAffectProcessor(int amount, StatOperator operator) {
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
