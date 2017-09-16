package lol.gameevents.processors.main;

import lol.events.EventManager;
import lol.gameentities.skills.Skill;
import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.util.List;

/**
 * Created by huynq on 9/16/17.
 */
public class SkillAllProcessor extends Processor {

    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        Skill.getAllSkills().forEach(skill -> {
            EventManager.pushUIMessage(skill.getName());
            EventManager.pushUIMessage(String.format("id: %s", skill.getId()));
            EventManager.pushUIMessage(String.format("%s", skill.getDescription()));
            EventManager.pushUIMessage("Yêu cầu:");
            skill.getRequirements().forEach(EventManager::pushUIMessage);
        });
        return null;
    }
}
