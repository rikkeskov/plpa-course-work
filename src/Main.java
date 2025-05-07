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
            # This is my comment
            # Its important to have a bounding-box.
            # Otherwise the other ain't clipping it :)
            (BOUNDING-BOX (1 1) (50 50))
            (TEXT-AT (17 47) This is my very nice graph!)
            (RECTANGLE (3 3) (47 47))
            (TEXT-AT (52 0) X-axis)
            (LINE (9 10) (40 10))
            (TEXT-AT (7 40) Y-axis)
            (LINE (9 10) (9 40))
            (RECTANGLE (10 10) (12 39))
            (RECTANGLE (13 10) (15 31))
            (RECTANGLE (16 10) (18 36))
            (RECTANGLE (19 10) (21 33))
            (RECTANGLE (22 10) (24 31))
            (RECTANGLE (25 10) (27 22))
            (RECTANGLE (28 10) (30 26))
            (RECTANGLE (31 10) (33 21))
            (RECTANGLE (34 10) (36 16))
            (RECTANGLE (37 10) (39 18))
            # Trendline
            (LINE (11 38) (38 17))
            # Top lines
            (LINE (11 39) (14 31))
            (LINE (14 31) (17 36))
            (LINE (17 36) (20 33))
            (LINE (20 33) (23 31))
            (LINE (23 31) (26 22))
            (LINE (26 22) (29 26))
            (LINE (29 26) (32 21))
            (LINE (32 21) (35 16))
            (LINE (35 16) (38 18))
            # X-axis labels
            (TEXT-AT (9 8) 0)
            (TEXT-AT (11 8) 2)
            (TEXT-AT (13 8) 4)
            (TEXT-AT (15 8) 6)
            (TEXT-AT (17 8) 8)
            (TEXT-AT (19 8) 10)
            (TEXT-AT (21 8) 12)
            (TEXT-AT (23 8) 14)
            (TEXT-AT (25 8) 16)
            (TEXT-AT (27 8) 18)
            (TEXT-AT (29 8) 20)
            (TEXT-AT (31 8) 22)
            (TEXT-AT (33 8) 24)
            (TEXT-AT (35 8) 26)
            (TEXT-AT (37 8) 28)
            (TEXT-AT (39 8) 30)
            #(TEXT-AT (41 8) 32)
            #(TEXT-AT (43 8) 34)
            #(TEXT-AT (45 8) 36)
            #(TEXT-AT (47 8) 38)
            # Y-axis labels
            (TEXT-AT (8 10) 0)
            (TEXT-AT (8 12) 2)
            (TEXT-AT (8 14) 4)
            (TEXT-AT (8 16) 6)
            (TEXT-AT (8 18) 8)
            (TEXT-AT (8 20) 10)
            (TEXT-AT (8 22) 12)
            (TEXT-AT (8 24) 14)
            (TEXT-AT (8 26) 16)
            (TEXT-AT (8 28) 18)
            (TEXT-AT (8 30) 20)
            (TEXT-AT (8 32) 22)
            (TEXT-AT (8 34) 24)
            (TEXT-AT (8 36) 26)
            (TEXT-AT (8 38) 28)
            #(TEXT-AT (8 40) 30)
            #(TEXT-AT (8 42) 32)
            #(TEXT-AT (8 44) 34)
            #(TEXT-AT (8 46) 36)
            (FILL blue 8 9 10 11 12 13 14 15 16 17)
            (COLOR green 18)
            (COLOR magenta 19 20 21 22 23 24 25 26 27)
            # here begins the pie chart
            (BOUNDING-BOX (50 1) (100 50))
            (CIRCLE (75 25) 20)
            (TEXT-AT (75 47) This is the pie chart of my 2025 running statistics)
            (LINE (75 25) (75 45))
            (TEXT-AT (82 35) January 21%)
            (LINE (75 25) (93 33))
            (TEXT-AT (84 22) February 23%)
            (LINE (75 25) (84 7))
            (TEXT-AT (75 9) March 18%)
            (LINE (75 25) (63 9))
            (TEXT-AT (64 18) April 25%)
            (LINE (75 25) (60 38))
            (TEXT-AT (70 33) May 11%)
            (FILL red 60)
            (COLOR cyan 60)
            (RECTANGLE (53 3) (97 47))
            """);
  }

}

