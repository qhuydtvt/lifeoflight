package lol.gameevents.processors.combat;

import lol.events.EventManager;
import lol.gameentities.State;
import lol.gameentities.monsters.Monster;
import lol.gameentities.players.Player;
import lol.gameentities.skills.Skill;
import lol.gameevents.CombatEvent;
import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.util.List;

/**
 * Created by huynq on 9/15/17.
 */
public class SingleTargetSkillProcessor extends Processor {

    private String skillId;

    public SingleTargetSkillProcessor(String skillId) {
        this.skillId = skillId;
    }

    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        if (commands.size() == 0) {
            EventManager.pushUIMessage("Bạn chưa chọn mục tiêu");
            return null;
        }

        List<Monster> monsters = ((CombatEvent)currentEvent).getMonsters();
        int targetMonsterIndex = Integer.parseInt(commands.get(0));
        if (targetMonsterIndex >= monsters.size() || targetMonsterIndex < 0) {
            EventManager.pushUIMessage("Không chọn được mục tiêu");
            return null;
        }

        Player player = State.instance.getPlayer();

        Monster monster = monsters.get(targetMonsterIndex);
        Skill skill = player.getSkill(skillId);
        player.useSkill(skillId, monster);

        EventManager.pushUIMessage(String.format("%s đã trúng %s", monster.getName(), skill.getName()));
        EventManager.pushUIMessage(monster.getInfo());
        return null;
    }
}
