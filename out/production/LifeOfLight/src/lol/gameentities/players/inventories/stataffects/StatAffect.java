package lol.gameentities.players.inventories.stataffects;

import lol.gameentities.players.inventories.stataffects.processors.HPAffectProcessor;
import lol.gameentities.players.inventories.stataffects.processors.StatAffectProcessor;
import lol.gameentities.players.inventories.stataffects.processors.StrengthAffectProcessor;
import lol.gameentities.players.inventories.stataffects.operators.AddStatOperator;
import lol.gameentities.players.inventories.stataffects.operators.MulStatOperator;
import lol.gameentities.players.inventories.stataffects.operators.StatOperator;

/**
 * Created by huynq on 8/5/17.
 */
public class StatAffect {
    public String statName;
    public float amount;
    public boolean isMultiply;

    public StatAffect() {
    }

    public StatAffect(String statName, float amount, boolean isMultiply) {
        this.statName = statName;
        this.amount = amount;
        this.isMultiply = isMultiply;
    }

    public StatAffectProcessor getProcessor() {
        StatOperator operator = isMultiply ? new MulStatOperator() : new AddStatOperator();
        switch (statName.toUpperCase()) {
            case "HP":
                return new HPAffectProcessor((int) amount, operator);
            case "STR":
                return new StrengthAffectProcessor((int) amount, operator);
        }
        return null;
    }

    public String dialog() {
        if (isMultiply) {
            return String.format("Increased %s by %s percent", statName, (amount - 1)* 100 );
        } else {
            return String.format("Increased %s by %s", statName, amount);
        }
    }
}
