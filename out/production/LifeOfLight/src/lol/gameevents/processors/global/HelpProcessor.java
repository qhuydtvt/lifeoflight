package lol.gameevents.processors.global;

import com.google.gson.annotations.SerializedName;
import lol.bases.Utils;
import lol.events.EventManager;
import lol.gameevents.GameEvent;
import lol.gameevents.processors.Processor;

import java.util.List;

/**
 * Created by huynq on 8/9/17.
 */
public class HelpProcessor extends Processor {
    private static final HelpText helpText = Utils.parseJSON("assets/scripts/help.json", HelpText.class);

    class HelpText {
        @SerializedName("text")
        List<String> texts;
    }

    @Override
    public GameEvent process(List<String> commands, GameEvent currentEvent) {
        for (String text : helpText.texts) {
            EventManager.pushUIMessage(text);
        }
        return null;
    }
}
