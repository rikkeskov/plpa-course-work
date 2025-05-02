import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;


public class Main {
  public static void main(String[] args) {

    JFrame frame = new JFrame("Input and Display GUI");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setLayout(new BorderLayout());

    // Left Panel: Display area
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new BorderLayout());

    // Canvas to draw on
    CustomPanel canvas = new CustomPanel();
    leftPanel.add(canvas, BorderLayout.CENTER);

    // Error box
    JTextArea errorBox = new JTextArea();
    errorBox.setEditable(false);
    errorBox.setPreferredSize(new Dimension(0, 100)); // Explicitly set a preferred size
    errorBox.setBackground(Color.LIGHT_GRAY);
    leftPanel.add(new JScrollPane(errorBox), BorderLayout.SOUTH);
    leftPanel.validate();

    // Right Panel: Input area
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BorderLayout());
    rightPanel.setPreferredSize(new Dimension(250, 0)); // Explicitly set a preferred size
    JTextArea inputField = new JTextArea();
    inputField.setLineWrap(true);
    inputField.setWrapStyleWord(true);
    JScrollPane scrollPane = new JScrollPane(inputField);
    rightPanel.add(scrollPane, BorderLayout.CENTER);

    // Add panels to the frame
    frame.add(leftPanel, BorderLayout.CENTER);
    frame.add(rightPanel, BorderLayout.EAST);

    // Make the frame visible
    frame.setVisible(true);

    // Add a DocumentListener to the text area's Document
    inputField.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        handleTextChange();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        handleTextChange();
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        handleTextChange();
      }

      private void handleTextChange() {
        canvas.repaint(inputField.getText());
        errorBox.setText(canvas.getPainter().getContext().formatErrors());
      }
    });
    inputField.setText("""
            (BOUNDING-BOX (0 0) (30 30))
            (CIRCLE (20 20) 20)
            (LINE (5 5) (40 20))
            (RECTANGLE (5 5) (20 20))
            (TEXT-AT (25 10) this text wraps and clips if you make it long enough.)
            (BOUNDING-BOX (10 10) (40 40))
            (CIRCLE (25 25) 10)""");
  }

}

