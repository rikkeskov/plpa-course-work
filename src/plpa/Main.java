package plpa;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Input and Display GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
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
        rightPanel.setPreferredSize(new Dimension(150, 0)); // Explicitly set a preferred size
        JTextField inputField = new JTextField();
        rightPanel.add(inputField, BorderLayout.CENTER);

        // Add panels to the frame
        frame.add(leftPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        // Timer to update the display every 2 seconds
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText().trim();
                if (userInput.isEmpty()) {
                    errorBox.setText("Error: Input cannot be empty!");
                    displayLabel.setText("Input will appear here");
                } else {
                    displayLabel.setText(userInput);
                    errorBox.setText(""); // Clear any previous errors
                }
            }
        });

        timer.start(); // Start the timer

        // Make the frame visible
        frame.setVisible(true);
    }
}