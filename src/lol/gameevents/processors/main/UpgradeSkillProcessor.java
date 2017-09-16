package lol.gameevents.processors.main;

import lol.events.EventManager;
import lol.gameentities.State;
import lol.gameentities.players.Player;
import lol.gameentities.skills.Skill;
import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.util.List;

/**
 * Created by huynq on 9/16/17.
 */
public class UpgradeSkillProcessor extends Processor {
    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        Player player = State.instance.getPlayer();
        if (!player.canUpgradeSkill()) {
            EventManager.pushUIMessage("Bạn không có khả năng nâng cấp kỹ năng");
            return null;
        }

        if (commands.size() == 0) {
            EventManager.pushUIMessage("Bạn cần chọn tên kỹ năng muốn học / nâng cấp");
            return null;
        }

        String skillId = commands.get(0);
        Skill skill  = Skill.find(skillId);
        if (skill == null) {
            EventManager.pushUIMessage("Kỹ năng này không tồn tại");
            return null;
        }


        player.upgradeSkill(skillId);
        EventManager.pushUIMessage(String.format("Bạn đã học/nâng cấp %s, gõ ;#00FF00skill_tree; để xem", skill.getName()));
        return null;
    }
}
