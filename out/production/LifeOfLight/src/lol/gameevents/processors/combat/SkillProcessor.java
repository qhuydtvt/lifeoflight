package lol.gameevents.processors.combat;

import lol.events.EventManager;
import lol.gameentities.CombatUnit;
import lol.gameentities.State;
import lol.gameentities.monsters.Monster;
import lol.gameentities.players.Player;
import lol.gameentities.skills.SkillResult;
import lol.gameevents.CombatEvent;
import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.util.List;

/**
 * Created by huynq on 9/15/17.
 */
public class SkillProcessor extends Processor {

    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        if (commands.size() == 0) {
            EventManager.pushUIMessage("Bạn cần phải chọn skill để dùng");
            return null;
        }

        String skillName = commands.get(0);

        CombatEvent combatEvent = (CombatEvent) currentEvent;
        List<Monster> monsters = combatEvent.getMonsters();
        Player player = State.instance.getPlayer();

        List<SkillResult> skillResults = player.useSkill(skillName, monsters);

        for (SkillResult result : skillResults) {
            EventManager.pushUIMessage(String.format("%s mất %s HP", result.affectCombatName, result.damage));
        }

        return null;
    }
}
