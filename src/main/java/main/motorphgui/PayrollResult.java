/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphgui;

/**
 *
 * @author Macky
 */
public class PayrollResult {
    public String employeeId;
    public String fullName;
    public String position;
    public String supervisor;
    public String payPeriod;
    public double hourlyRate;
    public double monthlySalary;

    public double grossPay;
    public double overtimePay;
    public int tardinessMinutes;

    public double riceAllowance;
    public double phoneAllowance;
    public double clothingAllowance;

    public double sssDeduction;
    public double philhealthDeduction;
    public double pagibigDeduction;
    public double withholdingTax;

    public double totalGovtDeductions;
    public double totalDeductions;
    public double netPay;

    public PayrollResult() {}
}
