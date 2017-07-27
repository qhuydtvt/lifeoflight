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
        char typedCharacter = e.getKeyChar();
        if (isValidInput(typedCharacter)) {
            setCommand(command + typedCharacter);
            if(commandListener != null) {
                commandListener.commandChanged(command);
            }
        }

        if (typedCharacter == '\b' && command.length() > 0) {
            int endPosition = command.length() - 2;
            if (endPosition < 0) endPosition = 0;
            setCommand(command.substring(0,  endPosition));
        }
    }

    private void setCommand(String newCommand) {
        command = newCommand;
        if (this.commandListener != null) {
            this.commandListener.commandChanged(command);
        }
    }

    boolean isValidInput(char c) {
        return Character.isDigit(c) || Character.isSpaceChar(c) || Character.isLetter(c);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (commandListener != null && command.length() > 0) {
                commandListener.onCommandFinished(this.command);
                setCommand("");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
