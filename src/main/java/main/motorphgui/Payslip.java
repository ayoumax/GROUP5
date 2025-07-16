/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphgui;

/**
 *
 * @author Kristel
 */

import main.motorphgui.GovernmentDetails;

public class Payslip {
    private int employeeId;
    private float grossPay;
    private float tax;
    private float netPay;
    private String sssNumber;
    private String philHealthNumber;
    private String tin;
    private String pagIbigNumber;
    private String govDetails;
    private String witholdingTax;
    private double governmentDeduction;
    

    public Payslip(int employeeId, float grossPay, double withholdingTax, float netPay) {
        this.employeeId = employeeId;
        this.grossPay = grossPay;
        this.witholdingTax = witholdingTax;
        this.netPay = netPay;
    }

    // Getters and Setters
    
}
