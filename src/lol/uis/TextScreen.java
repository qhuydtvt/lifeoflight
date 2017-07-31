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
        if (command.toUpperCase().equals("TEST")) {
            this.addText("Văn học là khoa học nghiên cứu về văn chương. Nó lấy các hiện tượng văn chương nghệ thuật làm đối tượng cho mình. Quan hệ giữa văn chương và văn học là quan hệ giữa đối tượng và chủ thể, giữa nghệ thuật và khoa học; văn chương (nghệ thuật) là đối tượng của văn học (khoa học). Chính thế bạn tăng ;#46ff2d2 HP");
        }
        else if (command.toUpperCase().equals("CLEAR")) {
            this.clear();
        }
    }

    @Override
    public void commandChanged(String command) {

    }

    @Override
    public void onEvent(EventType type, Object message) {
        if (type == EventType.UI_MESSAGE) {
            addText(message.toString());
        }
    }
}
