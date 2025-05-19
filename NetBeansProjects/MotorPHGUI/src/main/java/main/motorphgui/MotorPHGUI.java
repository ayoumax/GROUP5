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

        // Action Listener
        btnGenerate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int empId = Integer.parseInt(txtEmployeeId.getText().trim());
                    int payDays = Integer.parseInt(txtPayCoverage.getText().trim());

                    if (payDays <= 0) {
                        throw new IllegalArgumentException("Pay coverage must be greater than 0.");
                    }

                    // Mock employee (replace with real logic if needed)
                    Employee emp = new Employee(empId, "Macky Arriesgado", 25, "Developer", 500.0f); // salary is per day
                    float basicSalary = emp.getSalary() * payDays;

                    Tax tax = new Tax(101, empId, 0.12f);
                    tax.calculateTax(basicSalary);

                    Payroll payroll = new Payroll(1001, empId, basicSalary, 2000f, tax.getTaxAmount());
                    payroll.calculateSalary();

                    // Output
                    outputArea.setText(
                            "Employee ID: " + empId
                            + "\nPosition: " + emp.getPosition()
                            + "\nPay Days: " + payDays
                            + "\nGross Salary: ₱" + basicSalary
                            + "\nTax: ₱" + tax.getTaxAmount()
                            + "\nNet Salary: ₱" + payroll.getNetSalary()
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
