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

        setTitle("MotorPH View Employee Details");
        setSize(620, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 10));
        getContentPane().setBackground(Color.WHITE);

        // Top Panel with Grid
        JPanel topPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        topPanel.setBackground(Color.WHITE);

        JLabel empID = new JLabel("Employee ID:");
        JLabel idValue = new JLabel(String.valueOf(emp.getEmployeeId()));
        empID.setFont(new Font("Segoe UI", Font.BOLD, 14));
        idValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        topPanel.add(empID);
        topPanel.add(idValue);
        
        JLabel lastName = new JLabel("Last Name:");
        JLabel lastValue = new JLabel(String.valueOf(emp.getLastName()));
        lastName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lastValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        topPanel.add(lastName);
        topPanel.add(lastValue);
        
        JLabel firstName = new JLabel("First Name:");
        JLabel firstValue = new JLabel(String.valueOf(emp.getFirstName()));
        firstName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        firstValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        topPanel.add(firstName);
        topPanel.add(firstValue);
        
        JLabel position = new JLabel("Position:");
        JLabel positionValue = new JLabel(String.valueOf(emp.getPosition()));
        position.setFont(new Font("Segoe UI", Font.BOLD, 14));
        positionValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        topPanel.add(position);
        topPanel.add(positionValue);
        
        JLabel imSupervisor = new JLabel("Immediate Supervisor:");
        JLabel supervisorValue = new JLabel(String.valueOf(emp.getImmediateSupervisor()));
        imSupervisor.setFont(new Font("Segoe UI", Font.BOLD, 14));
        supervisorValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        topPanel.add(imSupervisor);
        topPanel.add(supervisorValue);
        
        JLabel sss = new JLabel("SSS Number:");
        JLabel sssValue = new JLabel(String.valueOf(emp.getGovernmentDetails().getSssNumber()));
        sss.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sssValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        topPanel.add(sss);
        topPanel.add(sssValue);
        
        JLabel philNumber = new JLabel("Philhealth Number:");
        JLabel philValue = new JLabel(String.valueOf(emp.getGovernmentDetails().getPhilHealthNumber()));
        philNumber.setFont(new Font("Segoe UI", Font.BOLD, 14));
        philValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        topPanel.add(philNumber);
        topPanel.add(philValue);
        
        JLabel tinNumber = new JLabel("TIN:");
        JLabel tinValue = new JLabel(String.valueOf(emp.getGovernmentDetails().getTinNumber()));
        tinNumber.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tinValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        topPanel.add(tinNumber);
        topPanel.add(tinValue);
        
        JLabel pagNumber = new JLabel("Pag-IBIG Number:");
        JLabel pagValue = new JLabel(String.valueOf(emp.getGovernmentDetails().getPagIbigNumber()));
        pagNumber.setFont(new Font("Segoe UI", Font.BOLD, 14));
        pagValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        topPanel.add(pagNumber);
        topPanel.add(pagValue);
        
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
        
