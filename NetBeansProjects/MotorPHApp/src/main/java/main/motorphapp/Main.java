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
        // Create employee
        Employee emp = new Employee(1, "Aldrin John Tamayo", 25, "Developer", 30000f);

        // Apply leave
        emp.applyLeave();

        // Calculate tax
        Tax tax = new Tax(1, emp.getEmployeeId(), 0.12f);
        tax.calculateTax(emp.getSalary());

        // Create payroll
        Payroll payroll = new Payroll(101, emp.getEmployeeId(), 30000f, 5000f, tax.getTaxAmount());
        payroll.calculateSalary();

        // Print formatted payslip
        System.out.println("===============================");
        System.out.println("        EMPLOYEE PAYSLIP       ");
        System.out.println("===============================");
        System.out.println("Employee ID   : " + emp.getEmployeeId());
        System.out.println("Name          : " + emp.getName());
        System.out.println("Position      : " + emp.getPosition());
        System.out.println("Gross Salary  : PHP " + emp.getSalary());
        System.out.println("Allowance     : PHP 5000.0");
        System.out.println("Tax (12%)     : PHP " + tax.getTaxAmount());
        System.out.println("Net Salary    : PHP " + payroll.getNetSalary());
        System.out.println("===============================");
        System.out.println("Leave Status  : Leave requested");
        System.out.println("===============================");
    }
}
