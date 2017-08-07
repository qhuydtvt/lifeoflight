package lol.gameevents.processors.world;

import lol.events.EventManager;
import lol.formulas.ItemRateFormula;
import lol.gameentities.State;
import lol.gameentities.eventconfigs.EventConfig;
import lol.gameentities.items.GameItem;
import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.util.List;

import static lol.gameentities.items.GameItem.TYPE_NULL;

/**
 * Created by huynq on 8/7/17.
 */
public class WordEventItemProcessor extends Processor {

    private EventConfig eventConfig;
    private ItemRateFormula itemRateFormula;

    public WordEventItemProcessor(EventConfig eventConfig, ItemRateFormula itemRateFormula) {
        this.eventConfig = eventConfig;
        this.itemRateFormula = itemRateFormula;
    }

    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        int itemType = itemRateFormula.randomItemType();
        if (itemType != TYPE_NULL) {
            EventManager.pushUIMessage(eventConfig.beginText);
            GameItem gameItem = GameItem.random(itemType, State.instance);
            if (gameItem != null) {
                State.instance.getPlayer().collect(gameItem);
                EventManager.pushUIMessage(gameItem.name);
                EventManager.pushUIMessage(gameItem.getDescription());
                EventManager.pushUIMessage(gameItem.text());
            }
        }
        return null;
    }
}
