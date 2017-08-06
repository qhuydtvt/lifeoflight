package lol.gameentities.items.stataffects.processors;

import lol.gameentities.CombatStat;
import lol.gameentities.items.stataffects.operators.StatOperator;

/**
 * Created by huynq on 8/5/17.
 */
public class HpAffectProcessor extends StatAffectProcessor {

    public HpAffectProcessor(float amount, StatOperator operator) {
        super(amount, operator);
    }

    @Override
    public void affect(CombatStat currentStat, CombatStat originStat) {
        currentStat.hp = (int)operator.op(currentStat.hp, originStat.hp, amount);
        if (currentStat.hp > currentStat.maxHp) {
            currentStat.hp = currentStat.maxHp;
        }
    }
}
