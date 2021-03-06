package lol.gameentities.stataffects.processors;

import lol.gameentities.CombatStat;
import lol.gameentities.stataffects.operators.StatOperator;

/**
 * Created by huynq on 8/7/17.
 */
public class StaminaAffectProcessor extends StatAffectProcessor {

    public StaminaAffectProcessor(float amount, StatOperator operator) {
        super(amount, operator);
    }

    @Override
    public void affect(CombatStat currentStat, final CombatStat originStat) {
        currentStat.stamina = (int)operator.op(currentStat.stamina, originStat.stamina, amount);
    }
}
