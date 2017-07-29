package lol.uis;

import lol.inputs.CommandListener;
import lol.inputs.InputManager;

/**
 * Created by huynq on 7/28/17.
 */
public class InputText extends TextView {
    public InputText() {
        super();
        lines.add(">> ");

        InputManager.instance.addCommandListener(new CommandListener() {
            @Override
            public void onCommandFinished(String command) {

            }

            @Override
            public void commandChanged(String command) {
                lines.clear();
                lines.add(">> " + command);
            }
        });
    }
}
