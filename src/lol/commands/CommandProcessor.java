package lol.commands;

import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public abstract class CommandProcessor {
    public abstract void process(List<String> commands);

    public static void forward(List<String> commands, CommandProcessor subProcessor) {
        if (commands.size() == 0) {
            subProcessor.process(null);
        } else {
            subProcessor.process(commands.subList(1, commands.size()));
        }
    }
}
