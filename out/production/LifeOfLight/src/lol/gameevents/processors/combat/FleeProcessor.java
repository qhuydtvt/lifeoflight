package lol.gameevents.processors.combat;

import lol.bases.Utils;
import lol.events.EventManager;
import lol.gameentities.State;
import lol.gameentities.players.Player;
import lol.gameevents.CombatEvent;
import lol.gameevents.GameEvent;
import lol.gameevents.MainGameEvent;
import lol.gameevents.processors.Processor;
import lol.monsters.Monster;

import java.util.List;
import java.util.Random;

/**
 * Created by huynq on 8/1/17.
 */
public class FleeProcessor extends Processor {
    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        if (Utils.rollDice() < State.instance.getPlayer().stat.luck) {
            EventManager.pushUIMessage("You escaped from combat");
            return new MainGameEvent();
        } else {
            EventManager.pushUIMessage("You tried to flee but failed");
            Player player = State.instance.getPlayer();
            fight(player, ((CombatEvent)currentEvent).getMonsters());
            if (player.stat.hp <= 0) {
                return new MainGameEvent();
            }
            return null;
        }
    }

    private void fight(Player player, List<Monster> monsters) {
        Monster monster = monsters.get(new Random().nextInt(monsters.size()));
        if(Utils.rollDice() < monster.getStat().getLuck()) {
            player.getHit(monster.getStat().getStrength());
            EventManager.pushUIMessage(String.format("%s hit you, you now has %s hp left", monster.getName(), player.stat.hp));
        } else {
            EventManager.pushUIMessage(String.format("%s attacked you, but missed", monster.getName()));
        }
    }
}
