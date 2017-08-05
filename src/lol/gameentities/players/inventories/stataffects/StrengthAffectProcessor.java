package lol.gameentities.players.inventories.stataffects;

import lol.gameentities.players.PlayerStat;
import lol.gameentities.players.inventories.stataffects.statoperators.StatOperator;

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
    public void affect(PlayerStat currentStat, PlayerStat originStat) {
        currentStat.str = (int)operator.op(currentStat.str, originStat.str, amount);
    }
}
