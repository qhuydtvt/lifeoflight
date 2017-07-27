package lol.uis;

import lol.bases.Vector2D;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by huynq on 7/28/17.
 */
public class TextView extends GamePanel {
    protected ArrayList<String> lines;
    private String separator = "--------------------------------------------------------------------------------------------";
    private Color textColor;

    private FontMetrics fontMetrics;
    private int linesMax = -1;
    private Vector2D offsetText;

    public TextView() {
        super();
        lines = new ArrayList<>();
        offsetText = new Vector2D();
        textColor = Color.WHITE;
        fontMetrics = null;
        linesMax = -1;
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
                linesMax = (int) (getSize().y / fontMetrics.getHeight()) - 1;
            }
        }
        
        drawVerticalLines(g2d);

        g2d.drawString(separator, getPosition().x - getAnchor().x * getSize().x, getPosition().y - getAnchor().y * getSize().y);

        int stringX = (int) (offsetText.x + getPosition().x - getAnchor().x * getSize().x);
        int stringY = (int) (offsetText.y + getPosition().y - getAnchor().y * getSize().y);

        for (String line : lines) {
            g2d.drawString(line, stringX, stringY);
            stringY += fontMetrics.getHeight();
        }

    }

    private void drawVerticalLines(Graphics2D g2d) {
        int x = (int) (getPosition().x - getAnchor().x * getSize().x);
        int y = (int) (getPosition().y - getAnchor().y * getSize().y);
        for(int i = 0; i < linesMax + 2; i ++) {
            g2d.drawString("|", x, y);
            y += fontMetrics.getHeight();
        }
    }

    int count = 0;

    public void appendText(String str) {
        if (fontMetrics == null) {
            System.out.println("Font metrics is not ready");
        } else {
            String[] words = str.split(" ");
            StringBuilder newLine = new StringBuilder();
            for (String word : words) {
                // Accumulate a new line
                newLine.append(word).append(" ");
                if (fontMetrics.stringWidth(newLine.toString()) > this.getSize().y) {
                    // New line has enough width
                    lines.add(count++ + newLine.toString());
                    newLine.setLength(0); // Flush
                    if (lines.size() > linesMax) {
                        // Trim the begnining
                        for (int i = 0; i < (lines.size() - linesMax) && lines.size() > 0; i++) {
                            lines.remove(0);
                        }
                    }
                }
            }
        }
    }
}
