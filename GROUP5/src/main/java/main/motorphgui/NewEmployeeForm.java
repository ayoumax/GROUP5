/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphgui;
import javax.swing.*;
import java.util.List;
import java.awt.*;

/**
 *
 * @author WINDOWS 10
 */
public class NewEmployeeForm extends JFrame{
    private JTextField txtFirstName, txtLastName, txtSalary, txtAllowance;
    private JTextField txtSSS, txtPhil, txtTIN, txtPagibig;
    private EmployeeTableFrame parentFrame;

    public NewEmployeeForm(EmployeeTableFrame parent) {
         this.parentFrame = parent;
        setTitle("New Employee");
        setSize(400, 450);
        setLayout(null);

        String[] labels = {"First Name", "Last Name", "Salary", "Allowance", "SSS", "PhilHealth", "TIN", "PagIBIG"};
        JTextField[] fields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i] + ":");
            lbl.setBounds(20, 30 + i * 30, 100, 25);
            add(lbl);

            fields[i] = new JTextField();
            fields[i].setBounds(130, 30 + i * 30, 200, 25);
            add(fields[i]);
        }

        txtFirstName = fields[0];
        txtLastName = fields[1];
        txtSalary = fields[2];
        txtAllowance = fields[3];
        txtSSS = fields[4];
        txtPhil = fields[5];
        txtTIN = fields[6];
        txtPagibig = fields[7];

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(130, 370, 100, 30);
        add(btnSave);

        btnSave.addActionListener(e -> saveNewEmployee());
    }

    private void saveNewEmployee() {
        try {
            // Validate required text fields
            if (txtFirstName.getText().trim().isEmpty() || txtLastName.getText().trim().isEmpty()
                    || txtSalary.getText().trim().isEmpty() || txtAllowance.getText().trim().isEmpty()
                    || txtSSS.getText().trim().isEmpty() || txtPhil.getText().trim().isEmpty()
                    || txtTIN.getText().trim().isEmpty() || txtPagibig.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "All fields are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate salary and allowance (must be valid floats)
            float salary, allowance;
            try {
                salary = Float.parseFloat(txtSalary.getText().trim());
                allowance = Float.parseFloat(txtAllowance.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Salary and Allowance must be valid numbers!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate government IDs (must be digits only)
            if (!txtSSS.getText().trim().matches("\\d+")
                    || !txtPhil.getText().trim().matches("\\d+")
                    || !txtTIN.getText().trim().matches("\\d+")
                    || !txtPagibig.getText().trim().matches("\\d+")) {

                JOptionPane.showMessageDialog(this, "SSS, PhilHealth, TIN, and Pag-IBIG numbers must contain digits only!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // If all validations pass, proceed to save
            int id = (int) (System.currentTimeMillis() % 100000); // Simple ID generation
            String first = txtFirstName.getText().trim();
            String last = txtLastName.getText().trim();

            String sss = txtSSS.getText().trim();
            String phil = txtPhil.getText().trim();
            String tin = txtTIN.getText().trim();
            String pag = txtPagibig.getText().trim();

            GovernmentDetails gov = new GovernmentDetails(sss, phil, tin, pag);
            CompensationDetails comp = new CompensationDetails(salary, allowance);
            Employee emp = new Employee(id, last, first, salary, gov, comp);

            List<Employee> employees = CSVHandler.loadEmployees("data/employee.csv");
            employees.add(emp);
            CSVHandler.saveEmployees(employees, "data/employee.csv");

            parentFrame.refreshTable();
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
