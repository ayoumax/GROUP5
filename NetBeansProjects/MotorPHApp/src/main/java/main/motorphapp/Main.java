/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphapp;

/**
 *
 * @author Ayou
 */
public class Main {

    public static void main(String[] args) {
        Employee emp = new Employee(1, "Aldrin John Tamayo", 25, "Developer", 30000f);
        emp.viewPayslip();
        emp.applyLeave();

        Tax tax = new Tax(1, emp.getEmployeeId(), 0.12f);
        tax.calculateTax(emp.getSalary());
        tax.viewTaxableDetails();

        Payroll payroll = new Payroll(101, emp.getEmployeeId(), 30000f, 5000f, tax.getTaxAmount());
        payroll.calculateSalary();
        payroll.generatePayslip();
    }
}
