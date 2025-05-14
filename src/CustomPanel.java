import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {
    private final Painter painter;
    private String textToDraw;

    int gridSize = 10;

    public CustomPanel() {
        setBackground(Color.WHITE);
        this.painter = new Painter();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // We repaint the component each time
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
        // Scale the y-axis the other way so Y moves upwards
        g2d.scale(1, -1);
        // Translate the 0,0 to bottom left corner.
        g2d.translate(0, -panelHeight);

        // Add our painter with the text from inputField to draw
        g2d.setStroke(new BasicStroke(1.5f));
        painter.paint(textToDraw, g2d);
        g2d.clipRect(0, 0, panelWidth, panelHeight);
    }

    public void repaint(String text) {
        this.textToDraw = text;
        super.repaint();
    }

    public Painter getPainter() { return painter; }

}
