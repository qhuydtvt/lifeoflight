package lol.formulas;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lol.bases.Utils;
import lol.gameentities.CombatUnit;

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

        PhysicsAttackResult(int damage, boolean isCriticalAttack) {
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

    public PhysicsAttackResult physicsAttack(CombatUnit unit) {
        String critChanceTemplate = critchance.replace("{basePdmg}", safe(basepdmg));
        Float critChance = evaluate(render(critChanceTemplate, unit));

        String finalTemplate = patk
                .replace("{critBonusDmg}", safe(critbonusdmg))
                .replace("{critChance}", critChance.toString())
                .replace("{basePdmg}", safe(basepdmg));
        int damage = (int)((float)evaluate(render(finalTemplate, unit)));
        return new PhysicsAttackResult(damage, critChance == 1.0f);
    }

    public String render(String template, CombatUnit unit) {
        return template
                .replace("{player.hp}", statStr(unit.stat.hp))
                .replace("{player.mana}", statStr(unit.stat.mana))
                .replace("{player.stamina}", statStr(unit.stat.stamina))
                .replace("{player.str}", statStr(unit.stat.str))
                .replace("{player.dex}", statStr(unit.stat.dex))
                .replace("{player.wis}", statStr(unit.stat.wis))
                .replace("{player.luck}", statStr(unit.stat.luck))
                .replace("{player.vision}", statStr(unit.stat.vision))
                .replace("{player.maxHp}", statStr(unit.stat.maxHp))
                .replace("{player.strRate}", statStr(unit.stat.strRate));
    }

    public boolean doge(CombatUnit unit) {
        return evaluate(render(dodgechance, unit)) == 1.0f;
    }
}
