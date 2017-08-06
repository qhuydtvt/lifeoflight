package lol.gameentities.items.stataffects;

import com.google.gson.Gson;
import lol.formulas.Formula;
import lol.gameentities.items.stataffects.processors.*;
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
                return new HpAffectProcessor(amount(), operator);
            case "STR":
                return new StrAffectProcessor(amount(), operator);
            case "DEX":
                return new DexAffectProcessor(amount(), operator);
            case "WIS":
                return new WisAffectProcessor(amount(), operator);
            case "LUCK":
                return new LuckAffectProcessor(amount(), operator);
            case "STAMINA":
                return new StaminaAffectProcessor(amount(), operator);
        }
        System.out.println(String.format("Processor is not implemented yet %s", statName));
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
        solidifiedAffect.amount = Formula.evaluate(this.amount).toString();
        return solidifiedAffect;
    }

    @Override
    public int hashCode() {
        return String.format("%s %s", statName, isMultiply).hashCode();
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

    public StatAffect addFormula(StatAffect other) {
        StatAffect statAffect = this.clone();
        statAffect.amount = String.format("(%s + %s)", statAffect.amount, other.amount);
        return statAffect;
    }

    @Override
    public String toString() {
        return "StatAffect{" +
                "statName='" + statName + '\'' +
                ", amount='" + amount + '\'' +
                ", isMultiply=" + isMultiply +
                '}';
    }
}
