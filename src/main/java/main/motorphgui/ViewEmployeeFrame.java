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
/**
 *
 * @author WINDOWS 10
 */
public class ViewEmployeeFrame extends JFrame{
    private Employee employee;
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

        JButton computeBtn = new JButton("Compute");
        computeBtn.setBounds(20, 100, 100, 30);
        add(computeBtn);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane pane = new JScrollPane(resultArea);
        pane.setBounds(20, 140, 340, 200);
        add(pane);

        computeBtn.addActionListener(e -> computePay());
    }

    private void computePay() {
        String selectedMonth = (String) monthBox.getSelectedItem(); // ✅ show month
        float tax = employee.calculateTax();
        float gross = employee.getSalary() * 22 + employee.getCompensation().getAllowance(); // 22 days default
        Payroll payroll = new Payroll(1, employee.getEmployeeId(), gross, 0, tax);
        payroll.calculateSalary();

        resultArea.setText("Month: " + selectedMonth +
                "\nPosition: " + employee.getPosition() +
                "\nGross: ₱" + gross +
                "\nTax: ₱" + tax +
                "\nNet: ₱" + payroll.getNetSalary());
    }
}
    