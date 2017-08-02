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

    public class PhysicsAttackResult {
        public int damage;
        public boolean isCriticalAttack;

        public PhysicsAttackResult(int damage, boolean isCriticalAttack) {
            this.damage = damage;
            this.isCriticalAttack = isCriticalAttack;
        }

        @Override
        public String toString() {
            return "PhysicsAttackResult{" +
                    "damage=" + damage +
                    ", isCriticalAttack=" + isCriticalAttack +
                    '}';
        }
    }

    public PhysicsAttackResult physicsAttack() {
        String critChanceTemplate = critchance.replace("{basePdmg}", safe(basepdmg));
        Float critChance = evaluate(critChanceTemplate);

        String finalTemplate = patk
                .replace("{critBonusDmg}", safe(critbonusdmg))
                .replace("{critChance}", critChance.toString())
                .replace("{basePdmg}", safe(basepdmg));
        int damage = (int) evaluate(finalTemplate);
        return new PhysicsAttackResult(damage, critChance == 1.0f);
    }

    public boolean doge() {
        return evaluate(dodgechance) == 1.0f;
    }
}
