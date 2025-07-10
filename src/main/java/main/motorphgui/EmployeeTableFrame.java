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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EmployeeTableFrame extends JFrame {

    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private List<Employee> employeeList; 
    private final String csvFilePath = "data/employee.csv";

    public EmployeeTableFrame() {
        setTitle("MotorPH Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        // Table Header
        String[] columnNames = {"Employee ID", "Last Name", "First Name", "SSS", "PhilHealth", "TIN", "Pag-IBIG"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        employeeTable = new JTable(tableModel);
        employeeTable.setRowHeight(28);
        employeeTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        employeeTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        employeeTable.setGridColor(Color.LIGHT_GRAY);
        employeeTable.setShowGrid(true);
        employeeTable.setSelectionBackground(new Color(220, 235, 252));
        employeeTable.setSelectionForeground(Color.BLACK);
        employeeTable.getTableHeader().setReorderingAllowed(false);

        employeeTable.setPreferredScrollableViewportSize(null);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        loadEmployees();

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        tablePanel.setBackground(Color.WHITE);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        int tableHeight = employeeTable.getRowCount() * employeeTable.getRowHeight();
        tablePanel.setPreferredSize(new Dimension(920, 800));

        // === Button Panel ===
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        buttonPanel.setBackground(Color.WHITE);

        String[] buttonLabels = {"View Payslip", "Add Employee", "Update Employee", "Delete Employee", "Refresh"};
        JButton[] buttons = new JButton[buttonLabels.length];
        Color primary = new Color(45, 140, 240);

        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFocusPainted(false);
            buttons[i].setBackground(primary);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFont(new Font("Segoe UI", Font.BOLD, 13));
            buttons[i].setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
            buttonPanel.add(buttons[i]);
        }

        JButton btnViewPayslip = buttons[0];
        JButton btnAdd = buttons[1];
        JButton btnUpdate = buttons[2];
        JButton btnDelete = buttons[3];
        JButton btnRefresh = buttons[4];

        // === Add listeners ===
        btnViewPayslip.addActionListener(e -> viewPayslip());
        btnAdd.addActionListener(e -> new NewEmployeeForm(this).setVisible(true));
        btnUpdate.addActionListener(e -> updateEmployee());
        btnDelete.addActionListener(e -> deleteEmployee());
        btnRefresh.addActionListener(e -> {
            loadEmployees();
            adjustTableHeight();
        });

        // === Final Layout ===
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        JButton btnLogout = new JButton("Logout");
        btnLogout.setFocusPainted(false);
        btnLogout.setBackground(new Color(200, 50, 50));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLogout.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                SwingUtilities.invokeLater(() -> new LoginGUI());
            }
        });
        
        buttonPanel.add(btnLogout);

        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        adjustTableHeight();
        setSize(950, 850);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void loadEmployees() {
        try {
            employeeList = CSVHandler.loadEmployees("data/employee.csv");
            refreshTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading employee data:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        for (Employee emp : employeeList) {
            tableModel.addRow(new Object[]{
                emp.getEmployeeId(),
                emp.getLastName(),
                emp.getFirstName(),
                emp.getGovernmentDetails().getSssNumber(),
                emp.getGovernmentDetails().getPhilHealthNumber(),
                emp.getGovernmentDetails().getTinNumber(),
                emp.getGovernmentDetails().getPagIbigNumber()
            });
        }
    }

    public void adjustTableHeight() {
        int rowCount = tableModel.getRowCount();
        int rowHeight = employeeTable.getRowHeight();
        int headerHeight = employeeTable.getTableHeader().getPreferredSize().height;
        int totalHeight = (rowCount * rowHeight) + headerHeight + 20;

        Dimension size = new Dimension(employeeTable.getPreferredSize().width, totalHeight);
        employeeTable.setPreferredSize(size);
        pack();
    }

    private void viewPayslip() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            int empId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());

            Employee selectedEmployee = employeeList.stream()
                    .filter(e -> e.getEmployeeId() == empId)
                    .findFirst()
                    .orElse(null);

            if (selectedEmployee != null) {
                SwingUtilities.invokeLater(() -> {
                    ViewEmployeeFrame frame = new ViewEmployeeFrame(selectedEmployee);
                    frame.setVisible(true);
                    dispose();
                });
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee");
        }
    }

    private void updateEmployee() {
    int selectedRow = employeeTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select an employee to update.");
        return;
    }

    int empId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());

    Employee selectedEmp = null;
    for (Employee emp : employeeList) {
        if (emp.getEmployeeId() == empId) {
            selectedEmp = emp;
            break;
        }
    }

    if (selectedEmp != null) {
        UpdateEmployeeFrame updateFrame = new UpdateEmployeeFrame(selectedEmp, employeeList, csvFilePath, this);
        updateFrame.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Selected employee not found.");
    }
}

    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this employee?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int empId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                employeeList.removeIf(e -> e.getEmployeeId() == empId); // FIX: use employeeList
                CSVHandler.saveEmployees(employeeList, "data/employee.csv");
                refreshTable();
                adjustTableHeight();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.");
        }
    }
}
    

