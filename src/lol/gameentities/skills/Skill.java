package lol.gameentities.skills;

import lol.gameentities.CombatUnit;
import lol.gameentities.monsters.Monster;

import java.util.List;

/**
 * Created by huynq on 9/15/17.
 */
public class Skill {
    private String name;
    private int level;

    public List<SkillResult> affect(List<CombatUnit> combatUnits) {

        return null;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}
