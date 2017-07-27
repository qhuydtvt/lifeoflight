package lol.bases;

import java.awt.*;
import java.io.IOException;

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
}
