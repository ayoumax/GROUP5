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
import java.util.List;

public class ViewEmployeeFrame extends JFrame {

    private JComboBox<String> cmbMonth;
    private JComboBox<String> cmbWeek;
    private JTextArea txtPayslip;
    private Employee employee;

    public ViewEmployeeFrame(Employee emp) {
        this.employee = emp;

        setTitle("Payslip Details");
        setSize(620, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 10));
        getContentPane().setBackground(Color.WHITE);

        // Top Panel with Grid
        JPanel topPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        topPanel.setBackground(Color.WHITE);

        JLabel lblEmpId = new JLabel("Employee ID:");
        JLabel lblIdValue = new JLabel(String.valueOf(emp.getEmployeeId()));

        lblEmpId.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblIdValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        topPanel.add(lblEmpId);
        topPanel.add(lblIdValue);

        JLabel lblMonth = new JLabel("Select Month:");
        lblMonth.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cmbMonth = new JComboBox<>();
        cmbMonth.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        topPanel.add(lblMonth);
        topPanel.add(cmbMonth);

        JLabel lblWeek = new JLabel("Select Week:");
        lblWeek.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cmbWeek = new JComboBox<>();
        cmbWeek.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        topPanel.add(lblWeek);
        topPanel.add(cmbWeek);

        JButton btnGenerate = new JButton("Compute");
        styleButton(btnGenerate);

        topPanel.add(new JLabel()); // empty cell
        topPanel.add(btnGenerate);

        add(topPanel, BorderLayout.NORTH);

        // === Payslip Text Area ===
        txtPayslip = new JTextArea();
        txtPayslip.setFont(new Font("Consolas", Font.PLAIN, 13));
        txtPayslip.setEditable(false);
        txtPayslip.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(new JScrollPane(txtPayslip), BorderLayout.CENTER);

        // === Bottom Button ===
        JButton btnBack = new JButton("Back to Dashboard");
        styleButton(btnBack);
        btnBack.setPreferredSize(new Dimension(200, 40));

        JPanel southPanel = new JPanel();
        southPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        southPanel.setBackground(Color.WHITE);
        southPanel.add(btnBack);
        add(southPanel, BorderLayout.SOUTH);

        // === Action Logic ===
        btnBack.addActionListener(e -> {
            new EmployeeTableFrame().setVisible(true);
            dispose();
        });

        btnGenerate.addActionListener(e -> generatePayslip());

        cmbMonth.addActionListener(e -> populateWeeks());

        populateMonths();
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(45, 140, 240));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
    }

    private void populateMonths() {
        cmbMonth.removeAllItems();
        List<String> months = PayrollCalculator.getAvailableMonthsForEmployee(employee.getEmployeeId());
        for (String m : months) {
            cmbMonth.addItem(m);
        }
        if (cmbMonth.getItemCount() > 0) {
            cmbMonth.setSelectedIndex(0);
            populateWeeks();
        }
    }

    private void populateWeeks() {
        cmbWeek.removeAllItems();
        String selectedMonth = (String) cmbMonth.getSelectedItem();
        if (selectedMonth != null) {
            List<String> weeks = PayrollCalculator.getWeeksForEmployeeMonth(employee.getEmployeeId(), selectedMonth);
            for (String w : weeks) {
                cmbWeek.addItem(w);
            }
        }
    }

    private void generatePayslip() {
        String selectedWeek = (String) cmbWeek.getSelectedItem();
        if (selectedWeek != null) {
            String payslip = PayrollCalculator.generatePayslip(employee.getEmployeeId(), (String) cmbMonth.getSelectedItem(), selectedWeek);
            txtPayslip.setText(payslip);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a valid week.");
        }
    }
}
        
