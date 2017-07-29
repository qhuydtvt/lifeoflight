package lol.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynq on 7/28/17.
 */
public class InputManager implements KeyListener {
    public static final InputManager instance = new InputManager();
    public String command;

    private List<CommandListener> commandListeners;

    public void addCommandListener(CommandListener commandListener) {
        commandListeners.add(commandListener);
    }

    public InputManager() {
        command = "";
        commandListeners = new ArrayList<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char typedCharacter = e.getKeyChar();
        if (isValidInput(typedCharacter)) {
            setCommand(command + typedCharacter);
        }

        if (typedCharacter == '\b' && command.length() > 0) {
            int endPosition = command.length() - 1;
            if (endPosition < 0) endPosition = 0;
            setCommand(command.substring(0,  endPosition));
        }
    }

    private void setCommand(String newCommand) {
        command = newCommand;
        for (CommandListener cmdListener : commandListeners) {
            cmdListener.commandChanged(command);
        }
    }

    private void finishCommand() {
        for (CommandListener cmdListener : commandListeners) {
            cmdListener.onCommandFinished(this.command);
        }
        setCommand("");
    }

    private boolean isValidInput(char c) {
        return Character.isDigit(c)
                || Character.isSpaceChar(c)
                || Character.isLetter(c)
                || c == '#'
                || c == ';';
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (command.length() > 0) {
                finishCommand();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
