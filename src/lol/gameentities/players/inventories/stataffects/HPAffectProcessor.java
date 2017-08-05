package lol.gameentities.players.inventories.stataffects;

import lol.gameentities.players.PlayerStat;
import lol.gameentities.players.inventories.stataffects.statoperators.StatOperator;

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
    public void affect(PlayerStat currentStat, PlayerStat originStat) {
        currentStat.hp = (int)operator.op(currentStat.hp, originStat.hp, amount);
    }
}
