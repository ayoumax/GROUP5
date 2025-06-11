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
    private JTextField txtLastName, txtFirstName, txtSSS, txtPhil, txtTIN, txtPagIbig;
    private JButton btnUpdate, btnDelete;
    private DefaultTableModel tableModel;
    private List<Employee> employeeList;

    public EmployeeTableFrame() {
        setTitle("MotorPH Employee List");
        setSize(800, 500);
        setLayout(null);
//JTable set up
        String[] columns = {"Employee ID", "Last Name", "First Name", "SSS", "PhilHealth", "TIN", "Pag-IBIG"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 740, 200);
        add(scrollPane);

        JButton btnView = new JButton("View Employee");
        btnView.setBounds(20, 240, 150, 30);
        add(btnView);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(180, 240, 150, 30);
        add(btnAdd);
        
        // Update and Delete buttons
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(340, 240, 150, 30);
        btnUpdate.setEnabled(false);
        add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(500, 240, 150, 30);
        btnDelete.setEnabled(false);
        add(btnDelete);

        loadEmployees();

        btnView.addActionListener(e -> viewSelectedEmployee());
        btnAdd.addActionListener(e -> openNewEmployeeForm());
        
   // Labels and TextFields
        txtLastName = new JTextField(); txtFirstName = new JTextField();
        txtSSS = new JTextField(); txtPhil = new JTextField();
        txtTIN = new JTextField(); txtPagIbig = new JTextField();

        int y = 280;
        int height = 25;
        String[] labels = {"Last Name", "First Name", "SSS", "PhilHealth", "TIN", "Pag-IBIG"};
        JTextField[] fields = {txtLastName, txtFirstName, txtSSS, txtPhil, txtTIN, txtPagIbig};

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i] + ":");
            lbl.setBounds(20 + (i % 3) * 250, y + (i / 3) * 40, 100, height);
            fields[i].setBounds(110 + (i % 3) * 250, y + (i / 3) * 40, 120, height);
            add(lbl); add(fields[i]);
        }

        
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
            GovernmentDetails gov = emp.getGovernmentDetails();
             txtLastName.setText(emp.getLastName());
        txtFirstName.setText(emp.getFirstName());
        txtSSS.setText(gov.getSssNumber());
        txtPhil.setText(gov.getPhilHealthNumber());
        txtTIN.setText(gov.getTin());
        txtPagIbig.setText(gov.getPagIbigNumber());

        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        btnUpdate.addActionListener(e -> updateEmployee());
        btnDelete.addActionListener(e -> deleteEmployee());
    }
}

    private void openNewEmployeeForm() {
        new NewEmployeeForm(this).setVisible(true);
    }

    public void refreshTable() {
        loadEmployees();
    }
    private void updateEmployee() {
    int row = table.getSelectedRow();
    if (row != -1) {
        Employee emp = employeeList.get(row);
        emp.setLastName(txtLastName.getText());
        emp.setFirstName(txtFirstName.getText());
        GovernmentDetails gov = emp.getGovernmentDetails();
        gov.setSssNumber(txtSSS.getText());
        gov.setPhilHealthNumber(txtPhil.getText());
        gov.setTin(txtTIN.getText());
        gov.setPagIbigNumber(txtPagIbig.getText());

        CSVHandler.saveEmployees(employeeList);
        refreshTable();
        clearFields();
        JOptionPane.showMessageDialog(this, "Employee updated successfully!");
    } else {
        JOptionPane.showMessageDialog(this, "No employee selected.");
    }
}

    private void deleteEmployee() {
    int row = table.getSelectedRow();
    if (row != -1) {
        employeeList.remove(row);
        CSVHandler.saveEmployees(employeeList);
        refreshTable();
        clearFields();
    }
}

    private void clearFields() {
    txtLastName.setText(""); txtFirstName.setText("");
    txtSSS.setText(""); txtPhil.setText("");
    txtTIN.setText(""); txtPagIbig.setText("");
    btnUpdate.setEnabled(false);
    btnDelete.setEnabled(false);
    
}
    
}
    

