package lol.gameentities.stataffects.processors;

import lol.gameentities.CombatStat;
import lol.gameentities.stataffects.operators.StatOperator;

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
        if (currentStat.maxHp > 0 && currentStat.hp > currentStat.maxHp) {
            currentStat.hp = currentStat.maxHp;
        }

        if (currentStat.hp < 0) currentStat.hp = 0;
    }
}
