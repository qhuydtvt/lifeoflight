package lol.uis;

import lol.bases.renderers.LineRenderer;
import lol.bases.renderers.WordsRenderer;
import lol.inputs.CommandListener;
import lol.inputs.InputManager;

/**
 * Created by huynq on 7/28/17.
 */
public class InputText extends TextView {
    public InputText() {
        super();
        this.lineRenderers.add(new
                LineRenderer().add(WordsRenderer.parse(">> ").get(0)));

        InputManager.instance.addCommandListener(new CommandListener() {
            @Override
            public void onCommandFinished(String command) {
                lineRenderers.clear();
                addText(">> ");
            }

            @Override
            public void commandChanged(String command) {
                lineRenderers.clear();
                addText(">> " + command);
            }
        });
    }
}
