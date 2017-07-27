package lol.settings;

/**
 * Created by huynq on 7/28/17.
 */
public class Settings {
    public static final String GAME_TITLE = "Life of Light";
    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT = 768;

    public static final int CMD_SCREEN_HEIGHT = 80;
    public static final int CMD_SCREEN_WIDTH = 800;

    public static final int TEXT_SCREEN_SCREEN_HEIGHT = SCREEN_HEIGHT - CMD_SCREEN_HEIGHT;
    public static final int TEXT_SCREEN_SCREEN_WIDTH = CMD_SCREEN_WIDTH;

    public static final int STATS_SCREEN_WIDTH = SCREEN_WIDTH - CMD_SCREEN_WIDTH;
    public static final int STATS_SCREEN_HEIGHT = SCREEN_HEIGHT;
}
