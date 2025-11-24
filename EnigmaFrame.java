import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnigmaFrame extends JFrame {
    private JComboBox<Integer> rotor1, rotor2, rotor3;
    private JTextField startField;
    private JTextArea inputArea, outputArea;
    private JButton encryptButton, decryptButton;
    private JLabel rotor1Label, rotor2Label, rotor3Label, startLabel, inputLabel, outputLabel;

    public EnigmaFrame() {
        setTitle("Enigma Machine GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Rotor selection panel
        JPanel rotorPanel = new JPanel();
        rotorPanel.setLayout(new GridLayout(2, 4, 10, 10));
        rotor1Label = new JLabel("Inner Rotor");
        rotor2Label = new JLabel("Middle Rotor");
        rotor3Label = new JLabel("Outer Rotor");
        startLabel = new JLabel("Start Position");
        rotorPanel.add(rotor1Label);
        rotorPanel.add(rotor2Label);
        rotorPanel.add(rotor3Label);
        rotorPanel.add(startLabel);
        rotor1 = new JComboBox<>(new Integer[]{1,2,3,4,5});
        rotor2 = new JComboBox<>(new Integer[]{1,2,3,4,5});
        rotor3 = new JComboBox<>(new Integer[]{1,2,3,4,5});
        startField = new JTextField(3);
        rotorPanel.add(rotor1);
        rotorPanel.add(rotor2);
        rotorPanel.add(rotor3);
        rotorPanel.add(startField);

        // Input/output panel
        JPanel ioPanel = new JPanel();
        ioPanel.setLayout(new GridLayout(2, 2, 10, 10));
        inputLabel = new JLabel("Input Message");
        outputLabel = new JLabel("Output Message");
        inputArea = new JTextArea(5, 20);
        outputArea = new JTextArea(5, 20);
        outputArea.setEditable(false);
        ioPanel.add(inputLabel);
        ioPanel.add(outputLabel);
        ioPanel.add(new JScrollPane(inputArea));
        ioPanel.add(new JScrollPane(outputArea));

        // Button panel
        JPanel buttonPanel = new JPanel();
        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");
        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);

        add(rotorPanel, BorderLayout.NORTH);
        add(ioPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        encryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processMessage(true);
            }
        });
        decryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processMessage(false);
            }
        });
    }

    private void processMessage(boolean encrypt) {
        try {
            int id1 = (Integer)rotor1.getSelectedItem();
            int id2 = (Integer)rotor2.getSelectedItem();
            int id3 = (Integer)rotor3.getSelectedItem();
            String start = startField.getText();
            String message = inputArea.getText();
            Enigma enigma = new Enigma(id1, id2, id3, start);
            String result = encrypt ? enigma.encrypt(message) : enigma.decrypt(message);
            outputArea.setText(result);
        } catch (Exception ex) {
            outputArea.setText("Error: " + ex.getMessage());
        }
    }
}
