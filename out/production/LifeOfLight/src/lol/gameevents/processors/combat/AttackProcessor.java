package lol.gameevents.processors.combat;

import lol.events.EventManager;
import lol.formulas.CombatFormula;
import lol.formulas.ItemRateFormula;
import lol.gameentities.State;
import lol.gameentities.items.GameItem;
import lol.gameentities.players.Player;
import lol.gameevents.CombatEvent;
import lol.gameevents.GameEvent;
import lol.gameevents.MainGameEvent;
import lol.gameevents.processors.Processor;
import lol.gameentities.monsters.Monster;

import lol.formulas.CombatFormula.PhysicsAttackResult;

import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public class AttackProcessor extends Processor {

    private ItemRateFormula itemRateFormula;

    public AttackProcessor(ItemRateFormula itemRateFormula) {
        this.itemRateFormula = itemRateFormula;
    }

    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        State state = State.instance;
        CombatEvent combatEvent = (CombatEvent) currentEvent;
        List<Monster> monsters = combatEvent.getMonsters();
        if (commands.size() == 0) {
            EventManager.pushUIMessage("You must choose a monster to attack");
            return null;
        }

        String monsterIdxString = commands.get(0);

        try {
            EventManager.pushUIMessage(";");
            int monsterIdx = Integer.parseInt(monsterIdxString);
            Monster monster = monsters.get(monsterIdx);
            Player player = State.instance.getPlayer();
            fight(player, monster);
            monsterFightBack(player, monsters);
            if (player.getStat().hp <= 0) {
                return new MainGameEvent();
            }
            if (monster.getStat().getHp() <= 0) {
                EventManager.pushUIMessage(String.format("%s just died", monster.getName()));
                monsters.remove(monster);
                player.changeExp(5);
                EventManager.pushUIMessage(String.format("Your EXP just increased by %s", 5));
                generateRandomItem();
                while (player.levelUp()) {
                    EventManager.pushUIMessage(String.format("Congrats, you just leveled up to %s", player.currentLevel + 1));
                    EventManager.pushUIMessage(String.format("Your new .getStat().: %s", "blah blah"));
                }
                if (monsters.size() == 0) {
                    EventManager.pushUIMessage("All monsters were killed, you won the combat");
                    return new MainGameEvent();
                }
                return null;
            }

            EventManager.pushUIMessage(";");
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            EventManager.pushUIMessage("Again, which one?");
            return null;
        }

        return null;
    }

    private void generateRandomItem() {
        State state = State.instance;
        Player player = state.getPlayer();
        int type = itemRateFormula.randomItemType();
        if (type != GameItem.TYPE_NULL) {
            GameItem gameItem = GameItem.random(type, state);
            if (gameItem != null) {
                player.collect(gameItem);
                EventManager.pushUIMessage(String.format("Bạn vừa nhặt được %s", gameItem.name));
                EventManager.pushUIMessage(gameItem.name);
                EventManager.pushUIMessage(gameItem.getDescription());
                EventManager.pushUIMessage(gameItem.dialog());
            }
        }
    }

    private void fight(Player player, Monster monster) {
        PhysicsAttackResult physicsAttackResult = CombatFormula.instance.physicsAttack(player);
        if (physicsAttackResult.isCriticalAttack) {
            EventManager.pushUIMessage("Critical attack");
        }
        if (CombatFormula.instance.doge(monster)) {
            EventManager.pushUIMessage(String.format("%s dodged your attack", monster.getName()));
        } else {
            monster.getHit(physicsAttackResult.damage);
            EventManager.pushUIMessage(String.format("You hit %s with %s damage and now it has %s hp left", monster.getName(),
                    physicsAttackResult.damage,
                    monster.getStat().getHp()));
        }
    }

    private void monsterFightBack(Player player, List<Monster> monsters) {
        for (Monster monster : monsters) {
            if (monster.getStat().hp > 0) {
                if (!CombatFormula.instance.doge(player)) {
                    player.getHit(monster.getStat().str);
                    EventManager.pushUIMessage(String.format("%s hit you, you now has %s hp left", monster.getName(), player.getStat().hp));
                    if (player.getStat().hp <= 0) return;
                } else {
                    EventManager.pushUIMessage(String.format("%s attacked you but missed", monster.getName()));
                }
            }
        }
    }
}
