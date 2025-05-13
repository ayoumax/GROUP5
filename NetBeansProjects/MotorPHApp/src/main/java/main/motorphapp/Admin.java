/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphapp;

/**
 *
 * @author Ayou
 */
public class Admin {

    private int adminId;
    private String name;
    private String privileges;

    public Admin(int adminId, String name, String privileges) {
        this.adminId = adminId;
        this.name = name;
        this.privileges = privileges;
    }

    public void manageEmployee() {
        System.out.println(name + " is managing employees.");
    }

    public void managePayroll() {
        System.out.println(name + " is managing payroll.");
    }

    public void manageDepartment() {
        System.out.println(name + " is managing departments.");
    }
}
