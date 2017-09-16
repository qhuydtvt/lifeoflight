package lol.gameentities.skills;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lol.bases.Utils;
import lol.gameentities.CombatStat;
import lol.gameentities.CombatUnit;
import lol.gameentities.stataffects.StatAffect;
import lol.gameevents.CombatEvent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by huynq on 9/15/17.
 */
public class Skill implements Cloneable {

    public static final int SKILL_SINGLE_TARGET = 0;
    public static final int SKILL_MULTIPLE_TARGET = 1;

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("level")
    private int level;
    @SerializedName("manaTaken")
    private int manaTaken;
    @SerializedName("rangeType")
    private int rangeType;
    @SerializedName("statAffects")
    private List<StatAffect> statAffects;

    private static List<Skill> allSkills;

    public List<StatAffect> getStatAffects() {
        return statAffects;
    }

    static {
        String allSkillContent = Utils.loadFileContent("assets/skills/skills.json");
        if (allSkillContent != null) {
            Type allSkillType = new TypeToken<List<Skill>>() {
            }.getType();
            allSkills = new Gson().fromJson(allSkillContent, allSkillType);
            System.out.println(allSkills);
        }
    }

    public void upgrade() {
        level ++;
    }

    public static Skill find(String id) {
        Optional<Skill> skillOPt = allSkills.stream().filter(skill -> skill.id.equalsIgnoreCase(id)).findFirst();
        return skillOPt.orElse(null);
    }

    public void affect(List<? extends CombatUnit> combatUnits) {
        combatUnits.forEach(this::affect);
    }

    public <T extends CombatUnit> void affect(T unit) {
        statAffects.forEach(statAffect -> {
            CombatStat newStat = unit.getStat().clone();
            statAffect.getProcessor().affect(newStat, unit.getStat());
            unit.setStat(newStat);
        });
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getRange() {
        return rangeType;
    }

    public int getManaTaken() {
        return manaTaken;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", manaTaken=" + manaTaken +
                ", rangeType=" + rangeType +
                ", statAffects=" + statAffects +
                '}';
    }
}
