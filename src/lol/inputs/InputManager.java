package lol.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by huynq on 7/28/17.
 */
public class InputManager implements KeyListener {
    public static final InputManager instance = new InputManager();
    public String command;

    private CommandListener commandListener;

    public void setCommandListener(CommandListener commandListener) {
        this.commandListener = commandListener;
    }

    public InputManager() {
        command = "";
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() != '\n')
            command += e.getKeyChar();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (commandListener != null && command.length() > 0) {
                commandListener.onCommand(this.command);
                this.command = "";
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
