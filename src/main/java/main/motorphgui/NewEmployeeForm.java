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
    private JTextField txtFirstName, txtLastName, txtAge, txtPosition, txtSalary, txtAllowance;
    private JTextField txtSSS, txtPhil, txtTIN, txtPagibig;
    private EmployeeTableFrame parentFrame;

    public NewEmployeeForm(EmployeeTableFrame parent) {
         this.parentFrame = parent;
        setTitle("Add New Employee");
        setSize(400, 450);
        setLayout(null);

        String[] labels = {"First Name", "Last Name", "Age", "Position", "Salary", "Allowance", "SSS", "PhilHealth", "TIN", "PagIBIG"};
        JTextField[] fields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i] + ":");
            lbl.setBounds(20, 30 + i * 30, 100, 25);
            add(lbl);

            fields[i] = new JTextField();
            fields[i].setBounds(130, 30 + i * 30, 200, 25);
            add(fields[i]);
        }

        txtFirstName = fields[0]; txtLastName = fields[1]; txtAge = fields[2]; txtPosition = fields[3];
        txtSalary = fields[4]; txtAllowance = fields[5]; txtSSS = fields[6]; txtPhil = fields[7];
        txtTIN = fields[8]; txtPagibig = fields[9];

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(130, 370, 100, 30);
        add(btnSave);

        btnSave.addActionListener(e -> saveNewEmployee());
    }

    private void saveNewEmployee() {
        try {
            int id = (int)(System.currentTimeMillis() % 100000); // Simple ID generation
            String first = txtFirstName.getText().trim();
            String last = txtLastName.getText().trim();
            int age = Integer.parseInt(txtAge.getText().trim());
            String pos = txtPosition.getText().trim();
            float salary = Float.parseFloat(txtSalary.getText().trim());
            float allow = Float.parseFloat(txtAllowance.getText().trim());

            String sss = txtSSS.getText().trim();
            String phil = txtPhil.getText().trim();
            String tin = txtTIN.getText().trim();
            String pag = txtPagibig.getText().trim();

            GovernmentDetails gov = new GovernmentDetails(sss, phil, tin, pag);
            CompensationDetails comp = new CompensationDetails(salary, allow);
            Employee emp = new Employee(id, last, first, age, pos, salary, gov, comp);

            List<Employee> employees = CSVHandler.loadEmployees("data/employee.csv");
            employees.add(emp);
            CSVHandler.saveEmployees(employees, "data/employee.csv");

            parentFrame.refreshTable();
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    }
    

