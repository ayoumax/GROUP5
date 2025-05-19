/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphgui;

/**
 *
 * @author Ayou
 */
public class Employee {

    private int employeeId;
    private String name;
    private int age;
    private String position;
    private float salary; // per day

    public Employee(int employeeId, String name, int age, String position, float salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.age = age;
        this.position = position;
        this.salary = salary;
    }

    public float getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }
}
