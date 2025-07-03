/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphgui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
/**
 *
 * @author WINDOWS 10
 */
public class ViewEmployeeFrame extends JFrame{
    private Employee employee;
    private JComboBox<String> yearBox;
    private JComboBox<String> monthBox;
    private JTextArea resultArea;

    public ViewEmployeeFrame(Employee emp) {
       this.employee = emp;
        setTitle("View Employee Details");
        setSize(400, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ✅ Close properly

        JLabel label = new JLabel("Employee: " + emp.getFirstName() + " " + emp.getLastName());
        label.setBounds(20, 20, 300, 25);
        add(label);

        JLabel monthLabel = new JLabel("Select Month:");
        monthLabel.setBounds(20, 60, 100, 25);
        add(monthLabel);

        monthBox = new JComboBox<>(new String[]{
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        });
        monthBox.setBounds(120, 60, 200, 25);
        add(monthBox);
        
        String[] years = new String[11];
        for (int i = 0; i < 5; i++) {
            years[i] = String.valueOf(2024 + i);
        }
        yearBox = new JComboBox<>(years);
        yearBox.setBounds(120, 90, 200, 25);
        add(yearBox);

        JButton computeBtn = new JButton("Compute");
        computeBtn.setBounds(20, 130, 100, 30);
        add(computeBtn);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane pane = new JScrollPane(resultArea);
        pane.setBounds(20, 170, 340, 180);
        add(pane);

        computeBtn.addActionListener(e -> computePay());
    }

    public float calculateMonthlyHoursWorked(int empId, int month, int year, List<AttendanceRecord> records) {
    float totalHours = 0;
    for (AttendanceRecord record : records) {
        if (record.getEmployeeId() == empId &&
            record.getDate().getMonthValue() == month &&
            record.getDate().getYear() == year) {
            totalHours += record.getTotalHoursWorked();
        }
    }
    return totalHours;
}
    private void computePay() {
        String selectedMonth = (String) monthBox.getSelectedItem(); // ✅ show month
        String selectedYear = (String) yearBox.getSelectedItem();
        int month = monthBox.getSelectedIndex() + 1;
        int year = Integer.parseInt(selectedYear);
    // Load attendance records
        List<AttendanceRecord> logs = AttendanceLoader.loadAttendanceRecords("data/attendance.csv");
        float hoursWorked = calculateMonthlyHoursWorked(employee.getEmployeeId(), month, year, logs);
        String hoursStr = String.format("%.2f", hoursWorked); 
        float hourlyRate = employee.getSalary();
        float allowance = employee.getCompensation().getAllowance();
        float gross = hourlyRate * hoursWorked + allowance;
        float tax = employee.calculateTax(gross);
        float net = gross - tax;
       

        resultArea.setText("Payslip for: " + selectedMonth + " " + selectedYear +
            "\nPosition: " + employee.getPosition() +
            "\nHourly Rate: ₱" + String.format("%.2f", hourlyRate) +
            "\nHours Worked: " + hoursStr +
            "\nAllowance: ₱" + String.format("%.2f", allowance) +
            "\n\nGross Pay: ₱" + String.format("%.2f", gross) +
            "\nTax: ₱" + String.format("%.2f", tax) +
            "\nNet Pay: ₱" + String.format("%.2f", net));
        
       
    }
}
        
