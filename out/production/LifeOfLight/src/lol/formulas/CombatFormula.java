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
        String finalTemplate = patk
                .replace("{critBonusDmg}", safe(critbonusdmg))
                .replace("{critChance}", safe(critchance))
                .replace("{basePdmg}", safe(basepdmg));
        System.out.println(render(finalTemplate));
        return (int)evaluate(finalTemplate);
    }
}
