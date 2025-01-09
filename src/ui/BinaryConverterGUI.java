package ui;

import util.DatabaseUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BinaryConverterGUI {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Binary to Decimal Converter");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create UI components
        JLabel inputLabel = new JLabel("Enter Binary Number:");
        JTextField binaryInputField = new JTextField(20);
        JButton convertButton = new JButton("Convert");
        JLabel resultLabel = new JLabel("Decimal Output: ");
        JButton saveButton = new JButton("Save to Database");
        saveButton.setEnabled(false); // Initially disabled

        // Panel for layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.add(inputLabel);
        panel.add(binaryInputField);
        panel.add(convertButton);
        panel.add(resultLabel);
        panel.add(saveButton);

        frame.add(panel);

        // Action for the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String binaryInput = binaryInputField.getText();
                try {
                    // Convert binary to decimal
                    int decimalOutput = Integer.parseInt(binaryInput, 2);
                    resultLabel.setText("Decimal Output: " + decimalOutput);

                    // Enable the save button
                    saveButton.setEnabled(true);

                    // Save the result when the button is clicked
                    saveButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            DatabaseUtil.saveConversion(binaryInput, decimalOutput);
                            JOptionPane.showMessageDialog(frame, "Conversion saved to database!");
                        }
                    });
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid binary input. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    resultLabel.setText("Decimal Output: ");
                    saveButton.setEnabled(false);
                }
            }
        });

        // Display the frame
        frame.setVisible(true);
    }
}
