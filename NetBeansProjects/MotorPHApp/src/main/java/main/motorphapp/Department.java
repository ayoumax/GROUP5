/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphapp;

/**
 *
 * @author Ayou
 */
import java.util.ArrayList;

public class Department {

    private int departmentId;
    private String departmentName;
    private int managerId;
    private ArrayList<Employee> employees = new ArrayList<>();

    public Department(int departmentId, String departmentName, int managerId) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.managerId = managerId;
    }

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    public void removeEmployee(Employee e) {
        employees.remove(e);
    }

    public void getDepartmentDetails() {
        System.out.println("Department: " + departmentName + " | Manager ID: " + managerId);
    }
}
