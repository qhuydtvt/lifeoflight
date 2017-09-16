package lol.gameentities.stataffects.processors;

import lol.gameentities.CombatStat;
import lol.gameentities.stataffects.operators.StatOperator;

/**
 * Created by huynq on 8/7/17.
 */
public class VisionAffectProcessor extends StatAffectProcessor {

    public VisionAffectProcessor(float amount, StatOperator operator) {
        super(amount, operator);
    }

    @Override
    public void affect(CombatStat currentStat, CombatStat originStat) {
        currentStat.vision = (int)operator.op(currentStat.vision, originStat.vision, amount);
    }
}
