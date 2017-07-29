package lol.uis;

import lol.bases.Vector2D;
import lol.bases.renderers.LineRenderer;
import lol.bases.renderers.WordsRenderer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by huynq on 7/28/17.
 */
public class TextView extends GamePanel {
    protected ArrayList<LineRenderer> lineRenderers;

    private String separator = "------------------------------------------------------------------------------------------------------------------------";
    private Color textColor;
    public static final int HEX_NUMBER_OF_CHAR = 7;

    private FontMetrics fontMetrics;
    private int linesMax = -1;
    private Vector2D offsetText;
    private Object renderLock;

    public TextView() {
        super();
        lineRenderers = new ArrayList<>();
        offsetText = new Vector2D();
        textColor = Color.WHITE;
        fontMetrics = null;
        linesMax = -1;
        renderLock = new Object();
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Vector2D getOffsetText() {
        return offsetText;
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        g2d.setColor(textColor);

        if (fontMetrics == null) {

            fontMetrics = g2d.getFontMetrics();
            if (linesMax == -1) {
                linesMax = (int) ((getSize().y - offsetText.y) / fontMetrics.getHeight()) - 1;
            }
        }

        drawVerticalLines(g2d);

        g2d.drawString(separator, getPosition().x - getAnchor().x * getSize().x, getPosition().y - getAnchor().y * getSize().y);

        Vector2D realPosition = position
                .add(offsetText)
                .subtract(getAnchor().x * getSize().x, getAnchor().y * getSize().y);

        synchronized (renderLock) {
            for (LineRenderer lineRenderer : lineRenderers) {
                lineRenderer.render(g2d, realPosition);
                realPosition = realPosition.add(0, fontMetrics.getHeight());
            }
        }

    }

    private void drawVerticalLines(Graphics2D g2d) {
        int x = (int) (getPosition().x - getAnchor().x * getSize().x);
        int y = (int) (getPosition().y - getAnchor().y * getSize().y);
        for (int i = 0; i < (getSize().y / g2d.getFontMetrics().getHeight()) + 1; i++) {
            g2d.drawString("|", x, y);
            y += fontMetrics.getHeight();
        }
    }

    public void clear() {
        synchronized (renderLock) {
            this.lineRenderers.clear();;
        }
    }

    public void addText(String str) {
        if (fontMetrics == null) {
            System.out.println("Font metrics is not ready");
        } else {
            ArrayList<WordsRenderer> wordsRenderers = new ArrayList<>();

            for (String words : str.split(";")) {
                if (words.length() > 0) {
                    WordsRenderer wordsRenderer = WordsRenderer.parse(words);
                    wordsRenderers.add(wordsRenderer);
                }
            }

            LineRenderer newLineRenderer = new LineRenderer();

            for (int wordIndex = 0; wordIndex < wordsRenderers.size(); wordIndex++) {
                WordsRenderer wordsRenderer = wordsRenderers.get(wordIndex);
                newLineRenderer.add(wordsRenderer);

                boolean lineLengthExceeds = newLineRenderer.stringWidth(fontMetrics) - 20 > this.getSize().y;
                boolean isLastWord = (wordIndex == wordsRenderers.size() - 1);
                if (lineLengthExceeds || isLastWord) {
                    synchronized (renderLock) {
                        this.lineRenderers.add(newLineRenderer);
                        newLineRenderer = new LineRenderer();
                        if (lineRenderers.size() > linesMax) {
                            // Trim the begnining
                            for (int lineIndex = 0; lineIndex < (lineRenderers.size() - linesMax) && lineRenderers.size() > 0; lineIndex++) {
                                lineRenderers.remove(0);
                            }
                        }
                    }
                }
            }
        }
    }
}
