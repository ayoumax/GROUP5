/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



package main.motorphgui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import main.motorphgui.Employee;
import main.motorphgui.GovernmentDetails;
import main.motorphgui.CSVHandler;

/**
 *
 * @author WINDOWS 10
 */
public class EmployeeTableFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Employee> employeeList;

    public EmployeeTableFrame() {
        setTitle("MotorPH Employee List");
        setSize(800, 400);
        setLayout(null);

        String[] columns = {"Employee ID", "Last Name", "First Name", "SSS", "PhilHealth", "TIN", "Pag-IBIG"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 740, 200);
        add(scrollPane);

        JButton btnView = new JButton("View Employee");
        btnView.setBounds(20, 240, 150, 30);
        add(btnView);

        JButton btnAdd = new JButton("New Employee");
        btnAdd.setBounds(180, 240, 150, 30);
        add(btnAdd);

        loadEmployees();

        btnView.addActionListener(e -> viewSelectedEmployee());
        btnAdd.addActionListener(e -> openNewEmployeeForm());
    }

    private void loadEmployees() {
        employeeList = CSVHandler.loadEmployees("data/employee.csv");
        tableModel.setRowCount(0); // Clear
        for (Employee emp : employeeList) {
            GovernmentDetails gov = emp.getGovernmentDetails();
            tableModel.addRow(new Object[]{
                emp.getEmployeeId(), emp.getLastName(), emp.getFirstName(),
                gov.getSssNumber(), gov.getPhilHealthNumber(),
                gov.getTin(), gov.getPagIbigNumber()
            });
        }
    }

    private void viewSelectedEmployee() {
        int row = table.getSelectedRow();
        if (row != -1) {
            Employee emp = employeeList.get(row);
            new ViewEmployeeFrame(emp).setVisible(true);
        }
    }

    private void openNewEmployeeForm() {
        new NewEmployeeForm(this).setVisible(true);
    }

    public void refreshTable() {
        loadEmployees();
    }
}
    

