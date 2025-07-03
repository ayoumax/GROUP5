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
    private float hourlyRate; // per hour
    private GovernmentDetails govDetails; 
    private CompensationDetails compensation;
  
 
    public Employee(int employeeId, String lastName, String firstName, float basicSalary, GovernmentDetails governmentDetails, CompensationDetails compensationDetails) {
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.hourlyRate = basicSalary;
        this.govDetails = governmentDetails;
        this.compensation = compensationDetails;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
     public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
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
        return hourlyRate;// per hour
    }

    public void setSalary(float salary) {
        this.hourlyRate = salary;
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
    public Payslip viewPayslip(float hoursWorked) {
        float grossPay = (hourlyRate* hoursWorked) + compensation.getAllowance();
        float tax = calculateTax(grossPay);
        float netPay = grossPay - tax;
        return new Payslip(this.employeeId, grossPay, tax, netPay);
    }

    public boolean updateProfile(String newLastName,String newFirstName, int newAge, String newPosition, float newSalary) {
        this.lastName = newLastName;
        this.firstName = newFirstName;
        this.age = newAge;
        this.position = newPosition;
        this.hourlyRate = newSalary;
        return true;
    }

    public Leave applyLeave(LocalDate startDate, LocalDate endDate, String reason) {
        return new Leave(0, employeeId, startDate, endDate, "Pending");
    }

    public float calculateTax(float grossPay) {
    
        float taxAmount;
        if (grossPay <= 20833) {
            taxAmount = 0;
        } else if (grossPay <= 33332) {
            taxAmount = (grossPay - 20833) * 0.20f;
        } else if (grossPay <= 66666) {
            taxAmount = 2500 + (grossPay - 33333) * 0.25f;
        } else {
            taxAmount = 10833 + (grossPay - 66667) * 0.30f;
        }
        return taxAmount;
    }

    public List<Float> viewTaxableDetails(float hoursWorked) {
    float grossPay = (hourlyRate * hoursWorked) + compensation.getAllowance();
    float tax = calculateTax(grossPay);
    return List.of(grossPay, tax);
    }

    public CompensationDetails getCompensationDetails() {
    return compensation;
    }
    public void setGovernmentDetails(GovernmentDetails gov) {
    this.govDetails = gov;
}
}
