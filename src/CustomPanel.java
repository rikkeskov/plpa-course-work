import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {
    private final Painter painter;
    private String textToDraw;

    int panelHeight = 400;
    int panelWidth = 600;

    int gridSize = 10;

    public CustomPanel() {
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(Color.WHITE);

        this.painter = new Painter(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw grid
        g.setColor(Color.LIGHT_GRAY);

        // Draw vertical lines
        for (int x = 0; x < panelWidth; x += gridSize) {
            g.drawLine(x, 0, x, panelHeight);
        }

        // Draw horizontal lines
        for (int y = 0; y < panelHeight; y += gridSize) {
            g.drawLine(0, y, panelWidth, y);
        }

        Graphics2D g2d = (Graphics2D) g;

        if (textToDraw != null) {
            painter.paint(textToDraw, g2d);
        }

    }

    public void repaint(String text) {
        this.textToDraw = text;
        super.repaint();
    }

    public Painter getPainter() {
        return painter;
    }

}
