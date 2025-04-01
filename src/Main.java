import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;


public class Main {
  public static void main(String[] args) {
    // Test that command is correctly defined from the class name
    BoundingBox b = new BoundingBox("(1 2) (3 4)");
    System.out.println(b.command());

    // Init painter
    JComponent canvas = new JPanel();
    Painter painter = new Painter(canvas);

    JFrame frame = new JFrame("Input and Display GUI");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 500);
    frame.setLayout(new BorderLayout());

    // Left Panel: Display area
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new BorderLayout());
    leftPanel.add(canvas);


    JTextArea errorBox = new JTextArea("Errors will appear here...");
    errorBox.setEditable(false);
    errorBox.setBackground(Color.LIGHT_GRAY);
    leftPanel.add(new JScrollPane(errorBox), BorderLayout.CENTER);

    // Right Panel: Input area
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BorderLayout());
    rightPanel.setPreferredSize(new Dimension(250, 0)); // Explicitly set a preferred size
    JTextArea inputField = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(inputField);
    rightPanel.add(scrollPane, BorderLayout.CENTER);

    // Add panels to the frame
    frame.add(leftPanel, BorderLayout.CENTER);
    frame.add(rightPanel, BorderLayout.EAST);

    Throttler t = new Throttler();

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
        // Debounce 500ms
        t.throttle(
                () -> painter.paint(inputField.getText()),
                3000
        );
        //painter.paint(inputField.getText());
      }
    });

    // Make the frame visible
    frame.setVisible(true);
  }

}

