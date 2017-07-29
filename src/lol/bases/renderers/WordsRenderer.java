package lol.bases.renderers;

import lol.bases.Vector2D;

import java.awt.*;

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

    public String getPlainText() {
        return plainText;
    }

    public Color getColor() {
        return color;
    }

    public int stringWidth(FontMetrics fontMetrics) {
        return fontMetrics.stringWidth(plainText);
    }

    public static WordsRenderer parse(String coloredText) {
        if (coloredText.startsWith("#")) {
            if (coloredText.length() < HEX_NUMBER_OF_CHAR) {
                System.out.println("Xeko lao: " + coloredText);
                return null;
            } else {
                String hexColor = coloredText.substring(0, HEX_NUMBER_OF_CHAR - 1);
                String plainText = coloredText.substring(HEX_NUMBER_OF_CHAR, coloredText.length());

                WordsRenderer wordsRenderer =  new WordsRenderer();
                wordsRenderer.plainText = plainText;
                wordsRenderer.color = Color.decode(hexColor);
                return wordsRenderer;
            }
        } else {
            WordsRenderer wordsRenderer =  new WordsRenderer();
            wordsRenderer.plainText = coloredText;
            wordsRenderer.color = Color.white;
            return wordsRenderer;
        }
    }

    @Override
    public String toString() {
        return "WordsRenderer{" +
                "plainText='" + plainText + '\'' +
                ", color=" + color +
                '}';
    }
}
