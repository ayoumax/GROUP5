/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphgui;

/**
 *
 * @author Macky
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class NewEmployeeForm extends JFrame {

    private JTextField[] fields = new JTextField[14];
    private String[] labels = {
        "Employee ID", "Last Name", "First Name", "SSS", "PhilHealth", "TIN", "Pag-IBIG",
        "Position", "Immediate Supervisor", "Monthly Salary",
        "Rice Subsidy", "Phone Allowance", "Clothing Allowance", "Hourly Rate"
    };

    private EmployeeTableFrame parent;

    public NewEmployeeForm(EmployeeTableFrame parent) {
        this.parent = parent;

        setTitle("Add New Employee");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Font and color
        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 13);
        Color bgColor = new Color(245, 245, 250);
        Color buttonColor = new Color(66, 133, 244); // blue
        Color buttonTextColor = Color.WHITE;

        JPanel inputPanel = new JPanel(new GridLayout(14, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        inputPanel.setBackground(bgColor);

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i] + ":");
            lbl.setFont(labelFont);
            inputPanel.add(lbl);

            fields[i] = new JTextField();
            fields[i].setFont(fieldFont);
            fields[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(180, 180, 180)),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));
            inputPanel.add(fields[i]);
        }

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        saveButton.setBackground(buttonColor);
        saveButton.setForeground(buttonTextColor);
        saveButton.setFocusPainted(false);
        saveButton.setPreferredSize(new Dimension(120, 35));
        saveButton.addActionListener(e -> saveEmployee());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(bgColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        buttonPanel.add(saveButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().setBackground(bgColor);
        setSize(450, 680);
        setLocationRelativeTo(null);
    }

    private void saveEmployee() {
        try {
            List<String> row = new ArrayList<>();
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields.");
                    return;
                }
                row.add(fields[i].getText().trim());
            }

            CSVHandler.appendEmployee(row, "data/employee.csv");
            parent.loadEmployees();
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving employee: " + ex.getMessage());
        }
    }
}
