import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;


public class Main {
  public static void main(String[] args) {

    JFrame frame = new JFrame("Input and Display GUI");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 500);
    frame.setLayout(new BorderLayout());

    // Left Panel: Display area
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new BorderLayout());
    // Canvas to draw on
    JComponent canvas = new JPanel();
    canvas.setPreferredSize(new Dimension(0, 400));
    canvas.setBorder(BorderFactory.createLineBorder(Color.GREEN));
    leftPanel.add(canvas, BorderLayout.NORTH);

    // Init painter
    Painter painter = new Painter(canvas);

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
    JScrollPane scrollPane = new JScrollPane(inputField);
    rightPanel.add(scrollPane, BorderLayout.CENTER);

    // Add panels to the frame
    frame.add(leftPanel, BorderLayout.CENTER);
    frame.add(rightPanel, BorderLayout.EAST);

    inputField.setText("(BOUNDING-BOX (0 0) (30 30))");

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
        painter.paint(inputField.getText());
        errorBox.setText(painter.context().formatErrors());
      }
    });

    // Make the frame visible
    frame.setVisible(true);
  }

}

