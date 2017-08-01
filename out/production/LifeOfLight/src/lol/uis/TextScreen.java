package lol.uis;

import lol.events.EventListener;
import lol.events.EventManager;
import lol.events.EventType;
import lol.inputs.CommandListener;

/**
 * Created by huynq on 7/30/17.
 */
public class TextScreen extends TextView implements CommandListener, EventListener {

    public TextScreen() {
        super();
        EventManager.register(this);
    }

    @Override
    public void onCommandFinished(String command) {
    }

    @Override
    public void commandChanged(String command) {

    }

    @Override
    public void onEvent(EventType type, Object message) {
        if (type == EventType.UI_MESSAGE) {
            addText(message.toString());
        } else if (type == EventType.UI_CLEAR) {
            clear();
        }
    }
}
