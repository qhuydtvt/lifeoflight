package lol.bases;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.List;

/**
 * Created by huynq on 7/28/17.
 */
public class Utils {
    public static Font loadFont(String fontPath, float size) {
        try {
            return Font .createFont(Font.TRUETYPE_FONT, new java.io.File(fontPath))
                    .deriveFont(size)
                    .deriveFont(Font.BOLD);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String loadFileContent(String url) {
        File file = new File(url);
        FileInputStream fis = null;
        String content = null;
        try {
            fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            content = new String(data, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static <T> T choice(List<T> choices) {
        return choices.get(random.nextInt(choices.size()));
    }

    private static Random random = new Random();

    public static int rollDice() {
        return random.nextInt(6) + 1;
    }

    public static Font vnFont = new Font(Font.MONOSPACED, Font.PLAIN, 14);
}
