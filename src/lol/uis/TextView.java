package lol.uis;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by huynq on 7/28/17.
 */
public class TextView extends GamePanel {
    private ArrayList<String> lines;
    private Color textColor;

    private FontMetrics fontMetrics;
    private int linesMax;

    public TextView() {
        super();
        lines = new ArrayList<>();
        textColor = Color.WHITE;
        fontMetrics = null;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        g2d.setColor(textColor);

        if (fontMetrics == null) {
            fontMetrics = g2d.getFontMetrics();
            linesMax = (int)(getSize().y / fontMetrics.getHeight()) - 1;
        }

        int stringY = 20;

        for (String line : lines) {
            g2d.drawString(line, 0, stringY);
            stringY += fontMetrics.getHeight();
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
                    lines.add( count++ + newLine.toString());
                    newLine.setLength(0); // Flush
                    if (lines.size() > linesMax) {
                        // Trim the begnining
                        for (int i = 0; i < ( lines.size() - linesMax) && lines.size() > 0; i++) {
                            lines.remove(0);
                        }
                    }
                }
            }
        }
    }
}
