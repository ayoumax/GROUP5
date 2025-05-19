/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphgui;

/**
 *
 * @author Ayou
 */
public class Payroll {

    private int payrollId;
    private int employeeId;
    private float basicSalary;
    private float allowances;
    private float deductions;
    private float netSalary;

    public Payroll(int payrollId, int employeeId, float basicSalary, float allowances, float deductions) {
        this.payrollId = payrollId;
        this.employeeId = employeeId;
        this.basicSalary = basicSalary;
        this.allowances = allowances;
        this.deductions = deductions;
    }

    public void calculateSalary() {
        netSalary = basicSalary + allowances - deductions;
    }

    public float getNetSalary() {
        return netSalary;
    }
}
