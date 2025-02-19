package plpa;
import javax.swing.*;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Input and Display GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());

        // Left Panel: Display area
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        JLabel displayLabel = new JLabel("Input will appear here", SwingConstants.CENTER);
        displayLabel.setFont(new Font("Arial", Font.BOLD, 16));
        leftPanel.add(displayLabel, BorderLayout.NORTH);



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
                displayLabel.setText(" " + inputField.getText());
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }
}