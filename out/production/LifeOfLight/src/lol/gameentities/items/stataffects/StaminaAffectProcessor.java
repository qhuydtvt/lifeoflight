package lol.gameentities.items.stataffects;

import lol.gameentities.CombatStat;
import lol.gameentities.items.stataffects.operators.StatOperator;
import lol.gameentities.items.stataffects.processors.StatAffectProcessor;

/**
 * Created by huynq on 8/7/17.
 */
public class StaminaAffectProcessor extends StatAffectProcessor {

    public StaminaAffectProcessor(float amount, StatOperator operator) {
        super(amount, operator);
    }

    @Override
    public void affect(CombatStat currentStat, CombatStat originStat) {
        currentStat.stamina = (int)operator.op(currentStat.stamina, originStat.stamina, amount);
    }
}
