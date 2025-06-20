/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphgui;

import java.time.LocalDate;
import java.util.List;
import main.motorphgui.CompensationDetails;
import main.motorphgui.GovernmentDetails;
import main.motorphgui.Payslip;

/**
 *
 * @author Ayou
 */
public class Employee {

    private int employeeId;
    private String lastName;
    private String firstName;
    private int age;
    private String position;
    private float salary; // per day
    private GovernmentDetails govDetails; 
    private CompensationDetails compensation;
    float calculateTax;
 

    public Employee(int employeeId, String lastName, String firstName,int age, String position, float salary, 
            GovernmentDetails govDetails, CompensationDetails compensation) {
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.position = position;
        this.salary = salary;
         this.govDetails = govDetails;
        this.compensation = compensation;
    }

    Employee(int id, String fullName, int age, String position, float salary, GovernmentDetails gov, CompensationDetails comp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        // Getters and Setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastName;
    }
     public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public float getSalary() {
        return salary;// per day salary
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public GovernmentDetails getGovDetails() {
        return govDetails;
    }

    public void setGovDetails(GovernmentDetails govDetails) {
        this.govDetails = govDetails;
    }

    public CompensationDetails getCompensation() {
        return compensation;
    }

    public void setCompensation(CompensationDetails compensation) {
        this.compensation = compensation;
    }

    // Functionality methods
    public Payslip viewPayslip() {
        float tax = calculateTax();
        float netPay = salary - tax;
        return new Payslip(this.employeeId, salary, tax, netPay);
    }

    public boolean updateProfile(String newLastName,String newFirstName, int newAge, String newPosition, float newSalary) {
        this.lastName = newLastName;
        this.firstName = newFirstName;
        this.age = newAge;
        this.position = newPosition;
        this.salary = newSalary;
        return true;
    }

    public Leave applyLeave(LocalDate startDate, LocalDate endDate, String reason) {
        return new Leave(0, employeeId, startDate, endDate, "Pending");
    }

    public float calculateTax() {
        float taxAmount;
        if (salary <= 20833) {
            taxAmount = 0;
        } else if (salary <= 33332) {
            taxAmount = (salary - 20833) * 0.20f;
        } else if (salary <= 66666) {
            taxAmount = 2500 + (salary - 33333) * 0.25f;
        } else {
            taxAmount = 10833 + (salary - 66667) * 0.30f;
        }
        return taxAmount;
    }

    public List<Float> viewTaxableDetails() {
        return List.of(salary, calculateTax());
    }

    public CompensationDetails getCompensationDetails() {
    return compensation;
    }

    public GovernmentDetails getGovernmentDetails() {
    return govDetails;
}
}