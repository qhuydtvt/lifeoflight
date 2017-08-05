package lol.gameentities.players.inventories.stataffects;

import lol.gameentities.players.inventories.stataffects.statoperators.AddStatOperator;
import lol.gameentities.players.inventories.stataffects.statoperators.MulStatOperator;
import lol.gameentities.players.inventories.stataffects.statoperators.StatOperator;

/**
 * Created by huynq on 8/5/17.
 */
public class StatAffect {
    public String statName;
    public float amount;
    public boolean isMultiply;

    public StatAffectProcessor getProcessor() {
        StatOperator operator = isMultiply ? new AddStatOperator() : new MulStatOperator();
        switch (statName.toUpperCase()) {
            case "HP":
                return new HPAffectProcessor((int) amount, operator);
            case "STR":
                return new StrengthAffectProcessor((int) amount, operator);
        }
        return null;
    }
}
