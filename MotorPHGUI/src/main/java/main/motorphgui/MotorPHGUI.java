/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main.motorphgui;

/**
 *
 * @author Ayou
 */
import javax.swing.*;
import java.awt.event.*;

public class MotorPHGUI {

    public static void main(String[] args) {
        // GUI Frame
        JFrame frame = new JFrame("MotorPH Employee App");

        // Labels
        JLabel lblEmployeeId = new JLabel("Employee ID:");
        lblEmployeeId.setBounds(20, 20, 100, 25);

        JTextField txtEmployeeId = new JTextField();
        txtEmployeeId.setBounds(130, 20, 150, 25);

        JLabel lblPayCoverage = new JLabel("Pay Coverage (days):");
        lblPayCoverage.setBounds(20, 60, 150, 25);

        JTextField txtPayCoverage = new JTextField();
        txtPayCoverage.setBounds(170, 60, 110, 25);

        // Button
        JButton btnGenerate = new JButton("Generate Payslip");
        btnGenerate.setBounds(80, 100, 160, 30);

        // Output label
        JTextArea outputArea = new JTextArea();
        outputArea.setBounds(20, 150, 260, 100);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        // Action Listener
        btnGenerate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 outputArea.setText(""); // Clear previous output
                try {
                    // Validate input is not empty
                    String empIdText = txtEmployeeId.getText().trim();
                    String payDaysText = txtPayCoverage.getText().trim();

                    if (empIdText.isEmpty() || payDaysText.isEmpty()) {
                        throw new IllegalArgumentException("Both fields are required.");
                    }
                    
                    int empId = Integer.parseInt(txtEmployeeId.getText().trim());
                    int payDays = Integer.parseInt(txtPayCoverage.getText().trim());

                    if (payDays <= 0) {
                        throw new IllegalArgumentException("Pay coverage must be greater than 0.");
                    }

                    if (empId != 1001) {
                        throw new IllegalArgumentException("Employee not found.");
                    }
                  CompensationDetails comp = new CompensationDetails(500.0f, 100.0f);
                  GovernmentDetails gov = new GovernmentDetails("SSS123", "PH123", "PAGIBIG123");
                  Employee emp = new Employee(empId, "Macky", 25, "Developer", 500.0f,gov, comp);

                  float grossSalary = emp.getSalary() * payDays + emp.getCompensation().getAllowance();
                  float tax = emp.calculateTax;

        Payroll payroll = new Payroll(1001, empId, grossSalary, 0f, tax);
        payroll.calculateSalary();

        outputArea.setText(
        "Employee ID: " + empId +
        "\nName: " + emp.getName() +
        "\nPosition: " + emp.getPosition() +
        "\nPay Days: " + payDays +
        "\nAllowance: ₱" + comp.getAllowance() +
        "\nGross Salary: ₱" + grossSalary +
        "\nTax: ₱" + tax +
        "\nNet Salary: ₱" + payroll.getNetSalary()
);

                } catch (NumberFormatException ex) {
                    outputArea.setText("Please enter valid numbers.");
                } catch (IllegalArgumentException ex) {
                    outputArea.setText(ex.getMessage());
                } catch (Exception ex) {
                    outputArea.setText("Unexpected error: " + ex.getMessage());
                }
            }
        });

        // Add components to frame
        frame.add(lblEmployeeId);
        frame.add(txtEmployeeId);
        frame.add(lblPayCoverage);
        frame.add(txtPayCoverage);
        frame.add(btnGenerate);
        frame.add(outputArea);

        // Frame setup
        frame.setSize(320, 300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
