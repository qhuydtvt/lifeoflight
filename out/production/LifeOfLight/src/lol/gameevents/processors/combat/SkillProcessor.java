package lol.gameevents.processors.combat;

import lol.events.EventManager;
import lol.gameentities.State;
import lol.gameentities.players.Player;
import lol.gameentities.skills.Skill;
import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.util.List;

/**
 * Created by huynq on 9/15/17.
 */
public class SkillProcessor extends Processor {

    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        Player player = State.instance.getPlayer();

        if (commands.size() == 0) {
            EventManager.pushUIMessage("Skill chưa được chọn");
            return null;
        }

        String skillId = commands.get(0);
        if (Skill.find(skillId) == null) {
            EventManager.pushUIMessage("Skill này không tồn tại");
            return null;
        }

        Skill skill = player.getSkill(skillId);

        if (skill == null) {
            EventManager.pushUIMessage("Bạn chưa học skill này");
            return null;
        }

        if (!player.hasManaForSkill(skillId)) {
            EventManager.pushUIMessage("Bạn không đủ mana để sử dụng skill này");
            return null;
        }

        if (skill.getRange() == Skill.SKILL_SINGLE_TARGET) {
            return forward(commands, new SingleTargetSkillProcessor(skillId), currentEvent);
        } else {
            return forward(commands, new MultipleTargetSkillProcessor(skillId), currentEvent);
        }
    }
}
