package lol.gameevents;

import lol.events.EventManager;
import lol.gameevents.commands.AttackProcessor;
import lol.gameevents.commands.CommandProcessor;
import lol.gameevents.commands.FleeProcessor;
import lol.monsters.Monster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public class CombatEvent implements GameEvent {

    private List<Monster> monsters;

    HashMap<String, CommandProcessor> commandProcessors = new HashMap<String, CommandProcessor>() {{
        put("ATK", new AttackProcessor());
        put("FLEE", new FleeProcessor());
    }};

    public CombatEvent() {
        monsters = new ArrayList<>();
        for(int i = 0; i < 2; i++) {
            monsters.add(Monster.randomMonster());
        }
        EventManager.pushUIMessage("You have just entered a combat");
        EventManager.pushUIMessage(String.format("You met %s monsters", monsters.size()));
        for (Monster monster : monsters) {
            EventManager.pushUIMessage(monster.getInfo());
        }
        EventManager.pushUIMessage("You can ;#FF0000fight; or you can ;#FFC0CBflee;");
    }

    @Override
    public GameEvent process(List<String> commands) {
        if (commands.size() == 0) return null;
        String mainCommand = commands.get(0);
        if (!commandProcessors.containsKey(mainCommand)) {
            EventManager.pushHelpMessage();
            return null;
        }
        return CommandProcessor.forward(commands, commandProcessors.get(mainCommand), this);
    }


    @Override
    public GameEvent postProcess() {
        return null;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }
}
