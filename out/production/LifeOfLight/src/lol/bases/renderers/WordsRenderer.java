package lol.bases.renderers;

import com.sun.istack.internal.NotNull;
import lol.bases.Vector2D;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.*;

/**
 * Created by huynq on 7/30/17.
 */
public class WordsRenderer {
    private String plainText;
    private Color color;
    public static final int HEX_NUMBER_OF_CHAR = 7;

    public void render(Graphics2D g2d, Vector2D position) {
        g2d.setColor(color);
        g2d.drawString(plainText, position.x, position.y);
    }

    public WordsRenderer(String plainText, Color color) {
        this.plainText = plainText;
        this.color = color;
    }

    public WordsRenderer() {
    }

    public String getPlainText() {
        return plainText;
    }

    public Color getColor() {
        return color;
    }

    public int stringWidth(FontMetrics fontMetrics) {
        return fontMetrics.stringWidth(plainText);
    }

    public static List<WordsRenderer> parse(String coloredText) {
        ArrayList<WordsRenderer> wordsRenderers = new ArrayList<>();
        Color color = Color.WHITE;
        String allPlainText = null;

        if (coloredText.startsWith("#") && coloredText.length() >= HEX_NUMBER_OF_CHAR) {
            color = Color.decode(coloredText.substring(0, HEX_NUMBER_OF_CHAR));
            allPlainText = " " +coloredText.substring(HEX_NUMBER_OF_CHAR, coloredText.length());
        } else {
            color = Color.WHITE;
            allPlainText = coloredText;
        }

        String[] plainTexts = allPlainText.split(" ");

        for (int i = 0; i < plainTexts.length; i++) {
            String plainText = plainTexts[i];
            String suffix = i < plainTexts.length - 1 ? " " : "";
            wordsRenderers.add(new WordsRenderer(plainText + suffix, color));
        }

        return wordsRenderers;
    }

    @Override
    public String toString() {
        return "WordsRenderer{" +
                "plainText='" + plainText + '\'' +
                ", color=" + color +
                '}';
    }
}
