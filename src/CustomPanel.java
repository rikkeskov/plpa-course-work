import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {
    private final Painter painter;
    private String textToDraw;

    int gridSize = 10;

    public CustomPanel() {
        setBackground(Color.WHITE);

        this.painter = new Painter(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        int panelHeight = getHeight();
        int panelWidth = getWidth();

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, panelWidth, panelHeight);
        // Draw grid
        g2d.setColor(Color.LIGHT_GRAY);

        for (int x = 0; x < panelWidth; x += gridSize) {
            g2d.drawLine(x, 0, x, panelHeight);
        }
        for (int y = 0; y < panelHeight; y += gridSize) {
            g2d.drawLine(0, y, panelWidth, y);
        }



        // Add our painter with the text from inputField to draw
        painter.paint(textToDraw, g2d);

    }

    public void repaint(String text) {
        this.textToDraw = text;
        super.repaint();
    }

    public Painter getPainter() {
        return painter;
    }

}
