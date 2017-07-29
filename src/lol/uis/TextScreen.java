package lol.uis;

import lol.inputs.CommandListener;

/**
 * Created by huynq on 7/30/17.
 */
public class TextScreen extends TextView implements CommandListener {

    @Override
    public void onCommandFinished(String command) {
        this.appendText(command);
    }

    @Override
    public void commandChanged(String command) {

    }
}
