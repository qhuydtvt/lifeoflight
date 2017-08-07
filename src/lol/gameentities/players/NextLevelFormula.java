package lol.gameentities.players;

import com.google.gson.annotations.SerializedName;

import com.udojava.evalex.Expression;
import lol.gameentities.CombatStat;

/**
 * Created by huynq on 8/6/17.
 */
public class NextLevelFormula {

    @SerializedName("nextLevelMinExp")
    public String nextLevelMinExp;
    @SerializedName("maxHP")
    public String maxhp;
    @SerializedName("hp")
    public String hp;
    @SerializedName("mana")
    public String mana;
    @SerializedName("stamina")
    public String stamina;
    @SerializedName("strRate")
    public String strrate;
    @SerializedName("dexRate")
    public String dexrate;
    @SerializedName("wisRate")
    public String wisRate;
    @SerializedName("str")
    public String str;
    @SerializedName("dex")
    public String dex;
    @SerializedName("wis")
    public String wis;
    @SerializedName("luck")
    public String luck;
    @SerializedName("vision")
    public String vision;

    public NextLevelFormula() {
    }

    private float eval(String formula, CombatStat stat) {
        String formulaWithValues = formula
                .replace("{nextLevelMinExp}", stat.nextLevelMinExp.toString())
                .replace("{maxHp}", stat.maxHp + "")
                .replace("{hp}", stat.hp.toString())
                .replace("{mana}", stat.mana.toString())
                .replace("{strRate}", stat.strRate.toString())
                .replace("{dexRate}", stat.dexRate.toString())
                .replace("{wisRate}", stat.wisRate.toString())
                .replace("{str}", stat.str.toString())
                .replace("{dex}", stat.dex.toString())
                .replace("{wis}", stat.wis.toString())
                .replace("{luck}", stat.luck.toString())
                .replace("{vision}", stat.vision.toString());
        return new Expression(formulaWithValues).eval().floatValue();
    }


    public CombatStat calculate(CombatStat oldStat) {
        CombatStat stat = new CombatStat();
        stat.nextLevelMinExp = (int) eval(nextLevelMinExp, oldStat);
        stat.maxHp = (int) eval(maxhp, oldStat);
        stat.hp = (int) eval(hp, oldStat);
        stat.mana = (int) eval(mana, oldStat);
        stat.strRate = eval(strrate, oldStat);
        stat.dexRate = eval(dexrate, oldStat);
        stat.wisRate = eval(wisRate, oldStat);
        stat.str = (int) eval(str, oldStat);
        stat.dex = (int) eval(dex, oldStat);
        stat.wis = (int) eval(wis, oldStat);
        stat.luck = (int) eval(luck, oldStat);
        stat.vision = (int) eval(vision, oldStat);
        return stat;
    }
}
