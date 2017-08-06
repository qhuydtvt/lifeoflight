package lol.gameentities.items.stataffects;

import com.google.gson.Gson;
import lol.formulas.Formula;
import lol.gameentities.items.stataffects.processors.HPAffectProcessor;
import lol.gameentities.items.stataffects.processors.StatAffectProcessor;
import lol.gameentities.items.stataffects.processors.StrengthAffectProcessor;
import lol.gameentities.items.stataffects.operators.AddStatOperator;
import lol.gameentities.items.stataffects.operators.MulStatOperator;
import lol.gameentities.items.stataffects.operators.StatOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynq on 8/5/17.
 */
public class StatAffect implements Cloneable {

    public String statName;
    public String amount;
    public boolean isMultiply;

    public StatAffect() {
    }

    public StatAffect(String statName, String amount, boolean isMultiply) {
        this.statName = statName;
        this.amount = amount;
        this.isMultiply = isMultiply;
    }

    private float amount() {
        return Float.parseFloat(amount);
    }

    public StatAffectProcessor getProcessor() {
        StatOperator operator = isMultiply ? new MulStatOperator() : new AddStatOperator();
        switch (statName.toUpperCase()) {
            case "HP":
                return new HPAffectProcessor(amount(), operator);
            case "STR":
                return new StrengthAffectProcessor(amount(), operator);
        }
        return null;
    }

    public String dialog() {
        if (isMultiply) {
            return String.format("Increased %s by %s percent", statName, (amount() - 1) * 100);
        } else {
            return String.format("Increased %s by %s", statName, amount);
        }
    }

    public static List<StatAffect> solidify(List<StatAffect> affects) {
        ArrayList<StatAffect> returnAffects = new ArrayList<>();
        for (StatAffect affect: affects) {
            returnAffects.add(affect.solidify());
        }
        return returnAffects;
    }

    public StatAffect solidify() {
        StatAffect solidifiedAffect = this.clone();
        solidifiedAffect.amount = Formula.render(this.amount);
        return solidifiedAffect;
    }

    @Override
    public int hashCode() {
        return String.format("%s %s %s", statName, amount, isMultiply).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StatAffect
                && statName.equals(((StatAffect) obj).statName)
                && isMultiply == ((StatAffect) obj).isMultiply;
    }

    public StatAffect clone() {
        String JSONString = new Gson().toJson(this);
        return new Gson().fromJson(JSONString, StatAffect.class);
    }

    public StatAffect add(StatAffect other) {
        StatAffect statAffect = this.clone();
        statAffect.amount += other.amount;
        return statAffect;
    }
}
