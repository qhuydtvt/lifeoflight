package lol.formulas;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lol.bases.Utils;

/**
 * Created by huynq on 8/3/17.
 */
public class CombatFormula extends Formula {

    @SerializedName("basePdmg")
    private String basepdmg;
    @SerializedName("baseMdmg")
    private String basemdmg;
    @SerializedName("critChance")
    private String critchance;
    @SerializedName("critBonusDmg")
    private String critbonusdmg;
    @SerializedName("pAtk")
    private String patk;
    @SerializedName("mAtk")
    private String matk;
    @SerializedName("dodgeChance")
    private String dodgechance;
    @SerializedName("hpRegen")
    private String hpregen;
    @SerializedName("staRegen")
    private String staregen;
    @SerializedName("manaRegen")
    private String manaregen;

    public static final CombatFormula instance;

    static {
        String formulaContent = Utils.loadFileContent("assets/combat/combat_formula.json");
        instance = new Gson().fromJson(formulaContent, CombatFormula.class);
    }

    public int physicsAttack() {
        String finalPhysicsFormula = patk
                .replace("{critBonusDmg}", safe(critbonusdmg))
                .replace("{critChance}", safe(critchance))
                .replace("{basePdmg}", safe(basepdmg));
        System.out.println(finalPhysicsFormula);
        return -1;
    }

    public String getBasepdmg() {
        return basepdmg;
    }

    public void setBasepdmg(String basepdmg) {
        this.basepdmg = basepdmg;
    }

    public String getBasemdmg() {
        return basemdmg;
    }

    public void setBasemdmg(String basemdmg) {
        this.basemdmg = basemdmg;
    }

    public String getCritchance() {
        return critchance;
    }

    public void setCritchance(String critchance) {
        this.critchance = critchance;
    }

    public String getCritbonusdmg() {
        return critbonusdmg;
    }

    public void setCritbonusdmg(String critbonusdmg) {
        this.critbonusdmg = critbonusdmg;
    }

    public String getPatk() {
        return patk;
    }

    public void setPatk(String patk) {
        this.patk = patk;
    }

    public String getMatk() {
        return matk;
    }

    public void setMatk(String matk) {
        this.matk = matk;
    }

    public String getDodgechance() {
        return dodgechance;
    }

    public void setDodgechance(String dodgechance) {
        this.dodgechance = dodgechance;
    }

    public String getHpregen() {
        return hpregen;
    }

    public void setHpregen(String hpregen) {
        this.hpregen = hpregen;
    }

    public String getStaregen() {
        return staregen;
    }

    public void setStaregen(String staregen) {
        this.staregen = staregen;
    }

    public String getManaregen() {
        return manaregen;
    }

    public void setManaregen(String manaregen) {
        this.manaregen = manaregen;
    }
}
