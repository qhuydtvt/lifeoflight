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
public class MultipleTargetSkillProcessor extends Processor {
    private String skillId;

    public MultipleTargetSkillProcessor(String skillId) {
        this.skillId = skillId;
    }

    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        List<Monster> monsters = ((CombatEvent)currentEvent).getMonsters();
        Player player = State.instance.getPlayer();
        Skill skill = player.getSkill(skillId);
        player.useSkill(skillId, monsters);
        EventManager.pushUIMessage(String.format("Đội hình địch đã bị trúng %s", skill.getName()));
        monsters.forEach(monster -> EventManager.pushUIMessage(monster.getInfo()));
        return null;
    }
}
