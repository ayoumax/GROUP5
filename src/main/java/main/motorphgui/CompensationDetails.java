/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphgui;

/**
 *
 * @author WINDOWS 10
 */
public class CompensationDetails {
     private float basicSalary;
    private float allowance;
    
    public CompensationDetails(float basicSalary, float allowance) {
        this.basicSalary = basicSalary;
        this.allowance = allowance;
    }

   public float getBasicSalary() {
        return basicSalary;
    }

    public float getAllowance() {
        return allowance;
    }

    public float getTotalCompensation() {
        return basicSalary + allowance;
    }

    void setAllowance(float allowance) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
